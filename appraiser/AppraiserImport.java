/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.appraiser;

import com.wfusion.baseland.MainApp;
import com.wfusion.dataaccess.dao.DaoBase;
import com.wfusion.dataaccess.vo.VoBase;
import com.wfusion.datasources.ConnectionFactory;
import com.wfusion.util.SqlBuilder;
import com.wfusion.util.SqlUtil;
import com.wfusion.util.StringProcess;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_MAIN;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_MAIN;

public class AppraiserImport {
    private String importPath = "";
    private String sourcePath = "";
    private String year = "";
    private String baseno = "";

    public AppraiserImport(String string, String string2) {
        this.importPath = string;
        this.sourcePath = string2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean checkBaseno(StringBuffer stringBuffer) {
        Connection connection = null;
        Connection connection2 = null;
        boolean bl = true;
        try {
            connection = ConnectionFactory.createConnection("sqlite", this.importPath, "", "", "empty", "empty");
            connection2 = ConnectionFactory.createConnection("sqlite", this.sourcePath, "", "", "empty", "empty");
            bl = this.check(stringBuffer, connection, connection2);
        }
        catch (Exception exception) {
            try {
                exception.printStackTrace();
                SqlUtil.rollback(connection2);
            }
            catch (Throwable throwable) {
                SqlUtil.close(connection);
                SqlUtil.close(connection2);
                throw throwable;
            }
            SqlUtil.close(connection);
            SqlUtil.close(connection2);
        }
        SqlUtil.close(connection);
        SqlUtil.close(connection2);
        return bl;
    }

    private boolean check(StringBuffer stringBuffer, Connection connection, Connection connection2) throws Exception {
        boolean bl = true;
        ArrayList<VoBase> arrayList = this.queryMainData("", "", connection);
        if (arrayList != null && arrayList.size() > 0) {
            this.year = arrayList.get(0).getElementAt("year").toString();
            this.baseno = arrayList.get(0).getElementAt("baseno").toString();
            ArrayList<VoBase> arrayList2 = this.queryMainData(this.year, this.baseno, connection2);
            if (arrayList2 != null && arrayList2.size() > 0) {
                bl = false;
            }
        } else {
            bl = false;
            stringBuffer.append("\u532f\u5165\u6a94\u6848\u7121\u4efb\u4f55\u6848\u4ef6\uff0c\u8acb\u78ba\u8a8d!!");
        }
        return bl;
    }

    private ArrayList<VoBase> queryMainData(String string, String string2, Connection connection) throws Exception {
        Object object;
        String string3 = "select * from BASELAND_MAIN where 1=1 ";
        if (!StringProcess.isEmpty(string)) {
            string3 = string3 + " and year=@@";
            object = new SqlBuilder(string3);
            ((SqlBuilder)object).setString(0, string);
            string3 = ((SqlBuilder)object).getSql();
        }
        if (!StringProcess.isEmpty(string2)) {
            string3 = string3 + " and baseno=@@";
            object = new SqlBuilder(string3);
            ((SqlBuilder)object).setString(0, string2);
            string3 = ((SqlBuilder)object).getSql();
        }
        System.out.println(string3);
        object = (DaoBase)Class.forName("moiland.baseland.dataaccess.ndao.NDAO_BASELAND_MAIN").newInstance();
        return ((DaoBase)object).findBySql(string3, connection);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean importData(StringBuffer stringBuffer) throws Exception {
        Connection connection = null;
        Connection connection2 = null;
        boolean bl = true;
        try {
            connection = ConnectionFactory.createConnection("sqlite", this.importPath, "", "", "empty", "empty");
            this.checkAndCreatTable(connection);
            connection2 = ConnectionFactory.createConnection("sqlite", this.sourcePath, "", "", "empty", "empty");
            connection2.setAutoCommit(false);
            stringBuffer.append(this.copy(connection, connection2));
            connection2.commit();
        }
        catch (Exception exception) {
            try {
                exception.printStackTrace();
                bl = false;
                stringBuffer.append("\u932f\u8aa4!" + exception.toString());
                SqlUtil.rollback(connection2);
            }
            catch (Throwable throwable) {
                SqlUtil.close(connection);
                SqlUtil.close(connection2);
                throw throwable;
            }
            SqlUtil.close(connection);
            SqlUtil.close(connection2);
        }
        SqlUtil.close(connection);
        SqlUtil.close(connection2);
        return bl;
    }

    private void checkAndCreatTable(Connection connection) throws Exception {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            new MainApp().execAlterOrCreateTable(connection, statement);
        }
        catch (Exception exception) {
            throw exception;
        }
        finally {
            SqlUtil.close(statement);
        }
    }

    public String copy(Connection connection, Connection connection2) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("BASELAND_MAIN " + this.copyTable(this.year, this.baseno, "BASELAND_MAIN", connection, connection2) + "\u7b46.\r\n");
        stringBuffer.append("BASELAND_APPRAISAL " + this.copyTable(this.year, this.baseno, "BASELAND_APPRAISAL", connection, connection2) + "\u7b46.\r\n");
        stringBuffer.append("BASELAND_APPRAISALA3_SCORE " + this.copyTable(this.year, this.baseno, "BASELAND_APPRAISALA3_SCORE", connection, connection2) + "\u7b46.\r\n");
        stringBuffer.append("BASELAND_SELL " + this.copyTable(this.year, this.baseno, "BASELAND_SELL", connection, connection2) + "\u7b46.\r\n");
        stringBuffer.append("BASELAND_FLOOR_EFFECT " + this.copyTable(this.year, this.baseno, "BASELAND_FLOOR_EFFECT", connection, connection2) + "\u7b46.\r\n");
        stringBuffer.append("BASELAND_RENT " + this.copyTable(this.year, this.baseno, "BASELAND_RENT", connection, connection2) + "\u7b46.\r\n");
        stringBuffer.append("BASELAND_RENT_EXT " + this.copyTable(this.year, this.baseno, "BASELAND_RENT_EXT", connection, connection2) + "\u7b46.\r\n");
        stringBuffer.append("BASELAND_RENT_MONTH " + this.copyTable(this.year, this.baseno, "BASELAND_RENT_MONTH", connection, connection2) + "\u7b46.\r\n");
        stringBuffer.append("BASELAND_DEVELOP " + this.copyTable(this.year, this.baseno, "BASELAND_DEVELOP", connection, connection2) + "\u7b46.\r\n");
        stringBuffer.append("BASELAND_DEVELOP_EXT " + this.copyTable(this.year, this.baseno, "BASELAND_DEVELOP_EXT", connection, connection2) + "\u7b46.\r\n");
        stringBuffer.append("BASELAND_IMAGES " + this.copyTable(this.year, this.baseno, "BASELAND_IMAGES", connection, connection2) + "\u7b46.\r\n");
        stringBuffer.append("BASELAND_REGIONAL_FACTOR " + this.copyFactorTable(this.year, this.baseno, "BASELAND_REGIONAL_FACTOR", connection, connection2) + "\u7b46.\r\n");
        stringBuffer.append("BASELAND_REGIONAL_FACTOR_STD " + this.copyFactorTable(this.year, this.baseno, "BASELAND_REGIONAL_FACTOR_STD", connection, connection2) + "\u7b46.\r\n");
        stringBuffer.append("BASELAND_INDIVIDUAL_FACTOR " + this.copyFactorTable(this.year, this.baseno, "BASELAND_INDIVIDUAL_FACTOR", connection, connection2) + "\u7b46.\r\n");
        stringBuffer.append("BASELAND_INDIVIDUAL_FACTOR_STD " + this.copyFactorTable(this.year, this.baseno, "BASELAND_INDIVIDUAL_FACTOR_STD", connection, connection2) + "\u7b46.\r\n");
        return stringBuffer.toString();
    }

    private String copyTable(String string, String string2, String string3, Connection connection, Connection connection2) throws Exception {
        Object object;
        String string4 = "select * from " + string3 + " where 1=1 ";
        if (!StringProcess.isEmpty(string)) {
            string4 = string4 + " and year=@@";
            object = new SqlBuilder(string4);
            ((SqlBuilder)object).setString(0, string);
            string4 = ((SqlBuilder)object).getSql();
        }
        if (!StringProcess.isEmpty(string2)) {
            string4 = string4 + " and baseno=@@";
            object = new SqlBuilder(string4);
            ((SqlBuilder)object).setString(0, string2);
            string4 = ((SqlBuilder)object).getSql();
        }
        System.out.println(string4);
        object = (DaoBase)Class.forName("moiland.baseland.dataaccess.ndao.NDAO_" + string3).newInstance();
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList arrayList = ((DaoBase)object).findBySql(string4, connection);
        ((DaoBase)object).delete(arrayList, connection2);
        ((DaoBase)object).create(arrayList, connection2);
        stringBuffer.append(arrayList.size());
        return stringBuffer.toString();
    }

    private String copyFactorTable(String string, String string2, String string3, Connection connection, Connection connection2) throws Exception {
        NDAO_BASELAND_MAIN nDAO_BASELAND_MAIN = new NDAO_BASELAND_MAIN();
        NVO_BASELAND_MAIN nVO_BASELAND_MAIN = nDAO_BASELAND_MAIN.findByPk(string2, string, connection);
        StringBuffer stringBuffer = new StringBuffer();
        if (nVO_BASELAND_MAIN != null) {
            DaoBase daoBase = (DaoBase)Class.forName("moiland.baseland.dataaccess.ndao.NDAO_" + string3).newInstance();
            String string4 = "select * from " + string3 + " where 1=1 and baseno=@@ ";
            SqlBuilder sqlBuilder = new SqlBuilder(string4);
            sqlBuilder.setString(0, string2);
            string4 = sqlBuilder.getSql();
            System.out.println(string4);
            ArrayList arrayList = daoBase.findBySql(string4, connection);
            if (arrayList == null || arrayList.size() < 1) {
                string4 = "select * from " + string3 + " where 1=1 ";
                string4 = string4 + " and year=@@";
                string4 = string4 + " and city=@@";
                string4 = string4 + " and dist=@@";
                string4 = string4 + " and version=@@";
                string4 = string4 + " and baseno=''";
                sqlBuilder = new SqlBuilder(string4);
                sqlBuilder.setString(0, string);
                sqlBuilder.setString(1, nVO_BASELAND_MAIN.getCity());
                sqlBuilder.setString(2, nVO_BASELAND_MAIN.getDist());
                String string5 = "";
                string5 = "BA".equals(nVO_BASELAND_MAIN.getUrban()) || "BB".equals(nVO_BASELAND_MAIN.getUrban()) ? "A3" : ("BD".equals(nVO_BASELAND_MAIN.getUrban()) ? "A3BD" : "A3BF");
                sqlBuilder.setString(3, string5);
                string4 = sqlBuilder.getSql();
                System.out.println(string4);
                arrayList = daoBase.findBySql(string4, connection);
                if (arrayList != null && arrayList.size() > 0) {
                    for (VoBase voBase : arrayList) {
                        voBase.getElementAt("baseno").setValue(string2);
                        System.out.println(voBase.getElementAt("baseno").toString());
                    }
                }
            }
            daoBase.delete(arrayList, connection2);
            daoBase.create(arrayList, connection2);
            stringBuffer.append(arrayList.size());
        }
        return stringBuffer.toString();
    }
}

