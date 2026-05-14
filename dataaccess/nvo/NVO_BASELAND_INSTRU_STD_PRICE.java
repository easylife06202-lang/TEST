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

public class NVO_BASELAND_INSTRU_STD_PRICE
extends VoBase
implements Serializable,
Cloneable {
    private static final long serialVersionUID = -601000527L;
    private DbString city = new DbString("city");
    private DbString instru_code = new DbString("instru_code");
    private DbInteger floor_start = new DbInteger("floor_start");
    private DbInteger floor_end = new DbInteger("floor_end");
    private DbInteger uniprice = new DbInteger("uniprice");
    private String nameOfCity = "";

    public NVO_BASELAND_INSTRU_STD_PRICE() {
        this.tableName = "baseland_instru_std_price";
        super.setFieldCount(5);
        this.elems = new DbElement[5];
        this.elems[0] = this.city;
        this.elems[1] = this.instru_code;
        this.elems[2] = this.floor_start;
        this.elems[3] = this.floor_end;
        this.elems[4] = this.uniprice;
        this.city.setPkFlag(true);
        this.instru_code.setPkFlag(true);
        this.floor_start.setPkFlag(true);
        this.floor_end.setPkFlag(true);
    }

    public String getNameOfCity() {
        return this.nameOfCity;
    }

    public void setNameOfCity(String string) {
        this.nameOfCity = string;
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

    public String getInstru_code() {
        return this.instru_code.getValue();
    }

    public int getFloor_start() {
        return this.floor_start.getValue();
    }

    public int getFloor_end() {
        return this.floor_end.getValue();
    }

    public int getUniprice() {
        return this.uniprice.getValue();
    }

    public void setCity(String string) {
        this.city.setValue(string);
    }

    public void setInstru_code(String string) {
        this.instru_code.setValue(string);
    }

    public void setFloor_start(int n) {
        this.floor_start.setValue(n);
    }

    public void setFloor_end(int n) {
        this.floor_end.setValue(n);
    }

    public void setUniprice(int n) {
        this.uniprice.setValue(n);
    }

    @Override
    public Object clone() {
        NVO_BASELAND_INSTRU_STD_PRICE nVO_BASELAND_INSTRU_STD_PRICE = new NVO_BASELAND_INSTRU_STD_PRICE();
        DbElement[] dbElementArray = new DbElement[this.elems.length];
        nVO_BASELAND_INSTRU_STD_PRICE.city = (DbString)this.city.clone();
        nVO_BASELAND_INSTRU_STD_PRICE.instru_code = (DbString)this.instru_code.clone();
        nVO_BASELAND_INSTRU_STD_PRICE.floor_start = (DbInteger)this.floor_start.clone();
        nVO_BASELAND_INSTRU_STD_PRICE.floor_end = (DbInteger)this.floor_end.clone();
        nVO_BASELAND_INSTRU_STD_PRICE.uniprice = (DbInteger)this.uniprice.clone();
        dbElementArray[0] = nVO_BASELAND_INSTRU_STD_PRICE.city;
        dbElementArray[1] = nVO_BASELAND_INSTRU_STD_PRICE.instru_code;
        dbElementArray[2] = nVO_BASELAND_INSTRU_STD_PRICE.floor_start;
        dbElementArray[3] = nVO_BASELAND_INSTRU_STD_PRICE.floor_end;
        dbElementArray[4] = nVO_BASELAND_INSTRU_STD_PRICE.uniprice;
        nVO_BASELAND_INSTRU_STD_PRICE.elems = dbElementArray;
        nVO_BASELAND_INSTRU_STD_PRICE.fieldCount = this.fieldCount;
        nVO_BASELAND_INSTRU_STD_PRICE.orderString = this.orderString;
        nVO_BASELAND_INSTRU_STD_PRICE.tableName = this.tableName;
        return nVO_BASELAND_INSTRU_STD_PRICE;
    }
}

