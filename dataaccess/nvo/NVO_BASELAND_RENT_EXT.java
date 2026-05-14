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

public class NVO_BASELAND_RENT_EXT
extends VoBase
implements Serializable,
Cloneable {
    private static final long serialVersionUID = -1859331723L;
    private DbString baseno = new DbString("baseno");
    private DbString year = new DbString("year");
    private DbString city = new DbString("city");
    private DbString dist = new DbString("dist");
    private DbString land_position = new DbString("land_position");
    private DbString cre01 = new DbString("cre01");
    private DbString cre02 = new DbString("cre02");
    private DbString cre03 = new DbString("cre03");
    private DbString cre04 = new DbString("cre04");
    private DbString cre05 = new DbString("cre05");
    private DbString cre05f = new DbString("cre05f");
    private DbString cre05bf = new DbString("cre05bf");
    private DbString cre06 = new DbString("cre06");
    private DbDouble cre07 = new DbDouble("cre07");
    private DbDouble cre08 = new DbDouble("cre08");
    private DbDouble cre09 = new DbDouble("cre09");
    private DbDouble cre10 = new DbDouble("cre10");
    private DbDouble cre11 = new DbDouble("cre11");
    private DbDouble cre12 = new DbDouble("cre12");
    private DbDouble cre13 = new DbDouble("cre13");
    private DbDouble cre14 = new DbDouble("cre14");
    private DbDouble cre15 = new DbDouble("cre15");
    private DbDouble cre16 = new DbDouble("cre16");
    private DbDouble cre17 = new DbDouble("cre17");
    private DbDouble cre18 = new DbDouble("cre18");
    private DbDouble cre19 = new DbDouble("cre19");
    private DbDouble cre20 = new DbDouble("cre20");
    private DbDouble cre21 = new DbDouble("cre21");
    private DbDouble cre22 = new DbDouble("cre22");
    private DbString cre23 = new DbString("cre23");
    private DbString cre24 = new DbString("cre24");
    private DbInteger cre25 = new DbInteger("cre25");
    private DbInteger cre26 = new DbInteger("cre26");
    private DbInteger cre27 = new DbInteger("cre27");
    private DbString cre28 = new DbString("cre28");
    private DbInteger cre29 = new DbInteger("cre29");
    private DbLong cre30 = new DbLong("cre30");
    private DbLong cre31 = new DbLong("cre31");
    private DbDouble cre32 = new DbDouble("cre32", 100.0);
    private DbString cre33 = new DbString("cre33");
    private DbLong cre34 = new DbLong("cre34");
    private DbDouble cre35 = new DbDouble("cre35");
    private DbLong cre36 = new DbLong("cre36");
    private DbLong cre37 = new DbLong("cre37");
    private DbDouble cre38 = new DbDouble("cre38");
    private DbLong cre39 = new DbLong("cre39");
    private DbDouble cre40 = new DbDouble("cre40");
    private DbLong cre41 = new DbLong("cre41");
    private DbDouble cre42 = new DbDouble("cre42");
    private DbLong cre43 = new DbLong("cre43");
    private DbDouble cre44 = new DbDouble("cre44");
    private DbLong cre45 = new DbLong("cre45");
    private DbDouble cre46 = new DbDouble("cre46");
    private DbLong cre47 = new DbLong("cre47");
    private DbDouble cre48 = new DbDouble("cre48");
    private DbLong cre49 = new DbLong("cre49");
    private DbDouble cre50 = new DbDouble("cre50");
    private DbLong cre51 = new DbLong("cre51");
    private DbDouble cre52 = new DbDouble("cre52");
    private DbLong cre53 = new DbLong("cre53");
    private DbLong cre54 = new DbLong("cre54");
    private DbLong cre55 = new DbLong("cre55");
    private DbLong cre56 = new DbLong("cre56");
    private DbLong cre57 = new DbLong("cre57");
    private DbString cre58 = new DbString("cre58");
    private DbDouble cre59 = new DbDouble("cre59");
    private DbDouble cre07ori = new DbDouble("cre07ori");

    public NVO_BASELAND_RENT_EXT() {
        this.tableName = "baseland_rent_ext";
        super.setFieldCount(66);
        this.elems = new DbElement[66];
        this.elems[0] = this.baseno;
        this.elems[1] = this.year;
        this.elems[2] = this.city;
        this.elems[3] = this.dist;
        this.elems[4] = this.land_position;
        this.elems[5] = this.cre01;
        this.elems[6] = this.cre02;
        this.elems[7] = this.cre03;
        this.elems[8] = this.cre04;
        this.elems[9] = this.cre05;
        this.elems[10] = this.cre05f;
        this.elems[11] = this.cre05bf;
        this.elems[12] = this.cre06;
        this.elems[13] = this.cre07;
        this.elems[14] = this.cre08;
        this.elems[15] = this.cre09;
        this.elems[16] = this.cre10;
        this.elems[17] = this.cre11;
        this.elems[18] = this.cre12;
        this.elems[19] = this.cre13;
        this.elems[20] = this.cre14;
        this.elems[21] = this.cre15;
        this.elems[22] = this.cre16;
        this.elems[23] = this.cre17;
        this.elems[24] = this.cre18;
        this.elems[25] = this.cre19;
        this.elems[26] = this.cre20;
        this.elems[27] = this.cre21;
        this.elems[28] = this.cre22;
        this.elems[29] = this.cre23;
        this.elems[30] = this.cre24;
        this.elems[31] = this.cre25;
        this.elems[32] = this.cre26;
        this.elems[33] = this.cre27;
        this.elems[34] = this.cre28;
        this.elems[35] = this.cre29;
        this.elems[36] = this.cre30;
        this.elems[37] = this.cre31;
        this.elems[38] = this.cre32;
        this.elems[39] = this.cre33;
        this.elems[40] = this.cre34;
        this.elems[41] = this.cre35;
        this.elems[42] = this.cre36;
        this.elems[43] = this.cre37;
        this.elems[44] = this.cre38;
        this.elems[45] = this.cre39;
        this.elems[46] = this.cre40;
        this.elems[47] = this.cre41;
        this.elems[48] = this.cre42;
        this.elems[49] = this.cre43;
        this.elems[50] = this.cre44;
        this.elems[51] = this.cre45;
        this.elems[52] = this.cre46;
        this.elems[53] = this.cre47;
        this.elems[54] = this.cre48;
        this.elems[55] = this.cre49;
        this.elems[56] = this.cre50;
        this.elems[57] = this.cre51;
        this.elems[58] = this.cre52;
        this.elems[59] = this.cre53;
        this.elems[60] = this.cre54;
        this.elems[61] = this.cre55;
        this.elems[62] = this.cre56;
        this.elems[63] = this.cre57;
        this.elems[64] = this.cre58;
        this.elems[65] = this.cre59;
        this.baseno.setPkFlag(true);
        this.year.setPkFlag(true);
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

    public String getCity() {
        return this.city.getValue();
    }

    public String getDist() {
        return this.dist.getValue();
    }

    public String getLand_position() {
        return this.land_position.getValue();
    }

    public String getCre01() {
        return this.cre01.getValue();
    }

    public String getCre02() {
        return this.cre02.getValue();
    }

    public String getCre03() {
        return this.cre03.getValue();
    }

    public String getCre04() {
        return this.cre04.getValue();
    }

    public String getCre05() {
        return this.cre05.getValue();
    }

    public String getCre05f() {
        return this.cre05f.getValue();
    }

    public String getCre05bf() {
        return this.cre05bf.getValue();
    }

    public String getCre06() {
        return this.cre06.getValue();
    }

    public double getCre07() {
        return this.cre07.getValue();
    }

    public double getCre08() {
        return this.cre08.getValue();
    }

    public double getCre09() {
        return this.cre09.getValue();
    }

    public double getCre10() {
        return this.cre10.getValue();
    }

    public double getCre11() {
        return this.cre11.getValue();
    }

    public double getCre12() {
        return this.cre12.getValue();
    }

    public double getCre13() {
        return this.cre13.getValue();
    }

    public double getCre14() {
        return this.cre14.getValue();
    }

    public double getCre15() {
        return this.cre15.getValue();
    }

    public double getCre16() {
        return this.cre16.getValue();
    }

    public double getCre17() {
        return this.cre17.getValue();
    }

    public double getCre18() {
        return this.cre18.getValue();
    }

    public double getCre19() {
        return this.cre19.getValue();
    }

    public double getCre20() {
        return this.cre20.getValue();
    }

    public double getCre21() {
        return this.cre21.getValue();
    }

    public double getCre22() {
        return this.cre22.getValue();
    }

    public String getCre23() {
        return this.cre23.getValue();
    }

    public String getCre24() {
        return this.cre24.getValue();
    }

    public int getCre25() {
        return this.cre25.getValue();
    }

    public int getCre26() {
        return this.cre26.getValue();
    }

    public int getCre27() {
        return this.cre27.getValue();
    }

    public String getCre28() {
        return this.cre28.getValue();
    }

    public int getCre29() {
        return this.cre29.getValue();
    }

    public long getCre30() {
        return this.cre30.getValue();
    }

    public long getCre31() {
        return this.cre31.getValue();
    }

    public double getCre32() {
        return this.cre32.getValue();
    }

    public String getCre33() {
        return this.cre33.getValue();
    }

    public long getCre34() {
        return this.cre34.getValue();
    }

    public double getCre35() {
        return this.cre35.getValue();
    }

    public long getCre36() {
        return this.cre36.getValue();
    }

    public long getCre37() {
        return this.cre37.getValue();
    }

    public double getCre38() {
        return this.cre38.getValue();
    }

    public long getCre39() {
        return this.cre39.getValue();
    }

    public double getCre40() {
        return this.cre40.getValue();
    }

    public long getCre41() {
        return this.cre41.getValue();
    }

    public double getCre42() {
        return this.cre42.getValue();
    }

    public long getCre43() {
        return this.cre43.getValue();
    }

    public double getCre44() {
        return this.cre44.getValue();
    }

    public long getCre45() {
        return this.cre45.getValue();
    }

    public double getCre46() {
        return this.cre46.getValue();
    }

    public long getCre47() {
        return this.cre47.getValue();
    }

    public double getCre48() {
        return this.cre48.getValue();
    }

    public long getCre49() {
        return this.cre49.getValue();
    }

    public double getCre50() {
        return this.cre50.getValue();
    }

    public long getCre51() {
        return this.cre51.getValue();
    }

    public double getCre52() {
        return this.cre52.getValue();
    }

    public long getCre53() {
        return this.cre53.getValue();
    }

    public long getCre54() {
        return this.cre54.getValue();
    }

    public long getCre55() {
        return this.cre55.getValue();
    }

    public long getCre56() {
        return this.cre56.getValue();
    }

    public long getCre57() {
        return this.cre57.getValue();
    }

    public String getCre58() {
        return this.cre58.getValue();
    }

    public double getCre59() {
        return this.cre59.getValue();
    }

    public void setBaseno(String string) {
        this.baseno.setValue(string);
    }

    public void setYear(String string) {
        this.year.setValue(string);
    }

    public void setCity(String string) {
        this.city.setValue(string);
    }

    public void setDist(String string) {
        this.dist.setValue(string);
    }

    public void setLand_position(String string) {
        this.land_position.setValue(string);
    }

    public void setCre01(String string) {
        this.cre01.setValue(string);
    }

    public void setCre02(String string) {
        this.cre02.setValue(string);
    }

    public void setCre03(String string) {
        this.cre03.setValue(string);
    }

    public void setCre04(String string) {
        this.cre04.setValue(string);
    }

    public void setCre05(String string) {
        this.cre05.setValue(string);
    }

    public void setCre05f(String string) {
        this.cre05f.setValue(string);
    }

    public void setCre05bf(String string) {
        this.cre05bf.setValue(string);
    }

    public void setCre06(String string) {
        this.cre06.setValue(string);
    }

    public void setCre07(double d) {
        this.cre07.setValue(d);
    }

    public void setCre08(double d) {
        this.cre08.setValue(d);
    }

    public void setCre09(double d) {
        this.cre09.setValue(d);
    }

    public void setCre10(double d) {
        this.cre10.setValue(d);
    }

    public void setCre11(double d) {
        this.cre11.setValue(d);
    }

    public void setCre12(double d) {
        this.cre12.setValue(d);
    }

    public void setCre13(double d) {
        this.cre13.setValue(d);
    }

    public void setCre14(double d) {
        this.cre14.setValue(d);
    }

    public void setCre15(double d) {
        this.cre15.setValue(d);
    }

    public void setCre16(double d) {
        this.cre16.setValue(d);
    }

    public void setCre17(double d) {
        this.cre17.setValue(d);
    }

    public void setCre18(double d) {
        this.cre18.setValue(d);
    }

    public void setCre19(double d) {
        this.cre19.setValue(d);
    }

    public void setCre20(double d) {
        this.cre20.setValue(d);
    }

    public void setCre21(double d) {
        this.cre21.setValue(d);
    }

    public void setCre22(double d) {
        this.cre22.setValue(d);
    }

    public void setCre23(String string) {
        this.cre23.setValue(string);
    }

    public void setCre24(String string) {
        this.cre24.setValue(string);
    }

    public void setCre25(int n) {
        this.cre25.setValue(n);
    }

    public void setCre26(int n) {
        this.cre26.setValue(n);
    }

    public void setCre27(int n) {
        this.cre27.setValue(n);
    }

    public void setCre28(String string) {
        this.cre28.setValue(string);
    }

    public void setCre29(int n) {
        this.cre29.setValue(n);
    }

    public void setCre30(long l) {
        this.cre30.setValue(l);
    }

    public void setCre31(long l) {
        this.cre31.setValue(l);
    }

    public void setCre32(double d) {
        this.cre32.setValue(d);
    }

    public void setCre33(String string) {
        this.cre33.setValue(string);
    }

    public void setCre34(long l) {
        this.cre34.setValue(l);
    }

    public void setCre35(double d) {
        this.cre35.setValue(d);
    }

    public void setCre36(long l) {
        this.cre36.setValue(l);
    }

    public void setCre37(long l) {
        this.cre37.setValue(l);
    }

    public void setCre38(double d) {
        this.cre38.setValue(d);
    }

    public void setCre39(long l) {
        this.cre39.setValue(l);
    }

    public void setCre40(double d) {
        this.cre40.setValue(d);
    }

    public void setCre41(long l) {
        this.cre41.setValue(l);
    }

    public void setCre42(double d) {
        this.cre42.setValue(d);
    }

    public void setCre43(long l) {
        this.cre43.setValue(l);
    }

    public void setCre44(double d) {
        this.cre44.setValue(d);
    }

    public void setCre45(long l) {
        this.cre45.setValue(l);
    }

    public void setCre46(double d) {
        this.cre46.setValue(d);
    }

    public void setCre47(long l) {
        this.cre47.setValue(l);
    }

    public void setCre48(double d) {
        this.cre48.setValue(d);
    }

    public void setCre49(long l) {
        this.cre49.setValue(l);
    }

    public void setCre50(double d) {
        this.cre50.setValue(d);
    }

    public void setCre51(long l) {
        this.cre51.setValue(l);
    }

    public void setCre52(double d) {
        this.cre52.setValue(d);
    }

    public void setCre53(long l) {
        this.cre53.setValue(l);
    }

    public void setCre54(long l) {
        this.cre54.setValue(l);
    }

    public void setCre55(long l) {
        this.cre55.setValue(l);
    }

    public void setCre56(long l) {
        this.cre56.setValue(l);
    }

    public void setCre57(long l) {
        this.cre57.setValue(l);
    }

    public void setCre58(String string) {
        this.cre58.setValue(string);
    }

    public void setCre59(double d) {
        this.cre59.setValue(d);
    }

    public double getCre07ori() {
        return this.cre07ori.getValue();
    }

    public void setCre07ori(double d) {
        this.cre07ori.setValue(d);
    }

    @Override
    public Object clone() {
        NVO_BASELAND_RENT_EXT nVO_BASELAND_RENT_EXT = new NVO_BASELAND_RENT_EXT();
        DbElement[] dbElementArray = new DbElement[this.elems.length];
        nVO_BASELAND_RENT_EXT.baseno = (DbString)this.baseno.clone();
        nVO_BASELAND_RENT_EXT.year = (DbString)this.year.clone();
        nVO_BASELAND_RENT_EXT.city = (DbString)this.city.clone();
        nVO_BASELAND_RENT_EXT.dist = (DbString)this.dist.clone();
        nVO_BASELAND_RENT_EXT.land_position = (DbString)this.land_position.clone();
        nVO_BASELAND_RENT_EXT.cre01 = (DbString)this.cre01.clone();
        nVO_BASELAND_RENT_EXT.cre02 = (DbString)this.cre02.clone();
        nVO_BASELAND_RENT_EXT.cre03 = (DbString)this.cre03.clone();
        nVO_BASELAND_RENT_EXT.cre04 = (DbString)this.cre04.clone();
        nVO_BASELAND_RENT_EXT.cre05 = (DbString)this.cre05.clone();
        nVO_BASELAND_RENT_EXT.cre05f = (DbString)this.cre05f.clone();
        nVO_BASELAND_RENT_EXT.cre05bf = (DbString)this.cre05bf.clone();
        nVO_BASELAND_RENT_EXT.cre06 = (DbString)this.cre06.clone();
        nVO_BASELAND_RENT_EXT.cre07 = (DbDouble)this.cre07.clone();
        nVO_BASELAND_RENT_EXT.cre08 = (DbDouble)this.cre08.clone();
        nVO_BASELAND_RENT_EXT.cre09 = (DbDouble)this.cre09.clone();
        nVO_BASELAND_RENT_EXT.cre10 = (DbDouble)this.cre10.clone();
        nVO_BASELAND_RENT_EXT.cre11 = (DbDouble)this.cre11.clone();
        nVO_BASELAND_RENT_EXT.cre12 = (DbDouble)this.cre12.clone();
        nVO_BASELAND_RENT_EXT.cre13 = (DbDouble)this.cre13.clone();
        nVO_BASELAND_RENT_EXT.cre14 = (DbDouble)this.cre14.clone();
        nVO_BASELAND_RENT_EXT.cre15 = (DbDouble)this.cre15.clone();
        nVO_BASELAND_RENT_EXT.cre16 = (DbDouble)this.cre16.clone();
        nVO_BASELAND_RENT_EXT.cre17 = (DbDouble)this.cre17.clone();
        nVO_BASELAND_RENT_EXT.cre18 = (DbDouble)this.cre18.clone();
        nVO_BASELAND_RENT_EXT.cre19 = (DbDouble)this.cre19.clone();
        nVO_BASELAND_RENT_EXT.cre20 = (DbDouble)this.cre20.clone();
        nVO_BASELAND_RENT_EXT.cre21 = (DbDouble)this.cre21.clone();
        nVO_BASELAND_RENT_EXT.cre22 = (DbDouble)this.cre22.clone();
        nVO_BASELAND_RENT_EXT.cre23 = (DbString)this.cre23.clone();
        nVO_BASELAND_RENT_EXT.cre24 = (DbString)this.cre24.clone();
        nVO_BASELAND_RENT_EXT.cre25 = (DbInteger)this.cre25.clone();
        nVO_BASELAND_RENT_EXT.cre26 = (DbInteger)this.cre26.clone();
        nVO_BASELAND_RENT_EXT.cre27 = (DbInteger)this.cre27.clone();
        nVO_BASELAND_RENT_EXT.cre28 = (DbString)this.cre28.clone();
        nVO_BASELAND_RENT_EXT.cre29 = (DbInteger)this.cre29.clone();
        nVO_BASELAND_RENT_EXT.cre30 = (DbLong)this.cre30.clone();
        nVO_BASELAND_RENT_EXT.cre31 = (DbLong)this.cre31.clone();
        nVO_BASELAND_RENT_EXT.cre32 = (DbDouble)this.cre32.clone();
        nVO_BASELAND_RENT_EXT.cre33 = (DbString)this.cre33.clone();
        nVO_BASELAND_RENT_EXT.cre34 = (DbLong)this.cre34.clone();
        nVO_BASELAND_RENT_EXT.cre35 = (DbDouble)this.cre35.clone();
        nVO_BASELAND_RENT_EXT.cre36 = (DbLong)this.cre36.clone();
        nVO_BASELAND_RENT_EXT.cre37 = (DbLong)this.cre37.clone();
        nVO_BASELAND_RENT_EXT.cre38 = (DbDouble)this.cre38.clone();
        nVO_BASELAND_RENT_EXT.cre39 = (DbLong)this.cre39.clone();
        nVO_BASELAND_RENT_EXT.cre40 = (DbDouble)this.cre40.clone();
        nVO_BASELAND_RENT_EXT.cre41 = (DbLong)this.cre41.clone();
        nVO_BASELAND_RENT_EXT.cre42 = (DbDouble)this.cre42.clone();
        nVO_BASELAND_RENT_EXT.cre43 = (DbLong)this.cre43.clone();
        nVO_BASELAND_RENT_EXT.cre44 = (DbDouble)this.cre44.clone();
        nVO_BASELAND_RENT_EXT.cre45 = (DbLong)this.cre45.clone();
        nVO_BASELAND_RENT_EXT.cre46 = (DbDouble)this.cre46.clone();
        nVO_BASELAND_RENT_EXT.cre47 = (DbLong)this.cre47.clone();
        nVO_BASELAND_RENT_EXT.cre48 = (DbDouble)this.cre48.clone();
        nVO_BASELAND_RENT_EXT.cre49 = (DbLong)this.cre49.clone();
        nVO_BASELAND_RENT_EXT.cre50 = (DbDouble)this.cre50.clone();
        nVO_BASELAND_RENT_EXT.cre51 = (DbLong)this.cre51.clone();
        nVO_BASELAND_RENT_EXT.cre52 = (DbDouble)this.cre52.clone();
        nVO_BASELAND_RENT_EXT.cre53 = (DbLong)this.cre53.clone();
        nVO_BASELAND_RENT_EXT.cre54 = (DbLong)this.cre54.clone();
        nVO_BASELAND_RENT_EXT.cre55 = (DbLong)this.cre55.clone();
        nVO_BASELAND_RENT_EXT.cre56 = (DbLong)this.cre56.clone();
        nVO_BASELAND_RENT_EXT.cre57 = (DbLong)this.cre57.clone();
        nVO_BASELAND_RENT_EXT.cre58 = (DbString)this.cre58.clone();
        nVO_BASELAND_RENT_EXT.cre59 = (DbDouble)this.cre59.clone();
        dbElementArray[0] = nVO_BASELAND_RENT_EXT.baseno;
        dbElementArray[1] = nVO_BASELAND_RENT_EXT.year;
        dbElementArray[2] = nVO_BASELAND_RENT_EXT.city;
        dbElementArray[3] = nVO_BASELAND_RENT_EXT.dist;
        dbElementArray[4] = nVO_BASELAND_RENT_EXT.land_position;
        dbElementArray[5] = nVO_BASELAND_RENT_EXT.cre01;
        dbElementArray[6] = nVO_BASELAND_RENT_EXT.cre02;
        dbElementArray[7] = nVO_BASELAND_RENT_EXT.cre03;
        dbElementArray[8] = nVO_BASELAND_RENT_EXT.cre04;
        dbElementArray[9] = nVO_BASELAND_RENT_EXT.cre05;
        dbElementArray[10] = nVO_BASELAND_RENT_EXT.cre05f;
        dbElementArray[11] = nVO_BASELAND_RENT_EXT.cre05bf;
        dbElementArray[12] = nVO_BASELAND_RENT_EXT.cre06;
        dbElementArray[13] = nVO_BASELAND_RENT_EXT.cre07;
        dbElementArray[14] = nVO_BASELAND_RENT_EXT.cre08;
        dbElementArray[15] = nVO_BASELAND_RENT_EXT.cre09;
        dbElementArray[16] = nVO_BASELAND_RENT_EXT.cre10;
        dbElementArray[17] = nVO_BASELAND_RENT_EXT.cre11;
        dbElementArray[18] = nVO_BASELAND_RENT_EXT.cre12;
        dbElementArray[19] = nVO_BASELAND_RENT_EXT.cre13;
        dbElementArray[20] = nVO_BASELAND_RENT_EXT.cre14;
        dbElementArray[21] = nVO_BASELAND_RENT_EXT.cre15;
        dbElementArray[22] = nVO_BASELAND_RENT_EXT.cre16;
        dbElementArray[23] = nVO_BASELAND_RENT_EXT.cre17;
        dbElementArray[24] = nVO_BASELAND_RENT_EXT.cre18;
        dbElementArray[25] = nVO_BASELAND_RENT_EXT.cre19;
        dbElementArray[26] = nVO_BASELAND_RENT_EXT.cre20;
        dbElementArray[27] = nVO_BASELAND_RENT_EXT.cre21;
        dbElementArray[28] = nVO_BASELAND_RENT_EXT.cre22;
        dbElementArray[29] = nVO_BASELAND_RENT_EXT.cre23;
        dbElementArray[30] = nVO_BASELAND_RENT_EXT.cre24;
        dbElementArray[31] = nVO_BASELAND_RENT_EXT.cre25;
        dbElementArray[32] = nVO_BASELAND_RENT_EXT.cre26;
        dbElementArray[33] = nVO_BASELAND_RENT_EXT.cre27;
        dbElementArray[34] = nVO_BASELAND_RENT_EXT.cre28;
        dbElementArray[35] = nVO_BASELAND_RENT_EXT.cre29;
        dbElementArray[36] = nVO_BASELAND_RENT_EXT.cre30;
        dbElementArray[37] = nVO_BASELAND_RENT_EXT.cre31;
        dbElementArray[38] = nVO_BASELAND_RENT_EXT.cre32;
        dbElementArray[39] = nVO_BASELAND_RENT_EXT.cre33;
        dbElementArray[40] = nVO_BASELAND_RENT_EXT.cre34;
        dbElementArray[41] = nVO_BASELAND_RENT_EXT.cre35;
        dbElementArray[42] = nVO_BASELAND_RENT_EXT.cre36;
        dbElementArray[43] = nVO_BASELAND_RENT_EXT.cre37;
        dbElementArray[44] = nVO_BASELAND_RENT_EXT.cre38;
        dbElementArray[45] = nVO_BASELAND_RENT_EXT.cre39;
        dbElementArray[46] = nVO_BASELAND_RENT_EXT.cre40;
        dbElementArray[47] = nVO_BASELAND_RENT_EXT.cre41;
        dbElementArray[48] = nVO_BASELAND_RENT_EXT.cre42;
        dbElementArray[49] = nVO_BASELAND_RENT_EXT.cre43;
        dbElementArray[50] = nVO_BASELAND_RENT_EXT.cre44;
        dbElementArray[51] = nVO_BASELAND_RENT_EXT.cre45;
        dbElementArray[52] = nVO_BASELAND_RENT_EXT.cre46;
        dbElementArray[53] = nVO_BASELAND_RENT_EXT.cre47;
        dbElementArray[54] = nVO_BASELAND_RENT_EXT.cre48;
        dbElementArray[55] = nVO_BASELAND_RENT_EXT.cre49;
        dbElementArray[56] = nVO_BASELAND_RENT_EXT.cre50;
        dbElementArray[57] = nVO_BASELAND_RENT_EXT.cre51;
        dbElementArray[58] = nVO_BASELAND_RENT_EXT.cre52;
        dbElementArray[59] = nVO_BASELAND_RENT_EXT.cre53;
        dbElementArray[60] = nVO_BASELAND_RENT_EXT.cre54;
        dbElementArray[61] = nVO_BASELAND_RENT_EXT.cre55;
        dbElementArray[62] = nVO_BASELAND_RENT_EXT.cre56;
        dbElementArray[63] = nVO_BASELAND_RENT_EXT.cre57;
        dbElementArray[64] = nVO_BASELAND_RENT_EXT.cre58;
        dbElementArray[65] = nVO_BASELAND_RENT_EXT.cre59;
        nVO_BASELAND_RENT_EXT.elems = dbElementArray;
        nVO_BASELAND_RENT_EXT.fieldCount = this.fieldCount;
        nVO_BASELAND_RENT_EXT.orderString = this.orderString;
        nVO_BASELAND_RENT_EXT.tableName = this.tableName;
        return nVO_BASELAND_RENT_EXT;
    }
}

