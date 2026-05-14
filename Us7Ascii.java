/*
 * Decompiled with CFR 0.152.
 */
package moiland.landuse.util;

import java.util.ResourceBundle;

public class Us7Ascii {
    private static Us7Ascii us = null;
    private boolean bnlEncoding = false;
    private String dbKind = "ORACLE";
    private String candicate = "||";

    public static Us7Ascii getInstance() {
        return us;
    }

    public Us7Ascii(boolean bl) {
        this.bnlEncoding = bl;
    }

    private Us7Ascii() {
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("dbEncoding");
            this.bnlEncoding = resourceBundle.getString("encoding").equals("true");
            String string = this.dbKind = resourceBundle.getString("dbKind").equals("") || resourceBundle.getString("dbKind") == null ? "ORACLE" : resourceBundle.getString("dbKind");
            this.candicate = this.dbKind.equals("ORACLE") || this.dbKind.equals("DB2") ? "||" : (this.dbKind.equals("MSSQL") ? "+" : "||");
        }
        catch (Exception exception) {
            this.bnlEncoding = false;
            System.out.println("\u8b80\u53d6Encoding\u8a2d\u5b9a\u6a94\u5931\u6557\uff0c\u9810\u8a2d\u8f49\u6a94\u70baFALSE!");
        }
    }

    public boolean getEncoding() {
        return this.bnlEncoding;
    }

    public String getDbKind() {
        return this.dbKind;
    }

    public String getCandicate() {
        return this.candicate;
    }

    public String Encoding(String string) {
        try {
            if (this.bnlEncoding) {
                return new String(string.getBytes("MS950"), "ISO8859_1");
            }
            return new String(string);
        }
        catch (Exception exception) {
            return null;
        }
    }

    public String Decoding(String string) {
        try {
            if (this.bnlEncoding) {
                return new String(string.getBytes("ISO8859_1"), "MS950");
            }
            return new String(string);
        }
        catch (Exception exception) {
            return null;
        }
    }

    public boolean isBnlEncoding() {
        return this.bnlEncoding;
    }

    public void setBnlEncoding(boolean bl) {
        this.bnlEncoding = bl;
    }

    public void setDbKind(String string) {
        this.dbKind = string;
    }

    static {
        us = new Us7Ascii();
    }
}

