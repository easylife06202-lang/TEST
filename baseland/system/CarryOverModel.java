/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.baseland.system;

import com.wfusion.baseland.basic.Model;
import com.wfusion.baseland.estimate.EstimateModel;
import com.wfusion.dataaccess.vo.VoBase;
import com.wfusion.util.SUtility;
import com.wfusion.util.SqlUtil;
import com.wfusion.util.StringProcess;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import moiland.baseland.action.bean.BaseLandSellParamBean;
import moiland.baseland.bo.BaseLandSellBo;
import moiland.baseland.bo.BaseLandYearCopyHelper;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_DEVELOP;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_DEVELOP_EXT;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_MAIN;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_RENT;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_RENT_EXT;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_RENT_MONTH;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_REPORT_PARAM;
import moiland.baseland.dataaccess.ndao.NDAO_SRKEYN_ALL;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_DEVELOP;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_DEVELOP_EXT;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_MAIN;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT_EXT;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT_MONTH;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_REPORT_PARAM;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_SELL;
import moiland.baseland.dataaccess.nvo.NVO_SRKEYN_ALL;

public class CarryOverModel
extends Model {
    public ArrayList<String> getExistYears() {
        Connection connection = null;
        try {
            connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
            ArrayList<String> arrayList = new BaseLandYearCopyHelper(false, false).getExistYears(connection);
            SqlUtil.close(connection);
            return arrayList;
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
        return new ArrayList<String>();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public String copy(String string, String string2, String string3, boolean bl, boolean bl2) {
        Connection connection = null;
        String string4 = "";
        try {
            connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
            connection.setAutoCommit(false);
            string4 = new BaseLandYearCopyHelper(bl, bl2).copy(string, string2, string3, connection);
            connection.commit();
            connection.setAutoCommit(false);
            this.createInitData(string, string3, bl, bl2, connection);
            connection.commit();
        }
        catch (Exception exception) {
            try {
                string4 = "\u767c\u751f\u932f\u8aa4 - " + exception.getMessage();
                try {
                    connection.rollback();
                }
                catch (SQLException sQLException) {
                    // empty catch block
                }
            }
            catch (Throwable throwable) {
                SqlUtil.close(connection);
                throw throwable;
            }
            SqlUtil.close(connection);
        }
        SqlUtil.close(connection);
        return string4;
    }

    private void createInitData(String string, String string2, boolean bl, boolean bl2, Connection connection) throws Exception {
        ArrayList<NVO_BASELAND_MAIN> arrayList = new NDAO_BASELAND_MAIN().getDataByYear(string, string2, connection);
        if (arrayList != null && arrayList.size() > 0) {
            for (NVO_BASELAND_MAIN nVO_BASELAND_MAIN : arrayList) {
                NVO_BASELAND_SELL nVO_BASELAND_SELL = new NVO_BASELAND_SELL();
                NVO_BASELAND_SELL nVO_BASELAND_SELL2 = new NVO_BASELAND_SELL();
                NVO_BASELAND_SELL nVO_BASELAND_SELL3 = new NVO_BASELAND_SELL();
                this.initSellBeans(nVO_BASELAND_MAIN, nVO_BASELAND_SELL, nVO_BASELAND_SELL2, nVO_BASELAND_SELL3);
                BaseLandSellBo baseLandSellBo = new BaseLandSellBo();
                BaseLandSellParamBean baseLandSellParamBean = new BaseLandSellParamBean(nVO_BASELAND_SELL, "add");
                baseLandSellBo.saveBaseLandSell(baseLandSellParamBean, connection);
                baseLandSellParamBean = new BaseLandSellParamBean(nVO_BASELAND_SELL2, "add");
                baseLandSellBo.saveBaseLandSell(baseLandSellParamBean, connection);
                baseLandSellParamBean = new BaseLandSellParamBean(nVO_BASELAND_SELL3, "add");
                baseLandSellBo.saveBaseLandSell(baseLandSellParamBean, connection);
                if (bl) {
                    this.execCopy(connection, nVO_BASELAND_MAIN, nVO_BASELAND_SELL, nVO_BASELAND_SELL2, nVO_BASELAND_SELL3);
                    continue;
                }
                if (!bl2) continue;
                try {
                    this.execCopy(connection, nVO_BASELAND_MAIN, nVO_BASELAND_SELL, nVO_BASELAND_SELL2, nVO_BASELAND_SELL3);
                }
                catch (Exception exception) {}
            }
        }
    }

    private void execCopy(Connection connection, NVO_BASELAND_MAIN nVO_BASELAND_MAIN, NVO_BASELAND_SELL nVO_BASELAND_SELL, NVO_BASELAND_SELL nVO_BASELAND_SELL2, NVO_BASELAND_SELL nVO_BASELAND_SELL3) throws Exception {
        NVO_BASELAND_RENT_EXT nVO_BASELAND_RENT_EXT = new NVO_BASELAND_RENT_EXT();
        NVO_BASELAND_RENT nVO_BASELAND_RENT = new NVO_BASELAND_RENT();
        NVO_BASELAND_RENT_MONTH nVO_BASELAND_RENT_MONTH = new NVO_BASELAND_RENT_MONTH();
        NVO_BASELAND_RENT_MONTH nVO_BASELAND_RENT_MONTH2 = new NVO_BASELAND_RENT_MONTH();
        NVO_BASELAND_RENT_MONTH nVO_BASELAND_RENT_MONTH3 = new NVO_BASELAND_RENT_MONTH();
        NVO_BASELAND_DEVELOP nVO_BASELAND_DEVELOP = new NVO_BASELAND_DEVELOP();
        NVO_BASELAND_DEVELOP_EXT nVO_BASELAND_DEVELOP_EXT = new NVO_BASELAND_DEVELOP_EXT();
        this.initBeans(nVO_BASELAND_MAIN, nVO_BASELAND_RENT_EXT, nVO_BASELAND_RENT, nVO_BASELAND_RENT_MONTH, nVO_BASELAND_RENT_MONTH2, nVO_BASELAND_RENT_MONTH3, nVO_BASELAND_DEVELOP, nVO_BASELAND_DEVELOP_EXT, connection);
        NVO_BASELAND_REPORT_PARAM nVO_BASELAND_REPORT_PARAM = this.getSystemParam(nVO_BASELAND_MAIN);
        if (nVO_BASELAND_REPORT_PARAM != null) {
            this.setSystemParam(nVO_BASELAND_REPORT_PARAM, nVO_BASELAND_SELL, nVO_BASELAND_SELL2, nVO_BASELAND_SELL3, nVO_BASELAND_RENT_EXT, nVO_BASELAND_RENT, nVO_BASELAND_DEVELOP);
        }
        new NDAO_BASELAND_RENT_EXT().update2(nVO_BASELAND_RENT_EXT, connection);
        NDAO_BASELAND_RENT_MONTH nDAO_BASELAND_RENT_MONTH = new NDAO_BASELAND_RENT_MONTH();
        new NDAO_BASELAND_RENT().update2(nVO_BASELAND_RENT, connection);
        nDAO_BASELAND_RENT_MONTH.update2(nVO_BASELAND_RENT_MONTH, connection);
        nDAO_BASELAND_RENT_MONTH.update2(nVO_BASELAND_RENT_MONTH2, connection);
        nDAO_BASELAND_RENT_MONTH.update2(nVO_BASELAND_RENT_MONTH3, connection);
        new NDAO_BASELAND_DEVELOP().update2(nVO_BASELAND_DEVELOP, connection);
        new NDAO_BASELAND_DEVELOP_EXT().update2(nVO_BASELAND_DEVELOP_EXT, connection);
    }

    private void setSystemParam(NVO_BASELAND_REPORT_PARAM nVO_BASELAND_REPORT_PARAM, NVO_BASELAND_SELL nVO_BASELAND_SELL, NVO_BASELAND_SELL nVO_BASELAND_SELL2, NVO_BASELAND_SELL nVO_BASELAND_SELL3, NVO_BASELAND_RENT_EXT nVO_BASELAND_RENT_EXT, NVO_BASELAND_RENT nVO_BASELAND_RENT, NVO_BASELAND_DEVELOP nVO_BASELAND_DEVELOP) {
        nVO_BASELAND_SELL.setCs09(nVO_BASELAND_REPORT_PARAM.getOwner_rate());
        nVO_BASELAND_SELL.setCs10(nVO_BASELAND_REPORT_PARAM.getOwner_ratio());
        nVO_BASELAND_SELL.setCs12(nVO_BASELAND_REPORT_PARAM.getDebt_rate());
        nVO_BASELAND_SELL.setCs13(nVO_BASELAND_REPORT_PARAM.getDebt_ratio());
        nVO_BASELAND_SELL.setCs15(nVO_BASELAND_REPORT_PARAM.getPresale_rate());
        nVO_BASELAND_SELL.setCs16(nVO_BASELAND_REPORT_PARAM.getPresale_ratio());
        nVO_BASELAND_SELL.setCs32(nVO_BASELAND_REPORT_PARAM.getDesign_ratio());
        nVO_BASELAND_SELL.setCs34(nVO_BASELAND_REPORT_PARAM.getAd_ratio());
        nVO_BASELAND_SELL.setCs36(nVO_BASELAND_REPORT_PARAM.getManage_ratio());
        nVO_BASELAND_SELL.setCs38(nVO_BASELAND_REPORT_PARAM.getTax_ratio());
        nVO_BASELAND_SELL.setCs42(nVO_BASELAND_REPORT_PARAM.getDevp_rate());
        nVO_BASELAND_SELL2.setCs09(nVO_BASELAND_REPORT_PARAM.getOwner_rate());
        nVO_BASELAND_SELL2.setCs10(nVO_BASELAND_REPORT_PARAM.getOwner_ratio());
        nVO_BASELAND_SELL2.setCs12(nVO_BASELAND_REPORT_PARAM.getDebt_rate());
        nVO_BASELAND_SELL2.setCs13(nVO_BASELAND_REPORT_PARAM.getDebt_ratio());
        nVO_BASELAND_SELL2.setCs15(nVO_BASELAND_REPORT_PARAM.getPresale_rate());
        nVO_BASELAND_SELL2.setCs16(nVO_BASELAND_REPORT_PARAM.getPresale_ratio());
        nVO_BASELAND_SELL2.setCs32(nVO_BASELAND_REPORT_PARAM.getDesign_ratio());
        nVO_BASELAND_SELL2.setCs34(nVO_BASELAND_REPORT_PARAM.getAd_ratio());
        nVO_BASELAND_SELL2.setCs36(nVO_BASELAND_REPORT_PARAM.getManage_ratio());
        nVO_BASELAND_SELL2.setCs38(nVO_BASELAND_REPORT_PARAM.getTax_ratio());
        nVO_BASELAND_SELL2.setCs42(nVO_BASELAND_REPORT_PARAM.getDevp_rate());
        nVO_BASELAND_SELL3.setCs09(nVO_BASELAND_REPORT_PARAM.getOwner_rate());
        nVO_BASELAND_SELL3.setCs10(nVO_BASELAND_REPORT_PARAM.getOwner_ratio());
        nVO_BASELAND_SELL3.setCs12(nVO_BASELAND_REPORT_PARAM.getDebt_rate());
        nVO_BASELAND_SELL3.setCs13(nVO_BASELAND_REPORT_PARAM.getDebt_ratio());
        nVO_BASELAND_SELL3.setCs15(nVO_BASELAND_REPORT_PARAM.getPresale_rate());
        nVO_BASELAND_SELL3.setCs16(nVO_BASELAND_REPORT_PARAM.getPresale_ratio());
        nVO_BASELAND_SELL3.setCs32(nVO_BASELAND_REPORT_PARAM.getDesign_ratio());
        nVO_BASELAND_SELL3.setCs34(nVO_BASELAND_REPORT_PARAM.getAd_ratio());
        nVO_BASELAND_SELL3.setCs36(nVO_BASELAND_REPORT_PARAM.getManage_ratio());
        nVO_BASELAND_SELL3.setCs38(nVO_BASELAND_REPORT_PARAM.getTax_ratio());
        nVO_BASELAND_SELL3.setCs42(nVO_BASELAND_REPORT_PARAM.getDevp_rate());
        nVO_BASELAND_RENT_EXT.setCre10(nVO_BASELAND_REPORT_PARAM.getOwner_rate());
        nVO_BASELAND_RENT_EXT.setCre11(nVO_BASELAND_REPORT_PARAM.getOwner_ratio());
        nVO_BASELAND_RENT_EXT.setCre13(nVO_BASELAND_REPORT_PARAM.getDebt_rate());
        nVO_BASELAND_RENT_EXT.setCre14(nVO_BASELAND_REPORT_PARAM.getDebt_ratio());
        nVO_BASELAND_RENT_EXT.setCre16(nVO_BASELAND_REPORT_PARAM.getPresale_rate());
        nVO_BASELAND_RENT_EXT.setCre17(nVO_BASELAND_REPORT_PARAM.getPresale_ratio());
        nVO_BASELAND_RENT_EXT.setCre35(nVO_BASELAND_REPORT_PARAM.getDesign_ratio());
        nVO_BASELAND_RENT_EXT.setCre38(nVO_BASELAND_REPORT_PARAM.getAd_ratio());
        nVO_BASELAND_RENT_EXT.setCre40(nVO_BASELAND_REPORT_PARAM.getManage_ratio());
        nVO_BASELAND_RENT_EXT.setCre42(nVO_BASELAND_REPORT_PARAM.getTax_ratio());
        nVO_BASELAND_RENT_EXT.setCre50(nVO_BASELAND_REPORT_PARAM.getDevp_rate());
        nVO_BASELAND_RENT.setCr30(nVO_BASELAND_REPORT_PARAM.getBuild_benefit_rate());
        nVO_BASELAND_RENT.setCr35(nVO_BASELAND_REPORT_PARAM.getLand_benefit_rate());
        nVO_BASELAND_DEVELOP.setOwner_rate(nVO_BASELAND_REPORT_PARAM.getOwner_rate());
        nVO_BASELAND_DEVELOP.setOwner_ratio(nVO_BASELAND_REPORT_PARAM.getOwner_ratio());
        nVO_BASELAND_DEVELOP.setDebt_rate(nVO_BASELAND_REPORT_PARAM.getDebt_rate());
        nVO_BASELAND_DEVELOP.setDebt_ratio(nVO_BASELAND_REPORT_PARAM.getDebt_ratio());
        nVO_BASELAND_DEVELOP.setPresale_rate(nVO_BASELAND_REPORT_PARAM.getPresale_rate());
        nVO_BASELAND_DEVELOP.setPresale_ratio(nVO_BASELAND_REPORT_PARAM.getPresale_ratio());
        nVO_BASELAND_DEVELOP.setDesign_ratio(nVO_BASELAND_REPORT_PARAM.getDesign_ratio());
        nVO_BASELAND_DEVELOP.setAd_ratio(nVO_BASELAND_REPORT_PARAM.getAd_ratio());
        nVO_BASELAND_DEVELOP.setManage_ratio(nVO_BASELAND_REPORT_PARAM.getManage_ratio());
        nVO_BASELAND_DEVELOP.setTax_ratio(nVO_BASELAND_REPORT_PARAM.getTax_ratio());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private NVO_BASELAND_REPORT_PARAM getSystemParam(NVO_BASELAND_MAIN nVO_BASELAND_MAIN) {
        Connection connection = null;
        NVO_BASELAND_REPORT_PARAM nVO_BASELAND_REPORT_PARAM = null;
        try {
            String string = nVO_BASELAND_MAIN.getCity();
            String string2 = nVO_BASELAND_MAIN.getYear();
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

    private void initSellBeans(NVO_BASELAND_MAIN nVO_BASELAND_MAIN, NVO_BASELAND_SELL nVO_BASELAND_SELL, NVO_BASELAND_SELL nVO_BASELAND_SELL2, NVO_BASELAND_SELL nVO_BASELAND_SELL3) {
        nVO_BASELAND_SELL.setCity(nVO_BASELAND_MAIN.getCity());
        nVO_BASELAND_SELL.setDist(nVO_BASELAND_MAIN.getDist());
        nVO_BASELAND_SELL.setBaseno(nVO_BASELAND_MAIN.getBaseno());
        nVO_BASELAND_SELL.setYear(nVO_BASELAND_MAIN.getYear());
        nVO_BASELAND_SELL.setCaseno("1");
        nVO_BASELAND_SELL.setCs66(100.0);
        nVO_BASELAND_SELL2.setCity(nVO_BASELAND_MAIN.getCity());
        nVO_BASELAND_SELL2.setDist(nVO_BASELAND_MAIN.getDist());
        nVO_BASELAND_SELL2.setBaseno(nVO_BASELAND_MAIN.getBaseno());
        nVO_BASELAND_SELL2.setYear(nVO_BASELAND_MAIN.getYear());
        nVO_BASELAND_SELL2.setCaseno("2");
        nVO_BASELAND_SELL2.setCs66(100.0);
        nVO_BASELAND_SELL3.setCity(nVO_BASELAND_MAIN.getCity());
        nVO_BASELAND_SELL3.setDist(nVO_BASELAND_MAIN.getDist());
        nVO_BASELAND_SELL3.setBaseno(nVO_BASELAND_MAIN.getBaseno());
        nVO_BASELAND_SELL3.setYear(nVO_BASELAND_MAIN.getYear());
        nVO_BASELAND_SELL3.setCaseno("3");
        nVO_BASELAND_SELL3.setCs66(100.0);
    }

    private void initBeans(NVO_BASELAND_MAIN nVO_BASELAND_MAIN, NVO_BASELAND_RENT_EXT nVO_BASELAND_RENT_EXT, NVO_BASELAND_RENT nVO_BASELAND_RENT, NVO_BASELAND_RENT_MONTH nVO_BASELAND_RENT_MONTH, NVO_BASELAND_RENT_MONTH nVO_BASELAND_RENT_MONTH2, NVO_BASELAND_RENT_MONTH nVO_BASELAND_RENT_MONTH3, NVO_BASELAND_DEVELOP nVO_BASELAND_DEVELOP, NVO_BASELAND_DEVELOP_EXT nVO_BASELAND_DEVELOP_EXT, Connection connection) {
        nVO_BASELAND_RENT_EXT.setBaseno(nVO_BASELAND_MAIN.getBaseno());
        nVO_BASELAND_RENT_EXT.setYear(nVO_BASELAND_MAIN.getYear());
        nVO_BASELAND_RENT_EXT.setCity(nVO_BASELAND_MAIN.getCity());
        nVO_BASELAND_RENT_EXT.setDist(nVO_BASELAND_MAIN.getDist());
        double d = new EstimateModel().getAdjustRatio(nVO_BASELAND_MAIN.getYear() + "0331");
        nVO_BASELAND_RENT_EXT.setCre32(d);
        nVO_BASELAND_RENT_EXT.setCre59(100.0);
        nVO_BASELAND_RENT.setBaseno(nVO_BASELAND_MAIN.getBaseno());
        nVO_BASELAND_RENT.setYear(nVO_BASELAND_MAIN.getYear());
        nVO_BASELAND_RENT.setCity(nVO_BASELAND_MAIN.getCity());
        nVO_BASELAND_RENT.setDist(nVO_BASELAND_MAIN.getDist());
        nVO_BASELAND_RENT_MONTH.setBaseno(nVO_BASELAND_MAIN.getBaseno());
        nVO_BASELAND_RENT_MONTH.setYear(nVO_BASELAND_MAIN.getYear());
        nVO_BASELAND_RENT_MONTH.setCity(nVO_BASELAND_MAIN.getCity());
        nVO_BASELAND_RENT_MONTH.setRent_caseno("1");
        nVO_BASELAND_RENT_MONTH2.setBaseno(nVO_BASELAND_MAIN.getBaseno());
        nVO_BASELAND_RENT_MONTH2.setYear(nVO_BASELAND_MAIN.getYear());
        nVO_BASELAND_RENT_MONTH2.setCity(nVO_BASELAND_MAIN.getCity());
        nVO_BASELAND_RENT_MONTH2.setRent_caseno("2");
        nVO_BASELAND_RENT_MONTH3.setBaseno(nVO_BASELAND_MAIN.getBaseno());
        nVO_BASELAND_RENT_MONTH3.setYear(nVO_BASELAND_MAIN.getYear());
        nVO_BASELAND_RENT_MONTH3.setCity(nVO_BASELAND_MAIN.getCity());
        nVO_BASELAND_RENT_MONTH3.setRent_caseno("3");
        nVO_BASELAND_DEVELOP.setOfce(nVO_BASELAND_MAIN.getOfce());
        nVO_BASELAND_DEVELOP.setBaseno(nVO_BASELAND_MAIN.getBaseno());
        nVO_BASELAND_DEVELOP.setYear(nVO_BASELAND_MAIN.getYear());
        nVO_BASELAND_DEVELOP.setCity(nVO_BASELAND_MAIN.getCity());
        nVO_BASELAND_DEVELOP.setDist(nVO_BASELAND_MAIN.getDist());
        nVO_BASELAND_DEVELOP.setSale_are_ratio(1.6);
        this.setDevelopSaleParamDefaultValue(nVO_BASELAND_DEVELOP);
        nVO_BASELAND_DEVELOP.setBuild_cost_rate(d);
        nVO_BASELAND_DEVELOP.setBuild_cost_exp(100.0);
        nVO_BASELAND_DEVELOP_EXT.setBaseno(nVO_BASELAND_MAIN.getBaseno());
        nVO_BASELAND_DEVELOP_EXT.setYear(nVO_BASELAND_MAIN.getYear());
        nVO_BASELAND_DEVELOP_EXT.setCity(nVO_BASELAND_MAIN.getCity());
    }

    private void setDevelopSaleParamDefaultValue(NVO_BASELAND_DEVELOP nVO_BASELAND_DEVELOP) {
        if (nVO_BASELAND_DEVELOP.getSale_electric() == 0.0) {
            nVO_BASELAND_DEVELOP.setSale_electric(1.15);
        }
        if (nVO_BASELAND_DEVELOP.getSale_balcony() == 0.0) {
            nVO_BASELAND_DEVELOP.setSale_balcony(1.15);
        }
        if (nVO_BASELAND_DEVELOP.getSale_protrusionc() == 0) {
            nVO_BASELAND_DEVELOP.setSale_protrusionc(3);
        }
        if (nVO_BASELAND_DEVELOP.getSale_protrusionm() == 0) {
            nVO_BASELAND_DEVELOP.setSale_protrusionm(8);
        }
        if (nVO_BASELAND_DEVELOP.getSale_publicratio() == 0.0) {
            nVO_BASELAND_DEVELOP.setSale_publicratio(0.15);
        }
        if (nVO_BASELAND_DEVELOP.getSale_parkarea() == 0.0) {
            nVO_BASELAND_DEVELOP.setSale_parkarea(12.0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int updateSect() {
        Connection connection = null;
        int n = 0;
        try {
            Object object;
            connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
            URL uRL = new URL("https://lisp.land.moi.gov.tw/MMS/Handle/DownloadQuerySection.ashx?DownloadType=csv");
            HttpURLConnection httpURLConnection = (HttpURLConnection)uRL.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestProperty("User-Agent", "CodeJava Agent");
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            ArrayList<String[]> arrayList = new ArrayList<String[]>();
            String string = "";
            while ((string = bufferedReader.readLine()) != null) {
                object = string;
                if (((String)object).indexOf(",") <= -1) continue;
                String[] stringArray = ((String)object).split(",");
                arrayList.add(stringArray);
                ++n;
            }
            object = this.readTextCSV(arrayList);
            connection.setAutoCommit(false);
            new NDAO_SRKEYN_ALL().delete((ArrayList<? extends VoBase>)object, connection);
            new NDAO_SRKEYN_ALL().create((ArrayList<? extends VoBase>)object, connection);
            connection.commit();
            SqlUtil.close(connection);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
        return n;
    }

    private ArrayList<NVO_SRKEYN_ALL> readTextCSV(ArrayList<String[]> arrayList) {
        String string = "\u6bb5";
        String string2 = "\u5c0f\u6bb5";
        ArrayList<NVO_SRKEYN_ALL> arrayList2 = new ArrayList<NVO_SRKEYN_ALL>();
        for (int i = 1; i < arrayList.size(); ++i) {
            String[] stringArray = arrayList.get(i);
            String string3 = StringProcess.NULL(stringArray[0]);
            String string4 = StringProcess.NULL(stringArray[1]);
            String string5 = StringProcess.NULL(stringArray[2]);
            String string6 = StringProcess.NULL(stringArray[3]);
            String string7 = stringArray[4];
            if (string6.equals("\u8a3b\u92b7") || StringProcess.isEmpty(string3) || StringProcess.isEmpty(string5) || StringProcess.isEmpty(string7)) continue;
            NVO_SRKEYN_ALL nVO_SRKEYN_ALL = new NVO_SRKEYN_ALL();
            if (string7.length() != 4) continue;
            nVO_SRKEYN_ALL.setKcde_1("48");
            String string8 = "";
            String string9 = "";
            String string10 = "";
            string8 = string7.substring(0, 1);
            string9 = string7.substring(2, 4);
            string10 = string7.substring(0, 2);
            nVO_SRKEYN_ALL.setKcde_2(string8);
            nVO_SRKEYN_ALL.setKcde_3(string9);
            nVO_SRKEYN_ALL.setKrmk(string10);
            if (string3.length() > 0) {
                nVO_SRKEYN_ALL.setKcde_4(StringProcess.fillZero(string5, 4));
            }
            StringBuffer stringBuffer = new StringBuffer("");
            if (string3.length() > 0) {
                if (string3.substring(string3.length() - 1).equals(string)) {
                    stringBuffer.append(StringProcess.ascii2Unicode(string3));
                } else {
                    stringBuffer.append(StringProcess.ascii2Unicode(string3));
                    stringBuffer.append(string);
                }
            }
            if (!StringProcess.isEmpty(string4) && string4.length() > 0) {
                stringBuffer.append(StringProcess.ascii2Unicode(string4));
                stringBuffer.append(string2);
            }
            nVO_SRKEYN_ALL.setKname(stringBuffer.toString());
            arrayList2.add(nVO_SRKEYN_ALL);
        }
        return arrayList2;
    }
}

