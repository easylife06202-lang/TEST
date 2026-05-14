/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.pricerate.em;

import java.util.LinkedHashMap;

public enum EnumPriceRateMainItem {
    CPI("\u6d88\u8cbb\u8005\u7269\u50f9\u6307\u6578", true),
    PPI("\u5730\u50f9\u6307\u6578", false),
    BPI("\u623f\u5c4b\u50f9\u683c\u6307\u6578", true);

    private String description = "";
    private boolean singleDistrict = true;

    private EnumPriceRateMainItem(String string2, boolean bl) {
        this.description = string2;
        this.singleDistrict = bl;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isSingleDistrict() {
        return this.singleDistrict;
    }

    public static EnumPriceRateMainItem findSelfByString(String string) {
        for (EnumPriceRateMainItem enumPriceRateMainItem : EnumPriceRateMainItem.values()) {
            if (!enumPriceRateMainItem.toString().equals(string)) continue;
            return enumPriceRateMainItem;
        }
        return null;
    }

    public static LinkedHashMap<String, String> toMap() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        for (EnumPriceRateMainItem enumPriceRateMainItem : EnumPriceRateMainItem.values()) {
            linkedHashMap.put(enumPriceRateMainItem.toString(), enumPriceRateMainItem.getDescription());
        }
        return linkedHashMap;
    }
}

