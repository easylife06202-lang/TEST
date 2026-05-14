/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.baseland.system;

import com.wfusion.baseland.basic.Model;
import com.wfusion.fx.util.ExceptionDialog;
import com.wfusion.util.ConnectionFactory;
import com.wfusion.util.DateTime;
import com.wfusion.util.OptionPair;
import com.wfusion.util.SqlUtil;
import com.wfusion.util.StringProcess;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import moiland.baseland.buildprice.adjust.bo.BaseLandBuildPriceAdjustDataBo;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_BUILDPRICE_RATIO;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_REPORT_PARAM;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_BUILDPRICE_RATIO;
import moiland.baseland.util.bo.BaseLandOptionDataBo;

public class BaseLandBuildPriceAdjustModel
extends Model {
    TreeMap<String, HashMap<String, Object>> voValue = new TreeMap();
    String buildCoseBaseDate = null;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ArrayList<String> getBuildPriceAdjustYearList(String string) {
        Connection connection = null;
        ArrayList<String> arrayList = new ArrayList<String>();
        try {
            connection = ConnectionFactory.createConnection("sqlite", SQLITE_PATH + "BaseLand.db", "", "", "", "");
            ArrayList<String> arrayList2 = BaseLandOptionDataBo.getBuildPriceAdjustYearList(connection);
            int n = StringProcess.parserInt(string.substring(0, 3), 0);
            Iterator<String> iterator = arrayList2.iterator();
            while (iterator.hasNext()) {
                String string2 = iterator.next();
                if (n <= StringProcess.parserInt(string2, 0)) continue;
                iterator.remove();
            }
            arrayList.add("\u8acb\u9078\u64c7...");
            arrayList.addAll(new DateTime().getYearList(arrayList2, 0, 2, false, false));
            SqlUtil.close(connection);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
        return arrayList;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ArrayList<OptionPair> getBuild_cost_basedate() {
        Connection connection = null;
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        arrayList.add(new OptionPair("", "\u8acb\u9078\u64c7..."));
        try {
            connection = ConnectionFactory.createConnection("sqlite", SQLITE_PATH + "BaseLand.db", "", "", "", "");
            ArrayList<String> arrayList2 = new NDAO_BASELAND_REPORT_PARAM().getBuildCostBasedateList(connection);
            for (String string : arrayList2) {
                if (string.length() != 5) continue;
                arrayList.add(new OptionPair(string, string.substring(0, 3) + "\u5e74 " + string.substring(3, 5) + "\u6708"));
            }
            SqlUtil.close(connection);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
        return arrayList;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void query(String string, String string2) {
        Connection connection = null;
        try {
            ArrayList<Object> arrayList = new ArrayList();
            connection = ConnectionFactory.createConnection("sqlite", SQLITE_PATH + "BaseLand.db", "", "", "", "");
            BaseLandBuildPriceAdjustDataBo baseLandBuildPriceAdjustDataBo = new BaseLandBuildPriceAdjustDataBo(string, string2);
            arrayList = baseLandBuildPriceAdjustDataBo.getEditData(connection);
            this.voValue.clear();
            int n = StringProcess.parserInt(string);
            for (NVO_BASELAND_BUILDPRICE_RATIO nVO_BASELAND_BUILDPRICE_RATIO : arrayList) {
                int n2 = 0;
                HashMap<String, Object> hashMap = new HashMap<String, Object>();
                n2 = nVO_BASELAND_BUILDPRICE_RATIO.getYear().equals(String.valueOf(n)) ? StringProcess.parserInt(nVO_BASELAND_BUILDPRICE_RATIO.getMonth()) + 15 : (nVO_BASELAND_BUILDPRICE_RATIO.getYear().equals(String.valueOf(n - 1)) ? StringProcess.parserInt(nVO_BASELAND_BUILDPRICE_RATIO.getMonth()) + 3 : StringProcess.parserInt(nVO_BASELAND_BUILDPRICE_RATIO.getMonth()) - 9);
                if (n2 > 0) {
                    hashMap.putAll(nVO_BASELAND_BUILDPRICE_RATIO.getFieldToHashMapExport());
                    hashMap.put("tfRatio_" + n2, nVO_BASELAND_BUILDPRICE_RATIO.getRatio());
                    hashMap.put("lbDate_" + n2, nVO_BASELAND_BUILDPRICE_RATIO.getYear() + "\u5e74" + nVO_BASELAND_BUILDPRICE_RATIO.getMonth() + "\u6708");
                    hashMap.put("lbBaseDate", nVO_BASELAND_BUILDPRICE_RATIO.getBasedate().substring(0, 3) + " \u5e74 " + nVO_BASELAND_BUILDPRICE_RATIO.getBasedate().substring(3, 5) + " \u6708");
                }
                this.voValue.put(nVO_BASELAND_BUILDPRICE_RATIO.getYear() + nVO_BASELAND_BUILDPRICE_RATIO.getMonth(), hashMap);
            }
            SqlUtil.close(connection);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean save() {
        Connection connection = null;
        boolean bl = false;
        try {
            connection = this.getConnection();
            NDAO_BASELAND_BUILDPRICE_RATIO nDAO_BASELAND_BUILDPRICE_RATIO = new NDAO_BASELAND_BUILDPRICE_RATIO();
            ArrayList<NVO_BASELAND_BUILDPRICE_RATIO> arrayList = new ArrayList<NVO_BASELAND_BUILDPRICE_RATIO>();
            for (HashMap<String, Object> hashMap : this.voValue.values()) {
                NVO_BASELAND_BUILDPRICE_RATIO nVO_BASELAND_BUILDPRICE_RATIO = new NVO_BASELAND_BUILDPRICE_RATIO();
                nVO_BASELAND_BUILDPRICE_RATIO.setBeanByHashMap(hashMap, false);
                arrayList.add(nVO_BASELAND_BUILDPRICE_RATIO);
            }
            connection.setAutoCommit(false);
            nDAO_BASELAND_BUILDPRICE_RATIO.delete(arrayList, connection);
            nDAO_BASELAND_BUILDPRICE_RATIO.create(arrayList, connection);
            connection.commit();
            bl = true;
        }
        catch (Exception exception) {
            ExceptionDialog.show(exception);
            SqlUtil.rollback(connection);
        }
        finally {
            SqlUtil.close(connection);
        }
        return bl;
    }
}

