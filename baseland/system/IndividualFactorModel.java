/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.baseland.system;

import com.wfusion.baseland.SQLiteDataProviderModel;
import com.wfusion.baseland.basic.Model;
import com.wfusion.baseland.system.SystemIndividualFactor_editController;
import com.wfusion.util.OptionPair;
import com.wfusion.util.SUtility;
import com.wfusion.util.SqlUtil;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_INDIVIDUAL_FACTOR;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_INDIVIDUAL_FACTOR_STD;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_INDIVIDUAL_FACTOR;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_INDIVIDUAL_FACTOR_STD;
import moiland.baseland.factor.bean.FactorItemBean;
import moiland.baseland.factor.bo.BaseLandFactorDataBo;
import moiland.baseland.factor.bo.BaseLandFactorHtmlHelper;
import moiland.baseland.factor.bo.BaseLandIndividualFactorFileBo;
import moiland.baseland.factor.em.EnumFactorType;
import moiland.baseland.util.BaseLandCode;

public class IndividualFactorModel
extends Model {
    ArrayList<NVO_BASELAND_INDIVIDUAL_FACTOR> listData = null;
    Map<String, FactorItemBean> factorItem = null;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ArrayList<NVO_BASELAND_INDIVIDUAL_FACTOR> query(String string, String string2, String string3, String string4, String string5, String string6) {
        Connection connection = null;
        try {
            connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
            BaseLandCode.refreshCode(connection, connection, connection);
            NVO_BASELAND_INDIVIDUAL_FACTOR nVO_BASELAND_INDIVIDUAL_FACTOR = this.getFormBean(string, string2, string3, string4, string5, string6);
            this.listData = new NDAO_BASELAND_INDIVIDUAL_FACTOR().queryOneVersionByMainCode(nVO_BASELAND_INDIVIDUAL_FACTOR, connection);
            BaseLandFactorDataBo baseLandFactorDataBo = new BaseLandFactorDataBo(EnumFactorType.INDIVIDUAL);
            this.factorItem = baseLandFactorDataBo.getFactorItemByVersion(nVO_BASELAND_INDIVIDUAL_FACTOR.getVersion());
            this.decodeData(this.listData);
            SqlUtil.close(connection);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
        return this.listData;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void refreshCode(String string) {
        Connection connection = null;
        try {
            connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
            BaseLandCode.refreshCode(connection, connection, connection);
            SqlUtil.close(connection);
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
    public boolean isExist(NVO_BASELAND_INDIVIDUAL_FACTOR nVO_BASELAND_INDIVIDUAL_FACTOR) {
        Connection connection = null;
        try {
            connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
            boolean bl = new NDAO_BASELAND_INDIVIDUAL_FACTOR().isExist(nVO_BASELAND_INDIVIDUAL_FACTOR, connection);
            SqlUtil.close(connection);
            return bl;
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void save(NVO_BASELAND_INDIVIDUAL_FACTOR nVO_BASELAND_INDIVIDUAL_FACTOR, String string, String string2, String string3, String string4, String string5, String string6) {
        Connection connection = null;
        try {
            connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
            new NDAO_BASELAND_INDIVIDUAL_FACTOR().update2(nVO_BASELAND_INDIVIDUAL_FACTOR, connection);
            NVO_BASELAND_INDIVIDUAL_FACTOR nVO_BASELAND_INDIVIDUAL_FACTOR2 = this.getFormBean(string, string2, string3, string4, string5, string6);
            this.listData = new NDAO_BASELAND_INDIVIDUAL_FACTOR().queryOneVersionByMainCode(nVO_BASELAND_INDIVIDUAL_FACTOR2, connection);
            this.decodeData(this.listData);
            SqlUtil.close(connection);
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
    public void delete(NVO_BASELAND_INDIVIDUAL_FACTOR nVO_BASELAND_INDIVIDUAL_FACTOR) {
        Connection connection = null;
        try {
            connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
            new NDAO_BASELAND_INDIVIDUAL_FACTOR().delete(nVO_BASELAND_INDIVIDUAL_FACTOR, connection);
            this.listData.remove(nVO_BASELAND_INDIVIDUAL_FACTOR);
            SqlUtil.close(connection);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
    }

    private NVO_BASELAND_INDIVIDUAL_FACTOR getFormBean(String string, String string2, String string3, String string4, String string5, String string6) {
        NVO_BASELAND_INDIVIDUAL_FACTOR nVO_BASELAND_INDIVIDUAL_FACTOR = new NVO_BASELAND_INDIVIDUAL_FACTOR();
        nVO_BASELAND_INDIVIDUAL_FACTOR.setCity(string);
        nVO_BASELAND_INDIVIDUAL_FACTOR.setDist(string2);
        nVO_BASELAND_INDIVIDUAL_FACTOR.setYear(string3);
        nVO_BASELAND_INDIVIDUAL_FACTOR.setVersion(string4);
        nVO_BASELAND_INDIVIDUAL_FACTOR.setBaseno(string5);
        nVO_BASELAND_INDIVIDUAL_FACTOR.setMainCode(string6);
        this.decodeData(nVO_BASELAND_INDIVIDUAL_FACTOR);
        return nVO_BASELAND_INDIVIDUAL_FACTOR;
    }

    private void decodeData(ArrayList<NVO_BASELAND_INDIVIDUAL_FACTOR> arrayList) {
        for (NVO_BASELAND_INDIVIDUAL_FACTOR nVO_BASELAND_INDIVIDUAL_FACTOR : arrayList) {
            this.decodeData(nVO_BASELAND_INDIVIDUAL_FACTOR);
        }
    }

    private void decodeData(NVO_BASELAND_INDIVIDUAL_FACTOR nVO_BASELAND_INDIVIDUAL_FACTOR) {
        nVO_BASELAND_INDIVIDUAL_FACTOR.setNameOfCity(SQLiteDataProviderModel.getMapAA45().get(nVO_BASELAND_INDIVIDUAL_FACTOR.getCity()));
        nVO_BASELAND_INDIVIDUAL_FACTOR.setNameOfDist(SQLiteDataProviderModel.getMapAA46().get(nVO_BASELAND_INDIVIDUAL_FACTOR.getCity()).get(nVO_BASELAND_INDIVIDUAL_FACTOR.getDist()));
        nVO_BASELAND_INDIVIDUAL_FACTOR.setNameOfVersion(BaseLandCode.decodeFactorVersion(nVO_BASELAND_INDIVIDUAL_FACTOR.getVersion()));
        nVO_BASELAND_INDIVIDUAL_FACTOR.setNameOfMainCode(BaseLandCode.decodeIndividualMainCode(nVO_BASELAND_INDIVIDUAL_FACTOR.getVersion(), nVO_BASELAND_INDIVIDUAL_FACTOR.getMainCode()));
        nVO_BASELAND_INDIVIDUAL_FACTOR.setNameOfItem(BaseLandCode.decodeIndividualItem(nVO_BASELAND_INDIVIDUAL_FACTOR.getVersion(), nVO_BASELAND_INDIVIDUAL_FACTOR.getItem()));
    }

    public ArrayList<NVO_BASELAND_INDIVIDUAL_FACTOR> getListData() {
        return this.listData;
    }

    public ArrayList<OptionPair> getDegreeList() {
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        for (int i = 2; i <= 15; ++i) {
            arrayList.add(new OptionPair(String.valueOf(i), String.valueOf(i)));
        }
        return arrayList;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public String importAndCheckData(String string, String string2, boolean bl) {
        Connection connection = null;
        String string3 = "";
        try {
            connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
            BaseLandIndividualFactorFileBo baseLandIndividualFactorFileBo = new BaseLandIndividualFactorFileBo();
            string3 = baseLandIndividualFactorFileBo.process_import(string, string2, bl, connection);
        }
        catch (Exception exception) {
            try {
                exception.printStackTrace();
                string3 = exception.toString();
            }
            catch (Throwable throwable) {
                SqlUtil.close(connection);
                throw throwable;
            }
            SqlUtil.close(connection);
        }
        SqlUtil.close(connection);
        return string3;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void editStd(NVO_BASELAND_INDIVIDUAL_FACTOR nVO_BASELAND_INDIVIDUAL_FACTOR, EnumFactorType enumFactorType, SystemIndividualFactor_editController systemIndividualFactor_editController) {
        Connection connection = null;
        try {
            connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
            BaseLandFactorDataBo baseLandFactorDataBo = new BaseLandFactorDataBo(enumFactorType);
            nVO_BASELAND_INDIVIDUAL_FACTOR = baseLandFactorDataBo.findMainDataByPk(nVO_BASELAND_INDIVIDUAL_FACTOR, connection);
            this.decodeData(nVO_BASELAND_INDIVIDUAL_FACTOR);
            ArrayList<NVO_BASELAND_INDIVIDUAL_FACTOR_STD> arrayList = baseLandFactorDataBo.queryStdDataByItem(nVO_BASELAND_INDIVIDUAL_FACTOR, connection);
            String string = BaseLandFactorHtmlHelper.getShowText("\u4ee5" + nVO_BASELAND_INDIVIDUAL_FACTOR.getNameOfItem() + "\u8861\u91cf", 56);
            systemIndividualFactor_editController.factorItem = baseLandFactorDataBo.getFactorItemByItem(nVO_BASELAND_INDIVIDUAL_FACTOR.getVersion(), nVO_BASELAND_INDIVIDUAL_FACTOR.getItem());
            systemIndividualFactor_editController.codeMap = BaseLandFactorHtmlHelper.getCodeMap(enumFactorType, nVO_BASELAND_INDIVIDUAL_FACTOR);
            systemIndividualFactor_editController.editData = arrayList;
            systemIndividualFactor_editController.defaultText = string;
            SqlUtil.close(connection);
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
    public void saveStdData(NVO_BASELAND_INDIVIDUAL_FACTOR nVO_BASELAND_INDIVIDUAL_FACTOR, ArrayList<NVO_BASELAND_INDIVIDUAL_FACTOR_STD> arrayList) {
        Connection connection = null;
        try {
            connection = this.getConnection();
            connection.setAutoCommit(false);
            if (nVO_BASELAND_INDIVIDUAL_FACTOR != null) {
                new NDAO_BASELAND_INDIVIDUAL_FACTOR().update2(nVO_BASELAND_INDIVIDUAL_FACTOR, connection);
            }
            new NDAO_BASELAND_INDIVIDUAL_FACTOR_STD().clearStdDataByItem(nVO_BASELAND_INDIVIDUAL_FACTOR.getCity(), nVO_BASELAND_INDIVIDUAL_FACTOR.getDist(), nVO_BASELAND_INDIVIDUAL_FACTOR.getYear(), nVO_BASELAND_INDIVIDUAL_FACTOR.getVersion(), nVO_BASELAND_INDIVIDUAL_FACTOR.getBaseno(), nVO_BASELAND_INDIVIDUAL_FACTOR.getItem(), connection);
            if (arrayList != null && arrayList.size() > 0) {
                new NDAO_BASELAND_INDIVIDUAL_FACTOR_STD().update2(arrayList, connection);
            }
            connection.commit();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
    }
}

