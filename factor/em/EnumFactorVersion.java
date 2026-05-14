/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.factor.em;

import java.util.LinkedHashMap;

public enum EnumFactorVersion {
    A3BA("A3", "\u4f4f\u5b85\u7248"),
    A3BB("A3", "\u5546\u696d\u7248"),
    A3BD("A3BD", "\u5de5\u696d\u7248"),
    A3BF("A3BF", "\u8fb2\u696d\u7248");

    private String description = "";
    private String factorCode = "";

    private EnumFactorVersion(String string2, String string3) {
        this.factorCode = string2;
        this.description = string3;
    }

    public String getFactorCode() {
        return this.factorCode;
    }

    public String getDescription() {
        return this.description;
    }

    public static EnumFactorVersion findSelfByString(String string) {
        for (EnumFactorVersion enumFactorVersion : EnumFactorVersion.values()) {
            if (!enumFactorVersion.toString().equals(string)) continue;
            return enumFactorVersion;
        }
        return null;
    }

    public static LinkedHashMap<String, String> toMap() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        for (EnumFactorVersion enumFactorVersion : EnumFactorVersion.values()) {
            linkedHashMap.put(enumFactorVersion.toString(), enumFactorVersion.getDescription());
        }
        return linkedHashMap;
    }
}

