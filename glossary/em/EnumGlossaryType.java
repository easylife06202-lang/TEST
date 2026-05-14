/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.glossary.em;

public enum EnumGlossaryType {
    MAIN("\u5730\u50f9\u57fa\u6e96\u5730\u4f30\u50f9\u5831\u544a\u8868", "MAIN"),
    APPR("\u6bd4\u8f03\u6cd5\u8abf\u67e5\u4f30\u50f9\u8868", "APPR"),
    SELL("\u6210\u672c\u6cd5\u53ca\u623f\u5730\u5206\u96e2\u4f30\u50f9\u8868", "SELL"),
    RENT_EXT("\u6210\u672c\u6cd5\u8abf\u67e5\u4f30\u50f9\u8868", "RENT_EXT"),
    RENT("\u6536\u76ca\u6cd5\u8abf\u67e5\u4f30\u50f9\u8868", "RENT"),
    DEVELOP("\u571f\u5730\u958b\u767c\u5206\u6790\u6cd5\u8abf\u67e5\u4f30\u50f9\u8868", "DEVELOP");

    private String description = "";
    private String code = "";

    private EnumGlossaryType(String string2, String string3) {
        this.description = string2;
        this.code = string3;
    }

    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    public static EnumGlossaryType findSelfByTname(String string) {
        for (EnumGlossaryType enumGlossaryType : EnumGlossaryType.values()) {
            if (!enumGlossaryType.getCode().equals(string)) continue;
            return enumGlossaryType;
        }
        return null;
    }
}

