/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.glossary.em;

import moiland.baseland.glossary.em.EnumGlossaryType;

public enum EnumGlossaryField {
    MAIN_NOTE("\u5099\u8a3b", EnumGlossaryType.MAIN),
    APPR_NOTES("\u5099\u8a3b", EnumGlossaryType.APPR),
    SELL_CS56("\u5099\u8a3b", EnumGlossaryType.SELL),
    RENT_CRE58("\u5099\u8a3b", EnumGlossaryType.RENT_EXT),
    RENT_CR33("\u5efa\u7269\u6536\u76ca\u8cc7\u672c\u5316\u7387\u6c7a\u5b9a\u7406\u7531", EnumGlossaryType.RENT),
    RENT_CR39("\u571f\u5730\u6536\u76ca\u8cc7\u672c\u5316\u7387\u6c7a\u5b9a\u7406\u7531", EnumGlossaryType.RENT),
    RENT_CR44("\u5099\u8a3b", EnumGlossaryType.RENT),
    DEVLOP_NOTE("\u5099\u8a3b", EnumGlossaryType.DEVELOP);

    private String description = "";
    private EnumGlossaryType type = null;

    private EnumGlossaryField(String string2, EnumGlossaryType enumGlossaryType) {
        this.description = string2;
        this.type = enumGlossaryType;
    }

    public String getDescription() {
        return this.description;
    }

    public EnumGlossaryType getType() {
        return this.type;
    }

    public static EnumGlossaryField findSelfByTname(String string) {
        for (EnumGlossaryField enumGlossaryField : EnumGlossaryField.values()) {
            if (!enumGlossaryField.toString().equals(string)) continue;
            return enumGlossaryField;
        }
        return null;
    }
}

