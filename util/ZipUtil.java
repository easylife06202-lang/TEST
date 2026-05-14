/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.tools.zip.ZipEntry
 *  org.apache.tools.zip.ZipFile
 *  org.apache.tools.zip.ZipOutputStream
 */
package com.wfusion.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

public class ZipUtil {
    public void makeZip(File file, File file2) throws IOException, FileNotFoundException {
        ZipOutputStream zipOutputStream = new ZipOutputStream((OutputStream)new FileOutputStream(file2));
        this.makeZip(file, zipOutputStream, "");
        zipOutputStream.close();
    }

    private void makeZip(File file, ZipOutputStream zipOutputStream, String string) throws IOException, FileNotFoundException {
        if (file.isDirectory()) {
            System.out.println("\u627e\u5230\u8cc7\u6599\u593e:" + file.getName());
            string = string + file.getName() + File.separator;
            String[] stringArray = file.list();
            if (stringArray != null) {
                for (int i = 0; i < stringArray.length; ++i) {
                    this.makeZip(new File(file, stringArray[i]), zipOutputStream, string);
                }
            }
        } else {
            int n;
            System.out.println("\u58d3\u7e2e\u6a94\u6848:" + file.getName());
            byte[] byArray = new byte[1024];
            string = string.substring(string.indexOf(File.separator) + 1);
            ZipEntry zipEntry = new ZipEntry(string + file.getName());
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            zipOutputStream.putNextEntry(zipEntry);
            while ((n = bufferedInputStream.read(byArray)) >= 0) {
                zipOutputStream.write(byArray, 0, n);
            }
            bufferedInputStream.close();
            zipOutputStream.closeEntry();
        }
    }

    public void unZip(File file, String string) throws Exception {
        try {
            ZipFile zipFile = new ZipFile(file);
            Enumeration enumeration = zipFile.getEntries();
            ZipEntry zipEntry = null;
            this.createDirectory(string, "");
            while (enumeration.hasMoreElements()) {
                int n;
                File file2;
                String string2;
                zipEntry = (ZipEntry)enumeration.nextElement();
                System.out.println("unziping " + zipEntry.getName());
                if (zipEntry.isDirectory()) {
                    string2 = zipEntry.getName();
                    string2 = string2.substring(0, string2.length() - 1);
                    file2 = new File(string + File.separator + string2);
                    file2.mkdir();
                    System.out.println("\u5275\u5efa\u7acb\u76ee\u9304\uff1a" + string + File.separator + string2);
                    continue;
                }
                string2 = zipEntry.getName();
                if ((string2 = string2.replace('\\', '/')).indexOf("/") != -1) {
                    this.createDirectory(string, string2.substring(0, string2.lastIndexOf("/")));
                    string2 = string2.substring(string2.lastIndexOf("/") + 1, string2.length());
                }
                file2 = new File(string + File.separator + zipEntry.getName());
                file2.createNewFile();
                InputStream inputStream = zipFile.getInputStream(zipEntry);
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                byte[] byArray = new byte[1024];
                while ((n = inputStream.read(byArray)) != -1) {
                    fileOutputStream.write(byArray, 0, n);
                }
                fileOutputStream.close();
                inputStream.close();
            }
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void createDirectory(String string, String string2) {
        File file = new File(string);
        try {
            if (string2 == "" && !file.exists()) {
                file.mkdir();
            } else if (string2 != "") {
                String[] stringArray = string2.replace('\\', '/').split("/");
                for (int i = 0; i < stringArray.length; ++i) {
                    File file2 = new File(string + File.separator + stringArray[i]);
                    if (!file2.exists()) {
                        file2.mkdir();
                    }
                    string = string + File.separator + stringArray[i];
                }
            }
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}

