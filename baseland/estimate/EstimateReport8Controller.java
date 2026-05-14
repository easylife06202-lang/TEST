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
 *  javafx.scene.control.CheckBox
 *  javafx.scene.control.ComboBox
 *  javafx.scene.control.Label
 *  javafx.scene.control.TextField
 *  javafx.scene.layout.AnchorPane
 *  javafx.stage.Modality
 *  javafx.stage.Stage
 *  javafx.stage.StageStyle
 */
package com.wfusion.baseland.estimate;

import com.wfusion.baseland.SQLiteDataProviderModel;
import com.wfusion.baseland.basic.BaseLandDialog;
import com.wfusion.baseland.estimate.DevlopParamController;
import com.wfusion.baseland.estimate.EstimateController;
import com.wfusion.baseland.estimate.EstimateModel;
import com.wfusion.baseland.estimate.EstimateReport8Model;
import com.wfusion.baseland.system.GlossaryModel;
import com.wfusion.fx.node.AbsTextField;
import com.wfusion.fx.node.DecimalField;
import com.wfusion.fx.node.StringArea;
import com.wfusion.fx.node.StringField;
import com.wfusion.fx.util.ExceptionDialog;
import com.wfusion.fx.util.JavaFXUtil;
import com.wfusion.fx.util.NumberFormater;
import com.wfusion.util.OptionPair;
import com.wfusion.util.StringProcess;
import java.io.IOException;
import java.math.BigDecimal;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import moiland.baseland.bo.SaveCheckBo;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_MAIN;

public class EstimateReport8Controller {
    @FXML
    private AnchorPane report8;
    @FXML
    private AnchorPane panel_ext;
    @FXML
    private ComboBox<OptionPair> same_case;
    @FXML
    private ComboBox<OptionPair> price_type;
    @FXML
    private ComboBox<OptionPair> inst_code;
    @FXML
    private ComboBox<OptionPair> is_merge;
    @FXML
    private ComboBox<OptionPair> floor_type;
    @FXML
    private ComboBox<OptionPair> cbLiteral;
    @FXML
    private ComboBox<OptionPair> same_case1;
    @FXML
    private DecimalField benefit_rate;
    @FXML
    private DecimalField indir_cost;
    @FXML
    private DecimalField build_cost;
    @FXML
    private DecimalField sum_rate;
    @FXML
    private DecimalField rf_area;
    @FXML
    private DecimalField sale_are_ratio;
    @FXML
    private DecimalField floor1_area;
    @FXML
    private DecimalField floor_up;
    @FXML
    private DecimalField other_area;
    @FXML
    private DecimalField floor2_area;
    @FXML
    private DecimalField direct_cost;
    @FXML
    private DecimalField baseland_aa10;
    @FXML
    private StringField baseland_landuse;
    @FXML
    private DecimalField baseland_cov_ratio;
    @FXML
    private DecimalField baseland_are_ratio;
    @FXML
    private StringField baseland_shape;
    @FXML
    private StringField baseland_slop;
    @FXML
    private DecimalField baseland_width;
    @FXML
    private DecimalField baseland_deep;
    @FXML
    private StringField baseland_street_rel;
    @FXML
    private DecimalField baseland_roadwidth;
    @FXML
    private StringArea notes;
    @FXML
    private Label formula;
    @FXML
    private CheckBox sale_are_type;
    @FXML
    private Button btParamSetup;
    @FXML
    private Button btAddLiteral;
    @FXML
    private Label totalLab;
    @FXML
    private Label unitLab;
    @FXML
    private StringField land_position;
    EstimateController parentController = null;
    EstimateReport8Model model = new EstimateReport8Model();
    boolean isFirstRefresh = true;
    SaveCheckBo checkbo = new SaveCheckBo(EstimateModel.BASELANDBEAN);
    ChangeListener<Boolean> lostFocusListener = new ChangeListener<Boolean>(){

        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean bl, Boolean bl2) {
            if (!bl2.booleanValue()) {
                EstimateReport8Controller.this.reCal();
            }
        }
    };

    public void init(BaseLandDialog baseLandDialog, final EstimateController estimateController) {
        this.parentController = estimateController;
        this.initTextField();
        this.initCombox();
        this.btParamSetup.setOnAction(actionEvent -> {
            Stage stage = new Stage();
            DevlopParamController devlopParamController = null;
            try {
                stage.initStyle(StageStyle.UNDECORATED);
                stage.initModality(Modality.APPLICATION_MODAL);
                FXMLLoader fXMLLoader = new FXMLLoader(this.getClass().getResource("/view/DevlopParam.fxml"));
                Scene scene = new Scene((Parent)fXMLLoader.load());
                stage.setScene(scene);
                devlopParamController = (DevlopParamController)fXMLLoader.getController();
                devlopParamController.init(baseLandDialog, estimateController, this, this.model);
                stage.showAndWait();
            }
            catch (IOException iOException) {
                ExceptionDialog.show(iOException);
            }
        });
        this.btAddLiteral.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                String string = EstimateReport8Controller.this.notes.getValue();
                if (!StringProcess.isEmpty(string)) {
                    GlossaryModel glossaryModel = new GlossaryModel();
                    glossaryModel.addLiteral(string, "DEVELOP", "DEVLOP_NOTE");
                    glossaryModel.query("DEVELOP", "DEVLOP_NOTE", 0, "");
                    EstimateReport8Controller.this.cbLiteral.setItems(FXCollections.observableArrayList(glossaryModel.getDataOptionpairs()));
                    EstimateReport8Controller.this.cbLiteral.getSelectionModel().selectLast();
                    JavaFXUtil.showToastMessageBox(estimateController.dialog.getStage(), "\u5df2\u65b0\u589e\u8fad\u5eab", 1500);
                }
            }
        });
        this.sale_are_type.selectedProperty().addListener((ChangeListener)new ChangeListener<Boolean>(){

            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean bl, Boolean bl2) {
                EstimateReport8Controller.this.sale_are_typeChange(bl2);
            }
        });
    }

    private void initTextField() {
        String[] stringArray;
        String string;
        String string2;
        for (Node node : this.report8.getChildren()) {
            if (node.getId() == null) continue;
            if (node instanceof TextField) {
                node.focusedProperty().addListener(this.lostFocusListener);
            }
            if (node instanceof DecimalField) {
                string2 = EstimateModel.BASELANDBEAN.voDevelop.getTableName().toUpperCase();
                string = node.getId().replaceAll("_copy", "").toUpperCase();
                stringArray = SaveCheckBo.columnSet.get(string2 + "_" + string);
                if (stringArray == null || stringArray.length != 4) continue;
                ((DecimalField)node).setName(stringArray[0]);
                ((DecimalField)node).setPrecision(StringProcess.parserInt(stringArray[2]));
                ((DecimalField)node).setScale(StringProcess.parserInt(stringArray[3]));
                continue;
            }
            if (node instanceof StringField) {
                string2 = EstimateModel.BASELANDBEAN.voDevelop.getTableName().toUpperCase();
                string = node.getId().replaceAll("_copy", "").toUpperCase();
                stringArray = SaveCheckBo.columnSet.get(string2 + "_" + string);
                if (stringArray == null || stringArray.length != 4) continue;
                ((StringField)node).setName(stringArray[0]);
                ((StringField)node).setMaxLength(StringProcess.parserInt(stringArray[2]));
                ((StringField)node).setMinLength(StringProcess.parserInt(stringArray[3]));
                continue;
            }
            if (!(node instanceof StringArea)) continue;
            string2 = EstimateModel.BASELANDBEAN.voDevelop.getTableName().toUpperCase();
            string = node.getId().replaceAll("_copy", "").toUpperCase();
            stringArray = SaveCheckBo.columnSet.get(string2 + "_" + string);
            if (stringArray != null && stringArray.length == 4) {
                ((StringArea)node).setName(stringArray[0]);
                ((StringArea)node).setMaxLength(StringProcess.parserInt(stringArray[2]));
                ((StringArea)node).setMinLength(StringProcess.parserInt(stringArray[3]));
            }
            node.focusedProperty().addListener(this.lostFocusListener);
        }
        for (Node node : this.panel_ext.getChildren()) {
            if (node.getId() == null) continue;
            if (node instanceof TextField) {
                node.focusedProperty().addListener(this.lostFocusListener);
            }
            if (node instanceof DecimalField) {
                string2 = EstimateModel.BASELANDBEAN.voDevelopExt.getTableName().toUpperCase();
                string = node.getId().replaceAll("_copy", "").toUpperCase();
                stringArray = SaveCheckBo.columnSet.get(string2 + "_" + string);
                if (stringArray == null || stringArray.length != 4) continue;
                ((DecimalField)node).setName(stringArray[0]);
                ((DecimalField)node).setPrecision(StringProcess.parserInt(stringArray[2]));
                ((DecimalField)node).setScale(StringProcess.parserInt(stringArray[3]));
                continue;
            }
            if (!(node instanceof StringField)) continue;
            string2 = EstimateModel.BASELANDBEAN.voDevelopExt.getTableName().toUpperCase();
            string = node.getId().replaceAll("_copy", "").toUpperCase();
            stringArray = SaveCheckBo.columnSet.get(string2 + "_" + string);
            if (stringArray == null || stringArray.length != 4) continue;
            ((StringField)node).setName(stringArray[0]);
            ((StringField)node).setMaxLength(StringProcess.parserInt(stringArray[2]));
            ((StringField)node).setMinLength(StringProcess.parserInt(stringArray[3]));
        }
        this.land_position.setName("\u571f\u958b\u6cd5_\u571f\u5730\u5750\u843d");
        this.land_position.setMaxLength(200);
        this.land_position.setMinLength(0);
    }

    private void initCombox() {
        this.inst_code.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getInstruCodeList()));
        this.inst_code.getSelectionModel().selectFirst();
        this.inst_code.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                if (EstimateReport8Controller.this.isFirstRefresh || optionPair2 == null) {
                    return;
                }
                EstimateReport8Controller.this.model.voValue.put("inst_code", optionPair2.getValue());
                String string = EstimateReport8Controller.this.floor_up.getText().toString();
                if (!StringProcess.isEmpty(string)) {
                    int n = EstimateReport8Controller.this.getInstruStdPrice(optionPair2.getValue(), StringProcess.parserInt(string));
                    EstimateReport8Controller.this.build_cost.setText(String.valueOf(n));
                }
                EstimateReport8Controller.this.reCal();
            }
        });
        this.floor_type.setItems(FXCollections.observableArrayList(this.model.getFloorTypeList()));
        this.floor_type.getSelectionModel().selectFirst();
        this.floor_type.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                if (EstimateReport8Controller.this.isFirstRefresh || optionPair2 == null) {
                    return;
                }
                EstimateReport8Controller.this.model.voValue.put("floor_type", optionPair2.getValue());
                EstimateReport8Controller.this.reCal();
            }
        });
        this.is_merge.setItems(FXCollections.observableArrayList(this.model.getIsMergeList()));
        this.is_merge.getSelectionModel().selectFirst();
        this.is_merge.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                if (EstimateReport8Controller.this.isFirstRefresh || optionPair2 == null) {
                    return;
                }
                EstimateReport8Controller.this.model.voValue.put("is_merge", optionPair2.getValue());
                EstimateReport8Controller.this.reCal();
                if ("0".equals(optionPair2.getValue())) {
                    EstimateReport8Controller.this.totalLab.setText("\u571f\u5730\u7e3d\u50f9");
                    EstimateReport8Controller.this.unitLab.setText("\u571f\u5730\u55ae\u50f9");
                } else {
                    EstimateReport8Controller.this.totalLab.setText("\u5408\u4f75\u6216\u865b\u64ec\u958b\u767c\u55ae\u5143\u571f\u5730\u7e3d\u50f9");
                    EstimateReport8Controller.this.unitLab.setText("\u4ee5\u5408\u4f75\u6216\u865b\u64ec\u958b\u767c\u55ae\u5143\u63a8\u7b97\u57fa\u6e96\u5730\u55ae\u50f9");
                }
            }
        });
        this.same_case.setItems(FXCollections.observableArrayList(this.model.getIsMergeList()));
        this.same_case.getSelectionModel().selectFirst();
        this.same_case.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                if (EstimateReport8Controller.this.isFirstRefresh || optionPair2 == null) {
                    return;
                }
                EstimateReport8Controller.this.model.voValue.put("same_case", optionPair2.getValue());
                EstimateReport8Controller.this.reCal();
            }
        });
        this.price_type.setItems(FXCollections.observableArrayList(this.model.getPriceTypeList()));
        this.price_type.getSelectionModel().selectFirst();
        this.price_type.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                if (EstimateReport8Controller.this.isFirstRefresh || optionPair2 == null) {
                    return;
                }
                EstimateReport8Controller.this.model.voValue.put("price_type", optionPair2.getValue());
                EstimateReport8Controller.this.reCal();
            }
        });
        GlossaryModel glossaryModel = new GlossaryModel();
        glossaryModel.query("DEVELOP", "DEVLOP_NOTE", 0, "");
        this.cbLiteral.setItems(FXCollections.observableArrayList(glossaryModel.getDataOptionpairs()));
        this.cbLiteral.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                if (optionPair2 != null && !StringProcess.isEmpty(optionPair2.getValue())) {
                    EstimateReport8Controller.this.notes.setText(optionPair2.getAlias());
                }
            }
        });
    }

    public void reCal() {
        this.updateVoFromUI();
        this.model.reCal();
        this.updateUIFromVo();
    }

    public void refresh() {
        this.isFirstRefresh = true;
        this.updateUIFromVo();
        this.refreshCombox();
        this.isFirstRefresh = false;
    }

    public void pageRefresh() {
        if ("0".equals(((OptionPair)this.is_merge.getValue()).getValue())) {
            this.totalLab.setText("\u571f\u5730\u7e3d\u50f9");
            this.unitLab.setText("\u571f\u5730\u55ae\u50f9");
        } else {
            this.totalLab.setText("\u5408\u4f75\u6216\u865b\u64ec\u958b\u767c\u55ae\u5143\u571f\u5730\u7e3d\u50f9");
            this.unitLab.setText("\u4ee5\u5408\u4f75\u6216\u865b\u64ec\u958b\u767c\u55ae\u5143\u63a8\u7b97\u57fa\u6e96\u5730\u55ae\u50f9");
        }
    }

    private void refreshCombox() {
        this.inst_code.getSelectionModel().select(StringProcess.parserInt(EstimateModel.BASELANDBEAN.voDevelop.getInst_code(), 0));
        this.floor_type.getSelectionModel().select(StringProcess.parserInt(EstimateModel.BASELANDBEAN.voDevelop.getFloor_type(), 0));
        this.is_merge.getSelectionModel().select(StringProcess.parserInt(EstimateModel.BASELANDBEAN.voDevelop.getIs_merge(), 0));
        this.same_case.getSelectionModel().select(StringProcess.parserInt(EstimateModel.BASELANDBEAN.voDevelop.getSame_case(), 0));
        if (StringProcess.parserInt(EstimateModel.BASELANDBEAN.voDevelop.getPrice_type()) > 0) {
            this.price_type.getSelectionModel().select(StringProcess.parserInt(EstimateModel.BASELANDBEAN.voDevelop.getPrice_type()) - 1);
        }
    }

    protected void updateVoFromUI() {
        for (Node node : this.report8.getChildren()) {
            if (node == null || !this.model.voValue.containsKey(node.getId())) continue;
            if (node instanceof DecimalField) {
                this.model.voValue.put(node.getId(), ((DecimalField)node).getValue().toString());
            } else if (node instanceof StringField) {
                this.model.voValue.put(node.getId(), ((StringField)node).getValue().toString());
            } else if (node instanceof StringArea) {
                this.model.voValue.put(node.getId(), ((StringArea)node).getValue().toString());
            }
            if (!(node instanceof CheckBox)) continue;
            this.model.voValue.put(node.getId(), ((CheckBox)node).isSelected() ? "1" : "0");
        }
        for (Node node : this.panel_ext.getChildren()) {
            if (node == null || !this.model.voValue.containsKey(node.getId())) continue;
            if (node instanceof DecimalField) {
                this.model.voValue.put(node.getId(), ((DecimalField)node).getValue().toString());
                continue;
            }
            if (!(node instanceof StringField)) continue;
            this.model.voValue.put(node.getId(), ((StringField)node).getValue().toString());
        }
        this.model.voValue.put("floor_type", ((OptionPair)this.floor_type.getValue()).getValue());
        this.model.voValue.put("is_merge", ((OptionPair)this.is_merge.getValue()).getValue());
        this.model.voValue.put("same_case", ((OptionPair)this.same_case.getValue()).getValue());
        this.model.voValue.put("price_type", ((OptionPair)this.price_type.getValue()).getValue());
        this.model.updateVo();
    }

    protected void updateUIFromVo() {
        String string;
        this.model.updateHashMapValues();
        boolean bl = StringProcess.parserBoolean((String)this.model.voValue.get("is_merge"));
        if (bl) {
            double d = StringProcess.parserDouble((String)this.model.voValue.get("area_pseudo")) * 0.3025;
            this.model.voValue.put("land_position", this.model.voValue.get("land_position_pseudo"));
            this.model.voValue.put("aa10", this.model.voValue.get("area_pseudo"));
            this.model.voValue.put("landuse", this.model.voValue.get("landuse_pseudo"));
            this.model.voValue.put("cov_ratio", this.model.voValue.get("cov_ratio_pseudo"));
            this.model.voValue.put("are_ratio", this.model.voValue.get("are_ratio_pseudo"));
            this.model.voValue.put("aa10_ping", NumberFormater.df2.format(d));
        } else if (EstimateModel.BASELANDBEAN.voMain != null) {
            NVO_BASELAND_MAIN nVO_BASELAND_MAIN = EstimateModel.BASELANDBEAN.voMain;
            this.model.voValue.put("land_position", nVO_BASELAND_MAIN.getLand_position());
            this.model.voValue.put("landuse", nVO_BASELAND_MAIN.getLanduse());
            this.model.voValue.put("aa10", nVO_BASELAND_MAIN.getAa10());
            double d = new BigDecimal(nVO_BASELAND_MAIN.getAa10()).multiply(new BigDecimal("0.3025")).setScale(2, 5).doubleValue();
            this.model.voValue.put("aa10_ping", d);
            this.model.voValue.put("cov_ratio", nVO_BASELAND_MAIN.getCov_ratio());
            this.model.voValue.put("are_ratio", nVO_BASELAND_MAIN.getAre_ratio());
            this.model.voValue.put("shape", nVO_BASELAND_MAIN.getShape());
            this.model.voValue.put("slop", nVO_BASELAND_MAIN.getSlop());
            this.model.voValue.put("width", nVO_BASELAND_MAIN.getWidth());
            this.model.voValue.put("deep", nVO_BASELAND_MAIN.getDeep());
            this.model.voValue.put("street_rel", nVO_BASELAND_MAIN.getStreet_rel());
            this.model.voValue.put("roadwidth", nVO_BASELAND_MAIN.getRoadwidth());
        }
        for (Node node : this.report8.getChildren()) {
            string = node.getId();
            if (string == null || !this.model.voValue.containsKey(string.replaceAll("_copy", ""))) continue;
            if (node instanceof AbsTextField) {
                ((AbsTextField)node).setValue(this.model.voValue.get(string.replaceAll("_copy", "")).toString());
                continue;
            }
            if (!(node instanceof StringArea)) continue;
            ((StringArea)node).setText(this.model.voValue.get(string.replaceAll("_copy", "")).toString());
        }
        for (Node node : this.panel_ext.getChildren()) {
            string = node.getId();
            if (string == null || !this.model.voValue.containsKey(string.replaceAll("_copy", "")) || !(node instanceof AbsTextField)) continue;
            ((AbsTextField)node).setValue(this.model.voValue.get(string.replaceAll("_copy", "")).toString());
        }
        this.baseland_aa10.setValue(Double.toString(EstimateModel.BASELANDBEAN.voMain.getAa10()));
        this.baseland_landuse.setValue(EstimateModel.BASELANDBEAN.voMain.getLanduse());
        this.baseland_cov_ratio.setValue(Double.toString(EstimateModel.BASELANDBEAN.voMain.getCov_ratio()));
        this.baseland_are_ratio.setValue(Double.toString(EstimateModel.BASELANDBEAN.voMain.getAre_ratio()));
        this.baseland_shape.setValue(EstimateModel.BASELANDBEAN.voMain.getShape());
        this.baseland_slop.setValue(EstimateModel.BASELANDBEAN.voMain.getSlop());
        this.baseland_width.setValue(Double.toString(EstimateModel.BASELANDBEAN.voMain.getWidth()));
        this.baseland_deep.setValue(Double.toString(EstimateModel.BASELANDBEAN.voMain.getDeep()));
        this.baseland_street_rel.setValue(EstimateModel.BASELANDBEAN.voMain.getStreet_rel());
        this.baseland_roadwidth.setValue(!StringProcess.isEmpty(EstimateModel.BASELANDBEAN.voMain.getRoadwidth()) ? EstimateModel.BASELANDBEAN.voMain.getRoadwidth() : "0");
        this.sale_are_type.setSelected("1".equals(this.model.voValue.get("sale_are_type")));
        this.is_merge.getSelectionModel().select(StringProcess.parserInt(this.model.voValue.get("is_merge"), 0));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\u00f7(1+").append(this.benefit_rate.getText().toString()).append("%)\u00f7(1+").append(this.sum_rate.getText().toString());
        stringBuilder.append("%)-(").append(this.direct_cost.getText().toString()).append("+").append(this.indir_cost.getText().toString()).append(")");
        this.formula.setText(stringBuilder.toString());
    }

    private int getInstruStdPrice(String string, int n) {
        return this.parentController.model.getInstruStdPrice(string, n);
    }

    private void sale_are_typeChange(Boolean bl) {
        if (bl.booleanValue()) {
            this.floor1_area.setEditable(false);
            if (!this.floor1_area.getStyleClass().contains((Object)"forbidden")) {
                this.floor1_area.getStyleClass().add((Object)"forbidden");
            }
            this.floor2_area.setEditable(false);
            if (!this.floor2_area.getStyleClass().contains((Object)"forbidden")) {
                this.floor2_area.getStyleClass().add((Object)"forbidden");
            }
            this.rf_area.setEditable(false);
            if (!this.rf_area.getStyleClass().contains((Object)"forbidden")) {
                this.rf_area.getStyleClass().add((Object)"forbidden");
            }
            this.other_area.setEditable(false);
            if (!this.other_area.getStyleClass().contains((Object)"forbidden")) {
                this.other_area.getStyleClass().add((Object)"forbidden");
            }
            this.sale_are_ratio.setEditable(true);
            this.sale_are_ratio.getStyleClass().remove((Object)"forbidden");
        } else {
            this.floor1_area.setEditable(true);
            this.floor1_area.getStyleClass().remove((Object)"forbidden");
            this.floor2_area.setEditable(true);
            this.floor2_area.getStyleClass().remove((Object)"forbidden");
            this.rf_area.setEditable(true);
            this.rf_area.getStyleClass().remove((Object)"forbidden");
            this.other_area.setEditable(true);
            this.other_area.getStyleClass().remove((Object)"forbidden");
            this.sale_are_ratio.setEditable(false);
            if (!this.sale_are_ratio.getStyleClass().contains((Object)"forbidden")) {
                this.sale_are_ratio.getStyleClass().add((Object)"forbidden");
            }
        }
        if (!this.isFirstRefresh) {
            this.reCal();
        }
    }
}

