/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.fxml.FXMLLoader
 *  javafx.scene.Parent
 *  javafx.scene.Scene
 *  javafx.stage.Stage
 */
package com.wfusion.baseland.estimate;

import com.wfusion.baseland.basic.BaseLandDialog;
import com.wfusion.baseland.estimate.EstimateVersionCopyController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EstimateVersionCopyDialog
extends BaseLandDialog {
    public EstimateVersionCopyDialog(Stage stage) {
        super(stage);
    }

    public String load(String string, String string2, String string3) {
        String string4 = "";
        try {
            this.loader = new FXMLLoader(this.getClass().getResource("/view/EstimateVersionCopy.fxml"));
            this.scene = new Scene((Parent)this.loader.load());
            EstimateVersionCopyController estimateVersionCopyController = (EstimateVersionCopyController)this.loader.getController();
            estimateVersionCopyController.setDialog(this);
            estimateVersionCopyController.init(this, string, string2, string3);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            string4 = exception.getMessage();
        }
        return string4;
    }

    @Override
    public void show() {
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    @Override
    public String load() {
        return null;
    }
}

