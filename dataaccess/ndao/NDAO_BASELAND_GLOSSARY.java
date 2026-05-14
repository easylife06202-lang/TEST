/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.dataaccess.ndao;

import com.wfusion.dataaccess.dao.DaoBase;
import com.wfusion.util.SqlBuilder;
import com.wfusion.util.SqlUtil;
import com.wfusion.util.StringProcess;
import java.sql.Connection;
import java.util.ArrayList;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_GLOSSARY;
import moiland.landuse.util.Us7Ascii;

public class NDAO_BASELAND_GLOSSARY
extends DaoBase {
    public NDAO_BASELAND_GLOSSARY() {
        this.fullClassName = "moiland.baseland.dataaccess.nvo.NVO_BASELAND_GLOSSARY";
        this.us = new Us7Ascii(false);
    }

    public NVO_BASELAND_GLOSSARY findByPk(NVO_BASELAND_GLOSSARY nVO_BASELAND_GLOSSARY, Connection connection) throws Exception {
        return this.findByPk(nVO_BASELAND_GLOSSARY.getCode_0(), nVO_BASELAND_GLOSSARY.getCode_1(), nVO_BASELAND_GLOSSARY.getSno(), connection);
    }

    public ArrayList<NVO_BASELAND_GLOSSARY> query(String string, String string2, int n, String string3, Connection connection) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" SELECT * FROM BASELAND_GLOSSARY WHERE 1=1");
        if (!StringProcess.isEmpty(string)) {
            stringBuilder.append(" AND CODE_0='").append(string).append("'");
        }
        if (!StringProcess.isEmpty(string2)) {
            stringBuilder.append(" AND CODE_1='").append(string2).append("'");
        }
        if (n > 0) {
            stringBuilder.append(" AND SNO=").append(n);
        }
        if (!StringProcess.isEmpty(string3)) {
            stringBuilder.append(" AND LITERAL like '%").append(string3).append("%'");
        }
        stringBuilder.append(" order by CODE_0,CODE_1,sno");
        return this.findBySql(stringBuilder.toString(), connection);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public NVO_BASELAND_GLOSSARY findByPk(String string, String string2, int n, Connection connection) throws Exception {
        String string3 = " SELECT * FROM BASELAND_GLOSSARY WHERE CODE_0=@@ AND CODE_1=@@ AND SNO=@@ ";
        SqlBuilder sqlBuilder = new SqlBuilder(string3);
        int n2 = 0;
        sqlBuilder.setString(n2++, string);
        sqlBuilder.setString(n2++, string2);
        sqlBuilder.setInt(n2++, n);
        NVO_BASELAND_GLOSSARY nVO_BASELAND_GLOSSARY = null;
        try {
            this._stmt = connection.createStatement();
            this._rs = this._stmt.executeQuery(sqlBuilder.getSql());
            if (this._rs.next()) {
                nVO_BASELAND_GLOSSARY = (NVO_BASELAND_GLOSSARY)this.getResults(this._rs);
            }
        }
        finally {
            SqlUtil.close(this._rs);
            SqlUtil.close(this._stmt);
        }
        return nVO_BASELAND_GLOSSARY;
    }

    public ArrayList<NVO_BASELAND_GLOSSARY> queryOneFieldData(NVO_BASELAND_GLOSSARY nVO_BASELAND_GLOSSARY, Connection connection) throws Exception {
        return this.queryOneFieldData(nVO_BASELAND_GLOSSARY.getCode_0(), nVO_BASELAND_GLOSSARY.getCode_1(), connection);
    }

    public ArrayList<NVO_BASELAND_GLOSSARY> queryOneFieldData(String string, String string2, Connection connection) throws Exception {
        String string3 = " SELECT * FROM BASELAND_GLOSSARY WHERE CODE_0=@@ AND CODE_1=@@ ORDER BY LITERAL ";
        SqlBuilder sqlBuilder = new SqlBuilder(string3);
        int n = 0;
        sqlBuilder.setString(n++, string);
        sqlBuilder.setString(n++, string2);
        return this.findBySql(sqlBuilder.getSql(), connection);
    }

    public int getNextSno(NVO_BASELAND_GLOSSARY nVO_BASELAND_GLOSSARY, Connection connection) throws Exception {
        return this.getNextSno(nVO_BASELAND_GLOSSARY.getCode_0(), nVO_BASELAND_GLOSSARY.getCode_1(), connection);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int getNextSno(String string, String string2, Connection connection) throws Exception {
        String string3 = " SELECT MAX(SNO) AS SNO FROM BASELAND_GLOSSARY WHERE CODE_0=@@ AND CODE_1=@@ ";
        SqlBuilder sqlBuilder = new SqlBuilder(string3);
        int n = 0;
        sqlBuilder.setString(n++, string);
        sqlBuilder.setString(n++, string2);
        int n2 = 0;
        try {
            this._stmt = connection.createStatement();
            this._rs = this._stmt.executeQuery(sqlBuilder.getSql());
            if (this._rs.next()) {
                n2 = this._rs.getInt("SNO");
            }
        }
        finally {
            SqlUtil.close(this._rs);
            SqlUtil.close(this._stmt);
        }
        return ++n2;
    }

    public boolean checkExist(NVO_BASELAND_GLOSSARY nVO_BASELAND_GLOSSARY, Connection connection) throws Exception {
        String string = " SELECT * FROM BASELAND_GLOSSARY WHERE CODE_0=@@ AND CODE_1=@@ AND LITERAL=@@";
        SqlBuilder sqlBuilder = new SqlBuilder(string);
        int n = 0;
        sqlBuilder.setString(n++, nVO_BASELAND_GLOSSARY.getCode_0());
        sqlBuilder.setString(n++, nVO_BASELAND_GLOSSARY.getCode_1());
        sqlBuilder.setString(n++, nVO_BASELAND_GLOSSARY.getLiteral());
        ArrayList arrayList = this.findBySql(sqlBuilder.getSql(), connection);
        if (arrayList.size() > 0) {
            nVO_BASELAND_GLOSSARY.setSno(((NVO_BASELAND_GLOSSARY)arrayList.get(0)).getSno());
            return true;
        }
        return false;
    }
}

