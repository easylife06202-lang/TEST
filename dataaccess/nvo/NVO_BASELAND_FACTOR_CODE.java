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

public class NVO_BASELAND_FACTOR_CODE
extends VoBase
implements Serializable,
Cloneable {
    private static final long serialVersionUID = -813288669L;
    private DbString code_0 = new DbString("code_0");
    private DbString code_1 = new DbString("code_1");
    private DbString code_2 = new DbString("code_2");
    private DbString code_3 = new DbString("code_3");
    private DbString name = new DbString("name");
    private DbString a3 = new DbString("a3");
    private DbString a3bd = new DbString("a3bd");
    private DbString a3bf = new DbString("a3bf");
    private DbInteger sna3 = new DbInteger("sna3");
    private DbInteger sna3bd = new DbInteger("sna3bd");
    private DbInteger sna3bf = new DbInteger("sna3bf");
    private DbString asfield = new DbString("asfield");
    private DbString stdtype = new DbString("stdtype");
    private DbString stdunit = new DbString("stdunit");

    public NVO_BASELAND_FACTOR_CODE() {
        this.tableName = "baseland_factor_code";
        super.setFieldCount(14);
        this.elems = new DbElement[14];
        this.elems[0] = this.code_0;
        this.elems[1] = this.code_1;
        this.elems[2] = this.code_2;
        this.elems[3] = this.code_3;
        this.elems[4] = this.name;
        this.elems[5] = this.a3;
        this.elems[6] = this.a3bd;
        this.elems[7] = this.a3bf;
        this.elems[8] = this.sna3;
        this.elems[9] = this.sna3bd;
        this.elems[10] = this.sna3bf;
        this.elems[11] = this.asfield;
        this.elems[12] = this.stdtype;
        this.elems[13] = this.stdunit;
        this.code_0.setPkFlag(true);
        this.code_1.setPkFlag(true);
        this.code_2.setPkFlag(true);
        this.code_3.setPkFlag(true);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
    }

    public String getCode_0() {
        return this.code_0.getValue();
    }

    public String getCode_1() {
        return this.code_1.getValue();
    }

    public String getCode_2() {
        return this.code_2.getValue();
    }

    public String getCode_3() {
        return this.code_3.getValue();
    }

    public String getName() {
        return this.name.getValue();
    }

    public String getA3() {
        return this.a3.getValue();
    }

    public String getA3bd() {
        return this.a3bd.getValue();
    }

    public String getA3bf() {
        return this.a3bf.getValue();
    }

    public int getSna3() {
        return this.sna3.getValue();
    }

    public int getSna3bd() {
        return this.sna3bd.getValue();
    }

    public int getSna3bf() {
        return this.sna3bf.getValue();
    }

    public String getAsfield() {
        return this.asfield.getValue();
    }

    public String getStdtype() {
        return this.stdtype.getValue();
    }

    public String getStdunit() {
        return this.stdunit.getValue();
    }

    public void setCode_0(String string) {
        this.code_0.setValue(string);
    }

    public void setCode_1(String string) {
        this.code_1.setValue(string);
    }

    public void setCode_2(String string) {
        this.code_2.setValue(string);
    }

    public void setCode_3(String string) {
        this.code_3.setValue(string);
    }

    public void setName(String string) {
        this.name.setValue(string);
    }

    public void setA3(String string) {
        this.a3.setValue(string);
    }

    public void setA3bd(String string) {
        this.a3bd.setValue(string);
    }

    public void setA3bf(String string) {
        this.a3bf.setValue(string);
    }

    public void setSna3(int n) {
        this.sna3.setValue(n);
    }

    public void setSna3bd(int n) {
        this.sna3bd.setValue(n);
    }

    public void setSna3bf(int n) {
        this.sna3bf.setValue(n);
    }

    public void setAsfield(String string) {
        this.asfield.setValue(string);
    }

    public void setStdtype(String string) {
        this.stdtype.setValue(string);
    }

    public void setStdunit(String string) {
        this.stdunit.setValue(string);
    }

    @Override
    public Object clone() {
        NVO_BASELAND_FACTOR_CODE nVO_BASELAND_FACTOR_CODE = new NVO_BASELAND_FACTOR_CODE();
        DbElement[] dbElementArray = new DbElement[this.elems.length];
        nVO_BASELAND_FACTOR_CODE.code_0 = (DbString)this.code_0.clone();
        nVO_BASELAND_FACTOR_CODE.code_1 = (DbString)this.code_1.clone();
        nVO_BASELAND_FACTOR_CODE.code_2 = (DbString)this.code_2.clone();
        nVO_BASELAND_FACTOR_CODE.code_3 = (DbString)this.code_3.clone();
        nVO_BASELAND_FACTOR_CODE.name = (DbString)this.name.clone();
        nVO_BASELAND_FACTOR_CODE.a3 = (DbString)this.a3.clone();
        nVO_BASELAND_FACTOR_CODE.a3bd = (DbString)this.a3bd.clone();
        nVO_BASELAND_FACTOR_CODE.a3bf = (DbString)this.a3bf.clone();
        nVO_BASELAND_FACTOR_CODE.sna3 = (DbInteger)this.sna3.clone();
        nVO_BASELAND_FACTOR_CODE.sna3bd = (DbInteger)this.sna3bd.clone();
        nVO_BASELAND_FACTOR_CODE.sna3bf = (DbInteger)this.sna3bf.clone();
        nVO_BASELAND_FACTOR_CODE.asfield = (DbString)this.asfield.clone();
        nVO_BASELAND_FACTOR_CODE.stdtype = (DbString)this.stdtype.clone();
        nVO_BASELAND_FACTOR_CODE.stdunit = (DbString)this.stdunit.clone();
        dbElementArray[0] = nVO_BASELAND_FACTOR_CODE.code_0;
        dbElementArray[1] = nVO_BASELAND_FACTOR_CODE.code_1;
        dbElementArray[2] = nVO_BASELAND_FACTOR_CODE.code_2;
        dbElementArray[3] = nVO_BASELAND_FACTOR_CODE.code_3;
        dbElementArray[4] = nVO_BASELAND_FACTOR_CODE.name;
        dbElementArray[5] = nVO_BASELAND_FACTOR_CODE.a3;
        dbElementArray[6] = nVO_BASELAND_FACTOR_CODE.a3bd;
        dbElementArray[7] = nVO_BASELAND_FACTOR_CODE.a3bf;
        dbElementArray[8] = nVO_BASELAND_FACTOR_CODE.sna3;
        dbElementArray[9] = nVO_BASELAND_FACTOR_CODE.sna3bd;
        dbElementArray[10] = nVO_BASELAND_FACTOR_CODE.sna3bf;
        dbElementArray[11] = nVO_BASELAND_FACTOR_CODE.asfield;
        dbElementArray[12] = nVO_BASELAND_FACTOR_CODE.stdtype;
        dbElementArray[13] = nVO_BASELAND_FACTOR_CODE.stdunit;
        nVO_BASELAND_FACTOR_CODE.elems = dbElementArray;
        nVO_BASELAND_FACTOR_CODE.fieldCount = this.fieldCount;
        nVO_BASELAND_FACTOR_CODE.orderString = this.orderString;
        nVO_BASELAND_FACTOR_CODE.tableName = this.tableName;
        return nVO_BASELAND_FACTOR_CODE;
    }
}

