/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.io.FileUtils
 */
package com.wfusion.baseland.system;

import com.wfusion.baseland.SystemCopyBean;
import com.wfusion.baseland.basic.Model;
import com.wfusion.dataaccess.vo.VoBase;
import com.wfusion.fx.util.ExceptionDialog;
import com.wfusion.fx.util.JavaFXUtil;
import com.wfusion.util.SUtility;
import com.wfusion.util.SqlUtil;
import com.wfusion.util.StringProcess;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_AHP;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_INDIVIDUAL_FACTOR;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_INDIVIDUAL_FACTOR_STD;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_INSTRU_STD_PRICE;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_PRICERATE;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_REGIONAL_FACTOR;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_REGIONAL_FACTOR_STD;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_REPORT_PARAM;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_AHP;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_INDIVIDUAL_FACTOR;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_INDIVIDUAL_FACTOR_STD;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_INSTRU_STD_PRICE;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_PRICERATE;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_REGIONAL_FACTOR;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_REGIONAL_FACTOR_STD;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_REPORT_PARAM;
import org.apache.commons.io.FileUtils;

public class SystemCopyModel
extends Model {
    private SimpleDateFormat datef = new SimpleDateFormat("YYYYMMddHHmmss");

    public boolean backupDB() {
        String string = SQLITE_PATH + "BaseLand.db";
        String string2 = SQLITE_PATH + "BaseLand_A.db";
        String string3 = SQLITE_PATH + "BaseLand_B.db";
        String string4 = SQLITE_PATH + "BaseLand_C.db";
        String string5 = SQLITE_PATH + "Backup\\" + this.datef.format(new Date());
        File file = new File(string5);
        file.mkdirs();
        File file2 = new File(string);
        File file3 = new File(string2);
        File file4 = new File(string3);
        File file5 = new File(string4);
        File file6 = new File(string5 + "\\" + "BaseLand.db");
        File file7 = new File(string5 + "\\" + "BaseLand_A.db");
        File file8 = new File(string5 + "\\" + "BaseLand_B.db");
        File file9 = new File(string5 + "\\" + "BaseLand_C.db");
        if (!file2.exists()) {
            JavaFXUtil.showErrorMessageBox("\u8cc7\u6599\u5eab\u932f\u8aa4\uff0c\u8acb\u5f9e\u65b0\u5b89\u88dd\u7a0b\u5f0f", "");
            return false;
        }
        try {
            FileUtils.copyFile((File)file2, (File)file6);
            if (file3.exists()) {
                FileUtils.copyFile((File)file3, (File)file7);
            }
            if (file4.exists()) {
                FileUtils.copyFile((File)file4, (File)file8);
            }
            if (file5.exists()) {
                FileUtils.copyFile((File)file5, (File)file9);
            }
            return true;
        }
        catch (IOException iOException) {
            ExceptionDialog.show(iOException);
            return false;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean copyRegionalFactor(SystemCopyBean systemCopyBean) {
        Connection connection;
        block20: {
            boolean bl;
            connection = null;
            NDAO_BASELAND_REGIONAL_FACTOR nDAO_BASELAND_REGIONAL_FACTOR = new NDAO_BASELAND_REGIONAL_FACTOR();
            NDAO_BASELAND_REGIONAL_FACTOR_STD nDAO_BASELAND_REGIONAL_FACTOR_STD = new NDAO_BASELAND_REGIONAL_FACTOR_STD();
            try {
                connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
                ArrayList<NVO_BASELAND_REGIONAL_FACTOR> arrayList = null;
                arrayList = StringProcess.isEmpty(systemCopyBean.getChooseLandNo()) ? nDAO_BASELAND_REGIONAL_FACTOR.queryDataForCopy_excludeBaseno(systemCopyBean.getChooseAA45(), systemCopyBean.getChooseYear(), systemCopyBean.getChooseDist(), systemCopyBean.getChooseVersion(), systemCopyBean.getChooseLandNo(), connection) : (StringProcess.isEmpty(systemCopyBean.getCopyLandNo()) ? nDAO_BASELAND_REGIONAL_FACTOR.queryDataForCopy_excludeBaseno(systemCopyBean.getChooseAA45(), systemCopyBean.getChooseYear(), systemCopyBean.getChooseDist(), systemCopyBean.getChooseVersion(), systemCopyBean.getChooseLandNo(), connection) : nDAO_BASELAND_REGIONAL_FACTOR.queryDataForCopy(systemCopyBean.getChooseAA45(), systemCopyBean.getChooseYear(), systemCopyBean.getChooseDist(), systemCopyBean.getChooseVersion(), systemCopyBean.getChooseLandNo(), connection));
                if (arrayList != null && arrayList.size() > 0) {
                    connection.setAutoCommit(false);
                    ArrayList<NVO_BASELAND_REGIONAL_FACTOR> arrayList2 = null;
                    arrayList2 = StringProcess.isEmpty(systemCopyBean.getCopyLandNo()) ? nDAO_BASELAND_REGIONAL_FACTOR.queryDataForCopy_excludeBaseno(systemCopyBean.getCopyAA45(), systemCopyBean.getCopyYear(), systemCopyBean.getCopyDist(), systemCopyBean.getCopyVersion(), systemCopyBean.getCopyLandNo(), connection) : nDAO_BASELAND_REGIONAL_FACTOR.queryDataForCopy(systemCopyBean.getCopyAA45(), systemCopyBean.getCopyYear(), systemCopyBean.getCopyDist(), systemCopyBean.getCopyVersion(), systemCopyBean.getCopyLandNo(), connection);
                    if (arrayList2 != null && arrayList2.size() > 0) {
                        nDAO_BASELAND_REGIONAL_FACTOR.delete(arrayList2, connection);
                        for (NVO_BASELAND_REGIONAL_FACTOR cloneable2 : arrayList2) {
                            nDAO_BASELAND_REGIONAL_FACTOR_STD.clearStdDataByItem(cloneable2.getCity(), cloneable2.getDist(), cloneable2.getYear(), cloneable2.getVersion(), cloneable2.getBaseno(), cloneable2.getItem(), connection);
                        }
                    }
                    ArrayList arrayList3 = new ArrayList();
                    HashMap<String, ArrayList<NVO_BASELAND_REGIONAL_FACTOR_STD>> hashMap = new HashMap<String, ArrayList<NVO_BASELAND_REGIONAL_FACTOR_STD>>();
                    for (NVO_BASELAND_REGIONAL_FACTOR nVO_BASELAND_REGIONAL_FACTOR : arrayList) {
                        Object object = nVO_BASELAND_REGIONAL_FACTOR.getCity();
                        String string = nVO_BASELAND_REGIONAL_FACTOR.getYear();
                        String string2 = nVO_BASELAND_REGIONAL_FACTOR.getDist();
                        String string3 = nVO_BASELAND_REGIONAL_FACTOR.getBaseno();
                        String string4 = nVO_BASELAND_REGIONAL_FACTOR.getVersion();
                        nVO_BASELAND_REGIONAL_FACTOR.setCity(systemCopyBean.getCopyAA45());
                        nVO_BASELAND_REGIONAL_FACTOR.setYear(systemCopyBean.getCopyYear());
                        nVO_BASELAND_REGIONAL_FACTOR.setDist(systemCopyBean.getCopyDist());
                        if (!StringProcess.isEmpty(systemCopyBean.getCopyLandNo())) {
                            nVO_BASELAND_REGIONAL_FACTOR.setBaseno(systemCopyBean.getCopyLandNo());
                        }
                        if (!StringProcess.isEmpty(systemCopyBean.getCopyVersion())) {
                            nVO_BASELAND_REGIONAL_FACTOR.setVersion(systemCopyBean.getCopyVersion());
                        }
                        if (StringProcess.isEmpty(systemCopyBean.getChooseLandNo()) && StringProcess.isEmpty(systemCopyBean.getCopyLandNo())) {
                            this.addBeCopiedData(systemCopyBean, connection, nDAO_BASELAND_REGIONAL_FACTOR_STD, arrayList3, hashMap, nVO_BASELAND_REGIONAL_FACTOR, (String)object, string, string2, string3, string4);
                            continue;
                        }
                        if (arrayList2 == null || arrayList2.size() == 0) {
                            this.addBeCopiedData(systemCopyBean, connection, nDAO_BASELAND_REGIONAL_FACTOR_STD, arrayList3, hashMap, nVO_BASELAND_REGIONAL_FACTOR, (String)object, string, string2, string3, string4);
                            continue;
                        }
                        boolean bl2 = false;
                        if (!StringProcess.isEmpty(systemCopyBean.getChooseLandNo()) && !StringProcess.isEmpty(systemCopyBean.getCopyLandNo())) {
                            this.addBeCopiedData(systemCopyBean, connection, nDAO_BASELAND_REGIONAL_FACTOR_STD, arrayList3, hashMap, nVO_BASELAND_REGIONAL_FACTOR, (String)object, string, string2, string3, string4);
                            continue;
                        }
                        for (NVO_BASELAND_REGIONAL_FACTOR nVO_BASELAND_REGIONAL_FACTOR2 : arrayList2) {
                            if (!nVO_BASELAND_REGIONAL_FACTOR.getYear().equals(nVO_BASELAND_REGIONAL_FACTOR2.getYear()) || !nVO_BASELAND_REGIONAL_FACTOR.getCity().equals(nVO_BASELAND_REGIONAL_FACTOR2.getCity()) || !nVO_BASELAND_REGIONAL_FACTOR.getDist().equals(nVO_BASELAND_REGIONAL_FACTOR2.getDist()) || !nVO_BASELAND_REGIONAL_FACTOR.getVersion().equals(nVO_BASELAND_REGIONAL_FACTOR2.getVersion()) || !nVO_BASELAND_REGIONAL_FACTOR.getItem().equals(nVO_BASELAND_REGIONAL_FACTOR2.getItem())) continue;
                            bl2 = true;
                        }
                        if (!bl2) continue;
                        this.addBeCopiedData(systemCopyBean, connection, nDAO_BASELAND_REGIONAL_FACTOR_STD, arrayList3, hashMap, nVO_BASELAND_REGIONAL_FACTOR, (String)object, string, string2, string3, string4);
                    }
                    ArrayList arrayList4 = new ArrayList();
                    for (Object object : hashMap.keySet()) {
                        arrayList4.addAll((Collection)hashMap.get(object));
                    }
                    nDAO_BASELAND_REGIONAL_FACTOR_STD.delete(arrayList4, connection);
                    nDAO_BASELAND_REGIONAL_FACTOR_STD.update2(arrayList4, connection);
                    if (arrayList3 != null && arrayList3.size() > 0) {
                        ArrayList<NVO_BASELAND_REGIONAL_FACTOR_STD> arrayList5 = nDAO_BASELAND_REGIONAL_FACTOR_STD.queryDeleteForCopyStd(systemCopyBean.getChooseAA45(), systemCopyBean.getChooseYear(), systemCopyBean.getChooseDist(), systemCopyBean.getChooseVersion(), systemCopyBean.getChooseLandNo(), false, connection);
                        for (NVO_BASELAND_REGIONAL_FACTOR_STD nVO_BASELAND_REGIONAL_FACTOR_STD : arrayList5) {
                            nVO_BASELAND_REGIONAL_FACTOR_STD.setCity(systemCopyBean.getCopyAA45());
                            nVO_BASELAND_REGIONAL_FACTOR_STD.setDist(systemCopyBean.getCopyDist());
                            nVO_BASELAND_REGIONAL_FACTOR_STD.setYear(systemCopyBean.getCopyYear());
                            if (!StringProcess.isEmpty(systemCopyBean.getCopyVersion())) {
                                nVO_BASELAND_REGIONAL_FACTOR_STD.setVersion(systemCopyBean.getCopyVersion());
                            }
                            if (StringProcess.isEmpty(systemCopyBean.getCopyLandNo())) continue;
                            nVO_BASELAND_REGIONAL_FACTOR_STD.setBaseno(systemCopyBean.getCopyLandNo());
                        }
                        nDAO_BASELAND_REGIONAL_FACTOR_STD.update2(arrayList5, connection);
                    }
                    nDAO_BASELAND_REGIONAL_FACTOR.update2(arrayList3, connection);
                    connection.commit();
                    break block20;
                }
                JavaFXUtil.showErrorMessageBox("\u67e5\u7121\u53ef\u8907\u88fd\u5167\u5bb9\uff0c\u8acb\u78ba\u8a8d\u9078\u64c7\u76ee\u6a19!", "");
                bl = false;
            }
            catch (Exception exception) {
                boolean bl3;
                try {
                    exception.getStackTrace();
                    ExceptionDialog.show(exception);
                    try {
                        connection.rollback();
                        connection.commit();
                    }
                    catch (SQLException sQLException) {
                        sQLException.printStackTrace();
                    }
                    bl3 = false;
                }
                catch (Throwable throwable) {
                    SqlUtil.close(connection);
                    throw throwable;
                }
                SqlUtil.close(connection);
                return bl3;
            }
            SqlUtil.close(connection);
            return bl;
        }
        SqlUtil.close(connection);
        return true;
    }

    private void addBeCopiedData(SystemCopyBean systemCopyBean, Connection connection, NDAO_BASELAND_REGIONAL_FACTOR_STD nDAO_BASELAND_REGIONAL_FACTOR_STD, ArrayList<NVO_BASELAND_REGIONAL_FACTOR> arrayList, HashMap<String, ArrayList<NVO_BASELAND_REGIONAL_FACTOR_STD>> hashMap, NVO_BASELAND_REGIONAL_FACTOR nVO_BASELAND_REGIONAL_FACTOR, String string, String string2, String string3, String string4, String string5) throws Exception {
        arrayList.add(nVO_BASELAND_REGIONAL_FACTOR);
        String string6 = string + string2 + string3 + string4 + string5;
        if (!hashMap.containsKey(string6)) {
            hashMap.put(string6, this.getBeCopiedData_forBaselandRegionalFactorSTD(systemCopyBean, nDAO_BASELAND_REGIONAL_FACTOR_STD, string, string2, string3, string5, string4, connection));
        }
    }

    private ArrayList<NVO_BASELAND_REGIONAL_FACTOR_STD> getBeCopiedData_forBaselandRegionalFactorSTD(SystemCopyBean systemCopyBean, NDAO_BASELAND_REGIONAL_FACTOR_STD nDAO_BASELAND_REGIONAL_FACTOR_STD, String string, String string2, String string3, String string4, String string5, Connection connection) throws Exception {
        ArrayList<NVO_BASELAND_REGIONAL_FACTOR_STD> arrayList = nDAO_BASELAND_REGIONAL_FACTOR_STD.queryDeleteForCopyStd(string, string2, string3, string4, string5, connection);
        if (arrayList != null) {
            for (NVO_BASELAND_REGIONAL_FACTOR_STD nVO_BASELAND_REGIONAL_FACTOR_STD : arrayList) {
                nVO_BASELAND_REGIONAL_FACTOR_STD.setCity(systemCopyBean.getCopyAA45());
                nVO_BASELAND_REGIONAL_FACTOR_STD.setYear(systemCopyBean.getCopyYear());
                nVO_BASELAND_REGIONAL_FACTOR_STD.setDist(systemCopyBean.getCopyDist());
                if (!StringProcess.isEmpty(systemCopyBean.getCopyLandNo())) {
                    nVO_BASELAND_REGIONAL_FACTOR_STD.setBaseno(systemCopyBean.getCopyLandNo());
                }
                if (StringProcess.isEmpty(systemCopyBean.getCopyVersion())) continue;
                nVO_BASELAND_REGIONAL_FACTOR_STD.setVersion(systemCopyBean.getCopyVersion());
            }
        }
        return arrayList;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean copyIndividualFactor(SystemCopyBean systemCopyBean) {
        Connection connection;
        block19: {
            boolean bl;
            connection = null;
            NDAO_BASELAND_INDIVIDUAL_FACTOR nDAO_BASELAND_INDIVIDUAL_FACTOR = new NDAO_BASELAND_INDIVIDUAL_FACTOR();
            NDAO_BASELAND_INDIVIDUAL_FACTOR_STD nDAO_BASELAND_INDIVIDUAL_FACTOR_STD = new NDAO_BASELAND_INDIVIDUAL_FACTOR_STD();
            try {
                connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
                ArrayList<NVO_BASELAND_INDIVIDUAL_FACTOR> arrayList = null;
                arrayList = StringProcess.isEmpty(systemCopyBean.getChooseLandNo()) ? nDAO_BASELAND_INDIVIDUAL_FACTOR.queryDataForCopy_excludeBaseno(systemCopyBean.getChooseAA45(), systemCopyBean.getChooseYear(), systemCopyBean.getChooseDist(), systemCopyBean.getChooseVersion(), systemCopyBean.getChooseLandNo(), connection) : (StringProcess.isEmpty(systemCopyBean.getCopyLandNo()) ? nDAO_BASELAND_INDIVIDUAL_FACTOR.queryDataForCopy_excludeBaseno(systemCopyBean.getChooseAA45(), systemCopyBean.getChooseYear(), systemCopyBean.getChooseDist(), systemCopyBean.getChooseVersion(), systemCopyBean.getChooseLandNo(), connection) : nDAO_BASELAND_INDIVIDUAL_FACTOR.queryDataForCopy(systemCopyBean.getChooseAA45(), systemCopyBean.getChooseYear(), systemCopyBean.getChooseDist(), systemCopyBean.getChooseVersion(), systemCopyBean.getChooseLandNo(), connection));
                if (arrayList != null && arrayList.size() > 0) {
                    ArrayList<NVO_BASELAND_INDIVIDUAL_FACTOR_STD> arrayList2;
                    connection.setAutoCommit(false);
                    ArrayList<NVO_BASELAND_INDIVIDUAL_FACTOR> arrayList3 = null;
                    arrayList3 = StringProcess.isEmpty(systemCopyBean.getCopyLandNo()) ? nDAO_BASELAND_INDIVIDUAL_FACTOR.queryDataForCopy_excludeBaseno(systemCopyBean.getCopyAA45(), systemCopyBean.getCopyYear(), systemCopyBean.getCopyDist(), systemCopyBean.getCopyVersion(), systemCopyBean.getCopyLandNo(), connection) : nDAO_BASELAND_INDIVIDUAL_FACTOR.queryDataForCopy(systemCopyBean.getCopyAA45(), systemCopyBean.getCopyYear(), systemCopyBean.getCopyDist(), systemCopyBean.getCopyVersion(), systemCopyBean.getCopyLandNo(), connection);
                    if (arrayList3 != null && arrayList3.size() > 0) {
                        nDAO_BASELAND_INDIVIDUAL_FACTOR.delete(arrayList3, connection);
                        for (NVO_BASELAND_INDIVIDUAL_FACTOR cloneable2 : arrayList3) {
                            nDAO_BASELAND_INDIVIDUAL_FACTOR_STD.clearStdDataByItem(cloneable2.getCity(), cloneable2.getDist(), cloneable2.getYear(), cloneable2.getVersion(), cloneable2.getBaseno(), cloneable2.getItem(), connection);
                        }
                    }
                    ArrayList arrayList4 = new ArrayList();
                    HashMap<String, ArrayList<NVO_BASELAND_INDIVIDUAL_FACTOR_STD>> hashMap = new HashMap<String, ArrayList<NVO_BASELAND_INDIVIDUAL_FACTOR_STD>>();
                    for (NVO_BASELAND_INDIVIDUAL_FACTOR nVO_BASELAND_INDIVIDUAL_FACTOR : arrayList) {
                        String string = nVO_BASELAND_INDIVIDUAL_FACTOR.getCity();
                        String string2 = nVO_BASELAND_INDIVIDUAL_FACTOR.getYear();
                        String string3 = nVO_BASELAND_INDIVIDUAL_FACTOR.getDist();
                        String string4 = nVO_BASELAND_INDIVIDUAL_FACTOR.getBaseno();
                        String string5 = nVO_BASELAND_INDIVIDUAL_FACTOR.getVersion();
                        nVO_BASELAND_INDIVIDUAL_FACTOR.setCity(systemCopyBean.getCopyAA45());
                        nVO_BASELAND_INDIVIDUAL_FACTOR.setYear(systemCopyBean.getCopyYear());
                        nVO_BASELAND_INDIVIDUAL_FACTOR.setDist(systemCopyBean.getCopyDist());
                        if (!StringProcess.isEmpty(systemCopyBean.getCopyLandNo())) {
                            nVO_BASELAND_INDIVIDUAL_FACTOR.setBaseno(systemCopyBean.getCopyLandNo());
                        }
                        if (!StringProcess.isEmpty(systemCopyBean.getCopyVersion())) {
                            nVO_BASELAND_INDIVIDUAL_FACTOR.setVersion(systemCopyBean.getCopyVersion());
                        }
                        if (StringProcess.isEmpty(systemCopyBean.getChooseLandNo()) && StringProcess.isEmpty(systemCopyBean.getCopyLandNo())) {
                            this.addBeCopiedData(systemCopyBean, connection, nDAO_BASELAND_INDIVIDUAL_FACTOR_STD, arrayList4, hashMap, nVO_BASELAND_INDIVIDUAL_FACTOR, string, string2, string3, string4, string5);
                            continue;
                        }
                        if (arrayList3 == null || arrayList3.size() == 0) {
                            this.addBeCopiedData(systemCopyBean, connection, nDAO_BASELAND_INDIVIDUAL_FACTOR_STD, arrayList4, hashMap, nVO_BASELAND_INDIVIDUAL_FACTOR, string, string2, string3, string4, string5);
                            continue;
                        }
                        boolean bl2 = false;
                        for (NVO_BASELAND_INDIVIDUAL_FACTOR nVO_BASELAND_INDIVIDUAL_FACTOR2 : arrayList3) {
                            if (!nVO_BASELAND_INDIVIDUAL_FACTOR.getYear().equals(nVO_BASELAND_INDIVIDUAL_FACTOR2.getYear()) || !nVO_BASELAND_INDIVIDUAL_FACTOR.getCity().equals(nVO_BASELAND_INDIVIDUAL_FACTOR2.getCity()) || !nVO_BASELAND_INDIVIDUAL_FACTOR.getDist().equals(nVO_BASELAND_INDIVIDUAL_FACTOR2.getDist()) || !nVO_BASELAND_INDIVIDUAL_FACTOR.getVersion().equals(nVO_BASELAND_INDIVIDUAL_FACTOR2.getVersion()) || !nVO_BASELAND_INDIVIDUAL_FACTOR.getItem().equals(nVO_BASELAND_INDIVIDUAL_FACTOR2.getItem())) continue;
                            bl2 = true;
                        }
                        if (!bl2) continue;
                        this.addBeCopiedData(systemCopyBean, connection, nDAO_BASELAND_INDIVIDUAL_FACTOR_STD, arrayList4, hashMap, nVO_BASELAND_INDIVIDUAL_FACTOR, string, string2, string3, string4, string5);
                    }
                    if (arrayList4 != null && arrayList4.size() > 0) {
                        arrayList2 = nDAO_BASELAND_INDIVIDUAL_FACTOR_STD.queryDeleteForCopyStd(systemCopyBean.getChooseAA45(), systemCopyBean.getChooseYear(), systemCopyBean.getChooseDist(), systemCopyBean.getChooseVersion(), systemCopyBean.getChooseLandNo(), false, connection);
                        for (NVO_BASELAND_INDIVIDUAL_FACTOR_STD nVO_BASELAND_INDIVIDUAL_FACTOR_STD : arrayList2) {
                            nVO_BASELAND_INDIVIDUAL_FACTOR_STD.setCity(systemCopyBean.getCopyAA45());
                            nVO_BASELAND_INDIVIDUAL_FACTOR_STD.setDist(systemCopyBean.getCopyDist());
                            nVO_BASELAND_INDIVIDUAL_FACTOR_STD.setYear(systemCopyBean.getCopyYear());
                            if (!StringProcess.isEmpty(systemCopyBean.getCopyVersion())) {
                                nVO_BASELAND_INDIVIDUAL_FACTOR_STD.setVersion(systemCopyBean.getCopyVersion());
                            }
                            if (StringProcess.isEmpty(systemCopyBean.getCopyLandNo())) continue;
                            nVO_BASELAND_INDIVIDUAL_FACTOR_STD.setBaseno(systemCopyBean.getCopyLandNo());
                        }
                        nDAO_BASELAND_INDIVIDUAL_FACTOR_STD.update2(arrayList2, connection);
                    }
                    arrayList2 = new ArrayList();
                    for (String string : hashMap.keySet()) {
                        arrayList2.addAll((Collection)hashMap.get(string));
                    }
                    nDAO_BASELAND_INDIVIDUAL_FACTOR_STD.delete((ArrayList<? extends VoBase>)arrayList2, connection);
                    nDAO_BASELAND_INDIVIDUAL_FACTOR_STD.update2(arrayList2, connection);
                    nDAO_BASELAND_INDIVIDUAL_FACTOR.update2(arrayList4, connection);
                    connection.commit();
                    break block19;
                }
                JavaFXUtil.showErrorMessageBox("\u67e5\u7121\u53ef\u8907\u88fd\u5167\u5bb9\uff0c\u8acb\u78ba\u8a8d\u9078\u64c7\u76ee\u6a19!", "");
                bl = false;
            }
            catch (Exception exception) {
                boolean bl3;
                try {
                    exception.getStackTrace();
                    ExceptionDialog.show(exception);
                    try {
                        connection.rollback();
                        connection.commit();
                    }
                    catch (SQLException sQLException) {
                        sQLException.printStackTrace();
                    }
                    bl3 = false;
                }
                catch (Throwable throwable) {
                    SqlUtil.close(connection);
                    throw throwable;
                }
                SqlUtil.close(connection);
                return bl3;
            }
            SqlUtil.close(connection);
            return bl;
        }
        SqlUtil.close(connection);
        return true;
    }

    private void addBeCopiedData(SystemCopyBean systemCopyBean, Connection connection, NDAO_BASELAND_INDIVIDUAL_FACTOR_STD nDAO_BASELAND_INDIVIDUAL_FACTOR_STD, ArrayList<NVO_BASELAND_INDIVIDUAL_FACTOR> arrayList, HashMap<String, ArrayList<NVO_BASELAND_INDIVIDUAL_FACTOR_STD>> hashMap, NVO_BASELAND_INDIVIDUAL_FACTOR nVO_BASELAND_INDIVIDUAL_FACTOR, String string, String string2, String string3, String string4, String string5) throws Exception {
        arrayList.add(nVO_BASELAND_INDIVIDUAL_FACTOR);
        String string6 = string + string2 + string3 + string4 + string5;
        if (!hashMap.containsKey(string6)) {
            hashMap.put(string6, this.getBeCopiedData_forBaselandIndivdualFactorSTD(systemCopyBean, nDAO_BASELAND_INDIVIDUAL_FACTOR_STD, string, string2, string3, string5, string4, connection));
        }
    }

    private ArrayList<NVO_BASELAND_INDIVIDUAL_FACTOR_STD> getBeCopiedData_forBaselandIndivdualFactorSTD(SystemCopyBean systemCopyBean, NDAO_BASELAND_INDIVIDUAL_FACTOR_STD nDAO_BASELAND_INDIVIDUAL_FACTOR_STD, String string, String string2, String string3, String string4, String string5, Connection connection) throws Exception {
        ArrayList<NVO_BASELAND_INDIVIDUAL_FACTOR_STD> arrayList = nDAO_BASELAND_INDIVIDUAL_FACTOR_STD.queryDeleteForCopyStd(string, string2, string3, string4, string5, connection);
        if (arrayList != null) {
            for (NVO_BASELAND_INDIVIDUAL_FACTOR_STD nVO_BASELAND_INDIVIDUAL_FACTOR_STD : arrayList) {
                nVO_BASELAND_INDIVIDUAL_FACTOR_STD.setCity(systemCopyBean.getCopyAA45());
                nVO_BASELAND_INDIVIDUAL_FACTOR_STD.setYear(systemCopyBean.getCopyYear());
                nVO_BASELAND_INDIVIDUAL_FACTOR_STD.setDist(systemCopyBean.getCopyDist());
                if (!StringProcess.isEmpty(systemCopyBean.getCopyLandNo())) {
                    nVO_BASELAND_INDIVIDUAL_FACTOR_STD.setBaseno(systemCopyBean.getCopyLandNo());
                }
                if (StringProcess.isEmpty(systemCopyBean.getCopyVersion())) continue;
                nVO_BASELAND_INDIVIDUAL_FACTOR_STD.setVersion(systemCopyBean.getCopyVersion());
            }
        }
        return arrayList;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean copyPricerate(SystemCopyBean systemCopyBean) {
        Connection connection;
        block9: {
            boolean bl;
            connection = null;
            NDAO_BASELAND_PRICERATE nDAO_BASELAND_PRICERATE = new NDAO_BASELAND_PRICERATE();
            try {
                connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
                ArrayList<NVO_BASELAND_PRICERATE> arrayList = nDAO_BASELAND_PRICERATE.queryDataForCopy(systemCopyBean.getChooseAA45(), systemCopyBean.getChooseYear(), systemCopyBean.getChooseDist(), systemCopyBean.getChooseVersion(), connection);
                if (arrayList != null && arrayList.size() > 0) {
                    connection.setAutoCommit(false);
                    ArrayList<NVO_BASELAND_PRICERATE> arrayList2 = nDAO_BASELAND_PRICERATE.queryDataForCopy(systemCopyBean.getCopyAA45(), systemCopyBean.getCopyYear(), systemCopyBean.getCopyDist(), systemCopyBean.getCopyVersion(), connection);
                    if (arrayList2 != null && arrayList2.size() > 0) {
                        nDAO_BASELAND_PRICERATE.delete(arrayList2, connection);
                    }
                    for (NVO_BASELAND_PRICERATE nVO_BASELAND_PRICERATE : arrayList) {
                        nVO_BASELAND_PRICERATE.setCity(systemCopyBean.getCopyAA45());
                        nVO_BASELAND_PRICERATE.setRate_type(systemCopyBean.getCopyYear());
                        nVO_BASELAND_PRICERATE.setDist(systemCopyBean.getCopyDist());
                        nVO_BASELAND_PRICERATE.setYear(systemCopyBean.getCopyVersion());
                    }
                    nDAO_BASELAND_PRICERATE.update2(arrayList, connection);
                    connection.commit();
                    break block9;
                }
                JavaFXUtil.showErrorMessageBox("\u67e5\u7121\u53ef\u8907\u88fd\u5167\u5bb9\uff0c\u8acb\u78ba\u8a8d\u9078\u64c7\u76ee\u6a19!", "");
                bl = false;
            }
            catch (Exception exception) {
                boolean bl2;
                try {
                    exception.getStackTrace();
                    ExceptionDialog.show(exception);
                    try {
                        connection.rollback();
                        connection.commit();
                    }
                    catch (SQLException sQLException) {
                        sQLException.printStackTrace();
                    }
                    bl2 = false;
                }
                catch (Throwable throwable) {
                    SqlUtil.close(connection);
                    throw throwable;
                }
                SqlUtil.close(connection);
                return bl2;
            }
            SqlUtil.close(connection);
            return bl;
        }
        SqlUtil.close(connection);
        return true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean copyInstru(SystemCopyBean systemCopyBean) {
        Connection connection;
        block9: {
            boolean bl;
            connection = null;
            NDAO_BASELAND_INSTRU_STD_PRICE nDAO_BASELAND_INSTRU_STD_PRICE = new NDAO_BASELAND_INSTRU_STD_PRICE();
            try {
                connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
                ArrayList<NVO_BASELAND_INSTRU_STD_PRICE> arrayList = nDAO_BASELAND_INSTRU_STD_PRICE.queryDataForCopy(systemCopyBean.getChooseAA45(), systemCopyBean.getChooseVersion(), connection);
                if (arrayList != null && arrayList.size() > 0) {
                    connection.setAutoCommit(false);
                    ArrayList<NVO_BASELAND_INSTRU_STD_PRICE> arrayList2 = nDAO_BASELAND_INSTRU_STD_PRICE.queryDataForCopy(systemCopyBean.getCopyAA45(), systemCopyBean.getCopyVersion(), connection);
                    if (arrayList2 != null && arrayList2.size() > 0) {
                        nDAO_BASELAND_INSTRU_STD_PRICE.delete(arrayList2, connection);
                    }
                    for (NVO_BASELAND_INSTRU_STD_PRICE nVO_BASELAND_INSTRU_STD_PRICE : arrayList) {
                        nVO_BASELAND_INSTRU_STD_PRICE.setCity(systemCopyBean.getCopyAA45());
                        if (StringProcess.isEmpty(systemCopyBean.getCopyVersion())) continue;
                        nVO_BASELAND_INSTRU_STD_PRICE.setInstru_code(systemCopyBean.getCopyVersion());
                    }
                    nDAO_BASELAND_INSTRU_STD_PRICE.update2(arrayList, connection);
                    connection.commit();
                    break block9;
                }
                JavaFXUtil.showErrorMessageBox("\u67e5\u7121\u53ef\u8907\u88fd\u5167\u5bb9\uff0c\u8acb\u78ba\u8a8d\u9078\u64c7\u76ee\u6a19!", "");
                bl = false;
            }
            catch (Exception exception) {
                boolean bl2;
                try {
                    exception.getStackTrace();
                    ExceptionDialog.show(exception);
                    try {
                        connection.rollback();
                        connection.commit();
                    }
                    catch (SQLException sQLException) {
                        sQLException.printStackTrace();
                    }
                    bl2 = false;
                }
                catch (Throwable throwable) {
                    SqlUtil.close(connection);
                    throw throwable;
                }
                SqlUtil.close(connection);
                return bl2;
            }
            SqlUtil.close(connection);
            return bl;
        }
        SqlUtil.close(connection);
        return true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean copyAhp(SystemCopyBean systemCopyBean) {
        Connection connection;
        block9: {
            boolean bl;
            connection = null;
            NDAO_BASELAND_AHP nDAO_BASELAND_AHP = new NDAO_BASELAND_AHP();
            try {
                connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
                ArrayList<NVO_BASELAND_AHP> arrayList = nDAO_BASELAND_AHP.queryDataForCopy(systemCopyBean.getChooseAA45(), systemCopyBean.getChooseYear(), connection);
                if (arrayList != null && arrayList.size() > 0) {
                    connection.setAutoCommit(false);
                    ArrayList<NVO_BASELAND_AHP> arrayList2 = nDAO_BASELAND_AHP.queryDataForCopy(systemCopyBean.getCopyAA45(), systemCopyBean.getCopyYear(), connection);
                    if (arrayList2 != null && arrayList2.size() > 0) {
                        nDAO_BASELAND_AHP.delete(arrayList2, connection);
                    }
                    for (NVO_BASELAND_AHP nVO_BASELAND_AHP : arrayList) {
                        nVO_BASELAND_AHP.setCity(systemCopyBean.getCopyAA45());
                        nVO_BASELAND_AHP.setYear(systemCopyBean.getCopyYear());
                    }
                    nDAO_BASELAND_AHP.update2(arrayList, connection);
                    connection.commit();
                    break block9;
                }
                JavaFXUtil.showErrorMessageBox("\u67e5\u7121\u53ef\u8907\u88fd\u5167\u5bb9\uff0c\u8acb\u78ba\u8a8d\u9078\u64c7\u76ee\u6a19!", "");
                bl = false;
            }
            catch (Exception exception) {
                boolean bl2;
                try {
                    exception.getStackTrace();
                    ExceptionDialog.show(exception);
                    try {
                        connection.rollback();
                        connection.commit();
                    }
                    catch (SQLException sQLException) {
                        sQLException.printStackTrace();
                    }
                    bl2 = false;
                }
                catch (Throwable throwable) {
                    SqlUtil.close(connection);
                    throw throwable;
                }
                SqlUtil.close(connection);
                return bl2;
            }
            SqlUtil.close(connection);
            return bl;
        }
        SqlUtil.close(connection);
        return true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean copyReportParam(SystemCopyBean systemCopyBean) {
        Connection connection;
        block9: {
            boolean bl;
            connection = null;
            NDAO_BASELAND_REPORT_PARAM nDAO_BASELAND_REPORT_PARAM = new NDAO_BASELAND_REPORT_PARAM();
            try {
                connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
                ArrayList<NVO_BASELAND_REPORT_PARAM> arrayList = nDAO_BASELAND_REPORT_PARAM.queryDataForCopy(systemCopyBean.getChooseAA45(), systemCopyBean.getChooseYear(), connection);
                if (arrayList != null && arrayList.size() > 0) {
                    connection.setAutoCommit(false);
                    ArrayList<NVO_BASELAND_REPORT_PARAM> arrayList2 = nDAO_BASELAND_REPORT_PARAM.queryDataForCopy(systemCopyBean.getCopyAA45(), systemCopyBean.getCopyYear(), connection);
                    if (arrayList2 != null && arrayList2.size() > 0) {
                        nDAO_BASELAND_REPORT_PARAM.delete(arrayList2, connection);
                    }
                    for (NVO_BASELAND_REPORT_PARAM nVO_BASELAND_REPORT_PARAM : arrayList) {
                        nVO_BASELAND_REPORT_PARAM.setCity(systemCopyBean.getCopyAA45());
                        nVO_BASELAND_REPORT_PARAM.setYear(systemCopyBean.getCopyYear());
                    }
                    nDAO_BASELAND_REPORT_PARAM.update2(arrayList, connection);
                    connection.commit();
                    break block9;
                }
                JavaFXUtil.showErrorMessageBox("\u67e5\u7121\u53ef\u8907\u88fd\u5167\u5bb9\uff0c\u8acb\u78ba\u8a8d\u9078\u64c7\u76ee\u6a19!", "");
                bl = false;
            }
            catch (Exception exception) {
                boolean bl2;
                try {
                    exception.getStackTrace();
                    ExceptionDialog.show(exception);
                    try {
                        connection.rollback();
                        connection.commit();
                    }
                    catch (SQLException sQLException) {
                        sQLException.printStackTrace();
                    }
                    bl2 = false;
                }
                catch (Throwable throwable) {
                    SqlUtil.close(connection);
                    throw throwable;
                }
                SqlUtil.close(connection);
                return bl2;
            }
            SqlUtil.close(connection);
            return bl;
        }
        SqlUtil.close(connection);
        return true;
    }
}

