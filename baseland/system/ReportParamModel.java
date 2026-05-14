/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.baseland.system;

import com.wfusion.baseland.basic.Model;
import com.wfusion.fx.util.ExceptionDialog;
import com.wfusion.util.ConnectionFactory;
import com.wfusion.util.OptionPair;
import com.wfusion.util.SqlUtil;
import java.sql.Connection;
import java.util.ArrayList;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_REPORT_PARAM;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_REPORT_PARAM;
import moiland.baseland.report.param.bo.BaseLandReportParamDataBo;

public class ReportParamModel
extends Model {
    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public NVO_BASELAND_REPORT_PARAM getEditData(String string, String string2) {
        Connection connection = null;
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        arrayList.add(new OptionPair("", "\u8acb\u9078\u64c7..."));
        try {
            NVO_BASELAND_REPORT_PARAM nVO_BASELAND_REPORT_PARAM;
            connection = ConnectionFactory.createConnection("sqlite", SQLITE_PATH + "BaseLand.db", "", "", "", "");
            BaseLandReportParamDataBo baseLandReportParamDataBo = new BaseLandReportParamDataBo(string, string2);
            NVO_BASELAND_REPORT_PARAM nVO_BASELAND_REPORT_PARAM2 = nVO_BASELAND_REPORT_PARAM = baseLandReportParamDataBo.getEditData(connection);
            SqlUtil.close(connection);
            return nVO_BASELAND_REPORT_PARAM2;
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean save(NVO_BASELAND_REPORT_PARAM nVO_BASELAND_REPORT_PARAM, String string, String string2) {
        Connection connection = null;
        boolean bl = false;
        try {
            NDAO_BASELAND_REPORT_PARAM nDAO_BASELAND_REPORT_PARAM = new NDAO_BASELAND_REPORT_PARAM();
            connection = ConnectionFactory.createConnection("sqlite", SQLITE_PATH + "BaseLand.db", "", "", "", "");
            BaseLandReportParamDataBo baseLandReportParamDataBo = new BaseLandReportParamDataBo(string, string2);
            connection.setAutoCommit(false);
            nDAO_BASELAND_REPORT_PARAM.delete(nVO_BASELAND_REPORT_PARAM, connection);
            nDAO_BASELAND_REPORT_PARAM.create(nVO_BASELAND_REPORT_PARAM, connection);
            connection.commit();
            bl = true;
            SqlUtil.close(connection);
        }
        catch (Exception exception) {
            SqlUtil.rollback(connection);
            ExceptionDialog.show(exception);
        }
        finally {
            SqlUtil.close(connection);
        }
        return bl;
    }
}

