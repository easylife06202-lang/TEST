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

public class NVO_BASELAND_PRICERATE
extends VoBase
implements Serializable,
Cloneable {
    private static final long serialVersionUID = -1955962033L;
    private DbString city = new DbString("city");
    private DbString rate_type = new DbString("rate_type");
    private DbString dist = new DbString("dist");
    private DbString year = new DbString("year");
    private DbString ym = new DbString("ym");
    private DbDouble index_rate = new DbDouble("index_rate");
    private String nameOfCity = "";
    private String nameOfDist = "";
    private String nameOfRateType = "";
    private String nameOfYm = "";
    private String dataType = "";

    public NVO_BASELAND_PRICERATE() {
        this.tableName = "baseland_pricerate";
        super.setFieldCount(6);
        this.elems = new DbElement[6];
        this.elems[0] = this.city;
        this.elems[1] = this.rate_type;
        this.elems[2] = this.dist;
        this.elems[3] = this.year;
        this.elems[4] = this.ym;
        this.elems[5] = this.index_rate;
        this.city.setPkFlag(true);
        this.rate_type.setPkFlag(true);
        this.dist.setPkFlag(true);
        this.year.setPkFlag(true);
        this.ym.setPkFlag(true);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
    }

    public String getCity() {
        return this.city.getValue();
    }

    public String getRate_type() {
        return this.rate_type.getValue();
    }

    public String getDist() {
        return this.dist.getValue();
    }

    public String getYear() {
        return this.year.getValue();
    }

    public String getYm() {
        return this.ym.getValue();
    }

    public double getIndex_rate() {
        return this.index_rate.getValue();
    }

    public void setCity(String string) {
        this.city.setValue(string);
    }

    public void setRate_type(String string) {
        this.rate_type.setValue(string);
    }

    public void setDist(String string) {
        this.dist.setValue(string);
    }

    public void setYear(String string) {
        this.year.setValue(string);
    }

    public void setYm(String string) {
        this.ym.setValue(string);
    }

    public void setIndex_rate(double d) {
        this.index_rate.setValue(d);
    }

    public String getNameOfYm() {
        return this.ym.getValue().substring(0, 3) + " \u5e74 " + this.getMonthOfYm() + " \u6708";
    }

    public String getYearOfYm() {
        return this.ym.getValue().substring(0, 3);
    }

    public String getMonthOfYm() {
        return this.ym.getValue().substring(3);
    }

    public String getNameOfCity() {
        return this.nameOfCity;
    }

    public void setNameOfCity(String string) {
        this.nameOfCity = string;
    }

    public String getNameOfDist() {
        return this.nameOfDist;
    }

    public void setNameOfDist(String string) {
        this.nameOfDist = string;
    }

    public String getNameOfRateType() {
        return this.nameOfRateType;
    }

    public void setNameOfRateType(String string) {
        this.nameOfRateType = string;
    }

    public String getDataType() {
        return this.dataType;
    }

    public void setDataType(String string) {
        this.dataType = string;
    }

    @Override
    public Object clone() {
        NVO_BASELAND_PRICERATE nVO_BASELAND_PRICERATE = new NVO_BASELAND_PRICERATE();
        DbElement[] dbElementArray = new DbElement[this.elems.length];
        nVO_BASELAND_PRICERATE.city = (DbString)this.city.clone();
        nVO_BASELAND_PRICERATE.rate_type = (DbString)this.rate_type.clone();
        nVO_BASELAND_PRICERATE.dist = (DbString)this.dist.clone();
        nVO_BASELAND_PRICERATE.year = (DbString)this.year.clone();
        nVO_BASELAND_PRICERATE.ym = (DbString)this.ym.clone();
        nVO_BASELAND_PRICERATE.index_rate = (DbDouble)this.index_rate.clone();
        dbElementArray[0] = nVO_BASELAND_PRICERATE.city;
        dbElementArray[1] = nVO_BASELAND_PRICERATE.rate_type;
        dbElementArray[2] = nVO_BASELAND_PRICERATE.dist;
        dbElementArray[3] = nVO_BASELAND_PRICERATE.year;
        dbElementArray[4] = nVO_BASELAND_PRICERATE.ym;
        dbElementArray[5] = nVO_BASELAND_PRICERATE.index_rate;
        nVO_BASELAND_PRICERATE.elems = dbElementArray;
        nVO_BASELAND_PRICERATE.fieldCount = this.fieldCount;
        nVO_BASELAND_PRICERATE.orderString = this.orderString;
        nVO_BASELAND_PRICERATE.tableName = this.tableName;
        return nVO_BASELAND_PRICERATE;
    }
}

