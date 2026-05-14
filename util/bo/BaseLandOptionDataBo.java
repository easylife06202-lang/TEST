/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.util.bo;

import com.wfusion.util.YearUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_AHP;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_BUILDPRICE_RATIO;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_INDIVIDUAL_FACTOR;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_MAIN;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_PRICERATE;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_REGIONAL_FACTOR;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_REPORT_PARAM;
import moiland.baseland.pricerate.em.EnumPriceRateSubItem;
import moiland.baseland.util.BaseLandCode;
import moiland.baseland.util.CodeList;

public class BaseLandOptionDataBo {
    public static Map<String, String> getCity(String string) {
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        if ("".equals(string)) {
            treeMap.putAll(CodeList.getCountyMap());
        } else {
            treeMap.putAll(CodeList.getCountyMap(string));
        }
        return treeMap;
    }

    public static Map<String, String> getOffice(String string) {
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        Map<String, String> map = CodeList.getCityOffice(string);
        if ("0".equals(string.substring(1))) {
            treeMap.putAll(map);
        } else if (map.containsKey(string)) {
            treeMap.put(string, map.get(string));
        }
        return treeMap;
    }

    public static Map<String, String> getDist(String string, String string2) {
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        if ("".equals(string2)) {
            treeMap.putAll(CodeList.getCityTownMap(string));
        } else {
            treeMap.putAll(CodeList.getCityTownMap(string, string2));
        }
        return treeMap;
    }

    public static Map<String, String> getSectMap(String string, String string2, String string3, Connection connection) {
        Map<String, String> map = new TreeMap<String, String>();
        try {
            CodeList.getSectAllMap(connection);
            if (string3.matches("[0123][0-9]") && string.matches("[A-Z]")) {
                map = BaseLandOptionDataBo.getDistSectMap(string, string3);
            } else if (string2.matches("[A-Z][A-X]")) {
                map = BaseLandOptionDataBo.getOfficeSectMap(string2);
            } else if (string.matches("[A-Z]")) {
                map = BaseLandOptionDataBo.getCitySectMap(string);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return map;
    }

    private static Map<String, String> getCitySectMap(String string) {
        Map<String, String> map = CodeList.getSectAllMap();
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        for (String string2 : map.keySet()) {
            if (!string2.startsWith(string)) continue;
            treeMap.put(string2.substring(3, 7), map.get(string2));
        }
        return treeMap;
    }

    private static Map<String, String> getOfficeSectMap(String string) {
        Map<String, String> map = CodeList.getSectAllMap();
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        for (String string2 : map.keySet()) {
            if (!string2.endsWith(string)) continue;
            treeMap.put(string2.substring(3, 7), map.get(string2));
        }
        return treeMap;
    }

    private static Map<String, String> getDistSectMap(String string, String string2) {
        Map<String, String> map = CodeList.getSectAllMap();
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        String string3 = string + string2;
        for (String string4 : map.keySet()) {
            if (!string4.startsWith(string3)) continue;
            treeMap.put(string4.substring(3, 7), map.get(string4));
        }
        return treeMap;
    }

    public static Map<String, String> getFactorVersion() {
        return BaseLandCode.getFactorVersion();
    }

    public static ArrayList<String> getYearsFromRegionalFactor(String string, String string2, Connection connection) {
        ArrayList<String> arrayList = new ArrayList<String>();
        try {
            arrayList = new NDAO_BASELAND_REGIONAL_FACTOR().getExistYearList(string, string2, connection);
            arrayList = YearUtil.checkLastYear(arrayList);
        }
        catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }
        return arrayList;
    }

    public static Map<String, String> getRegionalFactorMainCode(String string, String string2) {
        return BaseLandCode.getRegionalFactorMainCodeList(string, string2);
    }

    public static Map<String, String> getRegionalFactorItemCode(String string, String string2) {
        Map<String, String> map = BaseLandCode.getRegionalFactorItemList(string, string2);
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        for (String string3 : map.keySet()) {
            if (string3.length() != 2) continue;
            treeMap.put(string3, map.get(string3));
        }
        return treeMap;
    }

    public static ArrayList<String> getYearsFromIndividualFactor(String string, String string2, Connection connection) {
        ArrayList<String> arrayList = new ArrayList<String>();
        try {
            arrayList = new NDAO_BASELAND_INDIVIDUAL_FACTOR().getExistYearList(string, string2, connection);
            arrayList = YearUtil.checkLastYear(arrayList);
        }
        catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }
        return arrayList;
    }

    public static Map<String, String> getIndividualFactorMainCode(String string, String string2) {
        return BaseLandCode.getIndividualFactorMainCodeList(string, string2);
    }

    public static Map<String, String> getIndividualFactorItemCode(String string, String string2) {
        Map<String, String> map = BaseLandCode.getIndividualFactorItemList(string, string2);
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        for (String string3 : map.keySet()) {
            if (string3.length() != 2) continue;
            treeMap.put(string3, map.get(string3));
        }
        return treeMap;
    }

    public static ArrayList<String> getBaseMainYearList(String string, String string2, Connection connection) {
        ArrayList<String> arrayList = new ArrayList();
        arrayList = new NDAO_BASELAND_MAIN().getExistYearList(string, string2, connection);
        arrayList = YearUtil.checkLastYear(arrayList);
        return arrayList;
    }

    public static ArrayList<String> getBaselandList(String string, String string2, String string3, String string4, Connection connection) {
        ArrayList<String> arrayList = new ArrayList();
        arrayList = new NDAO_BASELAND_MAIN().getBandMainNum(string, string2, string3, string4, connection);
        return arrayList;
    }

    public static ArrayList<String> getBaselandList_withVersion(String string, String string2, String string3, String string4, String string5, Connection connection) {
        ArrayList<String> arrayList = new ArrayList();
        arrayList = new NDAO_BASELAND_MAIN().getBandMainNum_withVersion(string, string2, string3, string4, string5, connection);
        return arrayList;
    }

    public static ArrayList<String> getBaselandListWithoutAppraiserCase(String string, String string2, String string3, String string4, Connection connection) {
        ArrayList<String> arrayList = new ArrayList();
        arrayList = new NDAO_BASELAND_MAIN().getBandMainNumWithoutAppraiserCase(string, string2, string3, string4, connection);
        return arrayList;
    }

    public static Map<String, String> getPriceRateMainItemCode() {
        return BaseLandCode.getPriceRateMainItem();
    }

    public static Map<String, String> getPriceRateSubItemCode() {
        return BaseLandCode.getPriceRateSubItem();
    }

    public static Map<String, String> getPriceRateDistCode(String string, String string2) {
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        EnumPriceRateSubItem enumPriceRateSubItem = EnumPriceRateSubItem.findSelfByString(string2);
        if (enumPriceRateSubItem != null) {
            treeMap.put("00", "\u4e0d\u5340\u5206");
            if (!enumPriceRateSubItem.isSingleDistrict()) {
                treeMap.putAll(BaseLandOptionDataBo.getDist(string, ""));
            }
        }
        return treeMap;
    }

    public static ArrayList<String> getPriceRateYearList(String string, String string2, String string3, Connection connection) {
        ArrayList<String> arrayList = new ArrayList<String>();
        try {
            arrayList = new NDAO_BASELAND_PRICERATE().getExistYearList(string, string2, string3, connection);
            arrayList = YearUtil.checkLastYear(arrayList);
        }
        catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }
        return arrayList;
    }

    public static ArrayList<String> getReportParamYearList(String string, Connection connection) {
        ArrayList<String> arrayList = new ArrayList<String>();
        try {
            arrayList = new NDAO_BASELAND_REPORT_PARAM().getExistYearList(string, connection);
            arrayList = YearUtil.checkLastYear(arrayList);
        }
        catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }
        return arrayList;
    }

    public static ArrayList<String> getAhpParamYearList(String string, Connection connection) {
        ArrayList<String> arrayList = new ArrayList<String>();
        try {
            arrayList = new NDAO_BASELAND_AHP().getExistYearList(string, connection);
            arrayList = YearUtil.checkLastYear(arrayList);
        }
        catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }
        return arrayList;
    }

    public static ArrayList<String> getBuildPriceAdjustYearList(Connection connection) {
        ArrayList<String> arrayList = new ArrayList<String>();
        try {
            arrayList = new NDAO_BASELAND_BUILDPRICE_RATIO().getExistYearList(connection);
            arrayList = YearUtil.checkLastYear(arrayList);
        }
        catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }
        return arrayList;
    }
}

