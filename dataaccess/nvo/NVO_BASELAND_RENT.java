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

public class NVO_BASELAND_RENT
extends VoBase
implements Serializable,
Cloneable {
    private static final long serialVersionUID = 1128574387L;
    private DbString baseno = new DbString("baseno");
    private DbString year = new DbString("year");
    private DbString city = new DbString("city");
    private DbString dist = new DbString("dist");
    private DbDouble cr01 = new DbDouble("cr01");
    private DbInteger cr02 = new DbInteger("cr02");
    private DbDouble cr03 = new DbDouble("cr03");
    private DbDouble cr04 = new DbDouble("cr04");
    private DbString cr05 = new DbString("cr05");
    private DbString cr06 = new DbString("cr06");
    private DbLong cr07 = new DbLong("cr07");
    private DbLong cr08 = new DbLong("cr08");
    private DbLong cr09 = new DbLong("cr09");
    private DbInteger cr10 = new DbInteger("cr10");
    private DbLong cr11 = new DbLong("cr11");
    private DbLong cr12 = new DbLong("cr12");
    private DbLong cr13 = new DbLong("cr13");
    private DbDouble cr14 = new DbDouble("cr14");
    private DbLong cr15 = new DbLong("cr15");
    private DbLong cr16 = new DbLong("cr16");
    private DbLong cr17 = new DbLong("cr17");
    private DbDouble cr18 = new DbDouble("cr18");
    private DbLong cr19 = new DbLong("cr19");
    private DbLong cr20 = new DbLong("cr20");
    private DbLong cr21 = new DbLong("cr21");
    private DbLong cr22 = new DbLong("cr22");
    private DbLong cr23 = new DbLong("cr23");
    private DbLong cr24 = new DbLong("cr24");
    private DbLong cr25 = new DbLong("cr25");
    private DbLong cr26 = new DbLong("cr26");
    private DbDouble cr27 = new DbDouble("cr27");
    private DbLong cr28 = new DbLong("cr28");
    private DbLong cr29 = new DbLong("cr29");
    private DbDouble cr30 = new DbDouble("cr30");
    private DbLong cr31 = new DbLong("cr31");
    private DbLong cr32 = new DbLong("cr32");
    private DbString cr33 = new DbString("cr33");
    private DbLong cr34 = new DbLong("cr34");
    private DbDouble cr35 = new DbDouble("cr35");
    private DbLong cr36 = new DbLong("cr36");
    private DbLong cr37 = new DbLong("cr37");
    private DbLong cr38 = new DbLong("cr38");
    private DbString cr39 = new DbString("cr39");
    private DbLong cr40 = new DbLong("cr40");
    private DbLong cr41 = new DbLong("cr41");
    private DbDouble cr42 = new DbDouble("cr42");
    private DbDouble cr43 = new DbDouble("cr43");
    private DbString cr44 = new DbString("cr44");
    private DbDouble cr45 = new DbDouble("cr45", 100.0);
    private DbDouble cr46 = new DbDouble("cr46");
    private DbDouble cr47 = new DbDouble("cr47");
    private DbInteger cr48 = new DbInteger("cr48");
    private DbString cr48name = new DbString("cr48name");
    private double cre07 = 0.0;
    private double cre25 = 0.0;
    private double tmpcr25 = 0.0;
    private double cre27 = 0.0;
    private long cre31 = 0L;
    private long cre34 = 0L;
    private long cre29 = 0L;
    private long cre53 = 0L;
    private long cre54 = 0L;
    private boolean building = true;

    public NVO_BASELAND_RENT() {
        this.tableName = "baseland_rent";
        super.setFieldCount(52);
        this.elems = new DbElement[52];
        this.elems[0] = this.baseno;
        this.elems[1] = this.year;
        this.elems[2] = this.city;
        this.elems[3] = this.dist;
        this.elems[4] = this.cr01;
        this.elems[5] = this.cr02;
        this.elems[6] = this.cr03;
        this.elems[7] = this.cr04;
        this.elems[8] = this.cr05;
        this.elems[9] = this.cr06;
        this.elems[10] = this.cr07;
        this.elems[11] = this.cr08;
        this.elems[12] = this.cr09;
        this.elems[13] = this.cr10;
        this.elems[14] = this.cr11;
        this.elems[15] = this.cr12;
        this.elems[16] = this.cr13;
        this.elems[17] = this.cr14;
        this.elems[18] = this.cr15;
        this.elems[19] = this.cr16;
        this.elems[20] = this.cr17;
        this.elems[21] = this.cr18;
        this.elems[22] = this.cr19;
        this.elems[23] = this.cr20;
        this.elems[24] = this.cr21;
        this.elems[25] = this.cr22;
        this.elems[26] = this.cr23;
        this.elems[27] = this.cr24;
        this.elems[28] = this.cr25;
        this.elems[29] = this.cr26;
        this.elems[30] = this.cr27;
        this.elems[31] = this.cr28;
        this.elems[32] = this.cr29;
        this.elems[33] = this.cr30;
        this.elems[34] = this.cr31;
        this.elems[35] = this.cr32;
        this.elems[36] = this.cr33;
        this.elems[37] = this.cr34;
        this.elems[38] = this.cr35;
        this.elems[39] = this.cr36;
        this.elems[40] = this.cr37;
        this.elems[41] = this.cr38;
        this.elems[42] = this.cr39;
        this.elems[43] = this.cr40;
        this.elems[44] = this.cr41;
        this.elems[45] = this.cr42;
        this.elems[46] = this.cr43;
        this.elems[47] = this.cr44;
        this.elems[48] = this.cr45;
        this.elems[49] = this.cr46;
        this.elems[50] = this.cr47;
        this.elems[51] = this.cr48;
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

    public double getCr01() {
        return this.cr01.getValue();
    }

    public int getCr02() {
        return this.cr02.getValue();
    }

    public double getCr03() {
        return this.cr03.getValue();
    }

    public double getCr04() {
        return this.cr04.getValue();
    }

    public String getCr05() {
        return this.cr05.getValue();
    }

    public String getCr06() {
        return this.cr06.getValue();
    }

    public long getCr07() {
        return this.cr07.getValue();
    }

    public long getCr08() {
        return this.cr08.getValue();
    }

    public long getCr09() {
        return this.cr09.getValue();
    }

    public int getCr10() {
        return this.cr10.getValue();
    }

    public long getCr11() {
        return this.cr11.getValue();
    }

    public long getCr12() {
        return this.cr12.getValue();
    }

    public long getCr13() {
        return this.cr13.getValue();
    }

    public double getCr14() {
        return this.cr14.getValue();
    }

    public long getCr15() {
        return this.cr15.getValue();
    }

    public long getCr16() {
        return this.cr16.getValue();
    }

    public long getCr17() {
        return this.cr17.getValue();
    }

    public double getCr18() {
        return this.cr18.getValue();
    }

    public long getCr19() {
        return this.cr19.getValue();
    }

    public long getCr20() {
        return this.cr20.getValue();
    }

    public long getCr21() {
        return this.cr21.getValue();
    }

    public long getCr22() {
        return this.cr22.getValue();
    }

    public long getCr23() {
        return this.cr23.getValue();
    }

    public long getCr24() {
        return this.cr24.getValue();
    }

    public long getCr25() {
        return this.cr25.getValue();
    }

    public long getCr26() {
        return this.cr26.getValue();
    }

    public double getCr27() {
        return this.cr27.getValue();
    }

    public long getCr28() {
        return this.cr28.getValue();
    }

    public long getCr29() {
        return this.cr29.getValue();
    }

    public double getCr30() {
        return this.cr30.getValue();
    }

    public long getCr31() {
        return this.cr31.getValue();
    }

    public long getCr32() {
        return this.cr32.getValue();
    }

    public String getCr33() {
        return this.cr33.getValue();
    }

    public long getCr34() {
        return this.cr34.getValue();
    }

    public double getCr35() {
        return this.cr35.getValue();
    }

    public long getCr36() {
        return this.cr36.getValue();
    }

    public long getCr37() {
        return this.cr37.getValue();
    }

    public long getCr38() {
        return this.cr38.getValue();
    }

    public String getCr39() {
        return this.cr39.getValue();
    }

    public long getCr40() {
        return this.cr40.getValue();
    }

    public long getCr41() {
        return this.cr41.getValue();
    }

    public double getCr42() {
        return this.cr42.getValue();
    }

    public double getCr43() {
        return this.cr43.getValue();
    }

    public String getCr44() {
        return this.cr44.getValue();
    }

    public double getCr45() {
        return this.cr45.getValue();
    }

    public double getCr46() {
        return this.cr46.getValue();
    }

    public double getCr47() {
        return this.cr47.getValue();
    }

    public int getCr48() {
        return this.cr48.getValue();
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

    public void setCr01(double d) {
        this.cr01.setValue(d);
    }

    public void setCr02(int n) {
        this.cr02.setValue(n);
    }

    public void setCr03(double d) {
        this.cr03.setValue(d);
    }

    public void setCr04(double d) {
        this.cr04.setValue(d);
    }

    public void setCr05(String string) {
        this.cr05.setValue(string);
    }

    public void setCr06(String string) {
        this.cr06.setValue(string);
    }

    public void setCr07(long l) {
        this.cr07.setValue(l);
    }

    public void setCr08(long l) {
        this.cr08.setValue(l);
    }

    public void setCr09(long l) {
        this.cr09.setValue(l);
    }

    public void setCr10(int n) {
        this.cr10.setValue(n);
    }

    public void setCr11(long l) {
        this.cr11.setValue(l);
    }

    public void setCr12(long l) {
        this.cr12.setValue(l);
    }

    public void setCr13(long l) {
        this.cr13.setValue(l);
    }

    public void setCr14(double d) {
        this.cr14.setValue(d);
    }

    public void setCr15(long l) {
        this.cr15.setValue(l);
    }

    public void setCr16(long l) {
        this.cr16.setValue(l);
    }

    public void setCr17(long l) {
        this.cr17.setValue(l);
    }

    public void setCr18(double d) {
        this.cr18.setValue(d);
    }

    public void setCr19(long l) {
        this.cr19.setValue(l);
    }

    public void setCr20(long l) {
        this.cr20.setValue(l);
    }

    public void setCr21(long l) {
        this.cr21.setValue(l);
    }

    public void setCr22(long l) {
        this.cr22.setValue(l);
    }

    public void setCr23(long l) {
        this.cr23.setValue(l);
    }

    public void setCr24(long l) {
        this.cr24.setValue(l);
    }

    public void setCr25(long l) {
        this.cr25.setValue(l);
    }

    public void setCr26(long l) {
        this.cr26.setValue(l);
    }

    public void setCr27(double d) {
        this.cr27.setValue(d);
    }

    public void setCr28(long l) {
        this.cr28.setValue(l);
    }

    public void setCr29(long l) {
        this.cr29.setValue(l);
    }

    public void setCr30(double d) {
        this.cr30.setValue(d);
    }

    public void setCr31(long l) {
        this.cr31.setValue(l);
    }

    public void setCr32(long l) {
        this.cr32.setValue(l);
    }

    public void setCr33(String string) {
        this.cr33.setValue(string);
    }

    public void setCr34(long l) {
        this.cr34.setValue(l);
    }

    public void setCr35(double d) {
        this.cr35.setValue(d);
    }

    public void setCr36(long l) {
        this.cr36.setValue(l);
    }

    public void setCr37(long l) {
        this.cr37.setValue(l);
    }

    public void setCr38(long l) {
        this.cr38.setValue(l);
    }

    public void setCr39(String string) {
        this.cr39.setValue(string);
    }

    public void setCr40(long l) {
        this.cr40.setValue(l);
    }

    public void setCr41(long l) {
        this.cr41.setValue(l);
    }

    public void setCr42(double d) {
        this.cr42.setValue(d);
    }

    public void setCr43(double d) {
        this.cr43.setValue(d);
    }

    public void setCr44(String string) {
        this.cr44.setValue(string);
    }

    public void setCr45(double d) {
        this.cr45.setValue(d);
    }

    public void setCr46(double d) {
        this.cr46.setValue(d);
    }

    public void setCr47(double d) {
        this.cr47.setValue(d);
    }

    public void setCr48(int n) {
        this.cr48.setValue(n);
    }

    public String getCr48name() {
        return this.cr48name.getValue();
    }

    public void setCr48name(String string) {
        this.cr48name.setValue(string);
    }

    @Override
    public Object clone() {
        NVO_BASELAND_RENT nVO_BASELAND_RENT = new NVO_BASELAND_RENT();
        DbElement[] dbElementArray = new DbElement[this.elems.length];
        nVO_BASELAND_RENT.baseno = (DbString)this.baseno.clone();
        nVO_BASELAND_RENT.year = (DbString)this.year.clone();
        nVO_BASELAND_RENT.city = (DbString)this.city.clone();
        nVO_BASELAND_RENT.dist = (DbString)this.dist.clone();
        nVO_BASELAND_RENT.cr01 = (DbDouble)this.cr01.clone();
        nVO_BASELAND_RENT.cr02 = (DbInteger)this.cr02.clone();
        nVO_BASELAND_RENT.cr03 = (DbDouble)this.cr03.clone();
        nVO_BASELAND_RENT.cr04 = (DbDouble)this.cr04.clone();
        nVO_BASELAND_RENT.cr05 = (DbString)this.cr05.clone();
        nVO_BASELAND_RENT.cr06 = (DbString)this.cr06.clone();
        nVO_BASELAND_RENT.cr07 = (DbLong)this.cr07.clone();
        nVO_BASELAND_RENT.cr08 = (DbLong)this.cr08.clone();
        nVO_BASELAND_RENT.cr09 = (DbLong)this.cr09.clone();
        nVO_BASELAND_RENT.cr10 = (DbInteger)this.cr10.clone();
        nVO_BASELAND_RENT.cr11 = (DbLong)this.cr11.clone();
        nVO_BASELAND_RENT.cr12 = (DbLong)this.cr12.clone();
        nVO_BASELAND_RENT.cr13 = (DbLong)this.cr13.clone();
        nVO_BASELAND_RENT.cr14 = (DbDouble)this.cr14.clone();
        nVO_BASELAND_RENT.cr15 = (DbLong)this.cr15.clone();
        nVO_BASELAND_RENT.cr16 = (DbLong)this.cr16.clone();
        nVO_BASELAND_RENT.cr17 = (DbLong)this.cr17.clone();
        nVO_BASELAND_RENT.cr18 = (DbDouble)this.cr18.clone();
        nVO_BASELAND_RENT.cr19 = (DbLong)this.cr19.clone();
        nVO_BASELAND_RENT.cr20 = (DbLong)this.cr20.clone();
        nVO_BASELAND_RENT.cr21 = (DbLong)this.cr21.clone();
        nVO_BASELAND_RENT.cr22 = (DbLong)this.cr22.clone();
        nVO_BASELAND_RENT.cr23 = (DbLong)this.cr23.clone();
        nVO_BASELAND_RENT.cr24 = (DbLong)this.cr24.clone();
        nVO_BASELAND_RENT.cr25 = (DbLong)this.cr25.clone();
        nVO_BASELAND_RENT.cr26 = (DbLong)this.cr26.clone();
        nVO_BASELAND_RENT.cr27 = (DbDouble)this.cr27.clone();
        nVO_BASELAND_RENT.cr28 = (DbLong)this.cr28.clone();
        nVO_BASELAND_RENT.cr29 = (DbLong)this.cr29.clone();
        nVO_BASELAND_RENT.cr30 = (DbDouble)this.cr30.clone();
        nVO_BASELAND_RENT.cr31 = (DbLong)this.cr31.clone();
        nVO_BASELAND_RENT.cr32 = (DbLong)this.cr32.clone();
        nVO_BASELAND_RENT.cr33 = (DbString)this.cr33.clone();
        nVO_BASELAND_RENT.cr34 = (DbLong)this.cr34.clone();
        nVO_BASELAND_RENT.cr35 = (DbDouble)this.cr35.clone();
        nVO_BASELAND_RENT.cr36 = (DbLong)this.cr36.clone();
        nVO_BASELAND_RENT.cr37 = (DbLong)this.cr37.clone();
        nVO_BASELAND_RENT.cr38 = (DbLong)this.cr38.clone();
        nVO_BASELAND_RENT.cr39 = (DbString)this.cr39.clone();
        nVO_BASELAND_RENT.cr40 = (DbLong)this.cr40.clone();
        nVO_BASELAND_RENT.cr41 = (DbLong)this.cr41.clone();
        nVO_BASELAND_RENT.cr42 = (DbDouble)this.cr42.clone();
        nVO_BASELAND_RENT.cr43 = (DbDouble)this.cr43.clone();
        nVO_BASELAND_RENT.cr44 = (DbString)this.cr44.clone();
        nVO_BASELAND_RENT.cr45 = (DbDouble)this.cr45.clone();
        nVO_BASELAND_RENT.cr46 = (DbDouble)this.cr46.clone();
        nVO_BASELAND_RENT.cr47 = (DbDouble)this.cr47.clone();
        nVO_BASELAND_RENT.cr48 = (DbInteger)this.cr48.clone();
        dbElementArray[0] = nVO_BASELAND_RENT.baseno;
        dbElementArray[1] = nVO_BASELAND_RENT.year;
        dbElementArray[2] = nVO_BASELAND_RENT.city;
        dbElementArray[3] = nVO_BASELAND_RENT.dist;
        dbElementArray[4] = nVO_BASELAND_RENT.cr01;
        dbElementArray[5] = nVO_BASELAND_RENT.cr02;
        dbElementArray[6] = nVO_BASELAND_RENT.cr03;
        dbElementArray[7] = nVO_BASELAND_RENT.cr04;
        dbElementArray[8] = nVO_BASELAND_RENT.cr05;
        dbElementArray[9] = nVO_BASELAND_RENT.cr06;
        dbElementArray[10] = nVO_BASELAND_RENT.cr07;
        dbElementArray[11] = nVO_BASELAND_RENT.cr08;
        dbElementArray[12] = nVO_BASELAND_RENT.cr09;
        dbElementArray[13] = nVO_BASELAND_RENT.cr10;
        dbElementArray[14] = nVO_BASELAND_RENT.cr11;
        dbElementArray[15] = nVO_BASELAND_RENT.cr12;
        dbElementArray[16] = nVO_BASELAND_RENT.cr13;
        dbElementArray[17] = nVO_BASELAND_RENT.cr14;
        dbElementArray[18] = nVO_BASELAND_RENT.cr15;
        dbElementArray[19] = nVO_BASELAND_RENT.cr16;
        dbElementArray[20] = nVO_BASELAND_RENT.cr17;
        dbElementArray[21] = nVO_BASELAND_RENT.cr18;
        dbElementArray[22] = nVO_BASELAND_RENT.cr19;
        dbElementArray[23] = nVO_BASELAND_RENT.cr20;
        dbElementArray[24] = nVO_BASELAND_RENT.cr21;
        dbElementArray[25] = nVO_BASELAND_RENT.cr22;
        dbElementArray[26] = nVO_BASELAND_RENT.cr23;
        dbElementArray[27] = nVO_BASELAND_RENT.cr24;
        dbElementArray[28] = nVO_BASELAND_RENT.cr25;
        dbElementArray[29] = nVO_BASELAND_RENT.cr26;
        dbElementArray[30] = nVO_BASELAND_RENT.cr27;
        dbElementArray[31] = nVO_BASELAND_RENT.cr28;
        dbElementArray[32] = nVO_BASELAND_RENT.cr29;
        dbElementArray[33] = nVO_BASELAND_RENT.cr30;
        dbElementArray[34] = nVO_BASELAND_RENT.cr31;
        dbElementArray[35] = nVO_BASELAND_RENT.cr32;
        dbElementArray[36] = nVO_BASELAND_RENT.cr33;
        dbElementArray[37] = nVO_BASELAND_RENT.cr34;
        dbElementArray[38] = nVO_BASELAND_RENT.cr35;
        dbElementArray[39] = nVO_BASELAND_RENT.cr36;
        dbElementArray[40] = nVO_BASELAND_RENT.cr37;
        dbElementArray[41] = nVO_BASELAND_RENT.cr38;
        dbElementArray[42] = nVO_BASELAND_RENT.cr39;
        dbElementArray[43] = nVO_BASELAND_RENT.cr40;
        dbElementArray[44] = nVO_BASELAND_RENT.cr41;
        dbElementArray[45] = nVO_BASELAND_RENT.cr42;
        dbElementArray[46] = nVO_BASELAND_RENT.cr43;
        dbElementArray[47] = nVO_BASELAND_RENT.cr44;
        dbElementArray[48] = nVO_BASELAND_RENT.cr45;
        dbElementArray[49] = nVO_BASELAND_RENT.cr46;
        dbElementArray[50] = nVO_BASELAND_RENT.cr47;
        dbElementArray[51] = nVO_BASELAND_RENT.cr48;
        nVO_BASELAND_RENT.elems = dbElementArray;
        nVO_BASELAND_RENT.fieldCount = this.fieldCount;
        nVO_BASELAND_RENT.orderString = this.orderString;
        nVO_BASELAND_RENT.tableName = this.tableName;
        return nVO_BASELAND_RENT;
    }

    public double getCre07() {
        return this.cre07;
    }

    public void setCre07(double d) {
        this.cre07 = d;
    }

    public double getCre25() {
        return this.cre25;
    }

    public void setCre25(double d) {
        this.cre25 = d;
    }

    public long getCre29() {
        return this.cre29;
    }

    public void setCre29(long l) {
        this.cre29 = l;
    }

    public long getCre53() {
        return this.cre53;
    }

    public void setCre53(long l) {
        this.cre53 = l;
    }

    public long getCre54() {
        return this.cre54;
    }

    public void setCre54(long l) {
        this.cre54 = l;
    }

    public double getCre27() {
        return this.cre27;
    }

    public void setCre27(double d) {
        this.cre27 = d;
    }

    public boolean isBuilding() {
        return this.building;
    }

    public void setBuilding(boolean bl) {
        this.building = bl;
    }

    public long getCre31() {
        return this.cre31;
    }

    public void setCre31(long l) {
        this.cre31 = l;
    }

    public long getCre34() {
        return this.cre34;
    }

    public void setCre34(long l) {
        this.cre34 = l;
    }

    public double getTmpcr25() {
        return this.tmpcr25;
    }

    public void setTmpcr25(double d) {
        this.tmpcr25 = d;
    }
}

