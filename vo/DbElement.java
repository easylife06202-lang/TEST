/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.dataaccess.vo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DbElement
implements Cloneable,
Serializable {
    private static final long serialVersionUID = 1349602723549446684L;
    public static final String LONG = "Long";
    public static final String INT = "Integer";
    public static final String DOUBLE = "Double";
    public static final String STRING = "String";
    public static final String BYTES = "Bytes";
    public static final String BINARY = "Object";
    protected String name;
    protected String dataType = "String";
    protected boolean pkFlag = false;
    protected Object member;
    protected boolean updateFlag = true;
    protected int pos = 0;
    protected boolean searchFlag = false;
    protected boolean useLikeKeyWord = false;

    public void setValue(Object object) {
        if (STRING.equals(this.dataType)) {
            this.member = object;
        } else if (DOUBLE.equals(this.dataType)) {
            this.member = new Double(DbElement.parserDouble(object.toString()));
        } else if (LONG.equals(this.dataType)) {
            this.member = new Long(DbElement.parserLong(object.toString()));
        } else if (INT.equals(this.dataType)) {
            this.member = new Integer(DbElement.parserInt(object.toString()));
        } else if (BYTES.equals(this.dataType)) {
            this.member = (byte[])object;
        } else if (BINARY.equals(this.dataType)) {
            this.member = object;
        }
    }

    public byte parserByte(String string) {
        byte by = 0;
        try {
            by = Byte.parseByte(string);
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
        return by;
    }

    public Object clone() {
        DbElement dbElement = new DbElement();
        dbElement.dataType = this.dataType;
        dbElement.name = this.name;
        if (this.member instanceof String) {
            dbElement.member = (String)this.member;
        } else if (this.member instanceof Double) {
            dbElement.member = new Double((Double)this.member);
        } else if (this.member instanceof Long) {
            dbElement.member = new Long((Long)this.member);
        } else if (this.member instanceof Integer) {
            dbElement.member = new Integer((Integer)this.member);
        } else if (this.member instanceof byte[]) {
            byte[] byArray = new byte[((byte[])this.member).length];
            System.arraycopy((byte[])this.member, 0, byArray, 0, byArray.length);
            dbElement.member = byArray;
        } else {
            dbElement.member = dbElement.clone();
        }
        dbElement.pkFlag = this.pkFlag;
        dbElement.pos = this.pos;
        dbElement.searchFlag = this.searchFlag;
        dbElement.updateFlag = this.updateFlag;
        return dbElement;
    }

    public Object getObject() {
        return this.member;
    }

    public int getPos() {
        return this.pos;
    }

    public void setPos(int n) {
        this.pos = n;
    }

    protected Object getMember() {
        return this.member;
    }

    public String getType() {
        return this.dataType;
    }

    public boolean isPk() {
        return this.pkFlag;
    }

    public boolean isUpdate() {
        return this.updateFlag;
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
    }

    public String getName() {
        return this.name;
    }

    public boolean isSearchFlag() {
        return this.searchFlag;
    }

    public boolean isUpdateFlag() {
        return this.updateFlag;
    }

    public boolean isUseLikeKeyWord() {
        return this.useLikeKeyWord;
    }

    protected void setName(String string) {
        this.name = string;
    }

    public void setPkFlag(boolean bl) {
        this.pkFlag = bl;
    }

    public void setSearchFlag(boolean bl) {
        this.searchFlag = bl;
    }

    public void setUpdateFlag(boolean bl) {
        this.updateFlag = bl;
    }

    public void setUseLikeKeyWord(boolean bl) {
        this.useLikeKeyWord = bl;
    }

    public String toString() {
        if (this.member == null) {
            return "DbElement NULL";
        }
        if (this.member instanceof String) {
            return (String)this.member;
        }
        if (this.member instanceof Double) {
            return ((Double)this.member).toString();
        }
        if (this.member instanceof Long) {
            return ((Long)this.member).toString();
        }
        if (this.member instanceof Integer) {
            return ((Integer)this.member).toString();
        }
        if (this.member instanceof byte[]) {
            return "byte size=" + ((byte[])this.member).length;
        }
        return "unknow type/binary:" + this.member.toString();
    }

    public static double parserDouble(String string, double d) {
        double d2 = d;
        if (string != null && !string.trim().equals("")) {
            try {
                d2 = Double.parseDouble(string);
            }
            catch (Exception exception) {
                System.out.println("StringProcess.parserDouble have error:" + string);
            }
        }
        return d2;
    }

    public static double parserDouble(String string) {
        return DbElement.parserDouble(string, 0.0);
    }

    public static int parserInt(String string, int n) {
        int n2 = n;
        double d = DbElement.parserDouble(string, n);
        n2 = (int)d;
        return n2;
    }

    public static int parserInt(String string) {
        return DbElement.parserInt(string, 0);
    }

    public static long parserLong(String string) {
        return DbElement.parserLong(string, 0L);
    }

    public static long parserLong(String string, long l) {
        long l2 = l;
        double d = DbElement.parserDouble(string, l);
        l2 = (long)d;
        return l2;
    }

    public void reset() {
        if (STRING.equals(this.dataType)) {
            this.member = "";
        } else if (DOUBLE.equals(this.dataType)) {
            this.member = new Double(0.0);
        } else if (LONG.equals(this.dataType)) {
            this.member = new Long(0L);
        } else if (INT.equals(this.dataType)) {
            this.member = new Integer(0);
        } else if (BYTES.equals(this.dataType)) {
            this.member = new byte[0];
        } else if (BINARY.equals(this.dataType)) {
            this.member = new Object();
        }
    }
}

