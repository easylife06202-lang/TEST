/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.instru.bo;

import com.wfusion.util.SqlUtil;
import java.sql.Connection;
import java.util.ArrayList;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_INSTRU_STD_PRICE;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_INSTRU_STD_PRICE;

public class BaseLandInstruStdPriceDataBo {
    private NVO_BASELAND_INSTRU_STD_PRICE formBean = null;
    private NDAO_BASELAND_INSTRU_STD_PRICE dao = null;

    public BaseLandInstruStdPriceDataBo(NVO_BASELAND_INSTRU_STD_PRICE nVO_BASELAND_INSTRU_STD_PRICE) {
        this.formBean = nVO_BASELAND_INSTRU_STD_PRICE;
        this.dao = new NDAO_BASELAND_INSTRU_STD_PRICE();
    }

    public BaseLandInstruStdPriceDataBo(String string, String string2) {
        this.formBean = new NVO_BASELAND_INSTRU_STD_PRICE();
        this.formBean.setCity(string);
        this.formBean.setInstru_code(string2);
        this.dao = new NDAO_BASELAND_INSTRU_STD_PRICE();
    }

    public ArrayList<NVO_BASELAND_INSTRU_STD_PRICE> getEditData(Connection connection) {
        ArrayList<NVO_BASELAND_INSTRU_STD_PRICE> arrayList = new ArrayList<NVO_BASELAND_INSTRU_STD_PRICE>();
        try {
            arrayList = this.dao.queryOneInstruStdPriceData(this.formBean.getCity(), this.formBean.getInstru_code(), connection);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return arrayList;
    }

    public void updateData(ArrayList<NVO_BASELAND_INSTRU_STD_PRICE> arrayList, Connection connection) {
        try {
            connection.setAutoCommit(false);
            this.dao.clearOneInstruStdPriceData(this.formBean.getCity(), this.formBean.getInstru_code(), connection);
            this.dao.create(arrayList, connection);
            connection.commit();
        }
        catch (Exception exception) {
            SqlUtil.rollback(connection);
        }
    }
}

