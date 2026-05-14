/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.util;

import java.sql.Connection;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_APPRAISALA3_SCORE;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_DEVELOP;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT_EXT;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_REPORT_PARAM;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_SELL;
import moiland.baseland.report.param.bo.BaseLandReportParamDataBo;

public class BaseLandParamFillHelper {
    private NVO_BASELAND_REPORT_PARAM param = new NVO_BASELAND_REPORT_PARAM();

    public BaseLandParamFillHelper(String string, String string2, Connection connection) {
        this.param = new BaseLandReportParamDataBo(string, string2).getEditData(connection);
    }

    public NVO_BASELAND_REPORT_PARAM getParam() {
        return this.param;
    }

    public void fillValue(NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE) {
        nVO_BASELAND_APPRAISALA3_SCORE.setPriceRateType(this.param.getPrice_rate_type());
    }

    public void fillValue(NVO_BASELAND_SELL nVO_BASELAND_SELL) {
        nVO_BASELAND_SELL.setCs09(this.param.getOwner_rate());
        nVO_BASELAND_SELL.setCs10(this.param.getOwner_ratio());
        nVO_BASELAND_SELL.setCs12(this.param.getDebt_rate());
        nVO_BASELAND_SELL.setCs13(this.param.getDebt_ratio());
        nVO_BASELAND_SELL.setCs15(this.param.getPresale_rate());
        nVO_BASELAND_SELL.setCs16(this.param.getPresale_ratio());
        nVO_BASELAND_SELL.setCs32(this.param.getDesign_ratio());
        nVO_BASELAND_SELL.setCs34(this.param.getAd_ratio());
        nVO_BASELAND_SELL.setCs36(this.param.getManage_ratio());
        nVO_BASELAND_SELL.setCs38(this.param.getTax_ratio());
        nVO_BASELAND_SELL.setCs42(this.param.getDevp_rate());
    }

    public void fillValue(NVO_BASELAND_RENT_EXT nVO_BASELAND_RENT_EXT) {
        nVO_BASELAND_RENT_EXT.setCre10(this.param.getOwner_rate());
        nVO_BASELAND_RENT_EXT.setCre11(this.param.getOwner_ratio());
        nVO_BASELAND_RENT_EXT.setCre13(this.param.getDebt_rate());
        nVO_BASELAND_RENT_EXT.setCre14(this.param.getDebt_ratio());
        nVO_BASELAND_RENT_EXT.setCre16(this.param.getPresale_rate());
        nVO_BASELAND_RENT_EXT.setCre17(this.param.getPresale_ratio());
        nVO_BASELAND_RENT_EXT.setCre35(this.param.getDesign_ratio());
        nVO_BASELAND_RENT_EXT.setCre38(this.param.getAd_ratio());
        nVO_BASELAND_RENT_EXT.setCre40(this.param.getManage_ratio());
        nVO_BASELAND_RENT_EXT.setCre42(this.param.getTax_ratio());
        nVO_BASELAND_RENT_EXT.setCre50(this.param.getDevp_rate());
    }

    public void fillValue(NVO_BASELAND_RENT nVO_BASELAND_RENT) {
        nVO_BASELAND_RENT.setCr30(this.param.getBuild_benefit_rate());
        nVO_BASELAND_RENT.setCr35(this.param.getLand_benefit_rate());
    }

    public void fillValue(NVO_BASELAND_DEVELOP nVO_BASELAND_DEVELOP) {
        nVO_BASELAND_DEVELOP.setOwner_rate(this.param.getOwner_rate());
        nVO_BASELAND_DEVELOP.setOwner_ratio(this.param.getOwner_ratio());
        nVO_BASELAND_DEVELOP.setDebt_rate(this.param.getDebt_rate());
        nVO_BASELAND_DEVELOP.setDebt_ratio(this.param.getDebt_ratio());
        nVO_BASELAND_DEVELOP.setPresale_rate(this.param.getPresale_rate());
        nVO_BASELAND_DEVELOP.setPresale_ratio(this.param.getPresale_ratio());
        nVO_BASELAND_DEVELOP.setDesign_ratio(this.param.getDesign_ratio());
        nVO_BASELAND_DEVELOP.setAd_ratio(this.param.getAd_ratio());
        nVO_BASELAND_DEVELOP.setManage_ratio(this.param.getManage_ratio());
        nVO_BASELAND_DEVELOP.setTax_ratio(this.param.getTax_ratio());
    }
}

