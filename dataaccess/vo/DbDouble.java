/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.dataaccess.vo;

import com.wfusion.dataaccess.vo.DbElement;
import java.io.Serializable;
import java.text.DecimalFormat;

public class DbDouble
extends DbElement
implements Serializable,
Cloneable {
    private static final DecimalFormat df = new DecimalFormat("#0.0###############");
    private static final long serialVersionUID = 2992117579420870612L;

    public DbDouble(String string) {
        this.dataType = "Double";
        this.setValue(new Double(0.0));
        super.setName(string);
    }

    public DbDouble(String string, double d) {
        this.dataType = "Double";
        this.setValue(new Double(d));
        super.setName(string);
    }

    @Override
    public Object clone() {
        DbDouble dbDouble = new DbDouble(this.getName(), this.getValue());
        dbDouble.dataType = this.dataType;
        dbDouble.pkFlag = this.pkFlag;
        dbDouble.pos = this.pos;
        dbDouble.searchFlag = this.searchFlag;
        dbDouble.updateFlag = this.updateFlag;
        return dbDouble;
    }

    public double getValue() {
        double d = 0.0;
        if (super.getMember() != null) {
            d = super.getMember() instanceof Double ? (Double)super.getMember() : DbDouble.parserDouble(super.getMember().toString());
        }
        return d;
    }

    public void setValue(double d) {
        super.setValue(new Double(d));
    }

    @Override
    public String toString() {
        return df.format(this.getValue());
    }
}

