/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package com.wfusion.dataaccess.vo;

import com.wfusion.dataaccess.vo.DbDouble;
import com.wfusion.dataaccess.vo.DbElement;
import com.wfusion.dataaccess.vo.DbInteger;
import com.wfusion.dataaccess.vo.DbLong;
import com.wfusion.dataaccess.vo.DbString;
import com.wfusion.util.StringProcess;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VoBase
implements Serializable,
Cloneable {
    private static final long serialVersionUID = 3221023311963439839L;
    protected static Logger log = LoggerFactory.getLogger(VoBase.class);
    protected String tableName;
    protected DbElement[] elems;
    protected int fieldCount = 0;
    protected String orderString = "";
    public static String DEL = "delete";
    public static String UPD = "update";
    public static String ADD = "insert";
    public static String QRY = "select";
    public boolean unicode = false;

    public Object clone() {
        VoBase voBase = new VoBase();
        DbElement[] dbElementArray = new DbElement[this.elems.length];
        for (int i = 0; i < this.elems.length; ++i) {
            dbElementArray[i] = (DbElement)this.elems[i].clone();
        }
        voBase.elems = dbElementArray;
        voBase.fieldCount = this.fieldCount;
        voBase.orderString = this.orderString;
        voBase.tableName = this.tableName;
        return voBase;
    }

    public DbElement getElementAt(int n) {
        if (n < this.elems.length) {
            return this.elems[n];
        }
        return null;
    }

    public DbElement getElementAt(String string) {
        for (int i = 0; i < this.elems.length; ++i) {
            if (!this.elems[i].getName().equalsIgnoreCase(string)) continue;
            return this.elems[i];
        }
        return null;
    }

    public void resetUpdatFlag(boolean bl) {
        for (int i = 0; i < this.fieldCount; ++i) {
            this.elems[i].setUpdateFlag(bl);
        }
    }

    public void resetSearchFlag(boolean bl) {
        for (int i = 0; i < this.fieldCount; ++i) {
            this.elems[i].setSearchFlag(bl);
        }
    }

    public String getQuerySearchBatchingSql(String string, String string2) {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer.append("select * from ").append(this.tableName).append(" where ");
        for (int i = 0; i < this.elems.length; ++i) {
            if (!this.elems[i].isSearchFlag()) continue;
            stringBuffer.append(this.elems[i].getName()).append(string);
        }
        if (stringBuffer.indexOf(string) > 0) {
            stringBuffer2.append(stringBuffer.substring(0, stringBuffer.length() - string.length()));
        }
        stringBuffer2.append(" in ").append(string2);
        if (!this.orderString.equals("")) {
            stringBuffer2.append(' ').append("order by ").append(this.orderString);
        }
        log.debug(stringBuffer2.toString());
        return stringBuffer2.toString();
    }

    public String getQueryPkBatchingSql(String string, String string2) {
        int n;
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        for (n = 0; n < this.elems.length; ++n) {
            if (this.elems[n].isPk()) {
                this.elems[n].setSearchFlag(true);
                continue;
            }
            this.elems[n].setSearchFlag(false);
        }
        stringBuffer.append("select * from ").append(this.tableName).append(" where ");
        for (n = 0; n < this.elems.length; ++n) {
            if (!this.elems[n].isSearchFlag()) continue;
            stringBuffer.append(this.elems[n].getName()).append(string);
        }
        if (stringBuffer.indexOf(string) > 0) {
            stringBuffer2.append(stringBuffer.substring(0, stringBuffer.length() - string.length()));
        }
        stringBuffer2.append(" in ").append(string2);
        if (!this.orderString.equals("")) {
            stringBuffer2.append(' ').append("order by ").append(this.orderString);
        }
        log.debug(stringBuffer2.toString());
        return stringBuffer2.toString();
    }

    public String getQuerySql() {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer.append("select * from ").append(this.tableName).append(" where ");
        for (int i = 0; i < this.elems.length; ++i) {
            if (!this.elems[i].isSearchFlag()) continue;
            if (this.elems[i].getMember() == null) {
                stringBuffer.append(' ').append(this.elems[i].getName()).append(" is null ").append(" and ");
                continue;
            }
            if (this.elems[i].getType().equals("String")) {
                stringBuffer.append(this.elems[i].getName()).append(this.elems[i].useLikeKeyWord ? " LIKE " : " = ").append("'").append(this.elems[i].useLikeKeyWord ? "%" : "").append((String)this.elems[i].getMember()).append(this.elems[i].useLikeKeyWord ? "%" : "").append("'").append(" and ");
                continue;
            }
            if (this.elems[i].getType().equals("Long")) {
                stringBuffer.append(this.elems[i].getName()).append(" = ").append((Long)this.elems[i].getMember()).append(" and ");
                continue;
            }
            if (this.elems[i].getType().equals("Integer")) {
                stringBuffer.append(this.elems[i].getName()).append(" = ").append((Integer)this.elems[i].getMember()).append(" and ");
                continue;
            }
            if (!this.elems[i].getType().equals("Double")) continue;
            stringBuffer.append(this.elems[i].getName()).append(" = ").append((Double)this.elems[i].getMember()).append(" and ");
        }
        if (stringBuffer.indexOf("and") > 0) {
            stringBuffer2.append(stringBuffer.substring(0, stringBuffer.length() - 4));
        } else {
            stringBuffer2.append(stringBuffer.substring(0, stringBuffer.length() - 6));
        }
        if (!this.orderString.equals("")) {
            stringBuffer2.append(' ').append("order by ").append(this.orderString);
        }
        log.debug(stringBuffer2.toString());
        return stringBuffer2.toString();
    }

    public String getQueryPkSql() {
        for (int i = 0; i < this.elems.length; ++i) {
            if (this.elems[i].isPk()) {
                this.elems[i].setSearchFlag(true);
                continue;
            }
            this.elems[i].setSearchFlag(false);
        }
        log.debug(this.getQuerySql());
        return this.getQuerySql();
    }

    public String getInsertSql() {
        int n;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("insert into ").append(this.tableName).append(" (");
        for (n = 0; n < this.elems.length - 1; ++n) {
            stringBuffer.append(this.elems[n].getName()).append(',');
        }
        stringBuffer.append(this.elems[this.elems.length - 1].getName()).append(')');
        stringBuffer.append(" values (");
        for (n = 0; n < this.elems.length - 1; ++n) {
            if (this.elems[n].getType().equals("String")) {
                if (((DbString)this.elems[n]).getValue() != null) {
                    stringBuffer.append(this.unicode ? "N'" : "'").append(this.replaceSqlError(((DbString)this.elems[n]).getValue())).append("'");
                } else {
                    stringBuffer.append("null");
                }
            } else if (this.elems[n].getType().equals("Long")) {
                stringBuffer.append(((DbLong)this.elems[n]).getValue());
            } else if (this.elems[n].getType().equals("Integer")) {
                stringBuffer.append(((DbInteger)this.elems[n]).getValue());
            } else if (this.elems[n].getType().equals("Double")) {
                stringBuffer.append(((DbDouble)this.elems[n]).getValue());
            } else if (this.elems[n].getType().equals("Bytes")) {
                stringBuffer.append('?');
            }
            stringBuffer.append(',');
        }
        if (this.elems[this.elems.length - 1].getType().equals("String")) {
            if (((DbString)this.elems[this.elems.length - 1]).getValue() != null) {
                stringBuffer.append(this.unicode ? "N'" : "'").append(this.replaceSqlError(((DbString)this.elems[this.elems.length - 1]).getValue())).append("'");
            } else {
                stringBuffer.append("null");
            }
        } else if (this.elems[this.elems.length - 1].getType().equals("Long")) {
            stringBuffer.append(((DbLong)this.elems[this.elems.length - 1]).getValue());
        } else if (this.elems[this.elems.length - 1].getType().equals("Integer")) {
            stringBuffer.append(((DbInteger)this.elems[this.elems.length - 1]).getValue());
        } else if (this.elems[this.elems.length - 1].getType().equals("Double")) {
            stringBuffer.append(((DbDouble)this.elems[this.elems.length - 1]).getValue());
        } else if (this.elems[this.elems.length - 1].getType().equals("Bytes")) {
            stringBuffer.append('?');
        }
        stringBuffer.append(')');
        log.debug(stringBuffer.toString());
        return stringBuffer.toString();
    }

    public String getDeleteSql() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("delete from ").append(this.tableName).append(" where ");
        for (int i = 0; i < this.elems.length; ++i) {
            if (!this.elems[i].isSearchFlag()) continue;
            if (this.elems[i].getMember() == null) {
                stringBuffer.append(' ').append(this.elems[i].getName()).append(" is null ").append(" and ");
                continue;
            }
            if (this.elems[i].getType().equals("String")) {
                if (this.elems[i].useLikeKeyWord) {
                    stringBuffer.append(this.elems[i].getName()).append(" LIKE '%").append((String)this.elems[i].getMember()).append("%' and ");
                    continue;
                }
                stringBuffer.append(this.elems[i].getName()).append(" = ").append("'").append((String)this.elems[i].getMember()).append("'").append(" and ");
                continue;
            }
            if (this.elems[i].getType().equals("Long")) {
                stringBuffer.append(this.elems[i].getName()).append(" = ").append((Long)this.elems[i].getMember()).append(" and ");
                continue;
            }
            if (this.elems[i].getType().equals("Integer")) {
                stringBuffer.append(this.elems[i].getName()).append(" = ").append((Integer)this.elems[i].getMember()).append(" and ");
                continue;
            }
            if (!this.elems[i].getType().equals("Double")) continue;
            stringBuffer.append(this.elems[i].getName()).append(" = ").append((Double)this.elems[i].getMember()).append(" and ");
        }
        if (stringBuffer.indexOf(" and ") > 0) {
            log.debug(stringBuffer.substring(0, stringBuffer.length() - 4));
            return stringBuffer.substring(0, stringBuffer.length() - 4);
        }
        log.debug(stringBuffer.substring(0, stringBuffer.length() - 6));
        return stringBuffer.substring(0, stringBuffer.length() - 6);
    }

    public String getDeletePkSql() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("delete from ").append(this.tableName).append(" where ");
        for (int i = 0; i < this.elems.length; ++i) {
            if (!this.elems[i].isPk()) continue;
            stringBuffer.append(this.elems[i].getName()).append('=');
            if (this.elems[i].getType().equals("String")) {
                stringBuffer.append(this.unicode ? "N'" : "'").append(this.replaceSqlError(((DbString)this.elems[i]).getValue())).append("'");
            } else if (this.elems[i].getType().equals("Long")) {
                stringBuffer.append(((DbLong)this.elems[i]).getValue());
            } else if (this.elems[i].getType().equals("Integer")) {
                stringBuffer.append(((DbInteger)this.elems[i]).getValue());
            } else if (this.elems[i].getType().equals("Double")) {
                stringBuffer.append(((DbDouble)this.elems[i]).getValue());
            }
            stringBuffer.append(" and ");
        }
        log.debug(stringBuffer.substring(0, stringBuffer.length() - 4));
        return stringBuffer.substring(0, stringBuffer.length() - 4);
    }

    public String getUpdateSql() {
        int n;
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer.append("update ").append(this.tableName).append(" set ");
        for (n = 0; n < this.elems.length; ++n) {
            if (!this.elems[n].isUpdate() || this.elems[n].isPk()) continue;
            stringBuffer.append(this.elems[n].getName()).append('=');
            if (this.elems[n].getType().equals("String")) {
                if (((DbString)this.elems[n]).getValue() != null) {
                    stringBuffer.append("'").append(this.replaceSqlError(((DbString)this.elems[n]).getValue())).append("'");
                } else {
                    stringBuffer.append("").append(((DbString)this.elems[n]).getValue()).append("");
                }
            } else if (this.elems[n].getType().equals("Long")) {
                stringBuffer.append(((DbLong)this.elems[n]).getValue());
            } else if (this.elems[n].getType().equals("Integer")) {
                stringBuffer.append(((DbInteger)this.elems[n]).getValue());
            } else if (this.elems[n].getType().equals("Double")) {
                stringBuffer.append(((DbDouble)this.elems[n]).getValue());
            }
            stringBuffer.append(',');
        }
        stringBuffer2.append(stringBuffer.substring(0, stringBuffer.length() - 1));
        stringBuffer2.append(" where ");
        for (n = 0; n < this.elems.length; ++n) {
            if (!this.elems[n].isSearchFlag()) continue;
            if (this.elems[n].getMember() == null) {
                stringBuffer2.append(' ').append(this.elems[n].getName()).append(" is null ").append(" and ");
                continue;
            }
            if (this.elems[n].getType().equals("String")) {
                if (this.elems[n].useLikeKeyWord) {
                    stringBuffer2.append(this.elems[n].getName()).append(" LIKE '%").append((String)this.elems[n].getMember()).append("%' and ");
                    continue;
                }
                stringBuffer2.append(this.elems[n].getName()).append(" = ").append("'").append((String)this.elems[n].getMember()).append("'").append(" and ");
                continue;
            }
            if (this.elems[n].getType().equals("Long")) {
                stringBuffer2.append(this.elems[n].getName()).append(" = ").append((Long)this.elems[n].getMember()).append(" and ");
                continue;
            }
            if (this.elems[n].getType().equals("Integer")) {
                stringBuffer2.append(this.elems[n].getName()).append(" = ").append((Integer)this.elems[n].getMember()).append(" and ");
                continue;
            }
            if (!this.elems[n].getType().equals("Double")) continue;
            stringBuffer2.append(this.elems[n].getName()).append(" = ").append((Double)this.elems[n].getMember()).append(" and ");
        }
        if (stringBuffer2.indexOf("and") > 0) {
            log.debug(stringBuffer2.substring(0, stringBuffer2.length() - 4));
            return stringBuffer2.substring(0, stringBuffer2.length() - 4);
        }
        log.debug(stringBuffer2.substring(0, stringBuffer2.length() - 6));
        return stringBuffer2.substring(0, stringBuffer2.length() - 6);
    }

    public String replaceSqlError(String string) {
        if (string.indexOf("'") > -1) {
            string = string.replaceAll("'", "''");
        }
        if (string.indexOf("\"") > -1) {
            string = string.replaceAll("\"", "&\"");
        }
        return string;
    }

    public String getUpdatePkSql() {
        int n;
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer.append("update ").append(this.tableName).append(" set ");
        for (n = 0; n < this.elems.length; ++n) {
            if (!this.elems[n].isUpdate() || this.elems[n].isPk()) continue;
            stringBuffer.append(this.elems[n].getName()).append('=');
            if (this.elems[n].getType().equals("String")) {
                if (((DbString)this.elems[n]).getValue() != null) {
                    stringBuffer.append("'").append(this.replaceSqlError(((DbString)this.elems[n]).getValue())).append("'");
                } else {
                    stringBuffer.append("").append(((DbString)this.elems[n]).getValue()).append("");
                }
            } else if (this.elems[n].getType().equals("Long")) {
                stringBuffer.append(((DbLong)this.elems[n]).getValue());
            } else if (this.elems[n].getType().equals("Integer")) {
                stringBuffer.append(((DbInteger)this.elems[n]).getValue());
            } else if (this.elems[n].getType().equals("Double")) {
                stringBuffer.append(((DbDouble)this.elems[n]).getValue());
            } else if (this.elems[n].getType().equals("Bytes")) {
                stringBuffer.append('?');
            }
            stringBuffer.append(',');
        }
        stringBuffer2.append(stringBuffer.substring(0, stringBuffer.length() - 1));
        stringBuffer2.append(" where ");
        for (n = 0; n < this.elems.length; ++n) {
            if (!this.elems[n].isPk()) continue;
            stringBuffer2.append(this.elems[n].getName()).append('=');
            if (this.elems[n].getType().equals("String")) {
                stringBuffer2.append("'").append(this.replaceSqlError(((DbString)this.elems[n]).getValue())).append("'");
            } else if (this.elems[n].getType().equals("Long")) {
                stringBuffer2.append(((DbLong)this.elems[n]).getValue());
            } else if (this.elems[n].getType().equals("Integer")) {
                stringBuffer2.append(((DbInteger)this.elems[n]).getValue());
            } else if (this.elems[n].getType().equals("Double")) {
                stringBuffer2.append(((DbDouble)this.elems[n]).getValue());
            }
            stringBuffer2.append(" and ");
        }
        log.debug(stringBuffer2.substring(0, stringBuffer2.length() - 4));
        return stringBuffer2.substring(0, stringBuffer2.length() - 4);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
    }

    public String getTableName() {
        return this.tableName;
    }

    public int getFieldCount() {
        return this.fieldCount;
    }

    public String getOrderString() {
        return this.orderString;
    }

    public void setTableName(String string) {
        this.tableName = string;
    }

    public void setFieldCount(int n) {
        this.fieldCount = n;
    }

    public void setOrderString(String string) {
        this.orderString = string;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < this.elems.length; ++i) {
            stringBuffer.append(this.elems[i].getName()).append(" = ").append(this.elems[i].toString()).append("\n");
        }
        return stringBuffer.toString();
    }

    public VoBase copyBean(VoBase voBase, VoBase voBase2, HashMap<String, String> hashMap, StringBuffer stringBuffer) {
        DbElement dbElement;
        int n;
        String string = voBase.getTableName();
        DbElement[] dbElementArray = voBase.elems;
        DbElement[] dbElementArray2 = voBase2.elems;
        HashSet<String> hashSet = new HashSet<String>();
        for (n = 0; n < dbElementArray.length; ++n) {
            dbElement = dbElementArray[n];
            for (int i = 0; i < dbElementArray2.length; ++i) {
                DbElement dbElement2 = dbElementArray2[i];
                if (!dbElement.getName().equals(dbElement2.getName()) || hashSet.contains(dbElement.getName())) continue;
                if (dbElement2.getType().equals("String")) {
                    dbElement2.setValue(((DbString)dbElement).getValue());
                } else if (dbElement2.getType().equals("Long")) {
                    dbElement2.setValue(((DbLong)dbElement).getValue());
                } else if (dbElement2.getType().equals("Integer")) {
                    dbElement2.setValue(((DbInteger)dbElement).getValue());
                } else if (dbElement2.getType().equals("Double")) {
                    dbElement2.setValue(((DbDouble)dbElement).getValue());
                } else {
                    dbElement2.setValue(dbElement.toString());
                }
                hashSet.add(dbElement.getName());
            }
        }
        for (n = 0; n < dbElementArray.length; ++n) {
            dbElement = this.getElementAt(n);
            if (!hashSet.contains(dbElement.getName())) {
                if (!hashMap.containsKey(string + "." + dbElement.getName())) continue;
                DbElement dbElement3 = voBase2.getElementAt(String.valueOf(hashMap.get(string + "." + dbElement.getName())));
                if (dbElement3.getType().equals("String")) {
                    dbElement3.setValue(((DbString)dbElement).getValue());
                    continue;
                }
                if (dbElement3.getType().equals("Long")) {
                    dbElement3.setValue(((DbLong)dbElement).getValue());
                    continue;
                }
                if (dbElement3.getType().equals("Integer")) {
                    dbElement3.setValue(((DbInteger)dbElement).getValue());
                    continue;
                }
                if (dbElement3.getType().equals("Double")) {
                    dbElement3.setValue(((DbDouble)dbElement).getValue());
                    continue;
                }
                dbElement3.setValue(dbElement.toString());
                continue;
            }
            stringBuffer.append(string + "." + dbElement.getName() + "\u7121\u6cd5\u532f\u5165!<br>");
        }
        return voBase2;
    }

    public void setBeanByRequest(HashMap<?, ?> hashMap, boolean bl) {
        for (String string : hashMap.keySet()) {
            DbElement dbElement = this.getElementAt(string);
            if (dbElement == null) continue;
            String string2 = "";
            if (bl) {
                try {
                    string2 = new String(StringProcess.NULL((String)hashMap.get(string)).getBytes("ISO8859_1"), "MS950");
                }
                catch (Exception exception) {
                    System.out.println(exception);
                }
            } else {
                string2 = StringProcess.NULL((String)hashMap.get(string));
            }
            if (dbElement.getType().equals("String")) {
                dbElement.setValue(string2);
                continue;
            }
            if (dbElement.getType().equals("Long")) {
                dbElement.setValue(new Long(StringProcess.parserLong(string2)));
                continue;
            }
            if (dbElement.getType().equals("Integer")) {
                dbElement.setValue(new Integer(StringProcess.parserInt(string2)));
                continue;
            }
            if (dbElement.getType().equals("Double")) {
                dbElement.setValue(new Double(StringProcess.parserDouble(string2)));
                continue;
            }
            dbElement.setValue(string2);
        }
    }

    public String getPkString(String string) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(string);
        for (int i = 0; i < this.elems.length; ++i) {
            if (!this.elems[i].pkFlag) continue;
            if (this.elems[i].getType().equals("String")) {
                if (((DbString)this.elems[i]).getValue() != null) {
                    stringBuffer.append(((DbString)this.elems[i]).getValue().replaceAll("'", "''")).append(string);
                    continue;
                }
                stringBuffer.append("null").append(string);
                continue;
            }
            if (this.elems[i].getType().equals("Long")) {
                stringBuffer.append(((DbLong)this.elems[i]).getValue()).append(string);
                continue;
            }
            if (this.elems[i].getType().equals("Integer")) {
                stringBuffer.append(((DbInteger)this.elems[i]).getValue()).append(string);
                continue;
            }
            if (this.elems[i].getType().equals("Double")) {
                stringBuffer.append(((DbDouble)this.elems[i]).getValue()).append(string);
                continue;
            }
            if (!this.elems[i].getType().equals("Bytes")) continue;
            stringBuffer.append('?').append(string);
        }
        return stringBuffer.substring(string.length());
    }

    public String getExportCSV(ArrayList<String> arrayList, boolean bl) {
        String string = ",";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(string);
        DbElement dbElement = null;
        for (int i = 0; i < arrayList.size(); ++i) {
            dbElement = this.getElementAt(arrayList.get(i));
            if (dbElement == null) continue;
            if (dbElement.getType().equals("String")) {
                if (((DbString)dbElement).getValue() != null) {
                    if (bl) {
                        stringBuffer.append("\"").append(((DbString)dbElement).getValue()).append("\"").append(string);
                        continue;
                    }
                    stringBuffer.append(((DbString)dbElement).getValue()).append(string);
                    continue;
                }
                stringBuffer.append("null").append(string);
                continue;
            }
            if (dbElement.getType().equals("Long")) {
                stringBuffer.append(((DbLong)dbElement).getValue()).append(string);
                continue;
            }
            if (dbElement.getType().equals("Integer")) {
                stringBuffer.append(((DbInteger)dbElement).getValue()).append(string);
                continue;
            }
            if (dbElement.getType().equals("Double")) {
                stringBuffer.append(((DbDouble)dbElement).getValue()).append(string);
                continue;
            }
            if (!dbElement.getType().equals("Bytes")) continue;
            stringBuffer.append('?').append(string);
        }
        return stringBuffer.substring(string.length());
    }

    public String getExport() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < this.elems.length; ++i) {
            stringBuffer.append("|").append(this.elems[i].toString());
        }
        return stringBuffer.substring(1);
    }

    public HashMap<String, String> getHashMapExport() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        for (int i = 0; i < this.elems.length; ++i) {
            hashMap.put(this.elems[i].getName().toString(), this.elems[i].toString());
        }
        return hashMap;
    }

    public HashMap<String, Object> getFieldToHashMapExport() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        try {
            Class<?> clazz = this.getClass();
            HashSet<String> hashSet = new HashSet<String>();
            Method[] methodArray = clazz.getMethods();
            AccessibleObject[] accessibleObjectArray = methodArray;
            int n = accessibleObjectArray.length;
            for (int i = 0; i < n; ++i) {
                Method method = accessibleObjectArray[i];
                hashSet.add(method.getName());
            }
            for (AccessibleObject accessibleObject : accessibleObjectArray = clazz.getDeclaredFields()) {
                Object object;
                String string = ((Field)accessibleObject).getName();
                Method method = null;
                StringBuffer stringBuffer = new StringBuffer("get");
                stringBuffer.append(string.substring(0, 1).toUpperCase()).append(string.substring(1));
                if (!hashSet.contains(stringBuffer.toString())) continue;
                method = clazz.getMethod(stringBuffer.toString(), new Class[0]);
                if (((Field)accessibleObject).getType().getName().equals("java.lang.String")) {
                    object = String.valueOf(method.invoke((Object)this, new Object[0]));
                    object = ((String)object).replaceAll("'", "");
                    object = ((String)object).replaceAll("\"", " ");
                    object = ((String)object).replaceAll("\\r\\n", "\\\\r\\\\n");
                    hashMap.put(string, object);
                    continue;
                }
                if (((Field)accessibleObject).getType().getName().equals("double") || ((Field)accessibleObject).getType().getName().equals("java.lang.Double") || ((Field)accessibleObject).getType().getName().equals("com.wfusion.dataaccess.vo.DbDouble")) {
                    object = method.invoke((Object)this, new Object[0]);
                    BigDecimal bigDecimal = new BigDecimal(new Double((Double)object).toString());
                    hashMap.put(string, bigDecimal.toPlainString());
                    continue;
                }
                hashMap.put(string, method.invoke((Object)this, new Object[0]));
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return hashMap;
    }

    public void setBeanByBean(Object object, Object object2, HashMap<String, String> hashMap, HashSet<String> hashSet, boolean bl) {
        Class<?> clazz = object.getClass();
        Class<?> clazz2 = object2.getClass();
        Field[] fieldArray = clazz.getDeclaredFields();
        HashSet<String> hashSet2 = new HashSet<String>();
        Method[] methodArray = clazz2.getMethods();
        for (Method accessibleObject : methodArray) {
            hashSet2.add(accessibleObject.getName());
        }
        for (AccessibleObject accessibleObject : fieldArray) {
            String string = ((Field)accessibleObject).getName();
            if (hashSet.contains(string)) continue;
            try {
                Cloneable cloneable;
                Method exception = null;
                Method method = null;
                String string2 = StringProcess.setFustionString(string, bl);
                String string3 = StringProcess.getFustionString(string, bl);
                if (string.equals("serialVersionUID")) continue;
                boolean bl2 = false;
                if (hashSet2.contains(string2)) {
                    bl2 = true;
                    exception = clazz.getMethod(string3, new Class[0]);
                } else if (hashMap.containsKey(string)) {
                    string2 = StringProcess.setFustionString(hashMap.get(string), bl);
                    bl2 = true;
                }
                if (!bl2) continue;
                String string4 = ((Field)accessibleObject).getType().getName();
                exception = clazz.getMethod(string3, new Class[0]);
                method = string4.equals("java.lang.String") ? clazz2.getMethod(string2, String.class) : (string4.equals("double") ? clazz2.getMethod(string2, Double.TYPE) : (string4.equals("int") ? clazz2.getMethod(string2, Integer.TYPE) : (string4.equals("long") ? clazz2.getMethod(string2, Long.TYPE) : (string4.equals("boolean") ? clazz2.getMethod(string2, Boolean.TYPE) : (string4.equals("java.lang.Double") ? clazz2.getMethod(string2, Double.class) : (string4.equals("java.lang.Integer") ? clazz2.getMethod(string2, Integer.class) : (string4.equals("java.lang.Integer") ? clazz2.getMethod(string2, Integer.class) : (string4.equals("com.wfusion.dataaccess.vo.DbString") ? clazz2.getMethod(string2, String.class) : (string4.equals("com.wfusion.dataaccess.vo.DbDouble") ? clazz2.getMethod(string2, Double.TYPE) : (string4.equals("com.wfusion.dataaccess.vo.DbLong") ? clazz2.getMethod(string2, Long.TYPE) : (string4.equals("com.wfusion.dataaccess.vo.DbInteger") ? clazz2.getMethod(string2, Integer.TYPE) : null)))))))))));
                if (method != null) {
                    method.invoke(object2, exception.invoke(object, new Object[0]));
                    continue;
                }
                if (string4.equals("java.util.TreeMap")) {
                    method = clazz2.getMethod(string2, TreeMap.class);
                    cloneable = (TreeMap)exception.invoke(object, new Object[0]);
                    method.invoke(object2, ((TreeMap)cloneable).clone());
                    continue;
                }
                if (string4.equals("java.util.HashMap")) {
                    method = clazz2.getMethod(string2, HashMap.class);
                    cloneable = (HashMap)exception.invoke(object, new Object[0]);
                    method.invoke(object2, ((HashMap)cloneable).clone());
                    continue;
                }
                if (string4.equals("java.util.ArrayList")) {
                    method = clazz2.getMethod(string2, ArrayList.class);
                    cloneable = (ArrayList)exception.invoke(object, new Object[0]);
                    method.invoke(object2, ((ArrayList)cloneable).clone());
                    continue;
                }
                if (!string4.equals("java.util.HashSet")) continue;
                method = clazz2.getMethod(string2, HashSet.class);
                cloneable = (HashSet)exception.invoke(object, new Object[0]);
                method.invoke(object2, ((HashSet)cloneable).clone());
            }
            catch (NoSuchMethodException noSuchMethodException) {
                System.out.println(noSuchMethodException.toString());
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public void setBeanByBean(Object object, Object object2, HashMap<String, String> hashMap, HashSet<String> hashSet) {
        this.setBeanByBean(object, object2, hashMap, hashSet, true);
    }

    public void setBeanByBean(Object object, Object object2) {
        this.setBeanByBean(object, object2, new HashMap<String, String>(), new HashSet<String>(), true);
    }

    public void setBeanByBean(Object object, Object object2, boolean bl) {
        this.setBeanByBean(object, object2, new HashMap<String, String>(), new HashSet<String>(), bl);
    }

    public void resetPk2SearchFlag(boolean bl) {
        for (int i = 0; i < this.fieldCount; ++i) {
            if (!this.elems[i].isPk()) continue;
            this.elems[i].setSearchFlag(bl);
        }
    }

    public void setBeanByHashMap(HashMap<String, ? extends Object> hashMap, boolean bl) {
        Class<?> clazz = this.getClass();
        HashSet<String> hashSet = new HashSet<String>();
        Method[] methodArray = clazz.getMethods();
        for (Method method : methodArray) {
            hashSet.add(method.getName());
        }
        AccessibleObject[] accessibleObjectArray = clazz.getDeclaredFields();
        HashMap<String, AccessibleObject> hashMap2 = new HashMap<String, AccessibleObject>();
        for (AccessibleObject object : accessibleObjectArray) {
            hashMap2.put(((Field)object).getName(), object);
        }
        Iterator<String> iterator = hashMap.keySet().iterator();
        while (iterator.hasNext()) {
            try {
                String string = (String)iterator.next();
                String string2 = string.toLowerCase();
                String string3 = "set" + string2.substring(0, 1).toUpperCase() + string2.substring(1);
                Method method = null;
                if (!hashSet.contains(string3) || !hashMap2.containsKey(string2)) continue;
                Field field = (Field)hashMap2.get(string2);
                if (field.getType().getName().equals("java.lang.String") || field.getType().getName().equals("com.wfusion.dataaccess.vo.DbString")) {
                    method = this.getClass().getMethod(string3.toString(), String.class);
                    method.invoke((Object)this, String.valueOf(hashMap.get(string) == null ? "" : hashMap.get(string)));
                    continue;
                }
                if (field.getType().getName().equals("double") || field.getType().getName().equals("com.wfusion.dataaccess.vo.DbDouble")) {
                    method = this.getClass().getMethod(string3.toString(), Double.TYPE);
                    method.invoke((Object)this, StringProcess.parserDouble(hashMap.get(string).toString()));
                    continue;
                }
                if (field.getType().getName().equals("int") || field.getType().getName().equals("com.wfusion.dataaccess.vo.DbInteger")) {
                    method = this.getClass().getMethod(string3.toString(), Integer.TYPE);
                    method.invoke((Object)this, StringProcess.parserInt(hashMap.get(string).toString()));
                    continue;
                }
                if (!field.getType().getName().equals("long") && !field.getType().getName().equals("com.wfusion.dataaccess.vo.DbLong")) continue;
                method = this.getClass().getMethod(string3.toString(), Long.TYPE);
                method.invoke((Object)this, StringProcess.parserInt(hashMap.get(string).toString()));
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public String getDeletePkPreSql() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("delete from ").append(this.tableName).append(" where ");
        for (int i = 0; i < this.elems.length; ++i) {
            if (!this.elems[i].isPk()) continue;
            stringBuffer.append(this.elems[i].getName()).append("=?");
            stringBuffer.append(" and ");
        }
        log.debug(stringBuffer.substring(0, stringBuffer.length() - 4));
        return stringBuffer.substring(0, stringBuffer.length() - 4);
    }

    public boolean checkPkNotEmpty() {
        boolean bl = true;
        for (int i = 0; i < this.elems.length; ++i) {
            if (!this.elems[i].isPk()) continue;
            bl = !this.elems[i].toString().equals("") && bl;
        }
        return bl;
    }

    public void setBeanByStringArray(String[] stringArray) throws Exception {
        if (stringArray == null || this.elems.length != stringArray.length) {
            // empty if block
        }
        int n = 0;
        for (DbElement dbElement : this.elems) {
            dbElement.setValue(stringArray[n]);
            ++n;
        }
    }

    public void setSerrchFlags(String[] stringArray, boolean bl) {
        int n;
        int n2 = stringArray.length;
        try {
            for (n = 0; n < n2; ++n) {
                DbElement dbElement = this.getElementAt(stringArray[n]);
                if (bl) {
                    if (StringProcess.isEmpty(String.valueOf(dbElement.getObject()))) continue;
                    dbElement.setSearchFlag(true);
                    continue;
                }
                dbElement.setSearchFlag(true);
            }
        }
        catch (Exception exception) {
            System.out.println("VoBase: set search flag from " + stringArray[n] + " error.");
            exception.printStackTrace();
        }
    }

    public boolean isUnicode() {
        return this.unicode;
    }

    public void setUnicode(boolean bl) {
        this.unicode = bl;
    }

    public StringBuffer checkDataSize(TreeMap<String, String> treeMap) {
        StringBuffer stringBuffer = new StringBuffer("");
        for (int i = 0; i < this.elems.length; ++i) {
            Object object;
            if (!treeMap.containsKey(this.elems[i].getName())) continue;
            String string = treeMap.get(this.elems[i].getName());
            int n = 0;
            if (string.indexOf(",") > 0) {
                object = string.split(",");
                n = StringProcess.parserInt(object[0]);
            } else {
                n = StringProcess.parserInt(string);
            }
            object = "";
            if (this.elems[i].getType().equals("String")) {
                object = ((DbString)this.elems[i]).getValue();
            } else if (this.elems[i].getType().equals("Long")) {
                object = String.valueOf(((DbLong)this.elems[i]).getValue());
            } else if (this.elems[i].getType().equals("Integer")) {
                object = String.valueOf(((DbInteger)this.elems[i]).getValue());
            } else if (this.elems[i].getType().equals("Double")) {
                DecimalFormat decimalFormat = new DecimalFormat("####.###########");
                String string2 = decimalFormat.format(((DbDouble)this.elems[i]).getValue());
                object = string2.indexOf(".") > -1 ? string2.substring(0, string2.indexOf(".")) : string2;
            }
            if (((String)object).length() <= n) continue;
            if (stringBuffer.length() > 0) {
                stringBuffer.append(";");
            }
            stringBuffer.append(this.elems[i].getName() + "\u6b04\u4f4d\u9577\u5ea6" + ((String)object).length() + "\u8d85\u904e\u6a19\u6e96\u9577\u5ea6" + n);
        }
        return stringBuffer;
    }

    public ArrayList<String> getFields() {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < this.elems.length; ++i) {
            arrayList.add(this.elems[i].getName());
        }
        return arrayList;
    }
}

