/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.util;

import com.wfusion.util.StringProcess;
import java.util.Collection;
import java.util.Iterator;

public class ArrayJoiner {
    public static String join(String[] stringArray) {
        if (stringArray == null) {
            return "";
        }
        String string = "(";
        for (int i = 0; i < stringArray.length; ++i) {
            string = string + (i > 0 ? ", " : "");
            string = string + "'" + stringArray[i] + "'";
        }
        string = string + ")";
        return string;
    }

    public static String joinAllCity() {
        String[] stringArray = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Z"};
        return ArrayJoiner.join(stringArray);
    }

    public static String join(Collection collection) {
        if (collection == null || collection.size() == 0) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer("(");
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            stringBuffer.append("'").append(iterator.next()).append("',");
        }
        return stringBuffer.substring(0, stringBuffer.length() - 1) + ")";
    }

    public static String ArrayToSQL(String[] stringArray) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < stringArray.length; ++i) {
            String string = stringArray[i];
            if (StringProcess.isEmpty(string)) continue;
            if (i != 0) {
                stringBuffer.append(",");
            }
            stringBuffer.append("'" + string + "'");
        }
        return stringBuffer.toString();
    }

    public static String array2String(Collection collection, String string) {
        if (collection == null || collection.size() == 0) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            stringBuffer.append(string).append(iterator.next());
        }
        return stringBuffer.substring(string.length());
    }

    public static String array2String(String[] stringArray, String string) {
        if (stringArray == null || stringArray.length == 0 || string == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (String string2 : stringArray) {
            stringBuffer.append(string).append(string2);
        }
        return stringBuffer.substring(string.length());
    }

    public static String join(String[] stringArray, String string) {
        if (stringArray == null) {
            return "";
        }
        String string2 = "(";
        for (int i = 0; i < stringArray.length; ++i) {
            string2 = string2 + (i > 0 ? string : "");
            string2 = string2 + "'" + stringArray[i] + "'";
        }
        string2 = string2 + ")";
        return string2;
    }

    public static String getInString(Collection<?> collection) {
        StringBuffer stringBuffer = new StringBuffer();
        if (collection == null || collection.size() == 0) {
            stringBuffer.append("()");
        } else {
            stringBuffer.append('(');
            for (Object obj : collection) {
                if (obj instanceof String) {
                    stringBuffer.append("'").append(obj).append("',");
                    continue;
                }
                stringBuffer.append(obj).append(",");
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            stringBuffer.append(')');
        }
        return stringBuffer.toString();
    }
}

