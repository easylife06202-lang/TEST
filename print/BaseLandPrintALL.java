/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonParser
 *  net.sf.jasperreports.engine.JRDataSource
 *  net.sf.jasperreports.engine.JasperRunManager
 *  net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
 *  org.apache.commons.io.FileUtils
 *  org.apache.commons.io.FilenameUtils
 */
package moiland.baseland.print;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.wfusion.baseland.estimate.EstimateReport7Model;
import com.wfusion.dataaccess.vo.DbElement;
import com.wfusion.util.DateTime;
import com.wfusion.util.JasperUtils;
import com.wfusion.util.SqlBuilder;
import com.wfusion.util.StringProcess;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.ImageIcon;
import moiland.baseland.action.bean.BaseLandDevelopParamBean;
import moiland.baseland.bo.AutoCalBaseLandDevelopBo;
import moiland.baseland.bo.AutoCalBaseLandRent;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_DEVELOP;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_DEVELOP_EXT;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_IMAGES;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_MAIN;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_RENT;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_RENT_EXT;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_RENT_MONTH;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_SELL;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_APPRAISALA3_SCORE;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_DEVELOP;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_DEVELOP_EXT;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_IMAGES;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_MAIN;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT_EXT;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT_MONTH;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_SELL;
import moiland.baseland.factor.bean.FactorLevelBean;
import moiland.baseland.factor.em.EnumFactorVersion;
import moiland.baseland.print.BaseLandPrintRemark;
import moiland.baseland.print.BaseLandReport2Bean;
import moiland.baseland.print.BaseLandReportImageBean;
import moiland.baseland.print.BaseLandReportImageHelper;
import moiland.baseland.print.DevelopReportBean;
import moiland.baseland.print.bean.BaseLandRentMonthBean;
import moiland.baseland.util.BaseLandCode;
import moiland.baseland.util.BaseLandCompareFactorListHelper;
import moiland.baseland.util.BaseLandFactorVersionHelper;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class BaseLandPrintALL {
    private static final String PDF_EXT = ".pdf";
    private static final String JASPER_REPORT1 = "baselandreport_1.jasper";
    private static final String JASPER_REPORT2_A3 = "baselandreport_2_A3.jasper";
    private static final String JASPER_REPORT2_A3BD = "baselandreport_2_A3BD.jasper";
    private static final String JASPER_REPORT2_A3BF = "baselandreport_2_A3BF.jasper";
    private static final String JASPER_REPORT5 = "baselandreport_345.jasper";
    private static final String JASPER_REPORT6 = "baselandreport_6.jasper";
    private static final String JASPER_REPORT7 = "baselandreport_7.jasper";
    private static final String JASPER_REPORT7_MONTH = "baselandreport_7_1.jasper";
    private static final String JASPER_REPORT8 = "baselandreport_8.jasper";
    private static final String JASPER_REPORT8_EXT = "baselandreport_8_ext.jasper";
    private static final String JASPER_PICTURE = "baselandreport_picture.jasper";
    private String jrxmlFolderPath = "";
    private String sessionId = "123456";
    private String baseno = "";
    private String year = "";
    private String version = "";
    private final String splitChar = "&#32;&#32;";
    private boolean rmk_page = false;
    private boolean printRentMonth = false;

    public boolean isPrintRentMonth() {
        return this.printRentMonth;
    }

    public void setPrintRentMonth(boolean bl) {
        this.printRentMonth = bl;
    }

    public boolean isRmk_page() {
        return this.rmk_page;
    }

    public void setRmk_page(boolean bl) {
        this.rmk_page = bl;
    }

    public BaseLandPrintALL(String string, String string2, String string3, String string4) {
        this.jrxmlFolderPath = FilenameUtils.normalizeNoEndSeparator((String)string4) + "/";
        this.sessionId = string;
        this.baseno = string3;
        this.year = string2;
    }

    public byte[] printAll(Connection connection, Connection connection2) throws Exception {
        JasperUtils jasperUtils = new JasperUtils();
        byte[] byArray = null;
        NVO_BASELAND_MAIN nVO_BASELAND_MAIN = new NVO_BASELAND_MAIN();
        File file = new File(System.getProperty("java.io.tmpdir"), this.sessionId + "_" + DateTime.getCurrentTime14());
        if (file.exists()) {
            FileUtils.forceDelete((File)file);
        }
        FileUtils.forceMkdir((File)file);
        File file2 = new File(file, this.baseno + PDF_EXT);
        File file3 = new File(file, this.baseno + "_1" + PDF_EXT);
        nVO_BASELAND_MAIN = this.printReport1(file3.getCanonicalPath(), connection);
        if (file3.exists()) {
            jasperUtils.add(file3);
            if (!StringProcess.isEmpty(nVO_BASELAND_MAIN.getAttachs())) {
                this.printAttach(jasperUtils, nVO_BASELAND_MAIN.getAttachs().toCharArray(), file, connection, connection2);
            }
        }
        jasperUtils.printMergerPdfFile(file2.getCanonicalPath());
        byArray = FileUtils.readFileToByteArray((File)file2);
        FileUtils.forceDelete((File)file);
        return byArray;
    }

    private NVO_BASELAND_MAIN printReport1(String string, Connection connection) throws Exception {
        Object object;
        String string2 = this.jrxmlFolderPath + JASPER_REPORT1;
        String string3 = "Select * From BASELAND_MAIN where year=@@ and baseno=@@";
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        NVO_BASELAND_MAIN nVO_BASELAND_MAIN = new NVO_BASELAND_MAIN();
        SqlBuilder sqlBuilder = new SqlBuilder(string3);
        sqlBuilder.setString(0, this.year);
        sqlBuilder.setString(1, this.baseno);
        nVO_BASELAND_MAIN = new NDAO_BASELAND_MAIN().findByPk(this.baseno, this.year, connection);
        if (nVO_BASELAND_MAIN == null) {
            throw new Exception("\u67e5\u7121" + this.year + "\u5e74\u57fa\u6e96\u5730[" + this.baseno + "]\u8cc7\u6599");
        }
        this.version = nVO_BASELAND_MAIN.getVersion();
        nVO_BASELAND_MAIN.setFill_date(StringProcess.isEmpty(nVO_BASELAND_MAIN.getFill_date()) ? DateTime.getDateText(DateTime.getTaiwanToday()) : DateTime.getDateText(nVO_BASELAND_MAIN.getFill_date()));
        nVO_BASELAND_MAIN.setNotes(this.fillerNote(nVO_BASELAND_MAIN.getNotes(), nVO_BASELAND_MAIN.getStreet(), nVO_BASELAND_MAIN.getStreet_rel(), nVO_BASELAND_MAIN.getRoadwidth()));
        String string4 = "select * from baseland_images where year=@@ and baseno=@@ and photo_type ='SKT' ";
        SqlBuilder sqlBuilder2 = new SqlBuilder(string4);
        sqlBuilder2.setString(0, this.year);
        sqlBuilder2.setString(1, this.baseno);
        ArrayList arrayList = new NDAO_BASELAND_IMAGES().findBySql(sqlBuilder2.getSql(), connection);
        if (arrayList.size() > 0 && (object = (Object)((NVO_BASELAND_IMAGES)arrayList.get(0)).getPhoto()) != null && ((Object)object).length > 0) {
            try {
                hashMap.put("SKT_IMAGE", new ImageIcon((byte[])object).getImage());
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        object = nVO_BASELAND_MAIN.getAttachs();
        String string5 = "";
        String string6 = "";
        String string7 = "";
        String string8 = "";
        String string9 = "";
        String string10 = "";
        ArrayList<Object> arrayList2 = new ArrayList();
        arrayList2 = this.getAttachs((String)object);
        if (!arrayList2.isEmpty()) {
            string5 = (String)arrayList2.get(0);
            string6 = (String)arrayList2.get(1);
            string7 = (String)arrayList2.get(2);
            string8 = (String)arrayList2.get(3);
            string9 = (String)arrayList2.get(4);
            string10 = (String)arrayList2.get(5);
        }
        nVO_BASELAND_MAIN.setAttach1(string5);
        nVO_BASELAND_MAIN.setAttach2(string6);
        nVO_BASELAND_MAIN.setAttach3(string7);
        nVO_BASELAND_MAIN.setAttach4(string8);
        nVO_BASELAND_MAIN.setAttach5(string9);
        nVO_BASELAND_MAIN.setAttach6(string10);
        if (StringProcess.isEmpty(nVO_BASELAND_MAIN.getPrice_date())) {
            nVO_BASELAND_MAIN.setPrice_date(this.year + "0331");
        }
        BaseLandPrintRemark<NVO_BASELAND_MAIN> baseLandPrintRemark = new BaseLandPrintRemark<NVO_BASELAND_MAIN>(nVO_BASELAND_MAIN.getBaseno(), this.jrxmlFolderPath);
        baseLandPrintRemark.printReport(baseLandPrintRemark, string2, string, nVO_BASELAND_MAIN, "notes", "1", this.rmk_page, hashMap);
        return nVO_BASELAND_MAIN;
    }

    private String fillerNote(String string, String string2, String string3, String string4) {
        String string5 = "";
        String string6 = "";
        int n = string.indexOf("&#32;&#32;");
        if (n > -1) {
            string5 = string.substring(0, n);
            string5 = string5.replaceAll("&(?!amp;)", "");
            String string7 = this.getRoadData(string2, string3, string5, string4);
            string6 = string.substring(n, string.length());
            string6 = string6.replaceAll("&#32;&#32;", "");
            string = string6 + "\r\n" + string7;
        } else if (!StringProcess.isEmpty(string)) {
            JsonElement jsonElement = new JsonParser().parse(string);
            if (jsonElement.isJsonArray()) {
                String string8;
                string5 = string;
                string5 = string5.replaceAll("&(?!amp;)", "");
                string = string8 = this.getRoadData(string2, string3, string5, string4);
            } else {
                string = string.replaceAll("&#32;&#32;", "");
            }
        }
        return string;
    }

    private String getRoadData(String string, String string2, String string3, String string4) {
        if (!StringProcess.isEmpty(string3)) {
            String string5 = "";
            Gson gson = new Gson();
            HashMap[] hashMapArray = null;
            for (HashMap hashMap : hashMapArray = (HashMap[])gson.fromJson(string3, HashMap[].class)) {
                if (StringProcess.isEmpty((String)hashMap.get("ROAD"))) continue;
                string5 = string5 + "\u3001" + (String)hashMap.get("ROAD") + "(" + (String)hashMap.get("WIDTH") + "m)";
            }
            return "\u672c\u57fa\u6e96\u5730\u70ba" + string2 + "\uff0c\u6240\u81e8\u9053\u8def\u70ba\uff1a" + string + "(" + string4 + "m)" + string5;
        }
        return "";
    }

    private void printAttach(JasperUtils jasperUtils, char[] cArray, File file, Connection connection, Connection connection2) throws IOException {
        File file2;
        if (cArray[0] == '1') {
            file2 = new File(file, this.baseno + "_2" + PDF_EXT);
            this.printReport2(file2.getCanonicalPath(), connection, connection2);
            if (file2.exists()) {
                jasperUtils.add(file2);
            }
        }
        if (cArray[1] == '1') {
            file2 = new File(file, this.baseno + "_345" + PDF_EXT);
            this.printReport345(file2.getCanonicalPath(), connection);
            if (file2.exists()) {
                jasperUtils.add(file2);
            }
        }
        if (cArray[3] == '1') {
            file2 = new File(file, this.baseno + "_6" + PDF_EXT);
            this.printReport6(file2.getCanonicalPath(), connection);
            if (file2.exists()) {
                jasperUtils.add(file2);
            }
        }
        if (cArray[2] == '1') {
            file2 = new File(file, this.baseno + "_7" + PDF_EXT);
            this.printReport7(file2.getCanonicalPath(), connection);
            if (file2.exists()) {
                jasperUtils.add(file2);
            }
        }
        if (cArray[4] == '1') {
            file2 = new File(file, this.baseno + "_8" + PDF_EXT);
            NVO_BASELAND_DEVELOP nVO_BASELAND_DEVELOP = this.printReport8(file2.getCanonicalPath(), connection);
            if (file2.exists()) {
                jasperUtils.add(file2);
            }
            if (nVO_BASELAND_DEVELOP != null && "1".equals(nVO_BASELAND_DEVELOP.getIs_merge())) {
                File file3 = new File(file, this.baseno + "_8e" + PDF_EXT);
                System.out.println(file3.getCanonicalPath());
                this.printReport8Ext(file3.getCanonicalPath(), connection);
                if (file3.exists()) {
                    jasperUtils.add(file3);
                }
            }
        }
        if (cArray[5] == '1') {
            file2 = new File(file, this.baseno + "_pic" + PDF_EXT);
            this.printReportPicture(file2.getCanonicalPath(), cArray, connection);
            if (file2.exists()) {
                jasperUtils.add(file2);
            }
        }
    }

    private void printReport2(String string, Connection connection, Connection connection2) {
        String string2 = this.jrxmlFolderPath + this.getMyReport2Jrxml();
        try {
            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("SUBREPORT_PATH", this.jrxmlFolderPath);
            BaseLandReport2Bean baseLandReport2Bean = new BaseLandReport2Bean(this.year, this.baseno, connection);
            if (baseLandReport2Bean != null && baseLandReport2Bean.getSources().size() == 3) {
                this.converCodeAndLevel(baseLandReport2Bean, connection2);
                ArrayList<BaseLandReport2Bean> arrayList = new ArrayList<BaseLandReport2Bean>();
                arrayList.add(baseLandReport2Bean);
                if (baseLandReport2Bean.getReport2().getFin_pricep() == 0) {
                    baseLandReport2Bean.getReport2().setFin_pricep((int)StringProcess.roundCd((double)baseLandReport2Bean.getReport2().getFin_price() * 3.3058));
                }
                if (arrayList.size() > 0) {
                    baseLandReport2Bean.setBaseno(this.baseno);
                    BaseLandPrintRemark<BaseLandReport2Bean> baseLandPrintRemark = new BaseLandPrintRemark<BaseLandReport2Bean>(baseLandReport2Bean.getBaseno(), this.jrxmlFolderPath);
                    baseLandPrintRemark.printReport(baseLandPrintRemark, string2, string, baseLandReport2Bean, "notes", "2", this.rmk_page, hashMap);
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private String getMyReport2Jrxml() {
        String string = this.baseno.substring(3, 5);
        EnumFactorVersion enumFactorVersion = BaseLandFactorVersionHelper.getFactorVersionByString(this.version, string);
        switch (enumFactorVersion) {
            case A3BD: {
                return JASPER_REPORT2_A3BD;
            }
            case A3BF: {
                return JASPER_REPORT2_A3BF;
            }
        }
        return JASPER_REPORT2_A3;
    }

    private void converCodeAndLevel(BaseLandReport2Bean baseLandReport2Bean, Connection connection) {
        try {
            NVO_BASELAND_MAIN nVO_BASELAND_MAIN = baseLandReport2Bean.getMain();
            BaseLandCompareFactorListHelper baseLandCompareFactorListHelper = new BaseLandCompareFactorListHelper(nVO_BASELAND_MAIN.getBaseno(), nVO_BASELAND_MAIN.getYear(), nVO_BASELAND_MAIN.getVersion(), nVO_BASELAND_MAIN.getUrban());
            Map<String, FactorLevelBean> map = baseLandCompareFactorListHelper.getRegionalItemLevelListById(connection);
            Map<String, FactorLevelBean> map2 = baseLandCompareFactorListHelper.getIndividualItemLevelListById(connection);
            NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE = baseLandReport2Bean.getTarget();
            if (nVO_BASELAND_APPRAISALA3_SCORE != null) {
                this.converCodeAndLevel(nVO_BASELAND_APPRAISALA3_SCORE, map, map2);
            } else {
                System.out.println("null");
            }
            for (NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE2 : baseLandReport2Bean.getSources()) {
                this.converCodeAndLevel(nVO_BASELAND_APPRAISALA3_SCORE2, map, map2);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void converCodeAndLevel(NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE, Map<String, FactorLevelBean> map, Map<String, FactorLevelBean> map2) {
        this.converLvToText(nVO_BASELAND_APPRAISALA3_SCORE, map);
        this.converLvToText(nVO_BASELAND_APPRAISALA3_SCORE, map2);
        nVO_BASELAND_APPRAISALA3_SCORE.setAs342_nm(BaseLandCode.decodeAs342(nVO_BASELAND_APPRAISALA3_SCORE.getAs342_nm()));
        nVO_BASELAND_APPRAISALA3_SCORE.setAs343_nm(BaseLandCode.decodeAs343(nVO_BASELAND_APPRAISALA3_SCORE.getAs343_nm()));
        nVO_BASELAND_APPRAISALA3_SCORE.setAs347_nm(BaseLandCode.decodeAs347(nVO_BASELAND_APPRAISALA3_SCORE.getAs347_nm()));
        nVO_BASELAND_APPRAISALA3_SCORE.setAs368_nm(BaseLandCode.decodeAs368(nVO_BASELAND_APPRAISALA3_SCORE.getAs368_nm()));
    }

    private void converLvToText(NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE, Map<String, FactorLevelBean> map) {
        for (String string : map.keySet()) {
            FactorLevelBean factorLevelBean = map.get(string);
            String string2 = factorLevelBean.getItemField().toLowerCase() + "_lv";
            DbElement dbElement = nVO_BASELAND_APPRAISALA3_SCORE.getElementAt(string2);
            if (dbElement == null) continue;
            dbElement.setValue(factorLevelBean.getLvText(dbElement.toString()));
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void printReport345(String string, Connection connection) {
        String string2 = this.jrxmlFolderPath + JASPER_REPORT5;
        string = string.replaceAll(PDF_EXT, "");
        JasperUtils jasperUtils = new JasperUtils();
        String string3 = "Select * from BASELAND_SELL Where year = '" + this.year + "' " + "and baseno ='" + this.baseno + "'" + "order by caseno";
        HashMap hashMap = new HashMap();
        try {
            ArrayList arrayList = new NDAO_BASELAND_SELL().findBySql(string3, connection);
            if (arrayList != null && arrayList.size() > 0) {
                TreeMap<String, Object> treeMap = new TreeMap<String, Object>();
                for (Object object : arrayList) {
                    int n = StringProcess.parserInt(((NVO_BASELAND_SELL)object).getCaseno());
                    treeMap.put(String.valueOf(n + 2), object);
                }
                for (Object object : treeMap.keySet()) {
                    NVO_BASELAND_SELL nVO_BASELAND_SELL = (NVO_BASELAND_SELL)treeMap.get(object);
                    String string4 = string + "_" + (String)object + PDF_EXT;
                    File file = new File(string4);
                    if (nVO_BASELAND_SELL.getCs05() != null && !nVO_BASELAND_SELL.getCs05().equals("")) {
                        nVO_BASELAND_SELL.setAa49(StringProcess.getLandShort(nVO_BASELAND_SELL.getAa49()));
                        nVO_BASELAND_SELL.setEd49(StringProcess.build2Short(nVO_BASELAND_SELL.getEd49()));
                        nVO_BASELAND_SELL.setCs05(BaseLandCode.decodeInstruCode(nVO_BASELAND_SELL.getCs05()));
                    }
                    String string5 = string + "_rmk_" + (String)object + PDF_EXT;
                    File file2 = new File(string5);
                    BaseLandPrintRemark baseLandPrintRemark = new BaseLandPrintRemark(nVO_BASELAND_SELL.getBaseno(), this.jrxmlFolderPath);
                    boolean bl = baseLandPrintRemark.printReportRemark(string5, nVO_BASELAND_SELL, "cs56", (String)object, this.rmk_page);
                    nVO_BASELAND_SELL.setLand_position(StringProcess.ascii2Unicode(nVO_BASELAND_SELL.getLand_position()));
                    nVO_BASELAND_SELL.setCs01(StringProcess.ascii2Unicode(nVO_BASELAND_SELL.getCs01()));
                    ArrayList<NVO_BASELAND_SELL> arrayList2 = new ArrayList<NVO_BASELAND_SELL>();
                    arrayList2.add(nVO_BASELAND_SELL);
                    JasperRunManager.runReportToPdfFile((String)string2, (String)string4, hashMap, (JRDataSource)new JRBeanCollectionDataSource(arrayList2));
                    jasperUtils.add(file);
                    System.out.println("rmkFile=" + file2.exists());
                    if (!bl || !file2.exists()) continue;
                    jasperUtils.add(file2);
                }
                jasperUtils.printMergerPdfFile(string + PDF_EXT);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void printReport6(String string, Connection connection) {
        String string2 = this.jrxmlFolderPath + JASPER_REPORT6;
        String string3 = "select * from BASELAND_RENT_EXT where year ='" + this.year + "' " + "and baseno = '" + this.baseno + "';";
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        try {
            ArrayList arrayList = new NDAO_BASELAND_RENT_EXT().findBySql(string3, connection);
            for (Object object : arrayList) {
                if (((NVO_BASELAND_RENT_EXT)object).getCre06() == null || ((NVO_BASELAND_RENT_EXT)object).getCre06().equals("")) continue;
                ((NVO_BASELAND_RENT_EXT)object).setCre01(StringProcess.build2Short(((NVO_BASELAND_RENT_EXT)object).getCre01()));
                ((NVO_BASELAND_RENT_EXT)object).setCre06(BaseLandCode.decodeInstruCode(((NVO_BASELAND_RENT_EXT)object).getCre06()));
            }
            if (arrayList.size() > 0) {
                Object object;
                NVO_BASELAND_RENT_EXT nVO_BASELAND_RENT_EXT = (NVO_BASELAND_RENT_EXT)arrayList.get(0);
                nVO_BASELAND_RENT_EXT.setCre02(StringProcess.ascii2Unicode(nVO_BASELAND_RENT_EXT.getCre02()));
                object = new BaseLandPrintRemark(nVO_BASELAND_RENT_EXT.getBaseno(), this.jrxmlFolderPath);
                ((BaseLandPrintRemark)object).printReport((BaseLandPrintRemark)object, string2, string, nVO_BASELAND_RENT_EXT, "cre58", "6", this.rmk_page, hashMap);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void printReport7(String string, Connection connection) {
        block5: {
            String string2 = this.jrxmlFolderPath + JASPER_REPORT7;
            String string3 = "select * from BASELAND_RENT where year ='" + this.year + "' " + "and baseno = '" + this.baseno + "';";
            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            try {
                Object object;
                Object object2;
                ArrayList arrayList = new NDAO_BASELAND_RENT().findBySql(string3, connection);
                if (arrayList.size() <= 0) break block5;
                NVO_BASELAND_RENT nVO_BASELAND_RENT = (NVO_BASELAND_RENT)arrayList.get(0);
                nVO_BASELAND_RENT.setCr48name(new EstimateReport7Model().getCR48NameMap(String.valueOf(nVO_BASELAND_RENT.getCr48())));
                BaseLandPrintRemark<NVO_BASELAND_RENT> baseLandPrintRemark = new BaseLandPrintRemark<NVO_BASELAND_RENT>(nVO_BASELAND_RENT.getBaseno(), this.jrxmlFolderPath);
                baseLandPrintRemark.printReport(baseLandPrintRemark, string2, string, nVO_BASELAND_RENT, "cr44", "7", this.rmk_page, hashMap);
                if (!this.printRentMonth) break block5;
                ArrayList<BaseLandRentMonthBean> arrayList2 = new ArrayList<BaseLandRentMonthBean>();
                BaseLandRentMonthBean baseLandRentMonthBean = new BaseLandRentMonthBean();
                if (arrayList.size() > 0 && ((TreeMap)(object2 = new NDAO_BASELAND_RENT_MONTH().queryDataMap(this.year, this.baseno, connection))).size() > 0) {
                    try {
                        object = new AutoCalBaseLandRent();
                        int n = ((AutoCalBaseLandRent)object).calRentMonth((TreeMap<String, NVO_BASELAND_RENT_MONTH>)object2);
                        baseLandRentMonthBean = this.getLandRentMonth((TreeMap<String, NVO_BASELAND_RENT_MONTH>)object2, n);
                        arrayList2.add(baseLandRentMonthBean);
                    }
                    catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                object2 = new JasperUtils();
                object = new File(string);
                ((JasperUtils)object2).add((File)object);
                String string4 = this.jrxmlFolderPath + JASPER_REPORT7_MONTH;
                string = string.replaceAll(PDF_EXT, "");
                String string5 = string + "_month" + PDF_EXT;
                File file = new File(string5);
                JasperRunManager.runReportToPdfFile((String)string4, (String)string5, hashMap, (JRDataSource)new JRBeanCollectionDataSource(arrayList2));
                ((JasperUtils)object2).add(file);
                ((JasperUtils)object2).printMergerPdfFile(string + PDF_EXT);
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    private BaseLandRentMonthBean getLandRentMonth(TreeMap<String, NVO_BASELAND_RENT_MONTH> treeMap, long l) {
        int n = 0;
        BaseLandRentMonthBean baseLandRentMonthBean = new BaseLandRentMonthBean();
        baseLandRentMonthBean.setBaseno(this.baseno);
        baseLandRentMonthBean.setTmpcr09(l);
        for (String string : treeMap.keySet()) {
            NVO_BASELAND_RENT_MONTH nVO_BASELAND_RENT_MONTH = treeMap.get(string);
            if (nVO_BASELAND_RENT_MONTH.getRental_type().equals("1")) {
                nVO_BASELAND_RENT_MONTH.setRental_type("\u767b\u9304\u79df\u91d1");
            } else if (nVO_BASELAND_RENT_MONTH.getRental_type().equals("2")) {
                nVO_BASELAND_RENT_MONTH.setRental_type("\u8a62\u554f\u79df\u91d1");
            } else if (nVO_BASELAND_RENT_MONTH.getRental_type().equals("3")) {
                nVO_BASELAND_RENT_MONTH.setRental_type("\u5f85\u79df\u50f9");
            }
            if (nVO_BASELAND_RENT_MONTH.getRent_date().equals("1")) {
                nVO_BASELAND_RENT_MONTH.setRent_date("\u6700\u8fd13\u500b\u6708");
            } else if (nVO_BASELAND_RENT_MONTH.getRent_date().equals("2")) {
                nVO_BASELAND_RENT_MONTH.setRent_date("4-6\u6708");
            } else if (nVO_BASELAND_RENT_MONTH.getRent_date().equals("3")) {
                nVO_BASELAND_RENT_MONTH.setRent_date("6\u500b\u6708\u4ee5\u524d");
            }
            HashMap<String, Object> hashMap = nVO_BASELAND_RENT_MONTH.getFieldToHashMapExport();
            System.out.println(hashMap);
            for (String string2 : hashMap.keySet()) {
                Object object = hashMap.get(string2);
                String string3 = "";
                if (",baseno,year,city,addr,land_position".indexOf(string2) != -1) continue;
                Method method = null;
                Method method2 = null;
                String string4 = StringProcess.getFustionString(string2, false);
                try {
                    method = nVO_BASELAND_RENT_MONTH.getClass().getMethod(string4, new Class[0]);
                    string3 = method.getReturnType().getName();
                    if (string2.equals("near_adj")) {
                        if (String.valueOf(object).equals("0")) {
                            object = "";
                        } else if (String.valueOf(object).equals("1")) {
                            object = "\u8fd1\u50f9\u5340\u6bb5\u5167";
                        } else if (String.valueOf(object).equals("2")) {
                            object = "\u8fd1\u50f9\u5340\u6bb5\u5916";
                        }
                        string3 = "java.lang.String";
                    }
                    String string5 = StringProcess.setFustionString(string2 + "_" + n);
                    if (string3.equals("java.lang.String")) {
                        method2 = baseLandRentMonthBean.getClass().getMethod(string5, String.class);
                        method2.invoke((Object)baseLandRentMonthBean, String.valueOf(object));
                        continue;
                    }
                    if (string3.equals("long")) {
                        method2 = baseLandRentMonthBean.getClass().getMethod(string5, Long.class);
                        method2.invoke((Object)baseLandRentMonthBean, StringProcess.parserLong(String.valueOf(object)));
                        continue;
                    }
                    if (string3.equals("int")) {
                        method2 = baseLandRentMonthBean.getClass().getMethod(string5, Integer.class);
                        method2.invoke((Object)baseLandRentMonthBean, StringProcess.parserInt(String.valueOf(object)));
                        continue;
                    }
                    if (!string3.equals("double")) continue;
                    method2 = baseLandRentMonthBean.getClass().getMethod(string5, Double.class);
                    method2.invoke((Object)baseLandRentMonthBean, StringProcess.parserDouble(String.valueOf(object)));
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            ++n;
        }
        return baseLandRentMonthBean;
    }

    private NVO_BASELAND_DEVELOP printReport8(String string, Connection connection) {
        String string2 = this.jrxmlFolderPath + JASPER_REPORT8;
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        try {
            NVO_BASELAND_MAIN nVO_BASELAND_MAIN = new NDAO_BASELAND_MAIN().findByPk(this.baseno, this.year, connection);
            NVO_BASELAND_DEVELOP nVO_BASELAND_DEVELOP = new NDAO_BASELAND_DEVELOP().findByPk(this.baseno, this.year, connection);
            NVO_BASELAND_DEVELOP_EXT nVO_BASELAND_DEVELOP_EXT = new NDAO_BASELAND_DEVELOP_EXT().findByPk(this.baseno, this.year, connection);
            if (nVO_BASELAND_MAIN != null && nVO_BASELAND_DEVELOP != null) {
                nVO_BASELAND_DEVELOP.setInst_code(BaseLandCode.decodeInstruCode(nVO_BASELAND_DEVELOP.getInst_code()));
                BaseLandDevelopParamBean baseLandDevelopParamBean = new BaseLandDevelopParamBean(nVO_BASELAND_MAIN, nVO_BASELAND_DEVELOP, nVO_BASELAND_DEVELOP_EXT);
                new AutoCalBaseLandDevelopBo(baseLandDevelopParamBean).calculateData();
                DevelopReportBean developReportBean = new DevelopReportBean(baseLandDevelopParamBean.getVoMain(), baseLandDevelopParamBean.getVoDevelop(), baseLandDevelopParamBean.getVoDevelopExt());
                ArrayList<DevelopReportBean> arrayList = new ArrayList<DevelopReportBean>();
                arrayList.add(developReportBean);
                BaseLandPrintRemark<DevelopReportBean> baseLandPrintRemark = new BaseLandPrintRemark<DevelopReportBean>(this.baseno, this.jrxmlFolderPath);
                baseLandPrintRemark.printReport(baseLandPrintRemark, string2, string, developReportBean, "notes", "8", this.rmk_page, hashMap);
            }
            return nVO_BASELAND_DEVELOP;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    private void printReport8Ext(String string, Connection connection) {
        String string2 = this.jrxmlFolderPath + JASPER_REPORT8_EXT;
        HashMap hashMap = new HashMap();
        try {
            NVO_BASELAND_MAIN nVO_BASELAND_MAIN = new NDAO_BASELAND_MAIN().findByPk(this.baseno, this.year, connection);
            NVO_BASELAND_DEVELOP_EXT nVO_BASELAND_DEVELOP_EXT = new NDAO_BASELAND_DEVELOP_EXT().findByPk(this.baseno, this.year, connection);
            if (nVO_BASELAND_MAIN != null && nVO_BASELAND_DEVELOP_EXT != null) {
                DevelopReportBean developReportBean = new DevelopReportBean(nVO_BASELAND_MAIN, nVO_BASELAND_DEVELOP_EXT);
                ArrayList<DevelopReportBean> arrayList = new ArrayList<DevelopReportBean>();
                arrayList.add(developReportBean);
                JasperRunManager.runReportToPdfFile((String)string2, (String)string, hashMap, (JRDataSource)new JRBeanCollectionDataSource(arrayList));
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void printReportPicture(String string, char[] cArray, Connection connection) {
        String string2 = this.jrxmlFolderPath + JASPER_PICTURE;
        BaseLandReportImageHelper baseLandReportImageHelper = new BaseLandReportImageHelper();
        try {
            HashMap hashMap = new HashMap();
            ArrayList<BaseLandReportImageBean> arrayList = baseLandReportImageHelper.getPrintBeans(this.year, this.baseno, cArray, connection);
            if (arrayList.size() > 0) {
                JasperRunManager.runReportToPdfFile((String)string2, (String)string, hashMap, (JRDataSource)new JRBeanCollectionDataSource(arrayList));
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private ArrayList<String> getAttachs(String string) {
        ArrayList<String> arrayList = new ArrayList<String>();
        if (string.length() == 6) {
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
            linkedHashMap.put("\u6bd4\u8f03\u6cd5\u8abf\u67e5\u4f30\u50f9\u8868", string.substring(0, 1));
            linkedHashMap.put("\u6210\u672c\u6cd5\u53ca\u623f\u5730\u5206\u96e2\u4f30\u50f9\u8868", string.substring(1, 2));
            linkedHashMap.put("\u6536\u76ca\u6cd5\u8abf\u67e5\u4f30\u50f9\u8868", string.substring(2, 3));
            linkedHashMap.put("\u6210\u672c\u6cd5\u8abf\u67e5\u4f30\u50f9\u8868", string.substring(3, 4));
            linkedHashMap.put("\u571f\u5730\u958b\u767c\u5206\u6790\u6cd5\u8abf\u67e5\u4f30\u50f9\u8868", string.substring(4, 5));
            linkedHashMap.put("\u7167\u7247", string.substring(5));
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                if (((String)entry.getValue()).equals("1")) {
                    arrayList.add((String)entry.getKey());
                    continue;
                }
                arrayList.add("");
            }
        } else {
            System.out.println("Attachs \u6b04\u4f4d\u9577\u5ea6\u932f\u8aa4");
        }
        return arrayList;
    }

    public static int countRowSize(String string, int n, String string2) throws Exception {
        int n2 = 0;
        if (string != null && string2 != null) {
            String[] stringArray = StringProcess.split(string, string2);
            n2 = stringArray.length;
            for (String string3 : stringArray) {
                int n3 = string.getBytes("ISO8859_1").length;
                if (n3 <= n) continue;
                n2 += n3 / n;
            }
        } else {
            return 0;
        }
        return n2;
    }
}

