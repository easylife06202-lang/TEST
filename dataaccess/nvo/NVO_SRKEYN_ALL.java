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

public class NVO_SRKEYN_ALL
extends VoBase
implements Serializable,
Cloneable {
    private static final long serialVersionUID = 6830321535680892299L;
    private DbString kcde_1 = new DbString("kcde_1");
    private DbString kcde_2 = new DbString("kcde_2");
    private DbString kcde_3 = new DbString("kcde_3");
    private DbString kcde_4 = new DbString("kcde_4");
    private DbString kname = new DbString("kname");
    private DbString krmk = new DbString("krmk");

    public NVO_SRKEYN_ALL() {
        this.tableName = "srkeyn_all";
        super.setFieldCount(6);
        this.elems = new DbElement[6];
        this.elems[0] = this.kcde_1;
        this.elems[1] = this.kcde_2;
        this.elems[2] = this.kcde_3;
        this.elems[3] = this.kcde_4;
        this.elems[4] = this.kname;
        this.elems[5] = this.krmk;
        this.kcde_1.setPkFlag(true);
        this.kcde_2.setPkFlag(true);
        this.kcde_4.setPkFlag(true);
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

    public String getKcde_3() {
        return this.kcde_3.getValue();
    }

    public String getKcde_4() {
        return this.kcde_4.getValue();
    }

    public String getKname() {
        return this.kname.getValue();
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

    public void setKcde_3(String string) {
        this.kcde_3.setValue(string);
    }

    public void setKcde_4(String string) {
        this.kcde_4.setValue(string);
    }

    public void setKname(String string) {
        this.kname.setValue(string);
    }

    public void setKrmk(String string) {
        this.krmk.setValue(string);
    }

    @Override
    public Object clone() {
        NVO_SRKEYN_ALL nVO_SRKEYN_ALL = new NVO_SRKEYN_ALL();
        DbElement[] dbElementArray = new DbElement[this.elems.length];
        nVO_SRKEYN_ALL.kcde_1 = (DbString)this.kcde_1.clone();
        nVO_SRKEYN_ALL.kcde_2 = (DbString)this.kcde_2.clone();
        nVO_SRKEYN_ALL.kcde_3 = (DbString)this.kcde_3.clone();
        nVO_SRKEYN_ALL.kcde_4 = (DbString)this.kcde_4.clone();
        nVO_SRKEYN_ALL.kname = (DbString)this.kname.clone();
        nVO_SRKEYN_ALL.krmk = (DbString)this.krmk.clone();
        dbElementArray[0] = nVO_SRKEYN_ALL.kcde_1;
        dbElementArray[1] = nVO_SRKEYN_ALL.kcde_2;
        dbElementArray[2] = nVO_SRKEYN_ALL.kcde_3;
        dbElementArray[3] = nVO_SRKEYN_ALL.kcde_4;
        dbElementArray[4] = nVO_SRKEYN_ALL.kname;
        dbElementArray[5] = nVO_SRKEYN_ALL.krmk;
        nVO_SRKEYN_ALL.elems = dbElementArray;
        nVO_SRKEYN_ALL.fieldCount = this.fieldCount;
        nVO_SRKEYN_ALL.orderString = this.orderString;
        nVO_SRKEYN_ALL.tableName = this.tableName;
        return nVO_SRKEYN_ALL;
    }
}

