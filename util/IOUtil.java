/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.io.IOUtils
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package com.wfusion.util;

import com.wfusion.util.FileList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IOUtil {
    private static final Logger log = LoggerFactory.getLogger(IOUtil.class);
    private boolean needInflater;
    private Deflater df = null;

    public IOUtil() {
        this.needInflater = true;
        this.df = this.getDeflater();
    }

    public IOUtil(boolean bl) {
        this.needInflater = bl;
        this.df = this.getDeflater();
    }

    public Deflater getDeflater() {
        if (this.df == null) {
            Deflater deflater = new Deflater();
            deflater.setLevel(9);
            this.df = deflater;
        }
        return this.df;
    }

    public void setDeflater(Deflater deflater) {
        this.df = deflater;
    }

    public void setNeedInflater(boolean bl) {
        this.needInflater = bl;
    }

    public OutputStream inflater(OutputStream outputStream) {
        if (this.needInflater) {
            return new DeflaterOutputStream(outputStream, this.getDeflater());
        }
        return outputStream;
    }

    public InputStream inflater(InputStream inputStream) {
        if (this.needInflater) {
            return new InflaterInputStream(inputStream);
        }
        return inputStream;
    }

    public ObjectOutputStream getObjectOutputStream(URLConnection uRLConnection) throws Exception {
        return new ObjectOutputStream(this.inflater(uRLConnection.getOutputStream()));
    }

    public ObjectOutputStream getObjectOutputStream(String string) throws Exception {
        return new ObjectOutputStream(this.inflater(new FileOutputStream(new File(string))));
    }

    public ObjectInputStream getObjectInputStream(URLConnection uRLConnection) throws Exception {
        return new ObjectInputStream(this.inflater(uRLConnection.getInputStream()));
    }

    public ObjectInputStream getObjectInputStream(String string) throws Exception {
        return new ObjectInputStream(this.inflater(new FileInputStream(new File(string))));
    }

    public static void close(OutputStream outputStream) {
        try {
            outputStream.close();
        }
        catch (Exception exception) {
            log.error(exception.getMessage());
        }
        finally {
            outputStream = null;
        }
    }

    public static void close(InputStream inputStream) {
        try {
            inputStream.close();
        }
        catch (Exception exception) {
            log.error(exception.getMessage());
        }
        finally {
            inputStream = null;
        }
    }

    public static Object getInstance(String string) throws Exception {
        Object var1_1 = null;
        try {
            var1_1 = Class.forName(string).newInstance();
        }
        catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
        return var1_1;
    }

    public static void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        IOUtils.copy((InputStream)inputStream, (OutputStream)outputStream);
    }

    public static void delDirAllFile(String string, boolean bl) {
        if (FileList.createDirOnNoneExist(string) != null) {
            File file = new File(string);
            IOUtil.delAllFile(string, file, bl);
        }
    }

    private static void delAllFile(String string, File file, boolean bl) {
        File[] fileArray = file.listFiles();
        for (int i = 0; i < fileArray.length; ++i) {
            if (fileArray[i].isFile()) {
                fileArray[i].delete();
            }
            if (!fileArray[i].isDirectory()) continue;
            IOUtil.delAllFile(string + "\\" + fileArray[i].getName(), fileArray[i], bl);
            if (!bl) continue;
            fileArray[i].delete();
        }
        if (bl) {
            file.delete();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public File outFile(byte[] byArray, String string) {
        FileOutputStream fileOutputStream = null;
        File file = null;
        try {
            fileOutputStream = new FileOutputStream(string);
            fileOutputStream.write(byArray);
            fileOutputStream.flush();
            file = new File(string);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            try {
                fileOutputStream.close();
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }
        return file;
    }
}

