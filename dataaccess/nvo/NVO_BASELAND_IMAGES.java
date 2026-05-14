/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.dataaccess.nvo;

import com.wfusion.dataaccess.vo.DbByteArray;
import com.wfusion.dataaccess.vo.DbElement;
import com.wfusion.dataaccess.vo.DbString;
import com.wfusion.dataaccess.vo.VoBase;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class NVO_BASELAND_IMAGES
extends VoBase
implements Serializable,
Cloneable {
    private static final long serialVersionUID = 1977555570L;
    private DbString year = new DbString("year");
    private DbString baseno = new DbString("baseno");
    private DbString city = new DbString("city");
    private DbString ofce = new DbString("ofce");
    private DbString dist = new DbString("dist");
    private DbString photo_type = new DbString("photo_type");
    private DbByteArray photo = new DbByteArray("photo");
    private String kind = "";

    public NVO_BASELAND_IMAGES() {
        this.tableName = "baseland_images";
        super.setFieldCount(7);
        this.elems = new DbElement[7];
        this.elems[0] = this.year;
        this.elems[1] = this.baseno;
        this.elems[2] = this.city;
        this.elems[3] = this.ofce;
        this.elems[4] = this.dist;
        this.elems[5] = this.photo_type;
        this.elems[6] = this.photo;
        this.year.setPkFlag(true);
        this.baseno.setPkFlag(true);
        this.photo_type.setPkFlag(true);
    }

    public String getKind() {
        return this.kind;
    }

    public void setKind(String string) {
        this.kind = string;
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
    }

    public String getYear() {
        return this.year.getValue();
    }

    public String getBaseno() {
        return this.baseno.getValue();
    }

    public String getCity() {
        return this.city.getValue();
    }

    public String getOfce() {
        return this.ofce.getValue();
    }

    public String getDist() {
        return this.dist.getValue();
    }

    public String getPhoto_type() {
        return this.photo_type.getValue();
    }

    public byte[] getPhoto() {
        return this.photo.getValue();
    }

    public void setYear(String string) {
        this.year.setValue(string);
    }

    public void setBaseno(String string) {
        this.baseno.setValue(string);
    }

    public void setCity(String string) {
        this.city.setValue(string);
    }

    public void setOfce(String string) {
        this.ofce.setValue(string);
    }

    public void setDist(String string) {
        this.dist.setValue(string);
    }

    public void setPhoto_type(String string) {
        this.photo_type.setValue(string);
    }

    public void setPhoto(byte[] byArray) {
        this.photo.setValue(byArray);
    }

    @Override
    public Object clone() {
        NVO_BASELAND_IMAGES nVO_BASELAND_IMAGES = new NVO_BASELAND_IMAGES();
        DbElement[] dbElementArray = new DbElement[this.elems.length];
        nVO_BASELAND_IMAGES.year = (DbString)this.year.clone();
        nVO_BASELAND_IMAGES.baseno = (DbString)this.baseno.clone();
        nVO_BASELAND_IMAGES.city = (DbString)this.city.clone();
        nVO_BASELAND_IMAGES.ofce = (DbString)this.ofce.clone();
        nVO_BASELAND_IMAGES.dist = (DbString)this.dist.clone();
        nVO_BASELAND_IMAGES.photo_type = (DbString)this.photo_type.clone();
        nVO_BASELAND_IMAGES.photo = (DbByteArray)this.photo.clone();
        dbElementArray[0] = nVO_BASELAND_IMAGES.year;
        dbElementArray[1] = nVO_BASELAND_IMAGES.baseno;
        dbElementArray[2] = nVO_BASELAND_IMAGES.city;
        dbElementArray[3] = nVO_BASELAND_IMAGES.ofce;
        dbElementArray[4] = nVO_BASELAND_IMAGES.dist;
        dbElementArray[5] = nVO_BASELAND_IMAGES.photo_type;
        dbElementArray[6] = nVO_BASELAND_IMAGES.photo;
        nVO_BASELAND_IMAGES.elems = dbElementArray;
        nVO_BASELAND_IMAGES.fieldCount = this.fieldCount;
        nVO_BASELAND_IMAGES.orderString = this.orderString;
        nVO_BASELAND_IMAGES.tableName = this.tableName;
        return nVO_BASELAND_IMAGES;
    }
}

