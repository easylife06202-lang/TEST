/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.dataaccess.nvo;

import com.wfusion.dataaccess.vo.DbElement;
import com.wfusion.dataaccess.vo.DbInteger;
import com.wfusion.dataaccess.vo.DbString;
import com.wfusion.dataaccess.vo.VoBase;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import moiland.baseland.factor.face.FactorMainVoFace;

public class NVO_BASELAND_REGIONAL_FACTOR
extends VoBase
implements Serializable,
Cloneable,
FactorMainVoFace {
    private static final long serialVersionUID = 1472239797L;
    private DbString city = new DbString("city");
    private DbString dist = new DbString("dist");
    private DbString year = new DbString("year");
    private DbString version = new DbString("version");
    private DbString baseno = new DbString("baseno");
    private DbString item = new DbString("item");
    private DbInteger impact = new DbInteger("impact");
    private DbInteger degree = new DbInteger("degree");
    private DbString dnames = new DbString("dnames");
    private DbString std_type = new DbString("std_type");
    private DbString std_unit = new DbString("std_unit");
    private String mainCode = "";
    private String nameOfCity = "";
    private String nameOfDist = "";
    private String nameOfVersion = "";
    private String nameOfMainCode = "";
    private String nameOfItem = "";

    public NVO_BASELAND_REGIONAL_FACTOR() {
        this.tableName = "baseland_regional_factor";
        super.setFieldCount(11);
        this.elems = new DbElement[11];
        this.elems[0] = this.city;
        this.elems[1] = this.dist;
        this.elems[2] = this.year;
        this.elems[3] = this.version;
        this.elems[4] = this.baseno;
        this.elems[5] = this.item;
        this.elems[6] = this.impact;
        this.elems[7] = this.degree;
        this.elems[8] = this.dnames;
        this.elems[9] = this.std_type;
        this.elems[10] = this.std_unit;
        this.city.setPkFlag(true);
        this.dist.setPkFlag(true);
        this.year.setPkFlag(true);
        this.version.setPkFlag(true);
        this.baseno.setPkFlag(true);
        this.item.setPkFlag(true);
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
    public int getImpact() {
        return this.impact.getValue();
    }

    @Override
    public int getDegree() {
        return this.degree.getValue();
    }

    @Override
    public String getDnames() {
        return this.dnames.getValue();
    }

    @Override
    public String getStd_type() {
        return this.std_type.getValue();
    }

    @Override
    public String getStd_unit() {
        return this.std_unit.getValue();
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

    public void setImpact(int n) {
        this.impact.setValue(n);
    }

    public void setDegree(int n) {
        this.degree.setValue(n);
    }

    public void setDnames(String string) {
        this.dnames.setValue(string);
    }

    public void setStd_type(String string) {
        this.std_type.setValue(string);
    }

    public void setStd_unit(String string) {
        this.std_unit.setValue(string);
    }

    @Override
    public String getMainCode() {
        if ((this.mainCode == null || this.mainCode.length() == 0) && this.item.getValue().length() > 1) {
            this.mainCode = this.item.getValue().substring(0, 1);
        }
        return this.mainCode;
    }

    public void setMainCode(String string) {
        this.mainCode = string;
    }

    @Override
    public String getNameOfCity() {
        return this.nameOfCity;
    }

    public void setNameOfCity(String string) {
        this.nameOfCity = string;
    }

    @Override
    public String getNameOfDist() {
        return this.nameOfDist;
    }

    public void setNameOfDist(String string) {
        this.nameOfDist = string;
    }

    @Override
    public String getNameOfVersion() {
        return this.nameOfVersion;
    }

    public void setNameOfVersion(String string) {
        this.nameOfVersion = string;
    }

    @Override
    public String getNameOfMainCode() {
        return this.nameOfMainCode;
    }

    public void setNameOfMainCode(String string) {
        this.nameOfMainCode = string;
    }

    @Override
    public String getNameOfItem() {
        return this.nameOfItem;
    }

    public void setNameOfItem(String string) {
        this.nameOfItem = string;
    }

    @Override
    public String[] getDnamesArray() {
        return this.dnames.getValue().split(",");
    }

    public boolean isDegreeMatchDnames() {
        return this.degree.getValue() == this.dnames.getValue().split(",").length;
    }

    @Override
    public Object clone() {
        NVO_BASELAND_REGIONAL_FACTOR nVO_BASELAND_REGIONAL_FACTOR = new NVO_BASELAND_REGIONAL_FACTOR();
        DbElement[] dbElementArray = new DbElement[this.elems.length];
        nVO_BASELAND_REGIONAL_FACTOR.city = (DbString)this.city.clone();
        nVO_BASELAND_REGIONAL_FACTOR.dist = (DbString)this.dist.clone();
        nVO_BASELAND_REGIONAL_FACTOR.year = (DbString)this.year.clone();
        nVO_BASELAND_REGIONAL_FACTOR.version = (DbString)this.version.clone();
        nVO_BASELAND_REGIONAL_FACTOR.baseno = (DbString)this.baseno.clone();
        nVO_BASELAND_REGIONAL_FACTOR.item = (DbString)this.item.clone();
        nVO_BASELAND_REGIONAL_FACTOR.impact = (DbInteger)this.impact.clone();
        nVO_BASELAND_REGIONAL_FACTOR.degree = (DbInteger)this.degree.clone();
        nVO_BASELAND_REGIONAL_FACTOR.dnames = (DbString)this.dnames.clone();
        nVO_BASELAND_REGIONAL_FACTOR.std_type = (DbString)this.std_type.clone();
        nVO_BASELAND_REGIONAL_FACTOR.std_unit = (DbString)this.std_unit.clone();
        dbElementArray[0] = nVO_BASELAND_REGIONAL_FACTOR.city;
        dbElementArray[1] = nVO_BASELAND_REGIONAL_FACTOR.dist;
        dbElementArray[2] = nVO_BASELAND_REGIONAL_FACTOR.year;
        dbElementArray[3] = nVO_BASELAND_REGIONAL_FACTOR.version;
        dbElementArray[4] = nVO_BASELAND_REGIONAL_FACTOR.baseno;
        dbElementArray[5] = nVO_BASELAND_REGIONAL_FACTOR.item;
        dbElementArray[6] = nVO_BASELAND_REGIONAL_FACTOR.impact;
        dbElementArray[7] = nVO_BASELAND_REGIONAL_FACTOR.degree;
        dbElementArray[8] = nVO_BASELAND_REGIONAL_FACTOR.dnames;
        dbElementArray[9] = nVO_BASELAND_REGIONAL_FACTOR.std_type;
        dbElementArray[10] = nVO_BASELAND_REGIONAL_FACTOR.std_unit;
        nVO_BASELAND_REGIONAL_FACTOR.elems = dbElementArray;
        nVO_BASELAND_REGIONAL_FACTOR.fieldCount = this.fieldCount;
        nVO_BASELAND_REGIONAL_FACTOR.orderString = this.orderString;
        nVO_BASELAND_REGIONAL_FACTOR.tableName = this.tableName;
        return nVO_BASELAND_REGIONAL_FACTOR;
    }
}

