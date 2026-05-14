/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.io.FileUtils
 */
package com.wfusion.baseland.system;

import com.wfusion.baseland.SystemDelBakBean;
import com.wfusion.baseland.basic.Model;
import com.wfusion.fx.util.JavaFXUtil;
import com.wfusion.util.StringProcess;
import java.io.File;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;

public class SystemDeleteBakModel
extends Model {
    public ArrayList<SystemDelBakBean> bakList = null;

    public ArrayList<SystemDelBakBean> queryBakList() {
        ArrayList<SystemDelBakBean> arrayList = new ArrayList<SystemDelBakBean>();
        String string = SQLITE_PATH + "Backup";
        File file = new File(string);
        if (file.exists() && file.isDirectory()) {
            String[] stringArray = file.list();
            if (stringArray != null && stringArray.length > 0) {
                SystemDelBakBean systemDelBakBean = null;
                for (String string2 : stringArray) {
                    systemDelBakBean = new SystemDelBakBean();
                    systemDelBakBean.setFileName(string2);
                    systemDelBakBean.setFileShow(this.getShow(string2));
                    arrayList.add(systemDelBakBean);
                }
            }
            this.bakList = arrayList;
        }
        return arrayList;
    }

    private String getShow(String string) {
        String string2 = "";
        if (!StringProcess.isEmpty(string) && string.length() == 14) {
            String string3 = string.substring(0, 4);
            String string4 = string.substring(4, 6);
            String string5 = string.substring(6, 8);
            String string6 = string.substring(8, 10);
            String string7 = string.substring(10, 12);
            String string8 = string.substring(12, 14);
            string2 = "\u5099\u4efd\u6642\u9593: " + string3 + "/" + string4 + "/" + string5 + " " + string6 + ":" + string7 + ":" + string8;
        }
        return string2;
    }

    public ArrayList<SystemDelBakBean> getBakList() {
        return this.bakList;
    }

    public void deleteBak(SystemDelBakBean systemDelBakBean) {
        String string = SQLITE_PATH + "Backup\\" + systemDelBakBean.getFileName();
        File file = new File(string);
        if (file.exists() && file.isDirectory()) {
            FileUtils.deleteQuietly((File)file);
        }
        if (!file.exists()) {
            JavaFXUtil.showNormalMessageBox("\u522a\u9664\u6210\u529f", "");
        } else {
            JavaFXUtil.showErrorMessageBox("\u522a\u9664\u5931\u6557");
        }
    }
}

