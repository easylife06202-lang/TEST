/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.baseland;

import com.wfusion.baseland.basic.Model;
import com.wfusion.util.ConnectionFactory;
import com.wfusion.util.DateTime;
import com.wfusion.util.OptionPair;
import com.wfusion.util.SqlUtil;
import com.wfusion.util.StringProcess;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_INSTRU;
import moiland.baseland.dataaccess.ndao.NDAO_SRKEYN_ALL;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_INSTRU;
import moiland.baseland.dataaccess.nvo.NVO_SRKEYN_ALL;
import moiland.baseland.em.EnumBaseLandUrban;
import moiland.baseland.util.BaseLandCode;
import moiland.baseland.util.CodeList;
import moiland.baseland.util.bo.BaseLandOptionDataBo;

public class SQLiteDataProviderModel
extends Model {
    private static TreeMap<String, NVO_BASELAND_INSTRU> MAPINSTRU = new TreeMap();
    private static Map<String, String> MAPAA45 = new TreeMap<String, String>();
    private static Map<String, Map<String, String>> MAPAA46 = new TreeMap<String, Map<String, String>>();

    public static void refreshCode() {
        Connection connection = null;
        try {
            connection = ConnectionFactory.createConnection("sqlite", SQLITE_PATH + "BaseLand.db", "", "", "", "");
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

    public static ArrayList<OptionPair> getDefaultList() {
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        arrayList.add(new OptionPair("", "\u8acb\u9078\u64c7..."));
        return arrayList;
    }

    public static ArrayList<OptionPair> getAA45List() {
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        arrayList.add(new OptionPair("", "\u8acb\u9078\u64c7..."));
        for (String string : MAPAA45.keySet()) {
            arrayList.add(new OptionPair(string, MAPAA45.get(string) + "(" + string + ")"));
        }
        return arrayList;
    }

    public static ArrayList<OptionPair> getAA46List(String string) {
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        arrayList.add(new OptionPair("", "\u8acb\u9078\u64c7..."));
        if (!MAPAA46.containsKey(string)) {
            return arrayList;
        }
        for (String string2 : MAPAA46.get(string).keySet()) {
            arrayList.add(new OptionPair(string2, MAPAA46.get(string).get(string2) + "(" + string2 + ")"));
        }
        return arrayList;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static HashMap<String, String> getAA48Map(String string, String string2, String string3) {
        Connection connection = null;
        NDAO_SRKEYN_ALL nDAO_SRKEYN_ALL = new NDAO_SRKEYN_ALL();
        HashMap<String, String> hashMap = new HashMap<String, String>();
        try {
            if (StringProcess.isEmpty(string)) {
                HashMap<String, String> hashMap2 = hashMap;
                return hashMap2;
            }
            connection = ConnectionFactory.createConnection("sqlite", SQLITE_PATH + "BaseLand.db", "", "", "", "");
            ArrayList<NVO_SRKEYN_ALL> arrayList = nDAO_SRKEYN_ALL.getSectsByOfficeOrTown(string, string2, string3, connection);
            for (NVO_SRKEYN_ALL nVO_SRKEYN_ALL : arrayList) {
                hashMap.put(nVO_SRKEYN_ALL.getKcde_4(), nVO_SRKEYN_ALL.getKname());
            }
            SqlUtil.close(connection);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
        return hashMap;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static ArrayList<OptionPair> getAA48List(String string, String string2, String string3) {
        Connection connection = null;
        NDAO_SRKEYN_ALL nDAO_SRKEYN_ALL = new NDAO_SRKEYN_ALL();
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        arrayList.add(new OptionPair("", "\u8acb\u9078\u64c7..."));
        try {
            if (StringProcess.isEmpty(string)) {
                ArrayList<OptionPair> arrayList2 = arrayList;
                return arrayList2;
            }
            connection = ConnectionFactory.createConnection("sqlite", SQLITE_PATH + "BaseLand.db", "", "", "", "");
            ArrayList<NVO_SRKEYN_ALL> arrayList3 = nDAO_SRKEYN_ALL.getSectsByOfficeOrTown(string, string2, string3, connection);
            for (NVO_SRKEYN_ALL nVO_SRKEYN_ALL : arrayList3) {
                arrayList.add(new OptionPair(nVO_SRKEYN_ALL.getKcde_4(), "(" + nVO_SRKEYN_ALL.getKcde_4() + ")" + nVO_SRKEYN_ALL.getKname()));
            }
            Collections.sort(arrayList);
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

    public static ArrayList<OptionPair> getAreaList() {
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        arrayList.add(new OptionPair("", "\u4e0d\u5206\u5340"));
        LinkedHashMap<String, String> linkedHashMap = EnumBaseLandUrban.toMap();
        if (linkedHashMap != null && linkedHashMap.size() > 0) {
            for (Map.Entry<String, String> entry : linkedHashMap.entrySet()) {
                arrayList.add(new OptionPair(entry.getKey(), "(" + entry.getKey() + ")" + entry.getValue()));
            }
        }
        return arrayList;
    }

    public static ArrayList<String> getMonthList(int n) {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = n; i < n + 12; ++i) {
            if (i < 10) {
                arrayList.add("0" + Integer.toString(i));
                continue;
            }
            arrayList.add(Integer.toString(i));
        }
        return arrayList;
    }

    public static ArrayList<OptionPair> getRegionalFactorMainCodeList(String string, String string2) {
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        arrayList.add(new OptionPair("", "\u8acb\u9078\u64c7..."));
        Map<String, String> map = BaseLandCode.getRegionalFactorMainCodeList(string, string2);
        for (String string3 : map.keySet()) {
            arrayList.add(new OptionPair(string3, map.get(string3)));
        }
        return arrayList;
    }

    public static ArrayList<OptionPair> getIndividualFactorMainCodeList(String string, String string2) {
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        arrayList.add(new OptionPair("", "\u8acb\u9078\u64c7..."));
        Map<String, String> map = BaseLandCode.getIndividualFactorMainCodeList(string, string2);
        for (String string3 : map.keySet()) {
            arrayList.add(new OptionPair(string3, map.get(string3)));
        }
        return arrayList;
    }

    public static ArrayList<OptionPair> getRegionalFactorItemList(String string, String string2) {
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        arrayList.add(new OptionPair("", "\u8acb\u9078\u64c7..."));
        Map<String, String> map = BaseLandCode.getRegionalFactorItemList(string, string2);
        for (String string3 : map.keySet()) {
            arrayList.add(new OptionPair(string3, map.get(string3)));
        }
        return arrayList;
    }

    public static ArrayList<OptionPair> getIndividualFactorItemList(String string, String string2) {
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        arrayList.add(new OptionPair("", "\u8acb\u9078\u64c7..."));
        Map<String, String> map = BaseLandCode.getIndividualFactorItemList(string, string2);
        for (String string3 : map.keySet()) {
            arrayList.add(new OptionPair(string3, map.get(string3)));
        }
        return arrayList;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static ArrayList<OptionPair> getYearsListFromRegionalFactor(String string, String string2) {
        Connection connection = null;
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        arrayList.add(new OptionPair("", "\u8acb\u9078\u64c7..."));
        try {
            connection = ConnectionFactory.createConnection("sqlite", SQLITE_PATH + "BaseLand.db", "", "", "", "");
            ArrayList<String> arrayList2 = BaseLandOptionDataBo.getYearsFromRegionalFactor(string, string2, connection);
            ArrayList<String> arrayList3 = new DateTime().getYearList(arrayList2, 5, 2, false, false);
            for (String string3 : arrayList3) {
                arrayList.add(new OptionPair(string3, string3));
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

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static ArrayList<OptionPair> getYearsListFromIndividualFactor(String string, String string2) {
        Connection connection = null;
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        arrayList.add(new OptionPair("", "\u8acb\u9078\u64c7..."));
        try {
            connection = ConnectionFactory.createConnection("sqlite", SQLITE_PATH + "BaseLand.db", "", "", "", "");
            ArrayList<String> arrayList2 = BaseLandOptionDataBo.getYearsFromIndividualFactor(string, string2, connection);
            ArrayList<String> arrayList3 = new DateTime().getYearList(arrayList2, 5, 2, false, false);
            for (String string3 : arrayList3) {
                arrayList.add(new OptionPair(string3, string3));
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

    public static ArrayList<OptionPair> getFactorVersion() {
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        arrayList.add(new OptionPair("", "\u8acb\u9078\u64c7..."));
        for (String string : BaseLandCode.getFactorVersion().keySet()) {
            arrayList.add(new OptionPair(string, BaseLandCode.getFactorVersion().get(string)));
        }
        return arrayList;
    }

    public static ArrayList<OptionPair> getFactorVersion(String string, String string2) {
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        arrayList.add(new OptionPair("", "\u8acb\u9078\u64c7..."));
        arrayList.add(new OptionPair(string, string2));
        return arrayList;
    }

    public static ArrayList<OptionPair> getBaselandNoList(String string, String string2, String string3) {
        return SQLiteDataProviderModel.getBaselandNoList(string, string2, string3, "");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static ArrayList<OptionPair> getBaselandNoList(String string, String string2, String string3, String string4) {
        Connection connection = null;
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        arrayList.add(new OptionPair("", "\u8acb\u9078\u64c7..."));
        try {
            connection = ConnectionFactory.createConnection("sqlite", SQLITE_PATH + "BaseLand.db", "", "", "", "");
            ArrayList<String> arrayList2 = BaseLandOptionDataBo.getBaselandList(string, string2, string3, string4, connection);
            for (String string5 : arrayList2) {
                arrayList.add(new OptionPair(string5, string5));
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

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static ArrayList<OptionPair> getBaselandNoList_withVersion(String string, String string2, String string3, String string4) {
        Connection connection = null;
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        arrayList.add(new OptionPair("", "\u8acb\u9078\u64c7..."));
        try {
            connection = ConnectionFactory.createConnection("sqlite", SQLITE_PATH + "BaseLand.db", "", "", "", "");
            ArrayList<String> arrayList2 = BaseLandOptionDataBo.getBaselandList_withVersion(string, string2, string3, "", string4, connection);
            for (String string5 : arrayList2) {
                arrayList.add(new OptionPair(string5, string5));
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

    public static ArrayList<OptionPair> getPriceRateTypeCode() {
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        arrayList.add(new OptionPair("", "\u8acb\u9078\u64c7..."));
        try {
            for (String string : BaseLandCode.getPriceRateSubItem().keySet()) {
                arrayList.add(new OptionPair(string, BaseLandCode.getPriceRateSubItem().get(string)));
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return arrayList;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static ArrayList<OptionPair> getPriceRateDistCode(String string, String string2) {
        Connection connection = null;
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        arrayList.add(new OptionPair("", "\u8acb\u9078\u64c7..."));
        try {
            connection = ConnectionFactory.createConnection("sqlite", SQLITE_PATH + "BaseLand.db", "", "", "", "");
            Map<String, String> map = BaseLandOptionDataBo.getPriceRateDistCode(string, string2);
            if (map == null || map.size() == 0) {
                ArrayList<OptionPair> arrayList2 = SQLiteDataProviderModel.getAA46List(string);
                SqlUtil.close(connection);
                return arrayList2;
            }
            for (String string3 : map.keySet()) {
                arrayList.add(new OptionPair(string3, map.get(string3) + "(" + string3 + ")"));
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

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static ArrayList<OptionPair> getYearsFromPriceRateOption(String string, String string2, String string3) {
        Connection connection = null;
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        arrayList.add(new OptionPair("", "\u8acb\u9078\u64c7..."));
        try {
            connection = ConnectionFactory.createConnection("sqlite", SQLITE_PATH + "BaseLand.db", "", "", "", "");
            ArrayList<String> arrayList2 = BaseLandOptionDataBo.getPriceRateYearList(string, string3, string2, connection);
            arrayList2 = new DateTime().getYearList(arrayList2, 5, 2, false, false);
            for (String string4 : arrayList2) {
                arrayList.add(new OptionPair(string4, string4));
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

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static ArrayList<OptionPair> getReportParamYearList(String string) {
        Connection connection = null;
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        arrayList.add(new OptionPair("", "\u8acb\u9078\u64c7..."));
        try {
            connection = ConnectionFactory.createConnection("sqlite", SQLITE_PATH + "BaseLand.db", "", "", "", "");
            ArrayList<String> arrayList2 = BaseLandOptionDataBo.getReportParamYearList(string, connection);
            arrayList2 = new DateTime().getYearList(arrayList2, 5, 2, false, false);
            for (String string2 : arrayList2) {
                arrayList.add(new OptionPair(string2, string2));
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

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static ArrayList<OptionPair> getAhpParamYearList(String string) {
        Connection connection = null;
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        arrayList.add(new OptionPair("", "\u8acb\u9078\u64c7..."));
        try {
            connection = ConnectionFactory.createConnection("sqlite", SQLITE_PATH + "BaseLand.db", "", "", "", "");
            ArrayList<String> arrayList2 = BaseLandOptionDataBo.getAhpParamYearList(string, connection);
            arrayList2 = new DateTime().getYearList(arrayList2, 5, 2, false, false);
            for (String string2 : arrayList2) {
                arrayList.add(new OptionPair(string2, string2));
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

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static ArrayList<OptionPair> getInstruCodeList() {
        Connection connection = null;
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        try {
            connection = ConnectionFactory.createConnection("sqlite", SQLITE_PATH + "BaseLand.db", "", "", "", "");
            Map<String, String> map = BaseLandCode.getInstruCodeMap();
            for (String string : map.keySet()) {
                arrayList.add(new OptionPair(string, map.get(string)));
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

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static TreeMap<String, NVO_BASELAND_INSTRU> getMAPINSTRU() {
        Connection connection = null;
        TreeMap<String, NVO_BASELAND_INSTRU> treeMap = new TreeMap<String, NVO_BASELAND_INSTRU>();
        try {
            connection = ConnectionFactory.createConnection("sqlite", SQLITE_PATH + "BaseLand.db", "", "", "", "");
            ArrayList<NVO_BASELAND_INSTRU> arrayList = new NDAO_BASELAND_INSTRU().queryAllData(connection);
            for (NVO_BASELAND_INSTRU nVO_BASELAND_INSTRU : arrayList) {
                treeMap.put(nVO_BASELAND_INSTRU.getInstru_code(), nVO_BASELAND_INSTRU);
            }
            SqlUtil.close(connection);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
        return treeMap;
    }

    public static Map<String, String> getMapAA45() {
        return MAPAA45;
    }

    public static Map<String, Map<String, String>> getMapAA46() {
        return MAPAA46;
    }

    public static String getOfficeFromSect(String string, String string2) {
        return CodeList.getOfficeFromSect(string, string2);
    }

    static {
        Connection connection = null;
        try {
            connection = ConnectionFactory.createConnection("sqlite", SQLITE_PATH + "BaseLand.db", "", "", "", "");
            CodeList.refresh(connection, connection);
            MAPAA45 = CodeList.getCountyMap();
            for (String string : MAPAA45.keySet()) {
                MAPAA46.put(string, CodeList.getCityTownMap(string));
            }
            SqlUtil.close(connection);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
    }
}

