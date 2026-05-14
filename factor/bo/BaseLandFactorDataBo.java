/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.factor.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_INDIVIDUAL_FACTOR;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_INDIVIDUAL_FACTOR_STD;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_REGIONAL_FACTOR;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_REGIONAL_FACTOR_STD;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_INDIVIDUAL_FACTOR;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_INDIVIDUAL_FACTOR_STD;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_REGIONAL_FACTOR;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_REGIONAL_FACTOR_STD;
import moiland.baseland.factor.bean.FactorItemBean;
import moiland.baseland.factor.em.EnumFactorType;
import moiland.baseland.util.BaseLandCode;

public class BaseLandFactorDataBo {
    private EnumFactorType myFactorType = null;
    private NDAO_BASELAND_REGIONAL_FACTOR daoFRMain = null;
    private NDAO_BASELAND_REGIONAL_FACTOR_STD daoFRStd = null;
    private NDAO_BASELAND_INDIVIDUAL_FACTOR daoFIMain = null;
    private NDAO_BASELAND_INDIVIDUAL_FACTOR_STD daoFIStd = null;

    public BaseLandFactorDataBo(EnumFactorType enumFactorType) {
        this.myFactorType = enumFactorType;
        if (EnumFactorType.REGIONAL == enumFactorType) {
            this.daoFRMain = new NDAO_BASELAND_REGIONAL_FACTOR();
            this.daoFRStd = new NDAO_BASELAND_REGIONAL_FACTOR_STD();
        } else if (EnumFactorType.INDIVIDUAL == enumFactorType) {
            this.daoFIMain = new NDAO_BASELAND_INDIVIDUAL_FACTOR();
            this.daoFIStd = new NDAO_BASELAND_INDIVIDUAL_FACTOR_STD();
        }
    }

    public Map<String, String> getFactorMainCodeList(String string, String string2) {
        if (EnumFactorType.REGIONAL == this.myFactorType) {
            return BaseLandCode.getRegionalFactorMainCodeList(string, string2);
        }
        if (EnumFactorType.INDIVIDUAL == this.myFactorType) {
            return BaseLandCode.getIndividualFactorMainCodeList(string, string2);
        }
        return new TreeMap<String, String>();
    }

    public Map<String, String> getFactorItemList(String string, String string2) {
        if (EnumFactorType.REGIONAL == this.myFactorType) {
            return BaseLandCode.getRegionalFactorItemList(string, string2);
        }
        if (EnumFactorType.INDIVIDUAL == this.myFactorType) {
            return BaseLandCode.getIndividualFactorItemList(string, string2);
        }
        return new TreeMap<String, String>();
    }

    public Map<String, FactorItemBean> getFactorItemByVersion(String string) {
        if (EnumFactorType.REGIONAL == this.myFactorType) {
            return BaseLandCode.getRegionalFactorItemByVersion(string);
        }
        if (EnumFactorType.INDIVIDUAL == this.myFactorType) {
            return BaseLandCode.getIndividualFactorItemByVersion(string);
        }
        return new TreeMap<String, FactorItemBean>();
    }

    public FactorItemBean getFactorItemByItem(String string, String string2) {
        return this.getFactorItemByVersion(string).get(string2);
    }

    public ArrayList<NVO_BASELAND_REGIONAL_FACTOR> queryMainDataByMainCode(NVO_BASELAND_REGIONAL_FACTOR nVO_BASELAND_REGIONAL_FACTOR, Connection connection) throws Exception {
        if (EnumFactorType.REGIONAL != this.myFactorType) {
            throw new Exception("\u975e\u9069\u7528\u7684\u57fa\u6e96\u985e\u578b\uff1a[" + this.myFactorType.getDescription() + "]");
        }
        return this.daoFRMain.queryOneVersionByMainCode(nVO_BASELAND_REGIONAL_FACTOR.getCity(), nVO_BASELAND_REGIONAL_FACTOR.getDist(), nVO_BASELAND_REGIONAL_FACTOR.getYear(), nVO_BASELAND_REGIONAL_FACTOR.getVersion(), nVO_BASELAND_REGIONAL_FACTOR.getBaseno(), nVO_BASELAND_REGIONAL_FACTOR.getMainCode(), connection);
    }

    public ArrayList<NVO_BASELAND_INDIVIDUAL_FACTOR> queryMainDataByMainCode(NVO_BASELAND_INDIVIDUAL_FACTOR nVO_BASELAND_INDIVIDUAL_FACTOR, Connection connection) throws Exception {
        if (EnumFactorType.INDIVIDUAL != this.myFactorType) {
            throw new Exception("\u975e\u9069\u7528\u7684\u57fa\u6e96\u985e\u578b\uff1a[" + this.myFactorType.getDescription() + "]");
        }
        return this.daoFIMain.queryOneVersionByMainCode(nVO_BASELAND_INDIVIDUAL_FACTOR.getCity(), nVO_BASELAND_INDIVIDUAL_FACTOR.getDist(), nVO_BASELAND_INDIVIDUAL_FACTOR.getYear(), nVO_BASELAND_INDIVIDUAL_FACTOR.getVersion(), nVO_BASELAND_INDIVIDUAL_FACTOR.getBaseno(), nVO_BASELAND_INDIVIDUAL_FACTOR.getMainCode(), connection);
    }

    public NVO_BASELAND_REGIONAL_FACTOR findMainDataByPk(NVO_BASELAND_REGIONAL_FACTOR nVO_BASELAND_REGIONAL_FACTOR, Connection connection) throws Exception {
        if (EnumFactorType.REGIONAL != this.myFactorType) {
            throw new Exception("\u975e\u9069\u7528\u7684\u57fa\u6e96\u985e\u578b\uff1a[" + this.myFactorType.getDescription() + "]");
        }
        return this.daoFRMain.findByPk(nVO_BASELAND_REGIONAL_FACTOR, connection);
    }

    public NVO_BASELAND_INDIVIDUAL_FACTOR findMainDataByPk(NVO_BASELAND_INDIVIDUAL_FACTOR nVO_BASELAND_INDIVIDUAL_FACTOR, Connection connection) throws Exception {
        if (EnumFactorType.INDIVIDUAL != this.myFactorType) {
            throw new Exception("\u975e\u9069\u7528\u7684\u57fa\u6e96\u985e\u578b\uff1a[" + this.myFactorType.getDescription() + "]");
        }
        return this.daoFIMain.findByPk(nVO_BASELAND_INDIVIDUAL_FACTOR, connection);
    }

    public int clearStdDataByItem(NVO_BASELAND_REGIONAL_FACTOR nVO_BASELAND_REGIONAL_FACTOR, Connection connection) throws SQLException, Exception {
        if (EnumFactorType.REGIONAL != this.myFactorType) {
            throw new Exception("\u975e\u9069\u7528\u7684\u57fa\u6e96\u985e\u578b\uff1a[" + this.myFactorType.getDescription() + "]");
        }
        return this.daoFRStd.clearStdDataByItem(nVO_BASELAND_REGIONAL_FACTOR.getCity(), nVO_BASELAND_REGIONAL_FACTOR.getDist(), nVO_BASELAND_REGIONAL_FACTOR.getYear(), nVO_BASELAND_REGIONAL_FACTOR.getVersion(), nVO_BASELAND_REGIONAL_FACTOR.getBaseno(), nVO_BASELAND_REGIONAL_FACTOR.getItem(), connection);
    }

    public int clearStdDataByItem(NVO_BASELAND_INDIVIDUAL_FACTOR nVO_BASELAND_INDIVIDUAL_FACTOR, Connection connection) throws SQLException, Exception {
        if (EnumFactorType.INDIVIDUAL != this.myFactorType) {
            throw new Exception("\u975e\u9069\u7528\u7684\u57fa\u6e96\u985e\u578b\uff1a[" + this.myFactorType.getDescription() + "]");
        }
        return this.daoFIStd.clearStdDataByItem(nVO_BASELAND_INDIVIDUAL_FACTOR.getCity(), nVO_BASELAND_INDIVIDUAL_FACTOR.getDist(), nVO_BASELAND_INDIVIDUAL_FACTOR.getYear(), nVO_BASELAND_INDIVIDUAL_FACTOR.getVersion(), nVO_BASELAND_INDIVIDUAL_FACTOR.getBaseno(), nVO_BASELAND_INDIVIDUAL_FACTOR.getItem(), connection);
    }

    public int clearMainDataByPk(NVO_BASELAND_REGIONAL_FACTOR nVO_BASELAND_REGIONAL_FACTOR, Connection connection) throws Exception {
        if (EnumFactorType.REGIONAL != this.myFactorType) {
            throw new Exception("\u975e\u9069\u7528\u7684\u57fa\u6e96\u985e\u578b\uff1a[" + this.myFactorType.getDescription() + "]");
        }
        return this.daoFRMain.clearByPk(nVO_BASELAND_REGIONAL_FACTOR, connection);
    }

    public int clearMainDataByPk(NVO_BASELAND_INDIVIDUAL_FACTOR nVO_BASELAND_INDIVIDUAL_FACTOR, Connection connection) throws Exception {
        if (EnumFactorType.INDIVIDUAL != this.myFactorType) {
            throw new Exception("\u975e\u9069\u7528\u7684\u57fa\u6e96\u985e\u578b\uff1a[" + this.myFactorType.getDescription() + "]");
        }
        return this.daoFIMain.clearByPk(nVO_BASELAND_INDIVIDUAL_FACTOR, connection);
    }

    public void saveMainData(NVO_BASELAND_REGIONAL_FACTOR nVO_BASELAND_REGIONAL_FACTOR, Connection connection) throws Exception {
        if (EnumFactorType.REGIONAL != this.myFactorType) {
            throw new Exception("\u975e\u9069\u7528\u7684\u57fa\u6e96\u985e\u578b\uff1a[" + this.myFactorType.getDescription() + "]");
        }
        this.daoFRMain.create(nVO_BASELAND_REGIONAL_FACTOR, connection);
    }

    public void saveMainData(NVO_BASELAND_INDIVIDUAL_FACTOR nVO_BASELAND_INDIVIDUAL_FACTOR, Connection connection) throws Exception {
        if (EnumFactorType.INDIVIDUAL != this.myFactorType) {
            throw new Exception("\u975e\u9069\u7528\u7684\u57fa\u6e96\u985e\u578b\uff1a[" + this.myFactorType.getDescription() + "]");
        }
        this.daoFIMain.create(nVO_BASELAND_INDIVIDUAL_FACTOR, connection);
    }

    public int[] clearSpecificVersionByBaseno(String string, String string2, Connection connection) throws Exception {
        int[] nArray = new int[]{0, 0};
        if (EnumFactorType.REGIONAL == this.myFactorType) {
            nArray[0] = this.daoFRMain.clearSpecificVersionByBaseno(string, string2, connection);
            nArray[1] = this.daoFRStd.clearSpecificVersionByBaseno(string, string2, connection);
        } else if (EnumFactorType.INDIVIDUAL == this.myFactorType) {
            nArray[0] = this.daoFIMain.clearSpecificVersionByBaseno(string, string2, connection);
            nArray[1] = this.daoFIStd.clearSpecificVersionByBaseno(string, string2, connection);
        }
        return nArray;
    }

    public ArrayList<NVO_BASELAND_REGIONAL_FACTOR_STD> queryStdDataByItem(NVO_BASELAND_REGIONAL_FACTOR nVO_BASELAND_REGIONAL_FACTOR, Connection connection) throws Exception {
        if (EnumFactorType.REGIONAL != this.myFactorType) {
            throw new Exception("\u975e\u9069\u7528\u7684\u57fa\u6e96\u985e\u578b\uff1a[" + this.myFactorType.getDescription() + "]");
        }
        return this.daoFRStd.queryStdDataByItem(nVO_BASELAND_REGIONAL_FACTOR.getCity(), nVO_BASELAND_REGIONAL_FACTOR.getDist(), nVO_BASELAND_REGIONAL_FACTOR.getYear(), nVO_BASELAND_REGIONAL_FACTOR.getVersion(), nVO_BASELAND_REGIONAL_FACTOR.getBaseno(), nVO_BASELAND_REGIONAL_FACTOR.getItem(), connection);
    }

    public ArrayList<NVO_BASELAND_INDIVIDUAL_FACTOR_STD> queryStdDataByItem(NVO_BASELAND_INDIVIDUAL_FACTOR nVO_BASELAND_INDIVIDUAL_FACTOR, Connection connection) throws Exception {
        if (EnumFactorType.INDIVIDUAL != this.myFactorType) {
            throw new Exception("\u975e\u9069\u7528\u7684\u57fa\u6e96\u985e\u578b\uff1a[" + this.myFactorType.getDescription() + "]");
        }
        return this.daoFIStd.queryStdDataByItem(nVO_BASELAND_INDIVIDUAL_FACTOR.getCity(), nVO_BASELAND_INDIVIDUAL_FACTOR.getDist(), nVO_BASELAND_INDIVIDUAL_FACTOR.getYear(), nVO_BASELAND_INDIVIDUAL_FACTOR.getVersion(), nVO_BASELAND_INDIVIDUAL_FACTOR.getBaseno(), nVO_BASELAND_INDIVIDUAL_FACTOR.getItem(), connection);
    }

    public void saveStdData(NVO_BASELAND_REGIONAL_FACTOR nVO_BASELAND_REGIONAL_FACTOR, ArrayList<NVO_BASELAND_REGIONAL_FACTOR_STD> arrayList, Connection connection) throws Exception {
        if (EnumFactorType.REGIONAL != this.myFactorType) {
            throw new Exception("\u975e\u9069\u7528\u7684\u57fa\u6e96\u985e\u578b\uff1a[" + this.myFactorType.getDescription() + "]");
        }
        this.saveMainData(nVO_BASELAND_REGIONAL_FACTOR, connection);
        this.daoFRStd.create(arrayList, connection);
    }

    public void saveStdData(NVO_BASELAND_INDIVIDUAL_FACTOR nVO_BASELAND_INDIVIDUAL_FACTOR, ArrayList<NVO_BASELAND_INDIVIDUAL_FACTOR_STD> arrayList, Connection connection) throws Exception {
        if (EnumFactorType.INDIVIDUAL != this.myFactorType) {
            throw new Exception("\u975e\u9069\u7528\u7684\u57fa\u6e96\u985e\u578b\uff1a[" + this.myFactorType.getDescription() + "]");
        }
        this.saveMainData(nVO_BASELAND_INDIVIDUAL_FACTOR, connection);
        this.daoFIStd.create(arrayList, connection);
    }
}

