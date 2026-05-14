/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.factor.util;

import java.util.LinkedHashMap;

public enum EnumFactorComparison {
    EQUAL("=", "\u7b49\u65bc"),
    GREATER_THAN(">", "\u5927\u65bc"),
    GREATER_THAN_OR_EQUAL(">=", "\u5927\u65bc\u7b49\u65bc"),
    LESS_THAN("<", "\u5c0f\u65bc"),
    LESS_THAN_OR_EQUAL("<=", "\u5c0f\u65bc\u7b49\u65bc");

    private String description = "";
    private String code = "";

    private EnumFactorComparison(String string2, String string3) {
        this.code = string2;
        this.description = string3;
    }

    public String getDescription() {
        return this.description;
    }

    public String getCode() {
        return this.code;
    }

    public String toString() {
        return this.description;
    }

    public static EnumFactorComparison findSelfByString(String string) {
        for (EnumFactorComparison enumFactorComparison : EnumFactorComparison.values()) {
            if (!enumFactorComparison.code.equals(string)) continue;
            return enumFactorComparison;
        }
        return null;
    }

    public static LinkedHashMap<String, String> toMap() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        for (EnumFactorComparison enumFactorComparison : EnumFactorComparison.values()) {
            linkedHashMap.put(enumFactorComparison.code, enumFactorComparison.description);
        }
        return linkedHashMap;
    }
}

