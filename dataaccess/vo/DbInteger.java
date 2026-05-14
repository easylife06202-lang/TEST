/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.dataaccess.vo;

import com.wfusion.dataaccess.vo.DbElement;
import java.io.Serializable;
import java.text.DecimalFormat;

public class DbInteger
extends DbElement
implements Serializable,
Cloneable {
    private static final DecimalFormat df = new DecimalFormat("#0");
    private static final long serialVersionUID = -73522942550948298L;

    public DbInteger(String string, int n) {
        this.dataType = "Integer";
        this.setValue(new Integer(n));
        super.setName(string);
    }

    public DbInteger(String string) {
        this.dataType = "Integer";
        this.setValue(new Integer(0));
        super.setName(string);
    }

    @Override
    public Object clone() {
        DbInteger dbInteger = new DbInteger(this.getName(), this.getValue());
        dbInteger.dataType = this.dataType;
        dbInteger.pkFlag = this.pkFlag;
        dbInteger.pos = this.pos;
        dbInteger.searchFlag = this.searchFlag;
        dbInteger.updateFlag = this.updateFlag;
        return dbInteger;
    }

    public int getValue() {
        int n = 0;
        n = super.getMember() != null ? (super.getMember() instanceof Integer ? (Integer)super.getMember() : DbInteger.parserInt(super.getMember().toString())) : 0;
        return n;
    }

    public void setValue(int n) {
        super.setValue(new Integer(n));
    }

    @Override
    public String toString() {
        return df.format(this.getValue());
    }
}

