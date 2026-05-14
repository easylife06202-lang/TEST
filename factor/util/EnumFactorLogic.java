/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.factor.util;

import java.util.LinkedHashMap;

public enum EnumFactorLogic {
    AND("\u4e14"),
    OR("\u6216");

    private String code = "";

    private EnumFactorLogic(String string2) {
        this.code = string2;
    }

    public String getCode() {
        return this.code;
    }

    public static EnumFactorLogic findSelfByString(String string) {
        for (EnumFactorLogic enumFactorLogic : EnumFactorLogic.values()) {
            if (!enumFactorLogic.code.equals(string)) continue;
            return enumFactorLogic;
        }
        return null;
    }

    public static LinkedHashMap<String, String> toMap() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        for (EnumFactorLogic enumFactorLogic : EnumFactorLogic.values()) {
            linkedHashMap.put(enumFactorLogic.code, enumFactorLogic.code);
        }
        return linkedHashMap;
    }
}

