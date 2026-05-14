/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.util;

import com.wfusion.util.BigDecimalUtil;
import java.sql.Connection;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_PRICERATE;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_APPRAISALA3_SCORE;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_PRICERATE;
import moiland.baseland.pricerate.em.EnumPriceRateMainItem;
import moiland.baseland.pricerate.em.EnumPriceRateSubItem;

public class BaseLandPriceRateFillHelper {
    private static int SCALE_2 = 2;
    private NDAO_BASELAND_PRICERATE dao = new NDAO_BASELAND_PRICERATE();
    private String city = "";
    private String dist = "";
    private String year = "";
    private EnumPriceRateSubItem priceRateType = null;

    public BaseLandPriceRateFillHelper(String string, String string2, String string3, EnumPriceRateSubItem enumPriceRateSubItem) {
        this.city = string;
        this.priceRateType = enumPriceRateSubItem;
        this.dist = string2;
        this.year = string3;
    }

    public void setAs308Dv(NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE, NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE2, Connection connection) {
        try {
            double d = 100.0;
            double d2 = 0.0;
            NVO_BASELAND_PRICERATE nVO_BASELAND_PRICERATE = this.getIndexRate(nVO_BASELAND_APPRAISALA3_SCORE.getDist(), nVO_BASELAND_APPRAISALA3_SCORE.getAs308(), connection);
            if (nVO_BASELAND_PRICERATE != null) {
                nVO_BASELAND_APPRAISALA3_SCORE.setPriceRateDataExist(true);
                d = nVO_BASELAND_PRICERATE.getIndex_rate();
                NVO_BASELAND_PRICERATE nVO_BASELAND_PRICERATE2 = this.getIndexRate(nVO_BASELAND_APPRAISALA3_SCORE2.getDist(), nVO_BASELAND_APPRAISALA3_SCORE2.getAs308(), connection);
                d2 = nVO_BASELAND_PRICERATE2 == null ? 100.0 : nVO_BASELAND_PRICERATE2.getIndex_rate();
            }
            double d3 = BigDecimalUtil.round(BigDecimalUtil.sub(d, d2), SCALE_2);
            nVO_BASELAND_APPRAISALA3_SCORE2.setAs308_dv(d3);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public double getPriceRate(String string, String string2, Connection connection) {
        double d = 0.0;
        try {
            NVO_BASELAND_PRICERATE nVO_BASELAND_PRICERATE = this.getIndexRate(string, string2, connection);
            if (nVO_BASELAND_PRICERATE != null) {
                d = nVO_BASELAND_PRICERATE.getIndex_rate();
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return d;
    }

    private NVO_BASELAND_PRICERATE getIndexRate(String string, String string2, Connection connection) throws Exception {
        NVO_BASELAND_PRICERATE nVO_BASELAND_PRICERATE = null;
        if (string2.length() == 7) {
            String string3 = string2.substring(0, 5);
            if (EnumPriceRateMainItem.PPI == this.priceRateType.getMainItem() && (nVO_BASELAND_PRICERATE = this.dao.findByPk(this.city, this.priceRateType.toString(), string, this.year, string3, connection)) == null) {
                nVO_BASELAND_PRICERATE = this.dao.findByPk(this.city, this.priceRateType.getMainItem().toString(), string, this.year, string3, connection);
            }
            if (nVO_BASELAND_PRICERATE == null && (nVO_BASELAND_PRICERATE = this.dao.findByPk(this.city, this.priceRateType.toString(), "00", this.year, string3, connection)) == null) {
                nVO_BASELAND_PRICERATE = this.dao.findByPk(this.city, this.priceRateType.getMainItem().toString(), "00", this.year, string3, connection);
            }
        }
        return nVO_BASELAND_PRICERATE;
    }

    private NVO_BASELAND_PRICERATE getIndexRate(NVO_BASELAND_PRICERATE nVO_BASELAND_PRICERATE, NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE, Connection connection) throws Exception {
        NVO_BASELAND_PRICERATE nVO_BASELAND_PRICERATE2 = null;
        if (nVO_BASELAND_APPRAISALA3_SCORE.getAs308().length() == 7) {
            String string = nVO_BASELAND_APPRAISALA3_SCORE.getAs308().substring(0, 5);
            nVO_BASELAND_PRICERATE2 = this.dao.findByPk(nVO_BASELAND_PRICERATE.getCity(), nVO_BASELAND_PRICERATE.getRate_type(), nVO_BASELAND_APPRAISALA3_SCORE.getDist(), nVO_BASELAND_PRICERATE.getYear(), string, connection);
        }
        return nVO_BASELAND_PRICERATE2;
    }

    public String getIndexRateName(String string, Connection connection) throws Exception {
        NVO_BASELAND_PRICERATE nVO_BASELAND_PRICERATE = null;
        String string2 = "";
        if (string.length() == 7) {
            String string3 = string.substring(0, 5);
            if (this.priceRateType == null) {
                throw new Exception("\u5176\u4ed6\u516c\u5831\u53d6\u5f97\u932f\u8aa4!!\u8acb\u6aa2\u67e5\u662f\u5426\u6709\u8a2d\u5b9a\u5176\u4ed6\u516c\u5831\u53c3\u6578!!");
            }
            if (EnumPriceRateMainItem.PPI == this.priceRateType.getMainItem()) {
                nVO_BASELAND_PRICERATE = this.dao.findByPk(this.city, this.priceRateType.toString(), this.dist, this.year, string3, connection);
                string2 = this.priceRateType.getDescription();
                if (nVO_BASELAND_PRICERATE == null) {
                    nVO_BASELAND_PRICERATE = this.dao.findByPk(this.city, this.priceRateType.getMainItem().toString(), this.dist, this.year, string3, connection);
                    string2 = this.priceRateType.getMainItem().getDescription();
                }
            }
            if (nVO_BASELAND_PRICERATE == null) {
                nVO_BASELAND_PRICERATE = this.dao.findByPk(this.city, this.priceRateType.toString(), "00", this.year, string3, connection);
                string2 = "\u4e0d\u5206\u5340" + this.priceRateType.getDescription();
                if (nVO_BASELAND_PRICERATE == null) {
                    nVO_BASELAND_PRICERATE = this.dao.findByPk(this.city, this.priceRateType.getMainItem().toString(), "00", this.year, string3, connection);
                    string2 = this.priceRateType.getMainItem().getDescription();
                }
            }
        }
        return string2;
    }
}

