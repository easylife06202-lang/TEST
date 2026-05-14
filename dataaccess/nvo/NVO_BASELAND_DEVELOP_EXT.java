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

public class NVO_BASELAND_DEVELOP_EXT
extends VoBase
implements Serializable,
Cloneable {
    private static final long serialVersionUID = -1121113787L;
    private DbString baseno = new DbString("baseno");
    private DbString year = new DbString("year");
    private DbString city = new DbString("city");
    private DbString land_position_pseudo = new DbString("land_position_pseudo");
    private DbDouble area_pseudo = new DbDouble("area_pseudo");
    private DbDouble area_rate = new DbDouble("area_rate");
    private DbString landuse_pseudo = new DbString("landuse_pseudo");
    private DbDouble landuse_rate = new DbDouble("landuse_rate");
    private DbDouble cov_ratio_pseudo = new DbDouble("cov_ratio_pseudo");
    private DbDouble cov_rate = new DbDouble("cov_rate");
    private DbDouble are_ratio_pseudo = new DbDouble("are_ratio_pseudo");
    private DbDouble are_rate = new DbDouble("are_rate");
    private DbString shape_pseudo = new DbString("shape_pseudo");
    private DbDouble shape_rate = new DbDouble("shape_rate");
    private DbString slop_pseudo = new DbString("slop_pseudo");
    private DbDouble slop_rate = new DbDouble("slop_rate");
    private DbDouble width_pseudo = new DbDouble("width_pseudo");
    private DbDouble width_rate = new DbDouble("width_rate");
    private DbDouble deep_pseudo = new DbDouble("deep_pseudo");
    private DbDouble deep_rate = new DbDouble("deep_rate");
    private DbString street_rel_pseudo = new DbString("street_rel_pseudo");
    private DbDouble street_rel_rate = new DbDouble("street_rel_rate");
    private DbDouble roadwidth_pseudo = new DbDouble("roadwidth_pseudo");
    private DbDouble roadwidth_rate = new DbDouble("roadwidth_rate");
    private DbString com_eff_pseudo = new DbString("com_eff_pseudo");
    private DbString com_eff_base = new DbString("com_eff_base");
    private DbDouble com_eff_rate = new DbDouble("com_eff_rate");
    private DbString dev_eff_pseudo = new DbString("dev_eff_pseudo");
    private DbString dev_eff_base = new DbString("dev_eff_base");
    private DbDouble dev_eff_rate = new DbDouble("dev_eff_rate");
    private DbString other_pseudo = new DbString("other_pseudo");
    private DbString other_base = new DbString("other_base");
    private DbDouble other_rate = new DbDouble("other_rate");
    private DbDouble merge_rate_ext = new DbDouble("merge_rate_ext");

    public NVO_BASELAND_DEVELOP_EXT() {
        this.tableName = "baseland_develop_ext";
        super.setFieldCount(34);
        this.elems = new DbElement[34];
        this.elems[0] = this.baseno;
        this.elems[1] = this.year;
        this.elems[2] = this.city;
        this.elems[3] = this.land_position_pseudo;
        this.elems[4] = this.area_pseudo;
        this.elems[5] = this.area_rate;
        this.elems[6] = this.landuse_pseudo;
        this.elems[7] = this.landuse_rate;
        this.elems[8] = this.cov_ratio_pseudo;
        this.elems[9] = this.cov_rate;
        this.elems[10] = this.are_ratio_pseudo;
        this.elems[11] = this.are_rate;
        this.elems[12] = this.shape_pseudo;
        this.elems[13] = this.shape_rate;
        this.elems[14] = this.slop_pseudo;
        this.elems[15] = this.slop_rate;
        this.elems[16] = this.width_pseudo;
        this.elems[17] = this.width_rate;
        this.elems[18] = this.deep_pseudo;
        this.elems[19] = this.deep_rate;
        this.elems[20] = this.street_rel_pseudo;
        this.elems[21] = this.street_rel_rate;
        this.elems[22] = this.roadwidth_pseudo;
        this.elems[23] = this.roadwidth_rate;
        this.elems[24] = this.com_eff_pseudo;
        this.elems[25] = this.com_eff_base;
        this.elems[26] = this.com_eff_rate;
        this.elems[27] = this.dev_eff_pseudo;
        this.elems[28] = this.dev_eff_base;
        this.elems[29] = this.dev_eff_rate;
        this.elems[30] = this.other_pseudo;
        this.elems[31] = this.other_base;
        this.elems[32] = this.other_rate;
        this.elems[33] = this.merge_rate_ext;
        this.baseno.setPkFlag(true);
        this.year.setPkFlag(true);
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

    public String getLand_position_pseudo() {
        return this.land_position_pseudo.getValue();
    }

    public double getArea_pseudo() {
        return this.area_pseudo.getValue();
    }

    public double getArea_rate() {
        return this.area_rate.getValue();
    }

    public String getLanduse_pseudo() {
        return this.landuse_pseudo.getValue();
    }

    public double getLanduse_rate() {
        return this.landuse_rate.getValue();
    }

    public double getCov_ratio_pseudo() {
        return this.cov_ratio_pseudo.getValue();
    }

    public double getCov_rate() {
        return this.cov_rate.getValue();
    }

    public double getAre_ratio_pseudo() {
        return this.are_ratio_pseudo.getValue();
    }

    public double getAre_rate() {
        return this.are_rate.getValue();
    }

    public String getShape_pseudo() {
        return this.shape_pseudo.getValue();
    }

    public double getShape_rate() {
        return this.shape_rate.getValue();
    }

    public String getSlop_pseudo() {
        return this.slop_pseudo.getValue();
    }

    public double getSlop_rate() {
        return this.slop_rate.getValue();
    }

    public double getWidth_pseudo() {
        return this.width_pseudo.getValue();
    }

    public double getWidth_rate() {
        return this.width_rate.getValue();
    }

    public double getDeep_pseudo() {
        return this.deep_pseudo.getValue();
    }

    public double getDeep_rate() {
        return this.deep_rate.getValue();
    }

    public String getStreet_rel_pseudo() {
        return this.street_rel_pseudo.getValue();
    }

    public double getStreet_rel_rate() {
        return this.street_rel_rate.getValue();
    }

    public double getRoadwidth_pseudo() {
        return this.roadwidth_pseudo.getValue();
    }

    public double getRoadwidth_rate() {
        return this.roadwidth_rate.getValue();
    }

    public String getCom_eff_pseudo() {
        return this.com_eff_pseudo.getValue();
    }

    public String getCom_eff_base() {
        return this.com_eff_base.getValue();
    }

    public double getCom_eff_rate() {
        return this.com_eff_rate.getValue();
    }

    public String getDev_eff_pseudo() {
        return this.dev_eff_pseudo.getValue();
    }

    public String getDev_eff_base() {
        return this.dev_eff_base.getValue();
    }

    public double getDev_eff_rate() {
        return this.dev_eff_rate.getValue();
    }

    public String getOther_pseudo() {
        return this.other_pseudo.getValue();
    }

    public String getOther_base() {
        return this.other_base.getValue();
    }

    public double getOther_rate() {
        return this.other_rate.getValue();
    }

    public double getMerge_rate_ext() {
        return this.merge_rate_ext.getValue();
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

    public void setLand_position_pseudo(String string) {
        this.land_position_pseudo.setValue(string);
    }

    public void setArea_pseudo(double d) {
        this.area_pseudo.setValue(d);
    }

    public void setArea_rate(double d) {
        this.area_rate.setValue(d);
    }

    public void setLanduse_pseudo(String string) {
        this.landuse_pseudo.setValue(string);
    }

    public void setLanduse_rate(double d) {
        this.landuse_rate.setValue(d);
    }

    public void setCov_ratio_pseudo(double d) {
        this.cov_ratio_pseudo.setValue(d);
    }

    public void setCov_rate(double d) {
        this.cov_rate.setValue(d);
    }

    public void setAre_ratio_pseudo(double d) {
        this.are_ratio_pseudo.setValue(d);
    }

    public void setAre_rate(double d) {
        this.are_rate.setValue(d);
    }

    public void setShape_pseudo(String string) {
        this.shape_pseudo.setValue(string);
    }

    public void setShape_rate(double d) {
        this.shape_rate.setValue(d);
    }

    public void setSlop_pseudo(String string) {
        this.slop_pseudo.setValue(string);
    }

    public void setSlop_rate(double d) {
        this.slop_rate.setValue(d);
    }

    public void setWidth_pseudo(double d) {
        this.width_pseudo.setValue(d);
    }

    public void setWidth_rate(double d) {
        this.width_rate.setValue(d);
    }

    public void setDeep_pseudo(double d) {
        this.deep_pseudo.setValue(d);
    }

    public void setDeep_rate(double d) {
        this.deep_rate.setValue(d);
    }

    public void setStreet_rel_pseudo(String string) {
        this.street_rel_pseudo.setValue(string);
    }

    public void setStreet_rel_rate(double d) {
        this.street_rel_rate.setValue(d);
    }

    public void setRoadwidth_pseudo(double d) {
        this.roadwidth_pseudo.setValue(d);
    }

    public void setRoadwidth_rate(double d) {
        this.roadwidth_rate.setValue(d);
    }

    public void setCom_eff_pseudo(String string) {
        this.com_eff_pseudo.setValue(string);
    }

    public void setCom_eff_base(String string) {
        this.com_eff_base.setValue(string);
    }

    public void setCom_eff_rate(double d) {
        this.com_eff_rate.setValue(d);
    }

    public void setDev_eff_pseudo(String string) {
        this.dev_eff_pseudo.setValue(string);
    }

    public void setDev_eff_base(String string) {
        this.dev_eff_base.setValue(string);
    }

    public void setDev_eff_rate(double d) {
        this.dev_eff_rate.setValue(d);
    }

    public void setOther_pseudo(String string) {
        this.other_pseudo.setValue(string);
    }

    public void setOther_base(String string) {
        this.other_base.setValue(string);
    }

    public void setOther_rate(double d) {
        this.other_rate.setValue(d);
    }

    public void setMerge_rate_ext(double d) {
        this.merge_rate_ext.setValue(d);
    }

    @Override
    public Object clone() {
        NVO_BASELAND_DEVELOP_EXT nVO_BASELAND_DEVELOP_EXT = new NVO_BASELAND_DEVELOP_EXT();
        DbElement[] dbElementArray = new DbElement[this.elems.length];
        nVO_BASELAND_DEVELOP_EXT.baseno = (DbString)this.baseno.clone();
        nVO_BASELAND_DEVELOP_EXT.year = (DbString)this.year.clone();
        nVO_BASELAND_DEVELOP_EXT.city = (DbString)this.city.clone();
        nVO_BASELAND_DEVELOP_EXT.land_position_pseudo = (DbString)this.land_position_pseudo.clone();
        nVO_BASELAND_DEVELOP_EXT.area_pseudo = (DbDouble)this.area_pseudo.clone();
        nVO_BASELAND_DEVELOP_EXT.area_rate = (DbDouble)this.area_rate.clone();
        nVO_BASELAND_DEVELOP_EXT.landuse_pseudo = (DbString)this.landuse_pseudo.clone();
        nVO_BASELAND_DEVELOP_EXT.landuse_rate = (DbDouble)this.landuse_rate.clone();
        nVO_BASELAND_DEVELOP_EXT.cov_ratio_pseudo = (DbDouble)this.cov_ratio_pseudo.clone();
        nVO_BASELAND_DEVELOP_EXT.cov_rate = (DbDouble)this.cov_rate.clone();
        nVO_BASELAND_DEVELOP_EXT.are_ratio_pseudo = (DbDouble)this.are_ratio_pseudo.clone();
        nVO_BASELAND_DEVELOP_EXT.are_rate = (DbDouble)this.are_rate.clone();
        nVO_BASELAND_DEVELOP_EXT.shape_pseudo = (DbString)this.shape_pseudo.clone();
        nVO_BASELAND_DEVELOP_EXT.shape_rate = (DbDouble)this.shape_rate.clone();
        nVO_BASELAND_DEVELOP_EXT.slop_pseudo = (DbString)this.slop_pseudo.clone();
        nVO_BASELAND_DEVELOP_EXT.slop_rate = (DbDouble)this.slop_rate.clone();
        nVO_BASELAND_DEVELOP_EXT.width_pseudo = (DbDouble)this.width_pseudo.clone();
        nVO_BASELAND_DEVELOP_EXT.width_rate = (DbDouble)this.width_rate.clone();
        nVO_BASELAND_DEVELOP_EXT.deep_pseudo = (DbDouble)this.deep_pseudo.clone();
        nVO_BASELAND_DEVELOP_EXT.deep_rate = (DbDouble)this.deep_rate.clone();
        nVO_BASELAND_DEVELOP_EXT.street_rel_pseudo = (DbString)this.street_rel_pseudo.clone();
        nVO_BASELAND_DEVELOP_EXT.street_rel_rate = (DbDouble)this.street_rel_rate.clone();
        nVO_BASELAND_DEVELOP_EXT.roadwidth_pseudo = (DbDouble)this.roadwidth_pseudo.clone();
        nVO_BASELAND_DEVELOP_EXT.roadwidth_rate = (DbDouble)this.roadwidth_rate.clone();
        nVO_BASELAND_DEVELOP_EXT.com_eff_pseudo = (DbString)this.com_eff_pseudo.clone();
        nVO_BASELAND_DEVELOP_EXT.com_eff_base = (DbString)this.com_eff_base.clone();
        nVO_BASELAND_DEVELOP_EXT.com_eff_rate = (DbDouble)this.com_eff_rate.clone();
        nVO_BASELAND_DEVELOP_EXT.dev_eff_pseudo = (DbString)this.dev_eff_pseudo.clone();
        nVO_BASELAND_DEVELOP_EXT.dev_eff_base = (DbString)this.dev_eff_base.clone();
        nVO_BASELAND_DEVELOP_EXT.dev_eff_rate = (DbDouble)this.dev_eff_rate.clone();
        nVO_BASELAND_DEVELOP_EXT.other_pseudo = (DbString)this.other_pseudo.clone();
        nVO_BASELAND_DEVELOP_EXT.other_base = (DbString)this.other_base.clone();
        nVO_BASELAND_DEVELOP_EXT.other_rate = (DbDouble)this.other_rate.clone();
        nVO_BASELAND_DEVELOP_EXT.merge_rate_ext = (DbDouble)this.merge_rate_ext.clone();
        dbElementArray[0] = nVO_BASELAND_DEVELOP_EXT.baseno;
        dbElementArray[1] = nVO_BASELAND_DEVELOP_EXT.year;
        dbElementArray[2] = nVO_BASELAND_DEVELOP_EXT.city;
        dbElementArray[3] = nVO_BASELAND_DEVELOP_EXT.land_position_pseudo;
        dbElementArray[4] = nVO_BASELAND_DEVELOP_EXT.area_pseudo;
        dbElementArray[5] = nVO_BASELAND_DEVELOP_EXT.area_rate;
        dbElementArray[6] = nVO_BASELAND_DEVELOP_EXT.landuse_pseudo;
        dbElementArray[7] = nVO_BASELAND_DEVELOP_EXT.landuse_rate;
        dbElementArray[8] = nVO_BASELAND_DEVELOP_EXT.cov_ratio_pseudo;
        dbElementArray[9] = nVO_BASELAND_DEVELOP_EXT.cov_rate;
        dbElementArray[10] = nVO_BASELAND_DEVELOP_EXT.are_ratio_pseudo;
        dbElementArray[11] = nVO_BASELAND_DEVELOP_EXT.are_rate;
        dbElementArray[12] = nVO_BASELAND_DEVELOP_EXT.shape_pseudo;
        dbElementArray[13] = nVO_BASELAND_DEVELOP_EXT.shape_rate;
        dbElementArray[14] = nVO_BASELAND_DEVELOP_EXT.slop_pseudo;
        dbElementArray[15] = nVO_BASELAND_DEVELOP_EXT.slop_rate;
        dbElementArray[16] = nVO_BASELAND_DEVELOP_EXT.width_pseudo;
        dbElementArray[17] = nVO_BASELAND_DEVELOP_EXT.width_rate;
        dbElementArray[18] = nVO_BASELAND_DEVELOP_EXT.deep_pseudo;
        dbElementArray[19] = nVO_BASELAND_DEVELOP_EXT.deep_rate;
        dbElementArray[20] = nVO_BASELAND_DEVELOP_EXT.street_rel_pseudo;
        dbElementArray[21] = nVO_BASELAND_DEVELOP_EXT.street_rel_rate;
        dbElementArray[22] = nVO_BASELAND_DEVELOP_EXT.roadwidth_pseudo;
        dbElementArray[23] = nVO_BASELAND_DEVELOP_EXT.roadwidth_rate;
        dbElementArray[24] = nVO_BASELAND_DEVELOP_EXT.com_eff_pseudo;
        dbElementArray[25] = nVO_BASELAND_DEVELOP_EXT.com_eff_base;
        dbElementArray[26] = nVO_BASELAND_DEVELOP_EXT.com_eff_rate;
        dbElementArray[27] = nVO_BASELAND_DEVELOP_EXT.dev_eff_pseudo;
        dbElementArray[28] = nVO_BASELAND_DEVELOP_EXT.dev_eff_base;
        dbElementArray[29] = nVO_BASELAND_DEVELOP_EXT.dev_eff_rate;
        dbElementArray[30] = nVO_BASELAND_DEVELOP_EXT.other_pseudo;
        dbElementArray[31] = nVO_BASELAND_DEVELOP_EXT.other_base;
        dbElementArray[32] = nVO_BASELAND_DEVELOP_EXT.other_rate;
        dbElementArray[33] = nVO_BASELAND_DEVELOP_EXT.merge_rate_ext;
        nVO_BASELAND_DEVELOP_EXT.elems = dbElementArray;
        nVO_BASELAND_DEVELOP_EXT.fieldCount = this.fieldCount;
        nVO_BASELAND_DEVELOP_EXT.orderString = this.orderString;
        nVO_BASELAND_DEVELOP_EXT.tableName = this.tableName;
        return nVO_BASELAND_DEVELOP_EXT;
    }
}

