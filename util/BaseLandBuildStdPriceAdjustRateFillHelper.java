/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.util;

import java.sql.Connection;
import moiland.baseland.buildprice.adjust.bo.BaseLandBuildPriceAdjustDataBo;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_DEVELOP;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT_EXT;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_SELL;
import moiland.baseland.report.param.bo.BaseLandReportParamDataBo;

public class BaseLandBuildStdPriceAdjustRateFillHelper {
    private static final double ZERO_PERCENT = 0.0;
    private String city = "";
    private String year = "";

    public BaseLandBuildStdPriceAdjustRateFillHelper(String string, String string2) {
        this.city = string;
        this.year = string2;
    }

    public void setBuildCostAdjustRate(NVO_BASELAND_SELL nVO_BASELAND_SELL, String string, Connection connection) {
        if (string.length() > 5) {
            double d = this.getAdjustRatio(string, nVO_BASELAND_SELL.getBaseno(), connection);
            nVO_BASELAND_SELL.setCs29(d);
        } else {
            nVO_BASELAND_SELL.setCs29(0.0);
        }
    }

    public void setBuildCostAdjustRate(NVO_BASELAND_RENT_EXT nVO_BASELAND_RENT_EXT, String string, Connection connection) {
        if (string.length() > 5) {
            double d = this.getAdjustRatio(string, nVO_BASELAND_RENT_EXT.getBaseno(), connection);
            nVO_BASELAND_RENT_EXT.setCre32(d);
        } else {
            nVO_BASELAND_RENT_EXT.setCre32(0.0);
        }
    }

    public void setBuildCostAdjustRate(NVO_BASELAND_DEVELOP nVO_BASELAND_DEVELOP, String string, Connection connection) {
        if (string.length() > 5) {
            double d = this.getAdjustRatio(string, nVO_BASELAND_DEVELOP.getBaseno(), connection);
            nVO_BASELAND_DEVELOP.setBuild_cost_rate(d);
        } else {
            nVO_BASELAND_DEVELOP.setBuild_cost_rate(0.0);
        }
    }

    public double getAdjustRatio(String string, String string2, Connection connection) {
        double d = 0.0;
        if (string.length() == 7) {
            String string3 = string.substring(0, 3);
            String string4 = string.substring(3, 5);
            String string5 = this.getBuildCostBaseDate(connection);
            if (string5.length() == 5) {
                d = BaseLandBuildPriceAdjustDataBo.getRatioData(string5, string3, string4, connection).getRatio();
            }
        }
        return d;
    }

    private String getBuildCostBaseDate(Connection connection) {
        return new BaseLandReportParamDataBo(this.city, this.year).getEditData(connection).getBuild_cost_basedate();
    }
}

