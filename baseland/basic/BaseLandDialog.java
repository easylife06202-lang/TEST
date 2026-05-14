/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.fxml.FXMLLoader
 *  javafx.scene.Scene
 *  javafx.stage.Modality
 *  javafx.stage.Stage
 *  javafx.stage.StageStyle
 *  javafx.stage.Window
 */
package com.wfusion.baseland.basic;

import com.wfusion.baseland.basic.IBaseLandDialog;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public abstract class BaseLandDialog
implements IBaseLandDialog {
    protected Stage parentStage;
    protected Stage stage = new Stage();
    protected FXMLLoader loader = null;
    protected Scene scene = null;

    public BaseLandDialog(Stage stage, StageStyle stageStyle, Modality modality) {
        this.parentStage = stage;
        this.stage.initStyle(stageStyle);
        this.stage.initModality(modality);
        this.stage.initOwner((Window)stage);
    }

    public BaseLandDialog(Stage stage) {
        this(stage, StageStyle.UNDECORATED, Modality.APPLICATION_MODAL);
    }

    public Stage getParentStage() {
        return this.parentStage;
    }

    @Override
    public Stage getStage() {
        return this.stage;
    }

    @Override
    public abstract String load();

    @Override
    public abstract void show();
}

