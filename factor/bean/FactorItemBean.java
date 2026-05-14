/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.factor.bean;

import moiland.baseland.factor.em.EnumFactorStdType;

public class FactorItemBean {
    private String itemCode = "";
    private String itemText = "";
    private int itemSn = 0;
    private String itemField = "";
    private EnumFactorStdType stdType = null;
    private String stdUnit = "";

    public FactorItemBean(String string, String string2, int n, String string3, String string4, String string5) {
        this.itemCode = string;
        this.itemText = string2;
        this.itemSn = n;
        this.itemField = string3;
        this.stdType = EnumFactorStdType.findSelfByString(string4);
        this.stdUnit = string5;
    }

    public String getItemCode() {
        return this.itemCode;
    }

    public void setItemCode(String string) {
        this.itemCode = string;
    }

    public String getItemText() {
        return this.itemText;
    }

    public void setItemText(String string) {
        this.itemText = string;
    }

    public int getItemSn() {
        return this.itemSn;
    }

    public void setItemSn(int n) {
        this.itemSn = n;
    }

    public String getItemField() {
        return this.itemField;
    }

    public void setItemField(String string) {
        this.itemField = string;
    }

    public EnumFactorStdType getStdType() {
        return this.stdType;
    }

    public void setStdType(String string) {
        this.stdType = EnumFactorStdType.findSelfByString(string);
    }

    public String getStdUnit() {
        return this.stdUnit;
    }

    public void setStdUnit(String string) {
        this.stdUnit = string;
    }

    public boolean isAutoSwitchLevel() {
        return EnumFactorStdType.NUMERAL == this.stdType || EnumFactorStdType.SELECTION == this.stdType;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append(this.itemCode);
        stringBuilder.append(", ");
        stringBuilder.append(this.itemField);
        stringBuilder.append(", ");
        stringBuilder.append(this.itemSn);
        stringBuilder.append(", ");
        stringBuilder.append(this.itemText);
        stringBuilder.append(", ");
        stringBuilder.append((Object)this.stdType);
        stringBuilder.append(", ");
        stringBuilder.append(this.stdUnit);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public FactorItemBean clone() {
        FactorItemBean factorItemBean = new FactorItemBean(this.itemCode, this.itemText, this.itemSn, this.itemField, this.stdType.getCode(), this.stdUnit);
        return factorItemBean;
    }
}

