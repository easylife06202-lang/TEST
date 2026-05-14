/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.beans.value.ObservableValue
 *  javafx.concurrent.Task
 *  javafx.geometry.Pos
 *  javafx.scene.Node
 *  javafx.scene.Parent
 *  javafx.scene.Scene
 *  javafx.scene.control.Label
 *  javafx.scene.control.ProgressIndicator
 *  javafx.scene.layout.StackPane
 *  javafx.scene.paint.Color
 *  javafx.scene.paint.Paint
 *  javafx.scene.text.Font
 *  javafx.scene.text.FontWeight
 *  javafx.stage.Stage
 *  javafx.stage.StageStyle
 *  javafx.stage.Window
 */
package com.wfusion.fx.util;

import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class UIProgressIndicator {
    final Stage stage = new Stage();
    Stage ownerStage;
    String display;

    public UIProgressIndicator(Stage stage) {
        this(stage, "\u8f09\u5165\u4e2d");
    }

    public UIProgressIndicator(Stage stage, String string) {
        this.stage.initOwner((Window)stage);
        this.stage.setResizable(false);
        this.stage.initStyle(StageStyle.TRANSPARENT);
        this.display = string;
    }

    public void show(Task<?> task) {
        StackPane stackPane = new StackPane();
        stackPane.setPrefWidth(this.stage.getOwner().getWidth());
        stackPane.setPrefHeight(this.stage.getOwner().getHeight());
        stackPane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.9); -fx-padding: 30px 100px 30px 100px;");
        Label label = new Label(this.display);
        label.setFont(Font.font((String)"\u5fae\u8edf\u6b63\u9ed1\u9ad4", (FontWeight)FontWeight.NORMAL, (double)40.0));
        label.setTextFill((Paint)Color.RED);
        label.setAlignment(Pos.CENTER);
        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.setStyle(" -fx-progress-color: #FFB000;");
        progressIndicator.setMaxHeight(200.0);
        progressIndicator.progressProperty().bind((ObservableValue)task.progressProperty());
        stackPane.getChildren().add((Object)progressIndicator);
        StackPane.setAlignment((Node)progressIndicator, (Pos)Pos.CENTER);
        stackPane.getChildren().add((Object)label);
        StackPane.setAlignment((Node)label, (Pos)Pos.CENTER);
        Scene scene = new Scene((Parent)stackPane);
        scene.setFill((Paint)Color.TRANSPARENT);
        this.stage.setScene(scene);
        this.stage.show();
        this.stage.setX(this.stage.getOwner().getX());
        this.stage.setY(this.stage.getOwner().getY());
    }

    public void close() {
        this.stage.close();
    }
}

