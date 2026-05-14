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
import moiland.baseland.action.bean.BaseLandAppraisalParamBean;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_APPRAISAL;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_APPRAISALA3_SCORE;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_MAIN;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_APPRAISAL;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_APPRAISALA3_SCORE;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_MAIN;
import moiland.baseland.pricerate.em.EnumPriceRateSubItem;
import moiland.baseland.report.param.bo.BaseLandReportParamDataBo;
import moiland.baseland.util.BaseLandParamFillHelper;
import moiland.baseland.util.BaseLandPriceRateFillHelper;

public class BaseLandAppraisalBo {
    NDAO_BASELAND_APPRAISAL resultDao = new NDAO_BASELAND_APPRAISAL();
    NDAO_BASELAND_APPRAISALA3_SCORE scoreDao = new NDAO_BASELAND_APPRAISALA3_SCORE();

    public void queryBaseLandAppraisal(BaseLandAppraisalParamBean baseLandAppraisalParamBean, Connection connection) throws Exception {
        if (!baseLandAppraisalParamBean.getResultVo().getBaseno().equals("") && !baseLandAppraisalParamBean.getResultVo().getYear().equals("")) {
            ArrayList<NVO_BASELAND_APPRAISAL> arrayList = this.resultDao.queryData(baseLandAppraisalParamBean.getResultVo().getYear(), baseLandAppraisalParamBean.getResultVo().getBaseno(), connection);
            if (arrayList.size() > 0) {
                new BaseLandParamFillHelper(baseLandAppraisalParamBean.getTarAppVo().getCity(), baseLandAppraisalParamBean.getTarAppVo().getYear(), connection).fillValue(baseLandAppraisalParamBean.getTarAppVo());
                baseLandAppraisalParamBean.setResultVo(arrayList.get(0));
                baseLandAppraisalParamBean.setMode("edit");
            } else {
                NVO_BASELAND_MAIN nVO_BASELAND_MAIN = new NDAO_BASELAND_MAIN().findByPk(baseLandAppraisalParamBean.getResultVo().getBaseno(), baseLandAppraisalParamBean.getResultVo().getYear(), connection);
                if (nVO_BASELAND_MAIN != null && baseLandAppraisalParamBean.getTarAppVo() != null) {
                    new BaseLandParamFillHelper(nVO_BASELAND_MAIN.getCity(), nVO_BASELAND_MAIN.getYear(), connection).fillValue(baseLandAppraisalParamBean.getTarAppVo());
                    this.fillTargetAppraisal(nVO_BASELAND_MAIN, baseLandAppraisalParamBean.getTarAppVo());
                }
                baseLandAppraisalParamBean.getResultVo().setYear(baseLandAppraisalParamBean.getResultVo().getYear());
                baseLandAppraisalParamBean.getResultVo().setBaseno(baseLandAppraisalParamBean.getResultVo().getBaseno());
                baseLandAppraisalParamBean.setMode("add");
            }
            this.setAs308Dv(baseLandAppraisalParamBean.getTarAppVo(), baseLandAppraisalParamBean.getScoreAppMap(), connection);
        }
    }

    public void setAs308Dv(NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE, TreeMap<String, NVO_BASELAND_APPRAISALA3_SCORE> treeMap, Connection connection) {
        String string = new BaseLandReportParamDataBo(nVO_BASELAND_APPRAISALA3_SCORE.getCity(), nVO_BASELAND_APPRAISALA3_SCORE.getYear()).getEditData(connection).getPrice_rate_type();
        EnumPriceRateSubItem enumPriceRateSubItem = EnumPriceRateSubItem.findSelfByBaseno(string, nVO_BASELAND_APPRAISALA3_SCORE.getBaseno());
        BaseLandPriceRateFillHelper baseLandPriceRateFillHelper = new BaseLandPriceRateFillHelper(nVO_BASELAND_APPRAISALA3_SCORE.getCity(), nVO_BASELAND_APPRAISALA3_SCORE.getDist(), nVO_BASELAND_APPRAISALA3_SCORE.getYear(), enumPriceRateSubItem);
        for (String string2 : treeMap.keySet()) {
            NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE2 = treeMap.get(string2);
            baseLandPriceRateFillHelper.setAs308Dv(nVO_BASELAND_APPRAISALA3_SCORE, nVO_BASELAND_APPRAISALA3_SCORE2, connection);
        }
    }

    public void fillTargetAppraisal(NVO_BASELAND_MAIN nVO_BASELAND_MAIN, NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE) {
        nVO_BASELAND_APPRAISALA3_SCORE.setAs308(nVO_BASELAND_MAIN.getPrice_date());
        nVO_BASELAND_APPRAISALA3_SCORE.setAs339(nVO_BASELAND_MAIN.getAa10());
        nVO_BASELAND_APPRAISALA3_SCORE.setAs301(nVO_BASELAND_MAIN.getLand_position());
        nVO_BASELAND_APPRAISALA3_SCORE.setAs340_ds(nVO_BASELAND_MAIN.getWidth());
        nVO_BASELAND_APPRAISALA3_SCORE.setAs341_ds(nVO_BASELAND_MAIN.getDeep());
    }

    public void saveBaseLandAppraisal(BaseLandAppraisalParamBean baseLandAppraisalParamBean, Connection connection) throws Exception {
        boolean bl;
        baseLandAppraisalParamBean.clearMessage();
        boolean bl2 = bl = !StringProcess.isEmpty(baseLandAppraisalParamBean.getResultVo().getYear()) && !StringProcess.isEmpty(baseLandAppraisalParamBean.getResultVo().getBaseno());
        if (bl) {
            ArrayList<NVO_BASELAND_APPRAISAL> arrayList = this.resultDao.queryData(baseLandAppraisalParamBean.getResultVo().getYear(), baseLandAppraisalParamBean.getResultVo().getBaseno(), connection);
            ArrayList<NVO_BASELAND_APPRAISALA3_SCORE> arrayList2 = this.scoreDao.queryData(baseLandAppraisalParamBean.getResultVo().getYear(), baseLandAppraisalParamBean.getResultVo().getBaseno(), connection);
            ArrayList<NVO_BASELAND_APPRAISALA3_SCORE> arrayList3 = new ArrayList<NVO_BASELAND_APPRAISALA3_SCORE>();
            arrayList3.addAll(baseLandAppraisalParamBean.getScoreAppMap().values());
            try {
                connection.setAutoCommit(false);
                this.resultDao.delete(arrayList, connection);
                this.resultDao.create(baseLandAppraisalParamBean.getResultVo(), connection);
                this.scoreDao.delete(arrayList2, connection);
                this.scoreDao.create(arrayList3, connection);
                connection.commit();
                baseLandAppraisalParamBean.getMessage().append("\u5132\u5b58\u6210\u529f\uff01");
                baseLandAppraisalParamBean.setSuccess(true);
            }
            catch (SQLException sQLException) {
                SqlUtil.rollback(connection);
                baseLandAppraisalParamBean.getMessage().append("\u5132\u5b58\u5931\u6557\uff01" + sQLException.getMessage());
                sQLException.printStackTrace();
            }
        }
    }
}

