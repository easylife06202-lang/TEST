/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.util;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_FACTOR_CODE;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_FACTOR_CODE;
import moiland.baseland.factor.bean.FactorItemBean;
import moiland.baseland.factor.em.EnumFactorType;
import moiland.baseland.factor.em.EnumFactorVersion;

public class BaseLandFactorListHelper {
    private static final boolean MAIN_CODE = true;
    private static final boolean ITEM_CODE = false;
    private static final String VERSION_YES = "1";

    public static Map<String, Map<String, FactorItemBean>> getRegionalMainList(Connection connection) {
        return BaseLandFactorListHelper.generateItemList(EnumFactorType.REGIONAL, true, connection);
    }

    public static Map<String, Map<String, FactorItemBean>> getAllRegionalItemList(Connection connection) {
        return BaseLandFactorListHelper.generateItemList(EnumFactorType.REGIONAL, false, connection);
    }

    public static Map<String, Map<String, FactorItemBean>> getIndividualMainList(Connection connection) {
        return BaseLandFactorListHelper.generateItemList(EnumFactorType.INDIVIDUAL, true, connection);
    }

    public static Map<String, Map<String, FactorItemBean>> getAllIndividualItemList(Connection connection) {
        return BaseLandFactorListHelper.generateItemList(EnumFactorType.INDIVIDUAL, false, connection);
    }

    private static Map<String, Map<String, FactorItemBean>> generateItemList(EnumFactorType enumFactorType, boolean bl, Connection connection) {
        TreeMap<String, Map<String, FactorItemBean>> treeMap = new TreeMap<String, Map<String, FactorItemBean>>();
        ArrayList<NVO_BASELAND_FACTOR_CODE> arrayList = BaseLandFactorListHelper.getFactorData(enumFactorType, bl, connection);
        String string = "";
        String string2 = "";
        String string3 = "";
        String string4 = "";
        String string5 = "";
        String string6 = "";
        String string7 = "";
        int n = 0;
        for (NVO_BASELAND_FACTOR_CODE nVO_BASELAND_FACTOR_CODE : arrayList) {
            string = bl ? nVO_BASELAND_FACTOR_CODE.getCode_1() : nVO_BASELAND_FACTOR_CODE.getCode_1() + nVO_BASELAND_FACTOR_CODE.getCode_2();
            string2 = nVO_BASELAND_FACTOR_CODE.getName();
            string3 = nVO_BASELAND_FACTOR_CODE.getAsfield();
            string6 = nVO_BASELAND_FACTOR_CODE.getStdtype();
            string7 = nVO_BASELAND_FACTOR_CODE.getStdunit();
            for (EnumFactorVersion enumFactorVersion : EnumFactorVersion.values()) {
                string4 = enumFactorVersion.toString();
                string5 = enumFactorVersion.getFactorCode();
                if (!nVO_BASELAND_FACTOR_CODE.getElementAt(string5).toString().equals(VERSION_YES)) continue;
                n = (Integer)nVO_BASELAND_FACTOR_CODE.getElementAt("SN" + string5).getObject();
                if (!treeMap.containsKey(string4)) {
                    treeMap.put(string4, new TreeMap());
                }
                ((Map)treeMap.get(string4)).put(string, new FactorItemBean(string, string2, n, string3, string6, string7));
            }
        }
        return treeMap;
    }

    private static ArrayList<NVO_BASELAND_FACTOR_CODE> getFactorData(EnumFactorType enumFactorType, boolean bl, Connection connection) {
        ArrayList arrayList = new ArrayList();
        try {
            String string = bl ? BaseLandFactorListHelper.getMainCodeQueryString(enumFactorType) : BaseLandFactorListHelper.getItemsQueryString(enumFactorType);
            arrayList = new NDAO_BASELAND_FACTOR_CODE().findBySql(string, connection);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return arrayList;
    }

    private static String getMainCodeQueryString(EnumFactorType enumFactorType) {
        StringBuffer stringBuffer = new StringBuffer(" SELECT * FROM BASELAND_FACTOR_CODE WHERE CODE_2='' AND CODE_3='' ");
        stringBuffer.append(" AND CODE_0='").append(enumFactorType.getCode()).append("'");
        stringBuffer.append(" ORDER BY CODE_1 ");
        return stringBuffer.toString();
    }

    private static String getItemsQueryString(EnumFactorType enumFactorType) {
        StringBuffer stringBuffer = new StringBuffer(" SELECT * FROM BASELAND_FACTOR_CODE WHERE CODE_2<>'' AND CODE_3<>'' ");
        stringBuffer.append(" AND CODE_0='").append(enumFactorType.getCode()).append("'");
        stringBuffer.append(" ORDER BY CODE_1, CODE_2, CODE_3 ");
        return stringBuffer.toString();
    }
}

