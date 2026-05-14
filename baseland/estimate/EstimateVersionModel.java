/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.baseland.estimate;

import com.wfusion.baseland.basic.Model;
import com.wfusion.util.SUtility;
import com.wfusion.util.SqlUtil;
import java.sql.Connection;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_MAIN;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_MAIN;

public class EstimateVersionModel
extends Model {
    public NVO_BASELAND_MAIN ver_formal = null;
    public NVO_BASELAND_MAIN ver_A = null;
    public NVO_BASELAND_MAIN ver_B = null;
    public NVO_BASELAND_MAIN ver_C = null;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void queryVersion(String string, String string2) {
        block5: {
            Connection connection = null;
            Connection connection2 = null;
            Connection connection3 = null;
            Connection connection4 = null;
            try {
                connection = this.getConnection();
                connection2 = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand_A.db");
                connection3 = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand_B.db");
                connection4 = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand_C.db");
                NDAO_BASELAND_MAIN nDAO_BASELAND_MAIN = new NDAO_BASELAND_MAIN();
                NVO_BASELAND_MAIN nVO_BASELAND_MAIN = new NVO_BASELAND_MAIN();
                nVO_BASELAND_MAIN.setYear(string);
                nVO_BASELAND_MAIN.setBaseno(string2);
                this.ver_formal = (NVO_BASELAND_MAIN)nDAO_BASELAND_MAIN.findByPk(nVO_BASELAND_MAIN, connection);
                this.ver_A = (NVO_BASELAND_MAIN)nDAO_BASELAND_MAIN.findByPk(nVO_BASELAND_MAIN, connection2);
                this.ver_B = (NVO_BASELAND_MAIN)nDAO_BASELAND_MAIN.findByPk(nVO_BASELAND_MAIN, connection3);
                this.ver_C = (NVO_BASELAND_MAIN)nDAO_BASELAND_MAIN.findByPk(nVO_BASELAND_MAIN, connection4);
                SqlUtil.close(connection);
            }
            catch (Exception exception) {
                exception.printStackTrace();
                break block5;
            }
            finally {
                SqlUtil.close(connection);
                SqlUtil.close(connection2);
                SqlUtil.close(connection3);
                SqlUtil.close(connection4);
            }
            SqlUtil.close(connection2);
            SqlUtil.close(connection3);
            SqlUtil.close(connection4);
        }
    }
}

