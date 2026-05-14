/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.animation.KeyFrame
 *  javafx.animation.KeyValue
 *  javafx.animation.Timeline
 *  javafx.scene.Node
 *  javafx.scene.Parent
 *  javafx.scene.control.Alert
 *  javafx.scene.control.Alert$AlertType
 *  javafx.scene.control.ScrollPane
 *  javafx.scene.control.TextArea
 *  javafx.scene.control.Tooltip
 *  javafx.stage.Stage
 *  javafx.util.Duration
 */
package com.wfusion.fx.util;

import com.wfusion.fx.util.Toast;
import com.wfusion.util.StringProcess;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javafx.util.Duration;

public class JavaFXUtil {
    public static void showErrorMessageBox(String string) {
        JavaFXUtil.showErrorMessageBox(string, "");
    }

    public static void showErrorMessageBox(String string, String string2) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("\u932f\u8aa4");
        alert.setContentText(string);
        if (!StringProcess.isEmpty(string2)) {
            alert.getDialogPane().setExpandableContent((Node)new ScrollPane((Node)new TextArea(string2)));
        }
        alert.showAndWait();
    }

    public static void showNormalMessageBox(String string, String string2) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("\u8a0a\u606f");
        alert.setContentText(string);
        if (!StringProcess.isEmpty(string2)) {
            alert.getDialogPane().setExpandableContent((Node)new ScrollPane((Node)new TextArea(string2)));
        }
        alert.showAndWait();
    }

    public static void showToastMessageBox(Stage stage, String string, int n, int n2, int n3) {
        Toast.makeText(stage, string, n, n2, n3);
    }

    public static void showToastMessageBox(Stage stage, String string, int n) {
        JavaFXUtil.showToastMessageBox(stage, string, n, 500, 500);
    }

    public static ArrayList<Node> getAllNodes(Parent parent) {
        ArrayList<Node> arrayList = new ArrayList<Node>();
        JavaFXUtil.addAllDescendents(parent, arrayList);
        return arrayList;
    }

    private static void addAllDescendents(Parent parent, ArrayList<Node> arrayList) {
        for (Node node : parent.getChildrenUnmodifiable()) {
            arrayList.add(node);
            if (!(node instanceof Parent)) continue;
            JavaFXUtil.addAllDescendents((Parent)node, arrayList);
        }
    }

    public static final LocalDate LOCAL_DATE(String string) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyMMdd");
        LocalDate localDate = LocalDate.parse(string, dateTimeFormatter);
        return localDate;
    }

    public static void hackTooltipStartTiming(Tooltip tooltip, double d) {
        try {
            Field field = tooltip.getClass().getDeclaredField("BEHAVIOR");
            field.setAccessible(true);
            Object object = field.get(tooltip);
            Field field2 = object.getClass().getDeclaredField("activationTimer");
            field2.setAccessible(true);
            Timeline timeline = (Timeline)field2.get(object);
            timeline.getKeyFrames().clear();
            timeline.getKeyFrames().add((Object)new KeyFrame(new Duration(d), new KeyValue[0]));
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}

