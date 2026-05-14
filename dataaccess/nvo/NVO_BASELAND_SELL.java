/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.dataaccess.nvo;

import com.wfusion.dataaccess.vo.DbDouble;
import com.wfusion.dataaccess.vo.DbElement;
import com.wfusion.dataaccess.vo.DbInteger;
import com.wfusion.dataaccess.vo.DbLong;
import com.wfusion.dataaccess.vo.DbString;
import com.wfusion.dataaccess.vo.VoBase;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class NVO_BASELAND_SELL
extends VoBase
implements Serializable,
Cloneable {
    private static final long serialVersionUID = 1128604108L;
    private DbString baseno = new DbString("baseno");
    private DbString year = new DbString("year");
    private DbString caseno = new DbString("caseno");
    private DbString city = new DbString("city");
    private DbString dist = new DbString("dist");
    private DbString aa48 = new DbString("aa48");
    private DbString aa49 = new DbString("aa49");
    private DbDouble aa10 = new DbDouble("aa10");
    private DbString land_position = new DbString("land_position");
    private DbString selltype = new DbString("selltype", "4");
    private DbString ed49 = new DbString("ed49");
    private DbString p1ma_caseno = new DbString("p1ma_caseno");
    private DbString cs01 = new DbString("cs01");
    private DbString cs02 = new DbString("cs02");
    private DbString cs03 = new DbString("cs03");
    private DbString cs04 = new DbString("cs04");
    private DbString cs04f = new DbString("cs04f");
    private DbString cs05 = new DbString("cs05");
    private DbDouble cs06 = new DbDouble("cs06");
    private DbDouble cs07 = new DbDouble("cs07");
    private DbDouble cs08 = new DbDouble("cs08");
    private DbDouble cs09 = new DbDouble("cs09");
    private DbDouble cs10 = new DbDouble("cs10");
    private DbDouble cs11 = new DbDouble("cs11");
    private DbDouble cs12 = new DbDouble("cs12");
    private DbDouble cs13 = new DbDouble("cs13");
    private DbDouble cs14 = new DbDouble("cs14");
    private DbDouble cs15 = new DbDouble("cs15");
    private DbDouble cs16 = new DbDouble("cs16");
    private DbDouble cs17 = new DbDouble("cs17");
    private DbDouble cs18 = new DbDouble("cs18");
    private DbDouble cs19 = new DbDouble("cs19");
    private DbString cs20 = new DbString("cs20");
    private DbString cs21 = new DbString("cs21");
    private DbInteger cs22 = new DbInteger("cs22");
    private DbInteger cs23 = new DbInteger("cs23");
    private DbInteger cs24 = new DbInteger("cs24");
    private DbString cs25 = new DbString("cs25");
    private DbDouble cs26 = new DbDouble("cs26");
    private DbLong cs27 = new DbLong("cs27");
    private DbLong cs28 = new DbLong("cs28");
    private DbDouble cs29 = new DbDouble("cs29");
    private DbString cs30 = new DbString("cs30");
    private DbLong cs31 = new DbLong("cs31");
    private DbDouble cs32 = new DbDouble("cs32");
    private DbLong cs33 = new DbLong("cs33");
    private DbDouble cs34 = new DbDouble("cs34");
    private DbLong cs35 = new DbLong("cs35");
    private DbDouble cs36 = new DbDouble("cs36");
    private DbLong cs37 = new DbLong("cs37");
    private DbDouble cs38 = new DbDouble("cs38");
    private DbLong cs39 = new DbLong("cs39");
    private DbDouble cs40 = new DbDouble("cs40");
    private DbLong cs41 = new DbLong("cs41");
    private DbDouble cs42 = new DbDouble("cs42");
    private DbLong cs43 = new DbLong("cs43");
    private DbLong cs44 = new DbLong("cs44");
    private DbLong cs45 = new DbLong("cs45");
    private DbLong cs46 = new DbLong("cs46");
    private DbLong cs47 = new DbLong("cs47");
    private DbLong cs48 = new DbLong("cs48");
    private DbDouble cs49 = new DbDouble("cs49");
    private DbLong cs50 = new DbLong("cs50");
    private DbLong cs51 = new DbLong("cs51");
    private DbString cs52 = new DbString("cs52", "\u505c\u8eca\u4f4d\u7e3d\u50f9\u683c(\u5143)");
    private DbLong cs53 = new DbLong("cs53");
    private DbLong cs54 = new DbLong("cs54");
    private DbLong cs55 = new DbLong("cs55");
    private DbString cs56 = new DbString("cs56");
    private DbString cs57 = new DbString("cs57");
    private DbString cs58 = new DbString("cs58", "\u589e\u5efa\u6210\u672c(\u5143)");
    private DbLong cs59 = new DbLong("cs59");
    private DbDouble cs64 = new DbDouble("cs64");
    private DbDouble cs65 = new DbDouble("cs65");
    private DbDouble cs66 = new DbDouble("cs66");

    public NVO_BASELAND_SELL() {
        this.tableName = "baseland_sell";
        super.setFieldCount(75);
        this.elems = new DbElement[75];
        this.elems[0] = this.baseno;
        this.elems[1] = this.year;
        this.elems[2] = this.caseno;
        this.elems[3] = this.city;
        this.elems[4] = this.dist;
        this.elems[5] = this.aa48;
        this.elems[6] = this.aa49;
        this.elems[7] = this.aa10;
        this.elems[8] = this.land_position;
        this.elems[9] = this.selltype;
        this.elems[10] = this.ed49;
        this.elems[11] = this.p1ma_caseno;
        this.elems[12] = this.cs01;
        this.elems[13] = this.cs02;
        this.elems[14] = this.cs03;
        this.elems[15] = this.cs04;
        this.elems[16] = this.cs04f;
        this.elems[17] = this.cs05;
        this.elems[18] = this.cs06;
        this.elems[19] = this.cs07;
        this.elems[20] = this.cs08;
        this.elems[21] = this.cs09;
        this.elems[22] = this.cs10;
        this.elems[23] = this.cs11;
        this.elems[24] = this.cs12;
        this.elems[25] = this.cs13;
        this.elems[26] = this.cs14;
        this.elems[27] = this.cs15;
        this.elems[28] = this.cs16;
        this.elems[29] = this.cs17;
        this.elems[30] = this.cs18;
        this.elems[31] = this.cs19;
        this.elems[32] = this.cs20;
        this.elems[33] = this.cs21;
        this.elems[34] = this.cs22;
        this.elems[35] = this.cs23;
        this.elems[36] = this.cs24;
        this.elems[37] = this.cs25;
        this.elems[38] = this.cs26;
        this.elems[39] = this.cs27;
        this.elems[40] = this.cs28;
        this.elems[41] = this.cs29;
        this.elems[42] = this.cs30;
        this.elems[43] = this.cs31;
        this.elems[44] = this.cs32;
        this.elems[45] = this.cs33;
        this.elems[46] = this.cs34;
        this.elems[47] = this.cs35;
        this.elems[48] = this.cs36;
        this.elems[49] = this.cs37;
        this.elems[50] = this.cs38;
        this.elems[51] = this.cs39;
        this.elems[52] = this.cs40;
        this.elems[53] = this.cs41;
        this.elems[54] = this.cs42;
        this.elems[55] = this.cs43;
        this.elems[56] = this.cs44;
        this.elems[57] = this.cs45;
        this.elems[58] = this.cs46;
        this.elems[59] = this.cs47;
        this.elems[60] = this.cs48;
        this.elems[61] = this.cs49;
        this.elems[62] = this.cs50;
        this.elems[63] = this.cs51;
        this.elems[64] = this.cs52;
        this.elems[65] = this.cs53;
        this.elems[66] = this.cs54;
        this.elems[67] = this.cs55;
        this.elems[68] = this.cs56;
        this.elems[69] = this.cs57;
        this.elems[70] = this.cs58;
        this.elems[71] = this.cs59;
        this.elems[72] = this.cs64;
        this.elems[73] = this.cs65;
        this.elems[74] = this.cs66;
        this.baseno.setPkFlag(true);
        this.year.setPkFlag(true);
        this.caseno.setPkFlag(true);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
    }

    public String getBaseno() {
        return this.baseno.getValue();
    }

    public String getYear() {
        return this.year.getValue();
    }

    public String getCaseno() {
        return this.caseno.getValue();
    }

    public String getCity() {
        return this.city.getValue();
    }

    public String getDist() {
        return this.dist.getValue();
    }

    public String getAa48() {
        return this.aa48.getValue();
    }

    public String getAa49() {
        return this.aa49.getValue();
    }

    public double getAa10() {
        return this.aa10.getValue();
    }

    public String getLand_position() {
        return this.land_position.getValue();
    }

    public String getSelltype() {
        return this.selltype.getValue();
    }

    public String getEd49() {
        return this.ed49.getValue();
    }

    public String getP1ma_caseno() {
        return this.p1ma_caseno.getValue();
    }

    public String getCs01() {
        return this.cs01.getValue();
    }

    public String getCs02() {
        return this.cs02.getValue();
    }

    public String getCs03() {
        return this.cs03.getValue();
    }

    public String getCs04() {
        return this.cs04.getValue();
    }

    public String getCs04f() {
        return this.cs04f.getValue();
    }

    public String getCs05() {
        return this.cs05.getValue();
    }

    public double getCs06() {
        return this.cs06.getValue();
    }

    public double getCs07() {
        return this.cs07.getValue();
    }

    public double getCs08() {
        return this.cs08.getValue();
    }

    public double getCs09() {
        return this.cs09.getValue();
    }

    public double getCs10() {
        return this.cs10.getValue();
    }

    public double getCs11() {
        return this.cs11.getValue();
    }

    public double getCs12() {
        return this.cs12.getValue();
    }

    public double getCs13() {
        return this.cs13.getValue();
    }

    public double getCs14() {
        return this.cs14.getValue();
    }

    public double getCs15() {
        return this.cs15.getValue();
    }

    public double getCs16() {
        return this.cs16.getValue();
    }

    public double getCs17() {
        return this.cs17.getValue();
    }

    public double getCs18() {
        return this.cs18.getValue();
    }

    public double getCs19() {
        return this.cs19.getValue();
    }

    public String getCs20() {
        return this.cs20.getValue();
    }

    public String getCs21() {
        return this.cs21.getValue();
    }

    public int getCs22() {
        return this.cs22.getValue();
    }

    public int getCs23() {
        return this.cs23.getValue();
    }

    public int getCs24() {
        return this.cs24.getValue();
    }

    public String getCs25() {
        return this.cs25.getValue();
    }

    public double getCs26() {
        return this.cs26.getValue();
    }

    public long getCs27() {
        return this.cs27.getValue();
    }

    public long getCs28() {
        return this.cs28.getValue();
    }

    public double getCs29() {
        return this.cs29.getValue();
    }

    public String getCs30() {
        return this.cs30.getValue();
    }

    public long getCs31() {
        return this.cs31.getValue();
    }

    public double getCs32() {
        return this.cs32.getValue();
    }

    public long getCs33() {
        return this.cs33.getValue();
    }

    public double getCs34() {
        return this.cs34.getValue();
    }

    public long getCs35() {
        return this.cs35.getValue();
    }

    public double getCs36() {
        return this.cs36.getValue();
    }

    public long getCs37() {
        return this.cs37.getValue();
    }

    public double getCs38() {
        return this.cs38.getValue();
    }

    public long getCs39() {
        return this.cs39.getValue();
    }

    public double getCs40() {
        return this.cs40.getValue();
    }

    public long getCs41() {
        return this.cs41.getValue();
    }

    public double getCs42() {
        return this.cs42.getValue();
    }

    public long getCs43() {
        return this.cs43.getValue();
    }

    public long getCs44() {
        return this.cs44.getValue();
    }

    public long getCs45() {
        return this.cs45.getValue();
    }

    public long getCs46() {
        return this.cs46.getValue();
    }

    public long getCs47() {
        return this.cs47.getValue();
    }

    public long getCs48() {
        return this.cs48.getValue();
    }

    public double getCs49() {
        return this.cs49.getValue();
    }

    public long getCs50() {
        return this.cs50.getValue();
    }

    public long getCs51() {
        return this.cs51.getValue();
    }

    public String getCs52() {
        return this.cs52.getValue();
    }

    public long getCs53() {
        return this.cs53.getValue();
    }

    public long getCs54() {
        return this.cs54.getValue();
    }

    public long getCs55() {
        return this.cs55.getValue();
    }

    public String getCs56() {
        return this.cs56.getValue();
    }

    public String getCs57() {
        return this.cs57.getValue();
    }

    public String getCs58() {
        return this.cs58.getValue();
    }

    public long getCs59() {
        return this.cs59.getValue();
    }

    public double getCs64() {
        return this.cs64.getValue();
    }

    public double getCs65() {
        return this.cs65.getValue();
    }

    public double getCs66() {
        return this.cs66.getValue();
    }

    public void setBaseno(String string) {
        this.baseno.setValue(string);
    }

    public void setYear(String string) {
        this.year.setValue(string);
    }

    public void setCaseno(String string) {
        this.caseno.setValue(string);
    }

    public void setCity(String string) {
        this.city.setValue(string);
    }

    public void setDist(String string) {
        this.dist.setValue(string);
    }

    public void setAa48(String string) {
        this.aa48.setValue(string);
    }

    public void setAa49(String string) {
        this.aa49.setValue(string);
    }

    public void setAa10(double d) {
        this.aa10.setValue(d);
    }

    public void setLand_position(String string) {
        this.land_position.setValue(string);
    }

    public void setSelltype(String string) {
        this.selltype.setValue(string);
    }

    public void setEd49(String string) {
        this.ed49.setValue(string);
    }

    public void setP1ma_caseno(String string) {
        this.p1ma_caseno.setValue(string);
    }

    public void setCs01(String string) {
        this.cs01.setValue(string);
    }

    public void setCs02(String string) {
        this.cs02.setValue(string);
    }

    public void setCs03(String string) {
        this.cs03.setValue(string);
    }

    public void setCs04(String string) {
        this.cs04.setValue(string);
    }

    public void setCs04f(String string) {
        this.cs04f.setValue(string);
    }

    public void setCs05(String string) {
        this.cs05.setValue(string);
    }

    public void setCs06(double d) {
        this.cs06.setValue(d);
    }

    public void setCs07(double d) {
        this.cs07.setValue(d);
    }

    public void setCs08(double d) {
        this.cs08.setValue(d);
    }

    public void setCs09(double d) {
        this.cs09.setValue(d);
    }

    public void setCs10(double d) {
        this.cs10.setValue(d);
    }

    public void setCs11(double d) {
        this.cs11.setValue(d);
    }

    public void setCs12(double d) {
        this.cs12.setValue(d);
    }

    public void setCs13(double d) {
        this.cs13.setValue(d);
    }

    public void setCs14(double d) {
        this.cs14.setValue(d);
    }

    public void setCs15(double d) {
        this.cs15.setValue(d);
    }

    public void setCs16(double d) {
        this.cs16.setValue(d);
    }

    public void setCs17(double d) {
        this.cs17.setValue(d);
    }

    public void setCs18(double d) {
        this.cs18.setValue(d);
    }

    public void setCs19(double d) {
        this.cs19.setValue(d);
    }

    public void setCs20(String string) {
        this.cs20.setValue(string);
    }

    public void setCs21(String string) {
        this.cs21.setValue(string);
    }

    public void setCs22(int n) {
        this.cs22.setValue(n);
    }

    public void setCs23(int n) {
        this.cs23.setValue(n);
    }

    public void setCs24(int n) {
        this.cs24.setValue(n);
    }

    public void setCs25(String string) {
        this.cs25.setValue(string);
    }

    public void setCs26(double d) {
        this.cs26.setValue(d);
    }

    public void setCs27(long l) {
        this.cs27.setValue(l);
    }

    public void setCs28(long l) {
        this.cs28.setValue(l);
    }

    public void setCs29(double d) {
        this.cs29.setValue(d);
    }

    public void setCs30(String string) {
        this.cs30.setValue(string);
    }

    public void setCs31(long l) {
        this.cs31.setValue(l);
    }

    public void setCs32(double d) {
        this.cs32.setValue(d);
    }

    public void setCs33(long l) {
        this.cs33.setValue(l);
    }

    public void setCs34(double d) {
        this.cs34.setValue(d);
    }

    public void setCs35(long l) {
        this.cs35.setValue(l);
    }

    public void setCs36(double d) {
        this.cs36.setValue(d);
    }

    public void setCs37(long l) {
        this.cs37.setValue(l);
    }

    public void setCs38(double d) {
        this.cs38.setValue(d);
    }

    public void setCs39(long l) {
        this.cs39.setValue(l);
    }

    public void setCs40(double d) {
        this.cs40.setValue(d);
    }

    public void setCs41(long l) {
        this.cs41.setValue(l);
    }

    public void setCs42(double d) {
        this.cs42.setValue(d);
    }

    public void setCs43(long l) {
        this.cs43.setValue(l);
    }

    public void setCs44(long l) {
        this.cs44.setValue(l);
    }

    public void setCs45(long l) {
        this.cs45.setValue(l);
    }

    public void setCs46(long l) {
        this.cs46.setValue(l);
    }

    public void setCs47(long l) {
        this.cs47.setValue(l);
    }

    public void setCs48(long l) {
        this.cs48.setValue(l);
    }

    public void setCs49(double d) {
        this.cs49.setValue(d);
    }

    public void setCs50(long l) {
        this.cs50.setValue(l);
    }

    public void setCs51(long l) {
        this.cs51.setValue(l);
    }

    public void setCs52(String string) {
        this.cs52.setValue(string);
    }

    public void setCs53(long l) {
        this.cs53.setValue(l);
    }

    public void setCs54(long l) {
        this.cs54.setValue(l);
    }

    public void setCs55(long l) {
        this.cs55.setValue(l);
    }

    public void setCs56(String string) {
        this.cs56.setValue(string);
    }

    public void setCs57(String string) {
        this.cs57.setValue(string);
    }

    public void setCs58(String string) {
        this.cs58.setValue(string);
    }

    public void setCs59(long l) {
        this.cs59.setValue(l);
    }

    public void setCs64(double d) {
        this.cs64.setValue(d);
    }

    public void setCs65(double d) {
        this.cs65.setValue(d);
    }

    public void setCs66(double d) {
        this.cs66.setValue(d);
    }

    @Override
    public Object clone() {
        NVO_BASELAND_SELL nVO_BASELAND_SELL = new NVO_BASELAND_SELL();
        DbElement[] dbElementArray = new DbElement[this.elems.length];
        nVO_BASELAND_SELL.baseno = (DbString)this.baseno.clone();
        nVO_BASELAND_SELL.year = (DbString)this.year.clone();
        nVO_BASELAND_SELL.caseno = (DbString)this.caseno.clone();
        nVO_BASELAND_SELL.city = (DbString)this.city.clone();
        nVO_BASELAND_SELL.dist = (DbString)this.dist.clone();
        nVO_BASELAND_SELL.aa48 = (DbString)this.aa48.clone();
        nVO_BASELAND_SELL.aa49 = (DbString)this.aa49.clone();
        nVO_BASELAND_SELL.aa10 = (DbDouble)this.aa10.clone();
        nVO_BASELAND_SELL.land_position = (DbString)this.land_position.clone();
        nVO_BASELAND_SELL.selltype = (DbString)this.selltype.clone();
        nVO_BASELAND_SELL.ed49 = (DbString)this.ed49.clone();
        nVO_BASELAND_SELL.p1ma_caseno = (DbString)this.p1ma_caseno.clone();
        nVO_BASELAND_SELL.cs01 = (DbString)this.cs01.clone();
        nVO_BASELAND_SELL.cs02 = (DbString)this.cs02.clone();
        nVO_BASELAND_SELL.cs03 = (DbString)this.cs03.clone();
        nVO_BASELAND_SELL.cs04 = (DbString)this.cs04.clone();
        nVO_BASELAND_SELL.cs04f = (DbString)this.cs04f.clone();
        nVO_BASELAND_SELL.cs05 = (DbString)this.cs05.clone();
        nVO_BASELAND_SELL.cs06 = (DbDouble)this.cs06.clone();
        nVO_BASELAND_SELL.cs07 = (DbDouble)this.cs07.clone();
        nVO_BASELAND_SELL.cs08 = (DbDouble)this.cs08.clone();
        nVO_BASELAND_SELL.cs09 = (DbDouble)this.cs09.clone();
        nVO_BASELAND_SELL.cs10 = (DbDouble)this.cs10.clone();
        nVO_BASELAND_SELL.cs11 = (DbDouble)this.cs11.clone();
        nVO_BASELAND_SELL.cs12 = (DbDouble)this.cs12.clone();
        nVO_BASELAND_SELL.cs13 = (DbDouble)this.cs13.clone();
        nVO_BASELAND_SELL.cs14 = (DbDouble)this.cs14.clone();
        nVO_BASELAND_SELL.cs15 = (DbDouble)this.cs15.clone();
        nVO_BASELAND_SELL.cs16 = (DbDouble)this.cs16.clone();
        nVO_BASELAND_SELL.cs17 = (DbDouble)this.cs17.clone();
        nVO_BASELAND_SELL.cs18 = (DbDouble)this.cs18.clone();
        nVO_BASELAND_SELL.cs19 = (DbDouble)this.cs19.clone();
        nVO_BASELAND_SELL.cs20 = (DbString)this.cs20.clone();
        nVO_BASELAND_SELL.cs21 = (DbString)this.cs21.clone();
        nVO_BASELAND_SELL.cs22 = (DbInteger)this.cs22.clone();
        nVO_BASELAND_SELL.cs23 = (DbInteger)this.cs23.clone();
        nVO_BASELAND_SELL.cs24 = (DbInteger)this.cs24.clone();
        nVO_BASELAND_SELL.cs25 = (DbString)this.cs25.clone();
        nVO_BASELAND_SELL.cs26 = (DbDouble)this.cs26.clone();
        nVO_BASELAND_SELL.cs27 = (DbLong)this.cs27.clone();
        nVO_BASELAND_SELL.cs28 = (DbLong)this.cs28.clone();
        nVO_BASELAND_SELL.cs29 = (DbDouble)this.cs29.clone();
        nVO_BASELAND_SELL.cs30 = (DbString)this.cs30.clone();
        nVO_BASELAND_SELL.cs31 = (DbLong)this.cs31.clone();
        nVO_BASELAND_SELL.cs32 = (DbDouble)this.cs32.clone();
        nVO_BASELAND_SELL.cs33 = (DbLong)this.cs33.clone();
        nVO_BASELAND_SELL.cs34 = (DbDouble)this.cs34.clone();
        nVO_BASELAND_SELL.cs35 = (DbLong)this.cs35.clone();
        nVO_BASELAND_SELL.cs36 = (DbDouble)this.cs36.clone();
        nVO_BASELAND_SELL.cs37 = (DbLong)this.cs37.clone();
        nVO_BASELAND_SELL.cs38 = (DbDouble)this.cs38.clone();
        nVO_BASELAND_SELL.cs39 = (DbLong)this.cs39.clone();
        nVO_BASELAND_SELL.cs40 = (DbDouble)this.cs40.clone();
        nVO_BASELAND_SELL.cs41 = (DbLong)this.cs41.clone();
        nVO_BASELAND_SELL.cs42 = (DbDouble)this.cs42.clone();
        nVO_BASELAND_SELL.cs43 = (DbLong)this.cs43.clone();
        nVO_BASELAND_SELL.cs44 = (DbLong)this.cs44.clone();
        nVO_BASELAND_SELL.cs45 = (DbLong)this.cs45.clone();
        nVO_BASELAND_SELL.cs46 = (DbLong)this.cs46.clone();
        nVO_BASELAND_SELL.cs47 = (DbLong)this.cs47.clone();
        nVO_BASELAND_SELL.cs48 = (DbLong)this.cs48.clone();
        nVO_BASELAND_SELL.cs49 = (DbDouble)this.cs49.clone();
        nVO_BASELAND_SELL.cs50 = (DbLong)this.cs50.clone();
        nVO_BASELAND_SELL.cs51 = (DbLong)this.cs51.clone();
        nVO_BASELAND_SELL.cs52 = (DbString)this.cs52.clone();
        nVO_BASELAND_SELL.cs53 = (DbLong)this.cs53.clone();
        nVO_BASELAND_SELL.cs54 = (DbLong)this.cs54.clone();
        nVO_BASELAND_SELL.cs55 = (DbLong)this.cs55.clone();
        nVO_BASELAND_SELL.cs56 = (DbString)this.cs56.clone();
        nVO_BASELAND_SELL.cs57 = (DbString)this.cs57.clone();
        nVO_BASELAND_SELL.cs58 = (DbString)this.cs58.clone();
        nVO_BASELAND_SELL.cs59 = (DbLong)this.cs59.clone();
        nVO_BASELAND_SELL.cs64 = (DbDouble)this.cs64.clone();
        nVO_BASELAND_SELL.cs65 = (DbDouble)this.cs65.clone();
        nVO_BASELAND_SELL.cs66 = (DbDouble)this.cs66.clone();
        dbElementArray[0] = nVO_BASELAND_SELL.baseno;
        dbElementArray[1] = nVO_BASELAND_SELL.year;
        dbElementArray[2] = nVO_BASELAND_SELL.caseno;
        dbElementArray[3] = nVO_BASELAND_SELL.city;
        dbElementArray[4] = nVO_BASELAND_SELL.dist;
        dbElementArray[5] = nVO_BASELAND_SELL.aa48;
        dbElementArray[6] = nVO_BASELAND_SELL.aa49;
        dbElementArray[7] = nVO_BASELAND_SELL.aa10;
        dbElementArray[8] = nVO_BASELAND_SELL.land_position;
        dbElementArray[9] = nVO_BASELAND_SELL.selltype;
        dbElementArray[10] = nVO_BASELAND_SELL.ed49;
        dbElementArray[11] = nVO_BASELAND_SELL.p1ma_caseno;
        dbElementArray[12] = nVO_BASELAND_SELL.cs01;
        dbElementArray[13] = nVO_BASELAND_SELL.cs02;
        dbElementArray[14] = nVO_BASELAND_SELL.cs03;
        dbElementArray[15] = nVO_BASELAND_SELL.cs04;
        dbElementArray[16] = nVO_BASELAND_SELL.cs04f;
        dbElementArray[17] = nVO_BASELAND_SELL.cs05;
        dbElementArray[18] = nVO_BASELAND_SELL.cs06;
        dbElementArray[19] = nVO_BASELAND_SELL.cs07;
        dbElementArray[20] = nVO_BASELAND_SELL.cs08;
        dbElementArray[21] = nVO_BASELAND_SELL.cs09;
        dbElementArray[22] = nVO_BASELAND_SELL.cs10;
        dbElementArray[23] = nVO_BASELAND_SELL.cs11;
        dbElementArray[24] = nVO_BASELAND_SELL.cs12;
        dbElementArray[25] = nVO_BASELAND_SELL.cs13;
        dbElementArray[26] = nVO_BASELAND_SELL.cs14;
        dbElementArray[27] = nVO_BASELAND_SELL.cs15;
        dbElementArray[28] = nVO_BASELAND_SELL.cs16;
        dbElementArray[29] = nVO_BASELAND_SELL.cs17;
        dbElementArray[30] = nVO_BASELAND_SELL.cs18;
        dbElementArray[31] = nVO_BASELAND_SELL.cs19;
        dbElementArray[32] = nVO_BASELAND_SELL.cs20;
        dbElementArray[33] = nVO_BASELAND_SELL.cs21;
        dbElementArray[34] = nVO_BASELAND_SELL.cs22;
        dbElementArray[35] = nVO_BASELAND_SELL.cs23;
        dbElementArray[36] = nVO_BASELAND_SELL.cs24;
        dbElementArray[37] = nVO_BASELAND_SELL.cs25;
        dbElementArray[38] = nVO_BASELAND_SELL.cs26;
        dbElementArray[39] = nVO_BASELAND_SELL.cs27;
        dbElementArray[40] = nVO_BASELAND_SELL.cs28;
        dbElementArray[41] = nVO_BASELAND_SELL.cs29;
        dbElementArray[42] = nVO_BASELAND_SELL.cs30;
        dbElementArray[43] = nVO_BASELAND_SELL.cs31;
        dbElementArray[44] = nVO_BASELAND_SELL.cs32;
        dbElementArray[45] = nVO_BASELAND_SELL.cs33;
        dbElementArray[46] = nVO_BASELAND_SELL.cs34;
        dbElementArray[47] = nVO_BASELAND_SELL.cs35;
        dbElementArray[48] = nVO_BASELAND_SELL.cs36;
        dbElementArray[49] = nVO_BASELAND_SELL.cs37;
        dbElementArray[50] = nVO_BASELAND_SELL.cs38;
        dbElementArray[51] = nVO_BASELAND_SELL.cs39;
        dbElementArray[52] = nVO_BASELAND_SELL.cs40;
        dbElementArray[53] = nVO_BASELAND_SELL.cs41;
        dbElementArray[54] = nVO_BASELAND_SELL.cs42;
        dbElementArray[55] = nVO_BASELAND_SELL.cs43;
        dbElementArray[56] = nVO_BASELAND_SELL.cs44;
        dbElementArray[57] = nVO_BASELAND_SELL.cs45;
        dbElementArray[58] = nVO_BASELAND_SELL.cs46;
        dbElementArray[59] = nVO_BASELAND_SELL.cs47;
        dbElementArray[60] = nVO_BASELAND_SELL.cs48;
        dbElementArray[61] = nVO_BASELAND_SELL.cs49;
        dbElementArray[62] = nVO_BASELAND_SELL.cs50;
        dbElementArray[63] = nVO_BASELAND_SELL.cs51;
        dbElementArray[64] = nVO_BASELAND_SELL.cs52;
        dbElementArray[65] = nVO_BASELAND_SELL.cs53;
        dbElementArray[66] = nVO_BASELAND_SELL.cs54;
        dbElementArray[67] = nVO_BASELAND_SELL.cs55;
        dbElementArray[68] = nVO_BASELAND_SELL.cs56;
        dbElementArray[69] = nVO_BASELAND_SELL.cs57;
        dbElementArray[70] = nVO_BASELAND_SELL.cs58;
        dbElementArray[71] = nVO_BASELAND_SELL.cs59;
        dbElementArray[72] = nVO_BASELAND_SELL.cs64;
        dbElementArray[73] = nVO_BASELAND_SELL.cs65;
        dbElementArray[74] = nVO_BASELAND_SELL.cs66;
        nVO_BASELAND_SELL.elems = dbElementArray;
        nVO_BASELAND_SELL.fieldCount = this.fieldCount;
        nVO_BASELAND_SELL.orderString = this.orderString;
        nVO_BASELAND_SELL.tableName = this.tableName;
        return nVO_BASELAND_SELL;
    }
}

