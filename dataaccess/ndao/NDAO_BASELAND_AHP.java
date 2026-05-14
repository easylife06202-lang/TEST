/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.dataaccess.ndao;

import com.wfusion.dataaccess.dao.DaoBase;
import com.wfusion.util.SqlBuilder;
import com.wfusion.util.SqlUtil;
import com.wfusion.util.StringProcess;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_AHP;
import moiland.landuse.util.Us7Ascii;

public class NDAO_BASELAND_AHP
extends DaoBase {
    public NDAO_BASELAND_AHP() {
        this.fullClassName = "moiland.baseland.dataaccess.nvo.NVO_BASELAND_AHP";
        this.us = new Us7Ascii(false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ArrayList<String> getExistYearList(String string, Connection connection) throws SQLException {
        String string2 = " SELECT * FROM BASELAND_AHP WHERE CITY=@@ ORDER BY YEAR DESC ";
        SqlBuilder sqlBuilder = new SqlBuilder(string2);
        int n = 0;
        sqlBuilder.setString(n++, string);
        ArrayList<String> arrayList = new ArrayList<String>();
        try {
            this._stmt = connection.createStatement();
            this._rs = this._stmt.executeQuery(sqlBuilder.getSql());
            while (this._rs.next()) {
                arrayList.add(this._rs.getString("YEAR"));
            }
        }
        finally {
            SqlUtil.close(this._rs);
            SqlUtil.close(this._stmt);
        }
        return arrayList;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public NVO_BASELAND_AHP findByPk(String string, String string2, Connection connection) throws Exception {
        String string3 = " SELECT * FROM BASELAND_AHP WHERE CITY=@@ AND YEAR=@@ ";
        SqlBuilder sqlBuilder = new SqlBuilder(string3);
        int n = 0;
        sqlBuilder.setString(n++, string);
        sqlBuilder.setString(n++, string2);
        try {
            this._stmt = connection.createStatement();
            this._rs = this._stmt.executeQuery(sqlBuilder.getSql());
            if (this._rs.next()) {
                NVO_BASELAND_AHP nVO_BASELAND_AHP = (NVO_BASELAND_AHP)this.getResults(this._rs);
                return nVO_BASELAND_AHP;
            }
        }
        finally {
            SqlUtil.close(this._rs);
            SqlUtil.close(this._stmt);
        }
        return null;
    }

    public ArrayList<NVO_BASELAND_AHP> queryDataForCopy(String string, String string2, Connection connection) throws Exception {
        boolean bl = !StringProcess.isEmpty(string) && !StringProcess.isEmpty(string2);
        ArrayList arrayList = new ArrayList();
        if (bl) {
            SqlBuilder sqlBuilder = new SqlBuilder();
            StringBuffer stringBuffer = new StringBuffer();
            ArrayList<String> arrayList2 = new ArrayList<String>();
            stringBuffer.append(" SELECT * FROM BASELAND_AHP ");
            stringBuffer.append(" WHERE CITY=@@ AND YEAR=@@");
            arrayList2.add(string);
            arrayList2.add(string2);
            stringBuffer.append(" ORDER BY CITY ");
            sqlBuilder.setPreSql(stringBuffer.toString());
            sqlBuilder.setValueArray(arrayList2.toArray(), true);
            NDAO_BASELAND_AHP nDAO_BASELAND_AHP = new NDAO_BASELAND_AHP();
            arrayList = nDAO_BASELAND_AHP.findBySql(sqlBuilder.getSql(), connection);
        }
        return arrayList;
    }
}

