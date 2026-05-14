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
 *  javafx.scene.control.Alert
 *  javafx.scene.control.Alert$AlertType
 *  javafx.scene.control.Button
 *  javafx.scene.control.ButtonType
 *  javafx.scene.control.ComboBox
 *  javafx.scene.control.Label
 *  javafx.scene.layout.AnchorPane
 *  javafx.stage.Stage
 */
package com.wfusion.baseland.system;

import com.wfusion.baseland.SQLiteDataProviderModel;
import com.wfusion.baseland.SystemCopyBean;
import com.wfusion.baseland.basic.Controller;
import com.wfusion.baseland.basic.IBaseLandDialog;
import com.wfusion.baseland.system.SystemCopyModel;
import com.wfusion.fx.util.JavaFXUtil;
import com.wfusion.util.OptionPair;
import com.wfusion.util.StringProcess;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SystemCopyController
extends Controller {
    @FXML
    private AnchorPane panel;
    @FXML
    private Button btCopy;
    @FXML
    private Button btExit;
    @FXML
    private ComboBox<OptionPair> copyType;
    @FXML
    private ComboBox<OptionPair> chooseAA45;
    @FXML
    private ComboBox<OptionPair> copyAA45;
    @FXML
    private ComboBox<OptionPair> chooseYear;
    @FXML
    private ComboBox<OptionPair> copyYear;
    @FXML
    private ComboBox<OptionPair> chooseDist;
    @FXML
    private ComboBox<OptionPair> copyDist;
    @FXML
    private ComboBox<OptionPair> chooseVersion;
    @FXML
    private ComboBox<OptionPair> copyVersion;
    @FXML
    private ComboBox<OptionPair> chooseLandNo;
    @FXML
    private ComboBox<OptionPair> copyLandNo;
    @FXML
    private Label chLabelYear;
    @FXML
    private Label chLabelDist;
    @FXML
    private Label cpLabelDist;
    @FXML
    private Label cpLabelYear;
    @FXML
    private Label chLabel;
    @FXML
    private Label chLabelVer;
    @FXML
    private Label cpLabelVer;
    @FXML
    private Label cpLabel;
    @FXML
    private Label chLabelLand;
    @FXML
    private Label cpLabelLand;
    @FXML
    private AnchorPane hbWinTitle;
    @FXML
    private Label TITLE;
    @FXML
    private Button EXIT;
    SystemCopyModel model = new SystemCopyModel();

    @Override
    public void init(IBaseLandDialog iBaseLandDialog) {
        super.init(iBaseLandDialog);
        this.initComboBox();
        this.initButton();
        this.switchPageShow(((OptionPair)this.copyType.getValue()).getValue());
    }

    private void initComboBox() {
        this.createCopyType();
        this.copyType.getSelectionModel().selectFirst();
        this.copyType.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                if (optionPair2 != null) {
                    SystemCopyController.this.switchPageShow(optionPair2.getValue());
                }
            }
        });
        this.chooseAA45.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getAA45List()));
        this.chooseAA45.getSelectionModel().selectFirst();
        this.chooseAA45.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                String string = ((OptionPair)SystemCopyController.this.copyType.getValue()).getValue();
                String string2 = null;
                if (SystemCopyController.this.chooseAA45.getSelectionModel().getSelectedItem() != null) {
                    string2 = ((OptionPair)SystemCopyController.this.chooseAA45.getSelectionModel().getSelectedItem()).getValue();
                }
                if ("A5".equals(string) || "A6".equals(string) || "A7".equals(string)) {
                    SystemCopyController.this.chooseYear.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getAhpParamYearList(string2)));
                    SystemCopyController.this.chooseYear.getSelectionModel().selectFirst();
                } else if ("A3".equals(string)) {
                    SystemCopyController.this.chooseYear.getItems().clear();
                    SystemCopyController.this.chooseYear.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getPriceRateTypeCode()));
                    SystemCopyController.this.chooseYear.getSelectionModel().selectFirst();
                } else if ("A4".equals(string)) {
                    SystemCopyController.this.chooseVersion.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getDefaultList()));
                    SystemCopyController.this.chooseVersion.getItems().addAll(SQLiteDataProviderModel.getInstruCodeList());
                    SystemCopyController.this.chooseVersion.getSelectionModel().selectFirst();
                } else {
                    SystemCopyController.this.chooseDist.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getAA46List(string2)));
                    SystemCopyController.this.chooseDist.getSelectionModel().selectFirst();
                }
            }
        });
        this.copyAA45.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getAA45List()));
        this.copyAA45.getSelectionModel().selectFirst();
        this.copyAA45.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                String string = ((OptionPair)SystemCopyController.this.copyType.getValue()).getValue();
                String string2 = null;
                if (SystemCopyController.this.copyAA45.getSelectionModel().getSelectedItem() != null) {
                    string2 = ((OptionPair)SystemCopyController.this.copyAA45.getSelectionModel().getSelectedItem()).getValue();
                }
                if ("A5".equals(string) || "A6".equals(string) || "A7".equals(string)) {
                    SystemCopyController.this.copyYear.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getAhpParamYearList(string2)));
                    SystemCopyController.this.copyYear.getSelectionModel().selectFirst();
                } else if ("A3".equals(string)) {
                    SystemCopyController.this.copyYear.getItems().clear();
                    SystemCopyController.this.copyYear.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getPriceRateTypeCode()));
                    SystemCopyController.this.copyYear.getSelectionModel().selectFirst();
                } else if ("A4".equals(string)) {
                    SystemCopyController.this.copyVersion.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getDefaultList()));
                    SystemCopyController.this.copyVersion.getItems().addAll(SQLiteDataProviderModel.getInstruCodeList());
                    SystemCopyController.this.copyVersion.getSelectionModel().selectFirst();
                } else {
                    SystemCopyController.this.copyDist.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getAA46List(string2)));
                    SystemCopyController.this.copyDist.getSelectionModel().selectFirst();
                    SystemCopyController.this.copyYear.getItems().clear();
                    SystemCopyController.this.copyVersion.getItems().clear();
                    SystemCopyController.this.copyLandNo.getItems().clear();
                }
            }
        });
        this.chooseDist.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getDefaultList()));
        this.chooseDist.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                if (optionPair2 != null) {
                    String string = ((OptionPair)SystemCopyController.this.copyType.getValue()).getValue();
                    if ("A1".equals(string) || "A2".equals(string)) {
                        if (SystemCopyController.this.chooseAA45.getSelectionModel().getSelectedItem() != null && !StringProcess.isEmpty(optionPair2.getValue())) {
                            String string2 = ((OptionPair)SystemCopyController.this.chooseAA45.getValue()).getValue();
                            String string3 = optionPair2.getValue();
                            SystemCopyController.this.chooseYear.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getYearsListFromRegionalFactor(string2, string3)));
                            SystemCopyController.this.chooseYear.getSelectionModel().selectFirst();
                        }
                    } else if ("A3".equals(string)) {
                        if (!(SystemCopyController.this.chooseAA45.getValue() == null || StringProcess.isEmpty(((OptionPair)SystemCopyController.this.chooseAA45.getValue()).getValue()) || SystemCopyController.this.chooseYear.getValue() == null || StringProcess.isEmpty(((OptionPair)SystemCopyController.this.chooseYear.getValue()).getValue()) || optionPair2 == null || StringProcess.isEmpty(optionPair2.getValue()))) {
                            SystemCopyController.this.chooseVersion.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getYearsFromPriceRateOption(((OptionPair)SystemCopyController.this.chooseAA45.getValue()).getValue(), ((OptionPair)SystemCopyController.this.chooseYear.getValue()).getValue(), optionPair2.getValue())));
                        } else {
                            SystemCopyController.this.chooseVersion.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getDefaultList()));
                        }
                        SystemCopyController.this.chooseVersion.getSelectionModel().selectFirst();
                    }
                }
            }
        });
        this.copyDist.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getDefaultList()));
        this.copyDist.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                if (optionPair2 != null) {
                    String string = ((OptionPair)SystemCopyController.this.copyType.getValue()).getValue();
                    if ("A1".equals(string) || "A2".equals(string)) {
                        if (SystemCopyController.this.copyAA45.getSelectionModel().getSelectedItem() != null && !StringProcess.isEmpty(optionPair2.getValue())) {
                            String string2 = ((OptionPair)SystemCopyController.this.copyAA45.getValue()).getValue();
                            String string3 = optionPair2.getValue();
                            SystemCopyController.this.copyYear.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getYearsListFromRegionalFactor(string2, string3)));
                            SystemCopyController.this.copyYear.getSelectionModel().selectFirst();
                            SystemCopyController.this.copyVersion.getItems().clear();
                            SystemCopyController.this.copyLandNo.getItems().clear();
                        }
                    } else if ("A3".equals(string)) {
                        if (!(SystemCopyController.this.copyAA45.getValue() == null || StringProcess.isEmpty(((OptionPair)SystemCopyController.this.copyAA45.getValue()).getValue()) || SystemCopyController.this.copyYear.getValue() == null || StringProcess.isEmpty(((OptionPair)SystemCopyController.this.copyYear.getValue()).getValue()) || optionPair2 == null || StringProcess.isEmpty(optionPair2.getValue()))) {
                            SystemCopyController.this.copyVersion.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getYearsFromPriceRateOption(((OptionPair)SystemCopyController.this.copyAA45.getValue()).getValue(), ((OptionPair)SystemCopyController.this.copyYear.getValue()).getValue(), optionPair2.getValue())));
                        } else {
                            SystemCopyController.this.copyVersion.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getDefaultList()));
                        }
                        SystemCopyController.this.copyVersion.getSelectionModel().selectFirst();
                    }
                }
            }
        });
        this.chooseYear.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getDefaultList()));
        this.chooseYear.getSelectionModel().selectFirst();
        this.chooseYear.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                String string = "";
                String string2 = "";
                String string3 = "";
                String string4 = "";
                string4 = ((OptionPair)SystemCopyController.this.copyType.getValue()).getValue();
                if (SystemCopyController.this.chooseAA45.getSelectionModel().getSelectedItem() != null) {
                    string = ((OptionPair)SystemCopyController.this.chooseAA45.getSelectionModel().getSelectedItem()).getValue();
                }
                if (SystemCopyController.this.chooseDist.getSelectionModel().getSelectedItem() != null) {
                    string2 = ((OptionPair)SystemCopyController.this.chooseDist.getSelectionModel().getSelectedItem()).getValue();
                }
                if (optionPair2 != null) {
                    string3 = optionPair2.getValue();
                }
                if ("A1".equals(string4) || "A2".equals(string4)) {
                    if (!(StringProcess.isEmpty(string) || StringProcess.isEmpty(string2) || StringProcess.isEmpty(string3))) {
                        SystemCopyController.this.chooseLandNo.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getBaselandNoList(string, string2, string3)));
                        SystemCopyController.this.chooseVersion.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getFactorVersion()));
                    }
                } else if ("A3".equals(string4) && !StringProcess.isEmpty(string) && !StringProcess.isEmpty(string3)) {
                    SystemCopyController.this.chooseDist.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getPriceRateDistCode(string, string3)));
                    SystemCopyController.this.chooseDist.getSelectionModel().selectFirst();
                }
            }
        });
        this.copyYear.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getDefaultList()));
        this.copyYear.getSelectionModel().selectFirst();
        this.copyYear.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                String string = "";
                String string2 = "";
                String string3 = "";
                String string4 = "";
                string4 = ((OptionPair)SystemCopyController.this.copyType.getValue()).getValue();
                if (SystemCopyController.this.copyAA45.getSelectionModel().getSelectedItem() != null) {
                    string = ((OptionPair)SystemCopyController.this.copyAA45.getSelectionModel().getSelectedItem()).getValue();
                }
                if (SystemCopyController.this.copyDist.getSelectionModel().getSelectedItem() != null) {
                    string2 = ((OptionPair)SystemCopyController.this.copyDist.getSelectionModel().getSelectedItem()).getValue();
                }
                if (optionPair2 != null) {
                    string3 = optionPair2.getValue();
                }
                if ("A1".equals(string4) || "A2".equals(string4)) {
                    if (!(StringProcess.isEmpty(string) || StringProcess.isEmpty(string2) || StringProcess.isEmpty(string3) || SystemCopyController.this.chooseVersion.getSelectionModel().getSelectedItem() == null || ((OptionPair)SystemCopyController.this.chooseVersion.getSelectionModel().getSelectedItem()).getValue() == "")) {
                        SystemCopyController.this.copyLandNo.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getBaselandNoList(string, string2, string3)));
                        SystemCopyController.this.copyVersion.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getFactorVersion(((OptionPair)SystemCopyController.this.chooseVersion.getSelectionModel().getSelectedItem()).getValue(), ((OptionPair)SystemCopyController.this.chooseVersion.getSelectionModel().getSelectedItem()).getAlias())));
                        SystemCopyController.this.copyVersion.getSelectionModel().selectFirst();
                        SystemCopyController.this.copyVersion.setDisable(false);
                        SystemCopyController.this.copyLandNo.setDisable(false);
                    }
                } else if ("A3".equals(string4) && !StringProcess.isEmpty(string) && !StringProcess.isEmpty(string3)) {
                    SystemCopyController.this.copyDist.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getPriceRateDistCode(string, string3)));
                    SystemCopyController.this.copyDist.getSelectionModel().selectFirst();
                }
            }
        });
        this.chooseVersion.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                if ("".equals(((OptionPair)SystemCopyController.this.chooseVersion.getSelectionModel().getSelectedItem()).getValue())) {
                    SystemCopyController.this.chooseLandNo.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getBaselandNoList_withVersion("", "", "", "")));
                    SystemCopyController.this.chooseLandNo.getSelectionModel().selectFirst();
                    SystemCopyController.this.copyVersion.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getFactorVersion()));
                    SystemCopyController.this.copyVersion.getSelectionModel().selectFirst();
                    SystemCopyController.this.copyVersion.setDisable(true);
                    SystemCopyController.this.copyLandNo.setDisable(true);
                } else {
                    String string = ((OptionPair)SystemCopyController.this.chooseAA45.getSelectionModel().getSelectedItem()).getValue();
                    String string2 = ((OptionPair)SystemCopyController.this.chooseDist.getSelectionModel().getSelectedItem()).getValue();
                    String string3 = ((OptionPair)SystemCopyController.this.chooseYear.getSelectionModel().getSelectedItem()).getValue();
                    String string4 = ((OptionPair)SystemCopyController.this.chooseVersion.getSelectionModel().getSelectedItem()).getValue();
                    SystemCopyController.this.chooseLandNo.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getBaselandNoList_withVersion(string, string2, string3, string4)));
                    SystemCopyController.this.chooseLandNo.getSelectionModel().selectFirst();
                    SystemCopyController.this.copyVersion.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getFactorVersion(((OptionPair)SystemCopyController.this.chooseVersion.getSelectionModel().getSelectedItem()).getValue(), ((OptionPair)SystemCopyController.this.chooseVersion.getSelectionModel().getSelectedItem()).getAlias())));
                    if (((OptionPair)SystemCopyController.this.copyYear.getSelectionModel().getSelectedItem()).getValue() != "") {
                        SystemCopyController.this.copyVersion.setDisable(false);
                        SystemCopyController.this.copyLandNo.getItems().clear();
                        SystemCopyController.this.copyLandNo.setDisable(false);
                    }
                }
            }
        });
        this.copyVersion.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                if (SystemCopyController.this.copyVersion.getSelectionModel().getSelectedItem() != null) {
                    if ("".equals(((OptionPair)SystemCopyController.this.copyVersion.getSelectionModel().getSelectedItem()).getValue())) {
                        SystemCopyController.this.copyLandNo.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getBaselandNoList_withVersion("", "", "", "")));
                        SystemCopyController.this.copyLandNo.getSelectionModel().selectFirst();
                    } else {
                        String string = ((OptionPair)SystemCopyController.this.copyAA45.getSelectionModel().getSelectedItem()).getValue();
                        String string2 = ((OptionPair)SystemCopyController.this.copyDist.getSelectionModel().getSelectedItem()).getValue();
                        String string3 = ((OptionPair)SystemCopyController.this.copyYear.getSelectionModel().getSelectedItem()).getValue();
                        String string4 = ((OptionPair)SystemCopyController.this.copyVersion.getSelectionModel().getSelectedItem()).getValue();
                        SystemCopyController.this.copyLandNo.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getBaselandNoList_withVersion(string, string2, string3, string4)));
                        SystemCopyController.this.copyLandNo.getSelectionModel().selectFirst();
                    }
                }
            }
        });
        this.copyVersion.setDisable(true);
        this.copyLandNo.setDisable(true);
    }

    private void switchPageShow(String string) {
        if ("A1".equals(string) || "A2".equals(string)) {
            for (Node node : this.panel.getChildren()) {
                String string2 = node.getId();
                if (StringProcess.isEmpty(string2)) continue;
                if (node.getStyleClass().contains((Object)"notShow")) {
                    node.setVisible(true);
                }
                if (!(node instanceof ComboBox) || string2.equals("copyType")) continue;
                ((ComboBox)node).getSelectionModel().selectFirst();
            }
            this.chLabelYear.setVisible(true);
            this.cpLabelYear.setVisible(true);
            this.chooseYear.setVisible(true);
            this.copyYear.setVisible(true);
            this.chLabelYear.setText("\u5e74\u671f");
            this.cpLabelYear.setText("\u5e74\u671f");
            this.chLabelVer.setText("\u7248\u672c\u985e\u578b");
            this.cpLabelVer.setText("\u7248\u672c\u985e\u578b");
        } else if ("A3".equals(string)) {
            for (Node node : this.panel.getChildren()) {
                String string3 = node.getId();
                if (StringProcess.isEmpty(string3)) continue;
                if (node.getStyleClass().contains((Object)"notShow")) {
                    node.setVisible(true);
                }
                if (!(node instanceof ComboBox) || string3.equals("copyType")) continue;
                ((ComboBox)node).getSelectionModel().selectFirst();
            }
            this.chLabelLand.setVisible(false);
            this.chooseLandNo.getItems().clear();
            this.chooseLandNo.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getDefaultList()));
            this.chooseLandNo.setVisible(false);
            this.chLabel.setVisible(false);
            this.cpLabelLand.setVisible(false);
            this.copyLandNo.getItems().clear();
            this.copyLandNo.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getDefaultList()));
            this.copyLandNo.setVisible(false);
            this.cpLabel.setVisible(false);
            this.chLabelYear.setVisible(true);
            this.cpLabelYear.setVisible(true);
            this.chooseYear.setVisible(true);
            this.copyYear.setVisible(true);
            this.chLabelYear.setText("\u6307\u6578\u985e\u578b");
            this.cpLabelYear.setText("\u6307\u6578\u985e\u578b");
            this.chooseYear.getItems().clear();
            this.chooseYear.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getDefaultList()));
            this.copyYear.getItems().clear();
            this.copyYear.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getDefaultList()));
            this.chLabelVer.setText("\u5e74\u5ea6");
            this.cpLabelVer.setText("\u5e74\u5ea6");
            this.chooseVersion.getItems().clear();
            this.chooseVersion.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getDefaultList()));
            this.copyVersion.getItems().clear();
            this.copyVersion.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getDefaultList()));
        } else if ("A4".equals(string)) {
            for (Node node : this.panel.getChildren()) {
                String string4 = node.getId();
                if (StringProcess.isEmpty(string4)) continue;
                if (node.getStyleClass().contains((Object)"notShow")) {
                    node.setVisible(false);
                    if (node instanceof ComboBox) {
                        ((ComboBox)node).getItems().clear();
                        ((ComboBox)node).setItems(FXCollections.observableList(SQLiteDataProviderModel.getDefaultList()));
                    }
                }
                if (!(node instanceof ComboBox) || string4.equals("copyType")) continue;
                ((ComboBox)node).getSelectionModel().selectFirst();
            }
            this.chLabelYear.setVisible(false);
            this.cpLabelYear.setVisible(false);
            this.chooseYear.setVisible(false);
            this.copyYear.setVisible(false);
            this.chooseYear.getItems().clear();
            this.chooseYear.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getDefaultList()));
            this.copyYear.getItems().clear();
            this.copyYear.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getDefaultList()));
            this.chLabel.setVisible(true);
            this.cpLabel.setVisible(true);
            this.chLabelVer.setVisible(true);
            this.cpLabelVer.setVisible(true);
            this.chooseVersion.setVisible(true);
            this.copyVersion.setVisible(true);
            this.chLabelVer.setText("\u5efa\u7269\u69cb\u9020\u7a2e\u985e");
            this.cpLabelVer.setText("\u5efa\u7269\u69cb\u9020\u7a2e\u985e");
        } else if ("A5".equals(string) || "A6".equals(string) || "A7".equals(string)) {
            for (Node node : this.panel.getChildren()) {
                String string5 = node.getId();
                if (StringProcess.isEmpty(string5)) continue;
                if (node.getStyleClass().contains((Object)"notShow")) {
                    node.setVisible(false);
                    if (node instanceof ComboBox) {
                        ((ComboBox)node).getItems().clear();
                        ((ComboBox)node).setItems(FXCollections.observableList(SQLiteDataProviderModel.getDefaultList()));
                    }
                }
                if (!(node instanceof ComboBox) || string5.equals("copyType")) continue;
                ((ComboBox)node).getSelectionModel().selectFirst();
            }
            this.chLabelYear.setVisible(true);
            this.cpLabelYear.setVisible(true);
            this.chooseYear.setVisible(true);
            this.copyYear.setVisible(true);
            this.chLabelYear.setText("\u5e74\u5ea6");
            this.cpLabelYear.setText("\u5e74\u5ea6");
            this.chooseYear.getItems().clear();
            this.chooseYear.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getDefaultList()));
            this.copyYear.getItems().clear();
            this.copyYear.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getDefaultList()));
        }
        if ("A1".equals(string) || "A2".equals(string)) {
            this.chooseYear.setLayoutY(210.0);
            this.chLabelYear.setLayoutY(210.0);
            this.chooseDist.setLayoutY(170.0);
            this.chLabelDist.setLayoutY(170.0);
            this.copyYear.setLayoutY(210.0);
            this.cpLabelYear.setLayoutY(210.0);
            this.copyDist.setLayoutY(170.0);
            this.cpLabelDist.setLayoutY(170.0);
        } else {
            this.chooseYear.setLayoutY(170.0);
            this.chLabelYear.setLayoutY(170.0);
            this.chooseDist.setLayoutY(210.0);
            this.chLabelDist.setLayoutY(210.0);
            this.copyYear.setLayoutY(170.0);
            this.cpLabelYear.setLayoutY(170.0);
            this.copyDist.setLayoutY(210.0);
            this.cpLabelDist.setLayoutY(210.0);
        }
    }

    private void initButton() {
        this.btExit.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                Node node = (Node)actionEvent.getSource();
                Stage stage = (Stage)node.getScene().getWindow();
                stage.close();
            }
        });
        this.btCopy.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                String string = SystemCopyController.this.copyCheck();
                if (StringProcess.isEmpty(string)) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "\u8907\u88fd\u5f8c\u6703\u5c07\u6240\u9078\u539f\u53c3\u6578\u8986\u84cb\uff0c\u662f\u5426\u8981\u9032\u884c\u8907\u88fd", new ButtonType[]{ButtonType.YES, ButtonType.NO});
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        boolean bl;
                        System.out.println("\u9032\u884c\u8907\u88fd");
                        SystemCopyBean systemCopyBean = SystemCopyController.this.transPageToBean();
                        if (SystemCopyController.this.model.backupDB() && (bl = SystemCopyController.this.copyData(systemCopyBean))) {
                            JavaFXUtil.showToastMessageBox(new Stage(), "\u8907\u88fd\u6210\u529f", 2500);
                        }
                    }
                } else {
                    JavaFXUtil.showErrorMessageBox(string, "");
                }
            }
        });
    }

    private boolean copyData(SystemCopyBean systemCopyBean) {
        boolean bl = false;
        if ("A1".equals(systemCopyBean.getCopyType())) {
            bl = this.model.copyRegionalFactor(systemCopyBean);
        } else if ("A2".equals(systemCopyBean.getCopyType())) {
            bl = this.model.copyIndividualFactor(systemCopyBean);
        } else if ("A3".equals(systemCopyBean.getCopyType())) {
            bl = this.model.copyPricerate(systemCopyBean);
        } else if ("A4".equals(systemCopyBean.getCopyType())) {
            bl = this.model.copyInstru(systemCopyBean);
        } else if ("A5".equals(systemCopyBean.getCopyType())) {
            bl = this.model.copyAhp(systemCopyBean);
        } else if (!"A6".equals(systemCopyBean.getCopyType()) && "A7".equals(systemCopyBean.getCopyType())) {
            bl = this.model.copyReportParam(systemCopyBean);
        }
        return bl;
    }

    private SystemCopyBean transPageToBean() {
        SystemCopyBean systemCopyBean = new SystemCopyBean();
        systemCopyBean.setCopyType(((OptionPair)this.copyType.getValue()).getValue());
        systemCopyBean.setChooseAA45(((OptionPair)this.chooseAA45.getValue()).getValue());
        systemCopyBean.setChooseDist(this.chooseDist.getValue() != null ? ((OptionPair)this.chooseDist.getValue()).getValue() : "");
        systemCopyBean.setChooseLandNo(this.chooseLandNo.getValue() != null ? ((OptionPair)this.chooseLandNo.getValue()).getValue() : "");
        systemCopyBean.setChooseVersion(this.chooseVersion.getValue() != null ? ((OptionPair)this.chooseVersion.getValue()).getValue() : "");
        systemCopyBean.setChooseYear(this.chooseYear.getValue() != null ? ((OptionPair)this.chooseYear.getValue()).getValue() : "");
        systemCopyBean.setCopyAA45(((OptionPair)this.copyAA45.getValue()).getValue());
        systemCopyBean.setCopyDist(this.copyDist.getValue() != null ? ((OptionPair)this.copyDist.getValue()).getValue() : "");
        systemCopyBean.setCopyLandNo(this.copyLandNo.getValue() != null ? ((OptionPair)this.copyLandNo.getValue()).getValue() : "");
        systemCopyBean.setCopyVersion(this.copyVersion.getValue() != null ? ((OptionPair)this.copyVersion.getValue()).getValue() : "");
        systemCopyBean.setCopyYear(this.copyYear.getValue() != null ? ((OptionPair)this.copyYear.getValue()).getValue() : "");
        return systemCopyBean;
    }

    private String copyCheck() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer = this.checkPKChoose(stringBuffer);
        stringBuffer = this.checkChooseLevel(stringBuffer);
        return stringBuffer.toString();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private StringBuffer checkChooseLevel(StringBuffer stringBuffer) {
        if ("A1".equals(((OptionPair)this.copyType.getValue()).getValue()) || "A2".equals(((OptionPair)this.copyType.getValue()).getValue())) {
            if (this.chooseVersion.getValue() != null && !StringProcess.isEmpty(((OptionPair)this.chooseVersion.getValue()).getValue())) {
                if (this.copyVersion.getValue() == null || StringProcess.isEmpty(((OptionPair)this.copyVersion.getValue()).getValue())) {
                    stringBuffer.append("\u8acb\u9078\u64c7\u8907\u88fd\u76ee\u7684\u7684\u7248\u672c\u985e\u578b\r\n");
                    return stringBuffer;
                }
                if (this.copyVersion.getValue() != null && !StringProcess.isEmpty(((OptionPair)this.copyVersion.getValue()).getValue()) && ((OptionPair)this.chooseVersion.getValue()).getValue() != ((OptionPair)this.copyVersion.getValue()).getValue()) {
                    stringBuffer.append("\u53ea\u80fd\u8907\u88fd\u76f8\u540c\u7684\u7248\u672c\u985e\u578b\r\n");
                    return stringBuffer;
                }
            } else if (this.copyVersion.getValue() != null && !StringProcess.isEmpty(((OptionPair)this.copyVersion.getValue()).getValue())) {
                stringBuffer.append("\u8acb\u9078\u64c7\u9078\u64c7\u76ee\u6a19\u7684\u7248\u672c\u985e\u578b\r\n");
                return stringBuffer;
            }
            if (this.chooseLandNo.getValue() != null && !StringProcess.isEmpty(((OptionPair)this.chooseLandNo.getValue()).getValue()) && (this.copyLandNo.getValue() == null || StringProcess.isEmpty(((OptionPair)this.copyLandNo.getValue()).getValue()))) {
                stringBuffer.append("\u8acb\u9078\u64c7\u8907\u88fd\u76ee\u7684\u7684\u57fa\u6e96\u5730\u7de8\u865f\r\n");
                return stringBuffer;
            }
            if (!((OptionPair)this.chooseAA45.getValue()).equals(this.copyAA45.getValue()) || !((OptionPair)this.chooseYear.getValue()).equals(this.copyYear.getValue()) || !((OptionPair)this.chooseDist.getValue()).equals(this.copyDist.getValue())) return stringBuffer;
            if (this.copyVersion.getValue() != null) {
                if (this.copyLandNo.getValue() != null) {
                    if (!((OptionPair)this.chooseLandNo.getValue()).equals(this.copyLandNo.getValue())) return stringBuffer;
                    stringBuffer.append("\u4f86\u6e90\u8207\u76ee\u7684\u5b8c\u5168\u76f8\u540c\uff0c\u8acb\u91cd\u65b0\u9078\u64c7\r\n");
                    return stringBuffer;
                }
                if (!((OptionPair)this.chooseVersion.getValue()).equals(this.copyVersion.getValue())) return stringBuffer;
                stringBuffer.append("\u4f86\u6e90\u8207\u76ee\u7684\u5b8c\u5168\u76f8\u540c\uff0c\u8acb\u91cd\u65b0\u9078\u64c7\r\n");
                return stringBuffer;
            }
            stringBuffer.append("\u4f86\u6e90\u8207\u76ee\u7684\u5b8c\u5168\u76f8\u540c\uff0c\u8acb\u91cd\u65b0\u9078\u64c7\r\n");
            return stringBuffer;
        }
        if (!"A4".equals(((OptionPair)this.copyType.getValue()).getValue())) return stringBuffer;
        if (this.chooseVersion.getValue() != null && !StringProcess.isEmpty(((OptionPair)this.chooseVersion.getValue()).getValue())) {
            if (this.copyVersion.getValue() != null && !StringProcess.isEmpty(((OptionPair)this.copyVersion.getValue()).getValue())) return stringBuffer;
            stringBuffer.append("\u8acb\u9078\u64c7\u8907\u88fd\u76ee\u7684\u7684\u5efa\u7269\u69cb\u9020\u7a2e\u985e\r\n");
            return stringBuffer;
        }
        if (this.copyVersion.getValue() == null || StringProcess.isEmpty(((OptionPair)this.copyVersion.getValue()).getValue())) return stringBuffer;
        stringBuffer.append("\u8acb\u9078\u64c7\u9078\u64c7\u76ee\u6a19\u7684\u5efa\u7269\u69cb\u9020\u7a2e\u985e\r\n");
        return stringBuffer;
    }

    private StringBuffer checkPKChoose(StringBuffer stringBuffer) {
        if (this.chooseAA45.getValue() == null || StringProcess.isEmpty(((OptionPair)this.chooseAA45.getValue()).getValue())) {
            stringBuffer.append("\u8acb\u9078\u64c7\u9078\u64c7\u76ee\u6a19\u7684\u7e23\u5e02\r\n");
        }
        if (this.copyAA45.getValue() == null || StringProcess.isEmpty(((OptionPair)this.copyAA45.getValue()).getValue())) {
            stringBuffer.append("\u8acb\u9078\u64c7\u8907\u88fd\u76ee\u7684\u7684\u7e23\u5e02\r\n");
        }
        if ((this.chooseYear.getValue() == null || StringProcess.isEmpty(((OptionPair)this.chooseYear.getValue()).getValue())) && !"A4".equals(((OptionPair)this.copyType.getValue()).getValue())) {
            if ("A1".equals(((OptionPair)this.copyType.getValue()).getValue()) || "A1".equals(((OptionPair)this.copyType.getValue()).getValue())) {
                stringBuffer.append("\u8acb\u9078\u64c7\u9078\u64c7\u76ee\u6a19\u7684\u5e74\u671f\r\n");
            } else if ("A3".equals(((OptionPair)this.copyType.getValue()).getValue())) {
                stringBuffer.append("\u8acb\u9078\u64c7\u9078\u64c7\u76ee\u6a19\u7684\u6307\u6578\u985e\u578b\r\n");
            } else {
                stringBuffer.append("\u8acb\u9078\u64c7\u9078\u64c7\u76ee\u6a19\u7684\u5e74\u5ea6\r\n");
            }
        }
        if ((this.copyYear.getValue() == null || StringProcess.isEmpty(((OptionPair)this.copyYear.getValue()).getValue())) && !"A4".equals(((OptionPair)this.copyType.getValue()).getValue())) {
            if ("A1".equals(((OptionPair)this.copyType.getValue()).getValue()) || "A1".equals(((OptionPair)this.copyType.getValue()).getValue())) {
                stringBuffer.append("\u8acb\u9078\u64c7\u8907\u88fd\u76ee\u7684\u7684\u5e74\u671f\r\n");
            } else if ("A3".equals(((OptionPair)this.copyType.getValue()).getValue())) {
                stringBuffer.append("\u8acb\u9078\u64c7\u8907\u88fd\u76ee\u7684\u7684\u6307\u6578\u985e\u578b\r\n");
            } else {
                stringBuffer.append("\u8acb\u9078\u64c7\u8907\u88fd\u76ee\u7684\u7684\u5e74\u5ea6\r\n");
            }
        }
        if ((this.chooseDist.getValue() == null || StringProcess.isEmpty(((OptionPair)this.chooseDist.getValue()).getValue())) && ("A1".equals(((OptionPair)this.copyType.getValue()).getValue()) || "A1".equals(((OptionPair)this.copyType.getValue()).getValue()))) {
            stringBuffer.append("\u8acb\u9078\u64c7\u9078\u64c7\u76ee\u6a19\u7684\u884c\u653f\u5340\r\n");
        }
        if ((this.copyDist.getValue() == null || StringProcess.isEmpty(((OptionPair)this.copyDist.getValue()).getValue())) && ("A1".equals(((OptionPair)this.copyType.getValue()).getValue()) || "A1".equals(((OptionPair)this.copyType.getValue()).getValue()))) {
            stringBuffer.append("\u8acb\u9078\u64c7\u8907\u88fd\u76ee\u7684\u7684\u884c\u653f\u5340\r\n");
        }
        if ("A3".equals(((OptionPair)this.copyType.getValue()).getValue())) {
            boolean bl = true;
            if (this.chooseVersion.getValue() == null || StringProcess.isEmpty(((OptionPair)this.chooseVersion.getValue()).getValue())) {
                stringBuffer.append("\u8acb\u9078\u64c7\u9078\u64c7\u76ee\u6a19\u7684\u5e74\u5ea6\r\n");
                bl = false;
            }
            if (this.copyVersion.getValue() == null || StringProcess.isEmpty(((OptionPair)this.copyVersion.getValue()).getValue())) {
                stringBuffer.append("\u8acb\u9078\u64c7\u8907\u88fd\u76ee\u7684\u7684\u5e74\u5ea6\r\n");
                bl = false;
            }
            if (bl && !((OptionPair)this.copyVersion.getValue()).getValue().equals(((OptionPair)this.chooseVersion.getValue()).getValue())) {
                stringBuffer.append("\u50c5\u80fd\u8907\u88fd\u76f8\u540c\u5e74\u5ea6\r\n");
            }
        }
        return stringBuffer;
    }

    private void createCopyType() {
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        arrayList.add(new OptionPair("A1", "\u5340\u57df\u56e0\u7d20\u8a55\u50f9\u57fa\u6e96\u8868"));
        arrayList.add(new OptionPair("A2", "\u500b\u5225\u56e0\u7d20\u8a55\u50f9\u57fa\u6e96\u8868"));
        arrayList.add(new OptionPair("A3", "\u50f9\u683c\u65e5\u671f\u8abf\u6574\u6307\u6578"));
        arrayList.add(new OptionPair("A4", "\u5efa\u7269\u69cb\u9020\u7269\u6a19\u6e96\u55ae\u50f9\u8a2d\u5b9a"));
        arrayList.add(new OptionPair("A5", "\u6b0a\u91cd\u53c3\u6578"));
        arrayList.add(new OptionPair("A7", "\u5176\u4ed6\u516c\u5831\u53c3\u6578"));
        this.copyType.setItems(FXCollections.observableArrayList(arrayList));
    }

    @Override
    public void refresh() {
    }
}

