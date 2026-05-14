/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.bo;

import com.wfusion.util.BigDecimalUtil;
import com.wfusion.util.StringProcess;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_AHP;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_APPRAISAL;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_APPRAISALA3_SCORE;
import moiland.baseland.factor.em.EnumFactorVersion;

public class AutoCalBaseLandAppraisalBo {
    private static final String PROPERTIES_BASE_A3 = "baseland_appraisal_subitem_A3";
    private static final String PROPERTIES_BASE_A3BD = "baseland_appraisal_subitem_A3BD";
    private static final String PROPERTIES_BASE_A3BF = "baseland_appraisal_subitem_A3BF";
    private static final double SQUARE_METER_TO_LEVEL_GROUND = 0.3025;
    private static Map<EnumFactorVersion, Map<String, String[]>> sumItemVersionMap = new TreeMap<EnumFactorVersion, Map<String, String[]>>();
    private NVO_BASELAND_APPRAISALA3_SCORE tarAppVo = new NVO_BASELAND_APPRAISALA3_SCORE();
    private TreeMap<String, NVO_BASELAND_APPRAISALA3_SCORE> scoreAppMap = new TreeMap();
    private NVO_BASELAND_APPRAISAL resultVo = new NVO_BASELAND_APPRAISAL();
    private NVO_BASELAND_AHP ahp = null;
    private Map<String, String[]> sumItemMap = null;

    public static Map<String, String[]> getSumItemMap(String string) {
        TreeMap<String, String[]> treeMap = new TreeMap<String, String[]>();
        ResourceBundle resourceBundle = ResourceBundle.getBundle(string);
        Enumeration<String> enumeration = resourceBundle.getKeys();
        while (enumeration.hasMoreElements()) {
            String string2 = String.valueOf(enumeration.nextElement());
            String string3 = String.valueOf(resourceBundle.getString(string2));
            String[] stringArray = new String[1];
            if (string3.indexOf(",") > -1) {
                stringArray = string3.split(",");
            } else {
                stringArray[0] = string3;
            }
            treeMap.put(string2, stringArray);
        }
        return treeMap;
    }

    public AutoCalBaseLandAppraisalBo(NVO_BASELAND_AHP nVO_BASELAND_AHP, EnumFactorVersion enumFactorVersion) {
        this.ahp = nVO_BASELAND_AHP;
        this.sumItemMap = enumFactorVersion != null ? sumItemVersionMap.get((Object)enumFactorVersion) : new TreeMap<String, String[]>();
    }

    public void sumAllRate() {
        for (String string : this.scoreAppMap.keySet()) {
            if (string.equals("0")) continue;
            NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE = this.scoreAppMap.get(string);
            this.sumRate(string, nVO_BASELAND_APPRAISALA3_SCORE);
        }
    }

    private void sumRate(String string, NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE) {
        double d = 0.0;
        int n = 0;
        Class<NVO_BASELAND_APPRAISALA3_SCORE> clazz = NVO_BASELAND_APPRAISALA3_SCORE.class;
        try {
            for (String string2 : this.sumItemMap.keySet()) {
                String[] stringArray = this.sumItemMap.get(string2);
                double d2 = 0.0;
                Method method = null;
                String string3 = StringProcess.setFustionString(string2, true);
                for (String string4 : stringArray) {
                    String string5 = StringProcess.getFustionString(string4, true);
                    try {
                        Method method2 = clazz.getMethod(string5, new Class[0]);
                        double d3 = StringProcess.parserDouble(String.valueOf(method2.invoke((Object)nVO_BASELAND_APPRAISALA3_SCORE, new Object[0])));
                        d2 = BigDecimalUtil.add(d2, d3);
                        if (string4.indexOf("_DV") <= -1) continue;
                        if (d3 != 0.0) {
                            ++n;
                        }
                        d = BigDecimalUtil.add(d, Math.abs(d3));
                    }
                    catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                method = clazz.getMethod(string3, Double.TYPE);
                method.invoke((Object)nVO_BASELAND_APPRAISALA3_SCORE, d2);
            }
            if (nVO_BASELAND_APPRAISALA3_SCORE.getAs307_dv() != 0.0) {
                ++n;
            }
            if (nVO_BASELAND_APPRAISALA3_SCORE.getAs308_dv() != 0.0) {
                ++n;
            }
            nVO_BASELAND_APPRAISALA3_SCORE.setDiff_cnt(n);
            d = BigDecimalUtil.add(d, Math.abs(nVO_BASELAND_APPRAISALA3_SCORE.getAs307_dv()));
            d = BigDecimalUtil.add(d, Math.abs(nVO_BASELAND_APPRAISALA3_SCORE.getAs308_dv()));
            nVO_BASELAND_APPRAISALA3_SCORE.setAbs_rate(d);
            this.calTotalDiffRate(nVO_BASELAND_APPRAISALA3_SCORE);
            this.calMonth_near(nVO_BASELAND_APPRAISALA3_SCORE);
            this.calArea_near(nVO_BASELAND_APPRAISALA3_SCORE);
            System.out.println(string + "->" + n + ",abs_rate=" + nVO_BASELAND_APPRAISALA3_SCORE.getAbs_rate() + ",vo.getMonth_near()=" + nVO_BASELAND_APPRAISALA3_SCORE.getMonth_near());
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private double calMonth_near(NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE) {
        int n = 0;
        if (nVO_BASELAND_APPRAISALA3_SCORE.getAs308().length() < 7) {
            n = 0;
        } else {
            int n2 = StringProcess.parserInt(nVO_BASELAND_APPRAISALA3_SCORE.getYear()) * 12 + 3;
            int n3 = StringProcess.parserInt(nVO_BASELAND_APPRAISALA3_SCORE.getAs308().substring(0, 3)) * 12 + StringProcess.parserInt(nVO_BASELAND_APPRAISALA3_SCORE.getAs308().substring(3, 5));
            n = Math.abs(n2 - n3);
        }
        double d = 0.5;
        if (n > 0) {
            d = n;
        }
        nVO_BASELAND_APPRAISALA3_SCORE.setMonth_near(d);
        return d;
    }

    private int calArea_near(NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE) {
        if (nVO_BASELAND_APPRAISALA3_SCORE.getAs320() == 0.0 && nVO_BASELAND_APPRAISALA3_SCORE.getAs325() == 0.0 && nVO_BASELAND_APPRAISALA3_SCORE.getAs331() == 0.0 && nVO_BASELAND_APPRAISALA3_SCORE.getAs313_dv() == 0.0 && nVO_BASELAND_APPRAISALA3_SCORE.getAs314_dv() == 0.0) {
            nVO_BASELAND_APPRAISALA3_SCORE.setArea_near(1);
            return 1;
        }
        nVO_BASELAND_APPRAISALA3_SCORE.setArea_near(2);
        return 2;
    }

    private void calPriceDiff() {
        double d;
        NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE;
        NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE2 = this.scoreAppMap.containsKey("1") ? this.scoreAppMap.get("1") : new NVO_BASELAND_APPRAISALA3_SCORE();
        NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE3 = this.scoreAppMap.containsKey("2") ? this.scoreAppMap.get("2") : new NVO_BASELAND_APPRAISALA3_SCORE();
        NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE4 = nVO_BASELAND_APPRAISALA3_SCORE = this.scoreAppMap.containsKey("3") ? this.scoreAppMap.get("3") : new NVO_BASELAND_APPRAISALA3_SCORE();
        if (nVO_BASELAND_APPRAISALA3_SCORE2.getAs373() > 0 && nVO_BASELAND_APPRAISALA3_SCORE3.getAs373() > 0) {
            d = Math.abs(BigDecimalUtil.sub(nVO_BASELAND_APPRAISALA3_SCORE2.getAs373(), nVO_BASELAND_APPRAISALA3_SCORE3.getAs373()));
            d = BigDecimalUtil.div(d, BigDecimalUtil.add(nVO_BASELAND_APPRAISALA3_SCORE2.getAs373(), nVO_BASELAND_APPRAISALA3_SCORE3.getAs373()));
            d = BigDecimalUtil.mul(d, 2.0);
            this.resultVo.setPrice_diff12(this.mulHundred(BigDecimalUtil.round(d, 4)));
        }
        if (nVO_BASELAND_APPRAISALA3_SCORE3.getAs373() > 0 && nVO_BASELAND_APPRAISALA3_SCORE.getAs373() > 0) {
            d = Math.abs(BigDecimalUtil.sub(nVO_BASELAND_APPRAISALA3_SCORE3.getAs373(), nVO_BASELAND_APPRAISALA3_SCORE.getAs373()));
            d = BigDecimalUtil.div(d, BigDecimalUtil.add(nVO_BASELAND_APPRAISALA3_SCORE3.getAs373(), nVO_BASELAND_APPRAISALA3_SCORE.getAs373()));
            d = BigDecimalUtil.mul(d, 2.0);
            this.resultVo.setPrice_diff23(this.mulHundred(BigDecimalUtil.round(d, 4)));
        }
        if (nVO_BASELAND_APPRAISALA3_SCORE2.getAs373() > 0 && nVO_BASELAND_APPRAISALA3_SCORE.getAs373() > 0) {
            d = Math.abs(BigDecimalUtil.sub(nVO_BASELAND_APPRAISALA3_SCORE2.getAs373(), nVO_BASELAND_APPRAISALA3_SCORE.getAs373()));
            d = BigDecimalUtil.div(d, BigDecimalUtil.add(nVO_BASELAND_APPRAISALA3_SCORE2.getAs373(), nVO_BASELAND_APPRAISALA3_SCORE.getAs373()));
            d = BigDecimalUtil.mul(d, 2.0);
            this.resultVo.setPrice_diff31(this.mulHundred(BigDecimalUtil.round(d, 4)));
        }
    }

    private void calTotalDiffRate(NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE) {
        double d = 0.0;
        double d2 = BigDecimalUtil.add(1.0, this.divHundred(nVO_BASELAND_APPRAISALA3_SCORE.getAs307_dv()));
        double d3 = BigDecimalUtil.add(1.0, this.divHundred(nVO_BASELAND_APPRAISALA3_SCORE.getAs308_dv()));
        double d4 = BigDecimalUtil.add(1.0, this.divHundred(nVO_BASELAND_APPRAISALA3_SCORE.getAs332()));
        double d5 = BigDecimalUtil.add(1.0, this.divHundred(nVO_BASELAND_APPRAISALA3_SCORE.getAs371()));
        d = BigDecimalUtil.mul(d2, d3);
        d = BigDecimalUtil.mul(d, d4);
        d = BigDecimalUtil.mul(d, d5);
        d = BigDecimalUtil.sub(d, 1.0);
        d = this.mulHundred(BigDecimalUtil.round(d, 4));
        nVO_BASELAND_APPRAISALA3_SCORE.setAs372(d);
    }

    public void calResult() {
        this.sumAllRate();
        this.calDiversityAll();
        this.calPriceDiff();
        this.calculateFinalPrice();
    }

    public NVO_BASELAND_APPRAISALA3_SCORE getTarAppVo() {
        return this.tarAppVo;
    }

    public void setTarAppVo(NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE) {
        this.tarAppVo = nVO_BASELAND_APPRAISALA3_SCORE;
    }

    public TreeMap<String, NVO_BASELAND_APPRAISALA3_SCORE> getScoreAppMap() {
        return this.scoreAppMap;
    }

    public void setScoreAppMap(TreeMap<String, NVO_BASELAND_APPRAISALA3_SCORE> treeMap) {
        this.scoreAppMap = treeMap;
    }

    public NVO_BASELAND_APPRAISAL getResultVo() {
        return this.resultVo;
    }

    public void setResultVo(NVO_BASELAND_APPRAISAL nVO_BASELAND_APPRAISAL) {
        this.resultVo = nVO_BASELAND_APPRAISAL;
    }

    public double getAveragePriceType() {
        int n = 0;
        for (NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE : this.scoreAppMap.values()) {
            n += nVO_BASELAND_APPRAISALA3_SCORE.getPrice_type();
        }
        return (double)n / 3.0;
    }

    public double getAverageMonthNear() {
        double d = 0.0;
        for (NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE : this.scoreAppMap.values()) {
            d += nVO_BASELAND_APPRAISALA3_SCORE.getMonth_near();
        }
        return d / 3.0;
    }

    public double getAverageAreaNear() {
        int n = 0;
        for (NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE : this.scoreAppMap.values()) {
            n += nVO_BASELAND_APPRAISALA3_SCORE.getArea_near();
        }
        return (double)n / 3.0;
    }

    public double getAverageAbsRate() {
        double d = 0.0;
        for (NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE : this.scoreAppMap.values()) {
            d += nVO_BASELAND_APPRAISALA3_SCORE.getAbs_rate();
        }
        return d / 3.0;
    }

    public double getAverageDiffCnt() {
        int n = 0;
        for (NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE : this.scoreAppMap.values()) {
            n += nVO_BASELAND_APPRAISALA3_SCORE.getDiff_cnt();
        }
        return (double)n / 3.0;
    }

    public double getAverageDiversity() {
        return (this.resultVo.getDiversity1() + this.resultVo.getDiversity2() + this.resultVo.getDiversity3()) / 3.0;
    }

    public double calDiversity(NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE) {
        double d = 0.0;
        if (nVO_BASELAND_APPRAISALA3_SCORE != null) {
            if (this.getAveragePriceType() > 0.0) {
                d += 1.0 * (double)nVO_BASELAND_APPRAISALA3_SCORE.getPrice_type() / this.getAveragePriceType() * ((double)this.ahp.getComp_price_type() / 100.0);
            } else {
                System.out.println("getAveragePriceType()=" + this.getAveragePriceType());
            }
            if (this.getAverageMonthNear() > 0.0) {
                d += 1.0 * nVO_BASELAND_APPRAISALA3_SCORE.getMonth_near() / this.getAverageMonthNear() * ((double)this.ahp.getComp_month() / 100.0);
            } else {
                System.out.println("getAverageMonthNear()=" + this.getAverageMonthNear());
            }
            if (this.getAverageAreaNear() > 0.0) {
                d += 1.0 * (double)nVO_BASELAND_APPRAISALA3_SCORE.getArea_near() / this.getAverageAreaNear() * ((double)this.ahp.getComp_near() / 100.0);
            } else {
                System.out.println("getAveragePriceType()=" + this.getAverageAreaNear());
            }
            if (this.getAverageAbsRate() > 0.0) {
                d += 1.0 * nVO_BASELAND_APPRAISALA3_SCORE.getAbs_rate() / this.getAverageAbsRate() * ((double)this.ahp.getComp_diff_abs() / 100.0);
            } else {
                System.out.println("getAverageAbsRate()=" + this.getAverageAbsRate());
            }
            if (this.getAverageDiffCnt() > 0.0) {
                d += 1.0 * (double)nVO_BASELAND_APPRAISALA3_SCORE.getDiff_cnt() / this.getAverageDiffCnt() * ((double)this.ahp.getComp_diff_items() / 100.0);
            } else {
                System.out.println("getAverageDiffCnt()=" + this.getAverageDiffCnt());
            }
            d = this.mulHundred(BigDecimalUtil.round(d, 4));
            return d;
        }
        return d;
    }

    public void calDiversityAll() {
        this.resultVo.setDiversity1(this.calDiversity(this.scoreAppMap.get("1")));
        this.resultVo.setDiversity2(this.calDiversity(this.scoreAppMap.get("2")));
        this.resultVo.setDiversity3(this.calDiversity(this.scoreAppMap.get("3")));
        this.resultVo.setCal_ahp1(this.calculateCalAhp(this.resultVo.getDiversity1()));
        this.resultVo.setCal_ahp2(this.calculateCalAhp(this.resultVo.getDiversity2()));
        this.resultVo.setCal_ahp3(this.calculateCalAhp(this.resultVo.getDiversity3()));
    }

    private double calculateCalAhp(double d) {
        double d2 = this.getAverageDiversity();
        if (d2 == 0.0) {
            return 0.0;
        }
        double d3 = 0.0;
        d3 = d < d2 ? (1.0 + (d2 - d) / d2) / 3.0 : (1.0 - (d - d2) / d2) / 3.0;
        d3 = BigDecimalUtil.round(d3, 4);
        return this.mulHundred(d3);
    }

    public void calculateFinalPrice() {
        double d = 0.0;
        d += this.resultVo.getFin_ahp1() / 100.0 * (double)(this.scoreAppMap.get("1") == null ? 0 : this.scoreAppMap.get("1").getAs373());
        d += this.resultVo.getFin_ahp2() / 100.0 * (double)(this.scoreAppMap.get("2") == null ? 0 : this.scoreAppMap.get("2").getAs373());
        this.resultVo.setFin_price((int)StringProcess.roundCd(d += this.resultVo.getFin_ahp3() / 100.0 * (double)(this.scoreAppMap.get("3") == null ? 0 : this.scoreAppMap.get("3").getAs373())));
        this.resultVo.setFin_pricep((int)StringProcess.roundCd(BigDecimalUtil.div(this.resultVo.getFin_price(), 0.3025)));
    }

    private double divHundred(double d) {
        if (d != 0.0) {
            return BigDecimalUtil.div(d, 100.0);
        }
        return d;
    }

    private double mulHundred(double d) {
        if (d != 0.0) {
            return BigDecimalUtil.mul(d, 100.0);
        }
        return d;
    }

    static {
        sumItemVersionMap.put(EnumFactorVersion.A3BA, AutoCalBaseLandAppraisalBo.getSumItemMap(PROPERTIES_BASE_A3));
        sumItemVersionMap.put(EnumFactorVersion.A3BB, AutoCalBaseLandAppraisalBo.getSumItemMap(PROPERTIES_BASE_A3));
        sumItemVersionMap.put(EnumFactorVersion.A3BD, AutoCalBaseLandAppraisalBo.getSumItemMap(PROPERTIES_BASE_A3BD));
        sumItemVersionMap.put(EnumFactorVersion.A3BF, AutoCalBaseLandAppraisalBo.getSumItemMap(PROPERTIES_BASE_A3BF));
    }
}

