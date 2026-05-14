/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.instru.bo;

import com.wfusion.util.SqlUtil;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_INSTRU;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_INSTRU;

public class BaseLandInstruCodeBo {
    private NDAO_BASELAND_INSTRU dao = new NDAO_BASELAND_INSTRU();

    public Map<String, String> getInstruCodeMap(Connection connection) {
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        ArrayList<NVO_BASELAND_INSTRU> arrayList = this.getAllData(connection);
        for (NVO_BASELAND_INSTRU nVO_BASELAND_INSTRU : arrayList) {
            treeMap.put(nVO_BASELAND_INSTRU.getInstru_code(), nVO_BASELAND_INSTRU.getInstru_name());
        }
        return treeMap;
    }

    public ArrayList<NVO_BASELAND_INSTRU> getAllData(Connection connection) {
        try {
            return this.dao.queryAllData(connection);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return new ArrayList<NVO_BASELAND_INSTRU>();
        }
    }

    public void saveAllData(ArrayList<NVO_BASELAND_INSTRU> arrayList, Connection connection) {
        try {
            connection.setAutoCommit(false);
            this.dao.clearAllData(connection);
            this.dao.create(arrayList, connection);
            connection.commit();
        }
        catch (Exception exception) {
            SqlUtil.rollback(connection);
        }
    }
}

