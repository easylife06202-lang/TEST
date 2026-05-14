/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.baseland.estimate;

import com.wfusion.baseland.basic.Model;
import com.wfusion.baseland.estimate.EstimateModel;
import com.wfusion.util.OptionPair;
import java.util.ArrayList;
import java.util.HashMap;
import moiland.baseland.action.bean.BaseLandDevelopParamBean;
import moiland.baseland.bo.AutoCalBaseLandDevelopBo;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_MAIN;

public class EstimateReport8Model
extends Model {
    NVO_BASELAND_MAIN displayMainVo = null;
    HashMap<String, Object> voValue = new HashMap();
    HashMap<String, Object> voExtValue = new HashMap();
    public double baseland_aa10 = 0.0;
    public String baseland_landuse = "";
    public double baseland_cov_ratio = 0.0;
    public double baseland_are_ratio = 0.0;

    public ArrayList<OptionPair> getFloorTypeList() {
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        arrayList.add(new OptionPair("0", "\u4f9d\u5e02\u5834\u578b\u614b\u898f\u5283"));
        arrayList.add(new OptionPair("1", "\u5047\u8a2d\u63a8\u7b97"));
        return arrayList;
    }

    public ArrayList<OptionPair> getIsMergeList() {
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        arrayList.add(new OptionPair("0", "\u5426"));
        arrayList.add(new OptionPair("1", "\u662f"));
        return arrayList;
    }

    public ArrayList<OptionPair> getPriceTypeList() {
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        arrayList.add(new OptionPair("1", "\u78ba\u5be6\u6210\u4ea4\u50f9"));
        arrayList.add(new OptionPair("2", "\u8a62\u554f\u6210\u4ea4\u50f9"));
        arrayList.add(new OptionPair("3", "\u5f85\u552e\u50f9"));
        return arrayList;
    }

    public void updateHashMapValues() {
        this.voValue = new HashMap();
        this.voValue.putAll(EstimateModel.BASELANDBEAN.voDevelop.getFieldToHashMapExport());
        this.voValue.putAll(EstimateModel.BASELANDBEAN.voDevelopExt.getFieldToHashMapExport());
    }

    public void reCal() {
        BaseLandDevelopParamBean baseLandDevelopParamBean = new BaseLandDevelopParamBean(EstimateModel.BASELANDBEAN.voMain, EstimateModel.BASELANDBEAN.voDevelop, EstimateModel.BASELANDBEAN.voDevelopExt);
        this.baseland_aa10 = EstimateModel.BASELANDBEAN.voMain.getAa10();
        this.baseland_landuse = EstimateModel.BASELANDBEAN.voMain.getLanduse();
        this.baseland_cov_ratio = EstimateModel.BASELANDBEAN.voMain.getCov_ratio();
        this.baseland_are_ratio = EstimateModel.BASELANDBEAN.voMain.getAre_ratio();
        new AutoCalBaseLandDevelopBo(baseLandDevelopParamBean).calculateData();
        this.displayMainVo = baseLandDevelopParamBean.getVoMain();
        EstimateModel.BASELANDBEAN.voMain.setCprice(EstimateModel.BASELANDBEAN.voDevelop.getLand_unit_price_ping());
    }

    public void updateVo() {
        EstimateModel.BASELANDBEAN.voDevelop.setBeanByHashMap(this.voValue, false);
        EstimateModel.BASELANDBEAN.voDevelopExt.setBeanByHashMap(this.voValue, false);
    }
}

