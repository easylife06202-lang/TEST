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
 *  javafx.fxml.FXMLLoader
 *  javafx.scene.Node
 *  javafx.scene.Parent
 *  javafx.scene.Scene
 *  javafx.scene.control.Button
 *  javafx.scene.control.ComboBox
 *  javafx.scene.control.Label
 *  javafx.scene.control.RadioButton
 *  javafx.scene.control.TextField
 *  javafx.scene.layout.AnchorPane
 *  javafx.scene.text.Text
 *  javafx.stage.Modality
 *  javafx.stage.Stage
 *  javafx.stage.StageStyle
 *  javafx.stage.Window
 */
package com.wfusion.baseland.estimate;

import com.wfusion.baseland.SQLiteDataProviderModel;
import com.wfusion.baseland.basic.BaseLandDialog;
import com.wfusion.baseland.estimate.EstimateController;
import com.wfusion.baseland.estimate.EstimateModel;
import com.wfusion.baseland.estimate.EstimateReport4Model;
import com.wfusion.baseland.estimate.FloorEffectController;
import com.wfusion.baseland.estimate.ImageBrowserDialog;
import com.wfusion.baseland.system.GlossaryModel;
import com.wfusion.fx.node.AbsTextField;
import com.wfusion.fx.node.DecimalField;
import com.wfusion.fx.node.StringArea;
import com.wfusion.fx.node.StringField;
import com.wfusion.fx.util.JavaFXUtil;
import com.wfusion.fx.util.NumberFormater;
import com.wfusion.util.DateTime;
import com.wfusion.util.OptionPair;
import com.wfusion.util.StringProcess;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import moiland.baseland.bo.SaveCheckBo;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_INSTRU;
import moiland.baseland.verify.BaseLandVerifyUtil;

public class EstimateReport4Controller {
    @FXML
    private AnchorPane report4;
    @FXML
    private AnchorPane panel1;
    @FXML
    private AnchorPane panel2;
    @FXML
    private AnchorPane panel3;
    @FXML
    private AnchorPane panel4;
    @FXML
    private AnchorPane panel5;
    @FXML
    private AnchorPane panel6;
    @FXML
    private ComboBox<String> cs21;
    @FXML
    private StringField aa49;
    @FXML
    private ComboBox<OptionPair> aa48;
    @FXML
    private ComboBox<OptionPair> dist;
    @FXML
    private DecimalField cs081;
    @FXML
    private DecimalField cs28;
    @FXML
    private DecimalField cs29;
    @FXML
    private DecimalField cs66;
    @FXML
    private DecimalField cs26;
    @FXML
    private DecimalField cs24;
    @FXML
    private ComboBox<OptionPair> cs25;
    @FXML
    private DecimalField cs50;
    @FXML
    private StringField cs58;
    @FXML
    private DecimalField cs59;
    @FXML
    private StringArea cs56;
    @FXML
    private RadioButton havebuildN;
    @FXML
    private StringField ed49;
    @FXML
    private RadioButton havebuildY;
    @FXML
    private ComboBox<String> cbMonth;
    @FXML
    private DecimalField cs08;
    @FXML
    private StringField cs04f;
    @FXML
    private DecimalField cs48;
    @FXML
    private RadioButton CS04_1;
    @FXML
    private ComboBox<OptionPair> cs05;
    @FXML
    private RadioButton CS04_2;
    @FXML
    private StringField cs02;
    @FXML
    private StringField cs03;
    @FXML
    private StringField cs01;
    @FXML
    private StringField cs30;
    @FXML
    private RadioButton floorAll;
    @FXML
    private Label lbPrompt;
    @FXML
    private RadioButton floorPart;
    @FXML
    private DecimalField cs64;
    @FXML
    private DecimalField cs65;
    @FXML
    private StringField land_position;
    @FXML
    private Button btFloorEffect;
    @FXML
    private ComboBox<OptionPair> cbLiteral;
    @FXML
    private Button btAddLiteral;
    @FXML
    private Label lbLabel1;
    @FXML
    private Label lbLabel2;
    @FXML
    private Label lbLabel3;
    @FXML
    private Label lbLabel4;
    @FXML
    private Label lbLabel5;
    @FXML
    private Label lbLabel6;
    @FXML
    private Label lbLabel531;
    @FXML
    private Label lbLabel591;
    @FXML
    private Text lbLabel31;
    @FXML
    private Text lbLabel51;
    @FXML
    private StringField cs52;
    @FXML
    private DecimalField cs53;
    @FXML
    private Button btPhotos;
    EstimateController parentController = null;
    EstimateReport4Model model = new EstimateReport4Model();
    BaseLandDialog manager;
    boolean isFirstRefresh = true;
    boolean notEmpty = false;
    SaveCheckBo checkbo = new SaveCheckBo(EstimateModel.BASELANDBEAN);
    ChangeListener<Boolean> lostFocusListener = new ChangeListener<Boolean>(){

        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean bl, Boolean bl2) {
            if (!bl2.booleanValue()) {
                EstimateReport4Controller.this.reCal();
            }
        }
    };

    public void init(BaseLandDialog baseLandDialog, final EstimateController estimateController) {
        this.manager = baseLandDialog;
        this.parentController = estimateController;
        this.initCombox();
        this.initButton();
        this.initRadioButton();
        this.initTextField();
        this.cs081.textProperty().addListener((ChangeListener)new ChangeListener<String>(){

            public void changed(ObservableValue<? extends String> observableValue, String string, String string2) {
                String string3 = string2;
                String string4 = (String)EstimateReport4Controller.this.cbMonth.getSelectionModel().getSelectedItem();
                String string5 = DateTime.m2y(string3, string4);
                EstimateReport4Controller.this.model.voValue.put("cs08", StringProcess.parserDouble(string5));
                EstimateReport4Controller.this.cs08.setValue(string5);
            }
        });
        this.cs02.textProperty().addListener((ChangeListener)new ChangeListener<String>(){

            public void changed(ObservableValue<? extends String> observableValue, String string, String string2) {
                if (!EstimateReport4Controller.this.isFirstRefresh && !StringProcess.isEmpty(string2)) {
                    if (EstimateReport4Controller.this.cs05.getSelectionModel().getSelectedItem() != null && !StringProcess.isEmpty(((OptionPair)EstimateReport4Controller.this.cs05.getSelectionModel().getSelectedItem()).getValue())) {
                        int n = EstimateReport4Controller.this.getInstruStdPrice(((OptionPair)EstimateReport4Controller.this.cs05.getSelectionModel().getSelectedItem()).getValue(), StringProcess.parserInt(string2));
                        EstimateReport4Controller.this.cs28.setValue(String.valueOf(n));
                    }
                    EstimateReport4Controller.this.reCal();
                }
            }
        });
        this.cs30.focusedProperty().addListener((ChangeListener)new ChangeListener<Boolean>(){
            String oldDate = "";

            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean bl, Boolean bl2) {
                if (EstimateReport4Controller.this.havebuildY.isSelected()) {
                    String string = EstimateReport4Controller.this.cs30.getValue().toString();
                    if (bl2.booleanValue() && !StringProcess.isEmpty(string)) {
                        this.oldDate = string;
                    }
                    if (!(EstimateReport4Controller.this.isFirstRefresh || bl2.booleanValue() || StringProcess.isEmpty(string))) {
                        if (!this.oldDate.equals(string)) {
                            if (BaseLandVerifyUtil.checkDate(string, EstimateReport4Controller.this.notEmpty)) {
                                double d = new EstimateModel().getAdjustRatio(string);
                                EstimateReport4Controller.this.cs29.setValue(NumberFormater.df2.format(d));
                                EstimateReport4Controller.this.lbPrompt.setVisible(false);
                                EstimateReport4Controller.this.reCal();
                            } else {
                                JavaFXUtil.showToastMessageBox(estimateController.dialog.getStage(), "\u4ea4\u6613\u65e5\u671f\u683c\u5f0f\u932f\u8aa4", 500);
                                EstimateReport4Controller.this.cs30.setValue("");
                                EstimateReport4Controller.this.lbPrompt.setVisible(true);
                            }
                        }
                    } else {
                        EstimateReport4Controller.this.lbPrompt.setVisible(true);
                    }
                }
            }
        });
    }

    private void initTextField() {
        String string = EstimateModel.BASELANDBEAN.voSell_2.getTableName().toUpperCase();
        for (Node node : this.report4.getChildren()) {
            String[] stringArray2;
            Object object;
            if (node instanceof DecimalField) {
                object = node.getId().toUpperCase();
                stringArray2 = SaveCheckBo.columnSet.get(string + "_" + (String)object);
                if (stringArray2 != null && stringArray2.length == 4) {
                    ((DecimalField)node).setName(stringArray2[0]);
                    ((DecimalField)node).setPrecision(StringProcess.parserInt(stringArray2[2]));
                    ((DecimalField)node).setScale(StringProcess.parserInt(stringArray2[3]));
                }
                node.focusedProperty().addListener(this.lostFocusListener);
            } else if (node instanceof StringField) {
                object = node.getId().toUpperCase();
                stringArray2 = SaveCheckBo.columnSet.get(string + "_" + (String)object);
                if (stringArray2 != null && stringArray2.length == 4) {
                    ((StringField)node).setName(stringArray2[0]);
                    ((StringField)node).setMaxLength(StringProcess.parserInt(stringArray2[2]));
                    ((StringField)node).setMinLength(StringProcess.parserInt(stringArray2[3]));
                }
                node.focusedProperty().addListener(this.lostFocusListener);
            } else if (node instanceof StringArea) {
                object = node.getId().toUpperCase();
                stringArray2 = SaveCheckBo.columnSet.get(string + "_" + (String)object);
                if (stringArray2 != null && stringArray2.length == 4) {
                    ((StringArea)node).setName(stringArray2[0]);
                    ((StringArea)node).setMaxLength(StringProcess.parserInt(stringArray2[2]));
                    ((StringArea)node).setMinLength(StringProcess.parserInt(stringArray2[3]));
                }
                node.focusedProperty().addListener(this.lostFocusListener);
            } else if (node instanceof TextField) {
                node.focusedProperty().addListener(this.lostFocusListener);
            }
            if (!(node instanceof AnchorPane)) continue;
            for (String[] stringArray2 : ((AnchorPane)node).getChildren()) {
                String[] stringArray3;
                String string2;
                if (stringArray2 instanceof DecimalField) {
                    string2 = stringArray2.getId().toUpperCase();
                    stringArray3 = SaveCheckBo.columnSet.get(string + "_" + string2);
                    if (stringArray3 != null && stringArray3.length == 4) {
                        ((DecimalField)stringArray2).setName(stringArray3[0]);
                        ((DecimalField)stringArray2).setPrecision(StringProcess.parserInt(stringArray3[2]));
                        ((DecimalField)stringArray2).setScale(StringProcess.parserInt(stringArray3[3]));
                    }
                    stringArray2.focusedProperty().addListener(this.lostFocusListener);
                    continue;
                }
                if (stringArray2 instanceof StringField) {
                    string2 = stringArray2.getId().toUpperCase();
                    stringArray3 = SaveCheckBo.columnSet.get(string + "_" + string2);
                    if (stringArray3 != null && stringArray3.length == 4) {
                        ((StringField)stringArray2).setName(stringArray3[0]);
                        ((StringField)stringArray2).setMaxLength(StringProcess.parserInt(stringArray3[2]));
                        ((StringField)stringArray2).setMinLength(StringProcess.parserInt(stringArray3[3]));
                    }
                    stringArray2.focusedProperty().addListener(this.lostFocusListener);
                    continue;
                }
                if (!(stringArray2 instanceof TextField)) continue;
                stringArray2.focusedProperty().addListener(this.lostFocusListener);
            }
        }
    }

    private void updateLandPosition() {
        Object object;
        String string = StringProcess.NULL(SQLiteDataProviderModel.getMapAA45().get(EstimateModel.BASELANDBEAN.queryBean.AA45));
        String string2 = "";
        String string3 = "";
        if (this.dist.getSelectionModel().getSelectedItem() != null) {
            String string4 = ((OptionPair)this.dist.getSelectionModel().getSelectedItem()).getValue();
            string2 = SQLiteDataProviderModel.getMapAA46().get(EstimateModel.BASELANDBEAN.queryBean.AA45).get(string4);
            if (this.aa48.getSelectionModel().getSelectedItem() != null) {
                String string5 = ((OptionPair)this.aa48.getSelectionModel().getSelectedItem()).getValue();
                object = SQLiteDataProviderModel.getAA48Map(EstimateModel.BASELANDBEAN.queryBean.AA45, EstimateModel.BASELANDBEAN.queryBean.office, string4);
                string3 = StringProcess.NULL((String)((HashMap)object).get(string5));
            }
        }
        object = StringProcess.parserShortLandNo2Long(this.aa49.getValue());
        object = StringProcess.getLandShort((String)object);
        if (StringProcess.isEmpty(string2)) {
            this.land_position.setValue("");
        } else {
            this.land_position.setValue(string + string2 + string3 + (StringProcess.isEmpty((String)object) ? "" : (String)object + "\u5730\u865f "));
        }
        this.land_position.setEditable(true);
        if (this.land_position.getStyleClass().contains((Object)"forbidden")) {
            this.land_position.getStyleClass().remove((Object)"forbidden");
        }
    }

    private void initCombox() {
        this.dist.getSelectionModel().selectedItemProperty().addListener((observableValue, optionPair, optionPair2) -> {
            this.updateLandPosition();
            if (optionPair2 != null && !StringProcess.isEmpty(optionPair2.getValue())) {
                ArrayList<OptionPair> arrayList = SQLiteDataProviderModel.getAA48List(EstimateModel.BASELANDBEAN.queryBean.AA45, "", optionPair2.getValue());
                this.aa48.setItems(FXCollections.observableArrayList(arrayList));
                this.model.voValue.put("dist", optionPair2.getValue());
                EstimateModel.BASELANDBEAN.voAppRaA3Vo2.setDist(optionPair2.getValue());
            }
        });
        this.aa48.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                try {
                    EstimateReport4Controller.this.updateLandPosition();
                    if (optionPair2 != null && !StringProcess.isEmpty(optionPair2.getValue())) {
                        EstimateReport4Controller.this.model.voValue.put("aa48", optionPair2.getValue());
                    }
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
        });
        this.aa49.textProperty().addListener((ChangeListener)new ChangeListener<String>(){

            public void changed(ObservableValue<? extends String> observableValue, String string, String string2) {
                EstimateReport4Controller.this.updateLandPosition();
            }
        });
        this.cs05.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getInstruCodeList()));
        this.cs05.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                if (EstimateReport4Controller.this.isFirstRefresh || optionPair2 == null) {
                    return;
                }
                EstimateReport4Controller.this.model.voValue.put("cs05", optionPair2.getValue());
                NVO_BASELAND_INSTRU nVO_BASELAND_INSTRU = SQLiteDataProviderModel.getMAPINSTRU().get(optionPair2.getValue());
                EstimateReport4Controller.this.cs24.setValue(String.valueOf(nVO_BASELAND_INSTRU.getYear_limits()));
                EstimateReport4Controller.this.cs26.setValue(String.valueOf(nVO_BASELAND_INSTRU.getResidual_rate()));
                String string = EstimateReport4Controller.this.cs02.getValue().toString();
                if (!StringProcess.isEmpty(string)) {
                    int n = EstimateReport4Controller.this.getInstruStdPrice(optionPair2.getValue(), StringProcess.parserInt(string));
                    EstimateReport4Controller.this.cs28.setValue(String.valueOf(n));
                }
                EstimateReport4Controller.this.reCal();
            }
        });
        this.cs25.setItems(FXCollections.observableArrayList(this.parentController.model.getCS25List()));
        this.cs25.getSelectionModel().selectFirst();
        this.cbMonth.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getMonthList(0)));
        this.cbMonth.getSelectionModel().selectFirst();
        this.cbMonth.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<String>(){

            public void changed(ObservableValue<? extends String> observableValue, String string, String string2) {
                String string3 = EstimateReport4Controller.this.cs081.getValue().toString();
                String string4 = string2;
                String string5 = DateTime.m2y(string3, string4);
                EstimateReport4Controller.this.model.voValue.put("cs08", StringProcess.parserDouble(string5));
                EstimateReport4Controller.this.cs08.setValue(string5);
                EstimateReport4Controller.this.reCal();
            }
        });
        this.cs21.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getMonthList(1)));
        this.cs21.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<String>(){

            public void changed(ObservableValue<? extends String> observableValue, String string, String string2) {
                String string3 = string2;
                EstimateReport4Controller.this.model.voValue.put("cs21", string3);
                EstimateReport4Controller.this.reCal();
            }
        });
        GlossaryModel glossaryModel = new GlossaryModel();
        glossaryModel.query("SELL", "SELL_CS56", 0, "");
        this.cbLiteral.setItems(FXCollections.observableArrayList(glossaryModel.getDataOptionpairs()));
        this.cbLiteral.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                if (optionPair2 != null && !StringProcess.isEmpty(optionPair2.getValue())) {
                    EstimateReport4Controller.this.cs56.setText(optionPair2.getAlias());
                }
            }
        });
    }

    private void initButton() {
        this.btFloorEffect.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                if (!(StringProcess.isEmpty(EstimateReport4Controller.this.cs02.getValue()) && StringProcess.isEmpty(EstimateReport4Controller.this.cs03.getValue()) || StringProcess.isEmpty(EstimateReport4Controller.this.cs04f.getValue()))) {
                    EstimateReport4Controller.this.createFloorEffect();
                } else {
                    JavaFXUtil.showErrorMessageBox("\u8acb\u78ba\u5be6\u586b\u5beb\u7e3d\u6a13\u5c64\u6578\u8207\u6a19\u7684\u6a13\u5c64");
                }
            }
        });
        this.btAddLiteral.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                String string = EstimateReport4Controller.this.cs56.getText();
                if (!StringProcess.isEmpty(string)) {
                    GlossaryModel glossaryModel = new GlossaryModel();
                    glossaryModel.addLiteral(string, "SELL", "SELL_CS56");
                    glossaryModel.query("SELL", "SELL_CS56", 0, "");
                    EstimateReport4Controller.this.cbLiteral.setItems(FXCollections.observableArrayList(glossaryModel.getDataOptionpairs()));
                    EstimateReport4Controller.this.cbLiteral.getSelectionModel().selectLast();
                    JavaFXUtil.showToastMessageBox(EstimateReport4Controller.this.parentController.dialog.getStage(), "\u5df2\u65b0\u589e\u8fad\u5eab", 1500);
                }
            }
        });
        this.btPhotos.setOnAction(actionEvent -> {
            Node node = (Node)actionEvent.getSource();
            Window window = node.getScene().getWindow();
            ImageBrowserDialog imageBrowserDialog = new ImageBrowserDialog((Stage)window);
            imageBrowserDialog.load("\u6210\u672c\u6cd5\u53ca\u623f\u5730\u5206\u96e2\u4f30\u50f9\u88682", "SELL2");
            imageBrowserDialog.show();
        });
    }

    private void initRadioButton() {
        this.havebuildN.selectedProperty().addListener((ChangeListener)new ChangeListener<Boolean>(){

            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean bl, Boolean bl2) {
                if (bl2.booleanValue()) {
                    EstimateReport4Controller.this.floorPart.setDisable(true);
                    EstimateReport4Controller.this.floorAll.setDisable(true);
                    EstimateReport4Controller.this.changeType(4);
                }
            }
        });
        this.havebuildY.selectedProperty().addListener((ChangeListener)new ChangeListener<Boolean>(){

            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean bl, Boolean bl2) {
                if (bl2.booleanValue()) {
                    EstimateReport4Controller.this.floorPart.setDisable(false);
                    EstimateReport4Controller.this.floorAll.setDisable(false);
                    if (EstimateReport4Controller.this.floorPart.isSelected()) {
                        EstimateReport4Controller.this.changeType(5);
                    } else if (EstimateReport4Controller.this.floorAll.isSelected()) {
                        EstimateReport4Controller.this.changeType(6);
                    }
                }
            }
        });
        this.floorPart.selectedProperty().addListener((ChangeListener)new ChangeListener<Boolean>(){

            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean bl, Boolean bl2) {
                if (bl2.booleanValue() && !EstimateReport4Controller.this.floorPart.isDisable()) {
                    EstimateReport4Controller.this.changeType(5);
                }
            }
        });
        this.floorAll.selectedProperty().addListener((ChangeListener)new ChangeListener<Boolean>(){

            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean bl, Boolean bl2) {
                if (bl2.booleanValue()) {
                    EstimateReport4Controller.this.changeType(6);
                }
            }
        });
        this.CS04_1.selectedProperty().addListener((ChangeListener)new ChangeListener<Boolean>(){

            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean bl, Boolean bl2) {
                if (bl2.booleanValue()) {
                    EstimateReport4Controller.this.cs04f.setDisable(true);
                }
            }
        });
        this.CS04_2.selectedProperty().addListener((ChangeListener)new ChangeListener<Boolean>(){

            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean bl, Boolean bl2) {
                if (bl2.booleanValue()) {
                    EstimateReport4Controller.this.cs04f.setDisable(false);
                }
            }
        });
    }

    public void refresh() {
        this.isFirstRefresh = true;
        this.dist.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getAA46List(EstimateModel.BASELANDBEAN.queryBean.AA45)));
        this.updateUIFromVo();
        this.refreshCombox();
        this.refreshRadioButton();
        this.isFirstRefresh = false;
    }

    private void refreshRadioButton() {
        this.CS04_1.setSelected(this.model.voValue.get("cs04").equals("0"));
        this.CS04_2.setSelected(this.model.voValue.get("cs04").equals("1"));
    }

    private void refreshCombox() {
        GlossaryModel glossaryModel = new GlossaryModel();
        glossaryModel.query("SELL", "SELL_CS56", 0, "");
        this.cbLiteral.setItems(FXCollections.observableArrayList(glossaryModel.getDataOptionpairs()));
        this.cbLiteral.getSelectionModel().selectFirst();
    }

    void reCal() {
        this.updateVoFromUI();
        this.model.reCal();
        this.updateUIFromVo();
    }

    protected void updateVoFromUI() {
        if (!StringProcess.isEmpty(this.ed49.getValue().toString())) {
            this.ed49.setValue(this.checkBuildCode(this.ed49.getValue().toString()));
        }
        for (Node node : this.report4.getChildren()) {
            Object object;
            Object object2;
            if (this.model.voValue.containsKey(node.getId())) {
                if (node instanceof DecimalField) {
                    if (node.getStyleClass().contains((Object)"rp2_ch") && ((BigDecimal)(object2 = new BigDecimal((String)this.model.voValue.get(node.getId())))).compareTo((BigDecimal)(object = new BigDecimal(((DecimalField)node).getValue().toString()))) != 0) {
                        EstimateModel.col_update.put("as339_2", true);
                    }
                    this.model.voValue.put(node.getId(), ((DecimalField)node).getValue().toString());
                } else if (node instanceof StringField) {
                    this.model.voValue.put(node.getId(), ((StringField)node).getValue().toString());
                } else if (node instanceof StringArea) {
                    this.model.voValue.put(node.getId(), ((StringArea)node).getText().toString());
                } else if (node instanceof TextField) {
                    this.model.voValue.put(node.getId(), ((TextField)node).getText().toString());
                }
            }
            if (!(node instanceof AnchorPane)) continue;
            object2 = ((AnchorPane)node).getChildren().iterator();
            while (object2.hasNext()) {
                object = (Node)object2.next();
                if (!this.model.voValue.containsKey(object.getId())) continue;
                if (object instanceof DecimalField) {
                    this.model.voValue.put(object.getId(), ((DecimalField)((Object)object)).getValue().toString());
                    continue;
                }
                if (object instanceof StringField) {
                    this.model.voValue.put(object.getId(), ((StringField)((Object)object)).getValue().toString());
                    continue;
                }
                if (!(object instanceof TextField)) continue;
                this.model.voValue.put(object.getId(), ((TextField)object).getText().toString());
            }
        }
        this.model.voValue.put("cs04", this.CS04_1.isSelected() ? "0" : "1");
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
        String string;
        String[] stringArray2;
        this.model.updateHashMapValues();
        for (String[] stringArray2 : this.report4.getChildren()) {
            string = stringArray2.getId();
            if (string == null) continue;
            if (stringArray2 instanceof AbsTextField) {
                ((AbsTextField)stringArray2).setValue(this.model.voValue.get(string).toString());
            } else if (stringArray2 instanceof StringArea) {
                ((StringArea)stringArray2).setText(this.model.voValue.get(string).toString());
            } else if (stringArray2 instanceof TextField) {
                ((TextField)stringArray2).setText(this.model.voValue.get(string).toString());
            }
            if (!(stringArray2 instanceof AnchorPane)) continue;
            for (Node node : ((AnchorPane)stringArray2).getChildren()) {
                if (!this.model.voValue.containsKey(node.getId())) continue;
                if (node instanceof AbsTextField) {
                    ((AbsTextField)node).setValue(this.model.voValue.get(node.getId()).toString());
                    continue;
                }
                if (node instanceof StringArea) {
                    ((StringArea)node).setText(this.model.voValue.get(node.getId()).toString());
                    continue;
                }
                if (!(node instanceof TextField)) continue;
                ((TextField)node).setText(this.model.voValue.get(node.getId()).toString());
            }
        }
        int n = StringProcess.parserInt((String)this.model.voValue.get("selltype"));
        this.changeType(n);
        stringArray2 = DateTime.y2m(this.model.voValue.get("cs08").toString());
        this.cs081.setValue(String.valueOf(stringArray2[0]));
        this.cbMonth.getSelectionModel().select((Object)stringArray2[1]);
        if (this.isFirstRefresh) {
            string = (String)this.model.voValue.get("dist");
            String string2 = (String)this.model.voValue.get("aa48");
            this.dist.getSelectionModel().select((Object)new OptionPair(string, ""));
            this.aa48.getSelectionModel().select((Object)new OptionPair(string2, ""));
        }
        this.updateLandPosition();
        this.lbPrompt.setVisible(StringProcess.isEmpty(this.cs30.getValue()));
        this.cs05.getSelectionModel().select(StringProcess.parserInt((String)this.model.voValue.get("cs05")));
        this.cs21.getSelectionModel().select((Object)((String)this.model.voValue.get("cs21")));
        this.cs52.setValue(StringProcess.ascii2Unicode((String)this.model.voValue.get("cs52")));
        this.cs58.setValue(StringProcess.ascii2Unicode((String)this.model.voValue.get("cs58")));
        this.cs56.setText(StringProcess.ascii2Unicode((String)this.model.voValue.get("cs56")));
        this.cs01.setValue(StringProcess.ascii2Unicode((String)this.model.voValue.get("cs01")));
        this.land_position.setValue(StringProcess.ascii2Unicode((String)this.model.voValue.get("land_position")));
        this.isFirstRefresh = false;
    }

    private void changeType(int n) {
        if (n == 0) {
            return;
        }
        this.havebuildN.setSelected(n == 4);
        this.havebuildY.setSelected(n == 5 || n == 6);
        this.floorPart.setSelected(n != 6);
        this.floorAll.setSelected(n == 6);
        EstimateModel.BASELANDBEAN.voSell_2.setSelltype(String.valueOf(n));
        if (n == 4) {
            this.lbLabel1.setText("\u3000\u6bd4\u8f03\u6a19\u7684\u7e3d\u50f9\u683c(\u5143)");
            this.lbLabel2.setText("\u3000\u6b63\u5e38\u8cb7\u8ce3\u7e3d\u50f9\u683c(\u5143)");
            this.lbLabel3.setText("");
            this.lbLabel31.setText("");
            this.cs48.setVisible(false);
            this.lbLabel4.setText("\u3000\u571f\u5730\u9762\u7a4d(\u33a1)");
            this.lbLabel5.setText("");
            this.lbLabel51.setText("");
            this.cs50.setVisible(false);
            this.cs52.setVisible(false);
            this.cs53.setVisible(false);
            this.lbLabel531.setVisible(false);
            this.lbLabel591.setVisible(false);
            this.cs58.setVisible(false);
            this.cs59.setVisible(false);
            this.lbLabel6.setText("\u3000\u571f\u5730\u6b63\u5e38\u8cb7\u8ce3\u55ae\u50f9(\u5143/\u33a1)");
            this.model.voValue.put("selltype", "4");
            this.panel1.setDisable(true);
            this.panel2.setDisable(true);
            this.panel5.setVisible(false);
            this.panel6.setDisable(true);
            this.ed49.setEditable(false);
            if (!this.ed49.getStyleClass().contains((Object)"forbidden")) {
                this.ed49.getStyleClass().add((Object)"forbidden");
            }
            this.cs01.setEditable(false);
            if (!this.cs01.getStyleClass().contains((Object)"forbidden")) {
                this.cs01.getStyleClass().add((Object)"forbidden");
            }
        } else if (n == 5) {
            this.lbLabel1.setText("\u3000\u6bd4\u8f03\u6a19\u7684\u7e3d\u50f9\u683c(\u5143)");
            this.lbLabel2.setText("\u3000\u6b63\u5e38\u8cb7\u8ce3\u7e3d\u50f9\u683c(\u5143)(\u5373\u8cb7\u8ce3\u5be6\u4f8b\u623f\u5730\u50f9\u683c)");
            this.lbLabel3.setText("\u3000\u6a19\u7684\u5efa\u7269\u6210\u672c\u50f9\u683c(\u7e3d\u50f9)(\u4e0d\u542b\u8eca\u4f4d)");
            this.lbLabel31.setText("\u5143");
            this.cs48.setVisible(true);
            this.lbLabel4.setText("\u3000\u6a19\u7684\u571f\u5730\u6301\u5206\u9762\u7a4d(\u33a1)");
            this.lbLabel5.setText("\u3000\u6a19\u7684\u571f\u5730\u6b0a\u5229\u55ae\u50f9(\u5143/\u33a1)");
            this.lbLabel51.setText("\u5143/\u33a1");
            this.cs50.setVisible(true);
            this.cs52.setVisible(true);
            this.cs53.setVisible(true);
            this.lbLabel531.setVisible(true);
            this.lbLabel591.setVisible(true);
            this.cs58.setVisible(true);
            this.cs59.setVisible(true);
            this.lbLabel6.setText("\u3000\u571f\u5730\u6b63\u5e38\u8cb7\u8ce3\u55ae\u50f9(\u5143/\u33a1)");
            this.model.voValue.put("selltype", "5");
            this.CS04_2.setDisable(false);
            this.CS04_2.setSelected(true);
            this.CS04_1.setDisable(true);
            this.panel1.setDisable(false);
            this.panel2.setDisable(false);
            this.panel5.setVisible(true);
            this.panel6.setDisable(false);
            this.ed49.setEditable(true);
            this.ed49.getStyleClass().remove((Object)"forbidden");
            this.cs01.setEditable(true);
            this.cs01.getStyleClass().remove((Object)"forbidden");
        } else {
            this.lbLabel1.setText("\u3000\u6bd4\u8f03\u6a19\u7684\u7e3d\u50f9\u683c(\u5143)");
            this.lbLabel2.setText("\u3000\u6b63\u5e38\u8cb7\u8ce3\u7e3d\u50f9\u683c(\u5143)(\u5373\u8cb7\u8ce3\u5be6\u4f8b\u623f\u5730\u50f9\u683c)");
            this.lbLabel3.setText("\u3000\u6a19\u7684\u5efa\u7269\u6210\u672c\u50f9\u683c(\u7e3d\u50f9)(\u4e0d\u542b\u8eca\u4f4d)");
            this.lbLabel31.setText("\u5143");
            this.cs48.setVisible(true);
            this.lbLabel4.setText("\u3000\u6a19\u7684\u571f\u5730\u9762\u7a4d(\u33a1)");
            this.lbLabel5.setText("");
            this.lbLabel51.setText("");
            this.cs50.setVisible(false);
            this.cs52.setVisible(true);
            this.cs53.setVisible(true);
            this.lbLabel531.setVisible(true);
            this.lbLabel591.setVisible(true);
            this.cs58.setVisible(true);
            this.cs59.setVisible(true);
            this.lbLabel6.setText("\u3000\u571f\u5730\u6b63\u5e38\u8cb7\u8ce3\u55ae\u50f9(\u5143/\u33a1)");
            this.model.voValue.put("selltype", "6");
            this.CS04_1.setDisable(false);
            this.CS04_1.setSelected(true);
            this.CS04_2.setDisable(true);
            this.panel1.setDisable(false);
            this.panel2.setDisable(false);
            this.panel5.setVisible(false);
            this.panel6.setDisable(false);
            this.ed49.setEditable(true);
            this.ed49.getStyleClass().remove((Object)"forbidden");
            this.cs01.setEditable(true);
            this.cs01.getStyleClass().remove((Object)"forbidden");
        }
    }

    protected void createFloorEffect() {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader fXMLLoader = new FXMLLoader(this.getClass().getResource("/view/FloorEffect.fxml"));
            Scene scene = new Scene((Parent)fXMLLoader.load());
            stage.setScene(scene);
            FloorEffectController floorEffectController = (FloorEffectController)fXMLLoader.getController();
            String string = (String)this.model.voValue.get("year");
            String string2 = (String)this.model.voValue.get("baseno");
            String string3 = (String)this.model.voValue.get("caseno");
            int n = StringProcess.parserInt((String)this.model.voValue.get("cs03"));
            int n2 = StringProcess.parserInt((String)this.model.voValue.get("cs02"));
            String string4 = (String)this.model.voValue.get("cs04f");
            long l = (Long)this.model.voValue.get("cs48");
            long l2 = (Long)this.model.voValue.get("cs47");
            floorEffectController.init(this.manager, string, string2, string3, n, n2, string4, l, l2, stringBuilder);
            stage.showAndWait();
            String[] stringArray = StringProcess.split(stringBuilder.toString(), ",");
            if (stringArray.length == 2) {
                this.model.voValue.put("cs64", stringArray[0]);
                this.model.voValue.put("cs65", stringArray[1]);
                this.cs64.setValue(stringArray[0]);
                this.cs65.setValue(stringArray[1]);
                this.reCal();
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private int getInstruStdPrice(String string, int n) {
        return this.model.getInstruStdPrice(string, n);
    }
}

