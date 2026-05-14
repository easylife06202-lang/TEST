/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.bo;

import com.wfusion.util.BigDecimalUtil;
import com.wfusion.util.StringProcess;
import java.text.DecimalFormat;
import moiland.baseland.action.bean.BaseLandDevelopParamBean;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_DEVELOP;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_DEVELOP_EXT;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_MAIN;

public class AutoCalBaseLandDevelopBo {
    private static final double SQUARE_METER_TO_LEVEL_GROUND = 0.3025;
    private static final double LEVEL_GROUND_TO_SQUARE_METER = 3.3058;
    public static final double HUNDRED_PERCENT = 100.0;
    private static final int SCALE_0 = 0;
    private static final int SCALE_2 = 2;
    private BaseLandDevelopParamBean param = null;
    private DecimalFormat df = new DecimalFormat("###,###.##########");

    public AutoCalBaseLandDevelopBo(BaseLandDevelopParamBean baseLandDevelopParamBean) {
        this.param = baseLandDevelopParamBean;
    }

    public void calculateData() {
        NVO_BASELAND_MAIN nVO_BASELAND_MAIN = this.param.getVoMain();
        NVO_BASELAND_DEVELOP nVO_BASELAND_DEVELOP = this.param.getVoDevelop();
        NVO_BASELAND_DEVELOP_EXT nVO_BASELAND_DEVELOP_EXT = this.param.getVoDevelopExt();
        boolean bl = StringProcess.parserBoolean(nVO_BASELAND_DEVELOP.getIs_merge());
        if (bl) {
            nVO_BASELAND_MAIN.setLand_position(nVO_BASELAND_DEVELOP_EXT.getLand_position_pseudo());
            nVO_BASELAND_MAIN.setAa10(nVO_BASELAND_DEVELOP_EXT.getArea_pseudo());
            nVO_BASELAND_MAIN.setLanduse(nVO_BASELAND_DEVELOP_EXT.getLanduse_pseudo());
            nVO_BASELAND_MAIN.setCov_ratio(nVO_BASELAND_DEVELOP_EXT.getCov_ratio_pseudo());
            nVO_BASELAND_MAIN.setAre_ratio(nVO_BASELAND_DEVELOP_EXT.getAre_ratio_pseudo());
        }
        if (nVO_BASELAND_DEVELOP_EXT != null) {
            nVO_BASELAND_DEVELOP_EXT.setMerge_rate_ext(AutoCalBaseLandDevelopBo.round(this.calculateMergeRateExt(), 2));
        }
        nVO_BASELAND_MAIN.setAa10_ping(AutoCalBaseLandDevelopBo.round(AutoCalBaseLandDevelopBo.converToLevelGround(nVO_BASELAND_MAIN.getAa10()), 2));
        nVO_BASELAND_DEVELOP.setAre_area(AutoCalBaseLandDevelopBo.round(this.calculateMainAreArea(), 2));
        nVO_BASELAND_DEVELOP.setAre_area_ping(AutoCalBaseLandDevelopBo.round(AutoCalBaseLandDevelopBo.converToLevelGround(this.calculateMainAreArea()), 2));
        if (nVO_BASELAND_DEVELOP.getSale_are_type().equals("1")) {
            nVO_BASELAND_DEVELOP.setFloor1_area(AutoCalBaseLandDevelopBo.round(this.calculateFloor1Area(), 2));
            nVO_BASELAND_DEVELOP.setFloor2_area(AutoCalBaseLandDevelopBo.round(this.calculateFloor2Area(), 2));
            nVO_BASELAND_DEVELOP.setRf_area(AutoCalBaseLandDevelopBo.round(this.calculateRoofArea(), 2));
            nVO_BASELAND_DEVELOP.setOther_area(AutoCalBaseLandDevelopBo.round(this.calculateOtherArea(), 2));
        }
        nVO_BASELAND_DEVELOP.setPark_area(AutoCalBaseLandDevelopBo.round(this.calculateParkArea(), 2));
        nVO_BASELAND_DEVELOP.setPark_cnt((int)AutoCalBaseLandDevelopBo.roundDown(this.calculateParkingSpace(), 0));
        nVO_BASELAND_DEVELOP.setSale_area(AutoCalBaseLandDevelopBo.round(this.calculateSaleArea(), 2));
        if (!nVO_BASELAND_DEVELOP.getSale_are_type().equals("1")) {
            nVO_BASELAND_DEVELOP.setSale_are_ratio(AutoCalBaseLandDevelopBo.round(this.calculateSaleAreRatio(), 2));
        }
        nVO_BASELAND_DEVELOP.setFloor1_value((long)AutoCalBaseLandDevelopBo.round(this.calculateFloor1Value(), 0));
        nVO_BASELAND_DEVELOP.setFloor2_value((long)AutoCalBaseLandDevelopBo.round(this.calculateFloor2Value(), 0));
        nVO_BASELAND_DEVELOP.setRf_uprice((int)AutoCalBaseLandDevelopBo.round(this.calculateRfUprice(), 0));
        nVO_BASELAND_DEVELOP.setRf_value((long)AutoCalBaseLandDevelopBo.round(this.calculateRfValue(), 0));
        nVO_BASELAND_DEVELOP.setOther_value((long)AutoCalBaseLandDevelopBo.round(this.calculateOtherValue(), 0));
        nVO_BASELAND_DEVELOP.setPark_value((long)AutoCalBaseLandDevelopBo.round(this.calculateParkValue(), 0));
        nVO_BASELAND_DEVELOP.setSale_value((long)AutoCalBaseLandDevelopBo.round(this.calculateSaleValue(), 0));
        nVO_BASELAND_DEVELOP.setBuild_cost_adjust((int)AutoCalBaseLandDevelopBo.round(this.calculateBuildCostAdjust(), 0));
        nVO_BASELAND_DEVELOP.setBuild_cost_adjust_ping((int)AutoCalBaseLandDevelopBo.round(AutoCalBaseLandDevelopBo.converToSquareMeter(this.calculateBuildCostAdjust()), 0));
        nVO_BASELAND_DEVELOP.setDirect_cost((long)AutoCalBaseLandDevelopBo.round(this.calculateDirectCost(), 0));
        nVO_BASELAND_DEVELOP.setDesign_cost((long)AutoCalBaseLandDevelopBo.round(this.calculateDesignCost(), 0));
        nVO_BASELAND_DEVELOP.setAd_cost((long)AutoCalBaseLandDevelopBo.round(this.calculateAdCost(), 0));
        nVO_BASELAND_DEVELOP.setManage_cost((long)AutoCalBaseLandDevelopBo.round(this.calculateManageCost(), 0));
        nVO_BASELAND_DEVELOP.setTax_cost((long)AutoCalBaseLandDevelopBo.round(this.calculateTaxCost(), 0));
        nVO_BASELAND_DEVELOP.setIndir_ratio(AutoCalBaseLandDevelopBo.round(this.calculateIndirectRatio(), 2));
        nVO_BASELAND_DEVELOP.setIndir_cost((long)AutoCalBaseLandDevelopBo.round(this.calculateIndirectCost(), 0));
        nVO_BASELAND_DEVELOP.setYear_rate(AutoCalBaseLandDevelopBo.round(this.calculateYear_rate(), 2));
        nVO_BASELAND_DEVELOP.setBuild_value((long)AutoCalBaseLandDevelopBo.round(this.calculateBuildValue(), 0));
        nVO_BASELAND_DEVELOP.setFunds_ratio(AutoCalBaseLandDevelopBo.round(this.calculateFundsRate(), 2));
        nVO_BASELAND_DEVELOP.setSum_rate(AutoCalBaseLandDevelopBo.round(this.calculateSumRate(), 2));
        nVO_BASELAND_DEVELOP.setLand_value((long)AutoCalBaseLandDevelopBo.round(this.calculateLandValue(), 0));
        nVO_BASELAND_DEVELOP.setTotal_value((long)AutoCalBaseLandDevelopBo.round(this.calculateTotalValue(), 0));
        nVO_BASELAND_DEVELOP.setBuild_ratio(AutoCalBaseLandDevelopBo.round(this.calculateBuildRatio(), 2));
        nVO_BASELAND_DEVELOP.setLand_ratio(AutoCalBaseLandDevelopBo.round(this.calculateLandRatio(), 2));
        nVO_BASELAND_DEVELOP.setTotal_ratio(AutoCalBaseLandDevelopBo.round(this.calculateTotalRatio(), 2));
        nVO_BASELAND_DEVELOP.setLand_unit_price((int)AutoCalBaseLandDevelopBo.round(this.calculateLandUnitPrice(bl), 0));
        nVO_BASELAND_DEVELOP.setLand_unit_price_ping((int)StringProcess.roundCd(AutoCalBaseLandDevelopBo.converToSquareMeter(this.calculateLandUnitPrice(bl))));
    }

    private double calculateMainAreArea() {
        double d = BigDecimalUtil.div(BigDecimalUtil.mul(this.param.getVoMain().getAa10(), this.param.getVoMain().getAre_ratio()), 100.0);
        return d;
    }

    private double calculateFloor1Area() {
        double d = this.param.getVoDevelop().getSale_electric();
        double d2 = this.param.getVoDevelop().getSale_balcony();
        double d3 = this.param.getVoDevelop().getAre_area_ping();
        if (this.param.getVoDevelop().getFloor_up() > 0) {
            d3 = BigDecimalUtil.div(d3, this.param.getVoDevelop().getFloor_up());
            d3 = BigDecimalUtil.mul(d3, d);
            d3 = BigDecimalUtil.mul(d3, d2);
        }
        return d3;
    }

    private double calculateFloor2Area() {
        double d = this.param.getVoDevelop().getSale_electric();
        double d2 = this.param.getVoDevelop().getSale_balcony();
        double d3 = this.param.getVoDevelop().getAre_area_ping();
        if (this.param.getVoDevelop().getFloor_up() > 0) {
            d3 = BigDecimalUtil.div(d3, this.param.getVoDevelop().getFloor_up());
            d3 = BigDecimalUtil.mul(d3, this.param.getVoDevelop().getFloor_up() - 1);
            d3 = BigDecimalUtil.mul(d3, d);
            d3 = BigDecimalUtil.mul(d3, d2);
        }
        return d3;
    }

    private double calculateRoofArea() {
        double d = Integer.valueOf(this.param.getVoDevelop().getSale_protrusionm()).doubleValue();
        double d2 = Integer.valueOf(this.param.getVoDevelop().getSale_protrusionc()).doubleValue();
        double d3 = this.param.getVoDevelop().getAre_area_ping();
        if (this.param.getVoDevelop().getFloor_up() > 0 && d > 0.0) {
            d3 = BigDecimalUtil.div(d3, this.param.getVoDevelop().getFloor_up());
            d3 = BigDecimalUtil.div(d3, d);
            d3 = BigDecimalUtil.mul(d3, d2);
        }
        return d3;
    }

    private double calculateOtherArea() {
        double d = this.param.getVoDevelop().getAre_area_ping();
        d = BigDecimalUtil.mul(d, this.param.getVoDevelop().getSale_are_ratio());
        d = BigDecimalUtil.sub(d, this.param.getVoDevelop().getFloor1_area());
        d = BigDecimalUtil.sub(d, this.param.getVoDevelop().getFloor2_area());
        d = BigDecimalUtil.sub(d, this.param.getVoDevelop().getRf_area());
        return d;
    }

    private double calculateParkArea() {
        double d = this.param.getVoDevelop().getSale_publicratio();
        double d2 = AutoCalBaseLandDevelopBo.converToLevelGround(this.param.getVoMain().getAa10());
        d2 = BigDecimalUtil.mul(d2, this.param.getVoDevelop().getFloor_dw());
        d2 = BigDecimalUtil.mul(d2, this.param.getVoDevelop().getBfloor_ratio());
        d2 = BigDecimalUtil.mul(d2, BigDecimalUtil.sub(1.0, d));
        d2 = BigDecimalUtil.div(d2, 100.0);
        return d2;
    }

    private double calculateParkingSpace() {
        double d = this.param.getVoDevelop().getSale_parkarea();
        double d2 = 0.0;
        if (this.param.getVoDevelop().getPark_area() > 0.0 && d > 0.0) {
            d2 = BigDecimalUtil.div(this.param.getVoDevelop().getPark_area(), d);
        }
        return d2;
    }

    private double calculateSaleArea() {
        double d = 0.0;
        d = BigDecimalUtil.add(d, this.param.getVoDevelop().getFloor1_area());
        d = BigDecimalUtil.add(d, this.param.getVoDevelop().getFloor2_area());
        d = BigDecimalUtil.add(d, this.param.getVoDevelop().getRf_area());
        d = BigDecimalUtil.add(d, this.param.getVoDevelop().getOther_area());
        d = BigDecimalUtil.add(d, this.param.getVoDevelop().getPark_area());
        return d;
    }

    private double calculateSaleAreRatio() {
        double d = 0.0;
        double d2 = 0.0;
        d2 = BigDecimalUtil.add(d2, this.param.getVoDevelop().getFloor1_area());
        d2 = BigDecimalUtil.add(d2, this.param.getVoDevelop().getFloor2_area());
        d2 = BigDecimalUtil.add(d2, this.param.getVoDevelop().getRf_area());
        d2 = BigDecimalUtil.add(d2, this.param.getVoDevelop().getOther_area());
        double d3 = this.param.getVoDevelop().getAre_area_ping();
        if (d3 > 0.0) {
            d = BigDecimalUtil.div(d2, d3);
        }
        return d;
    }

    private double calculateFloor1Value() {
        double d = BigDecimalUtil.mul(this.param.getVoDevelop().getFloor1_area(), this.param.getVoDevelop().getFloor1_uprice());
        return d;
    }

    private double calculateFloor2Value() {
        double d = BigDecimalUtil.mul(this.param.getVoDevelop().getFloor2_area(), this.param.getVoDevelop().getFloor2_uprice());
        return d;
    }

    private double calculateRfUprice() {
        double d = this.param.getVoDevelop().getFloor1_area();
        double d2 = this.param.getVoDevelop().getFloor2_area();
        double d3 = BigDecimalUtil.add(d, d2);
        double d4 = BigDecimalUtil.mul(this.param.getVoDevelop().getFloor1_uprice(), d);
        double d5 = BigDecimalUtil.mul(this.param.getVoDevelop().getFloor2_uprice(), d2);
        double d6 = BigDecimalUtil.add(d4, d5);
        if (d3 > 0.0) {
            d6 = BigDecimalUtil.div(d6, d3);
        }
        return d6;
    }

    private double calculateRfValue() {
        double d = BigDecimalUtil.mul(this.param.getVoDevelop().getRf_area(), this.param.getVoDevelop().getRf_uprice());
        return d;
    }

    private double calculateOtherValue() {
        double d = BigDecimalUtil.mul(this.param.getVoDevelop().getOther_area(), this.param.getVoDevelop().getRf_uprice());
        return d;
    }

    private double calculateParkValue() {
        double d = BigDecimalUtil.mul(this.param.getVoDevelop().getPark_cnt(), this.param.getVoDevelop().getPark_uprice());
        return d;
    }

    private double calculateSaleValue() {
        double d = 0.0;
        d = BigDecimalUtil.add(d, this.param.getVoDevelop().getFloor1_value());
        d = BigDecimalUtil.add(d, this.param.getVoDevelop().getFloor2_value());
        d = BigDecimalUtil.add(d, this.param.getVoDevelop().getRf_value());
        d = BigDecimalUtil.add(d, this.param.getVoDevelop().getOther_value());
        d = BigDecimalUtil.add(d, this.param.getVoDevelop().getPark_value());
        return d;
    }

    private double calculateBuildCostAdjust() {
        double d = BigDecimalUtil.mul(this.param.getVoDevelop().getBuild_cost(), BigDecimalUtil.div(this.param.getVoDevelop().getBuild_cost_rate(), 100.0));
        d = BigDecimalUtil.mul(d, BigDecimalUtil.div(this.param.getVoDevelop().getBuild_cost_exp(), 100.0));
        return d;
    }

    private double calculateDirectCost() {
        double d = this.param.getVoDevelop().getSale_area();
        double d2 = this.param.getVoDevelop().getBuild_cost_adjust();
        double d3 = BigDecimalUtil.mul(d, d2);
        d3 = AutoCalBaseLandDevelopBo.converToSquareMeter(d3);
        return d3;
    }

    private double calculateDesignCost() {
        double d = this.param.getVoDevelop().getDirect_cost();
        double d2 = this.param.getVoDevelop().getDesign_ratio();
        double d3 = BigDecimalUtil.mul(d, d2);
        d3 = BigDecimalUtil.div(d3, 100.0);
        return d3;
    }

    private double calculateAdCost() {
        double d = this.param.getVoDevelop().getSale_value();
        double d2 = this.param.getVoDevelop().getAd_ratio();
        double d3 = BigDecimalUtil.mul(d, d2);
        d3 = BigDecimalUtil.div(d3, 100.0);
        return d3;
    }

    private double calculateManageCost() {
        double d = this.param.getVoDevelop().getSale_value();
        double d2 = this.param.getVoDevelop().getManage_ratio();
        double d3 = BigDecimalUtil.mul(d, d2);
        d3 = BigDecimalUtil.div(d3, 100.0);
        return d3;
    }

    private double calculateTaxCost() {
        double d = this.param.getVoDevelop().getSale_value();
        double d2 = this.param.getVoDevelop().getTax_ratio();
        double d3 = BigDecimalUtil.mul(d, d2);
        d3 = BigDecimalUtil.div(d3, 100.0);
        return d3;
    }

    private double calculateIndirectRatio() {
        double d = 0.0;
        d = BigDecimalUtil.add(d, this.param.getVoDevelop().getDesign_ratio());
        d = BigDecimalUtil.add(d, this.param.getVoDevelop().getAd_ratio());
        d = BigDecimalUtil.add(d, this.param.getVoDevelop().getManage_ratio());
        d = BigDecimalUtil.add(d, this.param.getVoDevelop().getTax_ratio());
        return d;
    }

    private double calculateIndirectCost() {
        double d = 0.0;
        d = BigDecimalUtil.add(d, this.param.getVoDevelop().getDesign_cost());
        d = BigDecimalUtil.add(d, this.param.getVoDevelop().getAd_cost());
        d = BigDecimalUtil.add(d, this.param.getVoDevelop().getManage_cost());
        d = BigDecimalUtil.add(d, this.param.getVoDevelop().getTax_cost());
        return d;
    }

    private double calculateYear_rate() {
        double d = BigDecimalUtil.mul(this.param.getVoDevelop().getOwner_rate(), this.param.getVoDevelop().getOwner_ratio());
        double d2 = BigDecimalUtil.mul(this.param.getVoDevelop().getDebt_rate(), this.param.getVoDevelop().getDebt_ratio());
        double d3 = BigDecimalUtil.mul(this.param.getVoDevelop().getPresale_rate(), this.param.getVoDevelop().getPresale_ratio());
        double d4 = BigDecimalUtil.add(d, BigDecimalUtil.add(d2, d3));
        d4 = BigDecimalUtil.div(d4, 100.0);
        return d4;
    }

    private double calculateFundsRate() {
        double d = BigDecimalUtil.add(this.param.getVoDevelop().getDirect_cost(), this.param.getVoDevelop().getIndir_cost());
        double d2 = this.param.getVoDevelop().getBuild_value();
        double d3 = BigDecimalUtil.mul(BigDecimalUtil.div(this.param.getVoDevelop().getYear_rate(), 100.0), this.param.getVoDevelop().getDevelop_years());
        double d4 = BigDecimalUtil.add(d2, BigDecimalUtil.div(BigDecimalUtil.mul(d3, d2), 2.0));
        double d5 = this.param.getVoDevelop().getSale_value();
        double d6 = BigDecimalUtil.div(this.param.getVoDevelop().getBenefit_rate(), 100.0);
        double d7 = 0.0;
        if (d5 > 0.0) {
            double d8 = BigDecimalUtil.add(d4, BigDecimalUtil.mul(d, BigDecimalUtil.add(1.0, d3)));
            d8 = BigDecimalUtil.sub(d8, BigDecimalUtil.div(d5, BigDecimalUtil.add(1.0, d6)));
            double d9 = -d8 + Math.pow(Math.pow(d8, 2.0) - 4.0 * (1.0 + d3) * (d * d4 - d5 / (1.0 + d6) * d2), 0.5);
            double d10 = BigDecimalUtil.add(BigDecimalUtil.div(d9, BigDecimalUtil.mul(2.0, BigDecimalUtil.add(1.0, d3))), d);
            double d11 = BigDecimalUtil.mul(d10, BigDecimalUtil.add(1.0, d6));
            double d12 = 0.0;
            if (d11 > 0.0) {
                d12 = BigDecimalUtil.div(d5, d11);
            }
            double d13 = d12 - 1.0;
            if (d3 > 0.0) {
                d7 = BigDecimalUtil.div(d13, d3);
            }
        }
        d7 = BigDecimalUtil.mul(d7, 100.0);
        return d7;
    }

    private double calculateSumRate() {
        double d = this.param.getVoDevelop().getDevelop_years();
        double d2 = this.param.getVoDevelop().getYear_rate();
        double d3 = this.param.getVoDevelop().getFunds_ratio();
        d = BigDecimalUtil.mul(d, d2);
        d = BigDecimalUtil.mul(d, d3);
        d = BigDecimalUtil.div(d, 100.0);
        return d;
    }

    private double calculateBuildValue() {
        double d = this.param.getVoDevelop().getDirect_cost();
        double d2 = this.param.getVoDevelop().getDesign_cost();
        double d3 = BigDecimalUtil.add(d, d2);
        return d3;
    }

    private double calculateLandValue() {
        double d = this.param.getVoDevelop().getSale_value();
        double d2 = BigDecimalUtil.add(1.0, BigDecimalUtil.div(this.param.getVoDevelop().getBenefit_rate(), 100.0));
        double d3 = BigDecimalUtil.add(1.0, BigDecimalUtil.div(this.param.getVoDevelop().getSum_rate(), 100.0));
        double d4 = this.param.getVoDevelop().getDirect_cost();
        double d5 = this.param.getVoDevelop().getIndir_cost();
        double d6 = d;
        if (d2 > 0.0 && d3 > 0.0) {
            d6 = BigDecimalUtil.div(d6, d2);
            d6 = BigDecimalUtil.div(d6, d3);
        }
        d6 = BigDecimalUtil.sub(d6, BigDecimalUtil.add(d4, d5));
        return d6;
    }

    private double calculateTotalValue() {
        double d = this.param.getVoDevelop().getBuild_value();
        double d2 = this.param.getVoDevelop().getLand_value();
        double d3 = BigDecimalUtil.add(d, d2);
        return d3;
    }

    private double calculateBuildRatio() {
        double d = this.param.getVoDevelop().getBuild_value();
        double d2 = this.param.getVoDevelop().getTotal_value();
        double d3 = 0.0;
        if (d2 > 0.0) {
            d3 = BigDecimalUtil.div(d, d2);
        }
        d3 = BigDecimalUtil.mul(d3, 100.0);
        return d3;
    }

    private double calculateLandRatio() {
        double d = this.param.getVoDevelop().getLand_value();
        double d2 = this.param.getVoDevelop().getTotal_value();
        double d3 = 0.0;
        if (d2 > 0.0) {
            d3 = BigDecimalUtil.div(d, d2);
        }
        d3 = BigDecimalUtil.mul(d3, 100.0);
        return d3;
    }

    private double calculateTotalRatio() {
        double d = this.calculateBuildRatio();
        double d2 = this.calculateLandRatio();
        double d3 = BigDecimalUtil.div(d, 2.0);
        d3 = BigDecimalUtil.add(d3, d2);
        return d3;
    }

    private double calculateLandUnitPrice(boolean bl) {
        double d = 0.0;
        double d2 = this.param.getVoDevelop().getLand_value();
        if (this.param.getVoMain().getAa10() > 0.0) {
            d = BigDecimalUtil.div(d2, this.param.getVoMain().getAa10());
            if (bl) {
                double d3 = this.param.getVoDevelopExt().getMerge_rate_ext();
                d = BigDecimalUtil.mul(d, d3);
                d = BigDecimalUtil.div(d, 100.0);
            }
            d = StringProcess.roundCd(d);
        }
        return d;
    }

    private double calculateMergeRateExt() {
        double d = 0.0;
        d = BigDecimalUtil.add(d, this.param.getVoDevelopExt().getArea_rate());
        d = BigDecimalUtil.add(d, this.param.getVoDevelopExt().getLanduse_rate());
        d = BigDecimalUtil.add(d, this.param.getVoDevelopExt().getCov_rate());
        d = BigDecimalUtil.add(d, this.param.getVoDevelopExt().getAre_rate());
        d = BigDecimalUtil.add(d, this.param.getVoDevelopExt().getShape_rate());
        d = BigDecimalUtil.add(d, this.param.getVoDevelopExt().getSlop_rate());
        d = BigDecimalUtil.add(d, this.param.getVoDevelopExt().getWidth_rate());
        d = BigDecimalUtil.add(d, this.param.getVoDevelopExt().getDeep_rate());
        d = BigDecimalUtil.add(d, this.param.getVoDevelopExt().getStreet_rel_rate());
        d = BigDecimalUtil.add(d, this.param.getVoDevelopExt().getRoadwidth_rate());
        d = BigDecimalUtil.add(d, this.param.getVoDevelopExt().getCom_eff_rate());
        d = BigDecimalUtil.add(d, this.param.getVoDevelopExt().getDev_eff_rate());
        d = BigDecimalUtil.add(d, this.param.getVoDevelopExt().getOther_rate());
        d = BigDecimalUtil.add(d, 100.0);
        return d;
    }

    private static double converToSquareMeter(double d) {
        return BigDecimalUtil.mul(d, 3.3058);
    }

    private static double converToLevelGround(double d) {
        return BigDecimalUtil.mul(d, 0.3025);
    }

    private static double round(double d, int n) {
        return BigDecimalUtil.round(d, n);
    }

    private static double roundDown(double d, int n) {
        return BigDecimalUtil.roundDown(d, n);
    }
}

