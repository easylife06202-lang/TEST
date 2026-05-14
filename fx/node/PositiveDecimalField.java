/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.fx.node;

import com.wfusion.fx.node.DecimalField;
import com.wfusion.util.StringProcess;

public class PositiveDecimalField
extends DecimalField {
    @Override
    protected boolean isValid(String string) {
        String string2 = string.replaceAll(",", "");
        if (StringProcess.isEmpty(string2) || "-".equals(string2) || string2.contains("-")) {
            return false;
        }
        return super.isValid(string);
    }
}

