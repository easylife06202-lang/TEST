/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.dataaccess.ndao;

import com.wfusion.dataaccess.dao.DaoBase;
import com.wfusion.util.ArraysUtils;
import com.wfusion.util.SqlBuilder;
import com.wfusion.util.SqlUtil;
import com.wfusion.util.StringProcess;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_MAIN;
import moiland.landuse.util.Us7Ascii;

public class NDAO_BASELAND_MAIN
extends DaoBase {
    public NDAO_BASELAND_MAIN() {
        this.fullClassName = "moiland.baseland.dataaccess.nvo.NVO_BASELAND_MAIN";
        this.us = new Us7Ascii(false);
    }

    public void clear(String string, String string2, Connection connection) throws Exception {
        NVO_BASELAND_MAIN nVO_BASELAND_MAIN = new NVO_BASELAND_MAIN();
        nVO_BASELAND_MAIN.setYear(string);
        nVO_BASELAND_MAIN.setBaseno(string2);
        this.delete(nVO_BASELAND_MAIN, connection);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private ArrayList<NVO_BASELAND_MAIN> getBandMainNum(String string, String string2, String string3, String string4, String string5, Connection connection) {
        ArrayList arrayList = new ArrayList();
        SqlBuilder sqlBuilder = new SqlBuilder();
        StringBuffer stringBuffer = new StringBuffer("SELECT * FROM BASELAND_MAIN WHERE CITY=@@ AND DIST=@@ AND YEAR=@@ ");
        ArrayList<String> arrayList2 = new ArrayList<String>();
        arrayList2.add(string);
        arrayList2.add(string2);
        arrayList2.add(string3);
        if (!string4.equals("") && !string5.equals("")) {
            stringBuffer.append("AND BASENO BETWEEN @@ AND @@");
            arrayList2.add(string4);
            arrayList2.add(string5);
        }
        stringBuffer.append("ORDER BY BASESEQ");
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            sqlBuilder.setPreSql(stringBuffer.toString());
            sqlBuilder.setValueArray(arrayList2.toArray(), false);
            arrayList = this.findBySql(sqlBuilder.getSql(), connection);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(resultSet);
            SqlUtil.close(statement);
        }
        return arrayList;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ArrayList<NVO_BASELAND_MAIN> getBandMainDataList(String string, String string2, String string3, String string4, String string5, String string6, Connection connection) {
        ArrayList arrayList = new ArrayList();
        SqlBuilder sqlBuilder = new SqlBuilder();
        StringBuffer stringBuffer = new StringBuffer(" SELECT * FROM BASELAND_MAIN WHERE CITY=@@ AND DIST=@@ AND YEAR=@@ ");
        ArrayList<String> arrayList2 = new ArrayList<String>();
        arrayList2.add(string);
        arrayList2.add(string2);
        arrayList2.add(string3);
        if (!"".equals(string4)) {
            stringBuffer.append("AND URBAN=@@ ");
            arrayList2.add(string4);
        }
        if (!string5.equals("") && !string6.equals("")) {
            stringBuffer.append("AND BASESEQ BETWEEN @@ AND @@ ");
            arrayList2.add(string5);
            arrayList2.add(string6);
        }
        stringBuffer.append("ORDER BY CITY, DIST, BASESEQ ");
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            sqlBuilder.setPreSql(stringBuffer.toString());
            sqlBuilder.setValueArray(arrayList2.toArray(), false);
            arrayList = this.findBySql(sqlBuilder.getSql(), connection);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(resultSet);
            SqlUtil.close(statement);
        }
        return arrayList;
    }

    public ArrayList<String> getBandMainNum(String string, String string2, String string3, String string4, Connection connection) {
        return this.getBandMainNum_withVersion(string, string2, string3, string4, "", connection);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ArrayList<String> getBandMainNum_withVersion(String string, String string2, String string3, String string4, String string5, Connection connection) {
        ArrayList<String> arrayList;
        block10: {
            arrayList = new ArrayList<String>();
            SqlBuilder sqlBuilder = new SqlBuilder();
            StringBuffer stringBuffer = new StringBuffer(" SELECT BASENO FROM BASELAND_MAIN WHERE CITY=@@ AND DIST=@@ AND YEAR=@@ ");
            ArrayList<Object> arrayList2 = new ArrayList<Object>();
            arrayList2.add(string);
            arrayList2.add(string2);
            arrayList2.add(string3);
            if (string4.matches("^(B[ABDF]|E[ABCDEFGHJKLMNPQRST])(,(B[ABDF]|E[ABCDEFGHJKLMNPQRST]))+$")) {
                stringBuffer.append("AND URBAN in @@ ");
                arrayList2.add(ArraysUtils.array2List(string4.split(",")));
            } else if (string4.length() > 0) {
                stringBuffer.append("AND URBAN=@@ ");
                arrayList2.add(string4);
            }
            if (!StringProcess.isEmpty(string5)) {
                stringBuffer.append("AND VERSION=@@");
                arrayList2.add(string5);
            }
            stringBuffer.append("ORDER BY CITY, DIST, BASESEQ ");
            Statement statement = null;
            ResultSet resultSet = null;
            try {
                sqlBuilder.setPreSql(stringBuffer.toString());
                sqlBuilder.setValueArray(arrayList2.toArray(), false);
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sqlBuilder.toString());
                while (resultSet.next()) {
                    String string6 = StringProcess.NULL(resultSet.getString("BASENO"));
                    arrayList.add(string6);
                }
                SqlUtil.close(resultSet);
            }
            catch (Exception exception) {
                exception.printStackTrace();
                break block10;
            }
            finally {
                SqlUtil.close(resultSet);
                SqlUtil.close(statement);
            }
            SqlUtil.close(statement);
        }
        return arrayList;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ArrayList<String> getExistYearList(String string, String string2, Connection connection) {
        ArrayList<String> arrayList;
        block7: {
            arrayList = new ArrayList<String>();
            SqlBuilder sqlBuilder = new SqlBuilder();
            StringBuffer stringBuffer = new StringBuffer(" SELECT DISTINCT YEAR FROM BASELAND_MAIN WHERE CITY=@@ ");
            ArrayList<String> arrayList2 = new ArrayList<String>();
            arrayList2.add(string);
            if (string2.length() > 0) {
                stringBuffer.append(" AND DIST=@@ ");
                arrayList2.add(string2);
            }
            stringBuffer.append("ORDER BY YEAR DESC ");
            Statement statement = null;
            ResultSet resultSet = null;
            try {
                sqlBuilder.setPreSql(stringBuffer.toString());
                sqlBuilder.setValueArray(arrayList2.toArray(), false);
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sqlBuilder.toString());
                while (resultSet.next()) {
                    arrayList.add(StringProcess.NULL(resultSet.getString("YEAR")));
                }
                SqlUtil.close(resultSet);
            }
            catch (Exception exception) {
                exception.printStackTrace();
                break block7;
            }
            finally {
                SqlUtil.close(resultSet);
                SqlUtil.close(statement);
            }
            SqlUtil.close(statement);
        }
        return arrayList;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ArrayList<String> getBandMainNumWithoutAppraiserCase(String string, String string2, String string3, String string4, Connection connection) {
        ArrayList<String> arrayList;
        block9: {
            arrayList = new ArrayList<String>();
            SqlBuilder sqlBuilder = new SqlBuilder();
            StringBuffer stringBuffer = new StringBuffer(" SELECT BASENO FROM BASELAND_MAIN WHERE CREATOR<>'1' AND CITY=@@ AND DIST=@@ AND YEAR=@@ ");
            ArrayList<Object> arrayList2 = new ArrayList<Object>();
            arrayList2.add(string);
            arrayList2.add(string2);
            arrayList2.add(string3);
            if (string4.matches("^(B[ABDF]|EE)(,(B[ABDF]|EE))+$")) {
                stringBuffer.append("AND URBAN in @@ ");
                arrayList2.add(ArraysUtils.array2List(string4.split(",")));
            } else if (string4.length() > 0) {
                stringBuffer.append("AND URBAN=@@ ");
                arrayList2.add(string4);
            }
            stringBuffer.append("ORDER BY CITY, DIST, BASESEQ ");
            Statement statement = null;
            ResultSet resultSet = null;
            try {
                sqlBuilder.setPreSql(stringBuffer.toString());
                sqlBuilder.setValueArray(arrayList2.toArray(), false);
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sqlBuilder.toString());
                while (resultSet.next()) {
                    String string5 = StringProcess.NULL(resultSet.getString("BASENO"));
                    arrayList.add(string5);
                }
                SqlUtil.close(resultSet);
            }
            catch (Exception exception) {
                exception.printStackTrace();
                break block9;
            }
            finally {
                SqlUtil.close(resultSet);
                SqlUtil.close(statement);
            }
            SqlUtil.close(statement);
        }
        return arrayList;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ArrayList<NVO_BASELAND_MAIN> getBandMainVos(String string, String string2, String string3, String string4, Connection connection) {
        ArrayList<NVO_BASELAND_MAIN> arrayList;
        block9: {
            arrayList = new ArrayList<NVO_BASELAND_MAIN>();
            SqlBuilder sqlBuilder = new SqlBuilder();
            StringBuffer stringBuffer = new StringBuffer(" SELECT * FROM BASELAND_MAIN WHERE CITY=@@ AND DIST=@@ AND YEAR=@@ ");
            ArrayList<Object> arrayList2 = new ArrayList<Object>();
            arrayList2.add(string);
            arrayList2.add(string2);
            arrayList2.add(string3);
            if (string4.matches("^(B[ABDF]|EE)(,(B[ABDF]|EE))+$")) {
                stringBuffer.append("AND URBAN in @@ ");
                arrayList2.add(ArraysUtils.array2List(string4.split(",")));
            } else if (string4.length() > 0) {
                stringBuffer.append("AND URBAN=@@ ");
                arrayList2.add(string4);
            }
            stringBuffer.append("ORDER BY CITY, DIST, BASESEQ ");
            Statement statement = null;
            ResultSet resultSet = null;
            try {
                sqlBuilder.setPreSql(stringBuffer.toString());
                sqlBuilder.setValueArray(arrayList2.toArray(), false);
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sqlBuilder.toString());
                while (resultSet.next()) {
                    arrayList.add((NVO_BASELAND_MAIN)this.getResults(resultSet));
                }
                SqlUtil.close(resultSet);
            }
            catch (Exception exception) {
                exception.printStackTrace();
                break block9;
            }
            finally {
                SqlUtil.close(resultSet);
                SqlUtil.close(statement);
            }
            SqlUtil.close(statement);
        }
        return arrayList;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ArrayList<NVO_BASELAND_MAIN> getDataByYear(String string, String string2, Connection connection) {
        ArrayList<NVO_BASELAND_MAIN> arrayList;
        block6: {
            arrayList = new ArrayList<NVO_BASELAND_MAIN>();
            SqlBuilder sqlBuilder = new SqlBuilder();
            StringBuffer stringBuffer = new StringBuffer(" SELECT * FROM BASELAND_MAIN WHERE CITY=@@ AND YEAR=@@ ");
            stringBuffer.append("ORDER BY DIST, BASENO ");
            ArrayList<String> arrayList2 = new ArrayList<String>();
            arrayList2.add(string);
            arrayList2.add(string2);
            Statement statement = null;
            ResultSet resultSet = null;
            try {
                sqlBuilder.setPreSql(stringBuffer.toString());
                sqlBuilder.setValueArray(arrayList2.toArray(), false);
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sqlBuilder.toString());
                while (resultSet.next()) {
                    arrayList.add((NVO_BASELAND_MAIN)this.getResults(resultSet));
                }
                SqlUtil.close(resultSet);
            }
            catch (Exception exception) {
                exception.printStackTrace();
                break block6;
            }
            finally {
                SqlUtil.close(resultSet);
                SqlUtil.close(statement);
            }
            SqlUtil.close(statement);
        }
        return arrayList;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public String getMaxNo(String string, String string2, String string3, String string4, Connection connection) throws Exception {
        String string5;
        block6: {
            int n = 1;
            string5 = "";
            SqlBuilder sqlBuilder = new SqlBuilder();
            String string6 = "SELECT MAX(BASESEQ) AS BASESEQ FROM BASELAND_MAIN WHERE CITY=@@ AND DIST=@@ AND YEAR=@@";
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add(string);
            arrayList.add(string2);
            arrayList.add(string3);
            sqlBuilder.setPreSql(string6);
            sqlBuilder.setValueArray(arrayList.toArray(), false);
            Statement statement = null;
            ResultSet resultSet = null;
            try {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sqlBuilder.toString());
                if (resultSet.next()) {
                    n = StringProcess.parserInt(StringProcess.NULL(resultSet.getString("BASESEQ")));
                    ++n;
                }
                string5 = string + string2 + string4 + StringProcess.fillZero(n, 4);
                SqlUtil.close(resultSet);
            }
            catch (Exception exception) {
                exception.printStackTrace();
                break block6;
            }
            finally {
                SqlUtil.close(resultSet);
                SqlUtil.close(statement);
            }
            SqlUtil.close(statement);
        }
        return string5;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public NVO_BASELAND_MAIN findByPk(String string, String string2, Connection connection) throws Exception {
        String string3 = " SELECT * FROM BASELAND_MAIN WHERE BASENO=@@ AND YEAR=@@ ";
        SqlBuilder sqlBuilder = new SqlBuilder(string3);
        int n = 0;
        sqlBuilder.setString(n++, string);
        sqlBuilder.setString(n++, string2);
        NVO_BASELAND_MAIN nVO_BASELAND_MAIN = null;
        try {
            this._stmt = connection.createStatement();
            this._rs = this._stmt.executeQuery(sqlBuilder.getSql());
            if (this._rs.next()) {
                nVO_BASELAND_MAIN = (NVO_BASELAND_MAIN)this.getResults(this._rs);
                nVO_BASELAND_MAIN.setUserid(nVO_BASELAND_MAIN.getUserid());
            }
        }
        finally {
            SqlUtil.close(this._rs);
            SqlUtil.close(this._stmt);
        }
        return nVO_BASELAND_MAIN;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public NVO_BASELAND_MAIN findBySeq(String string, String string2, String string3, String string4, Connection connection) throws Exception {
        String string5 = " SELECT * FROM BASELAND_MAIN WHERE CITY=@@ AND DIST=@@ AND BASESEQ=@@ AND YEAR=@@ ";
        SqlBuilder sqlBuilder = new SqlBuilder(string5);
        int n = 0;
        sqlBuilder.setString(n++, string);
        sqlBuilder.setString(n++, string2);
        sqlBuilder.setString(n++, string3);
        sqlBuilder.setString(n++, string4);
        try {
            this._stmt = connection.createStatement();
            this._rs = this._stmt.executeQuery(sqlBuilder.getSql());
            if (this._rs.next()) {
                NVO_BASELAND_MAIN nVO_BASELAND_MAIN = (NVO_BASELAND_MAIN)this.getResults(this._rs);
                return nVO_BASELAND_MAIN;
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
    public int clearByYear(String string, String string2, Connection connection) throws SQLException {
        String string3 = " DELETE FROM BASELAND_MAIN WHERE CITY=@@ AND YEAR=@@ ";
        SqlBuilder sqlBuilder = new SqlBuilder(string3);
        sqlBuilder.setString(0, string);
        sqlBuilder.setString(1, string2);
        int n = 0;
        try {
            this._stmt = connection.createStatement();
            n = this._stmt.executeUpdate(sqlBuilder.getSql());
        }
        finally {
            SqlUtil.close(this._stmt);
        }
        return n;
    }
}

