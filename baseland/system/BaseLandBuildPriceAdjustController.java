/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.beans.value.ChangeListener
 *  javafx.beans.value.ObservableValue
 *  javafx.collections.FXCollections
 *  javafx.event.ActionEvent
 *  javafx.event.EventHandler
 *  javafx.fxml.FXML
 *  javafx.scene.Node
 *  javafx.scene.control.Button
 *  javafx.scene.control.ComboBox
 *  javafx.scene.control.Label
 *  javafx.scene.control.TextField
 *  javafx.scene.layout.AnchorPane
 *  javafx.stage.Stage
 *  javafx.util.converter.DoubleStringConverter
 */
package com.wfusion.baseland.system;

import com.wfusion.baseland.basic.Controller;
import com.wfusion.baseland.basic.IBaseLandDialog;
import com.wfusion.baseland.system.BaseLandBuildPriceAdjustModel;
import com.wfusion.fx.util.JavaFXUtil;
import com.wfusion.fx.util.NumberFormater;
import com.wfusion.util.OptionPair;
import com.wfusion.util.StringProcess;
import java.util.HashMap;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

public class BaseLandBuildPriceAdjustController
extends Controller {
    @FXML
    private ComboBox<String> cbYear;
    @FXML
    private Button btEdit;
    @FXML
    private Button btSave;
    @FXML
    private Button btExit;
    @FXML
    private ComboBox<OptionPair> cbBasePeriod;
    @FXML
    private Button btBase;
    @FXML
    private AnchorPane panelData;
    @FXML
    private Label lbYear;
    BaseLandBuildPriceAdjustModel model = new BaseLandBuildPriceAdjustModel();
    DoubleStringConverter doubleConvert = new DoubleStringConverter(){

        public Double fromString(String string) {
            try {
                return super.fromString(string);
            }
            catch (NumberFormatException numberFormatException) {
                return null;
            }
        }
    };

    @Override
    public void init(IBaseLandDialog iBaseLandDialog) {
        super.init(iBaseLandDialog);
        this.initComboBox();
        this.btExit.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                Node node = (Node)actionEvent.getSource();
                Stage stage = (Stage)node.getScene().getWindow();
                stage.close();
            }
        });
        this.btSave.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                if (BaseLandBuildPriceAdjustController.this.model.voValue == null || BaseLandBuildPriceAdjustController.this.model.voValue.size() != 18) {
                    return;
                }
                BaseLandBuildPriceAdjustController.this.updateVoFromUI();
                if (BaseLandBuildPriceAdjustController.this.model.save()) {
                    JavaFXUtil.showToastMessageBox(BaseLandBuildPriceAdjustController.this.selfDialog.getStage(), "\u5132\u5b58\u6210\u529f", 500);
                }
            }
        });
        this.btEdit.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                String string = "";
                String string2 = "";
                if (BaseLandBuildPriceAdjustController.this.cbYear.getSelectionModel().getSelectedItem() != null) {
                    string = ((String)BaseLandBuildPriceAdjustController.this.cbYear.getSelectionModel().getSelectedItem()).toString();
                }
                if (BaseLandBuildPriceAdjustController.this.cbBasePeriod.getSelectionModel().getSelectedItem() != null) {
                    string2 = ((OptionPair)BaseLandBuildPriceAdjustController.this.cbBasePeriod.getSelectionModel().getSelectedItem()).getValue();
                }
                if (!StringProcess.isEmpty(string) && !StringProcess.isEmpty(string2)) {
                    BaseLandBuildPriceAdjustController.this.lbYear.setText(string);
                    BaseLandBuildPriceAdjustController.this.model.query(string, string2);
                    BaseLandBuildPriceAdjustController.this.updateUIFromVo();
                }
            }
        });
    }

    private void initComboBox() {
        this.cbBasePeriod.setItems(FXCollections.observableArrayList(this.model.getBuild_cost_basedate()));
        this.cbBasePeriod.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                String string = null;
                if (optionPair2 != null) {
                    string = optionPair2.getValue();
                    BaseLandBuildPriceAdjustController.this.cbYear.setItems(FXCollections.observableArrayList(BaseLandBuildPriceAdjustController.this.model.getBuildPriceAdjustYearList(string)));
                    BaseLandBuildPriceAdjustController.this.cbYear.setDisable(false);
                }
            }
        });
    }

    protected void updateUIFromVo() {
        for (Node node : this.panelData.getChildren()) {
            String string = node.getId();
            if (string == null) continue;
            for (HashMap<String, Object> hashMap : this.model.voValue.values()) {
                if (!hashMap.containsKey(string)) continue;
                if (node instanceof TextField) {
                    ((TextField)node).setText(NumberFormater.df2.format((Double)hashMap.get(string)));
                }
                if (!(node instanceof Label)) continue;
                ((Label)node).setText((String)hashMap.get(string));
            }
        }
    }

    public void updateVoFromUI() {
        int n = StringProcess.parserInt(this.lbYear.getText(), 0);
        if (n == 0) {
            return;
        }
        for (Node node : this.panelData.getChildren()) {
            String string = node.getId();
            if (string == null || !(node instanceof TextField)) continue;
            String[] stringArray = StringProcess.split(string, "_");
            int n2 = StringProcess.parserInt(stringArray[1], 0);
            String string2 = null;
            string2 = n2 > 0 && n2 <= 3 ? String.valueOf(n - 2) + StringProcess.fillZero(n2 + 9, 2) : (n2 > 3 && n2 <= 15 ? String.valueOf(n - 1) + StringProcess.fillZero(n2 - 3, 2) : String.valueOf(n) + StringProcess.fillZero(n2 - 15, 2));
            HashMap<String, Object> hashMap = this.model.voValue.get(string2);
            if (hashMap == null) {
                hashMap = new HashMap();
                this.model.voValue.put(string2, hashMap);
            }
            hashMap.put("ratio", ((TextField)node).getText());
        }
    }

    @Override
    public void refresh() {
    }
}

