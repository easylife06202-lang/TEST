/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.dataaccess.nvo;

import com.wfusion.dataaccess.vo.DbDouble;
import com.wfusion.dataaccess.vo.DbElement;
import com.wfusion.dataaccess.vo.DbString;
import com.wfusion.dataaccess.vo.VoBase;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class NVO_BASELAND_FLOOR_EFFECT
extends VoBase
implements Serializable,
Cloneable {
    private static final long serialVersionUID = 1735230440L;
    private DbString baseno = new DbString("baseno");
    private DbString year = new DbString("year");
    private DbString caseno = new DbString("caseno");
    private DbString city = new DbString("city");
    private DbString ofce = new DbString("ofce");
    private DbString jsondata = new DbString("jsondata");
    private DbDouble avg_effect = new DbDouble("avg_effect");
    private DbDouble avg_ratio = new DbDouble("avg_ratio");

    public NVO_BASELAND_FLOOR_EFFECT() {
        this.tableName = "baseland_floor_effect";
        super.setFieldCount(8);
        this.elems = new DbElement[8];
        this.elems[0] = this.baseno;
        this.elems[1] = this.year;
        this.elems[2] = this.caseno;
        this.elems[3] = this.city;
        this.elems[4] = this.ofce;
        this.elems[5] = this.jsondata;
        this.elems[6] = this.avg_effect;
        this.elems[7] = this.avg_ratio;
        this.baseno.setPkFlag(true);
        this.year.setPkFlag(true);
        this.caseno.setPkFlag(true);
        this.city.setPkFlag(true);
        this.ofce.setPkFlag(true);
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

    public String getOfce() {
        return this.ofce.getValue();
    }

    public String getJsondata() {
        return this.jsondata.getValue();
    }

    public double getAvg_effect() {
        return this.avg_effect.getValue();
    }

    public double getAvg_ratio() {
        return this.avg_ratio.getValue();
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

    public void setOfce(String string) {
        this.ofce.setValue(string);
    }

    public void setJsondata(String string) {
        this.jsondata.setValue(string);
    }

    public void setAvg_effect(double d) {
        this.avg_effect.setValue(d);
    }

    public void setAvg_ratio(double d) {
        this.avg_ratio.setValue(d);
    }

    @Override
    public Object clone() {
        NVO_BASELAND_FLOOR_EFFECT nVO_BASELAND_FLOOR_EFFECT = new NVO_BASELAND_FLOOR_EFFECT();
        DbElement[] dbElementArray = new DbElement[this.elems.length];
        nVO_BASELAND_FLOOR_EFFECT.baseno = (DbString)this.baseno.clone();
        nVO_BASELAND_FLOOR_EFFECT.year = (DbString)this.year.clone();
        nVO_BASELAND_FLOOR_EFFECT.caseno = (DbString)this.caseno.clone();
        nVO_BASELAND_FLOOR_EFFECT.city = (DbString)this.city.clone();
        nVO_BASELAND_FLOOR_EFFECT.ofce = (DbString)this.ofce.clone();
        nVO_BASELAND_FLOOR_EFFECT.jsondata = (DbString)this.jsondata.clone();
        nVO_BASELAND_FLOOR_EFFECT.avg_effect = (DbDouble)this.avg_effect.clone();
        nVO_BASELAND_FLOOR_EFFECT.avg_ratio = (DbDouble)this.avg_ratio.clone();
        dbElementArray[0] = nVO_BASELAND_FLOOR_EFFECT.baseno;
        dbElementArray[1] = nVO_BASELAND_FLOOR_EFFECT.year;
        dbElementArray[2] = nVO_BASELAND_FLOOR_EFFECT.caseno;
        dbElementArray[3] = nVO_BASELAND_FLOOR_EFFECT.city;
        dbElementArray[4] = nVO_BASELAND_FLOOR_EFFECT.ofce;
        dbElementArray[5] = nVO_BASELAND_FLOOR_EFFECT.jsondata;
        dbElementArray[6] = nVO_BASELAND_FLOOR_EFFECT.avg_effect;
        dbElementArray[7] = nVO_BASELAND_FLOOR_EFFECT.avg_ratio;
        nVO_BASELAND_FLOOR_EFFECT.elems = dbElementArray;
        nVO_BASELAND_FLOOR_EFFECT.fieldCount = this.fieldCount;
        nVO_BASELAND_FLOOR_EFFECT.orderString = this.orderString;
        nVO_BASELAND_FLOOR_EFFECT.tableName = this.tableName;
        return nVO_BASELAND_FLOOR_EFFECT;
    }
}

