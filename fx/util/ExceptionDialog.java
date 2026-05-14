/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.scene.Node
 *  javafx.scene.control.Alert
 *  javafx.scene.control.Alert$AlertType
 *  javafx.scene.control.Label
 *  javafx.scene.control.TextArea
 *  javafx.scene.layout.GridPane
 *  javafx.scene.layout.Priority
 */
package com.wfusion.fx.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class ExceptionDialog {
    public static void show(Exception exception) {
        ExceptionDialog.show(exception, "");
    }

    public static void show(Error error) {
        ExceptionDialog.show(error, "");
    }

    public static void show(Error error, String string) {
        System.out.println(error.getMessage());
        error.printStackTrace();
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        error.printStackTrace(printWriter);
        String string2 = stringWriter.toString();
        ExceptionDialog.show(string, string2);
    }

    public static void show(Exception exception, String string) {
        System.out.println(exception.getMessage());
        exception.printStackTrace();
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        exception.printStackTrace(printWriter);
        String string2 = stringWriter.toString();
        ExceptionDialog.show(string, string2);
    }

    public static void show(String string, String string2) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("\u4f8b\u5916\u932f\u8aa4");
        alert.setHeaderText("\u767c\u751f\u4f8b\u5916\u932f\u8aa4");
        alert.setContentText(string);
        Label label = new Label("\u4f8b\u5916\u932f\u8aa4\u8a0a\u606f\u70ba\uff1a");
        TextArea textArea = new TextArea(string2);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow((Node)textArea, (Priority)Priority.ALWAYS);
        GridPane.setHgrow((Node)textArea, (Priority)Priority.ALWAYS);
        GridPane gridPane = new GridPane();
        gridPane.setMaxWidth(Double.MAX_VALUE);
        gridPane.add((Node)label, 0, 0);
        gridPane.add((Node)textArea, 0, 1);
        alert.getDialogPane().setExpandableContent((Node)gridPane);
        alert.showAndWait();
    }
}

