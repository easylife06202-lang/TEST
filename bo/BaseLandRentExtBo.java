/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.bo;

import com.wfusion.util.SqlUtil;
import com.wfusion.util.StringProcess;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import moiland.baseland.action.bean.BaseLandRentExtParamBean;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_RENT_EXT;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT_EXT;
import moiland.baseland.util.BaseLandBuildStdPriceAdjustRateFillHelper;
import moiland.baseland.util.BaseLandParamFillHelper;

public class BaseLandRentExtBo {
    NDAO_BASELAND_RENT_EXT ndao = new NDAO_BASELAND_RENT_EXT();

    public void queryBaseLandRentExt(BaseLandRentExtParamBean baseLandRentExtParamBean, Connection connection) throws Exception {
        String string = baseLandRentExtParamBean.getcRenextvo().getCity();
        String string2 = baseLandRentExtParamBean.getcRenextvo().getYear();
        String string3 = string2 + "0331";
        ArrayList<NVO_BASELAND_RENT_EXT> arrayList = this.ndao.queryData(string2, baseLandRentExtParamBean.getcRenextvo().getBaseno(), connection);
        if (arrayList.size() > 0) {
            baseLandRentExtParamBean.setcRenextvo(arrayList.get(0));
            baseLandRentExtParamBean.setMode("edit");
        } else {
            new BaseLandParamFillHelper(string, string2, connection).fillValue(baseLandRentExtParamBean.getcRenextvo());
            new BaseLandBuildStdPriceAdjustRateFillHelper(string, string2).setBuildCostAdjustRate(baseLandRentExtParamBean.getcRenextvo(), string3, connection);
            baseLandRentExtParamBean.getcRenextvo().setCre33(string3);
            baseLandRentExtParamBean.setMode("add");
        }
    }

    public void saveBaseBaseLandRentExt(BaseLandRentExtParamBean baseLandRentExtParamBean, Connection connection) throws Exception {
        boolean bl;
        baseLandRentExtParamBean.clearMessage();
        NVO_BASELAND_RENT_EXT nVO_BASELAND_RENT_EXT = baseLandRentExtParamBean.getcRenextvo();
        boolean bl2 = bl = !StringProcess.isEmpty(nVO_BASELAND_RENT_EXT.getYear()) && !StringProcess.isEmpty(nVO_BASELAND_RENT_EXT.getBaseno());
        if (StringProcess.isEmpty(nVO_BASELAND_RENT_EXT.getCre01())) {
            baseLandRentExtParamBean.getMessage().append("\u5efa\u865f\u70ba\u7a7a\uff01\u5132\u5b58\u5931\u6557\uff01");
            bl = false;
        }
        System.out.println("isTrue=" + bl);
        if (bl) {
            ArrayList<NVO_BASELAND_RENT_EXT> arrayList = this.ndao.queryData(nVO_BASELAND_RENT_EXT.getYear(), nVO_BASELAND_RENT_EXT.getBaseno(), connection);
            try {
                connection.setAutoCommit(false);
                this.ndao.delete(arrayList, connection);
                this.ndao.create(nVO_BASELAND_RENT_EXT, connection);
                connection.commit();
                baseLandRentExtParamBean.getMessage().append("\u5132\u5b58\u6210\u529f\uff01");
                baseLandRentExtParamBean.setSuccess(true);
            }
            catch (SQLException sQLException) {
                SqlUtil.rollback(connection);
                baseLandRentExtParamBean.getMessage().append("\u5132\u5b58\u5931\u6557\uff01" + sQLException.getMessage());
            }
        }
    }
}

