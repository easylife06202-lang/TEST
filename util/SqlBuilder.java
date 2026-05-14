/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.util;

import com.wfusion.util.SQLInjectionEnum;
import com.wfusion.util.SQLInjectionFilterHelper;
import java.sql.SQLException;
import java.util.Collection;

public class SqlBuilder {
    private String[] tmpArray = null;
    private Object[] valueArray = null;
    public boolean printSql = false;
    private SQLInjectionEnum[] excludedArray = null;

    public static void main(String[] stringArray) {
        SqlBuilder sqlBuilder = new SqlBuilder();
        try {
            sqlBuilder.setPreSql("update vehlog01 set onlinecheck=@@ where recvtime is not null");
            sqlBuilder.setDouble(0, -99.0);
            String string = sqlBuilder.getSql();
            System.out.println("get sql=" + string);
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public SqlBuilder() {
    }

    public SqlBuilder(String string, boolean bl) {
        try {
            this.setPreSql(bl ? string.toUpperCase() : string);
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public SqlBuilder(String string) {
        this(string, false);
    }

    public void setPreSql(String string) throws SQLException {
        if (string == null || string.indexOf("@@") < 0) {
            throw new SQLException("Sql command is null or have not parameter! in SqlBuilder");
        }
        this.tmpArray = new String(string + " ").split("@@");
        this.valueArray = this.tmpArray.length == 1 ? new Object[1] : new Object[this.tmpArray.length - 1];
    }

    public void setInt(int n, int n2) throws SQLException {
        if (n >= this.valueArray.length) {
            throw new SQLException("Over parameter length! in SqlBuilder");
        }
        this.valueArray[n] = new Integer(n2);
    }

    public void setCollection(int n, Collection<?> collection) throws SQLException {
        if (n >= this.valueArray.length) {
            throw new SQLException("Over parameter length! in SqlBuilder");
        }
        this.valueArray[n] = collection;
    }

    public void setString(int n, String string) throws SQLException {
        if (n >= this.valueArray.length) {
            throw new SQLException("Over parameter length! in SqlBuilder");
        }
        this.valueArray[n] = string;
    }

    public void setString(int n, String string, boolean bl) throws SQLException {
        if (n >= this.valueArray.length) {
            throw new SQLException("Over parameter length! in SqlBuilder");
        }
        this.valueArray[n] = this.FilterSQLInjection(string, bl);
    }

    public String FilterSQLInjection(String string, boolean bl) {
        string = this.excludedArray != null ? SQLInjectionFilterHelper.cleanSQLInjection(string, this.excludedArray) : SQLInjectionFilterHelper.cleanSQLInjection(string);
        if (bl) {
            string = string.replaceAll("%", "");
        }
        return string;
    }

    public void setDouble(int n, double d) throws SQLException {
        if (n >= this.valueArray.length) {
            throw new SQLException("Over parameter length! in SqlBuilder");
        }
        this.valueArray[n] = new Double(d);
    }

    public String getSql() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < this.tmpArray.length - 1; ++i) {
            if (this.valueArray[i] == null) {
                stringBuffer.append(this.tmpArray[i]).append("").append(this.valueArray[i]);
                continue;
            }
            if (this.valueArray[i] instanceof String) {
                stringBuffer.append(this.tmpArray[i]).append("'").append(this.valueArray[i]).append("'");
                continue;
            }
            if (this.valueArray[i] instanceof Integer) {
                stringBuffer.append(this.tmpArray[i]).append("").append(this.valueArray[i]);
                continue;
            }
            if (this.valueArray[i] instanceof Double) {
                stringBuffer.append(this.tmpArray[i]).append("").append(this.valueArray[i]);
                continue;
            }
            if (!(this.valueArray[i] instanceof Collection)) continue;
            stringBuffer.append(this.tmpArray[i]).append("").append(this.getInString((Collection)this.valueArray[i]));
        }
        stringBuffer.append(this.tmpArray[this.tmpArray.length - 1]);
        if (this.printSql) {
            System.out.println(stringBuffer.toString());
        }
        return stringBuffer.toString();
    }

    public String toString() {
        return this.getSql();
    }

    public String getInString(Collection<?> collection) {
        StringBuffer stringBuffer = new StringBuffer();
        if (collection == null || collection.size() == 0) {
            stringBuffer.append("()");
        } else {
            stringBuffer.append('(');
            for (Object obj : collection) {
                if (obj instanceof String) {
                    stringBuffer.append("'").append(obj).append("',");
                    continue;
                }
                stringBuffer.append(obj).append(",");
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            stringBuffer.append(')');
        }
        return stringBuffer.toString();
    }

    public void setValueArray(Object[] objectArray, boolean bl) {
        this.valueArray = objectArray;
    }

    public SQLInjectionEnum[] getExcludedArray() {
        return this.excludedArray;
    }

    public void setExcludedArray(SQLInjectionEnum[] sQLInjectionEnumArray) {
        this.excludedArray = sQLInjectionEnumArray;
    }
}

