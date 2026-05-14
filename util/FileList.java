/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.io.FileUtils
 */
package com.wfusion.util;

import java.io.File;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;

public class FileList {
    public static String[] get(String string) {
        ArrayList<String> arrayList = new ArrayList<String>();
        File file = new File(string);
        File[] fileArray = file.listFiles();
        int n = 0;
        String string2 = "";
        for (int i = 0; i < fileArray.length; ++i) {
            n = fileArray[i].getName().lastIndexOf(".") != -1 ? fileArray[i].getName().lastIndexOf(".") : fileArray[i].getName().length();
            string2 = fileArray[i].getName().substring(0, n);
            arrayList.add(string2);
        }
        String[] stringArray = arrayList.toArray(new String[0]);
        return stringArray;
    }

    public static File createDirOnNoneExist(String string) {
        File file = null;
        try {
            file = new File(string);
            if (!file.exists()) {
                FileUtils.forceMkdir((File)file);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return file;
    }
}

