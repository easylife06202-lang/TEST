/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.dataaccess.ndao;

import com.wfusion.dataaccess.dao.DaoBase;
import com.wfusion.util.SqlBuilder;
import com.wfusion.util.StringProcess;
import java.sql.Connection;
import java.util.ArrayList;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_FLOOR_EFFECT;
import moiland.landuse.util.Us7Ascii;

public class NDAO_BASELAND_FLOOR_EFFECT
extends DaoBase {
    public NDAO_BASELAND_FLOOR_EFFECT() {
        this.fullClassName = "moiland.baseland.dataaccess.nvo.NVO_BASELAND_FLOOR_EFFECT";
        this.us = new Us7Ascii(false);
    }

    public void clear(String string, String string2, String string3, Connection connection) throws Exception {
        NVO_BASELAND_FLOOR_EFFECT nVO_BASELAND_FLOOR_EFFECT = new NVO_BASELAND_FLOOR_EFFECT();
        nVO_BASELAND_FLOOR_EFFECT.resetPk2SearchFlag(false);
        nVO_BASELAND_FLOOR_EFFECT.getElementAt("year").setSearchFlag(true);
        nVO_BASELAND_FLOOR_EFFECT.getElementAt("baseno").setSearchFlag(true);
        nVO_BASELAND_FLOOR_EFFECT.getElementAt("caseno").setSearchFlag(true);
        nVO_BASELAND_FLOOR_EFFECT.setYear(string);
        nVO_BASELAND_FLOOR_EFFECT.setBaseno(string2);
        nVO_BASELAND_FLOOR_EFFECT.setCaseno(string3);
        this.deletex(nVO_BASELAND_FLOOR_EFFECT, connection);
    }

    public NVO_BASELAND_FLOOR_EFFECT queryData(String string, String string2, String string3, Connection connection) throws Exception {
        NVO_BASELAND_FLOOR_EFFECT nVO_BASELAND_FLOOR_EFFECT = null;
        boolean bl = !StringProcess.isEmpty(string2) && !StringProcess.isEmpty(string) && !StringProcess.isEmpty(string3);
        ArrayList arrayList = new ArrayList();
        if (bl) {
            SqlBuilder sqlBuilder = new SqlBuilder();
            StringBuffer stringBuffer = new StringBuffer("");
            ArrayList<String> arrayList2 = new ArrayList<String>();
            stringBuffer.append("SELECT * FROM BASELAND_FLOOR_EFFECT WHERE year=@@ AND baseno=@@ AND caseno=@@");
            arrayList2.add(string);
            arrayList2.add(string2);
            arrayList2.add(string3);
            sqlBuilder.setPreSql(stringBuffer.toString());
            sqlBuilder.setValueArray(arrayList2.toArray(), true);
            sqlBuilder.printSql = false;
            arrayList = this.findBySql(sqlBuilder.getSql(), connection);
            if (arrayList.size() > 0) {
                nVO_BASELAND_FLOOR_EFFECT = (NVO_BASELAND_FLOOR_EFFECT)arrayList.get(0);
                nVO_BASELAND_FLOOR_EFFECT.setJsondata(nVO_BASELAND_FLOOR_EFFECT.getJsondata().replaceAll("&(?!amp;)", ""));
            }
        }
        return nVO_BASELAND_FLOOR_EFFECT;
    }
}

