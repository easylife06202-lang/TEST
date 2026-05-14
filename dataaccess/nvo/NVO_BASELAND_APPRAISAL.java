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

public class NVO_BASELAND_APPRAISAL
extends VoBase
implements Serializable,
Cloneable {
    private static final long serialVersionUID = -1756519285L;
    private DbString year = new DbString("year");
    private DbString baseno = new DbString("baseno");
    private DbString city = new DbString("city");
    private DbString ofce = new DbString("ofce");
    private DbString dist = new DbString("dist");
    private DbDouble diversity1 = new DbDouble("diversity1");
    private DbDouble diversity2 = new DbDouble("diversity2");
    private DbDouble diversity3 = new DbDouble("diversity3");
    private DbDouble price_diff12 = new DbDouble("price_diff12");
    private DbDouble price_diff23 = new DbDouble("price_diff23");
    private DbDouble price_diff31 = new DbDouble("price_diff31");
    private DbDouble cal_ahp1 = new DbDouble("cal_ahp1");
    private DbDouble cal_ahp2 = new DbDouble("cal_ahp2");
    private DbDouble cal_ahp3 = new DbDouble("cal_ahp3");
    private DbDouble fin_ahp1 = new DbDouble("fin_ahp1");
    private DbDouble fin_ahp2 = new DbDouble("fin_ahp2");
    private DbDouble fin_ahp3 = new DbDouble("fin_ahp3");
    private DbInteger fin_price = new DbInteger("fin_price");
    private DbInteger fin_pricep = new DbInteger("fin_pricep");
    private DbString notes = new DbString("notes");

    public NVO_BASELAND_APPRAISAL() {
        this.tableName = "baseland_appraisal";
        super.setFieldCount(20);
        this.elems = new DbElement[20];
        this.elems[0] = this.year;
        this.elems[1] = this.baseno;
        this.elems[2] = this.city;
        this.elems[3] = this.ofce;
        this.elems[4] = this.dist;
        this.elems[5] = this.diversity1;
        this.elems[6] = this.diversity2;
        this.elems[7] = this.diversity3;
        this.elems[8] = this.price_diff12;
        this.elems[9] = this.price_diff23;
        this.elems[10] = this.price_diff31;
        this.elems[11] = this.cal_ahp1;
        this.elems[12] = this.cal_ahp2;
        this.elems[13] = this.cal_ahp3;
        this.elems[14] = this.fin_ahp1;
        this.elems[15] = this.fin_ahp2;
        this.elems[16] = this.fin_ahp3;
        this.elems[17] = this.fin_price;
        this.elems[18] = this.fin_pricep;
        this.elems[19] = this.notes;
        this.year.setPkFlag(true);
        this.baseno.setPkFlag(true);
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

    public double getDiversity1() {
        return this.diversity1.getValue();
    }

    public double getDiversity2() {
        return this.diversity2.getValue();
    }

    public double getDiversity3() {
        return this.diversity3.getValue();
    }

    public double getPrice_diff12() {
        return this.price_diff12.getValue();
    }

    public double getPrice_diff23() {
        return this.price_diff23.getValue();
    }

    public double getPrice_diff31() {
        return this.price_diff31.getValue();
    }

    public double getCal_ahp1() {
        return this.cal_ahp1.getValue();
    }

    public double getCal_ahp2() {
        return this.cal_ahp2.getValue();
    }

    public double getCal_ahp3() {
        return this.cal_ahp3.getValue();
    }

    public double getFin_ahp1() {
        return this.fin_ahp1.getValue();
    }

    public double getFin_ahp2() {
        return this.fin_ahp2.getValue();
    }

    public double getFin_ahp3() {
        return this.fin_ahp3.getValue();
    }

    public int getFin_price() {
        return this.fin_price.getValue();
    }

    public int getFin_pricep() {
        return this.fin_pricep.getValue();
    }

    public String getNotes() {
        return this.notes.getValue();
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

    public void setDiversity1(double d) {
        this.diversity1.setValue(d);
    }

    public void setDiversity2(double d) {
        this.diversity2.setValue(d);
    }

    public void setDiversity3(double d) {
        this.diversity3.setValue(d);
    }

    public void setPrice_diff12(double d) {
        this.price_diff12.setValue(d);
    }

    public void setPrice_diff23(double d) {
        this.price_diff23.setValue(d);
    }

    public void setPrice_diff31(double d) {
        this.price_diff31.setValue(d);
    }

    public void setCal_ahp1(double d) {
        this.cal_ahp1.setValue(d);
    }

    public void setCal_ahp2(double d) {
        this.cal_ahp2.setValue(d);
    }

    public void setCal_ahp3(double d) {
        this.cal_ahp3.setValue(d);
    }

    public void setFin_ahp1(double d) {
        this.fin_ahp1.setValue(d);
    }

    public void setFin_ahp2(double d) {
        this.fin_ahp2.setValue(d);
    }

    public void setFin_ahp3(double d) {
        this.fin_ahp3.setValue(d);
    }

    public void setFin_price(int n) {
        this.fin_price.setValue(n);
    }

    public void setFin_pricep(int n) {
        this.fin_pricep.setValue(n);
    }

    public void setNotes(String string) {
        this.notes.setValue(string);
    }

    @Override
    public Object clone() {
        NVO_BASELAND_APPRAISAL nVO_BASELAND_APPRAISAL = new NVO_BASELAND_APPRAISAL();
        DbElement[] dbElementArray = new DbElement[this.elems.length];
        nVO_BASELAND_APPRAISAL.year = (DbString)this.year.clone();
        nVO_BASELAND_APPRAISAL.baseno = (DbString)this.baseno.clone();
        nVO_BASELAND_APPRAISAL.city = (DbString)this.city.clone();
        nVO_BASELAND_APPRAISAL.ofce = (DbString)this.ofce.clone();
        nVO_BASELAND_APPRAISAL.dist = (DbString)this.dist.clone();
        nVO_BASELAND_APPRAISAL.diversity1 = (DbDouble)this.diversity1.clone();
        nVO_BASELAND_APPRAISAL.diversity2 = (DbDouble)this.diversity2.clone();
        nVO_BASELAND_APPRAISAL.diversity3 = (DbDouble)this.diversity3.clone();
        nVO_BASELAND_APPRAISAL.price_diff12 = (DbDouble)this.price_diff12.clone();
        nVO_BASELAND_APPRAISAL.price_diff23 = (DbDouble)this.price_diff23.clone();
        nVO_BASELAND_APPRAISAL.price_diff31 = (DbDouble)this.price_diff31.clone();
        nVO_BASELAND_APPRAISAL.cal_ahp1 = (DbDouble)this.cal_ahp1.clone();
        nVO_BASELAND_APPRAISAL.cal_ahp2 = (DbDouble)this.cal_ahp2.clone();
        nVO_BASELAND_APPRAISAL.cal_ahp3 = (DbDouble)this.cal_ahp3.clone();
        nVO_BASELAND_APPRAISAL.fin_ahp1 = (DbDouble)this.fin_ahp1.clone();
        nVO_BASELAND_APPRAISAL.fin_ahp2 = (DbDouble)this.fin_ahp2.clone();
        nVO_BASELAND_APPRAISAL.fin_ahp3 = (DbDouble)this.fin_ahp3.clone();
        nVO_BASELAND_APPRAISAL.fin_price = (DbInteger)this.fin_price.clone();
        nVO_BASELAND_APPRAISAL.fin_pricep = (DbInteger)this.fin_pricep.clone();
        nVO_BASELAND_APPRAISAL.notes = (DbString)this.notes.clone();
        dbElementArray[0] = nVO_BASELAND_APPRAISAL.year;
        dbElementArray[1] = nVO_BASELAND_APPRAISAL.baseno;
        dbElementArray[2] = nVO_BASELAND_APPRAISAL.city;
        dbElementArray[3] = nVO_BASELAND_APPRAISAL.ofce;
        dbElementArray[4] = nVO_BASELAND_APPRAISAL.dist;
        dbElementArray[5] = nVO_BASELAND_APPRAISAL.diversity1;
        dbElementArray[6] = nVO_BASELAND_APPRAISAL.diversity2;
        dbElementArray[7] = nVO_BASELAND_APPRAISAL.diversity3;
        dbElementArray[8] = nVO_BASELAND_APPRAISAL.price_diff12;
        dbElementArray[9] = nVO_BASELAND_APPRAISAL.price_diff23;
        dbElementArray[10] = nVO_BASELAND_APPRAISAL.price_diff31;
        dbElementArray[11] = nVO_BASELAND_APPRAISAL.cal_ahp1;
        dbElementArray[12] = nVO_BASELAND_APPRAISAL.cal_ahp2;
        dbElementArray[13] = nVO_BASELAND_APPRAISAL.cal_ahp3;
        dbElementArray[14] = nVO_BASELAND_APPRAISAL.fin_ahp1;
        dbElementArray[15] = nVO_BASELAND_APPRAISAL.fin_ahp2;
        dbElementArray[16] = nVO_BASELAND_APPRAISAL.fin_ahp3;
        dbElementArray[17] = nVO_BASELAND_APPRAISAL.fin_price;
        dbElementArray[18] = nVO_BASELAND_APPRAISAL.fin_pricep;
        dbElementArray[19] = nVO_BASELAND_APPRAISAL.notes;
        nVO_BASELAND_APPRAISAL.elems = dbElementArray;
        nVO_BASELAND_APPRAISAL.fieldCount = this.fieldCount;
        nVO_BASELAND_APPRAISAL.orderString = this.orderString;
        nVO_BASELAND_APPRAISAL.tableName = this.tableName;
        return nVO_BASELAND_APPRAISAL;
    }
}

