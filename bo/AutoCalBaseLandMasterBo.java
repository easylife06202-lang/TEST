/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.bo;

import com.wfusion.util.BigDecimalUtil;
import com.wfusion.util.StringProcess;
import java.util.TreeMap;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_AHP;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_APPRAISAL;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_APPRAISALA3_SCORE;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_DEVELOP;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_MAIN;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT_EXT;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT_MONTH;

public class AutoCalBaseLandMasterBo {
    private static final int HUGE_SALE_MONEY = 300000000;
    private static String SELL_TYPE = "01";
    private static String RENT_TYPE = "02";
    private static String DEVELOP_BY_MARKET = "0";
    private static String DEVELOP_BY_HYPOTHESIS = "1";
    private NVO_BASELAND_MAIN main = null;
    private NVO_BASELAND_DEVELOP voDevelop = null;
    private NVO_BASELAND_APPRAISAL app = null;
    private NVO_BASELAND_RENT rent = null;
    private NVO_BASELAND_RENT_EXT rent_ext = null;
    private NVO_BASELAND_AHP ahp = null;
    private TreeMap<String, NVO_BASELAND_RENT_MONTH> rent_month = new TreeMap();
    private TreeMap<String, NVO_BASELAND_APPRAISALA3_SCORE> scoreAppMap = new TreeMap();

    public void getNormailizeCAL_AHP() {
        double d = this.getCal_AHP("sell");
        double d2 = this.getCal_AHP("rent");
        double d3 = this.getCal_AHP("develop");
        if (Double.isNaN(d)) {
            d = 0.0;
        }
        if (Double.isNaN(d2)) {
            d2 = 0.0;
        }
        if (Double.isNaN(d3)) {
            d3 = 0.0;
        }
        int n = 0;
        if (this.main.getPprice() > 0) {
            ++n;
        }
        if (this.main.getRprice() > 0) {
            ++n;
        }
        if (this.main.getCprice() > 0) {
            ++n;
        }
        if (n >= 2) {
            double d4;
            double d5 = (d + d2 + d3) / (double)n;
            if (this.app != null && this.main.getPprice() > 0) {
                d4 = this.calculateCalAhpRatio(d, d5);
                this.main.setAph_sell(Double.isNaN(d4) ? 0.0 : d4);
            }
            if (this.rent != null && this.main.getRprice() > 0) {
                d4 = this.calculateCalAhpRatio(d2, d5);
                this.main.setAhp_rent(Double.isNaN(d4) ? 0.0 : d4);
            }
            if (this.voDevelop != null && this.main.getCprice() > 0) {
                d4 = this.calculateCalAhpRatio(d3, d5);
                this.main.setAhp_develop(Double.isNaN(d4) ? 0.0 : d4);
            }
        }
    }

    public double getNormailizeCAL_AHP(String string) {
        double d = this.getCal_AHP("sell");
        double d2 = this.getCal_AHP("rent");
        double d3 = this.getCal_AHP("develop");
        double d4 = (d + d2 + d3) / 3.0;
        double d5 = 0.0;
        if (this.app != null && "sell".equals(string)) {
            d5 = this.calculateCalAhpRatio(d, d4);
        }
        if (this.rent != null && "rent".equals(string)) {
            d5 = this.calculateCalAhpRatio(d2, d4);
        }
        if (this.voDevelop != null && "develop".equals(string)) {
            d5 = this.calculateCalAhpRatio(d3, d4);
        }
        return d5;
    }

    private double calculateCalAhpRatio(double d, double d2) {
        if (d2 == 0.0) {
            return 0.0;
        }
        double d3 = 0.0;
        d3 = d < d2 ? (1.0 - (d2 - d) / d2) / 3.0 * 100.0 : (1.0 + (d - d2) / d2) / 3.0 * 100.0;
        return d3;
    }

    public double getCal_AHP(String string) {
        double d = 0.0;
        if (this.ahp != null) {
            d = this.getCredibilityRate(string) * this.getBiasRate(string) * this.getRelationRate(string) * (double)this.ahp.getCredibility() / 100.0;
            d += this.getSimilarityRate(string) * (double)this.ahp.getSimilarity() / 100.0;
            d += this.getValuationTypeRate(string) * (double)this.ahp.getValue_type() / 100.0;
        }
        return d;
    }

    private double getCredibilityRate(String string) {
        double d = 0.0;
        if (this.app != null && "sell".equals(string)) {
            double d2 = 0.0;
            d2 += this.scoreAppMap.get("1") == null ? 0.0 : (double)this.scoreAppMap.get("1").getPrice_type() * this.app.getCal_ahp1() / 100.0;
            d2 += this.scoreAppMap.get("2") == null ? 0.0 : (double)this.scoreAppMap.get("2").getPrice_type() * this.app.getCal_ahp2() / 100.0;
            if ((d2 += this.scoreAppMap.get("3") == null ? 0.0 : (double)this.scoreAppMap.get("3").getPrice_type() * this.app.getCal_ahp3() / 100.0) != 0.0) {
                d = 1.0 / d2;
            }
        }
        if (this.rent != null && "rent".equals(string)) {
            d = (double)this.rent_month.size() / 4.0;
        }
        if (this.voDevelop != null && "develop".equals(string) && StringProcess.parserInt(this.voDevelop.getPrice_type()) != 0) {
            d = 1.0 / (double)StringProcess.parserInt(this.voDevelop.getPrice_type());
        }
        return d;
    }

    private double getSimilarityRate(String string) {
        double d;
        double d2;
        double d3 = 0.0;
        if (this.app != null && "sell".equals(string)) {
            d2 = this.scoreAppMap.get("1") == null ? 0.0 : this.scoreAppMap.get("1").getMonth_near();
            d = this.scoreAppMap.get("2") == null ? 0.0 : this.scoreAppMap.get("2").getMonth_near();
            double d4 = this.scoreAppMap.get("3") == null ? 0.0 : this.scoreAppMap.get("3").getMonth_near();
            double d5 = this.scoreAppMap.get("1") == null ? 0.0 : (double)this.scoreAppMap.get("1").getArea_near();
            double d6 = this.scoreAppMap.get("2") == null ? 0.0 : (double)this.scoreAppMap.get("2").getArea_near();
            double d7 = this.scoreAppMap.get("3") == null ? 0.0 : (double)this.scoreAppMap.get("3").getArea_near();
            double d8 = this.scoreAppMap.get("1") == null ? 0.0 : this.scoreAppMap.get("1").getAbs_rate() / 100.0;
            double d9 = this.scoreAppMap.get("2") == null ? 0.0 : this.scoreAppMap.get("2").getAbs_rate() / 100.0;
            double d10 = this.scoreAppMap.get("3") == null ? 0.0 : this.scoreAppMap.get("3").getAbs_rate() / 100.0;
            double d11 = this.scoreAppMap.get("1") == null ? 0.0 : (double)this.scoreAppMap.get("1").getDiff_cnt();
            double d12 = this.scoreAppMap.get("2") == null ? 0.0 : (double)this.scoreAppMap.get("2").getDiff_cnt();
            double d13 = this.scoreAppMap.get("3") == null ? 0.0 : (double)this.scoreAppMap.get("3").getDiff_cnt();
            double d14 = this.app.getCal_ahp1() / 100.0;
            double d15 = this.app.getCal_ahp2() / 100.0;
            double d16 = this.app.getCal_ahp3() / 100.0;
            double d17 = (double)this.ahp.getComp_month() / 100.0;
            double d18 = (double)this.ahp.getComp_near() / 100.0;
            double d19 = (double)this.ahp.getComp_diff_abs() / 100.0;
            double d20 = (double)this.ahp.getComp_diff_items() / 100.0;
            double d21 = d17 + d18 + d19 + d20;
            d3 = 0.3 / (d2 / this.AVERAGE(d2, d, d4) * d17 + d5 * d18 + (d8 / this.AVERAGE(d8, d9, d10) * d19 + d11 / this.AVERAGE(d11, d12, d13) * d20 * d14 + ((d / this.AVERAGE(d2, d, d4) * d17 + d6 * d18 + d9 / this.AVERAGE(d8, d9, d10) * d19 + d12 / this.AVERAGE(d11, d12, d13) * d20) * d15 + (d4 / this.AVERAGE(d2, d, d4) * d17 + d7 * d18 + d10 / this.AVERAGE(d8, d9, d10) * d19 + d13 / this.AVERAGE(d11, d12, d13) * d20) * d16 / d21))) + 0.7 / ((d2 * d17 + d5 * d18 + d8 * d19 + d11 * d20) * d14 + (d * d17 + d6 * d18 + d9 * d19 + d12 * d20) * d15 + (d4 * d17 + d7 * d18 + d10 * d19 + d13 * d20) * d16) / d21;
        }
        if (this.rent != null && "rent".equals(string)) {
            return (this.getSimilarityRate("sell") + this.getSimilarityRate("develop")) / 2.0;
        }
        if (this.voDevelop != null && "develop".equals(string)) {
            d3 = 0.8;
            d2 = StringProcess.parserInt(this.voDevelop.getFloor_type());
            if ((d3 -= Math.pow(d2 + (d = (double)StringProcess.parserInt(this.voDevelop.getSame_case())) / 3.0, 3.0)) < 0.5) {
                d3 = 0.5;
            }
        }
        return d3;
    }

    private double AVERAGE(double d, double d2, double d3) {
        return (d + d2 + d3) / 3.0;
    }

    private double getBiasRate(String string) {
        double d;
        double d2;
        double d3;
        double d4 = 0.0;
        if (this.app != null && this.ahp != null && "sell".equals(string)) {
            d3 = 0.0;
            d3 += this.app.getCal_ahp1() / 100.0 * (double)(this.scoreAppMap.get("1") == null ? false : this.scoreAppMap.get("1").getAbs_rate() > (double)this.ahp.getComp_diff_limit());
            d3 += this.app.getCal_ahp2() / 100.0 * (double)(this.scoreAppMap.get("2") == null ? false : this.scoreAppMap.get("2").getAbs_rate() > (double)this.ahp.getComp_diff_limit());
            d4 = 1.0 - (double)this.ahp.getComp_diff_abs() / 100.0 * (d3 += this.app.getCal_ahp3() / 100.0 * (double)(this.scoreAppMap.get("3") == null ? false : this.scoreAppMap.get("3").getAbs_rate() > (double)this.ahp.getComp_diff_limit()));
        }
        if (this.rent != null && "rent".equals(string)) {
            d3 = this.rent.getCr11() == this.rent.getCr12() ? (double)this.ahp.getRent_month_money() / 100.0 : 0.0;
            d2 = 0.0;
            if (this.rent_ext != null && this.rent_ext.getCre27() != 0) {
                d2 = (double)(this.rent_ext.getCre25() * this.ahp.getRent_years()) / 100.0 / (double)this.rent_ext.getCre27();
            }
            d = (double)this.ahp.getRent_buildcost_ext() / 100.0 / 3.0;
            d4 = 1.0 - d3 - d2 - d;
        }
        if (this.voDevelop != null && "develop".equals(string)) {
            d3 = DEVELOP_BY_MARKET.equals(this.voDevelop.getFloor_type()) ? 0.0 : (double)(1 * this.ahp.getDev_floors_plan()) / 100.0;
            d2 = this.voDevelop.getSale_value() > 300000000L ? (double)this.ahp.getDev_sale_money() / 100.0 : 0.0;
            d = 0.0;
            if (BigDecimalUtil.round(this.voDevelop.getTotal_ratio(), 2) != BigDecimalUtil.round(this.voDevelop.getFunds_ratio(), 2)) {
                d = (double)this.ahp.getDev_fouds_rate() / 100.0;
            }
            double d5 = (double)this.ahp.getDev_buildcost_ext() / 100.0;
            d4 = 1.0 - d3 - d2 - d - d5;
        }
        return d4;
    }

    private double getValuationTypeRate(String string) {
        if (this.app != null && "sell".equals(string)) {
            if (SELL_TYPE.equals(this.main.getTrad_type())) {
                return this.getCredibilityRate(string) * 2.0;
            }
            return this.getCredibilityRate(string);
        }
        if (this.rent != null && "rent".equals(string)) {
            if (RENT_TYPE.equals(this.main.getTrad_type())) {
                return this.getCredibilityRate(string) * 3.0;
            }
            return this.getCredibilityRate(string);
        }
        if (this.voDevelop != null && "develop".equals(string)) {
            return (this.getValuationTypeRate("sell") + this.getValuationTypeRate("rent")) / 2.0;
        }
        return 0.0;
    }

    private double getRelationRate(String string) {
        double d = 0.0;
        if (this.app != null && "sell".equals(string)) {
            return 1.0;
        }
        if (this.rent != null && "rent".equals(string)) {
            return 1.0 - (double)(this.rent.getCr48() * this.ahp.getRent_capitalization()) / 100.0;
        }
        if (this.voDevelop != null && "develop".equals(string)) {
            double d2 = StringProcess.parserInt(this.voDevelop.getSame_case());
            return 1.0 - d2 / 2.0;
        }
        return d;
    }

    public NVO_BASELAND_MAIN getMain() {
        return this.main;
    }

    public NVO_BASELAND_DEVELOP getVoDevelop() {
        return this.voDevelop;
    }

    public NVO_BASELAND_APPRAISAL getApp() {
        return this.app;
    }

    public NVO_BASELAND_RENT getRent() {
        return this.rent;
    }

    public NVO_BASELAND_RENT_EXT getRent_ext() {
        return this.rent_ext;
    }

    public NVO_BASELAND_AHP getAhp() {
        return this.ahp;
    }

    public TreeMap<String, NVO_BASELAND_RENT_MONTH> getRent_month() {
        return this.rent_month;
    }

    public TreeMap<String, NVO_BASELAND_APPRAISALA3_SCORE> getScoreAppMap() {
        return this.scoreAppMap;
    }

    public void setMain(NVO_BASELAND_MAIN nVO_BASELAND_MAIN) {
        this.main = nVO_BASELAND_MAIN;
    }

    public void setVoDevelop(NVO_BASELAND_DEVELOP nVO_BASELAND_DEVELOP) {
        this.voDevelop = nVO_BASELAND_DEVELOP;
    }

    public void setApp(NVO_BASELAND_APPRAISAL nVO_BASELAND_APPRAISAL) {
        this.app = nVO_BASELAND_APPRAISAL;
    }

    public void setRent(NVO_BASELAND_RENT nVO_BASELAND_RENT) {
        this.rent = nVO_BASELAND_RENT;
    }

    public void setRent_ext(NVO_BASELAND_RENT_EXT nVO_BASELAND_RENT_EXT) {
        this.rent_ext = nVO_BASELAND_RENT_EXT;
    }

    public void setAhp(NVO_BASELAND_AHP nVO_BASELAND_AHP) {
        this.ahp = nVO_BASELAND_AHP;
    }

    public void setRent_month(TreeMap<String, NVO_BASELAND_RENT_MONTH> treeMap) {
        this.rent_month = treeMap;
    }

    public void setScoreAppMap(TreeMap<String, NVO_BASELAND_APPRAISALA3_SCORE> treeMap) {
        this.scoreAppMap = treeMap;
    }
}

