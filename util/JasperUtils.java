/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.sf.jasperreports.engine.JRDataSource
 *  net.sf.jasperreports.engine.JasperCompileManager
 *  net.sf.jasperreports.engine.JasperFillManager
 *  net.sf.jasperreports.engine.JasperPrint
 *  net.sf.jasperreports.engine.JasperReport
 *  net.sf.jasperreports.engine.JasperRunManager
 *  net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
 *  net.sf.jasperreports.engine.design.JasperDesign
 *  net.sf.jasperreports.engine.export.JRXlsExporterParameter
 *  net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter
 *  net.sf.jasperreports.engine.fill.JRFileVirtualizer
 *  net.sf.jasperreports.engine.util.JRLoader
 *  net.sf.jasperreports.engine.xml.JRXmlLoader
 *  net.sf.jasperreports.view.save.JRDocxSaveContributor
 *  net.sf.jasperreports.view.save.JRRtfSaveContributor
 *  org.apache.pdfbox.io.RandomAccess
 *  org.apache.pdfbox.io.RandomAccessFile
 *  org.apache.pdfbox.pdmodel.PDDocument
 *  org.apache.pdfbox.util.PDFMergerUtility
 */
package com.wfusion.util;

import com.wfusion.util.BigDecimalUtil;
import com.wfusion.util.StringProcess;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.save.JRDocxSaveContributor;
import net.sf.jasperreports.view.save.JRRtfSaveContributor;
import org.apache.pdfbox.io.RandomAccess;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFMergerUtility;

public class JasperUtils {
    private static final int MEGABYTE = 0x100000;
    private List<InputStream> list = new ArrayList<InputStream>();
    private String disPdfFileName = "temp.pdf";

    public static void jrXmlToJasperFile(String string, String string2) throws Exception {
        JasperCompileManager.compileReportToFile((JasperDesign)JRXmlLoader.load((String)string), (String)string2);
    }

    public static JasperReport jrXmlToJasperReport(String string) throws Exception {
        if (string.indexOf(".jrxml") > 0) {
            return JasperCompileManager.compileReport((JasperDesign)JRXmlLoader.load((String)string));
        }
        return (JasperReport)JRLoader.loadObjectFromFile((String)string);
    }

    public static JasperReport jrXmlToJasperReport(InputStream inputStream) throws Exception {
        JasperDesign jasperDesign = JRXmlLoader.load((InputStream)inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport((JasperDesign)jasperDesign);
        return jasperReport;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static byte[] printPdfReport(String string, HashMap hashMap, Collection collection) {
        FilterOutputStream filterOutputStream = null;
        BufferedInputStream bufferedInputStream = null;
        byte[] byArray = null;
        try {
            JasperReport jasperReport = null;
            if (string.indexOf(".jasper") > 0) {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(string));
                jasperReport = (JasperReport)JRLoader.loadObject((InputStream)bufferedInputStream);
            } else {
                jasperReport = JasperUtils.jrXmlToJasperReport(string);
            }
            byArray = JasperRunManager.runReportToPdf((JasperReport)jasperReport, (Map)hashMap, (JRDataSource)new JRBeanCollectionDataSource(collection));
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            if (filterOutputStream != null) {
                try {
                    filterOutputStream.close();
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
        return byArray;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void printPdfFile(String string, HashMap hashMap, Collection collection, String string2) {
        FilterOutputStream filterOutputStream = null;
        BufferedInputStream bufferedInputStream = null;
        try {
            JasperReport jasperReport = null;
            byte[] byArray = new byte[]{};
            if (string.indexOf(".jasper") > 0) {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(string));
                jasperReport = (JasperReport)JRLoader.loadObject((InputStream)bufferedInputStream);
            } else {
                jasperReport = JasperUtils.jrXmlToJasperReport(string);
            }
            byArray = JasperRunManager.runReportToPdf((JasperReport)jasperReport, (Map)hashMap, (JRDataSource)new JRBeanCollectionDataSource(collection));
            FileOutputStream fileOutputStream = new FileOutputStream(string2);
            filterOutputStream = new BufferedOutputStream(fileOutputStream);
            filterOutputStream.write(byArray);
            ((BufferedOutputStream)filterOutputStream).flush();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            if (filterOutputStream != null) {
                try {
                    filterOutputStream.close();
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static JasperPrint createJasperPrint(String string, HashMap hashMap, Collection collection) {
        JasperPrint jasperPrint = null;
        try {
            JasperReport jasperReport = JasperUtils.jrXmlToJasperReport(string);
            jasperPrint = JasperFillManager.fillReport((JasperReport)jasperReport, (Map)hashMap, (JRDataSource)new JRBeanCollectionDataSource(collection));
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return jasperPrint;
    }

    public boolean haveMergerPdf() {
        return this.list.size() > 0;
    }

    public String getDisPdfFileName() {
        return this.disPdfFileName;
    }

    public void setDisPdfFileName(String string) {
        try {
            this.disPdfFileName = new String(string.getBytes(), "ISO8859-1") + ".pdf";
        }
        catch (UnsupportedEncodingException unsupportedEncodingException) {
            this.disPdfFileName = "temp.pdf";
        }
    }

    public void add(File file) {
        try {
            if (file != null) {
                this.list.add(new FileInputStream(file));
            }
        }
        catch (Exception exception) {
            System.out.println("Not Find File " + file.getName());
        }
    }

    public void add(byte[] byArray) {
        if (byArray != null && byArray.length != 0) {
            this.list.add(new ByteArrayInputStream(byArray));
        }
    }

    public byte[] getJasperBytes(String string, HashMap hashMap, Collection collection) {
        return this.getJasperBytes(string, hashMap, collection, null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public byte[] getJasperBytes(String string, HashMap hashMap, Collection collection, String string2) {
        byte[] byArray = new byte[]{};
        JRFileVirtualizer jRFileVirtualizer = null;
        try {
            JasperReport jasperReport = null;
            jasperReport = string.endsWith(".jasper") ? (JasperReport)JRLoader.loadObjectFromFile((String)string) : JasperUtils.jrXmlToJasperReport(string);
            if (!StringProcess.isEmpty(string2)) {
                jRFileVirtualizer = new JRFileVirtualizer(2, string2);
                hashMap.put("REPORT_VIRTUALIZER", jRFileVirtualizer);
                jRFileVirtualizer.setReadOnly(true);
            }
            byArray = JasperRunManager.runReportToPdf((JasperReport)jasperReport, (Map)hashMap, (JRDataSource)new JRBeanCollectionDataSource(collection));
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            if (jRFileVirtualizer != null) {
                jRFileVirtualizer.cleanup();
            }
        }
        return byArray;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private PDDocument getMergerPdfDocumentObj(File file) {
        PDFMergerUtility pDFMergerUtility = new PDFMergerUtility();
        boolean bl = false;
        PDDocument pDDocument = null;
        try {
            if (this.list.size() > 0) {
                String string = System.getProperty("java.io.tmpdir");
                if (this.list.size() == 1) {
                    InputStream inputStream = this.list.get(0);
                    pDDocument = PDDocument.load((InputStream)inputStream);
                } else {
                    long l = System.currentTimeMillis();
                    System.out.println("getMergerPdfDocumentObj()=" + string + "/" + file.getName() + ",list=" + this.list.size());
                    this.showTotalMemory("Start MergerPdf");
                    for (int i = 0; i < this.list.size(); ++i) {
                        InputStream inputStream = this.list.get(i);
                        try (PDDocument pDDocument2 = null;){
                            if (bl) {
                                pDDocument2 = PDDocument.load((InputStream)inputStream);
                                pDFMergerUtility.appendDocument(pDDocument, pDDocument2);
                                continue;
                            }
                            pDDocument = PDDocument.load((InputStream)inputStream, (RandomAccess)new RandomAccessFile(file, "rw"));
                            bl = true;
                            continue;
                        }
                    }
                    this.showTotalMemory("End MergerPdf");
                    System.out.println("\u4f5c\u696d\u5b8c\u6210\uff01\u5171\u8cbb\u6642\uff1a" + BigDecimalUtil.div(BigDecimalUtil.sub(System.currentTimeMillis(), l), 1000.0) + "\u79d2");
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return pDDocument;
    }

    private void showTotalMemory(String string) {
        System.out.print(string + ":");
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage();
        long l = memoryUsage.getMax() / 0x100000L;
        long l2 = memoryUsage.getUsed() / 0x100000L;
        double d = 0.0;
        if (l > 0L) {
            d = BigDecimalUtil.div(l2, l);
            d = BigDecimalUtil.round(d, 4);
            d = BigDecimalUtil.mul(d, 100.0);
        }
        System.out.println(" Memory Use :" + l2 + "M /" + l + "M ratio=" + d + "%");
    }

    private void close(PDDocument pDDocument) {
        if (pDDocument != null) {
            try {
                pDDocument.close();
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public void printMergerPdfFile() {
        this.printMergerPdfFile(this.getDisPdfFileName());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void printMergerPdfFile(String string) {
        block12: {
            File file = null;
            try {
                file = File.createTempFile("file", null);
                PDDocument pDDocument = this.getMergerPdfDocumentObj(file);
                if (pDDocument != null) {
                    try {
                        pDDocument.save(string);
                        break block12;
                    }
                    catch (Exception exception) {
                        exception.printStackTrace();
                        break block12;
                    }
                    finally {
                        this.close(pDDocument);
                    }
                }
                System.out.println("\u7121PDF\u53ef\u5408\u4f75\u5217\u5370!");
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
            finally {
                if (file.exists()) {
                    file.delete();
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void printPdfFile(InputStream inputStream, HashMap hashMap, Collection collection, String string) {
        FilterOutputStream filterOutputStream = null;
        BufferedInputStream bufferedInputStream = null;
        byte[] byArray = new byte[]{};
        try {
            byArray = JasperRunManager.runReportToPdf((JasperReport)JasperUtils.jrXmlToJasperReport(inputStream), (Map)hashMap, (JRDataSource)new JRBeanCollectionDataSource(collection));
            FileOutputStream fileOutputStream = new FileOutputStream(string);
            filterOutputStream = new BufferedOutputStream(fileOutputStream);
            filterOutputStream.write(byArray);
            ((BufferedOutputStream)filterOutputStream).flush();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            if (filterOutputStream != null) {
                try {
                    filterOutputStream.close();
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void printRtfFile(String string, HashMap hashMap, Collection collection, String string2) {
        BufferedInputStream bufferedInputStream = null;
        try {
            JasperReport jasperReport = null;
            if (string.indexOf(".jasper") > 0) {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(string));
                jasperReport = (JasperReport)JRLoader.loadObject((InputStream)bufferedInputStream);
            } else {
                jasperReport = JasperUtils.jrXmlToJasperReport(string);
            }
            JasperPrint jasperPrint = JasperFillManager.fillReport((JasperReport)jasperReport, (Map)hashMap, (JRDataSource)new JRBeanCollectionDataSource(collection));
            JRRtfSaveContributor jRRtfSaveContributor = new JRRtfSaveContributor(Locale.TAIWAN, null);
            jRRtfSaveContributor.save(jasperPrint, new File(string2));
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            try {
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void printDocxFile(String string, HashMap hashMap, Collection collection, String string2) {
        BufferedInputStream bufferedInputStream = null;
        try {
            JasperReport jasperReport = null;
            if (string.indexOf(".jasper") > 0) {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(string));
                jasperReport = (JasperReport)JRLoader.loadObject((InputStream)bufferedInputStream);
            } else {
                jasperReport = JasperUtils.jrXmlToJasperReport(string);
            }
            JasperPrint jasperPrint = JasperFillManager.fillReport((JasperReport)jasperReport, (Map)hashMap, (JRDataSource)new JRBeanCollectionDataSource(collection));
            JRDocxSaveContributor jRDocxSaveContributor = new JRDocxSaveContributor(Locale.TAIWAN, null);
            jRDocxSaveContributor.save(jasperPrint, new File(string2));
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            try {
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void printXlsxFile(String string, HashMap hashMap, Collection collection, String string2) {
        BufferedInputStream bufferedInputStream = null;
        try {
            JasperReport jasperReport = null;
            if (string.indexOf(".jasper") > 0) {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(string));
                jasperReport = (JasperReport)JRLoader.loadObject((InputStream)bufferedInputStream);
            } else {
                jasperReport = JasperUtils.jrXmlToJasperReport(string);
            }
            JasperPrint jasperPrint = JasperFillManager.fillReport((JasperReport)jasperReport, (Map)hashMap, (JRDataSource)new JRBeanCollectionDataSource(collection));
            JRXlsxExporter jRXlsxExporter = new JRXlsxExporter();
            jRXlsxExporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, (Object)jasperPrint);
            jRXlsxExporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, (Object)string2);
            jRXlsxExporter.exportReport();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            try {
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }
    }
}

