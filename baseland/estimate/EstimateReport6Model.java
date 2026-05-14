/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.baseland.estimate;

import com.wfusion.baseland.basic.Model;
import com.wfusion.baseland.estimate.EstimateModel;
import java.util.HashMap;
import moiland.baseland.bo.AutoCalBaseLandRent;

public class EstimateReport6Model
extends Model {
    HashMap<String, Object> voValue = new HashMap();

    public void updateHashMapValues() {
        this.voValue = EstimateModel.BASELANDBEAN.voRentExt.getFieldToHashMapExport();
    }

    public void reCal() {
        new AutoCalBaseLandRent().calRentExt(EstimateModel.BASELANDBEAN.voRentExt);
    }

    public void updateVo() {
        EstimateModel.BASELANDBEAN.voRentExt.setBeanByHashMap(this.voValue, false);
    }
}

