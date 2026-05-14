/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.bo;

import com.wfusion.util.SqlUtil;
import com.wfusion.util.StringProcess;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import moiland.baseland.action.bean.BaseLandMainParamBean;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_APPRAISAL;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_APPRAISALA3_SCORE;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_DEVELOP;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_DEVELOP_EXT;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_IMAGES;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_INDIVIDUAL_FACTOR;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_MAIN;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_REGIONAL_FACTOR;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_RENT;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_RENT_EXT;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_RENT_MONTH;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_SELL;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_APPRAISAL;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_APPRAISALA3_SCORE;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_DEVELOP;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_DEVELOP_EXT;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_MAIN;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT_EXT;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT_MONTH;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_SELL;
import moiland.baseland.util.CodeList;

public class BaseLandMainBo {
    private NDAO_BASELAND_MAIN ndao = new NDAO_BASELAND_MAIN();

    public static boolean isAppraiserCase(NVO_BASELAND_MAIN nVO_BASELAND_MAIN) {
        return nVO_BASELAND_MAIN == null ? false : nVO_BASELAND_MAIN.getCreator().matches("1");
    }

    public void queryBaseLandMain(BaseLandMainParamBean baseLandMainParamBean, Connection connection) throws Exception {
        NVO_BASELAND_MAIN nVO_BASELAND_MAIN = baseLandMainParamBean.getBandLandMainVo();
        if (baseLandMainParamBean.getMode().equals("add")) {
            if (nVO_BASELAND_MAIN.getBaseno().equals("")) {
                this.getNewBaseNo(nVO_BASELAND_MAIN, connection);
            } else {
                nVO_BASELAND_MAIN.setBaseseq(nVO_BASELAND_MAIN.getBaseno().substring(5));
            }
            baseLandMainParamBean.setBandLandMainVo(nVO_BASELAND_MAIN);
        } else {
            baseLandMainParamBean.getMessage().append("");
        }
    }

    public void clearBaseLandMain(String string, String string2, Connection connection) throws Exception {
        NDAO_BASELAND_DEVELOP_EXT nDAO_BASELAND_DEVELOP_EXT;
        NVO_BASELAND_DEVELOP_EXT nVO_BASELAND_DEVELOP_EXT;
        NDAO_BASELAND_DEVELOP nDAO_BASELAND_DEVELOP;
        NVO_BASELAND_DEVELOP nVO_BASELAND_DEVELOP;
        NDAO_BASELAND_SELL nDAO_BASELAND_SELL;
        ArrayList<NVO_BASELAND_SELL> arrayList;
        NDAO_BASELAND_APPRAISALA3_SCORE nDAO_BASELAND_APPRAISALA3_SCORE;
        ArrayList<NVO_BASELAND_APPRAISALA3_SCORE> arrayList2;
        this.ndao.clear(string, string2, connection);
        NDAO_BASELAND_APPRAISAL nDAO_BASELAND_APPRAISAL = new NDAO_BASELAND_APPRAISAL();
        ArrayList<NVO_BASELAND_APPRAISAL> arrayList3 = nDAO_BASELAND_APPRAISAL.queryData(string, string2, connection);
        if (arrayList3.size() > 0) {
            nDAO_BASELAND_APPRAISAL.delete(arrayList3, connection);
        }
        if ((arrayList2 = (nDAO_BASELAND_APPRAISALA3_SCORE = new NDAO_BASELAND_APPRAISALA3_SCORE()).queryData(string, string2, connection)).size() > 0) {
            nDAO_BASELAND_APPRAISALA3_SCORE.delete(arrayList2, connection);
        }
        if ((arrayList = (nDAO_BASELAND_SELL = new NDAO_BASELAND_SELL()).queryData(string, string2, "", connection)).size() > 0) {
            nDAO_BASELAND_SELL.delete(arrayList, connection);
        }
        NDAO_BASELAND_RENT nDAO_BASELAND_RENT = new NDAO_BASELAND_RENT();
        NDAO_BASELAND_RENT_MONTH nDAO_BASELAND_RENT_MONTH = new NDAO_BASELAND_RENT_MONTH();
        NDAO_BASELAND_RENT_EXT nDAO_BASELAND_RENT_EXT = new NDAO_BASELAND_RENT_EXT();
        ArrayList<NVO_BASELAND_RENT> arrayList4 = nDAO_BASELAND_RENT.queryData(string, string2, connection);
        ArrayList<NVO_BASELAND_RENT_MONTH> arrayList5 = nDAO_BASELAND_RENT_MONTH.queryData(string, string2, connection);
        ArrayList<NVO_BASELAND_RENT_EXT> arrayList6 = nDAO_BASELAND_RENT_EXT.queryData(string, string2, connection);
        if (arrayList4.size() > 0) {
            nDAO_BASELAND_RENT.delete(arrayList4, connection);
        }
        if (arrayList5.size() > 0) {
            nDAO_BASELAND_RENT_MONTH.delete(arrayList5, connection);
        }
        if (arrayList6.size() > 0) {
            nDAO_BASELAND_RENT_EXT.delete(arrayList6, connection);
        }
        if ((nVO_BASELAND_DEVELOP = (nDAO_BASELAND_DEVELOP = new NDAO_BASELAND_DEVELOP()).findByPk(string2, string, connection)) != null) {
            nDAO_BASELAND_DEVELOP.delete(nVO_BASELAND_DEVELOP, connection);
        }
        if ((nVO_BASELAND_DEVELOP_EXT = (nDAO_BASELAND_DEVELOP_EXT = new NDAO_BASELAND_DEVELOP_EXT()).findByPk(string2, string, connection)) != null) {
            nDAO_BASELAND_DEVELOP_EXT.delete(nVO_BASELAND_DEVELOP_EXT, connection);
        }
        NDAO_BASELAND_IMAGES nDAO_BASELAND_IMAGES = new NDAO_BASELAND_IMAGES();
        nDAO_BASELAND_IMAGES.clearByPk(string, string2, connection);
        NDAO_BASELAND_REGIONAL_FACTOR nDAO_BASELAND_REGIONAL_FACTOR = new NDAO_BASELAND_REGIONAL_FACTOR();
        nDAO_BASELAND_REGIONAL_FACTOR.clearSpecificVersionByBaseno(string, string2, connection);
        NDAO_BASELAND_INDIVIDUAL_FACTOR nDAO_BASELAND_INDIVIDUAL_FACTOR = new NDAO_BASELAND_INDIVIDUAL_FACTOR();
        nDAO_BASELAND_INDIVIDUAL_FACTOR.clearSpecificVersionByBaseno(string, string2, connection);
    }

    public void getNewBaseNo(NVO_BASELAND_MAIN nVO_BASELAND_MAIN, Connection connection) throws Exception {
        String string = this.ndao.getMaxNo(nVO_BASELAND_MAIN.getCity(), nVO_BASELAND_MAIN.getDist(), nVO_BASELAND_MAIN.getYear(), nVO_BASELAND_MAIN.getUrban(), connection);
        nVO_BASELAND_MAIN.setBaseseq(string.substring(5));
        if (string.length() >= 4) {
            nVO_BASELAND_MAIN.setBaseseq(string.substring(string.length() - 4));
        }
        nVO_BASELAND_MAIN.setBaseno(string);
    }

    public void saveBaseLandMain(BaseLandMainParamBean baseLandMainParamBean, Connection connection, Connection connection2, Connection connection3) throws Exception {
        boolean bl;
        baseLandMainParamBean.clearMessage();
        NVO_BASELAND_MAIN nVO_BASELAND_MAIN = baseLandMainParamBean.getBandLandMainVo();
        boolean bl2 = bl = !StringProcess.isEmpty(nVO_BASELAND_MAIN.getYear()) && !StringProcess.isEmpty(nVO_BASELAND_MAIN.getBaseno());
        if (StringProcess.isEmpty(nVO_BASELAND_MAIN.getVersion())) {
            baseLandMainParamBean.getMessage().append("\u516b\u3001\u63a8\u4f30\u57fa\u6e96\u5730\u5730\u50f9\u4e4b\u9644\u4ef6\n1.\u6bd4\u8f03\u6cd5\u8abf\u67e5\u4f30\u50f9\u8868\uff0c\u7248\u672c\u672a\u9078\u64c7\uff01\u4e0d\u80fd\u5132\u5b58\uff01");
            bl = false;
        }
        if (bl) {
            try {
                connection.setAutoCommit(false);
                NVO_BASELAND_MAIN nVO_BASELAND_MAIN2 = (NVO_BASELAND_MAIN)this.ndao.findByPk(nVO_BASELAND_MAIN, connection);
                if (baseLandMainParamBean.getMode().equals("add")) {
                    if (nVO_BASELAND_MAIN2 != null) {
                        baseLandMainParamBean.getMessage().append("\u7de8\u865f\u91cd\u8986\uff01\u5132\u5b58\u5931\u6557\uff01");
                    } else {
                        this.ndao.create(nVO_BASELAND_MAIN, connection);
                        baseLandMainParamBean.setMode("edit");
                    }
                } else {
                    if (nVO_BASELAND_MAIN2 != null) {
                        if (StringProcess.isEmpty(nVO_BASELAND_MAIN.getAa49())) {
                            nVO_BASELAND_MAIN.setGeom(null);
                        } else if (nVO_BASELAND_MAIN.getAa49().equals(StringProcess.NULL(nVO_BASELAND_MAIN2.getAa49())) && nVO_BASELAND_MAIN2.getGeom() != null) {
                            nVO_BASELAND_MAIN.setX(nVO_BASELAND_MAIN2.getX());
                            nVO_BASELAND_MAIN.setY(nVO_BASELAND_MAIN2.getY());
                            nVO_BASELAND_MAIN.setGeom(nVO_BASELAND_MAIN2.getGeom());
                        }
                    }
                    this.ndao.update(nVO_BASELAND_MAIN, connection);
                }
                connection.commit();
                NVO_BASELAND_MAIN nVO_BASELAND_MAIN3 = (NVO_BASELAND_MAIN)this.ndao.findByPk(nVO_BASELAND_MAIN, connection);
                if (nVO_BASELAND_MAIN3 != null) {
                    baseLandMainParamBean.getMessage().append("\u5132\u5b58\u6210\u529f\uff01");
                    baseLandMainParamBean.setSuccess(true);
                }
            }
            catch (SQLException sQLException) {
                SqlUtil.rollback(connection);
                baseLandMainParamBean.getMessage().append("\u5132\u5b58\u5931\u6557\uff01" + sQLException.getMessage());
            }
        }
    }

    public Map<String, String> getLandPosition(String string, String string2, Set<String> set, Connection connection) {
        TreeMap<String, String> treeMap2;
        Object object;
        HashSet<String> hashSet = new HashSet<String>();
        if (string2.indexOf(string2) > -1) {
            for (TreeMap<String, String> treeMap2 : object = string2.split(",")) {
                hashSet.add((String)((Object)treeMap2));
            }
        }
        object = this.splitKeyNumNotAdom(hashSet);
        StringBuilder stringBuilder = new StringBuilder("");
        String string3 = "";
        int n = 0;
        for (String string4 : ((TreeMap)object).keySet()) {
            if (n > 0) {
                stringBuilder.append("\u3001");
            } else {
                stringBuilder.append(CodeList.decodeCity(string));
                string3 = CodeList.getDistFromSect(string, string4);
                stringBuilder.append(CodeList.decodeDistFromSect(string, string4));
            }
            stringBuilder.append(CodeList.decodeSect(string, string4));
            HashSet hashSet2 = (HashSet)((TreeMap)object).get(string4);
            StringBuilder stringBuilder2 = new StringBuilder("");
            for (String string5 : hashSet2) {
                if (stringBuilder2.length() > 0) {
                    stringBuilder2.append("\u3001");
                }
                stringBuilder2.append(StringProcess.getLandShort(string5));
            }
            stringBuilder2.append("\u5730\u865f");
            if (set != null && set.size() > 1) {
                stringBuilder2.append("\u7b49").append(set.size()).append("\u7b46");
            }
            stringBuilder.append((CharSequence)stringBuilder2);
            ++n;
        }
        treeMap2 = new TreeMap<String, String>();
        treeMap2.put("landPosition", stringBuilder.toString());
        treeMap2.put("dist", string3);
        return treeMap2;
    }

    private TreeMap<String, HashSet<String>> splitLandNum(String string) {
        HashSet<String> hashSet = new HashSet<String>();
        if (string.length() > 0) {
            String[] stringArray;
            for (String string2 : stringArray = string.split(",")) {
                hashSet.add(string2);
            }
        }
        return this.splitKeyNumNotAdom(hashSet);
    }

    private TreeMap<String, HashSet<String>> splitKeyNumNotAdom(HashSet<String> hashSet) {
        TreeMap<String, HashSet<String>> treeMap = new TreeMap<String, HashSet<String>>();
        for (String string : hashSet) {
            if (string.length() == 12) {
                String string2 = string.substring(0, 4);
                String string3 = string.substring(4, 12);
                HashSet<Object> hashSet2 = null;
                hashSet2 = treeMap.containsKey(string2) ? treeMap.get(string2) : new HashSet();
                hashSet2.add(string3);
                treeMap.put(string2, hashSet2);
                continue;
            }
            System.out.println("key=>" + string);
        }
        return treeMap;
    }
}

