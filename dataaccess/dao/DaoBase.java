/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package com.wfusion.dataaccess.dao;

import com.wfusion.dataaccess.vo.DbByteArray;
import com.wfusion.dataaccess.vo.DbDouble;
import com.wfusion.dataaccess.vo.DbElement;
import com.wfusion.dataaccess.vo.DbInteger;
import com.wfusion.dataaccess.vo.DbLong;
import com.wfusion.dataaccess.vo.DbString;
import com.wfusion.dataaccess.vo.VoBase;
import com.wfusion.util.ArraysUtils;
import com.wfusion.util.StringProcess;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.zip.GZIPOutputStream;
import moiland.landuse.util.Us7Ascii;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaoBase {
    protected Us7Ascii us = Us7Ascii.getInstance();
    protected int batchCount = 0;
    protected static Logger log = LoggerFactory.getLogger(DaoBase.class);
    protected String fullClassName = "java.lang.Object";
    protected PreparedStatement _ps = null;
    protected Statement _stmt = null;
    protected ResultSet _rs = null;
    private boolean looped = false;
    private HashMap<String, Method> methodMap = new HashMap();

    public DaoBase() {
    }

    public DaoBase(boolean bl) {
        this.us = new Us7Ascii(bl);
    }

    public DaoBase(boolean bl, String string) {
        this.us = new Us7Ascii(bl);
        this.fullClassName = string;
    }

    public void setBatchCount(int n) {
        this.batchCount = n;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void updateBackupMechnism(VoBase voBase, String string, boolean bl, Connection connection) throws Exception {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            VoBase voBase2 = this.findByPk(voBase, connection);
            if (voBase2 != null) {
                statement.executeUpdate(this.us.Encoding(voBase2.getDeletePkSql().replaceAll(voBase2.getTableName(), string.toUpperCase())));
                statement.executeUpdate(this.us.Encoding(voBase2.getInsertSql().replaceAll(voBase2.getTableName(), string.toUpperCase())));
                if (!bl) {
                    statement.executeUpdate(this.us.Encoding(voBase.getUpdateSql()));
                }
            } else {
                statement.executeUpdate(this.us.Encoding(voBase.getInsertSql()));
            }
        }
        finally {
            this.close(statement);
        }
    }

    public boolean isExist(VoBase voBase, Connection connection) throws SQLException {
        return this.getCount(voBase, connection) > 0;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int getCount(VoBase voBase, Connection connection) throws SQLException {
        boolean bl = true;
        for (int i = 0; i < voBase.getFieldCount(); ++i) {
            if (!voBase.getElementAt(i).isSearchFlag()) continue;
            bl = false;
            break;
        }
        String string = "";
        string = bl ? voBase.getQueryPkSql() : voBase.getQuerySql();
        string = StringProcess.replaceAll(string, "*", "count(*) as c");
        Statement statement = null;
        ResultSet resultSet = null;
        int n = 0;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(this.us.Encoding(string));
            n = resultSet.next() ? resultSet.getInt(1) : 0;
            this.close(resultSet);
            this.close(statement);
        }
        catch (Throwable throwable) {
            this.close(resultSet);
            this.close(statement);
            throw throwable;
        }
        return n;
    }

    public void create(ArrayList<? extends VoBase> arrayList, Connection connection) throws Exception {
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        VoBase voBase = null;
        try {
            for (int i = 0; i < arrayList.size(); ++i) {
                voBase = arrayList.get(i);
                String string = voBase.getInsertSql();
                if (string.indexOf("?") > 0) {
                    preparedStatement = connection.prepareStatement(this.us.Encoding(string));
                    int n = 1;
                    for (int j = 0; j < voBase.getFieldCount(); ++j) {
                        if (!voBase.getElementAt(j).getType().equals("Bytes")) continue;
                        byte[] byArray = ((DbByteArray)voBase.getElementAt(j)).getValue();
                        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
                        preparedStatement.setBinaryStream(n++, (InputStream)byteArrayInputStream, byArray.length);
                    }
                    preparedStatement.executeUpdate();
                    continue;
                }
                if (statement == null) {
                    statement = connection.createStatement();
                }
                statement.execute(this.us.Encoding(string));
            }
        }
        catch (SQLException sQLException) {
            System.out.println(voBase.getTableName() + "\u7121\u6cd5\u5b8c\u6210\u8cc7\u6599\u8868\u5beb\u5165\uff0c\u5176\u539f\u56e0\u70ba:\n" + voBase.getInsertSql());
            this.checkDataSize(arrayList, voBase, connection);
            sQLException.printStackTrace();
            throw sQLException;
        }
        finally {
            this.close(preparedStatement);
            this.close(statement);
        }
    }

    public void create(VoBase voBase, Connection connection) throws SQLException {
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        try {
            String string = voBase.getInsertSql();
            if (string.indexOf("?") > 0) {
                preparedStatement = connection.prepareStatement(this.us.Encoding(string));
                int n = 1;
                for (int i = 0; i < voBase.getFieldCount(); ++i) {
                    if (!voBase.getElementAt(i).getType().equals("Bytes")) continue;
                    byte[] byArray = ((DbByteArray)voBase.getElementAt(i)).getValue();
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
                    preparedStatement.setBinaryStream(n++, (InputStream)byteArrayInputStream, byArray.length);
                }
                preparedStatement.executeUpdate();
            } else {
                statement = connection.createStatement();
                statement.executeUpdate(this.us.Encoding(string));
            }
            this.close(statement);
            this.close(preparedStatement);
        }
        catch (SQLException sQLException) {
            try {
                System.out.println(voBase.getTableName() + "\u7121\u6cd5\u5b8c\u6210\u8cc7\u6599\u8868\u5beb\u5165\uff0c\u5176\u539f\u56e0\u70ba:\n" + voBase == null ? "" : voBase.getInsertSql());
                throw sQLException;
            }
            catch (Throwable throwable) {
                this.close(statement);
                this.close(preparedStatement);
                throw throwable;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public int create(VoBase voBase, Statement statement, Connection connection) throws SQLException {
        try {
            String string = voBase.getInsertSql();
            if (string.indexOf("?") <= 0) {
                statement.addBatch(this.us.Encoding(string));
                return 0;
            }
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement(this.us.Encoding(string));
                int n = 1;
                int n2 = 0;
                while (true) {
                    if (n2 >= voBase.getFieldCount()) {
                        n2 = preparedStatement.executeUpdate();
                        this.close(preparedStatement);
                        return n2;
                    }
                    if (voBase.getElementAt(n2).getType().equals("Bytes")) {
                        byte[] byArray = ((DbByteArray)voBase.getElementAt(n2)).getValue();
                        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
                        preparedStatement.setBinaryStream(n++, (InputStream)byteArrayInputStream, byArray.length);
                        DaoBase.close(byteArrayInputStream);
                    }
                    ++n2;
                }
            }
            catch (SQLException sQLException) {
                try {
                    throw sQLException;
                }
                catch (Throwable throwable) {
                    this.close(preparedStatement);
                    throw throwable;
                }
            }
        }
        catch (SQLException sQLException) {
            System.out.println(voBase.getTableName() + "\u7121\u6cd5\u5b8c\u6210\u8cc7\u6599\u8868\u5beb\u5165\uff0c\u5176\u539f\u56e0\u70ba:" + sQLException);
            throw sQLException;
        }
    }

    public void updatex(VoBase voBase, Connection connection) throws SQLException {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(this.us.Encoding(voBase.getUpdateSql()));
        }
        catch (SQLException sQLException) {
            System.out.println(voBase.getTableName() + "\u7121\u6cd5\u5b8c\u6210\u8cc7\u6599\u8868\u66f4\u65b0\uff0c\u5176\u539f\u56e0\u70ba:" + sQLException);
            throw sQLException;
        }
        finally {
            this.close(statement);
        }
    }

    public void update(VoBase voBase, Connection connection) throws SQLException {
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        try {
            statement = connection.createStatement();
            String string = voBase.getUpdatePkSql();
            if (string.indexOf("?") > 0) {
                preparedStatement = connection.prepareStatement(this.us.Encoding(string));
                int n = 1;
                for (int i = 0; i < voBase.getFieldCount(); ++i) {
                    if (!voBase.getElementAt(i).isUpdate() || !voBase.getElementAt(i).getType().equals("Bytes")) continue;
                    byte[] byArray = ((DbByteArray)voBase.getElementAt(i)).getValue();
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
                    preparedStatement.setBinaryStream(n++, (InputStream)byteArrayInputStream, byArray.length);
                }
                preparedStatement.executeUpdate();
            } else {
                statement.executeUpdate(this.us.Encoding(string));
            }
            this.close(preparedStatement);
            this.close(statement);
        }
        catch (SQLException sQLException) {
            try {
                System.out.println(voBase.getTableName() + "\u7121\u6cd5\u5b8c\u6210\u8cc7\u6599\u8868\u66f4\u65b0\uff0c\u5176\u539f\u56e0\u70ba:" + sQLException);
                System.out.println(this.us.Encoding(voBase.getUpdatePkSql()));
                throw sQLException;
            }
            catch (Throwable throwable) {
                this.close(preparedStatement);
                this.close(statement);
                throw throwable;
            }
        }
    }

    public void update(ArrayList<? extends VoBase> arrayList, Connection connection) throws Exception {
        Statement statement = null;
        VoBase voBase = null;
        try {
            statement = connection.createStatement();
            for (int i = 0; i < arrayList.size(); ++i) {
                voBase = arrayList.get(i);
                statement.addBatch(this.us.Encoding(voBase.getUpdatePkSql()));
            }
            statement.executeBatch();
        }
        catch (SQLException sQLException) {
            System.out.println(voBase.getTableName() + "\u7121\u6cd5\u5b8c\u6210\u8cc7\u6599\u8868\u66f4\u65b0\uff0c\u5176\u539f\u56e0\u70ba:" + sQLException.getMessage());
            this.checkDataSize(arrayList, voBase, connection);
            throw sQLException;
        }
        finally {
            this.close(statement);
        }
    }

    public void checkDataSize(ArrayList<? extends VoBase> arrayList, VoBase voBase, Connection connection) throws Exception {
        String string = "SELECT * FROM " + voBase.getTableName();
        ResultSet resultSet = null;
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(string);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); ++i) {
                String string2 = resultSetMetaData.getColumnName(i).toLowerCase();
                if (resultSetMetaData.getScale(i) > 0) {
                    treeMap.put(string2, resultSetMetaData.getPrecision(i) + "," + resultSetMetaData.getScale(i));
                    continue;
                }
                treeMap.put(string2, String.valueOf(resultSetMetaData.getColumnDisplaySize(i)));
                treeMap.put(resultSetMetaData.getColumnName(i).toLowerCase(), String.valueOf(resultSetMetaData.getColumnDisplaySize(i)));
            }
            StringBuffer stringBuffer = new StringBuffer("");
            for (int i = 0; i < arrayList.size(); ++i) {
                voBase = arrayList.get(i);
                StringBuffer stringBuffer2 = voBase.checkDataSize(treeMap);
                if (stringBuffer2.length() <= 0) continue;
                stringBuffer.append(voBase.getPkString(",") + "," + stringBuffer2 + "\n");
            }
            if (stringBuffer.length() > 0) {
                throw new SQLException(stringBuffer.toString());
            }
            this.close(resultSet);
            this.close(statement);
        }
        catch (Exception exception) {
            try {
                throw exception;
            }
            catch (Throwable throwable) {
                this.close(resultSet);
                this.close(statement);
                throw throwable;
            }
        }
    }

    public void checkDataSize(VoBase voBase, Connection connection) throws Exception {
        String string = "SELECT * FROM " + voBase.getTableName();
        ResultSet resultSet = null;
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        Statement statement = null;
        try {
            CharSequence charSequence;
            statement = connection.createStatement();
            resultSet = statement.executeQuery(string);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); ++i) {
                charSequence = resultSetMetaData.getColumnName(i).toLowerCase();
                if (resultSetMetaData.getScale(i) > 0) {
                    treeMap.put((String)charSequence, resultSetMetaData.getPrecision(i) + "," + resultSetMetaData.getScale(i));
                    continue;
                }
                treeMap.put((String)charSequence, String.valueOf(resultSetMetaData.getColumnDisplaySize(i)));
                treeMap.put(resultSetMetaData.getColumnName(i).toLowerCase(), String.valueOf(resultSetMetaData.getColumnDisplaySize(i)));
            }
            StringBuffer stringBuffer = new StringBuffer("");
            charSequence = voBase.checkDataSize(treeMap);
            if (((StringBuffer)charSequence).length() > 0) {
                stringBuffer.append(voBase.getPkString(",") + "," + charSequence + "\n");
            }
            if (stringBuffer.length() > 0) {
                throw new SQLException(stringBuffer.toString());
            }
            this.close(resultSet);
            this.close(statement);
        }
        catch (Exception exception) {
            try {
                throw exception;
            }
            catch (Throwable throwable) {
                this.close(resultSet);
                this.close(statement);
                throw throwable;
            }
        }
    }

    public int delete(ArrayList<? extends VoBase> arrayList, Connection connection) throws SQLException {
        int n = 0;
        Statement statement = null;
        VoBase voBase = null;
        try {
            int[] nArray;
            statement = connection.createStatement();
            for (int i = 0; i < arrayList.size(); ++i) {
                voBase = arrayList.get(i);
                statement.addBatch(this.us.Encoding(voBase.getDeletePkSql()));
            }
            for (int n2 : nArray = statement.executeBatch()) {
                n += n2;
            }
            int n3 = n;
            return n3;
        }
        catch (SQLException sQLException) {
            System.out.println(voBase.getTableName() + "\u7121\u6cd5\u5b8c\u6210\u8cc7\u6599\u8868\u522a\u9664\uff0c\u5176\u539f\u56e0\u70ba:" + sQLException);
            throw sQLException;
        }
        finally {
            this.close(statement);
        }
    }

    public int delete(VoBase voBase, Connection connection) throws SQLException {
        int n = 0;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            n = statement.executeUpdate(this.us.Encoding(voBase.getDeletePkSql()));
        }
        catch (SQLException sQLException) {
            System.out.println(voBase.getTableName() + "\u7121\u6cd5\u5b8c\u6210\u8cc7\u6599\u8868\u522a\u9664\uff0c\u5176\u539f\u56e0\u70ba:" + sQLException + ",and \n sql=" + this.us.Encoding(voBase.getDeletePkSql()));
            throw sQLException;
        }
        finally {
            this.close(statement);
        }
        return n;
    }

    public int deletex(VoBase voBase, Connection connection) throws SQLException {
        int n = 0;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            n = statement.executeUpdate(this.us.Encoding(voBase.getDeleteSql()));
        }
        catch (SQLException sQLException) {
            System.out.println(voBase.getTableName() + "\u7121\u6cd5\u5b8c\u6210\u8cc7\u6599\u8868\u522a\u9664\uff0c\u5176\u539f\u56e0\u70ba:" + sQLException + ",and sql=" + this.us.Encoding(voBase.getDeleteSql()));
            throw sQLException;
        }
        finally {
            this.close(statement);
        }
        return n;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ArrayList<VoBase> findByPkJoin(ArrayList<? extends VoBase> arrayList, int n, Connection connection) throws Exception {
        ArrayList<VoBase> arrayList2 = new ArrayList<VoBase>();
        Statement statement = null;
        ResultSet resultSet = null;
        if (arrayList != null && arrayList.size() > 0) {
            VoBase voBase = arrayList.get(0);
            try {
                statement = connection.createStatement();
                for (int i = 0; i < arrayList.size(); i += n) {
                    resultSet = statement.executeQuery(this.us.Encoding(voBase.getQueryPkBatchingSql(this.us.getCandicate(), this.getPkInStatement(arrayList, i, i + n > arrayList.size() ? arrayList.size() : i + n))));
                    while (resultSet.next()) {
                        arrayList2.add(this.getResults(resultSet));
                    }
                }
            }
            finally {
                this.close(resultSet);
                this.close(statement);
            }
        }
        return arrayList2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ArrayList<VoBase> findBySearchJoin(ArrayList<? extends VoBase> arrayList, int n, Connection connection) throws Exception {
        ArrayList<VoBase> arrayList2 = new ArrayList<VoBase>();
        Statement statement = null;
        ResultSet resultSet = null;
        if (arrayList != null && arrayList.size() > 0) {
            VoBase voBase = arrayList.get(0);
            try {
                statement = connection.createStatement();
                for (int i = 0; i < arrayList.size(); i += n) {
                    System.out.println("sql=" + voBase.getQuerySearchBatchingSql(this.us.getCandicate(), this.getPkInStatement(arrayList, i, i + n > arrayList.size() ? arrayList.size() : i + n)));
                    resultSet = statement.executeQuery(this.us.Encoding(voBase.getQuerySearchBatchingSql(this.us.getCandicate(), this.getSearchInStatement(arrayList, i, i + n > arrayList.size() ? arrayList.size() : i + n))));
                    while (resultSet.next()) {
                        arrayList2.add(this.getResults(resultSet));
                    }
                }
            }
            finally {
                this.close(resultSet);
                this.close(statement);
            }
        }
        return arrayList2;
    }

    protected String getSearchInStatement(ArrayList<? extends VoBase> arrayList, int n, int n2) {
        StringBuffer stringBuffer = new StringBuffer();
        VoBase voBase = arrayList.get(0);
        stringBuffer.append('(');
        for (int i = n; i < n2; ++i) {
            voBase = arrayList.get(i);
            stringBuffer.append("'");
            for (int j = 0; j < voBase.getFieldCount(); ++j) {
                if (!voBase.getElementAt(j).isSearchFlag()) continue;
                stringBuffer.append(voBase.getElementAt(j).toString());
            }
            stringBuffer.append('\'').append(',');
        }
        return stringBuffer.substring(0, stringBuffer.length() - 1) + ")";
    }

    protected String getPkInStatement(ArrayList<? extends VoBase> arrayList, int n, int n2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append('(');
        for (int i = n; i < n2; ++i) {
            VoBase voBase = arrayList.get(i);
            stringBuffer.append("'");
            for (int j = 0; j < voBase.getFieldCount(); ++j) {
                if (!voBase.getElementAt(j).isPk()) continue;
                stringBuffer.append(voBase.getElementAt(j).toString());
            }
            stringBuffer.append('\'').append(',');
        }
        return stringBuffer.substring(0, stringBuffer.length() - 1) + ")";
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ArrayList findByVos(ArrayList<? extends VoBase> arrayList, Connection connection) throws Exception {
        ArrayList<VoBase> arrayList2 = new ArrayList<VoBase>();
        Statement statement = null;
        ResultSet resultSet = null;
        VoBase voBase = null;
        try {
            statement = connection.createStatement();
            for (int i = 0; i < arrayList.size(); ++i) {
                voBase = arrayList.get(i);
                resultSet = statement.executeQuery(this.us.Encoding(voBase.getQueryPkSql()));
                if (!resultSet.next()) continue;
                arrayList2.add(this.getResults(resultSet));
            }
        }
        finally {
            this.close(resultSet);
            this.close(statement);
        }
        return arrayList2;
    }

    public VoBase findByPk(VoBase voBase, Connection connection) throws Exception {
        ResultSet resultSet;
        Statement statement;
        block5: {
            statement = null;
            resultSet = null;
            statement = connection.createStatement();
            resultSet = statement.executeQuery(this.us.Encoding(voBase.getQueryPkSql()));
            if (!resultSet.next()) break block5;
            VoBase voBase2 = this.getResults(resultSet);
            this.close(resultSet);
            this.close(statement);
            return voBase2;
        }
        try {
            VoBase voBase3 = null;
            this.close(resultSet);
            this.close(statement);
            return voBase3;
        }
        catch (Exception exception) {
            try {
                System.out.println("\u5ffd\u7565 VoBase findByPk : " + exception.getMessage());
                throw exception;
            }
            catch (Throwable throwable) {
                this.close(resultSet);
                this.close(statement);
                throw throwable;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ArrayList findBySearch(VoBase voBase, Connection connection) throws Exception {
        ArrayList<VoBase> arrayList = new ArrayList<VoBase>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(this.us.Encoding(voBase.getQuerySql()));
            while (resultSet.next()) {
                arrayList.add(this.getResults(resultSet));
            }
            this.close(resultSet);
            this.close(statement);
        }
        catch (Throwable throwable) {
            this.close(resultSet);
            this.close(statement);
            throw throwable;
        }
        return arrayList;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ArrayList findBySql(String string, Connection connection) throws Exception {
        ArrayList<VoBase> arrayList = new ArrayList<VoBase>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(this.us.Encoding(string));
            while (resultSet.next()) {
                arrayList.add(this.getResults(resultSet));
            }
            this.close(resultSet);
            this.close(statement);
        }
        catch (Throwable throwable) {
            this.close(resultSet);
            this.close(statement);
            throw throwable;
        }
        return arrayList;
    }

    public VoBase getResults(ResultSet resultSet) throws Exception {
        VoBase voBase = (VoBase)Class.forName(this.fullClassName).newInstance();
        int n = 0;
        try {
            for (n = 0; n < voBase.getFieldCount(); ++n) {
                if (voBase.getElementAt(n).getType().equals("String")) {
                    voBase.getElementAt(n).setValue(StringProcess.NULL(this.us.Decoding(resultSet.getString(voBase.getElementAt(n).getName()))));
                    continue;
                }
                if (voBase.getElementAt(n).getType().equals("Long")) {
                    voBase.getElementAt(n).setValue(new Long((long)StringProcess.parserDouble(resultSet.getString(voBase.getElementAt(n).getName()))));
                    continue;
                }
                if (voBase.getElementAt(n).getType().equals("Integer")) {
                    voBase.getElementAt(n).setValue(new Integer(StringProcess.parserInt(resultSet.getString(voBase.getElementAt(n).getName()))));
                    continue;
                }
                if (voBase.getElementAt(n).getType().equals("Double")) {
                    voBase.getElementAt(n).setValue(new Double(StringProcess.parserDouble(resultSet.getString(voBase.getElementAt(n).getName()))));
                    continue;
                }
                if (voBase.getElementAt(n).getType().equals("Bytes")) {
                    byte[] byArray = resultSet.getBytes(voBase.getElementAt(n).getName());
                    voBase.getElementAt(n).setValue(byArray);
                    continue;
                }
                throw new SQLException("no such data-type:" + voBase.getElementAt(n).getType());
            }
        }
        catch (Exception exception) {
            System.out.println("get some problem: " + voBase.getElementAt(n).getName() + " get error!");
            System.out.println(exception);
            throw exception;
        }
        return voBase;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public VoBase getResultsByName(ResultSet resultSet) throws Exception {
        VoBase voBase = (VoBase)Class.forName(this.fullClassName).newInstance();
        int n = 0;
        try {
            StringBuffer stringBuffer = new StringBuffer();
            StringBuffer stringBuffer2 = new StringBuffer();
            for (n = 0; n < voBase.getFieldCount(); ++n) {
                DbElement dbElement;
                block23: {
                    dbElement = voBase.getElementAt(n);
                    stringBuffer.append(dbElement.getName());
                    stringBuffer2.append(dbElement.getType());
                    try {
                        if (resultSet.getString(stringBuffer.toString()) == null) {
                            stringBuffer.delete(0, stringBuffer.length());
                            stringBuffer2.delete(0, stringBuffer2.length());
                        }
                        break block23;
                    }
                    catch (Exception exception) {
                        System.out.println("wfusion: get " + stringBuffer.toString() + " error!" + exception.toString());
                        stringBuffer.delete(0, stringBuffer.length());
                        stringBuffer2.delete(0, stringBuffer2.length());
                    }
                    continue;
                }
                if (stringBuffer2.toString().equals("String")) {
                    dbElement.setValue(resultSet.getString(stringBuffer.toString()) == null ? "" : this.us.Decoding(resultSet.getString(stringBuffer.toString())));
                } else if (stringBuffer2.toString().equals("Long")) {
                    dbElement.setValue(new Long(resultSet.getLong(stringBuffer.toString())));
                } else if (stringBuffer2.toString().equals("Integer")) {
                    dbElement.setValue(new Integer(resultSet.getInt(stringBuffer.toString())));
                } else if (stringBuffer2.toString().equals("Double")) {
                    dbElement.setValue(new Double(resultSet.getDouble(stringBuffer.toString())));
                } else if (stringBuffer2.toString().equals("Bytes")) {
                    try (BufferedInputStream bufferedInputStream = null;){
                        bufferedInputStream = new BufferedInputStream(resultSet.getBinaryStream(stringBuffer.toString()));
                        byte[] byArray = new byte[1024];
                        byte[] byArray2 = new byte[]{};
                        int n2 = bufferedInputStream.read(byArray);
                        while (n2 > 0) {
                            byArray2 = ArraysUtils.append(byArray2, byArray, n2);
                            n2 = bufferedInputStream.read(byArray);
                        }
                        dbElement.setValue(byArray2);
                    }
                } else {
                    throw new SQLException("no such data-type:" + voBase.getElementAt(n).getType());
                }
                stringBuffer.delete(0, stringBuffer.length());
                stringBuffer2.delete(0, stringBuffer2.length());
            }
        }
        catch (Exception exception) {
            System.out.println("get some problem: " + voBase.getElementAt(n).getName() + " get error!");
            System.out.println(exception);
            throw exception;
        }
        return voBase;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public VoBase getResultsForSync(ResultSet resultSet) throws Exception {
        VoBase voBase = (VoBase)Class.forName(this.fullClassName).newInstance();
        int n = 0;
        try {
            for (n = 0; n < voBase.getFieldCount(); ++n) {
                if (voBase.getElementAt(n).getType().equals("String")) {
                    voBase.getElementAt(n).setValue(resultSet.getString(n + 1) == null ? "" : this.us.Decoding(resultSet.getString(n + 1)));
                    continue;
                }
                if (voBase.getElementAt(n).getType().equals("Long")) {
                    voBase.getElementAt(n).setValue(new Long(resultSet.getLong(n + 1)));
                    continue;
                }
                if (voBase.getElementAt(n).getType().equals("Integer")) {
                    voBase.getElementAt(n).setValue(new Integer(resultSet.getInt(n + 1)));
                    continue;
                }
                if (voBase.getElementAt(n).getType().equals("Double")) {
                    voBase.getElementAt(n).setValue(new Double(resultSet.getDouble(n + 1)));
                    continue;
                }
                if (voBase.getElementAt(n).getType().equals("Bytes")) {
                    try (BufferedInputStream bufferedInputStream = null;){
                        bufferedInputStream = new BufferedInputStream(resultSet.getBinaryStream(n + 1));
                        byte[] byArray = new byte[1024];
                        byte[] byArray2 = new byte[]{};
                        int n2 = bufferedInputStream.read(byArray);
                        while (n2 > 0) {
                            byArray2 = ArraysUtils.append(byArray2, byArray, n2);
                            n2 = bufferedInputStream.read(byArray);
                        }
                        voBase.getElementAt(n).setValue(byArray2);
                        continue;
                    }
                }
                System.out.println("no such data-type:" + voBase.getElementAt(n).getType());
                throw new SQLException("no such data-type:" + voBase.getElementAt(n).getType());
            }
        }
        catch (Exception exception) {
            System.out.println("get some problem: " + voBase.getElementAt(n).getName() + " get error!");
        }
        return voBase;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int executeUpdate(String string, Connection connection) throws Exception {
        int n;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            n = statement.executeUpdate(this.us.Encoding(string));
        }
        finally {
            this.close(statement);
        }
        return n;
    }

    public int executeBatchDel(ArrayList<? extends VoBase> arrayList, Connection connection) throws Exception {
        int n = 0;
        int[] nArray = null;
        Statement statement = null;
        try {
            int n2;
            statement = connection.createStatement();
            int n3 = arrayList.size();
            for (n2 = 0; n2 < n3; ++n2) {
                VoBase voBase = arrayList.get(n2);
                statement.addBatch(voBase.getDeleteSql());
            }
            nArray = statement.executeBatch();
            for (n2 = 0; n2 < nArray.length; ++n2) {
                n += nArray[n2];
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
        finally {
            this.close(statement);
        }
        return n;
    }

    public void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
                statement = null;
            }
            catch (SQLException sQLException) {
                sQLException.printStackTrace();
            }
        }
    }

    public void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
                resultSet = null;
            }
            catch (SQLException sQLException) {
                sQLException.printStackTrace();
            }
        }
    }

    public static void close(OutputStream outputStream) {
        try {
            outputStream.close();
        }
        catch (Exception exception) {
        }
        finally {
            outputStream = null;
        }
    }

    public static void close(InputStream inputStream) {
        try {
            inputStream.close();
        }
        catch (Exception exception) {
        }
        finally {
            inputStream = null;
        }
    }

    public boolean deleteCascade(VoBase voBase, VoBase voBase2, Connection connection) throws Exception {
        boolean bl = false;
        if (voBase != null && voBase2 != null) {
            for (int i = 0; i < voBase.getFieldCount(); ++i) {
                DbElement dbElement;
                DbElement dbElement2 = voBase.getElementAt(i);
                if (!dbElement2.isPk() || (dbElement = voBase2.getElementAt(dbElement2.getName())) == null) continue;
                dbElement.setValue(dbElement2.getObject());
                dbElement.setSearchFlag(true);
            }
            this.deletex(voBase2, connection);
            this.delete(voBase, connection);
            bl = true;
        }
        return bl;
    }

    public String getFullClassName() {
        return this.fullClassName;
    }

    public void update2(ArrayList<? extends VoBase> arrayList, Connection connection) throws Exception {
        Statement statement = null;
        VoBase voBase = null;
        try {
            statement = connection.createStatement();
            for (int i = 0; i < arrayList.size(); ++i) {
                voBase = arrayList.get(i);
                if (this.isExist(voBase, connection)) {
                    statement.addBatch(this.us.Encoding(voBase.getUpdatePkSql()));
                    continue;
                }
                statement.addBatch(this.us.Encoding(voBase.getInsertSql()));
            }
            statement.executeBatch();
        }
        catch (SQLException sQLException) {
            System.out.println(voBase.getTableName() + "\u7121\u6cd5\u5b8c\u6210\u8cc7\u6599\u8868\u66f4\u65b0\uff0c\u5176\u539f\u56e0\u70ba:" + sQLException);
            this.checkDataSize(arrayList, voBase, connection);
            throw sQLException;
        }
        finally {
            this.close(statement);
        }
    }

    public void update2(VoBase voBase, Connection connection) throws Exception {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            if (this.isExist(voBase, connection)) {
                statement.addBatch(this.us.Encoding(voBase.getUpdatePkSql()));
            } else {
                statement.addBatch(this.us.Encoding(voBase.getInsertSql()));
            }
            statement.executeBatch();
        }
        catch (SQLException sQLException) {
            System.out.println(voBase.getTableName() + "\u7121\u6cd5\u5b8c\u6210\u8cc7\u6599\u8868\u66f4\u65b0\uff0c\u5176\u539f\u56e0\u70ba:" + sQLException);
            throw sQLException;
        }
        finally {
            this.close(statement);
        }
    }

    public int update(ArrayList<? extends VoBase> arrayList, boolean bl, Connection connection) throws SQLException {
        Statement statement = null;
        VoBase voBase = null;
        int n = 0;
        try {
            statement = connection.createStatement();
            for (int i = 0; i < arrayList.size(); ++i) {
                voBase = arrayList.get(i);
                if (this.isExist(voBase, connection)) {
                    if (!bl) continue;
                    statement.addBatch(this.us.Encoding(voBase.getUpdatePkSql()));
                    continue;
                }
                statement.addBatch(this.us.Encoding(voBase.getInsertSql()));
            }
            int[] nArray = statement.executeBatch();
            for (int i = 0; i < nArray.length; ++i) {
                n += nArray[i];
            }
        }
        catch (SQLException sQLException) {
            System.out.println(voBase.getTableName() + "\u7121\u6cd5\u5b8c\u6210\u8cc7\u6599\u8868\u66f4\u65b0\uff0c\u5176\u539f\u56e0\u70ba:" + sQLException + voBase.getUpdatePkSql());
            throw sQLException;
        }
        finally {
            this.close(statement);
        }
        return n;
    }

    public Object getResultByRefection(ResultSet resultSet, Class<?> clazz) throws Exception {
        Field[] fieldArray = clazz.getDeclaredFields();
        Object obj = clazz.newInstance();
        for (Field field : fieldArray) {
            String string = field.getName();
            try {
                StringBuffer stringBuffer = new StringBuffer("set");
                stringBuffer.append(field.getName().substring(0, 1).toUpperCase()).append(field.getName().substring(1));
                Method method = null;
                if (this.looped) {
                    if (!this.methodMap.containsKey(stringBuffer.toString())) continue;
                    method = this.methodMap.get(stringBuffer.toString());
                }
                if (field.getType().getName().equals("java.lang.String")) {
                    if (method == null) {
                        method = clazz.getMethod(stringBuffer.toString(), String.class);
                    }
                    method.invoke(obj, this.us.Decoding(resultSet.getString(string)));
                } else if (field.getType().getName().equals("double")) {
                    if (method == null) {
                        method = clazz.getMethod(stringBuffer.toString(), Double.TYPE);
                    }
                    method.invoke(obj, resultSet.getDouble(string));
                } else if (field.getType().getName().equals("int")) {
                    if (method == null) {
                        method = clazz.getMethod(stringBuffer.toString(), Integer.TYPE);
                    }
                    method.invoke(obj, resultSet.getInt(string));
                } else if (field.getType().getName().equals("long")) {
                    if (method == null) {
                        method = clazz.getMethod(stringBuffer.toString(), Long.TYPE);
                    }
                    method.invoke(obj, resultSet.getLong(string));
                } else if (field.getType().getName().equals("java.lang.Double")) {
                    if (method == null) {
                        method = clazz.getMethod(stringBuffer.toString(), Double.class);
                    }
                    method.invoke(obj, resultSet.getDouble(string));
                } else if (field.getType().getName().equals("java.lang.Integer")) {
                    if (method == null) {
                        method = clazz.getMethod(stringBuffer.toString(), Integer.class);
                    }
                    method.invoke(obj, resultSet.getInt(string));
                }
                if (this.looped) continue;
                this.methodMap.put(stringBuffer.toString(), method);
            }
            catch (Exception exception) {
                System.out.println(resultSet + " not found fieldName=" + string);
            }
        }
        this.looped = true;
        return obj;
    }

    public void resetCacheMethod() {
        this.methodMap.clear();
        this.looped = false;
    }

    public ArrayList<HashMap<String, Object>> getHashMapResult(ResultSet resultSet, boolean bl) throws Exception {
        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String, Object>>();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int n = resultSetMetaData.getColumnCount();
        while (resultSet.next()) {
            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            for (int i = 1; i <= n; ++i) {
                if (bl) {
                    hashMap.put(resultSetMetaData.getColumnName(i), this.us.Decoding(resultSet.getString(i)));
                    continue;
                }
                if (resultSetMetaData.getColumnType(i) == 1 || resultSetMetaData.getColumnType(i) == 12 || resultSetMetaData.getColumnType(i) == -1) {
                    hashMap.put(resultSetMetaData.getColumnName(i), this.us.Decoding(resultSet.getString(i)));
                }
                if (resultSetMetaData.getColumnType(i) == -5 || resultSetMetaData.getColumnType(i) == 4 || resultSetMetaData.getColumnType(i) == 5) {
                    if (resultSet.getLong(i) > Integer.MAX_VALUE) {
                        hashMap.put(resultSetMetaData.getColumnName(i), resultSet.getLong(i));
                    } else {
                        hashMap.put(resultSetMetaData.getColumnName(i), resultSet.getInt(i));
                    }
                }
                if (resultSetMetaData.getColumnType(i) != 3 && resultSetMetaData.getColumnType(i) != 8 && resultSetMetaData.getColumnType(i) != 2 && resultSetMetaData.getColumnType(i) != 6 && resultSetMetaData.getColumnType(i) != 7) continue;
                hashMap.put(resultSetMetaData.getColumnName(i), resultSet.getDouble(i));
            }
            arrayList.add(hashMap);
        }
        return arrayList;
    }

    public int deleteByPreparedStatement(ArrayList<? extends VoBase> arrayList, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        VoBase voBase = null;
        int n = 0;
        if (arrayList == null || arrayList.size() == 0) {
            return 0;
        }
        try {
            preparedStatement = connection.prepareStatement(arrayList.get(0).getDeletePkPreSql());
            long l = System.currentTimeMillis();
            for (int i = 0; i < arrayList.size(); ++i) {
                int n2 = 1;
                voBase = arrayList.get(i);
                for (int j = 0; j < voBase.getFieldCount(); ++j) {
                    if (!voBase.getElementAt(j).isPk()) continue;
                    if (voBase.getElementAt(j).getType().equals("String")) {
                        preparedStatement.setString(n2, this.us.Encoding(((DbString)voBase.getElementAt(j)).getValue()));
                    } else if (voBase.getElementAt(j).getType().equals("Long")) {
                        preparedStatement.setLong(n2, ((DbLong)voBase.getElementAt(j)).getValue());
                    } else if (voBase.getElementAt(j).getType().equals("Integer")) {
                        preparedStatement.setInt(n2, ((DbInteger)voBase.getElementAt(j)).getValue());
                    } else if (voBase.getElementAt(j).getType().equals("Double")) {
                        preparedStatement.setDouble(n2, ((DbDouble)voBase.getElementAt(j)).getValue());
                    } else if (voBase.getElementAt(j).getType().equals("Bytes")) {
                        preparedStatement.setBytes(n2, ((DbByteArray)voBase.getElementAt(j)).getValue());
                    } else {
                        preparedStatement.setString(n2, this.us.Encoding(((DbString)voBase.getElementAt(j)).getValue()));
                    }
                    ++n2;
                }
                n += preparedStatement.executeUpdate();
                if (i % 1000 != 0) continue;
                System.out.println("Daobase.deleteByPreparedStatement: recs:" + i + ",spend:" + (double)(System.currentTimeMillis() - l) / 1000.0);
                l = System.currentTimeMillis();
            }
            this.close(preparedStatement);
        }
        catch (SQLException sQLException) {
            try {
                System.out.println(voBase.getTableName() + "\u7121\u6cd5\u5b8c\u6210\u8cc7\u6599\u8868\u522a\u9664\uff0c\u5176\u539f\u56e0\u70ba:" + sQLException);
                throw sQLException;
            }
            catch (Throwable throwable) {
                this.close(preparedStatement);
                throw throwable;
            }
        }
        return n;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ArrayList<HashMap<String, Object>> getHashMapResult(String string, boolean bl, Connection connection) throws Exception {
        try {
            this._stmt = connection.createStatement();
            this._rs = this._stmt.executeQuery(string);
            ArrayList<HashMap<String, Object>> arrayList = this.getHashMapResult(this._rs, bl);
            return arrayList;
        }
        finally {
            this.close(this._rs);
            this.close(this._stmt);
        }
    }

    public VoBase getResultsByDyn(ResultSet resultSet) throws Exception {
        int n;
        VoBase voBase = (VoBase)Class.forName(this.fullClassName).newInstance();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int n2 = resultSetMetaData.getColumnCount();
        try {
            for (n = 1; n <= n2; ++n) {
                this.loadRsData(resultSetMetaData.getColumnName(n), voBase, resultSet);
            }
        }
        catch (Exception exception) {
            System.out.println("wfusion:get date from rs error, column[" + resultSetMetaData.getColumnName(n) + "], " + exception);
            throw exception;
        }
        return voBase;
    }

    private void loadRsData(String string, VoBase voBase, ResultSet resultSet) throws Exception {
        block7: {
            DbElement dbElement = voBase.getElementAt(string);
            try {
                if (dbElement == null) break block7;
                if (dbElement.getType().equals("String")) {
                    dbElement.setValue(resultSet.getString(string) == null ? "" : this.us.Decoding(resultSet.getString(string)));
                    break block7;
                }
                if (dbElement.getType().equals("Long")) {
                    dbElement.setValue(new Long(resultSet.getLong(string)));
                    break block7;
                }
                if (dbElement.getType().equals("Integer")) {
                    dbElement.setValue(new Integer(resultSet.getInt(string)));
                    break block7;
                }
                if (dbElement.getType().equals("Double")) {
                    dbElement.setValue(new Double(resultSet.getDouble(string)));
                    break block7;
                }
                if (dbElement.getType().equals("Bytes")) {
                    this.setByte(string, dbElement, resultSet);
                    break block7;
                }
                throw new SQLException("wfusion:no such data-type:" + dbElement.getType());
            }
            catch (Exception exception) {
                System.out.println("wfusion:get data from rs exception, " + exception);
                exception.printStackTrace();
            }
        }
    }

    private void setByte(String string, DbElement dbElement, ResultSet resultSet) throws Exception {
        BufferedInputStream bufferedInputStream = null;
        InputStream inputStream = null;
        try {
            inputStream = resultSet.getBinaryStream(string);
            if (inputStream != null) {
                bufferedInputStream = new BufferedInputStream(inputStream);
                byte[] byArray = new byte[1024];
                byte[] byArray2 = new byte[]{};
                int n = bufferedInputStream.read(byArray);
                while (n > 0) {
                    byArray2 = ArraysUtils.append(byArray2, byArray, n);
                    n = bufferedInputStream.read(byArray);
                }
                dbElement.setValue(byArray2);
            }
        }
        catch (Exception exception) {
            System.out.println("wfusion:get bytes array from rs exception, " + exception);
            dbElement.setValue(new byte[0]);
            throw exception;
        }
        finally {
            DaoBase.close(bufferedInputStream);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int exportVo2Files(String string, String string2, int n, String string3, Connection connection) throws Exception {
        Statement statement = null;
        ResultSet resultSet = null;
        int n2 = 0;
        try {
            String string4;
            statement = connection.createStatement();
            resultSet = statement.executeQuery(string);
            ArrayList<VoBase> arrayList = new ArrayList<VoBase>();
            while (resultSet.next()) {
                ++n2;
                arrayList.add(this.getResults(resultSet));
                if (arrayList.size() != n) continue;
                string4 = string2 + "_" + String.valueOf(1000000000 + n2);
                this.write2File(arrayList, string3, string4);
                arrayList.clear();
            }
            string4 = string2 + "_" + String.valueOf(1000000000 + n2);
            this.write2File(arrayList, string3, string4);
            arrayList.clear();
            this.close(resultSet);
            this.close(statement);
        }
        catch (Throwable throwable) {
            this.close(resultSet);
            this.close(statement);
            throw throwable;
        }
        return n2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void write2File(Object object, String string, String string2) {
        File file = new File(string);
        if (!file.exists()) {
            file.mkdirs();
        }
        String string3 = string + "/" + string2 + ".sync";
        System.out.println(string3);
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(new File(string3))));
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            DaoBase.close(objectOutputStream);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            DaoBase.close(objectOutputStream);
        }
    }
}

