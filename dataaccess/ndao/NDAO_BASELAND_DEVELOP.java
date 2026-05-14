/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.dataaccess.ndao;

import com.wfusion.dataaccess.dao.DaoBase;
import com.wfusion.util.SqlBuilder;
import com.wfusion.util.SqlUtil;
import java.sql.Connection;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_DEVELOP;
import moiland.landuse.util.Us7Ascii;

public class NDAO_BASELAND_DEVELOP
extends DaoBase {
    public NDAO_BASELAND_DEVELOP() {
        this.fullClassName = "moiland.baseland.dataaccess.nvo.NVO_BASELAND_DEVELOP";
        this.us = new Us7Ascii(false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public NVO_BASELAND_DEVELOP findByPk(String string, String string2, Connection connection) throws Exception {
        String string3 = " SELECT * FROM BASELAND_DEVELOP WHERE BASENO=@@ AND YEAR=@@ ";
        SqlBuilder sqlBuilder = new SqlBuilder(string3);
        int n = 0;
        sqlBuilder.setString(n++, string);
        sqlBuilder.setString(n++, string2);
        try {
            this._stmt = connection.createStatement();
            this._rs = this._stmt.executeQuery(sqlBuilder.getSql());
            if (this._rs.next()) {
                NVO_BASELAND_DEVELOP nVO_BASELAND_DEVELOP = (NVO_BASELAND_DEVELOP)this.getResults(this._rs);
                return nVO_BASELAND_DEVELOP;
            }
        }
        finally {
            SqlUtil.close(this._rs);
            SqlUtil.close(this._stmt);
        }
        return null;
    }
}

