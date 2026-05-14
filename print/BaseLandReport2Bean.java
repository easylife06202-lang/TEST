/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
 */
package moiland.baseland.print;

import com.wfusion.util.StringProcess;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.TreeMap;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_APPRAISAL;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_APPRAISALA3_SCORE;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_MAIN;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_SELL;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_APPRAISAL;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_APPRAISALA3_SCORE;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_MAIN;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_SELL;
import moiland.baseland.util.BaseLandCode;
import moiland.baseland.util.CodeList;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class BaseLandReport2Bean {
    private NVO_BASELAND_APPRAISAL report2 = null;
    private NVO_BASELAND_APPRAISALA3_SCORE target = null;
    private ArrayList<NVO_BASELAND_APPRAISALA3_SCORE> sources = new ArrayList();
    private JRBeanCollectionDataSource jrs = null;
    private NVO_BASELAND_MAIN main = null;
    private String baseno = "";
    private double as371_1 = 0.0;
    private double as372_1 = 0.0;
    private int as373_1 = 0;
    private double month_near_1 = 0.0;
    private int area_near_1 = 0;
    private double abs_rate_1 = 0.0;
    private int diff_cnt_1 = 0;
    private double as371_2 = 0.0;
    private double as372_2 = 0.0;
    private int as373_2 = 0;
    private double month_near_2 = 0.0;
    private int area_near_2 = 0;
    private double abs_rate_2 = 0.0;
    private int diff_cnt_2 = 0;
    private double as371_3 = 0.0;
    private double as372_3 = 0.0;
    private int as373_3 = 0;
    private double month_near_3 = 0.0;
    private int area_near_3 = 0;
    private double abs_rate_3 = 0.0;
    private int diff_cnt_3 = 0;
    private String notes = "";

    public String getBaseno() {
        return this.baseno;
    }

    public void setBaseno(String string) {
        this.baseno = string;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String string) {
        this.notes = string;
    }

    public BaseLandReport2Bean() {
    }

    public BaseLandReport2Bean(String string, String string2, Connection connection) throws Exception {
        this();
        this.getReportData(string, string2, connection);
    }

    public void getReportData(String string, String string2, Connection connection) throws Exception {
        this.main = new NDAO_BASELAND_MAIN().findByPk(string2, string, connection);
        TreeMap<String, NVO_BASELAND_APPRAISALA3_SCORE> treeMap = new NDAO_BASELAND_APPRAISALA3_SCORE().queryDataMap(string, string2, connection);
        TreeMap<String, NVO_BASELAND_SELL> treeMap2 = new NDAO_BASELAND_SELL().queryCaseSellMap(string, string2, "", connection);
        if (treeMap.containsKey("0")) {
            this.target = treeMap.get("0");
            BaseLandReport2Bean.fillTargetAppraisal(this.main, this.target);
        }
        if (treeMap.containsKey("1") && treeMap2.containsKey("1")) {
            BaseLandReport2Bean.fillSourceAppraisal(treeMap2.get("1"), treeMap.get("1"));
            this.sources.add(treeMap.get("1"));
        }
        if (treeMap.containsKey("2") && treeMap2.containsKey("2")) {
            BaseLandReport2Bean.fillSourceAppraisal(treeMap2.get("2"), treeMap.get("2"));
            this.sources.add(treeMap.get("2"));
        }
        if (treeMap.containsKey("3") && treeMap2.containsKey("3")) {
            BaseLandReport2Bean.fillSourceAppraisal(treeMap2.get("3"), treeMap.get("3"));
            this.sources.add(treeMap.get("3"));
        }
        this.jrs = new JRBeanCollectionDataSource(this.sources);
        NVO_BASELAND_APPRAISAL nVO_BASELAND_APPRAISAL = new NVO_BASELAND_APPRAISAL();
        nVO_BASELAND_APPRAISAL.setYear(string);
        nVO_BASELAND_APPRAISAL.setBaseno(string2);
        this.report2 = (NVO_BASELAND_APPRAISAL)new NDAO_BASELAND_APPRAISAL().findByPk(nVO_BASELAND_APPRAISAL, connection);
        if (treeMap.containsKey("1")) {
            this.as371_1 = treeMap.get("1").getAs371();
            this.as372_1 = treeMap.get("1").getAs372();
            this.as373_1 = treeMap.get("1").getAs373();
            this.month_near_1 = treeMap.get("1").getMonth_near();
            this.area_near_1 = treeMap.get("1").getArea_near();
            this.abs_rate_1 = treeMap.get("1").getAbs_rate();
            this.diff_cnt_1 = treeMap.get("1").getDiff_cnt();
        }
        if (treeMap.containsKey("2")) {
            this.as371_2 = treeMap.get("2").getAs371();
            this.as372_2 = treeMap.get("2").getAs372();
            this.as373_2 = treeMap.get("2").getAs373();
            this.month_near_2 = treeMap.get("2").getMonth_near();
            this.area_near_2 = treeMap.get("2").getArea_near();
            this.abs_rate_2 = treeMap.get("2").getAbs_rate();
            this.diff_cnt_2 = treeMap.get("2").getDiff_cnt();
        }
        if (treeMap.containsKey("3")) {
            this.as371_3 = treeMap.get("3").getAs371();
            this.as372_3 = treeMap.get("3").getAs372();
            this.as373_3 = treeMap.get("3").getAs373();
            this.month_near_3 = treeMap.get("3").getMonth_near();
            this.area_near_3 = treeMap.get("3").getArea_near();
            this.abs_rate_3 = treeMap.get("3").getAbs_rate();
            this.diff_cnt_3 = treeMap.get("3").getDiff_cnt();
        }
        this.notes = this.report2.getNotes();
        string2 = this.report2.getBaseno();
    }

    public static void fillTargetAppraisal(NVO_BASELAND_MAIN nVO_BASELAND_MAIN, NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE) {
        if (!nVO_BASELAND_MAIN.getAddr().equals("")) {
            nVO_BASELAND_APPRAISALA3_SCORE.setAs301(StringProcess.ascii2Unicode(nVO_BASELAND_MAIN.getAddr()));
        } else {
            nVO_BASELAND_APPRAISALA3_SCORE.setAs301(StringProcess.ascii2Unicode(nVO_BASELAND_MAIN.getLand_position()));
        }
        nVO_BASELAND_APPRAISALA3_SCORE.setAs302_nm(BaseLandCode.decodeAs302(nVO_BASELAND_APPRAISALA3_SCORE.getAs302()));
        nVO_BASELAND_APPRAISALA3_SCORE.setAs303_nm(BaseLandCode.decodeAs303(nVO_BASELAND_APPRAISALA3_SCORE.getAs303()));
        nVO_BASELAND_APPRAISALA3_SCORE.setAs308(nVO_BASELAND_MAIN.getPrice_date());
        nVO_BASELAND_APPRAISALA3_SCORE.setAs339(nVO_BASELAND_MAIN.getAa10());
        nVO_BASELAND_APPRAISALA3_SCORE.setAs364_nm(CodeList.decodeUrban(nVO_BASELAND_APPRAISALA3_SCORE.getAs364_nm()));
    }

    public static void fillSourceAppraisal(NVO_BASELAND_SELL nVO_BASELAND_SELL, NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE) {
        if ("4".equals(nVO_BASELAND_SELL.getSelltype())) {
            nVO_BASELAND_APPRAISALA3_SCORE.setAs301(StringProcess.ascii2Unicode(nVO_BASELAND_SELL.getLand_position()));
        } else {
            nVO_BASELAND_APPRAISALA3_SCORE.setAs301(StringProcess.ascii2Unicode(nVO_BASELAND_SELL.getCs01()));
        }
        nVO_BASELAND_APPRAISALA3_SCORE.setAs302_nm(BaseLandCode.decodeAs302(nVO_BASELAND_APPRAISALA3_SCORE.getAs302()));
        nVO_BASELAND_APPRAISALA3_SCORE.setAs303_nm(BaseLandCode.decodeAs303(nVO_BASELAND_APPRAISALA3_SCORE.getAs303()));
        nVO_BASELAND_APPRAISALA3_SCORE.setAs304(nVO_BASELAND_SELL.getCs46());
        if (nVO_BASELAND_SELL.getSelltype().equals("4")) {
            nVO_BASELAND_APPRAISALA3_SCORE.setAs305(nVO_BASELAND_SELL.getCs49());
        } else if (nVO_BASELAND_SELL.getSelltype().equals("5") || nVO_BASELAND_SELL.getSelltype().equals("6")) {
            nVO_BASELAND_APPRAISALA3_SCORE.setAs305(nVO_BASELAND_SELL.getCs06());
        }
        nVO_BASELAND_APPRAISALA3_SCORE.setAs306(nVO_BASELAND_SELL.getCs51());
        nVO_BASELAND_APPRAISALA3_SCORE.setAs308(nVO_BASELAND_SELL.getCs30());
        nVO_BASELAND_APPRAISALA3_SCORE.setAs339(nVO_BASELAND_SELL.getAa10());
        nVO_BASELAND_APPRAISALA3_SCORE.setAs364_nm(CodeList.decodeUrban(nVO_BASELAND_APPRAISALA3_SCORE.getAs364_nm()));
    }

    public NVO_BASELAND_APPRAISAL getReport2() {
        return this.report2;
    }

    public NVO_BASELAND_APPRAISALA3_SCORE getTarget() {
        return this.target;
    }

    public ArrayList<NVO_BASELAND_APPRAISALA3_SCORE> getSources() {
        return this.sources;
    }

    public NVO_BASELAND_MAIN getMain() {
        return this.main;
    }

    public double getAs371_1() {
        return this.as371_1;
    }

    public double getAs372_1() {
        return this.as372_1;
    }

    public int getAs373_1() {
        return this.as373_1;
    }

    public double getMonth_near_1() {
        return this.month_near_1;
    }

    public int getArea_near_1() {
        return this.area_near_1;
    }

    public double getAbs_rate_1() {
        return this.abs_rate_1;
    }

    public int getDiff_cnt_1() {
        return this.diff_cnt_1;
    }

    public double getAs371_2() {
        return this.as371_2;
    }

    public double getAs372_2() {
        return this.as372_2;
    }

    public int getAs373_2() {
        return this.as373_2;
    }

    public double getMonth_near_2() {
        return this.month_near_2;
    }

    public int getArea_near_2() {
        return this.area_near_2;
    }

    public double getAbs_rate_2() {
        return this.abs_rate_2;
    }

    public int getDiff_cnt_2() {
        return this.diff_cnt_2;
    }

    public double getAs371_3() {
        return this.as371_3;
    }

    public double getAs372_3() {
        return this.as372_3;
    }

    public int getAs373_3() {
        return this.as373_3;
    }

    public double getMonth_near_3() {
        return this.month_near_3;
    }

    public int getArea_near_3() {
        return this.area_near_3;
    }

    public double getAbs_rate_3() {
        return this.abs_rate_3;
    }

    public int getDiff_cnt_3() {
        return this.diff_cnt_3;
    }

    public void setReport2(NVO_BASELAND_APPRAISAL nVO_BASELAND_APPRAISAL) {
        this.report2 = nVO_BASELAND_APPRAISAL;
    }

    public void setTarget(NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE) {
        this.target = nVO_BASELAND_APPRAISALA3_SCORE;
    }

    public void setSources(ArrayList<NVO_BASELAND_APPRAISALA3_SCORE> arrayList) {
        this.sources = arrayList;
    }

    public void setMain(NVO_BASELAND_MAIN nVO_BASELAND_MAIN) {
        this.main = nVO_BASELAND_MAIN;
    }

    public void setAs371_1(double d) {
        this.as371_1 = d;
    }

    public void setAs372_1(double d) {
        this.as372_1 = d;
    }

    public void setAs373_1(int n) {
        this.as373_1 = n;
    }

    public void setMonth_near_1(int n) {
        this.month_near_1 = n;
    }

    public void setArea_near_1(int n) {
        this.area_near_1 = n;
    }

    public void setAbs_rate_1(double d) {
        this.abs_rate_1 = d;
    }

    public void setDiff_cnt_1(int n) {
        this.diff_cnt_1 = n;
    }

    public void setAs371_2(double d) {
        this.as371_2 = d;
    }

    public void setAs372_2(double d) {
        this.as372_2 = d;
    }

    public void setAs373_2(int n) {
        this.as373_2 = n;
    }

    public void setMonth_near_2(int n) {
        this.month_near_2 = n;
    }

    public void setArea_near_2(int n) {
        this.area_near_2 = n;
    }

    public void setAbs_rate_2(double d) {
        this.abs_rate_2 = d;
    }

    public void setDiff_cnt_2(int n) {
        this.diff_cnt_2 = n;
    }

    public void setAs371_3(double d) {
        this.as371_3 = d;
    }

    public void setAs372_3(double d) {
        this.as372_3 = d;
    }

    public void setAs373_3(int n) {
        this.as373_3 = n;
    }

    public void setMonth_near_3(int n) {
        this.month_near_3 = n;
    }

    public void setArea_near_3(int n) {
        this.area_near_3 = n;
    }

    public void setAbs_rate_3(double d) {
        this.abs_rate_3 = d;
    }

    public void setDiff_cnt_3(int n) {
        this.diff_cnt_3 = n;
    }

    public JRBeanCollectionDataSource getJrs() {
        return this.jrs;
    }
}

