/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.pricerate.bo;

import com.wfusion.util.BigDecimalUtil;
import com.wfusion.util.SqlUtil;
import com.wfusion.util.StringProcess;
import java.sql.Connection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.TreeMap;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_PRICERATE;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_PRICERATE;
import moiland.baseland.pricerate.formbean.BaseLandPriceRateFormBean;

public class BaseLandPriceRateDataBo {
    private static final String NORMAL = "Normal";
    private static final String ADDITIONAL = "Additional";
    private static final String BASE = "Base";
    private BaseLandPriceRateFormBean formBean = null;
    private NDAO_BASELAND_PRICERATE dao = null;
    private String mode = "query";

    public BaseLandPriceRateDataBo(BaseLandPriceRateFormBean baseLandPriceRateFormBean) {
        this.formBean = baseLandPriceRateFormBean;
        this.dao = new NDAO_BASELAND_PRICERATE();
    }

    public TreeMap<String, ArrayList<NVO_BASELAND_PRICERATE>> getOneSubTypeData(Connection connection) {
        TreeMap<String, ArrayList<NVO_BASELAND_PRICERATE>> treeMap = new TreeMap<String, ArrayList<NVO_BASELAND_PRICERATE>>();
        treeMap.put(NORMAL, new ArrayList());
        treeMap.put(ADDITIONAL, new ArrayList());
        treeMap.put(BASE, new ArrayList());
        ArrayList<Object> arrayList = new ArrayList();
        String string = this.formBean.getYear() + "03";
        try {
            arrayList = this.dao.queryOneSubTypeData(this.formBean.getCity(), this.formBean.getRateType(), this.formBean.getDist(), this.formBean.getYear(), connection);
            if (arrayList.size() == 0) {
                this.mode = "add";
                arrayList = this.initOneSubTypeData();
            }
            LinkedHashSet<String> linkedHashSet = this.getNormalYearMonth();
            for (NVO_BASELAND_PRICERATE nVO_BASELAND_PRICERATE : arrayList) {
                if (linkedHashSet.contains(nVO_BASELAND_PRICERATE.getYm())) {
                    treeMap.get(NORMAL).add(nVO_BASELAND_PRICERATE);
                    if (!string.equals(nVO_BASELAND_PRICERATE.getYm())) continue;
                    treeMap.get(BASE).add(nVO_BASELAND_PRICERATE);
                    continue;
                }
                treeMap.get(ADDITIONAL).add(nVO_BASELAND_PRICERATE);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        this.converToDifferentRate(treeMap);
        return treeMap;
    }

    private void converToDifferentRate(TreeMap<String, ArrayList<NVO_BASELAND_PRICERATE>> treeMap) {
        double d = 100.0;
        if (treeMap.containsKey(BASE) && treeMap.get(BASE).size() > 0) {
            NVO_BASELAND_PRICERATE nVO_BASELAND_PRICERATE = treeMap.get(BASE).get(0);
            d = nVO_BASELAND_PRICERATE.getIndex_rate();
        }
        treeMap.remove(BASE);
        for (String string : treeMap.keySet()) {
            for (NVO_BASELAND_PRICERATE nVO_BASELAND_PRICERATE : treeMap.get(string)) {
                nVO_BASELAND_PRICERATE.setIndex_rate(BigDecimalUtil.round(d - nVO_BASELAND_PRICERATE.getIndex_rate(), 2));
            }
        }
    }

    public String getMode() {
        return this.mode;
    }

    public void saveData(ArrayList<NVO_BASELAND_PRICERATE> arrayList, Connection connection) throws Exception {
        try {
            connection.setAutoCommit(false);
            this.dao.clearOneSubTypeData(this.formBean.getCity(), this.formBean.getRateType(), this.formBean.getDist(), this.formBean.getYear(), connection);
            this.dao.create(arrayList, connection);
            connection.commit();
        }
        catch (Exception exception) {
            SqlUtil.rollback(connection);
            throw exception;
        }
    }

    public void deleteData(Connection connection) throws Exception {
        try {
            connection.setAutoCommit(false);
            this.dao.clearOneSubTypeData(this.formBean.getCity(), this.formBean.getRateType(), this.formBean.getDist(), this.formBean.getYear(), connection);
            connection.commit();
        }
        catch (Exception exception) {
            SqlUtil.rollback(connection);
            throw exception;
        }
    }

    private LinkedHashSet<String> getNormalYearMonth() {
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<String>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(5, 1);
        calendar.set(2, 2);
        calendar.set(1, StringProcess.parserInt(this.formBean.getYear()) + 1911);
        calendar.set(2, calendar.get(2) - 18);
        for (int i = 0; i < 18; ++i) {
            calendar.set(2, calendar.get(2) + 1);
            String string = this.getTwYearMonth(calendar);
            linkedHashSet.add(string);
        }
        return linkedHashSet;
    }

    private ArrayList<NVO_BASELAND_PRICERATE> initOneSubTypeData() throws ParseException {
        ArrayList<NVO_BASELAND_PRICERATE> arrayList = new ArrayList<NVO_BASELAND_PRICERATE>();
        for (String string : this.getNormalYearMonth()) {
            NVO_BASELAND_PRICERATE nVO_BASELAND_PRICERATE = this.createDataVo(string);
            arrayList.add(nVO_BASELAND_PRICERATE);
        }
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

    private NVO_BASELAND_PRICERATE createDataVo(String string) {
        NVO_BASELAND_PRICERATE nVO_BASELAND_PRICERATE = new NVO_BASELAND_PRICERATE();
        nVO_BASELAND_PRICERATE.setCity(this.formBean.getCity());
        nVO_BASELAND_PRICERATE.setRate_type(this.formBean.getRateType());
        nVO_BASELAND_PRICERATE.setDist(this.formBean.getDist());
        nVO_BASELAND_PRICERATE.setYear(this.formBean.getYear());
        nVO_BASELAND_PRICERATE.setYm(string);
        nVO_BASELAND_PRICERATE.setIndex_rate(100.0);
        return nVO_BASELAND_PRICERATE;
    }
}

