/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.util;

import java.sql.Connection;
import java.util.Map;
import java.util.TreeMap;
import moiland.baseland.em.EnumBaseLandUrban;
import moiland.baseland.factor.bean.FactorItemBean;
import moiland.baseland.factor.em.EnumFactorVersion;
import moiland.baseland.instru.bo.BaseLandInstruCodeBo;
import moiland.baseland.pricerate.em.EnumPriceRateMainItem;
import moiland.baseland.pricerate.em.EnumPriceRateSubItem;
import moiland.baseland.util.BaseLandFactorListHelper;
import moiland.baseland.util.CodeList;

public class BaseLandCode {
    private static Map<String, String> baseLandUrban = null;
    private static Map<String, String> priceRateMainItem = null;
    private static Map<String, String> priceRateAllItem = null;
    private static Map<String, String> instruCodeMap = null;
    private static Map<String, String> factorVersion = null;
    private static Map<String, Map<String, FactorItemBean>> regionalFactorMainCode = null;
    private static Map<String, Map<String, FactorItemBean>> regionalFactorAllItem = null;
    private static Map<String, Map<String, FactorItemBean>> individualFactorMainCode = null;
    private static Map<String, Map<String, FactorItemBean>> individualFactorAllItem = null;
    private static Map<String, String> compareAs302Map = null;
    private static Map<String, String> compareAs303Map = null;
    private static Map<String, String> compareAs342Map = null;
    private static Map<String, String> compareAs343Map = null;
    private static Map<String, String> compareAs347Map = null;
    private static Map<String, String> compareAs368Map = null;

    public static void refreshCode(Connection connection, Connection connection2, Connection connection3) {
        compareAs302Map = new TreeMap<String, String>();
        compareAs302Map.put("0", "\u5176\u4ed6");
        compareAs302Map.put("1", "\u7d20\u5730");
        compareAs302Map.put("2", "\u900f\u5929");
        compareAs302Map.put("3", "\u516c\u5bd3");
        compareAs302Map.put("4", "\u83ef\u5ec8");
        compareAs302Map.put("5", "\u5927\u6a13");
        compareAs302Map.put("6", "\u5957\u623f");
        compareAs303Map = new TreeMap<String, String>();
        compareAs303Map.put("0", "\u5176\u4ed6");
        compareAs303Map.put("1", "\u4f4f");
        compareAs303Map.put("2", "\u5546");
        compareAs303Map.put("3", "\u5de5");
        compareAs342Map = new TreeMap<String, String>();
        compareAs342Map.put("0", "\u4e0d\u898f\u5247\u5f62");
        compareAs342Map.put("1", "\u65b9\u5f62");
        compareAs342Map.put("2", "\u9577\u65b9\u5f62");
        compareAs342Map.put("3", "\u68af\u5f62");
        compareAs343Map = new TreeMap<String, String>();
        compareAs343Map.put("0", "\u7121\u81e8\u8def");
        compareAs343Map.put("1", "\u55ae\u9762");
        compareAs343Map.put("2", "\u96d9\u9762");
        compareAs343Map.put("3", "\u4e09\u9762");
        compareAs343Map.put("4", "\u56db\u9762(\u542b\u4ee5\u4e0a)");
        compareAs347Map = new TreeMap<String, String>();
        compareAs347Map.put("1", "\u5df7\u9053");
        compareAs347Map.put("2", "\u6b21\u8981\u9053\u8def");
        compareAs347Map.put("3", "\u4e3b\u8981\u9053\u8def");
        compareAs347Map.put("4", "\u8fb2\u8def");
        compareAs347Map.put("5", "\u79c1\u8a2d\u5df7\u9053");
        compareAs347Map.put("6", "\u7121\u81e8\u8def");
        compareAs368Map = new TreeMap<String, String>();
        compareAs368Map.put("0", "\u6709");
        compareAs368Map.put("1", "\u7121");
        baseLandUrban = EnumBaseLandUrban.toMap();
        factorVersion = EnumFactorVersion.toMap();
        priceRateMainItem = EnumPriceRateMainItem.toMap();
        priceRateAllItem = EnumPriceRateSubItem.toMap();
        CodeList.refresh(connection2, connection3);
        if (connection != null) {
            instruCodeMap = new BaseLandInstruCodeBo().getInstruCodeMap(connection);
            regionalFactorMainCode = BaseLandFactorListHelper.getRegionalMainList(connection);
            regionalFactorAllItem = BaseLandFactorListHelper.getAllRegionalItemList(connection);
            individualFactorMainCode = BaseLandFactorListHelper.getIndividualMainList(connection);
            individualFactorAllItem = BaseLandFactorListHelper.getAllIndividualItemList(connection);
        }
        System.out.println("BaseLandCode\uff0c\u66f4\u65b0\u4ee3\u78bc\u5b8c\u6210");
    }

    public static Map<String, String> getBaseLandUrban() {
        return baseLandUrban;
    }

    public static Map<String, String> getPriceRateMainItem() {
        return priceRateMainItem;
    }

    public static Map<String, String> getPriceRateSubItem() {
        return priceRateAllItem;
    }

    public static Map<String, String> getInstruCodeMap() {
        return instruCodeMap;
    }

    public static Map<String, String> getFactorVersion() {
        return factorVersion;
    }

    public static Map<String, String> getRegionalFactorMainCodeList(String string, String string2) {
        return BaseLandCode.getFactorCodeList(string, string2, regionalFactorMainCode);
    }

    public static Map<String, String> getIndividualFactorMainCodeList(String string, String string2) {
        return BaseLandCode.getFactorCodeList(string, string2, individualFactorMainCode);
    }

    public static Map<String, String> getRegionalFactorItemList(String string, String string2) {
        return BaseLandCode.getFactorCodeList(string, string2, regionalFactorAllItem);
    }

    public static Map<String, String> getIndividualFactorItemList(String string, String string2) {
        return BaseLandCode.getFactorCodeList(string, string2, individualFactorAllItem);
    }

    public static Map<String, FactorItemBean> getRegionalFactorMainCodeByVersion(String string) {
        return BaseLandCode.getFactorItemByVersion(string, regionalFactorMainCode);
    }

    public static Map<String, FactorItemBean> getIndividualFactorMainCodeByVersion(String string) {
        return BaseLandCode.getFactorItemByVersion(string, individualFactorMainCode);
    }

    public static Map<String, FactorItemBean> getRegionalFactorItemByVersion(String string) {
        return BaseLandCode.getFactorItemByVersion(string, regionalFactorAllItem);
    }

    public static FactorItemBean getRegionalFactorItemByItem(String string, String string2) {
        return BaseLandCode.getFactorItemByVersion(string, regionalFactorAllItem).get(string2);
    }

    public static Map<String, FactorItemBean> getIndividualFactorItemByVersion(String string) {
        return BaseLandCode.getFactorItemByVersion(string, individualFactorAllItem);
    }

    public static FactorItemBean getIndividualFactorItemByItem(String string, String string2) {
        return BaseLandCode.getFactorItemByVersion(string, individualFactorAllItem).get(string2);
    }

    public static String decodeFactorVersion(String string) {
        if (EnumFactorVersion.findSelfByString(string) != null) {
            return BaseLandCode.getValueByKey(string, factorVersion);
        }
        return string;
    }

    public static String decodeBaseLandUrban(String string) {
        if (EnumBaseLandUrban.findSelfByString(string) != null) {
            return BaseLandCode.getValueByKey(string, baseLandUrban);
        }
        return string;
    }

    public static String decodeRegionalMainCode(String string, String string2) {
        return BaseLandCode.decodeFactorCode(string, string2, regionalFactorMainCode);
    }

    public static String decodeIndividualMainCode(String string, String string2) {
        return BaseLandCode.decodeFactorCode(string, string2, individualFactorMainCode);
    }

    public static String decodeRegionalItemCode(String string, String string2) {
        return BaseLandCode.decodeFactorCode(string, string2, regionalFactorAllItem);
    }

    public static String decodeIndividualItem(String string, String string2) {
        return BaseLandCode.decodeFactorCode(string, string2, individualFactorAllItem);
    }

    public static String decodePriceRateMainItem(String string) {
        return BaseLandCode.getValueByKey(string, priceRateMainItem);
    }

    public static String decodePriceRateSubItem(String string) {
        return BaseLandCode.getValueByKey(string, priceRateAllItem);
    }

    public static String decodeInstruCode(String string) {
        return BaseLandCode.getValueByKey(string, instruCodeMap);
    }

    public static String decodeAs302(String string) {
        return BaseLandCode.getValueByKey(string, compareAs302Map);
    }

    public static String decodeAs303(String string) {
        return BaseLandCode.getValueByKey(string, compareAs303Map);
    }

    public static String decodeAs342(String string) {
        return BaseLandCode.getValueByKey(string, compareAs342Map);
    }

    public static Map<String, String> getCompareAs342Map() {
        return compareAs342Map;
    }

    public static String decodeAs343(String string) {
        return BaseLandCode.getValueByKey(string, compareAs343Map);
    }

    public static Map<String, String> getCompareAs343Map() {
        return compareAs343Map;
    }

    public static String decodeAs347(String string) {
        return BaseLandCode.getValueByKey(string, compareAs347Map);
    }

    public static Map<String, String> getCompareAs347Map() {
        return compareAs347Map;
    }

    public static String decodeAs368(String string) {
        return BaseLandCode.getValueByKey(string, compareAs368Map);
    }

    public static Map<String, String> getCompareAs368Map() {
        return compareAs368Map;
    }

    private static Map<String, FactorItemBean> getFactorItemByVersion(String string, Map<String, Map<String, FactorItemBean>> map) {
        EnumFactorVersion enumFactorVersion = EnumFactorVersion.findSelfByString(string);
        if (enumFactorVersion != null && map != null && map.containsKey(enumFactorVersion.toString())) {
            return map.get(enumFactorVersion.toString());
        }
        return new TreeMap<String, FactorItemBean>();
    }

    private static Map<String, String> getFactorCodeList(String string, String string2, Map<String, Map<String, FactorItemBean>> map) {
        EnumFactorVersion enumFactorVersion;
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        if (string2 != null && map != null && (enumFactorVersion = EnumFactorVersion.findSelfByString(string)) != null && map.containsKey(enumFactorVersion.toString())) {
            Map<String, FactorItemBean> map2 = map.get(enumFactorVersion.toString());
            for (String string3 : map2.keySet()) {
                if (string2.length() != 0 && !string3.startsWith(string2)) continue;
                treeMap.put(string3, map2.get(string3).getItemText());
            }
        }
        return treeMap;
    }

    private static String decodeFactorCode(String string, String string2, Map<String, Map<String, FactorItemBean>> map) {
        Map<String, FactorItemBean> map2;
        EnumFactorVersion enumFactorVersion;
        String string3 = string2;
        if (map != null && (enumFactorVersion = EnumFactorVersion.findSelfByString(string)) != null && map.containsKey(string) && (map2 = map.get(string)).containsKey(string2)) {
            string3 = map2.get(string2).getItemText();
        }
        return string3;
    }

    private static String getValueByKey(String string, Map<String, String> map) {
        String string2 = "";
        if (string != null) {
            string2 = string;
            if (map != null && map.containsKey(string)) {
                string2 = map.get(string);
            }
        }
        return string2;
    }

    static {
        BaseLandCode.refreshCode(null, null, null);
    }
}

