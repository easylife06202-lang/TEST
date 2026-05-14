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

public class NVO_BASELAND_BUILDPRICE_RATIO
extends VoBase
implements Serializable,
Cloneable {
    private static final long serialVersionUID = -1478526527L;
    private DbString basedate = new DbString("basedate");
    private DbString year = new DbString("year");
    private DbString month = new DbString("month");
    private DbDouble ratio = new DbDouble("ratio");

    public NVO_BASELAND_BUILDPRICE_RATIO() {
        this.tableName = "baseland_buildprice_ratio";
        super.setFieldCount(4);
        this.elems = new DbElement[4];
        this.elems[0] = this.basedate;
        this.elems[1] = this.year;
        this.elems[2] = this.month;
        this.elems[3] = this.ratio;
        this.basedate.setPkFlag(true);
        this.year.setPkFlag(true);
        this.month.setPkFlag(true);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
    }

    public String getBasedate() {
        return this.basedate.getValue();
    }

    public String getYear() {
        return this.year.getValue();
    }

    public String getMonth() {
        return this.month.getValue();
    }

    public double getRatio() {
        return this.ratio.getValue();
    }

    public void setBasedate(String string) {
        this.basedate.setValue(string);
    }

    public void setYear(String string) {
        this.year.setValue(string);
    }

    public void setMonth(String string) {
        this.month.setValue(string);
    }

    public void setRatio(double d) {
        this.ratio.setValue(d);
    }

    @Override
    public Object clone() {
        NVO_BASELAND_BUILDPRICE_RATIO nVO_BASELAND_BUILDPRICE_RATIO = new NVO_BASELAND_BUILDPRICE_RATIO();
        DbElement[] dbElementArray = new DbElement[this.elems.length];
        nVO_BASELAND_BUILDPRICE_RATIO.basedate = (DbString)this.basedate.clone();
        nVO_BASELAND_BUILDPRICE_RATIO.year = (DbString)this.year.clone();
        nVO_BASELAND_BUILDPRICE_RATIO.month = (DbString)this.month.clone();
        nVO_BASELAND_BUILDPRICE_RATIO.ratio = (DbDouble)this.ratio.clone();
        dbElementArray[0] = nVO_BASELAND_BUILDPRICE_RATIO.basedate;
        dbElementArray[1] = nVO_BASELAND_BUILDPRICE_RATIO.year;
        dbElementArray[2] = nVO_BASELAND_BUILDPRICE_RATIO.month;
        dbElementArray[3] = nVO_BASELAND_BUILDPRICE_RATIO.ratio;
        nVO_BASELAND_BUILDPRICE_RATIO.elems = dbElementArray;
        nVO_BASELAND_BUILDPRICE_RATIO.fieldCount = this.fieldCount;
        nVO_BASELAND_BUILDPRICE_RATIO.orderString = this.orderString;
        nVO_BASELAND_BUILDPRICE_RATIO.tableName = this.tableName;
        return nVO_BASELAND_BUILDPRICE_RATIO;
    }
}

