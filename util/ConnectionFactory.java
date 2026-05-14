/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.ResourceBundle;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnectionFactory {
    public static final String PREFIX = "java:comp/env/jdbc/";
    public static final String MSSQL = "mssql";
    public static final String ORACLE = "oracle";
    public static final String POSTGRE = "postgre";
    public static final String POSTGIS = "postgis";
    public static final String SQLITE = "sqlite";
    public static final String DB2 = "db2";
    Context ctx = null;
    DataSource ds = null;
    private static HashMap<String, String> driverMap = new HashMap<String, String>(){
        {
            this.put(ConnectionFactory.MSSQL, "net.sourceforge.jtds.jdbc.Driver");
            this.put(ConnectionFactory.ORACLE, "oracle.jdbc.driver.OracleDriver");
            this.put(ConnectionFactory.POSTGRE, "org.postgresql.Driver");
            this.put(ConnectionFactory.POSTGIS, "org.postgis.DriverWrapper");
            this.put(ConnectionFactory.SQLITE, "org.sqlite.JDBC");
            this.put(ConnectionFactory.DB2, "com.ibm.db2.jcc.DB2Driver");
        }
    };
    private static HashMap<String, String> urlMap = new HashMap<String, String>(){
        {
            this.put(ConnectionFactory.MSSQL, "jdbc:jtds:sqlserver://IP:PORT/DBNAME");
            this.put(ConnectionFactory.ORACLE, "jdbc:oracle:thin:@IP:PORT:DBNAME");
            this.put(ConnectionFactory.POSTGRE, "jdbc:postgresql://IP:PORT/DBNAME");
            this.put(ConnectionFactory.POSTGIS, "jdbc:postgresql_postGIS://IP:PORT/DBNAME");
            this.put(ConnectionFactory.SQLITE, "jdbc:sqlite:DBNAME");
            this.put(ConnectionFactory.DB2, "jdbc:db2://IP:PORT/DBNAME");
        }
    };

    public static Connection createPoolConnection(String string) {
        Connection connection = null;
        try {
            InitialContext initialContext = new InitialContext();
            DataSource dataSource = (DataSource)initialContext.lookup(PREFIX + string);
            connection = dataSource.getConnection();
        }
        catch (Exception exception) {
            System.out.println("Context/DataSource:\u8cc7\u6599\u5eab\u4f86\u6e90\u53d6\u5f97\u5931\u6557\uff01" + exception);
        }
        if (connection == null) {
            connection = ConnectionFactory.createConnection("database_" + string);
        }
        return connection;
    }

    public static Connection createConnection(String string) {
        Connection connection = null;
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle(string);
            String string2 = resourceBundle.getString("database.driver");
            String string3 = resourceBundle.getString("database.url");
            String string4 = resourceBundle.getString("database.id");
            String string5 = resourceBundle.getString("database.password");
            Class.forName(string2);
            connection = DriverManager.getConnection(string3, string4, string5);
        }
        catch (Exception exception) {
            System.out.println(exception.toString());
        }
        return connection;
    }

    public static Connection createConnection(String string, String string2, String string3, String string4, String string5, String string6) {
        Connection connection = null;
        try {
            String string7 = driverMap.get(string);
            String string8 = urlMap.get(string);
            string8 = string8.replaceAll("IP", string3);
            string8 = string8.replaceAll("PORT", string4);
            string8 = string8.replaceAll("DBNAME", string2.replace('\\', '/'));
            Class.forName(string7);
            connection = DriverManager.getConnection(string8, string5, string6);
        }
        catch (Exception exception) {
            System.out.println(exception.toString());
        }
        return connection;
    }
}

