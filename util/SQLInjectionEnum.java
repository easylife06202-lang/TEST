/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.util;

public enum SQLInjectionEnum {
    WAITFOR("(?i)waitfor", "&#119;&#97;&#105;&#116;&#102;&#111;&#114;"),
    DELAY("(?i)delay", "&#100;&#101;&#108;&#97;&#121;"),
    APOSTROPHE("'", "&#39;"),
    NOTE("--", "&#45;&#45;"),
    BOLCK_NOTE2("/", "&#47;"),
    CHAR("char", "&#99;&#104;&#97;&#114;"),
    EQUAL("=", "&#61;"),
    LESS("<", "&#60;"),
    MORE(">", "&#62;"),
    GET_CHAR1("%28", ""),
    GET_CHAR2("%29", ""),
    GET_CHAR3("%25", ""),
    GET_CHAR4("%3C", ""),
    GET_CHAR5("%3E", ""),
    GET_CHAR6("%3B", ""),
    GET_CHAR7("%3D", ""),
    GET_CHAR8("%5C", ""),
    GET_CHAR9("%20", ""),
    GET_CHAR10("%27", "");

    private String key = "";
    private String replaceValue = "";

    private SQLInjectionEnum(String string2, String string3) {
        this.key = string2;
        this.replaceValue = string3;
    }

    public String getKey() {
        return this.key;
    }

    public String getReplaceValue() {
        return this.replaceValue;
    }
}

