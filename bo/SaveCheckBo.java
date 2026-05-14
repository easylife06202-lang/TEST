/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.bo;

import com.wfusion.baseland.BaseLandBean;
import com.wfusion.dataaccess.vo.DbElement;
import com.wfusion.dataaccess.vo.VoBase;
import com.wfusion.fx.util.JavaFXUtil;
import com.wfusion.util.StringProcess;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import moiland.baseland.print.BaseLandPrintRemark;

public class SaveCheckBo {
    private BaseLandBean baseLandBean = new BaseLandBean();
    public static Map<String, String[]> columnSet = new TreeMap<String, String[]>();
    private String br = "\n";
    private static String saveCheck = "baseland_saveCheck";
    private static String tab1 = "\u57fa\u6e96\u5730\u4f30\u50f9\u5831\u544a\u8868";
    private static String tab2 = "\u6bd4\u8f03\u4f30\u50f9\u8868";
    private static String tab2_0 = "\u6bd4\u8f03\u4f30\u50f9\u8868\u52d8\u4f30\u6a19\u7684";
    private static String tab2_1 = "\u6bd4\u8f03\u4f30\u50f9\u8868\u6bd4\u8f03\u6a19\u76841";
    private static String tab2_2 = "\u6bd4\u8f03\u4f30\u50f9\u8868\u6bd4\u8f03\u6a19\u76842";
    private static String tab2_3 = "\u6bd4\u8f03\u4f30\u50f9\u8868\u6bd4\u8f03\u6a19\u76843";
    private static String tab3 = "\u6210\u672c\u6cd5(1)";
    private static String tab4 = "\u6210\u672c\u6cd5(2)";
    private static String tab5 = "\u6210\u672c\u6cd5(3)";
    private static String tab6 = "\u6210\u672c\u6cd5(\u6536\u76ca)";
    private static String tab7 = "\u6536\u76ca\u8abf\u67e5\u4f30\u50f9\u8868";
    private static String tab7_1 = "\u6536\u76ca\u8abf\u67e5\u4f30\u50f9\u8868\u6708\u79df\u91d11";
    private static String tab7_2 = "\u6536\u76ca\u8abf\u67e5\u4f30\u50f9\u8868\u6708\u79df\u91d12";
    private static String tab7_3 = "\u6536\u76ca\u8abf\u67e5\u4f30\u50f9\u8868\u6708\u79df\u91d13";
    private static String tab8 = "\u571f\u958b\u6cd5\u8abf\u67e5\u4f30\u50f9\u8868";
    private static String tab8_0 = "\u571f\u958b\u6cd5\u8abf\u67e5\u4f30\u50f9\u8868\u9644\u8868";

    public SaveCheckBo(BaseLandBean baseLandBean) {
        this.baseLandBean = baseLandBean;
    }

    public String saveCheckColumn() {
        StringBuffer stringBuffer = new StringBuffer();
        if (this.baseLandBean.voMain != null) {
            this.checkByTable(stringBuffer, this.baseLandBean.voMain, tab1);
            this.checkRemark(stringBuffer, this.baseLandBean.voMain, tab1, "NOTES", "1");
        }
        if (this.baseLandBean.voAppRaMain != null) {
            this.checkByTable(stringBuffer, this.baseLandBean.voAppRaMain, tab2);
            this.checkRemark(stringBuffer, this.baseLandBean.voAppRaMain, tab2, "NOTES", "2");
        }
        if (this.baseLandBean.voAppRaMain != null) {
            this.checkByTable(stringBuffer, this.baseLandBean.voAppRaA3Vo0, tab2_0);
        }
        if (this.baseLandBean.voAppRaA3Vo0 != null) {
            this.checkByTable(stringBuffer, this.baseLandBean.voAppRaA3Vo1, tab2_1);
        }
        if (this.baseLandBean.voAppRaA3Vo0 != null) {
            this.checkByTable(stringBuffer, this.baseLandBean.voAppRaA3Vo2, tab2_2);
        }
        if (this.baseLandBean.voAppRaA3Vo0 != null) {
            this.checkByTable(stringBuffer, this.baseLandBean.voAppRaA3Vo3, tab2_3);
        }
        if (this.baseLandBean.voSell_1 != null) {
            this.checkByTable(stringBuffer, this.baseLandBean.voSell_1, tab3);
            this.checkRemark(stringBuffer, this.baseLandBean.voSell_1, tab3, "CS56", "3");
        }
        if (this.baseLandBean.voSell_2 != null) {
            this.checkByTable(stringBuffer, this.baseLandBean.voSell_2, tab4);
            this.checkRemark(stringBuffer, this.baseLandBean.voSell_2, tab4, "CS56", "4");
        }
        if (this.baseLandBean.voSell_3 != null) {
            this.checkByTable(stringBuffer, this.baseLandBean.voSell_3, tab5);
            this.checkRemark(stringBuffer, this.baseLandBean.voSell_3, tab5, "CS56", "5");
        }
        if (this.baseLandBean.voRentExt != null) {
            this.checkByTable(stringBuffer, this.baseLandBean.voRentExt, tab6);
            this.checkRemark(stringBuffer, this.baseLandBean.voRentExt, tab6, "CRE58", "6");
        }
        if (this.baseLandBean.voRent != null) {
            this.checkByTable(stringBuffer, this.baseLandBean.voRent, tab7);
            this.checkRemark(stringBuffer, this.baseLandBean.voRent, tab7, "CR44", "7");
        }
        if (this.baseLandBean.voRentMonth1 != null) {
            this.checkByTable(stringBuffer, this.baseLandBean.voRentMonth1, tab7_1);
        }
        if (this.baseLandBean.voRentMonth2 != null) {
            this.checkByTable(stringBuffer, this.baseLandBean.voRentMonth2, tab7_2);
        }
        if (this.baseLandBean.voRentMonth3 != null) {
            this.checkByTable(stringBuffer, this.baseLandBean.voRentMonth3, tab7_3);
        }
        if (this.baseLandBean.voDevelop != null) {
            this.checkByTable(stringBuffer, this.baseLandBean.voDevelop, tab8);
            this.checkRemark(stringBuffer, this.baseLandBean.voDevelop, tab8, "NOTES", "8");
        }
        if (this.baseLandBean.voDevelopExt != null) {
            this.checkByTable(stringBuffer, this.baseLandBean.voDevelopExt, tab8_0);
        }
        return stringBuffer.toString();
    }

    private void checkByTable(StringBuffer stringBuffer, VoBase voBase, String string) {
        String string2 = voBase.getTableName().toUpperCase() + "_";
        int n = voBase.getFieldCount();
        for (int i = 0; i < n; ++i) {
            DbElement dbElement = voBase.getElementAt(i);
            if (dbElement == null) continue;
            String[] stringArray = new String[]{};
            stringArray = columnSet.get(string2 + dbElement.getName().toUpperCase());
            if (stringArray != null && stringArray.length == 4) {
                this.checkByType(stringBuffer, dbElement, stringArray, string);
                continue;
            }
            System.out.println("\u67e5\u7121\u6b04\u4f4d: " + string2 + dbElement.getName());
        }
    }

    private void checkRemark(StringBuffer stringBuffer, VoBase voBase, String string, String string2, String string3) {
        boolean bl = false;
        try {
            String string4;
            if (voBase.getElementAt(string2) != null && !StringProcess.isEmpty(string4 = voBase.getElementAt(string2).toString())) {
                int n;
                BaseLandPrintRemark baseLandPrintRemark = new BaseLandPrintRemark("", "");
                Integer[] integerArray = baseLandPrintRemark.getMaxLen(string3);
                if (integerArray[0] > 0) {
                    n = BaseLandPrintRemark.countRowSize(string4, integerArray[0], this.br);
                    System.out.println(string3 + ":rowSize=" + n + ",maxlen[1]=" + integerArray[1]);
                    bl = n > integerArray[1];
                } else if (integerArray[0] == 0) {
                    bl = true;
                }
                if (bl) {
                    n = BaseLandPrintRemark.countRowSize(string4, 60, this.br);
                    if (n > 30) {
                        stringBuffer.append(string + "\u5099\u8a3b\u6b04\u4f4d\u5217\u5370\u8d85\u904e30\u884c\uff0c\u8acb\u4fee\u6b63!!");
                    }
                }
            }
        }
        catch (Exception exception) {
            JavaFXUtil.showErrorMessageBox("\u5132\u5b58\u5931\u6557\uff0c\u8acb\u627e\u7a0b\u5f0f\u7ba1\u7406\u54e1", exception.toString());
            stringBuffer.append(string + "\u5099\u8a3b\u6aa2\u6838\u5931\u6557");
        }
    }

    private void checkByTableUnicode(StringBuffer stringBuffer, VoBase voBase, String string) {
        String string2 = voBase.getTableName().toUpperCase() + "_";
        int n = voBase.getFieldCount();
        for (int i = 0; i < n; ++i) {
            DbElement dbElement = voBase.getElementAt(i);
            if (dbElement == null) continue;
            String[] stringArray = new String[]{};
            stringArray = columnSet.get(string2 + dbElement.getName().toUpperCase());
            if (stringArray != null && stringArray.length == 4) {
                this.checkUnicode(stringBuffer, dbElement, stringArray, string);
                continue;
            }
            System.out.println("\u67e5\u7121\u6b04\u4f4d: " + string2 + dbElement.getName());
        }
    }

    private void checkUnicode(StringBuffer stringBuffer, DbElement dbElement, String[] stringArray, String string) {
        boolean bl = true;
        String string2 = dbElement.toString();
        if (!StringProcess.isEmpty(string2) && "NVARCHAR".equals(stringArray[1].toString())) {
            try {
                for (int i = 0; i < string2.length(); ++i) {
                    String string3 = string2.charAt(i) + "";
                    byte[] byArray = string3.getBytes("MS950");
                    if (string2.charAt(i) == '?' || byArray.length != 1 || byArray[0] != 63) continue;
                    bl = false;
                    break;
                }
                if (!bl) {
                    stringBuffer.append(string + "-" + stringArray[0].toString() + "\r\n");
                }
            }
            catch (UnsupportedEncodingException unsupportedEncodingException) {
                unsupportedEncodingException.printStackTrace();
            }
        }
    }

    private void checkByType(StringBuffer stringBuffer, DbElement dbElement, String[] stringArray, String string) {
        String string2 = dbElement.toString();
        if (!StringProcess.isEmpty(string2)) {
            if ("NVARCHAR".equals(stringArray[1].toString())) {
                if (Integer.parseInt(stringArray[2].toString()) < string2.length()) {
                    stringBuffer.append(string + "-" + stringArray[0].toString() + "\u6b04\u4f4d\u8f38\u5165\u9577\u5ea6\u4e0d\u53ef\u8d85\u904e" + stringArray[2].toString() + "\u5b57\r\n");
                }
            } else if ("NUMERIC".equals(stringArray[1].toString())) {
                try {
                    boolean bl = true;
                    boolean bl2 = true;
                    int n = Integer.parseInt(stringArray[2].toString());
                    int n2 = Integer.parseInt(stringArray[3].toString());
                    if (n2 > 0) {
                        if (n - n2 < string2.split("\\.")[0].length()) {
                            bl = false;
                        }
                        if (n2 < string2.split("\\.")[1].length()) {
                            bl2 = false;
                        }
                        if (!bl) {
                            if (!bl2) {
                                stringBuffer.append(string + "-" + stringArray[0].toString() + "\u6b04\u4f4d\u8f38\u5165\u6574\u6578\u4e0d\u53ef\u8d85\u904e" + Integer.toString(n - n2) + "\u4f4d\uff0c\u4e14\u5c0f\u6578\u9ede\u4e0d\u53ef\u8d85\u904e" + stringArray[3].toString() + "\u4f4d\r\n");
                            } else {
                                stringBuffer.append(string + "-" + stringArray[0].toString() + "\u6b04\u4f4d\u8f38\u5165\u6574\u6578\u4e0d\u53ef\u8d85\u904e" + Integer.toString(n - n2) + "\u4f4d\r\n");
                            }
                        } else if (!bl2) {
                            stringBuffer.append(string + "-" + stringArray[0].toString() + "\u6b04\u4f4d\u8f38\u5165\u5c0f\u6578\u9ede\u4e0d\u53ef\u8d85\u904e" + stringArray[3].toString() + "\u4f4d\r\n");
                        }
                    } else if (new BigDecimal(string2).compareTo(new BigDecimal(new BigDecimal(string2).longValue())) != 0) {
                        stringBuffer.append(string + "-" + stringArray[0].toString() + "\u6b04\u4f4d\u50c5\u80fd\u8f38\u5165\u6b63\u6574\u6578\r\n");
                    } else if (Integer.parseInt(stringArray[2].toString()) < string2.length()) {
                        stringBuffer.append(string + "-" + stringArray[0].toString() + "\u6b04\u4f4d\u8f38\u5165\u9577\u5ea6\u4e0d\u53ef\u8d85\u904e" + stringArray[2].toString() + "\u4f4d\r\n");
                    }
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    public static Map<String, String[]> getColumnSetting(String string) {
        TreeMap<String, String[]> treeMap = new TreeMap<String, String[]>();
        ResourceBundle resourceBundle = ResourceBundle.getBundle(string);
        Enumeration<String> enumeration = resourceBundle.getKeys();
        while (enumeration.hasMoreElements()) {
            String string2;
            String string3 = String.valueOf(enumeration.nextElement());
            try {
                string2 = new String(resourceBundle.getString(string3).getBytes("ISO-8859-1"), "UTF-8");
            }
            catch (Exception exception) {
                System.out.println("\u4e2d\u6587\u8f49\u78bc\u932f\u8aa4: " + string3);
                string2 = String.valueOf(resourceBundle.getString(string3));
            }
            String[] stringArray = new String[1];
            if (string2.indexOf(",") > -1) {
                stringArray = string2.split(",");
            } else {
                stringArray[0] = string2;
            }
            treeMap.put(string3, stringArray);
        }
        return treeMap;
    }

    public String unicodeCheckColumn() {
        StringBuffer stringBuffer = new StringBuffer();
        if (this.baseLandBean.voMain != null) {
            this.checkByTableUnicode(stringBuffer, this.baseLandBean.voMain, tab1);
        }
        if (this.baseLandBean.voAppRaMain != null) {
            this.checkByTableUnicode(stringBuffer, this.baseLandBean.voAppRaMain, tab2);
        }
        if (this.baseLandBean.voAppRaMain != null) {
            this.checkByTableUnicode(stringBuffer, this.baseLandBean.voAppRaA3Vo0, tab2_0);
        }
        if (this.baseLandBean.voAppRaA3Vo0 != null) {
            this.checkByTableUnicode(stringBuffer, this.baseLandBean.voAppRaA3Vo1, tab2_1);
        }
        if (this.baseLandBean.voAppRaA3Vo0 != null) {
            this.checkByTableUnicode(stringBuffer, this.baseLandBean.voAppRaA3Vo2, tab2_2);
        }
        if (this.baseLandBean.voAppRaA3Vo0 != null) {
            this.checkByTableUnicode(stringBuffer, this.baseLandBean.voAppRaA3Vo3, tab2_3);
        }
        if (this.baseLandBean.voSell_1 != null) {
            this.checkByTableUnicode(stringBuffer, this.baseLandBean.voSell_1, tab3);
        }
        if (this.baseLandBean.voSell_2 != null) {
            this.checkByTableUnicode(stringBuffer, this.baseLandBean.voSell_2, tab4);
        }
        if (this.baseLandBean.voSell_3 != null) {
            this.checkByTableUnicode(stringBuffer, this.baseLandBean.voSell_3, tab5);
        }
        if (this.baseLandBean.voRentExt != null) {
            this.checkByTableUnicode(stringBuffer, this.baseLandBean.voRentExt, tab6);
        }
        if (this.baseLandBean.voRent != null) {
            this.checkByTableUnicode(stringBuffer, this.baseLandBean.voRent, tab7);
        }
        if (this.baseLandBean.voRentMonth1 != null) {
            this.checkByTableUnicode(stringBuffer, this.baseLandBean.voRentMonth1, tab7_1);
        }
        if (this.baseLandBean.voRentMonth2 != null) {
            this.checkByTableUnicode(stringBuffer, this.baseLandBean.voRentMonth2, tab7_2);
        }
        if (this.baseLandBean.voRentMonth3 != null) {
            this.checkByTableUnicode(stringBuffer, this.baseLandBean.voRentMonth3, tab7_3);
        }
        if (this.baseLandBean.voDevelop != null) {
            this.checkByTableUnicode(stringBuffer, this.baseLandBean.voDevelop, tab8);
        }
        if (this.baseLandBean.voDevelopExt != null) {
            this.checkByTableUnicode(stringBuffer, this.baseLandBean.voDevelopExt, tab8_0);
        }
        return stringBuffer.toString();
    }

    static {
        columnSet = SaveCheckBo.getColumnSetting(saveCheck);
    }
}

