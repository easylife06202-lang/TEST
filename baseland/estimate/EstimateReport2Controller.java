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
 *  javafx.scene.control.Label
 *  javafx.scene.control.TextField
 *  javafx.scene.layout.AnchorPane
 */
package com.wfusion.baseland.estimate;

import com.wfusion.baseland.basic.BaseLandDialog;
import com.wfusion.baseland.estimate.EstimateController;
import com.wfusion.baseland.estimate.EstimateModel;
import com.wfusion.baseland.estimate.EstimateReport2Model;
import com.wfusion.baseland.system.GlossaryModel;
import com.wfusion.fx.node.AbsTextField;
import com.wfusion.fx.node.DecimalField;
import com.wfusion.fx.node.StringArea;
import com.wfusion.fx.node.StringField;
import com.wfusion.fx.util.JavaFXUtil;
import com.wfusion.util.OptionPair;
import com.wfusion.util.StringProcess;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import moiland.baseland.bo.SaveCheckBo;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_AHP;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_FACTOR_CODE;
import moiland.baseland.factor.bean.FactorLevelBean;
import moiland.baseland.factor.bean.FactorScoreBean;

public class EstimateReport2Controller {
    @FXML
    private AnchorPane caseMain;
    @FXML
    private AnchorPane case0;
    @FXML
    private AnchorPane case1;
    @FXML
    private AnchorPane case2;
    @FXML
    private AnchorPane case3;
    @FXML
    private StringArea notes;
    @FXML
    private AnchorPane caseLabel;
    @FXML
    private ComboBox<OptionPair> cbLiteral;
    @FXML
    private Button btAddLiteral;
    @FXML
    private StringField baseno;
    @FXML
    private DecimalField fin_ahp1;
    @FXML
    private DecimalField fin_ahp2;
    @FXML
    private DecimalField fin_ahp3;
    @FXML
    private DecimalField as307_dv_1;
    @FXML
    private TextField rateVersion;
    @FXML
    private TextField reginalVersion;
    @FXML
    private TextField individualVersion;
    private ArrayList<AnchorPane> panes = new ArrayList();
    EstimateReport2Model model = new EstimateReport2Model();
    EstimateController parentController = null;
    SaveCheckBo checkbo = new SaveCheckBo(EstimateModel.BASELANDBEAN);
    String colorRed = "-fx-text-inner-color: red;";
    boolean isFirstRefresh = true;
    boolean queryAHP = false;
    boolean haveAHP = true;

    public void init(BaseLandDialog baseLandDialog, EstimateController estimateController) {
        this.parentController = estimateController;
        this.queryAHP();
        this.panes.add(this.case0);
        this.panes.add(this.case1);
        this.panes.add(this.case2);
        this.panes.add(this.case3);
        this.initCombox();
        this.initPanel(this.case0, "_0", this.model.voValues0);
        this.initPanel(this.case1, "_1", this.model.voValues1);
        this.initPanel(this.case2, "_2", this.model.voValues2);
        this.initPanel(this.case3, "_3", this.model.voValues3);
        this.initPanel(this.caseLabel, "", this.model.voValues0);
        this.initCaseMainPanel();
        this.btAddLiteral.setOnAction(actionEvent -> {
            String string = this.notes.getValue();
            if (!StringProcess.isEmpty(string)) {
                GlossaryModel glossaryModel = new GlossaryModel();
                glossaryModel.addLiteral(string, "APPR", "APPR_NOTES");
                glossaryModel.query("APPR", "APPR_NOTES", 0, "");
                this.cbLiteral.setItems(FXCollections.observableArrayList(glossaryModel.getDataOptionpairs()));
                this.cbLiteral.getSelectionModel().selectLast();
                JavaFXUtil.showToastMessageBox(estimateController.dialog.getStage(), "\u5df2\u65b0\u589e\u8fad\u5eab", 1500);
            }
        });
    }

    private void initCaseMainPanel() {
        if (!this.haveAHP) {
            return;
        }
        String string = EstimateModel.BASELANDBEAN.voAppRaMain.getTableName().toUpperCase();
        for (Node node : this.caseMain.getChildren()) {
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
                node.focusedProperty().addListener(this.lostFocusListener(node));
                ((TextField)node).setEditable(!node.getStyleClass().contains((Object)"forbidden"));
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
                node.focusedProperty().addListener(this.lostFocusListener(node));
                ((TextField)node).setEditable(!node.getStyleClass().contains((Object)"forbidden"));
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
                node.focusedProperty().addListener(this.lostFocusListener(node));
                continue;
            }
            if (!(node instanceof TextField)) continue;
            node.focusedProperty().addListener(this.lostFocusListener(node));
            ((TextField)node).setEditable(!node.getStyleClass().contains((Object)"forbidden"));
        }
    }

    private void initPanel(AnchorPane anchorPane, String string, HashMap<String, Object> hashMap) {
        if (!this.haveAHP) {
            return;
        }
        String string2 = EstimateModel.BASELANDBEAN.voAppRaA3Vo0.getTableName().toUpperCase();
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("as315_nm");
        arrayList.add("as345_nm");
        arrayList.add("as350_nm");
        arrayList.add("as351_nm");
        arrayList.add("as352_nm");
        arrayList.add("as353_nm");
        arrayList.add("as354_nm");
        for (Node node : anchorPane.getChildren()) {
            String[] stringArray;
            String string3;
            if (node instanceof DecimalField) {
                string3 = node.getId().replace(string, "").toUpperCase();
                stringArray = SaveCheckBo.columnSet.get(string2 + "_" + string3);
                if (stringArray != null && stringArray.length == 4) {
                    ((DecimalField)node).setName(stringArray[0]);
                    ((DecimalField)node).setPrecision(StringProcess.parserInt(stringArray[2]));
                    ((DecimalField)node).setScale(StringProcess.parserInt(stringArray[3]));
                }
                node.focusedProperty().addListener(this.lostFocusListener(node));
                ((TextField)node).setEditable(!node.getStyleClass().contains((Object)"forbidden"));
                if (node.getStyleClass().contains((Object)"thirtyChe")) {
                    ((DecimalField)node).textProperty().addListener(this.perCheListener((DecimalField)node, 30));
                    continue;
                }
                if (node.getStyleClass().contains((Object)"fifteenChe")) {
                    ((DecimalField)node).textProperty().addListener(this.perCheListener((DecimalField)node, 15));
                    continue;
                }
                if (!node.getStyleClass().contains((Object)"twentyChe")) continue;
                ((DecimalField)node).textProperty().addListener(this.perCheListener((DecimalField)node, 19));
                continue;
            }
            if (node instanceof StringField) {
                string3 = node.getId().replace(string, "").toUpperCase();
                stringArray = SaveCheckBo.columnSet.get(string2 + "_" + string3);
                if (stringArray != null && stringArray.length == 4) {
                    ((StringField)node).setName(stringArray[0]);
                    ((StringField)node).setMaxLength(StringProcess.parserInt(stringArray[2]));
                    ((StringField)node).setMinLength(StringProcess.parserInt(stringArray[3]));
                }
                if (string3.length() >= 8) {
                    if (!arrayList.contains(string3.substring(0, 8).toLowerCase())) {
                        node.focusedProperty().addListener(this.lostFocusListener(node));
                    }
                } else {
                    node.focusedProperty().addListener(this.lostFocusListener(node));
                }
                ((TextField)node).setEditable(!node.getStyleClass().contains((Object)"forbidden"));
                continue;
            }
            if (node instanceof StringArea) {
                string3 = node.getId().replace(string, "").toUpperCase();
                stringArray = SaveCheckBo.columnSet.get(string2 + "_" + string3);
                if (stringArray != null && stringArray.length == 4) {
                    ((StringArea)node).setName(stringArray[0]);
                    ((StringArea)node).setMaxLength(StringProcess.parserInt(stringArray[2]));
                    ((StringArea)node).setMinLength(StringProcess.parserInt(stringArray[3]));
                }
                ((StringArea)node).setEditable(!node.getStyleClass().contains((Object)"forbidden"));
                continue;
            }
            if (node instanceof TextField) {
                node.focusedProperty().addListener(this.lostFocusListener(node));
                ((TextField)node).setEditable(!node.getStyleClass().contains((Object)"forbidden"));
                continue;
            }
            if (!(node instanceof ComboBox) || node.getId() == null) continue;
            node.focusedProperty().addListener(this.lostFocusListener(node));
        }
    }

    private ChangeListener<? super Boolean> lostFocusListener(final Node node) {
        ChangeListener<Boolean> changeListener = new ChangeListener<Boolean>(){

            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean bl, Boolean bl2) {
                if (!bl2.booleanValue()) {
                    EstimateReport2Controller.this.autoSwitchLevel(node, EstimateReport2Controller.this.isFirstRefresh);
                    EstimateReport2Controller.this.reCal();
                }
            }
        };
        return changeListener;
    }

    private ChangeListener<String> perCheListener(final DecimalField decimalField, final int n) {
        ChangeListener<String> changeListener = new ChangeListener<String>(){

            public void changed(ObservableValue<? extends String> observableValue, String string, String string2) {
                if (!StringProcess.isEmpty(string2)) {
                    String string3 = decimalField.getValue().toString().replace(",", "");
                    if (StringProcess.parserDouble(string3) > (double)n || StringProcess.parserDouble(string3) < (double)(-n)) {
                        if (n == 30 || n == 19) {
                            decimalField.getStyleClass().remove((Object)"forbidden");
                            if (!decimalField.getStyleClass().contains((Object)"forbidden2")) {
                                decimalField.getStyleClass().add((Object)"forbidden2");
                            }
                        } else {
                            decimalField.setStyle(EstimateReport2Controller.this.colorRed);
                        }
                    } else if (n == 30) {
                        decimalField.getStyleClass().remove((Object)"forbidden2");
                        if (!decimalField.getStyleClass().contains((Object)"forbidden")) {
                            decimalField.getStyleClass().add((Object)"forbidden");
                        }
                    } else {
                        decimalField.setStyle("");
                    }
                }
            }
        };
        return changeListener;
    }

    void reCal() {
        this.updateVoFromUI();
        int n = this.model.checkSum100();
        if (n != 0 && n != 100) {
            JavaFXUtil.showToastMessageBox(this.parentController.dialog.getStage(), "\u6a19\u7684\u6b0a\u6578\u52a0\u7e3d\u9700\u70ba100\uff0c\u76ee\u524d" + n, 1500);
            return;
        }
        this.model.calAppraisal();
        this.updateUIFromVo();
    }

    public void refresh() {
        this.isFirstRefresh = true;
        if (!this.haveAHP) {
            JavaFXUtil.showErrorMessageBox("\u67e5\u7121\u6b0a\u91cd\u53c3\u6578", "\u67e5\u7121\u6b0a\u91cd\u53c3\u6578\uff0c\u8acb\u5148\u81f3\u7cfb\u7d71\u7ba1\u7406-\u6b0a\u91cd\u53c3\u6578\u9032\u884c\u8a2d\u5b9a");
            return;
        }
        this.model.updateMap();
        this.updateUIFromVo();
        this.refreshLabel();
        this.getParaVersion();
        this.isFirstRefresh = false;
        this.specialColmnSet(this.isFirstRefresh);
    }

    private void queryAHP() {
        if (!this.queryAHP) {
            NVO_BASELAND_AHP nVO_BASELAND_AHP = this.parentController.model.getAHP(EstimateModel.BASELANDBEAN.queryBean);
            this.haveAHP = nVO_BASELAND_AHP != null ? nVO_BASELAND_AHP.isHaveData() : false;
        }
    }

    private void specialColmnSet(boolean bl) {
        this.case0ColRef(bl);
        this.caseColRef(this.case1, bl);
        this.caseColRef(this.case2, bl);
        this.caseColRef(this.case3, bl);
    }

    private void caseColRef(AnchorPane anchorPane, boolean bl) {
        for (Node node : anchorPane.getChildren()) {
            String string = node.getId();
            if (string == null || string.indexOf("as339") <= -1 || EstimateModel.col_update.get(string) == null || !EstimateModel.col_update.get(string).booleanValue()) continue;
            EstimateModel.col_update.put(string, false);
            this.autoSwitchLevel(node, bl);
        }
    }

    private void case0ColRef(boolean bl) {
        for (Node node : this.case0.getChildren()) {
            String string = node.getId();
            if (string == null || !"as339_0".equals(string) && !"as340_ds_0".equals(string) && !"as341_ds_0".equals(string) && !"as365_ds_0".equals(string) && !"as366_ds_0".equals(string) || EstimateModel.col_update.get(string) == null || !EstimateModel.col_update.get(string).booleanValue()) continue;
            EstimateModel.col_update.put(string, false);
            this.autoSwitchLevel(node, bl);
        }
    }

    private void getParaVersion() {
        try {
            this.model.getParaVersion();
        }
        catch (Exception exception) {
            exception.printStackTrace();
            JavaFXUtil.showToastMessageBox(this.parentController.dialog.getStage(), exception.getMessage(), 5000);
        }
        this.rateVersion.setText(this.model.priceRateVersion);
        this.reginalVersion.setText(EstimateModel.reginVersion);
        this.individualVersion.setText(EstimateModel.individualVersion);
    }

    private void initCombox() {
        for (AnchorPane anchorPane : this.panes) {
            this.initCombox(anchorPane, "as302", this.model.getAS302List());
            this.initCombox(anchorPane, "as303", this.model.getAS303List());
            this.initCombox(anchorPane, "as342_nm", this.model.getas342List());
            this.initCombox(anchorPane, "as343_nm", this.model.getas343List());
            this.initCombox(anchorPane, "as347_nm", this.model.getas347List());
            this.initCombox364(anchorPane, "as364_nm", this.model.getAS364List());
            this.initCombox(anchorPane, "as368_nm", this.model.getas368List());
            this.initCombox(anchorPane, "price_type", this.model.getPriceTypeList());
            this.initComboxListener(anchorPane);
        }
        GlossaryModel glossaryModel = new GlossaryModel();
        glossaryModel.query("APPR", "APPR_NOTES", 0, "");
        this.cbLiteral.setItems(FXCollections.observableArrayList(glossaryModel.getDataOptionpairs()));
        this.cbLiteral.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                if (optionPair2 != null && !StringProcess.isEmpty(optionPair2.getValue())) {
                    EstimateReport2Controller.this.notes.setText(optionPair2.getAlias());
                }
            }
        });
    }

    private void initCombox364(AnchorPane anchorPane, String string, ArrayList<OptionPair> arrayList) {
        for (Node node : anchorPane.getChildren()) {
            String string2 = node.getId();
            if (string2 == null || !string2.contains(string) || !(node instanceof ComboBox)) continue;
            ComboBox comboBox = (ComboBox)node;
            comboBox.setItems(FXCollections.observableArrayList(arrayList));
            comboBox.getSelectionModel().selectFirst();
            comboBox.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

                public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                    if (EstimateReport2Controller.this.isFirstRefresh || optionPair2 == null) {
                        return;
                    }
                    EstimateReport2Controller.this.reCal();
                }
            });
        }
    }

    protected void initCombox(AnchorPane anchorPane, String string, ArrayList<FactorScoreBean> arrayList) {
        for (final Node node : anchorPane.getChildren()) {
            String string2 = node.getId();
            if (string2 == null || !string2.contains(string) || !(node instanceof ComboBox)) continue;
            ComboBox comboBox = (ComboBox)node;
            comboBox.setItems(FXCollections.observableArrayList(arrayList));
            comboBox.getSelectionModel().selectFirst();
            comboBox.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<FactorScoreBean>(){

                public void changed(ObservableValue<? extends FactorScoreBean> observableValue, FactorScoreBean factorScoreBean, FactorScoreBean factorScoreBean2) {
                    if (EstimateReport2Controller.this.isFirstRefresh || factorScoreBean2 == null) {
                        return;
                    }
                    EstimateReport2Controller.this.autoSwitchLevel(node, EstimateReport2Controller.this.isFirstRefresh);
                    EstimateReport2Controller.this.reCal();
                }
            });
        }
    }

    private void initComboxListener(AnchorPane anchorPane) {
        for (Node node : anchorPane.getChildren()) {
            if (!(node instanceof ComboBox)) continue;
            String string = node.getId();
            String string2 = string.replaceAll("_0", "").replaceAll("_1", "").replaceAll("_2", "").replaceAll("_3", "").replaceAll("_lv", "");
            if (!string.contains("_lv")) continue;
            if (string.endsWith("_0")) {
                ((ComboBox)node).getSelectionModel().selectedItemProperty().addListener((ChangeListener)new MainComboxListener(string2));
                continue;
            }
            if (string.endsWith("_1")) {
                ((ComboBox)node).getSelectionModel().selectedItemProperty().addListener((ChangeListener)new SubComboxListener(string2, "_1", this.case1));
                continue;
            }
            if (string.endsWith("_2")) {
                ((ComboBox)node).getSelectionModel().selectedItemProperty().addListener((ChangeListener)new SubComboxListener(string2, "_2", this.case2));
                continue;
            }
            if (!string.endsWith("_3")) continue;
            ((ComboBox)node).getSelectionModel().selectedItemProperty().addListener((ChangeListener)new SubComboxListener(string2, "_3", this.case3));
        }
    }

    private void refreshCombox(AnchorPane anchorPane, Map<String, FactorLevelBean> map, Map<String, FactorLevelBean> map2) {
        for (Node node : anchorPane.getChildren()) {
            if (!(node instanceof ComboBox)) continue;
            ArrayList<FactorScoreBean> arrayList = new ArrayList<FactorScoreBean>();
            arrayList.add(new FactorScoreBean(0, 0.0, "\u8acb\u9078\u64c7"));
            String string = node.getId().replaceAll("_0", "").replaceAll("_1", "").replaceAll("_2", "").replaceAll("_3", "").replaceAll("_lv", "");
            if (map.containsKey(string)) {
                arrayList.addAll(map.get(string).getLvList());
                ((ComboBox)node).setItems(FXCollections.observableArrayList(arrayList));
                ((ComboBox)node).getSelectionModel().selectFirst();
            }
            if (!map2.containsKey(string)) continue;
            arrayList.addAll(map2.get(string).getLvList());
            ((ComboBox)node).setItems(FXCollections.observableArrayList(arrayList));
            ((ComboBox)node).getSelectionModel().selectFirst();
        }
    }

    public void refreshCombox() {
        Map<String, FactorLevelBean> map = this.parentController.model.factorRegional;
        LinkedHashMap<String, FactorLevelBean> linkedHashMap = new LinkedHashMap<String, FactorLevelBean>();
        for (FactorLevelBean object2 : map.values()) {
            linkedHashMap.put(object2.getItemField().toLowerCase(), object2);
        }
        Map<String, FactorLevelBean> map2 = this.parentController.model.factorIndividual;
        LinkedHashMap<String, FactorLevelBean> linkedHashMap2 = new LinkedHashMap<String, FactorLevelBean>();
        for (FactorLevelBean factorLevelBean : map2.values()) {
            linkedHashMap2.put(factorLevelBean.getItemField().toLowerCase(), factorLevelBean);
        }
        for (AnchorPane anchorPane : this.panes) {
            this.refreshCombox(anchorPane, linkedHashMap, linkedHashMap2);
        }
    }

    private void refreshLabel() {
        Map<String, FactorLevelBean> map = this.parentController.model.factorRegional;
        Map<String, FactorLevelBean> map2 = this.parentController.model.factorIndividual;
        HashMap<String, String> hashMap = new HashMap<String, String>();
        for (FactorLevelBean factorLevelBean : map.values()) {
            hashMap.put(factorLevelBean.getItemField().toLowerCase() + "_lb", factorLevelBean.getItemText());
        }
        for (FactorLevelBean factorLevelBean : map2.values()) {
            hashMap.put(factorLevelBean.getItemField().toLowerCase() + "_lb", factorLevelBean.getItemText());
        }
        for (FactorLevelBean factorLevelBean : this.caseLabel.getChildren()) {
            String string = factorLevelBean.getId();
            if (string == null || !hashMap.containsKey(string) || !(factorLevelBean instanceof Label)) continue;
            ((Label)factorLevelBean).setText(hashMap.get(string).toString());
        }
    }

    protected void updateVoFromUI(AnchorPane anchorPane, HashMap<String, Object> hashMap, String string) {
        for (Node node : anchorPane.getChildren()) {
            ComboBox comboBox;
            String string2 = node.getId();
            if (string2 == null) continue;
            if (!StringProcess.isEmpty(string) && string2.endsWith(string)) {
                string2 = string2.substring(0, string2.indexOf(string));
            }
            if (node instanceof DecimalField) {
                hashMap.put(string2, ((DecimalField)node).getValue().toString());
            } else if (node instanceof StringField) {
                hashMap.put(string2, ((StringField)node).getValue().toString());
            } else if (node instanceof TextField) {
                hashMap.put(string2, ((TextField)node).getText().toString());
            }
            if (node instanceof StringArea) {
                hashMap.put(string2, ((StringArea)node).getValue().toString());
            }
            if (!(node instanceof ComboBox)) continue;
            Object object = ((ComboBox)node).getSelectionModel().getSelectedItem();
            if (object instanceof FactorScoreBean) {
                comboBox = (ComboBox)node;
                if (comboBox.getSelectionModel().getSelectedItem() == null) continue;
                if (((FactorScoreBean)comboBox.getSelectionModel().getSelectedItem()).getLevel() < 0) {
                    hashMap.put(string2, "");
                    continue;
                }
                hashMap.put(string2, String.valueOf(((FactorScoreBean)comboBox.getSelectionModel().getSelectedItem()).getLevel()));
                continue;
            }
            if (!(object instanceof OptionPair) || (comboBox = (ComboBox)node).getSelectionModel().getSelectedItem() == null) continue;
            hashMap.put(string2, String.valueOf(((OptionPair)comboBox.getSelectionModel().getSelectedItem()).getValue()));
        }
    }

    protected void updateVoFromUI() {
        String string;
        for (Node node : this.caseMain.getChildren()) {
            string = node.getId();
            if (string == null) continue;
            if (node instanceof DecimalField) {
                this.model.voValuesMain.put(string, ((DecimalField)node).getValue().toString());
            } else if (node instanceof StringField) {
                this.model.voValuesMain.put(string, ((StringField)node).getValue().toString());
            } else if (node instanceof TextField) {
                this.model.voValuesMain.put(string, ((TextField)node).getText().toString());
            }
            if (!(node instanceof StringArea)) continue;
            this.model.voValuesMain.put(string, ((StringArea)node).getValue().toString());
        }
        this.updateVoFromUI(this.case0, this.model.voValues0, "_0");
        this.updateVoFromUI(this.case1, this.model.voValues1, "_1");
        this.updateVoFromUI(this.case2, this.model.voValues2, "_2");
        this.updateVoFromUI(this.case3, this.model.voValues3, "_3");
        for (Node node : this.caseLabel.getChildren()) {
            string = node.getId();
            if (string == null) continue;
            if (node instanceof StringField) {
                this.model.voValues0.put(string, ((StringField)node).getValue().toString());
                continue;
            }
            if (!(node instanceof TextField)) continue;
            this.model.voValues0.put(string, ((TextField)node).getText().toString());
        }
        this.model.updateVo();
    }

    protected void updateUIFromVo(AnchorPane anchorPane, HashMap<String, Object> hashMap, String string) {
        block0: for (Node node : anchorPane.getChildren()) {
            String string2 = node.getId();
            if (string2 == null) continue;
            if (!StringProcess.isEmpty(string) && string2.endsWith(string)) {
                string2 = string2.substring(0, string2.indexOf(string));
            }
            if (!hashMap.containsKey(string2)) continue;
            if (node instanceof AbsTextField) {
                ((AbsTextField)node).setValue(hashMap.get(string2).toString());
            } else if (node instanceof TextField) {
                ((TextField)node).setText(hashMap.get(string2).toString());
            }
            if (node instanceof StringArea) {
                ((StringArea)node).setText(hashMap.get(string2).toString());
            }
            if (!(node instanceof ComboBox)) continue;
            Object object = ((ComboBox)node).getSelectionModel().getSelectedItem();
            if (object instanceof FactorScoreBean) {
                ComboBox comboBox = (ComboBox)node;
                String string3 = StringProcess.isEmpty(hashMap.get(string2).toString()) ? "-1" : hashMap.get(string2).toString();
                for (FactorScoreBean factorScoreBean : comboBox.getItems()) {
                    if (factorScoreBean.getLevel() != StringProcess.parserInt(string3)) continue;
                    comboBox.getSelectionModel().select((Object)factorScoreBean);
                    continue block0;
                }
                continue;
            }
            if (!(object instanceof OptionPair)) continue;
            ((ComboBox)node).getSelectionModel().select((Object)new OptionPair(hashMap.get(string2).toString(), ""));
        }
    }

    protected void updateUIFromVo() {
        String string;
        this.model.setAs308Dv();
        for (Node node : this.caseMain.getChildren()) {
            string = node.getId();
            if (string == null || !this.model.voValuesMain.containsKey(string)) continue;
            if (node instanceof AbsTextField) {
                ((AbsTextField)node).setValue(this.model.voValuesMain.get(string).toString());
            } else if (node instanceof TextField) {
                ((TextField)node).setText(this.model.voValuesMain.get(string).toString());
            }
            if (!(node instanceof StringArea)) continue;
            ((StringArea)node).setText(this.model.voValuesMain.get(string).toString());
        }
        this.baseno.setValue(EstimateModel.BASELANDBEAN.queryBean.baseno);
        this.updateUIFromVo(this.case0, this.model.voValues0, "_0");
        this.updateUIFromVo(this.case1, this.model.voValues1, "_1");
        this.updateUIFromVo(this.case2, this.model.voValues2, "_2");
        this.updateUIFromVo(this.case3, this.model.voValues3, "_3");
        for (Node node : this.caseLabel.getChildren()) {
            string = node.getId();
            if (string == null || !this.model.voValues0.containsKey(string) || !(node instanceof StringField)) continue;
            ((StringField)node).setValue(this.model.voValues0.get(string).toString());
        }
    }

    public boolean savecheck() {
        boolean bl = true;
        this.reCal();
        if (StringProcess.isEmpty(this.notes.getValue())) {
            bl = this.checkPanel(this.case1);
            if (bl) {
                bl = this.checkPanel(this.case2);
            }
            if (bl) {
                bl = this.checkPanel(this.case3);
            }
        }
        return bl;
    }

    private boolean checkPanel(AnchorPane anchorPane) {
        boolean bl = true;
        for (Node node : anchorPane.getChildren()) {
            if (!(node instanceof DecimalField) || !node.getStyle().contains(this.colorRed) && !node.getStyleClass().contains((Object)"forbidden2")) continue;
            bl = false;
            break;
        }
        return bl;
    }

    public void autoSwitchLevel(Node node, boolean bl) {
        String[] stringArray = node.getId().split("_");
        if (stringArray.length == 3) {
            if (this.parentController.model.isAutoSwitchLevel.containsKey(stringArray[0].toUpperCase()) && !"lv".equals(stringArray[1]) && !"dv".equals(stringArray[1])) {
                Object object;
                Object object2;
                NVO_BASELAND_FACTOR_CODE nVO_BASELAND_FACTOR_CODE = this.parentController.model.isAutoSwitchLevel.get(stringArray[0].toUpperCase());
                AnchorPane anchorPane = null;
                anchorPane = "0".equals(stringArray[2]) ? this.case0 : ("1".equals(stringArray[2]) ? this.case1 : ("2".equals(stringArray[2]) ? this.case2 : this.case3));
                String string = this.getLevel(anchorPane, stringArray[0], stringArray[2]);
                if (bl) {
                    return;
                }
                String string2 = "";
                if (node instanceof DecimalField) {
                    string2 = ((DecimalField)node).getValue().toString();
                } else if (node instanceof StringField) {
                    string2 = ((StringField)node).getValue().toString();
                } else if (node instanceof TextField) {
                    string2 = ((TextField)node).getText().toString();
                } else if (node instanceof ComboBox) {
                    object2 = ((ComboBox)node).getSelectionModel().getSelectedItem();
                    if (object2 instanceof FactorScoreBean) {
                        object = (ComboBox)node;
                        if (object.getSelectionModel().getSelectedItem() != null) {
                            string2 = ((FactorScoreBean)object.getSelectionModel().getSelectedItem()).getLevel() < 0 ? "" : String.valueOf(((FactorScoreBean)object.getSelectionModel().getSelectedItem()).getLevel());
                        }
                    } else if (object2 instanceof OptionPair && (object = (ComboBox)node).getSelectionModel().getSelectedItem() != null) {
                        string2 = String.valueOf(((OptionPair)object.getSelectionModel().getSelectedItem()).getValue());
                    }
                }
                object2 = "";
                if ("FR".equals(nVO_BASELAND_FACTOR_CODE.getCode_0())) {
                    if (this.reginalVersion.getText().indexOf("\u6848\u4ef6") > -1) {
                        object2 = EstimateModel.BASELANDBEAN.queryBean.baseno;
                    }
                } else if (this.individualVersion.getText().indexOf("\u6848\u4ef6") > -1) {
                    object2 = EstimateModel.BASELANDBEAN.queryBean.baseno;
                }
                object = this.model.switchLevle(nVO_BASELAND_FACTOR_CODE, string2, string, (String)object2);
                this.setLevel(anchorPane, stringArray[0], stringArray[2], (String)object);
            }
        } else if (stringArray.length == 2 && "as339".equals(stringArray[0])) {
            NVO_BASELAND_FACTOR_CODE nVO_BASELAND_FACTOR_CODE = this.parentController.model.isAutoSwitchLevel.get(stringArray[0].toUpperCase());
            AnchorPane anchorPane = null;
            anchorPane = "0".equals(stringArray[1]) ? this.case0 : ("1".equals(stringArray[1]) ? this.case1 : ("2".equals(stringArray[1]) ? this.case2 : this.case3));
            String string = this.getLevel(anchorPane, stringArray[0], stringArray[1]);
            if (bl) {
                return;
            }
            String string3 = "";
            if (node instanceof DecimalField) {
                string3 = ((DecimalField)node).getValue().toString();
            }
            String string4 = "";
            if ("FR".equals(nVO_BASELAND_FACTOR_CODE.getCode_0())) {
                if (this.reginalVersion.getText().indexOf("\u6848\u4ef6") > -1) {
                    string4 = EstimateModel.BASELANDBEAN.queryBean.baseno;
                }
            } else if (this.individualVersion.getText().indexOf("\u6848\u4ef6") > -1) {
                string4 = EstimateModel.BASELANDBEAN.queryBean.baseno;
            }
            String string5 = this.model.switchLevle(nVO_BASELAND_FACTOR_CODE, string3, string, string4);
            this.setLevel(anchorPane, stringArray[0], stringArray[1], string5);
        }
    }

    private void setLevel(AnchorPane anchorPane, String string, String string2, String string3) {
        block0: for (Node node : anchorPane.getChildren()) {
            if (!(node instanceof ComboBox) || !node.getId().equals(string + "_lv_" + string2)) continue;
            Object object = ((ComboBox)node).getSelectionModel().getSelectedItem();
            if (!(object instanceof FactorScoreBean)) break;
            ComboBox comboBox = (ComboBox)node;
            for (FactorScoreBean factorScoreBean : comboBox.getItems()) {
                if (factorScoreBean.getLevel() != StringProcess.parserInt(string3)) continue;
                comboBox.getSelectionModel().select((Object)factorScoreBean);
                break block0;
            }
        }
    }

    private String getLevel(AnchorPane anchorPane, String string, String string2) {
        String string3 = "";
        ComboBox comboBox = null;
        for (Node node : anchorPane.getChildren()) {
            if (!(node instanceof ComboBox) || !node.getId().equals(string + "_lv_" + string2)) continue;
            comboBox = (ComboBox)node;
            string3 = Integer.toString(((FactorScoreBean)comboBox.getSelectionModel().getSelectedItem()).getLevel());
            break;
        }
        return string3;
    }

    public boolean isRatio100percent() {
        double d = EstimateModel.BASELANDBEAN.voAppRaMain.getFin_ahp1();
        double d2 = EstimateModel.BASELANDBEAN.voAppRaMain.getFin_ahp2();
        double d3 = EstimateModel.BASELANDBEAN.voAppRaMain.getFin_ahp3();
        BigDecimal bigDecimal = new BigDecimal(d);
        BigDecimal bigDecimal2 = new BigDecimal(d2);
        BigDecimal bigDecimal3 = new BigDecimal(d3);
        return bigDecimal.add(bigDecimal2).add(bigDecimal3).doubleValue() == 100.0;
    }

    private class SubComboxListener
    implements ChangeListener<FactorScoreBean> {
        String key;
        String area;
        AnchorPane pane;

        public SubComboxListener(String string, String string2, AnchorPane anchorPane) {
            this.key = string;
            this.area = string2;
            this.pane = anchorPane;
        }

        public void changed(ObservableValue<? extends FactorScoreBean> observableValue, FactorScoreBean factorScoreBean, FactorScoreBean factorScoreBean2) {
            if (EstimateReport2Controller.this.isFirstRefresh || factorScoreBean2 == null) {
                return;
            }
            ComboBox comboBox = null;
            for (Node node : EstimateReport2Controller.this.case0.getChildren()) {
                if (!(node instanceof ComboBox) || !node.getId().equals(this.key + "_lv_0")) continue;
                comboBox = (ComboBox)node;
            }
            for (Node node : this.pane.getChildren()) {
                if (!(node instanceof TextField) || !node.getId().equals(this.key + "_dv" + this.area)) continue;
                ((TextField)node).setText(String.valueOf(((FactorScoreBean)comboBox.getSelectionModel().getSelectedItem()).getScore() - factorScoreBean2.getScore()));
            }
            EstimateReport2Controller.this.reCal();
        }
    }

    private class MainComboxListener
    implements ChangeListener<FactorScoreBean> {
        String key;

        public MainComboxListener(String string) {
            this.key = string;
        }

        public void changed(ObservableValue<? extends FactorScoreBean> observableValue, FactorScoreBean factorScoreBean, FactorScoreBean factorScoreBean2) {
            if (EstimateReport2Controller.this.isFirstRefresh || factorScoreBean2 == null) {
                return;
            }
            TextField textField = null;
            ComboBox<FactorScoreBean> comboBox = null;
            this.updateComboxDV(EstimateReport2Controller.this.case1, "_1", textField, comboBox, factorScoreBean2);
            this.updateComboxDV(EstimateReport2Controller.this.case2, "_2", textField, comboBox, factorScoreBean2);
            this.updateComboxDV(EstimateReport2Controller.this.case3, "_3", textField, comboBox, factorScoreBean2);
        }

        private void updateComboxDV(AnchorPane anchorPane, String string, TextField textField, ComboBox<FactorScoreBean> comboBox, FactorScoreBean factorScoreBean) {
            for (Node node : anchorPane.getChildren()) {
                if (node instanceof DecimalField && node.getId().equals(this.key + "_dv" + string)) {
                    textField = (DecimalField)node;
                } else if (node instanceof StringField && node.getId().equals(this.key + "_dv" + string)) {
                    textField = (StringField)node;
                } else if (node instanceof TextField && node.getId().equals(this.key + "_dv" + string)) {
                    textField = (TextField)node;
                }
                if (!(node instanceof ComboBox) || !node.getId().equals(this.key + "_lv" + string)) continue;
                comboBox = (ComboBox)node;
            }
            try {
                textField.setText(String.valueOf(factorScoreBean.getScore() - ((FactorScoreBean)comboBox.getSelectionModel().getSelectedItem()).getScore()));
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
            EstimateReport2Controller.this.reCal();
        }
    }
}

