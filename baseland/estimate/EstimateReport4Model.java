/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.baseland.estimate;

import com.wfusion.baseland.basic.Model;
import com.wfusion.baseland.estimate.EstimateModel;
import com.wfusion.util.SUtility;
import com.wfusion.util.SqlUtil;
import java.sql.Connection;
import java.util.HashMap;
import moiland.baseland.bo.AutoCalBaseLandSell;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_APPRAISALA3_SCORE;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_SELL;
import moiland.baseland.util.BaseLandInstruStdPriceTool;

public class EstimateReport4Model
extends Model {
    HashMap<String, Object> voValue = new HashMap();

    public void updateHashMapValues() {
        this.voValue = EstimateModel.BASELANDBEAN.voSell_2.getFieldToHashMapExport();
    }

    public void reCal() {
        NVO_BASELAND_SELL nVO_BASELAND_SELL = EstimateModel.BASELANDBEAN.voSell_2;
        NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE = EstimateModel.BASELANDBEAN.voAppRaA3Vo2;
        new AutoCalBaseLandSell(nVO_BASELAND_SELL);
        nVO_BASELAND_APPRAISALA3_SCORE.setAs301(nVO_BASELAND_SELL.getSelltype().equals("4") ? nVO_BASELAND_SELL.getLand_position() : nVO_BASELAND_SELL.getCs01());
        nVO_BASELAND_APPRAISALA3_SCORE.setAs304(nVO_BASELAND_SELL.getCs46());
        nVO_BASELAND_APPRAISALA3_SCORE.setAs305(nVO_BASELAND_SELL.getSelltype().equals("4") ? nVO_BASELAND_SELL.getCs49() : nVO_BASELAND_SELL.getCs06());
        nVO_BASELAND_APPRAISALA3_SCORE.setAs306(nVO_BASELAND_SELL.getCs51());
        nVO_BASELAND_APPRAISALA3_SCORE.setAs308(nVO_BASELAND_SELL.getCs30());
        nVO_BASELAND_APPRAISALA3_SCORE.setAs339(nVO_BASELAND_SELL.getAa10());
    }

    public void updateVo() {
        EstimateModel.BASELANDBEAN.voSell_2.setBeanByHashMap(this.voValue, false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int getInstruStdPrice(String string, int n) {
        Connection connection = null;
        try {
            int n2;
            connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
            BaseLandInstruStdPriceTool baseLandInstruStdPriceTool = new BaseLandInstruStdPriceTool(EstimateModel.BASELANDBEAN.queryBean.AA45, connection);
            int n3 = n2 = baseLandInstruStdPriceTool.getStartdardPrice(EstimateModel.BASELANDBEAN.queryBean.AA45, string, n);
            SqlUtil.close(connection);
            return n3;
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
        return 0;
    }
}

