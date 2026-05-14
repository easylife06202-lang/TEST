/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.fx.node;

import com.wfusion.fx.node.AbsTextField;
import com.wfusion.fx.node.TextFieldTypeEnum;
import com.wfusion.util.StringProcess;

public class StringField
extends AbsTextField {
    private int maxLength = 20;
    private int minLength = 0;

    public StringField() {
        super(TextFieldTypeEnum.TEXT);
        this.tooltip.setText(this.getFormatStr());
    }

    @Override
    protected String getFormatter() {
        return "";
    }

    @Override
    protected String getFormatStr() {
        return " [" + this.name + " ] \u683c\u5f0f\u932f\u8aa4\uff0c\u6700\u5c0f\u9577\u5ea6 [ " + this.minLength + " ]\uff0c\u6700\u5927\u9577\u5ea6 [ " + this.maxLength + " ]";
    }

    @Override
    protected boolean isValid(String string) {
        if (string == null) {
            return false;
        }
        if (string.length() > this.maxLength) {
            return false;
        }
        return string.length() >= this.minLength;
    }

    @Override
    public String getValue() {
        if (StringProcess.isEmpty(this.getText())) {
            return "";
        }
        if (this.getText().length() > this.maxLength) {
            return this.getText().substring(0, this.maxLength);
        }
        return this.getText();
    }

    public void setMaxLength(int n) {
        this.maxLength = n;
        this.tooltip.setText(this.getFormatStr());
    }

    public void setMinLength(int n) {
        this.minLength = n;
        this.tooltip.setText(this.getFormatStr());
    }
}

