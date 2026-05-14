/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.application.Application
 *  javafx.fxml.FXMLLoader
 *  javafx.scene.Parent
 *  javafx.scene.Scene
 *  javafx.scene.control.Alert
 *  javafx.scene.control.Alert$AlertType
 *  javafx.scene.control.ButtonType
 *  javafx.scene.layout.StackPane
 *  javafx.stage.Stage
 *  javafx.stage.StageStyle
 *  org.apache.commons.io.FileUtils
 */
package com.wfusion.baseland;

import com.wfusion.baseland.MainController;
import com.wfusion.baseland.basic.Model;
import com.wfusion.baseland.system.CarryOverModel;
import com.wfusion.baseland.system.SystemCopyModel;
import com.wfusion.fx.util.JavaFXUtil;
import com.wfusion.util.ConnectionFactory;
import com.wfusion.util.DateTime;
import com.wfusion.util.SqlUtil;
import com.wfusion.util.StringProcess;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_FACTOR_CODE;
import org.apache.commons.io.FileUtils;

public class MainApp
extends Application {
    public static boolean IS_TEST = false;
    private static String PATH = new File("").getAbsolutePath() + "\\";
    private static String LOG_DIR = "log";
    public static boolean isLogtoFile = false;
    String colmn_REGION_STD_TYPE = "ALTER TABLE BASELAND_REGIONAL_FACTOR ADD STD_TYPE nvarchar (10)  NULL";
    String colmn_REGION_STD_UNIT = "ALTER TABLE BASELAND_REGIONAL_FACTOR ADD STD_UNIT nvarchar (10)  NULL";
    String colmn_IND_STD_TYPE = "ALTER TABLE BASELAND_INDIVIDUAL_FACTOR ADD STD_TYPE nvarchar (10)  NULL";
    String colmn_IND_STD_UNIT = "ALTER TABLE BASELAND_INDIVIDUAL_FACTOR ADD STD_UNIT nvarchar (10)  NULL";
    String colmn_RENT_MONTH_LAND_POSITION = "ALTER TABLE BASELAND_RENT_MONTH ADD LAND_POSITION NVARCHAR (300)  NULL";
    String colmn_RENT_MONTH_ADDR = "ALTER TABLE BASELAND_RENT_MONTH ADD ADDR NVARCHAR (100)  NULL";
    String table_BASELAND_FLOOR_EFFECT = "CREATE TABLE BASELAND_FLOOR_EFFECT(BASENO NVARCHAR(10) NOT NULL,YEAR NVARCHAR(3) NOT NULL,CASENO NVARCHAR(1) NOT NULL,CITY NVARCHAR(1) NOT NULL,OFCE NVARCHAR(2) NOT NULL,JSONDATA NTEXT NOT NULL,AVG_EFFECT NUMERIC(8,2) NULL,AVG_RATIO NUMERIC(8,2) NULL)";
    String table_BASELAND_INDIVIDUAL_FACTOR_STD = "CREATE TABLE BASELAND_INDIVIDUAL_FACTOR_STD ([CITY] [nvarchar] (1) NOT NULL ,[DIST] [nvarchar] (2) NOT NULL ,[YEAR] [nvarchar] (3) NOT NULL ,[VERSION]  [nvarchar] (4) NOT NULL ,[BASENO] [nvarchar] (9) NOT NULL ,[ITEM] [nvarchar] (2) NOT NULL ,[LEVEL] [numeric] (2,0) NOT NULL ,[NONE] [nvarchar] (2) NOT NULL ,[CONTENTS] [nvarchar] (100) NOT NULL ,[A_SYMBOL] [nvarchar] (2) NOT NULL ,[A_DIGITAL] [numeric] (18,2) NOT NULL ,[AB_LOGIC] [nvarchar] (2) NOT NULL ,[B_SYMBOL] [nvarchar] (2) NOT NULL ,[B_DIGITAL] [numeric] (18,2) NOT NULL ,[BC_LOGIC] [nvarchar] (2) NOT NULL ,[C_SYMBOL] [nvarchar] (2) NOT NULL ,[C_DIGITAL] [numeric] (18,2) NOT NULL ,[CD_LOGIC] [nvarchar] (2) NOT NULL ,[D_SYMBOL] [nvarchar] (2) NOT NULL ,[D_DIGITAL] [numeric] (18,2) NOT NULL ,CONSTRAINT PK_BASELAND_INDIVIDUAL_FACTOR_STD PRIMARY KEY (CITY,DIST,YEAR,VERSION,BASENO,ITEM,LEVEL))";
    String table_BASELAND_REGIONAL_FACTOR_STD = "CREATE TABLE BASELAND_REGIONAL_FACTOR_STD ([CITY][nvarchar] (1) NOT NULL,[DIST][nvarchar] (2) NOT NULL,[YEAR][nvarchar] (3) NOT NULL,[VERSION][nvarchar] (4) NOT NULL,[BASENO] [nvarchar] (9) NOT NULL,[ITEM][nvarchar] (2) NOT NULL,[LEVEL][numeric](2,0)NOT NULL,[NONE][nvarchar] (2) NOT NULL,[CONTENTS][nvarchar] (100)NOT NULL,[A_SYMBOL][nvarchar] (2) NOT NULL,[A_DIGITAL] [numeric](18,2) NOT NULL,[AB_LOGIC][nvarchar] (2) NOT NULL,[B_SYMBOL][nvarchar] (2) NOT NULL,[B_DIGITAL] [numeric](18,2) NOT NULL,[BC_LOGIC][nvarchar] (2) NOT NULL,[C_SYMBOL][nvarchar] (2) NOT NULL,[C_DIGITAL] [numeric](18,2) NOT NULL,[CD_LOGIC][nvarchar] (2) NOT NULL,[D_SYMBOL][nvarchar] (2) NOT NULL,[D_DIGITAL] [numeric](18,2) NOT NULL,CONSTRAINT PK_BASELAND_REGIONAL_FACTOR_STD PRIMARY KEY ([CITY],[DIST],[YEAR],[VERSION],[BASENO],[ITEM],[LEVEL]))";

    public void start(Stage stage) throws Exception {
        if (isLogtoFile) {
            File file = new File(PATH + LOG_DIR);
            if (!file.exists()) {
                file.mkdirs();
            }
            System.setOut(new PrintStream(LOG_DIR + "/" + DateTime.getCurrentTime14() + ".log"));
        }
        stage.initStyle(StageStyle.UNDECORATED);
        if (this.checkJava() && this.checkDBCopy()) {
            this.openSystem(stage);
        }
    }

    private boolean checkDBCopy() {
        boolean bl = true;
        try {
            File file = new File(Model.SQLITE_PATH + "BaseLand.db");
            File file2 = new File(Model.SQLITE_PATH + "BaseLand_A.db");
            File file3 = new File(Model.SQLITE_PATH + "BaseLand_B.db");
            File file4 = new File(Model.SQLITE_PATH + "BaseLand_C.db");
            File file5 = new File(Model.SQLITE_PATH + "installed");
            if (!file.exists()) {
                FileUtils.copyFile((File)new File(Model.SQLITE_PATH + "init.db"), (File)file);
                FileUtils.copyFile((File)new File(Model.SQLITE_PATH + "init_ver.db"), (File)file2);
                FileUtils.copyFile((File)new File(Model.SQLITE_PATH + "init_ver.db"), (File)file3);
                FileUtils.copyFile((File)new File(Model.SQLITE_PATH + "init_ver.db"), (File)file4);
                new CarryOverModel().updateSect();
            } else if (file5.exists()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "\u662f\u5426\u91cd\u65b0\u8986\u84cb\u8cc7\u6599\u5eab? \u6ce8\u610f:\u5df2\u5efa\u7acb\u6848\u4ef6\u6703\u6e05\u9664!", new ButtonType[]{ButtonType.YES, ButtonType.NO});
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    new SystemCopyModel().backupDB();
                    FileUtils.copyFile((File)new File(Model.SQLITE_PATH + "init.db"), (File)file);
                    FileUtils.copyFile((File)new File(Model.SQLITE_PATH + "init_ver.db"), (File)file2);
                    FileUtils.copyFile((File)new File(Model.SQLITE_PATH + "init_ver.db"), (File)file3);
                    FileUtils.copyFile((File)new File(Model.SQLITE_PATH + "init_ver.db"), (File)file4);
                } else {
                    if (!file2.exists()) {
                        FileUtils.copyFile((File)new File(Model.SQLITE_PATH + "init_ver.db"), (File)file2);
                    }
                    if (!file3.exists()) {
                        FileUtils.copyFile((File)new File(Model.SQLITE_PATH + "init_ver.db"), (File)file3);
                    }
                    if (!file4.exists()) {
                        FileUtils.copyFile((File)new File(Model.SQLITE_PATH + "init_ver.db"), (File)file4);
                    }
                }
                new CarryOverModel().updateSect();
            }
            this.checkAndCreatTable(file);
            if (file5.exists()) {
                file5.delete();
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
            JavaFXUtil.showErrorMessageBox("\u521d\u59cb\u8cc7\u6599\u932f\u8aa4\uff0c\u8acb\u627e\u7a0b\u5f0f\u7ba1\u7406\u54e1");
            bl = false;
        }
        return bl;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void checkAndCreatTable(File file) {
        Connection connection = null;
        Connection connection2 = null;
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            if (file.exists()) {
                connection = ConnectionFactory.createConnection("sqlite", Model.SQLITE_PATH + "BaseLand.db", "", "", "", "");
                connection2 = ConnectionFactory.createConnection("sqlite", Model.SQLITE_PATH + "init.db", "", "", "", "");
                statement = connection.createStatement();
                this.execAlterOrCreateTable(connection, statement);
                this.updateFactorCode(statement, connection, connection2);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
            JavaFXUtil.showErrorMessageBox(exception.toString());
        }
        finally {
            SqlUtil.close(resultSet);
            SqlUtil.close(statement);
            SqlUtil.close(connection);
            SqlUtil.close(connection2);
        }
    }

    public void execAlterOrCreateTable(Connection connection, Statement statement) throws SQLException, Exception {
        this.createTable("BASELAND_FLOOR_EFFECT", this.table_BASELAND_FLOOR_EFFECT, statement, connection);
        this.createTable("BASELAND_INDIVIDUAL_FACTOR_STD", this.table_BASELAND_INDIVIDUAL_FACTOR_STD, statement, connection);
        this.createTable("BASELAND_REGIONAL_FACTOR_STD", this.table_BASELAND_REGIONAL_FACTOR_STD, statement, connection);
        this.createColumn("BASELAND_REGIONAL_FACTOR", "STD_TYPE", this.colmn_REGION_STD_TYPE, statement, connection);
        this.createColumn("BASELAND_REGIONAL_FACTOR", "STD_UNIT", this.colmn_REGION_STD_UNIT, statement, connection);
        this.createColumn("BASELAND_INDIVIDUAL_FACTOR", "STD_TYPE", this.colmn_IND_STD_TYPE, statement, connection);
        this.createColumn("BASELAND_INDIVIDUAL_FACTOR", "STD_UNIT", this.colmn_IND_STD_UNIT, statement, connection);
        this.createColumn("BASELAND_RENT_MONTH", "LAND_POSITION", this.colmn_RENT_MONTH_LAND_POSITION, statement, connection);
        this.createColumn("BASELAND_RENT_MONTH", "ADDR", this.colmn_RENT_MONTH_ADDR, statement, connection);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void updateFactorCode(Statement statement, Connection connection, Connection connection2) throws Exception {
        try (ResultSet resultSet = null;){
            resultSet = connection.getMetaData().getColumns(null, null, "BASELAND_FACTOR_CODE", "STDTYPE");
            if (!resultSet.next()) {
                System.out.println("BASELAND_FACTOR_CODE\u4e0d\u662f\u6700\u65b0\uff0c\u7acb\u5373\u66f4\u65b0");
                connection.setAutoCommit(false);
                statement.executeUpdate("DROP TABLE [BASELAND_FACTOR_CODE]");
                statement.executeUpdate("CREATE TABLE [BASELAND_FACTOR_CODE] ( [CODE_0] [nvarchar] (2) NOT NULL , [CODE_1] [nvarchar] (1) NOT NULL , [CODE_2] [nvarchar] (1) NOT NULL , [CODE_3] [nvarchar] (1) NOT NULL , [NAME] [nvarchar] (50) NOT NULL , [A3] [nvarchar] (1) NOT NULL , [A3BD] [nvarchar] (1) NOT NULL , [A3BF] [nvarchar] (1) NOT NULL , [SNA3] [numeric] (2,0) NOT NULL , [SNA3BD] [numeric] (2,0) NOT NULL , [SNA3BF] [numeric] (2,0) NOT NULL , [ASFIELD] [nvarchar] (10) NOT NULL , [STDTYPE] [nvarchar] (10) NOT NULL , [STDUNIT] [nvarchar] (10) NOT NULL ,CONSTRAINT [PK_BASELAND_FACTOR_CODE] PRIMARY KEY ([CODE_0],[CODE_1],[CODE_2],[CODE_3]) )");
                NDAO_BASELAND_FACTOR_CODE nDAO_BASELAND_FACTOR_CODE = new NDAO_BASELAND_FACTOR_CODE();
                ArrayList arrayList = nDAO_BASELAND_FACTOR_CODE.findBySql("select * from BASELAND_FACTOR_CODE", connection2);
                if (arrayList != null && arrayList.size() > 0) {
                    nDAO_BASELAND_FACTOR_CODE.update2(arrayList, connection);
                }
                connection.commit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void createTable(String string, String string2, Statement statement, Connection connection) throws SQLException {
        try (ResultSet resultSet = null;){
            resultSet = connection.getMetaData().getTables(null, null, string, null);
            if (!resultSet.next()) {
                System.out.println(string + "\u4e0d\u5b58\u5728\uff0c\u7acb\u5373\u65b0\u589e!!!");
                connection.setAutoCommit(false);
                statement.executeUpdate(string2);
                connection.commit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void createColumn(String string, String string2, String string3, Statement statement, Connection connection) throws SQLException {
        try (ResultSet resultSet = null;){
            resultSet = connection.getMetaData().getColumns(null, null, string, string2);
            if (!resultSet.next()) {
                System.out.println(string + "." + string2 + "\u4e0d\u5b58\u5728\uff0c\u7acb\u5373\u65b0\u589e!!!");
                connection.setAutoCommit(false);
                statement.executeUpdate(string3);
                connection.commit();
            }
        }
    }

    private void openSystem(Stage stage) throws IOException {
        Scene scene = new Scene((Parent)new StackPane());
        FXMLLoader fXMLLoader = new FXMLLoader(((Object)((Object)this)).getClass().getResource("/view/Main.fxml"));
        scene.setRoot((Parent)fXMLLoader.load());
        MainController mainController = (MainController)fXMLLoader.getController();
        stage.setScene(scene);
        mainController.init(null);
        stage.show();
    }

    private boolean checkJava() {
        BigDecimal bigDecimal;
        boolean bl = false;
        String string = System.getProperty("java.version");
        if (!StringProcess.isEmpty(string) && (bigDecimal = new BigDecimal(string.substring(0, 3))).compareTo(new BigDecimal("1.8")) > -1) {
            bl = true;
        }
        return bl;
    }

    public static void main(String[] stringArray) {
        MainApp.launch((String[])stringArray);
    }
}

