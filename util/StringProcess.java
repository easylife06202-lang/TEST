/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.util;

import com.wfusion.util.BigDecimalUtil;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class StringProcess {
    protected static final char[] HEXARRAY = "0123456789ABCDEF".toCharArray();
    private static final String UNICODE_START_SYMBOL = "&#";
    private static final String REGEX_UNICODE_INCLUDE = "(.*&#[0-9]+;.*)+";
    private static final String REGEX_PURE_NUMBER = "[0-9]+";

    public static String bytesToHex(byte[] byArray) {
        return StringProcess.bytesToHex(byArray, "");
    }

    public static String bytesToHex(byte[] byArray, String string) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < byArray.length; ++i) {
            int n = byArray[i] & 0xFF;
            stringBuilder.append(HEXARRAY[n >>> 4]).append(HEXARRAY[n & 0xF]).append(string);
        }
        return stringBuilder.toString();
    }

    public static String EMPTY(String string, String string2) {
        if (string == null || string.trim().equals("")) {
            return string2 == null ? "" : string2;
        }
        return string;
    }

    public static boolean isEmpty(String string) {
        return string == null || string.trim().equals("");
    }

    public static String NULL(String string) {
        if (string == null) {
            return "";
        }
        return string;
    }

    public static String NULL(String string, String string2) {
        if (string == null || string.equals("")) {
            return string2 == null ? "" : string2;
        }
        return string;
    }

    public static String NULL(Object object) {
        if (object == null) {
            return "";
        }
        return String.valueOf(object);
    }

    public static String[] NULL(String[] stringArray) {
        if (stringArray == null) {
            return new String[0];
        }
        for (int i = 0; i < stringArray.length; ++i) {
            stringArray[i] = StringProcess.NULL(stringArray[i]);
        }
        return stringArray;
    }

    public static String increment(String string, int n) {
        int n2 = 0;
        try {
            n2 = Integer.parseInt(string);
            return StringProcess.fillZero(++n2, n);
        }
        catch (Exception exception) {
            System.out.println("Error in increment String." + exception.getMessage());
            return null;
        }
    }

    public static String decrement(String string, int n) {
        int n2 = 0;
        try {
            n2 = Integer.parseInt(string);
            return StringProcess.fillZero(--n2, n);
        }
        catch (Exception exception) {
            System.out.println("Error in decrement String." + exception.getMessage());
            return null;
        }
    }

    public static String fillZero(int n, int n2) {
        return StringProcess.fillZero(String.valueOf(n), n2);
    }

    public static String trimZero(String string) {
        if (string != null) {
            int n;
            string = string.trim();
            for (n = 0; n < string.length() && string.charAt(n) == '0'; ++n) {
            }
            return string.substring(n);
        }
        return "";
    }

    public static String fillZero(String string, int n) {
        StringBuffer stringBuffer = new StringBuffer();
        if (string != null) {
            for (int i = 0; i < n; ++i) {
                stringBuffer.append('0');
            }
            stringBuffer.append(string);
            if (string.length() > n) {
                return string;
            }
            return stringBuffer.substring(stringBuffer.length() - n);
        }
        return null;
    }

    public static String fillRight(String string, char c, int n) {
        string = StringProcess.NULL(string);
        StringBuffer stringBuffer = new StringBuffer(string);
        for (int i = 0; i < n - string.length(); ++i) {
            stringBuffer.append(c);
        }
        return stringBuffer.toString();
    }

    public static String fillLeft(String string, char c, int n) {
        string = StringProcess.NULL(string);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < n - string.length(); ++i) {
            stringBuffer.append(c);
        }
        stringBuffer.append(string);
        return stringBuffer.toString();
    }

    public static boolean isNumber(String string) {
        boolean bl = false;
        String string2 = "^-?\\d+$";
        String string3 = "^(-?\\d+)(\\.\\d+)?$";
        bl = bl || string.matches(string2);
        bl = bl || string.matches(string3);
        return bl;
    }

    public static String[] split(String string, String string2) {
        if (string != null && string2 != null) {
            int n = StringProcess.countString(string, string2);
            return StringProcess.split(string, string2, n + 1);
        }
        return new String[0];
    }

    public static String[] split(String string, String string2, int n) {
        String[] stringArray = new String[n];
        for (int i = 0; i < stringArray.length; ++i) {
            stringArray[i] = "";
        }
        if (string2.equals(".") || string2.equals("+") || string2.equals("*") || string2.equals("?") || string2.equals("|") || string2.equals("$")) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append('[').append(string2).append(']');
            string2 = stringBuffer.toString();
        } else if (string2.equals("^")) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("[\\^]");
            string2 = stringBuffer.toString();
        }
        String[] stringArray2 = StringProcess.NULL(string).split(string2);
        for (int i = 0; i < stringArray2.length && i < n; ++i) {
            stringArray[i] = stringArray2[i];
        }
        return stringArray;
    }

    public static int countString(String string, String string2) {
        if (string != null && string2 != null) {
            int n = string.indexOf(string2);
            if (n >= 0) {
                string = string.substring(n + string2.length());
                return StringProcess.countString(string, string2) + 1;
            }
            return 0;
        }
        return 0;
    }

    public static String merge(String[] stringArray, String string) {
        StringBuffer stringBuffer = new StringBuffer();
        if (stringArray != null && string != null) {
            for (int i = 0; i < stringArray.length; ++i) {
                stringBuffer.append(string).append(stringArray[i]);
            }
        }
        if (stringBuffer.length() != 0) {
            return stringBuffer.substring(string.length());
        }
        return "";
    }

    public static String getField(Map<String, ?> map, String string) {
        return StringProcess.getField(map, string, "");
    }

    public static String getFieldDefaultSelf(Map<String, ?> map, String string) {
        return StringProcess.getField(map, string, string);
    }

    public static String getField(Map<String, ?> map, String string, String string2) {
        String string3 = string2;
        try {
            Object obj;
            if (map != null && map.containsKey(string) && (obj = map.get(string)) != null) {
                string3 = String.valueOf(obj);
            }
        }
        catch (Exception exception) {
            System.out.println(exception.toString());
        }
        return string3;
    }

    public static String getFustionString(String string) {
        return StringProcess.getFustionString(string, true);
    }

    public static String getFustionString(String string, boolean bl) {
        StringBuffer stringBuffer = new StringBuffer("");
        if (!StringProcess.isEmpty(string) && string.length() > 0) {
            stringBuffer.append("get");
            stringBuffer.append(string.substring(0, 1).toUpperCase());
            if (bl) {
                stringBuffer.append(string.substring(1).toLowerCase());
            } else {
                stringBuffer.append(string.substring(1));
            }
        }
        return stringBuffer.toString();
    }

    public static String setFustionString(String string) {
        return StringProcess.setFustionString(string, true);
    }

    public static String setFustionString(String string, boolean bl) {
        StringBuffer stringBuffer = new StringBuffer("");
        if (!StringProcess.isEmpty(string) && string.length() > 1) {
            stringBuffer.append("set");
            stringBuffer.append(string.substring(0, 1).toUpperCase());
            if (bl) {
                stringBuffer.append(string.substring(1).toLowerCase());
            } else {
                stringBuffer.append(string.substring(1));
            }
        }
        return stringBuffer.toString();
    }

    public static String addressInterval(String string, int n) {
        if (StringProcess.isEmpty(string)) {
            return "";
        }
        string = string.trim();
        string = string.replaceAll("\u4e4b", "-");
        string = string.replaceAll("\u2500", "-");
        string = string.replaceAll("\uff0d", "-");
        string = StringProcess.FullWidth2HalfWidth(string);
        int n2 = string.lastIndexOf("\u865f");
        int n3 = 0;
        int n4 = 0;
        for (int i = n2 - 1; i >= 0; --i) {
            if ("-".equals(string.substring(i, i + 1))) {
                n2 = i;
                continue;
            }
            if (string.substring(i, n2).matches(REGEX_PURE_NUMBER)) continue;
            n4 = StringProcess.parserInt(string.substring(i + 1, n2));
            n3 = i + 1;
            break;
        }
        String string2 = n4 / n * n + 1 + "-" + (n4 / n + 1) * n;
        return StringProcess.HalfWidth2FullWidth(string.substring(0, n3) + string2 + "\u865f");
    }

    public static String HalfWidth2FullWidth(String string) {
        String string2 = "";
        char[] cArray = string.toCharArray();
        int n = 0;
        for (int i = 0; i < cArray.length; ++i) {
            n = cArray[i];
            if (n >= 32 && n <= 126) {
                n += 65248;
            }
            string2 = string2 + (char)n;
        }
        return string2;
    }

    public static String FullWidth2HalfWidth(String string) {
        String string2 = "";
        char[] cArray = string.toCharArray();
        int n = 0;
        for (int i = 0; i < cArray.length; ++i) {
            n = cArray[i];
            if (n >= 65280 && n <= 65374) {
                n -= 65248;
            }
            string2 = string2 + (char)n;
        }
        return string2;
    }

    public static String replaceAll(String string, String string2, String string3) {
        if (string == null) {
            return string;
        }
        int n = 0;
        int n2 = 0;
        int n3 = string2.length();
        StringBuffer stringBuffer = new StringBuffer();
        while ((n2 = string.indexOf(string2, n)) >= 0) {
            stringBuffer.append(string.substring(n, n2));
            stringBuffer.append(string3);
            n = n2 + n3;
        }
        return new String(stringBuffer.append(string.substring(n)));
    }

    public static String unicode2Ascii(String string) throws Exception {
        return StringProcess.unicode2Ascii(string, "MS950");
    }

    public static String unicode2Ascii(String string, String string2) throws Exception {
        if (StringProcess.isEmpty(string)) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < string.length(); ++i) {
            String string3 = string.charAt(i) + "";
            byte[] byArray = string3.getBytes(string2);
            if (string.charAt(i) != '?' && byArray.length == 1 && byArray[0] == 63) {
                char c = string.charAt(i);
                stringBuffer.append(UNICODE_START_SYMBOL).append((int)c).append(";");
                continue;
            }
            stringBuffer.append(string.charAt(i));
        }
        return stringBuffer.toString();
    }

    public static String ascii2Unicode(String string) {
        if (string != null && string.matches(REGEX_UNICODE_INCLUDE)) {
            StringBuffer stringBuffer = new StringBuffer();
            int n = 0;
            int n2 = -1;
            while ((n2 = string.indexOf(UNICODE_START_SYMBOL, n)) > -1) {
                stringBuffer.append(string.substring(n, n2));
                int n3 = string.indexOf(";", n2);
                if (n3 > n2) {
                    String string2 = string.substring(n2 + UNICODE_START_SYMBOL.length(), n3);
                    if (string2.matches(REGEX_PURE_NUMBER)) {
                        stringBuffer.append((char)StringProcess.parserInt(string2));
                        n = n3 + 1;
                        continue;
                    }
                    stringBuffer.append(UNICODE_START_SYMBOL).append(string2);
                    n = n3;
                    continue;
                }
                n = n2;
                break;
            }
            stringBuffer.append(string.substring(n));
            return stringBuffer.toString();
        }
        return string;
    }

    public static String getdateCh(String string) {
        String string2 = "";
        if (string.length() == 7) {
            string2 = string.substring(0, 3) + "\u5e74" + StringProcess.parserInt(string.substring(3, 5)) + "\u6708" + StringProcess.parserInt(string.substring(5)) + "\u65e5";
        } else if (string.length() == 8) {
            string2 = string.substring(0, 4) + "\u5e74" + StringProcess.parserInt(string.substring(4, 6)) + "\u6708" + StringProcess.parserInt(string.substring(6)) + "\u65e5";
        }
        return string2;
    }

    public static BigDecimal parserDecimal(String string) {
        return StringProcess.parserDecimal(string, new BigDecimal(0));
    }

    public static BigDecimal parserDecimal(String string, BigDecimal bigDecimal) {
        BigDecimal bigDecimal2 = bigDecimal;
        if (bigDecimal2 == null) {
            bigDecimal2 = new BigDecimal(0);
        }
        try {
            if (string == null || "".equals(string.trim())) {
                return bigDecimal2;
            }
            bigDecimal2 = new BigDecimal(string);
        }
        catch (Exception exception) {
            System.out.println("StringProcess.parserDecimal:" + exception);
        }
        return bigDecimal2;
    }

    public static long parserLong(String string) {
        return StringProcess.parserLong(string, 0);
    }

    public static long parserLong(String string, int n) {
        long l = n;
        if (string != null && !string.trim().equals("")) {
            try {
                l = Long.parseLong(string);
            }
            catch (Exception exception) {
                System.out.println("StringProcess.parserDouble error due to input string: \"" + string + "\"");
            }
        }
        return l;
    }

    public static int parserInt(String string) {
        return StringProcess.parserInt(string, 0);
    }

    public static int parserInt(Object object, int n) {
        int n2 = n;
        if (object != null) {
            n2 = (int)StringProcess.parserDouble(object.toString(), n);
        }
        return n2;
    }

    public static int parserInt(String string, int n) {
        int n2 = n;
        n2 = (int)StringProcess.parserDouble(string, n);
        return n2;
    }

    public static double parserDouble(String string) {
        return StringProcess.parserDouble(string, 0.0);
    }

    public static double parserDouble(String string, double d) {
        double d2 = d;
        if (string != null && !string.trim().equals("") && !string.equalsIgnoreCase("NaN")) {
            try {
                d2 = Double.parseDouble(string);
            }
            catch (Exception exception) {
                System.out.println("StringProcess.parserDouble error due to input string: \"" + string + "\"");
            }
        }
        return d2;
    }

    public static boolean parserBoolean(String string) {
        boolean bl = false;
        if (string != null && !string.trim().equals("")) {
            bl = (string = string.trim()).equals("0") || string.equalsIgnoreCase("no") || string.equalsIgnoreCase("n") ? false : (string.equals("1") || string.equalsIgnoreCase("yes") || string.equalsIgnoreCase("y") ? true : string.equalsIgnoreCase("true"));
        }
        return bl;
    }

    public static boolean parserBoolean(String string, boolean bl) {
        boolean bl2 = bl;
        if (string != null && !string.trim().equals("")) {
            if ((string = string.trim()).equals("0") || string.equalsIgnoreCase("no") || string.equalsIgnoreCase("n") || string.equalsIgnoreCase("false")) {
                bl2 = false;
            } else if (string.equals("1") || string.equalsIgnoreCase("yes") || string.equalsIgnoreCase("y") || string.equalsIgnoreCase("true")) {
                bl2 = true;
            } else {
                try {
                    bl2 = Boolean.getBoolean(string.toLowerCase());
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
        return bl2;
    }

    public static double roundCd(double d) {
        double d2 = d;
        d2 = d2 > 0.0 && d2 <= 100.0 ? BigDecimalUtil.round(d2, 0) : (d2 > 100.0 && d2 <= 1000.0 ? BigDecimalUtil.round(BigDecimalUtil.div(d2, 10.0), 0) * 10.0 : (d2 > 1000.0 && d2 <= 100000.0 ? BigDecimalUtil.round(BigDecimalUtil.div(d2, 100.0), 0) * 100.0 : BigDecimalUtil.round(BigDecimalUtil.div(d2, 1000.0), 0) * 1000.0));
        return d2;
    }

    public static String moneyFomat(String string) {
        if (!StringProcess.isNumber(string)) {
            return "\u975e\u6578\u5b57";
        }
        double d = StringProcess.parserDouble(string);
        if (d >= 10000.0) {
            return new BigDecimal(d / 10000.0).setScale(1, 4).toString();
        }
        return new BigDecimal(d / 10000.0).setScale(3, 4).toString();
    }

    public static String[] parserSmallSection2(String string) {
        String[] stringArray = StringProcess.parserSmallSection(string);
        int n = stringArray[0].lastIndexOf("\u6bb5");
        if (n > 0) {
            stringArray[0].substring(0, n);
        }
        if ((n = stringArray[1].lastIndexOf("\u5c0f\u6bb5")) > 0) {
            stringArray[1].substring(0, n);
        }
        return stringArray;
    }

    public static String[] parserSmallSection(String string) {
        String[] stringArray = new String[]{"", ""};
        if (string != null && string.indexOf("\u5c0f\u6bb5") > 0) {
            if (string.indexOf("\u5c0f\u6bb5") > string.indexOf("\u6bb5")) {
                stringArray[0] = string.substring(0, string.indexOf("\u6bb5") + 1);
                stringArray[1] = string.substring(string.indexOf("\u6bb5") + 1);
            } else {
                stringArray[1] = string;
            }
        } else if (string != null) {
            stringArray[0] = string;
        }
        return stringArray;
    }

    public static String getLandMCode(String string) {
        if (string != null && string.length() >= 8) {
            return string.substring(0, 4);
        }
        System.out.println("\u4e0d\u5b8c\u6574\u5730\u865f\uff01--" + string);
        if (string != null) {
            return StringProcess.fillZero(string, 8).substring(0, 4);
        }
        return "0000";
    }

    public static String getLandCCode(String string) {
        if (string != null && string.length() >= 8) {
            return string.substring(4, 8);
        }
        System.out.println("\u4e0d\u5b8c\u6574\u5730\u865f\uff01--" + string);
        if (string != null) {
            return StringProcess.fillZero(string, 8).substring(4, 8);
        }
        return "0000";
    }

    public static String getLandShort(String string) {
        if (string != null && string.length() >= 8) {
            String string2 = StringProcess.trimZero(string.substring(0, 4));
            String string3 = StringProcess.trimZero(string.substring(4, 8));
            return string2 + ("".equals(string3) ? "" : "-" + string3);
        }
        return string;
    }

    public static String landCode13Increment(String string) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(string.substring(0, string.length() - 4));
        String string2 = string.substring(string.length() - 4);
        return stringBuffer.append(StringProcess.increment(string2, 4)).toString();
    }

    public static String priceShort2Long(String string, String string2) {
        String string3 = StringProcess.priceShort2Long(string);
        return string3.substring(0, 4) + string2 + string3.substring(4);
    }

    public static String priceLong2Long(String string) {
        String string2 = StringProcess.fillZero(string, 6);
        return string2.substring(0, 4) + "-" + string2.substring(4);
    }

    public static String priceShort2Long(String string) {
        String string2 = null;
        int n = 0;
        string2 = string != null ? ((n = (string = string.trim()).indexOf("-")) >= 0 ? StringProcess.fillZero(string.substring(0, n), 4) + StringProcess.fillZero(string.substring(n + 1), 2) : (StringProcess.fillZero(string.trim(), 4) + "00").substring(0, 6)) : "000000";
        return string2;
    }

    public static String priceLong2Short(String string) {
        String string2 = null;
        String string3 = null;
        String string4 = null;
        int n = 0;
        if (string != null) {
            if ((string = string.trim()).length() != 0 && string.indexOf("-") < 0) {
                if (string.length() < 6) {
                    for (n = 0; n < string.length() && string.charAt(n) == '0'; ++n) {
                    }
                    string3 = string.substring(n);
                    string4 = "";
                    string2 = string3;
                } else {
                    string3 = string.substring(0, 4);
                    string4 = string.substring(4, 6);
                    for (n = 0; n < 4 && string3.charAt(n) == '0'; ++n) {
                    }
                    string3 = string3.substring(n);
                    for (n = 0; n < 2 && string4.charAt(n) == '0'; ++n) {
                    }
                    string2 = (string4 = string4.substring(n)).equals("") ? string3 : string3 + "-" + string4;
                }
            } else if (string.indexOf("-") > 0) {
                String[] stringArray = string.split("-");
                string3 = StringProcess.trimZero(stringArray[0]);
                string4 = StringProcess.trimZero(stringArray[1]);
                string2 = string4.length() > 0 ? string3 + "-" + string4 : string3;
            } else {
                string2 = "9999";
            }
        } else {
            string2 = "9999";
        }
        if (string2.equals("")) {
            string2 = "9999";
        }
        return string2;
    }

    public static String[] arraysObjectToString(Object[] objectArray) {
        String[] stringArray = new String[objectArray.length];
        for (int i = 0; i < objectArray.length; ++i) {
            stringArray[i] = objectArray[i].toString();
        }
        return stringArray;
    }

    public static String parserShortLandNo2Long(String string) {
        String string2 = "";
        int n = 0;
        if (string == null) {
            string2 = "";
        } else if (!string.matches("[0-9]{1,4}[-]{0,1}[0-9]{0,4}")) {
            string2 = string;
        } else if (string != null) {
            n = (string = string.trim()).indexOf("-");
            string2 = n >= 0 ? StringProcess.fillZero(string.substring(0, n), 4) + StringProcess.fillZero(string.substring(n + 1), 4) : (string.length() <= 4 ? (StringProcess.fillZero(string.trim(), 4) + "0000").substring(0, 8) : StringProcess.fillLeft(string.trim(), '0', 8).substring(0, 8));
        }
        return string2;
    }

    public static String parserShortPriceNo2Long(String string) {
        String string2 = null;
        int n = 0;
        string2 = string != null ? ((n = (string = string.trim()).indexOf("-")) >= 0 ? StringProcess.fillZero(string.substring(0, n), 4) + StringProcess.fillZero(string.substring(n + 1), 2) : (StringProcess.fillZero(string.trim(), 4) + "00").substring(0, 6)) : "000000";
        return string2;
    }

    public static String[] parserShortPriceNo2LongStrArr(String string) {
        String string2 = StringProcess.parserShortPriceNo2Long(string.replaceAll("\\.", ""));
        String[] stringArray = new String[2];
        if (!string2.equals("000000")) {
            stringArray[0] = string2.substring(0, 4);
            stringArray[1] = string2.substring(4);
        } else {
            stringArray[0] = "";
            stringArray[1] = "";
        }
        return stringArray;
    }

    public static String parserShortBuildNo2Long(String string) {
        String string2 = null;
        int n = 0;
        string2 = string != null ? ((n = (string = string.trim()).indexOf("-")) >= 0 ? StringProcess.fillZero(string.substring(0, n), 5) + StringProcess.fillZero(string.substring(n + 1), 3) : (StringProcess.fillZero(string.trim(), 5) + "000").substring(0, 8)) : "00000000";
        return string2;
    }

    public static String[] parserShortBuildNo2LongStrArr(String string) {
        String string2 = StringProcess.parserShortBuildNo2Long(string.replaceAll("\\.", ""));
        String[] stringArray = new String[2];
        if (!string2.equals("00000000")) {
            stringArray[0] = string2.substring(0, 5);
            stringArray[1] = string2.substring(5);
        } else {
            stringArray[0] = "";
            stringArray[1] = "";
        }
        return stringArray;
    }

    public static String build2Short(String string) {
        if (string == null || string.length() == 0) {
            return "";
        }
        if (string.length() < 8) {
            return string;
        }
        StringBuffer stringBuffer = new StringBuffer();
        int n = StringProcess.parserInt(string.substring(0, 5));
        stringBuffer.append(n);
        int n2 = StringProcess.parserInt(string.substring(5));
        if (n2 == 0) {
            return stringBuffer.toString();
        }
        stringBuffer.append('-').append(n2);
        return stringBuffer.toString();
    }

    public static HashMap<String, HashSet<String>> groupSect(String[] stringArray) {
        int n = stringArray.length;
        HashMap<String, HashSet<String>> hashMap = new HashMap<String, HashSet<String>>();
        HashSet<Object> hashSet = null;
        for (int i = 0; i < n; ++i) {
            if (hashMap.containsKey(stringArray[i].substring(0, 4))) {
                hashSet = hashMap.get(stringArray[i].substring(0, 4));
            } else {
                hashSet = new HashSet();
                hashMap.put(stringArray[i].substring(0, 4), hashSet);
            }
            hashSet.add(stringArray[i].substring(4));
        }
        return hashMap;
    }

    public static String getFirstNumber(String string) throws Exception {
        String[] stringArray = (string = string.replaceAll("[^-?.?0-9]+", ",")).split(",");
        if (stringArray.length <= 0) {
            return "";
        }
        for (String string2 : stringArray) {
            if (StringProcess.isEmpty(string2)) continue;
            return string2;
        }
        return "";
    }

    public static int compareNonNumStr(String string, String string2) {
        try {
            double d = StringProcess.parserDouble(StringProcess.getFirstNumber(string));
            double d2 = StringProcess.parserDouble(StringProcess.getFirstNumber(string2));
            return d >= d2 ? 1 : -1;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return 0;
        }
    }
}

