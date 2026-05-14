/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.dataaccess.nvo;

import com.wfusion.dataaccess.vo.DbDouble;
import com.wfusion.dataaccess.vo.DbElement;
import com.wfusion.dataaccess.vo.DbInteger;
import com.wfusion.dataaccess.vo.DbLong;
import com.wfusion.dataaccess.vo.DbString;
import com.wfusion.dataaccess.vo.VoBase;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class NVO_BASELAND_RENT_MONTH
extends VoBase
implements Serializable,
Cloneable {
    private static final long serialVersionUID = -104273708L;
    private DbString baseno = new DbString("baseno");
    private DbString year = new DbString("year");
    private DbString rent_caseno = new DbString("rent_caseno");
    private DbString city = new DbString("city");
    private DbString orders = new DbString("orders");
    private DbDouble rent_area = new DbDouble("rent_area");
    private DbLong rental = new DbLong("rental");
    private DbString rental_type = new DbString("rental_type");
    private DbString rent_date = new DbString("rent_date");
    private DbDouble type_adj = new DbDouble("type_adj", 100.0);
    private DbDouble date_adj = new DbDouble("date_adj");
    private DbDouble reg_adj = new DbDouble("reg_adj");
    private DbDouble spe_adj = new DbDouble("spe_adj");
    private DbDouble abs_adj = new DbDouble("abs_adj");
    private DbInteger near_adj = new DbInteger("near_adj");
    private DbLong trial = new DbLong("trial");
    private DbInteger compare_items = new DbInteger("compare_items");
    private DbInteger weight = new DbInteger("weight");
    private DbDouble avg_adj = new DbDouble("avg_adj");
    private DbInteger cal_cr09 = new DbInteger("cal_cr09");
    private DbDouble cal_weight = new DbDouble("cal_weight");
    private DbString land_position = new DbString("land_position");
    private DbString addr = new DbString("addr");

    public NVO_BASELAND_RENT_MONTH() {
        this.tableName = "baseland_rent_month";
        super.setFieldCount(23);
        this.elems = new DbElement[23];
        this.elems[0] = this.baseno;
        this.elems[1] = this.year;
        this.elems[2] = this.rent_caseno;
        this.elems[3] = this.city;
        this.elems[4] = this.orders;
        this.elems[5] = this.rent_area;
        this.elems[6] = this.rental;
        this.elems[7] = this.rental_type;
        this.elems[8] = this.rent_date;
        this.elems[9] = this.type_adj;
        this.elems[10] = this.date_adj;
        this.elems[11] = this.reg_adj;
        this.elems[12] = this.spe_adj;
        this.elems[13] = this.abs_adj;
        this.elems[14] = this.near_adj;
        this.elems[15] = this.trial;
        this.elems[16] = this.compare_items;
        this.elems[17] = this.weight;
        this.elems[18] = this.avg_adj;
        this.elems[19] = this.cal_cr09;
        this.elems[20] = this.cal_weight;
        this.elems[21] = this.land_position;
        this.elems[22] = this.addr;
        this.baseno.setPkFlag(true);
        this.year.setPkFlag(true);
        this.rent_caseno.setPkFlag(true);
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

    public String getRent_caseno() {
        return this.rent_caseno.getValue();
    }

    public String getCity() {
        return this.city.getValue();
    }

    public String getOrders() {
        return this.orders.getValue();
    }

    public double getRent_area() {
        return this.rent_area.getValue();
    }

    public long getRental() {
        return this.rental.getValue();
    }

    public String getRental_type() {
        return this.rental_type.getValue();
    }

    public String getRent_date() {
        return this.rent_date.getValue();
    }

    public double getType_adj() {
        return this.type_adj.getValue();
    }

    public double getDate_adj() {
        return this.date_adj.getValue();
    }

    public double getReg_adj() {
        return this.reg_adj.getValue();
    }

    public double getSpe_adj() {
        return this.spe_adj.getValue();
    }

    public double getAbs_adj() {
        return this.abs_adj.getValue();
    }

    public int getNear_adj() {
        return this.near_adj.getValue();
    }

    public long getTrial() {
        return this.trial.getValue();
    }

    public int getCompare_items() {
        return this.compare_items.getValue();
    }

    public int getWeight() {
        return this.weight.getValue();
    }

    public double getAvg_adj() {
        return this.avg_adj.getValue();
    }

    public int getCal_cr09() {
        return this.cal_cr09.getValue();
    }

    public double getCal_weight() {
        return this.cal_weight.getValue();
    }

    public String getLand_position() {
        return this.land_position.getValue();
    }

    public String getAddr() {
        return this.addr.getValue();
    }

    public void setBaseno(String string) {
        this.baseno.setValue(string);
    }

    public void setYear(String string) {
        this.year.setValue(string);
    }

    public void setRent_caseno(String string) {
        this.rent_caseno.setValue(string);
    }

    public void setCity(String string) {
        this.city.setValue(string);
    }

    public void setOrders(String string) {
        this.orders.setValue(string);
    }

    public void setRent_area(double d) {
        this.rent_area.setValue(d);
    }

    public void setRental(long l) {
        this.rental.setValue(l);
    }

    public void setRental_type(String string) {
        this.rental_type.setValue(string);
    }

    public void setRent_date(String string) {
        this.rent_date.setValue(string);
    }

    public void setType_adj(double d) {
        this.type_adj.setValue(d);
    }

    public void setDate_adj(double d) {
        this.date_adj.setValue(d);
    }

    public void setReg_adj(double d) {
        this.reg_adj.setValue(d);
    }

    public void setSpe_adj(double d) {
        this.spe_adj.setValue(d);
    }

    public void setAbs_adj(double d) {
        this.abs_adj.setValue(d);
    }

    public void setNear_adj(int n) {
        this.near_adj.setValue(n);
    }

    public void setTrial(long l) {
        this.trial.setValue(l);
    }

    public void setCompare_items(int n) {
        this.compare_items.setValue(n);
    }

    public void setWeight(int n) {
        this.weight.setValue(n);
    }

    public void setAvg_adj(double d) {
        this.avg_adj.setValue(d);
    }

    public void setCal_cr09(int n) {
        this.cal_cr09.setValue(n);
    }

    public void setCal_weight(double d) {
        this.cal_weight.setValue(d);
    }

    public void setLand_position(String string) {
        this.land_position.setValue(string);
    }

    public void setAddr(String string) {
        this.addr.setValue(string);
    }

    @Override
    public Object clone() {
        NVO_BASELAND_RENT_MONTH nVO_BASELAND_RENT_MONTH = new NVO_BASELAND_RENT_MONTH();
        DbElement[] dbElementArray = new DbElement[this.elems.length];
        nVO_BASELAND_RENT_MONTH.baseno = (DbString)this.baseno.clone();
        nVO_BASELAND_RENT_MONTH.year = (DbString)this.year.clone();
        nVO_BASELAND_RENT_MONTH.rent_caseno = (DbString)this.rent_caseno.clone();
        nVO_BASELAND_RENT_MONTH.city = (DbString)this.city.clone();
        nVO_BASELAND_RENT_MONTH.orders = (DbString)this.orders.clone();
        nVO_BASELAND_RENT_MONTH.rent_area = (DbDouble)this.rent_area.clone();
        nVO_BASELAND_RENT_MONTH.rental = (DbLong)this.rental.clone();
        nVO_BASELAND_RENT_MONTH.rental_type = (DbString)this.rental_type.clone();
        nVO_BASELAND_RENT_MONTH.rent_date = (DbString)this.rent_date.clone();
        nVO_BASELAND_RENT_MONTH.type_adj = (DbDouble)this.type_adj.clone();
        nVO_BASELAND_RENT_MONTH.date_adj = (DbDouble)this.date_adj.clone();
        nVO_BASELAND_RENT_MONTH.reg_adj = (DbDouble)this.reg_adj.clone();
        nVO_BASELAND_RENT_MONTH.spe_adj = (DbDouble)this.spe_adj.clone();
        nVO_BASELAND_RENT_MONTH.abs_adj = (DbDouble)this.abs_adj.clone();
        nVO_BASELAND_RENT_MONTH.near_adj = (DbInteger)this.near_adj.clone();
        nVO_BASELAND_RENT_MONTH.trial = (DbLong)this.trial.clone();
        nVO_BASELAND_RENT_MONTH.compare_items = (DbInteger)this.compare_items.clone();
        nVO_BASELAND_RENT_MONTH.weight = (DbInteger)this.weight.clone();
        nVO_BASELAND_RENT_MONTH.avg_adj = (DbDouble)this.avg_adj.clone();
        nVO_BASELAND_RENT_MONTH.cal_cr09 = (DbInteger)this.cal_cr09.clone();
        nVO_BASELAND_RENT_MONTH.cal_weight = (DbDouble)this.cal_weight.clone();
        dbElementArray[0] = nVO_BASELAND_RENT_MONTH.baseno;
        dbElementArray[1] = nVO_BASELAND_RENT_MONTH.year;
        dbElementArray[2] = nVO_BASELAND_RENT_MONTH.rent_caseno;
        dbElementArray[3] = nVO_BASELAND_RENT_MONTH.city;
        dbElementArray[4] = nVO_BASELAND_RENT_MONTH.orders;
        dbElementArray[5] = nVO_BASELAND_RENT_MONTH.rent_area;
        dbElementArray[6] = nVO_BASELAND_RENT_MONTH.rental;
        dbElementArray[7] = nVO_BASELAND_RENT_MONTH.rental_type;
        dbElementArray[8] = nVO_BASELAND_RENT_MONTH.rent_date;
        dbElementArray[9] = nVO_BASELAND_RENT_MONTH.type_adj;
        dbElementArray[10] = nVO_BASELAND_RENT_MONTH.date_adj;
        dbElementArray[11] = nVO_BASELAND_RENT_MONTH.reg_adj;
        dbElementArray[12] = nVO_BASELAND_RENT_MONTH.spe_adj;
        dbElementArray[13] = nVO_BASELAND_RENT_MONTH.abs_adj;
        dbElementArray[14] = nVO_BASELAND_RENT_MONTH.near_adj;
        dbElementArray[15] = nVO_BASELAND_RENT_MONTH.trial;
        dbElementArray[16] = nVO_BASELAND_RENT_MONTH.compare_items;
        dbElementArray[17] = nVO_BASELAND_RENT_MONTH.weight;
        dbElementArray[18] = nVO_BASELAND_RENT_MONTH.avg_adj;
        dbElementArray[19] = nVO_BASELAND_RENT_MONTH.cal_cr09;
        dbElementArray[20] = nVO_BASELAND_RENT_MONTH.cal_weight;
        nVO_BASELAND_RENT_MONTH.elems = dbElementArray;
        nVO_BASELAND_RENT_MONTH.fieldCount = this.fieldCount;
        nVO_BASELAND_RENT_MONTH.orderString = this.orderString;
        nVO_BASELAND_RENT_MONTH.tableName = this.tableName;
        return nVO_BASELAND_RENT_MONTH;
    }
}

