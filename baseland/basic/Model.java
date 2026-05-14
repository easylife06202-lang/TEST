/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.baseland.basic;

import com.wfusion.util.ConnectionFactory;
import com.wfusion.util.StringProcess;
import java.io.File;
import java.sql.Connection;

public abstract class Model {
    public static final String SQLITE_PATH = new File(".").getAbsolutePath() + "\\DataDB\\";
    public static final String SQLITE_NAME = "BaseLand.db";
    public static final String SQLITE_A = "BaseLand_A.db";
    public static final String SQLITE_B = "BaseLand_B.db";
    public static final String SQLITE_C = "BaseLand_C.db";
    public static final String SQLITE_INIT = "init.db";
    public static final String SQLITE_INIT_VER = "init_ver.db";
    public static final String SQLITE_INSTALLED = "installed";
    public static String version_conn = "";

    protected Connection getConnection() {
        Connection connection = null;
        File file = new File(SQLITE_PATH + SQLITE_NAME);
        if (file.exists()) {
            connection = ConnectionFactory.createConnection("sqlite", SQLITE_PATH + SQLITE_NAME, "", "", "", "");
        }
        return connection;
    }

    protected Connection getVersionConnection() {
        Connection connection = null;
        if (!StringProcess.isEmpty(version_conn)) {
            File file = new File(SQLITE_PATH + version_conn);
            if (file.exists()) {
                connection = ConnectionFactory.createConnection("sqlite", SQLITE_PATH + version_conn, "", "", "", "");
            }
        } else {
            connection = this.getConnection();
        }
        return connection;
    }

    public static String getVersion() {
        String string = "";
        if (SQLITE_A.equals(version_conn)) {
            string = "A";
        } else if (SQLITE_B.equals(version_conn)) {
            string = "B";
        } else if (SQLITE_C.equals(version_conn)) {
            string = "B";
        }
        return string;
    }
}

