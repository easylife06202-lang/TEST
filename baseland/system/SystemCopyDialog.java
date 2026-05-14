/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.fxml.FXMLLoader
 *  javafx.scene.Parent
 *  javafx.scene.Scene
 *  javafx.stage.Stage
 */
package com.wfusion.baseland.system;

import com.wfusion.baseland.basic.BaseLandDialog;
import com.wfusion.baseland.system.SystemCopyController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SystemCopyDialog
extends BaseLandDialog {
    public SystemCopyDialog(Stage stage) {
        super(stage);
    }

    @Override
    public String load() {
        String string = "";
        try {
            this.loader = new FXMLLoader(this.getClass().getResource("/view/SystemCopy.fxml"));
            this.scene = new Scene((Parent)this.loader.load());
            SystemCopyController systemCopyController = (SystemCopyController)this.loader.getController();
            systemCopyController.init(this);
            System.out.println("finish");
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
}

