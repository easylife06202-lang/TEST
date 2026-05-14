/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.beans.value.ChangeListener
 *  javafx.beans.value.ObservableValue
 *  javafx.collections.FXCollections
 *  javafx.fxml.FXML
 *  javafx.scene.Node
 *  javafx.scene.control.Button
 *  javafx.scene.control.ComboBox
 *  javafx.scene.control.TextField
 *  javafx.scene.layout.AnchorPane
 *  javafx.stage.Stage
 *  javafx.stage.Window
 */
package com.wfusion.baseland.estimate;

import com.wfusion.baseland.basic.BaseLandDialog;
import com.wfusion.baseland.estimate.EstimateController;
import com.wfusion.baseland.estimate.EstimateModel;
import com.wfusion.baseland.estimate.EstimateReport7Model;
import com.wfusion.baseland.estimate.ImageBrowserRentDialog;
import com.wfusion.fx.node.AbsTextField;
import com.wfusion.fx.node.DecimalField;
import com.wfusion.fx.node.StringArea;
import com.wfusion.fx.node.StringField;
import com.wfusion.fx.util.JavaFXUtil;
import com.wfusion.util.OptionPair;
import com.wfusion.util.StringProcess;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import moiland.baseland.bo.SaveCheckBo;

public class EstimateReport7Controller {
    @FXML
    private AnchorPane report7;
    @FXML
    private ComboBox<OptionPair> cr48;
    @FXML
    private StringField cr06;
    @FXML
    private DecimalField cr11;
    @FXML
    private DecimalField cr03;
    @FXML
    private DecimalField cr09;
    @FXML
    private DecimalField cr42;
    @FXML
    private DecimalField cr43;
    @FXML
    private AnchorPane panelMonth;
    @FXML
    private DecimalField weight1;
    @FXML
    private ComboBox<OptionPair> rent_date1;
    @FXML
    private ComboBox<OptionPair> rent_date2;
    @FXML
    private DecimalField weight0;
    @FXML
    private ComboBox<OptionPair> rent_date0;
    @FXML
    private ComboBox<OptionPair> rental_type1;
    @FXML
    private ComboBox<OptionPair> rental_type2;
    @FXML
    private ComboBox<OptionPair> near_adj1;
    @FXML
    private ComboBox<OptionPair> near_adj2;
    @FXML
    private DecimalField weight2;
    @FXML
    private ComboBox<OptionPair> rental_type0;
    @FXML
    private ComboBox<OptionPair> near_adj0;
    @FXML
    private DecimalField tmpcr09;
    @FXML
    private Button btPhotos1;
    @FXML
    private Button btPhotos2;
    @FXML
    private Button btPhotos3;
    @FXML
    private Button bt_priceInsert;
    @FXML
    private DecimalField cr30;
    @FXML
    private DecimalField cr46;
    EstimateController parentController = null;
    EstimateReport7Model model = new EstimateReport7Model();
    SaveCheckBo checkbo = new SaveCheckBo(EstimateModel.BASELANDBEAN);
    boolean isFirstRefresh = true;
    ChangeListener<Boolean> lostFocusListener = new ChangeListener<Boolean>(){

        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean bl, Boolean bl2) {
            if (!bl2.booleanValue()) {
                EstimateReport7Controller.this.reCal();
            }
        }
    };
    ChangeListener<Boolean> lostFocusMonthListener = new ChangeListener<Boolean>(){

        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean bl, Boolean bl2) {
            if (EstimateReport7Controller.this.isFirstRefresh) {
                return;
            }
            if (!bl2.booleanValue()) {
                EstimateReport7Controller.this.reCalMonth();
                EstimateReport7Controller.this.reCal();
            }
        }
    };
    ChangeListener<OptionPair> chageMonthListener = new ChangeListener<OptionPair>(){

        public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
            if (EstimateReport7Controller.this.isFirstRefresh) {
                return;
            }
            EstimateReport7Controller.this.reCalMonth();
            EstimateReport7Controller.this.reCal();
        }
    };

    public void init(BaseLandDialog baseLandDialog, EstimateController estimateController) {
        this.parentController = estimateController;
        this.initCombox();
        this.initTextField();
        this.btPhotos1.setOnAction(actionEvent -> {
            Node node = (Node)actionEvent.getSource();
            Window window = node.getScene().getWindow();
            ImageBrowserRentDialog imageBrowserRentDialog = new ImageBrowserRentDialog((Stage)window);
            imageBrowserRentDialog.load("\u6536\u76ca\u6cd5-\u63a8\u4f30\u6708\u79df\u91d1-\u6536\u76ca\u5be6\u4f8b1", "RENT1", EstimateModel.BASELANDBEAN.voRentMonth1);
            imageBrowserRentDialog.show();
        });
        this.btPhotos2.setOnAction(actionEvent -> {
            Node node = (Node)actionEvent.getSource();
            Window window = node.getScene().getWindow();
            ImageBrowserRentDialog imageBrowserRentDialog = new ImageBrowserRentDialog((Stage)window);
            imageBrowserRentDialog.load("\u6536\u76ca\u6cd5-\u63a8\u4f30\u6708\u79df\u91d1-\u6536\u76ca\u5be6\u4f8b2", "RENT2", EstimateModel.BASELANDBEAN.voRentMonth2);
            imageBrowserRentDialog.show();
        });
        this.btPhotos3.setOnAction(actionEvent -> {
            Node node = (Node)actionEvent.getSource();
            Window window = node.getScene().getWindow();
            ImageBrowserRentDialog imageBrowserRentDialog = new ImageBrowserRentDialog((Stage)window);
            imageBrowserRentDialog.load("\u6536\u76ca\u6cd5-\u63a8\u4f30\u6708\u79df\u91d1-\u6536\u76ca\u5be6\u4f8b3", "RENT3", EstimateModel.BASELANDBEAN.voRentMonth3);
            imageBrowserRentDialog.show();
        });
        this.bt_priceInsert.setOnAction(actionEvent -> {
            int n = this.model.reCalRentMonth();
            if (n > 0) {
                this.cr09.setValue(Integer.toString(n));
                this.model.voValue.put("cr09", String.valueOf(n));
                this.reCal();
            }
        });
        this.cr06.focusedProperty().addListener((ChangeListener)new ChangeListener<Boolean>(){

            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean bl, Boolean bl2) {
                if (EstimateReport7Controller.this.isFirstRefresh) {
                    return;
                }
                if (!bl2.booleanValue() && !StringProcess.isEmpty(EstimateReport7Controller.this.cr06.getValue()) && "5168".equals(Integer.toHexString(EstimateReport7Controller.this.cr06.getValue().charAt(0)))) {
                    EstimateReport7Controller.this.cr42.setValue("100");
                    EstimateReport7Controller.this.cr43.setValue("100");
                    EstimateReport7Controller.this.reCal();
                }
            }
        });
    }

    private void initTextField() {
        String[] stringArray;
        String string;
        String string2 = EstimateModel.BASELANDBEAN.voRent.getTableName().toUpperCase();
        String string3 = EstimateModel.BASELANDBEAN.voRentMonth1.getTableName().toUpperCase();
        for (Node node : this.report7.getChildren()) {
            if (node instanceof DecimalField) {
                string = node.getId().toUpperCase();
                stringArray = SaveCheckBo.columnSet.get(string2 + "_" + string);
                if (stringArray != null && stringArray.length == 4) {
                    ((DecimalField)node).setName(stringArray[0]);
                    ((DecimalField)node).setPrecision(StringProcess.parserInt(stringArray[2]));
                    ((DecimalField)node).setScale(StringProcess.parserInt(stringArray[3]));
                }
                node.focusedProperty().addListener(this.lostFocusListener);
                continue;
            }
            if (node instanceof StringField) {
                string = node.getId().toUpperCase();
                stringArray = SaveCheckBo.columnSet.get(string2 + "_" + string);
                if (stringArray != null && stringArray.length == 4) {
                    ((StringField)node).setName(stringArray[0]);
                    ((StringField)node).setMaxLength(StringProcess.parserInt(stringArray[2]));
                    ((StringField)node).setMinLength(StringProcess.parserInt(stringArray[3]));
                }
                node.focusedProperty().addListener(this.lostFocusListener);
                continue;
            }
            if (node instanceof StringArea) {
                string = node.getId().toUpperCase();
                stringArray = SaveCheckBo.columnSet.get(string2 + "_" + string);
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
        for (Node node : this.panelMonth.getChildren()) {
            if (StringProcess.isEmpty(node.getId())) continue;
            string = node.getId().substring(0, node.getId().length() - 1).toUpperCase();
            if (node instanceof DecimalField) {
                stringArray = SaveCheckBo.columnSet.get(string3 + "_" + string);
                if (stringArray != null && stringArray.length == 4) {
                    ((DecimalField)node).setPrecision(StringProcess.parserInt(stringArray[2]));
                    ((DecimalField)node).setScale(StringProcess.parserInt(stringArray[3]));
                }
                node.focusedProperty().addListener(this.lostFocusMonthListener);
                continue;
            }
            if (node instanceof StringField) {
                stringArray = SaveCheckBo.columnSet.get(string3 + "_" + string);
                if (stringArray != null && stringArray.length == 4) {
                    ((StringField)node).setMaxLength(StringProcess.parserInt(stringArray[2]));
                    ((StringField)node).setMinLength(StringProcess.parserInt(stringArray[3]));
                }
                node.focusedProperty().addListener(this.lostFocusMonthListener);
                continue;
            }
            if (!(node instanceof TextField)) continue;
            node.focusedProperty().addListener(this.lostFocusMonthListener);
        }
    }

    private void initCombox() {
        this.cr48.setItems(FXCollections.observableArrayList(this.model.getCR48List()));
        this.cr48.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                if (EstimateReport7Controller.this.isFirstRefresh) {
                    return;
                }
                if (Integer.parseInt(optionPair2.getValue()) <= 2) {
                    if (Integer.parseInt(EstimateModel.BASELANDBEAN.queryBean.year) > 113) {
                        JavaFXUtil.showErrorMessageBox("\u8a72\u65b9\u6cd5\u5df2\u5ee2\u68c4\uff0c\u8acb\u9078\u64c7\u5176\u4ed6\u65b9\u6cd5");
                        try {
                            EstimateReport7Controller.this.cr48.getSelectionModel().select(3);
                        }
                        catch (Exception exception) {
                            System.out.println(exception.getMessage());
                        }
                        return;
                    }
                    JavaFXUtil.showErrorMessageBox("\u8a72\u65b9\u6cd5\u5df2\u5ee2\u68c4");
                }
                EstimateReport7Controller.this.model.voValue.put("cr48", optionPair2.getValue());
                EstimateReport7Controller.this.model.voValue.put("cr48name", optionPair2.getAlias());
                EstimateReport7Controller.this.updateVoFromUI();
            }
        });
        this.rental_type0.setItems(FXCollections.observableArrayList(this.model.getRental_typeList()));
        this.rental_type0.getSelectionModel().selectFirst();
        this.rental_type0.getSelectionModel().selectedItemProperty().addListener(this.chageMonthListener);
        this.rental_type1.setItems(FXCollections.observableArrayList(this.model.getRental_typeList()));
        this.rental_type1.getSelectionModel().selectFirst();
        this.rental_type1.getSelectionModel().selectedItemProperty().addListener(this.chageMonthListener);
        this.rental_type2.setItems(FXCollections.observableArrayList(this.model.getRental_typeList()));
        this.rental_type2.getSelectionModel().selectFirst();
        this.rental_type2.getSelectionModel().selectedItemProperty().addListener(this.chageMonthListener);
        this.rent_date0.setItems(FXCollections.observableArrayList(this.model.getRent_dateList()));
        this.rent_date0.getSelectionModel().selectFirst();
        this.rent_date0.getSelectionModel().selectedItemProperty().addListener(this.chageMonthListener);
        this.rent_date1.setItems(FXCollections.observableArrayList(this.model.getRent_dateList()));
        this.rent_date1.getSelectionModel().selectFirst();
        this.rent_date1.getSelectionModel().selectedItemProperty().addListener(this.chageMonthListener);
        this.rent_date2.setItems(FXCollections.observableArrayList(this.model.getRent_dateList()));
        this.rent_date2.getSelectionModel().selectFirst();
        this.rent_date2.getSelectionModel().selectedItemProperty().addListener(this.chageMonthListener);
        this.near_adj0.setItems(FXCollections.observableArrayList(this.model.getNear_adjList()));
        this.near_adj0.getSelectionModel().selectFirst();
        this.near_adj1.setItems(FXCollections.observableArrayList(this.model.getNear_adjList()));
        this.near_adj1.getSelectionModel().selectFirst();
        this.near_adj2.setItems(FXCollections.observableArrayList(this.model.getNear_adjList()));
        this.near_adj2.getSelectionModel().selectFirst();
    }

    public void refresh() {
        this.isFirstRefresh = true;
        this.updateUIFromVo();
        this.updateUIFromMonthVo();
        this.refreshCombox();
        this.isFirstRefresh = false;
        this.reCalMonth();
        this.reCal();
    }

    protected void updateUIFromVo() {
        this.model.updateHashMapValues();
        Object object = this.report7.getChildren().iterator();
        while (object.hasNext()) {
            Node node = (Node)object.next();
            String string = node.getId();
            if (string == null || !this.model.voValue.containsKey(string)) continue;
            if (node instanceof AbsTextField) {
                ((AbsTextField)node).setValue(this.model.voValue.get(string).toString());
            } else if (node instanceof TextField) {
                ((TextField)node).setText(this.model.voValue.get(string).toString());
            }
            if (node instanceof StringArea) {
                ((StringArea)node).setText(this.model.voValue.get(string).toString());
            }
            if (!(node instanceof ComboBox)) continue;
            ((ComboBox)node).getSelectionModel().select((Object)this.model.voValue.get(string).toString());
        }
        if (EstimateModel.BASELANDBEAN.voRent.isBuilding()) {
            this.cr03.setEditable(false);
            if (!this.cr03.getStyleClass().contains((Object)"forbidden")) {
                this.cr03.getStyleClass().add((Object)"forbidden");
            }
            this.cr30.setEditable(true);
            if (this.cr30.getStyleClass().contains((Object)"forbidden")) {
                this.cr30.getStyleClass().remove((Object)"forbidden");
            }
            this.cr46.setEditable(true);
            if (this.cr46.getStyleClass().contains((Object)"forbidden")) {
                this.cr46.getStyleClass().remove((Object)"forbidden");
            }
        } else {
            this.cr03.setEditable(true);
            this.cr03.getStyleClass().remove((Object)"forbidden");
            this.cr30.setValue("0");
            this.cr30.setEditable(false);
            if (!this.cr30.getStyleClass().contains((Object)"forbidden")) {
                this.cr30.getStyleClass().add((Object)"forbidden");
            }
            this.cr46.setValue("0");
            this.cr46.setEditable(false);
            if (!this.cr46.getStyleClass().contains((Object)"forbidden")) {
                this.cr46.getStyleClass().add((Object)"forbidden");
            }
        }
        if (StringProcess.isEmpty((String)(object = this.model.voValue.get("cr09").toString())) || "0".equals(object)) {
            this.cr11.setEditable(true);
            this.cr11.getStyleClass().remove((Object)"forbidden");
        } else {
            this.cr11.setEditable(false);
            if (!this.cr11.getStyleClass().contains((Object)"forbidden")) {
                this.cr11.getStyleClass().add((Object)"forbidden");
            }
        }
    }

    private void updateUIFromMonthVo() {
        this.model.updateMonthHashMapValues();
        for (Node node : this.panelMonth.getChildren()) {
            String string = node.getId();
            if (string == null || !this.model.monthVoValue.containsKey(string) || !(node instanceof TextField)) continue;
            ((TextField)node).setText(this.model.monthVoValue.get(string).toString());
        }
    }

    protected void updateVoFromUI() {
        for (Node node : this.report7.getChildren()) {
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
        this.model.voValue.put("cr48", ((OptionPair)this.cr48.getValue()).getValue());
        this.model.updateVo();
    }

    protected void updateMonthVoFromUI() {
        for (Node node : this.panelMonth.getChildren()) {
            OptionPair optionPair;
            if (!this.model.monthVoValue.containsKey(node.getId())) continue;
            if (node instanceof DecimalField) {
                this.model.monthVoValue.put(node.getId(), ((DecimalField)node).getValue().toString());
            } else if (node instanceof StringField) {
                this.model.monthVoValue.put(node.getId(), ((StringField)node).getValue().toString());
            } else if (node instanceof TextField) {
                this.model.monthVoValue.put(node.getId(), ((TextField)node).getText().toString());
            }
            if (!(node instanceof ComboBox) || (optionPair = (OptionPair)((ComboBox)node).getSelectionModel().getSelectedItem()) == null || StringProcess.isEmpty(optionPair.getValue())) continue;
            this.model.monthVoValue.put(node.getId(), optionPair.getValue());
        }
        this.model.updateMonthVo();
    }

    void reCal() {
        this.updateVoFromUI();
        this.model.reCal();
        this.updateUIFromVo();
    }

    void reCalMonth() {
        this.updateMonthVoFromUI();
        int n = (int)(StringProcess.parserDouble(this.weight0.getValue().toString(), 0.0) * 100.0);
        int n2 = (int)(StringProcess.parserDouble(this.weight1.getValue().toString(), 0.0) * 100.0);
        int n3 = (int)(StringProcess.parserDouble(this.weight2.getValue().toString(), 0.0) * 100.0);
        if (n != 0 && n2 != 0 && n3 != 0 && n + n2 + n3 != 10000) {
            JavaFXUtil.showToastMessageBox(this.parentController.dialog.getStage(), "\u6c7a\u5b9a\u6b0a\u91cd\u52a0\u7e3d\u9700\u70ba100", 1500);
            return;
        }
        int n4 = this.model.reCalRentMonth();
        this.tmpcr09.setValue(String.valueOf(n4));
        this.updateUIFromMonthVo();
    }

    private void refreshCombox() {
        this.rental_type0.getSelectionModel().select(StringProcess.parserInt(EstimateModel.BASELANDBEAN.voRentMonth1.getRental_type()));
        this.rental_type1.getSelectionModel().select(StringProcess.parserInt(EstimateModel.BASELANDBEAN.voRentMonth2.getRental_type()));
        this.rental_type2.getSelectionModel().select(StringProcess.parserInt(EstimateModel.BASELANDBEAN.voRentMonth3.getRental_type()));
        this.rent_date0.getSelectionModel().select(StringProcess.parserInt(EstimateModel.BASELANDBEAN.voRentMonth1.getRent_date()));
        this.rent_date1.getSelectionModel().select(StringProcess.parserInt(EstimateModel.BASELANDBEAN.voRentMonth2.getRent_date()));
        this.rent_date2.getSelectionModel().select(StringProcess.parserInt(EstimateModel.BASELANDBEAN.voRentMonth3.getRent_date()));
        this.near_adj0.getSelectionModel().select(EstimateModel.BASELANDBEAN.voRentMonth1.getNear_adj());
        this.near_adj1.getSelectionModel().select(EstimateModel.BASELANDBEAN.voRentMonth2.getNear_adj());
        this.near_adj2.getSelectionModel().select(EstimateModel.BASELANDBEAN.voRentMonth3.getNear_adj());
    }
}

