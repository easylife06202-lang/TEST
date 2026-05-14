/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.dataaccess.ndao;

import com.wfusion.dataaccess.dao.DaoBase;
import com.wfusion.util.SqlBuilder;
import com.wfusion.util.StringProcess;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.TreeMap;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_SELL;
import moiland.landuse.util.Us7Ascii;

public class NDAO_BASELAND_SELL
extends DaoBase {
    public NDAO_BASELAND_SELL() {
        this.fullClassName = "moiland.baseland.dataaccess.nvo.NVO_BASELAND_SELL";
        this.us = new Us7Ascii(false);
    }

    public ArrayList<NVO_BASELAND_SELL> queryData(String string, String string2, String string3, Connection connection) throws Exception {
        boolean bl = !StringProcess.isEmpty(string2) && !StringProcess.isEmpty(string);
        ArrayList arrayList = new ArrayList();
        if (bl) {
            SqlBuilder sqlBuilder = new SqlBuilder();
            StringBuffer stringBuffer = new StringBuffer("");
            ArrayList<String> arrayList2 = new ArrayList<String>();
            stringBuffer.append("SELECT * FROM BASELAND_SELL WHERE year=@@ AND baseno=@@");
            arrayList2.add(string);
            arrayList2.add(string2);
            if (!string3.equals("")) {
                stringBuffer.append(" AND caseno=@@");
                arrayList2.add(string3);
            }
            sqlBuilder.setPreSql(stringBuffer.toString());
            sqlBuilder.setValueArray(arrayList2.toArray(), true);
            sqlBuilder.printSql = false;
            arrayList = this.findBySql(sqlBuilder.getSql(), connection);
        }
        return arrayList;
    }

    public TreeMap<String, NVO_BASELAND_SELL> queryCaseSellMap(String string, String string2, String string3, Connection connection) throws Exception {
        TreeMap<String, NVO_BASELAND_SELL> treeMap = new TreeMap<String, NVO_BASELAND_SELL>();
        ArrayList<NVO_BASELAND_SELL> arrayList = this.queryData(string, string2, string3, connection);
        for (NVO_BASELAND_SELL nVO_BASELAND_SELL : arrayList) {
            treeMap.put(nVO_BASELAND_SELL.getCaseno(), nVO_BASELAND_SELL);
        }
        return treeMap;
    }
}

