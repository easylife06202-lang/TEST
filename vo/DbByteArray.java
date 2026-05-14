/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.dataaccess.vo;

import com.wfusion.dataaccess.vo.DbElement;
import java.io.Serializable;

public class DbByteArray
extends DbElement
implements Serializable,
Cloneable {
    private static final long serialVersionUID = 4972680393805551833L;

    public DbByteArray(String string) {
        this.dataType = "Bytes";
        this.setValue(new byte[0]);
        super.setName(string);
    }

    public DbByteArray(String string, byte[] byArray) {
        this.dataType = "Bytes";
        this.setValue(byArray);
        super.setName(string);
    }

    @Override
    public Object clone() {
        super.clone();
        byte[] byArray = null;
        if (this.getValue() != null) {
            byArray = new byte[this.getValue().length];
            System.arraycopy(this.getValue(), 0, byArray, 0, byArray.length);
        }
        DbByteArray dbByteArray = new DbByteArray(this.getName(), byArray);
        dbByteArray.dataType = this.dataType;
        dbByteArray.pkFlag = this.pkFlag;
        dbByteArray.pos = this.pos;
        dbByteArray.searchFlag = this.searchFlag;
        dbByteArray.updateFlag = this.updateFlag;
        return dbByteArray;
    }

    public byte[] getValue() {
        return (byte[])super.getMember();
    }

    public void setValue(byte[] byArray) {
        super.setValue(byArray);
    }
}

