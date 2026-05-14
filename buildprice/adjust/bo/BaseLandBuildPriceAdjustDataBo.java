/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.buildprice.adjust.bo;

import com.wfusion.util.SqlUtil;
import com.wfusion.util.StringProcess;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.TreeMap;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_BUILDPRICE_RATIO;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_BUILDPRICE_RATIO;

public class BaseLandBuildPriceAdjustDataBo {
    private String year = "";
    private String buildCostBaseDate = "";
    private NDAO_BASELAND_BUILDPRICE_RATIO dao = new NDAO_BASELAND_BUILDPRICE_RATIO();

    public BaseLandBuildPriceAdjustDataBo(String string, String string2) {
        this.year = string;
        this.buildCostBaseDate = string2;
    }

    public String getBuildCostBaseFormatDate() {
        StringBuffer stringBuffer = new StringBuffer();
        if (this.buildCostBaseDate.length() == 5) {
            stringBuffer.append(this.buildCostBaseDate.substring(0, 3)).append("\u5e74").append(this.buildCostBaseDate.substring(3, 5)).append("\u6708");
        }
        return stringBuffer.toString();
    }

    public void saveData(ArrayList<NVO_BASELAND_BUILDPRICE_RATIO> arrayList, Connection connection) throws Exception {
        try {
            connection.setAutoCommit(false);
            this.dao.delete(arrayList, connection);
            this.dao.create(arrayList, connection);
            connection.commit();
        }
        catch (Exception exception) {
            SqlUtil.rollback(connection);
            exception.printStackTrace();
            throw exception;
        }
    }

    public ArrayList<NVO_BASELAND_BUILDPRICE_RATIO> getEditData(Connection connection) {
        TreeMap<String, NVO_BASELAND_BUILDPRICE_RATIO> treeMap = new TreeMap<String, NVO_BASELAND_BUILDPRICE_RATIO>();
        try {
            ArrayList<NVO_BASELAND_BUILDPRICE_RATIO> arrayList = this.dao.queryOnePreiodData(this.buildCostBaseDate, this.year, connection);
            for (NVO_BASELAND_BUILDPRICE_RATIO nVO_BASELAND_BUILDPRICE_RATIO : arrayList) {
                treeMap.put(nVO_BASELAND_BUILDPRICE_RATIO.getYear() + nVO_BASELAND_BUILDPRICE_RATIO.getMonth(), nVO_BASELAND_BUILDPRICE_RATIO);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return this.initOnePreiodData(treeMap, this.year);
    }

    private LinkedHashSet<String> getNormalYearMonth() {
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<String>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(5, 1);
        calendar.set(2, 2);
        calendar.set(1, StringProcess.parserInt(this.year) + 1911);
        calendar.set(2, calendar.get(2) - 18);
        for (int i = 0; i < 18; ++i) {
            calendar.set(2, calendar.get(2) + 1);
            String string = this.getTwYearMonth(calendar);
            linkedHashSet.add(string);
        }
        return linkedHashSet;
    }

    private ArrayList<NVO_BASELAND_BUILDPRICE_RATIO> initOnePreiodData(TreeMap<String, NVO_BASELAND_BUILDPRICE_RATIO> treeMap, String string) {
        NVO_BASELAND_BUILDPRICE_RATIO nVO_BASELAND_BUILDPRICE_RATIO;
        String string2;
        String string3;
        int n;
        String string4 = StringProcess.decrement(string, 3);
        String string5 = StringProcess.decrement(string4, 3);
        for (n = 10; n <= 12; ++n) {
            string3 = String.valueOf(n + 1000).substring(2, 4);
            string2 = string5 + string3;
            if (treeMap.containsKey(string2)) continue;
            nVO_BASELAND_BUILDPRICE_RATIO = this.createDataVo(string5, string3);
            treeMap.put(string2, nVO_BASELAND_BUILDPRICE_RATIO);
        }
        for (n = 1; n <= 12; ++n) {
            string3 = String.valueOf(n + 1000).substring(2, 4);
            string2 = string4 + string3;
            if (treeMap.containsKey(string2)) continue;
            nVO_BASELAND_BUILDPRICE_RATIO = this.createDataVo(string4, string3);
            treeMap.put(string2, nVO_BASELAND_BUILDPRICE_RATIO);
        }
        for (n = 1; n <= 3; ++n) {
            string3 = String.valueOf(n + 1000).substring(2, 4);
            string2 = string + string3;
            if (treeMap.containsKey(string2)) continue;
            nVO_BASELAND_BUILDPRICE_RATIO = this.createDataVo(string, string3);
            treeMap.put(string2, nVO_BASELAND_BUILDPRICE_RATIO);
        }
        ArrayList<NVO_BASELAND_BUILDPRICE_RATIO> arrayList = new ArrayList<NVO_BASELAND_BUILDPRICE_RATIO>();
        arrayList.addAll(treeMap.values());
        return arrayList;
    }

    private String getTwYearMonth(Calendar calendar) {
        StringBuffer stringBuffer = new StringBuffer();
        int n = calendar.get(1) - 1911;
        if (n < 10) {
            stringBuffer.append("0");
        }
        if (n < 100) {
            stringBuffer.append("0");
        }
        stringBuffer.append(n);
        int n2 = calendar.get(2) + 1;
        if (n2 < 10) {
            stringBuffer.append("0");
        }
        stringBuffer.append(n2);
        return stringBuffer.toString();
    }

    private NVO_BASELAND_BUILDPRICE_RATIO createDataVo(String string, String string2) {
        NVO_BASELAND_BUILDPRICE_RATIO nVO_BASELAND_BUILDPRICE_RATIO = new NVO_BASELAND_BUILDPRICE_RATIO();
        nVO_BASELAND_BUILDPRICE_RATIO.setBasedate(this.buildCostBaseDate);
        nVO_BASELAND_BUILDPRICE_RATIO.setYear(string);
        nVO_BASELAND_BUILDPRICE_RATIO.setMonth(string2);
        nVO_BASELAND_BUILDPRICE_RATIO.setRatio(0.0);
        return nVO_BASELAND_BUILDPRICE_RATIO;
    }

    public static NVO_BASELAND_BUILDPRICE_RATIO getRatioData(String string, String string2, String string3, Connection connection) {
        NVO_BASELAND_BUILDPRICE_RATIO nVO_BASELAND_BUILDPRICE_RATIO = null;
        try {
            nVO_BASELAND_BUILDPRICE_RATIO = new NDAO_BASELAND_BUILDPRICE_RATIO().findByPk(string, string2, string3, connection);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        if (nVO_BASELAND_BUILDPRICE_RATIO == null) {
            nVO_BASELAND_BUILDPRICE_RATIO = new NVO_BASELAND_BUILDPRICE_RATIO();
            nVO_BASELAND_BUILDPRICE_RATIO.setBasedate(string);
            nVO_BASELAND_BUILDPRICE_RATIO.setYear(string2);
            nVO_BASELAND_BUILDPRICE_RATIO.setMonth(string3);
        }
        return nVO_BASELAND_BUILDPRICE_RATIO;
    }
}

