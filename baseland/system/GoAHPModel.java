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
import java.sql.SQLException;
import java.util.ArrayList;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_AHP;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_AHP;
import moiland.baseland.param.bo.BaseLandAhpParamDataBo;

public class GoAHPModel
extends Model {
    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public NVO_BASELAND_AHP getEditData(String string, String string2) {
        Connection connection = null;
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        arrayList.add(new OptionPair("", "\u8acb\u9078\u64c7..."));
        try {
            NVO_BASELAND_AHP nVO_BASELAND_AHP;
            connection = ConnectionFactory.createConnection("sqlite", SQLITE_PATH + "BaseLand.db", "", "", "", "");
            BaseLandAhpParamDataBo baseLandAhpParamDataBo = new BaseLandAhpParamDataBo(string, string2);
            NVO_BASELAND_AHP nVO_BASELAND_AHP2 = nVO_BASELAND_AHP = baseLandAhpParamDataBo.getEditData(connection);
            SqlUtil.close(connection);
            return nVO_BASELAND_AHP2;
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
    public boolean save(NVO_BASELAND_AHP nVO_BASELAND_AHP, String string, String string2) {
        Connection connection = null;
        boolean bl = false;
        try {
            NDAO_BASELAND_AHP nDAO_BASELAND_AHP = new NDAO_BASELAND_AHP();
            connection = this.getConnection();
            connection.setAutoCommit(false);
            nDAO_BASELAND_AHP.delete(nVO_BASELAND_AHP, connection);
            nDAO_BASELAND_AHP.create(nVO_BASELAND_AHP, connection);
            connection.commit();
            bl = true;
        }
        catch (SQLException sQLException) {
            try {
                SqlUtil.rollback(connection);
                ExceptionDialog.show(sQLException);
            }
            catch (Throwable throwable) {
                SqlUtil.close(connection);
                throw throwable;
            }
            SqlUtil.close(connection);
        }
        SqlUtil.close(connection);
        return bl;
    }
}

