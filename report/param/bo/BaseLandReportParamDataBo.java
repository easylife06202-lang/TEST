/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.report.param.bo;

import com.wfusion.util.SqlUtil;
import java.sql.Connection;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_REPORT_PARAM;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_REPORT_PARAM;

public class BaseLandReportParamDataBo {
    private String city = "";
    private String year = "";
    private NDAO_BASELAND_REPORT_PARAM dao = new NDAO_BASELAND_REPORT_PARAM();

    public BaseLandReportParamDataBo(String string, String string2) {
        this.city = string;
        this.year = string2;
    }

    public NVO_BASELAND_REPORT_PARAM getEditData(Connection connection) {
        NVO_BASELAND_REPORT_PARAM nVO_BASELAND_REPORT_PARAM = null;
        try {
            nVO_BASELAND_REPORT_PARAM = this.dao.findByPk(this.city, this.year, connection);
            if (nVO_BASELAND_REPORT_PARAM == null) {
                nVO_BASELAND_REPORT_PARAM = new NVO_BASELAND_REPORT_PARAM();
                nVO_BASELAND_REPORT_PARAM.setCity(this.city);
                nVO_BASELAND_REPORT_PARAM.setYear(this.year);
                nVO_BASELAND_REPORT_PARAM.setHaveData(false);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return nVO_BASELAND_REPORT_PARAM;
    }

    public void saveData(NVO_BASELAND_REPORT_PARAM nVO_BASELAND_REPORT_PARAM, Connection connection) throws Exception {
        try {
            connection.setAutoCommit(false);
            this.dao.delete(nVO_BASELAND_REPORT_PARAM, connection);
            this.dao.create(nVO_BASELAND_REPORT_PARAM, connection);
            connection.commit();
        }
        catch (Exception exception) {
            SqlUtil.rollback(connection);
            exception.printStackTrace();
            throw exception;
        }
    }
}

