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
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_REGIONAL_FACTOR_STD;
import moiland.landuse.util.Us7Ascii;

public class NDAO_BASELAND_REGIONAL_FACTOR_STD
extends DaoBase {
    public NDAO_BASELAND_REGIONAL_FACTOR_STD() {
        this.fullClassName = "moiland.baseland.dataaccess.nvo.NVO_BASELAND_REGIONAL_FACTOR_STD";
        this.us = new Us7Ascii(false);
    }

    public ArrayList<NVO_BASELAND_REGIONAL_FACTOR_STD> queryStdDataByItem(String string, String string2, String string3, String string4, String string5, String string6, Connection connection) throws Exception {
        String string7 = "SELECT * FROM BASELAND_REGIONAL_FACTOR_STD WHERE CITY=@@ AND DIST=@@ AND YEAR=@@ AND VERSION=@@ AND BASENO=@@ AND ITEM=@@ ORDER BY LEVEL";
        SqlBuilder sqlBuilder = new SqlBuilder(string7);
        int n = 0;
        sqlBuilder.setString(n++, string);
        sqlBuilder.setString(n++, string2);
        sqlBuilder.setString(n++, string3);
        sqlBuilder.setString(n++, string4);
        sqlBuilder.setString(n++, string5);
        sqlBuilder.setString(n++, string6);
        return this.findBySql(sqlBuilder.getSql(), connection);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int clearStdDataByItem(String string, String string2, String string3, String string4, String string5, String string6, Connection connection) throws SQLException {
        String string7 = "DELETE FROM BASELAND_REGIONAL_FACTOR_STD WHERE CITY=@@ AND DIST=@@ AND YEAR=@@ AND VERSION=@@ AND BASENO=@@ AND ITEM=@@";
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
            SqlUtil.close(this._stmt);
        }
        return n2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int clearSpecificVersionByBaseno(String string, String string2, Connection connection) throws SQLException {
        String string3 = "DELETE FROM BASELAND_REGIONAL_FACTOR_STD WHERE BASENO<>'' AND YEAR=@@ AND BASENO=@@";
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
            SqlUtil.close(this._stmt);
        }
        return n2;
    }

    public ArrayList<NVO_BASELAND_REGIONAL_FACTOR_STD> queryDeleteForCopyStd(String string, String string2, String string3, String string4, String string5, Connection connection) throws Exception {
        return this.queryDeleteForCopyStd(string, string2, string3, string4, string5, true, connection);
    }

    public ArrayList<NVO_BASELAND_REGIONAL_FACTOR_STD> queryDeleteForCopyStd(String string, String string2, String string3, String string4, String string5, boolean bl, Connection connection) throws Exception {
        boolean bl2;
        boolean bl3 = bl2 = !StringProcess.isEmpty(string) && !StringProcess.isEmpty(string3) && !StringProcess.isEmpty(string2);
        if (bl) {
            bl2 = bl2 && !StringProcess.isEmpty(string4);
        }
        ArrayList arrayList = new ArrayList();
        if (bl2) {
            SqlBuilder sqlBuilder = new SqlBuilder();
            StringBuffer stringBuffer = new StringBuffer();
            ArrayList<String> arrayList2 = new ArrayList<String>();
            stringBuffer.append(" SELECT * FROM BASELAND_REGIONAL_FACTOR_STD ");
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

