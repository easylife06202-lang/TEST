/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.dataaccess.ndao;

import com.wfusion.dataaccess.dao.DaoBase;
import com.wfusion.util.SqlBuilder;
import com.wfusion.util.SqlUtil;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_IMAGES;
import moiland.landuse.util.Us7Ascii;

public class NDAO_BASELAND_IMAGES
extends DaoBase {
    public NDAO_BASELAND_IMAGES() {
        this.fullClassName = "moiland.baseland.dataaccess.nvo.NVO_BASELAND_IMAGES";
        this.us = new Us7Ascii(false);
    }

    public NVO_BASELAND_IMAGES findByPk(String string, String string2, String string3, Connection connection) throws Exception {
        String string4 = " SELECT * FROM BASELAND_IMAGES WHERE YEAR=@@ AND BASENO=@@ AND PHOTO_TYPE=@@ ";
        SqlBuilder sqlBuilder = new SqlBuilder(string4);
        sqlBuilder.setString(0, string);
        sqlBuilder.setString(1, string2);
        sqlBuilder.setString(2, string3);
        ArrayList arrayList = this.findBySql(sqlBuilder.getSql(), connection);
        NVO_BASELAND_IMAGES nVO_BASELAND_IMAGES = null;
        if (arrayList.size() > 0) {
            nVO_BASELAND_IMAGES = (NVO_BASELAND_IMAGES)arrayList.get(0);
        }
        return nVO_BASELAND_IMAGES;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void clearByPk(String string, String string2, String string3, Connection connection) throws Exception {
        String string4 = " DELETE FROM BASELAND_IMAGES WHERE YEAR=@@ AND BASENO=@@ AND PHOTO_TYPE=@@ ";
        SqlBuilder sqlBuilder = new SqlBuilder(string4);
        sqlBuilder.setString(0, string);
        sqlBuilder.setString(1, string2);
        sqlBuilder.setString(2, string3);
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sqlBuilder.getSql());
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(statement);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void clearByPk(String string, String string2, Connection connection) throws Exception {
        String string3 = " DELETE FROM BASELAND_IMAGES WHERE YEAR=@@ AND BASENO=@@ ";
        SqlBuilder sqlBuilder = new SqlBuilder(string3);
        sqlBuilder.setString(0, string);
        sqlBuilder.setString(1, string2);
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sqlBuilder.getSql());
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(statement);
        }
    }
}

