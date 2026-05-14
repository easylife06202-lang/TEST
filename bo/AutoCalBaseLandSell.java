/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.bo;

import com.wfusion.util.BigDecimalUtil;
import com.wfusion.util.StringProcess;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_SELL;

public class AutoCalBaseLandSell {
    private NVO_BASELAND_SELL vo = new NVO_BASELAND_SELL();
    BigDecimalUtil bde = new BigDecimalUtil();
    boolean debugOut = true;
    boolean isLand = false;
    boolean isAllBuild = false;
    boolean isParkBuild = false;
    double cs11;
    double cs14;
    double cs17;
    double cs18;
    int cs22;
    int cs23;
    double cs27;
    double cs31;
    double cs33;
    double cs35;
    double cs37;
    double cs39;
    double cs40;
    double cs41;
    double cs43;
    double cs44;
    double cs45;
    double cs47;
    double cs48;
    double cs50;
    double cs51;
    double cs54;
    double cs55;

    public AutoCalBaseLandSell() {
    }

    public AutoCalBaseLandSell(NVO_BASELAND_SELL nVO_BASELAND_SELL) {
        this.vo = nVO_BASELAND_SELL;
        nVO_BASELAND_SELL.setCs25("\u5b9a\u984d\u6cd5");
        String string = nVO_BASELAND_SELL.getSelltype();
        if (string.equals("4")) {
            this.isLand = true;
        } else if (string.equals("5")) {
            this.isParkBuild = true;
        } else if (string.equals("6")) {
            this.isAllBuild = true;
        }
        if (this.isLand || this.isParkBuild || this.isAllBuild) {
            this.calFundsRate();
            this.calDepreciation();
            this.calCostPrice();
            this.calCaseSellSumPrice();
        }
    }

    public NVO_BASELAND_SELL getCaseSellVo() {
        return this.vo;
    }

    private void calDepreciation() {
        if (this.isParkBuild || this.isAllBuild) {
            this.cs22 = this.getBuildUndergoYear(this.vo.getCs30(), this.vo.getCs20(), this.vo.getCs21());
            this.vo.setCs22(this.cs22);
            if (this.vo.getCs24() > 0) {
                this.cs23 = this.vo.getCs24() - this.vo.getCs22();
            }
            this.vo.setCs23(this.cs23);
        }
    }

    private void calFundsRate() {
        if (this.isParkBuild || this.isAllBuild) {
            double d = BigDecimalUtil.mul(this.divHundred(this.vo.getCs09()), this.divHundred(this.vo.getCs10()));
            this.vo.setCs11(this.mulHundred(BigDecimalUtil.round(d, 4)));
            double d2 = BigDecimalUtil.mul(this.divHundred(this.vo.getCs12()), this.divHundred(this.vo.getCs13()));
            this.vo.setCs14(this.mulHundred(BigDecimalUtil.round(d2, 4)));
            double d3 = BigDecimalUtil.mul(this.divHundred(this.vo.getCs15()), this.divHundred(this.vo.getCs16()));
            this.vo.setCs17(this.mulHundred(BigDecimalUtil.round(d3, 4)));
            double d4 = BigDecimalUtil.add(BigDecimalUtil.add(d, d2), d3);
            this.vo.setCs18(this.mulHundred(BigDecimalUtil.round(d4, 4)));
        }
    }

    private void calCostPrice() {
        if (this.isParkBuild || this.isAllBuild) {
            double d = this.divHundred(this.vo.getCs18());
            double d2 = this.divHundred(this.vo.getCs19());
            double d3 = this.divHundred(this.vo.getCs29());
            double d4 = this.divHundred(this.vo.getCs32());
            double d5 = this.divHundred(this.vo.getCs34());
            double d6 = this.divHundred(this.vo.getCs36());
            double d7 = this.divHundred(this.vo.getCs38());
            double d8 = this.divHundred(this.vo.getCs42());
            double d9 = this.divHundred(this.vo.getCs66());
            this.cs31 = BigDecimalUtil.round(BigDecimalUtil.mul(BigDecimalUtil.mul(this.vo.getCs28(), d3), d9), 0);
            this.cs33 = BigDecimalUtil.mul(this.cs31, d4);
            this.cs40 = BigDecimalUtil.round(BigDecimalUtil.mul(BigDecimalUtil.mul(this.vo.getCs08(), d), d2), 4);
            double d10 = BigDecimalUtil.add(1.0, this.cs40);
            double d11 = BigDecimalUtil.add(1.0, d8);
            double d12 = BigDecimalUtil.mul(d10, d11);
            double d13 = BigDecimalUtil.mul(d12, BigDecimalUtil.add(this.cs31, this.cs33));
            double d14 = BigDecimalUtil.add(d5, BigDecimalUtil.add(d6, d7));
            d14 = BigDecimalUtil.mul(d14, d12);
            if ((d14 = BigDecimalUtil.sub(1.0, d14)) > 0.0) {
                this.cs35 = BigDecimalUtil.div(BigDecimalUtil.mul(d13, d5), d14);
                this.cs37 = BigDecimalUtil.div(BigDecimalUtil.mul(d13, d6), d14);
                this.cs39 = BigDecimalUtil.div(BigDecimalUtil.mul(d13, d7), d14);
            }
            double d15 = BigDecimalUtil.round(BigDecimalUtil.add(this.cs31, this.cs33), 0);
            d15 = BigDecimalUtil.round(BigDecimalUtil.add(d15, this.cs35), 0);
            d15 = BigDecimalUtil.round(BigDecimalUtil.add(d15, this.cs37), 0);
            d15 = BigDecimalUtil.round(BigDecimalUtil.add(d15, this.cs39), 0);
            if (this.cs40 > 0.0) {
                this.cs41 = BigDecimalUtil.round(BigDecimalUtil.mul(d15, this.cs40), 0);
            }
            d15 = BigDecimalUtil.round(BigDecimalUtil.add(d15, this.cs41), 0);
            if (d8 > 0.0) {
                this.cs43 = BigDecimalUtil.round(BigDecimalUtil.mul(d15, d8), 0);
            }
            this.cs44 = BigDecimalUtil.round(BigDecimalUtil.add(d15, this.cs43), 0);
            double d16 = BigDecimalUtil.sub(1.0, this.divHundred(this.vo.getCs26()));
            double d17 = this.vo.getCs24();
            double d18 = BigDecimalUtil.mul(BigDecimalUtil.mul(this.cs44, d16), (double)this.cs22 > d17 ? d17 : (double)this.cs22);
            if (this.vo.getCs24() > 0) {
                this.cs27 = BigDecimalUtil.round(BigDecimalUtil.div(d18, d17), 0);
            }
            this.cs45 = BigDecimalUtil.sub(this.cs44, this.cs27);
            this.vo.setCs27((long)BigDecimalUtil.round(this.cs27, 0));
            this.vo.setCs31((long)BigDecimalUtil.round(this.cs31, 0));
            this.vo.setCs33((long)BigDecimalUtil.round(this.cs33, 0));
            this.vo.setCs35((long)BigDecimalUtil.round(this.cs35, 0));
            this.vo.setCs37((long)BigDecimalUtil.round(this.cs37, 0));
            this.vo.setCs39((long)BigDecimalUtil.round(this.cs39, 0));
            this.vo.setCs40(BigDecimalUtil.round(this.mulHundred(this.cs40), 2));
            this.vo.setCs41((long)BigDecimalUtil.round(this.cs41, 0));
            this.vo.setCs43((long)BigDecimalUtil.round(this.cs43, 0));
            this.vo.setCs44((long)BigDecimalUtil.round(this.cs44, 0));
            this.vo.setCs45((long)BigDecimalUtil.round(this.cs45, 0));
        }
    }

    private void calCaseSellSumPrice() {
        double d;
        double d2 = this.divHundred(this.vo.getCs64());
        double d3 = this.divHundred(this.vo.getCs65());
        this.cs47 = this.vo.getCs46();
        this.cs47 -= (double)this.vo.getCs53();
        if (this.isParkBuild || this.isAllBuild) {
            this.cs48 = BigDecimalUtil.round(BigDecimalUtil.mul(this.cs45, this.vo.getCs07()), 0) + (double)this.vo.getCs59();
        }
        if (this.vo.getCs49() > 0.0 && this.isParkBuild) {
            this.cs50 = BigDecimalUtil.div(this.cs47 - this.cs48, this.vo.getCs49());
        }
        if (this.vo.getCs49() > 0.0 && this.isLand) {
            this.cs51 = BigDecimalUtil.div(this.cs47, this.vo.getCs49());
        }
        if (this.vo.getCs49() > 0.0 && this.isLand) {
            this.cs51 = BigDecimalUtil.div(this.cs47, this.vo.getCs49());
        }
        if (this.vo.getCs49() > 0.0 && this.isAllBuild) {
            this.cs51 = BigDecimalUtil.div(this.cs47 - this.cs48, this.vo.getCs49());
        }
        if (this.isParkBuild && d3 > 0.0) {
            d = BigDecimalUtil.mul(this.cs50, d2);
            this.cs51 = BigDecimalUtil.div(d, d3);
        }
        this.vo.setCs47((long)BigDecimalUtil.round(this.cs47, 0));
        this.vo.setCs48((long)BigDecimalUtil.round(this.cs48, 0));
        this.vo.setCs50((long)BigDecimalUtil.round(this.cs50, 0));
        this.vo.setCs51((long)BigDecimalUtil.round(this.cs51, 0));
        if (this.isParkBuild || this.isAllBuild) {
            d = 3.3058;
            if (this.vo.getCs07() > 0.0) {
                double d4;
                this.cs54 = d4 = BigDecimalUtil.mul(BigDecimalUtil.div(this.cs47, this.vo.getCs07()), d);
            }
            this.cs55 = BigDecimalUtil.mul(this.cs31, d);
            this.vo.setCs54((long)BigDecimalUtil.round(BigDecimalUtil.div(this.cs54, 100.0), 0) * 100L);
            this.vo.setCs55((long)BigDecimalUtil.round(this.cs55, 0));
        }
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

    public int getBuildUndergoYear(String string, String string2, String string3) {
        double d = 0.0;
        if (!string2.equals("") && !string3.equals("")) {
            if (string.length() == 7 && !string.equals("")) {
                int n = StringProcess.parserInt(string.substring(0, 3));
                int n2 = StringProcess.parserInt(string.substring(3, 5));
                int n3 = StringProcess.parserInt(string2);
                int n4 = StringProcess.parserInt(string3);
                if (n2 < n4) {
                    --n;
                }
                int n5 = n * 1 - n3 * 1;
                int n6 = n2 * 1 - n4 * 1;
                if (n6 < 0) {
                    n6 = n6 * 1 + 12;
                }
                if ((d = BigDecimalUtil.add(n5, BigDecimalUtil.div(n6, 12.0))) < 0.0) {
                    System.out.println("\u5efa\u7bc9\u5e74\u6708\u4e0d\u5f97\u5927\u65bc\u4ea4\u6613\u65e5\u671f");
                    d = 0.0;
                }
            } else {
                System.out.println("\u4ea4\u6613\u65e5\u671f\u932f\u8aa4");
            }
        }
        return (int)BigDecimalUtil.round(d, 0);
    }
}

