/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.baseland.estimate;

import com.wfusion.baseland.basic.Model;
import com.wfusion.baseland.estimate.EstimateModel;
import com.wfusion.util.OptionPair;
import com.wfusion.util.SUtility;
import com.wfusion.util.SqlUtil;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import moiland.baseland.bo.AutoCalBaseLandRent;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT_MONTH;
import moiland.baseland.util.BaseLandParamFillHelper;

public class EstimateReport7Model
extends Model {
    HashMap<String, Object> voValue = new HashMap();
    HashMap<String, Object> monthVoValue = new HashMap();

    public ArrayList<OptionPair> getRental_typeList() {
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        arrayList.add(new OptionPair("0", "\u8acb\u9078\u64c7"));
        arrayList.add(new OptionPair("1", "\u767b\u9304\u79df\u91d1"));
        arrayList.add(new OptionPair("2", "\u8a62\u554f\u79df\u91d1"));
        arrayList.add(new OptionPair("3", "\u5f85\u79df\u50f9"));
        return arrayList;
    }

    public ArrayList<OptionPair> getRent_dateList() {
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        arrayList.add(new OptionPair("0", "\u8acb\u9078\u64c7"));
        arrayList.add(new OptionPair("1", "\u6700\u8fd13\u500b\u6708"));
        arrayList.add(new OptionPair("2", "4-6\u6708"));
        arrayList.add(new OptionPair("3", "6\u500b\u6708\u4ee5\u524d"));
        return arrayList;
    }

    public ArrayList<OptionPair> getNear_adjList() {
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        arrayList.add(new OptionPair("0", "\u8acb\u9078\u64c7"));
        arrayList.add(new OptionPair("1", "1:\u8fd1\u50f9\u5340\u6bb5\u5167"));
        arrayList.add(new OptionPair("2", "2:\u8fd1\u50f9\u5340\u6bb5\u5916"));
        return arrayList;
    }

    public ArrayList<OptionPair> getCR48List() {
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        arrayList.add(new OptionPair("0", "\u7b2c43\u689d\u7b2c1\u6b3e(\u5ee2\u68c4)"));
        arrayList.add(new OptionPair("1", "\u7b2c43\u689d\u7b2c2\u6b3e(\u5ee2\u68c4)"));
        arrayList.add(new OptionPair("2", "\u4ee5\u6bd4\u8f03\u6cd5\u6848\u4f8b\u8a66\u7b97\u65b9\u5f0f(\u5ee2\u68c4)"));
        arrayList.add(new OptionPair("3", "\u98a8\u96aa\u6ea2\u916c\u6cd5"));
        arrayList.add(new OptionPair("4", "\u5e02\u5834\u8403\u53d6\u6cd5"));
        arrayList.add(new OptionPair("5", "\u52a0\u6b0a\u5e73\u5747\u8cc7\u91d1\u6210\u672c\u6cd5"));
        arrayList.add(new OptionPair("6", "\u50b5\u52d9\u4fdd\u969c\u6bd4\u7387\u6cd5"));
        arrayList.add(new OptionPair("7", "\u6709\u6548\u7e3d\u6536\u5165\u4e58\u6578\u6cd5"));
        return arrayList;
    }

    public String getCR48NameMap(String string) {
        String string2 = "";
        ArrayList<OptionPair> arrayList = this.getCR48List();
        for (OptionPair optionPair : arrayList) {
            if (!optionPair.getValue().equals(string)) continue;
            string2 = optionPair.getAlias();
        }
        return string2;
    }

    public void updateMonthHashMapValues() {
        this.monthVoValue = new HashMap();
        HashMap<String, Object> hashMap2 = EstimateModel.BASELANDBEAN.voRentMonth1.getFieldToHashMapExport();
        for (String hashMap3 : hashMap2.keySet()) {
            this.monthVoValue.put(hashMap3 + "0", hashMap2.get(hashMap3));
        }
        HashMap<String, Object> hashMap4 = EstimateModel.BASELANDBEAN.voRentMonth2.getFieldToHashMapExport();
        for (Object object : hashMap4.keySet()) {
            this.monthVoValue.put((String)object + "1", hashMap4.get(object));
        }
        HashMap<String, Object> hashMap = EstimateModel.BASELANDBEAN.voRentMonth3.getFieldToHashMapExport();
        for (String string : hashMap.keySet()) {
            this.monthVoValue.put(string + "2", hashMap.get(string));
        }
    }

    public void updateHashMapValues() {
        this.voValue = EstimateModel.BASELANDBEAN.voRent.getFieldToHashMapExport();
        this.voValue.putAll(EstimateModel.BASELANDBEAN.voRentExt.getFieldToHashMapExport());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void reCal() {
        Connection connection = null;
        try {
            connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
            BaseLandParamFillHelper baseLandParamFillHelper = new BaseLandParamFillHelper(EstimateModel.BASELANDBEAN.queryBean.AA45, EstimateModel.BASELANDBEAN.queryBean.year, connection);
            double d = baseLandParamFillHelper.getParam().getMc();
            double d2 = baseLandParamFillHelper.getParam().getMaintian_rate();
            new AutoCalBaseLandRent().calRentMaster(EstimateModel.BASELANDBEAN.voRent, baseLandParamFillHelper.getParam());
            EstimateModel.BASELANDBEAN.voMain.setRprice((int)EstimateModel.BASELANDBEAN.voRent.getCr41());
            SqlUtil.close(connection);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
    }

    public int reCalRentMonth() {
        TreeMap<String, NVO_BASELAND_RENT_MONTH> treeMap = new TreeMap<String, NVO_BASELAND_RENT_MONTH>();
        treeMap.put("1", EstimateModel.BASELANDBEAN.voRentMonth1);
        treeMap.put("2", EstimateModel.BASELANDBEAN.voRentMonth2);
        treeMap.put("3", EstimateModel.BASELANDBEAN.voRentMonth3);
        return new AutoCalBaseLandRent().calRentMonth(treeMap);
    }

    public void updateMonthVo() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        HashMap<String, Object> hashMap2 = new HashMap<String, Object>();
        HashMap<String, Object> hashMap3 = new HashMap<String, Object>();
        for (String string : this.monthVoValue.keySet()) {
            if (string.endsWith("0")) {
                hashMap.put(string.substring(0, string.length() - 1), this.monthVoValue.get(string));
            }
            if (string.endsWith("1")) {
                hashMap2.put(string.substring(0, string.length() - 1), this.monthVoValue.get(string));
            }
            if (!string.endsWith("2")) continue;
            hashMap3.put(string.substring(0, string.length() - 1), this.monthVoValue.get(string));
        }
        EstimateModel.BASELANDBEAN.voRentMonth1.setBeanByHashMap(hashMap, false);
        EstimateModel.BASELANDBEAN.voRentMonth2.setBeanByHashMap(hashMap2, false);
        EstimateModel.BASELANDBEAN.voRentMonth3.setBeanByHashMap(hashMap3, false);
        EstimateModel.BASELANDBEAN.voRentMonth1.setRent_caseno("1");
        EstimateModel.BASELANDBEAN.voRentMonth2.setRent_caseno("2");
        EstimateModel.BASELANDBEAN.voRentMonth3.setRent_caseno("3");
    }

    public void updateVo() {
        EstimateModel.BASELANDBEAN.voRent.setBeanByHashMap(this.voValue, false);
    }
}

