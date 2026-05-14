/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.event.ActionEvent
 *  javafx.event.EventHandler
 *  javafx.fxml.FXML
 *  javafx.scene.Node
 *  javafx.scene.control.Alert
 *  javafx.scene.control.Alert$AlertType
 *  javafx.scene.control.Button
 *  javafx.scene.control.ButtonType
 *  javafx.scene.control.Label
 *  javafx.scene.input.MouseEvent
 *  javafx.scene.layout.AnchorPane
 *  javafx.stage.Stage
 */
package com.wfusion.baseland.basic;

import com.wfusion.baseland.SQLiteDataProviderModel;
import com.wfusion.baseland.basic.IBaseLandDialog;
import com.wfusion.baseland.basic.IController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public abstract class Controller
implements IController {
    private double xOffset = 0.0;
    private double yOffset = 0.0;
    protected IBaseLandDialog selfDialog;
    @FXML
    private AnchorPane hbWinTitle;
    @FXML
    private Label TITLE;
    @FXML
    private Button EXIT;
    @FXML
    private Button EXIT2;

    @Override
    public void init(IBaseLandDialog iBaseLandDialog) {
        this.selfDialog = iBaseLandDialog;
        SQLiteDataProviderModel.refreshCode();
        if (this.EXIT != null) {
            this.EXIT.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

                public void handle(ActionEvent actionEvent) {
                    Node node = (Node)actionEvent.getSource();
                    Stage stage = (Stage)node.getScene().getWindow();
                    stage.close();
                }
            });
        }
        if (this.EXIT2 != null) {
            this.EXIT2.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

                public void handle(ActionEvent actionEvent) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "\u662f\u5426\u78ba\u5b9a\u95dc\u9589\u6b64\u9801\uff0c\u672a\u5132\u5b58\u8cc7\u6599\u5c07\u6703\u6d41\u5931", new ButtonType[]{ButtonType.YES, ButtonType.NO, ButtonType.CANCEL});
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        Node node = (Node)actionEvent.getSource();
                        Stage stage = (Stage)node.getScene().getWindow();
                        stage.close();
                    }
                }
            });
        }
        if (this.hbWinTitle != null) {
            this.hbWinTitle.setOnMousePressed((EventHandler)new EventHandler<MouseEvent>(){

                public void handle(MouseEvent mouseEvent) {
                    Node node = (Node)mouseEvent.getSource();
                    Stage stage = (Stage)node.getScene().getWindow();
                    Controller.this.xOffset = stage.getX() - mouseEvent.getScreenX();
                    Controller.this.yOffset = stage.getY() - mouseEvent.getScreenY();
                }
            });
        }
        if (this.hbWinTitle != null) {
            this.hbWinTitle.setOnMouseDragged((EventHandler)new EventHandler<MouseEvent>(){

                public void handle(MouseEvent mouseEvent) {
                    Node node = (Node)mouseEvent.getSource();
                    Stage stage = (Stage)node.getScene().getWindow();
                    stage.setX(mouseEvent.getScreenX() + Controller.this.xOffset);
                    stage.setY(mouseEvent.getScreenY() + Controller.this.yOffset);
                }
            });
        }
    }

    protected void setTitle(String string) {
        if (this.TITLE != null) {
            this.TITLE.setText(string);
        }
    }

    public Button getEXIT() {
        return this.EXIT;
    }

    @Override
    public abstract void refresh();
}

