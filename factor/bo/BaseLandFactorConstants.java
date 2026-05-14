/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.factor.bo;

import java.text.DecimalFormat;

public interface BaseLandFactorConstants {
    public static final String TEXT_COLON = "\uff1a";
    public static final String TEXT_PIPE = "\u2502";
    public static final String TEXT_AND = " \u4e14 ";
    public static final String TEXT_OR = " \u6216 ";
    public static final String TEXT_VALUE = "[\u503c]";
    public static final String TEXT_OR_NONE = " \u6216\u300c\u7121\u300d";
    public static final String MESSAGE_NO_LEVEL = "\u6c92\u6709\u9078\u64c7\u512a\u52a3\u7b49\u7d1a";
    public static final String SYMBOL_SEMI_COLON = ";";
    public static final String SYMBOL_AT = "@";
    public static final String SYMBOL_COLON = ":";
    public static final String SYMBOL_PIPE = "|";
    public static final String SYMBOL_BACK_PLOPING_COMMA = "\u3001";
    public static final String SYMBOL_ELLIPSIS = "...";
    public static final String VALUE_NONE = "\u7121";
    public static final String VALUE_AND = "\u4e14";
    public static final String VALUE_OR = "\u6216";
    public static final String PATTERN_NUMERIAL_ONLY = "^\\d+(\\.\\d+)?$";
    public static final String PATTERN_NUMERIAL_WITH_RANK_UNIT = "(\\d+(\\.\\d+)?)(rank_unit)";
    public static final String PATTERN_CONTAIN_NUMERIAL_WITH_RANK_UNIT_AND_NAME = "(.*\\d+(\\.\\d+)?)(rank_unit)(.+)";
    public static final int SCALE_2 = 2;
    public static final int SCALE_4 = 4;
    public static final DecimalFormat DF0 = new DecimalFormat("###0");
    public static final DecimalFormat DF2 = new DecimalFormat("###0.00");
}

