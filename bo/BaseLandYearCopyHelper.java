/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.bo;

import com.wfusion.dataaccess.dao.DaoBase;
import com.wfusion.dataaccess.vo.VoBase;
import com.wfusion.util.SqlBuilder;
import com.wfusion.util.SqlUtil;
import com.wfusion.util.StringProcess;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_MAIN;

public class BaseLandYearCopyHelper
extends DaoBase {
    boolean overwrite = true;
    boolean overWriteIgnoreExistData = false;
    HashSet<String> ignore_BaseNoList = null;

    public BaseLandYearCopyHelper(boolean bl, boolean bl2) {
        this.overwrite = bl;
        this.overWriteIgnoreExistData = bl2;
        this.ignore_BaseNoList = new HashSet();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ArrayList<String> getExistYears(Connection connection) throws Exception {
        ArrayList<String> arrayList = new ArrayList<String>();
        String string = "select distinct year from baseland_main order by year desc";
        try {
            this._stmt = connection.createStatement();
            this._rs = this._stmt.executeQuery(string);
            while (this._rs.next()) {
                arrayList.add(this._rs.getString("year"));
            }
        }
        finally {
            this.close(this._rs);
            this.close(this._stmt);
        }
        return arrayList;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void prepareQueryBaseNo(String string, String string2, Connection connection) {
        if (this.overWriteIgnoreExistData) {
            this.ignore_BaseNoList.clear();
            HashSet<String> hashSet = new HashSet<String>();
            hashSet.add("BASELAND_INDIVIDUAL_FACTOR");
            hashSet.add("BASELAND_INDIVIDUAL_FACTOR_STD");
            hashSet.add("BASELAND_REGIONAL_FACTOR");
            hashSet.add("BASELAND_REGIONAL_FACTOR_STD");
            hashSet.add("BASELAND_MAIN");
            hashSet.add("BASELAND_IMAGES");
            hashSet.add("BASELAND_APPRAISALA3_SCORE");
            Statement statement = null;
            ResultSet resultSet = null;
            try {
                statement = connection.createStatement();
                for (String string3 : hashSet) {
                    SqlBuilder sqlBuilder = new SqlBuilder();
                    String string4 = "SELECT BASENO FROM " + string3 + " WHERE CITY=@@ AND YEAR=@@";
                    sqlBuilder.setPreSql(string4);
                    sqlBuilder.setString(0, string);
                    sqlBuilder.setString(1, string2);
                    resultSet = statement.executeQuery(sqlBuilder.getSql());
                    while (resultSet.next()) {
                        this.ignore_BaseNoList.add(resultSet.getString("BASENO").trim());
                    }
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
            finally {
                SqlUtil.close(resultSet);
                SqlUtil.close(statement);
            }
        }
    }

    public String copy(String string, String string2, String string3, Connection connection) throws Exception {
        this.prepareQueryBaseNo(string, string3, connection);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("BASELAND_AHP " + this.copyTable(string, string2, string3, "BASELAND_AHP", "", connection) + "\u7b46.");
        stringBuffer.append("BASELAND_INDIVIDUAL_FACTOR " + this.copyTable(string, string2, string3, "BASELAND_INDIVIDUAL_FACTOR", "", connection) + "\u7b46.");
        stringBuffer.append("BASELAND_INDIVIDUAL_FACTOR_STD " + this.copyTable(string, string2, string3, "BASELAND_INDIVIDUAL_FACTOR_STD", "", connection) + "\u7b46.");
        stringBuffer.append("BASELAND_REGIONAL_FACTOR " + this.copyTable(string, string2, string3, "BASELAND_REGIONAL_FACTOR", "", connection) + "\u7b46.");
        stringBuffer.append("BASELAND_REGIONAL_FACTOR_STD " + this.copyTable(string, string2, string3, "BASELAND_REGIONAL_FACTOR_STD", "", connection) + "\u7b46.");
        stringBuffer.append("BASELAND_REPORT_PARAM " + this.copyTable(string, string2, string3, "BASELAND_REPORT_PARAM", "", connection) + "\u7b46.");
        stringBuffer.append("BASELAND_MAIN " + this.copyTable(string, string2, string3, "BASELAND_MAIN", "", connection) + "\u7b46.");
        stringBuffer.append("BASELAND_IMAGES " + this.copyTable(string, string2, string3, "BASELAND_IMAGES", "photo_type='SKT'", connection) + "\u7b46.");
        stringBuffer.append("BASELAND_APPRAISALA3_SCORE " + this.copyTable(string, string2, string3, "BASELAND_APPRAISALA3_SCORE", "as_type='0'", connection) + "\u7b46.");
        return stringBuffer.toString();
    }

    private String copyTable(String string, String string2, String string3, String string4, String string5, Connection connection) throws Exception {
        String string6 = "select * from " + string4 + " where city=@@ and year=@@";
        String string7 = "delete from " + string4 + " where city=@@ and year=@@";
        if (!StringProcess.isEmpty(string5)) {
            string6 = string6 + " AND " + string5;
        }
        SqlBuilder sqlBuilder = new SqlBuilder(string6);
        sqlBuilder.setString(0, string);
        sqlBuilder.setString(1, string2);
        SqlBuilder sqlBuilder2 = new SqlBuilder(string7);
        sqlBuilder2.setString(0, string);
        sqlBuilder2.setString(1, string3);
        System.out.println(sqlBuilder.getSql());
        System.out.println(sqlBuilder2.getSql());
        DaoBase daoBase = (DaoBase)Class.forName("moiland.baseland.dataaccess.ndao.NDAO_" + string4).newInstance();
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList arrayList = daoBase.findBySql(sqlBuilder.getSql(), connection);
        for (Object object : arrayList) {
            ((VoBase)object).getElementAt("year").setValue(string3);
        }
        if ("BASELAND_MAIN".equalsIgnoreCase(string4)) {
            for (int i = 0; i < arrayList.size(); ++i) {
                Object object;
                object = (NVO_BASELAND_MAIN)arrayList.get(i);
                ((NVO_BASELAND_MAIN)object).setBase_price_pre(((NVO_BASELAND_MAIN)object).getBase_pricep());
                ((NVO_BASELAND_MAIN)object).setPrice_date(string3 + "0331");
            }
        }
        if (this.overwrite) {
            daoBase.executeUpdate(sqlBuilder2.getSql(), connection);
        }
        if (this.overWriteIgnoreExistData) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add("BASELAND_INDIVIDUAL_FACTOR");
            arrayList2.add("BASELAND_INDIVIDUAL_FACTOR_STD");
            arrayList2.add("BASELAND_REGIONAL_FACTOR");
            arrayList2.add("BASELAND_REGIONAL_FACTOR_STD");
            for (VoBase voBase : arrayList) {
                try {
                    String string8 = voBase.getTableName().toUpperCase();
                    if (string8.equals("BASELAND_REPORT_PARAM")) {
                        daoBase.create(voBase, connection);
                        continue;
                    }
                    if (!arrayList2.contains(string8) && this.ignore_BaseNoList.contains(voBase.getElementAt("BASENO").toString())) continue;
                    daoBase.create(voBase, connection);
                }
                catch (Exception exception) {}
            }
        } else {
            try {
                daoBase.create(arrayList, connection);
            }
            catch (Exception exception) {
                if (arrayList.size() != 0) {
                    throw new Exception("\n\u6b32\u8f49\u5165\u7684\u5e74\u5ea6\u5df2\u6709\u8cc7\u6599!!\uff0c\u8acb\u52fe\u9078\u8986\u84cb!!\n" + exception.getMessage());
                }
                throw exception;
            }
        }
        stringBuffer.append(arrayList.size());
        return stringBuffer.toString();
    }
}

