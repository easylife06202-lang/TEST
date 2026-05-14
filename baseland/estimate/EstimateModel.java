/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.io.FileUtils
 */
package com.wfusion.baseland.estimate;

import com.wfusion.baseland.BaseLandBean;
import com.wfusion.baseland.QueryBean;
import com.wfusion.baseland.SQLiteDataProviderModel;
import com.wfusion.baseland.basic.Model;
import com.wfusion.baseland.estimate.EstimateReport3Model;
import com.wfusion.dataaccess.dao.DaoBase;
import com.wfusion.dataaccess.vo.VoBase;
import com.wfusion.datasources.ConnectionFactory;
import com.wfusion.fx.util.ExceptionDialog;
import com.wfusion.fx.util.JavaFXUtil;
import com.wfusion.util.DateTime;
import com.wfusion.util.OptionPair;
import com.wfusion.util.SUtility;
import com.wfusion.util.SqlBuilder;
import com.wfusion.util.SqlUtil;
import com.wfusion.util.StringProcess;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import moiland.baseland.action.bean.BaseLandMainParamBean;
import moiland.baseland.action.bean.BaseLandSellParamBean;
import moiland.baseland.appraiser.AppraiserExport;
import moiland.baseland.bo.BaseLandMainBo;
import moiland.baseland.bo.BaseLandSellBo;
import moiland.baseland.bo.SaveCheckBo;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_AHP;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_APPRAISAL;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_APPRAISALA3_SCORE;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_DEVELOP;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_DEVELOP_EXT;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_FACTOR_CODE;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_FLOOR_EFFECT;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_IMAGES;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_MAIN;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_RENT;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_RENT_EXT;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_RENT_MONTH;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_REPORT_PARAM;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_SELL;
import moiland.baseland.dataaccess.ndao.NDAO_SRKEYN_ALL;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_AHP;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_APPRAISAL;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_APPRAISALA3_SCORE;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_DEVELOP;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_DEVELOP_EXT;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_FACTOR_CODE;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_FLOOR_EFFECT;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_IMAGES;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_MAIN;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT_EXT;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT_MONTH;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_REPORT_PARAM;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_SELL;
import moiland.baseland.factor.bean.FactorLevelBean;
import moiland.baseland.factor.em.EnumFactorStdType;
import moiland.baseland.flooreffect.bean.BaseLandFloorEffectFormBean;
import moiland.baseland.print.BaseLandPrintALL;
import moiland.baseland.util.BaseLandBuildStdPriceAdjustRateFillHelper;
import moiland.baseland.util.BaseLandCompareFactorListHelper;
import moiland.baseland.util.BaseLandInstruStdPriceTool;
import moiland.baseland.util.BaseLandParamFillHelper;
import moiland.baseland.verify.BaseLandVerifyUtil;
import moiland.baseland.verify.VerifyException;
import org.apache.commons.io.FileUtils;

public class EstimateModel
extends Model {
    public static BaseLandBean BASELANDBEAN = new BaseLandBean();
    private NDAO_BASELAND_MAIN ndao_main = new NDAO_BASELAND_MAIN();
    private NDAO_BASELAND_APPRAISALA3_SCORE ndao_score = new NDAO_BASELAND_APPRAISALA3_SCORE();
    private NDAO_BASELAND_SELL ndao_sell = new NDAO_BASELAND_SELL();
    private NDAO_BASELAND_RENT ndao_rent = new NDAO_BASELAND_RENT();
    private NDAO_BASELAND_RENT_MONTH ndao_rent_month = new NDAO_BASELAND_RENT_MONTH();
    private NDAO_BASELAND_RENT_EXT ndao_rent_ext = new NDAO_BASELAND_RENT_EXT();
    private NDAO_BASELAND_FLOOR_EFFECT ndao_floor_effect = new NDAO_BASELAND_FLOOR_EFFECT();
    Map<String, FactorLevelBean> factorRegional = new LinkedHashMap<String, FactorLevelBean>();
    Map<String, FactorLevelBean> factorIndividual = new LinkedHashMap<String, FactorLevelBean>();
    Map<String, NVO_BASELAND_FACTOR_CODE> isAutoSwitchLevel = new LinkedHashMap<String, NVO_BASELAND_FACTOR_CODE>();
    public static String reginVersion = "";
    public static String individualVersion = "";
    public static String factorVer = "";
    public static String imagePath = "";
    public static Map<String, Boolean> col_update = new HashMap<String, Boolean>();

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ArrayList<QueryBean> getBandMainNum(QueryBean queryBean) {
        Connection connection = null;
        ArrayList<QueryBean> arrayList = new ArrayList<QueryBean>();
        try {
            connection = this.getConnection();
            if (connection == null) {
                JavaFXUtil.showErrorMessageBox("\u8cc7\u6599\u5eab\u9023\u7dda\u932f\u8aa4");
                ArrayList<QueryBean> arrayList2 = null;
                return arrayList2;
            }
            ArrayList<NVO_BASELAND_MAIN> arrayList3 = this.ndao_main.getBandMainVos(queryBean.AA45, queryBean.AA46, queryBean.year, queryBean.urban, connection);
            for (NVO_BASELAND_MAIN nVO_BASELAND_MAIN : arrayList3) {
                QueryBean queryBean2 = new QueryBean();
                queryBean2.AA45 = queryBean.AA45;
                queryBean2.office = queryBean.office;
                queryBean2.AA46 = queryBean.AA46;
                queryBean2.year = queryBean.year;
                queryBean2.urban = nVO_BASELAND_MAIN.getUrban();
                queryBean2.baseno = nVO_BASELAND_MAIN.getBaseno();
                queryBean2.mode = "edit";
                arrayList.add(queryBean2);
            }
        }
        catch (Exception exception) {
            ExceptionDialog.show(exception);
        }
        finally {
            SqlUtil.close(connection);
        }
        return arrayList;
    }

    public void updateMainVoFromDB() {
        Connection connection = null;
        try {
            connection = this.getVersionConnection();
            NVO_BASELAND_MAIN nVO_BASELAND_MAIN = new NVO_BASELAND_MAIN();
            nVO_BASELAND_MAIN.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
            nVO_BASELAND_MAIN.setBaseno(EstimateModel.BASELANDBEAN.queryBean.baseno);
            EstimateModel.BASELANDBEAN.voMain = (NVO_BASELAND_MAIN)this.ndao_main.findByPk(nVO_BASELAND_MAIN, connection);
            EstimateModel.BASELANDBEAN.queryBean.office = SQLiteDataProviderModel.getOfficeFromSect(EstimateModel.BASELANDBEAN.queryBean.AA45, EstimateModel.BASELANDBEAN.voMain.getAa48());
            EstimateModel.BASELANDBEAN.queryBean.version = EstimateModel.BASELANDBEAN.voMain.getVersion();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
    }

    public void updateBaseLandImages() {
        Connection connection = null;
        try {
            connection = this.getVersionConnection();
            NVO_BASELAND_IMAGES nVO_BASELAND_IMAGES = new NVO_BASELAND_IMAGES();
            nVO_BASELAND_IMAGES.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
            nVO_BASELAND_IMAGES.setBaseno(EstimateModel.BASELANDBEAN.queryBean.baseno);
            nVO_BASELAND_IMAGES.setPhoto_type("SKT");
            EstimateModel.BASELANDBEAN.voImages = (NVO_BASELAND_IMAGES)new NDAO_BASELAND_IMAGES().findByPk(nVO_BASELAND_IMAGES, connection);
            if (EstimateModel.BASELANDBEAN.voImages == null) {
                EstimateModel.BASELANDBEAN.voImages = new NVO_BASELAND_IMAGES();
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
    }

    public void updateAppRaVoFromDB() {
        Connection connection = null;
        try {
            connection = this.getVersionConnection();
            NVO_BASELAND_APPRAISAL nVO_BASELAND_APPRAISAL = new NVO_BASELAND_APPRAISAL();
            nVO_BASELAND_APPRAISAL.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
            nVO_BASELAND_APPRAISAL.setBaseno(EstimateModel.BASELANDBEAN.queryBean.baseno);
            EstimateModel.BASELANDBEAN.voAppRaMain = (NVO_BASELAND_APPRAISAL)new NDAO_BASELAND_APPRAISAL().findByPk(nVO_BASELAND_APPRAISAL, connection);
            if (EstimateModel.BASELANDBEAN.voAppRaMain == null) {
                EstimateModel.BASELANDBEAN.voAppRaMain = new NVO_BASELAND_APPRAISAL();
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
    }

    public void updateAppRaA3VoFromDB() {
        Connection connection = null;
        try {
            connection = this.getVersionConnection();
            NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE = new NVO_BASELAND_APPRAISALA3_SCORE();
            nVO_BASELAND_APPRAISALA3_SCORE.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
            nVO_BASELAND_APPRAISALA3_SCORE.setBaseno(EstimateModel.BASELANDBEAN.queryBean.baseno);
            nVO_BASELAND_APPRAISALA3_SCORE.setAs_type("0");
            EstimateModel.BASELANDBEAN.voAppRaA3Vo0 = (NVO_BASELAND_APPRAISALA3_SCORE)this.ndao_score.findByPk(nVO_BASELAND_APPRAISALA3_SCORE, connection);
            if (EstimateModel.BASELANDBEAN.voAppRaA3Vo0 == null) {
                EstimateModel.BASELANDBEAN.voAppRaA3Vo0 = this.createAppRaA3Vo("0");
            }
            nVO_BASELAND_APPRAISALA3_SCORE.setAs_type("1");
            EstimateModel.BASELANDBEAN.voAppRaA3Vo1 = (NVO_BASELAND_APPRAISALA3_SCORE)this.ndao_score.findByPk(nVO_BASELAND_APPRAISALA3_SCORE, connection);
            if (EstimateModel.BASELANDBEAN.voAppRaA3Vo1 == null) {
                EstimateModel.BASELANDBEAN.voAppRaA3Vo1 = this.createAppRaA3Vo("1");
            }
            nVO_BASELAND_APPRAISALA3_SCORE.setAs_type("2");
            EstimateModel.BASELANDBEAN.voAppRaA3Vo2 = (NVO_BASELAND_APPRAISALA3_SCORE)this.ndao_score.findByPk(nVO_BASELAND_APPRAISALA3_SCORE, connection);
            if (EstimateModel.BASELANDBEAN.voAppRaA3Vo2 == null) {
                EstimateModel.BASELANDBEAN.voAppRaA3Vo2 = this.createAppRaA3Vo("2");
            }
            nVO_BASELAND_APPRAISALA3_SCORE.setAs_type("3");
            EstimateModel.BASELANDBEAN.voAppRaA3Vo3 = (NVO_BASELAND_APPRAISALA3_SCORE)this.ndao_score.findByPk(nVO_BASELAND_APPRAISALA3_SCORE, connection);
            if (EstimateModel.BASELANDBEAN.voAppRaA3Vo3 == null) {
                EstimateModel.BASELANDBEAN.voAppRaA3Vo3 = this.createAppRaA3Vo("3");
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
    }

    private NVO_BASELAND_APPRAISALA3_SCORE createAppRaA3Vo(String string) {
        NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE = new NVO_BASELAND_APPRAISALA3_SCORE();
        nVO_BASELAND_APPRAISALA3_SCORE.setCity(EstimateModel.BASELANDBEAN.queryBean.AA45);
        nVO_BASELAND_APPRAISALA3_SCORE.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
        nVO_BASELAND_APPRAISALA3_SCORE.setBaseno(EstimateModel.BASELANDBEAN.queryBean.baseno);
        nVO_BASELAND_APPRAISALA3_SCORE.setAs_type(string);
        return nVO_BASELAND_APPRAISALA3_SCORE;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void updateSellVoFromDB() {
        Connection connection = null;
        try {
            connection = this.getVersionConnection();
            NVO_BASELAND_SELL nVO_BASELAND_SELL = null;
            NVO_BASELAND_SELL nVO_BASELAND_SELL2 = new NVO_BASELAND_SELL();
            nVO_BASELAND_SELL2.setCity(EstimateModel.BASELANDBEAN.queryBean.AA45);
            nVO_BASELAND_SELL2.setDist(EstimateModel.BASELANDBEAN.queryBean.AA46);
            nVO_BASELAND_SELL2.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
            nVO_BASELAND_SELL2.setBaseno(EstimateModel.BASELANDBEAN.queryBean.baseno);
            nVO_BASELAND_SELL2.setCaseno("1");
            nVO_BASELAND_SELL = (NVO_BASELAND_SELL)this.ndao_sell.findByPk(nVO_BASELAND_SELL2, connection);
            EstimateModel.BASELANDBEAN.voSell_1 = nVO_BASELAND_SELL == null ? new NVO_BASELAND_SELL() : nVO_BASELAND_SELL;
            nVO_BASELAND_SELL2.setCaseno("2");
            nVO_BASELAND_SELL = (NVO_BASELAND_SELL)this.ndao_sell.findByPk(nVO_BASELAND_SELL2, connection);
            EstimateModel.BASELANDBEAN.voSell_2 = nVO_BASELAND_SELL == null ? new NVO_BASELAND_SELL() : nVO_BASELAND_SELL;
            nVO_BASELAND_SELL2.setCaseno("3");
            nVO_BASELAND_SELL = (NVO_BASELAND_SELL)this.ndao_sell.findByPk(nVO_BASELAND_SELL2, connection);
            EstimateModel.BASELANDBEAN.voSell_3 = nVO_BASELAND_SELL == null ? new NVO_BASELAND_SELL() : nVO_BASELAND_SELL;
            EstimateReport3Model estimateReport3Model = new EstimateReport3Model();
            estimateReport3Model.updateAppRaVo(EstimateModel.BASELANDBEAN.voSell_1, EstimateModel.BASELANDBEAN.voAppRaA3Vo1);
            estimateReport3Model.updateAppRaVo(EstimateModel.BASELANDBEAN.voSell_2, EstimateModel.BASELANDBEAN.voAppRaA3Vo2);
            estimateReport3Model.updateAppRaVo(EstimateModel.BASELANDBEAN.voSell_3, EstimateModel.BASELANDBEAN.voAppRaA3Vo3);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void updateFloorEffectVoFromDB() {
        Connection connection = null;
        try {
            connection = this.getVersionConnection();
            NVO_BASELAND_FLOOR_EFFECT nVO_BASELAND_FLOOR_EFFECT = null;
            String string = EstimateModel.BASELANDBEAN.queryBean.baseno;
            String string2 = EstimateModel.BASELANDBEAN.queryBean.year;
            nVO_BASELAND_FLOOR_EFFECT = this.ndao_floor_effect.queryData(string2, string, "1", connection);
            EstimateModel.BASELANDBEAN.floor_1 = nVO_BASELAND_FLOOR_EFFECT == null ? new NVO_BASELAND_FLOOR_EFFECT() : nVO_BASELAND_FLOOR_EFFECT;
            nVO_BASELAND_FLOOR_EFFECT = this.ndao_floor_effect.queryData(string2, string, "2", connection);
            EstimateModel.BASELANDBEAN.floor_2 = nVO_BASELAND_FLOOR_EFFECT == null ? new NVO_BASELAND_FLOOR_EFFECT() : nVO_BASELAND_FLOOR_EFFECT;
            nVO_BASELAND_FLOOR_EFFECT = this.ndao_floor_effect.queryData(string2, string, "3", connection);
            EstimateModel.BASELANDBEAN.floor_3 = nVO_BASELAND_FLOOR_EFFECT == null ? new NVO_BASELAND_FLOOR_EFFECT() : nVO_BASELAND_FLOOR_EFFECT;
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
    }

    public void updateRentExtVoFromDB() {
        Connection connection = null;
        try {
            connection = this.getVersionConnection();
            NVO_BASELAND_RENT_EXT nVO_BASELAND_RENT_EXT = new NVO_BASELAND_RENT_EXT();
            nVO_BASELAND_RENT_EXT.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
            nVO_BASELAND_RENT_EXT.setBaseno(EstimateModel.BASELANDBEAN.queryBean.baseno);
            EstimateModel.BASELANDBEAN.voRentExt = (NVO_BASELAND_RENT_EXT)this.ndao_rent_ext.findByPk(nVO_BASELAND_RENT_EXT, connection);
            if (EstimateModel.BASELANDBEAN.voRentExt == null) {
                EstimateModel.BASELANDBEAN.voRentExt = new NVO_BASELAND_RENT_EXT();
            }
            EstimateModel.BASELANDBEAN.voRentExt.setCre07ori(EstimateModel.BASELANDBEAN.voRentExt.getCre07());
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
    }

    public void updateRentVoFromDB() {
        Connection connection = null;
        try {
            connection = this.getVersionConnection();
            NVO_BASELAND_RENT nVO_BASELAND_RENT = new NVO_BASELAND_RENT();
            nVO_BASELAND_RENT.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
            nVO_BASELAND_RENT.setBaseno(EstimateModel.BASELANDBEAN.queryBean.baseno);
            EstimateModel.BASELANDBEAN.voRent = (NVO_BASELAND_RENT)this.ndao_rent.findByPk(nVO_BASELAND_RENT, connection);
            if (EstimateModel.BASELANDBEAN.voRent == null) {
                EstimateModel.BASELANDBEAN.voRent = new NVO_BASELAND_RENT();
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
    }

    public void updateRentMonthVoFromDB() {
        Connection connection = null;
        try {
            connection = this.getVersionConnection();
            NVO_BASELAND_RENT_MONTH nVO_BASELAND_RENT_MONTH = new NVO_BASELAND_RENT_MONTH();
            nVO_BASELAND_RENT_MONTH.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
            nVO_BASELAND_RENT_MONTH.setBaseno(EstimateModel.BASELANDBEAN.queryBean.baseno);
            nVO_BASELAND_RENT_MONTH.setRent_caseno("1");
            EstimateModel.BASELANDBEAN.voRentMonth1 = (NVO_BASELAND_RENT_MONTH)this.ndao_rent_month.findByPk(nVO_BASELAND_RENT_MONTH, connection);
            if (EstimateModel.BASELANDBEAN.voRentMonth1 == null) {
                EstimateModel.BASELANDBEAN.voRentMonth1 = new NVO_BASELAND_RENT_MONTH();
            }
            nVO_BASELAND_RENT_MONTH.setRent_caseno("2");
            EstimateModel.BASELANDBEAN.voRentMonth2 = (NVO_BASELAND_RENT_MONTH)this.ndao_rent_month.findByPk(nVO_BASELAND_RENT_MONTH, connection);
            if (EstimateModel.BASELANDBEAN.voRentMonth2 == null) {
                EstimateModel.BASELANDBEAN.voRentMonth2 = new NVO_BASELAND_RENT_MONTH();
            }
            nVO_BASELAND_RENT_MONTH.setRent_caseno("3");
            EstimateModel.BASELANDBEAN.voRentMonth3 = (NVO_BASELAND_RENT_MONTH)this.ndao_rent_month.findByPk(nVO_BASELAND_RENT_MONTH, connection);
            if (EstimateModel.BASELANDBEAN.voRentMonth3 == null) {
                EstimateModel.BASELANDBEAN.voRentMonth3 = new NVO_BASELAND_RENT_MONTH();
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void updateDevelopVoFromDB() {
        Connection connection = null;
        Connection connection2 = null;
        try {
            connection = this.getConnection();
            connection2 = this.getVersionConnection();
            NVO_BASELAND_DEVELOP nVO_BASELAND_DEVELOP = new NDAO_BASELAND_DEVELOP().findByPk(EstimateModel.BASELANDBEAN.queryBean.baseno, EstimateModel.BASELANDBEAN.queryBean.year, connection2);
            if (nVO_BASELAND_DEVELOP != null) {
                EstimateModel.BASELANDBEAN.voDevelop = nVO_BASELAND_DEVELOP;
                EstimateModel.BASELANDBEAN.queryBean.mode = "edit";
            } else {
                EstimateModel.BASELANDBEAN.voDevelop.setBaseno(EstimateModel.BASELANDBEAN.queryBean.baseno);
                EstimateModel.BASELANDBEAN.voDevelop.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
                EstimateModel.BASELANDBEAN.voDevelop.setCity(EstimateModel.BASELANDBEAN.queryBean.AA45);
                EstimateModel.BASELANDBEAN.voDevelop.setDist(EstimateModel.BASELANDBEAN.queryBean.AA46);
                new BaseLandParamFillHelper(EstimateModel.BASELANDBEAN.queryBean.AA45, EstimateModel.BASELANDBEAN.queryBean.year, connection).fillValue(EstimateModel.BASELANDBEAN.voDevelop);
            }
            NVO_BASELAND_DEVELOP_EXT nVO_BASELAND_DEVELOP_EXT = new NDAO_BASELAND_DEVELOP_EXT().findByPk(EstimateModel.BASELANDBEAN.queryBean.baseno, EstimateModel.BASELANDBEAN.queryBean.year, connection2);
            if (nVO_BASELAND_DEVELOP_EXT != null) {
                EstimateModel.BASELANDBEAN.voDevelopExt = nVO_BASELAND_DEVELOP_EXT;
            } else {
                EstimateModel.BASELANDBEAN.voDevelopExt.setBaseno(EstimateModel.BASELANDBEAN.queryBean.baseno);
                EstimateModel.BASELANDBEAN.voDevelopExt.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
            SqlUtil.close(connection2);
        }
    }

    public boolean checkCompareFactorListEmpty() {
        return this.factorRegional.size() == 0 || this.factorIndividual.size() == 0;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void updateCompareFactorList() {
        Connection connection = null;
        try {
            connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
            String string = EstimateModel.BASELANDBEAN.queryBean.baseno;
            String string2 = EstimateModel.BASELANDBEAN.queryBean.year;
            String string3 = EstimateModel.BASELANDBEAN.queryBean.urban;
            String string4 = EstimateModel.BASELANDBEAN.queryBean.version;
            BaseLandCompareFactorListHelper baseLandCompareFactorListHelper = new BaseLandCompareFactorListHelper(string, string2, string4, string3);
            this.updateRegionalItemLevelList(baseLandCompareFactorListHelper, connection);
            this.updateIndividualItemLevelList(baseLandCompareFactorListHelper, connection);
            this.updataIsAutoSwitchLevel(connection);
            reginVersion = baseLandCompareFactorListHelper.reginVersion;
            individualVersion = baseLandCompareFactorListHelper.individualVersion;
            factorVer = baseLandCompareFactorListHelper.version;
            SqlUtil.close(connection);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
    }

    private void updataIsAutoSwitchLevel(Connection connection) throws Exception {
        NDAO_BASELAND_FACTOR_CODE nDAO_BASELAND_FACTOR_CODE = new NDAO_BASELAND_FACTOR_CODE();
        ArrayList arrayList = nDAO_BASELAND_FACTOR_CODE.findBySql("select * from BASELAND_FACTOR_CODE", connection);
        if (arrayList != null && arrayList.size() > 0) {
            this.isAutoSwitchLevel.clear();
            for (NVO_BASELAND_FACTOR_CODE nVO_BASELAND_FACTOR_CODE : arrayList) {
                if (!EnumFactorStdType.NUMERAL.toString().equals(nVO_BASELAND_FACTOR_CODE.getStdtype()) && !EnumFactorStdType.SELECTION.toString().equals(nVO_BASELAND_FACTOR_CODE.getStdtype())) continue;
                this.isAutoSwitchLevel.put(nVO_BASELAND_FACTOR_CODE.getAsfield(), nVO_BASELAND_FACTOR_CODE);
            }
        }
    }

    private void updateIndividualItemLevelList(BaseLandCompareFactorListHelper baseLandCompareFactorListHelper, Connection connection) throws Exception {
        Map<String, FactorLevelBean> map = baseLandCompareFactorListHelper.getIndividualItemLevelList(connection);
        for (String string : map.keySet()) {
            if (string.length() != 2) continue;
            this.factorIndividual.put(string, map.get(string));
        }
    }

    private void updateRegionalItemLevelList(BaseLandCompareFactorListHelper baseLandCompareFactorListHelper, Connection connection) throws Exception {
        Map<String, FactorLevelBean> map = baseLandCompareFactorListHelper.getRegionalItemLevelList(connection);
        for (String string : map.keySet()) {
            if (string.length() != 2) continue;
            this.factorRegional.put(string, map.get(string));
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ArrayList<String> getExistYearList() {
        Connection connection = null;
        ArrayList<String> arrayList = new ArrayList<String>();
        try {
            connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
            ArrayList<String> arrayList2 = this.ndao_main.getExistYearList(EstimateModel.BASELANDBEAN.queryBean.AA45, EstimateModel.BASELANDBEAN.queryBean.AA46, connection);
            if (arrayList2.size() == 0) {
                arrayList2.add(DateTime.getTWYear());
            } else if (!arrayList2.contains(DateTime.getTWYear())) {
                arrayList2.add(DateTime.getTWYear());
            }
            arrayList2 = new DateTime().getYearList(arrayList2, 1, 1, false, false);
            arrayList.addAll(arrayList2);
            SqlUtil.close(connection);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
        return arrayList;
    }

    public ArrayList<OptionPair> getCS25List() {
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        arrayList.add(new OptionPair("\u5b9a\u984d\u6cd5", "\u5b9a\u984d\u6cd5"));
        return arrayList;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public String getNewBaseseq() {
        Connection connection = null;
        try {
            connection = this.getConnection();
            NVO_BASELAND_MAIN nVO_BASELAND_MAIN = new NVO_BASELAND_MAIN();
            nVO_BASELAND_MAIN.setCity(EstimateModel.BASELANDBEAN.queryBean.AA45);
            nVO_BASELAND_MAIN.setDist(EstimateModel.BASELANDBEAN.queryBean.AA46);
            nVO_BASELAND_MAIN.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
            nVO_BASELAND_MAIN.setUrban(EstimateModel.BASELANDBEAN.queryBean.urban);
            new BaseLandMainBo().getNewBaseNo(nVO_BASELAND_MAIN, connection);
            String string = nVO_BASELAND_MAIN.getBaseseq();
            return string;
        }
        catch (Exception exception) {
            ExceptionDialog.show(exception);
        }
        finally {
            SqlUtil.close(connection);
        }
        return "";
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean getNewBaseNo(int n) {
        Connection connection = null;
        boolean bl = true;
        try {
            connection = this.getConnection();
            EstimateModel.BASELANDBEAN.voMain.setCity(EstimateModel.BASELANDBEAN.queryBean.AA45);
            EstimateModel.BASELANDBEAN.voMain.setDist(EstimateModel.BASELANDBEAN.queryBean.AA46);
            EstimateModel.BASELANDBEAN.voMain.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
            EstimateModel.BASELANDBEAN.voMain.setUrban(EstimateModel.BASELANDBEAN.queryBean.urban);
            EstimateModel.BASELANDBEAN.voMain.setVersion(EstimateModel.BASELANDBEAN.queryBean.version);
            if (n <= 0) {
                new BaseLandMainBo().getNewBaseNo(EstimateModel.BASELANDBEAN.voMain, connection);
            } else {
                String string = EstimateModel.BASELANDBEAN.queryBean.AA45 + EstimateModel.BASELANDBEAN.queryBean.AA46 + EstimateModel.BASELANDBEAN.queryBean.urban + StringProcess.fillZero(n, 4);
                EstimateModel.BASELANDBEAN.voMain.setBaseseq(StringProcess.fillZero(n, 4));
                EstimateModel.BASELANDBEAN.voMain.setBaseno(string);
            }
            EstimateModel.BASELANDBEAN.queryBean.baseno = EstimateModel.BASELANDBEAN.voMain.getBaseno();
        }
        catch (Exception exception) {
            exception.printStackTrace();
            bl = false;
        }
        finally {
            SqlUtil.close(connection);
        }
        return bl;
    }

    public void calFloorEffect(NVO_BASELAND_SELL nVO_BASELAND_SELL) {
        BaseLandFloorEffectFormBean baseLandFloorEffectFormBean = new BaseLandFloorEffectFormBean();
        baseLandFloorEffectFormBean.setYear(nVO_BASELAND_SELL.getYear());
        baseLandFloorEffectFormBean.setBaselandNo(nVO_BASELAND_SELL.getBaseno());
        baseLandFloorEffectFormBean.setCaseNo(nVO_BASELAND_SELL.getCaseno());
        baseLandFloorEffectFormBean.setTotalFloor(StringProcess.parserInt(nVO_BASELAND_SELL.getCs02()));
        baseLandFloorEffectFormBean.setTotalBasement(StringProcess.parserInt(nVO_BASELAND_SELL.getCs03()));
        baseLandFloorEffectFormBean.setTargetFloor(nVO_BASELAND_SELL.getCs04f() + "F");
        baseLandFloorEffectFormBean.setBuildingPrice(nVO_BASELAND_SELL.getCs48());
        baseLandFloorEffectFormBean.setTotalSellPrice(nVO_BASELAND_SELL.getCs51());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public byte[] print(QueryBean queryBean, boolean bl, boolean bl2) {
        byte[] byArray;
        Connection connection = null;
        Connection connection2 = null;
        try {
            connection = this.getVersionConnection();
            connection2 = this.getConnection();
            String string = "noUse";
            String string2 = queryBean.year;
            String string3 = queryBean.baseno;
            String string4 = new File(".").getAbsolutePath() + "\\ReportRepository\\";
            BaseLandPrintALL baseLandPrintALL = new BaseLandPrintALL(string, string2, string3, string4);
            baseLandPrintALL.setRmk_page(bl);
            baseLandPrintALL.setPrintRentMonth(bl2);
            byArray = baseLandPrintALL.printAll(connection, connection2);
        }
        catch (Exception exception) {
            try {
                exception.printStackTrace();
            }
            catch (Throwable throwable) {
                SqlUtil.close(connection2);
                SqlUtil.close(connection);
                throw throwable;
            }
            SqlUtil.close(connection2);
            SqlUtil.close(connection);
            return null;
        }
        SqlUtil.close(connection2);
        SqlUtil.close(connection);
        return byArray;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public String getOfficeByTown(String string, String string2) {
        Connection connection = null;
        try {
            connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
            String string3 = new NDAO_SRKEYN_ALL().getOfficeByTown(string, string2, connection);
            SqlUtil.close(connection);
            return string3;
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
        return "";
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int getInstruStdPrice(String string, int n) {
        Connection connection = null;
        try {
            int n2;
            connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
            BaseLandInstruStdPriceTool baseLandInstruStdPriceTool = new BaseLandInstruStdPriceTool(EstimateModel.BASELANDBEAN.queryBean.AA45, connection);
            int n3 = n2 = baseLandInstruStdPriceTool.getStartdardPrice(EstimateModel.BASELANDBEAN.queryBean.AA45, string, n);
            SqlUtil.close(connection);
            return n3;
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
        return 0;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean checkExistBaseNum(String string) {
        block8: {
            boolean bl;
            Connection connection = null;
            try {
                connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
                String string2 = BaseLandVerifyUtil.checkCity(StringProcess.NULL(EstimateModel.BASELANDBEAN.queryBean.AA45));
                String string3 = BaseLandVerifyUtil.checkDist(StringProcess.NULL(EstimateModel.BASELANDBEAN.queryBean.AA46));
                String string4 = BaseLandVerifyUtil.checkBaselandSeq(StringProcess.NULL(string));
                String string5 = BaseLandVerifyUtil.checkYear(StringProcess.NULL(EstimateModel.BASELANDBEAN.queryBean.year));
                NVO_BASELAND_MAIN nVO_BASELAND_MAIN = new NDAO_BASELAND_MAIN().findBySeq(string2, string3, string4, string5, connection);
                if (nVO_BASELAND_MAIN != null) {
                    boolean bl2 = false;
                    SqlUtil.close(connection);
                    return bl2;
                }
                bl = true;
                SqlUtil.close(connection);
            }
            catch (VerifyException verifyException) {
                SqlUtil.close(connection);
                break block8;
            }
            catch (Exception exception) {
                ExceptionDialog.show(exception);
                break block8;
            }
            finally {
                SqlUtil.close(connection);
            }
            return bl;
        }
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean delete(String string, String string2) {
        Connection connection = null;
        try {
            connection = this.getConnection();
            connection.setAutoCommit(false);
            new BaseLandMainBo().clearBaseLandMain(string, string2, connection);
            connection.commit();
            boolean bl = true;
            return bl;
        }
        catch (Exception exception) {
            ExceptionDialog.show(exception);
            if (connection != null) {
                try {
                    connection.rollback();
                }
                catch (SQLException sQLException) {
                    ExceptionDialog.show(sQLException);
                }
            }
        }
        finally {
            SqlUtil.close(connection);
        }
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public double getAdjustRatio(String string) {
        Connection connection = null;
        try {
            String string2 = EstimateModel.BASELANDBEAN.queryBean.AA45;
            String string3 = EstimateModel.BASELANDBEAN.queryBean.year;
            connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
            double d = new BaseLandBuildStdPriceAdjustRateFillHelper(string2, string3).getAdjustRatio(string, EstimateModel.BASELANDBEAN.queryBean.baseno, connection);
            SqlUtil.close(connection);
            return d;
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
        return 0.0;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean save() {
        Connection connection;
        boolean bl;
        block12: {
            bl = false;
            connection = null;
            try {
                SaveCheckBo saveCheckBo = new SaveCheckBo(BASELANDBEAN);
                String string = saveCheckBo.saveCheckColumn();
                if (StringProcess.isEmpty(string)) {
                    connection = this.getVersionConnection();
                    connection.setAutoCommit(false);
                    BaseLandSellBo baseLandSellBo = new BaseLandSellBo();
                    EstimateModel.BASELANDBEAN.voSell_1.setCity(EstimateModel.BASELANDBEAN.queryBean.AA45);
                    EstimateModel.BASELANDBEAN.voSell_2.setCity(EstimateModel.BASELANDBEAN.queryBean.AA45);
                    EstimateModel.BASELANDBEAN.voSell_3.setCity(EstimateModel.BASELANDBEAN.queryBean.AA45);
                    BaseLandSellParamBean baseLandSellParamBean = new BaseLandSellParamBean(EstimateModel.BASELANDBEAN.voSell_1, EstimateModel.BASELANDBEAN.queryBean.mode);
                    baseLandSellBo.saveBaseLandSell(baseLandSellParamBean, connection);
                    baseLandSellParamBean = new BaseLandSellParamBean(EstimateModel.BASELANDBEAN.voSell_2, EstimateModel.BASELANDBEAN.queryBean.mode);
                    baseLandSellBo.saveBaseLandSell(baseLandSellParamBean, connection);
                    baseLandSellParamBean = new BaseLandSellParamBean(EstimateModel.BASELANDBEAN.voSell_3, EstimateModel.BASELANDBEAN.queryBean.mode);
                    baseLandSellBo.saveBaseLandSell(baseLandSellParamBean, connection);
                    if (!StringProcess.isEmpty(EstimateModel.BASELANDBEAN.floor_1.getJsondata())) {
                        this.saveFloorEffect(EstimateModel.BASELANDBEAN.floor_1, "1", connection);
                    }
                    if (!StringProcess.isEmpty(EstimateModel.BASELANDBEAN.floor_2.getJsondata())) {
                        this.saveFloorEffect(EstimateModel.BASELANDBEAN.floor_2, "2", connection);
                    }
                    if (!StringProcess.isEmpty(EstimateModel.BASELANDBEAN.floor_3.getJsondata())) {
                        this.saveFloorEffect(EstimateModel.BASELANDBEAN.floor_3, "3", connection);
                    }
                    EstimateModel.BASELANDBEAN.voAppRaMain.setOfce(EstimateModel.BASELANDBEAN.queryBean.office);
                    EstimateModel.BASELANDBEAN.voAppRaMain.setBaseno(EstimateModel.BASELANDBEAN.queryBean.baseno);
                    EstimateModel.BASELANDBEAN.voAppRaA3Vo0.setOfce(EstimateModel.BASELANDBEAN.queryBean.office);
                    EstimateModel.BASELANDBEAN.voAppRaA3Vo1.setOfce(EstimateModel.BASELANDBEAN.queryBean.office);
                    EstimateModel.BASELANDBEAN.voAppRaA3Vo1.setCity(EstimateModel.BASELANDBEAN.queryBean.AA45);
                    EstimateModel.BASELANDBEAN.voAppRaA3Vo2.setOfce(EstimateModel.BASELANDBEAN.queryBean.office);
                    EstimateModel.BASELANDBEAN.voAppRaA3Vo2.setCity(EstimateModel.BASELANDBEAN.queryBean.AA45);
                    EstimateModel.BASELANDBEAN.voAppRaA3Vo3.setOfce(EstimateModel.BASELANDBEAN.queryBean.office);
                    EstimateModel.BASELANDBEAN.voAppRaA3Vo3.setCity(EstimateModel.BASELANDBEAN.queryBean.AA45);
                    NDAO_BASELAND_APPRAISAL nDAO_BASELAND_APPRAISAL = new NDAO_BASELAND_APPRAISAL();
                    nDAO_BASELAND_APPRAISAL.update2(EstimateModel.BASELANDBEAN.voAppRaMain, connection);
                    nDAO_BASELAND_APPRAISAL.update2(EstimateModel.BASELANDBEAN.voAppRaA3Vo0, connection);
                    nDAO_BASELAND_APPRAISAL.update2(EstimateModel.BASELANDBEAN.voAppRaA3Vo1, connection);
                    nDAO_BASELAND_APPRAISAL.update2(EstimateModel.BASELANDBEAN.voAppRaA3Vo2, connection);
                    nDAO_BASELAND_APPRAISAL.update2(EstimateModel.BASELANDBEAN.voAppRaA3Vo3, connection);
                    new NDAO_BASELAND_RENT_EXT().update2(EstimateModel.BASELANDBEAN.voRentExt, connection);
                    NDAO_BASELAND_RENT_MONTH nDAO_BASELAND_RENT_MONTH = new NDAO_BASELAND_RENT_MONTH();
                    new NDAO_BASELAND_RENT().update2(EstimateModel.BASELANDBEAN.voRent, connection);
                    nDAO_BASELAND_RENT_MONTH.update2(EstimateModel.BASELANDBEAN.voRentMonth1, connection);
                    nDAO_BASELAND_RENT_MONTH.update2(EstimateModel.BASELANDBEAN.voRentMonth2, connection);
                    nDAO_BASELAND_RENT_MONTH.update2(EstimateModel.BASELANDBEAN.voRentMonth3, connection);
                    EstimateModel.BASELANDBEAN.voDevelop.setOfce(EstimateModel.BASELANDBEAN.queryBean.office);
                    new NDAO_BASELAND_DEVELOP().update2(EstimateModel.BASELANDBEAN.voDevelop, connection);
                    new NDAO_BASELAND_DEVELOP_EXT().update2(EstimateModel.BASELANDBEAN.voDevelopExt, connection);
                    EstimateModel.BASELANDBEAN.voMain.setOfce(EstimateModel.BASELANDBEAN.queryBean.office);
                    BaseLandMainParamBean baseLandMainParamBean = new BaseLandMainParamBean(EstimateModel.BASELANDBEAN.voMain, EstimateModel.BASELANDBEAN.queryBean.mode);
                    new BaseLandMainBo().saveBaseLandMain(baseLandMainParamBean, connection, connection, connection);
                    EstimateModel.BASELANDBEAN.voImages.setOfce(EstimateModel.BASELANDBEAN.queryBean.office);
                    if (EstimateModel.BASELANDBEAN.voImages.getPhoto().length > 0) {
                        NDAO_BASELAND_IMAGES nDAO_BASELAND_IMAGES = new NDAO_BASELAND_IMAGES();
                        if (nDAO_BASELAND_IMAGES.isExist(EstimateModel.BASELANDBEAN.voImages, connection)) {
                            nDAO_BASELAND_IMAGES.delete(EstimateModel.BASELANDBEAN.voImages, connection);
                        }
                        nDAO_BASELAND_IMAGES.create(EstimateModel.BASELANDBEAN.voImages, connection);
                    }
                    connection.commit();
                    bl = true;
                    break block12;
                }
                JavaFXUtil.showErrorMessageBox(string);
            }
            catch (Exception exception) {
                try {
                    ExceptionDialog.show(exception);
                    try {
                        connection.rollback();
                    }
                    catch (SQLException sQLException) {
                        sQLException.printStackTrace();
                    }
                }
                catch (Throwable throwable) {
                    SqlUtil.close(connection);
                    throw throwable;
                }
                SqlUtil.close(connection);
            }
        }
        SqlUtil.close(connection);
        return bl;
    }

    private void saveFloorEffect(NVO_BASELAND_FLOOR_EFFECT nVO_BASELAND_FLOOR_EFFECT, String string, Connection connection) throws Exception {
        NDAO_BASELAND_FLOOR_EFFECT nDAO_BASELAND_FLOOR_EFFECT = new NDAO_BASELAND_FLOOR_EFFECT();
        nVO_BASELAND_FLOOR_EFFECT.setBaseno(EstimateModel.BASELANDBEAN.queryBean.baseno);
        nVO_BASELAND_FLOOR_EFFECT.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
        nVO_BASELAND_FLOOR_EFFECT.setCaseno(string);
        nVO_BASELAND_FLOOR_EFFECT.setCity(EstimateModel.BASELANDBEAN.queryBean.AA45);
        nVO_BASELAND_FLOOR_EFFECT.setOfce(EstimateModel.BASELANDBEAN.queryBean.office);
        nDAO_BASELAND_FLOOR_EFFECT.update2(nVO_BASELAND_FLOOR_EFFECT, connection);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public NVO_BASELAND_REPORT_PARAM getSystemParam(QueryBean queryBean) {
        Connection connection = null;
        NVO_BASELAND_REPORT_PARAM nVO_BASELAND_REPORT_PARAM = null;
        try {
            String string = EstimateModel.BASELANDBEAN.queryBean.AA45;
            String string2 = EstimateModel.BASELANDBEAN.queryBean.year;
            connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
            nVO_BASELAND_REPORT_PARAM = new NDAO_BASELAND_REPORT_PARAM().findByPk(string, string2, connection);
            SqlUtil.close(connection);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
        return nVO_BASELAND_REPORT_PARAM;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public NVO_BASELAND_AHP getAHP(QueryBean queryBean) {
        Connection connection = null;
        NVO_BASELAND_AHP nVO_BASELAND_AHP = null;
        try {
            String string = EstimateModel.BASELANDBEAN.queryBean.AA45;
            String string2 = EstimateModel.BASELANDBEAN.queryBean.year;
            connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
            nVO_BASELAND_AHP = new NDAO_BASELAND_AHP().findByPk(string, string2, connection);
            SqlUtil.close(connection);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
        return nVO_BASELAND_AHP;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public String createCheck() {
        Connection connection;
        StringBuffer stringBuffer;
        block7: {
            stringBuffer = new StringBuffer();
            connection = null;
            try {
                connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
                BaseLandMainParamBean baseLandMainParamBean = new BaseLandMainParamBean(EstimateModel.BASELANDBEAN.voMain, EstimateModel.BASELANDBEAN.queryBean.mode);
                new BaseLandMainBo().queryBaseLandMain(baseLandMainParamBean, connection);
                NVO_BASELAND_MAIN nVO_BASELAND_MAIN = baseLandMainParamBean.getBandLandMainVo();
                String string = nVO_BASELAND_MAIN.getBaseno().substring(3, 5);
                BaseLandCompareFactorListHelper baseLandCompareFactorListHelper = new BaseLandCompareFactorListHelper(nVO_BASELAND_MAIN.getBaseno(), nVO_BASELAND_MAIN.getYear(), nVO_BASELAND_MAIN.getVersion(), string);
                baseLandCompareFactorListHelper.getRegionalItemLevelList(connection);
                baseLandCompareFactorListHelper.getIndividualItemLevelList(connection);
                BaseLandParamFillHelper baseLandParamFillHelper = new BaseLandParamFillHelper(nVO_BASELAND_MAIN.getCity(), nVO_BASELAND_MAIN.getYear(), connection);
                if (!baseLandCompareFactorListHelper.isExistsRegionalData() && !baseLandCompareFactorListHelper.isExistsIndividualData()) {
                    stringBuffer.append("\u6c92\u6709\u5340\u57df\u56e0\u7d20\u53ca\u500b\u5225\u56e0\u7d20\uff0c\u7121\u6cd5\u65b0\u589e\u57fa\u6e96\u5730\uff01");
                    break block7;
                }
                if (!baseLandCompareFactorListHelper.isExistsRegionalData()) {
                    stringBuffer.append("\u6c92\u6709\u5340\u57df\u56e0\u7d20\uff0c\u7121\u6cd5\u65b0\u589e\u57fa\u6e96\u5730\uff01");
                    break block7;
                }
                if (!baseLandCompareFactorListHelper.isExistsIndividualData()) {
                    stringBuffer.append("\u6c92\u6709\u500b\u5225\u56e0\u7d20\uff0c\u7121\u6cd5\u65b0\u589e\u57fa\u6e96\u5730\uff01");
                    break block7;
                }
                if (baseLandParamFillHelper.getParam().getPrice_rate_type().length() != 0) break block7;
                stringBuffer.append("\u516c\u5831\u53c3\u6578\u6c92\u6709\u8a2d\u5b9a\u50f9\u683c\u65e5\u671f\u8abf\u6574\u6307\u6578\u985e\u578b\uff0c\u7121\u6cd5\u65b0\u589e\u57fa\u6e96\u5730\uff01");
            }
            catch (Exception exception) {
                try {
                    exception.printStackTrace();
                    stringBuffer.append(exception.toString());
                }
                catch (Throwable throwable) {
                    SqlUtil.close(connection);
                    throw throwable;
                }
                SqlUtil.close(connection);
            }
        }
        SqlUtil.close(connection);
        return stringBuffer.toString();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Loose catch block
     */
    public static void updateBeginData() {
        block10: {
            Connection connection = null;
            String string = "update srkeyn_all set kname='\u7af9\u5357\u93ae'  where kcde_1='46' and kcde_2=? and kcde_3=? ";
            Statement statement = null;
            connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
            statement = connection.prepareStatement(string);
            statement.setString(1, "K");
            statement.setString(2, "09");
            statement.executeUpdate();
            try {
                statement.close();
                statement = null;
            }
            catch (SQLException sQLException) {
                sQLException.printStackTrace();
            }
            SqlUtil.close(connection);
            break block10;
            catch (Exception exception) {
                try {
                    exception.printStackTrace();
                }
                catch (Throwable throwable) {
                    try {
                        statement.close();
                        statement = null;
                    }
                    catch (SQLException sQLException) {
                        sQLException.printStackTrace();
                    }
                    SqlUtil.close(connection);
                    throw throwable;
                }
                try {
                    statement.close();
                    statement = null;
                }
                catch (SQLException sQLException) {
                    sQLException.printStackTrace();
                }
                SqlUtil.close(connection);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public String exportCheck(String string, String string2, String string3, String string4, String string5, StringBuilder stringBuilder) {
        String string6 = "SUCC";
        AppraiserExport appraiserExport = new AppraiserExport(false);
        Connection connection = null;
        Connection connection2 = null;
        try {
            FileUtils.copyFile((File)new File(string), (File)new File(string2));
            connection = ConnectionFactory.createConnection("sqlite", string3, "", "", "empty", "empty");
            connection2 = ConnectionFactory.createConnection("sqlite", string2, "", "", "empty", "empty");
            appraiserExport.copy(string4, string5, connection, connection2);
            try {
                appraiserExport.checkRequiredField();
            }
            catch (Exception exception) {
                string6 = "NOSUCC";
                stringBuilder.append(exception.getMessage());
            }
        }
        catch (Exception exception) {
            try {
                exception.printStackTrace();
                string6 = "ERRs";
                stringBuilder.append("\u932f\u8aa4!" + exception.toString());
                SqlUtil.rollback(connection2);
            }
            catch (Throwable throwable) {
                SqlUtil.close(connection);
                SqlUtil.close(connection2);
                throw throwable;
            }
            SqlUtil.close(connection);
            SqlUtil.close(connection2);
        }
        SqlUtil.close(connection);
        SqlUtil.close(connection2);
        return string6;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public String exportSuccData(String string, String string2, String string3, String string4, String string5, StringBuilder stringBuilder) {
        String string6 = "TRUE";
        AppraiserExport appraiserExport = new AppraiserExport(false);
        Connection connection = null;
        Connection connection2 = null;
        try {
            FileUtils.copyFile((File)new File(string), (File)new File(string2));
            connection = ConnectionFactory.createConnection("sqlite", string3, "", "", "empty", "empty");
            connection2 = ConnectionFactory.createConnection("sqlite", string2, "", "", "empty", "empty");
            appraiserExport.copy(string4, string5, connection, connection2);
            connection2.setAutoCommit(false);
            appraiserExport.exportSucc(connection2);
            connection2.commit();
        }
        catch (Exception exception) {
            try {
                exception.printStackTrace();
                string6 = "FALSE";
                stringBuilder.append("\u932f\u8aa4!" + exception.toString());
                SqlUtil.rollback(connection2);
            }
            catch (Throwable throwable) {
                SqlUtil.close(connection);
                SqlUtil.close(connection2);
                throw throwable;
            }
            SqlUtil.close(connection);
            SqlUtil.close(connection2);
        }
        SqlUtil.close(connection);
        SqlUtil.close(connection2);
        return string6;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean checkImageSize(String string, String string2, StringBuilder stringBuilder) {
        boolean bl = true;
        Connection connection = null;
        try {
            Object object;
            connection = this.getConnection();
            String string3 = "select * from BASELAND_IMAGES where 1=1 ";
            if (!StringProcess.isEmpty(string)) {
                string3 = string3 + " and year=@@";
                object = new SqlBuilder(string3);
                ((SqlBuilder)object).setString(0, string);
                string3 = ((SqlBuilder)object).getSql();
            }
            if (!StringProcess.isEmpty(string2)) {
                string3 = string3 + " and baseno=@@";
                object = new SqlBuilder(string3);
                ((SqlBuilder)object).setString(0, string2);
                string3 = ((SqlBuilder)object).getSql();
            }
            System.out.println(string3);
            object = (DaoBase)Class.forName("moiland.baseland.dataaccess.ndao.NDAO_BASELAND_IMAGES").newInstance();
            ArrayList arrayList = ((DaoBase)object).findBySql(string3, connection);
            int n = 0x100000;
            NVO_BASELAND_IMAGES nVO_BASELAND_IMAGES = null;
            for (VoBase voBase : arrayList) {
                nVO_BASELAND_IMAGES = (NVO_BASELAND_IMAGES)voBase;
                if (nVO_BASELAND_IMAGES.getPhoto().length <= n) continue;
                bl = false;
                stringBuilder.append(this.ImageName(nVO_BASELAND_IMAGES.getPhoto_type()) + "\uff0c\u5716\u7247\u6a94\u6848\u8d85\u904e1MB\uff0c\u8acb\u91cd\u65b0\u4e0a\u50b3").append("\n");
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
        return bl;
    }

    private String ImageName(String string) {
        String string2 = "";
        if (string.equals("SKT")) {
            string2 = "\u4f4d\u7f6e\u7565\u5716";
        } else if (string.contains("BASE")) {
            string2 = "\u57fa\u6e96\u5730\u4f30\u50f9\u67e5\u4f30\u8868\u7167\u7247" + string.substring(string.length() - 1);
        } else if (string.contains("RENT")) {
            string2 = "\u6536\u76ca\u5be6\u4f8b\u7167\u7247" + string.substring(string.length() - 2, string.length() - 1) + "-" + string.substring(string.length() - 1);
        } else if (string.contains("SELL")) {
            string2 = "\u6210\u672c\u6cd5" + string.substring(string.length() - 2, string.length() - 1) + " \u7167\u7247" + string.substring(string.length() - 1);
        }
        return string2;
    }

    static {
        col_update.put("as339_0", false);
        col_update.put("as340_ds_0", false);
        col_update.put("as341_ds_0", false);
        col_update.put("as365_ds_0", false);
        col_update.put("as366_ds_0", false);
        col_update.put("as339_1", false);
        col_update.put("as339_2", false);
        col_update.put("as339_3", false);
    }
}

