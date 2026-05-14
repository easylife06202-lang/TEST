/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.dataaccess.ndao;

import com.wfusion.dataaccess.dao.DaoBase;
import com.wfusion.util.SqlBuilder;
import com.wfusion.util.StringProcess;
import java.sql.Connection;
import java.util.ArrayList;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT;
import moiland.landuse.util.Us7Ascii;

public class NDAO_BASELAND_RENT
extends DaoBase {
    public NDAO_BASELAND_RENT() {
        this.fullClassName = "moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT";
        this.us = new Us7Ascii(false);
    }

    public ArrayList<NVO_BASELAND_RENT> queryData(String string, String string2, Connection connection) throws Exception {
        boolean bl = !StringProcess.isEmpty(string2) && !StringProcess.isEmpty(string);
        ArrayList arrayList = new ArrayList();
        if (bl) {
            SqlBuilder sqlBuilder = new SqlBuilder();
            StringBuffer stringBuffer = new StringBuffer("");
            ArrayList<String> arrayList2 = new ArrayList<String>();
            stringBuffer.append("SELECT * FROM BASELAND_RENT WHERE year=@@ AND baseno=@@");
            arrayList2.add(string);
            arrayList2.add(string2);
            sqlBuilder.setPreSql(stringBuffer.toString());
            sqlBuilder.setValueArray(arrayList2.toArray(), true);
            sqlBuilder.printSql = false;
            arrayList = this.findBySql(sqlBuilder.getSql(), connection);
        }
        return arrayList;
    }
}

