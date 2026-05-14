/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.dataaccess.ndao;

import com.wfusion.dataaccess.dao.DaoBase;
import com.wfusion.util.SqlBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import moiland.baseland.dataaccess.nvo.NVO_SRKEYN_ALL;
import moiland.landuse.util.Us7Ascii;

public class NDAO_SRKEYN_ALL
extends DaoBase {
    public NDAO_SRKEYN_ALL() {
        this.fullClassName = "moiland.baseland.dataaccess.nvo.NVO_SRKEYN_ALL";
        this.us = new Us7Ascii(false);
    }

    public Map<String, String> getCounty(Connection connection) throws Exception {
        String string = "SELECT DISTINCT * FROM SRKEYN_ALL WHERE KCDE_1='45' ORDER BY KCDE_2,KCDE_3,KCDE_4";
        ArrayList arrayList = this.findBySql(string, connection);
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        for (NVO_SRKEYN_ALL nVO_SRKEYN_ALL : arrayList) {
            treeMap.put(nVO_SRKEYN_ALL.getKcde_2(), nVO_SRKEYN_ALL.getKname());
        }
        return treeMap;
    }

    public Map<String, String> getOfficeAll(Connection connection) throws Exception {
        String string = "SELECT DISTINCT * FROM SRKEYN_ALL WHERE KCDE_1='LN' AND KCDE_3 NOT LIKE '%X' ORDER BY KCDE_2,KCDE_3";
        ArrayList arrayList = this.findBySql(string, connection);
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        for (NVO_SRKEYN_ALL nVO_SRKEYN_ALL : arrayList) {
            treeMap.put(nVO_SRKEYN_ALL.getKcde_3(), nVO_SRKEYN_ALL.getKname());
        }
        return treeMap;
    }

    public Map<String, String> getTownAll(Connection connection) throws Exception {
        String string = "SELECT DISTINCT * FROM SRKEYN_ALL WHERE KCDE_1='46' ORDER BY KCDE_2,KCDE_3";
        ArrayList arrayList = this.findBySql(string, connection);
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        for (NVO_SRKEYN_ALL nVO_SRKEYN_ALL : arrayList) {
            treeMap.put(nVO_SRKEYN_ALL.getKcde_2() + nVO_SRKEYN_ALL.getKcde_3() + nVO_SRKEYN_ALL.getKrmk(), nVO_SRKEYN_ALL.getKname());
        }
        return treeMap;
    }

    public Map<String, NVO_SRKEYN_ALL> getCityTown(String string, Connection connection) throws Exception {
        SqlBuilder sqlBuilder = new SqlBuilder("SELECT DISTINCT * FROM SRKEYN_ALL WHERE KCDE_1='46' AND KCDE_2=@@ ORDER BY KCDE_2,KCDE_3");
        sqlBuilder.setString(0, string);
        ArrayList arrayList = this.findBySql(sqlBuilder.getSql(), connection);
        TreeMap<String, NVO_SRKEYN_ALL> treeMap = new TreeMap<String, NVO_SRKEYN_ALL>();
        for (NVO_SRKEYN_ALL nVO_SRKEYN_ALL : arrayList) {
            treeMap.put(nVO_SRKEYN_ALL.getKcde_3(), nVO_SRKEYN_ALL);
        }
        return treeMap;
    }

    public Map<String, NVO_SRKEYN_ALL> getOfficeTown(String string, Connection connection) throws Exception {
        SqlBuilder sqlBuilder = new SqlBuilder("SELECT DISTINCT * FROM SRKEYN_ALL WHERE KCDE_1='46' AND KRMK=@@ ORDER BY KCDE_2,KCDE_3");
        sqlBuilder.setString(0, string);
        ArrayList arrayList = this.findBySql(sqlBuilder.getSql(), connection);
        TreeMap<String, NVO_SRKEYN_ALL> treeMap = new TreeMap<String, NVO_SRKEYN_ALL>();
        for (NVO_SRKEYN_ALL nVO_SRKEYN_ALL : arrayList) {
            treeMap.put(nVO_SRKEYN_ALL.getKcde_3(), nVO_SRKEYN_ALL);
        }
        return treeMap;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Map<String, String> getSiteTownMap(String string, Connection connection) throws Exception {
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        String string2 = null;
        string2 = string.substring(1).equals("0") ? "select kcde_3,kname from srkeyn_all where kcde_1='46' and kcde_2='" + string.substring(0, 1) + "' order by kcde_3" : "select kcde_3,kname from srkeyn_all where kcde_1='46' and krmk='" + string + "' order by kcde_3";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(string2);
            while (resultSet.next()) {
                treeMap.put(resultSet.getString("kcde_3"), resultSet.getString("kname"));
            }
        }
        catch (Throwable throwable) {
            this.close(resultSet);
            this.close(statement);
            throw throwable;
        }
        this.close(resultSet);
        this.close(statement);
        return treeMap;
    }

    public ArrayList<NVO_SRKEYN_ALL> getSectsByOfficeOrTown(String string, String string2, String string3, Connection connection) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("SELECT DISTINCT * FROM SRKEYN_ALL WHERE KCDE_1 = '48' and KCDE_2 = '").append(string).append("' and KCDE_4 IS NOT NULL ");
        if (!string3.equals("")) {
            stringBuffer.append("and KCDE_3 ='" + string3 + "'");
        } else if (string2.matches("[A-Z][A-W]")) {
            stringBuffer.append("and KRMK ='" + string2 + "'");
        }
        ArrayList arrayList = this.findBySql(stringBuffer.toString(), connection);
        return arrayList;
    }

    public Map<String, String> getSectAll(Connection connection) throws Exception {
        String string = "SELECT DISTINCT * FROM SRKEYN_ALL WHERE KCDE_1='48' ORDER BY KCDE_2,KCDE_3,KCDE_4";
        ArrayList arrayList = this.findBySql(string, connection);
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        for (NVO_SRKEYN_ALL nVO_SRKEYN_ALL : arrayList) {
            String string2 = nVO_SRKEYN_ALL.getKcde_2() + nVO_SRKEYN_ALL.getKcde_3() + nVO_SRKEYN_ALL.getKcde_4() + nVO_SRKEYN_ALL.getKrmk();
            String string3 = nVO_SRKEYN_ALL.getKname();
            treeMap.put(string2, string3);
        }
        return treeMap;
    }

    public Map<String, NVO_SRKEYN_ALL> getSectTownMap(String string, Connection connection) throws Exception {
        SqlBuilder sqlBuilder = new SqlBuilder("SELECT DISTINCT * FROM SRKEYN_ALL WHERE KCDE_1='48' AND KCDE_2=@@ ORDER BY KCDE_2,KCDE_3,KCDE_4");
        sqlBuilder.setString(0, string);
        ArrayList arrayList = this.findBySql(sqlBuilder.getSql(), connection);
        TreeMap<String, NVO_SRKEYN_ALL> treeMap = new TreeMap<String, NVO_SRKEYN_ALL>();
        for (NVO_SRKEYN_ALL nVO_SRKEYN_ALL : arrayList) {
            String string2 = nVO_SRKEYN_ALL.getKcde_2() + nVO_SRKEYN_ALL.getKcde_4();
            treeMap.put(string2, nVO_SRKEYN_ALL);
        }
        return treeMap;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Map<String, String> getSect2Town(String string, Connection connection) throws Exception {
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        String string2 = "select kcde_3 as aa46,kcde_4 as aa48 from srkeyn_all where kcde_1='48' and kcde_2='" + string + "'";
        try {
            this._stmt = connection.createStatement();
            this._rs = this._stmt.executeQuery(string2);
            while (this._rs.next()) {
                treeMap.put(this._rs.getString("aa48"), this._rs.getString("aa46"));
            }
        }
        finally {
            this.close(this._rs);
            this.close(this._stmt);
        }
        return treeMap;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public String getOfficeByTown(String string, String string2, Connection connection) throws Exception {
        String string3 = "select krmk from srkeyn_all where kcde_1='46' and kcde_2=? and kcde_3=? ";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String string4 = "";
        try {
            preparedStatement = connection.prepareStatement(string3);
            preparedStatement.setString(1, string);
            preparedStatement.setString(2, string2);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                string4 = resultSet.getString("krmk");
            }
        }
        catch (Throwable throwable) {
            this.close(resultSet);
            this.close(preparedStatement);
            throw throwable;
        }
        this.close(resultSet);
        this.close(preparedStatement);
        return string4;
    }
}

