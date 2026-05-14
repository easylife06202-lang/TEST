/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.util;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.imageio.ImageIO;

public class SUtility {
    private String office = "EE";
    private String SQL_IP = "192.168.1.196";
    private String REGD_IP = "192.168.1.188";

    public SUtility(String string) {
        this.office = string;
    }

    public SUtility(String string, String string2, String string3) {
        this.office = string;
        this.SQL_IP = string2;
        this.REGD_IP = string3;
    }

    public Connection getTaipriceConnection(String string) {
        Connection connection = null;
        try {
            String string2 = "jdbc:jtds:sqlserver://" + this.SQL_IP + ":1433/" + string + "_taiprice";
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connection = DriverManager.getConnection(string2, "sa", "9725200");
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return connection;
    }

    public Connection getMofficeConnection(String string) {
        Connection connection = null;
        try {
            String string2 = "jdbc:jtds:sqlserver://" + this.SQL_IP + ":1433/" + string + "_Moffice";
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connection = DriverManager.getConnection(string2, "sa", "9725200");
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return connection;
    }

    public Connection getCityMofficeConnection(String string) {
        Connection connection = null;
        try {
            String string2 = "jdbc:jtds:sqlserver://" + this.SQL_IP + ":1433/" + string + "0_LandValue";
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connection = DriverManager.getConnection(string2, "sa", "9725200");
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return connection;
    }

    public Connection getOracleConnection(String string) {
        Connection connection = null;
        try {
            String string2 = "jdbc:oracle:thin:@192.168.1.188:1521:" + string;
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(string2, "LANDVALUE", "LANDVALUE");
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return connection;
    }

    public Connection getDb2Connection(String string) {
        Connection connection = null;
        try {
            String string2 = "jdbc:db2://" + this.REGD_IP + ":50000/E0";
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            connection = DriverManager.getConnection(string2, "LANDVALUE", "LANDVALUE");
        }
        catch (Exception exception) {
            System.out.println("\u932f\u8aa4:" + string);
            exception.printStackTrace();
        }
        return connection;
    }

    public static Connection getSQLiteConnection(String string) {
        Connection connection = null;
        try {
            String string2 = "jdbc:sqlite:DBNAME";
            string2 = string2.replaceAll("DBNAME", string.replace('\\', '/'));
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(string2, "", "");
        }
        catch (Exception exception) {
            System.out.println(exception.toString());
        }
        return connection;
    }

    public Connection getDb2Connection() {
        return this.getDb2Connection(this.office);
    }

    public Connection getOracleConnection() {
        return this.getOracleConnection(this.office);
    }

    public Connection getTaipriceConnection() {
        return this.getTaipriceConnection(this.office);
    }

    public Connection getMofficeConnection() {
        return this.getMofficeConnection(this.office);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void saveByteImg(byte[] byArray, String string) {
        if (byArray == null || byArray.length <= 0) {
            return;
        }
        OutputStream outputStream = null;
        try {
            outputStream = new BufferedOutputStream(new FileOutputStream(string));
            outputStream.write(byArray);
        }
        catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
        finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                }
                catch (IOException iOException) {
                    iOException.printStackTrace();
                }
            }
        }
    }

    public void saveBufferedImage(BufferedImage bufferedImage, String string) {
        this.saveBufferedImage(bufferedImage, string, "png");
    }

    public void saveBufferedImage(BufferedImage bufferedImage, String string, String string2) {
        if (bufferedImage == null) {
            return;
        }
        try {
            File file = new File(string);
            ImageIO.write((RenderedImage)bufferedImage, string2, file);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public byte[] BufferedImageToByteArray(BufferedImage bufferedImage) {
        return this.BufferedImageToByteArray(bufferedImage, "png");
    }

    public byte[] BufferedImageToByteArray(BufferedImage bufferedImage, String string) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] byArray = null;
        try {
            ImageIO.write((RenderedImage)bufferedImage, string, byteArrayOutputStream);
            byteArrayOutputStream.flush();
            byArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
        }
        catch (Exception exception) {
            // empty catch block
        }
        return byArray;
    }

    public BufferedImage ByteArrayToBufferedImage(byte[] byArray) {
        BufferedImage bufferedImage = null;
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(byArray);
            bufferedImage = ImageIO.read(byteArrayInputStream);
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
        return bufferedImage;
    }
}

