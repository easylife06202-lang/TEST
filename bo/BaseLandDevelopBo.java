/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.bo;

import com.wfusion.util.SqlUtil;
import com.wfusion.util.StringProcess;
import java.sql.Connection;
import moiland.baseland.action.bean.BaseLandDevelopParamBean;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_DEVELOP;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_DEVELOP_EXT;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_MAIN;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_DEVELOP;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_DEVELOP_EXT;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_MAIN;
import moiland.baseland.util.BaseLandBuildStdPriceAdjustRateFillHelper;
import moiland.baseland.util.BaseLandParamFillHelper;

public class BaseLandDevelopBo {
    NDAO_BASELAND_MAIN daoMain = new NDAO_BASELAND_MAIN();
    NDAO_BASELAND_DEVELOP daoDevelop = new NDAO_BASELAND_DEVELOP();
    NDAO_BASELAND_DEVELOP_EXT daoDevelopExt = new NDAO_BASELAND_DEVELOP_EXT();

    public void queryBaseLandDevelop(BaseLandDevelopParamBean baseLandDevelopParamBean, Connection connection) throws Exception {
        NVO_BASELAND_MAIN nVO_BASELAND_MAIN = this.daoMain.findByPk(baseLandDevelopParamBean.getVoMain().getBaseno(), baseLandDevelopParamBean.getVoMain().getYear(), connection);
        if (nVO_BASELAND_MAIN != null) {
            baseLandDevelopParamBean.setVoMain(nVO_BASELAND_MAIN);
        } else {
            nVO_BASELAND_MAIN = new NVO_BASELAND_MAIN();
            nVO_BASELAND_MAIN.setBaseno(baseLandDevelopParamBean.getVoMain().getBaseno());
            nVO_BASELAND_MAIN.setBaseno(baseLandDevelopParamBean.getVoMain().getYear());
            nVO_BASELAND_MAIN.setPrice_date(baseLandDevelopParamBean.getVoMain().getYear() + "0331");
        }
        NVO_BASELAND_DEVELOP nVO_BASELAND_DEVELOP = this.daoDevelop.findByPk(baseLandDevelopParamBean.getVoDevelop().getBaseno(), baseLandDevelopParamBean.getVoDevelop().getYear(), connection);
        if (nVO_BASELAND_DEVELOP != null) {
            baseLandDevelopParamBean.setVoDevelop(nVO_BASELAND_DEVELOP);
        } else {
            nVO_BASELAND_DEVELOP = baseLandDevelopParamBean.getVoDevelop();
            new BaseLandParamFillHelper(nVO_BASELAND_DEVELOP.getCity(), nVO_BASELAND_DEVELOP.getYear(), connection).fillValue(nVO_BASELAND_DEVELOP);
            new BaseLandBuildStdPriceAdjustRateFillHelper(nVO_BASELAND_DEVELOP.getCity(), nVO_BASELAND_DEVELOP.getYear()).setBuildCostAdjustRate(nVO_BASELAND_DEVELOP, nVO_BASELAND_MAIN.getPrice_date(), connection);
        }
        NVO_BASELAND_DEVELOP_EXT nVO_BASELAND_DEVELOP_EXT = this.daoDevelopExt.findByPk(baseLandDevelopParamBean.getVoDevelopExt().getBaseno(), baseLandDevelopParamBean.getVoDevelopExt().getYear(), connection);
        if (nVO_BASELAND_DEVELOP_EXT != null) {
            baseLandDevelopParamBean.setVoDevelopExt(nVO_BASELAND_DEVELOP_EXT);
        }
    }

    public void saveBaseLandDevelop(BaseLandDevelopParamBean baseLandDevelopParamBean, Connection connection) {
        baseLandDevelopParamBean.clearMessage();
        try {
            connection.setAutoCommit(false);
            this.saveBaseLandDevelopMain(baseLandDevelopParamBean, connection);
            this.saveBaseLandDevelopExt(baseLandDevelopParamBean, connection);
            connection.commit();
            baseLandDevelopParamBean.getMessage().append("\u5132\u5b58\u6210\u529f\uff01");
            baseLandDevelopParamBean.setSuccess(true);
        }
        catch (Exception exception) {
            SqlUtil.rollback(connection);
            baseLandDevelopParamBean.setSuccess(false);
            baseLandDevelopParamBean.getMessage().append("\u5132\u5b58\u5931\u6557\uff01" + exception.getMessage());
            exception.printStackTrace();
        }
    }

    private void saveBaseLandDevelopMain(BaseLandDevelopParamBean baseLandDevelopParamBean, Connection connection) throws Exception {
        boolean bl;
        NVO_BASELAND_DEVELOP nVO_BASELAND_DEVELOP = baseLandDevelopParamBean.getVoDevelop();
        boolean bl2 = bl = !StringProcess.isEmpty(nVO_BASELAND_DEVELOP.getYear()) && !StringProcess.isEmpty(nVO_BASELAND_DEVELOP.getBaseno());
        if (nVO_BASELAND_DEVELOP.getAre_area() <= 0.0) {
            throw new Exception("\u5bb9\u7a4d\u9762\u7a4d\u70ba\u7a7a\uff01");
        }
        if (bl) {
            try {
                this.daoDevelop.checkDataSize(nVO_BASELAND_DEVELOP, connection);
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
            this.daoDevelop.delete(nVO_BASELAND_DEVELOP, connection);
            this.daoDevelop.create(nVO_BASELAND_DEVELOP, connection);
        }
    }

    private void saveBaseLandDevelopExt(BaseLandDevelopParamBean baseLandDevelopParamBean, Connection connection) throws Exception {
        boolean bl;
        NVO_BASELAND_DEVELOP_EXT nVO_BASELAND_DEVELOP_EXT = baseLandDevelopParamBean.getVoDevelopExt();
        boolean bl2 = bl = !StringProcess.isEmpty(nVO_BASELAND_DEVELOP_EXT.getYear()) && !StringProcess.isEmpty(nVO_BASELAND_DEVELOP_EXT.getBaseno()) && !StringProcess.isEmpty(nVO_BASELAND_DEVELOP_EXT.getLand_position_pseudo()) && nVO_BASELAND_DEVELOP_EXT.getArea_pseudo() > 0.0;
        if (bl) {
            try {
                this.daoDevelopExt.checkDataSize(nVO_BASELAND_DEVELOP_EXT, connection);
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
            this.daoDevelopExt.delete(nVO_BASELAND_DEVELOP_EXT, connection);
            this.daoDevelopExt.create(nVO_BASELAND_DEVELOP_EXT, connection);
        }
    }
}

