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

public class NVO_BASELAND_AHP
extends VoBase
implements Serializable,
Cloneable {
    private static final long serialVersionUID = -1764725937L;
    private DbString city = new DbString("city");
    private DbString year = new DbString("year");
    private DbInteger credibility = new DbInteger("credibility");
    private DbInteger similarity = new DbInteger("similarity");
    private DbInteger value_type = new DbInteger("value_type");
    private DbInteger comp_price_type = new DbInteger("comp_price_type");
    private DbInteger comp_month = new DbInteger("comp_month");
    private DbInteger comp_near = new DbInteger("comp_near");
    private DbInteger comp_diff_abs = new DbInteger("comp_diff_abs");
    private DbInteger comp_diff_items = new DbInteger("comp_diff_items");
    private DbInteger comp_diff_limit = new DbInteger("comp_diff_limit");
    private DbInteger rent_month_money = new DbInteger("rent_month_money");
    private DbInteger rent_years = new DbInteger("rent_years");
    private DbInteger rent_buildcost_ext = new DbInteger("rent_buildcost_ext");
    private DbInteger dev_floors_plan = new DbInteger("dev_floors_plan");
    private DbInteger dev_sale_money = new DbInteger("dev_sale_money");
    private DbInteger dev_fouds_rate = new DbInteger("dev_fouds_rate");
    private DbInteger dev_buildcost_ext = new DbInteger("dev_buildcost_ext");
    private DbInteger rent_capitalization = new DbInteger("rent_capitalization");
    private boolean haveData = true;

    public NVO_BASELAND_AHP() {
        this.tableName = "baseland_ahp";
        super.setFieldCount(19);
        this.elems = new DbElement[19];
        this.elems[0] = this.city;
        this.elems[1] = this.year;
        this.elems[2] = this.credibility;
        this.elems[3] = this.similarity;
        this.elems[4] = this.value_type;
        this.elems[5] = this.comp_price_type;
        this.elems[6] = this.comp_month;
        this.elems[7] = this.comp_near;
        this.elems[8] = this.comp_diff_abs;
        this.elems[9] = this.comp_diff_items;
        this.elems[10] = this.comp_diff_limit;
        this.elems[11] = this.rent_month_money;
        this.elems[12] = this.rent_years;
        this.elems[13] = this.rent_buildcost_ext;
        this.elems[14] = this.dev_floors_plan;
        this.elems[15] = this.dev_sale_money;
        this.elems[16] = this.dev_fouds_rate;
        this.elems[17] = this.dev_buildcost_ext;
        this.elems[18] = this.rent_capitalization;
        this.city.setPkFlag(true);
        this.year.setPkFlag(true);
    }

    public boolean isHaveData() {
        return this.haveData;
    }

    public void setHaveData(boolean bl) {
        this.haveData = bl;
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

    public String getYear() {
        return this.year.getValue();
    }

    public int getCredibility() {
        return this.credibility.getValue();
    }

    public int getSimilarity() {
        return this.similarity.getValue();
    }

    public int getValue_type() {
        return this.value_type.getValue();
    }

    public int getComp_price_type() {
        return this.comp_price_type.getValue();
    }

    public int getComp_month() {
        return this.comp_month.getValue();
    }

    public int getComp_near() {
        return this.comp_near.getValue();
    }

    public int getComp_diff_abs() {
        return this.comp_diff_abs.getValue();
    }

    public int getComp_diff_items() {
        return this.comp_diff_items.getValue();
    }

    public int getComp_diff_limit() {
        return this.comp_diff_limit.getValue();
    }

    public int getRent_month_money() {
        return this.rent_month_money.getValue();
    }

    public int getRent_years() {
        return this.rent_years.getValue();
    }

    public int getRent_buildcost_ext() {
        return this.rent_buildcost_ext.getValue();
    }

    public int getDev_floors_plan() {
        return this.dev_floors_plan.getValue();
    }

    public int getDev_sale_money() {
        return this.dev_sale_money.getValue();
    }

    public int getDev_fouds_rate() {
        return this.dev_fouds_rate.getValue();
    }

    public int getDev_buildcost_ext() {
        return this.dev_buildcost_ext.getValue();
    }

    public int getRent_capitalization() {
        return this.rent_capitalization.getValue();
    }

    public void setCity(String string) {
        this.city.setValue(string);
    }

    public void setYear(String string) {
        this.year.setValue(string);
    }

    public void setCredibility(int n) {
        this.credibility.setValue(n);
    }

    public void setSimilarity(int n) {
        this.similarity.setValue(n);
    }

    public void setValue_type(int n) {
        this.value_type.setValue(n);
    }

    public void setComp_price_type(int n) {
        this.comp_price_type.setValue(n);
    }

    public void setComp_month(int n) {
        this.comp_month.setValue(n);
    }

    public void setComp_near(int n) {
        this.comp_near.setValue(n);
    }

    public void setComp_diff_abs(int n) {
        this.comp_diff_abs.setValue(n);
    }

    public void setComp_diff_items(int n) {
        this.comp_diff_items.setValue(n);
    }

    public void setComp_diff_limit(int n) {
        this.comp_diff_limit.setValue(n);
    }

    public void setRent_month_money(int n) {
        this.rent_month_money.setValue(n);
    }

    public void setRent_years(int n) {
        this.rent_years.setValue(n);
    }

    public void setRent_buildcost_ext(int n) {
        this.rent_buildcost_ext.setValue(n);
    }

    public void setDev_floors_plan(int n) {
        this.dev_floors_plan.setValue(n);
    }

    public void setDev_sale_money(int n) {
        this.dev_sale_money.setValue(n);
    }

    public void setDev_fouds_rate(int n) {
        this.dev_fouds_rate.setValue(n);
    }

    public void setDev_buildcost_ext(int n) {
        this.dev_buildcost_ext.setValue(n);
    }

    public void setRent_capitalization(int n) {
        this.rent_capitalization.setValue(n);
    }

    @Override
    public Object clone() {
        NVO_BASELAND_AHP nVO_BASELAND_AHP = new NVO_BASELAND_AHP();
        DbElement[] dbElementArray = new DbElement[this.elems.length];
        nVO_BASELAND_AHP.city = (DbString)this.city.clone();
        nVO_BASELAND_AHP.year = (DbString)this.year.clone();
        nVO_BASELAND_AHP.credibility = (DbInteger)this.credibility.clone();
        nVO_BASELAND_AHP.similarity = (DbInteger)this.similarity.clone();
        nVO_BASELAND_AHP.value_type = (DbInteger)this.value_type.clone();
        nVO_BASELAND_AHP.comp_price_type = (DbInteger)this.comp_price_type.clone();
        nVO_BASELAND_AHP.comp_month = (DbInteger)this.comp_month.clone();
        nVO_BASELAND_AHP.comp_near = (DbInteger)this.comp_near.clone();
        nVO_BASELAND_AHP.comp_diff_abs = (DbInteger)this.comp_diff_abs.clone();
        nVO_BASELAND_AHP.comp_diff_items = (DbInteger)this.comp_diff_items.clone();
        nVO_BASELAND_AHP.comp_diff_limit = (DbInteger)this.comp_diff_limit.clone();
        nVO_BASELAND_AHP.rent_month_money = (DbInteger)this.rent_month_money.clone();
        nVO_BASELAND_AHP.rent_years = (DbInteger)this.rent_years.clone();
        nVO_BASELAND_AHP.rent_buildcost_ext = (DbInteger)this.rent_buildcost_ext.clone();
        nVO_BASELAND_AHP.dev_floors_plan = (DbInteger)this.dev_floors_plan.clone();
        nVO_BASELAND_AHP.dev_sale_money = (DbInteger)this.dev_sale_money.clone();
        nVO_BASELAND_AHP.dev_fouds_rate = (DbInteger)this.dev_fouds_rate.clone();
        nVO_BASELAND_AHP.dev_buildcost_ext = (DbInteger)this.dev_buildcost_ext.clone();
        nVO_BASELAND_AHP.rent_capitalization = (DbInteger)this.rent_capitalization.clone();
        dbElementArray[0] = nVO_BASELAND_AHP.city;
        dbElementArray[1] = nVO_BASELAND_AHP.year;
        dbElementArray[2] = nVO_BASELAND_AHP.credibility;
        dbElementArray[3] = nVO_BASELAND_AHP.similarity;
        dbElementArray[4] = nVO_BASELAND_AHP.value_type;
        dbElementArray[5] = nVO_BASELAND_AHP.comp_price_type;
        dbElementArray[6] = nVO_BASELAND_AHP.comp_month;
        dbElementArray[7] = nVO_BASELAND_AHP.comp_near;
        dbElementArray[8] = nVO_BASELAND_AHP.comp_diff_abs;
        dbElementArray[9] = nVO_BASELAND_AHP.comp_diff_items;
        dbElementArray[10] = nVO_BASELAND_AHP.comp_diff_limit;
        dbElementArray[11] = nVO_BASELAND_AHP.rent_month_money;
        dbElementArray[12] = nVO_BASELAND_AHP.rent_years;
        dbElementArray[13] = nVO_BASELAND_AHP.rent_buildcost_ext;
        dbElementArray[14] = nVO_BASELAND_AHP.dev_floors_plan;
        dbElementArray[15] = nVO_BASELAND_AHP.dev_sale_money;
        dbElementArray[16] = nVO_BASELAND_AHP.dev_fouds_rate;
        dbElementArray[17] = nVO_BASELAND_AHP.dev_buildcost_ext;
        dbElementArray[18] = nVO_BASELAND_AHP.rent_capitalization;
        nVO_BASELAND_AHP.elems = dbElementArray;
        nVO_BASELAND_AHP.fieldCount = this.fieldCount;
        nVO_BASELAND_AHP.orderString = this.orderString;
        nVO_BASELAND_AHP.tableName = this.tableName;
        return nVO_BASELAND_AHP;
    }
}

