/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.bo;

import com.wfusion.util.SqlUtil;
import com.wfusion.util.StringProcess;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeMap;
import moiland.baseland.action.bean.BaseLandRentParamBean;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_RENT;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_RENT_MONTH;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT_MONTH;
import moiland.baseland.util.BaseLandParamFillHelper;

public class BaseLandRentBo {
    NDAO_BASELAND_RENT ndao = new NDAO_BASELAND_RENT();
    NDAO_BASELAND_RENT_MONTH monthDao = new NDAO_BASELAND_RENT_MONTH();

    public void queryBaseLandRent(BaseLandRentParamBean baseLandRentParamBean, Connection connection) throws Exception {
        ArrayList<NVO_BASELAND_RENT> arrayList = this.ndao.queryData(baseLandRentParamBean.getcRentvo().getYear(), baseLandRentParamBean.getcRentvo().getBaseno(), connection);
        if (arrayList.size() > 0) {
            baseLandRentParamBean.setcRentvo(arrayList.get(0));
            baseLandRentParamBean.setMode("edit");
        } else {
            new BaseLandParamFillHelper(baseLandRentParamBean.getcRentvo().getCity(), baseLandRentParamBean.getcRentvo().getYear(), connection).fillValue(baseLandRentParamBean.getcRentvo());
            baseLandRentParamBean.setMode("add");
        }
    }

    public void queryBaseLandRentMonth(BaseLandRentParamBean baseLandRentParamBean, Connection connection) throws Exception {
        TreeMap<String, NVO_BASELAND_RENT_MONTH> treeMap = this.monthDao.queryDataMap(baseLandRentParamBean.getcRentvo().getYear(), baseLandRentParamBean.getcRentvo().getBaseno(), connection);
        if (treeMap.size() > 0) {
            baseLandRentParamBean.setMonthMap(treeMap);
            baseLandRentParamBean.setMode("edit");
        } else {
            baseLandRentParamBean.setMode("add");
            for (int i = 1; i <= 3; ++i) {
                String string = String.valueOf(i);
                NVO_BASELAND_RENT_MONTH nVO_BASELAND_RENT_MONTH = new NVO_BASELAND_RENT_MONTH();
                nVO_BASELAND_RENT_MONTH.setYear(baseLandRentParamBean.getcRentvo().getYear());
                nVO_BASELAND_RENT_MONTH.setBaseno(baseLandRentParamBean.getcRentvo().getBaseno());
                nVO_BASELAND_RENT_MONTH.setOrders(string);
                nVO_BASELAND_RENT_MONTH.setRent_caseno(string);
                nVO_BASELAND_RENT_MONTH.setType_adj(100.0);
                nVO_BASELAND_RENT_MONTH.setDate_adj(100.0);
                nVO_BASELAND_RENT_MONTH.setReg_adj(100.0);
                nVO_BASELAND_RENT_MONTH.setSpe_adj(100.0);
                treeMap.put(string, nVO_BASELAND_RENT_MONTH);
            }
            baseLandRentParamBean.setMonthMap(treeMap);
        }
    }

    public void saveBaseBaseLandRent(BaseLandRentParamBean baseLandRentParamBean, Connection connection) throws Exception {
        boolean bl;
        baseLandRentParamBean.clearMessage();
        NVO_BASELAND_RENT nVO_BASELAND_RENT = baseLandRentParamBean.getcRentvo();
        boolean bl2 = bl = !StringProcess.isEmpty(nVO_BASELAND_RENT.getYear()) && !StringProcess.isEmpty(nVO_BASELAND_RENT.getBaseno());
        if (nVO_BASELAND_RENT.getCr03() <= 0.0) {
            baseLandRentParamBean.getMessage().append("\u6536\u76ca\u9762\u7a4d\u70ba\u7a7a\uff01\u5132\u5b58\u5931\u6557\uff01");
            bl = false;
        }
        if (bl) {
            ArrayList<NVO_BASELAND_RENT> arrayList = this.ndao.queryData(nVO_BASELAND_RENT.getYear(), nVO_BASELAND_RENT.getBaseno(), connection);
            ArrayList<NVO_BASELAND_RENT_MONTH> arrayList2 = this.monthDao.queryData(nVO_BASELAND_RENT.getYear(), nVO_BASELAND_RENT.getBaseno(), connection);
            ArrayList<NVO_BASELAND_RENT_MONTH> arrayList3 = new ArrayList<NVO_BASELAND_RENT_MONTH>();
            arrayList3.addAll(baseLandRentParamBean.getMonthMap().values());
            try {
                connection.setAutoCommit(false);
                this.ndao.delete(arrayList, connection);
                this.ndao.create(nVO_BASELAND_RENT, connection);
                this.monthDao.delete(arrayList2, connection);
                this.monthDao.create(arrayList3, connection);
                connection.commit();
                baseLandRentParamBean.getMessage().append("\u5132\u5b58\u6210\u529f\uff01");
                baseLandRentParamBean.setSuccess(true);
            }
            catch (SQLException sQLException) {
                SqlUtil.rollback(connection);
                baseLandRentParamBean.getMessage().append("\u5132\u5b58\u5931\u6557\uff01" + sQLException.getMessage());
            }
        }
    }
}

