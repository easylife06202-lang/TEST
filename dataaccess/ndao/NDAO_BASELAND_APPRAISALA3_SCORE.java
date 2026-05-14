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
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_APPRAISALA3_SCORE;
import moiland.landuse.util.Us7Ascii;

public class NDAO_BASELAND_APPRAISALA3_SCORE
extends DaoBase {
    public NDAO_BASELAND_APPRAISALA3_SCORE() {
        this.fullClassName = "moiland.baseland.dataaccess.nvo.NVO_BASELAND_APPRAISALA3_SCORE";
        this.us = new Us7Ascii(false);
    }

    public TreeMap<String, NVO_BASELAND_APPRAISALA3_SCORE> queryDataMap(String string, String string2, Connection connection) throws Exception {
        TreeMap<String, NVO_BASELAND_APPRAISALA3_SCORE> treeMap = new TreeMap<String, NVO_BASELAND_APPRAISALA3_SCORE>();
        ArrayList<NVO_BASELAND_APPRAISALA3_SCORE> arrayList = this.queryData(string, string2, connection);
        if (arrayList.size() > 0) {
            for (NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE : arrayList) {
                treeMap.put(nVO_BASELAND_APPRAISALA3_SCORE.getAs_type(), nVO_BASELAND_APPRAISALA3_SCORE);
            }
        }
        return treeMap;
    }

    public ArrayList<NVO_BASELAND_APPRAISALA3_SCORE> queryData(String string, String string2, Connection connection) throws Exception {
        boolean bl = !StringProcess.isEmpty(string2) && !StringProcess.isEmpty(string);
        ArrayList arrayList = new ArrayList();
        if (bl) {
            SqlBuilder sqlBuilder = new SqlBuilder();
            StringBuffer stringBuffer = new StringBuffer("");
            ArrayList<String> arrayList2 = new ArrayList<String>();
            stringBuffer.append("SELECT * FROM BASELAND_APPRAISALA3_SCORE WHERE year=@@ AND baseno=@@");
            arrayList2.add(string);
            arrayList2.add(string2);
            stringBuffer.append(" ORDER BY AS_TYPE");
            sqlBuilder.setPreSql(stringBuffer.toString());
            sqlBuilder.setValueArray(arrayList2.toArray(), true);
            sqlBuilder.printSql = false;
            arrayList = this.findBySql(sqlBuilder.getSql(), connection);
        }
        return arrayList;
    }
}

