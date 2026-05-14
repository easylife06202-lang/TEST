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
import com.wfusion.baseland.estimate.ImageBrowserRentController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT_MONTH;

public class ImageBrowserRentDialog
extends BaseLandDialog {
    public ImageBrowserRentDialog(Stage stage) {
        super(stage);
    }

    public String load(String string, String string2, NVO_BASELAND_RENT_MONTH nVO_BASELAND_RENT_MONTH) {
        String string3 = "";
        try {
            this.loader = new FXMLLoader(this.getClass().getResource("/view/ImageBrowserRent.fxml"));
            this.scene = new Scene((Parent)this.loader.load());
            ImageBrowserRentController imageBrowserRentController = (ImageBrowserRentController)this.loader.getController();
            imageBrowserRentController.init(this, string, string2, nVO_BASELAND_RENT_MONTH);
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

