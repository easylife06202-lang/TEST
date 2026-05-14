/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.dataaccess.nvo;

import com.wfusion.dataaccess.vo.DbElement;
import com.wfusion.dataaccess.vo.DbString;
import com.wfusion.dataaccess.vo.VoBase;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class NVO_SRKEYN
extends VoBase
implements Serializable,
Cloneable {
    private static final long serialVersionUID = 7496998083145519442L;
    private DbString kcde_1 = new DbString("kcde_1");
    private DbString kcde_2 = new DbString("kcde_2");
    private DbString kcnt = new DbString("kcnt");
    private DbString krmk = new DbString("krmk");

    public NVO_SRKEYN() {
        this.tableName = "srkeyn";
        super.setFieldCount(4);
        this.elems = new DbElement[4];
        this.elems[0] = this.kcde_1;
        this.elems[1] = this.kcde_2;
        this.elems[2] = this.kcnt;
        this.elems[3] = this.krmk;
        this.kcde_1.setPkFlag(true);
        this.kcde_2.setPkFlag(true);
        this.kcnt.setPkFlag(true);
        this.krmk.setPkFlag(true);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
    }

    public String getKcde_1() {
        return this.kcde_1.getValue();
    }

    public String getKcde_2() {
        return this.kcde_2.getValue();
    }

    public String getKcnt() {
        return this.kcnt.getValue();
    }

    public String getKrmk() {
        return this.krmk.getValue();
    }

    public void setKcde_1(String string) {
        this.kcde_1.setValue(string);
    }

    public void setKcde_2(String string) {
        this.kcde_2.setValue(string);
    }

    public void setKcnt(String string) {
        this.kcnt.setValue(string);
    }

    public void setKrmk(String string) {
        this.krmk.setValue(string);
    }

    @Override
    public Object clone() {
        NVO_SRKEYN nVO_SRKEYN = new NVO_SRKEYN();
        DbElement[] dbElementArray = new DbElement[this.elems.length];
        nVO_SRKEYN.kcde_1 = (DbString)this.kcde_1.clone();
        nVO_SRKEYN.kcde_2 = (DbString)this.kcde_2.clone();
        nVO_SRKEYN.kcnt = (DbString)this.kcnt.clone();
        nVO_SRKEYN.krmk = (DbString)this.krmk.clone();
        dbElementArray[0] = nVO_SRKEYN.kcde_1;
        dbElementArray[1] = nVO_SRKEYN.kcde_2;
        dbElementArray[2] = nVO_SRKEYN.kcnt;
        dbElementArray[3] = nVO_SRKEYN.krmk;
        nVO_SRKEYN.elems = dbElementArray;
        nVO_SRKEYN.fieldCount = this.fieldCount;
        nVO_SRKEYN.orderString = this.orderString;
        nVO_SRKEYN.tableName = this.tableName;
        return nVO_SRKEYN;
    }
}

