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
 *  javafx.scene.control.RadioButton
 *  javafx.scene.control.TextField
 *  javafx.scene.layout.AnchorPane
 */
package com.wfusion.baseland.estimate;

import com.wfusion.baseland.SQLiteDataProviderModel;
import com.wfusion.baseland.basic.BaseLandDialog;
import com.wfusion.baseland.estimate.EstimateController;
import com.wfusion.baseland.estimate.EstimateModel;
import com.wfusion.baseland.estimate.EstimateReport6Model;
import com.wfusion.baseland.system.GlossaryModel;
import com.wfusion.fx.node.AbsTextField;
import com.wfusion.fx.node.DecimalField;
import com.wfusion.fx.node.StringArea;
import com.wfusion.fx.node.StringField;
import com.wfusion.fx.util.JavaFXUtil;
import com.wfusion.util.BigDecimalUtil;
import com.wfusion.util.DateTime;
import com.wfusion.util.OptionPair;
import com.wfusion.util.StringProcess;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import moiland.baseland.bo.SaveCheckBo;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_INSTRU;

public class EstimateReport6Controller {
    @FXML
    private DecimalField cre31;
    @FXML
    private StringField cre01;
    @FXML
    private ComboBox<OptionPair> cre06;
    @FXML
    private StringField cre03;
    @FXML
    private DecimalField cre07;
    @FXML
    private DecimalField cre27;
    @FXML
    private DecimalField cre29;
    @FXML
    private StringArea cre58;
    @FXML
    private RadioButton CRE05_2;
    @FXML
    private RadioButton CRE05_1;
    @FXML
    private StringField cre05f;
    @FXML
    private ComboBox<String> cbMonth;
    @FXML
    private DecimalField cre091;
    @FXML
    private DecimalField cre09;
    @FXML
    private ComboBox<String> cre24;
    @FXML
    private StringField cre23;
    @FXML
    private StringField cre33;
    @FXML
    private ComboBox<OptionPair> cre28;
    @FXML
    private Button btSave1;
    @FXML
    private AnchorPane report6;
    @FXML
    private ComboBox<OptionPair> cbLiteral;
    @FXML
    private Button btAddLiteral;
    @FXML
    private Label lbPrompt;
    EstimateController parentController = null;
    EstimateReport6Model model = new EstimateReport6Model();
    boolean isFirstRefresh = true;
    SaveCheckBo checkbo = new SaveCheckBo(EstimateModel.BASELANDBEAN);
    ChangeListener<Boolean> lostFocusListener = new ChangeListener<Boolean>(){

        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean bl, Boolean bl2) {
            if (!bl2.booleanValue()) {
                EstimateReport6Controller.this.reCal();
            }
        }
    };

    public void init(BaseLandDialog baseLandDialog, EstimateController estimateController) {
        this.parentController = estimateController;
        this.initCombox();
        this.initButton();
        this.initRadioButton();
        this.initTextField();
        this.cre091.textProperty().addListener((ChangeListener)new ChangeListener<String>(){

            public void changed(ObservableValue<? extends String> observableValue, String string, String string2) {
                String string3 = string2;
                String string4 = (String)EstimateReport6Controller.this.cbMonth.getSelectionModel().getSelectedItem();
                String string5 = DateTime.m2y(string3, string4);
                EstimateReport6Controller.this.model.voValue.put("cre09", StringProcess.parserDouble(string5));
                EstimateReport6Controller.this.cre09.setValue(string5);
            }
        });
        this.cre23.textProperty().addListener((ChangeListener)new ChangeListener<String>(){

            public void changed(ObservableValue<? extends String> observableValue, String string, String string2) {
                if (!EstimateReport6Controller.this.isFirstRefresh && !StringProcess.isEmpty(string2)) {
                    EstimateReport6Controller.this.lbPrompt.setVisible(false);
                } else {
                    EstimateReport6Controller.this.lbPrompt.setVisible(true);
                }
            }
        });
    }

    private void initTextField() {
        String string = EstimateModel.BASELANDBEAN.voRentExt.getTableName().toUpperCase();
        for (Node node : this.report6.getChildren()) {
            String[] stringArray;
            String string2;
            if (node instanceof DecimalField) {
                string2 = node.getId().toUpperCase();
                stringArray = SaveCheckBo.columnSet.get(string + "_" + string2);
                if (stringArray != null && stringArray.length == 4) {
                    ((DecimalField)node).setName(stringArray[0]);
                    ((DecimalField)node).setPrecision(StringProcess.parserInt(stringArray[2]));
                    ((DecimalField)node).setScale(StringProcess.parserInt(stringArray[3]));
                }
                node.focusedProperty().addListener(this.lostFocusListener);
                continue;
            }
            if (node instanceof StringField) {
                string2 = node.getId().toUpperCase();
                stringArray = SaveCheckBo.columnSet.get(string + "_" + string2);
                if (stringArray != null && stringArray.length == 4) {
                    ((StringField)node).setName(stringArray[0]);
                    ((StringField)node).setMaxLength(StringProcess.parserInt(stringArray[2]));
                    ((StringField)node).setMinLength(StringProcess.parserInt(stringArray[3]));
                }
                node.focusedProperty().addListener(this.lostFocusListener);
                continue;
            }
            if (node instanceof StringArea) {
                string2 = node.getId().toUpperCase();
                stringArray = SaveCheckBo.columnSet.get(string + "_" + string2);
                if (stringArray != null && stringArray.length == 4) {
                    ((StringArea)node).setName(stringArray[0]);
                    ((StringArea)node).setMaxLength(StringProcess.parserInt(stringArray[2]));
                    ((StringArea)node).setMinLength(StringProcess.parserInt(stringArray[3]));
                }
                node.focusedProperty().addListener(this.lostFocusListener);
                continue;
            }
            if (!(node instanceof TextField)) continue;
            node.focusedProperty().addListener(this.lostFocusListener);
        }
    }

    private void initCombox() {
        this.cbMonth.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getMonthList(0)));
        this.cbMonth.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<String>(){

            public void changed(ObservableValue<? extends String> observableValue, String string, String string2) {
                String string3 = EstimateReport6Controller.this.cre091.getValue().toString();
                String string4 = string2;
                String string5 = DateTime.m2y(string3, string4);
                EstimateReport6Controller.this.model.voValue.put("cre09", StringProcess.parserDouble(string5));
                EstimateReport6Controller.this.cre09.setValue(string5);
                EstimateReport6Controller.this.reCal();
            }
        });
        this.cre06.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getInstruCodeList()));
        this.cre06.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                if (EstimateReport6Controller.this.isFirstRefresh || optionPair2 == null) {
                    return;
                }
                EstimateReport6Controller.this.model.voValue.put("cre06", optionPair2.getValue());
                NVO_BASELAND_INSTRU nVO_BASELAND_INSTRU = SQLiteDataProviderModel.getMAPINSTRU().get(optionPair2.getValue());
                EstimateReport6Controller.this.cre27.setValue(String.valueOf(nVO_BASELAND_INSTRU.getYear_limits()));
                new BigDecimalUtil();
                EstimateReport6Controller.this.cre29.setValue(String.valueOf((int)BigDecimalUtil.round(nVO_BASELAND_INSTRU.getResidual_rate(), 0)));
                String string = EstimateReport6Controller.this.cre03.getValue().toString();
                if (!StringProcess.isEmpty(string)) {
                    int n = EstimateReport6Controller.this.getInstruStdPrice(optionPair2.getValue(), StringProcess.parserInt(string));
                    EstimateReport6Controller.this.cre31.setValue(String.valueOf(n));
                }
                EstimateReport6Controller.this.reCal();
            }
        });
        this.cre07.textProperty().addListener((ChangeListener)new ChangeListener<String>(){

            public void changed(ObservableValue<? extends String> observableValue, String string, String string2) {
                if (!StringProcess.isEmpty(string2)) {
                    EstimateModel.BASELANDBEAN.voRentExt.setCre07ori(Double.parseDouble(string2.replaceAll(",", "")));
                }
            }
        });
        this.cre24.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getMonthList(1)));
        this.cre24.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<String>(){

            public void changed(ObservableValue<? extends String> observableValue, String string, String string2) {
                EstimateReport6Controller.this.model.voValue.put("cre24", string2);
                EstimateReport6Controller.this.reCal();
            }
        });
        this.cre28.setItems(FXCollections.observableArrayList(this.parentController.model.getCS25List()));
        this.cre28.getSelectionModel().selectFirst();
        GlossaryModel glossaryModel = new GlossaryModel();
        glossaryModel.query("RENT_EXT", "RENT_CRE58", 0, "");
        this.cbLiteral.setItems(FXCollections.observableArrayList(glossaryModel.getDataOptionpairs()));
        this.cbLiteral.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                if (optionPair2 != null && !StringProcess.isEmpty(optionPair2.getValue())) {
                    EstimateReport6Controller.this.cre58.setText(optionPair2.getAlias());
                }
            }
        });
    }

    private void initButton() {
        this.btAddLiteral.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                String string = EstimateReport6Controller.this.cre58.getText();
                if (!StringProcess.isEmpty(string)) {
                    GlossaryModel glossaryModel = new GlossaryModel();
                    glossaryModel.addLiteral(string, "RENT_EXT", "RENT_CRE58");
                    glossaryModel.query("RENT_EXT", "RENT_CRE58", 0, "");
                    EstimateReport6Controller.this.cbLiteral.setItems(FXCollections.observableArrayList(glossaryModel.getDataOptionpairs()));
                    EstimateReport6Controller.this.cbLiteral.getSelectionModel().selectLast();
                    JavaFXUtil.showToastMessageBox(EstimateReport6Controller.this.parentController.dialog.getStage(), "\u5df2\u65b0\u589e\u8fad\u5eab", 1500);
                }
            }
        });
    }

    private void initRadioButton() {
        this.CRE05_1.selectedProperty().addListener((ChangeListener)new ChangeListener<Boolean>(){

            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean bl, Boolean bl2) {
                if (bl2.booleanValue()) {
                    EstimateReport6Controller.this.cre05f.setDisable(true);
                    if (EstimateModel.BASELANDBEAN.voRent.getCr04() <= 0.0) {
                        EstimateModel.BASELANDBEAN.voRent.setCr04(EstimateModel.BASELANDBEAN.voMain.getAa10());
                    }
                    EstimateReport6Controller.this.reCal();
                }
            }
        });
        this.CRE05_2.selectedProperty().addListener((ChangeListener)new ChangeListener<Boolean>(){

            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean bl, Boolean bl2) {
                if (bl2.booleanValue()) {
                    EstimateReport6Controller.this.cre05f.setDisable(false);
                    EstimateReport6Controller.this.reCal();
                }
            }
        });
    }

    public void refresh() {
        this.isFirstRefresh = true;
        this.updateUIFromVo();
        this.refreshCombox();
        this.lbPrompt.setVisible(StringProcess.isEmpty(this.cre23.getValue()));
        this.isFirstRefresh = false;
    }

    private void refreshCombox() {
        this.cre06.getSelectionModel().select(StringProcess.parserInt((String)this.model.voValue.get("cre06")));
        this.cre24.getSelectionModel().select((Object)((String)this.model.voValue.get("cre24")));
    }

    void reCal() {
        this.updateVoFromUI();
        this.model.reCal();
        this.updateUIFromVo();
    }

    protected void updateVoFromUI() {
        if (!StringProcess.isEmpty(this.cre01.getValue().toString())) {
            this.cre01.setValue(this.checkBuildCode(this.cre01.getValue().toString()));
        }
        for (Node node : this.report6.getChildren()) {
            if (!this.model.voValue.containsKey(node.getId())) continue;
            if (node instanceof DecimalField) {
                this.model.voValue.put(node.getId(), ((DecimalField)node).getValue().toString());
            } else if (node instanceof StringField) {
                this.model.voValue.put(node.getId(), ((StringField)node).getValue().toString());
            } else if (node instanceof TextField) {
                this.model.voValue.put(node.getId(), ((TextField)node).getText().toString());
            }
            if (!(node instanceof StringArea)) continue;
            this.model.voValue.put(node.getId(), ((StringArea)node).getValue().toString());
        }
        if (this.CRE05_1.isSelected()) {
            this.model.voValue.put("cre05", "0");
        } else {
            this.model.voValue.put("cre05", "1");
        }
        this.model.voValue.put("cre28", ((OptionPair)this.cre28.getValue()).getValue());
        this.model.updateVo();
    }

    private String checkBuildCode(String string) {
        String string2 = "^[0-9]{8}$";
        String string3 = "^[0-9]{1,5}$";
        String string4 = "^[0-9]{1,5}\\-[0-9]{1,3}$";
        if (string.matches(string2) || string.matches(string3) || string.matches(string4)) {
            string = StringProcess.parserShortBuildNo2Long(string);
        } else {
            string = "";
            JavaFXUtil.showErrorMessageBox("\u5efa\u865f\u683c\u5f0f\u932f\u8aa4\uff0c\u8acb\u78ba\u8a8d!");
        }
        return string;
    }

    protected void updateUIFromVo() {
        this.model.updateHashMapValues();
        for (Node node : this.report6.getChildren()) {
            String string = node.getId();
            if (string == null || !this.model.voValue.containsKey(string)) continue;
            if (node instanceof AbsTextField) {
                ((AbsTextField)node).setValue(this.model.voValue.get(string).toString());
                continue;
            }
            if (node instanceof StringArea) {
                ((StringArea)node).setText(this.model.voValue.get(string).toString());
                continue;
            }
            if (!(node instanceof TextField)) continue;
            ((TextField)node).setText(this.model.voValue.get(string).toString());
        }
        if ("1".equals(this.model.voValue.get("cre05"))) {
            this.CRE05_2.setSelected(true);
        } else {
            this.CRE05_1.setSelected(true);
        }
        String[] stringArray = DateTime.y2m(this.model.voValue.get("cre09").toString());
        this.cre091.setValue(String.valueOf(stringArray[0]));
        this.cbMonth.getSelectionModel().select((Object)stringArray[1]);
    }

    private int getInstruStdPrice(String string, int n) {
        return this.parentController.model.getInstruStdPrice(string, n);
    }
}

