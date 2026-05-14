/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.dataaccess.nvo;

import com.wfusion.dataaccess.vo.DbDouble;
import com.wfusion.dataaccess.vo.DbElement;
import com.wfusion.dataaccess.vo.DbInteger;
import com.wfusion.dataaccess.vo.DbString;
import com.wfusion.dataaccess.vo.VoBase;
import com.wfusion.util.BigDecimalUtil;
import com.wfusion.util.StringProcess;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class NVO_BASELAND_APPRAISALA3_SCORE
extends VoBase
implements Serializable,
Cloneable {
    private static final long serialVersionUID = -479607408L;
    private DbString year = new DbString("year");
    private DbString baseno = new DbString("baseno");
    private DbString city = new DbString("city");
    private DbString ofce = new DbString("ofce");
    private DbString dist = new DbString("dist");
    private DbString as_type = new DbString("as_type");
    private DbString as301 = new DbString("as301");
    private DbString as302 = new DbString("as302");
    private DbString as303 = new DbString("as303");
    private DbString as307_nm = new DbString("as307_nm", "\u4e00\u822c\u6b63\u5e38\u60c5\u6cc1");
    private DbDouble as307_dv = new DbDouble("as307_dv");
    private DbDouble as308_dv = new DbDouble("as308_dv");
    private DbString as315_nm = new DbString("as315_nm");
    private DbDouble as315_ds = new DbDouble("as315_ds");
    private DbString as315_lv = new DbString("as315_lv");
    private DbDouble as315_dv = new DbDouble("as315_dv");
    private DbString as316_nm = new DbString("as316_nm");
    private DbString as316_lv = new DbString("as316_lv");
    private DbDouble as316_dv = new DbDouble("as316_dv");
    private DbString as317_nm = new DbString("as317_nm");
    private DbString as317_lv = new DbString("as317_lv");
    private DbDouble as317_dv = new DbDouble("as317_dv");
    private DbString as318_nm = new DbString("as318_nm");
    private DbString as318_lv = new DbString("as318_lv");
    private DbDouble as318_dv = new DbDouble("as318_dv");
    private DbString as319_nm = new DbString("as319_nm");
    private DbString as319_lv = new DbString("as319_lv");
    private DbDouble as319_dv = new DbDouble("as319_dv");
    private DbDouble as320 = new DbDouble("as320");
    private DbString as321_lv = new DbString("as321_lv");
    private DbDouble as321_dv = new DbDouble("as321_dv");
    private DbString as322_lv = new DbString("as322_lv");
    private DbDouble as322_dv = new DbDouble("as322_dv");
    private DbString as323_lv = new DbString("as323_lv");
    private DbDouble as323_dv = new DbDouble("as323_dv");
    private DbString as324_lv = new DbString("as324_lv");
    private DbDouble as324_dv = new DbDouble("as324_dv");
    private DbDouble as325 = new DbDouble("as325");
    private DbString as326_nm = new DbString("as326_nm");
    private DbString as326_lv = new DbString("as326_lv");
    private DbDouble as326_dv = new DbDouble("as326_dv");
    private DbString as327_nm = new DbString("as327_nm");
    private DbString as327_lv = new DbString("as327_lv");
    private DbDouble as327_dv = new DbDouble("as327_dv");
    private DbString as328_nm = new DbString("as328_nm");
    private DbString as328_lv = new DbString("as328_lv");
    private DbDouble as328_dv = new DbDouble("as328_dv");
    private DbString as329_nm = new DbString("as329_nm");
    private DbString as329_lv = new DbString("as329_lv");
    private DbDouble as329_dv = new DbDouble("as329_dv");
    private DbString as330_nm = new DbString("as330_nm");
    private DbString as330_lv = new DbString("as330_lv");
    private DbDouble as330_dv = new DbDouble("as330_dv");
    private DbDouble as331 = new DbDouble("as331");
    private DbString as313_lv = new DbString("as313_lv");
    private DbDouble as313_dv = new DbDouble("as313_dv");
    private DbString as314 = new DbString("as314");
    private DbString as314_lv = new DbString("as314_lv");
    private DbDouble as314_dv = new DbDouble("as314_dv");
    private DbDouble as332 = new DbDouble("as332");
    private DbString as339_lv = new DbString("as339_lv");
    private DbDouble as339_dv = new DbDouble("as339_dv");
    private DbDouble as340_ds = new DbDouble("as340_ds");
    private DbString as340_lv = new DbString("as340_lv");
    private DbDouble as340_dv = new DbDouble("as340_dv");
    private DbDouble as341_ds = new DbDouble("as341_ds");
    private DbString as341_lv = new DbString("as341_lv");
    private DbDouble as341_dv = new DbDouble("as341_dv");
    private DbString as342_nm = new DbString("as342_nm");
    private DbString as342_lv = new DbString("as342_lv");
    private DbDouble as342_dv = new DbDouble("as342_dv");
    private DbString as343_nm = new DbString("as343_nm");
    private DbString as343_lv = new DbString("as343_lv");
    private DbDouble as343_dv = new DbDouble("as343_dv");
    private DbDouble as344 = new DbDouble("as344");
    private DbString as345_nm = new DbString("as345_nm");
    private DbDouble as345_ds = new DbDouble("as345_ds");
    private DbString as345_lv = new DbString("as345_lv");
    private DbDouble as345_dv = new DbDouble("as345_dv");
    private DbString as346_lv = new DbString("as346_lv");
    private DbDouble as346_dv = new DbDouble("as346_dv");
    private DbString as347_nm = new DbString("as347_nm");
    private DbString as347_lv = new DbString("as347_lv");
    private DbDouble as347_dv = new DbDouble("as347_dv");
    private DbString as348 = new DbString("as348");
    private DbString as348_nm = new DbString("as348_nm");
    private DbString as348_lv = new DbString("as348_lv");
    private DbDouble as348_dv = new DbDouble("as348_dv");
    private DbDouble as349 = new DbDouble("as349");
    private DbString as350_nm = new DbString("as350_nm");
    private DbInteger as350_ds = new DbInteger("as350_ds");
    private DbString as350_lv = new DbString("as350_lv");
    private DbDouble as350_dv = new DbDouble("as350_dv");
    private DbString as351_nm = new DbString("as351_nm");
    private DbInteger as351_ds = new DbInteger("as351_ds");
    private DbString as351_lv = new DbString("as351_lv");
    private DbDouble as351_dv = new DbDouble("as351_dv");
    private DbString as352_nm = new DbString("as352_nm");
    private DbInteger as352_ds = new DbInteger("as352_ds");
    private DbString as352_lv = new DbString("as352_lv");
    private DbDouble as352_dv = new DbDouble("as352_dv");
    private DbString as353_nm = new DbString("as353_nm");
    private DbInteger as353_ds = new DbInteger("as353_ds");
    private DbString as353_lv = new DbString("as353_lv");
    private DbDouble as353_dv = new DbDouble("as353_dv");
    private DbString as354_nm = new DbString("as354_nm");
    private DbInteger as354_ds = new DbInteger("as354_ds");
    private DbString as354_lv = new DbString("as354_lv");
    private DbDouble as354_dv = new DbDouble("as354_dv");
    private DbString as355_lv = new DbString("as355_lv");
    private DbDouble as355_dv = new DbDouble("as355_dv");
    private DbString as356 = new DbString("as356");
    private DbString as356_nm = new DbString("as356_nm");
    private DbString as356_lv = new DbString("as356_lv");
    private DbDouble as356_dv = new DbDouble("as356_dv");
    private DbDouble as357 = new DbDouble("as357");
    private DbString as358_lv = new DbString("as358_lv");
    private DbDouble as358_dv = new DbDouble("as358_dv");
    private DbString as359_lv = new DbString("as359_lv");
    private DbDouble as359_dv = new DbDouble("as359_dv");
    private DbString as360_nm = new DbString("as360_nm");
    private DbString as360_lv = new DbString("as360_lv");
    private DbDouble as360_dv = new DbDouble("as360_dv");
    private DbString as361_lv = new DbString("as361_lv");
    private DbDouble as361_dv = new DbDouble("as361_dv");
    private DbString as362 = new DbString("as362");
    private DbString as362_nm = new DbString("as362_nm");
    private DbString as362_lv = new DbString("as362_lv");
    private DbDouble as362_dv = new DbDouble("as362_dv");
    private DbDouble as363 = new DbDouble("as363");
    private DbString as364_nm = new DbString("as364_nm");
    private DbString as364_lv = new DbString("as364_lv");
    private DbDouble as364_dv = new DbDouble("as364_dv");
    private DbDouble as365_ds = new DbDouble("as365_ds");
    private DbString as365_lv = new DbString("as365_lv");
    private DbDouble as365_dv = new DbDouble("as365_dv");
    private DbDouble as366_ds = new DbDouble("as366_ds");
    private DbString as366_lv = new DbString("as366_lv");
    private DbDouble as366_dv = new DbDouble("as366_dv");
    private DbDouble as367_ds = new DbDouble("as367_ds");
    private DbString as367_lv = new DbString("as367_lv");
    private DbDouble as367_dv = new DbDouble("as367_dv");
    private DbString as368_nm = new DbString("as368_nm");
    private DbString as368_lv = new DbString("as368_lv");
    private DbDouble as368_dv = new DbDouble("as368_dv");
    private DbString as369 = new DbString("as369");
    private DbString as369_nm = new DbString("as369_nm");
    private DbString as369_lv = new DbString("as369_lv");
    private DbDouble as369_dv = new DbDouble("as369_dv");
    private DbDouble as370 = new DbDouble("as370");
    private DbDouble as371 = new DbDouble("as371");
    private DbDouble as372 = new DbDouble("as372");
    private DbInteger as373 = new DbInteger("as373");
    private DbString as374_lv = new DbString("as374_lv");
    private DbDouble as374_dv = new DbDouble("as374_dv");
    private DbString as375_lv = new DbString("as375_lv");
    private DbDouble as375_dv = new DbDouble("as375_dv");
    private DbString as376_lv = new DbString("as376_lv");
    private DbDouble as376_dv = new DbDouble("as376_dv");
    private DbString as377 = new DbString("as377");
    private DbString as377_nm = new DbString("as377_nm");
    private DbString as377_lv = new DbString("as377_lv");
    private DbDouble as377_dv = new DbDouble("as377_dv");
    private DbString as378_lv = new DbString("as378_lv");
    private DbDouble as378_dv = new DbDouble("as378_dv");
    private DbString as379 = new DbString("as379");
    private DbString as379_nm = new DbString("as379_nm");
    private DbString as379_lv = new DbString("as379_lv");
    private DbDouble as379_dv = new DbDouble("as379_dv");
    private DbString as380_nm = new DbString("as380_nm");
    private DbString as380_lv = new DbString("as380_lv");
    private DbInteger as380_ds = new DbInteger("as380_ds");
    private DbDouble as380_dv = new DbDouble("as380_dv");
    private DbString as381_lv = new DbString("as381_lv");
    private DbDouble as381_dv = new DbDouble("as381_dv");
    private DbString as382_lv = new DbString("as382_lv");
    private DbDouble as382_dv = new DbDouble("as382_dv");
    private DbString as383_nm = new DbString("as383_nm");
    private DbString as383_lv = new DbString("as383_lv");
    private DbInteger as383_ds = new DbInteger("as383_ds");
    private DbDouble as383_dv = new DbDouble("as383_dv");
    private DbString as384_nm = new DbString("as384_nm");
    private DbString as384_lv = new DbString("as384_lv");
    private DbInteger as384_ds = new DbInteger("as384_ds");
    private DbDouble as384_dv = new DbDouble("as384_dv");
    private DbString as385_nm = new DbString("as385_nm");
    private DbString as385_lv = new DbString("as385_lv");
    private DbInteger as385_ds = new DbInteger("as385_ds");
    private DbDouble as385_dv = new DbDouble("as385_dv");
    private DbString as386_lv = new DbString("as386_lv");
    private DbDouble as386_dv = new DbDouble("as386_dv");
    private DbString as387_lv = new DbString("as387_lv");
    private DbDouble as387_dv = new DbDouble("as387_dv");
    private DbString as388_lv = new DbString("as388_lv");
    private DbDouble as388_dv = new DbDouble("as388_dv");
    private DbString as389_lv = new DbString("as389_lv");
    private DbDouble as389_dv = new DbDouble("as389_dv");
    private DbInteger price_type = new DbInteger("price_type");
    private DbDouble month_near = new DbDouble("month_near");
    private DbInteger area_near = new DbInteger("area_near");
    private DbDouble abs_rate = new DbDouble("abs_rate");
    private DbInteger diff_cnt = new DbInteger("diff_cnt");
    private String as302_nm = "";
    private String as303_nm = "";
    private long as304 = 0L;
    private double as305 = 0.0;
    private long as306 = 0L;
    private String as308 = "";
    private double as339 = 0.0;
    private String priceRateType = "";
    private boolean priceRateDataExist = false;

    public NVO_BASELAND_APPRAISALA3_SCORE() {
        this.tableName = "baseland_appraisala3_score";
        super.setFieldCount(202);
        this.elems = new DbElement[202];
        this.elems[0] = this.year;
        this.elems[1] = this.baseno;
        this.elems[2] = this.city;
        this.elems[3] = this.ofce;
        this.elems[4] = this.dist;
        this.elems[5] = this.as_type;
        this.elems[6] = this.as301;
        this.elems[7] = this.as302;
        this.elems[8] = this.as303;
        this.elems[9] = this.as307_nm;
        this.elems[10] = this.as307_dv;
        this.elems[11] = this.as308_dv;
        this.elems[12] = this.as315_nm;
        this.elems[13] = this.as315_ds;
        this.elems[14] = this.as315_lv;
        this.elems[15] = this.as315_dv;
        this.elems[16] = this.as316_nm;
        this.elems[17] = this.as316_lv;
        this.elems[18] = this.as316_dv;
        this.elems[19] = this.as317_nm;
        this.elems[20] = this.as317_lv;
        this.elems[21] = this.as317_dv;
        this.elems[22] = this.as318_nm;
        this.elems[23] = this.as318_lv;
        this.elems[24] = this.as318_dv;
        this.elems[25] = this.as319_nm;
        this.elems[26] = this.as319_lv;
        this.elems[27] = this.as319_dv;
        this.elems[28] = this.as320;
        this.elems[29] = this.as321_lv;
        this.elems[30] = this.as321_dv;
        this.elems[31] = this.as322_lv;
        this.elems[32] = this.as322_dv;
        this.elems[33] = this.as323_lv;
        this.elems[34] = this.as323_dv;
        this.elems[35] = this.as324_lv;
        this.elems[36] = this.as324_dv;
        this.elems[37] = this.as325;
        this.elems[38] = this.as326_nm;
        this.elems[39] = this.as326_lv;
        this.elems[40] = this.as326_dv;
        this.elems[41] = this.as327_nm;
        this.elems[42] = this.as327_lv;
        this.elems[43] = this.as327_dv;
        this.elems[44] = this.as328_nm;
        this.elems[45] = this.as328_lv;
        this.elems[46] = this.as328_dv;
        this.elems[47] = this.as329_nm;
        this.elems[48] = this.as329_lv;
        this.elems[49] = this.as329_dv;
        this.elems[50] = this.as330_nm;
        this.elems[51] = this.as330_lv;
        this.elems[52] = this.as330_dv;
        this.elems[53] = this.as331;
        this.elems[54] = this.as313_lv;
        this.elems[55] = this.as313_dv;
        this.elems[56] = this.as314;
        this.elems[57] = this.as314_lv;
        this.elems[58] = this.as314_dv;
        this.elems[59] = this.as332;
        this.elems[60] = this.as339_lv;
        this.elems[61] = this.as339_dv;
        this.elems[62] = this.as340_ds;
        this.elems[63] = this.as340_lv;
        this.elems[64] = this.as340_dv;
        this.elems[65] = this.as341_ds;
        this.elems[66] = this.as341_lv;
        this.elems[67] = this.as341_dv;
        this.elems[68] = this.as342_nm;
        this.elems[69] = this.as342_lv;
        this.elems[70] = this.as342_dv;
        this.elems[71] = this.as343_nm;
        this.elems[72] = this.as343_lv;
        this.elems[73] = this.as343_dv;
        this.elems[74] = this.as344;
        this.elems[75] = this.as345_nm;
        this.elems[76] = this.as345_ds;
        this.elems[77] = this.as345_lv;
        this.elems[78] = this.as345_dv;
        this.elems[79] = this.as346_lv;
        this.elems[80] = this.as346_dv;
        this.elems[81] = this.as347_nm;
        this.elems[82] = this.as347_lv;
        this.elems[83] = this.as347_dv;
        this.elems[84] = this.as348;
        this.elems[85] = this.as348_nm;
        this.elems[86] = this.as348_lv;
        this.elems[87] = this.as348_dv;
        this.elems[88] = this.as349;
        this.elems[89] = this.as350_nm;
        this.elems[90] = this.as350_ds;
        this.elems[91] = this.as350_lv;
        this.elems[92] = this.as350_dv;
        this.elems[93] = this.as351_nm;
        this.elems[94] = this.as351_ds;
        this.elems[95] = this.as351_lv;
        this.elems[96] = this.as351_dv;
        this.elems[97] = this.as352_nm;
        this.elems[98] = this.as352_ds;
        this.elems[99] = this.as352_lv;
        this.elems[100] = this.as352_dv;
        this.elems[101] = this.as353_nm;
        this.elems[102] = this.as353_ds;
        this.elems[103] = this.as353_lv;
        this.elems[104] = this.as353_dv;
        this.elems[105] = this.as354_nm;
        this.elems[106] = this.as354_ds;
        this.elems[107] = this.as354_lv;
        this.elems[108] = this.as354_dv;
        this.elems[109] = this.as355_lv;
        this.elems[110] = this.as355_dv;
        this.elems[111] = this.as356;
        this.elems[112] = this.as356_nm;
        this.elems[113] = this.as356_lv;
        this.elems[114] = this.as356_dv;
        this.elems[115] = this.as357;
        this.elems[116] = this.as358_lv;
        this.elems[117] = this.as358_dv;
        this.elems[118] = this.as359_lv;
        this.elems[119] = this.as359_dv;
        this.elems[120] = this.as360_nm;
        this.elems[121] = this.as360_lv;
        this.elems[122] = this.as360_dv;
        this.elems[123] = this.as361_lv;
        this.elems[124] = this.as361_dv;
        this.elems[125] = this.as362;
        this.elems[126] = this.as362_nm;
        this.elems[127] = this.as362_lv;
        this.elems[128] = this.as362_dv;
        this.elems[129] = this.as363;
        this.elems[130] = this.as364_nm;
        this.elems[131] = this.as364_lv;
        this.elems[132] = this.as364_dv;
        this.elems[133] = this.as365_ds;
        this.elems[134] = this.as365_lv;
        this.elems[135] = this.as365_dv;
        this.elems[136] = this.as366_ds;
        this.elems[137] = this.as366_lv;
        this.elems[138] = this.as366_dv;
        this.elems[139] = this.as367_ds;
        this.elems[140] = this.as367_lv;
        this.elems[141] = this.as367_dv;
        this.elems[142] = this.as368_nm;
        this.elems[143] = this.as368_lv;
        this.elems[144] = this.as368_dv;
        this.elems[145] = this.as369;
        this.elems[146] = this.as369_nm;
        this.elems[147] = this.as369_lv;
        this.elems[148] = this.as369_dv;
        this.elems[149] = this.as370;
        this.elems[150] = this.as371;
        this.elems[151] = this.as372;
        this.elems[152] = this.as373;
        this.elems[153] = this.as374_lv;
        this.elems[154] = this.as374_dv;
        this.elems[155] = this.as375_lv;
        this.elems[156] = this.as375_dv;
        this.elems[157] = this.as376_lv;
        this.elems[158] = this.as376_dv;
        this.elems[159] = this.as377;
        this.elems[160] = this.as377_nm;
        this.elems[161] = this.as377_lv;
        this.elems[162] = this.as377_dv;
        this.elems[163] = this.as378_lv;
        this.elems[164] = this.as378_dv;
        this.elems[165] = this.as379;
        this.elems[166] = this.as379_nm;
        this.elems[167] = this.as379_lv;
        this.elems[168] = this.as379_dv;
        this.elems[169] = this.as380_nm;
        this.elems[170] = this.as380_lv;
        this.elems[171] = this.as380_ds;
        this.elems[172] = this.as380_dv;
        this.elems[173] = this.as381_lv;
        this.elems[174] = this.as381_dv;
        this.elems[175] = this.as382_lv;
        this.elems[176] = this.as382_dv;
        this.elems[177] = this.as383_nm;
        this.elems[178] = this.as383_lv;
        this.elems[179] = this.as383_ds;
        this.elems[180] = this.as383_dv;
        this.elems[181] = this.as384_nm;
        this.elems[182] = this.as384_lv;
        this.elems[183] = this.as384_ds;
        this.elems[184] = this.as384_dv;
        this.elems[185] = this.as385_nm;
        this.elems[186] = this.as385_lv;
        this.elems[187] = this.as385_ds;
        this.elems[188] = this.as385_dv;
        this.elems[189] = this.as386_lv;
        this.elems[190] = this.as386_dv;
        this.elems[191] = this.as387_lv;
        this.elems[192] = this.as387_dv;
        this.elems[193] = this.as388_lv;
        this.elems[194] = this.as388_dv;
        this.elems[195] = this.as389_lv;
        this.elems[196] = this.as389_dv;
        this.elems[197] = this.price_type;
        this.elems[198] = this.month_near;
        this.elems[199] = this.area_near;
        this.elems[200] = this.abs_rate;
        this.elems[201] = this.diff_cnt;
        this.year.setPkFlag(true);
        this.baseno.setPkFlag(true);
        this.as_type.setPkFlag(true);
    }

    public String getAs302_nm() {
        return this.as302_nm;
    }

    public void setAs302_nm(String string) {
        this.as302_nm = string;
    }

    public String getAs303_nm() {
        return this.as303_nm;
    }

    public void setAs303_nm(String string) {
        this.as303_nm = string;
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

    public String getAs_type() {
        return this.as_type.getValue();
    }

    public String getAs301() {
        return this.as301.getValue();
    }

    public String getAs302() {
        return this.as302.getValue();
    }

    public String getAs303() {
        return this.as303.getValue();
    }

    public String getAs307_nm() {
        return this.as307_nm.getValue();
    }

    public double getAs307_dv() {
        return this.as307_dv.getValue();
    }

    public double getAs308_dv() {
        return this.as308_dv.getValue();
    }

    public String getAs315_nm() {
        return this.as315_nm.getValue();
    }

    public double getAs315_ds() {
        return this.as315_ds.getValue();
    }

    public String getAs315_lv() {
        return this.as315_lv.getValue();
    }

    public double getAs315_dv() {
        return this.as315_dv.getValue();
    }

    public String getAs316_nm() {
        return this.as316_nm.getValue();
    }

    public String getAs316_lv() {
        return this.as316_lv.getValue();
    }

    public String getAs316_print() {
        if (StringProcess.isEmpty(this.getAs316_nm())) {
            return this.getAs316_lv();
        }
        if (StringProcess.isEmpty(this.getAs316_lv())) {
            return this.getAs316_nm();
        }
        return this.getAs316_nm() + "(" + this.getAs316_lv() + ")";
    }

    public double getAs316_dv() {
        return this.as316_dv.getValue();
    }

    public String getAs317_nm() {
        return this.as317_nm.getValue();
    }

    public String getAs317_lv() {
        return this.as317_lv.getValue();
    }

    public double getAs317_dv() {
        return this.as317_dv.getValue();
    }

    public String getAs317_print() {
        if (StringProcess.isEmpty(this.getAs317_nm())) {
            return this.getAs317_lv();
        }
        if (StringProcess.isEmpty(this.getAs317_lv())) {
            return this.getAs317_nm();
        }
        return this.getAs317_nm() + "(" + this.getAs317_lv() + ")";
    }

    public String getAs318_nm() {
        return this.as318_nm.getValue();
    }

    public String getAs318_lv() {
        return this.as318_lv.getValue();
    }

    public double getAs318_dv() {
        return this.as318_dv.getValue();
    }

    public String getAs318_print() {
        if (StringProcess.isEmpty(this.getAs318_nm())) {
            return this.getAs318_lv();
        }
        if (StringProcess.isEmpty(this.getAs318_lv())) {
            return this.getAs318_nm();
        }
        return this.getAs318_nm() + "(" + this.getAs318_lv() + ")";
    }

    public String getAs319_nm() {
        return this.as319_nm.getValue();
    }

    public String getAs319_lv() {
        return this.as319_lv.getValue();
    }

    public double getAs319_dv() {
        return this.as319_dv.getValue();
    }

    public String getAs319_print() {
        if (StringProcess.isEmpty(this.getAs319_nm())) {
            return this.getAs319_lv();
        }
        if (StringProcess.isEmpty(this.getAs319_lv())) {
            return this.getAs319_nm();
        }
        return this.getAs319_nm() + "(" + this.getAs319_lv() + ")";
    }

    public double getAs320() {
        return this.as320.getValue();
    }

    public String getAs321_lv() {
        return this.as321_lv.getValue();
    }

    public double getAs321_dv() {
        return this.as321_dv.getValue();
    }

    public String getAs322_lv() {
        return this.as322_lv.getValue();
    }

    public double getAs322_dv() {
        return this.as322_dv.getValue();
    }

    public String getAs323_lv() {
        return this.as323_lv.getValue();
    }

    public double getAs323_dv() {
        return this.as323_dv.getValue();
    }

    public String getAs324_lv() {
        return this.as324_lv.getValue();
    }

    public double getAs324_dv() {
        return this.as324_dv.getValue();
    }

    public double getAs325() {
        return this.as325.getValue();
    }

    public String getAs326_nm() {
        return this.as326_nm.getValue();
    }

    public String getAs326_lv() {
        return this.as326_lv.getValue();
    }

    public double getAs326_dv() {
        return this.as326_dv.getValue();
    }

    public String getAs326_print() {
        if (StringProcess.isEmpty(this.getAs326_nm())) {
            return this.getAs326_lv();
        }
        if (StringProcess.isEmpty(this.getAs326_lv())) {
            return this.getAs326_nm();
        }
        return this.getAs326_nm() + "(" + this.getAs326_lv() + ")";
    }

    public String getAs327_nm() {
        return this.as327_nm.getValue();
    }

    public String getAs327_lv() {
        return this.as327_lv.getValue();
    }

    public double getAs327_dv() {
        return this.as327_dv.getValue();
    }

    public String getAs327_print() {
        if (StringProcess.isEmpty(this.getAs327_nm())) {
            return this.getAs327_lv();
        }
        if (StringProcess.isEmpty(this.getAs327_lv())) {
            return this.getAs327_nm();
        }
        return this.getAs327_nm() + "(" + this.getAs327_lv() + ")";
    }

    public String getAs328_nm() {
        return this.as328_nm.getValue();
    }

    public String getAs328_lv() {
        return this.as328_lv.getValue();
    }

    public double getAs328_dv() {
        return this.as328_dv.getValue();
    }

    public String getAs328_print() {
        if (StringProcess.isEmpty(this.getAs328_nm())) {
            return this.getAs328_lv();
        }
        if (StringProcess.isEmpty(this.getAs328_lv())) {
            return this.getAs328_nm();
        }
        return this.getAs328_nm() + "(" + this.getAs328_lv() + ")";
    }

    public String getAs329_nm() {
        return this.as329_nm.getValue();
    }

    public String getAs329_lv() {
        return this.as329_lv.getValue();
    }

    public double getAs329_dv() {
        return this.as329_dv.getValue();
    }

    public String getAs329_print() {
        if (StringProcess.isEmpty(this.getAs329_nm())) {
            return this.getAs329_lv();
        }
        if (StringProcess.isEmpty(this.getAs329_lv())) {
            return this.getAs329_nm();
        }
        return this.getAs329_nm() + "(" + this.getAs329_lv() + ")";
    }

    public String getAs330_nm() {
        return this.as330_nm.getValue();
    }

    public String getAs330_lv() {
        return this.as330_lv.getValue();
    }

    public double getAs330_dv() {
        return this.as330_dv.getValue();
    }

    public String getAs330_print() {
        if (StringProcess.isEmpty(this.getAs330_nm())) {
            return this.getAs330_lv();
        }
        if (StringProcess.isEmpty(this.getAs330_lv())) {
            return this.getAs330_nm();
        }
        return this.getAs330_nm() + "(" + this.getAs330_lv() + ")";
    }

    public double getAs331() {
        return this.as331.getValue();
    }

    public String getAs313_lv() {
        return this.as313_lv.getValue();
    }

    public double getAs313_dv() {
        return this.as313_dv.getValue();
    }

    public String getAs314() {
        return this.as314.getValue();
    }

    public String getAs314_lv() {
        return this.as314_lv.getValue();
    }

    public double getAs314_dv() {
        return this.as314_dv.getValue();
    }

    public double getAs332() {
        return this.as332.getValue();
    }

    public String getAs339_lv() {
        return this.as339_lv.getValue();
    }

    public double getAs339_dv() {
        return this.as339_dv.getValue();
    }

    public double getAs340_ds() {
        return this.as340_ds.getValue();
    }

    public String getAs340_lv() {
        return this.as340_lv.getValue();
    }

    public double getAs340_dv() {
        return this.as340_dv.getValue();
    }

    public double getAs341_ds() {
        return this.as341_ds.getValue();
    }

    public String getAs341_lv() {
        return this.as341_lv.getValue();
    }

    public double getAs341_dv() {
        return this.as341_dv.getValue();
    }

    public String getAs342_nm() {
        return this.as342_nm.getValue();
    }

    public String getAs342_lv() {
        return this.as342_lv.getValue();
    }

    public double getAs342_dv() {
        return this.as342_dv.getValue();
    }

    public String getAs343_nm() {
        return this.as343_nm.getValue();
    }

    public String getAs343_lv() {
        return this.as343_lv.getValue();
    }

    public double getAs343_dv() {
        return this.as343_dv.getValue();
    }

    public double getAs344() {
        return this.as344.getValue();
    }

    public String getAs345_nm() {
        return this.as345_nm.getValue();
    }

    public double getAs345_ds() {
        return this.as345_ds.getValue();
    }

    public String getAs345_lv() {
        return this.as345_lv.getValue();
    }

    public double getAs345_dv() {
        return this.as345_dv.getValue();
    }

    public String getAs346_lv() {
        return this.as346_lv.getValue();
    }

    public double getAs346_dv() {
        return this.as346_dv.getValue();
    }

    public String getAs347_nm() {
        return this.as347_nm.getValue();
    }

    public String getAs347_lv() {
        return this.as347_lv.getValue();
    }

    public double getAs347_dv() {
        return this.as347_dv.getValue();
    }

    public String getAs348() {
        return this.as348.getValue();
    }

    public String getAs348_nm() {
        return this.as348_nm.getValue();
    }

    public String getAs348_lv() {
        return this.as348_lv.getValue();
    }

    public String getAs348_print() {
        if (StringProcess.isEmpty(this.getAs348_nm())) {
            return this.getAs348_lv();
        }
        if (StringProcess.isEmpty(this.getAs348_lv())) {
            return this.getAs348_nm();
        }
        return this.getAs348_nm() + "(" + this.getAs348_lv() + ")";
    }

    public double getAs348_dv() {
        return this.as348_dv.getValue();
    }

    public double getAs349() {
        return this.as349.getValue();
    }

    public String getAs350_nm() {
        return this.as350_nm.getValue();
    }

    public int getAs350_ds() {
        return this.as350_ds.getValue();
    }

    public String getAs350_lv() {
        return this.as350_lv.getValue();
    }

    public double getAs350_dv() {
        return this.as350_dv.getValue();
    }

    public String getAs351_nm() {
        return this.as351_nm.getValue();
    }

    public int getAs351_ds() {
        return this.as351_ds.getValue();
    }

    public String getAs351_lv() {
        return this.as351_lv.getValue();
    }

    public double getAs351_dv() {
        return this.as351_dv.getValue();
    }

    public String getAs352_nm() {
        return this.as352_nm.getValue();
    }

    public int getAs352_ds() {
        return this.as352_ds.getValue();
    }

    public String getAs352_lv() {
        return this.as352_lv.getValue();
    }

    public double getAs352_dv() {
        return this.as352_dv.getValue();
    }

    public String getAs353_nm() {
        return this.as353_nm.getValue();
    }

    public int getAs353_ds() {
        return this.as353_ds.getValue();
    }

    public String getAs353_lv() {
        return this.as353_lv.getValue();
    }

    public double getAs353_dv() {
        return this.as353_dv.getValue();
    }

    public String getAs354_nm() {
        return this.as354_nm.getValue();
    }

    public int getAs354_ds() {
        return this.as354_ds.getValue();
    }

    public String getAs354_lv() {
        return this.as354_lv.getValue();
    }

    public double getAs354_dv() {
        return this.as354_dv.getValue();
    }

    public String getAs355_lv() {
        return this.as355_lv.getValue();
    }

    public double getAs355_dv() {
        return this.as355_dv.getValue();
    }

    public String getAs356() {
        return this.as356.getValue();
    }

    public String getAs356_nm() {
        return this.as356_nm.getValue();
    }

    public String getAs356_lv() {
        return this.as356_lv.getValue();
    }

    public double getAs356_dv() {
        return this.as356_dv.getValue();
    }

    public String getAs356_print() {
        if (StringProcess.isEmpty(this.getAs356_nm())) {
            return this.getAs356_lv();
        }
        if (StringProcess.isEmpty(this.getAs356_lv())) {
            return this.getAs356_nm();
        }
        return this.getAs356_nm() + "(" + this.getAs356_lv() + ")";
    }

    public double getAs357() {
        return this.as357.getValue();
    }

    public String getAs358_lv() {
        return this.as358_lv.getValue();
    }

    public double getAs358_dv() {
        return this.as358_dv.getValue();
    }

    public String getAs359_lv() {
        return this.as359_lv.getValue();
    }

    public double getAs359_dv() {
        return this.as359_dv.getValue();
    }

    public String getAs360_nm() {
        return this.as360_nm.getValue();
    }

    public String getAs360_lv() {
        return this.as360_lv.getValue();
    }

    public double getAs360_dv() {
        return this.as360_dv.getValue();
    }

    public String getAs360_print() {
        if (StringProcess.isEmpty(this.getAs360_nm())) {
            return this.getAs360_lv();
        }
        if (StringProcess.isEmpty(this.getAs360_lv())) {
            return this.getAs360_nm();
        }
        return this.getAs360_nm() + "(" + this.getAs360_lv() + ")";
    }

    public String getAs361_lv() {
        return this.as361_lv.getValue();
    }

    public double getAs361_dv() {
        return this.as361_dv.getValue();
    }

    public String getAs362() {
        return this.as362.getValue();
    }

    public String getAs362_nm() {
        return this.as362_nm.getValue();
    }

    public String getAs362_lv() {
        return this.as362_lv.getValue();
    }

    public double getAs362_dv() {
        return this.as362_dv.getValue();
    }

    public String getAs362_print() {
        if (StringProcess.isEmpty(this.getAs362_nm())) {
            return this.getAs362_lv();
        }
        if (StringProcess.isEmpty(this.getAs362_lv())) {
            return this.getAs362_nm();
        }
        return this.getAs362_nm() + "(" + this.getAs362_lv() + ")";
    }

    public double getAs363() {
        return this.as363.getValue();
    }

    public String getAs364_nm() {
        return this.as364_nm.getValue();
    }

    public String getAs364_lv() {
        return this.as364_lv.getValue();
    }

    public double getAs364_dv() {
        return this.as364_dv.getValue();
    }

    public double getAs365_ds() {
        return this.as365_ds.getValue();
    }

    public String getAs365_lv() {
        return this.as365_lv.getValue();
    }

    public double getAs365_dv() {
        return this.as365_dv.getValue();
    }

    public double getAs366_ds() {
        return this.as366_ds.getValue();
    }

    public String getAs366_lv() {
        return this.as366_lv.getValue();
    }

    public double getAs366_dv() {
        return this.as366_dv.getValue();
    }

    public double getAs367_ds() {
        return this.as367_ds.getValue();
    }

    public String getAs367_lv() {
        return this.as367_lv.getValue();
    }

    public double getAs367_dv() {
        return this.as367_dv.getValue();
    }

    public String getAs368_nm() {
        return this.as368_nm.getValue();
    }

    public String getAs368_lv() {
        return this.as368_lv.getValue();
    }

    public double getAs368_dv() {
        return this.as368_dv.getValue();
    }

    public String getAs369() {
        return this.as369.getValue();
    }

    public String getAs369_nm() {
        return this.as369_nm.getValue();
    }

    public String getAs369_lv() {
        return this.as369_lv.getValue();
    }

    public double getAs369_dv() {
        return this.as369_dv.getValue();
    }

    public String getAs369_print() {
        if (StringProcess.isEmpty(this.getAs369_nm())) {
            return this.getAs369_lv();
        }
        if (StringProcess.isEmpty(this.getAs369_lv())) {
            return this.getAs369_nm();
        }
        return this.getAs369_nm() + "(" + this.getAs369_lv() + ")";
    }

    public double getAs370() {
        return this.as370.getValue();
    }

    public double getAs371() {
        return this.as371.getValue();
    }

    public double getAs372() {
        return this.as372.getValue();
    }

    public int getAs373() {
        this.setAs373(this.calTargetPrice());
        return this.as373.getValue();
    }

    public String getAs374_lv() {
        return this.as374_lv.getValue();
    }

    public double getAs374_dv() {
        return this.as374_dv.getValue();
    }

    public String getAs375_lv() {
        return this.as375_lv.getValue();
    }

    public double getAs375_dv() {
        return this.as375_dv.getValue();
    }

    public String getAs376_lv() {
        return this.as376_lv.getValue();
    }

    public double getAs376_dv() {
        return this.as376_dv.getValue();
    }

    public String getAs377() {
        return this.as377.getValue();
    }

    public String getAs377_nm() {
        return this.as377_nm.getValue();
    }

    public String getAs377_lv() {
        return this.as377_lv.getValue();
    }

    public double getAs377_dv() {
        return this.as377_dv.getValue();
    }

    public String getAs377_print() {
        if (StringProcess.isEmpty(this.getAs377_nm())) {
            return this.getAs377_lv();
        }
        if (StringProcess.isEmpty(this.getAs377_lv())) {
            return this.getAs377_nm();
        }
        return this.getAs377_nm() + "(" + this.getAs377_lv() + ")";
    }

    public String getAs378_lv() {
        return this.as378_lv.getValue();
    }

    public double getAs378_dv() {
        return this.as378_dv.getValue();
    }

    public String getAs379() {
        return this.as379.getValue();
    }

    public String getAs379_nm() {
        return this.as379_nm.getValue();
    }

    public String getAs379_lv() {
        return this.as379_lv.getValue();
    }

    public double getAs379_dv() {
        return this.as379_dv.getValue();
    }

    public String getAs379_print() {
        if (StringProcess.isEmpty(this.getAs379_nm())) {
            return this.getAs379_lv();
        }
        if (StringProcess.isEmpty(this.getAs379_lv())) {
            return this.getAs379_nm();
        }
        return this.getAs379_nm() + "(" + this.getAs379_lv() + ")";
    }

    public String getAs380_nm() {
        return this.as380_nm.getValue();
    }

    public String getAs380_lv() {
        return this.as380_lv.getValue();
    }

    public int getAs380_ds() {
        return this.as380_ds.getValue();
    }

    public double getAs380_dv() {
        return this.as380_dv.getValue();
    }

    public String getAs381_lv() {
        return this.as381_lv.getValue();
    }

    public double getAs381_dv() {
        return this.as381_dv.getValue();
    }

    public String getAs382_lv() {
        return this.as382_lv.getValue();
    }

    public double getAs382_dv() {
        return this.as382_dv.getValue();
    }

    public String getAs383_nm() {
        return this.as383_nm.getValue();
    }

    public String getAs383_lv() {
        return this.as383_lv.getValue();
    }

    public int getAs383_ds() {
        return this.as383_ds.getValue();
    }

    public double getAs383_dv() {
        return this.as383_dv.getValue();
    }

    public String getAs384_nm() {
        return this.as384_nm.getValue();
    }

    public String getAs384_lv() {
        return this.as384_lv.getValue();
    }

    public int getAs384_ds() {
        return this.as384_ds.getValue();
    }

    public double getAs384_dv() {
        return this.as384_dv.getValue();
    }

    public String getAs385_nm() {
        return this.as385_nm.getValue();
    }

    public String getAs385_lv() {
        return this.as385_lv.getValue();
    }

    public int getAs385_ds() {
        return this.as385_ds.getValue();
    }

    public double getAs385_dv() {
        return this.as385_dv.getValue();
    }

    public String getAs386_lv() {
        return this.as386_lv.getValue();
    }

    public double getAs386_dv() {
        return this.as386_dv.getValue();
    }

    public String getAs387_lv() {
        return this.as387_lv.getValue();
    }

    public double getAs387_dv() {
        return this.as387_dv.getValue();
    }

    public String getAs388_lv() {
        return this.as388_lv.getValue();
    }

    public double getAs388_dv() {
        return this.as388_dv.getValue();
    }

    public String getAs389_lv() {
        return this.as389_lv.getValue();
    }

    public double getAs389_dv() {
        return this.as389_dv.getValue();
    }

    public int getPrice_type() {
        return this.price_type.getValue();
    }

    public double getMonth_near() {
        return this.month_near.getValue();
    }

    public int getArea_near() {
        return this.area_near.getValue();
    }

    public double getAbs_rate() {
        return this.abs_rate.getValue();
    }

    public int getDiff_cnt() {
        return this.diff_cnt.getValue();
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

    public void setAs_type(String string) {
        this.as_type.setValue(string);
    }

    public void setAs301(String string) {
        this.as301.setValue(string);
    }

    public void setAs302(String string) {
        this.as302.setValue(string);
    }

    public void setAs303(String string) {
        this.as303.setValue(string);
    }

    public void setAs307_nm(String string) {
        this.as307_nm.setValue(string);
    }

    public void setAs307_dv(double d) {
        this.as307_dv.setValue(d);
    }

    public void setAs308_dv(double d) {
        this.as308_dv.setValue(d);
    }

    public void setAs315_nm(String string) {
        this.as315_nm.setValue(string);
    }

    public void setAs315_ds(double d) {
        this.as315_ds.setValue(d);
    }

    public void setAs315_lv(String string) {
        this.as315_lv.setValue(string);
    }

    public void setAs315_dv(double d) {
        this.as315_dv.setValue(d);
    }

    public void setAs316_nm(String string) {
        this.as316_nm.setValue(string);
    }

    public void setAs316_lv(String string) {
        this.as316_lv.setValue(string);
    }

    public void setAs316_dv(double d) {
        this.as316_dv.setValue(d);
    }

    public void setAs317_nm(String string) {
        this.as317_nm.setValue(string);
    }

    public void setAs317_lv(String string) {
        this.as317_lv.setValue(string);
    }

    public void setAs317_dv(double d) {
        this.as317_dv.setValue(d);
    }

    public void setAs318_nm(String string) {
        this.as318_nm.setValue(string);
    }

    public void setAs318_lv(String string) {
        this.as318_lv.setValue(string);
    }

    public void setAs318_dv(double d) {
        this.as318_dv.setValue(d);
    }

    public void setAs319_nm(String string) {
        this.as319_nm.setValue(string);
    }

    public void setAs319_lv(String string) {
        this.as319_lv.setValue(string);
    }

    public void setAs319_dv(double d) {
        this.as319_dv.setValue(d);
    }

    public void setAs320(double d) {
        this.as320.setValue(d);
    }

    public void setAs321_lv(String string) {
        this.as321_lv.setValue(string);
    }

    public void setAs321_dv(double d) {
        this.as321_dv.setValue(d);
    }

    public void setAs322_lv(String string) {
        this.as322_lv.setValue(string);
    }

    public void setAs322_dv(double d) {
        this.as322_dv.setValue(d);
    }

    public void setAs323_lv(String string) {
        this.as323_lv.setValue(string);
    }

    public void setAs323_dv(double d) {
        this.as323_dv.setValue(d);
    }

    public void setAs324_lv(String string) {
        this.as324_lv.setValue(string);
    }

    public void setAs324_dv(double d) {
        this.as324_dv.setValue(d);
    }

    public void setAs325(double d) {
        this.as325.setValue(d);
    }

    public void setAs326_nm(String string) {
        this.as326_nm.setValue(string);
    }

    public void setAs326_lv(String string) {
        this.as326_lv.setValue(string);
    }

    public void setAs326_dv(double d) {
        this.as326_dv.setValue(d);
    }

    public void setAs327_nm(String string) {
        this.as327_nm.setValue(string);
    }

    public void setAs327_lv(String string) {
        this.as327_lv.setValue(string);
    }

    public void setAs327_dv(double d) {
        this.as327_dv.setValue(d);
    }

    public void setAs328_nm(String string) {
        this.as328_nm.setValue(string);
    }

    public void setAs328_lv(String string) {
        this.as328_lv.setValue(string);
    }

    public void setAs328_dv(double d) {
        this.as328_dv.setValue(d);
    }

    public void setAs329_nm(String string) {
        this.as329_nm.setValue(string);
    }

    public void setAs329_lv(String string) {
        this.as329_lv.setValue(string);
    }

    public void setAs329_dv(double d) {
        this.as329_dv.setValue(d);
    }

    public void setAs330_nm(String string) {
        this.as330_nm.setValue(string);
    }

    public void setAs330_lv(String string) {
        this.as330_lv.setValue(string);
    }

    public void setAs330_dv(double d) {
        this.as330_dv.setValue(d);
    }

    public void setAs331(double d) {
        this.as331.setValue(d);
    }

    public void setAs313_lv(String string) {
        this.as313_lv.setValue(string);
    }

    public void setAs313_dv(double d) {
        this.as313_dv.setValue(d);
    }

    public void setAs314(String string) {
        this.as314.setValue(string);
    }

    public void setAs314_lv(String string) {
        this.as314_lv.setValue(string);
    }

    public void setAs314_dv(double d) {
        this.as314_dv.setValue(d);
    }

    public void setAs332(double d) {
        this.as332.setValue(d);
    }

    public void setAs339_lv(String string) {
        this.as339_lv.setValue(string);
    }

    public void setAs339_dv(double d) {
        this.as339_dv.setValue(d);
    }

    public void setAs340_ds(double d) {
        this.as340_ds.setValue(d);
    }

    public void setAs340_lv(String string) {
        this.as340_lv.setValue(string);
    }

    public void setAs340_dv(double d) {
        this.as340_dv.setValue(d);
    }

    public void setAs341_ds(double d) {
        this.as341_ds.setValue(d);
    }

    public void setAs341_lv(String string) {
        this.as341_lv.setValue(string);
    }

    public void setAs341_dv(double d) {
        this.as341_dv.setValue(d);
    }

    public void setAs342_nm(String string) {
        this.as342_nm.setValue(string);
    }

    public void setAs342_lv(String string) {
        this.as342_lv.setValue(string);
    }

    public void setAs342_dv(double d) {
        this.as342_dv.setValue(d);
    }

    public void setAs343_nm(String string) {
        this.as343_nm.setValue(string);
    }

    public void setAs343_lv(String string) {
        this.as343_lv.setValue(string);
    }

    public void setAs343_dv(double d) {
        this.as343_dv.setValue(d);
    }

    public void setAs344(double d) {
        this.as344.setValue(d);
    }

    public void setAs345_nm(String string) {
        this.as345_nm.setValue(string);
    }

    public void setAs345_ds(double d) {
        this.as345_ds.setValue(d);
    }

    public void setAs345_lv(String string) {
        this.as345_lv.setValue(string);
    }

    public void setAs345_dv(double d) {
        this.as345_dv.setValue(d);
    }

    public void setAs346_lv(String string) {
        this.as346_lv.setValue(string);
    }

    public void setAs346_dv(double d) {
        this.as346_dv.setValue(d);
    }

    public void setAs347_nm(String string) {
        this.as347_nm.setValue(string);
    }

    public void setAs347_lv(String string) {
        this.as347_lv.setValue(string);
    }

    public void setAs347_dv(double d) {
        this.as347_dv.setValue(d);
    }

    public void setAs348(String string) {
        this.as348.setValue(string);
    }

    public void setAs348_nm(String string) {
        this.as348_nm.setValue(string);
    }

    public void setAs348_lv(String string) {
        this.as348_lv.setValue(string);
    }

    public void setAs348_dv(double d) {
        this.as348_dv.setValue(d);
    }

    public void setAs349(double d) {
        this.as349.setValue(d);
    }

    public void setAs350_nm(String string) {
        this.as350_nm.setValue(string);
    }

    public void setAs350_ds(int n) {
        this.as350_ds.setValue(n);
    }

    public void setAs350_lv(String string) {
        this.as350_lv.setValue(string);
    }

    public void setAs350_dv(double d) {
        this.as350_dv.setValue(d);
    }

    public void setAs351_nm(String string) {
        this.as351_nm.setValue(string);
    }

    public void setAs351_ds(int n) {
        this.as351_ds.setValue(n);
    }

    public void setAs351_lv(String string) {
        this.as351_lv.setValue(string);
    }

    public void setAs351_dv(double d) {
        this.as351_dv.setValue(d);
    }

    public void setAs352_nm(String string) {
        this.as352_nm.setValue(string);
    }

    public void setAs352_ds(int n) {
        this.as352_ds.setValue(n);
    }

    public void setAs352_lv(String string) {
        this.as352_lv.setValue(string);
    }

    public void setAs352_dv(double d) {
        this.as352_dv.setValue(d);
    }

    public void setAs353_nm(String string) {
        this.as353_nm.setValue(string);
    }

    public void setAs353_ds(int n) {
        this.as353_ds.setValue(n);
    }

    public void setAs353_lv(String string) {
        this.as353_lv.setValue(string);
    }

    public void setAs353_dv(double d) {
        this.as353_dv.setValue(d);
    }

    public void setAs354_nm(String string) {
        this.as354_nm.setValue(string);
    }

    public void setAs354_ds(int n) {
        this.as354_ds.setValue(n);
    }

    public void setAs354_lv(String string) {
        this.as354_lv.setValue(string);
    }

    public void setAs354_dv(double d) {
        this.as354_dv.setValue(d);
    }

    public void setAs355_lv(String string) {
        this.as355_lv.setValue(string);
    }

    public void setAs355_dv(double d) {
        this.as355_dv.setValue(d);
    }

    public void setAs356(String string) {
        this.as356.setValue(string);
    }

    public void setAs356_nm(String string) {
        this.as356_nm.setValue(string);
    }

    public void setAs356_lv(String string) {
        this.as356_lv.setValue(string);
    }

    public void setAs356_dv(double d) {
        this.as356_dv.setValue(d);
    }

    public void setAs357(double d) {
        this.as357.setValue(d);
    }

    public void setAs358_lv(String string) {
        this.as358_lv.setValue(string);
    }

    public void setAs358_dv(double d) {
        this.as358_dv.setValue(d);
    }

    public void setAs359_lv(String string) {
        this.as359_lv.setValue(string);
    }

    public void setAs359_dv(double d) {
        this.as359_dv.setValue(d);
    }

    public void setAs360_nm(String string) {
        this.as360_nm.setValue(string);
    }

    public void setAs360_lv(String string) {
        this.as360_lv.setValue(string);
    }

    public void setAs360_dv(double d) {
        this.as360_dv.setValue(d);
    }

    public void setAs361_lv(String string) {
        this.as361_lv.setValue(string);
    }

    public void setAs361_dv(double d) {
        this.as361_dv.setValue(d);
    }

    public void setAs362(String string) {
        this.as362.setValue(string);
    }

    public void setAs362_nm(String string) {
        this.as362_nm.setValue(string);
    }

    public void setAs362_lv(String string) {
        this.as362_lv.setValue(string);
    }

    public void setAs362_dv(double d) {
        this.as362_dv.setValue(d);
    }

    public void setAs363(double d) {
        this.as363.setValue(d);
    }

    public void setAs364_nm(String string) {
        this.as364_nm.setValue(string);
    }

    public void setAs364_lv(String string) {
        this.as364_lv.setValue(string);
    }

    public void setAs364_dv(double d) {
        this.as364_dv.setValue(d);
    }

    public void setAs365_ds(double d) {
        this.as365_ds.setValue(d);
    }

    public void setAs365_lv(String string) {
        this.as365_lv.setValue(string);
    }

    public void setAs365_dv(double d) {
        this.as365_dv.setValue(d);
    }

    public void setAs366_ds(double d) {
        this.as366_ds.setValue(d);
    }

    public void setAs366_lv(String string) {
        this.as366_lv.setValue(string);
    }

    public void setAs366_dv(double d) {
        this.as366_dv.setValue(d);
    }

    public void setAs367_ds(double d) {
        this.as367_ds.setValue(d);
    }

    public void setAs367_lv(String string) {
        this.as367_lv.setValue(string);
    }

    public void setAs367_dv(double d) {
        this.as367_dv.setValue(d);
    }

    public void setAs368_nm(String string) {
        this.as368_nm.setValue(string);
    }

    public void setAs368_lv(String string) {
        this.as368_lv.setValue(string);
    }

    public void setAs368_dv(double d) {
        this.as368_dv.setValue(d);
    }

    public void setAs369(String string) {
        this.as369.setValue(string);
    }

    public void setAs369_nm(String string) {
        this.as369_nm.setValue(string);
    }

    public void setAs369_lv(String string) {
        this.as369_lv.setValue(string);
    }

    public void setAs369_dv(double d) {
        this.as369_dv.setValue(d);
    }

    public void setAs370(double d) {
        this.as370.setValue(d);
    }

    public void setAs371(double d) {
        this.as371.setValue(d);
    }

    public void setAs372(double d) {
        this.as372.setValue(d);
    }

    public void setAs373(int n) {
        this.as373.setValue(n);
    }

    public void setAs374_lv(String string) {
        this.as374_lv.setValue(string);
    }

    public void setAs374_dv(double d) {
        this.as374_dv.setValue(d);
    }

    public void setAs375_lv(String string) {
        this.as375_lv.setValue(string);
    }

    public void setAs375_dv(double d) {
        this.as375_dv.setValue(d);
    }

    public void setAs376_lv(String string) {
        this.as376_lv.setValue(string);
    }

    public void setAs376_dv(double d) {
        this.as376_dv.setValue(d);
    }

    public void setAs377(String string) {
        this.as377.setValue(string);
    }

    public void setAs377_nm(String string) {
        this.as377_nm.setValue(string);
    }

    public void setAs377_lv(String string) {
        this.as377_lv.setValue(string);
    }

    public void setAs377_dv(double d) {
        this.as377_dv.setValue(d);
    }

    public void setAs378_lv(String string) {
        this.as378_lv.setValue(string);
    }

    public void setAs378_dv(double d) {
        this.as378_dv.setValue(d);
    }

    public void setAs379(String string) {
        this.as379.setValue(string);
    }

    public void setAs379_nm(String string) {
        this.as379_nm.setValue(string);
    }

    public void setAs379_lv(String string) {
        this.as379_lv.setValue(string);
    }

    public void setAs379_dv(double d) {
        this.as379_dv.setValue(d);
    }

    public void setAs380_nm(String string) {
        this.as380_nm.setValue(string);
    }

    public void setAs380_lv(String string) {
        this.as380_lv.setValue(string);
    }

    public void setAs380_ds(int n) {
        this.as380_ds.setValue(n);
    }

    public void setAs380_dv(double d) {
        this.as380_dv.setValue(d);
    }

    public void setAs381_lv(String string) {
        this.as381_lv.setValue(string);
    }

    public void setAs381_dv(double d) {
        this.as381_dv.setValue(d);
    }

    public void setAs382_lv(String string) {
        this.as382_lv.setValue(string);
    }

    public void setAs382_dv(double d) {
        this.as382_dv.setValue(d);
    }

    public void setAs383_nm(String string) {
        this.as383_nm.setValue(string);
    }

    public void setAs383_lv(String string) {
        this.as383_lv.setValue(string);
    }

    public void setAs383_ds(int n) {
        this.as383_ds.setValue(n);
    }

    public void setAs383_dv(double d) {
        this.as383_dv.setValue(d);
    }

    public void setAs384_nm(String string) {
        this.as384_nm.setValue(string);
    }

    public void setAs384_lv(String string) {
        this.as384_lv.setValue(string);
    }

    public void setAs384_ds(int n) {
        this.as384_ds.setValue(n);
    }

    public void setAs384_dv(double d) {
        this.as384_dv.setValue(d);
    }

    public void setAs385_nm(String string) {
        this.as385_nm.setValue(string);
    }

    public void setAs385_lv(String string) {
        this.as385_lv.setValue(string);
    }

    public void setAs385_ds(int n) {
        this.as385_ds.setValue(n);
    }

    public void setAs385_dv(double d) {
        this.as385_dv.setValue(d);
    }

    public void setAs386_lv(String string) {
        this.as386_lv.setValue(string);
    }

    public void setAs386_dv(double d) {
        this.as386_dv.setValue(d);
    }

    public void setAs387_lv(String string) {
        this.as387_lv.setValue(string);
    }

    public void setAs387_dv(double d) {
        this.as387_dv.setValue(d);
    }

    public void setAs388_lv(String string) {
        this.as388_lv.setValue(string);
    }

    public void setAs388_dv(double d) {
        this.as388_dv.setValue(d);
    }

    public void setAs389_lv(String string) {
        this.as389_lv.setValue(string);
    }

    public void setAs389_dv(double d) {
        this.as389_dv.setValue(d);
    }

    public void setPrice_type(int n) {
        this.price_type.setValue(n);
    }

    public void setMonth_near(double d) {
        this.month_near.setValue(d);
    }

    public void setArea_near(int n) {
        this.area_near.setValue(n);
    }

    public void setAbs_rate(double d) {
        this.abs_rate.setValue(d);
    }

    public void setDiff_cnt(int n) {
        this.diff_cnt.setValue(n);
    }

    @Override
    public Object clone() {
        NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE = new NVO_BASELAND_APPRAISALA3_SCORE();
        DbElement[] dbElementArray = new DbElement[this.elems.length];
        nVO_BASELAND_APPRAISALA3_SCORE.year = (DbString)this.year.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.baseno = (DbString)this.baseno.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.city = (DbString)this.city.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.ofce = (DbString)this.ofce.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.dist = (DbString)this.dist.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as_type = (DbString)this.as_type.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as301 = (DbString)this.as301.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as302 = (DbString)this.as302.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as303 = (DbString)this.as303.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as307_nm = (DbString)this.as307_nm.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as307_dv = (DbDouble)this.as307_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as308_dv = (DbDouble)this.as308_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as315_nm = (DbString)this.as315_nm.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as315_ds = (DbDouble)this.as315_ds.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as315_lv = (DbString)this.as315_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as315_dv = (DbDouble)this.as315_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as316_nm = (DbString)this.as316_nm.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as316_lv = (DbString)this.as316_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as316_dv = (DbDouble)this.as316_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as317_nm = (DbString)this.as317_nm.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as317_lv = (DbString)this.as317_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as317_dv = (DbDouble)this.as317_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as318_nm = (DbString)this.as318_nm.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as318_lv = (DbString)this.as318_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as318_dv = (DbDouble)this.as318_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as319_nm = (DbString)this.as319_nm.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as319_lv = (DbString)this.as319_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as319_dv = (DbDouble)this.as319_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as320 = (DbDouble)this.as320.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as321_lv = (DbString)this.as321_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as321_dv = (DbDouble)this.as321_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as322_lv = (DbString)this.as322_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as322_dv = (DbDouble)this.as322_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as323_lv = (DbString)this.as323_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as323_dv = (DbDouble)this.as323_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as324_lv = (DbString)this.as324_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as324_dv = (DbDouble)this.as324_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as325 = (DbDouble)this.as325.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as326_nm = (DbString)this.as326_nm.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as326_lv = (DbString)this.as326_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as326_dv = (DbDouble)this.as326_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as327_nm = (DbString)this.as327_nm.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as327_lv = (DbString)this.as327_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as327_dv = (DbDouble)this.as327_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as328_nm = (DbString)this.as328_nm.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as328_lv = (DbString)this.as328_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as328_dv = (DbDouble)this.as328_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as329_nm = (DbString)this.as329_nm.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as329_lv = (DbString)this.as329_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as329_dv = (DbDouble)this.as329_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as330_nm = (DbString)this.as330_nm.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as330_lv = (DbString)this.as330_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as330_dv = (DbDouble)this.as330_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as331 = (DbDouble)this.as331.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as313_lv = (DbString)this.as313_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as313_dv = (DbDouble)this.as313_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as314 = (DbString)this.as314.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as314_lv = (DbString)this.as314_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as314_dv = (DbDouble)this.as314_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as332 = (DbDouble)this.as332.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as339_lv = (DbString)this.as339_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as339_dv = (DbDouble)this.as339_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as340_ds = (DbDouble)this.as340_ds.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as340_lv = (DbString)this.as340_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as340_dv = (DbDouble)this.as340_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as341_ds = (DbDouble)this.as341_ds.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as341_lv = (DbString)this.as341_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as341_dv = (DbDouble)this.as341_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as342_nm = (DbString)this.as342_nm.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as342_lv = (DbString)this.as342_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as342_dv = (DbDouble)this.as342_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as343_nm = (DbString)this.as343_nm.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as343_lv = (DbString)this.as343_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as343_dv = (DbDouble)this.as343_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as344 = (DbDouble)this.as344.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as345_nm = (DbString)this.as345_nm.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as345_ds = (DbDouble)this.as345_ds.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as345_lv = (DbString)this.as345_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as345_dv = (DbDouble)this.as345_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as346_lv = (DbString)this.as346_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as346_dv = (DbDouble)this.as346_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as347_nm = (DbString)this.as347_nm.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as347_lv = (DbString)this.as347_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as347_dv = (DbDouble)this.as347_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as348 = (DbString)this.as348.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as348_nm = (DbString)this.as348_nm.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as348_lv = (DbString)this.as348_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as348_dv = (DbDouble)this.as348_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as349 = (DbDouble)this.as349.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as350_nm = (DbString)this.as350_nm.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as350_ds = (DbInteger)this.as350_ds.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as350_lv = (DbString)this.as350_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as350_dv = (DbDouble)this.as350_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as351_nm = (DbString)this.as351_nm.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as351_ds = (DbInteger)this.as351_ds.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as351_lv = (DbString)this.as351_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as351_dv = (DbDouble)this.as351_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as352_nm = (DbString)this.as352_nm.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as352_ds = (DbInteger)this.as352_ds.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as352_lv = (DbString)this.as352_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as352_dv = (DbDouble)this.as352_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as353_nm = (DbString)this.as353_nm.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as353_ds = (DbInteger)this.as353_ds.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as353_lv = (DbString)this.as353_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as353_dv = (DbDouble)this.as353_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as354_nm = (DbString)this.as354_nm.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as354_ds = (DbInteger)this.as354_ds.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as354_lv = (DbString)this.as354_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as354_dv = (DbDouble)this.as354_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as355_lv = (DbString)this.as355_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as355_dv = (DbDouble)this.as355_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as356 = (DbString)this.as356.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as356_nm = (DbString)this.as356_nm.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as356_lv = (DbString)this.as356_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as356_dv = (DbDouble)this.as356_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as357 = (DbDouble)this.as357.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as358_lv = (DbString)this.as358_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as358_dv = (DbDouble)this.as358_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as359_lv = (DbString)this.as359_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as359_dv = (DbDouble)this.as359_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as360_nm = (DbString)this.as360_nm.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as360_lv = (DbString)this.as360_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as360_dv = (DbDouble)this.as360_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as361_lv = (DbString)this.as361_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as361_dv = (DbDouble)this.as361_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as362 = (DbString)this.as362.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as362_nm = (DbString)this.as362_nm.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as362_lv = (DbString)this.as362_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as362_dv = (DbDouble)this.as362_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as363 = (DbDouble)this.as363.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as364_nm = (DbString)this.as364_nm.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as364_lv = (DbString)this.as364_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as364_dv = (DbDouble)this.as364_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as365_ds = (DbDouble)this.as365_ds.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as365_lv = (DbString)this.as365_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as365_dv = (DbDouble)this.as365_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as366_ds = (DbDouble)this.as366_ds.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as366_lv = (DbString)this.as366_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as366_dv = (DbDouble)this.as366_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as367_ds = (DbDouble)this.as367_ds.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as367_lv = (DbString)this.as367_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as367_dv = (DbDouble)this.as367_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as368_nm = (DbString)this.as368_nm.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as368_lv = (DbString)this.as368_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as368_dv = (DbDouble)this.as368_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as369 = (DbString)this.as369.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as369_nm = (DbString)this.as369_nm.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as369_lv = (DbString)this.as369_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as369_dv = (DbDouble)this.as369_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as370 = (DbDouble)this.as370.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as371 = (DbDouble)this.as371.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as372 = (DbDouble)this.as372.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as373 = (DbInteger)this.as373.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as374_lv = (DbString)this.as374_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as374_dv = (DbDouble)this.as374_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as375_lv = (DbString)this.as375_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as375_dv = (DbDouble)this.as375_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as376_lv = (DbString)this.as376_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as376_dv = (DbDouble)this.as376_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as377 = (DbString)this.as377.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as377_nm = (DbString)this.as377_nm.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as377_lv = (DbString)this.as377_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as377_dv = (DbDouble)this.as377_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as378_lv = (DbString)this.as378_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as378_dv = (DbDouble)this.as378_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as379 = (DbString)this.as379.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as379_nm = (DbString)this.as379_nm.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as379_lv = (DbString)this.as379_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as379_dv = (DbDouble)this.as379_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as380_nm = (DbString)this.as380_nm.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as380_lv = (DbString)this.as380_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as380_ds = (DbInteger)this.as380_ds.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as380_dv = (DbDouble)this.as380_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as381_lv = (DbString)this.as381_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as381_dv = (DbDouble)this.as381_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as382_lv = (DbString)this.as382_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as382_dv = (DbDouble)this.as382_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as383_nm = (DbString)this.as383_nm.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as383_lv = (DbString)this.as383_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as383_ds = (DbInteger)this.as383_ds.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as383_dv = (DbDouble)this.as383_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as384_nm = (DbString)this.as384_nm.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as384_lv = (DbString)this.as384_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as384_ds = (DbInteger)this.as384_ds.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as384_dv = (DbDouble)this.as384_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as385_nm = (DbString)this.as385_nm.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as385_lv = (DbString)this.as385_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as385_ds = (DbInteger)this.as385_ds.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as385_dv = (DbDouble)this.as385_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as386_lv = (DbString)this.as386_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as386_dv = (DbDouble)this.as386_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as387_lv = (DbString)this.as387_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as387_dv = (DbDouble)this.as387_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as388_lv = (DbString)this.as388_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as388_dv = (DbDouble)this.as388_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as389_lv = (DbString)this.as389_lv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.as389_dv = (DbDouble)this.as389_dv.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.price_type = (DbInteger)this.price_type.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.month_near = (DbDouble)this.month_near.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.area_near = (DbInteger)this.area_near.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.abs_rate = (DbDouble)this.abs_rate.clone();
        nVO_BASELAND_APPRAISALA3_SCORE.diff_cnt = (DbInteger)this.diff_cnt.clone();
        dbElementArray[0] = nVO_BASELAND_APPRAISALA3_SCORE.year;
        dbElementArray[1] = nVO_BASELAND_APPRAISALA3_SCORE.baseno;
        dbElementArray[2] = nVO_BASELAND_APPRAISALA3_SCORE.city;
        dbElementArray[3] = nVO_BASELAND_APPRAISALA3_SCORE.ofce;
        dbElementArray[4] = nVO_BASELAND_APPRAISALA3_SCORE.dist;
        dbElementArray[5] = nVO_BASELAND_APPRAISALA3_SCORE.as_type;
        dbElementArray[6] = nVO_BASELAND_APPRAISALA3_SCORE.as301;
        dbElementArray[7] = nVO_BASELAND_APPRAISALA3_SCORE.as302;
        dbElementArray[8] = nVO_BASELAND_APPRAISALA3_SCORE.as303;
        dbElementArray[9] = nVO_BASELAND_APPRAISALA3_SCORE.as307_nm;
        dbElementArray[10] = nVO_BASELAND_APPRAISALA3_SCORE.as307_dv;
        dbElementArray[11] = nVO_BASELAND_APPRAISALA3_SCORE.as308_dv;
        dbElementArray[12] = nVO_BASELAND_APPRAISALA3_SCORE.as315_nm;
        dbElementArray[13] = nVO_BASELAND_APPRAISALA3_SCORE.as315_ds;
        dbElementArray[14] = nVO_BASELAND_APPRAISALA3_SCORE.as315_lv;
        dbElementArray[15] = nVO_BASELAND_APPRAISALA3_SCORE.as315_dv;
        dbElementArray[16] = nVO_BASELAND_APPRAISALA3_SCORE.as316_nm;
        dbElementArray[17] = nVO_BASELAND_APPRAISALA3_SCORE.as316_lv;
        dbElementArray[18] = nVO_BASELAND_APPRAISALA3_SCORE.as316_dv;
        dbElementArray[19] = nVO_BASELAND_APPRAISALA3_SCORE.as317_nm;
        dbElementArray[20] = nVO_BASELAND_APPRAISALA3_SCORE.as317_lv;
        dbElementArray[21] = nVO_BASELAND_APPRAISALA3_SCORE.as317_dv;
        dbElementArray[22] = nVO_BASELAND_APPRAISALA3_SCORE.as318_nm;
        dbElementArray[23] = nVO_BASELAND_APPRAISALA3_SCORE.as318_lv;
        dbElementArray[24] = nVO_BASELAND_APPRAISALA3_SCORE.as318_dv;
        dbElementArray[25] = nVO_BASELAND_APPRAISALA3_SCORE.as319_nm;
        dbElementArray[26] = nVO_BASELAND_APPRAISALA3_SCORE.as319_lv;
        dbElementArray[27] = nVO_BASELAND_APPRAISALA3_SCORE.as319_dv;
        dbElementArray[28] = nVO_BASELAND_APPRAISALA3_SCORE.as320;
        dbElementArray[29] = nVO_BASELAND_APPRAISALA3_SCORE.as321_lv;
        dbElementArray[30] = nVO_BASELAND_APPRAISALA3_SCORE.as321_dv;
        dbElementArray[31] = nVO_BASELAND_APPRAISALA3_SCORE.as322_lv;
        dbElementArray[32] = nVO_BASELAND_APPRAISALA3_SCORE.as322_dv;
        dbElementArray[33] = nVO_BASELAND_APPRAISALA3_SCORE.as323_lv;
        dbElementArray[34] = nVO_BASELAND_APPRAISALA3_SCORE.as323_dv;
        dbElementArray[35] = nVO_BASELAND_APPRAISALA3_SCORE.as324_lv;
        dbElementArray[36] = nVO_BASELAND_APPRAISALA3_SCORE.as324_dv;
        dbElementArray[37] = nVO_BASELAND_APPRAISALA3_SCORE.as325;
        dbElementArray[38] = nVO_BASELAND_APPRAISALA3_SCORE.as326_nm;
        dbElementArray[39] = nVO_BASELAND_APPRAISALA3_SCORE.as326_lv;
        dbElementArray[40] = nVO_BASELAND_APPRAISALA3_SCORE.as326_dv;
        dbElementArray[41] = nVO_BASELAND_APPRAISALA3_SCORE.as327_nm;
        dbElementArray[42] = nVO_BASELAND_APPRAISALA3_SCORE.as327_lv;
        dbElementArray[43] = nVO_BASELAND_APPRAISALA3_SCORE.as327_dv;
        dbElementArray[44] = nVO_BASELAND_APPRAISALA3_SCORE.as328_nm;
        dbElementArray[45] = nVO_BASELAND_APPRAISALA3_SCORE.as328_lv;
        dbElementArray[46] = nVO_BASELAND_APPRAISALA3_SCORE.as328_dv;
        dbElementArray[47] = nVO_BASELAND_APPRAISALA3_SCORE.as329_nm;
        dbElementArray[48] = nVO_BASELAND_APPRAISALA3_SCORE.as329_lv;
        dbElementArray[49] = nVO_BASELAND_APPRAISALA3_SCORE.as329_dv;
        dbElementArray[50] = nVO_BASELAND_APPRAISALA3_SCORE.as330_nm;
        dbElementArray[51] = nVO_BASELAND_APPRAISALA3_SCORE.as330_lv;
        dbElementArray[52] = nVO_BASELAND_APPRAISALA3_SCORE.as330_dv;
        dbElementArray[53] = nVO_BASELAND_APPRAISALA3_SCORE.as331;
        dbElementArray[54] = nVO_BASELAND_APPRAISALA3_SCORE.as313_lv;
        dbElementArray[55] = nVO_BASELAND_APPRAISALA3_SCORE.as313_dv;
        dbElementArray[56] = nVO_BASELAND_APPRAISALA3_SCORE.as314;
        dbElementArray[57] = nVO_BASELAND_APPRAISALA3_SCORE.as314_lv;
        dbElementArray[58] = nVO_BASELAND_APPRAISALA3_SCORE.as314_dv;
        dbElementArray[59] = nVO_BASELAND_APPRAISALA3_SCORE.as332;
        dbElementArray[60] = nVO_BASELAND_APPRAISALA3_SCORE.as339_lv;
        dbElementArray[61] = nVO_BASELAND_APPRAISALA3_SCORE.as339_dv;
        dbElementArray[62] = nVO_BASELAND_APPRAISALA3_SCORE.as340_ds;
        dbElementArray[63] = nVO_BASELAND_APPRAISALA3_SCORE.as340_lv;
        dbElementArray[64] = nVO_BASELAND_APPRAISALA3_SCORE.as340_dv;
        dbElementArray[65] = nVO_BASELAND_APPRAISALA3_SCORE.as341_ds;
        dbElementArray[66] = nVO_BASELAND_APPRAISALA3_SCORE.as341_lv;
        dbElementArray[67] = nVO_BASELAND_APPRAISALA3_SCORE.as341_dv;
        dbElementArray[68] = nVO_BASELAND_APPRAISALA3_SCORE.as342_nm;
        dbElementArray[69] = nVO_BASELAND_APPRAISALA3_SCORE.as342_lv;
        dbElementArray[70] = nVO_BASELAND_APPRAISALA3_SCORE.as342_dv;
        dbElementArray[71] = nVO_BASELAND_APPRAISALA3_SCORE.as343_nm;
        dbElementArray[72] = nVO_BASELAND_APPRAISALA3_SCORE.as343_lv;
        dbElementArray[73] = nVO_BASELAND_APPRAISALA3_SCORE.as343_dv;
        dbElementArray[74] = nVO_BASELAND_APPRAISALA3_SCORE.as344;
        dbElementArray[75] = nVO_BASELAND_APPRAISALA3_SCORE.as345_nm;
        dbElementArray[76] = nVO_BASELAND_APPRAISALA3_SCORE.as345_ds;
        dbElementArray[77] = nVO_BASELAND_APPRAISALA3_SCORE.as345_lv;
        dbElementArray[78] = nVO_BASELAND_APPRAISALA3_SCORE.as345_dv;
        dbElementArray[79] = nVO_BASELAND_APPRAISALA3_SCORE.as346_lv;
        dbElementArray[80] = nVO_BASELAND_APPRAISALA3_SCORE.as346_dv;
        dbElementArray[81] = nVO_BASELAND_APPRAISALA3_SCORE.as347_nm;
        dbElementArray[82] = nVO_BASELAND_APPRAISALA3_SCORE.as347_lv;
        dbElementArray[83] = nVO_BASELAND_APPRAISALA3_SCORE.as347_dv;
        dbElementArray[84] = nVO_BASELAND_APPRAISALA3_SCORE.as348;
        dbElementArray[85] = nVO_BASELAND_APPRAISALA3_SCORE.as348_nm;
        dbElementArray[86] = nVO_BASELAND_APPRAISALA3_SCORE.as348_lv;
        dbElementArray[87] = nVO_BASELAND_APPRAISALA3_SCORE.as348_dv;
        dbElementArray[88] = nVO_BASELAND_APPRAISALA3_SCORE.as349;
        dbElementArray[89] = nVO_BASELAND_APPRAISALA3_SCORE.as350_nm;
        dbElementArray[90] = nVO_BASELAND_APPRAISALA3_SCORE.as350_ds;
        dbElementArray[91] = nVO_BASELAND_APPRAISALA3_SCORE.as350_lv;
        dbElementArray[92] = nVO_BASELAND_APPRAISALA3_SCORE.as350_dv;
        dbElementArray[93] = nVO_BASELAND_APPRAISALA3_SCORE.as351_nm;
        dbElementArray[94] = nVO_BASELAND_APPRAISALA3_SCORE.as351_ds;
        dbElementArray[95] = nVO_BASELAND_APPRAISALA3_SCORE.as351_lv;
        dbElementArray[96] = nVO_BASELAND_APPRAISALA3_SCORE.as351_dv;
        dbElementArray[97] = nVO_BASELAND_APPRAISALA3_SCORE.as352_nm;
        dbElementArray[98] = nVO_BASELAND_APPRAISALA3_SCORE.as352_ds;
        dbElementArray[99] = nVO_BASELAND_APPRAISALA3_SCORE.as352_lv;
        dbElementArray[100] = nVO_BASELAND_APPRAISALA3_SCORE.as352_dv;
        dbElementArray[101] = nVO_BASELAND_APPRAISALA3_SCORE.as353_nm;
        dbElementArray[102] = nVO_BASELAND_APPRAISALA3_SCORE.as353_ds;
        dbElementArray[103] = nVO_BASELAND_APPRAISALA3_SCORE.as353_lv;
        dbElementArray[104] = nVO_BASELAND_APPRAISALA3_SCORE.as353_dv;
        dbElementArray[105] = nVO_BASELAND_APPRAISALA3_SCORE.as354_nm;
        dbElementArray[106] = nVO_BASELAND_APPRAISALA3_SCORE.as354_ds;
        dbElementArray[107] = nVO_BASELAND_APPRAISALA3_SCORE.as354_lv;
        dbElementArray[108] = nVO_BASELAND_APPRAISALA3_SCORE.as354_dv;
        dbElementArray[109] = nVO_BASELAND_APPRAISALA3_SCORE.as355_lv;
        dbElementArray[110] = nVO_BASELAND_APPRAISALA3_SCORE.as355_dv;
        dbElementArray[111] = nVO_BASELAND_APPRAISALA3_SCORE.as356;
        dbElementArray[112] = nVO_BASELAND_APPRAISALA3_SCORE.as356_nm;
        dbElementArray[113] = nVO_BASELAND_APPRAISALA3_SCORE.as356_lv;
        dbElementArray[114] = nVO_BASELAND_APPRAISALA3_SCORE.as356_dv;
        dbElementArray[115] = nVO_BASELAND_APPRAISALA3_SCORE.as357;
        dbElementArray[116] = nVO_BASELAND_APPRAISALA3_SCORE.as358_lv;
        dbElementArray[117] = nVO_BASELAND_APPRAISALA3_SCORE.as358_dv;
        dbElementArray[118] = nVO_BASELAND_APPRAISALA3_SCORE.as359_lv;
        dbElementArray[119] = nVO_BASELAND_APPRAISALA3_SCORE.as359_dv;
        dbElementArray[120] = nVO_BASELAND_APPRAISALA3_SCORE.as360_nm;
        dbElementArray[121] = nVO_BASELAND_APPRAISALA3_SCORE.as360_lv;
        dbElementArray[122] = nVO_BASELAND_APPRAISALA3_SCORE.as360_dv;
        dbElementArray[123] = nVO_BASELAND_APPRAISALA3_SCORE.as361_lv;
        dbElementArray[124] = nVO_BASELAND_APPRAISALA3_SCORE.as361_dv;
        dbElementArray[125] = nVO_BASELAND_APPRAISALA3_SCORE.as362;
        dbElementArray[126] = nVO_BASELAND_APPRAISALA3_SCORE.as362_nm;
        dbElementArray[127] = nVO_BASELAND_APPRAISALA3_SCORE.as362_lv;
        dbElementArray[128] = nVO_BASELAND_APPRAISALA3_SCORE.as362_dv;
        dbElementArray[129] = nVO_BASELAND_APPRAISALA3_SCORE.as363;
        dbElementArray[130] = nVO_BASELAND_APPRAISALA3_SCORE.as364_nm;
        dbElementArray[131] = nVO_BASELAND_APPRAISALA3_SCORE.as364_lv;
        dbElementArray[132] = nVO_BASELAND_APPRAISALA3_SCORE.as364_dv;
        dbElementArray[133] = nVO_BASELAND_APPRAISALA3_SCORE.as365_ds;
        dbElementArray[134] = nVO_BASELAND_APPRAISALA3_SCORE.as365_lv;
        dbElementArray[135] = nVO_BASELAND_APPRAISALA3_SCORE.as365_dv;
        dbElementArray[136] = nVO_BASELAND_APPRAISALA3_SCORE.as366_ds;
        dbElementArray[137] = nVO_BASELAND_APPRAISALA3_SCORE.as366_lv;
        dbElementArray[138] = nVO_BASELAND_APPRAISALA3_SCORE.as366_dv;
        dbElementArray[139] = nVO_BASELAND_APPRAISALA3_SCORE.as367_ds;
        dbElementArray[140] = nVO_BASELAND_APPRAISALA3_SCORE.as367_lv;
        dbElementArray[141] = nVO_BASELAND_APPRAISALA3_SCORE.as367_dv;
        dbElementArray[142] = nVO_BASELAND_APPRAISALA3_SCORE.as368_nm;
        dbElementArray[143] = nVO_BASELAND_APPRAISALA3_SCORE.as368_lv;
        dbElementArray[144] = nVO_BASELAND_APPRAISALA3_SCORE.as368_dv;
        dbElementArray[145] = nVO_BASELAND_APPRAISALA3_SCORE.as369;
        dbElementArray[146] = nVO_BASELAND_APPRAISALA3_SCORE.as369_nm;
        dbElementArray[147] = nVO_BASELAND_APPRAISALA3_SCORE.as369_lv;
        dbElementArray[148] = nVO_BASELAND_APPRAISALA3_SCORE.as369_dv;
        dbElementArray[149] = nVO_BASELAND_APPRAISALA3_SCORE.as370;
        dbElementArray[150] = nVO_BASELAND_APPRAISALA3_SCORE.as371;
        dbElementArray[151] = nVO_BASELAND_APPRAISALA3_SCORE.as372;
        dbElementArray[152] = nVO_BASELAND_APPRAISALA3_SCORE.as373;
        dbElementArray[153] = nVO_BASELAND_APPRAISALA3_SCORE.as374_lv;
        dbElementArray[154] = nVO_BASELAND_APPRAISALA3_SCORE.as374_dv;
        dbElementArray[155] = nVO_BASELAND_APPRAISALA3_SCORE.as375_lv;
        dbElementArray[156] = nVO_BASELAND_APPRAISALA3_SCORE.as375_dv;
        dbElementArray[157] = nVO_BASELAND_APPRAISALA3_SCORE.as376_lv;
        dbElementArray[158] = nVO_BASELAND_APPRAISALA3_SCORE.as376_dv;
        dbElementArray[159] = nVO_BASELAND_APPRAISALA3_SCORE.as377;
        dbElementArray[160] = nVO_BASELAND_APPRAISALA3_SCORE.as377_nm;
        dbElementArray[161] = nVO_BASELAND_APPRAISALA3_SCORE.as377_lv;
        dbElementArray[162] = nVO_BASELAND_APPRAISALA3_SCORE.as377_dv;
        dbElementArray[163] = nVO_BASELAND_APPRAISALA3_SCORE.as378_lv;
        dbElementArray[164] = nVO_BASELAND_APPRAISALA3_SCORE.as378_dv;
        dbElementArray[165] = nVO_BASELAND_APPRAISALA3_SCORE.as379;
        dbElementArray[166] = nVO_BASELAND_APPRAISALA3_SCORE.as379_nm;
        dbElementArray[167] = nVO_BASELAND_APPRAISALA3_SCORE.as379_lv;
        dbElementArray[168] = nVO_BASELAND_APPRAISALA3_SCORE.as379_dv;
        dbElementArray[169] = nVO_BASELAND_APPRAISALA3_SCORE.as380_nm;
        dbElementArray[170] = nVO_BASELAND_APPRAISALA3_SCORE.as380_lv;
        dbElementArray[171] = nVO_BASELAND_APPRAISALA3_SCORE.as380_ds;
        dbElementArray[172] = nVO_BASELAND_APPRAISALA3_SCORE.as380_dv;
        dbElementArray[173] = nVO_BASELAND_APPRAISALA3_SCORE.as381_lv;
        dbElementArray[174] = nVO_BASELAND_APPRAISALA3_SCORE.as381_dv;
        dbElementArray[175] = nVO_BASELAND_APPRAISALA3_SCORE.as382_lv;
        dbElementArray[176] = nVO_BASELAND_APPRAISALA3_SCORE.as382_dv;
        dbElementArray[177] = nVO_BASELAND_APPRAISALA3_SCORE.as383_nm;
        dbElementArray[178] = nVO_BASELAND_APPRAISALA3_SCORE.as383_lv;
        dbElementArray[179] = nVO_BASELAND_APPRAISALA3_SCORE.as383_ds;
        dbElementArray[180] = nVO_BASELAND_APPRAISALA3_SCORE.as383_dv;
        dbElementArray[181] = nVO_BASELAND_APPRAISALA3_SCORE.as384_nm;
        dbElementArray[182] = nVO_BASELAND_APPRAISALA3_SCORE.as384_lv;
        dbElementArray[183] = nVO_BASELAND_APPRAISALA3_SCORE.as384_ds;
        dbElementArray[184] = nVO_BASELAND_APPRAISALA3_SCORE.as384_dv;
        dbElementArray[185] = nVO_BASELAND_APPRAISALA3_SCORE.as385_nm;
        dbElementArray[186] = nVO_BASELAND_APPRAISALA3_SCORE.as385_lv;
        dbElementArray[187] = nVO_BASELAND_APPRAISALA3_SCORE.as385_ds;
        dbElementArray[188] = nVO_BASELAND_APPRAISALA3_SCORE.as385_dv;
        dbElementArray[189] = nVO_BASELAND_APPRAISALA3_SCORE.as386_lv;
        dbElementArray[190] = nVO_BASELAND_APPRAISALA3_SCORE.as386_dv;
        dbElementArray[191] = nVO_BASELAND_APPRAISALA3_SCORE.as387_lv;
        dbElementArray[192] = nVO_BASELAND_APPRAISALA3_SCORE.as387_dv;
        dbElementArray[193] = nVO_BASELAND_APPRAISALA3_SCORE.as388_lv;
        dbElementArray[194] = nVO_BASELAND_APPRAISALA3_SCORE.as388_dv;
        dbElementArray[195] = nVO_BASELAND_APPRAISALA3_SCORE.as389_lv;
        dbElementArray[196] = nVO_BASELAND_APPRAISALA3_SCORE.as389_dv;
        dbElementArray[197] = nVO_BASELAND_APPRAISALA3_SCORE.price_type;
        dbElementArray[198] = nVO_BASELAND_APPRAISALA3_SCORE.month_near;
        dbElementArray[199] = nVO_BASELAND_APPRAISALA3_SCORE.area_near;
        dbElementArray[200] = nVO_BASELAND_APPRAISALA3_SCORE.abs_rate;
        dbElementArray[201] = nVO_BASELAND_APPRAISALA3_SCORE.diff_cnt;
        nVO_BASELAND_APPRAISALA3_SCORE.elems = dbElementArray;
        nVO_BASELAND_APPRAISALA3_SCORE.fieldCount = this.fieldCount;
        nVO_BASELAND_APPRAISALA3_SCORE.orderString = this.orderString;
        nVO_BASELAND_APPRAISALA3_SCORE.tableName = this.tableName;
        return nVO_BASELAND_APPRAISALA3_SCORE;
    }

    public double getAs305() {
        return this.as305;
    }

    public void setAs305(double d) {
        this.as305 = d;
    }

    public double getAs339() {
        return this.as339;
    }

    public void setAs339(double d) {
        this.as339 = d;
    }

    public int calTargetPrice() {
        int n = (int)BigDecimalUtil.round((double)this.getAs306() * (1.0 + this.getAs372() / 100.0), 0);
        return n;
    }

    public String getPriceRateType() {
        return this.priceRateType;
    }

    public void setPriceRateType(String string) {
        this.priceRateType = string;
    }

    public String getAs308() {
        return this.as308;
    }

    public void setAs308(String string) {
        this.as308 = string;
    }

    public long getAs304() {
        return this.as304;
    }

    public void setAs304(long l) {
        this.as304 = l;
    }

    public long getAs306() {
        return this.as306;
    }

    public void setAs306(long l) {
        this.as306 = l;
    }

    public boolean isPriceRateDataExist() {
        return this.priceRateDataExist;
    }

    public void setPriceRateDataExist(boolean bl) {
        this.priceRateDataExist = bl;
    }
}

