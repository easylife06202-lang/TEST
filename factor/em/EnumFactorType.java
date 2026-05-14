/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.factor.em;

public enum EnumFactorType {
    REGIONAL("\u5340\u57df\u56e0\u7d20", "FR"),
    INDIVIDUAL("\u500b\u5225\u56e0\u7d20", "FI");

    private String description = "";
    private String code = "";

    private EnumFactorType(String string2, String string3) {
        this.description = string2;
        this.code = string3;
    }

    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }
}

