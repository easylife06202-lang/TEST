/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.factor.bo;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedList;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_INDIVIDUAL_FACTOR;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_REGIONAL_FACTOR;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_INDIVIDUAL_FACTOR;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_INDIVIDUAL_FACTOR_STD;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_REGIONAL_FACTOR;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_REGIONAL_FACTOR_STD;
import moiland.baseland.factor.bo.BaseLandFactorDataBo;
import moiland.baseland.factor.em.EnumFactorStdType;
import moiland.baseland.factor.em.EnumFactorType;
import moiland.baseland.factor.face.FactorMainVoFace;
import moiland.baseland.factor.face.FactorStdVoFace;
import moiland.baseland.factor.util.ReversePolishNotationTool;
import moiland.baseland.util.BaseLandCode;

public class BaseLandFactorStdTool {
    public static final String SYMBOL_COLON = ":";

    public static boolean isEmptyProperty(FactorStdVoFace factorStdVoFace) {
        boolean bl = true;
        if (factorStdVoFace.getContents().length() > 0 || factorStdVoFace.getA_symbol().length() > 0) {
            bl = false;
        }
        return bl;
    }

    public String getRegionalAutoLevel(String string, String string2, String string3, String string4, String string5, String string6, String string7, String string8, Connection connection) throws Exception {
        NVO_BASELAND_REGIONAL_FACTOR nVO_BASELAND_REGIONAL_FACTOR = this.getRegionalFactorStdMain(string, string2, string3, string4, string5, string6, connection);
        if (nVO_BASELAND_REGIONAL_FACTOR == null) {
            throw new Exception("\u4e3b\u8868\u8cc7\u6599\u5df2\u4e0d\u5b58\u5728\uff01\n\n[" + BaseLandCode.decodeRegionalItemCode(string4, string6) + "]");
        }
        ArrayList<NVO_BASELAND_REGIONAL_FACTOR_STD> arrayList = new BaseLandFactorDataBo(EnumFactorType.REGIONAL).queryStdDataByItem(nVO_BASELAND_REGIONAL_FACTOR, connection);
        if (arrayList.size() > 0 && arrayList.size() != nVO_BASELAND_REGIONAL_FACTOR.getDegree()) {
            throw new Exception("\u7b49\u7d1a\u6a19\u6e96\u8cc7\u6599\u6709\u8aa4\uff0c\u8acb\u91cd\u8a2d\u7b49\u7d1a\uff01\n\n[" + BaseLandCode.decodeRegionalItemCode(string4, string6) + "]");
        }
        return this.getAutoLevel(nVO_BASELAND_REGIONAL_FACTOR, arrayList, string7, string8);
    }

    public String getIndividualAutoLevel(String string, String string2, String string3, String string4, String string5, String string6, String string7, String string8, Connection connection) throws Exception {
        NVO_BASELAND_INDIVIDUAL_FACTOR nVO_BASELAND_INDIVIDUAL_FACTOR = this.getIndividualFactorStdMain(string, string2, string3, string4, string5, string6, connection);
        if (nVO_BASELAND_INDIVIDUAL_FACTOR == null) {
            throw new Exception("\u4e3b\u8868\u8cc7\u6599\u5df2\u4e0d\u5b58\u5728\uff01\n\n[" + BaseLandCode.decodeIndividualItem(string4, string6) + "]");
        }
        ArrayList<NVO_BASELAND_INDIVIDUAL_FACTOR_STD> arrayList = new BaseLandFactorDataBo(EnumFactorType.INDIVIDUAL).queryStdDataByItem(nVO_BASELAND_INDIVIDUAL_FACTOR, connection);
        if (arrayList.size() > 0 && arrayList.size() != nVO_BASELAND_INDIVIDUAL_FACTOR.getDegree()) {
            throw new Exception("\u7b49\u7d1a\u6a19\u6e96\u8cc7\u6599\u6709\u8aa4\uff0c\u8acb\u91cd\u8a2d\u7b49\u7d1a\uff01\n\n[" + BaseLandCode.decodeIndividualItem(string4, string6) + "]");
        }
        return this.getAutoLevel(nVO_BASELAND_INDIVIDUAL_FACTOR, arrayList, string7, string8);
    }

    private NVO_BASELAND_REGIONAL_FACTOR getRegionalFactorStdMain(String string, String string2, String string3, String string4, String string5, String string6, Connection connection) {
        NVO_BASELAND_REGIONAL_FACTOR nVO_BASELAND_REGIONAL_FACTOR = null;
        try {
            NDAO_BASELAND_REGIONAL_FACTOR nDAO_BASELAND_REGIONAL_FACTOR = new NDAO_BASELAND_REGIONAL_FACTOR();
            nVO_BASELAND_REGIONAL_FACTOR = nDAO_BASELAND_REGIONAL_FACTOR.findByPk(string, string2, string3, string4, string5, string6, connection);
            if (nVO_BASELAND_REGIONAL_FACTOR == null) {
                nVO_BASELAND_REGIONAL_FACTOR = nDAO_BASELAND_REGIONAL_FACTOR.findByPk(string, string2, string3, string4, "", string6, connection);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return nVO_BASELAND_REGIONAL_FACTOR;
    }

    private NVO_BASELAND_INDIVIDUAL_FACTOR getIndividualFactorStdMain(String string, String string2, String string3, String string4, String string5, String string6, Connection connection) {
        NVO_BASELAND_INDIVIDUAL_FACTOR nVO_BASELAND_INDIVIDUAL_FACTOR = null;
        try {
            NDAO_BASELAND_INDIVIDUAL_FACTOR nDAO_BASELAND_INDIVIDUAL_FACTOR = new NDAO_BASELAND_INDIVIDUAL_FACTOR();
            nVO_BASELAND_INDIVIDUAL_FACTOR = nDAO_BASELAND_INDIVIDUAL_FACTOR.findByPk(string, string2, string3, string4, string5, string6, connection);
            if (nVO_BASELAND_INDIVIDUAL_FACTOR == null) {
                nVO_BASELAND_INDIVIDUAL_FACTOR = nDAO_BASELAND_INDIVIDUAL_FACTOR.findByPk(string, string2, string3, string4, "", string6, connection);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return nVO_BASELAND_INDIVIDUAL_FACTOR;
    }

    private String getAutoLevel(FactorMainVoFace factorMainVoFace, ArrayList<? extends FactorStdVoFace> arrayList, String string, String string2) throws Exception {
        EnumFactorStdType enumFactorStdType = EnumFactorStdType.findSelfByString(factorMainVoFace.getStd_type());
        if (enumFactorStdType == null) {
            return string;
        }
        String string3 = "";
        switch (enumFactorStdType) {
            case CUSTOM: {
                string3 = string;
                break;
            }
            case NUMERAL: {
                string3 = this.getAutoLevelByNumeral(arrayList, string2);
                break;
            }
            case SELECTION: {
                string3 = this.getAutoLevelBySelection(arrayList, string2, String.valueOf(factorMainVoFace.getDegree()));
            }
        }
        return string3;
    }

    private String getAutoLevelBySelection(ArrayList<? extends FactorStdVoFace> arrayList, String string, String string2) {
        if ("".equals(string)) {
            return "";
        }
        String string3 = string2;
        block0: for (FactorStdVoFace factorStdVoFace : arrayList) {
            String[] stringArray;
            for (String string4 : stringArray = factorStdVoFace.getContents().split(SYMBOL_COLON)) {
                if (!string4.equals(string)) continue;
                string3 = String.valueOf(factorStdVoFace.getLevel());
                break block0;
            }
        }
        return string3;
    }

    private String getAutoLevelByNumeral(ArrayList<? extends FactorStdVoFace> arrayList, String string) throws Exception {
        if (!string.matches("[0-9]+(\\.[0-9]+)?")) {
            return "";
        }
        double d = Double.valueOf(string);
        String string2 = "";
        for (FactorStdVoFace factorStdVoFace : arrayList) {
            boolean bl = this.matchLevelRange(factorStdVoFace, d);
            if (!bl) continue;
            string2 = String.valueOf(factorStdVoFace.getLevel());
            break;
        }
        if ("".equals(string2)) {
            throw new Exception("\u67e5\u7121\u76f8\u7b26\u7684\u7b49\u7d1a\uff01");
        }
        return string2;
    }

    private boolean matchLevelRange(FactorStdVoFace factorStdVoFace, double d) throws Exception {
        LinkedList<Object> linkedList = ReversePolishNotationTool.getNotation(factorStdVoFace, d);
        boolean bl = ReversePolishNotationTool.calculateNotation(linkedList);
        return bl;
    }

    public static void main(String[] stringArray) {
        int[] nArray = new int[]{5, 15, 25, 35, 45, 55, 135, 255};
        NVO_BASELAND_INDIVIDUAL_FACTOR_STD nVO_BASELAND_INDIVIDUAL_FACTOR_STD = new NVO_BASELAND_INDIVIDUAL_FACTOR_STD();
        nVO_BASELAND_INDIVIDUAL_FACTOR_STD.setLevel(1);
        nVO_BASELAND_INDIVIDUAL_FACTOR_STD.setA_symbol("<=");
        nVO_BASELAND_INDIVIDUAL_FACTOR_STD.setA_digital(10.0);
        nVO_BASELAND_INDIVIDUAL_FACTOR_STD.setAb_logic("\u6216");
        nVO_BASELAND_INDIVIDUAL_FACTOR_STD.setB_symbol(">=");
        nVO_BASELAND_INDIVIDUAL_FACTOR_STD.setB_digital(30.0);
        nVO_BASELAND_INDIVIDUAL_FACTOR_STD.setBc_logic("\u4e14");
        nVO_BASELAND_INDIVIDUAL_FACTOR_STD.setC_symbol("<=");
        nVO_BASELAND_INDIVIDUAL_FACTOR_STD.setC_digital(40.0);
        nVO_BASELAND_INDIVIDUAL_FACTOR_STD.setCd_logic("\u6216");
        nVO_BASELAND_INDIVIDUAL_FACTOR_STD.setD_symbol(">=");
        nVO_BASELAND_INDIVIDUAL_FACTOR_STD.setD_digital(140.0);
        BaseLandFactorStdTool baseLandFactorStdTool = new BaseLandFactorStdTool();
        for (int n : nArray) {
            try {
                System.out.println(n + " => " + baseLandFactorStdTool.matchLevelRange(nVO_BASELAND_INDIVIDUAL_FACTOR_STD, n));
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}

