/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.util;

import java.sql.Connection;
import java.util.Map;
import java.util.TreeMap;
import moiland.baseland.dataaccess.ndao.NDAO_SRKEYN;
import moiland.baseland.dataaccess.ndao.NDAO_SRKEYN_ALL;

public class CodeList {
    private static Map<String, String> countyMap = new TreeMap<String, String>();
    private static Map<String, String> officeAllMap = new TreeMap<String, String>();
    private static Map<String, String> townAllMap = new TreeMap<String, String>();
    private static Map<String, String> urbanMap = new TreeMap<String, String>();
    private static Map<String, String> sectAllMap = new TreeMap<String, String>();

    public static void refresh(Connection connection, Connection connection2) {
        NDAO_SRKEYN_ALL nDAO_SRKEYN_ALL = new NDAO_SRKEYN_ALL();
        NDAO_SRKEYN nDAO_SRKEYN = new NDAO_SRKEYN();
        try {
            if (connection != null) {
                countyMap = nDAO_SRKEYN_ALL.getCounty(connection);
                officeAllMap = nDAO_SRKEYN_ALL.getOfficeAll(connection);
                townAllMap = nDAO_SRKEYN_ALL.getTownAll(connection);
                sectAllMap = nDAO_SRKEYN_ALL.getSectAll(connection);
            }
            if (connection2 != null) {
                urbanMap = nDAO_SRKEYN.getRegdUrbanMap(connection2);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static Map<String, String> getCountyMap() {
        return countyMap;
    }

    public static Map<String, String> getCountyMap(String string) {
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        if (countyMap.containsKey(string)) {
            treeMap.put(string, countyMap.get(string));
        }
        return treeMap;
    }

    public static String decodeCity(String string) {
        String string2 = string;
        if (countyMap.containsKey(string)) {
            string2 = countyMap.get(string);
        }
        return string2;
    }

    public static Map<String, String> getCounty(Connection connection) throws Exception {
        countyMap = new NDAO_SRKEYN_ALL().getCounty(connection);
        return countyMap;
    }

    public static Map<String, String> getOfficeAllMap() {
        return officeAllMap;
    }

    public static Map<String, String> getCityOffice(String string) {
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        if (string.length() > 1) {
            String string2 = string.substring(0, 1);
            for (String string3 : officeAllMap.keySet()) {
                if (!string3.startsWith(string2)) continue;
                treeMap.put(string3, officeAllMap.get(string3));
            }
        }
        return treeMap;
    }

    public static Map<String, String> getCityOfficeByCity(String string) {
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        for (String string2 : officeAllMap.keySet()) {
            if (!string2.startsWith(string)) continue;
            treeMap.put(string2, officeAllMap.get(string2));
        }
        return treeMap;
    }

    public static String decodeUnit(String string) {
        String string2 = string;
        if (officeAllMap.containsKey(string)) {
            string2 = officeAllMap.get(string);
        }
        return string2;
    }

    public static Map<String, String> getOfficeAllMap(Connection connection) throws Exception {
        officeAllMap = new NDAO_SRKEYN_ALL().getOfficeAll(connection);
        return officeAllMap;
    }

    public static Map<String, String> getTownAllMap() {
        return townAllMap;
    }

    public static String decodeDist(String string, String string2) {
        String string3 = string2;
        String string4 = string + string2;
        if (string4.length() == 3) {
            for (String string5 : townAllMap.keySet()) {
                if (!string5.startsWith(string4)) continue;
                return townAllMap.get(string5);
            }
        }
        return string3;
    }

    public static Map<String, String> getTownAllMap(Connection connection) throws Exception {
        townAllMap = new NDAO_SRKEYN_ALL().getTownAll(connection);
        return townAllMap;
    }

    public static Map<String, String> getCityTownMap(String string) {
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        if (string.length() == 1) {
            for (String string2 : townAllMap.keySet()) {
                if (!string2.startsWith(string)) continue;
                String string3 = string2.substring(1, 3);
                treeMap.put(string3, townAllMap.get(string2));
            }
        }
        return treeMap;
    }

    public static Map<String, String> getCityTownMap(String string, String string2) {
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        if (string.length() == 1 && string2.length() == 2) {
            for (String string3 : townAllMap.keySet()) {
                if (!string3.startsWith(string + string2)) continue;
                treeMap.put(string2, townAllMap.get(string3));
            }
        }
        return treeMap;
    }

    public static Map<String, String> getUrbanMap() {
        return urbanMap;
    }

    public static Map<String, String> getUrbanMap(Connection connection) throws Exception {
        urbanMap = new NDAO_SRKEYN().getRegdUrbanMap(connection);
        return urbanMap;
    }

    public static String decodeUrban(String string) {
        String string2 = string;
        if (urbanMap.containsKey(string)) {
            string2 = urbanMap.get(string);
        }
        return string2;
    }

    public static Map<String, String> getSectAllMap(Connection connection) throws Exception {
        sectAllMap = new NDAO_SRKEYN_ALL().getSectAll(connection);
        return sectAllMap;
    }

    public static Map<String, String> getSectAllMap() {
        return sectAllMap;
    }

    public static String getOfficeFromSect(String string, String string2) {
        String string3 = "";
        for (String string4 : sectAllMap.keySet()) {
            if (!string4.startsWith(string) || !string4.substring(3, 7).equals(string2)) continue;
            string3 = string4.substring(7, 9);
            break;
        }
        return string3;
    }

    public static String getDistFromSect(String string, String string2) {
        String string3 = "";
        for (String string4 : sectAllMap.keySet()) {
            if (!string4.startsWith(string) || !string4.substring(3, 7).equals(string2)) continue;
            string3 = string4.substring(1, 3);
            break;
        }
        return string3;
    }

    public static String decodeDistFromSect(String string, String string2) {
        String string3 = CodeList.getDistFromSect(string, string2);
        return CodeList.decodeDist(string, string3);
    }

    public static String decodeSect(String string, String string2) {
        String string3 = "";
        for (String string4 : sectAllMap.keySet()) {
            if (!string4.startsWith(string) || !string4.substring(3, 7).equals(string2)) continue;
            string3 = sectAllMap.get(string4);
            break;
        }
        return string3;
    }
}

