/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.factor.em;

import java.util.LinkedHashMap;

public enum EnumFactorStdType {
    NUMERAL("\u6578\u503c\u578b", "NUMERAL"),
    SELECTION("\u6e05\u55ae\u578b", "SELECTION"),
    CUSTOM("\u81ea\u9078\u578b", "CUSTOM");

    private String description = "";
    private String code = "";

    private EnumFactorStdType(String string2, String string3) {
        this.description = string2;
        this.code = string3;
    }

    public String getDescription() {
        return this.description;
    }

    public String getCode() {
        return this.code;
    }

    public boolean isAllowNumeral() {
        return this.code.equals(EnumFactorStdType.NUMERAL.code);
    }

    public boolean isAllowSelection() {
        return this.code.equals(EnumFactorStdType.SELECTION.code);
    }

    public boolean isAllowCustom() {
        return true;
    }

    public static EnumFactorStdType findSelfByString(String string) {
        for (EnumFactorStdType enumFactorStdType : EnumFactorStdType.values()) {
            if (!enumFactorStdType.code.equals(string)) continue;
            return enumFactorStdType;
        }
        return null;
    }

    public static LinkedHashMap<String, String> toMap() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        for (EnumFactorStdType enumFactorStdType : EnumFactorStdType.values()) {
            linkedHashMap.put(enumFactorStdType.code, enumFactorStdType.description);
        }
        return linkedHashMap;
    }
}

