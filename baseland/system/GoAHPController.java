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
 *  javafx.scene.control.TextField
 *  javafx.stage.Stage
 */
package com.wfusion.baseland.system;

import com.wfusion.baseland.SQLiteDataProviderModel;
import com.wfusion.baseland.basic.Controller;
import com.wfusion.baseland.basic.IBaseLandDialog;
import com.wfusion.baseland.system.GoAHPModel;
import com.wfusion.fx.util.JavaFXUtil;
import com.wfusion.util.OptionPair;
import com.wfusion.util.StringProcess;
import java.util.ArrayList;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_AHP;

public class GoAHPController
extends Controller {
    @FXML
    private ComboBox<OptionPair> cbYear;
    @FXML
    private ComboBox<OptionPair> cbAA45;
    @FXML
    private Button btEdit;
    @FXML
    private Button btSave;
    @FXML
    private Button btExit;
    @FXML
    private TextField comp_price_type;
    @FXML
    private TextField comp_diff_abs;
    @FXML
    private TextField comp_diff_items;
    @FXML
    private TextField comp_month;
    @FXML
    private TextField comp_near;
    @FXML
    private TextField comp_diff_limit;
    @FXML
    private TextField rent_month_money;
    @FXML
    private TextField rent_buildcost_ext;
    @FXML
    private TextField rent_years;
    @FXML
    private TextField rent_capitalization;
    @FXML
    private TextField dev_floors_plan;
    @FXML
    private TextField dev_fouds_rate;
    @FXML
    private TextField dev_sale_money;
    @FXML
    private TextField dev_buildcost_ext;
    @FXML
    private TextField credibility;
    @FXML
    private TextField similarity;
    @FXML
    private TextField value_type;
    GoAHPModel model = new GoAHPModel();
    HashMap<String, Object> voValue = null;

    @Override
    public void init(IBaseLandDialog iBaseLandDialog) {
        super.init(iBaseLandDialog);
        this.cbAA45.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getAA45List()));
        this.cbAA45.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                String string = null;
                if (GoAHPController.this.cbAA45.getSelectionModel().getSelectedItem() != null) {
                    string = ((OptionPair)GoAHPController.this.cbAA45.getSelectionModel().getSelectedItem()).getValue();
                }
                if (!StringProcess.isEmpty(string)) {
                    GoAHPController.this.cbYear.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getAhpParamYearList(string)));
                }
            }
        });
        this.cbYear.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getDefaultList()));
        this.initButton();
    }

    private void initButton() {
        this.btExit.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                Node node = (Node)actionEvent.getSource();
                Stage stage = (Stage)node.getScene().getWindow();
                stage.close();
            }
        });
        this.btSave.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                String string = null;
                String string2 = null;
                if (GoAHPController.this.cbAA45.getSelectionModel().getSelectedItem() != null) {
                    string = ((OptionPair)GoAHPController.this.cbAA45.getSelectionModel().getSelectedItem()).getValue();
                }
                if (GoAHPController.this.cbYear.getSelectionModel().getSelectedItem() != null) {
                    string2 = ((OptionPair)GoAHPController.this.cbYear.getSelectionModel().getSelectedItem()).getValue();
                }
                if (!StringProcess.isEmpty(string) && !StringProcess.isEmpty(string2) && GoAHPController.this.saveValuesFromVo(string, string2)) {
                    JavaFXUtil.showToastMessageBox(GoAHPController.this.selfDialog.getStage(), "\u5132\u5b58\u6210\u529f", 500);
                }
            }
        });
        this.btEdit.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                String string = null;
                String string2 = null;
                if (GoAHPController.this.cbAA45.getSelectionModel().getSelectedItem() != null) {
                    string = ((OptionPair)GoAHPController.this.cbAA45.getSelectionModel().getSelectedItem()).getValue();
                }
                if (GoAHPController.this.cbYear.getSelectionModel().getSelectedItem() != null) {
                    string2 = ((OptionPair)GoAHPController.this.cbYear.getSelectionModel().getSelectedItem()).getValue();
                }
                if (!StringProcess.isEmpty(string) && !StringProcess.isEmpty(string2)) {
                    NVO_BASELAND_AHP nVO_BASELAND_AHP = GoAHPController.this.model.getEditData(string, string2);
                    GoAHPController.this.getValuesFromVo(nVO_BASELAND_AHP);
                    if (!nVO_BASELAND_AHP.isHaveData()) {
                        JavaFXUtil.showToastMessageBox(GoAHPController.this.selfDialog.getStage(), "\u8cc7\u6599\u5c1a\u672a\u65b0\u589e\uff0c\u8acb\u7de8\u8f2f\u4e26\u5132\u5b58", 2000);
                    }
                }
            }
        });
    }

    protected boolean saveValuesFromVo(String string, String string2) {
        ArrayList<Node> arrayList = JavaFXUtil.getAllNodes(this.cbAA45.getScene().getRoot());
        for (Node node : arrayList) {
            if (!this.voValue.containsKey(node.getId()) || !(node instanceof TextField)) continue;
            this.voValue.put(node.getId(), ((TextField)node).getText().toString());
        }
        NVO_BASELAND_AHP nVO_BASELAND_AHP = new NVO_BASELAND_AHP();
        nVO_BASELAND_AHP.setBeanByHashMap(this.voValue, false);
        return this.model.save(nVO_BASELAND_AHP, string, string2);
    }

    protected void getValuesFromVo(NVO_BASELAND_AHP nVO_BASELAND_AHP) {
        this.voValue = nVO_BASELAND_AHP.getFieldToHashMapExport();
        for (String string : this.voValue.keySet()) {
            Node node = this.cbAA45.getScene().lookup("#" + string);
            if (node == null || !(node instanceof TextField)) continue;
            ((TextField)node).setText(this.voValue.get(string).toString());
        }
    }

    @Override
    public void refresh() {
    }
}

