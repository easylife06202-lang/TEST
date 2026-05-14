/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.util;

import com.wfusion.util.DateTime;
import java.util.ArrayList;

public class YearUtil {
    public static ArrayList<String> checkLastYear(ArrayList<String> arrayList) {
        ArrayList<String> arrayList2 = new ArrayList<String>();
        arrayList2.addAll(arrayList);
        String string = DateTime.getTWYear();
        if (!arrayList.contains(string)) {
            arrayList2.add(string);
        }
        return arrayList2;
    }
}

