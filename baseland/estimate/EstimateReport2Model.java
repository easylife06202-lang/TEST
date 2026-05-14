/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.baseland.estimate;

import com.wfusion.baseland.basic.Model;
import com.wfusion.baseland.estimate.EstimateModel;
import com.wfusion.baseland.system.GoAHPModel;
import com.wfusion.fx.util.ExceptionDialog;
import com.wfusion.fx.util.NumberFormater;
import com.wfusion.util.ConnectionFactory;
import com.wfusion.util.OptionPair;
import com.wfusion.util.SUtility;
import com.wfusion.util.SqlUtil;
import com.wfusion.util.StringProcess;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import moiland.baseland.bo.AutoCalBaseLandAppraisalBo;
import moiland.baseland.dataaccess.ndao.NDAO_SRKEYN;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_AHP;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_APPRAISALA3_SCORE;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_FACTOR_CODE;
import moiland.baseland.factor.bean.FactorScoreBean;
import moiland.baseland.factor.bo.BaseLandFactorStdTool;
import moiland.baseland.factor.em.EnumFactorVersion;
import moiland.baseland.pricerate.em.EnumPriceRateSubItem;
import moiland.baseland.report.param.bo.BaseLandReportParamDataBo;
import moiland.baseland.util.BaseLandFactorVersionHelper;
import moiland.baseland.util.BaseLandPriceRateFillHelper;

public class EstimateReport2Model
extends Model {
    HashMap<String, Object> voValuesMain = new HashMap();
    HashMap<String, Object> voValues0 = new HashMap();
    HashMap<String, Object> voValues1 = new HashMap();
    HashMap<String, Object> voValues2 = new HashMap();
    HashMap<String, Object> voValues3 = new HashMap();
    public String priceRateVersion = "";

    public ArrayList<FactorScoreBean> getAS302List() {
        ArrayList<FactorScoreBean> arrayList = new ArrayList<FactorScoreBean>();
        arrayList.add(new FactorScoreBean(-1, 0.0, "\u8acb\u9078\u64c7"));
        arrayList.add(new FactorScoreBean(0, 0.0, "\u5176\u4ed6"));
        arrayList.add(new FactorScoreBean(1, 0.0, "\u7d20\u5730"));
        arrayList.add(new FactorScoreBean(2, 0.0, "\u900f\u5929"));
        arrayList.add(new FactorScoreBean(3, 0.0, "\u516c\u5bd3"));
        arrayList.add(new FactorScoreBean(4, 0.0, "\u83ef\u5ec8"));
        arrayList.add(new FactorScoreBean(5, 0.0, "\u5927\u6a13"));
        arrayList.add(new FactorScoreBean(6, 0.0, "\u5957\u623f"));
        return arrayList;
    }

    public ArrayList<FactorScoreBean> getAS303List() {
        ArrayList<FactorScoreBean> arrayList = new ArrayList<FactorScoreBean>();
        arrayList.add(new FactorScoreBean(-1, 0.0, "\u8acb\u9078\u64c7"));
        arrayList.add(new FactorScoreBean(0, 0.0, "\u5176\u4ed6"));
        arrayList.add(new FactorScoreBean(1, 0.0, "\u4f4f"));
        arrayList.add(new FactorScoreBean(2, 0.0, "\u5546"));
        arrayList.add(new FactorScoreBean(3, 0.0, "\u5de5"));
        return arrayList;
    }

    public ArrayList<FactorScoreBean> getPriceTypeList() {
        ArrayList<FactorScoreBean> arrayList = new ArrayList<FactorScoreBean>();
        arrayList.add(new FactorScoreBean(-1, 0.0, "\u8acb\u9078\u64c7"));
        arrayList.add(new FactorScoreBean(1, 0.0, "\u63ed\u9732\u5be6\u50f9"));
        arrayList.add(new FactorScoreBean(2, 0.0, "\u672a\u63ed\u9732\u5be6\u50f9"));
        arrayList.add(new FactorScoreBean(3, 0.0, "\u5f85\u552e\u50f9"));
        return arrayList;
    }

    public ArrayList<FactorScoreBean> getas342List() {
        ArrayList<FactorScoreBean> arrayList = new ArrayList<FactorScoreBean>();
        arrayList.add(new FactorScoreBean(-1, 0.0, "\u8acb\u9078\u64c7"));
        arrayList.add(new FactorScoreBean(0, 0.0, "\u4e0d\u898f\u5247\u5f62"));
        arrayList.add(new FactorScoreBean(1, 0.0, "\u65b9\u5f62"));
        arrayList.add(new FactorScoreBean(2, 0.0, "\u9577\u65b9\u5f62"));
        arrayList.add(new FactorScoreBean(3, 0.0, "\u68af\u5f62"));
        return arrayList;
    }

    public ArrayList<FactorScoreBean> getas343List() {
        ArrayList<FactorScoreBean> arrayList = new ArrayList<FactorScoreBean>();
        arrayList.add(new FactorScoreBean(-1, 0.0, "\u8acb\u9078\u64c7"));
        arrayList.add(new FactorScoreBean(0, 0.0, "\u7121\u81e8\u8def"));
        arrayList.add(new FactorScoreBean(1, 0.0, "\u55ae\u9762"));
        arrayList.add(new FactorScoreBean(2, 0.0, "\u96d9\u9762"));
        arrayList.add(new FactorScoreBean(3, 0.0, "\u4e09\u9762"));
        arrayList.add(new FactorScoreBean(4, 0.0, "\u56db\u9762(\u542b\u4ee5\u4e0a)"));
        return arrayList;
    }

    public ArrayList<FactorScoreBean> getas347List() {
        ArrayList<FactorScoreBean> arrayList = new ArrayList<FactorScoreBean>();
        arrayList.add(new FactorScoreBean(-1, 0.0, "\u8acb\u9078\u64c7"));
        arrayList.add(new FactorScoreBean(1, 0.0, "\u5df7\u9053"));
        arrayList.add(new FactorScoreBean(2, 0.0, "\u6b21\u8981\u9053\u8def"));
        arrayList.add(new FactorScoreBean(3, 0.0, "\u4e3b\u8981\u9053\u8def"));
        arrayList.add(new FactorScoreBean(4, 0.0, "\u8fb2\u8def"));
        arrayList.add(new FactorScoreBean(5, 0.0, "\u79c1\u8a2d\u5df7\u9053"));
        arrayList.add(new FactorScoreBean(6, 0.0, "\u7121\u81e8\u8def"));
        return arrayList;
    }

    public ArrayList<FactorScoreBean> getas368List() {
        ArrayList<FactorScoreBean> arrayList = new ArrayList<FactorScoreBean>();
        arrayList.add(new FactorScoreBean(-1, 0.0, "\u8acb\u9078\u64c7"));
        arrayList.add(new FactorScoreBean(0, 0.0, "\u6709"));
        arrayList.add(new FactorScoreBean(1, 0.0, "\u7121"));
        return arrayList;
    }

    public void calAppraisal() {
        NVO_BASELAND_AHP nVO_BASELAND_AHP = new GoAHPModel().getEditData(EstimateModel.BASELANDBEAN.queryBean.AA45, EstimateModel.BASELANDBEAN.queryBean.year);
        new BaseLandFactorVersionHelper();
        EnumFactorVersion enumFactorVersion = BaseLandFactorVersionHelper.getFactorVersionByString(EstimateModel.BASELANDBEAN.queryBean.version, EstimateModel.BASELANDBEAN.queryBean.urban);
        AutoCalBaseLandAppraisalBo autoCalBaseLandAppraisalBo = new AutoCalBaseLandAppraisalBo(nVO_BASELAND_AHP, enumFactorVersion);
        TreeMap<String, NVO_BASELAND_APPRAISALA3_SCORE> treeMap = new TreeMap<String, NVO_BASELAND_APPRAISALA3_SCORE>();
        treeMap.put("0", EstimateModel.BASELANDBEAN.voAppRaA3Vo0);
        treeMap.put("1", EstimateModel.BASELANDBEAN.voAppRaA3Vo1);
        treeMap.put("2", EstimateModel.BASELANDBEAN.voAppRaA3Vo2);
        treeMap.put("3", EstimateModel.BASELANDBEAN.voAppRaA3Vo3);
        autoCalBaseLandAppraisalBo.setScoreAppMap(treeMap);
        autoCalBaseLandAppraisalBo.setResultVo(EstimateModel.BASELANDBEAN.voAppRaMain);
        autoCalBaseLandAppraisalBo.calResult();
        this.updateMap();
        EstimateModel.BASELANDBEAN.voMain.setPprice(EstimateModel.BASELANDBEAN.voAppRaMain.getFin_pricep());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setAs308Dv() {
        this.updateVo();
        NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE = EstimateModel.BASELANDBEAN.voAppRaA3Vo0;
        Connection connection = null;
        try {
            connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
            String string = new BaseLandReportParamDataBo(nVO_BASELAND_APPRAISALA3_SCORE.getCity(), nVO_BASELAND_APPRAISALA3_SCORE.getYear()).getEditData(connection).getPrice_rate_type();
            EnumPriceRateSubItem enumPriceRateSubItem = EnumPriceRateSubItem.findSelfByBaseno(string, nVO_BASELAND_APPRAISALA3_SCORE.getBaseno());
            String string2 = EstimateModel.BASELANDBEAN.queryBean.AA45;
            String string3 = EstimateModel.BASELANDBEAN.queryBean.AA46;
            String string4 = EstimateModel.BASELANDBEAN.queryBean.year;
            BaseLandPriceRateFillHelper baseLandPriceRateFillHelper = new BaseLandPriceRateFillHelper(string2, string3, string4, enumPriceRateSubItem);
            baseLandPriceRateFillHelper.setAs308Dv(nVO_BASELAND_APPRAISALA3_SCORE, EstimateModel.BASELANDBEAN.voAppRaA3Vo1, connection);
            baseLandPriceRateFillHelper.setAs308Dv(nVO_BASELAND_APPRAISALA3_SCORE, EstimateModel.BASELANDBEAN.voAppRaA3Vo2, connection);
            baseLandPriceRateFillHelper.setAs308Dv(nVO_BASELAND_APPRAISALA3_SCORE, EstimateModel.BASELANDBEAN.voAppRaA3Vo3, connection);
            this.voValues1.put("as308_dv", NumberFormater.df2.format(EstimateModel.BASELANDBEAN.voAppRaA3Vo1.getAs308_dv()));
            this.voValues2.put("as308_dv", NumberFormater.df2.format(EstimateModel.BASELANDBEAN.voAppRaA3Vo2.getAs308_dv()));
            this.voValues3.put("as308_dv", NumberFormater.df2.format(EstimateModel.BASELANDBEAN.voAppRaA3Vo3.getAs308_dv()));
            SqlUtil.close(connection);
        }
        catch (Exception exception) {
            ExceptionDialog.show(exception);
        }
        finally {
            SqlUtil.close(connection);
        }
    }

    public int checkSum100() {
        int n = StringProcess.parserInt(this.voValuesMain.get("fin_ahp1"), 0);
        int n2 = StringProcess.parserInt(this.voValuesMain.get("fin_ahp2"), 0);
        int n3 = StringProcess.parserInt(this.voValuesMain.get("fin_ahp3"), 0);
        return n + n2 + n3;
    }

    public void updateVo() {
        EstimateModel.BASELANDBEAN.voAppRaMain.setBeanByHashMap(this.voValuesMain, false);
        EstimateModel.BASELANDBEAN.voAppRaA3Vo0.setBeanByHashMap(this.voValues0, false);
        EstimateModel.BASELANDBEAN.voAppRaA3Vo1.setBeanByHashMap(this.voValues1, false);
        EstimateModel.BASELANDBEAN.voAppRaA3Vo2.setBeanByHashMap(this.voValues2, false);
        EstimateModel.BASELANDBEAN.voAppRaA3Vo3.setBeanByHashMap(this.voValues3, false);
        EstimateModel.BASELANDBEAN.voAppRaMain.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
        EstimateModel.BASELANDBEAN.voAppRaMain.setCity(EstimateModel.BASELANDBEAN.queryBean.AA45);
        EstimateModel.BASELANDBEAN.voAppRaMain.setOfce(EstimateModel.BASELANDBEAN.queryBean.office);
        EstimateModel.BASELANDBEAN.voAppRaMain.setDist(EstimateModel.BASELANDBEAN.queryBean.AA46);
    }

    public void updateMap() {
        this.voValuesMain.putAll(EstimateModel.BASELANDBEAN.voAppRaMain.getFieldToHashMapExport());
        this.voValues0.putAll(EstimateModel.BASELANDBEAN.voAppRaA3Vo0.getFieldToHashMapExport());
        this.voValues1.putAll(EstimateModel.BASELANDBEAN.voAppRaA3Vo1.getFieldToHashMapExport());
        this.voValues2.putAll(EstimateModel.BASELANDBEAN.voAppRaA3Vo2.getFieldToHashMapExport());
        this.voValues3.putAll(EstimateModel.BASELANDBEAN.voAppRaA3Vo3.getFieldToHashMapExport());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ArrayList<OptionPair> getAS364List() {
        Connection connection = null;
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        arrayList.add(new OptionPair("", "\u8acb\u9078\u64c7"));
        try {
            connection = ConnectionFactory.createConnection("sqlite", SQLITE_PATH + "BaseLand.db", "", "", "", "");
            Map<String, String> map = new NDAO_SRKEYN().getRegdUrbanMap(connection);
            if (map != null && map.size() > 0) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    arrayList.add(new OptionPair(entry.getKey(), entry.getValue()));
                }
            }
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

    public void getParaVersion() throws Exception {
        Connection connection = null;
        try {
            connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
            this.getPriceRateName(connection);
        }
        catch (Exception exception) {
            try {
                throw exception;
            }
            catch (Throwable throwable) {
                SqlUtil.close(connection);
                throw throwable;
            }
        }
        SqlUtil.close(connection);
    }

    private void getPriceRateName(Connection connection) throws Exception {
        NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE = EstimateModel.BASELANDBEAN.voAppRaA3Vo0;
        String string = new BaseLandReportParamDataBo(nVO_BASELAND_APPRAISALA3_SCORE.getCity(), nVO_BASELAND_APPRAISALA3_SCORE.getYear()).getEditData(connection).getPrice_rate_type();
        EnumPriceRateSubItem enumPriceRateSubItem = EnumPriceRateSubItem.findSelfByBaseno(string, nVO_BASELAND_APPRAISALA3_SCORE.getBaseno());
        String string2 = EstimateModel.BASELANDBEAN.queryBean.AA45;
        String string3 = EstimateModel.BASELANDBEAN.queryBean.AA46;
        String string4 = EstimateModel.BASELANDBEAN.queryBean.year;
        BaseLandPriceRateFillHelper baseLandPriceRateFillHelper = new BaseLandPriceRateFillHelper(string2, string3, string4, enumPriceRateSubItem);
        this.priceRateVersion = baseLandPriceRateFillHelper.getIndexRateName(nVO_BASELAND_APPRAISALA3_SCORE.getAs308(), connection);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public String switchLevle(NVO_BASELAND_FACTOR_CODE nVO_BASELAND_FACTOR_CODE, String string, String string2, String string3) {
        Connection connection = null;
        String string4 = "";
        try {
            connection = this.getConnection();
            String string5 = EstimateModel.BASELANDBEAN.queryBean.AA45;
            String string6 = EstimateModel.BASELANDBEAN.queryBean.AA46;
            String string7 = EstimateModel.BASELANDBEAN.queryBean.year;
            String string8 = nVO_BASELAND_FACTOR_CODE.getCode_1() + nVO_BASELAND_FACTOR_CODE.getCode_2();
            switch (nVO_BASELAND_FACTOR_CODE.getCode_0()) {
                case "FR": {
                    string4 = new BaseLandFactorStdTool().getRegionalAutoLevel(string5, string6, string7, EstimateModel.factorVer, string3, string8, string2, string, connection);
                    return string4;
                }
                case "FI": {
                    string4 = new BaseLandFactorStdTool().getIndividualAutoLevel(string5, string6, string7, EstimateModel.factorVer, string3, string8, string2, string, connection);
                    return string4;
                }
            }
            return string4;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return string4;
        }
        finally {
            SqlUtil.close(connection);
        }
    }
}

