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
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_PRICERATE;
import moiland.landuse.util.Us7Ascii;

public class NDAO_BASELAND_PRICERATE
extends DaoBase {
    public NDAO_BASELAND_PRICERATE() {
        this.fullClassName = "moiland.baseland.dataaccess.nvo.NVO_BASELAND_PRICERATE";
        this.us = new Us7Ascii(false);
    }

    public ArrayList<NVO_BASELAND_PRICERATE> queryOneSubTypeData(String string, String string2, String string3, String string4, Connection connection) throws Exception {
        String string5 = " SELECT * FROM BASELAND_PRICERATE WHERE CITY=@@ AND RATE_TYPE=@@  AND DIST=@@ AND YEAR=@@ ORDER BY YM ";
        SqlBuilder sqlBuilder = new SqlBuilder(string5);
        int n = 0;
        sqlBuilder.setString(n++, string);
        sqlBuilder.setString(n++, string2);
        sqlBuilder.setString(n++, string3);
        sqlBuilder.setString(n++, string4);
        return this.findBySql(sqlBuilder.getSql(), connection);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int clearOneSubTypeData(String string, String string2, String string3, String string4, Connection connection) throws SQLException {
        String string5 = " DELETE FROM BASELAND_PRICERATE WHERE CITY=@@ AND RATE_TYPE=@@ AND DIST=@@ AND YEAR=@@ ";
        SqlBuilder sqlBuilder = new SqlBuilder(string5);
        int n = 0;
        sqlBuilder.setString(n++, string);
        sqlBuilder.setString(n++, string2);
        sqlBuilder.setString(n++, string3);
        sqlBuilder.setString(n++, string4);
        try {
            this._stmt = connection.createStatement();
            int n2 = this._stmt.executeUpdate(sqlBuilder.getSql());
            return n2;
        }
        finally {
            SqlUtil.close(this._stmt);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ArrayList<String> getExistYearList(String string, String string2, String string3, Connection connection) throws SQLException {
        String string4 = " SELECT DISTINCT * FROM BASELAND_PRICERATE WHERE CITY=@@ AND RATE_TYPE=@@ AND DIST=@@ ORDER BY YEAR DESC ";
        SqlBuilder sqlBuilder = new SqlBuilder(string4);
        int n = 0;
        sqlBuilder.setString(n++, string);
        sqlBuilder.setString(n++, string2);
        sqlBuilder.setString(n++, string3);
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
    public NVO_BASELAND_PRICERATE findByPk(String string, String string2, String string3, String string4, String string5, Connection connection) throws Exception {
        String string6 = " SELECT * FROM BASELAND_PRICERATE WHERE CITY=@@ AND RATE_TYPE=@@ AND DIST=@@ AND YEAR=@@ AND YM=@@ ";
        SqlBuilder sqlBuilder = new SqlBuilder(string6);
        int n = 0;
        sqlBuilder.setString(n++, string);
        sqlBuilder.setString(n++, string2);
        sqlBuilder.setString(n++, string3);
        sqlBuilder.setString(n++, string4);
        sqlBuilder.setString(n++, string5);
        try {
            this._stmt = connection.createStatement();
            this._rs = this._stmt.executeQuery(sqlBuilder.getSql());
            if (this._rs.next()) {
                NVO_BASELAND_PRICERATE nVO_BASELAND_PRICERATE = (NVO_BASELAND_PRICERATE)this.getResults(this._rs);
                return nVO_BASELAND_PRICERATE;
            }
        }
        finally {
            SqlUtil.close(this._rs);
            SqlUtil.close(this._stmt);
        }
        return null;
    }

    public ArrayList<NVO_BASELAND_PRICERATE> queryDataForCopy(String string, String string2, String string3, String string4, Connection connection) throws Exception {
        boolean bl = !StringProcess.isEmpty(string) && !StringProcess.isEmpty(string2) && !StringProcess.isEmpty(string3) && !StringProcess.isEmpty(string4);
        ArrayList arrayList = new ArrayList();
        if (bl) {
            SqlBuilder sqlBuilder = new SqlBuilder();
            StringBuffer stringBuffer = new StringBuffer();
            ArrayList<String> arrayList2 = new ArrayList<String>();
            stringBuffer.append(" SELECT * FROM BASELAND_PRICERATE ");
            stringBuffer.append(" WHERE CITY=@@ AND RATE_TYPE=@@ AND DIST=@@ AND YEAR=@@ ");
            arrayList2.add(string);
            arrayList2.add(string2);
            arrayList2.add(string3);
            arrayList2.add(string4);
            stringBuffer.append(" ORDER BY CITY,DIST ");
            sqlBuilder.setPreSql(stringBuffer.toString());
            sqlBuilder.setValueArray(arrayList2.toArray(), true);
            NDAO_BASELAND_PRICERATE nDAO_BASELAND_PRICERATE = new NDAO_BASELAND_PRICERATE();
            arrayList = nDAO_BASELAND_PRICERATE.findBySql(sqlBuilder.getSql(), connection);
        }
        return arrayList;
    }
}

