/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.print;

import com.wfusion.util.SqlBuilder;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_IMAGES;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_MAIN;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_RENT_MONTH;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_SELL;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_IMAGES;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_MAIN;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT_MONTH;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_SELL;
import moiland.baseland.print.BaseLandReportImageBean;

public class BaseLandReportImageHelper {
    public ArrayList<BaseLandReportImageBean> getPrintBeans(String string, String string2, char[] cArray, Connection connection) throws Exception {
        int n;
        ArrayList<BaseLandReportImageBean> arrayList = new ArrayList<BaseLandReportImageBean>();
        BaseLandReportImageBean baseLandReportImageBean = this.getMainBean(string, string2, connection);
        arrayList.add(baseLandReportImageBean);
        BaseLandReportImageBean baseLandReportImageBean2 = null;
        if (cArray[1] == '1') {
            for (n = 1; n <= 3; ++n) {
                baseLandReportImageBean2 = this.getSellBean(string, string2, String.valueOf(n), connection);
                if (baseLandReportImageBean2 == null) continue;
                arrayList.add(baseLandReportImageBean2);
            }
        }
        if (cArray[2] == '1') {
            for (n = 1; n <= 3; ++n) {
                baseLandReportImageBean2 = this.getRentBean(baseLandReportImageBean, string, string2, String.valueOf(n), connection);
                if (baseLandReportImageBean2 == null) continue;
                arrayList.add(baseLandReportImageBean2);
            }
        }
        return arrayList;
    }

    private BaseLandReportImageBean getMainBean(String string, String string2, Connection connection) throws Exception {
        byte[] byArray;
        NVO_BASELAND_MAIN nVO_BASELAND_MAIN = new NDAO_BASELAND_MAIN().findByPk(string2, string, connection);
        BaseLandReportImageBean baseLandReportImageBean = new BaseLandReportImageBean();
        baseLandReportImageBean.setNumber(nVO_BASELAND_MAIN.getBaseno());
        baseLandReportImageBean.setPicname("\u57fa\u6e96\u5730\uff1a");
        baseLandReportImageBean.setAddress(nVO_BASELAND_MAIN.getAddr());
        baseLandReportImageBean.setLand_position(nVO_BASELAND_MAIN.getLand_position());
        String string3 = "select * from baseland_images where year=@@ and baseno=@@ and photo_type like 'BASE%' ";
        SqlBuilder sqlBuilder = new SqlBuilder(string3);
        sqlBuilder.setString(0, string);
        sqlBuilder.setString(1, string2);
        ArrayList arrayList = new NDAO_BASELAND_IMAGES().findBySql(sqlBuilder.getSql(), connection);
        if (arrayList.size() > 0 && (byArray = ((NVO_BASELAND_IMAGES)arrayList.get(0)).getPhoto()) != null && byArray.length > 0) {
            try {
                baseLandReportImageBean.setPic1(new ImageIcon(byArray).getImage());
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        if (arrayList.size() > 1 && (byArray = ((NVO_BASELAND_IMAGES)arrayList.get(1)).getPhoto()) != null && byArray.length > 0) {
            try {
                baseLandReportImageBean.setPic2(new ImageIcon(byArray).getImage());
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return baseLandReportImageBean;
    }

    private BaseLandReportImageBean getSellBean(String string, String string2, String string3, Connection connection) throws Exception {
        ArrayList<NVO_BASELAND_SELL> arrayList = new NDAO_BASELAND_SELL().queryData(string, string2, string3, connection);
        if (arrayList.size() > 0) {
            byte[] byArray;
            NVO_BASELAND_SELL nVO_BASELAND_SELL = arrayList.get(0);
            BaseLandReportImageBean baseLandReportImageBean = new BaseLandReportImageBean();
            baseLandReportImageBean.setPicname("\u6bd4\u8f03\u6a19\u7684\uff1a");
            baseLandReportImageBean.setNumber(string3);
            baseLandReportImageBean.setAddress(nVO_BASELAND_SELL.getCs01());
            baseLandReportImageBean.setLand_position(nVO_BASELAND_SELL.getLand_position());
            String string4 = "select * from baseland_images where year=@@ and baseno=@@ and photo_type like 'SELL" + string3 + "%' ";
            SqlBuilder sqlBuilder = new SqlBuilder(string4);
            sqlBuilder.setString(0, string);
            sqlBuilder.setString(1, string2);
            ArrayList arrayList2 = new NDAO_BASELAND_IMAGES().findBySql(sqlBuilder.getSql(), connection);
            if (arrayList2.size() > 0 && (byArray = ((NVO_BASELAND_IMAGES)arrayList2.get(0)).getPhoto()) != null && byArray.length > 0) {
                try {
                    baseLandReportImageBean.setPic1(new ImageIcon(byArray).getImage());
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            if (arrayList2.size() > 1 && (byArray = ((NVO_BASELAND_IMAGES)arrayList2.get(1)).getPhoto()) != null && byArray.length > 0) {
                try {
                    baseLandReportImageBean.setPic2(new ImageIcon(byArray).getImage());
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            return baseLandReportImageBean;
        }
        return null;
    }

    private BaseLandReportImageBean getRentBean(BaseLandReportImageBean baseLandReportImageBean, String string, String string2, String string3, Connection connection) throws Exception {
        byte[] byArray;
        NVO_BASELAND_RENT_MONTH nVO_BASELAND_RENT_MONTH = new NDAO_BASELAND_RENT_MONTH().findByPk(string, string2, string3, connection);
        if (nVO_BASELAND_RENT_MONTH == null) {
            return null;
        }
        BaseLandReportImageBean baseLandReportImageBean2 = new BaseLandReportImageBean();
        baseLandReportImageBean2.setPicname("\u6536\u76ca\u5be6\u4f8b\uff1a");
        baseLandReportImageBean2.setNumber(string3);
        baseLandReportImageBean2.setLand_position(nVO_BASELAND_RENT_MONTH.getLand_position());
        baseLandReportImageBean2.setAddress(nVO_BASELAND_RENT_MONTH.getAddr());
        String string4 = "select * from baseland_images where year=@@ and baseno=@@ and photo_type like 'RENT" + string3 + "%' order by photo_type";
        SqlBuilder sqlBuilder = new SqlBuilder(string4);
        sqlBuilder.setString(0, string);
        sqlBuilder.setString(1, string2);
        ArrayList arrayList = new NDAO_BASELAND_IMAGES().findBySql(sqlBuilder.getSql(), connection);
        if (arrayList.size() > 0 && (byArray = ((NVO_BASELAND_IMAGES)arrayList.get(0)).getPhoto()) != null && byArray.length > 0) {
            try {
                baseLandReportImageBean2.setPic1(new ImageIcon(byArray).getImage());
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        if (arrayList.size() > 1 && (byArray = ((NVO_BASELAND_IMAGES)arrayList.get(1)).getPhoto()) != null && byArray.length > 0) {
            try {
                baseLandReportImageBean2.setPic2(new ImageIcon(byArray).getImage());
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return baseLandReportImageBean2;
    }
}

