/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.baseland.estimate;

import com.wfusion.baseland.BaseLandBean;
import com.wfusion.baseland.SQLiteDataProviderModel;
import com.wfusion.baseland.basic.Model;
import com.wfusion.baseland.estimate.EstimateModel;
import com.wfusion.baseland.estimate.EstimateReport8Model;
import com.wfusion.baseland.system.GoAHPModel;
import com.wfusion.fx.util.NumberFormater;
import com.wfusion.util.BigDecimalUtil;
import com.wfusion.util.ConnectionFactory;
import com.wfusion.util.OptionPair;
import com.wfusion.util.SqlUtil;
import com.wfusion.util.StringProcess;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import moiland.baseland.bo.AutoCalBaseLandMasterBo;
import moiland.baseland.dataaccess.ndao.NDAO_SRKEYN;
import moiland.baseland.dataaccess.ndao.NDAO_SRKEYN_ALL;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_AHP;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_APPRAISALA3_SCORE;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT_MONTH;
import moiland.baseland.dataaccess.nvo.NVO_SRKEYN_ALL;

public class EstimateReport1Model
extends Model {
    HashMap<String, Object> voValue = new HashMap();

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ArrayList<String> getLanduseList() {
        Connection connection = null;
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("\u8acb\u9078\u64c7");
        try {
            connection = ConnectionFactory.createConnection("sqlite", SQLITE_PATH + "BaseLand.db", "", "", "", "");
            Map<String, String> map = new NDAO_SRKEYN().getRegdUrbanMap(connection);
            arrayList.addAll(map.values());
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

    public ArrayList<OptionPair> getAA49List() {
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        String[] stringArray = StringProcess.split(EstimateModel.BASELANDBEAN.voMain.getAa49(), ",");
        HashMap<String, String> hashMap = SQLiteDataProviderModel.getAA48Map(EstimateModel.BASELANDBEAN.queryBean.AA45, "", "");
        for (String string : stringArray) {
            if (string.length() != 12) continue;
            String string2 = string.substring(0, 4);
            arrayList.add(new OptionPair(string, "(" + string2 + ")" + hashMap.get(string2) + " \u5730\u865f\uff1a" + string.substring(4)));
        }
        return arrayList;
    }

    public void updateHashMapValues() {
        this.voValue = EstimateModel.BASELANDBEAN.voMain.getFieldToHashMapExport();
        NVO_BASELAND_AHP nVO_BASELAND_AHP = new GoAHPModel().getEditData(EstimateModel.BASELANDBEAN.queryBean.AA45, EstimateModel.BASELANDBEAN.queryBean.year);
        AutoCalBaseLandMasterBo autoCalBaseLandMasterBo = new AutoCalBaseLandMasterBo();
        TreeMap<String, NVO_BASELAND_APPRAISALA3_SCORE> treeMap = new TreeMap<String, NVO_BASELAND_APPRAISALA3_SCORE>();
        TreeMap<String, NVO_BASELAND_RENT_MONTH> treeMap2 = new TreeMap<String, NVO_BASELAND_RENT_MONTH>();
        treeMap2.put("1", EstimateModel.BASELANDBEAN.voRentMonth1);
        treeMap2.put("2", EstimateModel.BASELANDBEAN.voRentMonth2);
        treeMap2.put("3", EstimateModel.BASELANDBEAN.voRentMonth3);
        treeMap.put("0", EstimateModel.BASELANDBEAN.voAppRaA3Vo0);
        treeMap.put("1", EstimateModel.BASELANDBEAN.voAppRaA3Vo1);
        treeMap.put("2", EstimateModel.BASELANDBEAN.voAppRaA3Vo2);
        treeMap.put("3", EstimateModel.BASELANDBEAN.voAppRaA3Vo3);
        autoCalBaseLandMasterBo.setMain(EstimateModel.BASELANDBEAN.voMain);
        autoCalBaseLandMasterBo.setVoDevelop(EstimateModel.BASELANDBEAN.voDevelop);
        autoCalBaseLandMasterBo.setApp(EstimateModel.BASELANDBEAN.voAppRaMain);
        autoCalBaseLandMasterBo.setRent(EstimateModel.BASELANDBEAN.voRent);
        autoCalBaseLandMasterBo.setRent_ext(EstimateModel.BASELANDBEAN.voRentExt);
        autoCalBaseLandMasterBo.setAhp(nVO_BASELAND_AHP);
        autoCalBaseLandMasterBo.setScoreAppMap(treeMap);
        autoCalBaseLandMasterBo.setRent_month(treeMap2);
        autoCalBaseLandMasterBo.getNormailizeCAL_AHP();
        if (!Double.isNaN(EstimateModel.BASELANDBEAN.voMain.getAph_sell()) && !Double.isInfinite(EstimateModel.BASELANDBEAN.voMain.getAph_sell())) {
            this.voValue.put("pprice_ratio1", NumberFormater.df1.format(EstimateModel.BASELANDBEAN.voMain.getAph_sell()));
        } else {
            EstimateModel.BASELANDBEAN.voMain.setAph_sell(0.0);
        }
        if (!Double.isNaN(EstimateModel.BASELANDBEAN.voMain.getAhp_rent()) && !Double.isInfinite(EstimateModel.BASELANDBEAN.voMain.getAhp_rent())) {
            this.voValue.put("rprice_ratio1", NumberFormater.df1.format(EstimateModel.BASELANDBEAN.voMain.getAhp_rent()));
        } else {
            EstimateModel.BASELANDBEAN.voMain.setAhp_rent(0.0);
        }
        if (!Double.isNaN(EstimateModel.BASELANDBEAN.voMain.getAhp_develop()) && !Double.isInfinite(EstimateModel.BASELANDBEAN.voMain.getAhp_develop())) {
            this.voValue.put("cprice_ratio1", NumberFormater.df1.format(EstimateModel.BASELANDBEAN.voMain.getAhp_develop()));
        } else {
            EstimateModel.BASELANDBEAN.voMain.setAhp_develop(0.0);
        }
    }

    public void reCal() {
        new EstimateReport8Model().reCal();
        BaseLandBean baseLandBean = EstimateModel.BASELANDBEAN;
        double d = baseLandBean.voMain.getPprice_ratio();
        double d2 = baseLandBean.voMain.getRprice_ratio();
        double d3 = baseLandBean.voMain.getCprice_ratio();
        double d4 = d + d2 + d3;
        if (d4 == 100.0) {
            double d5 = 0.0;
            if (baseLandBean.voMain.getPprice() > 0 && d > 0.0) {
                d5 += (double)baseLandBean.voMain.getPprice() * (d / 100.0);
            }
            if (baseLandBean.voMain.getRprice() > 0 && d2 > 0.0) {
                d5 += (double)baseLandBean.voMain.getRprice() * (d2 / 100.0);
            }
            if (baseLandBean.voMain.getCprice() > 0 && d3 > 0.0) {
                d5 += (double)baseLandBean.voMain.getCprice() * (d3 / 100.0);
            }
            d5 = StringProcess.roundCd(d5);
            double d6 = StringProcess.roundCd(d5 * 0.3025);
            baseLandBean.voMain.setBase_pricem((int)d6);
            baseLandBean.voMain.setBase_pricep((int)d5);
        }
        baseLandBean.voRentExt.setLand_position(baseLandBean.voMain.getLand_position());
        baseLandBean.voAppRaA3Vo0.setAs301(baseLandBean.voMain.getLand_scene().equals("01") ? (!StringProcess.isEmpty(baseLandBean.voMain.getAddr()) ? baseLandBean.voMain.getAddr() : baseLandBean.voMain.getLand_position()) : baseLandBean.voMain.getLand_position());
        baseLandBean.voRentExt.setCre02(EstimateModel.BASELANDBEAN.voMain.getAddr());
        baseLandBean.voAppRaA3Vo0.setAs308(baseLandBean.voMain.getPrice_date());
        baseLandBean.voRentExt.setCre33(baseLandBean.voMain.getPrice_date());
        baseLandBean.voAppRaA3Vo0.setAs339(baseLandBean.voMain.getAa10());
        baseLandBean.voAppRaA3Vo0.setAs365_ds(baseLandBean.voMain.getCov_ratio());
        baseLandBean.voAppRaA3Vo0.setAs366_ds(baseLandBean.voMain.getAre_ratio());
        baseLandBean.voAppRaA3Vo0.setAs340_ds(baseLandBean.voMain.getWidth());
        baseLandBean.voAppRaA3Vo0.setAs341_ds(baseLandBean.voMain.getDeep());
        baseLandBean.voAppRaA3Vo0.setAs342_nm(this.getShapeFromMain(baseLandBean.voMain.getShape()));
        String string = this.getStreetRelFromMain(baseLandBean.voMain.getStreet_rel());
        if (!StringProcess.isEmpty(string)) {
            baseLandBean.voAppRaA3Vo0.setAs343_nm(string);
        }
        baseLandBean.voAppRaA3Vo0.setAs345_nm(baseLandBean.voMain.getStreet());
        baseLandBean.voAppRaA3Vo0.setAs345_ds(StringProcess.parserDouble(baseLandBean.voMain.getRoadwidth()));
        baseLandBean.voRent.setBuilding("01".equals(baseLandBean.voMain.getLand_scene()));
    }

    public static double roundCd(double d) {
        double d2 = d;
        d2 = d2 > 0.0 && d2 <= 100.0 ? BigDecimalUtil.round(d2, 0) : (d2 > 100.0 && d2 <= 1000.0 ? BigDecimalUtil.round(BigDecimalUtil.div(d2, 10.0), 0) * 10.0 : (d2 > 1000.0 && d2 <= 100000.0 ? BigDecimalUtil.round(BigDecimalUtil.div(d2, 100.0), 0) * 100.0 : BigDecimalUtil.round(BigDecimalUtil.div(d2, 1000.0), 0) * 1000.0));
        return d2;
    }

    private String getStreetRelFromMain(String string) {
        String string2 = "";
        if (string.contains("\u55ae\u9762")) {
            string2 = "1";
        }
        if (string.contains("\u96d9\u9762")) {
            string2 = "2";
        }
        if (string.contains("\u4e09\u9762")) {
            string2 = "3";
        }
        if (string.contains("\u56db\u9762")) {
            string2 = "4";
        }
        return string2;
    }

    private String getShapeFromMain(String string) {
        String string2 = "0";
        if (!StringProcess.isEmpty(string)) {
            if ("\u65b9\u5f62".equals(string)) {
                string2 = "1";
            }
            if ("\u9577\u65b9\u5f62".equals(string)) {
                string2 = "2";
            }
            if ("\u68af\u5f62".equals(string)) {
                string2 = "3";
            }
        }
        return string2;
    }

    public void updateVo() {
        EstimateModel.BASELANDBEAN.voMain.setBeanByHashMap(this.voValue, false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ArrayList<NVO_SRKEYN_ALL> queryAa48List(String string, String string2) {
        Connection connection = null;
        ArrayList<NVO_SRKEYN_ALL> arrayList = new ArrayList<NVO_SRKEYN_ALL>();
        try {
            connection = this.getConnection();
            arrayList = new NDAO_SRKEYN_ALL().getSectsByOfficeOrTown(string, "", string2, connection);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
        return arrayList;
    }
}

