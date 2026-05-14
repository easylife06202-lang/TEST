/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.dataaccess.ndao;

import com.wfusion.dataaccess.dao.DaoBase;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

public class NDAO_SRKEYN
extends DaoBase {
    private static final String sqlTown = "select kcde_2,kcnt from srkeyn where kcde_1='46' and kcde_2 not like '/*%' order by kcde_2";
    private static final String sqlSect = "select kcde_2,kcnt from srkeyn where kcde_1='48' and krmk=? and kcde_2 not like '/*%' order by kcde_2";
    private static final String sqlAllSect = "select kcde_2,kcnt from srkeyn where kcde_1='48' and kcde_2 not like '/*%' order by kcde_2";

    public NDAO_SRKEYN() {
        this.fullClassName = "moiland.landprice.dataaccess.nvo.NVO_SRKEYN";
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public TreeMap<String, String> getTown(Connection connection) {
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        try {
            this._stmt = connection.createStatement();
            this._rs = this._stmt.executeQuery(sqlTown);
            while (this._rs.next()) {
                treeMap.put(this._rs.getString("kcde_2"), this.us.Decoding(this._rs.getString("kcnt")));
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
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
    public TreeMap<String, String> getSect(String string, Connection connection) {
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        try {
            this._ps = connection.prepareStatement(sqlSect);
            this._ps.setString(1, string);
            this._rs = this._ps.executeQuery();
            while (this._rs.next()) {
                treeMap.put(this._rs.getString("kcde_2"), this.us.Decoding(this._rs.getString("kcnt")));
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            this.close(this._rs);
            this.close(this._ps);
        }
        return treeMap;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public TreeMap<String, String> getAllSect(Connection connection) {
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlAllSect);
            while (resultSet.next()) {
                treeMap.put(resultSet.getString("kcde_2"), this.us.Decoding(resultSet.getString("kcnt")));
            }
        }
        catch (Exception exception) {
            try {
                exception.printStackTrace();
            }
            catch (Throwable throwable) {
                this.close(resultSet);
                this.close(statement);
                throw throwable;
            }
            this.close(resultSet);
            this.close(statement);
        }
        this.close(resultSet);
        this.close(statement);
        return treeMap;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Map<String, String> getRegdUrbanMap(Connection connection) throws Exception {
        String string = "select distinct kcde_1, kcde_2, kcnt from srkeyn where (kcde_1='11' and kcde_2 like 'B%') or (kcde_1='12' and kcde_2 like 'E%') order by kcde_2";
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        try {
            this._stmt = connection.createStatement();
            this._rs = this._stmt.executeQuery(string);
            while (this._rs.next()) {
                treeMap.put(this._rs.getString("kcde_2"), this.us.Decoding(this._rs.getString("kcnt")));
            }
        }
        finally {
            this.close(this._rs);
            this.close(this._stmt);
        }
        return treeMap;
    }
}

