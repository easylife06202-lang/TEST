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
 *  javafx.scene.layout.AnchorPane
 *  javafx.stage.Stage
 */
package com.wfusion.baseland.system;

import com.wfusion.baseland.SQLiteDataProviderModel;
import com.wfusion.baseland.basic.Controller;
import com.wfusion.baseland.basic.IBaseLandDialog;
import com.wfusion.baseland.estimate.EstimateModel;
import com.wfusion.baseland.system.ReportParamModel;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import moiland.baseland.bo.SaveCheckBo;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_REPORT_PARAM;
import moiland.baseland.util.BaseLandCode;

public class ReportParamController
extends Controller {
    @FXML
    private ComboBox<OptionPair> cbAA45;
    @FXML
    private ComboBox<OptionPair> cbYear;
    @FXML
    private ComboBox<OptionPair> cbRateType;
    @FXML
    private TextField build_cost_basedate;
    @FXML
    private TextField build_benefit_rate;
    @FXML
    private TextField design_ratio;
    @FXML
    private TextField presale_ratio;
    @FXML
    private TextField insure_rate;
    @FXML
    private TextField presale_rate;
    @FXML
    private TextField mc;
    @FXML
    private TextField land_benefit_rate;
    @FXML
    private TextField debt_ratio;
    @FXML
    private TextField owner_rate;
    @FXML
    private TextField debt_rate;
    @FXML
    private TextField owner_ratio;
    @FXML
    private TextField devp_rate;
    @FXML
    private TextField tax_ratio;
    @FXML
    private TextField reset_rate1;
    @FXML
    private TextField reset_rate2;
    @FXML
    private TextField maintian_rate;
    @FXML
    private TextField manage_ratio;
    @FXML
    private TextField ad_ratio;
    @FXML
    private TextField ben_manage_ratio;
    @FXML
    private AnchorPane showPanel;
    @FXML
    private Button btSave;
    @FXML
    private Button btEdit;
    @FXML
    private Button btExit;
    ReportParamModel model = new ReportParamModel();
    HashMap<String, Object> voValue = null;
    SaveCheckBo checkbo = new SaveCheckBo(EstimateModel.BASELANDBEAN);
    HashMap<String, String> checkCols = new HashMap();
    ChangeListener<Boolean> lostFocusListener_owner_rate = new ChangeListener<Boolean>(){

        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean bl, Boolean bl2) {
            if (!bl2.booleanValue()) {
                ReportParamController.this.checkLen(ReportParamController.this.owner_rate);
            }
        }
    };
    ChangeListener<Boolean> lostFocusListener_debt_rate = new ChangeListener<Boolean>(){

        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean bl, Boolean bl2) {
            if (!bl2.booleanValue()) {
                ReportParamController.this.checkLen(ReportParamController.this.debt_rate);
            }
        }
    };
    ChangeListener<Boolean> lostFocusListener_debt_ratio = new ChangeListener<Boolean>(){

        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean bl, Boolean bl2) {
            if (!bl2.booleanValue()) {
                ReportParamController.this.checkLen(ReportParamController.this.debt_ratio);
            }
        }
    };
    ChangeListener<Boolean> lostFocusListener_presale_rate = new ChangeListener<Boolean>(){

        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean bl, Boolean bl2) {
            if (!bl2.booleanValue()) {
                ReportParamController.this.checkLen(ReportParamController.this.presale_rate);
            }
        }
    };
    ChangeListener<Boolean> lostFocusListener_presale_ratio = new ChangeListener<Boolean>(){

        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean bl, Boolean bl2) {
            if (!bl2.booleanValue()) {
                ReportParamController.this.checkLen(ReportParamController.this.presale_ratio);
            }
        }
    };
    ChangeListener<Boolean> lostFocusListener_owner_ratio = new ChangeListener<Boolean>(){

        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean bl, Boolean bl2) {
            if (!bl2.booleanValue()) {
                ReportParamController.this.checkLen(ReportParamController.this.owner_ratio);
            }
        }
    };

    @Override
    public void init(IBaseLandDialog iBaseLandDialog) {
        super.init(iBaseLandDialog);
        this.initComboBox();
        this.initButton();
        this.checkCols.put("owner_rate", "CS09");
        this.checkCols.put("owner_ratio", "CS10");
        this.checkCols.put("debt_rate", "CS12");
        this.checkCols.put("debt_ratio", "CS13");
        this.checkCols.put("presale_rate", "CS15");
        this.checkCols.put("presale_ratio", "CS16");
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
                String string3 = null;
                String string4 = null;
                if (ReportParamController.this.cbAA45.getSelectionModel().getSelectedItem() != null) {
                    string = ((OptionPair)ReportParamController.this.cbAA45.getSelectionModel().getSelectedItem()).getValue();
                }
                if (ReportParamController.this.cbYear.getSelectionModel().getSelectedItem() != null) {
                    string2 = ((OptionPair)ReportParamController.this.cbYear.getSelectionModel().getSelectedItem()).getValue();
                }
                if (ReportParamController.this.build_cost_basedate != null) {
                    string3 = ReportParamController.this.build_cost_basedate.getText();
                }
                if (ReportParamController.this.cbRateType.getSelectionModel().getSelectedItem() != null) {
                    string4 = ((OptionPair)ReportParamController.this.cbRateType.getSelectionModel().getSelectedItem()).getValue();
                }
                if (!StringProcess.isEmpty(string3) && !StringProcess.isEmpty(string4)) {
                    if (!StringProcess.isEmpty(string) && !StringProcess.isEmpty(string2) && ReportParamController.this.saveValuesFromVo(string, string2)) {
                        JavaFXUtil.showToastMessageBox(ReportParamController.this.selfDialog.getStage(), "\u5132\u5b58\u6210\u529f", 500);
                    }
                } else {
                    JavaFXUtil.showErrorMessageBox("1.\u8acb\u586b\u5beb\u71df\u9020\u65bd\u5de5\u8cbb\u55ae\u50f9\u57fa\u6e96\u65e5\u671f  \r\n2.\u8acb\u9078\u64c7\u50f9\u683c\u65e5\u671f\u8abf\u6574\u6307\u6578\u985e\u578b");
                }
            }
        });
        this.btEdit.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                String string = null;
                String string2 = null;
                if (ReportParamController.this.cbAA45.getSelectionModel().getSelectedItem() != null) {
                    string = ((OptionPair)ReportParamController.this.cbAA45.getSelectionModel().getSelectedItem()).getValue();
                }
                if (ReportParamController.this.cbYear.getSelectionModel().getSelectedItem() != null) {
                    string2 = ((OptionPair)ReportParamController.this.cbYear.getSelectionModel().getSelectedItem()).getValue();
                }
                if (!StringProcess.isEmpty(string) && !StringProcess.isEmpty(string2)) {
                    NVO_BASELAND_REPORT_PARAM nVO_BASELAND_REPORT_PARAM = ReportParamController.this.model.getEditData(string, string2);
                    ReportParamController.this.getValuesFromVo(nVO_BASELAND_REPORT_PARAM);
                    ReportParamController.this.showPanel.setDisable(false);
                    if (!nVO_BASELAND_REPORT_PARAM.isHaveData()) {
                        JavaFXUtil.showToastMessageBox(ReportParamController.this.selfDialog.getStage(), "\u8cc7\u6599\u5c1a\u672a\u65b0\u589e\uff0c\u8acb\u7de8\u8f2f\u4e26\u5132\u5b58", 2000);
                    }
                }
            }
        });
        this.owner_rate.focusedProperty().addListener(this.lostFocusListener_owner_rate);
        this.owner_ratio.focusedProperty().addListener(this.lostFocusListener_owner_ratio);
        this.debt_rate.focusedProperty().addListener(this.lostFocusListener_debt_rate);
        this.debt_ratio.focusedProperty().addListener(this.lostFocusListener_debt_ratio);
        this.presale_rate.focusedProperty().addListener(this.lostFocusListener_presale_rate);
        this.presale_ratio.focusedProperty().addListener(this.lostFocusListener_presale_ratio);
    }

    private void checkLen(TextField textField) {
        String string;
        boolean bl = false;
        String[] stringArray = SaveCheckBo.columnSet.get("BASELAND_SELL_" + this.checkCols.get(textField.getId()));
        if (stringArray != null && stringArray.length == 4 && !StringProcess.isEmpty(string = textField.getText())) {
            int n = string.indexOf(".");
            int n2 = StringProcess.parserInt(stringArray[2]) - StringProcess.parserInt(stringArray[3]);
            int n3 = StringProcess.parserInt(stringArray[3]);
            if (n > -1) {
                if (string.substring(0, n).length() > n2 || string.substring(n + 1).length() > n3) {
                    bl = true;
                }
            } else if (string.length() > n2) {
                bl = true;
            }
            if (bl) {
                JavaFXUtil.showErrorMessageBox(this.getFormatStr(stringArray[0], n2, n3));
                textField.setText("");
            }
        }
    }

    private String getFormatStr(String string, int n, int n2) {
        return "[ " + string + " ] \u683c\u5f0f\u932f\u8aa4\uff0c\u6574\u6578 [ " + n + " ] \u4f4d\uff0c\u5c0f\u6578 [ " + n2 + " ] \u4f4d";
    }

    protected boolean saveValuesFromVo(String string, String string2) {
        ArrayList<Node> arrayList = JavaFXUtil.getAllNodes(this.cbAA45.getScene().getRoot());
        for (Node object2 : arrayList) {
            if (!this.voValue.containsKey(object2.getId()) || !(object2 instanceof TextField)) continue;
            this.voValue.put(object2.getId(), ((TextField)object2).getText().toString());
        }
        Object object3 = null;
        if (this.cbRateType.getSelectionModel().getSelectedItem() != null) {
            object3 = ((OptionPair)this.cbRateType.getSelectionModel().getSelectedItem()).getValue();
        }
        if (!StringProcess.isEmpty(object3)) {
            this.voValue.put("price_rate_type", object3);
        }
        NVO_BASELAND_REPORT_PARAM nVO_BASELAND_REPORT_PARAM = new NVO_BASELAND_REPORT_PARAM();
        nVO_BASELAND_REPORT_PARAM.setBeanByHashMap(this.voValue, false);
        return this.model.save(nVO_BASELAND_REPORT_PARAM, string, string2);
    }

    protected void getValuesFromVo(NVO_BASELAND_REPORT_PARAM nVO_BASELAND_REPORT_PARAM) {
        this.voValue = nVO_BASELAND_REPORT_PARAM.getFieldToHashMapExport();
        for (String string : this.voValue.keySet()) {
            Node node = this.cbAA45.getScene().lookup("#" + string);
            if (node == null || !(node instanceof TextField)) continue;
            ((TextField)node).setText(this.voValue.get(string).toString());
        }
        this.cbRateType.getSelectionModel().select((Object)new OptionPair(nVO_BASELAND_REPORT_PARAM.getPrice_rate_type(), BaseLandCode.getPriceRateMainItem().get(nVO_BASELAND_REPORT_PARAM.getPrice_rate_type())));
    }

    private void initComboBox() {
        this.cbAA45.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getAA45List()));
        this.cbAA45.getSelectionModel().selectFirst();
        this.cbAA45.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                String string = null;
                if (ReportParamController.this.cbAA45.getSelectionModel().getSelectedItem() != null) {
                    string = ((OptionPair)ReportParamController.this.cbAA45.getSelectionModel().getSelectedItem()).getValue();
                }
                if (!StringProcess.isEmpty(string)) {
                    ReportParamController.this.cbYear.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getReportParamYearList(string)));
                } else {
                    ReportParamController.this.showPanel.setDisable(true);
                    ReportParamController.this.btEdit.setDisable(true);
                }
            }
        });
        this.cbYear.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getDefaultList()));
        this.cbYear.getSelectionModel().selectFirst();
        this.cbYear.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                String string = null;
                if (ReportParamController.this.cbYear.getSelectionModel().getSelectedItem() != null) {
                    string = ((OptionPair)ReportParamController.this.cbYear.getSelectionModel().getSelectedItem()).getValue();
                }
                ReportParamController.this.btEdit.setDisable(StringProcess.isEmpty(string));
                ReportParamController.this.showPanel.setDisable(StringProcess.isEmpty(string));
            }
        });
        ArrayList<OptionPair> arrayList = this.getRateTypeCb(SQLiteDataProviderModel.getPriceRateTypeCode());
        this.cbRateType.setItems(FXCollections.observableArrayList(arrayList));
    }

    private ArrayList<OptionPair> getRateTypeCb(ArrayList<OptionPair> arrayList) {
        ArrayList<OptionPair> arrayList2 = new ArrayList<OptionPair>();
        for (OptionPair optionPair : arrayList) {
            if (optionPair.getValue().contains("_")) continue;
            arrayList2.add(optionPair);
        }
        return arrayList2;
    }

    @Override
    public void refresh() {
    }
}

