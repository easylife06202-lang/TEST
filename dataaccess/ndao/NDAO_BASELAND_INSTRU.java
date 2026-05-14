/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.dataaccess.ndao;

import com.wfusion.dataaccess.dao.DaoBase;
import com.wfusion.util.SqlBuilder;
import com.wfusion.util.SqlUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_INSTRU;
import moiland.landuse.util.Us7Ascii;

public class NDAO_BASELAND_INSTRU
extends DaoBase {
    public NDAO_BASELAND_INSTRU() {
        this.fullClassName = "moiland.baseland.dataaccess.nvo.NVO_BASELAND_INSTRU";
        this.us = new Us7Ascii(false);
    }

    public ArrayList<NVO_BASELAND_INSTRU> queryAllData(Connection connection) throws Exception {
        String string = " SELECT * FROM BASELAND_INSTRU ORDER BY INSTRU_CODE ";
        return this.findBySql(string, connection);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int clearAllData(Connection connection) throws SQLException {
        String string = " DELETE FROM BASELAND_INSTRU ";
        try {
            this._stmt = connection.createStatement();
            int n = this._stmt.executeUpdate(string);
            return n;
        }
        finally {
            SqlUtil.close(this._stmt);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public NVO_BASELAND_INSTRU findByPk(String string, Connection connection) throws Exception {
        String string2 = " SELECT * FROM BASELAND_INSTRU WHERE INSTRU_CODE=@@ ";
        SqlBuilder sqlBuilder = new SqlBuilder(string2);
        int n = 0;
        sqlBuilder.setString(n++, string);
        try {
            this._stmt = connection.createStatement();
            this._rs = this._stmt.executeQuery(sqlBuilder.getSql());
            if (this._rs.next()) {
                NVO_BASELAND_INSTRU nVO_BASELAND_INSTRU = (NVO_BASELAND_INSTRU)this.getResults(this._rs);
                return nVO_BASELAND_INSTRU;
            }
        }
        finally {
            SqlUtil.close(this._rs);
            SqlUtil.close(this._stmt);
        }
        return null;
    }
}

