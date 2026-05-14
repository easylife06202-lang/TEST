/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.bo;

import com.wfusion.util.BigDecimalUtil;
import com.wfusion.util.StringProcess;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.TreeMap;
import moiland.baseland.bo.AutoCalBaseLandSell;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT_EXT;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT_MONTH;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_REPORT_PARAM;

public class AutoCalBaseLandRent {
    public static double BASELAND_RENT_MONTH_A_AHP = 0.18;
    public static double BASELAND_RENT_MONTH_B_AHP = 0.17;
    public static double BASELAND_RENT_MONTH_C_AHP = 0.14;
    public static double BASELAND_RENT_MONTH_D_AHP = 0.05;
    public static double BASELAND_RENT_MONTH_E_AHP = 0.09;
    private final double pingUnit = 0.3025;

    public void calRentMaster(NVO_BASELAND_RENT nVO_BASELAND_RENT, NVO_BASELAND_REPORT_PARAM nVO_BASELAND_REPORT_PARAM) {
        boolean bl = nVO_BASELAND_RENT.isBuilding();
        double d = nVO_BASELAND_RENT.getCr01();
        double d2 = BigDecimalUtil.div(nVO_BASELAND_RENT.getCr14(), 100.0);
        long l = nVO_BASELAND_RENT.getCr17();
        double d3 = nVO_BASELAND_RENT.getCr18();
        double d4 = BigDecimalUtil.div(nVO_BASELAND_RENT.getCre29(), 100.0);
        double d5 = nVO_BASELAND_RENT.getCre27();
        double d6 = nVO_BASELAND_RENT.getCre34();
        long l2 = nVO_BASELAND_RENT.getCr20();
        long l3 = nVO_BASELAND_RENT.getCr21();
        double d7 = BigDecimalUtil.mul(d6, nVO_BASELAND_RENT.getCr03());
        d7 = BigDecimalUtil.round(BigDecimalUtil.mul(d7, this.divHundred(nVO_BASELAND_REPORT_PARAM.getMaintian_rate())), 0);
        nVO_BASELAND_RENT.setCr24((int)BigDecimalUtil.round(d7, 0));
        long l4 = nVO_BASELAND_RENT.getCr26();
        double d8 = BigDecimalUtil.div(nVO_BASELAND_RENT.getCr30(), 100.0);
        double d9 = nVO_BASELAND_RENT.getCr32();
        double d10 = BigDecimalUtil.div(nVO_BASELAND_RENT.getCr35(), 100.0);
        double d11 = nVO_BASELAND_RENT.getCr42();
        double d12 = nVO_BASELAND_RENT.getCr43();
        if (bl) {
            nVO_BASELAND_RENT.setCr03(nVO_BASELAND_RENT.getCre07());
        }
        double d13 = nVO_BASELAND_RENT.getCr03();
        double d14 = bl ? nVO_BASELAND_RENT.getCr04() : d13;
        double d15 = nVO_BASELAND_RENT.getCr09();
        double d16 = BigDecimalUtil.round(BigDecimalUtil.mul(nVO_BASELAND_RENT.getCre53(), d13), 0);
        double d17 = BigDecimalUtil.round(BigDecimalUtil.mul(nVO_BASELAND_RENT.getCre54(), d13), 0);
        double d18 = d15 > 0.0 ? (d > 0.0 ? BigDecimalUtil.round(BigDecimalUtil.mul(BigDecimalUtil.add(BigDecimalUtil.mul(d15, 12.0), BigDecimalUtil.mul(d, this.divHundred(nVO_BASELAND_REPORT_PARAM.getMc()))), d13), 0) : BigDecimalUtil.round(BigDecimalUtil.mul(BigDecimalUtil.mul(d15, 12.0), d13), 0)) : (double)nVO_BASELAND_RENT.getCr11();
        double d19 = d18;
        double d20 = BigDecimalUtil.round(d18 * (double)nVO_BASELAND_RENT.getCr02() / 12.0, 0);
        double d21 = BigDecimalUtil.round(BigDecimalUtil.mul(d2, d20), 0);
        double d22 = BigDecimalUtil.round(BigDecimalUtil.add(BigDecimalUtil.add(d19, l), d21), 0);
        double d23 = BigDecimalUtil.round(BigDecimalUtil.mul(d22, BigDecimalUtil.sub(1.0, this.div(d3, 12.0))), 0);
        nVO_BASELAND_RENT.setCr03(d13);
        nVO_BASELAND_RENT.setCr04(d14);
        nVO_BASELAND_RENT.setCr07((long)BigDecimalUtil.round(d16, 0));
        nVO_BASELAND_RENT.setCr08((long)BigDecimalUtil.round(d17, 0));
        nVO_BASELAND_RENT.setCr09((long)d15);
        nVO_BASELAND_RENT.setCr11((long)BigDecimalUtil.round(d18, 0));
        nVO_BASELAND_RENT.setCr12((long)BigDecimalUtil.round(d19, 0));
        nVO_BASELAND_RENT.setCr13((long)BigDecimalUtil.round(d20, 0));
        nVO_BASELAND_RENT.setCr15((long)BigDecimalUtil.round(d21, 0));
        nVO_BASELAND_RENT.setCr16((long)BigDecimalUtil.round(d22, 0));
        nVO_BASELAND_RENT.setCr19((long)BigDecimalUtil.round(d23, 0));
        double d24 = 0.0;
        double d25 = bl ? BigDecimalUtil.mul(d22, this.divHundred(nVO_BASELAND_REPORT_PARAM.getBen_manage_ratio())) : 0.0;
        double d26 = bl && d17 > 0.0 ? BigDecimalUtil.add(BigDecimalUtil.mul(d17, this.divHundred(nVO_BASELAND_REPORT_PARAM.getInsure_rate())), 1350.0) : (double)nVO_BASELAND_RENT.getCr23();
        double d27 = nVO_BASELAND_RENT.getCr25();
        d24 = BigDecimalUtil.round(d6 * d13 * this.divHundred(nVO_BASELAND_REPORT_PARAM.getReset_rate1()) * 1.0 / 20.0 * 20.0 * 2.0 / 50.0, 0);
        double d28 = this.div(BigDecimalUtil.sub(1.0, d4), d5);
        double d29 = bl ? BigDecimalUtil.round(this.div(d28, BigDecimalUtil.sub(1.0, BigDecimalUtil.mul(nVO_BASELAND_RENT.getCre25(), d28))), 4) : 0.0;
        double d30 = 0.0;
        d30 = BigDecimalUtil.add(d30, BigDecimalUtil.round(d25, 0));
        d30 = BigDecimalUtil.add(d30, BigDecimalUtil.round(d26, 0));
        d30 = BigDecimalUtil.add(d30, BigDecimalUtil.round(l4, 0));
        d30 = BigDecimalUtil.add(d30, BigDecimalUtil.round(l2, 0));
        d30 = BigDecimalUtil.add(d30, BigDecimalUtil.round(l3, 0));
        d30 = BigDecimalUtil.add(d30, BigDecimalUtil.round(d7, 0));
        d30 = BigDecimalUtil.add(d30, BigDecimalUtil.round(d27, 0));
        nVO_BASELAND_RENT.setCr22((long)BigDecimalUtil.round(d25, 0));
        nVO_BASELAND_RENT.setCr23((long)BigDecimalUtil.round(d26, 0));
        nVO_BASELAND_RENT.setCr27(this.mulHundred(d29));
        nVO_BASELAND_RENT.setCr28((long)BigDecimalUtil.round(d30, 0));
        nVO_BASELAND_RENT.setTmpcr25((long)BigDecimalUtil.round(d24, 0));
        double d31 = BigDecimalUtil.sub(d23, d30);
        double d32 = bl ? BigDecimalUtil.round(BigDecimalUtil.mul(d17, BigDecimalUtil.add(d29, d8)), 0) : 0.0;
        double d33 = BigDecimalUtil.sub(BigDecimalUtil.sub(d31, d9), d32);
        nVO_BASELAND_RENT.setCr29((long)BigDecimalUtil.round(d31, 0));
        nVO_BASELAND_RENT.setCr31((long)BigDecimalUtil.round(d32, 0));
        nVO_BASELAND_RENT.setCr34((long)BigDecimalUtil.round(d33, 0));
        double d34 = BigDecimalUtil.round(this.div(d33, d10), 0);
        double d35 = BigDecimalUtil.round(this.div(d34, d14), 0);
        double d36 = bl ? BigDecimalUtil.round(BigDecimalUtil.mul(d35, this.divHundred(nVO_BASELAND_RENT.getCr45())), 0) : d35;
        double d37 = bl ? BigDecimalUtil.round(BigDecimalUtil.mul(this.div(d11, d12), d36), 0) : d35;
        double d38 = BigDecimalUtil.div(d37, 0.3025);
        d37 = (long)StringProcess.roundCd(d37);
        d38 = (long)StringProcess.roundCd(d38);
        nVO_BASELAND_RENT.setCr40((long)BigDecimalUtil.round(d36, 0));
        nVO_BASELAND_RENT.setCr36((long)BigDecimalUtil.round(d34, 0));
        nVO_BASELAND_RENT.setCr37((long)BigDecimalUtil.round(d35, 0));
        nVO_BASELAND_RENT.setCr41((long)BigDecimalUtil.round(d38, 0));
        nVO_BASELAND_RENT.setCr38((long)BigDecimalUtil.round(d37, 0));
    }

    public void calRentExt(NVO_BASELAND_RENT_EXT nVO_BASELAND_RENT_EXT) {
        double d = nVO_BASELAND_RENT_EXT.getCre30();
        double d2 = nVO_BASELAND_RENT_EXT.getCre56();
        double d3 = BigDecimalUtil.div(nVO_BASELAND_RENT_EXT.getCre10(), 100.0);
        double d4 = BigDecimalUtil.div(nVO_BASELAND_RENT_EXT.getCre11(), 100.0);
        double d5 = BigDecimalUtil.div(nVO_BASELAND_RENT_EXT.getCre13(), 100.0);
        double d6 = BigDecimalUtil.div(nVO_BASELAND_RENT_EXT.getCre14(), 100.0);
        double d7 = BigDecimalUtil.div(nVO_BASELAND_RENT_EXT.getCre16(), 100.0);
        double d8 = BigDecimalUtil.div(nVO_BASELAND_RENT_EXT.getCre17(), 100.0);
        double d9 = BigDecimalUtil.div(nVO_BASELAND_RENT_EXT.getCre20(), 100.0);
        double d10 = nVO_BASELAND_RENT_EXT.getCre21();
        double d11 = BigDecimalUtil.div(nVO_BASELAND_RENT_EXT.getCre50(), 100.0);
        double d12 = nVO_BASELAND_RENT_EXT.getCre09();
        double d13 = BigDecimalUtil.div(nVO_BASELAND_RENT_EXT.getCre38(), 100.0);
        double d14 = BigDecimalUtil.div(nVO_BASELAND_RENT_EXT.getCre40(), 100.0);
        double d15 = BigDecimalUtil.div(nVO_BASELAND_RENT_EXT.getCre42(), 100.0);
        double d16 = nVO_BASELAND_RENT_EXT.getCre31();
        double d17 = BigDecimalUtil.div(nVO_BASELAND_RENT_EXT.getCre32(), 100.0);
        double d18 = BigDecimalUtil.div(nVO_BASELAND_RENT_EXT.getCre59(), 100.0);
        double d19 = BigDecimalUtil.div(nVO_BASELAND_RENT_EXT.getCre35(), 100.0);
        String string = nVO_BASELAND_RENT_EXT.getCre33();
        String string2 = nVO_BASELAND_RENT_EXT.getCre23();
        String string3 = nVO_BASELAND_RENT_EXT.getCre24();
        double d20 = nVO_BASELAND_RENT_EXT.getCre27();
        double d21 = BigDecimalUtil.div(nVO_BASELAND_RENT_EXT.getCre29(), 100.0);
        double d22 = nVO_BASELAND_RENT_EXT.getCre08();
        double d23 = BigDecimalUtil.mul(d3, d4);
        double d24 = BigDecimalUtil.mul(d5, d6);
        double d25 = BigDecimalUtil.mul(d7, d8);
        double d26 = BigDecimalUtil.round(BigDecimalUtil.add(BigDecimalUtil.add(d23, d24), d25), 4);
        double d27 = BigDecimalUtil.round(BigDecimalUtil.mul(BigDecimalUtil.mul(d26, d9), d10), 4);
        nVO_BASELAND_RENT_EXT.setCre12(BigDecimalUtil.round(this.mulHundred(d23), 2));
        nVO_BASELAND_RENT_EXT.setCre15(BigDecimalUtil.round(this.mulHundred(d24), 2));
        nVO_BASELAND_RENT_EXT.setCre18(BigDecimalUtil.round(this.mulHundred(d25), 2));
        nVO_BASELAND_RENT_EXT.setCre19(this.mulHundred(d26));
        nVO_BASELAND_RENT_EXT.setCre21(BigDecimalUtil.round(d12, 4));
        nVO_BASELAND_RENT_EXT.setCre22(this.mulHundred(d27));
        double d28 = d27;
        double d29 = BigDecimalUtil.add(d27, 1.0);
        double d30 = BigDecimalUtil.add(d11, 1.0);
        double d31 = BigDecimalUtil.add(BigDecimalUtil.add(d13, d14), d15);
        double d32 = BigDecimalUtil.round(BigDecimalUtil.mul(BigDecimalUtil.mul(d16, d17), d18), 0);
        double d33 = BigDecimalUtil.round(BigDecimalUtil.mul(d19, d32), 0);
        double d34 = BigDecimalUtil.add(d32, d33);
        double d35 = BigDecimalUtil.mul(BigDecimalUtil.mul(d29, d30), d34);
        double d36 = BigDecimalUtil.sub(1.0, BigDecimalUtil.mul(BigDecimalUtil.mul(d31, d29), d30));
        double d37 = BigDecimalUtil.round(this.div(BigDecimalUtil.mul(d13, d35), d36), 0);
        double d38 = BigDecimalUtil.round(this.div(BigDecimalUtil.mul(d14, d35), d36), 0);
        double d39 = BigDecimalUtil.round(this.div(BigDecimalUtil.mul(d15, d35), d36), 0);
        double d40 = BigDecimalUtil.add(BigDecimalUtil.add(BigDecimalUtil.add(d34, d37), d38), d39);
        double d41 = BigDecimalUtil.round(BigDecimalUtil.mul(d28, d40), 0);
        double d42 = BigDecimalUtil.add(d41, d40);
        double d43 = BigDecimalUtil.round(BigDecimalUtil.mul(d11, d42), 0);
        double d44 = BigDecimalUtil.add(BigDecimalUtil.add(d40, d41), d43);
        nVO_BASELAND_RENT_EXT.setCre46(this.mulHundred(d28));
        nVO_BASELAND_RENT_EXT.setCre48(this.mulHundred(d29));
        nVO_BASELAND_RENT_EXT.setCre52(this.mulHundred(d30));
        nVO_BASELAND_RENT_EXT.setCre44(this.mulHundred(d31));
        nVO_BASELAND_RENT_EXT.setCre34((long)BigDecimalUtil.round(d32, 0));
        nVO_BASELAND_RENT_EXT.setCre36((long)BigDecimalUtil.round(d33, 0));
        nVO_BASELAND_RENT_EXT.setCre37((long)BigDecimalUtil.round(d34, 0));
        nVO_BASELAND_RENT_EXT.setCre39((long)BigDecimalUtil.round(d37, 0));
        nVO_BASELAND_RENT_EXT.setCre41((long)BigDecimalUtil.round(d38, 0));
        nVO_BASELAND_RENT_EXT.setCre43((long)BigDecimalUtil.round(d39, 0));
        nVO_BASELAND_RENT_EXT.setCre45((long)BigDecimalUtil.round(d40, 0));
        nVO_BASELAND_RENT_EXT.setCre47((long)BigDecimalUtil.round(d41, 0));
        nVO_BASELAND_RENT_EXT.setCre49((long)BigDecimalUtil.round(d42, 0));
        nVO_BASELAND_RENT_EXT.setCre51((long)BigDecimalUtil.round(d43, 0));
        nVO_BASELAND_RENT_EXT.setCre53((long)BigDecimalUtil.round(d44, 0));
        int n = new AutoCalBaseLandSell().getBuildUndergoYear(string, string2, string3);
        if (d20 != 0.0) {
            d = BigDecimalUtil.mul(d44, BigDecimalUtil.sub(1.0, d21));
            d = BigDecimalUtil.div(d, d20);
            d = BigDecimalUtil.mul(d, n);
            d = BigDecimalUtil.round(d, 0);
        }
        double d45 = BigDecimalUtil.sub(d44, d);
        double d46 = BigDecimalUtil.mul(d22, d45);
        d46 = BigDecimalUtil.add(d2, d46);
        double d47 = BigDecimalUtil.mul(d32, 3.3058);
        nVO_BASELAND_RENT_EXT.setCre25(n);
        int n2 = nVO_BASELAND_RENT_EXT.getCre27() - nVO_BASELAND_RENT_EXT.getCre25();
        nVO_BASELAND_RENT_EXT.setCre26(n2);
        nVO_BASELAND_RENT_EXT.setCre30((long)BigDecimalUtil.round(d, 0));
        nVO_BASELAND_RENT_EXT.setCre54((long)BigDecimalUtil.round(d45, 0));
        nVO_BASELAND_RENT_EXT.setCre55((long)BigDecimalUtil.round(d46, 0));
        nVO_BASELAND_RENT_EXT.setCre57((long)BigDecimalUtil.roundDown(d47, 0));
    }

    public void sumBean(NVO_BASELAND_RENT_MONTH nVO_BASELAND_RENT_MONTH, NVO_BASELAND_RENT_MONTH nVO_BASELAND_RENT_MONTH2) {
        Field[] fieldArray;
        Class<?> clazz = nVO_BASELAND_RENT_MONTH.getClass();
        Class<?> clazz2 = nVO_BASELAND_RENT_MONTH.getClass();
        for (Field field : fieldArray = clazz.getDeclaredFields()) {
            String string = field.getName();
            String string2 = field.getType().getName();
            String string3 = StringProcess.setFustionString(string, false);
            String string4 = StringProcess.getFustionString(string, false);
            Method method = null;
            Method method2 = null;
            try {
                if (!string2.equals("com.wfusion.dataaccess.vo.DbInteger") && !string2.equals("com.wfusion.dataaccess.vo.DbDouble")) continue;
                method = clazz.getMethod(string4, new Class[0]);
                if (string2.equals("com.wfusion.dataaccess.vo.DbInteger")) {
                    method2 = clazz2.getMethod(string3, Integer.TYPE);
                } else {
                    if (!string2.equals("com.wfusion.dataaccess.vo.DbDouble")) continue;
                    method2 = clazz2.getMethod(string3, Double.TYPE);
                }
                double d = StringProcess.parserDouble(String.valueOf(method.invoke((Object)nVO_BASELAND_RENT_MONTH, new Object[0])));
                double d2 = d + StringProcess.parserDouble(String.valueOf(method.invoke((Object)nVO_BASELAND_RENT_MONTH2, new Object[0])));
                if (string2.equals("com.wfusion.dataaccess.vo.DbInteger")) {
                    method2.invoke((Object)nVO_BASELAND_RENT_MONTH2, (int)d2);
                    continue;
                }
                if (!string2.equals("com.wfusion.dataaccess.vo.DbDouble")) continue;
                method2.invoke((Object)nVO_BASELAND_RENT_MONTH2, d2);
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public int calRentMonth(TreeMap<String, NVO_BASELAND_RENT_MONTH> treeMap) {
        double d;
        double d2;
        NVO_BASELAND_RENT_MONTH nVO_BASELAND_RENT_MONTH;
        double d3 = 0.0;
        double d4 = 0.0;
        int n = 0;
        int n2 = 0;
        NVO_BASELAND_RENT_MONTH nVO_BASELAND_RENT_MONTH2 = new NVO_BASELAND_RENT_MONTH();
        for (String string : treeMap.keySet()) {
            NVO_BASELAND_RENT_MONTH nVO_BASELAND_RENT_MONTH3 = treeMap.get(string);
            nVO_BASELAND_RENT_MONTH3.setType_adj(this.divHundred(nVO_BASELAND_RENT_MONTH3.getType_adj()));
            nVO_BASELAND_RENT_MONTH3.setDate_adj(this.divHundred(nVO_BASELAND_RENT_MONTH3.getDate_adj()));
            nVO_BASELAND_RENT_MONTH3.setReg_adj(this.divHundred(nVO_BASELAND_RENT_MONTH3.getReg_adj()));
            nVO_BASELAND_RENT_MONTH3.setSpe_adj(this.divHundred(nVO_BASELAND_RENT_MONTH3.getSpe_adj()));
            n += StringProcess.parserInt(nVO_BASELAND_RENT_MONTH3.getRental_type());
            n2 += StringProcess.parserInt(nVO_BASELAND_RENT_MONTH3.getRent_date());
            if (nVO_BASELAND_RENT_MONTH3.getReg_adj() == 1.0) {
                nVO_BASELAND_RENT_MONTH3.setNear_adj(1);
            } else if (nVO_BASELAND_RENT_MONTH3.getReg_adj() > 0.0) {
                nVO_BASELAND_RENT_MONTH3.setNear_adj(2);
            } else {
                nVO_BASELAND_RENT_MONTH3.setNear_adj(0);
            }
            double d5 = Math.abs(BigDecimalUtil.sub(nVO_BASELAND_RENT_MONTH3.getType_adj(), 1.0));
            d5 = BigDecimalUtil.add(d5, Math.abs(BigDecimalUtil.sub(nVO_BASELAND_RENT_MONTH3.getDate_adj(), 1.0)));
            d5 = BigDecimalUtil.add(d5, Math.abs(BigDecimalUtil.sub(nVO_BASELAND_RENT_MONTH3.getReg_adj(), 1.0)));
            d5 = BigDecimalUtil.add(d5, Math.abs(BigDecimalUtil.sub(nVO_BASELAND_RENT_MONTH3.getSpe_adj(), 1.0)));
            int n3 = 0;
            if (nVO_BASELAND_RENT_MONTH3.getType_adj() == 1.0) {
                ++n3;
            }
            if (nVO_BASELAND_RENT_MONTH3.getDate_adj() == 1.0) {
                ++n3;
            }
            if (nVO_BASELAND_RENT_MONTH3.getReg_adj() == 1.0) {
                ++n3;
            }
            if (nVO_BASELAND_RENT_MONTH3.getSpe_adj() == 1.0) {
                ++n3;
            }
            int n4 = 4 - n3;
            nVO_BASELAND_RENT_MONTH3.setAbs_adj(BigDecimalUtil.round(d5, 4));
            nVO_BASELAND_RENT_MONTH3.setCompare_items(n4);
            this.sumBean(nVO_BASELAND_RENT_MONTH3, nVO_BASELAND_RENT_MONTH2);
        }
        double d6 = BigDecimalUtil.div(n, 3.0);
        double d7 = BigDecimalUtil.div(n2, 3.0);
        double d8 = BigDecimalUtil.div(nVO_BASELAND_RENT_MONTH2.getNear_adj(), 3.0);
        double d9 = BigDecimalUtil.div(nVO_BASELAND_RENT_MONTH2.getAbs_adj(), 3.0);
        double d10 = BigDecimalUtil.div(nVO_BASELAND_RENT_MONTH2.getCompare_items(), 3.0);
        double d11 = 0.0;
        for (String string : treeMap.keySet()) {
            nVO_BASELAND_RENT_MONTH = treeMap.get(string);
            d2 = 0.0;
            d = 0.0;
            double d12 = 0.0;
            double d13 = 0.0;
            double d14 = 0.0;
            if (d6 > 0.0) {
                d2 = BigDecimalUtil.mul(BigDecimalUtil.div(StringProcess.parserInt(nVO_BASELAND_RENT_MONTH.getRental_type()), d6), BASELAND_RENT_MONTH_A_AHP);
            }
            if (d7 > 0.0) {
                d = BigDecimalUtil.mul(BigDecimalUtil.div(StringProcess.parserInt(nVO_BASELAND_RENT_MONTH.getRent_date()), d7), BASELAND_RENT_MONTH_B_AHP);
            }
            if (d8 > 0.0) {
                d12 = BigDecimalUtil.mul(BigDecimalUtil.div(nVO_BASELAND_RENT_MONTH.getNear_adj(), d8), BASELAND_RENT_MONTH_C_AHP);
            }
            if (d9 > 0.0) {
                d13 = BigDecimalUtil.mul(BigDecimalUtil.div(nVO_BASELAND_RENT_MONTH.getAbs_adj(), d9), BASELAND_RENT_MONTH_D_AHP);
            }
            if (d10 > 0.0) {
                d14 = BigDecimalUtil.mul(BigDecimalUtil.div(nVO_BASELAND_RENT_MONTH.getCompare_items(), d10), BASELAND_RENT_MONTH_E_AHP);
            }
            double d15 = d2 + d + d12 + d13 + d14;
            double d16 = BigDecimalUtil.mul(nVO_BASELAND_RENT_MONTH.getRental(), nVO_BASELAND_RENT_MONTH.getType_adj());
            d16 = BigDecimalUtil.mul(d16, nVO_BASELAND_RENT_MONTH.getDate_adj());
            d16 = BigDecimalUtil.mul(d16, nVO_BASELAND_RENT_MONTH.getReg_adj());
            d16 = BigDecimalUtil.mul(d16, nVO_BASELAND_RENT_MONTH.getSpe_adj());
            nVO_BASELAND_RENT_MONTH.setCal_cr09((int)BigDecimalUtil.round(d16, 0));
            double d17 = this.divHundred(nVO_BASELAND_RENT_MONTH.getWeight());
            d4 = BigDecimalUtil.add(d4, d17);
            d3 = BigDecimalUtil.add(d3, BigDecimalUtil.mul(d16, d17));
            d11 = BigDecimalUtil.add(d11, d15);
            nVO_BASELAND_RENT_MONTH.setAvg_adj(d15);
        }
        if (d4 != 1.0) {
            d3 = 0.0;
        }
        d11 = BigDecimalUtil.div(d11, 3.0);
        for (String string : treeMap.keySet()) {
            nVO_BASELAND_RENT_MONTH = treeMap.get(string);
            d2 = 0.0;
            d = Math.abs(BigDecimalUtil.sub(d11, nVO_BASELAND_RENT_MONTH.getAvg_adj()));
            d2 = nVO_BASELAND_RENT_MONTH.getAvg_adj() < d11 ? 1.0 + BigDecimalUtil.div(d, d11) : 1.0 - BigDecimalUtil.div(d, d11);
            d2 = BigDecimalUtil.round(BigDecimalUtil.mul(BigDecimalUtil.div(1.0, 3.0), d2), 4);
            nVO_BASELAND_RENT_MONTH.setAvg_adj(this.mulHundred(BigDecimalUtil.round(nVO_BASELAND_RENT_MONTH.getAvg_adj(), 4)));
            nVO_BASELAND_RENT_MONTH.setCal_weight(this.mulHundred(d2));
            nVO_BASELAND_RENT_MONTH.setAbs_adj(this.mulHundred(nVO_BASELAND_RENT_MONTH.getAbs_adj()));
            nVO_BASELAND_RENT_MONTH.setType_adj(this.mulHundred(nVO_BASELAND_RENT_MONTH.getType_adj()));
            nVO_BASELAND_RENT_MONTH.setDate_adj(this.mulHundred(nVO_BASELAND_RENT_MONTH.getDate_adj()));
            nVO_BASELAND_RENT_MONTH.setReg_adj(this.mulHundred(nVO_BASELAND_RENT_MONTH.getReg_adj()));
            nVO_BASELAND_RENT_MONTH.setSpe_adj(this.mulHundred(nVO_BASELAND_RENT_MONTH.getSpe_adj()));
        }
        return (int)BigDecimalUtil.round(d3, 0);
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

    public double div(double d, double d2) {
        double d3 = 0.0;
        if (d2 != 0.0) {
            d3 = BigDecimalUtil.div(d, d2);
        }
        return d3;
    }
}

