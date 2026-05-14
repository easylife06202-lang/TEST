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
import com.wfusion.baseland.estimate.ImageBrowserController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ImageBrowserDialog
extends BaseLandDialog {
    public ImageBrowserDialog(Stage stage) {
        super(stage);
    }

    public String load(String string, String string2) {
        String string3 = "";
        try {
            this.loader = new FXMLLoader(this.getClass().getResource("/view/ImageBrowser.fxml"));
            this.scene = new Scene((Parent)this.loader.load());
            ImageBrowserController imageBrowserController = (ImageBrowserController)this.loader.getController();
            imageBrowserController.init(this, string, string2);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            string3 = exception.getMessage();
        }
        return string3;
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

