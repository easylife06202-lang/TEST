/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.beans.value.ChangeListener
 *  javafx.beans.value.ObservableValue
 *  javafx.collections.FXCollections
 *  javafx.collections.ObservableList
 *  javafx.event.ActionEvent
 *  javafx.event.EventHandler
 *  javafx.fxml.FXML
 *  javafx.fxml.FXMLLoader
 *  javafx.scene.Node
 *  javafx.scene.control.Button
 *  javafx.scene.control.ComboBox
 *  javafx.scene.control.Label
 *  javafx.scene.control.ListView
 *  javafx.scene.control.RadioButton
 *  javafx.scene.control.SelectionMode
 *  javafx.scene.control.TextField
 *  javafx.scene.control.ToggleGroup
 *  javafx.scene.layout.AnchorPane
 *  javafx.scene.layout.VBox
 *  javafx.stage.Stage
 */
package com.wfusion.baseland.system;

import com.wfusion.baseland.basic.Controller;
import com.wfusion.baseland.basic.IBaseLandDialog;
import com.wfusion.baseland.system.RegionalFactorModel;
import com.wfusion.fx.util.JavaFXUtil;
import com.wfusion.util.OptionPair;
import com.wfusion.util.StringProcess;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_REGIONAL_FACTOR;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_REGIONAL_FACTOR_STD;
import moiland.baseland.factor.bean.FactorItemBean;
import moiland.baseland.factor.em.EnumFactorStdType;
import moiland.baseland.factor.em.EnumFactorType;

public class SystemReginalFactor_editController
extends Controller {
    @FXML
    private Label lbItemCode;
    @FXML
    private Label lbMainCode;
    @FXML
    private Label lbDegree;
    @FXML
    private Label std_unit;
    @FXML
    private ToggleGroup stdType;
    @FXML
    private RadioButton custom;
    @FXML
    private RadioButton numeral;
    @FXML
    private RadioButton selection;
    @FXML
    private Button btExit;
    @FXML
    private Button btOK;
    @FXML
    private VBox vbLevelBox;
    @FXML
    private Button EXIT;
    @FXML
    private Label TITLE;
    public FactorItemBean factorItem = null;
    public Map<String, String> codeMap = null;
    public ArrayList<NVO_BASELAND_REGIONAL_FACTOR_STD> editData = null;
    public String defaultText = "";
    ArrayList<AnchorPane> nodeList = new ArrayList();
    NVO_BASELAND_REGIONAL_FACTOR dataVo = null;
    RegionalFactorModel model = null;

    public void init(NVO_BASELAND_REGIONAL_FACTOR nVO_BASELAND_REGIONAL_FACTOR, RegionalFactorModel regionalFactorModel, IBaseLandDialog iBaseLandDialog) {
        super.init(iBaseLandDialog);
        this.setTitle("\u5340\u57df\u56e0\u7d20\u57fa\u6e96\u8868-\u7b49\u7d1a\u6a19\u6e96\u8a2d\u5b9a");
        this.dataVo = nVO_BASELAND_REGIONAL_FACTOR;
        this.model = regionalFactorModel;
        regionalFactorModel.editStd(nVO_BASELAND_REGIONAL_FACTOR, EnumFactorType.REGIONAL, this);
        this.initUI();
        this.initRadioButton();
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
        this.btOK.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                if (SystemReginalFactor_editController.this.numeral.isSelected()) {
                    SystemReginalFactor_editController.this.saveTypeNumeral();
                } else if (SystemReginalFactor_editController.this.selection.isSelected()) {
                    SystemReginalFactor_editController.this.saveTypeSelection();
                } else if (SystemReginalFactor_editController.this.custom.isSelected()) {
                    SystemReginalFactor_editController.this.saveTypeCustom();
                } else {
                    JavaFXUtil.showErrorMessageBox("\u8acb\u9078\u64c7\u300c\u6a19\u6e96\u985e\u578b\u300d");
                    return;
                }
                SystemReginalFactor_editController.this.model.saveStdData(SystemReginalFactor_editController.this.dataVo, SystemReginalFactor_editController.this.editData);
                JavaFXUtil.showToastMessageBox(new Stage(), "\u5132\u5b58\u6210\u529f", 1500);
                SystemReginalFactor_editController.this.initUI();
            }
        });
    }

    private void initRadioButton() {
        this.numeral.selectedProperty().addListener((ChangeListener)new ChangeListener<Boolean>(){

            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean bl, Boolean bl2) {
                if (bl2.booleanValue()) {
                    SystemReginalFactor_editController.this.initUITypeNumeral();
                }
            }
        });
        this.selection.selectedProperty().addListener((ChangeListener)new ChangeListener<Boolean>(){

            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean bl, Boolean bl2) {
                if (bl2.booleanValue()) {
                    SystemReginalFactor_editController.this.initUITypeSelection();
                }
            }
        });
        this.custom.selectedProperty().addListener((ChangeListener)new ChangeListener<Boolean>(){

            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean bl, Boolean bl2) {
                if (bl2.booleanValue()) {
                    SystemReginalFactor_editController.this.initUITypeCustom();
                }
            }
        });
    }

    private void initUI() {
        this.lbMainCode.setText(this.dataVo.getNameOfMainCode());
        this.lbItemCode.setText(this.dataVo.getNameOfItem());
        this.lbDegree.setText(Integer.toString(this.dataVo.getDegree()));
        this.std_unit.setText(this.factorItem.getStdUnit());
        this.std_unit.setVisible(false);
        this.numeral.setVisible(false);
        this.selection.setVisible(false);
        if (EnumFactorStdType.NUMERAL.equals((Object)this.factorItem.getStdType())) {
            this.numeral.setVisible(true);
            this.std_unit.setVisible(true);
        } else {
            this.selection.setVisible(true);
        }
        if (EnumFactorStdType.NUMERAL.toString().equals(this.dataVo.getStd_type())) {
            this.numeral.setSelected(true);
            this.initUITypeNumeral();
        } else if (EnumFactorStdType.SELECTION.toString().equals(this.dataVo.getStd_type())) {
            this.selection.setSelected(true);
            this.initUITypeSelection();
        } else if (EnumFactorStdType.CUSTOM.toString().equals(this.dataVo.getStd_type())) {
            this.custom.setSelected(true);
            this.initUITypeCustom();
        }
    }

    private void initUITypeCustom() {
        try {
            this.nodeList.clear();
            this.vbLevelBox.getChildren().clear();
            String[] stringArray = StringProcess.split(this.dataVo.getDnames(), ",");
            for (int i = 1; i <= this.dataVo.getDegree(); ++i) {
                FXMLLoader fXMLLoader = new FXMLLoader(this.getClass().getResource("/view/FactorLevelItem3.fxml"));
                fXMLLoader.setController((Object)this);
                AnchorPane anchorPane = (AnchorPane)fXMLLoader.load();
                NVO_BASELAND_REGIONAL_FACTOR_STD nVO_BASELAND_REGIONAL_FACTOR_STD = null;
                if (this.editData != null && this.editData.size() > 0 && this.editData.size() == stringArray.length) {
                    nVO_BASELAND_REGIONAL_FACTOR_STD = this.editData.get(i - 1);
                }
                CustomPanel customPanel = new CustomPanel(anchorPane, nVO_BASELAND_REGIONAL_FACTOR_STD, stringArray[i - 1], String.valueOf(i));
                this.nodeList.add(customPanel);
                this.vbLevelBox.getChildren().add((Object)anchorPane);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
            JavaFXUtil.showErrorMessageBox(exception.toString());
        }
    }

    private void initUITypeSelection() {
        try {
            this.nodeList.clear();
            this.vbLevelBox.getChildren().clear();
            String[] stringArray = StringProcess.split(this.dataVo.getDnames(), ",");
            for (int i = 1; i <= this.dataVo.getDegree(); ++i) {
                FXMLLoader fXMLLoader = new FXMLLoader(this.getClass().getResource("/view/FactorLevelItem2.fxml"));
                fXMLLoader.setController((Object)this);
                AnchorPane anchorPane = (AnchorPane)fXMLLoader.load();
                this.nodeList.add(anchorPane);
                NVO_BASELAND_REGIONAL_FACTOR_STD nVO_BASELAND_REGIONAL_FACTOR_STD = null;
                if (this.editData != null && this.editData.size() > 0 && this.editData.size() == stringArray.length) {
                    nVO_BASELAND_REGIONAL_FACTOR_STD = this.editData.get(i - 1);
                }
                for (Node node : anchorPane.getChildren()) {
                    String[] stringArray2;
                    String string = node.getId();
                    if (string == null) continue;
                    if ("lbLevelName".equals(string)) {
                        ((Label)node).setText(stringArray[i - 1]);
                    }
                    if (!"list".equals(string)) continue;
                    ((ListView)node).setItems(FXCollections.observableArrayList(this.getSelection()));
                    ((ListView)node).getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                    if (nVO_BASELAND_REGIONAL_FACTOR_STD == null) continue;
                    for (String string2 : stringArray2 = StringProcess.split(nVO_BASELAND_REGIONAL_FACTOR_STD.getContents(), ":")) {
                        ((ListView)node).getSelectionModel().select((Object)new OptionPair(string2, ""));
                        ((ListView)node).scrollTo((Object)new OptionPair(string2, ""));
                    }
                }
                this.vbLevelBox.getChildren().add((Object)anchorPane);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
            JavaFXUtil.showErrorMessageBox(exception.toString());
        }
    }

    private void initUITypeNumeral() {
        try {
            this.nodeList.clear();
            this.vbLevelBox.getChildren().clear();
            String[] stringArray = StringProcess.split(this.dataVo.getDnames(), ",");
            for (int i = 1; i <= this.dataVo.getDegree(); ++i) {
                FXMLLoader fXMLLoader = new FXMLLoader(this.getClass().getResource("/view/FactorLevelItem1.fxml"));
                fXMLLoader.setController((Object)this);
                AnchorPane anchorPane = (AnchorPane)fXMLLoader.load();
                NVO_BASELAND_REGIONAL_FACTOR_STD nVO_BASELAND_REGIONAL_FACTOR_STD = null;
                if (this.editData != null && this.editData.size() > 0 && this.editData.size() == stringArray.length) {
                    nVO_BASELAND_REGIONAL_FACTOR_STD = this.editData.get(i - 1);
                }
                LevelPanel levelPanel = new LevelPanel(anchorPane, nVO_BASELAND_REGIONAL_FACTOR_STD, stringArray[i - 1], String.valueOf(i));
                this.nodeList.add(levelPanel);
                this.vbLevelBox.getChildren().add((Object)anchorPane);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
            JavaFXUtil.showErrorMessageBox(exception.toString());
        }
    }

    private ArrayList<OptionPair> getCompatorList() {
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        arrayList.add(new OptionPair("", "\u8acb\u9078\u64c7..."));
        arrayList.add(new OptionPair("<", "\uff1c"));
        arrayList.add(new OptionPair("<=", "\u2266"));
        arrayList.add(new OptionPair("=", "\uff1d"));
        arrayList.add(new OptionPair(">", "\uff1e"));
        arrayList.add(new OptionPair(">=", "\u2267"));
        return arrayList;
    }

    private ArrayList<OptionPair> getSelection() {
        ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
        if (this.codeMap != null && this.codeMap.size() > 0) {
            for (Map.Entry<String, String> entry : this.codeMap.entrySet()) {
                arrayList.add(new OptionPair(entry.getKey(), entry.getValue()));
            }
        }
        return arrayList;
    }

    private void saveTypeNumeral() {
        this.editData = new ArrayList();
        for (int i = 1; i <= this.dataVo.getDegree(); ++i) {
            LevelPanel levelPanel = (LevelPanel)this.nodeList.get(i - 1);
            levelPanel.updateVo();
            this.editData.add(levelPanel.stdVo);
        }
        this.dataVo.setStd_unit(this.std_unit.getText());
        this.dataVo.setStd_type(EnumFactorStdType.NUMERAL.toString());
    }

    private void saveTypeSelection() {
        this.editData = new ArrayList();
        for (int i = 1; i <= this.dataVo.getDegree(); ++i) {
            AnchorPane anchorPane = this.nodeList.get(i - 1);
            NVO_BASELAND_REGIONAL_FACTOR_STD nVO_BASELAND_REGIONAL_FACTOR_STD = this.getDefVo(i);
            for (Node node : anchorPane.getChildren()) {
                String string = node.getId();
                StringBuilder stringBuilder = new StringBuilder();
                if (!"list".equals(string)) continue;
                ObservableList observableList = ((ListView)node).getSelectionModel().getSelectedItems();
                for (OptionPair optionPair : observableList) {
                    stringBuilder.append(optionPair.getValue()).append(":");
                }
                if (stringBuilder.length() > 0) {
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                }
                nVO_BASELAND_REGIONAL_FACTOR_STD.setContents(stringBuilder.toString());
            }
            this.editData.add(nVO_BASELAND_REGIONAL_FACTOR_STD);
            this.dataVo.setStd_type(EnumFactorStdType.SELECTION.toString());
        }
    }

    private NVO_BASELAND_REGIONAL_FACTOR_STD getDefVo(int n) {
        NVO_BASELAND_REGIONAL_FACTOR_STD nVO_BASELAND_REGIONAL_FACTOR_STD = new NVO_BASELAND_REGIONAL_FACTOR_STD();
        nVO_BASELAND_REGIONAL_FACTOR_STD.setCity(this.dataVo.getCity());
        nVO_BASELAND_REGIONAL_FACTOR_STD.setDist(this.dataVo.getDist());
        nVO_BASELAND_REGIONAL_FACTOR_STD.setYear(this.dataVo.getYear());
        nVO_BASELAND_REGIONAL_FACTOR_STD.setVersion(this.dataVo.getVersion());
        nVO_BASELAND_REGIONAL_FACTOR_STD.setBaseno(this.dataVo.getBaseno());
        nVO_BASELAND_REGIONAL_FACTOR_STD.setItem(this.dataVo.getItem());
        nVO_BASELAND_REGIONAL_FACTOR_STD.setLevel(n);
        return nVO_BASELAND_REGIONAL_FACTOR_STD;
    }

    private void saveTypeCustom() {
        this.editData = new ArrayList();
        for (int i = 1; i <= this.dataVo.getDegree(); ++i) {
            CustomPanel customPanel = (CustomPanel)this.nodeList.get(i - 1);
            customPanel.updateVo();
            this.editData.add(customPanel.stdVo);
        }
        this.dataVo.setStd_unit(this.std_unit.getText());
        this.dataVo.setStd_type(EnumFactorStdType.CUSTOM.toString());
    }

    @Override
    public void refresh() {
    }

    class LevelPanel
    extends AnchorPane {
        Label lbLevelName = null;
        ComboBox<OptionPair> a_symbol = null;
        ComboBox<OptionPair> b_symbol = null;
        ComboBox<OptionPair> c_symbol = null;
        ComboBox<OptionPair> d_symbol = null;
        TextField a_digital = null;
        TextField b_digital = null;
        TextField c_digital = null;
        TextField d_digital = null;
        Button btAdd1 = null;
        Button btAdd2 = null;
        Button btAdd3 = null;
        Button btDel1 = null;
        Button btDel2 = null;
        Button btDel3 = null;
        RadioButton ab_logic_1 = null;
        RadioButton ab_logic_2 = null;
        RadioButton bc_logic_1 = null;
        RadioButton bc_logic_2 = null;
        RadioButton cd_logic_1 = null;
        RadioButton cd_logic_2 = null;
        NVO_BASELAND_REGIONAL_FACTOR_STD stdVo = null;
        AnchorPane panel = null;
        String level = null;

        public void updateVo() {
            this.stdVo = new NVO_BASELAND_REGIONAL_FACTOR_STD();
            this.stdVo.setCity(SystemReginalFactor_editController.this.dataVo.getCity());
            this.stdVo.setDist(SystemReginalFactor_editController.this.dataVo.getDist());
            this.stdVo.setYear(SystemReginalFactor_editController.this.dataVo.getYear());
            this.stdVo.setVersion(SystemReginalFactor_editController.this.dataVo.getVersion());
            this.stdVo.setBaseno(SystemReginalFactor_editController.this.dataVo.getBaseno());
            this.stdVo.setItem(SystemReginalFactor_editController.this.dataVo.getItem());
            this.stdVo.setLevel(StringProcess.parserInt(this.level));
            if (this.a_symbol.getSelectionModel() != null && this.a_symbol.getSelectionModel().getSelectedItem() != null) {
                this.stdVo.setA_symbol(((OptionPair)this.a_symbol.getSelectionModel().getSelectedItem()).getValue());
            }
            this.stdVo.setA_digital(StringProcess.parserDouble(this.a_digital.getText().toString()));
            if (this.ab_logic_1.isVisible()) {
                this.stdVo.setAb_logic(this.ab_logic_1.isSelected() ? "\u4e14" : "\u6216");
                this.stdVo.setB_symbol(((OptionPair)this.b_symbol.getSelectionModel().getSelectedItem()).getValue());
                this.stdVo.setB_digital(StringProcess.parserDouble(this.b_digital.getText().toString()));
            }
            if (this.bc_logic_1.isVisible()) {
                this.stdVo.setBc_logic(this.bc_logic_1.isSelected() ? "\u4e14" : "\u6216");
                this.stdVo.setC_symbol(((OptionPair)this.c_symbol.getSelectionModel().getSelectedItem()).getValue());
                this.stdVo.setC_digital(StringProcess.parserDouble(this.c_digital.getText().toString()));
            }
            if (this.cd_logic_1.isVisible()) {
                this.stdVo.setCd_logic(this.cd_logic_1.isSelected() ? "\u4e14" : "\u6216");
                this.stdVo.setD_symbol(((OptionPair)this.d_symbol.getSelectionModel().getSelectedItem()).getValue());
                this.stdVo.setD_digital(StringProcess.parserDouble(this.d_digital.getText().toString()));
            }
        }

        public LevelPanel(AnchorPane anchorPane, NVO_BASELAND_REGIONAL_FACTOR_STD nVO_BASELAND_REGIONAL_FACTOR_STD, String string, String string2) {
            this.panel = anchorPane;
            this.level = string2;
            this.stdVo = nVO_BASELAND_REGIONAL_FACTOR_STD;
            for (Node node : anchorPane.getChildren()) {
                String string3 = node.getId();
                if ("lbLevelName".equals(string3)) {
                    this.lbLevelName = (Label)node;
                }
                if ("a_symbol".equals(string3)) {
                    this.a_symbol = (ComboBox)node;
                }
                if ("b_symbol".equals(string3)) {
                    this.b_symbol = (ComboBox)node;
                }
                if ("c_symbol".equals(string3)) {
                    this.c_symbol = (ComboBox)node;
                }
                if ("d_symbol".equals(string3)) {
                    this.d_symbol = (ComboBox)node;
                }
                if ("a_digital".equals(string3)) {
                    this.a_digital = (TextField)node;
                }
                if ("b_digital".equals(string3)) {
                    this.b_digital = (TextField)node;
                }
                if ("c_digital".equals(string3)) {
                    this.c_digital = (TextField)node;
                }
                if ("d_digital".equals(string3)) {
                    this.d_digital = (TextField)node;
                }
                if ("btAdd1".equals(string3)) {
                    this.btAdd1 = (Button)node;
                }
                if ("btAdd2".equals(string3)) {
                    this.btAdd2 = (Button)node;
                }
                if ("btAdd3".equals(string3)) {
                    this.btAdd3 = (Button)node;
                }
                if ("btDel1".equals(string3)) {
                    this.btDel1 = (Button)node;
                }
                if ("btDel2".equals(string3)) {
                    this.btDel2 = (Button)node;
                }
                if ("btDel3".equals(string3)) {
                    this.btDel3 = (Button)node;
                }
                if ("ab_logic_1".equals(string3)) {
                    this.ab_logic_1 = (RadioButton)node;
                }
                if ("bc_logic_1".equals(string3)) {
                    this.bc_logic_1 = (RadioButton)node;
                }
                if ("cd_logic_1".equals(string3)) {
                    this.cd_logic_1 = (RadioButton)node;
                }
                if ("ab_logic_2".equals(string3)) {
                    this.ab_logic_2 = (RadioButton)node;
                }
                if ("bc_logic_2".equals(string3)) {
                    this.bc_logic_2 = (RadioButton)node;
                }
                if (!"cd_logic_2".equals(string3)) continue;
                this.cd_logic_2 = (RadioButton)node;
            }
            this.lbLevelName.setText(string);
            this.a_symbol.setItems(FXCollections.observableArrayList((Collection)SystemReginalFactor_editController.this.getCompatorList()));
            this.b_symbol.setItems(FXCollections.observableArrayList((Collection)SystemReginalFactor_editController.this.getCompatorList()));
            this.c_symbol.setItems(FXCollections.observableArrayList((Collection)SystemReginalFactor_editController.this.getCompatorList()));
            this.d_symbol.setItems(FXCollections.observableArrayList((Collection)SystemReginalFactor_editController.this.getCompatorList()));
            this.btDel1.setOnAction(actionEvent -> {
                this.ab_logic_1.setVisible(false);
                this.ab_logic_2.setVisible(false);
                this.b_symbol.setVisible(false);
                this.b_digital.setVisible(false);
                this.btDel1.setVisible(false);
                this.btAdd2.setVisible(false);
                anchorPane.setPrefHeight(45.0);
            });
            this.btDel2.setOnAction(actionEvent -> {
                this.bc_logic_1.setVisible(false);
                this.bc_logic_2.setVisible(false);
                this.c_symbol.setVisible(false);
                this.c_digital.setVisible(false);
                this.btDel1.setDisable(false);
                this.btDel2.setVisible(false);
                this.btAdd3.setVisible(false);
                anchorPane.setPrefHeight(75.0);
            });
            this.btDel3.setOnAction(actionEvent -> {
                this.cd_logic_1.setVisible(false);
                this.cd_logic_2.setVisible(false);
                this.d_symbol.setVisible(false);
                this.d_digital.setVisible(false);
                this.btDel2.setDisable(false);
                this.btDel3.setVisible(false);
                anchorPane.setPrefHeight(110.0);
            });
            this.btAdd1.setOnAction(actionEvent -> this.add1());
            this.btAdd2.setOnAction(actionEvent -> this.add2());
            this.btAdd3.setOnAction(actionEvent -> this.add3());
            if (this.stdVo != null) {
                this.a_symbol.getSelectionModel().select((Object)new OptionPair(this.stdVo.getA_symbol(), ""));
                this.a_digital.setText(String.valueOf(this.stdVo.getA_digital()));
                if (!StringProcess.isEmpty(this.stdVo.getAb_logic())) {
                    this.add1();
                    if ("\u4e14".equals(this.stdVo.getAb_logic())) {
                        this.ab_logic_1.setSelected(true);
                    }
                    if ("\u6216".equals(this.stdVo.getAb_logic())) {
                        this.ab_logic_2.setSelected(true);
                    }
                    this.b_symbol.getSelectionModel().select((Object)new OptionPair(this.stdVo.getB_symbol(), ""));
                    this.b_digital.setText(String.valueOf(this.stdVo.getB_digital()));
                }
                if (!StringProcess.isEmpty(this.stdVo.getBc_logic())) {
                    this.add2();
                    if ("\u4e14".equals(this.stdVo.getBc_logic())) {
                        this.bc_logic_1.setSelected(true);
                    }
                    if ("\u6216".equals(this.stdVo.getBc_logic())) {
                        this.bc_logic_2.setSelected(true);
                    }
                    this.c_symbol.getSelectionModel().select((Object)new OptionPair(this.stdVo.getC_symbol(), ""));
                    this.c_digital.setText(String.valueOf(this.stdVo.getC_digital()));
                }
                if (!StringProcess.isEmpty(this.stdVo.getCd_logic())) {
                    this.add3();
                    if ("\u4e14".equals(this.stdVo.getCd_logic())) {
                        this.cd_logic_1.setSelected(true);
                    }
                    if ("\u6216".equals(this.stdVo.getCd_logic())) {
                        this.cd_logic_2.setSelected(true);
                    }
                    this.d_symbol.getSelectionModel().select((Object)new OptionPair(this.stdVo.getD_symbol(), ""));
                    this.d_digital.setText(String.valueOf(this.stdVo.getD_digital()));
                }
            }
        }

        private void add3() {
            this.cd_logic_1.setVisible(true);
            this.cd_logic_2.setVisible(true);
            this.d_symbol.setVisible(true);
            this.d_digital.setVisible(true);
            this.btDel2.setDisable(true);
            this.btDel3.setVisible(true);
            this.panel.setPrefHeight(150.0);
        }

        private void add2() {
            this.bc_logic_1.setVisible(true);
            this.bc_logic_2.setVisible(true);
            this.c_symbol.setVisible(true);
            this.c_digital.setVisible(true);
            this.btDel1.setDisable(true);
            this.btDel2.setVisible(true);
            this.btAdd3.setVisible(true);
            this.panel.setPrefHeight(110.0);
        }

        private void add1() {
            this.ab_logic_1.setVisible(true);
            this.ab_logic_2.setVisible(true);
            this.b_symbol.setVisible(true);
            this.b_digital.setVisible(true);
            this.btDel1.setVisible(true);
            this.btAdd2.setVisible(true);
            this.panel.setPrefHeight(75.0);
        }
    }

    class CustomPanel
    extends AnchorPane {
        Label lbLevelName = null;
        TextField contents = null;
        Button btDef = null;
        AnchorPane panel = null;
        String level = null;
        NVO_BASELAND_REGIONAL_FACTOR_STD stdVo = null;

        public CustomPanel(AnchorPane anchorPane, NVO_BASELAND_REGIONAL_FACTOR_STD nVO_BASELAND_REGIONAL_FACTOR_STD, String string, String string2) {
            this.panel = anchorPane;
            this.level = string2;
            for (Node node : anchorPane.getChildren()) {
                String string3 = node.getId();
                if ("lbLevelName".equals(string3)) {
                    this.lbLevelName = (Label)node;
                }
                if ("contents".equals(string3)) {
                    this.contents = (TextField)node;
                }
                if (!"btDef".equals(string3)) continue;
                this.btDef = (Button)node;
            }
            this.btDef.setOnAction(actionEvent -> this.contents.setText(SystemReginalFactor_editController.this.defaultText));
            this.lbLevelName.setText(string);
            if (nVO_BASELAND_REGIONAL_FACTOR_STD != null && !StringProcess.isEmpty(nVO_BASELAND_REGIONAL_FACTOR_STD.getContents()) && EnumFactorStdType.CUSTOM.toString().equals(SystemReginalFactor_editController.this.dataVo.getStd_type())) {
                this.contents.setText(nVO_BASELAND_REGIONAL_FACTOR_STD.getContents());
            }
        }

        public void updateVo() {
            this.stdVo = new NVO_BASELAND_REGIONAL_FACTOR_STD();
            this.stdVo.setCity(SystemReginalFactor_editController.this.dataVo.getCity());
            this.stdVo.setDist(SystemReginalFactor_editController.this.dataVo.getDist());
            this.stdVo.setYear(SystemReginalFactor_editController.this.dataVo.getYear());
            this.stdVo.setVersion(SystemReginalFactor_editController.this.dataVo.getVersion());
            this.stdVo.setBaseno(SystemReginalFactor_editController.this.dataVo.getBaseno());
            this.stdVo.setItem(SystemReginalFactor_editController.this.dataVo.getItem());
            this.stdVo.setLevel(StringProcess.parserInt(this.level));
            this.stdVo.setContents(this.contents.getText());
        }
    }
}

