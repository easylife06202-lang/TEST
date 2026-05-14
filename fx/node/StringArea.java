/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.beans.value.ChangeListener
 *  javafx.beans.value.ObservableValue
 *  javafx.geometry.Point2D
 *  javafx.scene.Node
 *  javafx.scene.control.TextArea
 *  javafx.scene.control.Tooltip
 *  javafx.scene.input.KeyEvent
 */
package com.wfusion.fx.node;

import com.wfusion.fx.util.JavaFXUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;

public class StringArea
extends TextArea {
    private String value = "";
    protected Tooltip tooltip = new Tooltip();
    private int maxLength = 8;
    private int minLength = 0;
    private String name = "";
    ChangeListener<Boolean> lostFocusListener = new ChangeListener<Boolean>(){

        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean bl, Boolean bl2) {
            if (bl2.booleanValue()) {
                StringArea.this.setText(StringArea.this.value);
            } else {
                String string = StringArea.this.getValue();
                StringArea.this.setText(string);
            }
            StringArea.this.tooltip.hide();
        }
    };

    public StringArea() {
        this.tooltip.setStyle("-fx-background-radius: 5; -fx-background-color: rgba(128, 0, 0, 0.9);");
        JavaFXUtil.hackTooltipStartTiming(this.tooltip, 10.0);
        this.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (!this.isValid(this.getText())) {
                keyEvent.consume();
            }
        });
        this.focusedProperty().addListener(this.lostFocusListener);
        this.textProperty().addListener((observableValue, string, string2) -> {
            if (!this.isValid((String)string2)) {
                this.value = string;
                this.setText(this.value);
                Point2D point2D = this.localToScene(0.0, 0.0);
                this.tooltip.show((Node)this, point2D.getX() + this.getWidth() + this.getScene().getX() + this.getScene().getWindow().getX(), point2D.getY() + this.getScene().getY() + this.getScene().getWindow().getY());
            } else {
                this.value = string2;
            }
        });
    }

    public String getValue() {
        String string = this.getText();
        if (string.length() > this.maxLength) {
            return string.substring(0, this.maxLength);
        }
        return string;
    }

    protected boolean isValid(String string) {
        if (string == null) {
            return false;
        }
        if (string.length() > this.maxLength) {
            return false;
        }
        return string.length() >= this.minLength;
    }

    public void setMaxLength(int n) {
        this.maxLength = n;
        this.tooltip.setText("[ " + this.name + " ] \u683c\u5f0f\u932f\u8aa4\uff0c\u6700\u5c0f\u9577\u5ea6 [ " + this.minLength + " ]\uff0c\u6700\u5927\u9577\u5ea6 [ " + n + " ]");
    }

    public void setMinLength(int n) {
        this.minLength = n;
        this.tooltip.setText("[ " + this.name + " ] \u683c\u5f0f\u932f\u8aa4\uff0c\u6700\u5c0f\u9577\u5ea6 [ " + n + " ]\uff0c\u6700\u5927\u9577\u5ea6 [ " + this.maxLength + " ]");
    }

    public String getName() {
        return this.name;
    }

    public void setName(String string) {
        this.name = string;
    }
}

