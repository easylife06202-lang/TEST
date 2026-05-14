/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.bo;

import com.wfusion.util.SqlUtil;
import com.wfusion.util.StringProcess;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import moiland.baseland.action.bean.BaseLandSellParamBean;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_SELL;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_SELL;
import moiland.baseland.util.BaseLandBuildStdPriceAdjustRateFillHelper;
import moiland.baseland.util.BaseLandParamFillHelper;

public class BaseLandSellBo {
    NDAO_BASELAND_SELL ndao = new NDAO_BASELAND_SELL();

    public void queryBaseLandSell(BaseLandSellParamBean baseLandSellParamBean, Connection connection) throws Exception {
        if (!baseLandSellParamBean.getSellVo().getCaseno().equals("")) {
            String string = baseLandSellParamBean.getSellVo().getYear();
            ArrayList<NVO_BASELAND_SELL> arrayList = this.ndao.queryData(string, baseLandSellParamBean.getSellVo().getBaseno(), baseLandSellParamBean.getSellVo().getCaseno(), connection);
            if (arrayList.size() > 0) {
                baseLandSellParamBean.setSellVo(arrayList.get(0));
                baseLandSellParamBean.setMode("edit");
            } else {
                String string2 = baseLandSellParamBean.getSellVo().getCity();
                new BaseLandParamFillHelper(string2, string, connection).fillValue(baseLandSellParamBean.getSellVo());
                new BaseLandBuildStdPriceAdjustRateFillHelper(string2, string).setBuildCostAdjustRate(baseLandSellParamBean.getSellVo(), string + "0331", connection);
                baseLandSellParamBean.setMode("add");
                baseLandSellParamBean.getSellVo().setSelltype("5");
            }
        } else {
            baseLandSellParamBean.getMessage().append("\u8acb\u6307\u5b9a\u67e5\u8a62\u662f\u7b2c\u5e7e\u500b\u6210\u672c\u6cd5\uff01");
        }
    }

    public void saveBaseLandSell(BaseLandSellParamBean baseLandSellParamBean, Connection connection) throws Exception {
        baseLandSellParamBean.clearMessage();
        NVO_BASELAND_SELL nVO_BASELAND_SELL = baseLandSellParamBean.getSellVo();
        boolean bl = !StringProcess.isEmpty(nVO_BASELAND_SELL.getYear()) && !StringProcess.isEmpty(nVO_BASELAND_SELL.getCaseno()) && !StringProcess.isEmpty(nVO_BASELAND_SELL.getBaseno());
        System.out.println("isTrue=" + bl);
        if (bl) {
            ArrayList<NVO_BASELAND_SELL> arrayList = this.ndao.queryData(nVO_BASELAND_SELL.getYear(), nVO_BASELAND_SELL.getBaseno(), nVO_BASELAND_SELL.getCaseno(), connection);
            try {
                connection.setAutoCommit(false);
                this.ndao.delete(arrayList, connection);
                this.ndao.create(nVO_BASELAND_SELL, connection);
                connection.commit();
                baseLandSellParamBean.getMessage().append("\u5132\u5b58\u6210\u529f\uff01");
                baseLandSellParamBean.setSuccess(true);
            }
            catch (SQLException sQLException) {
                SqlUtil.rollback(connection);
                baseLandSellParamBean.getMessage().append("\u5132\u5b58\u5931\u6557\uff01" + sQLException.getMessage());
            }
        }
    }
}

