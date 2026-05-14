/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.pricerate.em;

import java.util.LinkedHashMap;

public enum EnumPriceRateType {
    CPI("\u6d88\u8cbb\u8005\u7269\u50f9\u6307\u6578", true),
    PPI("\u5730\u50f9\u6307\u6578", false),
    PPI_BA("\u5730\u50f9\u6307\u6578(\u4f4f\u5b85\u5340)", false),
    PPI_BB("\u5730\u50f9\u6307\u6578(\u5546\u696d\u5340)", false),
    PPI_BD("\u5730\u50f9\u6307\u6578(\u5de5\u696d\u5340)", false),
    BPI("\u623f\u5c4b\u50f9\u683c\u6307\u6578", true);

    private String description = "";
    private boolean singleDistrict = true;

    private EnumPriceRateType(String string2, boolean bl) {
        this.description = string2;
        this.singleDistrict = bl;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isSingleDistrict() {
        return this.singleDistrict;
    }

    public static EnumPriceRateType findSelfByString(String string) {
        for (EnumPriceRateType enumPriceRateType : EnumPriceRateType.values()) {
            if (!enumPriceRateType.toString().equals(string)) continue;
            return enumPriceRateType;
        }
        return null;
    }

    public static LinkedHashMap<String, String> toMap() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        for (EnumPriceRateType enumPriceRateType : EnumPriceRateType.values()) {
            linkedHashMap.put(enumPriceRateType.toString(), enumPriceRateType.getDescription());
        }
        return linkedHashMap;
    }
}

