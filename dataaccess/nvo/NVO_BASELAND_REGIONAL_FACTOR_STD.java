/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.dataaccess.nvo;

import com.wfusion.dataaccess.vo.DbDouble;
import com.wfusion.dataaccess.vo.DbElement;
import com.wfusion.dataaccess.vo.DbInteger;
import com.wfusion.dataaccess.vo.DbString;
import com.wfusion.dataaccess.vo.VoBase;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import moiland.baseland.factor.face.FactorStdVoFace;

public class NVO_BASELAND_REGIONAL_FACTOR_STD
extends VoBase
implements Serializable,
Cloneable,
FactorStdVoFace {
    private static final long serialVersionUID = -539515015L;
    private DbString city = new DbString("city");
    private DbString dist = new DbString("dist");
    private DbString year = new DbString("year");
    private DbString version = new DbString("version");
    private DbString baseno = new DbString("baseno");
    private DbString item = new DbString("item");
    private DbInteger level = new DbInteger("level");
    private DbString none = new DbString("none");
    private DbString contents = new DbString("contents");
    private DbString a_symbol = new DbString("a_symbol");
    private DbDouble a_digital = new DbDouble("a_digital");
    private DbString ab_logic = new DbString("ab_logic");
    private DbString b_symbol = new DbString("b_symbol");
    private DbDouble b_digital = new DbDouble("b_digital");
    private DbString bc_logic = new DbString("bc_logic");
    private DbString c_symbol = new DbString("c_symbol");
    private DbDouble c_digital = new DbDouble("c_digital");
    private DbString cd_logic = new DbString("cd_logic");
    private DbString d_symbol = new DbString("d_symbol");
    private DbDouble d_digital = new DbDouble("d_digital");

    public NVO_BASELAND_REGIONAL_FACTOR_STD() {
        this.tableName = "baseland_regional_factor_std";
        super.setFieldCount(20);
        this.elems = new DbElement[20];
        this.elems[0] = this.city;
        this.elems[1] = this.dist;
        this.elems[2] = this.year;
        this.elems[3] = this.version;
        this.elems[4] = this.baseno;
        this.elems[5] = this.item;
        this.elems[6] = this.level;
        this.elems[7] = this.none;
        this.elems[8] = this.contents;
        this.elems[9] = this.a_symbol;
        this.elems[10] = this.a_digital;
        this.elems[11] = this.ab_logic;
        this.elems[12] = this.b_symbol;
        this.elems[13] = this.b_digital;
        this.elems[14] = this.bc_logic;
        this.elems[15] = this.c_symbol;
        this.elems[16] = this.c_digital;
        this.elems[17] = this.cd_logic;
        this.elems[18] = this.d_symbol;
        this.elems[19] = this.d_digital;
        this.city.setPkFlag(true);
        this.dist.setPkFlag(true);
        this.year.setPkFlag(true);
        this.version.setPkFlag(true);
        this.baseno.setPkFlag(true);
        this.item.setPkFlag(true);
        this.level.setPkFlag(true);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
    }

    @Override
    public String getCity() {
        return this.city.getValue();
    }

    @Override
    public String getDist() {
        return this.dist.getValue();
    }

    @Override
    public String getYear() {
        return this.year.getValue();
    }

    @Override
    public String getVersion() {
        return this.version.getValue();
    }

    @Override
    public String getBaseno() {
        return this.baseno.getValue();
    }

    @Override
    public String getItem() {
        return this.item.getValue();
    }

    @Override
    public int getLevel() {
        return this.level.getValue();
    }

    @Override
    public String getNone() {
        return this.none.getValue();
    }

    @Override
    public String getContents() {
        return this.contents.getValue();
    }

    @Override
    public String getA_symbol() {
        return this.a_symbol.getValue();
    }

    @Override
    public double getA_digital() {
        return this.a_digital.getValue();
    }

    @Override
    public String getAb_logic() {
        return this.ab_logic.getValue();
    }

    @Override
    public String getB_symbol() {
        return this.b_symbol.getValue();
    }

    @Override
    public double getB_digital() {
        return this.b_digital.getValue();
    }

    @Override
    public String getBc_logic() {
        return this.bc_logic.getValue();
    }

    @Override
    public String getC_symbol() {
        return this.c_symbol.getValue();
    }

    @Override
    public double getC_digital() {
        return this.c_digital.getValue();
    }

    @Override
    public String getCd_logic() {
        return this.cd_logic.getValue();
    }

    @Override
    public String getD_symbol() {
        return this.d_symbol.getValue();
    }

    @Override
    public double getD_digital() {
        return this.d_digital.getValue();
    }

    public void setCity(String string) {
        this.city.setValue(string);
    }

    public void setDist(String string) {
        this.dist.setValue(string);
    }

    public void setYear(String string) {
        this.year.setValue(string);
    }

    public void setVersion(String string) {
        this.version.setValue(string);
    }

    public void setBaseno(String string) {
        this.baseno.setValue(string);
    }

    public void setItem(String string) {
        this.item.setValue(string);
    }

    public void setLevel(int n) {
        this.level.setValue(n);
    }

    public void setNone(String string) {
        this.none.setValue(string);
    }

    public void setContents(String string) {
        this.contents.setValue(string);
    }

    public void setA_symbol(String string) {
        this.a_symbol.setValue(string);
    }

    public void setA_digital(double d) {
        this.a_digital.setValue(d);
    }

    public void setAb_logic(String string) {
        this.ab_logic.setValue(string);
    }

    public void setB_symbol(String string) {
        this.b_symbol.setValue(string);
    }

    public void setB_digital(double d) {
        this.b_digital.setValue(d);
    }

    public void setBc_logic(String string) {
        this.bc_logic.setValue(string);
    }

    public void setC_symbol(String string) {
        this.c_symbol.setValue(string);
    }

    public void setC_digital(double d) {
        this.c_digital.setValue(d);
    }

    public void setCd_logic(String string) {
        this.cd_logic.setValue(string);
    }

    public void setD_symbol(String string) {
        this.d_symbol.setValue(string);
    }

    public void setD_digital(double d) {
        this.d_digital.setValue(d);
    }

    @Override
    public Object clone() {
        NVO_BASELAND_REGIONAL_FACTOR_STD nVO_BASELAND_REGIONAL_FACTOR_STD = new NVO_BASELAND_REGIONAL_FACTOR_STD();
        DbElement[] dbElementArray = new DbElement[this.elems.length];
        nVO_BASELAND_REGIONAL_FACTOR_STD.city = (DbString)this.city.clone();
        nVO_BASELAND_REGIONAL_FACTOR_STD.dist = (DbString)this.dist.clone();
        nVO_BASELAND_REGIONAL_FACTOR_STD.year = (DbString)this.year.clone();
        nVO_BASELAND_REGIONAL_FACTOR_STD.version = (DbString)this.version.clone();
        nVO_BASELAND_REGIONAL_FACTOR_STD.baseno = (DbString)this.baseno.clone();
        nVO_BASELAND_REGIONAL_FACTOR_STD.item = (DbString)this.item.clone();
        nVO_BASELAND_REGIONAL_FACTOR_STD.level = (DbInteger)this.level.clone();
        nVO_BASELAND_REGIONAL_FACTOR_STD.none = (DbString)this.none.clone();
        nVO_BASELAND_REGIONAL_FACTOR_STD.contents = (DbString)this.contents.clone();
        nVO_BASELAND_REGIONAL_FACTOR_STD.a_symbol = (DbString)this.a_symbol.clone();
        nVO_BASELAND_REGIONAL_FACTOR_STD.a_digital = (DbDouble)this.a_digital.clone();
        nVO_BASELAND_REGIONAL_FACTOR_STD.ab_logic = (DbString)this.ab_logic.clone();
        nVO_BASELAND_REGIONAL_FACTOR_STD.b_symbol = (DbString)this.b_symbol.clone();
        nVO_BASELAND_REGIONAL_FACTOR_STD.b_digital = (DbDouble)this.b_digital.clone();
        nVO_BASELAND_REGIONAL_FACTOR_STD.bc_logic = (DbString)this.bc_logic.clone();
        nVO_BASELAND_REGIONAL_FACTOR_STD.c_symbol = (DbString)this.c_symbol.clone();
        nVO_BASELAND_REGIONAL_FACTOR_STD.c_digital = (DbDouble)this.c_digital.clone();
        nVO_BASELAND_REGIONAL_FACTOR_STD.cd_logic = (DbString)this.cd_logic.clone();
        nVO_BASELAND_REGIONAL_FACTOR_STD.d_symbol = (DbString)this.d_symbol.clone();
        nVO_BASELAND_REGIONAL_FACTOR_STD.d_digital = (DbDouble)this.d_digital.clone();
        dbElementArray[0] = nVO_BASELAND_REGIONAL_FACTOR_STD.city;
        dbElementArray[1] = nVO_BASELAND_REGIONAL_FACTOR_STD.dist;
        dbElementArray[2] = nVO_BASELAND_REGIONAL_FACTOR_STD.year;
        dbElementArray[3] = nVO_BASELAND_REGIONAL_FACTOR_STD.version;
        dbElementArray[4] = nVO_BASELAND_REGIONAL_FACTOR_STD.baseno;
        dbElementArray[5] = nVO_BASELAND_REGIONAL_FACTOR_STD.item;
        dbElementArray[6] = nVO_BASELAND_REGIONAL_FACTOR_STD.level;
        dbElementArray[7] = nVO_BASELAND_REGIONAL_FACTOR_STD.none;
        dbElementArray[8] = nVO_BASELAND_REGIONAL_FACTOR_STD.contents;
        dbElementArray[9] = nVO_BASELAND_REGIONAL_FACTOR_STD.a_symbol;
        dbElementArray[10] = nVO_BASELAND_REGIONAL_FACTOR_STD.a_digital;
        dbElementArray[11] = nVO_BASELAND_REGIONAL_FACTOR_STD.ab_logic;
        dbElementArray[12] = nVO_BASELAND_REGIONAL_FACTOR_STD.b_symbol;
        dbElementArray[13] = nVO_BASELAND_REGIONAL_FACTOR_STD.b_digital;
        dbElementArray[14] = nVO_BASELAND_REGIONAL_FACTOR_STD.bc_logic;
        dbElementArray[15] = nVO_BASELAND_REGIONAL_FACTOR_STD.c_symbol;
        dbElementArray[16] = nVO_BASELAND_REGIONAL_FACTOR_STD.c_digital;
        dbElementArray[17] = nVO_BASELAND_REGIONAL_FACTOR_STD.cd_logic;
        dbElementArray[18] = nVO_BASELAND_REGIONAL_FACTOR_STD.d_symbol;
        dbElementArray[19] = nVO_BASELAND_REGIONAL_FACTOR_STD.d_digital;
        nVO_BASELAND_REGIONAL_FACTOR_STD.elems = dbElementArray;
        nVO_BASELAND_REGIONAL_FACTOR_STD.fieldCount = this.fieldCount;
        nVO_BASELAND_REGIONAL_FACTOR_STD.orderString = this.orderString;
        nVO_BASELAND_REGIONAL_FACTOR_STD.tableName = this.tableName;
        return nVO_BASELAND_REGIONAL_FACTOR_STD;
    }
}

