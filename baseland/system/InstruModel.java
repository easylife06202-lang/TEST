/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.baseland.system;

import com.wfusion.baseland.basic.Model;
import com.wfusion.util.SUtility;
import com.wfusion.util.SqlUtil;
import java.sql.Connection;
import java.util.ArrayList;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_INSTRU;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_INSTRU;

public class InstruModel
extends Model {
    ArrayList<NVO_BASELAND_INSTRU> listData = new ArrayList();

    public void query() {
        Connection connection = null;
        try {
            connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
            this.listData = new NDAO_BASELAND_INSTRU().queryAllData(connection);
            SqlUtil.close(connection);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
    }

    public ArrayList<NVO_BASELAND_INSTRU> getListData() {
        return this.listData;
    }

    public void setListData(ArrayList<NVO_BASELAND_INSTRU> arrayList) {
        this.listData = arrayList;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void update(NVO_BASELAND_INSTRU nVO_BASELAND_INSTRU) {
        Connection connection = null;
        try {
            connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
            new NDAO_BASELAND_INSTRU().update(nVO_BASELAND_INSTRU, connection);
            SqlUtil.close(connection);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
    }
}

