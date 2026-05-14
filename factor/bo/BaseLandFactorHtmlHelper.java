/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.factor.bo;

import com.wfusion.util.StringProcess;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import moiland.baseland.factor.bean.FactorItemBean;
import moiland.baseland.factor.bo.BaseLandFactorConstants;
import moiland.baseland.factor.em.EnumFactorStdType;
import moiland.baseland.factor.em.EnumFactorType;
import moiland.baseland.factor.face.FactorMainVoFace;
import moiland.baseland.factor.face.FactorStdVoFace;
import moiland.baseland.util.BaseLandCode;
import moiland.baseland.util.CodeList;

public class BaseLandFactorHtmlHelper
implements BaseLandFactorConstants {
    private static final int SHOW_TEXT_MAX_LENGTH = 47;

    public static List<String> getStdPreviewCode(EnumFactorType enumFactorType, FactorMainVoFace factorMainVoFace, ArrayList<? extends FactorStdVoFace> arrayList, String string) throws Exception {
        if (factorMainVoFace.getDegree() != factorMainVoFace.getDnamesArray().length) {
            throw new Exception(factorMainVoFace.getNameOfItem() + "\uff1a\u57fa\u6e96\u8868\u7d1a\u6578\u8207\u7b49\u7d1a\u540d\u7a31\u7684\u6578\u91cf\u4e0d\u7b26\uff01");
        }
        if (arrayList.size() > 0 && factorMainVoFace.getDegree() != arrayList.size()) {
            throw new Exception(factorMainVoFace.getNameOfItem() + "\uff1a\u57fa\u6e96\u8868\u7d1a\u6578\u8207\u6a19\u6e96\u8a2d\u5b9a\u7684\u8cc7\u6599\u7b46\u6578\u4e0d\u7b26\uff01");
        }
        EnumFactorStdType enumFactorStdType = EnumFactorStdType.findSelfByString(factorMainVoFace.getStd_type());
        List<String> list = new ArrayList<String>();
        switch (enumFactorStdType) {
            case CUSTOM: {
                list = BaseLandFactorHtmlHelper.generateHtmlByContents(factorMainVoFace.getDnamesArray(), arrayList, "\u2502", string);
                break;
            }
            case SELECTION: {
                Map<String, String> map = BaseLandFactorHtmlHelper.getCodeMap(enumFactorType, factorMainVoFace);
                list = BaseLandFactorHtmlHelper.generateHtmlBySelection(factorMainVoFace.getDnamesArray(), arrayList, map, string);
                break;
            }
            case NUMERAL: {
                list = BaseLandFactorHtmlHelper.generateHtmlByNumeral(factorMainVoFace.getDnamesArray(), arrayList, factorMainVoFace.getStd_unit(), string);
            }
        }
        return list;
    }

    private static List<String> generateHtmlByNumeral(String[] stringArray, ArrayList<? extends FactorStdVoFace> arrayList, String string, String string2) {
        ArrayList<String> arrayList2 = new ArrayList<String>();
        for (int i = 0; i < arrayList.size(); ++i) {
            FactorStdVoFace factorStdVoFace = arrayList.get(i);
            String string3 = String.valueOf(i + 1);
            String string4 = stringArray[i] + "\uff1a" + BaseLandFactorHtmlHelper.getShowText(factorStdVoFace, string);
            arrayList2.add(BaseLandFactorHtmlHelper.getOptionHtmlCode(string3, string4, string3.equals(string2)));
        }
        return arrayList2;
    }

    private static ArrayList<String> generateHtmlBySelection(String[] stringArray, ArrayList<? extends FactorStdVoFace> arrayList, Map<String, String> map, String string) {
        ArrayList<String> arrayList2 = new ArrayList<String>();
        for (int i = 0; i < arrayList.size(); ++i) {
            FactorStdVoFace factorStdVoFace = arrayList.get(i);
            int n = 47 - (stringArray[i] + "\uff1a").getBytes().length;
            String string2 = String.valueOf(i + 1);
            String string3 = stringArray[i] + "\uff1a" + factorStdVoFace.getContents();
            String string4 = stringArray[i] + "\uff1a" + BaseLandFactorHtmlHelper.getShowText(factorStdVoFace.getContents(), map, n);
            arrayList2.add(BaseLandFactorHtmlHelper.getOptionHtmlCodeWithCurtailText(string2, string3, string4, string2.equals(string)));
        }
        return arrayList2;
    }

    private static List<String> generateHtmlByContents(String[] stringArray, ArrayList<? extends FactorStdVoFace> arrayList, String string, String string2) {
        ArrayList<String> arrayList2 = new ArrayList<String>();
        for (int i = 0; i < arrayList.size(); ++i) {
            FactorStdVoFace factorStdVoFace = arrayList.get(i);
            int n = 47 - (stringArray[i] + string + BaseLandFactorHtmlHelper.getNoneText(factorStdVoFace.getNone())).getBytes().length;
            String string3 = String.valueOf(i + 1);
            String string4 = stringArray[i] + string + factorStdVoFace.getContents() + BaseLandFactorHtmlHelper.getNoneText(factorStdVoFace.getNone());
            String string5 = stringArray[i] + string + BaseLandFactorHtmlHelper.getShowText(factorStdVoFace.getContents(), n) + BaseLandFactorHtmlHelper.getNoneText(factorStdVoFace.getNone());
            arrayList2.add(BaseLandFactorHtmlHelper.getOptionHtmlCodeWithCurtailText(string3, string4, string5, string3.equals(string2)));
        }
        return arrayList2;
    }

    public static String getShowText(String string, int n) {
        StringBuffer stringBuffer = new StringBuffer(string);
        int n2 = string.indexOf("\uff08");
        int n3 = string.indexOf("\uff09");
        if (n2 > -1 && n3 > n2) {
            stringBuffer.delete(n2, n3 + 1);
        }
        return BaseLandFactorHtmlHelper.trimShowText(stringBuffer.toString(), n);
    }

    private static String getShowText(String string, Map<String, String> map, int n) {
        String[] stringArray;
        StringBuffer stringBuffer = new StringBuffer();
        for (String string2 : stringArray = string.split(":")) {
            stringBuffer.append("\u3001").append(StringProcess.NULL(map.get(string2), string2));
        }
        if (stringBuffer.length() > 0) {
            stringBuffer.delete(0, "\u3001".length());
        }
        return BaseLandFactorHtmlHelper.trimShowText(stringBuffer.toString(), n);
    }

    private static String getShowText(FactorStdVoFace factorStdVoFace, String string) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[\u503c]");
        stringBuffer.append(" ").append(factorStdVoFace.getA_symbol()).append(" ");
        stringBuffer.append(DF0.format(factorStdVoFace.getA_digital()));
        stringBuffer.append(string);
        if (factorStdVoFace.getB_symbol().length() > 0) {
            stringBuffer.append(" ").append(factorStdVoFace.getAb_logic()).append(" ");
            stringBuffer.append("[\u503c]");
            stringBuffer.append(" ").append(factorStdVoFace.getB_symbol()).append(" ");
            stringBuffer.append(DF0.format(factorStdVoFace.getB_digital()));
            stringBuffer.append(string);
        }
        if (factorStdVoFace.getC_symbol().length() > 0) {
            stringBuffer.append(" ").append(factorStdVoFace.getBc_logic()).append(" ");
            stringBuffer.append("[\u503c]");
            stringBuffer.append(" ").append(factorStdVoFace.getC_symbol()).append(" ");
            stringBuffer.append(DF0.format(factorStdVoFace.getC_digital()));
            stringBuffer.append(string);
        }
        if (factorStdVoFace.getD_symbol().length() > 0) {
            stringBuffer.append(" ").append(factorStdVoFace.getCd_logic()).append(" ");
            stringBuffer.append("[\u503c]");
            stringBuffer.append(" ").append(factorStdVoFace.getD_symbol()).append(" ");
            stringBuffer.append(DF0.format(factorStdVoFace.getD_digital()));
            stringBuffer.append(string);
        }
        stringBuffer.append(BaseLandFactorHtmlHelper.getNoneText(factorStdVoFace.getNone()));
        return stringBuffer.toString();
    }

    private static String trimShowText(String string, int n) {
        char c;
        int n2 = string.getBytes().length;
        if (n2 <= n) {
            return string;
        }
        if (n <= "...".length()) {
            return "...";
        }
        StringBuffer stringBuffer = new StringBuffer();
        int n3 = n - "...".length();
        int n4 = 0;
        for (int i = 0; i < string.length() && (n4 += (c = string.charAt(i)) < '\u0000' || c > '\u007f' ? 2 : 1) <= n3; ++i) {
            stringBuffer.append(c);
        }
        stringBuffer.append("...");
        return stringBuffer.toString();
    }

    private static String getOptionHtmlCodeWithCurtailText(String string, String string2, String string3, boolean bl) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<option value='").append(string).append("'").append(" title='").append(string2).append("'").append(bl ? " selected" : "").append(">").append(string3).append("</option>");
        return stringBuffer.toString();
    }

    private static String getOptionHtmlCode(String string, String string2, boolean bl) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<option value='").append(string).append("'").append(bl ? " selected" : "").append(">").append(string2).append("</option>");
        return stringBuffer.toString();
    }

    private static String getNoneText(String string) {
        return string.equals("\u7121") ? " \u6216\u300c\u7121\u300d" : "";
    }

    public static Map<String, String> getCodeMap(EnumFactorType enumFactorType, FactorMainVoFace factorMainVoFace) {
        if (EnumFactorType.INDIVIDUAL == enumFactorType) {
            FactorItemBean factorItemBean = BaseLandCode.getIndividualFactorItemByItem(factorMainVoFace.getVersion(), factorMainVoFace.getItem());
            if ("AS342".equals(factorItemBean.getItemField())) {
                return BaseLandCode.getCompareAs342Map();
            }
            if ("AS343".equals(factorItemBean.getItemField())) {
                return BaseLandCode.getCompareAs343Map();
            }
            if ("AS347".equals(factorItemBean.getItemField())) {
                return BaseLandCode.getCompareAs347Map();
            }
            if ("AS364".equals(factorItemBean.getItemField())) {
                return CodeList.getUrbanMap();
            }
            if ("AS368".equals(factorItemBean.getItemField())) {
                return BaseLandCode.getCompareAs368Map();
            }
        }
        return new TreeMap<String, String>();
    }
}

