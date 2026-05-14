/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.baseland;

import com.wfusion.baseland.QueryBean;
import com.wfusion.baseland.estimate.EstimateModel;
import com.wfusion.util.DateTime;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_APPRAISAL;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_APPRAISALA3_SCORE;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_DEVELOP;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_DEVELOP_EXT;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_FLOOR_EFFECT;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_IMAGES;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_MAIN;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT_EXT;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT_MONTH;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_SELL;

public class BaseLandBean {
    public boolean isInit = false;
    public QueryBean queryBean = new QueryBean();
    public NVO_BASELAND_MAIN voMain = new NVO_BASELAND_MAIN();
    public NVO_BASELAND_APPRAISAL voAppRaMain = new NVO_BASELAND_APPRAISAL();
    public NVO_BASELAND_APPRAISALA3_SCORE voAppRaA3Vo0 = new NVO_BASELAND_APPRAISALA3_SCORE();
    public NVO_BASELAND_APPRAISALA3_SCORE voAppRaA3Vo1 = new NVO_BASELAND_APPRAISALA3_SCORE();
    public NVO_BASELAND_APPRAISALA3_SCORE voAppRaA3Vo2 = new NVO_BASELAND_APPRAISALA3_SCORE();
    public NVO_BASELAND_APPRAISALA3_SCORE voAppRaA3Vo3 = new NVO_BASELAND_APPRAISALA3_SCORE();
    public NVO_BASELAND_SELL voSell_1 = new NVO_BASELAND_SELL();
    public NVO_BASELAND_SELL voSell_2 = new NVO_BASELAND_SELL();
    public NVO_BASELAND_SELL voSell_3 = new NVO_BASELAND_SELL();
    public NVO_BASELAND_FLOOR_EFFECT floor_1 = new NVO_BASELAND_FLOOR_EFFECT();
    public NVO_BASELAND_FLOOR_EFFECT floor_2 = new NVO_BASELAND_FLOOR_EFFECT();
    public NVO_BASELAND_FLOOR_EFFECT floor_3 = new NVO_BASELAND_FLOOR_EFFECT();
    public NVO_BASELAND_RENT_EXT voRentExt = new NVO_BASELAND_RENT_EXT();
    public NVO_BASELAND_RENT voRent = new NVO_BASELAND_RENT();
    public NVO_BASELAND_RENT_MONTH voRentMonth1 = new NVO_BASELAND_RENT_MONTH();
    public NVO_BASELAND_RENT_MONTH voRentMonth2 = new NVO_BASELAND_RENT_MONTH();
    public NVO_BASELAND_RENT_MONTH voRentMonth3 = new NVO_BASELAND_RENT_MONTH();
    public NVO_BASELAND_DEVELOP voDevelop = new NVO_BASELAND_DEVELOP();
    public NVO_BASELAND_DEVELOP_EXT voDevelopExt = new NVO_BASELAND_DEVELOP_EXT();
    public NVO_BASELAND_IMAGES voImages = new NVO_BASELAND_IMAGES();

    public void init() {
        this.queryBean = new QueryBean();
        this.voMain = new NVO_BASELAND_MAIN();
        this.voMain.setUserid("\u4f30\u50f9\u5e2b");
        this.voMain.setCreator("1");
        this.voMain.setFill_date(new DateTime().getFullChDate());
        this.voAppRaMain = new NVO_BASELAND_APPRAISAL();
        this.voAppRaA3Vo0 = new NVO_BASELAND_APPRAISALA3_SCORE();
        this.voAppRaA3Vo1 = new NVO_BASELAND_APPRAISALA3_SCORE();
        this.voAppRaA3Vo2 = new NVO_BASELAND_APPRAISALA3_SCORE();
        this.voAppRaA3Vo3 = new NVO_BASELAND_APPRAISALA3_SCORE();
        this.voSell_1 = new NVO_BASELAND_SELL();
        this.voSell_2 = new NVO_BASELAND_SELL();
        this.voSell_3 = new NVO_BASELAND_SELL();
        this.floor_1 = new NVO_BASELAND_FLOOR_EFFECT();
        this.floor_2 = new NVO_BASELAND_FLOOR_EFFECT();
        this.floor_3 = new NVO_BASELAND_FLOOR_EFFECT();
        this.voRentExt = new NVO_BASELAND_RENT_EXT();
        this.voRent = new NVO_BASELAND_RENT();
        this.voRentMonth1 = new NVO_BASELAND_RENT_MONTH();
        this.voRentMonth2 = new NVO_BASELAND_RENT_MONTH();
        this.voRentMonth3 = new NVO_BASELAND_RENT_MONTH();
        this.voDevelop = new NVO_BASELAND_DEVELOP();
        this.voDevelopExt = new NVO_BASELAND_DEVELOP_EXT();
        this.voImages = new NVO_BASELAND_IMAGES();
    }

    public void initBeans() {
        EstimateModel.BASELANDBEAN.voMain.setBaseno(EstimateModel.BASELANDBEAN.queryBean.baseno);
        EstimateModel.BASELANDBEAN.voMain.setCity(EstimateModel.BASELANDBEAN.queryBean.AA45);
        EstimateModel.BASELANDBEAN.voMain.setDist(EstimateModel.BASELANDBEAN.queryBean.AA46);
        EstimateModel.BASELANDBEAN.voMain.setPrice_date(EstimateModel.BASELANDBEAN.queryBean.year + "0331");
        EstimateModel.BASELANDBEAN.voMain.setOfce(EstimateModel.BASELANDBEAN.queryBean.office);
        EstimateModel.BASELANDBEAN.voMain.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
        EstimateModel.BASELANDBEAN.voMain.setUrban(EstimateModel.BASELANDBEAN.queryBean.urban);
        EstimateModel.BASELANDBEAN.voMain.setVersion(EstimateModel.BASELANDBEAN.queryBean.version);
        EstimateModel.BASELANDBEAN.voAppRaMain.setBaseno(EstimateModel.BASELANDBEAN.queryBean.baseno);
        EstimateModel.BASELANDBEAN.voAppRaMain.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
        EstimateModel.BASELANDBEAN.voAppRaMain.setCity(EstimateModel.BASELANDBEAN.queryBean.AA45);
        EstimateModel.BASELANDBEAN.voAppRaMain.setDist(EstimateModel.BASELANDBEAN.queryBean.AA46);
        EstimateModel.BASELANDBEAN.voAppRaA3Vo0.setBaseno(EstimateModel.BASELANDBEAN.queryBean.baseno);
        EstimateModel.BASELANDBEAN.voAppRaA3Vo0.setCity(EstimateModel.BASELANDBEAN.queryBean.AA45);
        EstimateModel.BASELANDBEAN.voAppRaA3Vo0.setDist(EstimateModel.BASELANDBEAN.queryBean.AA46);
        EstimateModel.BASELANDBEAN.voAppRaA3Vo0.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
        EstimateModel.BASELANDBEAN.voAppRaA3Vo0.setAs_type("0");
        EstimateModel.BASELANDBEAN.voAppRaA3Vo1.setBaseno(EstimateModel.BASELANDBEAN.queryBean.baseno);
        EstimateModel.BASELANDBEAN.voAppRaA3Vo1.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
        EstimateModel.BASELANDBEAN.voAppRaA3Vo1.setAs_type("1");
        EstimateModel.BASELANDBEAN.voAppRaA3Vo2.setBaseno(EstimateModel.BASELANDBEAN.queryBean.baseno);
        EstimateModel.BASELANDBEAN.voAppRaA3Vo2.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
        EstimateModel.BASELANDBEAN.voAppRaA3Vo2.setAs_type("2");
        EstimateModel.BASELANDBEAN.voAppRaA3Vo3.setBaseno(EstimateModel.BASELANDBEAN.queryBean.baseno);
        EstimateModel.BASELANDBEAN.voAppRaA3Vo3.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
        EstimateModel.BASELANDBEAN.voAppRaA3Vo3.setAs_type("3");
        EstimateModel.BASELANDBEAN.voSell_1.setBaseno(EstimateModel.BASELANDBEAN.queryBean.baseno);
        EstimateModel.BASELANDBEAN.voSell_1.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
        EstimateModel.BASELANDBEAN.voSell_1.setCaseno("1");
        EstimateModel.BASELANDBEAN.voSell_1.setCs66(100.0);
        EstimateModel.BASELANDBEAN.floor_1.setBaseno(EstimateModel.BASELANDBEAN.queryBean.baseno);
        EstimateModel.BASELANDBEAN.floor_1.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
        EstimateModel.BASELANDBEAN.floor_1.setCaseno("1");
        EstimateModel.BASELANDBEAN.floor_1.setCity(EstimateModel.BASELANDBEAN.queryBean.AA45);
        EstimateModel.BASELANDBEAN.floor_1.setOfce(EstimateModel.BASELANDBEAN.queryBean.office);
        EstimateModel.BASELANDBEAN.voSell_2.setBaseno(EstimateModel.BASELANDBEAN.queryBean.baseno);
        EstimateModel.BASELANDBEAN.voSell_2.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
        EstimateModel.BASELANDBEAN.voSell_2.setCaseno("2");
        EstimateModel.BASELANDBEAN.voSell_2.setCs66(100.0);
        EstimateModel.BASELANDBEAN.floor_2.setBaseno(EstimateModel.BASELANDBEAN.queryBean.baseno);
        EstimateModel.BASELANDBEAN.floor_2.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
        EstimateModel.BASELANDBEAN.floor_2.setCaseno("2");
        EstimateModel.BASELANDBEAN.floor_2.setCity(EstimateModel.BASELANDBEAN.queryBean.AA45);
        EstimateModel.BASELANDBEAN.floor_2.setOfce(EstimateModel.BASELANDBEAN.queryBean.office);
        EstimateModel.BASELANDBEAN.voSell_3.setBaseno(EstimateModel.BASELANDBEAN.queryBean.baseno);
        EstimateModel.BASELANDBEAN.voSell_3.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
        EstimateModel.BASELANDBEAN.voSell_3.setCaseno("3");
        EstimateModel.BASELANDBEAN.voSell_3.setCs66(100.0);
        EstimateModel.BASELANDBEAN.floor_3.setBaseno(EstimateModel.BASELANDBEAN.queryBean.baseno);
        EstimateModel.BASELANDBEAN.floor_3.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
        EstimateModel.BASELANDBEAN.floor_3.setCaseno("3");
        EstimateModel.BASELANDBEAN.floor_3.setCity(EstimateModel.BASELANDBEAN.queryBean.AA45);
        EstimateModel.BASELANDBEAN.floor_3.setOfce(EstimateModel.BASELANDBEAN.queryBean.office);
        EstimateModel.BASELANDBEAN.voRentExt.setBaseno(EstimateModel.BASELANDBEAN.queryBean.baseno);
        EstimateModel.BASELANDBEAN.voRentExt.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
        EstimateModel.BASELANDBEAN.voRentExt.setCity(EstimateModel.BASELANDBEAN.queryBean.AA45);
        EstimateModel.BASELANDBEAN.voRentExt.setDist(EstimateModel.BASELANDBEAN.queryBean.AA46);
        double d = new EstimateModel().getAdjustRatio(EstimateModel.BASELANDBEAN.queryBean.year + "0331");
        EstimateModel.BASELANDBEAN.voRentExt.setCre32(d);
        EstimateModel.BASELANDBEAN.voRentExt.setCre59(100.0);
        EstimateModel.BASELANDBEAN.voRent.setBaseno(EstimateModel.BASELANDBEAN.queryBean.baseno);
        EstimateModel.BASELANDBEAN.voRent.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
        EstimateModel.BASELANDBEAN.voRent.setCity(EstimateModel.BASELANDBEAN.queryBean.AA45);
        EstimateModel.BASELANDBEAN.voRent.setDist(EstimateModel.BASELANDBEAN.queryBean.AA46);
        EstimateModel.BASELANDBEAN.voRentMonth1.setBaseno(EstimateModel.BASELANDBEAN.queryBean.baseno);
        EstimateModel.BASELANDBEAN.voRentMonth1.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
        EstimateModel.BASELANDBEAN.voRentMonth1.setCity(EstimateModel.BASELANDBEAN.queryBean.AA45);
        EstimateModel.BASELANDBEAN.voRentMonth1.setRent_caseno("1");
        EstimateModel.BASELANDBEAN.voRentMonth2.setBaseno(EstimateModel.BASELANDBEAN.queryBean.baseno);
        EstimateModel.BASELANDBEAN.voRentMonth2.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
        EstimateModel.BASELANDBEAN.voRentMonth2.setCity(EstimateModel.BASELANDBEAN.queryBean.AA45);
        EstimateModel.BASELANDBEAN.voRentMonth2.setRent_caseno("2");
        EstimateModel.BASELANDBEAN.voRentMonth3.setBaseno(EstimateModel.BASELANDBEAN.queryBean.baseno);
        EstimateModel.BASELANDBEAN.voRentMonth3.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
        EstimateModel.BASELANDBEAN.voRentMonth3.setCity(EstimateModel.BASELANDBEAN.queryBean.AA45);
        EstimateModel.BASELANDBEAN.voRentMonth3.setRent_caseno("3");
        EstimateModel.BASELANDBEAN.voDevelop.setOfce(EstimateModel.BASELANDBEAN.queryBean.office);
        EstimateModel.BASELANDBEAN.voDevelop.setBaseno(EstimateModel.BASELANDBEAN.queryBean.baseno);
        EstimateModel.BASELANDBEAN.voDevelop.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
        EstimateModel.BASELANDBEAN.voDevelop.setCity(EstimateModel.BASELANDBEAN.queryBean.AA45);
        EstimateModel.BASELANDBEAN.voDevelop.setDist(EstimateModel.BASELANDBEAN.queryBean.AA46);
        EstimateModel.BASELANDBEAN.voDevelop.setSale_are_ratio(1.6);
        this.setDevelopSaleParamDefaultValue(EstimateModel.BASELANDBEAN.voDevelop);
        EstimateModel.BASELANDBEAN.voDevelop.setBuild_cost_rate(d);
        EstimateModel.BASELANDBEAN.voDevelop.setBuild_cost_exp(100.0);
        EstimateModel.BASELANDBEAN.voDevelopExt.setBaseno(EstimateModel.BASELANDBEAN.queryBean.baseno);
        EstimateModel.BASELANDBEAN.voDevelopExt.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
        EstimateModel.BASELANDBEAN.voDevelopExt.setCity(EstimateModel.BASELANDBEAN.queryBean.AA45);
        EstimateModel.BASELANDBEAN.voImages.setBaseno(EstimateModel.BASELANDBEAN.queryBean.baseno);
        EstimateModel.BASELANDBEAN.voImages.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
    }

    private void setDevelopSaleParamDefaultValue(NVO_BASELAND_DEVELOP nVO_BASELAND_DEVELOP) {
        if (nVO_BASELAND_DEVELOP.getSale_electric() == 0.0) {
            nVO_BASELAND_DEVELOP.setSale_electric(1.15);
        }
        if (nVO_BASELAND_DEVELOP.getSale_balcony() == 0.0) {
            nVO_BASELAND_DEVELOP.setSale_balcony(1.15);
        }
        if (nVO_BASELAND_DEVELOP.getSale_protrusionc() == 0) {
            nVO_BASELAND_DEVELOP.setSale_protrusionc(3);
        }
        if (nVO_BASELAND_DEVELOP.getSale_protrusionm() == 0) {
            nVO_BASELAND_DEVELOP.setSale_protrusionm(8);
        }
        if (nVO_BASELAND_DEVELOP.getSale_publicratio() == 0.0) {
            nVO_BASELAND_DEVELOP.setSale_publicratio(0.15);
        }
        if (nVO_BASELAND_DEVELOP.getSale_parkarea() == 0.0) {
            nVO_BASELAND_DEVELOP.setSale_parkarea(12.0);
        }
    }
}

