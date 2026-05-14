/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.collections.FXCollections
 *  javafx.event.ActionEvent
 *  javafx.event.EventHandler
 *  javafx.fxml.FXML
 *  javafx.scene.Node
 *  javafx.scene.control.Alert
 *  javafx.scene.control.Alert$AlertType
 *  javafx.scene.control.Button
 *  javafx.scene.control.ButtonType
 *  javafx.scene.control.ComboBox
 *  javafx.scene.control.Label
 *  javafx.stage.Stage
 */
package com.wfusion.baseland.estimate;

import com.wfusion.baseland.basic.Controller;
import com.wfusion.baseland.basic.IBaseLandDialog;
import com.wfusion.baseland.estimate.EstimateVersionCopyDialog;
import com.wfusion.baseland.estimate.EstimateVersionCopyModel;
import com.wfusion.fx.util.JavaFXUtil;
import com.wfusion.util.OptionPair;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class EstimateVersionCopyController
extends Controller {
    @FXML
    private Label baseno;
    @FXML
    private Label version;
    @FXML
    private ComboBox<OptionPair> ver_tar;
    @FXML
    private Button btCopy;
    @FXML
    private Button btExit;
    private String base_ver = "";
    EstimateVersionCopyDialog dialog = null;

    public void setDialog(EstimateVersionCopyDialog estimateVersionCopyDialog) {
        this.dialog = estimateVersionCopyDialog;
    }

    public void init(final IBaseLandDialog iBaseLandDialog, final String string, final String string2, String string3) {
        super.init(iBaseLandDialog);
        this.baseno.setText(string2);
        if (string3.equals("BaseLand_A.db")) {
            this.version.setText("\u7248\u672cA");
        } else if (string3.equals("BaseLand_B.db")) {
            this.version.setText("\u7248\u672cB");
        } else if (string3.equals("BaseLand_C.db")) {
            this.version.setText("\u7248\u672cC");
        } else {
            this.version.setText("\u6b63\u5f0f\u7248");
        }
        this.base_ver = string3;
        this.ver_tar.setItems(FXCollections.observableArrayList(this.getVerTarColl(this.base_ver)));
        this.ver_tar.getSelectionModel().selectFirst();
        this.btExit.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                Node node = (Node)actionEvent.getSource();
                Stage stage = (Stage)node.getScene().getWindow();
                stage.close();
            }
        });
        this.btCopy.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                EstimateVersionCopyModel estimateVersionCopyModel;
                boolean bl;
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "\u662f\u5426\u78ba\u5b9a\u8907\u88fd\uff0c\u5df2\u5b58\u5728\u7248\u672c\u5c07\u6703\u88ab\u8986\u84cb!!", new ButtonType[]{ButtonType.YES, ButtonType.NO, ButtonType.CANCEL});
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES && (bl = (estimateVersionCopyModel = new EstimateVersionCopyModel()).copyVersion(string, string2, EstimateVersionCopyController.this.base_ver, ((OptionPair)EstimateVersionCopyController.this.ver_tar.getSelectionModel().getSelectedItem()).getValue()))) {
                    JavaFXUtil.showToastMessageBox(iBaseLandDialog.getStage(), "\u8907\u88fd\u6210\u529f", 500);
                }
            }
        });
    }

    private ArrayList<OptionPair> getVerTarColl(String string) {
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        if (!string.equals("BaseLand.db")) {
            arrayList.add(new OptionPair("BaseLand.db", "\u6b63\u5f0f\u7248"));
        }
        if (!string.equals("BaseLand_A.db")) {
            arrayList.add(new OptionPair("BaseLand_A.db", "A\u7248\u672c"));
        }
        if (!string.equals("BaseLand_B.db")) {
            arrayList.add(new OptionPair("BaseLand_B.db", "B\u7248\u672c"));
        }
        if (!string.equals("BaseLand_C.db")) {
            arrayList.add(new OptionPair("BaseLand_C.db", "C\u7248\u672c"));
        }
        return arrayList;
    }

    @Override
    public void refresh() {
    }
}

