/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.dataaccess.ndao;

import com.wfusion.dataaccess.dao.DaoBase;
import com.wfusion.util.SqlBuilder;
import com.wfusion.util.SqlUtil;
import com.wfusion.util.StringProcess;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_INSTRU_STD_PRICE;
import moiland.landuse.util.Us7Ascii;

public class NDAO_BASELAND_INSTRU_STD_PRICE
extends DaoBase {
    public NDAO_BASELAND_INSTRU_STD_PRICE() {
        this.fullClassName = "moiland.baseland.dataaccess.nvo.NVO_BASELAND_INSTRU_STD_PRICE";
        this.us = new Us7Ascii(false);
    }

    public ArrayList<NVO_BASELAND_INSTRU_STD_PRICE> queryAllInstruStdPriceDataByCity(String string, Connection connection) throws Exception {
        String string2 = " SELECT * FROM BASELAND_INSTRU_STD_PRICE WHERE CITY=@@ ORDER BY FLOOR_START ";
        SqlBuilder sqlBuilder = new SqlBuilder(string2);
        int n = 0;
        sqlBuilder.setString(n++, string);
        return this.findBySql(sqlBuilder.getSql(), connection);
    }

    public ArrayList<NVO_BASELAND_INSTRU_STD_PRICE> queryOneInstruStdPriceData(String string, String string2, Connection connection) throws Exception {
        String string3 = " SELECT * FROM BASELAND_INSTRU_STD_PRICE WHERE CITY=@@ AND INSTRU_CODE=@@ ORDER BY FLOOR_START ";
        SqlBuilder sqlBuilder = new SqlBuilder(string3);
        int n = 0;
        sqlBuilder.setString(n++, string);
        sqlBuilder.setString(n++, string2);
        return this.findBySql(sqlBuilder.getSql(), connection);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int clearOneInstruStdPriceData(String string, String string2, Connection connection) throws SQLException {
        String string3 = " DELETE FROM BASELAND_INSTRU_STD_PRICE WHERE CITY=@@ AND INSTRU_CODE=@@ ";
        SqlBuilder sqlBuilder = new SqlBuilder(string3);
        int n = 0;
        sqlBuilder.setString(n++, string);
        sqlBuilder.setString(n++, string2);
        int n2 = 0;
        try {
            this._stmt = connection.createStatement();
            n2 = this._stmt.executeUpdate(sqlBuilder.getSql());
        }
        finally {
            SqlUtil.close(this._stmt);
        }
        return n2;
    }

    public ArrayList<NVO_BASELAND_INSTRU_STD_PRICE> queryDataForCopy(String string, String string2, Connection connection) throws Exception {
        boolean bl = !StringProcess.isEmpty(string);
        ArrayList arrayList = new ArrayList();
        if (bl) {
            SqlBuilder sqlBuilder = new SqlBuilder();
            StringBuffer stringBuffer = new StringBuffer();
            ArrayList<String> arrayList2 = new ArrayList<String>();
            stringBuffer.append(" SELECT * FROM BASELAND_INSTRU_STD_PRICE ");
            stringBuffer.append(" WHERE CITY=@@ ");
            arrayList2.add(string);
            if (!StringProcess.isEmpty(string2)) {
                stringBuffer.append("AND INSTRU_CODE=@@");
                arrayList2.add(string2);
            }
            stringBuffer.append(" ORDER BY CITY ");
            sqlBuilder.setPreSql(stringBuffer.toString());
            sqlBuilder.setValueArray(arrayList2.toArray(), true);
            NDAO_BASELAND_INSTRU_STD_PRICE nDAO_BASELAND_INSTRU_STD_PRICE = new NDAO_BASELAND_INSTRU_STD_PRICE();
            arrayList = nDAO_BASELAND_INSTRU_STD_PRICE.findBySql(sqlBuilder.getSql(), connection);
        }
        return arrayList;
    }
}

