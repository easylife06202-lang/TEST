/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class SqlUtil {
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
                statement = null;
            }
            catch (SQLException sQLException) {
                sQLException.printStackTrace();
            }
            finally {
                statement = null;
            }
        }
    }

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
                resultSet = null;
            }
            catch (SQLException sQLException) {
                sQLException.printStackTrace();
            }
            finally {
                resultSet = null;
            }
        }
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            }
            catch (Exception exception) {
                System.out.println(exception);
            }
            finally {
                connection = null;
            }
        }
    }

    public static void rollback(Connection connection) {
        if (connection != null) {
            try {
                if (!connection.getAutoCommit()) {
                    connection.rollback();
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static boolean isTableExist(String string, Connection connection) {
        Statement statement = null;
        boolean bl = false;
        try {
            statement = connection.createStatement();
            statement.executeQuery("select * from " + string + " where 1=2 ");
            bl = true;
        }
        catch (Exception exception) {
            bl = false;
        }
        finally {
            SqlUtil.close(statement);
        }
        return bl;
    }

    public static boolean isTableExist(String string, HashSet<String> hashSet, Connection connection) {
        ArrayList<String> arrayList = new ArrayList<String>();
        if (string.indexOf(",") > -1) {
            String[] stringArray = string.split(",");
            for (String string2 : stringArray) {
                arrayList.add(string2);
            }
        } else {
            arrayList.add(string);
        }
        for (String string3 : arrayList) {
            int n = SqlUtil.isTableExist(string3, connection) ? 1 : 0;
            if (n != 0) continue;
            hashSet.add(string3);
        }
        return hashSet.size() == 0;
    }

    public static String strArrJoin(String[] stringArray) {
        StringBuffer stringBuffer = new StringBuffer();
        int n = stringArray.length;
        for (int i = 0; i < n; ++i) {
            stringBuffer.append("'").append(stringArray[i]).append("'");
            if (i == n - 1) continue;
            stringBuffer.append(",");
        }
        return stringBuffer.toString();
    }

    public static String strArrOr(String[] stringArray, String string) {
        StringBuffer stringBuffer = new StringBuffer();
        int n = stringArray.length;
        for (int i = 0; i < n; ++i) {
            stringBuffer.append(string).append(" like ");
            stringBuffer.append("'").append(stringArray[i]).append("%'");
            if (i == n - 1) continue;
            stringBuffer.append(" or ");
        }
        return stringBuffer.toString();
    }

    public static String[][] partArr(String[] stringArray, int n) {
        int n2 = stringArray.length;
        int n3 = 0;
        ArrayList<String[]> arrayList = new ArrayList<String[]>();
        HashMap<String, String> hashMap = new HashMap<String, String>();
        for (int i = 0; i < n2; ++i) {
            hashMap.put(stringArray[i], null);
            if (n != ++n3) continue;
            arrayList.add(SqlUtil.mapKey2StrArr(hashMap));
            n3 = 0;
            hashMap.clear();
        }
        arrayList.add(SqlUtil.mapKey2StrArr(hashMap));
        String[][] stringArray2 = new String[arrayList.size()][];
        int n4 = arrayList.size();
        for (int i = 0; i < n4; ++i) {
            stringArray2[i] = (String[])arrayList.get(i);
        }
        return stringArray2;
    }

    public static String[] mapKey2StrArr(Map<String, String> map) {
        int n = map.size();
        String[] stringArray = new String[n];
        int n2 = 0;
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            stringArray[n2++] = iterator.next();
        }
        return stringArray;
    }

    public static String getInsertPreparedSql(String string, Collection<String> collection, int n) {
        StringBuffer stringBuffer;
        StringBuffer stringBuffer2 = new StringBuffer();
        if (n == 0) {
            return "";
        }
        stringBuffer2.append("insert into ").append(string);
        if (collection != null && collection.size() != 0) {
            stringBuffer = new StringBuffer();
            Iterator<String> iterator = collection.iterator();
            stringBuffer.append(" (");
            while (iterator.hasNext()) {
                stringBuffer.append(iterator.next()).append(',');
            }
            stringBuffer2.append(stringBuffer.substring(0, stringBuffer.length() - 1)).append(')');
        }
        stringBuffer = new StringBuffer();
        stringBuffer.append(" values(");
        for (int i = 0; i < n; ++i) {
            stringBuffer.append('?').append(',');
        }
        stringBuffer2.append(stringBuffer.substring(0, stringBuffer.length() - 1)).append(')');
        return stringBuffer2.toString();
    }

    public static TreeMap<String, ArrayList<String>> getTables(DatabaseMetaData databaseMetaData) throws SQLException {
        TreeMap<String, ArrayList<String>> treeMap = new TreeMap<String, ArrayList<String>>();
        ResultSet resultSet = databaseMetaData.getTables(null, "%", "%", new String[]{"TABLE"});
        while (resultSet.next()) {
            String string = resultSet.getString("TABLE_NAME");
            treeMap.put(string.toUpperCase(), SqlUtil.getColumns(databaseMetaData, string));
        }
        return treeMap;
    }

    public static ArrayList<String> getColumns(DatabaseMetaData databaseMetaData, String string) throws SQLException {
        ResultSet resultSet = databaseMetaData.getColumns(null, "%", string, "%");
        ArrayList<String> arrayList = new ArrayList<String>();
        while (resultSet.next()) {
            String string2 = resultSet.getString("COLUMN_NAME");
            arrayList.add(string2);
        }
        return arrayList;
    }

    public static ArrayList<String> getColumns(ResultSetMetaData resultSetMetaData) throws SQLException {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < resultSetMetaData.getColumnCount(); ++i) {
            String string = resultSetMetaData.getColumnName(i + 1);
            arrayList.add(string);
        }
        return arrayList;
    }

    public static String getPkString(DatabaseMetaData databaseMetaData, String string) throws SQLException {
        ResultSet resultSet = databaseMetaData.getPrimaryKeys(null, null, string);
        StringBuffer stringBuffer = new StringBuffer();
        while (resultSet.next()) {
            stringBuffer.append(resultSet.getString("COLUMN_NAME")).append('|');
        }
        return stringBuffer.toString();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static boolean isOracleConnectionValid(Connection connection) throws Exception {
        ResultSet resultSet;
        Statement statement;
        block2: {
            boolean bl;
            statement = null;
            resultSet = null;
            try {
                statement = connection.createStatement();
                resultSet = statement.executeQuery("select count(*) from dual");
                if (!resultSet.next()) break block2;
                bl = true;
            }
            catch (Throwable throwable) {
                SqlUtil.close(resultSet);
                SqlUtil.close(statement);
                throw throwable;
            }
            SqlUtil.close(resultSet);
            SqlUtil.close(statement);
            return bl;
        }
        SqlUtil.close(resultSet);
        SqlUtil.close(statement);
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static boolean isMssqlConnectionValid(Connection connection) throws Exception {
        ResultSet resultSet;
        Statement statement;
        block2: {
            boolean bl;
            statement = null;
            resultSet = null;
            try {
                statement = connection.createStatement();
                resultSet = statement.executeQuery("select getdate()");
                if (!resultSet.next()) break block2;
                bl = true;
            }
            catch (Throwable throwable) {
                SqlUtil.close(resultSet);
                SqlUtil.close(statement);
                throw throwable;
            }
            SqlUtil.close(resultSet);
            SqlUtil.close(statement);
            return bl;
        }
        SqlUtil.close(resultSet);
        SqlUtil.close(statement);
        return false;
    }
}

