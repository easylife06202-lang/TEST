/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.baseland.estimate;

import com.wfusion.baseland.basic.Model;
import com.wfusion.datasources.ConnectionFactory;
import com.wfusion.fx.util.JavaFXUtil;
import com.wfusion.util.SqlUtil;
import java.sql.Connection;
import moiland.baseland.appraiser.AppraiserExport;

public class EstimateVersionCopyModel
extends Model {
    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean copyVersion(String string, String string2, String string3, String string4) {
        boolean bl;
        block5: {
            Connection connection = null;
            Connection connection2 = null;
            bl = true;
            try {
                connection = ConnectionFactory.createConnection("sqlite", Model.SQLITE_PATH + string3, "", "", "empty", "empty");
                connection2 = ConnectionFactory.createConnection("sqlite", Model.SQLITE_PATH + string4, "", "", "empty", "empty");
                AppraiserExport appraiserExport = new AppraiserExport(true);
                connection2.setAutoCommit(false);
                appraiserExport.copyToVersion(string, string2, connection, connection2);
                connection2.commit();
                SqlUtil.close(connection);
            }
            catch (Exception exception) {
                exception.printStackTrace();
                JavaFXUtil.showErrorMessageBox("\u8907\u88fd\u932f\u8aa4\uff0c\u8acb\u806f\u7d61\u7cfb\u7d71\u7ba1\u7406\u54e1!", exception.toString());
                bl = false;
                break block5;
            }
            finally {
                SqlUtil.close(connection);
                SqlUtil.close(connection2);
            }
            SqlUtil.close(connection2);
        }
        return bl;
    }
}

