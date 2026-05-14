/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.dataaccess.vo;

import com.wfusion.dataaccess.vo.DbElement;
import java.io.Serializable;
import java.text.DecimalFormat;

public class DbLong
extends DbElement
implements Serializable,
Cloneable {
    private static final long serialVersionUID = 3191770771485804366L;
    private static final DecimalFormat df = new DecimalFormat("#0");

    public DbLong(String string, long l) {
        this.dataType = "Long";
        this.setValue(new Long(l));
        super.setName(string);
    }

    public DbLong(String string) {
        this.dataType = "Long";
        this.setValue(new Long(0L));
        super.setName(string);
    }

    @Override
    public Object clone() {
        DbLong dbLong = new DbLong(this.getName(), this.getValue());
        dbLong.dataType = this.dataType;
        dbLong.pkFlag = this.pkFlag;
        dbLong.pos = this.pos;
        dbLong.searchFlag = this.searchFlag;
        dbLong.updateFlag = this.updateFlag;
        return dbLong;
    }

    public long getValue() {
        long l = 0L;
        l = super.getMember() != null ? (super.getMember() instanceof Long ? (Long)super.getMember() : DbLong.parserLong(super.getMember().toString())) : 0L;
        return l;
    }

    public void setValue(long l) {
        super.setValue(new Long(l));
    }

    @Override
    public String toString() {
        return df.format(this.getValue());
    }
}

