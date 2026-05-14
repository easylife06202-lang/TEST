/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.embed.swing.SwingFXUtils
 *  javafx.scene.image.Image
 *  org.apache.commons.io.FileUtils
 */
package moiland.baseland.appraiser;

import com.wfusion.dataaccess.dao.DaoBase;
import com.wfusion.dataaccess.vo.VoBase;
import com.wfusion.datasources.ConnectionFactory;
import com.wfusion.util.SqlBuilder;
import com.wfusion.util.SqlUtil;
import com.wfusion.util.StringProcess;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_MAIN;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_APPRAISAL;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_APPRAISALA3_SCORE;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_DEVELOP;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_DEVELOP_EXT;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_IMAGES;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_INDIVIDUAL_FACTOR;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_MAIN;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_REGIONAL_FACTOR;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT_EXT;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT_MONTH;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_SELL;
import moiland.baseland.verify.BaseLandVerifyUtil;
import moiland.baseland.verify.VerifyException;
import org.apache.commons.io.FileUtils;

public class AppraiserExport {
    private LinkedHashMap<String, ArrayList<VoBase>> exportData = new LinkedHashMap();
    private boolean isDirectSave = false;

    public AppraiserExport(boolean bl) {
        this.isDirectSave = bl;
    }

    public ArrayList<NVO_BASELAND_MAIN> getMainData() {
        ArrayList<NVO_BASELAND_MAIN> arrayList = new ArrayList<NVO_BASELAND_MAIN>();
        for (VoBase voBase : this.exportData.get("BASELAND_MAIN")) {
            arrayList.add((NVO_BASELAND_MAIN)voBase);
        }
        return arrayList;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public String export(String string, String string2, String string3, String string4, String string5) throws Exception {
        Connection connection = null;
        Connection connection2 = null;
        String string6 = "";
        try {
            FileUtils.copyFile((File)new File(string), (File)new File(string2));
            connection = ConnectionFactory.createConnection("sqlite", string3, "", "", "empty", "empty");
            connection2 = ConnectionFactory.createConnection("sqlite", string2, "", "", "empty", "empty");
            connection2.setAutoCommit(false);
            string6 = this.copy(string4, string5, connection, connection2);
            connection2.commit();
        }
        catch (Exception exception) {
            try {
                exception.printStackTrace();
                string6 = "\u932f\u8aa4!" + exception.toString();
                SqlUtil.rollback(connection2);
            }
            catch (Throwable throwable) {
                SqlUtil.close(connection);
                SqlUtil.close(connection2);
                throw throwable;
            }
            SqlUtil.close(connection);
            SqlUtil.close(connection2);
        }
        SqlUtil.close(connection);
        SqlUtil.close(connection2);
        return string6;
    }

    public String copy(String string, String string2, Connection connection, Connection connection2) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\nBASELAND_MAIN " + this.copyTable(string, string2, "BASELAND_MAIN", connection, connection2) + "\u7b46.");
        stringBuffer.append("\nBASELAND_APPRAISAL " + this.copyTable(string, string2, "BASELAND_APPRAISAL", connection, connection2) + "\u7b46.");
        stringBuffer.append("\nBASELAND_APPRAISALA3_SCORE " + this.copyTable(string, string2, "BASELAND_APPRAISALA3_SCORE", connection, connection2) + "\u7b46.");
        stringBuffer.append("\nBASELAND_SELL " + this.copyTable(string, string2, "BASELAND_SELL", connection, connection2) + "\u7b46.");
        stringBuffer.append("\nBASELAND_FLOOR_EFFECT " + this.copyTable(string, string2, "BASELAND_FLOOR_EFFECT", connection, connection2) + "\u7b46.");
        stringBuffer.append("\nBASELAND_RENT " + this.copyTable(string, string2, "BASELAND_RENT", connection, connection2) + "\u7b46.");
        stringBuffer.append("\nBASELAND_RENT_EXT " + this.copyTable(string, string2, "BASELAND_RENT_EXT", connection, connection2) + "\u7b46.");
        stringBuffer.append("\nBASELAND_RENT_MONTH " + this.copyTable(string, string2, "BASELAND_RENT_MONTH", connection, connection2) + "\u7b46.");
        stringBuffer.append("\nBASELAND_DEVELOP " + this.copyTable(string, string2, "BASELAND_DEVELOP", connection, connection2) + "\u7b46.");
        stringBuffer.append("\nBASELAND_DEVELOP_EXT " + this.copyTable(string, string2, "BASELAND_DEVELOP_EXT", connection, connection2) + "\u7b46.");
        stringBuffer.append("\nBASELAND_IMAGES " + this.copyImage(string, string2, "BASELAND_IMAGES", connection, connection2) + "\u7b46.");
        stringBuffer.append("\nBASELAND_REGIONAL_FACTOR " + this.copyFactorTable(string, string2, "BASELAND_REGIONAL_FACTOR", connection, connection2) + "\u7b46.");
        stringBuffer.append("\nBASELAND_REGIONAL_FACTOR_STD " + this.copyFactorTable(string, string2, "BASELAND_REGIONAL_FACTOR_STD", connection, connection2) + "\u7b46.");
        stringBuffer.append("\nBASELAND_INDIVIDUAL_FACTOR " + this.copyFactorTable(string, string2, "BASELAND_INDIVIDUAL_FACTOR", connection, connection2) + "\u7b46.");
        stringBuffer.append("\nBASELAND_INDIVIDUAL_FACTOR_STD " + this.copyFactorTable(string, string2, "BASELAND_INDIVIDUAL_FACTOR_STD", connection, connection2) + "\u7b46.");
        return stringBuffer.toString();
    }

    public String copyToVersion(String string, String string2, Connection connection, Connection connection2) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\nBASELAND_MAIN " + this.copyTable(string, string2, "BASELAND_MAIN", connection, connection2) + "\u7b46.");
        stringBuffer.append("\nBASELAND_APPRAISAL " + this.copyTable(string, string2, "BASELAND_APPRAISAL", connection, connection2) + "\u7b46.");
        stringBuffer.append("\nBASELAND_APPRAISALA3_SCORE " + this.copyTable(string, string2, "BASELAND_APPRAISALA3_SCORE", connection, connection2) + "\u7b46.");
        stringBuffer.append("\nBASELAND_SELL " + this.copyTable(string, string2, "BASELAND_SELL", connection, connection2) + "\u7b46.");
        stringBuffer.append("\nBASELAND_FLOOR_EFFECT " + this.copyTable(string, string2, "BASELAND_FLOOR_EFFECT", connection, connection2) + "\u7b46.");
        stringBuffer.append("\nBASELAND_RENT " + this.copyTable(string, string2, "BASELAND_RENT", connection, connection2) + "\u7b46.");
        stringBuffer.append("\nBASELAND_RENT_EXT " + this.copyTable(string, string2, "BASELAND_RENT_EXT", connection, connection2) + "\u7b46.");
        stringBuffer.append("\nBASELAND_RENT_MONTH " + this.copyTable(string, string2, "BASELAND_RENT_MONTH", connection, connection2) + "\u7b46.");
        stringBuffer.append("\nBASELAND_DEVELOP " + this.copyTable(string, string2, "BASELAND_DEVELOP", connection, connection2) + "\u7b46.");
        stringBuffer.append("\nBASELAND_DEVELOP_EXT " + this.copyTable(string, string2, "BASELAND_DEVELOP_EXT", connection, connection2) + "\u7b46.");
        stringBuffer.append("\nBASELAND_IMAGES " + this.copyImage(string, string2, "BASELAND_IMAGES", connection, connection2) + "\u7b46.");
        return stringBuffer.toString();
    }

    private String copyFactorTable(String string, String string2, String string3, Connection connection, Connection connection2) throws Exception {
        NDAO_BASELAND_MAIN nDAO_BASELAND_MAIN = new NDAO_BASELAND_MAIN();
        NVO_BASELAND_MAIN nVO_BASELAND_MAIN = nDAO_BASELAND_MAIN.findByPk(string2, string, connection);
        StringBuffer stringBuffer = new StringBuffer();
        if (nVO_BASELAND_MAIN != null) {
            DaoBase daoBase = (DaoBase)Class.forName("moiland.baseland.dataaccess.ndao.NDAO_" + string3).newInstance();
            String string4 = "select * from " + string3 + " where 1=1 and baseno=@@ ";
            SqlBuilder sqlBuilder = new SqlBuilder(string4);
            sqlBuilder.setString(0, string2);
            string4 = sqlBuilder.getSql();
            System.out.println(string4);
            ArrayList arrayList = daoBase.findBySql(string4, connection);
            if (arrayList == null || arrayList.size() < 1) {
                string4 = "select * from " + string3 + " where 1=1 ";
                string4 = string4 + " and year=@@";
                string4 = string4 + " and city=@@";
                string4 = string4 + " and dist=@@";
                string4 = string4 + " and version=@@";
                string4 = string4 + " and baseno=''";
                sqlBuilder = new SqlBuilder(string4);
                sqlBuilder.setString(0, string);
                sqlBuilder.setString(1, nVO_BASELAND_MAIN.getCity());
                sqlBuilder.setString(2, nVO_BASELAND_MAIN.getDist());
                sqlBuilder.setString(3, nVO_BASELAND_MAIN.getVersion());
                string4 = sqlBuilder.getSql();
                System.out.println(string4);
                arrayList = daoBase.findBySql(string4, connection);
                if (arrayList != null && arrayList.size() > 0) {
                    for (VoBase voBase : arrayList) {
                        voBase.getElementAt("baseno").setValue(string2);
                    }
                }
            }
            this.exportData.put(string3, arrayList);
            if (this.isDirectSave) {
                this.doSave(connection2);
            }
            stringBuffer.append(arrayList.size());
        }
        return stringBuffer.toString();
    }

    private String copyTable(String string, String string2, String string3, Connection connection, Connection connection2) throws Exception {
        Object object;
        String string4 = "select * from " + string3 + " where 1=1 ";
        if (!StringProcess.isEmpty(string)) {
            string4 = string4 + " and year=@@";
            object = new SqlBuilder(string4);
            ((SqlBuilder)object).setString(0, string);
            string4 = ((SqlBuilder)object).getSql();
        }
        if (!StringProcess.isEmpty(string2)) {
            string4 = string4 + " and baseno=@@";
            object = new SqlBuilder(string4);
            ((SqlBuilder)object).setString(0, string2);
            string4 = ((SqlBuilder)object).getSql();
        }
        System.out.println(string4);
        object = (DaoBase)Class.forName("moiland.baseland.dataaccess.ndao.NDAO_" + string3).newInstance();
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList arrayList = ((DaoBase)object).findBySql(string4, connection);
        this.exportData.put(string3, arrayList);
        if (this.isDirectSave) {
            this.doSave(connection2);
        }
        stringBuffer.append(arrayList.size());
        return stringBuffer.toString();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private String copyImage(String string, String string2, String string3, Connection connection, Connection connection2) throws Exception {
        Object object;
        String string4 = "select * from " + string3 + " where 1=1 ";
        if (!StringProcess.isEmpty(string)) {
            string4 = string4 + " and year=@@";
            object = new SqlBuilder(string4);
            ((SqlBuilder)object).setString(0, string);
            string4 = ((SqlBuilder)object).getSql();
        }
        if (!StringProcess.isEmpty(string2)) {
            string4 = string4 + " and baseno=@@";
            object = new SqlBuilder(string4);
            ((SqlBuilder)object).setString(0, string2);
            string4 = ((SqlBuilder)object).getSql();
        }
        System.out.println(string4);
        object = (DaoBase)Class.forName("moiland.baseland.dataaccess.ndao.NDAO_" + string3).newInstance();
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList arrayList = ((DaoBase)object).findBySql(string4, connection);
        for (VoBase voBase : arrayList) {
            byte[] byArray = null;
            BufferedImage bufferedImage = null;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                Image image = new Image((InputStream)new ByteArrayInputStream((byte[])voBase.getElementAt("photo").getObject()));
                if (image == null) continue;
                bufferedImage = SwingFXUtils.fromFXImage((Image)image, null);
                ImageIO.write((RenderedImage)bufferedImage, "jpg", byteArrayOutputStream);
                byArray = byteArrayOutputStream.toByteArray();
                if (byArray.length <= 0) continue;
                voBase.getElementAt("photo").setValue(byArray);
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
            finally {
                try {
                    byteArrayOutputStream.close();
                }
                catch (IOException iOException) {}
            }
        }
        this.exportData.put(string3, arrayList);
        if (this.isDirectSave) {
            this.doSave(connection2);
        }
        stringBuffer.append(arrayList.size());
        return stringBuffer.toString();
    }

    public void doSave(Connection connection) throws Exception {
        for (String string : this.exportData.keySet()) {
            DaoBase daoBase = (DaoBase)Class.forName("moiland.baseland.dataaccess.ndao.NDAO_" + string).newInstance();
            ArrayList<VoBase> arrayList = this.exportData.get(string);
            if (arrayList == null || arrayList.size() <= 0) continue;
            daoBase.delete(arrayList, connection);
            daoBase.create(arrayList, connection);
        }
    }

    public void checkRequiredField() throws VerifyException {
        try {
            boolean bl;
            boolean bl2 = this.checkMain(this.exportData.get("BASELAND_MAIN"));
            this.checkAppraisalA3Score(this.exportData.get("BASELAND_APPRAISALA3_SCORE"));
            this.checkAppraisal(this.exportData.get("BASELAND_APPRAISAL"));
            this.checkSell(this.exportData.get("BASELAND_SELL"));
            this.checkRent(this.exportData.get("BASELAND_RENT"));
            this.checkRentMonth(this.exportData.get("BASELAND_RENT_MONTH"));
            if (bl2) {
                this.checkRentExt(this.exportData.get("BASELAND_RENT_EXT"));
            }
            if (bl = this.checkDevelop(this.exportData.get("BASELAND_DEVELOP"))) {
                this.checkDevelopExt(this.exportData.get("BASELAND_DEVELOP_EXT"));
            }
            this.checkImage(this.exportData.get("BASELAND_IMAGES"));
            this.checkIndividual(this.exportData.get("BASELAND_INDIVIDUAL_FACTOR"));
            this.checkRegional(this.exportData.get("BASELAND_REGIONAL_FACTOR"));
        }
        catch (VerifyException verifyException) {
            throw new VerifyException("\u300c" + verifyException.getMessage() + "\u300d\u8cc7\u6599\u6709\u8aa4\uff0c\u8acb\u78ba\u8a8d\u532f\u51fa\u672c\u6a94\u4e4b\u55ae\u6a5f\u7a0b\u5f0f\u7248\u672c\u662f\u5426\u6b63\u78ba\uff01");
        }
    }

    private boolean checkMain(ArrayList<VoBase> arrayList) throws VerifyException {
        boolean bl = false;
        try {
            NVO_BASELAND_MAIN nVO_BASELAND_MAIN = null;
            for (VoBase voBase : arrayList) {
                nVO_BASELAND_MAIN = (NVO_BASELAND_MAIN)voBase;
                BaseLandVerifyUtil.checkBaselandNo(nVO_BASELAND_MAIN.getBaseno());
                BaseLandVerifyUtil.checkCity(nVO_BASELAND_MAIN.getCity());
                BaseLandVerifyUtil.checkDist(nVO_BASELAND_MAIN.getDist());
                BaseLandVerifyUtil.checkYear(nVO_BASELAND_MAIN.getYear());
                BaseLandVerifyUtil.checkAa48(nVO_BASELAND_MAIN.getAa48());
                if (StringProcess.isEmpty(nVO_BASELAND_MAIN.getAa49())) {
                    throw new VerifyException();
                }
                bl = nVO_BASELAND_MAIN.getLand_scene().equals("01");
            }
        }
        catch (VerifyException verifyException) {
            verifyException.printStackTrace();
            throw new VerifyException("\u5730\u50f9\u57fa\u6e96\u5730\u4f30\u50f9\u5831\u544a\u8868");
        }
        return bl;
    }

    private void checkAppraisalA3Score(ArrayList<VoBase> arrayList) throws VerifyException {
        try {
            NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE = null;
            for (VoBase voBase : arrayList) {
                nVO_BASELAND_APPRAISALA3_SCORE = (NVO_BASELAND_APPRAISALA3_SCORE)voBase;
                BaseLandVerifyUtil.checkBaselandNo(nVO_BASELAND_APPRAISALA3_SCORE.getBaseno());
                BaseLandVerifyUtil.checkCity(nVO_BASELAND_APPRAISALA3_SCORE.getCity());
                BaseLandVerifyUtil.checkYear(nVO_BASELAND_APPRAISALA3_SCORE.getYear());
            }
        }
        catch (VerifyException verifyException) {
            verifyException.printStackTrace();
            throw new VerifyException("\u6bd4\u8f03\u6cd5\u8abf\u67e5\u4f30\u50f9\u8868");
        }
    }

    private void checkAppraisal(ArrayList<VoBase> arrayList) throws VerifyException {
        try {
            NVO_BASELAND_APPRAISAL nVO_BASELAND_APPRAISAL = null;
            for (VoBase voBase : arrayList) {
                nVO_BASELAND_APPRAISAL = (NVO_BASELAND_APPRAISAL)voBase;
                BaseLandVerifyUtil.checkBaselandNo(nVO_BASELAND_APPRAISAL.getBaseno());
                BaseLandVerifyUtil.checkCity(nVO_BASELAND_APPRAISAL.getCity());
                BaseLandVerifyUtil.checkDist(nVO_BASELAND_APPRAISAL.getDist());
                BaseLandVerifyUtil.checkYear(nVO_BASELAND_APPRAISAL.getYear());
            }
        }
        catch (VerifyException verifyException) {
            verifyException.printStackTrace();
            throw new VerifyException("\u6bd4\u8f03\u6cd5\u8abf\u67e5\u4f30\u50f9\u8868-\u8abf\u6574\u7387");
        }
    }

    private void checkSell(ArrayList<VoBase> arrayList) throws VerifyException {
        try {
            NVO_BASELAND_SELL nVO_BASELAND_SELL = null;
            for (VoBase voBase : arrayList) {
                nVO_BASELAND_SELL = (NVO_BASELAND_SELL)voBase;
                BaseLandVerifyUtil.checkBaselandNo(nVO_BASELAND_SELL.getBaseno());
                BaseLandVerifyUtil.checkCity(nVO_BASELAND_SELL.getCity());
                BaseLandVerifyUtil.checkDist(nVO_BASELAND_SELL.getDist());
                BaseLandVerifyUtil.checkYear(nVO_BASELAND_SELL.getYear());
                BaseLandVerifyUtil.checkAa48(nVO_BASELAND_SELL.getAa48());
                if (!StringProcess.isEmpty(nVO_BASELAND_SELL.getAa49())) continue;
                throw new VerifyException();
            }
        }
        catch (VerifyException verifyException) {
            verifyException.printStackTrace();
            throw new VerifyException("\u6210\u672c\u6cd5\u53ca\u623f\u5730\u5206\u96e2\u4f30\u50f9\u8868");
        }
    }

    private void checkRent(ArrayList<VoBase> arrayList) throws VerifyException {
        try {
            NVO_BASELAND_RENT nVO_BASELAND_RENT = null;
            for (VoBase voBase : arrayList) {
                nVO_BASELAND_RENT = (NVO_BASELAND_RENT)voBase;
                BaseLandVerifyUtil.checkBaselandNo(nVO_BASELAND_RENT.getBaseno());
                BaseLandVerifyUtil.checkCity(nVO_BASELAND_RENT.getCity());
                BaseLandVerifyUtil.checkDist(nVO_BASELAND_RENT.getDist());
                BaseLandVerifyUtil.checkYear(nVO_BASELAND_RENT.getYear());
            }
        }
        catch (VerifyException verifyException) {
            verifyException.printStackTrace();
            throw new VerifyException("\u6536\u76ca\u6cd5\u8abf\u67e5\u4f30\u50f9\u8868");
        }
    }

    private void checkRentMonth(ArrayList<VoBase> arrayList) throws VerifyException {
        try {
            NVO_BASELAND_RENT_MONTH nVO_BASELAND_RENT_MONTH = null;
            for (VoBase voBase : arrayList) {
                nVO_BASELAND_RENT_MONTH = (NVO_BASELAND_RENT_MONTH)voBase;
                BaseLandVerifyUtil.checkBaselandNo(nVO_BASELAND_RENT_MONTH.getBaseno());
                BaseLandVerifyUtil.checkCity(nVO_BASELAND_RENT_MONTH.getCity());
                BaseLandVerifyUtil.checkYear(nVO_BASELAND_RENT_MONTH.getYear());
            }
        }
        catch (VerifyException verifyException) {
            verifyException.printStackTrace();
            throw new VerifyException("\u6536\u76ca\u6cd5\u8abf\u67e5\u4f30\u50f9\u8868-\u63a8\u4f30\u6708\u79df\u91d1");
        }
    }

    private void checkRentExt(ArrayList<VoBase> arrayList) throws VerifyException {
        try {
            NVO_BASELAND_RENT_EXT nVO_BASELAND_RENT_EXT = null;
            for (VoBase voBase : arrayList) {
                nVO_BASELAND_RENT_EXT = (NVO_BASELAND_RENT_EXT)voBase;
                BaseLandVerifyUtil.checkBaselandNo(nVO_BASELAND_RENT_EXT.getBaseno());
                BaseLandVerifyUtil.checkCity(nVO_BASELAND_RENT_EXT.getCity());
                BaseLandVerifyUtil.checkDist(nVO_BASELAND_RENT_EXT.getDist());
                BaseLandVerifyUtil.checkYear(nVO_BASELAND_RENT_EXT.getYear());
            }
        }
        catch (VerifyException verifyException) {
            verifyException.printStackTrace();
            throw new VerifyException("\u6210\u672c\u6cd5\u8abf\u67e5\u4f30\u50f9\u8868");
        }
    }

    private boolean checkDevelop(ArrayList<VoBase> arrayList) throws VerifyException {
        boolean bl = false;
        try {
            NVO_BASELAND_DEVELOP nVO_BASELAND_DEVELOP = null;
            for (VoBase voBase : arrayList) {
                nVO_BASELAND_DEVELOP = (NVO_BASELAND_DEVELOP)voBase;
                BaseLandVerifyUtil.checkBaselandNo(nVO_BASELAND_DEVELOP.getBaseno());
                BaseLandVerifyUtil.checkCity(nVO_BASELAND_DEVELOP.getCity());
                BaseLandVerifyUtil.checkYear(nVO_BASELAND_DEVELOP.getYear());
                if (nVO_BASELAND_DEVELOP.getAre_area() <= 0.0) {
                    throw new VerifyException();
                }
                bl = StringProcess.parserBoolean(nVO_BASELAND_DEVELOP.getIs_merge());
            }
        }
        catch (VerifyException verifyException) {
            verifyException.printStackTrace();
            throw new VerifyException("\u571f\u5730\u958b\u767c\u5206\u6790\u6cd5\u8abf\u67e5\u4f30\u50f9\u8868");
        }
        return bl;
    }

    private void checkDevelopExt(ArrayList<VoBase> arrayList) throws VerifyException {
        try {
            NVO_BASELAND_DEVELOP_EXT nVO_BASELAND_DEVELOP_EXT = null;
            for (VoBase voBase : arrayList) {
                nVO_BASELAND_DEVELOP_EXT = (NVO_BASELAND_DEVELOP_EXT)voBase;
                BaseLandVerifyUtil.checkBaselandNo(nVO_BASELAND_DEVELOP_EXT.getBaseno());
                BaseLandVerifyUtil.checkCity(nVO_BASELAND_DEVELOP_EXT.getCity());
                BaseLandVerifyUtil.checkYear(nVO_BASELAND_DEVELOP_EXT.getYear());
                if (!StringProcess.isEmpty(nVO_BASELAND_DEVELOP_EXT.getLand_position_pseudo()) && !(nVO_BASELAND_DEVELOP_EXT.getArea_pseudo() <= 0.0)) continue;
                throw new VerifyException();
            }
        }
        catch (VerifyException verifyException) {
            verifyException.printStackTrace();
            throw new VerifyException("\u571f\u5730\u958b\u767c\u5206\u6790\u6cd5\u8abf\u67e5\u4f30\u50f9\u8868-\u9644\u8868");
        }
    }

    private void checkImage(ArrayList<VoBase> arrayList) throws VerifyException {
        try {
            NVO_BASELAND_IMAGES nVO_BASELAND_IMAGES = null;
            for (VoBase voBase : arrayList) {
                nVO_BASELAND_IMAGES = (NVO_BASELAND_IMAGES)voBase;
                BaseLandVerifyUtil.checkBaselandNo(nVO_BASELAND_IMAGES.getBaseno());
                BaseLandVerifyUtil.checkCity(nVO_BASELAND_IMAGES.getCity());
                BaseLandVerifyUtil.checkDist(nVO_BASELAND_IMAGES.getDist());
                BaseLandVerifyUtil.checkYear(nVO_BASELAND_IMAGES.getYear());
                BaseLandVerifyUtil.checkPhotoTypeCode(nVO_BASELAND_IMAGES.getPhoto_type());
            }
        }
        catch (VerifyException verifyException) {
            verifyException.printStackTrace();
            throw new VerifyException("\u76f8\u7247");
        }
    }

    private void checkIndividual(ArrayList<VoBase> arrayList) throws VerifyException {
        try {
            NVO_BASELAND_INDIVIDUAL_FACTOR nVO_BASELAND_INDIVIDUAL_FACTOR = null;
            for (VoBase voBase : arrayList) {
                nVO_BASELAND_INDIVIDUAL_FACTOR = (NVO_BASELAND_INDIVIDUAL_FACTOR)voBase;
                BaseLandVerifyUtil.checkBaselandNo(nVO_BASELAND_INDIVIDUAL_FACTOR.getBaseno());
                BaseLandVerifyUtil.checkCity(nVO_BASELAND_INDIVIDUAL_FACTOR.getCity());
                BaseLandVerifyUtil.checkDist(nVO_BASELAND_INDIVIDUAL_FACTOR.getDist());
                BaseLandVerifyUtil.checkYear(nVO_BASELAND_INDIVIDUAL_FACTOR.getYear());
                BaseLandVerifyUtil.checkFactorVersion(nVO_BASELAND_INDIVIDUAL_FACTOR.getVersion());
            }
        }
        catch (VerifyException verifyException) {
            verifyException.printStackTrace();
            throw new VerifyException("\u500b\u5225\u56e0\u7d20\u57fa\u6e96\u8868");
        }
    }

    private void checkRegional(ArrayList<VoBase> arrayList) throws VerifyException {
        try {
            NVO_BASELAND_REGIONAL_FACTOR nVO_BASELAND_REGIONAL_FACTOR = null;
            for (VoBase voBase : arrayList) {
                nVO_BASELAND_REGIONAL_FACTOR = (NVO_BASELAND_REGIONAL_FACTOR)voBase;
                BaseLandVerifyUtil.checkBaselandNo(nVO_BASELAND_REGIONAL_FACTOR.getBaseno());
                BaseLandVerifyUtil.checkCity(nVO_BASELAND_REGIONAL_FACTOR.getCity());
                BaseLandVerifyUtil.checkDist(nVO_BASELAND_REGIONAL_FACTOR.getDist());
                BaseLandVerifyUtil.checkYear(nVO_BASELAND_REGIONAL_FACTOR.getYear());
                BaseLandVerifyUtil.checkFactorVersion(nVO_BASELAND_REGIONAL_FACTOR.getVersion());
            }
        }
        catch (VerifyException verifyException) {
            verifyException.printStackTrace();
            throw new VerifyException("\u5340\u57df\u56e0\u7d20\u57fa\u6e96\u8868");
        }
    }

    public void exportSucc(Connection connection) throws Exception {
        boolean bl;
        boolean bl2 = this.succMain(this.exportData.get("BASELAND_MAIN"), "BASELAND_MAIN");
        this.succAppraisalA3Score(this.exportData.get("BASELAND_APPRAISALA3_SCORE"), "BASELAND_APPRAISALA3_SCORE");
        this.succAppraisal(this.exportData.get("BASELAND_APPRAISAL"), "BASELAND_APPRAISAL");
        this.succSell(this.exportData.get("BASELAND_SELL"), "BASELAND_SELL");
        this.succRent(this.exportData.get("BASELAND_RENT"), "BASELAND_RENT");
        this.succRentMonth(this.exportData.get("BASELAND_RENT_MONTH"), "BASELAND_RENT_MONTH");
        if (bl2) {
            this.succRentExt(this.exportData.get("BASELAND_RENT_EXT"), "BASELAND_RENT_EXT");
        }
        if (bl = this.succDevelop(this.exportData.get("BASELAND_DEVELOP"), "BASELAND_DEVELOP")) {
            this.succDevelopExt(this.exportData.get("BASELAND_DEVELOP_EXT"), "BASELAND_DEVELOP_EXT");
        }
        this.succImage(this.exportData.get("BASELAND_IMAGES"), "BASELAND_IMAGES");
        this.succIndividual(this.exportData.get("BASELAND_INDIVIDUAL_FACTOR"), "BASELAND_INDIVIDUAL_FACTOR");
        this.succRegional(this.exportData.get("BASELAND_REGIONAL_FACTOR"), "BASELAND_REGIONAL_FACTOR");
        this.doSave(connection);
    }

    private boolean succMain(ArrayList<VoBase> arrayList, String string) throws Exception {
        boolean bl = false;
        NVO_BASELAND_MAIN nVO_BASELAND_MAIN = null;
        ArrayList<VoBase> arrayList2 = new ArrayList<VoBase>();
        for (VoBase voBase : arrayList) {
            try {
                nVO_BASELAND_MAIN = (NVO_BASELAND_MAIN)voBase;
                BaseLandVerifyUtil.checkBaselandNo(nVO_BASELAND_MAIN.getBaseno());
                BaseLandVerifyUtil.checkCity(nVO_BASELAND_MAIN.getCity());
                BaseLandVerifyUtil.checkDist(nVO_BASELAND_MAIN.getDist());
                BaseLandVerifyUtil.checkYear(nVO_BASELAND_MAIN.getYear());
                BaseLandVerifyUtil.checkAa48(nVO_BASELAND_MAIN.getAa48());
                if (StringProcess.isEmpty(nVO_BASELAND_MAIN.getAa49())) {
                    throw new VerifyException();
                }
            }
            catch (VerifyException verifyException) {
                continue;
            }
            arrayList2.add(voBase);
            bl = nVO_BASELAND_MAIN.getLand_scene().equals("01");
        }
        this.exportData.put(string, arrayList2);
        return bl;
    }

    private void succAppraisalA3Score(ArrayList<VoBase> arrayList, String string) throws Exception {
        NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE = null;
        ArrayList<VoBase> arrayList2 = new ArrayList<VoBase>();
        for (VoBase voBase : arrayList) {
            try {
                nVO_BASELAND_APPRAISALA3_SCORE = (NVO_BASELAND_APPRAISALA3_SCORE)voBase;
                BaseLandVerifyUtil.checkBaselandNo(nVO_BASELAND_APPRAISALA3_SCORE.getBaseno());
                BaseLandVerifyUtil.checkCity(nVO_BASELAND_APPRAISALA3_SCORE.getCity());
                BaseLandVerifyUtil.checkYear(nVO_BASELAND_APPRAISALA3_SCORE.getYear());
            }
            catch (VerifyException verifyException) {
                continue;
            }
            arrayList2.add(voBase);
        }
        this.exportData.put(string, arrayList2);
    }

    private void succAppraisal(ArrayList<VoBase> arrayList, String string) throws Exception {
        NVO_BASELAND_APPRAISAL nVO_BASELAND_APPRAISAL = null;
        ArrayList<VoBase> arrayList2 = new ArrayList<VoBase>();
        for (VoBase voBase : arrayList) {
            try {
                nVO_BASELAND_APPRAISAL = (NVO_BASELAND_APPRAISAL)voBase;
                BaseLandVerifyUtil.checkBaselandNo(nVO_BASELAND_APPRAISAL.getBaseno());
                BaseLandVerifyUtil.checkCity(nVO_BASELAND_APPRAISAL.getCity());
                BaseLandVerifyUtil.checkDist(nVO_BASELAND_APPRAISAL.getDist());
                BaseLandVerifyUtil.checkYear(nVO_BASELAND_APPRAISAL.getYear());
            }
            catch (VerifyException verifyException) {
                continue;
            }
            arrayList2.add(voBase);
        }
        this.exportData.put(string, arrayList2);
    }

    private void succSell(ArrayList<VoBase> arrayList, String string) throws Exception {
        NVO_BASELAND_SELL nVO_BASELAND_SELL = null;
        ArrayList<VoBase> arrayList2 = new ArrayList<VoBase>();
        for (VoBase voBase : arrayList) {
            try {
                nVO_BASELAND_SELL = (NVO_BASELAND_SELL)voBase;
                BaseLandVerifyUtil.checkBaselandNo(nVO_BASELAND_SELL.getBaseno());
                BaseLandVerifyUtil.checkCity(nVO_BASELAND_SELL.getCity());
                BaseLandVerifyUtil.checkDist(nVO_BASELAND_SELL.getDist());
                BaseLandVerifyUtil.checkYear(nVO_BASELAND_SELL.getYear());
                BaseLandVerifyUtil.checkAa48(nVO_BASELAND_SELL.getAa48());
                if (StringProcess.isEmpty(nVO_BASELAND_SELL.getAa49())) {
                    throw new VerifyException();
                }
            }
            catch (VerifyException verifyException) {
                continue;
            }
            arrayList2.add(voBase);
        }
        this.exportData.put(string, arrayList2);
    }

    private void succRent(ArrayList<VoBase> arrayList, String string) throws VerifyException {
        NVO_BASELAND_RENT nVO_BASELAND_RENT = null;
        ArrayList<VoBase> arrayList2 = new ArrayList<VoBase>();
        for (VoBase voBase : arrayList) {
            try {
                nVO_BASELAND_RENT = (NVO_BASELAND_RENT)voBase;
                BaseLandVerifyUtil.checkBaselandNo(nVO_BASELAND_RENT.getBaseno());
                BaseLandVerifyUtil.checkCity(nVO_BASELAND_RENT.getCity());
                BaseLandVerifyUtil.checkDist(nVO_BASELAND_RENT.getDist());
                BaseLandVerifyUtil.checkYear(nVO_BASELAND_RENT.getYear());
            }
            catch (VerifyException verifyException) {
                continue;
            }
            arrayList2.add(voBase);
        }
        this.exportData.put(string, arrayList2);
    }

    private void succRentMonth(ArrayList<VoBase> arrayList, String string) throws VerifyException {
        NVO_BASELAND_RENT_MONTH nVO_BASELAND_RENT_MONTH = null;
        ArrayList<VoBase> arrayList2 = new ArrayList<VoBase>();
        for (VoBase voBase : arrayList) {
            try {
                nVO_BASELAND_RENT_MONTH = (NVO_BASELAND_RENT_MONTH)voBase;
                BaseLandVerifyUtil.checkBaselandNo(nVO_BASELAND_RENT_MONTH.getBaseno());
                BaseLandVerifyUtil.checkCity(nVO_BASELAND_RENT_MONTH.getCity());
                BaseLandVerifyUtil.checkYear(nVO_BASELAND_RENT_MONTH.getYear());
            }
            catch (VerifyException verifyException) {
                continue;
            }
            arrayList2.add(voBase);
        }
        this.exportData.put(string, arrayList2);
    }

    private void succRentExt(ArrayList<VoBase> arrayList, String string) throws VerifyException {
        NVO_BASELAND_RENT_EXT nVO_BASELAND_RENT_EXT = null;
        ArrayList<VoBase> arrayList2 = new ArrayList<VoBase>();
        for (VoBase voBase : arrayList) {
            try {
                nVO_BASELAND_RENT_EXT = (NVO_BASELAND_RENT_EXT)voBase;
                BaseLandVerifyUtil.checkBaselandNo(nVO_BASELAND_RENT_EXT.getBaseno());
                BaseLandVerifyUtil.checkCity(nVO_BASELAND_RENT_EXT.getCity());
                BaseLandVerifyUtil.checkDist(nVO_BASELAND_RENT_EXT.getDist());
                BaseLandVerifyUtil.checkYear(nVO_BASELAND_RENT_EXT.getYear());
            }
            catch (VerifyException verifyException) {
                continue;
            }
            arrayList2.add(voBase);
        }
        this.exportData.put(string, arrayList2);
    }

    private boolean succDevelop(ArrayList<VoBase> arrayList, String string) throws VerifyException {
        boolean bl = false;
        NVO_BASELAND_DEVELOP nVO_BASELAND_DEVELOP = null;
        ArrayList<VoBase> arrayList2 = new ArrayList<VoBase>();
        for (VoBase voBase : arrayList) {
            try {
                nVO_BASELAND_DEVELOP = (NVO_BASELAND_DEVELOP)voBase;
                BaseLandVerifyUtil.checkBaselandNo(nVO_BASELAND_DEVELOP.getBaseno());
                BaseLandVerifyUtil.checkCity(nVO_BASELAND_DEVELOP.getCity());
                BaseLandVerifyUtil.checkYear(nVO_BASELAND_DEVELOP.getYear());
                if (nVO_BASELAND_DEVELOP.getAre_area() <= 0.0) {
                    throw new VerifyException();
                }
            }
            catch (VerifyException verifyException) {
                continue;
            }
            arrayList2.add(voBase);
            bl = StringProcess.parserBoolean(nVO_BASELAND_DEVELOP.getIs_merge());
        }
        this.exportData.put(string, arrayList2);
        return bl;
    }

    private void succDevelopExt(ArrayList<VoBase> arrayList, String string) throws VerifyException {
        NVO_BASELAND_DEVELOP_EXT nVO_BASELAND_DEVELOP_EXT = null;
        ArrayList<VoBase> arrayList2 = new ArrayList<VoBase>();
        for (VoBase voBase : arrayList) {
            try {
                nVO_BASELAND_DEVELOP_EXT = (NVO_BASELAND_DEVELOP_EXT)voBase;
                BaseLandVerifyUtil.checkBaselandNo(nVO_BASELAND_DEVELOP_EXT.getBaseno());
                BaseLandVerifyUtil.checkCity(nVO_BASELAND_DEVELOP_EXT.getCity());
                BaseLandVerifyUtil.checkYear(nVO_BASELAND_DEVELOP_EXT.getYear());
                if (StringProcess.isEmpty(nVO_BASELAND_DEVELOP_EXT.getLand_position_pseudo()) || nVO_BASELAND_DEVELOP_EXT.getArea_pseudo() <= 0.0) {
                    throw new VerifyException();
                }
            }
            catch (VerifyException verifyException) {
                continue;
            }
            arrayList2.add(voBase);
        }
        this.exportData.put(string, arrayList2);
    }

    private void succImage(ArrayList<VoBase> arrayList, String string) throws VerifyException {
        NVO_BASELAND_IMAGES nVO_BASELAND_IMAGES = null;
        ArrayList<VoBase> arrayList2 = new ArrayList<VoBase>();
        for (VoBase voBase : arrayList) {
            try {
                nVO_BASELAND_IMAGES = (NVO_BASELAND_IMAGES)voBase;
                BaseLandVerifyUtil.checkBaselandNo(nVO_BASELAND_IMAGES.getBaseno());
                BaseLandVerifyUtil.checkCity(nVO_BASELAND_IMAGES.getCity());
                BaseLandVerifyUtil.checkDist(nVO_BASELAND_IMAGES.getDist());
                BaseLandVerifyUtil.checkYear(nVO_BASELAND_IMAGES.getYear());
                BaseLandVerifyUtil.checkPhotoTypeCode(nVO_BASELAND_IMAGES.getPhoto_type());
            }
            catch (VerifyException verifyException) {
                continue;
            }
            arrayList2.add(voBase);
        }
        this.exportData.put(string, arrayList2);
    }

    private void succIndividual(ArrayList<VoBase> arrayList, String string) throws VerifyException {
        NVO_BASELAND_INDIVIDUAL_FACTOR nVO_BASELAND_INDIVIDUAL_FACTOR = null;
        ArrayList<VoBase> arrayList2 = new ArrayList<VoBase>();
        for (VoBase voBase : arrayList) {
            try {
                nVO_BASELAND_INDIVIDUAL_FACTOR = (NVO_BASELAND_INDIVIDUAL_FACTOR)voBase;
                BaseLandVerifyUtil.checkBaselandNo(nVO_BASELAND_INDIVIDUAL_FACTOR.getBaseno());
                BaseLandVerifyUtil.checkCity(nVO_BASELAND_INDIVIDUAL_FACTOR.getCity());
                BaseLandVerifyUtil.checkDist(nVO_BASELAND_INDIVIDUAL_FACTOR.getDist());
                BaseLandVerifyUtil.checkYear(nVO_BASELAND_INDIVIDUAL_FACTOR.getYear());
                BaseLandVerifyUtil.checkFactorVersion(nVO_BASELAND_INDIVIDUAL_FACTOR.getVersion());
            }
            catch (VerifyException verifyException) {
                continue;
            }
            arrayList2.add(voBase);
        }
        this.exportData.put(string, arrayList2);
    }

    private void succRegional(ArrayList<VoBase> arrayList, String string) throws VerifyException {
        NVO_BASELAND_REGIONAL_FACTOR nVO_BASELAND_REGIONAL_FACTOR = null;
        ArrayList<VoBase> arrayList2 = new ArrayList<VoBase>();
        for (VoBase voBase : arrayList) {
            try {
                nVO_BASELAND_REGIONAL_FACTOR = (NVO_BASELAND_REGIONAL_FACTOR)voBase;
                BaseLandVerifyUtil.checkBaselandNo(nVO_BASELAND_REGIONAL_FACTOR.getBaseno());
                BaseLandVerifyUtil.checkCity(nVO_BASELAND_REGIONAL_FACTOR.getCity());
                BaseLandVerifyUtil.checkDist(nVO_BASELAND_REGIONAL_FACTOR.getDist());
                BaseLandVerifyUtil.checkYear(nVO_BASELAND_REGIONAL_FACTOR.getYear());
                BaseLandVerifyUtil.checkFactorVersion(nVO_BASELAND_REGIONAL_FACTOR.getVersion());
            }
            catch (VerifyException verifyException) {
                continue;
            }
            arrayList2.add(voBase);
        }
        this.exportData.put(string, arrayList2);
    }
}

