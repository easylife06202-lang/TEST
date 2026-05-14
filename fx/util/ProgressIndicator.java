/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.animation.KeyFrame
 *  javafx.animation.KeyValue
 *  javafx.animation.Timeline
 *  javafx.beans.value.WritableValue
 *  javafx.scene.Parent
 *  javafx.scene.Scene
 *  javafx.scene.control.ProgressIndicator
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

import com.wfusion.fx.util.ILoading;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.WritableValue;
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

public class ProgressIndicator {
    ILoading loading;

    public ProgressIndicator(ILoading iLoading) {
        this.loading = iLoading;
    }

    public void run(Stage stage) {
        Stage stage2 = new Stage();
        stage2.initOwner((Window)stage);
        stage2.setResizable(false);
        stage2.initStyle(StageStyle.TRANSPARENT);
        Text text = new Text("\u8acb\u7a0d\u5019...");
        text.setFont(Font.font((String)"Dialog", (FontWeight)FontWeight.BOLD, (double)40.0));
        text.setFill((Paint)Color.YELLOW);
        javafx.scene.control.ProgressIndicator progressIndicator = new javafx.scene.control.ProgressIndicator();
        progressIndicator.setStyle(" -fx-progress-color: red;");
        progressIndicator.setMinWidth(120.0);
        progressIndicator.setMinHeight(120.0);
        StackPane stackPane = new StackPane();
        stackPane.setStyle("-fx-background-radius: 20; -fx-background-color: rgba(0, 0, 0, 0.7); -fx-padding: 30px 100px 30px 100px;");
        stackPane.setOpacity(0.0);
        stackPane.getChildren().add((Object)text);
        stackPane.getChildren().add((Object)progressIndicator);
        Scene scene = new Scene((Parent)stackPane);
        scene.setFill((Paint)Color.TRANSPARENT);
        stage2.setScene(scene);
        stage2.show();
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis((double)1.0), new KeyValue[]{new KeyValue((WritableValue)stage2.getScene().getRoot().opacityProperty(), (Object)1)});
        timeline.getKeyFrames().add((Object)keyFrame);
        timeline.setOnFinished(actionEvent -> new Thread(() -> {
            this.loading.loading();
            Timeline timeline = new Timeline();
            KeyFrame keyFrame = new KeyFrame(Duration.millis((double)1.0), new KeyValue[]{new KeyValue((WritableValue)stage2.getScene().getRoot().opacityProperty(), (Object)0)});
            timeline.getKeyFrames().add((Object)keyFrame);
            timeline.setOnFinished(actionEvent -> stage2.close());
            timeline.play();
        }).start());
        timeline.play();
    }
}

