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

public class NVO_BASELAND_INSTRU
extends VoBase
implements Serializable,
Cloneable {
    private static final long serialVersionUID = 1979028227L;
    private DbString instru_code = new DbString("instru_code");
    private DbString instru_name = new DbString("instru_name");
    private DbInteger year_limits = new DbInteger("year_limits");
    private DbDouble residual_rate = new DbDouble("residual_rate");
    private DbString regd_codes = new DbString("regd_codes");

    public NVO_BASELAND_INSTRU() {
        this.tableName = "baseland_instru";
        super.setFieldCount(5);
        this.elems = new DbElement[5];
        this.elems[0] = this.instru_code;
        this.elems[1] = this.instru_name;
        this.elems[2] = this.year_limits;
        this.elems[3] = this.residual_rate;
        this.elems[4] = this.regd_codes;
        this.instru_code.setPkFlag(true);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
    }

    public String getInstru_code() {
        return this.instru_code.getValue();
    }

    public String getInstru_name() {
        return this.instru_name.getValue();
    }

    public int getYear_limits() {
        return this.year_limits.getValue();
    }

    public double getResidual_rate() {
        return this.residual_rate.getValue();
    }

    public String getRegd_codes() {
        return this.regd_codes.getValue();
    }

    public void setInstru_code(String string) {
        this.instru_code.setValue(string);
    }

    public void setInstru_name(String string) {
        this.instru_name.setValue(string);
    }

    public void setYear_limits(int n) {
        this.year_limits.setValue(n);
    }

    public void setResidual_rate(double d) {
        this.residual_rate.setValue(d);
    }

    public void setRegd_codes(String string) {
        this.regd_codes.setValue(string);
    }

    @Override
    public Object clone() {
        NVO_BASELAND_INSTRU nVO_BASELAND_INSTRU = new NVO_BASELAND_INSTRU();
        DbElement[] dbElementArray = new DbElement[this.elems.length];
        nVO_BASELAND_INSTRU.instru_code = (DbString)this.instru_code.clone();
        nVO_BASELAND_INSTRU.instru_name = (DbString)this.instru_name.clone();
        nVO_BASELAND_INSTRU.year_limits = (DbInteger)this.year_limits.clone();
        nVO_BASELAND_INSTRU.residual_rate = (DbDouble)this.residual_rate.clone();
        nVO_BASELAND_INSTRU.regd_codes = (DbString)this.regd_codes.clone();
        dbElementArray[0] = nVO_BASELAND_INSTRU.instru_code;
        dbElementArray[1] = nVO_BASELAND_INSTRU.instru_name;
        dbElementArray[2] = nVO_BASELAND_INSTRU.year_limits;
        dbElementArray[3] = nVO_BASELAND_INSTRU.residual_rate;
        dbElementArray[4] = nVO_BASELAND_INSTRU.regd_codes;
        nVO_BASELAND_INSTRU.elems = dbElementArray;
        nVO_BASELAND_INSTRU.fieldCount = this.fieldCount;
        nVO_BASELAND_INSTRU.orderString = this.orderString;
        nVO_BASELAND_INSTRU.tableName = this.tableName;
        return nVO_BASELAND_INSTRU;
    }
}

