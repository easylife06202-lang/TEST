/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.sf.jasperreports.engine.JRDataSource
 *  net.sf.jasperreports.engine.JRException
 *  net.sf.jasperreports.engine.JasperRunManager
 *  net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
 *  org.apache.commons.io.FilenameUtils
 */
package moiland.baseland.print;

import com.wfusion.util.BigDecimalUtil;
import com.wfusion.util.JasperUtils;
import com.wfusion.util.StringProcess;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import moiland.baseland.print.bean.BaseLandPrintRemarkBean;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.io.FilenameUtils;

public class BaseLandPrintRemark<NVO> {
    private static final String PDF_EXT = ".pdf";
    private static final String JASPER_REMARK = "baselandreport_remark.jasper";
    private String jrxmlFolderPath = "";
    private String baseno = "";
    private String br = "\n";
    private static HashMap<String, String> titleMap = new HashMap();
    private static HashMap<String, Integer[]> maxLenMap = new HashMap();

    public String getReportTitle(String string) {
        return StringProcess.getField(titleMap, string);
    }

    public Integer[] getMaxLen(String string) {
        Integer[] integerArray = null;
        if (maxLenMap.containsKey(string)) {
            integerArray = maxLenMap.get(string);
        }
        return integerArray;
    }

    public BaseLandPrintRemark(String string, String string2) {
        this.jrxmlFolderPath = FilenameUtils.normalizeNoEndSeparator((String)string2) + "/";
        this.baseno = string;
    }

    public boolean printReportRemark(String string, Object object, String string2, String string3, boolean bl) {
        String string4 = this.jrxmlFolderPath + JASPER_REMARK;
        Method method = null;
        Method method2 = null;
        String string5 = "";
        HashMap hashMap = new HashMap();
        ArrayList<BaseLandPrintRemarkBean> arrayList = new ArrayList<BaseLandPrintRemarkBean>();
        BaseLandPrintRemarkBean baseLandPrintRemarkBean = new BaseLandPrintRemarkBean();
        try {
            String string6 = StringProcess.getFustionString(string2, false);
            method = object.getClass().getMethod(string6, new Class[0]);
            string5 = String.valueOf(method.invoke(object, new Object[0]));
            Integer[] integerArray = this.getMaxLen(string3);
            if (integerArray[0] > 0) {
                int n = BaseLandPrintRemark.countRowSize(string5, integerArray[0], this.br);
                System.out.println(string3 + ":rowSize=" + n + ",maxlen[1]=" + integerArray[1]);
                bl = n > integerArray[1];
            } else if (integerArray[0] == 0 && bl) {
                bl = true;
            }
            if (string5.length() == 0) {
                bl = false;
            }
            if (bl) {
                String string7 = StringProcess.setFustionString(string2, false);
                method2 = object.getClass().getMethod(string7, String.class);
                method2.invoke(object, "\u8a73\u898b\u9644\u8868");
                baseLandPrintRemarkBean.setRemark(string5);
                baseLandPrintRemarkBean.setPageno(string3);
                baseLandPrintRemarkBean.setBaseno(this.baseno);
                baseLandPrintRemarkBean.setTitle(this.getReportTitle(string3));
                arrayList.add(baseLandPrintRemarkBean);
                if (arrayList.size() > 0) {
                    JasperRunManager.runReportToPdfFile((String)string4, (String)string, hashMap, (JRDataSource)new JRBeanCollectionDataSource(arrayList));
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return bl;
    }

    public void printReport(BaseLandPrintRemark baseLandPrintRemark, String string, String string2, NVO NVO, String string3, String string4, boolean bl, Map<String, Object> map) {
        string2 = string2.replaceAll(PDF_EXT, "");
        JasperUtils jasperUtils = new JasperUtils();
        String string5 = string2 + "_rmk_" + string4 + PDF_EXT;
        File file = new File(string5);
        boolean bl2 = baseLandPrintRemark.printReportRemark(string5, NVO, string3, string4, bl);
        String string6 = string2 + "_" + string4 + PDF_EXT;
        File file2 = new File(string6);
        ArrayList<NVO> arrayList = new ArrayList<NVO>();
        arrayList.add(NVO);
        try {
            JasperRunManager.runReportToPdfFile((String)string, (String)string6, map, (JRDataSource)new JRBeanCollectionDataSource(arrayList));
            jasperUtils.add(file2);
            if (bl2 && file.exists()) {
                jasperUtils.add(file);
            }
            jasperUtils.printMergerPdfFile(string2 + PDF_EXT);
        }
        catch (JRException jRException) {
            jRException.printStackTrace();
        }
    }

    public static int countRowSize(String string, int n, String string2) throws Exception {
        int n2 = 0;
        if (string != null && string2 != null) {
            String[] stringArray;
            for (String string3 : stringArray = StringProcess.split(string, string2)) {
                int n3 = string3.getBytes("ISO8859_1").length;
                if (n3 > n) {
                    int n4 = (int)BigDecimalUtil.ceil(BigDecimalUtil.div(n3, n), 0);
                    System.out.println("fontLen=" + n3 + ",rowFontSize=" + n + ",rowCount=" + (n2 += n4));
                    continue;
                }
                ++n2;
            }
        } else {
            return 0;
        }
        return n2;
    }

    static {
        if (titleMap.size() == 0) {
            titleMap.put("1", "\u5730\u50f9\u57fa\u6e96\u5730\u4f30\u50f9\u5831\u544a\u8868");
            titleMap.put("2", "\u6bd4\u8f03\u6cd5\u8abf\u67e5\u4f30\u50f9\u8868");
            titleMap.put("3", "\u6210\u672c\u6cd5\u53ca\u623f\u5730\u5206\u96e2\u4f30\u50f9\u8868");
            titleMap.put("4", "\u6210\u672c\u6cd5\u53ca\u623f\u5730\u5206\u96e2\u4f30\u50f9\u8868");
            titleMap.put("5", "\u6210\u672c\u6cd5\u53ca\u623f\u5730\u5206\u96e2\u4f30\u50f9\u8868");
            titleMap.put("6", "\u6210\u672c\u6cd5\u8abf\u67e5\u4f30\u50f9\u8868");
            titleMap.put("7", "\u6536\u76ca\u6cd5\u8abf\u67e5\u4f30\u50f9\u8868");
            titleMap.put("8", "\u571f\u5730\u958b\u767c\u5206\u6790\u6cd5\u8abf\u67e5\u4f30\u50f9\u8868");
        }
        if (maxLenMap.size() == 0) {
            maxLenMap.put("1", new Integer[]{25, 3});
            maxLenMap.put("2", new Integer[]{0, 0});
            maxLenMap.put("3", new Integer[]{70, 6});
            maxLenMap.put("4", new Integer[]{70, 6});
            maxLenMap.put("5", new Integer[]{70, 6});
            maxLenMap.put("6", new Integer[]{68, 5});
            maxLenMap.put("7", new Integer[]{68, 3});
            maxLenMap.put("8", new Integer[]{35, 1});
        }
    }
}

