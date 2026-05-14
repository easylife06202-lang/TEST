/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.em;

import java.util.LinkedHashMap;

public enum EnumBaseLandUrban {
    BA("\u4f4f\u5b85\u5340"),
    BB("\u5546\u696d\u5340"),
    BD("\u5de5\u696d\u5340"),
    BF("\u8fb2\u696d\u5340"),
    BX("\u8fb2\u696d\u5340\u5efa\u5730\u76ee"),
    BH("\u4fdd\u8b77\u5340"),
    BP("\u7279\u5b9a\u5c08\u7528\u5340"),
    BZ("\u5176\u4ed6\u4f7f\u7528\u5340"),
    AD("\u5de5\u696d\u5340"),
    AH("\u7279\u5b9a\u5c08\u7528\u5340"),
    AJ("\u570b\u5bb6\u516c\u5712\u5340"),
    EA("\u7532\u7a2e\u5efa\u7bc9\u7528\u5730"),
    EB("\u4e59\u7a2e\u5efa\u7bc9\u7528\u5730"),
    EC("\u4e19\u7a2e\u5efa\u7bc9\u7528\u5730"),
    ED("\u4e01\u7a2e\u5efa\u7bc9\u7528\u5730"),
    EE("\u8fb2\u7267\u7528\u5730"),
    EF("\u7926\u696d\u7528\u5730"),
    EG("\u4ea4\u901a\u7528\u5730"),
    EH("\u6c34\u5229\u7528\u5730"),
    EJ("\u904a\u61a9\u7528\u5730"),
    EK("\u53e4\u8e5f\u4fdd\u5b58\u7528\u5730"),
    EL("\u751f\u614b\u4fdd\u8b77\u7528\u5730"),
    EM("\u570b\u571f\u4fdd\u5b89\u7528\u5730"),
    EN("\u6baf\u846c\u7528\u5730"),
    EP("\u7279\u5b9a\u76ee\u7684\u4e8b\u696d\u7528\u5730"),
    EQ("\u9e7d\u696d\u7528\u5730"),
    ER("\u7aaf\u696d\u7528\u5730"),
    ES("\u6797\u696d\u7528\u5730"),
    ET("\u990a\u6b96\u7528\u5730");

    private String description = "";

    private EnumBaseLandUrban(String string2) {
        this.description = string2;
    }

    public String getDescription() {
        return this.description;
    }

    public static EnumBaseLandUrban findSelfByString(String string) {
        for (EnumBaseLandUrban enumBaseLandUrban : EnumBaseLandUrban.values()) {
            if (!enumBaseLandUrban.toString().equals(string)) continue;
            return enumBaseLandUrban;
        }
        return null;
    }

    public static LinkedHashMap<String, String> toMap() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        for (EnumBaseLandUrban enumBaseLandUrban : EnumBaseLandUrban.values()) {
            linkedHashMap.put(enumBaseLandUrban.toString(), enumBaseLandUrban.getDescription());
        }
        return linkedHashMap;
    }
}

