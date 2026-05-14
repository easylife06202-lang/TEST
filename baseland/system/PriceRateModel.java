/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.baseland.system;

import com.wfusion.baseland.SQLiteDataProviderModel;
import com.wfusion.baseland.basic.Model;
import com.wfusion.fx.util.ExceptionDialog;
import com.wfusion.util.BigDecimalUtil;
import com.wfusion.util.SUtility;
import com.wfusion.util.SqlUtil;
import com.wfusion.util.StringProcess;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_PRICERATE;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_PRICERATE;
import moiland.baseland.pricerate.bo.BaseLandPriceRateDataBo;
import moiland.baseland.pricerate.formbean.BaseLandPriceRateFormBean;
import moiland.baseland.util.BaseLandCode;
import moiland.baseland.verify.BaseLandVerifyUtil;

public class PriceRateModel
extends Model {
    private String city = "";
    private String rateType = "";
    private String dist = "";
    private String year = "";
    ArrayList<NVO_BASELAND_PRICERATE> listData = null;

    public void setCity(String string) {
        this.city = string;
    }

    public void setRateType(String string) {
        this.rateType = string;
    }

    public void setDist(String string) {
        this.dist = string;
    }

    public void setYear(String string) {
        this.year = string;
    }

    public void checkInput(StringBuilder stringBuilder) {
        stringBuilder.setLength(0);
        if (!BaseLandVerifyUtil.checkCity(this.city, false)) {
            stringBuilder.append("\u8acb\u9078\u64c7 [\u7e23\u5e02] ").append("\n");
        }
        if (!BaseLandVerifyUtil.checkPriceRateDist(this.dist, false)) {
            stringBuilder.append("\u8acb\u9078\u64c7 [\u884c\u653f\u5340] ").append("\n");
        }
        if (!BaseLandVerifyUtil.checkYear(this.year, false)) {
            stringBuilder.append("\u8acb\u9078\u64c7 [\u5e74\u671f] ").append("\n");
        }
        if (!BaseLandVerifyUtil.checkPriceRateType(this.rateType, false)) {
            stringBuilder.append("\u8acb\u9078\u64c7 [\u6307\u6578\u985e\u578b] ").append("\n");
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void query() {
        Connection connection = null;
        try {
            connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
            BaseLandPriceRateFormBean baseLandPriceRateFormBean = this.getFormBean();
            BaseLandPriceRateDataBo baseLandPriceRateDataBo = new BaseLandPriceRateDataBo(baseLandPriceRateFormBean);
            TreeMap<String, ArrayList<NVO_BASELAND_PRICERATE>> treeMap = baseLandPriceRateDataBo.getOneSubTypeData(connection);
            if (treeMap != null) {
                this.listData = new ArrayList();
                for (Map.Entry entry : treeMap.entrySet()) {
                    if (entry.getValue() == null || ((ArrayList)entry.getValue()).size() <= 0) continue;
                    for (NVO_BASELAND_PRICERATE nVO_BASELAND_PRICERATE : (ArrayList)entry.getValue()) {
                        if ((this.year + "03").equals(nVO_BASELAND_PRICERATE.getYm())) {
                            nVO_BASELAND_PRICERATE.setDataType("Base");
                        } else {
                            nVO_BASELAND_PRICERATE.setDataType((String)entry.getKey());
                        }
                        this.listData.add(nVO_BASELAND_PRICERATE);
                    }
                }
            }
            ArrayList<NVO_BASELAND_PRICERATE> arrayList = this.addHundred(this.listData);
            baseLandPriceRateDataBo.saveData(arrayList, connection);
            SqlUtil.close(connection);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
    }

    private ArrayList<NVO_BASELAND_PRICERATE> addHundred(ArrayList<NVO_BASELAND_PRICERATE> arrayList) {
        ArrayList<NVO_BASELAND_PRICERATE> arrayList2 = new ArrayList<NVO_BASELAND_PRICERATE>();
        double d = 100.0;
        HashMap<String, String> hashMap = new HashMap<String, String>();
        StringBuffer stringBuffer = new StringBuffer();
        for (NVO_BASELAND_PRICERATE nVO_BASELAND_PRICERATE : arrayList) {
            if ("Base".equals(nVO_BASELAND_PRICERATE.getDataType())) {
                d = BigDecimalUtil.round(d + nVO_BASELAND_PRICERATE.getIndex_rate(), 2);
            }
            NVO_BASELAND_PRICERATE nVO_BASELAND_PRICERATE2 = new NVO_BASELAND_PRICERATE();
            nVO_BASELAND_PRICERATE2.copyBean(nVO_BASELAND_PRICERATE, nVO_BASELAND_PRICERATE2, hashMap, stringBuffer);
            arrayList2.add(nVO_BASELAND_PRICERATE2);
        }
        for (NVO_BASELAND_PRICERATE nVO_BASELAND_PRICERATE : arrayList2) {
            if ("Base".equals(nVO_BASELAND_PRICERATE.getDataType())) {
                nVO_BASELAND_PRICERATE.setIndex_rate(d);
                continue;
            }
            nVO_BASELAND_PRICERATE.setIndex_rate(BigDecimalUtil.round(d - nVO_BASELAND_PRICERATE.getIndex_rate(), 2));
        }
        return arrayList2;
    }

    private BaseLandPriceRateFormBean getFormBean() {
        BaseLandPriceRateFormBean baseLandPriceRateFormBean = new BaseLandPriceRateFormBean();
        baseLandPriceRateFormBean.setCity(this.city);
        baseLandPriceRateFormBean.setRateType(this.rateType);
        baseLandPriceRateFormBean.setDist(this.dist);
        baseLandPriceRateFormBean.setYear(this.year);
        this.decodeData(baseLandPriceRateFormBean);
        return baseLandPriceRateFormBean;
    }

    private void decodeData(BaseLandPriceRateFormBean baseLandPriceRateFormBean) {
        baseLandPriceRateFormBean.setNameOfCity(SQLiteDataProviderModel.getMapAA45().get(baseLandPriceRateFormBean.getCity()));
        baseLandPriceRateFormBean.setNameOfDist(SQLiteDataProviderModel.getMapAA46().get(baseLandPriceRateFormBean.getCity()).get(baseLandPriceRateFormBean.getDist()));
        baseLandPriceRateFormBean.setNameOfRateType(BaseLandCode.decodePriceRateSubItem(baseLandPriceRateFormBean.getRateType()));
    }

    public ArrayList<NVO_BASELAND_PRICERATE> getListData() {
        return this.listData;
    }

    public void setListData(ArrayList<NVO_BASELAND_PRICERATE> arrayList) {
        this.listData = arrayList;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean save() {
        boolean bl = false;
        Connection connection = null;
        try {
            connection = this.getConnection();
            BaseLandPriceRateFormBean baseLandPriceRateFormBean = this.getFormBean();
            BaseLandPriceRateDataBo baseLandPriceRateDataBo = new BaseLandPriceRateDataBo(baseLandPriceRateFormBean);
            if (this.listData.size() > 0) {
                ArrayList<NVO_BASELAND_PRICERATE> arrayList = this.addHundred(this.listData);
                baseLandPriceRateDataBo.saveData(arrayList, connection);
                bl = true;
            }
        }
        catch (Exception exception) {
            ExceptionDialog.show(exception);
        }
        finally {
            SqlUtil.close(connection);
        }
        return bl;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void delete(NVO_BASELAND_PRICERATE nVO_BASELAND_PRICERATE) {
        Connection connection = null;
        try {
            connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
            new NDAO_BASELAND_PRICERATE().delete(nVO_BASELAND_PRICERATE, connection);
            this.listData.remove(nVO_BASELAND_PRICERATE);
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
    public boolean addPriceRate(String string, String string2, String string3, String string4, String string5, String string6) {
        Connection connection = null;
        boolean bl = true;
        double d = 100.0;
        try {
            connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
            NDAO_BASELAND_PRICERATE nDAO_BASELAND_PRICERATE = new NDAO_BASELAND_PRICERATE();
            NVO_BASELAND_PRICERATE nVO_BASELAND_PRICERATE = nDAO_BASELAND_PRICERATE.findByPk(string, string4, string2, string3, string3 + "03", connection);
            if (nVO_BASELAND_PRICERATE != null) {
                d = nVO_BASELAND_PRICERATE.getIndex_rate();
            }
            NVO_BASELAND_PRICERATE nVO_BASELAND_PRICERATE2 = new NVO_BASELAND_PRICERATE();
            nVO_BASELAND_PRICERATE2.setCity(string);
            nVO_BASELAND_PRICERATE2.setDist(string2);
            nVO_BASELAND_PRICERATE2.setRate_type(string4);
            nVO_BASELAND_PRICERATE2.setYear(string3);
            nVO_BASELAND_PRICERATE2.setYm(string5);
            nVO_BASELAND_PRICERATE2.setIndex_rate(BigDecimalUtil.round(d - StringProcess.parserDouble(string6), 2));
            new NDAO_BASELAND_PRICERATE().update2(nVO_BASELAND_PRICERATE2, connection);
            SqlUtil.close(connection);
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

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean checkRateExist(String string, String string2, String string3, String string4, String string5, String string6) {
        Connection connection = null;
        boolean bl = true;
        try {
            connection = this.getConnection();
            NVO_BASELAND_PRICERATE nVO_BASELAND_PRICERATE = new NDAO_BASELAND_PRICERATE().findByPk(string, string4, string2, string3, string5, connection);
            if (nVO_BASELAND_PRICERATE != null && nVO_BASELAND_PRICERATE.checkPkNotEmpty()) {
                bl = false;
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

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public String importRate(File file) {
        Connection connection = null;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            connection = this.getConnection();
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String string = null;
            int n = 1;
            while ((string = bufferedReader.readLine()) != null) {
                if (n > 1 && !StringProcess.isEmpty(string)) {
                    String[] stringArray = string.split(",");
                    if (stringArray.length >= 6) {
                        String string2 = this.checkAndSave(stringArray, connection);
                        if (!StringProcess.isEmpty(string2)) {
                            stringBuffer.append("\u7b2c" + n + "\u7b46\u8cc7\u6599\u683c\u5f0f\u932f\u8aa4\uff0c" + string2.substring(0, string2.length() - 1) + "\r\n");
                        }
                    } else {
                        stringBuffer.append("\u7b2c" + n + "\u7b46\u8cc7\u6599\u683c\u5f0f\u932f\u8aa4\uff0c\u8acb\u78ba\u8a8d! \r\n");
                    }
                }
                ++n;
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
        return stringBuffer.toString();
    }

    private String checkAndSave(String[] stringArray, Connection connection) throws Exception {
        String string = "";
        if (!BaseLandVerifyUtil.checkCity(stringArray[0], false)) {
            string = string + "\u7e23\u5e02\u4ee3\u78bc\u932f\u8aa4\uff0c\u8acb\u78ba\u8a8d\u3001";
        }
        if (!BaseLandVerifyUtil.checkPriceRateType(stringArray[1], false)) {
            string = string + "\u6307\u6578\u985e\u578b\u932f\u8aa4\uff0c\u8acb\u78ba\u8a8d\u3001";
        }
        if (!BaseLandVerifyUtil.checkDist(stringArray[2], false) && !"00".equals(stringArray[2])) {
            string = string + "\u884c\u653f\u5340\u4ee3\u78bc\u932f\u8aa4\uff0c\u8acb\u78ba\u8a8d\u3001";
        }
        if (!BaseLandVerifyUtil.checkYear(stringArray[3], false)) {
            string = string + "\u5e74\u5ea6\u932f\u8aa4\uff0c\u8acb\u78ba\u8a8d\u3001";
        }
        if (!BaseLandVerifyUtil.checkYearMonth(stringArray[4], false)) {
            string = string + "\u5e74\u6708\u932f\u8aa4\uff0c\u8acb\u78ba\u8a8d\u3001";
        }
        if (StringProcess.isEmpty(string)) {
            NVO_BASELAND_PRICERATE nVO_BASELAND_PRICERATE = new NVO_BASELAND_PRICERATE();
            nVO_BASELAND_PRICERATE.setCity(stringArray[0]);
            nVO_BASELAND_PRICERATE.setRate_type(stringArray[1]);
            nVO_BASELAND_PRICERATE.setDist(stringArray[2]);
            nVO_BASELAND_PRICERATE.setYear(stringArray[3]);
            nVO_BASELAND_PRICERATE.setYm(stringArray[4]);
            nVO_BASELAND_PRICERATE.setIndex_rate(StringProcess.parserDouble(stringArray[5]));
            connection.setAutoCommit(false);
            new NDAO_BASELAND_PRICERATE().update2(nVO_BASELAND_PRICERATE, connection);
            connection.commit();
        }
        return string;
    }
}

