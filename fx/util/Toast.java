/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.animation.KeyFrame
 *  javafx.animation.KeyValue
 *  javafx.animation.Timeline
 *  javafx.beans.value.WritableValue
 *  javafx.scene.Node
 *  javafx.scene.Parent
 *  javafx.scene.Scene
 *  javafx.scene.layout.StackPane
 *  javafx.scene.paint.Color
 *  javafx.scene.paint.Paint
 *  javafx.scene.text.Font
 *  javafx.scene.text.FontWeight
 *  javafx.scene.text.Text
 *  javafx.stage.Stage
 *  javafx.stage.StageStyle
 *  javafx.stage.Window
 *  javafx.util.Duration
 */
package com.wfusion.fx.util;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.WritableValue;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Duration;

public final class Toast {
    public static void makeText(Stage stage, String string, int n, int n2, int n3) {
        Stage stage2 = new Stage();
        stage2.initOwner((Window)stage);
        stage2.setResizable(false);
        stage2.initStyle(StageStyle.TRANSPARENT);
        Text text = new Text(string);
        text.setFont(Font.font((String)"Dialog", (FontWeight)FontWeight.BOLD, (double)40.0));
        text.setFill((Paint)Color.YELLOW);
        StackPane stackPane = new StackPane(new Node[]{text});
        stackPane.setStyle("-fx-background-radius: 20; -fx-background-color: rgba(128, 0, 0, 0.6); -fx-padding: 30px 100px 30px 100px;");
        stackPane.setOpacity(0.0);
        Scene scene = new Scene((Parent)stackPane);
        scene.setFill((Paint)Color.TRANSPARENT);
        stage2.setScene(scene);
        stage2.show();
        stage2.setX(stage.getX() + (stage.getWidth() / 2.0 - stage2.getWidth() / 2.0));
        stage2.setY(stage.getY() + (stage.getHeight() - stage2.getHeight() * 1.5));
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis((double)n2), new KeyValue[]{new KeyValue((WritableValue)stage2.getScene().getRoot().opacityProperty(), (Object)1)});
        timeline.getKeyFrames().add((Object)keyFrame);
        timeline.setOnFinished(actionEvent -> new Thread(() -> {
            try {
                Thread.sleep(n);
            }
            catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            Timeline timeline = new Timeline();
            KeyFrame keyFrame = new KeyFrame(Duration.millis((double)n3), new KeyValue[]{new KeyValue((WritableValue)stage2.getScene().getRoot().opacityProperty(), (Object)0)});
            timeline.getKeyFrames().add((Object)keyFrame);
            timeline.setOnFinished(actionEvent -> stage2.close());
            timeline.play();
        }).start());
        timeline.play();
    }
}

