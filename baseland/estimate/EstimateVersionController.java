/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.event.ActionEvent
 *  javafx.event.EventHandler
 *  javafx.fxml.FXML
 *  javafx.scene.Node
 *  javafx.scene.control.Button
 *  javafx.scene.control.Label
 *  javafx.scene.control.RadioButton
 *  javafx.scene.control.ToggleGroup
 *  javafx.scene.layout.AnchorPane
 *  javafx.stage.Stage
 */
package com.wfusion.baseland.estimate;

import com.wfusion.baseland.QueryBean;
import com.wfusion.baseland.basic.Controller;
import com.wfusion.baseland.basic.IBaseLandDialog;
import com.wfusion.baseland.estimate.EstimateLeftController;
import com.wfusion.baseland.estimate.EstimateVersionDialog;
import com.wfusion.baseland.estimate.EstimateVersionModel;
import com.wfusion.fx.util.JavaFXUtil;
import java.text.DecimalFormat;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EstimateVersionController
extends Controller {
    @FXML
    private AnchorPane panel;
    @FXML
    private Label baseno;
    @FXML
    private RadioButton ver_formal;
    @FXML
    private ToggleGroup version;
    @FXML
    private RadioButton ver_A;
    @FXML
    private RadioButton ver_B;
    @FXML
    private RadioButton ver_C;
    @FXML
    private Label formal_price;
    @FXML
    private Label verA_price;
    @FXML
    private Label verB_price;
    @FXML
    private Label verC_price;
    @FXML
    private Button btLoad;
    @FXML
    private Button btExit;
    @FXML
    private AnchorPane hbWinTitle;
    @FXML
    private Label TITLE;
    private EstimateVersionModel model = new EstimateVersionModel();
    EstimateVersionDialog dialog = null;
    EstimateLeftController parentController = null;
    DecimalFormat df = new DecimalFormat("#,###");

    public void setDialog(EstimateVersionDialog estimateVersionDialog) {
        this.dialog = estimateVersionDialog;
    }

    public void init(IBaseLandDialog iBaseLandDialog, QueryBean queryBean, final EstimateLeftController estimateLeftController) {
        super.init(iBaseLandDialog);
        this.parentController = estimateLeftController;
        this.queryVersion(queryBean);
        this.baseno.setText(queryBean.baseno);
        this.formal_price.setText(this.df.format(this.model.ver_formal.getBase_pricep()));
        if (this.model.ver_A != null) {
            this.verA_price.setText(this.df.format(this.model.ver_A.getBase_pricep()));
        } else {
            this.verA_price.setText("\u7121");
        }
        if (this.model.ver_B != null) {
            this.verB_price.setText(this.df.format(this.model.ver_B.getBase_pricep()));
        } else {
            this.verB_price.setText("\u7121");
        }
        if (this.model.ver_C != null) {
            this.verC_price.setText(this.df.format(this.model.ver_C.getBase_pricep()));
        } else {
            this.verC_price.setText("\u7121");
        }
        this.btExit.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                Node node = (Node)actionEvent.getSource();
                Stage stage = (Stage)node.getScene().getWindow();
                stage.close();
            }
        });
        this.btLoad.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                if (EstimateVersionController.this.ver_A.isSelected()) {
                    if (((EstimateVersionController)EstimateVersionController.this).model.ver_A == null) {
                        JavaFXUtil.showErrorMessageBox("\u6b64\u6848\u4ef6\u4e26\u7121\u7248\u672cA\uff0c\u8acb\u91cd\u65b0\u9078\u64c7!!");
                        return;
                    }
                    EstimateVersionController.this.model;
                    EstimateVersionController.this.model;
                    EstimateVersionModel.version_conn = "BaseLand_A.db";
                } else if (EstimateVersionController.this.ver_B.isSelected()) {
                    if (((EstimateVersionController)EstimateVersionController.this).model.ver_B == null) {
                        JavaFXUtil.showErrorMessageBox("\u6b64\u6848\u4ef6\u4e26\u7121\u7248\u672cB\uff0c\u8acb\u91cd\u65b0\u9078\u64c7!!");
                        return;
                    }
                    EstimateVersionController.this.model;
                    EstimateVersionController.this.model;
                    EstimateVersionModel.version_conn = "BaseLand_B.db";
                } else if (EstimateVersionController.this.ver_C.isSelected()) {
                    if (((EstimateVersionController)EstimateVersionController.this).model.ver_C == null) {
                        JavaFXUtil.showErrorMessageBox("\u6b64\u6848\u4ef6\u4e26\u7121\u7248\u672cC\uff0c\u8acb\u91cd\u65b0\u9078\u64c7!!");
                        return;
                    }
                    EstimateVersionController.this.model;
                    EstimateVersionController.this.model;
                    EstimateVersionModel.version_conn = "BaseLand_C.db";
                } else {
                    EstimateVersionController.this.model;
                    EstimateVersionController.this.model;
                    EstimateVersionModel.version_conn = "BaseLand.db";
                }
                estimateLeftController.load(estimateLeftController.parrentDialog.getStage());
                Node node = (Node)actionEvent.getSource();
                Stage stage = (Stage)node.getScene().getWindow();
                stage.close();
            }
        });
    }

    private void queryVersion(QueryBean queryBean) {
        this.model.queryVersion(queryBean.year, queryBean.baseno);
    }

    @Override
    public void refresh() {
    }
}

