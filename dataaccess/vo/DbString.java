/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.dataaccess.vo;

import com.wfusion.dataaccess.vo.DbElement;
import java.io.Serializable;

public class DbString
extends DbElement
implements Serializable,
Cloneable {
    private static final long serialVersionUID = 5462183178932075471L;

    public DbString(String string) {
        this.dataType = "String";
        this.setValue("");
        super.setName(string);
    }

    public DbString(String string, String string2) {
        this.dataType = "String";
        this.setValue(string2);
        super.setName(string);
    }

    @Override
    public Object clone() {
        DbString dbString = new DbString(this.getName(), this.getValue());
        dbString.dataType = this.dataType;
        dbString.pkFlag = this.pkFlag;
        dbString.pos = this.pos;
        dbString.searchFlag = this.searchFlag;
        dbString.updateFlag = this.updateFlag;
        return dbString;
    }

    public String getValue() {
        if (super.getMember() != null) {
            return String.valueOf(super.getMember());
        }
        return "";
    }

    public void setValue(String string) {
        super.setValue(string);
    }
}

