/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.beans.value.ChangeListener
 *  javafx.beans.value.ObservableValue
 *  javafx.event.ActionEvent
 *  javafx.event.EventHandler
 *  javafx.fxml.FXML
 *  javafx.scene.Node
 *  javafx.scene.control.Button
 *  javafx.scene.control.TextField
 *  javafx.stage.Stage
 */
package com.wfusion.baseland.system;

import com.wfusion.baseland.system.PriceRateModel;
import com.wfusion.fx.util.JavaFXUtil;
import com.wfusion.util.StringProcess;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import moiland.baseland.verify.BaseLandVerifyUtil;

public class CreateSystemPriceRateController {
    @FXML
    private TextField tfYM;
    @FXML
    private TextField tfRate;
    @FXML
    private Button btOK;
    @FXML
    private Button btExit;
    PriceRateModel model = null;
    String AA45 = "";
    String AA46 = "";
    String year = "";
    String rateType = "";
    ChangeListener<Boolean> YMListener = new ChangeListener<Boolean>(){

        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean bl, Boolean bl2) {
            if (!(bl2.booleanValue() || StringProcess.isEmpty(CreateSystemPriceRateController.this.tfYM.getText()) || BaseLandVerifyUtil.checkYearMonth(CreateSystemPriceRateController.this.tfYM.getText(), false))) {
                JavaFXUtil.showToastMessageBox(new Stage(), "\u8acb\u78ba\u8a8d\u5e74\u6708\u683c\u5f0f\uff0cEX:10801", 1000);
                CreateSystemPriceRateController.this.tfYM.setText("");
            }
        }
    };
    ChangeListener<Boolean> rateListener = new ChangeListener<Boolean>(){

        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean bl, Boolean bl2) {
            if (!(bl2.booleanValue() || StringProcess.isEmpty(CreateSystemPriceRateController.this.tfRate.getText()) || CreateSystemPriceRateController.this.tfRate.getText().matches("^[-]?[0-9]{1,3}+(.[0-9]{0,2})?$"))) {
                JavaFXUtil.showToastMessageBox(new Stage(), "\u8acb\u78ba\u8a8d\u6307\u6578\uff0c\u6574\u65783\u4f4d\uff0c\u5c0f\u65782\u4f4d", 1000);
                CreateSystemPriceRateController.this.tfRate.setText("");
            }
        }
    };

    public void init(PriceRateModel priceRateModel, String string, String string2, String string3, String string4) {
        this.model = priceRateModel;
        this.AA45 = string;
        this.AA46 = string2;
        this.year = string3;
        this.rateType = string4;
        this.tfYM.focusedProperty().addListener(this.YMListener);
        this.tfRate.focusedProperty().addListener(this.rateListener);
        this.btOK.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                boolean bl = true;
                if (StringProcess.isEmpty(CreateSystemPriceRateController.this.tfYM.getText()) || !BaseLandVerifyUtil.checkYearMonth(CreateSystemPriceRateController.this.tfYM.getText(), false)) {
                    JavaFXUtil.showToastMessageBox(new Stage(), "\u8acb\u78ba\u8a8d\u5e74\u6708\u683c\u5f0f\uff0cEX:10801", 1000);
                    bl = false;
                }
                if (StringProcess.isEmpty(CreateSystemPriceRateController.this.tfRate.getText()) || !CreateSystemPriceRateController.this.tfRate.getText().matches("^[-]?[0-9]{1,3}+(.[0-9]{0,2})?$")) {
                    JavaFXUtil.showToastMessageBox(new Stage(), "\u8acb\u78ba\u8a8d\u6307\u6578\uff0c\u6574\u65783\u4f4d\uff0c\u5c0f\u65782\u4f4d", 1000);
                    bl = false;
                }
                if (bl) {
                    if (CreateSystemPriceRateController.this.model.checkRateExist(CreateSystemPriceRateController.this.AA45, CreateSystemPriceRateController.this.AA46, CreateSystemPriceRateController.this.year, CreateSystemPriceRateController.this.rateType, CreateSystemPriceRateController.this.tfYM.getText(), CreateSystemPriceRateController.this.tfRate.getText())) {
                        if (CreateSystemPriceRateController.this.model.addPriceRate(CreateSystemPriceRateController.this.AA45, CreateSystemPriceRateController.this.AA46, CreateSystemPriceRateController.this.year, CreateSystemPriceRateController.this.rateType, CreateSystemPriceRateController.this.tfYM.getText(), CreateSystemPriceRateController.this.tfRate.getText())) {
                            Node node = (Node)actionEvent.getSource();
                            Stage stage = (Stage)node.getScene().getWindow();
                            stage.close();
                        } else {
                            JavaFXUtil.showToastMessageBox(new Stage(), "\u65b0\u589e\u5931\u6557!!", 1000);
                        }
                    } else {
                        JavaFXUtil.showToastMessageBox(new Stage(), "\u65b0\u589e\u5e74\u6708\u5df2\u5b58\u5728\u8acb\u78ba\u8a8d!!", 1000);
                    }
                }
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
}

