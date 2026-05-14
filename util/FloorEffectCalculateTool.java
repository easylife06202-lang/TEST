/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.util;

import com.wfusion.util.BigDecimalUtil;
import java.util.Map;
import java.util.TreeMap;
import moiland.baseland.flooreffect.bean.LandPriceAllocationCalculateBean;

public class FloorEffectCalculateTool {
    private Map<String, LandPriceAllocationCalculateBean> data = null;
    private double averageFloorEffectRatio = 0.0;
    private double averageLandPriceAllocationRatio = 0.0;

    public static Map<String, Object> calcaultLandPriceAllocationRatio(Map<String, LandPriceAllocationCalculateBean> map, String string, long l, long l2) {
        TreeMap<String, Object> treeMap = new TreeMap<String, Object>();
        FloorEffectCalculateTool floorEffectCalculateTool = new FloorEffectCalculateTool(map, string, l, l2);
        treeMap.put("averageFloorEffectRatio", floorEffectCalculateTool.averageFloorEffectRatio);
        treeMap.put("averageLandPriceAllocationRatio", floorEffectCalculateTool.averageLandPriceAllocationRatio);
        treeMap.put("data", floorEffectCalculateTool.data);
        return treeMap;
    }

    private FloorEffectCalculateTool(Map<String, LandPriceAllocationCalculateBean> map, String string, long l, long l2) {
        this.data = map;
        this.averageFloorEffectRatio = this.calculateAverageFloorEffectRatio(map, string);
        double d = FloorEffectCalculateTool.calculateBuildingRatio(l, l2);
        double d2 = FloorEffectCalculateTool.calculateBuildingEffectRatio(d, this.averageFloorEffectRatio);
        this.averageLandPriceAllocationRatio = FloorEffectCalculateTool.calculateAverageLandPriceAllocationRatio(map, d2);
    }

    private double calculateAverageFloorEffectRatio(Map<String, LandPriceAllocationCalculateBean> map, String string) {
        double d = 0.0;
        double d2 = 0.0;
        for (LandPriceAllocationCalculateBean landPriceAllocationCalculateBean : map.values()) {
            d = BigDecimalUtil.add(d, landPriceAllocationCalculateBean.getFloorEffectRatio());
        }
        if (map.size() > 0) {
            d2 = BigDecimalUtil.div(d, map.size());
        }
        return d2;
    }

    private static double calculateEstateTotalprice(long l, double d, double d2) {
        double d3 = 0.0;
        if (d2 != 0.0) {
            d3 = BigDecimalUtil.div(BigDecimalUtil.mul(l, d), d2);
        }
        return d3;
    }

    private static double calculateBuildingRatio(long l, double d) {
        double d2 = 0.0;
        if (d > 0.0) {
            d2 = BigDecimalUtil.div(l, d);
        }
        return d2;
    }

    private static double calculateBuildingEffectRatio(double d, double d2) {
        return BigDecimalUtil.mul(d, d2);
    }

    private static double calculateAverageLandPriceAllocationRatio(Map<String, LandPriceAllocationCalculateBean> map, double d) {
        double d2 = 0.0;
        double d3 = 0.0;
        for (LandPriceAllocationCalculateBean landPriceAllocationCalculateBean : map.values()) {
            double d4 = BigDecimalUtil.sub(landPriceAllocationCalculateBean.getFloorEffectRatio(), d);
            landPriceAllocationCalculateBean.setLandPriceAllocationRatio(d4);
            landPriceAllocationCalculateBean.setBuildingEffectRatio(d);
            d2 = BigDecimalUtil.add(d2, d4);
        }
        if (map.size() > 0) {
            d3 = BigDecimalUtil.div(d2, map.size());
        }
        return d3;
    }
}

