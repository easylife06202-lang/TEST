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

import com.wfusion.baseland.QueryBean;
import com.wfusion.baseland.basic.BaseLandDialog;
import com.wfusion.baseland.estimate.EstimateLeftController;
import com.wfusion.baseland.estimate.EstimateVersionController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EstimateVersionDialog
extends BaseLandDialog {
    public EstimateVersionDialog(Stage stage) {
        super(stage);
    }

    public String load(QueryBean queryBean, EstimateLeftController estimateLeftController) {
        String string = "";
        try {
            this.loader = new FXMLLoader(this.getClass().getResource("/view/EstimateVersion.fxml"));
            this.scene = new Scene((Parent)this.loader.load());
            EstimateVersionController estimateVersionController = (EstimateVersionController)this.loader.getController();
            estimateVersionController.setDialog(this);
            estimateVersionController.init(this, queryBean, estimateLeftController);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            string = exception.getMessage();
        }
        return string;
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

