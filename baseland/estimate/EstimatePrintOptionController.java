/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.event.ActionEvent
 *  javafx.event.EventHandler
 *  javafx.fxml.FXML
 *  javafx.scene.Node
 *  javafx.scene.control.Button
 *  javafx.scene.control.CheckBox
 *  javafx.scene.control.Label
 *  javafx.scene.layout.AnchorPane
 *  javafx.stage.Stage
 */
package com.wfusion.baseland.estimate;

import com.wfusion.baseland.QueryBean;
import com.wfusion.baseland.basic.Controller;
import com.wfusion.baseland.basic.IBaseLandDialog;
import com.wfusion.baseland.estimate.EstimateLeftController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EstimatePrintOptionController
extends Controller {
    @FXML
    private Button btOK;
    @FXML
    private Button btExit;
    @FXML
    private CheckBox report2Remark;
    @FXML
    private CheckBox rentRemark;
    @FXML
    private AnchorPane hbWinTitle;
    @FXML
    private Label TITLE;

    public void init(final ActionEvent actionEvent, IBaseLandDialog iBaseLandDialog, final QueryBean queryBean, final EstimateLeftController estimateLeftController) {
        super.init(iBaseLandDialog);
        this.btOK.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent2) {
                Node node = (Node)actionEvent2.getSource();
                Stage stage = (Stage)node.getScene().getWindow();
                stage.close();
                estimateLeftController.printMethod(actionEvent, queryBean, EstimatePrintOptionController.this.report2Remark.isSelected(), EstimatePrintOptionController.this.rentRemark.isSelected());
            }
        });
        this.btExit.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                Node node = (Node)actionEvent.getSource();
                Stage stage = (Stage)node.getScene().getWindow();
                stage.close();
            }
        });
    }

    @Override
    public void refresh() {
    }
}

