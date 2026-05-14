/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.param.bo;

import com.wfusion.util.SqlUtil;
import java.sql.Connection;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_AHP;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_AHP;

public class BaseLandAhpParamDataBo {
    private String city = "";
    private String year = "";
    private NDAO_BASELAND_AHP dao = new NDAO_BASELAND_AHP();

    public BaseLandAhpParamDataBo(String string, String string2) {
        this.city = string;
        this.year = string2;
    }

    public NVO_BASELAND_AHP getEditData(Connection connection) {
        NVO_BASELAND_AHP nVO_BASELAND_AHP = null;
        try {
            nVO_BASELAND_AHP = this.dao.findByPk(this.city, this.year, connection);
            if (nVO_BASELAND_AHP == null) {
                nVO_BASELAND_AHP = new NVO_BASELAND_AHP();
                nVO_BASELAND_AHP.setCity(this.city);
                nVO_BASELAND_AHP.setYear(this.year);
                nVO_BASELAND_AHP.setHaveData(false);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return nVO_BASELAND_AHP;
    }

    public void saveData(NVO_BASELAND_AHP nVO_BASELAND_AHP, Connection connection) throws Exception {
        try {
            connection.setAutoCommit(false);
            this.dao.delete(nVO_BASELAND_AHP, connection);
            this.dao.create(nVO_BASELAND_AHP, connection);
            connection.commit();
        }
        catch (Exception exception) {
            SqlUtil.rollback(connection);
            exception.printStackTrace();
            throw exception;
        }
    }
}

