/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.util;

import com.wfusion.util.SQLInjectionEnum;
import java.util.ArrayList;
import java.util.Arrays;

public class SQLInjectionFilterHelper {
    public static void main(String[] stringArray) {
        String string = "'";
        System.out.println(SQLInjectionFilterHelper.cleanSQLInjection("TEST1=" + string));
    }

    public static String cleanSQLInjection(String string, SQLInjectionEnum[] sQLInjectionEnumArray) {
        for (SQLInjectionEnum sQLInjectionEnum : SQLInjectionEnum.values()) {
            if (sQLInjectionEnumArray == null) {
                string = SQLInjectionFilterHelper.replace(sQLInjectionEnum, string);
                continue;
            }
            ArrayList<SQLInjectionEnum> arrayList = new ArrayList<SQLInjectionEnum>(Arrays.asList(sQLInjectionEnumArray));
            if (arrayList.contains((Object)sQLInjectionEnum)) continue;
            string = SQLInjectionFilterHelper.replace(sQLInjectionEnum, string);
        }
        return string;
    }

    public static String cleanSQLInjection(String string) {
        if (string != null) {
            for (SQLInjectionEnum sQLInjectionEnum : SQLInjectionEnum.values()) {
                string = SQLInjectionFilterHelper.replace(sQLInjectionEnum, string);
            }
        }
        return string;
    }

    private static String replace(SQLInjectionEnum sQLInjectionEnum, String string) {
        return string.replaceAll(sQLInjectionEnum.getKey(), sQLInjectionEnum.getReplaceValue());
    }
}

