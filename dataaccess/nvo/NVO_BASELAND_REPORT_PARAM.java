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

public class NVO_BASELAND_REPORT_PARAM
extends VoBase
implements Serializable,
Cloneable {
    private static final long serialVersionUID = -173255140L;
    private DbString city = new DbString("city");
    private DbString year = new DbString("year");
    private DbDouble owner_rate = new DbDouble("owner_rate");
    private DbDouble owner_ratio = new DbDouble("owner_ratio");
    private DbDouble debt_rate = new DbDouble("debt_rate");
    private DbDouble debt_ratio = new DbDouble("debt_ratio");
    private DbDouble presale_rate = new DbDouble("presale_rate");
    private DbDouble presale_ratio = new DbDouble("presale_ratio");
    private DbDouble design_ratio = new DbDouble("design_ratio");
    private DbDouble ad_ratio = new DbDouble("ad_ratio");
    private DbDouble manage_ratio = new DbDouble("manage_ratio");
    private DbDouble tax_ratio = new DbDouble("tax_ratio");
    private DbDouble devp_rate = new DbDouble("devp_rate");
    private DbDouble build_benefit_rate = new DbDouble("build_benefit_rate");
    private DbDouble land_benefit_rate = new DbDouble("land_benefit_rate");
    private DbDouble mc = new DbDouble("mc");
    private DbDouble insure_rate = new DbDouble("insure_rate");
    private DbDouble maintian_rate = new DbDouble("maintian_rate");
    private DbDouble reset_rate1 = new DbDouble("reset_rate1");
    private DbDouble reset_rate2 = new DbDouble("reset_rate2");
    private DbDouble ben_manage_ratio = new DbDouble("ben_manage_ratio");
    private DbString build_cost_basedate = new DbString("build_cost_basedate");
    private DbString price_rate_type = new DbString("price_rate_type");
    private boolean haveData = true;

    public NVO_BASELAND_REPORT_PARAM() {
        this.tableName = "baseland_report_param";
        super.setFieldCount(23);
        this.elems = new DbElement[23];
        this.elems[0] = this.city;
        this.elems[1] = this.year;
        this.elems[2] = this.owner_rate;
        this.elems[3] = this.owner_ratio;
        this.elems[4] = this.debt_rate;
        this.elems[5] = this.debt_ratio;
        this.elems[6] = this.presale_rate;
        this.elems[7] = this.presale_ratio;
        this.elems[8] = this.design_ratio;
        this.elems[9] = this.ad_ratio;
        this.elems[10] = this.manage_ratio;
        this.elems[11] = this.tax_ratio;
        this.elems[12] = this.devp_rate;
        this.elems[13] = this.build_benefit_rate;
        this.elems[14] = this.land_benefit_rate;
        this.elems[15] = this.mc;
        this.elems[16] = this.insure_rate;
        this.elems[17] = this.maintian_rate;
        this.elems[18] = this.reset_rate1;
        this.elems[19] = this.reset_rate2;
        this.elems[20] = this.ben_manage_ratio;
        this.elems[21] = this.build_cost_basedate;
        this.elems[22] = this.price_rate_type;
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

    public double getOwner_rate() {
        return this.owner_rate.getValue();
    }

    public double getOwner_ratio() {
        return this.owner_ratio.getValue();
    }

    public double getDebt_rate() {
        return this.debt_rate.getValue();
    }

    public double getDebt_ratio() {
        return this.debt_ratio.getValue();
    }

    public double getPresale_rate() {
        return this.presale_rate.getValue();
    }

    public double getPresale_ratio() {
        return this.presale_ratio.getValue();
    }

    public double getDesign_ratio() {
        return this.design_ratio.getValue();
    }

    public double getAd_ratio() {
        return this.ad_ratio.getValue();
    }

    public double getManage_ratio() {
        return this.manage_ratio.getValue();
    }

    public double getTax_ratio() {
        return this.tax_ratio.getValue();
    }

    public double getDevp_rate() {
        return this.devp_rate.getValue();
    }

    public double getBuild_benefit_rate() {
        return this.build_benefit_rate.getValue();
    }

    public double getLand_benefit_rate() {
        return this.land_benefit_rate.getValue();
    }

    public double getMc() {
        return this.mc.getValue();
    }

    public double getInsure_rate() {
        return this.insure_rate.getValue();
    }

    public double getMaintian_rate() {
        return this.maintian_rate.getValue();
    }

    public double getReset_rate1() {
        return this.reset_rate1.getValue();
    }

    public double getReset_rate2() {
        return this.reset_rate2.getValue();
    }

    public double getBen_manage_ratio() {
        return this.ben_manage_ratio.getValue();
    }

    public String getBuild_cost_basedate() {
        return this.build_cost_basedate.getValue();
    }

    public String getPrice_rate_type() {
        return this.price_rate_type.getValue();
    }

    public void setCity(String string) {
        this.city.setValue(string);
    }

    public void setYear(String string) {
        this.year.setValue(string);
    }

    public void setOwner_rate(double d) {
        this.owner_rate.setValue(d);
    }

    public void setOwner_ratio(double d) {
        this.owner_ratio.setValue(d);
    }

    public void setDebt_rate(double d) {
        this.debt_rate.setValue(d);
    }

    public void setDebt_ratio(double d) {
        this.debt_ratio.setValue(d);
    }

    public void setPresale_rate(double d) {
        this.presale_rate.setValue(d);
    }

    public void setPresale_ratio(double d) {
        this.presale_ratio.setValue(d);
    }

    public void setDesign_ratio(double d) {
        this.design_ratio.setValue(d);
    }

    public void setAd_ratio(double d) {
        this.ad_ratio.setValue(d);
    }

    public void setManage_ratio(double d) {
        this.manage_ratio.setValue(d);
    }

    public void setTax_ratio(double d) {
        this.tax_ratio.setValue(d);
    }

    public void setDevp_rate(double d) {
        this.devp_rate.setValue(d);
    }

    public void setBuild_benefit_rate(double d) {
        this.build_benefit_rate.setValue(d);
    }

    public void setLand_benefit_rate(double d) {
        this.land_benefit_rate.setValue(d);
    }

    public void setMc(double d) {
        this.mc.setValue(d);
    }

    public void setInsure_rate(double d) {
        this.insure_rate.setValue(d);
    }

    public void setMaintian_rate(double d) {
        this.maintian_rate.setValue(d);
    }

    public void setReset_rate1(double d) {
        this.reset_rate1.setValue(d);
    }

    public void setReset_rate2(double d) {
        this.reset_rate2.setValue(d);
    }

    public void setBen_manage_ratio(double d) {
        this.ben_manage_ratio.setValue(d);
    }

    public void setBuild_cost_basedate(String string) {
        this.build_cost_basedate.setValue(string);
    }

    public void setPrice_rate_type(String string) {
        this.price_rate_type.setValue(string);
    }

    @Override
    public Object clone() {
        NVO_BASELAND_REPORT_PARAM nVO_BASELAND_REPORT_PARAM = new NVO_BASELAND_REPORT_PARAM();
        DbElement[] dbElementArray = new DbElement[this.elems.length];
        nVO_BASELAND_REPORT_PARAM.city = (DbString)this.city.clone();
        nVO_BASELAND_REPORT_PARAM.year = (DbString)this.year.clone();
        nVO_BASELAND_REPORT_PARAM.owner_rate = (DbDouble)this.owner_rate.clone();
        nVO_BASELAND_REPORT_PARAM.owner_ratio = (DbDouble)this.owner_ratio.clone();
        nVO_BASELAND_REPORT_PARAM.debt_rate = (DbDouble)this.debt_rate.clone();
        nVO_BASELAND_REPORT_PARAM.debt_ratio = (DbDouble)this.debt_ratio.clone();
        nVO_BASELAND_REPORT_PARAM.presale_rate = (DbDouble)this.presale_rate.clone();
        nVO_BASELAND_REPORT_PARAM.presale_ratio = (DbDouble)this.presale_ratio.clone();
        nVO_BASELAND_REPORT_PARAM.design_ratio = (DbDouble)this.design_ratio.clone();
        nVO_BASELAND_REPORT_PARAM.ad_ratio = (DbDouble)this.ad_ratio.clone();
        nVO_BASELAND_REPORT_PARAM.manage_ratio = (DbDouble)this.manage_ratio.clone();
        nVO_BASELAND_REPORT_PARAM.tax_ratio = (DbDouble)this.tax_ratio.clone();
        nVO_BASELAND_REPORT_PARAM.devp_rate = (DbDouble)this.devp_rate.clone();
        nVO_BASELAND_REPORT_PARAM.build_benefit_rate = (DbDouble)this.build_benefit_rate.clone();
        nVO_BASELAND_REPORT_PARAM.land_benefit_rate = (DbDouble)this.land_benefit_rate.clone();
        nVO_BASELAND_REPORT_PARAM.mc = (DbDouble)this.mc.clone();
        nVO_BASELAND_REPORT_PARAM.insure_rate = (DbDouble)this.insure_rate.clone();
        nVO_BASELAND_REPORT_PARAM.maintian_rate = (DbDouble)this.maintian_rate.clone();
        nVO_BASELAND_REPORT_PARAM.reset_rate1 = (DbDouble)this.reset_rate1.clone();
        nVO_BASELAND_REPORT_PARAM.reset_rate2 = (DbDouble)this.reset_rate2.clone();
        nVO_BASELAND_REPORT_PARAM.ben_manage_ratio = (DbDouble)this.ben_manage_ratio.clone();
        nVO_BASELAND_REPORT_PARAM.build_cost_basedate = (DbString)this.build_cost_basedate.clone();
        nVO_BASELAND_REPORT_PARAM.price_rate_type = (DbString)this.price_rate_type.clone();
        dbElementArray[0] = nVO_BASELAND_REPORT_PARAM.city;
        dbElementArray[1] = nVO_BASELAND_REPORT_PARAM.year;
        dbElementArray[2] = nVO_BASELAND_REPORT_PARAM.owner_rate;
        dbElementArray[3] = nVO_BASELAND_REPORT_PARAM.owner_ratio;
        dbElementArray[4] = nVO_BASELAND_REPORT_PARAM.debt_rate;
        dbElementArray[5] = nVO_BASELAND_REPORT_PARAM.debt_ratio;
        dbElementArray[6] = nVO_BASELAND_REPORT_PARAM.presale_rate;
        dbElementArray[7] = nVO_BASELAND_REPORT_PARAM.presale_ratio;
        dbElementArray[8] = nVO_BASELAND_REPORT_PARAM.design_ratio;
        dbElementArray[9] = nVO_BASELAND_REPORT_PARAM.ad_ratio;
        dbElementArray[10] = nVO_BASELAND_REPORT_PARAM.manage_ratio;
        dbElementArray[11] = nVO_BASELAND_REPORT_PARAM.tax_ratio;
        dbElementArray[12] = nVO_BASELAND_REPORT_PARAM.devp_rate;
        dbElementArray[13] = nVO_BASELAND_REPORT_PARAM.build_benefit_rate;
        dbElementArray[14] = nVO_BASELAND_REPORT_PARAM.land_benefit_rate;
        dbElementArray[15] = nVO_BASELAND_REPORT_PARAM.mc;
        dbElementArray[16] = nVO_BASELAND_REPORT_PARAM.insure_rate;
        dbElementArray[17] = nVO_BASELAND_REPORT_PARAM.maintian_rate;
        dbElementArray[18] = nVO_BASELAND_REPORT_PARAM.reset_rate1;
        dbElementArray[19] = nVO_BASELAND_REPORT_PARAM.reset_rate2;
        dbElementArray[20] = nVO_BASELAND_REPORT_PARAM.ben_manage_ratio;
        dbElementArray[21] = nVO_BASELAND_REPORT_PARAM.build_cost_basedate;
        dbElementArray[22] = nVO_BASELAND_REPORT_PARAM.price_rate_type;
        nVO_BASELAND_REPORT_PARAM.elems = dbElementArray;
        nVO_BASELAND_REPORT_PARAM.fieldCount = this.fieldCount;
        nVO_BASELAND_REPORT_PARAM.orderString = this.orderString;
        nVO_BASELAND_REPORT_PARAM.tableName = this.tableName;
        return nVO_BASELAND_REPORT_PARAM;
    }
}

