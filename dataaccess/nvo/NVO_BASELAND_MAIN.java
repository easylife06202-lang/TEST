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

public class NVO_BASELAND_MAIN
extends VoBase
implements Serializable,
Cloneable {
    private static final long serialVersionUID = 1128421427L;
    private DbString year = new DbString("year");
    private DbString city = new DbString("city");
    private DbString ofce = new DbString("ofce");
    private DbString dist = new DbString("dist");
    private DbString urban = new DbString("urban");
    private DbString baseseq = new DbString("baseseq");
    private DbString baseno = new DbString("baseno");
    private DbString baseno_old = new DbString("baseno_old");
    private DbString version = new DbString("version");
    private DbString land_position = new DbString("land_position");
    private DbString aa48 = new DbString("aa48");
    private DbString aa49 = new DbString("aa49");
    private DbString addr = new DbString("addr");
    private DbString buildname = new DbString("buildname");
    private DbInteger floor_up = new DbInteger("floor_up");
    private DbInteger floor_bf = new DbInteger("floor_bf");
    private DbDouble aa10 = new DbDouble("aa10");
    private DbString landuse = new DbString("landuse");
    private DbDouble cov_ratio = new DbDouble("cov_ratio");
    private DbDouble are_ratio = new DbDouble("are_ratio");
    private DbString shape = new DbString("shape");
    private DbDouble width = new DbDouble("width");
    private DbDouble deep = new DbDouble("deep");
    private DbString street_rel = new DbString("street_rel");
    private DbString street = new DbString("street");
    private DbString same_range = new DbString("same_range");
    private DbString roadwidth = new DbString("roadwidth");
    private DbString slop = new DbString("slop");
    private DbInteger aa16 = new DbInteger("aa16");
    private DbString land_scene = new DbString("land_scene");
    private DbString trad_type = new DbString("trad_type");
    private DbString price_date = new DbString("price_date");
    private DbInteger pprice = new DbInteger("pprice");
    private DbDouble pprice_ratio = new DbDouble("pprice_ratio");
    private DbInteger rprice = new DbInteger("rprice");
    private DbDouble rprice_ratio = new DbDouble("rprice_ratio");
    private DbInteger cprice = new DbInteger("cprice");
    private DbDouble cprice_ratio = new DbDouble("cprice_ratio");
    private DbLong base_pricem = new DbLong("base_pricem");
    private DbLong base_pricep = new DbLong("base_pricep");
    private DbLong base_price_pre = new DbLong("base_price_pre");
    private DbString reason = new DbString("reason");
    private DbString attachs = new DbString("attachs");
    private DbString opinions = new DbString("opinions");
    private DbString notes = new DbString("notes");
    private DbString fill_date = new DbString("fill_date");
    private DbString userid = new DbString("userid");
    private DbString creator = new DbString("creator");
    private DbDouble x = new DbDouble("x");
    private DbDouble y = new DbDouble("y");
    private DbString geom = new DbString("geom");
    private String attach1 = "";
    private String attach2 = "";
    private String attach3 = "";
    private String attach4 = "";
    private String attach5 = "";
    private String attach6 = "";
    private double aph_sell = 0.0;
    private double ahp_rent = 0.0;
    private double ahp_develop = 0.0;
    private double aa10_ping = 0.0;
    private String as302 = "";
    private String userName = "";
    private String versionName = "";

    public NVO_BASELAND_MAIN() {
        this.tableName = "baseland_main";
        super.setFieldCount(51);
        this.elems = new DbElement[51];
        this.elems[0] = this.year;
        this.elems[1] = this.city;
        this.elems[2] = this.ofce;
        this.elems[3] = this.dist;
        this.elems[4] = this.urban;
        this.elems[5] = this.baseseq;
        this.elems[6] = this.baseno;
        this.elems[7] = this.baseno_old;
        this.elems[8] = this.version;
        this.elems[9] = this.land_position;
        this.elems[10] = this.aa48;
        this.elems[11] = this.aa49;
        this.elems[12] = this.addr;
        this.elems[13] = this.buildname;
        this.elems[14] = this.floor_up;
        this.elems[15] = this.floor_bf;
        this.elems[16] = this.aa10;
        this.elems[17] = this.landuse;
        this.elems[18] = this.cov_ratio;
        this.elems[19] = this.are_ratio;
        this.elems[20] = this.shape;
        this.elems[21] = this.width;
        this.elems[22] = this.deep;
        this.elems[23] = this.street_rel;
        this.elems[24] = this.street;
        this.elems[25] = this.same_range;
        this.elems[26] = this.roadwidth;
        this.elems[27] = this.slop;
        this.elems[28] = this.aa16;
        this.elems[29] = this.land_scene;
        this.elems[30] = this.trad_type;
        this.elems[31] = this.price_date;
        this.elems[32] = this.pprice;
        this.elems[33] = this.pprice_ratio;
        this.elems[34] = this.rprice;
        this.elems[35] = this.rprice_ratio;
        this.elems[36] = this.cprice;
        this.elems[37] = this.cprice_ratio;
        this.elems[38] = this.base_pricem;
        this.elems[39] = this.base_pricep;
        this.elems[40] = this.base_price_pre;
        this.elems[41] = this.reason;
        this.elems[42] = this.attachs;
        this.elems[43] = this.opinions;
        this.elems[44] = this.notes;
        this.elems[45] = this.fill_date;
        this.elems[46] = this.userid;
        this.elems[47] = this.creator;
        this.elems[48] = this.x;
        this.elems[49] = this.y;
        this.elems[50] = this.geom;
        this.year.setPkFlag(true);
        this.baseno.setPkFlag(true);
    }

    public double getAa10_ping() {
        return this.aa10_ping;
    }

    public void setAa10_ping(double d) {
        this.aa10_ping = d;
    }

    public String getAs302() {
        return this.as302;
    }

    public void setAs302(String string) {
        this.as302 = string;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setVersionName(String string) {
        this.versionName = string;
    }

    public String getVersionName() {
        return this.versionName;
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

    public String getCity() {
        return this.city.getValue();
    }

    public String getOfce() {
        return this.ofce.getValue();
    }

    public String getDist() {
        return this.dist.getValue();
    }

    public String getUrban() {
        return this.urban.getValue();
    }

    public String getBaseseq() {
        return this.baseseq.getValue();
    }

    public String getBaseno() {
        return this.baseno.getValue();
    }

    public String getBaseno_old() {
        return this.baseno_old.getValue();
    }

    public String getVersion() {
        return this.version.getValue();
    }

    public String getLand_position() {
        return this.land_position.getValue();
    }

    public String getAa48() {
        return this.aa48.getValue();
    }

    public String getAa49() {
        return this.aa49.getValue();
    }

    public String getAddr() {
        return this.addr.getValue();
    }

    public String getBuildname() {
        return this.buildname.getValue();
    }

    public int getFloor_up() {
        return this.floor_up.getValue();
    }

    public int getFloor_bf() {
        return this.floor_bf.getValue();
    }

    public double getAa10() {
        return this.aa10.getValue();
    }

    public String getLanduse() {
        return this.landuse.getValue();
    }

    public double getCov_ratio() {
        return this.cov_ratio.getValue();
    }

    public double getAre_ratio() {
        return this.are_ratio.getValue();
    }

    public String getShape() {
        return this.shape.getValue();
    }

    public double getWidth() {
        return this.width.getValue();
    }

    public double getDeep() {
        return this.deep.getValue();
    }

    public String getStreet_rel() {
        return this.street_rel.getValue();
    }

    public String getStreet() {
        return this.street.getValue();
    }

    public String getSame_range() {
        return this.same_range.getValue();
    }

    public String getRoadwidth() {
        return this.roadwidth.getValue();
    }

    public String getSlop() {
        return this.slop.getValue();
    }

    public int getAa16() {
        return this.aa16.getValue();
    }

    public String getLand_scene() {
        return this.land_scene.getValue();
    }

    public String getTrad_type() {
        return this.trad_type.getValue();
    }

    public String getPrice_date() {
        return this.price_date.getValue();
    }

    public int getPprice() {
        return this.pprice.getValue();
    }

    public double getPprice_ratio() {
        return this.pprice_ratio.getValue();
    }

    public int getRprice() {
        return this.rprice.getValue();
    }

    public double getRprice_ratio() {
        return this.rprice_ratio.getValue();
    }

    public int getCprice() {
        return this.cprice.getValue();
    }

    public double getCprice_ratio() {
        return this.cprice_ratio.getValue();
    }

    public long getBase_pricem() {
        return this.base_pricem.getValue();
    }

    public long getBase_pricep() {
        return this.base_pricep.getValue();
    }

    public long getBase_price_pre() {
        return this.base_price_pre.getValue();
    }

    public String getReason() {
        return this.reason.getValue();
    }

    public String getAttachs() {
        return this.attachs.getValue();
    }

    public String getOpinions() {
        return this.opinions.getValue();
    }

    public String getNotes() {
        return this.notes.getValue();
    }

    public String getFill_date() {
        return this.fill_date.getValue();
    }

    public String getUserid() {
        return this.userid.getValue();
    }

    public String getCreator() {
        return this.creator.getValue();
    }

    public double getX() {
        return this.x.getValue();
    }

    public double getY() {
        return this.y.getValue();
    }

    public String getGeom() {
        return this.geom.getValue();
    }

    public String getAttach1() {
        return this.attach1;
    }

    public String getAttach2() {
        return this.attach2;
    }

    public String getAttach3() {
        return this.attach3;
    }

    public String getAttach4() {
        return this.attach4;
    }

    public String getAttach5() {
        return this.attach5;
    }

    public String getAttach6() {
        return this.attach6;
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

    public void setUrban(String string) {
        this.urban.setValue(string);
    }

    public void setBaseseq(String string) {
        this.baseseq.setValue(string);
    }

    public void setBaseno(String string) {
        this.baseno.setValue(string);
    }

    public void setBaseno_old(String string) {
        this.baseno_old.setValue(string);
    }

    public void setVersion(String string) {
        this.version.setValue(string);
    }

    public void setLand_position(String string) {
        this.land_position.setValue(string);
    }

    public void setAa48(String string) {
        this.aa48.setValue(string);
    }

    public void setAa49(String string) {
        this.aa49.setValue(string);
    }

    public void setAddr(String string) {
        this.addr.setValue(string);
    }

    public void setBuildname(String string) {
        this.buildname.setValue(string);
    }

    public void setFloor_up(int n) {
        this.floor_up.setValue(n);
    }

    public void setFloor_bf(int n) {
        this.floor_bf.setValue(n);
    }

    public void setAa10(double d) {
        this.aa10.setValue(d);
    }

    public void setLanduse(String string) {
        this.landuse.setValue(string);
    }

    public void setCov_ratio(double d) {
        this.cov_ratio.setValue(d);
    }

    public void setAre_ratio(double d) {
        this.are_ratio.setValue(d);
    }

    public void setShape(String string) {
        this.shape.setValue(string);
    }

    public void setWidth(double d) {
        this.width.setValue(d);
    }

    public void setDeep(double d) {
        this.deep.setValue(d);
    }

    public void setStreet_rel(String string) {
        this.street_rel.setValue(string);
    }

    public void setStreet(String string) {
        this.street.setValue(string);
    }

    public void setSame_range(String string) {
        this.same_range.setValue(string);
    }

    public void setRoadwidth(String string) {
        this.roadwidth.setValue(string);
    }

    public void setSlop(String string) {
        this.slop.setValue(string);
    }

    public void setAa16(int n) {
        this.aa16.setValue(n);
    }

    public void setLand_scene(String string) {
        this.land_scene.setValue(string);
    }

    public void setTrad_type(String string) {
        this.trad_type.setValue(string);
    }

    public void setPrice_date(String string) {
        this.price_date.setValue(string);
    }

    public void setPprice(int n) {
        this.pprice.setValue(n);
    }

    public void setPprice_ratio(double d) {
        this.pprice_ratio.setValue(d);
    }

    public void setRprice(int n) {
        this.rprice.setValue(n);
    }

    public void setRprice_ratio(double d) {
        this.rprice_ratio.setValue(d);
    }

    public void setCprice(int n) {
        this.cprice.setValue(n);
    }

    public void setCprice_ratio(double d) {
        this.cprice_ratio.setValue(d);
    }

    public void setBase_pricem(long l) {
        this.base_pricem.setValue(l);
    }

    public void setBase_pricep(long l) {
        this.base_pricep.setValue(l);
    }

    public void setBase_price_pre(long l) {
        this.base_price_pre.setValue(l);
    }

    public void setReason(String string) {
        this.reason.setValue(string);
    }

    public void setAttachs(String string) {
        this.attachs.setValue(string);
    }

    public void setOpinions(String string) {
        this.opinions.setValue(string);
    }

    public void setNotes(String string) {
        this.notes.setValue(string);
    }

    public void setFill_date(String string) {
        this.fill_date.setValue(string);
    }

    public void setUserid(String string) {
        this.userid.setValue(string);
        this.userName = string;
    }

    public void setCreator(String string) {
        this.creator.setValue(string);
    }

    public void setX(double d) {
        this.x.setValue(d);
    }

    public void setY(double d) {
        this.y.setValue(d);
    }

    public void setGeom(String string) {
        this.geom.setValue(string);
    }

    public void setAttach1(String string) {
        this.attach1 = string;
    }

    public void setAttach2(String string) {
        this.attach2 = string;
    }

    public void setAttach3(String string) {
        this.attach3 = string;
    }

    public void setAttach4(String string) {
        this.attach4 = string;
    }

    public void setAttach5(String string) {
        this.attach5 = string;
    }

    public void setAttach6(String string) {
        this.attach6 = string;
    }

    @Override
    public Object clone() {
        NVO_BASELAND_MAIN nVO_BASELAND_MAIN = new NVO_BASELAND_MAIN();
        DbElement[] dbElementArray = new DbElement[this.elems.length];
        nVO_BASELAND_MAIN.year = (DbString)this.year.clone();
        nVO_BASELAND_MAIN.city = (DbString)this.city.clone();
        nVO_BASELAND_MAIN.ofce = (DbString)this.ofce.clone();
        nVO_BASELAND_MAIN.dist = (DbString)this.dist.clone();
        nVO_BASELAND_MAIN.urban = (DbString)this.urban.clone();
        nVO_BASELAND_MAIN.baseseq = (DbString)this.baseseq.clone();
        nVO_BASELAND_MAIN.baseno = (DbString)this.baseno.clone();
        nVO_BASELAND_MAIN.baseno_old = (DbString)this.baseno_old.clone();
        nVO_BASELAND_MAIN.version = (DbString)this.version.clone();
        nVO_BASELAND_MAIN.land_position = (DbString)this.land_position.clone();
        nVO_BASELAND_MAIN.aa48 = (DbString)this.aa48.clone();
        nVO_BASELAND_MAIN.aa49 = (DbString)this.aa49.clone();
        nVO_BASELAND_MAIN.addr = (DbString)this.addr.clone();
        nVO_BASELAND_MAIN.buildname = (DbString)this.buildname.clone();
        nVO_BASELAND_MAIN.floor_up = (DbInteger)this.floor_up.clone();
        nVO_BASELAND_MAIN.floor_bf = (DbInteger)this.floor_bf.clone();
        nVO_BASELAND_MAIN.aa10 = (DbDouble)this.aa10.clone();
        nVO_BASELAND_MAIN.landuse = (DbString)this.landuse.clone();
        nVO_BASELAND_MAIN.cov_ratio = (DbDouble)this.cov_ratio.clone();
        nVO_BASELAND_MAIN.are_ratio = (DbDouble)this.are_ratio.clone();
        nVO_BASELAND_MAIN.shape = (DbString)this.shape.clone();
        nVO_BASELAND_MAIN.width = (DbDouble)this.width.clone();
        nVO_BASELAND_MAIN.deep = (DbDouble)this.deep.clone();
        nVO_BASELAND_MAIN.street_rel = (DbString)this.street_rel.clone();
        nVO_BASELAND_MAIN.street = (DbString)this.street.clone();
        nVO_BASELAND_MAIN.same_range = (DbString)this.same_range.clone();
        nVO_BASELAND_MAIN.roadwidth = (DbString)this.roadwidth.clone();
        nVO_BASELAND_MAIN.slop = (DbString)this.slop.clone();
        nVO_BASELAND_MAIN.aa16 = (DbInteger)this.aa16.clone();
        nVO_BASELAND_MAIN.land_scene = (DbString)this.land_scene.clone();
        nVO_BASELAND_MAIN.trad_type = (DbString)this.trad_type.clone();
        nVO_BASELAND_MAIN.price_date = (DbString)this.price_date.clone();
        nVO_BASELAND_MAIN.pprice = (DbInteger)this.pprice.clone();
        nVO_BASELAND_MAIN.pprice_ratio = (DbDouble)this.pprice_ratio.clone();
        nVO_BASELAND_MAIN.rprice = (DbInteger)this.rprice.clone();
        nVO_BASELAND_MAIN.rprice_ratio = (DbDouble)this.rprice_ratio.clone();
        nVO_BASELAND_MAIN.cprice = (DbInteger)this.cprice.clone();
        nVO_BASELAND_MAIN.cprice_ratio = (DbDouble)this.cprice_ratio.clone();
        nVO_BASELAND_MAIN.base_pricem = (DbLong)this.base_pricem.clone();
        nVO_BASELAND_MAIN.base_pricep = (DbLong)this.base_pricep.clone();
        nVO_BASELAND_MAIN.base_price_pre = (DbLong)this.base_price_pre.clone();
        nVO_BASELAND_MAIN.reason = (DbString)this.reason.clone();
        nVO_BASELAND_MAIN.attachs = (DbString)this.attachs.clone();
        nVO_BASELAND_MAIN.opinions = (DbString)this.opinions.clone();
        nVO_BASELAND_MAIN.notes = (DbString)this.notes.clone();
        nVO_BASELAND_MAIN.fill_date = (DbString)this.fill_date.clone();
        nVO_BASELAND_MAIN.userid = (DbString)this.userid.clone();
        nVO_BASELAND_MAIN.creator = (DbString)this.creator.clone();
        nVO_BASELAND_MAIN.x = (DbDouble)this.x.clone();
        nVO_BASELAND_MAIN.y = (DbDouble)this.y.clone();
        nVO_BASELAND_MAIN.geom = (DbString)this.geom.clone();
        dbElementArray[0] = nVO_BASELAND_MAIN.year;
        dbElementArray[1] = nVO_BASELAND_MAIN.city;
        dbElementArray[2] = nVO_BASELAND_MAIN.ofce;
        dbElementArray[3] = nVO_BASELAND_MAIN.dist;
        dbElementArray[4] = nVO_BASELAND_MAIN.urban;
        dbElementArray[5] = nVO_BASELAND_MAIN.baseseq;
        dbElementArray[6] = nVO_BASELAND_MAIN.baseno;
        dbElementArray[7] = nVO_BASELAND_MAIN.baseno_old;
        dbElementArray[8] = nVO_BASELAND_MAIN.version;
        dbElementArray[9] = nVO_BASELAND_MAIN.land_position;
        dbElementArray[10] = nVO_BASELAND_MAIN.aa48;
        dbElementArray[11] = nVO_BASELAND_MAIN.aa49;
        dbElementArray[12] = nVO_BASELAND_MAIN.addr;
        dbElementArray[13] = nVO_BASELAND_MAIN.buildname;
        dbElementArray[14] = nVO_BASELAND_MAIN.floor_up;
        dbElementArray[15] = nVO_BASELAND_MAIN.floor_bf;
        dbElementArray[16] = nVO_BASELAND_MAIN.aa10;
        dbElementArray[17] = nVO_BASELAND_MAIN.landuse;
        dbElementArray[18] = nVO_BASELAND_MAIN.cov_ratio;
        dbElementArray[19] = nVO_BASELAND_MAIN.are_ratio;
        dbElementArray[20] = nVO_BASELAND_MAIN.shape;
        dbElementArray[21] = nVO_BASELAND_MAIN.width;
        dbElementArray[22] = nVO_BASELAND_MAIN.deep;
        dbElementArray[23] = nVO_BASELAND_MAIN.street_rel;
        dbElementArray[24] = nVO_BASELAND_MAIN.street;
        dbElementArray[25] = nVO_BASELAND_MAIN.same_range;
        dbElementArray[26] = nVO_BASELAND_MAIN.roadwidth;
        dbElementArray[27] = nVO_BASELAND_MAIN.slop;
        dbElementArray[28] = nVO_BASELAND_MAIN.aa16;
        dbElementArray[29] = nVO_BASELAND_MAIN.land_scene;
        dbElementArray[30] = nVO_BASELAND_MAIN.trad_type;
        dbElementArray[31] = nVO_BASELAND_MAIN.price_date;
        dbElementArray[32] = nVO_BASELAND_MAIN.pprice;
        dbElementArray[33] = nVO_BASELAND_MAIN.pprice_ratio;
        dbElementArray[34] = nVO_BASELAND_MAIN.rprice;
        dbElementArray[35] = nVO_BASELAND_MAIN.rprice_ratio;
        dbElementArray[36] = nVO_BASELAND_MAIN.cprice;
        dbElementArray[37] = nVO_BASELAND_MAIN.cprice_ratio;
        dbElementArray[38] = nVO_BASELAND_MAIN.base_pricem;
        dbElementArray[39] = nVO_BASELAND_MAIN.base_pricep;
        dbElementArray[40] = nVO_BASELAND_MAIN.base_price_pre;
        dbElementArray[41] = nVO_BASELAND_MAIN.reason;
        dbElementArray[42] = nVO_BASELAND_MAIN.attachs;
        dbElementArray[43] = nVO_BASELAND_MAIN.opinions;
        dbElementArray[44] = nVO_BASELAND_MAIN.notes;
        dbElementArray[45] = nVO_BASELAND_MAIN.fill_date;
        dbElementArray[46] = nVO_BASELAND_MAIN.userid;
        dbElementArray[47] = nVO_BASELAND_MAIN.creator;
        dbElementArray[48] = nVO_BASELAND_MAIN.x;
        dbElementArray[49] = nVO_BASELAND_MAIN.y;
        dbElementArray[50] = nVO_BASELAND_MAIN.geom;
        nVO_BASELAND_MAIN.elems = dbElementArray;
        nVO_BASELAND_MAIN.fieldCount = this.fieldCount;
        nVO_BASELAND_MAIN.orderString = this.orderString;
        nVO_BASELAND_MAIN.tableName = this.tableName;
        return nVO_BASELAND_MAIN;
    }

    public double getAph_sell() {
        return this.aph_sell;
    }

    public void setAph_sell(double d) {
        this.aph_sell = d;
    }

    public double getAhp_rent() {
        return this.ahp_rent;
    }

    public void setAhp_rent(double d) {
        this.ahp_rent = d;
    }

    public double getAhp_develop() {
        return this.ahp_develop;
    }

    public void setAhp_develop(double d) {
        this.ahp_develop = d;
    }
}

