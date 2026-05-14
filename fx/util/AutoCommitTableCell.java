/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.beans.binding.Bindings
 *  javafx.beans.value.ObservableBooleanValue
 *  javafx.beans.value.ObservableValue
 *  javafx.scene.Node
 *  javafx.scene.control.ContentDisplay
 *  javafx.scene.control.TableCell
 *  javafx.scene.input.KeyCode
 *  javafx.scene.input.KeyEvent
 */
package com.wfusion.fx.util;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public abstract class AutoCommitTableCell<S, T>
extends TableCell<S, T> {
    private Node field;
    private boolean startEditing;
    private T defaultValue;

    protected abstract Node newInputField();

    protected abstract T getInputValue();

    protected abstract void setInputValue(T var1);

    protected abstract T getDefaultValue();

    protected abstract String inputValueToText(T var1);

    public void startEdit() {
        try {
            this.startEditing = true;
            super.startEdit();
            this.setInputValue(this.getItem());
        }
        finally {
            this.startEditing = false;
        }
    }

    public void cancelEdit() {
        this.getTableView().edit(this.getIndex(), this.getTableColumn());
        this.commitEdit(this.getInputValue());
    }

    private void cancelOnEscape() {
        if (this.defaultValue != null) {
            this.defaultValue = null;
            this.setItem(null);
            this.setText(null);
            this.setInputValue(null);
        }
        super.cancelEdit();
    }

    protected void updateItem(T t, boolean bl) {
        if (this.startEditing && t == null) {
            this.defaultValue = this.getDefaultValue();
            t = this.defaultValue;
        }
        super.updateItem(t, bl);
        if (bl || t == null) {
            this.setText(null);
            this.setGraphic(null);
        } else {
            this.setText(this.inputValueToText(t));
            this.setGraphic((Node)(this.startEditing || this.isEditing() ? this.getInputField() : null));
        }
    }

    protected final Node getInputField() {
        if (this.field == null) {
            this.field = this.newInputField();
            this.field.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
                if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.TAB) {
                    this.commitEdit(this.getInputValue());
                } else if (keyEvent.getCode() == KeyCode.ESCAPE) {
                    this.cancelOnEscape();
                }
            });
            this.contentDisplayProperty().bind((ObservableValue)Bindings.when((ObservableBooleanValue)this.editingProperty()).then((Object)ContentDisplay.GRAPHIC_ONLY).otherwise((Object)ContentDisplay.TEXT_ONLY));
        }
        return this.field;
    }
}

