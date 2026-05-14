/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.util;

import java.util.LinkedHashMap;
import moiland.baseland.factor.em.EnumFactorVersion;

public class BaseLandFactorVersionHelper {
    public static EnumFactorVersion getFactorVersionByString(String string, String string2) {
        EnumFactorVersion enumFactorVersion = BaseLandFactorVersionHelper.getFactorVersionByString(string);
        if (enumFactorVersion == null) {
            enumFactorVersion = BaseLandFactorVersionHelper.findSelfByUrban(string2);
        }
        return enumFactorVersion;
    }

    private static EnumFactorVersion getFactorVersionByString(String string) {
        EnumFactorVersion enumFactorVersion = EnumFactorVersion.findSelfByString(string);
        return enumFactorVersion;
    }

    private static EnumFactorVersion findSelfByUrban(String string) {
        if ("BA".equals(string)) {
            return EnumFactorVersion.A3BA;
        }
        if ("BB".equals(string)) {
            return EnumFactorVersion.A3BB;
        }
        if ("BD".equals(string)) {
            return EnumFactorVersion.A3BD;
        }
        if ("BF".equals(string) || "EE".equals(string)) {
            return EnumFactorVersion.A3BF;
        }
        return null;
    }

    public static LinkedHashMap<String, String> getAvailableListByUrban(String string) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        if (string.matches("^BA$")) {
            linkedHashMap.put(EnumFactorVersion.A3BA.toString(), EnumFactorVersion.A3BA.getDescription());
        } else if (string.matches("^BB$")) {
            linkedHashMap.put(EnumFactorVersion.A3BB.toString(), EnumFactorVersion.A3BB.getDescription());
        } else if (string.matches("^BD$")) {
            linkedHashMap.put(EnumFactorVersion.A3BD.toString(), EnumFactorVersion.A3BD.getDescription());
        } else if (string.matches("^BF|EE$")) {
            linkedHashMap.put(EnumFactorVersion.A3BF.toString(), EnumFactorVersion.A3BF.getDescription());
        } else if (string.matches("^BX$")) {
            for (EnumFactorVersion enumFactorVersion : EnumFactorVersion.values()) {
                linkedHashMap.put(enumFactorVersion.toString(), enumFactorVersion.getDescription());
            }
        } else {
            for (EnumFactorVersion enumFactorVersion : EnumFactorVersion.values()) {
                linkedHashMap.put(enumFactorVersion.toString(), enumFactorVersion.getDescription());
            }
        }
        return linkedHashMap;
    }
}

