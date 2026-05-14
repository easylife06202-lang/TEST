/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.baseland.system;

import com.wfusion.baseland.basic.Model;
import com.wfusion.util.SUtility;
import com.wfusion.util.SqlUtil;
import java.sql.Connection;
import java.util.ArrayList;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_INSTRU_STD_PRICE;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_INSTRU_STD_PRICE;
import moiland.baseland.util.BaseLandCode;

public class InstruStdPriceModel
extends Model {
    String AA45 = null;
    String instru_code = null;
    ArrayList<NVO_BASELAND_INSTRU_STD_PRICE> listData = null;

    public void query() {
        Connection connection = null;
        try {
            connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
            BaseLandCode.refreshCode(connection, connection, connection);
            this.listData = new NDAO_BASELAND_INSTRU_STD_PRICE().queryOneInstruStdPriceData(this.AA45, this.instru_code, connection);
            SqlUtil.close(connection);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void save() {
        if (this.listData == null || this.listData.size() <= 0) {
            return;
        }
        Connection connection = null;
        NDAO_BASELAND_INSTRU_STD_PRICE nDAO_BASELAND_INSTRU_STD_PRICE = new NDAO_BASELAND_INSTRU_STD_PRICE();
        try {
            connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
            connection.setAutoCommit(false);
            ArrayList<NVO_BASELAND_INSTRU_STD_PRICE> arrayList = nDAO_BASELAND_INSTRU_STD_PRICE.queryOneInstruStdPriceData(this.listData.get(0).getCity(), this.listData.get(0).getInstru_code(), connection);
            if (arrayList != null && arrayList.size() > 0) {
                nDAO_BASELAND_INSTRU_STD_PRICE.delete(arrayList, connection);
            }
            nDAO_BASELAND_INSTRU_STD_PRICE.update2(this.listData, connection);
            connection.commit();
            SqlUtil.close(connection);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
    }

    public ArrayList<NVO_BASELAND_INSTRU_STD_PRICE> getListData() {
        return this.listData;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void delete(NVO_BASELAND_INSTRU_STD_PRICE nVO_BASELAND_INSTRU_STD_PRICE) {
        Connection connection = null;
        try {
            connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
            new NDAO_BASELAND_INSTRU_STD_PRICE().delete(nVO_BASELAND_INSTRU_STD_PRICE, connection);
            this.listData.remove(nVO_BASELAND_INSTRU_STD_PRICE);
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

