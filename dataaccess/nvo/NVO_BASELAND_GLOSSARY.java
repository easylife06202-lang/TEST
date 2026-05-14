/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.dataaccess.nvo;

import com.wfusion.dataaccess.vo.DbElement;
import com.wfusion.dataaccess.vo.DbInteger;
import com.wfusion.dataaccess.vo.DbString;
import com.wfusion.dataaccess.vo.VoBase;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class NVO_BASELAND_GLOSSARY
extends VoBase
implements Serializable,
Cloneable {
    private static final long serialVersionUID = -1905473640L;
    private DbString code_0 = new DbString("code_0");
    private DbString code_1 = new DbString("code_1");
    private DbInteger sno = new DbInteger("sno");
    private DbString literal = new DbString("literal");
    private String nameOfCode_0;
    private String nameOfCode_1;

    public NVO_BASELAND_GLOSSARY() {
        this.tableName = "baseland_glossary";
        super.setFieldCount(4);
        this.elems = new DbElement[4];
        this.elems[0] = this.code_0;
        this.elems[1] = this.code_1;
        this.elems[2] = this.sno;
        this.elems[3] = this.literal;
        this.code_0.setPkFlag(true);
        this.code_1.setPkFlag(true);
        this.sno.setPkFlag(true);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
    }

    public String getCode_0() {
        return this.code_0.getValue();
    }

    public String getCode_1() {
        return this.code_1.getValue();
    }

    public int getSno() {
        return this.sno.getValue();
    }

    public String getLiteral() {
        return this.literal.getValue();
    }

    public void setCode_0(String string) {
        this.code_0.setValue(string);
    }

    public void setCode_1(String string) {
        this.code_1.setValue(string);
    }

    public void setSno(int n) {
        this.sno.setValue(n);
    }

    public void setLiteral(String string) {
        this.literal.setValue(string);
    }

    public String getHtmlEncodeLiteral() throws UnsupportedEncodingException {
        return URLEncoder.encode(this.literal.getValue().replaceAll("[%]", "%25").replaceAll("[ ]", "%20"), "utf-8");
    }

    @Override
    public Object clone() {
        NVO_BASELAND_GLOSSARY nVO_BASELAND_GLOSSARY = new NVO_BASELAND_GLOSSARY();
        DbElement[] dbElementArray = new DbElement[this.elems.length];
        nVO_BASELAND_GLOSSARY.code_0 = (DbString)this.code_0.clone();
        nVO_BASELAND_GLOSSARY.code_1 = (DbString)this.code_1.clone();
        nVO_BASELAND_GLOSSARY.sno = (DbInteger)this.sno.clone();
        nVO_BASELAND_GLOSSARY.literal = (DbString)this.literal.clone();
        dbElementArray[0] = nVO_BASELAND_GLOSSARY.code_0;
        dbElementArray[1] = nVO_BASELAND_GLOSSARY.code_1;
        dbElementArray[2] = nVO_BASELAND_GLOSSARY.sno;
        dbElementArray[3] = nVO_BASELAND_GLOSSARY.literal;
        nVO_BASELAND_GLOSSARY.elems = dbElementArray;
        nVO_BASELAND_GLOSSARY.fieldCount = this.fieldCount;
        nVO_BASELAND_GLOSSARY.orderString = this.orderString;
        nVO_BASELAND_GLOSSARY.tableName = this.tableName;
        return nVO_BASELAND_GLOSSARY;
    }

    public String getNameOfCode_0() {
        return this.nameOfCode_0;
    }

    public void setNameOfCode_0(String string) {
        this.nameOfCode_0 = string;
    }

    public String getNameOfCode_1() {
        return this.nameOfCode_1;
    }

    public void setNameOfCode_1(String string) {
        this.nameOfCode_1 = string;
    }
}

