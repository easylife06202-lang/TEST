/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.baseland.system;

import com.wfusion.baseland.basic.Model;
import com.wfusion.util.OptionPair;
import com.wfusion.util.SUtility;
import com.wfusion.util.SqlUtil;
import java.sql.Connection;
import java.util.ArrayList;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_GLOSSARY;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_GLOSSARY;
import moiland.baseland.glossary.em.EnumGlossaryField;
import moiland.baseland.glossary.em.EnumGlossaryType;

public class GlossaryModel
extends Model {
    ArrayList<NVO_BASELAND_GLOSSARY> listData = new ArrayList();
    String code0 = "";
    String code1 = "";
    int sno = 0;
    String literal = "";

    public ArrayList<NVO_BASELAND_GLOSSARY> query() {
        return this.query(this.code0, this.code1, this.sno, this.literal);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ArrayList<NVO_BASELAND_GLOSSARY> query(String string, String string2, int n, String string3) {
        Connection connection = null;
        try {
            connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
            this.listData = new NDAO_BASELAND_GLOSSARY().query(string, string2, n, string3, connection);
            for (NVO_BASELAND_GLOSSARY nVO_BASELAND_GLOSSARY : this.listData) {
                nVO_BASELAND_GLOSSARY.setNameOfCode_0(EnumGlossaryType.valueOf(nVO_BASELAND_GLOSSARY.getCode_0()).getDescription());
                nVO_BASELAND_GLOSSARY.setNameOfCode_1(EnumGlossaryField.valueOf(nVO_BASELAND_GLOSSARY.getCode_1()).getDescription());
            }
            SqlUtil.close(connection);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
        return this.listData;
    }

    public ArrayList<NVO_BASELAND_GLOSSARY> getListData() {
        return this.listData;
    }

    public void setListData(ArrayList<NVO_BASELAND_GLOSSARY> arrayList) {
        this.listData = arrayList;
    }

    public ArrayList<OptionPair> getDataOptionpairs() {
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        arrayList.add(new OptionPair("", "\u8acb\u9078\u64c7..."));
        for (NVO_BASELAND_GLOSSARY nVO_BASELAND_GLOSSARY : this.listData) {
            arrayList.add(new OptionPair(nVO_BASELAND_GLOSSARY.getCode_0() + "," + nVO_BASELAND_GLOSSARY.getCode_1() + "," + nVO_BASELAND_GLOSSARY.getSno(), nVO_BASELAND_GLOSSARY.getLiteral()));
        }
        return arrayList;
    }

    public ArrayList<OptionPair> getTypeList() {
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        arrayList.add(new OptionPair("", "\u8acb\u9078\u64c7..."));
        for (EnumGlossaryType enumGlossaryType : EnumGlossaryType.values()) {
            if (EnumGlossaryType.RENT.getCode().equals(enumGlossaryType.getCode())) continue;
            arrayList.add(new OptionPair(enumGlossaryType.getCode(), enumGlossaryType.getDescription()));
        }
        return arrayList;
    }

    public ArrayList<OptionPair> getFieldList(String string) {
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        arrayList.add(new OptionPair("", "\u8acb\u9078\u64c7..."));
        for (EnumGlossaryField enumGlossaryField : EnumGlossaryField.values()) {
            if (!string.equals(enumGlossaryField.getType().getCode())) continue;
            arrayList.add(new OptionPair(enumGlossaryField.toString(), enumGlossaryField.getDescription()));
        }
        return arrayList;
    }

    public String getCode0() {
        return this.code0;
    }

    public void setCode0(String string) {
        this.code0 = string;
    }

    public String getCode1() {
        return this.code1;
    }

    public void setCode1(String string) {
        this.code1 = string;
    }

    public int getSno() {
        return this.sno;
    }

    public void setSno(int n) {
        this.sno = n;
    }

    public String getLiteral() {
        return this.literal;
    }

    public void setLiteral(String string) {
        this.literal = string;
    }

    public void addLiteral(String string) {
        this.addLiteral(string, this.code0, this.code1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void addLiteral(String string, String string2, String string3) {
        Connection connection = null;
        try {
            NDAO_BASELAND_GLOSSARY nDAO_BASELAND_GLOSSARY = new NDAO_BASELAND_GLOSSARY();
            connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
            NVO_BASELAND_GLOSSARY nVO_BASELAND_GLOSSARY = new NVO_BASELAND_GLOSSARY();
            nVO_BASELAND_GLOSSARY.setCode_0(string2);
            nVO_BASELAND_GLOSSARY.setCode_1(string3);
            nVO_BASELAND_GLOSSARY.setLiteral(string);
            if (nDAO_BASELAND_GLOSSARY.checkExist(nVO_BASELAND_GLOSSARY, connection)) {
                nDAO_BASELAND_GLOSSARY.update(nVO_BASELAND_GLOSSARY, connection);
            } else {
                int n = nDAO_BASELAND_GLOSSARY.getNextSno(nVO_BASELAND_GLOSSARY, connection);
                nVO_BASELAND_GLOSSARY.setSno(n);
                nDAO_BASELAND_GLOSSARY.create(nVO_BASELAND_GLOSSARY, connection);
            }
            SqlUtil.close(connection);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void update(NVO_BASELAND_GLOSSARY nVO_BASELAND_GLOSSARY) {
        Connection connection = null;
        try {
            connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
            new NDAO_BASELAND_GLOSSARY().update(nVO_BASELAND_GLOSSARY, connection);
            SqlUtil.close(connection);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void delete(NVO_BASELAND_GLOSSARY nVO_BASELAND_GLOSSARY) {
        Connection connection = null;
        try {
            connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
            new NDAO_BASELAND_GLOSSARY().delete(nVO_BASELAND_GLOSSARY, connection);
            SqlUtil.close(connection);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void glossaryAdd(String string, String string2, String string3) {
        Connection connection = null;
        try {
            NDAO_BASELAND_GLOSSARY nDAO_BASELAND_GLOSSARY = new NDAO_BASELAND_GLOSSARY();
            connection = SUtility.getSQLiteConnection(SQLITE_PATH + "BaseLand.db");
            int n = nDAO_BASELAND_GLOSSARY.getNextSno(string, string2, connection);
            NVO_BASELAND_GLOSSARY nVO_BASELAND_GLOSSARY = new NVO_BASELAND_GLOSSARY();
            nVO_BASELAND_GLOSSARY.setCode_0(string);
            nVO_BASELAND_GLOSSARY.setCode_1(string2);
            nVO_BASELAND_GLOSSARY.setSno(n);
            nVO_BASELAND_GLOSSARY.setLiteral(string3);
            nDAO_BASELAND_GLOSSARY.create(nVO_BASELAND_GLOSSARY, connection);
            this.listData.add(nVO_BASELAND_GLOSSARY);
            SqlUtil.close(connection);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
    }
}

