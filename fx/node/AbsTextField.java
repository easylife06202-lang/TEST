/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.beans.value.ChangeListener
 *  javafx.beans.value.ObservableValue
 *  javafx.geometry.Point2D
 *  javafx.geometry.Pos
 *  javafx.scene.Node
 *  javafx.scene.control.TextField
 *  javafx.scene.control.Tooltip
 *  javafx.scene.input.KeyEvent
 */
package com.wfusion.fx.node;

import com.wfusion.fx.node.TextFieldTypeEnum;
import com.wfusion.fx.util.JavaFXUtil;
import com.wfusion.util.StringProcess;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;

public abstract class AbsTextField
extends TextField {
    private TextFieldTypeEnum textFieldType;
    private String value = "";
    public Tooltip tooltip = new Tooltip();
    protected String name = "";
    ChangeListener<Boolean> lostFocusListener = new ChangeListener<Boolean>(){

        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean bl, Boolean bl2) {
            if (bl2.booleanValue()) {
                AbsTextField.this.setText(AbsTextField.this.value);
            } else {
                AbsTextField.this.setText(AbsTextField.this.formatValue(AbsTextField.this.getFormatter()));
            }
            AbsTextField.this.tooltip.hide();
        }
    };

    public AbsTextField(TextFieldTypeEnum textFieldTypeEnum) {
        this.tooltip.setStyle("-fx-font-size: 12;-fx-background-radius: 5; -fx-background-color: rgba(128, 0, 0, 0.9);");
        JavaFXUtil.hackTooltipStartTiming(this.tooltip, 10.0);
        this.textFieldType = textFieldTypeEnum;
        this.setAlignment(TextFieldTypeEnum.DECIMAL.equals((Object)textFieldTypeEnum) ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);
        this.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (!this.isValid(this.getText())) {
                keyEvent.consume();
            }
            this.focusedProperty().addListener(this.lostFocusListener);
        });
        this.textProperty().addListener((observableValue, string, string2) -> {
            if (!this.isValid((String)string2)) {
                if (TextFieldTypeEnum.DECIMAL == this.textFieldType) {
                    this.value = string.replaceAll(",", "");
                }
                this.setText(this.value);
                Point2D point2D = this.localToScene(0.0, 0.0);
                if (this.getScene() != null) {
                    this.tooltip.show((Node)this, point2D.getX() + this.getWidth() + this.getScene().getX() + this.getScene().getWindow().getX(), point2D.getY() + this.getScene().getY() + this.getScene().getWindow().getY());
                }
            } else {
                this.value = string2.replaceAll(",", "");
            }
        });
    }

    public void setValue(String string) {
        if (!this.isValid(string)) {
            this.setText("");
            this.setPromptText("\u6c92\u6709\u9810\u8a2d");
        } else {
            this.value = string;
            this.setText(string);
            this.setText(this.formatValue(this.getFormatter()));
        }
    }

    private String formatValue(String string) {
        String string2 = "";
        if (!StringProcess.isEmpty(this.getText())) {
            if (this.getText().endsWith(".")) {
                return this.getText();
            }
            DecimalFormat decimalFormat = new DecimalFormat(string);
            if (TextFieldTypeEnum.DECIMAL == this.textFieldType) {
                BigDecimal bigDecimal = (BigDecimal)this.getValue();
                string2 = decimalFormat.format(bigDecimal);
            } else if (TextFieldTypeEnum.TEXT == this.textFieldType) {
                string2 = (String)this.getValue();
            }
        }
        return string2;
    }

    protected abstract String getFormatter();

    protected abstract String getFormatStr();

    protected abstract boolean isValid(String var1);

    protected abstract Object getValue();

    public String getName() {
        return this.name;
    }

    public void setName(String string) {
        this.name = string;
    }
}

