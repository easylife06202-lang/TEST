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
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_BUILDPRICE_RATIO;
import moiland.landuse.util.Us7Ascii;

public class NDAO_BASELAND_BUILDPRICE_RATIO
extends DaoBase {
    public NDAO_BASELAND_BUILDPRICE_RATIO() {
        this.fullClassName = "moiland.baseland.dataaccess.nvo.NVO_BASELAND_BUILDPRICE_RATIO";
        this.us = new Us7Ascii(false);
    }

    public ArrayList<NVO_BASELAND_BUILDPRICE_RATIO> queryByBasedate(String string, Connection connection) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" SELECT * FROM BASELAND_BUILDPRICE_RATIO WHERE BASEDATE=@@ ");
        stringBuffer.append(" ORDER BY YEAR, MONTH ");
        SqlBuilder sqlBuilder = new SqlBuilder(stringBuffer.toString());
        int n = 0;
        sqlBuilder.setString(n++, string);
        return this.findBySql(sqlBuilder.getSql(), connection);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int clearByBasedate(String string, Connection connection) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" DELETE FROM BASELAND_BUILDPRICE_RATIO WHERE BASEDATE=@@ ");
        SqlBuilder sqlBuilder = new SqlBuilder(stringBuffer.toString());
        int n = 0;
        sqlBuilder.setString(n++, string);
        try {
            this._stmt = connection.createStatement();
            int n2 = this._stmt.executeUpdate(sqlBuilder.getSql());
            return n2;
        }
        finally {
            this._stmt.close();
        }
    }

    public ArrayList<NVO_BASELAND_BUILDPRICE_RATIO> queryOnePreiodData(String string, String string2, Connection connection) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" SELECT * FROM BASELAND_BUILDPRICE_RATIO WHERE BASEDATE=@@ AND YEAR=@@ AND MONTH<='03' ");
        stringBuffer.append(" UNION ");
        stringBuffer.append(" SELECT * FROM BASELAND_BUILDPRICE_RATIO WHERE BASEDATE=@@ AND YEAR=@@ ");
        stringBuffer.append(" UNION ");
        stringBuffer.append(" SELECT * FROM BASELAND_BUILDPRICE_RATIO WHERE BASEDATE=@@ AND YEAR=@@ AND MONTH>='10' ");
        stringBuffer.append(" ORDER BY YEAR, MONTH ");
        SqlBuilder sqlBuilder = new SqlBuilder(stringBuffer.toString());
        String string3 = StringProcess.decrement(string2, 3);
        String string4 = StringProcess.decrement(string3, 3);
        int n = 0;
        sqlBuilder.setString(n++, string);
        sqlBuilder.setString(n++, string2);
        sqlBuilder.setString(n++, string);
        sqlBuilder.setString(n++, string3);
        sqlBuilder.setString(n++, string);
        sqlBuilder.setString(n++, string4);
        return this.findBySql(sqlBuilder.getSql(), connection);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ArrayList<String> getExistYearList(Connection connection) throws SQLException {
        String string = " SELECT DISTINCT YEAR FROM BASELAND_BUILDPRICE_RATIO ORDER BY YEAR DESC ";
        ArrayList<String> arrayList = new ArrayList<String>();
        try {
            this._stmt = connection.createStatement();
            this._rs = this._stmt.executeQuery(string);
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
    public NVO_BASELAND_BUILDPRICE_RATIO findByPk(String string, String string2, String string3, Connection connection) throws Exception {
        String string4 = " SELECT * FROM BASELAND_BUILDPRICE_RATIO WHERE BASEDATE=@@ AND YEAR=@@ AND MONTH=@@ ";
        NVO_BASELAND_BUILDPRICE_RATIO nVO_BASELAND_BUILDPRICE_RATIO = null;
        try {
            SqlBuilder sqlBuilder = new SqlBuilder(string4);
            int n = 0;
            sqlBuilder.setString(n++, string);
            sqlBuilder.setString(n++, string2);
            sqlBuilder.setString(n++, string3);
            this._stmt = connection.createStatement();
            this._rs = this._stmt.executeQuery(sqlBuilder.getSql());
            if (this._rs.next()) {
                nVO_BASELAND_BUILDPRICE_RATIO = (NVO_BASELAND_BUILDPRICE_RATIO)this.getResults(this._rs);
            }
        }
        finally {
            SqlUtil.close(this._rs);
            SqlUtil.close(this._stmt);
        }
        return nVO_BASELAND_BUILDPRICE_RATIO;
    }
}

