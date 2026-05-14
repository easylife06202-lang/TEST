/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.util;

import com.wfusion.util.BigDecimalUtil;
import com.wfusion.util.StringProcess;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_INDIVIDUAL_FACTOR;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_MAIN;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_REGIONAL_FACTOR;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_INDIVIDUAL_FACTOR;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_MAIN;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_REGIONAL_FACTOR;
import moiland.baseland.factor.bean.FactorItemBean;
import moiland.baseland.factor.bean.FactorLevelBean;
import moiland.baseland.factor.bean.FactorScoreBean;
import moiland.baseland.factor.em.EnumFactorVersion;
import moiland.baseland.util.BaseLandCode;
import moiland.baseland.util.BaseLandFactorVersionHelper;

public class BaseLandCompareFactorListHelper {
    private static final int SCALE_2 = 2;
    private static final boolean SORT_BY_CODE = true;
    private static final boolean SORT_BY_SN = false;
    private NVO_BASELAND_MAIN voMain = null;
    private String baseno = "";
    private String year = "";
    public String version = "";
    private Map<String, FactorItemBean> regionalFactorCodeMap = null;
    private Map<String, FactorItemBean> individualFactorCodeMap = null;
    private boolean existsIndividualData = false;
    private boolean existsRegionalData = false;
    public String reginVersion = "";
    public String individualVersion = "";

    public BaseLandCompareFactorListHelper(String string, String string2, String string3, String string4) {
        this.baseno = string;
        this.year = string2;
        EnumFactorVersion enumFactorVersion = BaseLandFactorVersionHelper.getFactorVersionByString(string3, string4);
        if (enumFactorVersion != null) {
            this.version = enumFactorVersion.toString();
        }
        this.regionalFactorCodeMap = BaseLandCode.getRegionalFactorItemByVersion(this.version);
        this.regionalFactorCodeMap.putAll(BaseLandCode.getRegionalFactorMainCodeByVersion(this.version));
        this.individualFactorCodeMap = BaseLandCode.getIndividualFactorItemByVersion(this.version);
        this.individualFactorCodeMap.putAll(BaseLandCode.getIndividualFactorMainCodeByVersion(this.version));
    }

    public Map<String, FactorLevelBean> getRegionalItemLevelList(Connection connection) throws Exception {
        return this.getRegionalItemLevelList(true, connection);
    }

    public Map<String, FactorLevelBean> getRegionalItemLevelListById(Connection connection) throws Exception {
        return this.getRegionalItemLevelList(false, connection);
    }

    public boolean isExistsRegionalData() {
        return this.existsRegionalData;
    }

    private Map<String, FactorLevelBean> getRegionalItemLevelList(boolean bl, Connection connection) throws Exception {
        Map<String, FactorLevelBean> map = BaseLandCompareFactorListHelper.generateLevelList(bl, this.regionalFactorCodeMap);
        try {
            this.initReference(connection);
            ArrayList<NVO_BASELAND_REGIONAL_FACTOR> arrayList = this.getRegionalFactorData(connection);
            String string = "";
            this.existsRegionalData = arrayList.size() > 0;
            for (NVO_BASELAND_REGIONAL_FACTOR nVO_BASELAND_REGIONAL_FACTOR : arrayList) {
                FactorItemBean factorItemBean = this.regionalFactorCodeMap.get(nVO_BASELAND_REGIONAL_FACTOR.getItem());
                FactorLevelBean factorLevelBean = BaseLandCompareFactorListHelper.generateFactorLevelBean(factorItemBean, nVO_BASELAND_REGIONAL_FACTOR.getImpact(), nVO_BASELAND_REGIONAL_FACTOR.getDegree(), nVO_BASELAND_REGIONAL_FACTOR.getDnames());
                string = bl ? factorItemBean.getItemCode() : String.valueOf(factorItemBean.getItemSn());
                map.put(string, factorLevelBean);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return map;
    }

    public Map<String, FactorLevelBean> getIndividualItemLevelList(Connection connection) throws Exception {
        return this.getIndividualItemLevelList(true, connection);
    }

    public Map<String, FactorLevelBean> getIndividualItemLevelListById(Connection connection) throws Exception {
        return this.getIndividualItemLevelList(false, connection);
    }

    public boolean isExistsIndividualData() {
        return this.existsIndividualData;
    }

    private Map<String, FactorLevelBean> getIndividualItemLevelList(boolean bl, Connection connection) throws Exception {
        Map<String, FactorLevelBean> map = BaseLandCompareFactorListHelper.generateLevelList(bl, this.individualFactorCodeMap);
        try {
            this.initReference(connection);
            ArrayList<NVO_BASELAND_INDIVIDUAL_FACTOR> arrayList = this.getIndividualFactorData(connection);
            String string = "";
            this.existsIndividualData = arrayList.size() > 0;
            for (NVO_BASELAND_INDIVIDUAL_FACTOR nVO_BASELAND_INDIVIDUAL_FACTOR : arrayList) {
                FactorItemBean factorItemBean = this.individualFactorCodeMap.get(nVO_BASELAND_INDIVIDUAL_FACTOR.getItem());
                FactorLevelBean factorLevelBean = BaseLandCompareFactorListHelper.generateFactorLevelBean(factorItemBean, nVO_BASELAND_INDIVIDUAL_FACTOR.getImpact(), nVO_BASELAND_INDIVIDUAL_FACTOR.getDegree(), nVO_BASELAND_INDIVIDUAL_FACTOR.getDnames());
                string = bl ? factorItemBean.getItemCode() : String.valueOf(factorItemBean.getItemSn());
                map.put(string, factorLevelBean);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return map;
    }

    private ArrayList<NVO_BASELAND_REGIONAL_FACTOR> getRegionalFactorData(Connection connection) {
        ArrayList<NVO_BASELAND_REGIONAL_FACTOR> arrayList = new ArrayList<NVO_BASELAND_REGIONAL_FACTOR>();
        try {
            NDAO_BASELAND_REGIONAL_FACTOR nDAO_BASELAND_REGIONAL_FACTOR = new NDAO_BASELAND_REGIONAL_FACTOR();
            arrayList = nDAO_BASELAND_REGIONAL_FACTOR.queryOneVersionByMainCode(this.voMain.getCity(), this.voMain.getDist(), this.year, this.version, this.baseno, "", connection);
            if (arrayList.size() == 0) {
                arrayList = nDAO_BASELAND_REGIONAL_FACTOR.queryOneVersionByMainCode(this.voMain.getCity(), this.voMain.getDist(), this.year, this.version, "", "", connection);
                this.reginVersion = this.getVersionName("", this.version);
            } else {
                this.reginVersion = this.getVersionName(this.baseno, this.version);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return arrayList;
    }

    private String getVersionName(String string, String string2) {
        String string3 = "";
        string3 = !StringProcess.isEmpty(string) ? "\u6848\u4ef6" : "\u901a\u7528";
        for (EnumFactorVersion enumFactorVersion : EnumFactorVersion.values()) {
            if (!enumFactorVersion.toString().equals(string2)) continue;
            string3 = string3 + enumFactorVersion.getDescription();
        }
        return string3;
    }

    private ArrayList<NVO_BASELAND_INDIVIDUAL_FACTOR> getIndividualFactorData(Connection connection) {
        ArrayList<NVO_BASELAND_INDIVIDUAL_FACTOR> arrayList = new ArrayList<NVO_BASELAND_INDIVIDUAL_FACTOR>();
        try {
            NDAO_BASELAND_INDIVIDUAL_FACTOR nDAO_BASELAND_INDIVIDUAL_FACTOR = new NDAO_BASELAND_INDIVIDUAL_FACTOR();
            arrayList = nDAO_BASELAND_INDIVIDUAL_FACTOR.queryOneVersionByMainCode(this.voMain.getCity(), this.voMain.getDist(), this.year, this.version, this.baseno, "", connection);
            if (arrayList.size() == 0) {
                arrayList = nDAO_BASELAND_INDIVIDUAL_FACTOR.queryOneVersionByMainCode(this.voMain.getCity(), this.voMain.getDist(), this.year, this.version, "", "", connection);
                this.individualVersion = this.getVersionName("", this.version);
            } else {
                this.individualVersion = this.getVersionName(this.baseno, this.version);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return arrayList;
    }

    private void initReference(Connection connection) throws Exception {
        this.voMain = new NDAO_BASELAND_MAIN().findByPk(this.baseno, this.year, connection);
        if (this.voMain == null) {
            this.voMain = new NVO_BASELAND_MAIN();
            this.voMain.setCity(this.baseno.substring(0, 1));
            this.voMain.setDist(this.baseno.substring(1, 3));
            this.voMain.setUrban(this.baseno.substring(3, 5));
            this.voMain.setBaseno(this.baseno);
            this.voMain.setYear(this.year);
        }
    }

    private static FactorLevelBean generateFactorLevelBean(FactorItemBean factorItemBean, double d, int n, String string) {
        FactorLevelBean factorLevelBean = new FactorLevelBean(factorItemBean);
        factorLevelBean.setLvList(BaseLandCompareFactorListHelper.generateScoreBean(d, n, string));
        return factorLevelBean;
    }

    private static ArrayList<FactorScoreBean> generateScoreBean(double d, int n, String string) {
        ArrayList<FactorScoreBean> arrayList = new ArrayList<FactorScoreBean>();
        String[] stringArray = string.split(",");
        if (stringArray.length != n) {
            System.out.println("\u7b49\u7d1a\u8207\u540d\u7a31\u7121\u6cd5\u5c0d\u61c9");
        } else {
            double d2 = d / (double)(n - 1);
            for (int i = 0; i < n; ++i) {
                double d3 = d - d2 * (double)i;
                FactorScoreBean factorScoreBean = new FactorScoreBean(i + 1, BigDecimalUtil.round(d3, 2), stringArray[i]);
                arrayList.add(factorScoreBean);
            }
        }
        return arrayList;
    }

    private static Map<String, FactorLevelBean> generateLevelList(boolean bl, Map<String, FactorItemBean> map) {
        TreeMap<String, FactorLevelBean> treeMap = new TreeMap<String, FactorLevelBean>();
        String string = "";
        for (String string2 : map.keySet()) {
            FactorItemBean factorItemBean = map.get(string2);
            string = bl ? factorItemBean.getItemCode() : String.valueOf(factorItemBean.getItemSn());
            treeMap.put(string, new FactorLevelBean(factorItemBean));
        }
        return treeMap;
    }

    public String getVersion() {
        return this.version;
    }
}

