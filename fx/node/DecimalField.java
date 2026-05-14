/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.fx.node;

import com.wfusion.fx.node.AbsTextField;
import com.wfusion.fx.node.TextFieldTypeEnum;
import com.wfusion.util.StringProcess;
import java.math.BigDecimal;

public class DecimalField
extends AbsTextField {
    private int precision = 15;
    private int scale = 2;

    public DecimalField() {
        super(TextFieldTypeEnum.DECIMAL);
        this.tooltip.setText(this.getFormatStr());
    }

    @Override
    protected boolean isValid(String string) {
        if ("-".equals(string)) {
            return true;
        }
        String string2 = string.replaceAll(",", "").replaceAll("-", "");
        if (StringProcess.isEmpty(string2)) {
            return true;
        }
        int n = string2.indexOf(".");
        if (n > -1) {
            String string3 = string2.substring(0, n);
            if (string3.length() > this.precision - this.scale) {
                return false;
            }
            String string4 = string2.substring(n + 1, string2.length());
            if (string4.length() > this.scale) {
                return false;
            }
        } else if (string2.length() > this.precision - this.scale) {
            return false;
        }
        try {
            new BigDecimal(string2);
        }
        catch (Exception exception) {
            return false;
        }
        return true;
    }

    @Override
    public BigDecimal getValue() {
        String string = this.getText().replace(",", "");
        if (StringProcess.isEmpty(string)) {
            return new BigDecimal("0");
        }
        int n = string.indexOf(".");
        boolean bl = string.contains("-");
        if (n > -1) {
            String string2;
            String string3 = bl ? string.replace("-", "").substring(0, n - 1) : string.replace("-", "").substring(0, n);
            if (string3.length() > this.precision - this.scale) {
                string3 = string3.substring(0, this.precision - this.scale);
            }
            if ((string2 = string.substring(n + 1, string.length())).length() > this.scale) {
                string2 = string2.substring(0, this.scale);
            }
            BigDecimal bigDecimal = null;
            try {
                if (bl) {
                    string3 = "-" + string3;
                }
                bigDecimal = new BigDecimal(string3 + "." + string2);
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
            return bigDecimal;
        }
        if (string.replace("-", "").length() > this.precision - this.scale) {
            if (bl) {
                return new BigDecimal(string.substring(0, this.precision - this.scale));
            }
            return new BigDecimal("-" + string.substring(0, this.precision - this.scale));
        }
        BigDecimal bigDecimal = null;
        try {
            bigDecimal = new BigDecimal(string);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return bigDecimal;
    }

    @Override
    protected String getFormatter() {
        String string = ",###";
        if (this.scale > 0) {
            string = string + ".";
            Integer n = this.scale;
            while (n > 0) {
                string = string + "#";
                Integer n2 = n;
                Integer n3 = n = Integer.valueOf(n - 1);
            }
        }
        return string;
    }

    @Override
    protected String getFormatStr() {
        return "[ " + this.name + " ] \u683c\u5f0f\u932f\u8aa4\uff0c\u6574\u6578 [ " + (this.precision - this.scale) + " ] \u4f4d\uff0c\u5c0f\u6578 [ " + this.scale + " ] \u4f4d";
    }

    public void setPrecision(int n) {
        this.precision = n;
        this.tooltip.setText(this.getFormatStr());
    }

    public void setScale(int n) {
        this.scale = n;
        this.tooltip.setText(this.getFormatStr());
    }
}

