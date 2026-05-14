/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.pricerate.em;

import java.util.LinkedHashMap;
import moiland.baseland.pricerate.em.EnumPriceRateMainItem;

public enum EnumPriceRateSubItem {
    CPI("\u6d88\u8cbb\u8005\u7269\u50f9\u6307\u6578", EnumPriceRateMainItem.CPI),
    PPI("\u5730\u50f9\u6307\u6578", EnumPriceRateMainItem.PPI),
    PPI_BA("\u5730\u50f9\u6307\u6578(\u4f4f\u5b85\u5340)", EnumPriceRateMainItem.PPI),
    PPI_BB("\u5730\u50f9\u6307\u6578(\u5546\u696d\u5340)", EnumPriceRateMainItem.PPI),
    PPI_BD("\u5730\u50f9\u6307\u6578(\u5de5\u696d\u5340)", EnumPriceRateMainItem.PPI),
    BPI("\u623f\u5c4b\u50f9\u683c\u6307\u6578", EnumPriceRateMainItem.BPI);

    private String description = "";
    private EnumPriceRateMainItem mainItem = null;

    private EnumPriceRateSubItem(String string2, EnumPriceRateMainItem enumPriceRateMainItem) {
        this.description = string2;
        this.mainItem = enumPriceRateMainItem;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isSingleDistrict() {
        return this.mainItem.isSingleDistrict();
    }

    public EnumPriceRateMainItem getMainItem() {
        return this.mainItem;
    }

    public static EnumPriceRateSubItem findSelfByString(String string) {
        for (EnumPriceRateSubItem enumPriceRateSubItem : EnumPriceRateSubItem.values()) {
            if (!enumPriceRateSubItem.toString().equals(string)) continue;
            return enumPriceRateSubItem;
        }
        return null;
    }

    public static EnumPriceRateSubItem findSelfByBaseno(String string, String string2) {
        String string3 = string;
        String string4 = string2.substring(3, 5);
        if (EnumPriceRateMainItem.PPI.toString().equals(string)) {
            string3 = EnumPriceRateMainItem.PPI.toString();
            if (string4.matches("B[ABD]")) {
                string3 = string3 + "_" + string4;
            }
        }
        return EnumPriceRateSubItem.findSelfByString(string3);
    }

    public static LinkedHashMap<String, String> toMap() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        for (EnumPriceRateSubItem enumPriceRateSubItem : EnumPriceRateSubItem.values()) {
            linkedHashMap.put(enumPriceRateSubItem.toString(), enumPriceRateSubItem.getDescription());
        }
        return linkedHashMap;
    }
}

