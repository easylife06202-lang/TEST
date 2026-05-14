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
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_REGIONAL_FACTOR;
import moiland.landuse.util.Us7Ascii;

public class NDAO_BASELAND_REGIONAL_FACTOR
extends DaoBase {
    public NDAO_BASELAND_REGIONAL_FACTOR() {
        this.fullClassName = "moiland.baseland.dataaccess.nvo.NVO_BASELAND_REGIONAL_FACTOR";
        this.us = new Us7Ascii(false);
    }

    public ArrayList<NVO_BASELAND_REGIONAL_FACTOR> queryOneVersionByMainCode(NVO_BASELAND_REGIONAL_FACTOR nVO_BASELAND_REGIONAL_FACTOR, Connection connection) throws Exception {
        return this.queryOneVersionByMainCode(nVO_BASELAND_REGIONAL_FACTOR.getCity(), nVO_BASELAND_REGIONAL_FACTOR.getDist(), nVO_BASELAND_REGIONAL_FACTOR.getYear(), nVO_BASELAND_REGIONAL_FACTOR.getVersion(), nVO_BASELAND_REGIONAL_FACTOR.getBaseno(), nVO_BASELAND_REGIONAL_FACTOR.getMainCode(), connection);
    }

    public ArrayList<NVO_BASELAND_REGIONAL_FACTOR> queryOneVersionByMainCode(String string, String string2, String string3, String string4, String string5, String string6, Connection connection) throws Exception {
        String string7 = " SELECT * FROM BASELAND_REGIONAL_FACTOR WHERE CITY=@@ AND DIST=@@ AND YEAR=@@ AND VERSION=@@ AND BASENO=@@ AND ITEM LIKE @@ ORDER BY ITEM ";
        SqlBuilder sqlBuilder = new SqlBuilder(string7);
        int n = 0;
        sqlBuilder.setString(n++, string);
        sqlBuilder.setString(n++, string2);
        sqlBuilder.setString(n++, string3);
        sqlBuilder.setString(n++, string4);
        sqlBuilder.setString(n++, string5);
        sqlBuilder.setString(n++, string6 + "%");
        return this.findBySql(sqlBuilder.getSql(), connection);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ArrayList<String> getExistYearList(String string, String string2, Connection connection) throws SQLException {
        String string3 = " SELECT DISTINCT YEAR FROM BASELAND_REGIONAL_FACTOR WHERE CITY=@@ AND DIST=@@ ORDER BY YEAR DESC ";
        SqlBuilder sqlBuilder = new SqlBuilder(string3);
        int n = 0;
        sqlBuilder.setString(n++, string);
        sqlBuilder.setString(n++, string2);
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

    public NVO_BASELAND_REGIONAL_FACTOR findByPk(NVO_BASELAND_REGIONAL_FACTOR nVO_BASELAND_REGIONAL_FACTOR, Connection connection) throws Exception {
        return this.findByPk(nVO_BASELAND_REGIONAL_FACTOR.getCity(), nVO_BASELAND_REGIONAL_FACTOR.getDist(), nVO_BASELAND_REGIONAL_FACTOR.getYear(), nVO_BASELAND_REGIONAL_FACTOR.getVersion(), nVO_BASELAND_REGIONAL_FACTOR.getBaseno(), nVO_BASELAND_REGIONAL_FACTOR.getItem(), connection);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public NVO_BASELAND_REGIONAL_FACTOR findByPk(String string, String string2, String string3, String string4, String string5, String string6, Connection connection) throws Exception {
        String string7 = " SELECT * FROM BASELAND_REGIONAL_FACTOR WHERE CITY=@@ AND DIST=@@ AND YEAR=@@ AND VERSION=@@ AND BASENO=@@ AND ITEM=@@ ";
        SqlBuilder sqlBuilder = new SqlBuilder(string7);
        int n = 0;
        sqlBuilder.setString(n++, string);
        sqlBuilder.setString(n++, string2);
        sqlBuilder.setString(n++, string3);
        sqlBuilder.setString(n++, string4);
        sqlBuilder.setString(n++, string5);
        sqlBuilder.setString(n++, string6);
        NVO_BASELAND_REGIONAL_FACTOR nVO_BASELAND_REGIONAL_FACTOR = null;
        try {
            this._stmt = connection.createStatement();
            this._rs = this._stmt.executeQuery(sqlBuilder.getSql());
            if (this._rs.next()) {
                nVO_BASELAND_REGIONAL_FACTOR = (NVO_BASELAND_REGIONAL_FACTOR)this.getResults(this._rs);
            }
        }
        finally {
            SqlUtil.close(this._rs);
            SqlUtil.close(this._stmt);
        }
        return nVO_BASELAND_REGIONAL_FACTOR;
    }

    public int clearByPk(NVO_BASELAND_REGIONAL_FACTOR nVO_BASELAND_REGIONAL_FACTOR, Connection connection) throws Exception {
        return this.clearByPk(nVO_BASELAND_REGIONAL_FACTOR.getCity(), nVO_BASELAND_REGIONAL_FACTOR.getDist(), nVO_BASELAND_REGIONAL_FACTOR.getYear(), nVO_BASELAND_REGIONAL_FACTOR.getVersion(), nVO_BASELAND_REGIONAL_FACTOR.getBaseno(), nVO_BASELAND_REGIONAL_FACTOR.getItem(), connection);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int clearByPk(String string, String string2, String string3, String string4, String string5, String string6, Connection connection) throws Exception {
        String string7 = "DELETE FROM BASELAND_REGIONAL_FACTOR WHERE CITY=@@ AND DIST=@@ AND YEAR=@@ AND VERSION=@@ AND BASENO=@@ AND ITEM=@@";
        SqlBuilder sqlBuilder = new SqlBuilder(string7);
        int n = 0;
        sqlBuilder.setString(n++, string);
        sqlBuilder.setString(n++, string2);
        sqlBuilder.setString(n++, string3);
        sqlBuilder.setString(n++, string4);
        sqlBuilder.setString(n++, string5);
        sqlBuilder.setString(n++, string6);
        int n2 = 0;
        try {
            this._stmt = connection.createStatement();
            n2 = this._stmt.executeUpdate(sqlBuilder.getSql());
        }
        finally {
            SqlUtil.close(this._rs);
            SqlUtil.close(this._stmt);
        }
        return n2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int clearSpecificVersionByBaseno(String string, String string2, Connection connection) throws Exception {
        String string3 = " DELETE FROM BASELAND_REGIONAL_FACTOR WHERE YEAR=@@ AND BASENO=@@ ";
        SqlBuilder sqlBuilder = new SqlBuilder(string3);
        int n = 0;
        sqlBuilder.setString(n++, string);
        sqlBuilder.setString(n++, string2);
        int n2 = 0;
        try {
            this._stmt = connection.createStatement();
            n2 = this._stmt.executeUpdate(sqlBuilder.getSql());
        }
        finally {
            SqlUtil.close(this._rs);
            SqlUtil.close(this._stmt);
        }
        return n2;
    }

    public ArrayList<NVO_BASELAND_REGIONAL_FACTOR> queryDataForCopy(String string, String string2, String string3, String string4, String string5, Connection connection) throws Exception {
        return this.process_queryDataForCopy(string, string2, string3, string4, string5, connection, false);
    }

    public ArrayList<NVO_BASELAND_REGIONAL_FACTOR> queryDataForCopy_excludeBaseno(String string, String string2, String string3, String string4, String string5, Connection connection) throws Exception {
        return this.process_queryDataForCopy(string, string2, string3, string4, string5, connection, true);
    }

    private ArrayList<NVO_BASELAND_REGIONAL_FACTOR> process_queryDataForCopy(String string, String string2, String string3, String string4, String string5, Connection connection, boolean bl) throws Exception {
        boolean bl2 = !StringProcess.isEmpty(string) && !StringProcess.isEmpty(string3) && !StringProcess.isEmpty(string2);
        ArrayList arrayList = new ArrayList();
        if (bl2) {
            SqlBuilder sqlBuilder = new SqlBuilder();
            StringBuffer stringBuffer = new StringBuffer();
            ArrayList<String> arrayList2 = new ArrayList<String>();
            stringBuffer.append(" SELECT * FROM BASELAND_REGIONAL_FACTOR ");
            stringBuffer.append(" WHERE CITY=@@ AND DIST=@@ AND YEAR=@@ ");
            arrayList2.add(string);
            arrayList2.add(string3);
            arrayList2.add(string2);
            if (!StringProcess.isEmpty(string5)) {
                stringBuffer.append(" AND BASENO=@@ ");
                arrayList2.add(string5);
            }
            if (!StringProcess.isEmpty(string4)) {
                stringBuffer.append(" AND VERSION=@@ ");
                arrayList2.add(string4);
            }
            if (bl) {
                stringBuffer.append(" AND (BASENO = '' or BASENO is null)");
            }
            stringBuffer.append(" ORDER BY CITY,DIST ");
            sqlBuilder.setPreSql(stringBuffer.toString());
            sqlBuilder.setValueArray(arrayList2.toArray(), true);
            arrayList = this.findBySql(sqlBuilder.getSql(), connection);
        }
        return arrayList;
    }

    public ArrayList<NVO_BASELAND_REGIONAL_FACTOR> queryDeleteForCopy(String string, String string2, String string3, String string4, String string5, Connection connection) throws Exception {
        boolean bl = !StringProcess.isEmpty(string) && !StringProcess.isEmpty(string3) && !StringProcess.isEmpty(string2) && !StringProcess.isEmpty(string4);
        ArrayList arrayList = new ArrayList();
        if (bl) {
            SqlBuilder sqlBuilder = new SqlBuilder();
            StringBuffer stringBuffer = new StringBuffer();
            ArrayList<String> arrayList2 = new ArrayList<String>();
            stringBuffer.append(" SELECT * FROM BASELAND_REGIONAL_FACTOR ");
            stringBuffer.append(" WHERE CITY=@@ AND DIST=@@ AND YEAR=@@ ");
            arrayList2.add(string);
            arrayList2.add(string3);
            arrayList2.add(string2);
            if (!StringProcess.isEmpty(string5)) {
                stringBuffer.append(" AND BASENO=@@ ");
                arrayList2.add(string5);
            } else {
                stringBuffer.append(" AND BASENO='' ");
            }
            if (!StringProcess.isEmpty(string4)) {
                stringBuffer.append(" AND VERSION=@@ ");
                arrayList2.add(string4);
            }
            stringBuffer.append(" ORDER BY CITY,DIST ");
            sqlBuilder.setPreSql(stringBuffer.toString());
            sqlBuilder.setValueArray(arrayList2.toArray(), true);
            arrayList = this.findBySql(sqlBuilder.getSql(), connection);
        }
        return arrayList;
    }
}

