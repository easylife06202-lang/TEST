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
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT_MONTH;
import moiland.landuse.util.Us7Ascii;

public class NDAO_BASELAND_RENT_MONTH
extends DaoBase {
    public NDAO_BASELAND_RENT_MONTH() {
        this.fullClassName = "moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT_MONTH";
        this.us = new Us7Ascii(false);
    }

    public TreeMap<String, NVO_BASELAND_RENT_MONTH> queryDataMap(String string, String string2, Connection connection) throws Exception {
        TreeMap<String, NVO_BASELAND_RENT_MONTH> treeMap = new TreeMap<String, NVO_BASELAND_RENT_MONTH>();
        ArrayList<NVO_BASELAND_RENT_MONTH> arrayList = this.queryData(string, string2, connection);
        if (arrayList.size() > 0) {
            for (NVO_BASELAND_RENT_MONTH nVO_BASELAND_RENT_MONTH : arrayList) {
                treeMap.put(nVO_BASELAND_RENT_MONTH.getRent_caseno(), nVO_BASELAND_RENT_MONTH);
            }
        }
        return treeMap;
    }

    public ArrayList<NVO_BASELAND_RENT_MONTH> queryData(String string, String string2, Connection connection) throws Exception {
        boolean bl = !StringProcess.isEmpty(string2) && !StringProcess.isEmpty(string);
        ArrayList arrayList = new ArrayList();
        if (bl) {
            SqlBuilder sqlBuilder = new SqlBuilder();
            StringBuffer stringBuffer = new StringBuffer("");
            ArrayList<String> arrayList2 = new ArrayList<String>();
            stringBuffer.append("SELECT * FROM BASELAND_RENT_MONTH WHERE year=@@ AND baseno=@@ ORDER BY RENT_CASENO");
            arrayList2.add(string);
            arrayList2.add(string2);
            sqlBuilder.setPreSql(stringBuffer.toString());
            sqlBuilder.setValueArray(arrayList2.toArray(), true);
            sqlBuilder.printSql = false;
            arrayList = this.findBySql(sqlBuilder.getSql(), connection);
        }
        return arrayList;
    }

    public NVO_BASELAND_RENT_MONTH findByPk(String string, String string2, String string3, Connection connection) throws Exception {
        NVO_BASELAND_RENT_MONTH nVO_BASELAND_RENT_MONTH = new NVO_BASELAND_RENT_MONTH();
        nVO_BASELAND_RENT_MONTH.setYear(string);
        nVO_BASELAND_RENT_MONTH.setBaseno(string2);
        nVO_BASELAND_RENT_MONTH.setRent_caseno(string3);
        return (NVO_BASELAND_RENT_MONTH)this.findByPk(nVO_BASELAND_RENT_MONTH, connection);
    }
}

