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
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_REPORT_PARAM;
import moiland.landuse.util.Us7Ascii;

public class NDAO_BASELAND_REPORT_PARAM
extends DaoBase {
    public NDAO_BASELAND_REPORT_PARAM() {
        this.fullClassName = "moiland.baseland.dataaccess.nvo.NVO_BASELAND_REPORT_PARAM";
        this.us = new Us7Ascii(false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ArrayList<String> getExistYearList(String string, Connection connection) throws SQLException {
        String string2 = " SELECT * FROM BASELAND_REPORT_PARAM WHERE CITY=@@ ORDER BY YEAR DESC ";
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
    public NVO_BASELAND_REPORT_PARAM findByPk(String string, String string2, Connection connection) throws Exception {
        String string3 = " SELECT * FROM BASELAND_REPORT_PARAM WHERE CITY=@@ AND YEAR=@@ ";
        SqlBuilder sqlBuilder = new SqlBuilder(string3);
        int n = 0;
        sqlBuilder.setString(n++, string);
        sqlBuilder.setString(n++, string2);
        try {
            this._stmt = connection.createStatement();
            this._rs = this._stmt.executeQuery(sqlBuilder.getSql());
            if (this._rs.next()) {
                NVO_BASELAND_REPORT_PARAM nVO_BASELAND_REPORT_PARAM = (NVO_BASELAND_REPORT_PARAM)this.getResults(this._rs);
                return nVO_BASELAND_REPORT_PARAM;
            }
        }
        finally {
            SqlUtil.close(this._rs);
            SqlUtil.close(this._stmt);
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ArrayList<String> getBuildCostBasedateList(Connection connection) throws Exception {
        String string = "SELECT DISTINCT BUILD_COST_BASEDATE FROM BASELAND_REPORT_PARAM ORDER BY BUILD_COST_BASEDATE DESC";
        ArrayList<String> arrayList = new ArrayList<String>();
        try {
            this._stmt = connection.createStatement();
            this._rs = this._stmt.executeQuery(string);
            while (this._rs.next()) {
                arrayList.add(this._rs.getString("BUILD_COST_BASEDATE"));
            }
        }
        finally {
            SqlUtil.close(this._rs);
            SqlUtil.close(this._stmt);
        }
        return arrayList;
    }

    public ArrayList<NVO_BASELAND_REPORT_PARAM> queryDataForCopy(String string, String string2, Connection connection) throws Exception {
        boolean bl = !StringProcess.isEmpty(string) && !StringProcess.isEmpty(string2);
        ArrayList arrayList = new ArrayList();
        if (bl) {
            SqlBuilder sqlBuilder = new SqlBuilder();
            StringBuffer stringBuffer = new StringBuffer();
            ArrayList<String> arrayList2 = new ArrayList<String>();
            stringBuffer.append(" SELECT * FROM BASELAND_REPORT_PARAM ");
            stringBuffer.append(" WHERE CITY=@@ AND YEAR=@@");
            arrayList2.add(string);
            arrayList2.add(string2);
            stringBuffer.append(" ORDER BY CITY ");
            sqlBuilder.setPreSql(stringBuffer.toString());
            sqlBuilder.setValueArray(arrayList2.toArray(), true);
            NDAO_BASELAND_REPORT_PARAM nDAO_BASELAND_REPORT_PARAM = new NDAO_BASELAND_REPORT_PARAM();
            arrayList = nDAO_BASELAND_REPORT_PARAM.findBySql(sqlBuilder.getSql(), connection);
        }
        return arrayList;
    }
}

