/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.util;

import com.wfusion.util.BigDecimalUtil;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.TreeMap;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_INSTRU_STD_PRICE;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_INSTRU;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_INSTRU_STD_PRICE;
import moiland.baseland.instru.bean.BaseLandInstruStdPriceBean;
import moiland.baseland.instru.bo.BaseLandInstruCodeBo;
import moiland.baseland.util.BaseLandCode;

public class BaseLandInstruStdPriceTool {
    private String city = "";
    public static final int MIN_FLOOR = 1;
    public static final int MAX_FLOOR = 999;
    private static final double SQUARE_METER_TO_LEVEL_GROUND_FOR_DIVISOR = 0.3025;
    private static final String SQUARE_METER = "SquareMeter";
    private static final String LEVEL_GROUND = "LevelGround";
    private TreeMap<String, NVO_BASELAND_INSTRU> instruCodeMap = null;
    private TreeMap<String, TreeMap<Integer, BaseLandInstruStdPriceBean>> stdPriceMap = null;

    public BaseLandInstruStdPriceTool(String string, Connection connection) {
        this.city = string;
        this.refreshCodeMap(connection);
        this.refreshStdPriceMap(connection);
    }

    private void refreshCodeMap(Connection connection) {
        this.instruCodeMap = new TreeMap();
        ArrayList<NVO_BASELAND_INSTRU> arrayList = new BaseLandInstruCodeBo().getAllData(connection);
        for (NVO_BASELAND_INSTRU nVO_BASELAND_INSTRU : arrayList) {
            this.instruCodeMap.put(nVO_BASELAND_INSTRU.getInstru_code(), nVO_BASELAND_INSTRU);
        }
    }

    private void refreshStdPriceMap(Connection connection) {
        this.stdPriceMap = new TreeMap();
        try {
            ArrayList<NVO_BASELAND_INSTRU_STD_PRICE> arrayList = new NDAO_BASELAND_INSTRU_STD_PRICE().queryAllInstruStdPriceDataByCity(this.city, connection);
            for (NVO_BASELAND_INSTRU_STD_PRICE nVO_BASELAND_INSTRU_STD_PRICE : arrayList) {
                TreeMap<Integer, BaseLandInstruStdPriceBean> treeMap;
                String string = nVO_BASELAND_INSTRU_STD_PRICE.getCity();
                String string2 = nVO_BASELAND_INSTRU_STD_PRICE.getInstru_code();
                String string3 = string + "@" + string2;
                int n = nVO_BASELAND_INSTRU_STD_PRICE.getFloor_start();
                int n2 = nVO_BASELAND_INSTRU_STD_PRICE.getFloor_end();
                int n3 = nVO_BASELAND_INSTRU_STD_PRICE.getUniprice();
                if (!this.stdPriceMap.containsKey(string3)) {
                    treeMap = new TreeMap();
                    this.setValueBelowFloor1(string, string2, 1, n, n3, treeMap);
                    this.stdPriceMap.put(string3, treeMap);
                }
                treeMap = this.stdPriceMap.get(string3);
                this.setValueBelowFloor1(string, string2, n, n2, n3, treeMap);
            }
            this.fillTheLack();
        }
        catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("\u53d6\u4e0d\u5230\u571f\u958b\u6cd5-\u5efa\u7269\u6a19\u6e96\u55ae\u50f9\u7684\u8cc7\u6599\uff01");
        }
    }

    private void fillTheLack() {
        for (String string : this.stdPriceMap.keySet()) {
            String[] stringArray = string.split("@");
            String string2 = stringArray[0];
            String string3 = stringArray[1];
            TreeMap<Integer, BaseLandInstruStdPriceBean> treeMap = this.stdPriceMap.get(string);
            BaseLandInstruStdPriceBean baseLandInstruStdPriceBean = null;
            for (int i = 1; i <= 999; ++i) {
                if (treeMap.containsKey(i)) {
                    baseLandInstruStdPriceBean = treeMap.get(i);
                    continue;
                }
                if (baseLandInstruStdPriceBean != null) {
                    int n = baseLandInstruStdPriceBean.getPrice();
                    BaseLandInstruStdPriceBean baseLandInstruStdPriceBean2 = new BaseLandInstruStdPriceBean(string2, string3, i, n);
                    treeMap.put(i, baseLandInstruStdPriceBean2);
                    continue;
                }
                System.out.println("\u627e\u4e0d\u5230\u53ef\u4ee5\u53c3\u8003\u7684\u8a2d\u5b9a\u503c(\u524d\u4e00\u7b46)\uff01 city=" + string2 + ", instru_code=" + string3 + ", floor=" + i);
            }
        }
    }

    private void setValueBelowFloor1(String string, String string2, int n, int n2, int n3, TreeMap<Integer, BaseLandInstruStdPriceBean> treeMap) {
        for (int i = n; i <= n2; ++i) {
            BaseLandInstruStdPriceBean baseLandInstruStdPriceBean = new BaseLandInstruStdPriceBean(string, string2, i, n3);
            baseLandInstruStdPriceBean.setInstruName(BaseLandCode.decodeInstruCode(string2));
            treeMap.put(i, baseLandInstruStdPriceBean);
        }
    }

    public double getResidualRate(String string) {
        double d = 0.0;
        if (this.instruCodeMap.containsKey(string)) {
            NVO_BASELAND_INSTRU nVO_BASELAND_INSTRU = this.instruCodeMap.get(string);
            d = nVO_BASELAND_INSTRU.getResidual_rate();
        }
        return d;
    }

    public int getStartdardPrice(String string, String string2, int n) {
        int n2 = 0;
        BaseLandInstruStdPriceBean baseLandInstruStdPriceBean = this.getStartdardPriceBean(string, string2, n);
        if (baseLandInstruStdPriceBean != null && baseLandInstruStdPriceBean.getCity().equals(string)) {
            n2 = baseLandInstruStdPriceBean.getPrice();
        } else {
            System.out.println("getStartdardPrice \u627e\u4e0d\u5230\u5efa\u7269\u6a19\u6e96\u55ae\u50f9\u7684\u8a2d\u5b9a city=" + string + ", instruCode=" + string2 + ", floor=" + n + ", stdPrice=" + n2);
        }
        return n2;
    }

    private BaseLandInstruStdPriceBean getStartdardPriceBean(String string, String string2, int n) {
        BaseLandInstruStdPriceBean baseLandInstruStdPriceBean = null;
        String string3 = string + "@" + string2;
        if (this.stdPriceMap.containsKey(string3)) {
            TreeMap<Integer, BaseLandInstruStdPriceBean> treeMap = this.stdPriceMap.get(string3);
            baseLandInstruStdPriceBean = treeMap.get(n);
        }
        return baseLandInstruStdPriceBean;
    }

    public static String changeUnit(String string, ArrayList<NVO_BASELAND_INSTRU_STD_PRICE> arrayList) {
        String string2 = "";
        if (LEVEL_GROUND.equals(string)) {
            string2 = SQUARE_METER;
            BaseLandInstruStdPriceTool.changeUnitToSquareMeter(arrayList);
        } else {
            string2 = LEVEL_GROUND;
            BaseLandInstruStdPriceTool.changeUnitToLevelGround(arrayList);
        }
        return string2;
    }

    private static void changeUnitToLevelGround(ArrayList<NVO_BASELAND_INSTRU_STD_PRICE> arrayList) {
        for (NVO_BASELAND_INSTRU_STD_PRICE nVO_BASELAND_INSTRU_STD_PRICE : arrayList) {
            double d = nVO_BASELAND_INSTRU_STD_PRICE.getUniprice();
            d = BigDecimalUtil.round(BigDecimalUtil.div(d, 0.3025), 0);
            nVO_BASELAND_INSTRU_STD_PRICE.setUniprice((int)d);
        }
    }

    private static void changeUnitToSquareMeter(ArrayList<NVO_BASELAND_INSTRU_STD_PRICE> arrayList) {
        for (NVO_BASELAND_INSTRU_STD_PRICE nVO_BASELAND_INSTRU_STD_PRICE : arrayList) {
            double d = nVO_BASELAND_INSTRU_STD_PRICE.getUniprice();
            d = BigDecimalUtil.round(BigDecimalUtil.mul(d, 0.3025), 0);
            nVO_BASELAND_INSTRU_STD_PRICE.setUniprice((int)d);
        }
    }
}

