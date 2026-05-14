/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.util;

public class OptionPair
implements Comparable<OptionPair> {
    protected String value;
    protected String alias;

    public OptionPair() {
    }

    public OptionPair(String string, String string2) {
        this.value = string;
        this.alias = string2;
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    public String getAlias() {
        return this.alias;
    }

    public String getValue() {
        return this.value;
    }

    public void setAlias(String string) {
        this.alias = string;
    }

    public void setValue(String string) {
        this.value = string;
    }

    public String toString() {
        return this.alias;
    }

    public boolean equals(Object object) {
        if (object != null) {
            return this.hashCode() == object.hashCode();
        }
        return false;
    }

    @Override
    public int compareTo(OptionPair optionPair) {
        return this.value.compareTo(optionPair.value);
    }
}

