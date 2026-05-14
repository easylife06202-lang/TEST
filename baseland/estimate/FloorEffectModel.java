/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 */
package com.wfusion.baseland.estimate;

import com.google.gson.Gson;
import com.wfusion.baseland.basic.Model;
import com.wfusion.util.StringProcess;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_FLOOR_EFFECT;
import moiland.baseland.flooreffect.bean.LandPriceAllocationCalculateBean;

public class FloorEffectModel
extends Model {
    DecimalFormat df = new DecimalFormat("##0.00");

    public TreeMap<String, LandPriceAllocationCalculateBean> getFloorEffect(NVO_BASELAND_FLOOR_EFFECT nVO_BASELAND_FLOOR_EFFECT) {
        TreeMap<String, LandPriceAllocationCalculateBean> treeMap = null;
        Gson gson = new Gson();
        HashMap[] hashMapArray = null;
        if (!StringProcess.isEmpty(nVO_BASELAND_FLOOR_EFFECT.getJsondata())) {
            hashMapArray = (HashMap[])gson.fromJson(nVO_BASELAND_FLOOR_EFFECT.getJsondata(), HashMap[].class);
        }
        if (hashMapArray != null && hashMapArray.length > 0) {
            treeMap = new TreeMap<String, LandPriceAllocationCalculateBean>();
            LandPriceAllocationCalculateBean landPriceAllocationCalculateBean = null;
            for (HashMap hashMap : hashMapArray) {
                landPriceAllocationCalculateBean = new LandPriceAllocationCalculateBean();
                landPriceAllocationCalculateBean.setFloor((String)hashMap.get("fdFloor"));
                landPriceAllocationCalculateBean.setFloorEffectRatio(StringProcess.parserDouble((String)hashMap.get("fdEffect")));
                landPriceAllocationCalculateBean.setLandPriceAllocationRatio(StringProcess.parserDouble((String)hashMap.get("fdRatio")));
                treeMap.put(landPriceAllocationCalculateBean.getFloor(), landPriceAllocationCalculateBean);
            }
        }
        return treeMap;
    }

    public void saveFloorEffect(NVO_BASELAND_FLOOR_EFFECT nVO_BASELAND_FLOOR_EFFECT, TreeMap<String, LandPriceAllocationCalculateBean> treeMap, String string, String string2) {
        if (treeMap != null && treeMap.size() > 0) {
            nVO_BASELAND_FLOOR_EFFECT.setAvg_effect(StringProcess.parserDouble(string));
            nVO_BASELAND_FLOOR_EFFECT.setAvg_ratio(StringProcess.parserDouble(string2));
            String string3 = "\"";
            String string4 = "";
            for (Map.Entry<String, LandPriceAllocationCalculateBean> entry : treeMap.entrySet()) {
                if (!StringProcess.isEmpty(string4)) {
                    string4 = string4 + ",";
                }
                string4 = string4 + "{" + string3 + "fdFloor" + string3 + ":";
                string4 = string4 + string3 + entry.getValue().getFloor() + string3 + ",";
                string4 = string4 + string3 + "fdEffect" + string3 + ":";
                string4 = string4 + string3 + this.df.format(entry.getValue().getFloorEffectRatio()) + string3 + ",";
                string4 = string4 + string3 + "fdRatio" + string3 + ":";
                string4 = string4 + string3 + this.df.format(entry.getValue().getLandPriceAllocationRatio()) + string3 + "}";
            }
            nVO_BASELAND_FLOOR_EFFECT.setJsondata("[" + string4 + "]");
        } else {
            nVO_BASELAND_FLOOR_EFFECT.setAvg_effect(0.0);
            nVO_BASELAND_FLOOR_EFFECT.setAvg_ratio(0.0);
            nVO_BASELAND_FLOOR_EFFECT.setJsondata("");
        }
    }
}

