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

public class NVO_BASELAND_DEVELOP
extends VoBase
implements Serializable,
Cloneable {
    private static final long serialVersionUID = 822437251L;
    private DbString baseno = new DbString("baseno");
    private DbString year = new DbString("year");
    private DbString city = new DbString("city");
    private DbString ofce = new DbString("ofce");
    private DbString dist = new DbString("dist");
    private DbDouble are_area = new DbDouble("are_area");
    private DbString inst_code = new DbString("inst_code");
    private DbInteger floor_up = new DbInteger("floor_up");
    private DbInteger floor_dw = new DbInteger("floor_dw");
    private DbDouble develop_years = new DbDouble("develop_years");
    private DbString floor_type = new DbString("floor_type");
    private DbDouble bfloor_ratio = new DbDouble("bfloor_ratio");
    private DbDouble benefit_rate = new DbDouble("benefit_rate");
    private DbInteger build_cost = new DbInteger("build_cost");
    private DbDouble owner_rate = new DbDouble("owner_rate");
    private DbDouble owner_ratio = new DbDouble("owner_ratio");
    private DbDouble debt_rate = new DbDouble("debt_rate");
    private DbDouble debt_ratio = new DbDouble("debt_ratio");
    private DbDouble presale_rate = new DbDouble("presale_rate");
    private DbDouble presale_ratio = new DbDouble("presale_ratio");
    private DbDouble year_rate = new DbDouble("year_rate");
    private DbDouble funds_ratio = new DbDouble("funds_ratio");
    private DbDouble sum_rate = new DbDouble("sum_rate");
    private DbLong build_value = new DbLong("build_value");
    private DbDouble build_ratio = new DbDouble("build_ratio");
    private DbLong land_value = new DbLong("land_value");
    private DbDouble land_ratio = new DbDouble("land_ratio");
    private DbLong total_value = new DbLong("total_value");
    private DbDouble total_ratio = new DbDouble("total_ratio");
    private DbString floor1_name = new DbString("floor1_name", "1F");
    private DbString floor1_purpose = new DbString("floor1_purpose", "\u5546\u5e97");
    private DbDouble floor1_area = new DbDouble("floor1_area");
    private DbInteger floor1_uprice = new DbInteger("floor1_uprice");
    private DbLong floor1_value = new DbLong("floor1_value");
    private DbString floor2_name = new DbString("floor2_name", "2F\u4ee5\u4e0a");
    private DbString floor2_purpose = new DbString("floor2_purpose", "\u8fa6\u516c\u6216\u4f4f\u5b85");
    private DbDouble floor2_area = new DbDouble("floor2_area");
    private DbInteger floor2_uprice = new DbInteger("floor2_uprice");
    private DbLong floor2_value = new DbLong("floor2_value");
    private DbString rf_purpose = new DbString("rf_purpose", "\u5171\u540c\u4f7f\u7528");
    private DbDouble rf_area = new DbDouble("rf_area");
    private DbInteger rf_uprice = new DbInteger("rf_uprice");
    private DbLong rf_value = new DbLong("rf_value");
    private DbDouble other_area = new DbDouble("other_area");
    private DbLong other_value = new DbLong("other_value");
    private DbDouble park_area = new DbDouble("park_area");
    private DbInteger park_cnt = new DbInteger("park_cnt");
    private DbInteger park_uprice = new DbInteger("park_uprice");
    private DbLong park_value = new DbLong("park_value");
    private DbDouble sale_area = new DbDouble("sale_area");
    private DbLong sale_value = new DbLong("sale_value");
    private DbString same_case = new DbString("same_case");
    private DbString price_type = new DbString("price_type");
    private DbDouble build_cost_rate = new DbDouble("build_cost_rate", 100.0);
    private DbLong direct_cost = new DbLong("direct_cost");
    private DbDouble design_ratio = new DbDouble("design_ratio");
    private DbLong design_cost = new DbLong("design_cost");
    private DbDouble ad_ratio = new DbDouble("ad_ratio");
    private DbLong ad_cost = new DbLong("ad_cost");
    private DbDouble manage_ratio = new DbDouble("manage_ratio");
    private DbLong manage_cost = new DbLong("manage_cost");
    private DbDouble tax_ratio = new DbDouble("tax_ratio");
    private DbLong tax_cost = new DbLong("tax_cost");
    private DbDouble indir_ratio = new DbDouble("indir_ratio");
    private DbLong indir_cost = new DbLong("indir_cost");
    private DbString sale_value_memo = new DbString("sale_value_memo");
    private DbInteger land_unit_price = new DbInteger("land_unit_price");
    private DbDouble sale_are_ratio = new DbDouble("sale_are_ratio");
    private DbString sale_are_type = new DbString("sale_are_type");
    private DbString is_merge = new DbString("is_merge");
    private DbDouble merge_rate = new DbDouble("merge_rate");
    private DbString notes = new DbString("notes");
    private DbDouble sale_electric = new DbDouble("sale_electric");
    private DbDouble sale_balcony = new DbDouble("sale_balcony");
    private DbInteger sale_protrusionc = new DbInteger("sale_protrusionc");
    private DbInteger sale_protrusionm = new DbInteger("sale_protrusionm");
    private DbDouble sale_publicratio = new DbDouble("sale_publicratio");
    private DbDouble sale_parkarea = new DbDouble("sale_parkarea");
    private DbDouble build_cost_exp = new DbDouble("build_cost_exp");
    private double are_area_ping = 0.0;
    private int build_cost_ping = 0;
    private int build_cost_adjust = 0;
    private int build_cost_adjust_ping = 0;
    private int land_unit_price_ping = 0;

    public NVO_BASELAND_DEVELOP() {
        this.tableName = "baseland_develop";
        super.setFieldCount(79);
        this.elems = new DbElement[79];
        this.elems[0] = this.baseno;
        this.elems[1] = this.year;
        this.elems[2] = this.city;
        this.elems[3] = this.ofce;
        this.elems[4] = this.dist;
        this.elems[5] = this.are_area;
        this.elems[6] = this.inst_code;
        this.elems[7] = this.floor_up;
        this.elems[8] = this.floor_dw;
        this.elems[9] = this.develop_years;
        this.elems[10] = this.floor_type;
        this.elems[11] = this.bfloor_ratio;
        this.elems[12] = this.benefit_rate;
        this.elems[13] = this.build_cost;
        this.elems[14] = this.owner_rate;
        this.elems[15] = this.owner_ratio;
        this.elems[16] = this.debt_rate;
        this.elems[17] = this.debt_ratio;
        this.elems[18] = this.presale_rate;
        this.elems[19] = this.presale_ratio;
        this.elems[20] = this.year_rate;
        this.elems[21] = this.funds_ratio;
        this.elems[22] = this.sum_rate;
        this.elems[23] = this.build_value;
        this.elems[24] = this.build_ratio;
        this.elems[25] = this.land_value;
        this.elems[26] = this.land_ratio;
        this.elems[27] = this.total_value;
        this.elems[28] = this.total_ratio;
        this.elems[29] = this.floor1_name;
        this.elems[30] = this.floor1_purpose;
        this.elems[31] = this.floor1_area;
        this.elems[32] = this.floor1_uprice;
        this.elems[33] = this.floor1_value;
        this.elems[34] = this.floor2_name;
        this.elems[35] = this.floor2_purpose;
        this.elems[36] = this.floor2_area;
        this.elems[37] = this.floor2_uprice;
        this.elems[38] = this.floor2_value;
        this.elems[39] = this.rf_purpose;
        this.elems[40] = this.rf_area;
        this.elems[41] = this.rf_uprice;
        this.elems[42] = this.rf_value;
        this.elems[43] = this.other_area;
        this.elems[44] = this.other_value;
        this.elems[45] = this.park_area;
        this.elems[46] = this.park_cnt;
        this.elems[47] = this.park_uprice;
        this.elems[48] = this.park_value;
        this.elems[49] = this.sale_area;
        this.elems[50] = this.sale_value;
        this.elems[51] = this.same_case;
        this.elems[52] = this.price_type;
        this.elems[53] = this.build_cost_rate;
        this.elems[54] = this.direct_cost;
        this.elems[55] = this.design_ratio;
        this.elems[56] = this.design_cost;
        this.elems[57] = this.ad_ratio;
        this.elems[58] = this.ad_cost;
        this.elems[59] = this.manage_ratio;
        this.elems[60] = this.manage_cost;
        this.elems[61] = this.tax_ratio;
        this.elems[62] = this.tax_cost;
        this.elems[63] = this.indir_ratio;
        this.elems[64] = this.indir_cost;
        this.elems[65] = this.sale_value_memo;
        this.elems[66] = this.land_unit_price;
        this.elems[67] = this.sale_are_ratio;
        this.elems[68] = this.sale_are_type;
        this.elems[69] = this.is_merge;
        this.elems[70] = this.merge_rate;
        this.elems[71] = this.notes;
        this.elems[72] = this.sale_electric;
        this.elems[73] = this.sale_balcony;
        this.elems[74] = this.sale_protrusionc;
        this.elems[75] = this.sale_protrusionm;
        this.elems[76] = this.sale_publicratio;
        this.elems[77] = this.sale_parkarea;
        this.elems[78] = this.build_cost_exp;
        this.baseno.setPkFlag(true);
        this.year.setPkFlag(true);
    }

    public double getAre_area_ping() {
        return this.are_area_ping;
    }

    public void setAre_area_ping(double d) {
        this.are_area_ping = d;
    }

    public int getBuild_cost_ping() {
        return this.build_cost_ping;
    }

    public void setBuild_cost_ping(int n) {
        this.build_cost_ping = n;
    }

    public int getBuild_cost_adjust() {
        return this.build_cost_adjust;
    }

    public void setBuild_cost_adjust(int n) {
        this.build_cost_adjust = n;
    }

    public int getBuild_cost_adjust_ping() {
        return this.build_cost_adjust_ping;
    }

    public void setBuild_cost_adjust_ping(int n) {
        this.build_cost_adjust_ping = n;
    }

    public int getLand_unit_price_ping() {
        return this.land_unit_price_ping;
    }

    public void setLand_unit_price_ping(int n) {
        this.land_unit_price_ping = n;
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

    public String getCity() {
        return this.city.getValue();
    }

    public String getOfce() {
        return this.ofce.getValue();
    }

    public String getDist() {
        return this.dist.getValue();
    }

    public double getAre_area() {
        return this.are_area.getValue();
    }

    public String getInst_code() {
        return this.inst_code.getValue();
    }

    public int getFloor_up() {
        return this.floor_up.getValue();
    }

    public int getFloor_dw() {
        return this.floor_dw.getValue();
    }

    public double getDevelop_years() {
        return this.develop_years.getValue();
    }

    public String getFloor_type() {
        return this.floor_type.getValue();
    }

    public double getBfloor_ratio() {
        return this.bfloor_ratio.getValue();
    }

    public double getBenefit_rate() {
        return this.benefit_rate.getValue();
    }

    public int getBuild_cost() {
        return this.build_cost.getValue();
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

    public double getYear_rate() {
        return this.year_rate.getValue();
    }

    public double getFunds_ratio() {
        return this.funds_ratio.getValue();
    }

    public double getSum_rate() {
        return this.sum_rate.getValue();
    }

    public long getBuild_value() {
        return this.build_value.getValue();
    }

    public double getBuild_ratio() {
        return this.build_ratio.getValue();
    }

    public long getLand_value() {
        return this.land_value.getValue();
    }

    public double getLand_ratio() {
        return this.land_ratio.getValue();
    }

    public long getTotal_value() {
        return this.total_value.getValue();
    }

    public double getTotal_ratio() {
        return this.total_ratio.getValue();
    }

    public String getFloor1_name() {
        return this.floor1_name.getValue();
    }

    public String getFloor1_purpose() {
        return this.floor1_purpose.getValue();
    }

    public double getFloor1_area() {
        return this.floor1_area.getValue();
    }

    public int getFloor1_uprice() {
        return this.floor1_uprice.getValue();
    }

    public long getFloor1_value() {
        return this.floor1_value.getValue();
    }

    public String getFloor2_name() {
        return this.floor2_name.getValue();
    }

    public String getFloor2_purpose() {
        return this.floor2_purpose.getValue();
    }

    public double getFloor2_area() {
        return this.floor2_area.getValue();
    }

    public int getFloor2_uprice() {
        return this.floor2_uprice.getValue();
    }

    public long getFloor2_value() {
        return this.floor2_value.getValue();
    }

    public String getRf_purpose() {
        return this.rf_purpose.getValue();
    }

    public double getRf_area() {
        return this.rf_area.getValue();
    }

    public int getRf_uprice() {
        return this.rf_uprice.getValue();
    }

    public long getRf_value() {
        return this.rf_value.getValue();
    }

    public double getOther_area() {
        return this.other_area.getValue();
    }

    public long getOther_value() {
        return this.other_value.getValue();
    }

    public double getPark_area() {
        return this.park_area.getValue();
    }

    public int getPark_cnt() {
        return this.park_cnt.getValue();
    }

    public int getPark_uprice() {
        return this.park_uprice.getValue();
    }

    public long getPark_value() {
        return this.park_value.getValue();
    }

    public double getSale_area() {
        return this.sale_area.getValue();
    }

    public long getSale_value() {
        return this.sale_value.getValue();
    }

    public String getSame_case() {
        return this.same_case.getValue();
    }

    public String getPrice_type() {
        return this.price_type.getValue();
    }

    public double getBuild_cost_rate() {
        return this.build_cost_rate.getValue();
    }

    public long getDirect_cost() {
        return this.direct_cost.getValue();
    }

    public double getDesign_ratio() {
        return this.design_ratio.getValue();
    }

    public long getDesign_cost() {
        return this.design_cost.getValue();
    }

    public double getAd_ratio() {
        return this.ad_ratio.getValue();
    }

    public long getAd_cost() {
        return this.ad_cost.getValue();
    }

    public double getManage_ratio() {
        return this.manage_ratio.getValue();
    }

    public long getManage_cost() {
        return this.manage_cost.getValue();
    }

    public double getTax_ratio() {
        return this.tax_ratio.getValue();
    }

    public long getTax_cost() {
        return this.tax_cost.getValue();
    }

    public double getIndir_ratio() {
        return this.indir_ratio.getValue();
    }

    public long getIndir_cost() {
        return this.indir_cost.getValue();
    }

    public String getSale_value_memo() {
        return this.sale_value_memo.getValue();
    }

    public int getLand_unit_price() {
        return this.land_unit_price.getValue();
    }

    public double getSale_are_ratio() {
        return this.sale_are_ratio.getValue();
    }

    public String getSale_are_type() {
        return this.sale_are_type.getValue();
    }

    public String getIs_merge() {
        return this.is_merge.getValue();
    }

    public double getMerge_rate() {
        return this.merge_rate.getValue();
    }

    public String getNotes() {
        return this.notes.getValue();
    }

    public double getSale_electric() {
        return this.sale_electric.getValue();
    }

    public double getSale_balcony() {
        return this.sale_balcony.getValue();
    }

    public int getSale_protrusionc() {
        return this.sale_protrusionc.getValue();
    }

    public int getSale_protrusionm() {
        return this.sale_protrusionm.getValue();
    }

    public double getSale_publicratio() {
        return this.sale_publicratio.getValue();
    }

    public double getSale_parkarea() {
        return this.sale_parkarea.getValue();
    }

    public double getBuild_cost_exp() {
        return this.build_cost_exp.getValue();
    }

    public void setBaseno(String string) {
        this.baseno.setValue(string);
    }

    public void setYear(String string) {
        this.year.setValue(string);
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

    public void setAre_area(double d) {
        this.are_area.setValue(d);
    }

    public void setInst_code(String string) {
        this.inst_code.setValue(string);
    }

    public void setFloor_up(int n) {
        this.floor_up.setValue(n);
    }

    public void setFloor_dw(int n) {
        this.floor_dw.setValue(n);
    }

    public void setDevelop_years(double d) {
        this.develop_years.setValue(d);
    }

    public void setFloor_type(String string) {
        this.floor_type.setValue(string);
    }

    public void setBfloor_ratio(double d) {
        this.bfloor_ratio.setValue(d);
    }

    public void setBenefit_rate(double d) {
        this.benefit_rate.setValue(d);
    }

    public void setBuild_cost(int n) {
        this.build_cost.setValue(n);
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

    public void setYear_rate(double d) {
        this.year_rate.setValue(d);
    }

    public void setFunds_ratio(double d) {
        this.funds_ratio.setValue(d);
    }

    public void setSum_rate(double d) {
        this.sum_rate.setValue(d);
    }

    public void setBuild_value(long l) {
        this.build_value.setValue(l);
    }

    public void setBuild_ratio(double d) {
        this.build_ratio.setValue(d);
    }

    public void setLand_value(long l) {
        this.land_value.setValue(l);
    }

    public void setLand_ratio(double d) {
        this.land_ratio.setValue(d);
    }

    public void setTotal_value(long l) {
        this.total_value.setValue(l);
    }

    public void setTotal_ratio(double d) {
        this.total_ratio.setValue(d);
    }

    public void setFloor1_name(String string) {
        this.floor1_name.setValue(string);
    }

    public void setFloor1_purpose(String string) {
        this.floor1_purpose.setValue(string);
    }

    public void setFloor1_area(double d) {
        this.floor1_area.setValue(d);
    }

    public void setFloor1_uprice(int n) {
        this.floor1_uprice.setValue(n);
    }

    public void setFloor1_value(long l) {
        this.floor1_value.setValue(l);
    }

    public void setFloor2_name(String string) {
        this.floor2_name.setValue(string);
    }

    public void setFloor2_purpose(String string) {
        this.floor2_purpose.setValue(string);
    }

    public void setFloor2_area(double d) {
        this.floor2_area.setValue(d);
    }

    public void setFloor2_uprice(int n) {
        this.floor2_uprice.setValue(n);
    }

    public void setFloor2_value(long l) {
        this.floor2_value.setValue(l);
    }

    public void setRf_purpose(String string) {
        this.rf_purpose.setValue(string);
    }

    public void setRf_area(double d) {
        this.rf_area.setValue(d);
    }

    public void setRf_uprice(int n) {
        this.rf_uprice.setValue(n);
    }

    public void setRf_value(long l) {
        this.rf_value.setValue(l);
    }

    public void setOther_area(double d) {
        this.other_area.setValue(d);
    }

    public void setOther_value(long l) {
        this.other_value.setValue(l);
    }

    public void setPark_area(double d) {
        this.park_area.setValue(d);
    }

    public void setPark_cnt(int n) {
        this.park_cnt.setValue(n);
    }

    public void setPark_uprice(int n) {
        this.park_uprice.setValue(n);
    }

    public void setPark_value(long l) {
        this.park_value.setValue(l);
    }

    public void setSale_area(double d) {
        this.sale_area.setValue(d);
    }

    public void setSale_value(long l) {
        this.sale_value.setValue(l);
    }

    public void setSame_case(String string) {
        this.same_case.setValue(string);
    }

    public void setPrice_type(String string) {
        this.price_type.setValue(string);
    }

    public void setBuild_cost_rate(double d) {
        this.build_cost_rate.setValue(d);
    }

    public void setDirect_cost(long l) {
        this.direct_cost.setValue(l);
    }

    public void setDesign_ratio(double d) {
        this.design_ratio.setValue(d);
    }

    public void setDesign_cost(long l) {
        this.design_cost.setValue(l);
    }

    public void setAd_ratio(double d) {
        this.ad_ratio.setValue(d);
    }

    public void setAd_cost(long l) {
        this.ad_cost.setValue(l);
    }

    public void setManage_ratio(double d) {
        this.manage_ratio.setValue(d);
    }

    public void setManage_cost(long l) {
        this.manage_cost.setValue(l);
    }

    public void setTax_ratio(double d) {
        this.tax_ratio.setValue(d);
    }

    public void setTax_cost(long l) {
        this.tax_cost.setValue(l);
    }

    public void setIndir_ratio(double d) {
        this.indir_ratio.setValue(d);
    }

    public void setIndir_cost(long l) {
        this.indir_cost.setValue(l);
    }

    public void setSale_value_memo(String string) {
        this.sale_value_memo.setValue(string);
    }

    public void setLand_unit_price(int n) {
        this.land_unit_price.setValue(n);
    }

    public void setSale_are_ratio(double d) {
        this.sale_are_ratio.setValue(d);
    }

    public void setSale_are_type(String string) {
        this.sale_are_type.setValue(string);
    }

    public void setIs_merge(String string) {
        this.is_merge.setValue(string);
    }

    public void setMerge_rate(double d) {
        this.merge_rate.setValue(d);
    }

    public void setNotes(String string) {
        this.notes.setValue(string);
    }

    public void setSale_electric(double d) {
        this.sale_electric.setValue(d);
    }

    public void setSale_balcony(double d) {
        this.sale_balcony.setValue(d);
    }

    public void setSale_protrusionc(int n) {
        this.sale_protrusionc.setValue(n);
    }

    public void setSale_protrusionm(int n) {
        this.sale_protrusionm.setValue(n);
    }

    public void setSale_publicratio(double d) {
        this.sale_publicratio.setValue(d);
    }

    public void setSale_parkarea(double d) {
        this.sale_parkarea.setValue(d);
    }

    public void setBuild_cost_exp(double d) {
        this.build_cost_exp.setValue(d);
    }

    @Override
    public Object clone() {
        NVO_BASELAND_DEVELOP nVO_BASELAND_DEVELOP = new NVO_BASELAND_DEVELOP();
        DbElement[] dbElementArray = new DbElement[this.elems.length];
        nVO_BASELAND_DEVELOP.baseno = (DbString)this.baseno.clone();
        nVO_BASELAND_DEVELOP.year = (DbString)this.year.clone();
        nVO_BASELAND_DEVELOP.city = (DbString)this.city.clone();
        nVO_BASELAND_DEVELOP.ofce = (DbString)this.ofce.clone();
        nVO_BASELAND_DEVELOP.dist = (DbString)this.dist.clone();
        nVO_BASELAND_DEVELOP.are_area = (DbDouble)this.are_area.clone();
        nVO_BASELAND_DEVELOP.inst_code = (DbString)this.inst_code.clone();
        nVO_BASELAND_DEVELOP.floor_up = (DbInteger)this.floor_up.clone();
        nVO_BASELAND_DEVELOP.floor_dw = (DbInteger)this.floor_dw.clone();
        nVO_BASELAND_DEVELOP.develop_years = (DbDouble)this.develop_years.clone();
        nVO_BASELAND_DEVELOP.floor_type = (DbString)this.floor_type.clone();
        nVO_BASELAND_DEVELOP.bfloor_ratio = (DbDouble)this.bfloor_ratio.clone();
        nVO_BASELAND_DEVELOP.benefit_rate = (DbDouble)this.benefit_rate.clone();
        nVO_BASELAND_DEVELOP.build_cost = (DbInteger)this.build_cost.clone();
        nVO_BASELAND_DEVELOP.owner_rate = (DbDouble)this.owner_rate.clone();
        nVO_BASELAND_DEVELOP.owner_ratio = (DbDouble)this.owner_ratio.clone();
        nVO_BASELAND_DEVELOP.debt_rate = (DbDouble)this.debt_rate.clone();
        nVO_BASELAND_DEVELOP.debt_ratio = (DbDouble)this.debt_ratio.clone();
        nVO_BASELAND_DEVELOP.presale_rate = (DbDouble)this.presale_rate.clone();
        nVO_BASELAND_DEVELOP.presale_ratio = (DbDouble)this.presale_ratio.clone();
        nVO_BASELAND_DEVELOP.year_rate = (DbDouble)this.year_rate.clone();
        nVO_BASELAND_DEVELOP.funds_ratio = (DbDouble)this.funds_ratio.clone();
        nVO_BASELAND_DEVELOP.sum_rate = (DbDouble)this.sum_rate.clone();
        nVO_BASELAND_DEVELOP.build_value = (DbLong)this.build_value.clone();
        nVO_BASELAND_DEVELOP.build_ratio = (DbDouble)this.build_ratio.clone();
        nVO_BASELAND_DEVELOP.land_value = (DbLong)this.land_value.clone();
        nVO_BASELAND_DEVELOP.land_ratio = (DbDouble)this.land_ratio.clone();
        nVO_BASELAND_DEVELOP.total_value = (DbLong)this.total_value.clone();
        nVO_BASELAND_DEVELOP.total_ratio = (DbDouble)this.total_ratio.clone();
        nVO_BASELAND_DEVELOP.floor1_name = (DbString)this.floor1_name.clone();
        nVO_BASELAND_DEVELOP.floor1_purpose = (DbString)this.floor1_purpose.clone();
        nVO_BASELAND_DEVELOP.floor1_area = (DbDouble)this.floor1_area.clone();
        nVO_BASELAND_DEVELOP.floor1_uprice = (DbInteger)this.floor1_uprice.clone();
        nVO_BASELAND_DEVELOP.floor1_value = (DbLong)this.floor1_value.clone();
        nVO_BASELAND_DEVELOP.floor2_name = (DbString)this.floor2_name.clone();
        nVO_BASELAND_DEVELOP.floor2_purpose = (DbString)this.floor2_purpose.clone();
        nVO_BASELAND_DEVELOP.floor2_area = (DbDouble)this.floor2_area.clone();
        nVO_BASELAND_DEVELOP.floor2_uprice = (DbInteger)this.floor2_uprice.clone();
        nVO_BASELAND_DEVELOP.floor2_value = (DbLong)this.floor2_value.clone();
        nVO_BASELAND_DEVELOP.rf_purpose = (DbString)this.rf_purpose.clone();
        nVO_BASELAND_DEVELOP.rf_area = (DbDouble)this.rf_area.clone();
        nVO_BASELAND_DEVELOP.rf_uprice = (DbInteger)this.rf_uprice.clone();
        nVO_BASELAND_DEVELOP.rf_value = (DbLong)this.rf_value.clone();
        nVO_BASELAND_DEVELOP.other_area = (DbDouble)this.other_area.clone();
        nVO_BASELAND_DEVELOP.other_value = (DbLong)this.other_value.clone();
        nVO_BASELAND_DEVELOP.park_area = (DbDouble)this.park_area.clone();
        nVO_BASELAND_DEVELOP.park_cnt = (DbInteger)this.park_cnt.clone();
        nVO_BASELAND_DEVELOP.park_uprice = (DbInteger)this.park_uprice.clone();
        nVO_BASELAND_DEVELOP.park_value = (DbLong)this.park_value.clone();
        nVO_BASELAND_DEVELOP.sale_area = (DbDouble)this.sale_area.clone();
        nVO_BASELAND_DEVELOP.sale_value = (DbLong)this.sale_value.clone();
        nVO_BASELAND_DEVELOP.same_case = (DbString)this.same_case.clone();
        nVO_BASELAND_DEVELOP.price_type = (DbString)this.price_type.clone();
        nVO_BASELAND_DEVELOP.build_cost_rate = (DbDouble)this.build_cost_rate.clone();
        nVO_BASELAND_DEVELOP.direct_cost = (DbLong)this.direct_cost.clone();
        nVO_BASELAND_DEVELOP.design_ratio = (DbDouble)this.design_ratio.clone();
        nVO_BASELAND_DEVELOP.design_cost = (DbLong)this.design_cost.clone();
        nVO_BASELAND_DEVELOP.ad_ratio = (DbDouble)this.ad_ratio.clone();
        nVO_BASELAND_DEVELOP.ad_cost = (DbLong)this.ad_cost.clone();
        nVO_BASELAND_DEVELOP.manage_ratio = (DbDouble)this.manage_ratio.clone();
        nVO_BASELAND_DEVELOP.manage_cost = (DbLong)this.manage_cost.clone();
        nVO_BASELAND_DEVELOP.tax_ratio = (DbDouble)this.tax_ratio.clone();
        nVO_BASELAND_DEVELOP.tax_cost = (DbLong)this.tax_cost.clone();
        nVO_BASELAND_DEVELOP.indir_ratio = (DbDouble)this.indir_ratio.clone();
        nVO_BASELAND_DEVELOP.indir_cost = (DbLong)this.indir_cost.clone();
        nVO_BASELAND_DEVELOP.sale_value_memo = (DbString)this.sale_value_memo.clone();
        nVO_BASELAND_DEVELOP.land_unit_price = (DbInteger)this.land_unit_price.clone();
        nVO_BASELAND_DEVELOP.sale_are_ratio = (DbDouble)this.sale_are_ratio.clone();
        nVO_BASELAND_DEVELOP.sale_are_type = (DbString)this.sale_are_type.clone();
        nVO_BASELAND_DEVELOP.is_merge = (DbString)this.is_merge.clone();
        nVO_BASELAND_DEVELOP.merge_rate = (DbDouble)this.merge_rate.clone();
        nVO_BASELAND_DEVELOP.notes = (DbString)this.notes.clone();
        nVO_BASELAND_DEVELOP.sale_electric = (DbDouble)this.sale_electric.clone();
        nVO_BASELAND_DEVELOP.sale_balcony = (DbDouble)this.sale_balcony.clone();
        nVO_BASELAND_DEVELOP.sale_protrusionc = (DbInteger)this.sale_protrusionc.clone();
        nVO_BASELAND_DEVELOP.sale_protrusionm = (DbInteger)this.sale_protrusionm.clone();
        nVO_BASELAND_DEVELOP.sale_publicratio = (DbDouble)this.sale_publicratio.clone();
        nVO_BASELAND_DEVELOP.sale_parkarea = (DbDouble)this.sale_parkarea.clone();
        nVO_BASELAND_DEVELOP.build_cost_exp = (DbDouble)this.build_cost_exp.clone();
        dbElementArray[0] = nVO_BASELAND_DEVELOP.baseno;
        dbElementArray[1] = nVO_BASELAND_DEVELOP.year;
        dbElementArray[2] = nVO_BASELAND_DEVELOP.city;
        dbElementArray[3] = nVO_BASELAND_DEVELOP.ofce;
        dbElementArray[4] = nVO_BASELAND_DEVELOP.dist;
        dbElementArray[5] = nVO_BASELAND_DEVELOP.are_area;
        dbElementArray[6] = nVO_BASELAND_DEVELOP.inst_code;
        dbElementArray[7] = nVO_BASELAND_DEVELOP.floor_up;
        dbElementArray[8] = nVO_BASELAND_DEVELOP.floor_dw;
        dbElementArray[9] = nVO_BASELAND_DEVELOP.develop_years;
        dbElementArray[10] = nVO_BASELAND_DEVELOP.floor_type;
        dbElementArray[11] = nVO_BASELAND_DEVELOP.bfloor_ratio;
        dbElementArray[12] = nVO_BASELAND_DEVELOP.benefit_rate;
        dbElementArray[13] = nVO_BASELAND_DEVELOP.build_cost;
        dbElementArray[14] = nVO_BASELAND_DEVELOP.owner_rate;
        dbElementArray[15] = nVO_BASELAND_DEVELOP.owner_ratio;
        dbElementArray[16] = nVO_BASELAND_DEVELOP.debt_rate;
        dbElementArray[17] = nVO_BASELAND_DEVELOP.debt_ratio;
        dbElementArray[18] = nVO_BASELAND_DEVELOP.presale_rate;
        dbElementArray[19] = nVO_BASELAND_DEVELOP.presale_ratio;
        dbElementArray[20] = nVO_BASELAND_DEVELOP.year_rate;
        dbElementArray[21] = nVO_BASELAND_DEVELOP.funds_ratio;
        dbElementArray[22] = nVO_BASELAND_DEVELOP.sum_rate;
        dbElementArray[23] = nVO_BASELAND_DEVELOP.build_value;
        dbElementArray[24] = nVO_BASELAND_DEVELOP.build_ratio;
        dbElementArray[25] = nVO_BASELAND_DEVELOP.land_value;
        dbElementArray[26] = nVO_BASELAND_DEVELOP.land_ratio;
        dbElementArray[27] = nVO_BASELAND_DEVELOP.total_value;
        dbElementArray[28] = nVO_BASELAND_DEVELOP.total_ratio;
        dbElementArray[29] = nVO_BASELAND_DEVELOP.floor1_name;
        dbElementArray[30] = nVO_BASELAND_DEVELOP.floor1_purpose;
        dbElementArray[31] = nVO_BASELAND_DEVELOP.floor1_area;
        dbElementArray[32] = nVO_BASELAND_DEVELOP.floor1_uprice;
        dbElementArray[33] = nVO_BASELAND_DEVELOP.floor1_value;
        dbElementArray[34] = nVO_BASELAND_DEVELOP.floor2_name;
        dbElementArray[35] = nVO_BASELAND_DEVELOP.floor2_purpose;
        dbElementArray[36] = nVO_BASELAND_DEVELOP.floor2_area;
        dbElementArray[37] = nVO_BASELAND_DEVELOP.floor2_uprice;
        dbElementArray[38] = nVO_BASELAND_DEVELOP.floor2_value;
        dbElementArray[39] = nVO_BASELAND_DEVELOP.rf_purpose;
        dbElementArray[40] = nVO_BASELAND_DEVELOP.rf_area;
        dbElementArray[41] = nVO_BASELAND_DEVELOP.rf_uprice;
        dbElementArray[42] = nVO_BASELAND_DEVELOP.rf_value;
        dbElementArray[43] = nVO_BASELAND_DEVELOP.other_area;
        dbElementArray[44] = nVO_BASELAND_DEVELOP.other_value;
        dbElementArray[45] = nVO_BASELAND_DEVELOP.park_area;
        dbElementArray[46] = nVO_BASELAND_DEVELOP.park_cnt;
        dbElementArray[47] = nVO_BASELAND_DEVELOP.park_uprice;
        dbElementArray[48] = nVO_BASELAND_DEVELOP.park_value;
        dbElementArray[49] = nVO_BASELAND_DEVELOP.sale_area;
        dbElementArray[50] = nVO_BASELAND_DEVELOP.sale_value;
        dbElementArray[51] = nVO_BASELAND_DEVELOP.same_case;
        dbElementArray[52] = nVO_BASELAND_DEVELOP.price_type;
        dbElementArray[53] = nVO_BASELAND_DEVELOP.build_cost_rate;
        dbElementArray[54] = nVO_BASELAND_DEVELOP.direct_cost;
        dbElementArray[55] = nVO_BASELAND_DEVELOP.design_ratio;
        dbElementArray[56] = nVO_BASELAND_DEVELOP.design_cost;
        dbElementArray[57] = nVO_BASELAND_DEVELOP.ad_ratio;
        dbElementArray[58] = nVO_BASELAND_DEVELOP.ad_cost;
        dbElementArray[59] = nVO_BASELAND_DEVELOP.manage_ratio;
        dbElementArray[60] = nVO_BASELAND_DEVELOP.manage_cost;
        dbElementArray[61] = nVO_BASELAND_DEVELOP.tax_ratio;
        dbElementArray[62] = nVO_BASELAND_DEVELOP.tax_cost;
        dbElementArray[63] = nVO_BASELAND_DEVELOP.indir_ratio;
        dbElementArray[64] = nVO_BASELAND_DEVELOP.indir_cost;
        dbElementArray[65] = nVO_BASELAND_DEVELOP.sale_value_memo;
        dbElementArray[66] = nVO_BASELAND_DEVELOP.land_unit_price;
        dbElementArray[67] = nVO_BASELAND_DEVELOP.sale_are_ratio;
        dbElementArray[68] = nVO_BASELAND_DEVELOP.sale_are_type;
        dbElementArray[69] = nVO_BASELAND_DEVELOP.is_merge;
        dbElementArray[70] = nVO_BASELAND_DEVELOP.merge_rate;
        dbElementArray[71] = nVO_BASELAND_DEVELOP.notes;
        dbElementArray[72] = nVO_BASELAND_DEVELOP.sale_electric;
        dbElementArray[73] = nVO_BASELAND_DEVELOP.sale_balcony;
        dbElementArray[74] = nVO_BASELAND_DEVELOP.sale_protrusionc;
        dbElementArray[75] = nVO_BASELAND_DEVELOP.sale_protrusionm;
        dbElementArray[76] = nVO_BASELAND_DEVELOP.sale_publicratio;
        dbElementArray[77] = nVO_BASELAND_DEVELOP.sale_parkarea;
        dbElementArray[78] = nVO_BASELAND_DEVELOP.build_cost_exp;
        nVO_BASELAND_DEVELOP.elems = dbElementArray;
        nVO_BASELAND_DEVELOP.fieldCount = this.fieldCount;
        nVO_BASELAND_DEVELOP.orderString = this.orderString;
        nVO_BASELAND_DEVELOP.tableName = this.tableName;
        return nVO_BASELAND_DEVELOP;
    }
}

