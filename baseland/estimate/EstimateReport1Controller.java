/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonParser
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
 *  javafx.scene.control.Alert
 *  javafx.scene.control.Alert$AlertType
 *  javafx.scene.control.Button
 *  javafx.scene.control.ButtonType
 *  javafx.scene.control.CheckBox
 *  javafx.scene.control.ComboBox
 *  javafx.scene.control.Label
 *  javafx.scene.control.ListView
 *  javafx.scene.control.RadioButton
 *  javafx.scene.control.TextField
 *  javafx.scene.image.Image
 *  javafx.scene.image.ImageView
 *  javafx.scene.layout.AnchorPane
 *  javafx.stage.Modality
 *  javafx.stage.Stage
 *  javafx.stage.StageStyle
 *  javafx.stage.Window
 */
package com.wfusion.baseland.estimate;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.wfusion.baseland.SQLiteDataProviderModel;
import com.wfusion.baseland.basic.BaseLandDialog;
import com.wfusion.baseland.basic.IBaseLandDialog;
import com.wfusion.baseland.basic.IController;
import com.wfusion.baseland.estimate.EstimateController;
import com.wfusion.baseland.estimate.EstimateModel;
import com.wfusion.baseland.estimate.EstimateReport1Model;
import com.wfusion.baseland.estimate.EstimateSetRoadDataController;
import com.wfusion.baseland.estimate.EstimateVersionCopyDialog;
import com.wfusion.baseland.estimate.ImageBrowserDialog;
import com.wfusion.baseland.system.GlossaryModel;
import com.wfusion.fx.node.AbsTextField;
import com.wfusion.fx.node.DecimalField;
import com.wfusion.fx.node.StringArea;
import com.wfusion.fx.node.StringField;
import com.wfusion.fx.util.ImageProcessBo;
import com.wfusion.fx.util.JavaFXUtil;
import com.wfusion.util.OptionPair;
import com.wfusion.util.StringProcess;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import moiland.baseland.bo.SaveCheckBo;
import moiland.baseland.dataaccess.nvo.NVO_SRKEYN_ALL;
import moiland.baseland.util.BaseLandFactorVersionHelper;
import moiland.baseland.verify.BaseLandVerifyUtil;

public class EstimateReport1Controller
implements IController {
    @FXML
    private StringArea notes;
    @FXML
    private StringField cadasNO;
    @FXML
    private TextField fill_date;
    @FXML
    private ComboBox<OptionPair> aa48;
    @FXML
    private ComboBox<OptionPair> cbLiteral;
    @FXML
    private ComboBox<OptionPair> cbVersion;
    @FXML
    private ComboBox<String> cbLanduse;
    @FXML
    private Button btUpload;
    @FXML
    private RadioButton trad_type1;
    @FXML
    private RadioButton trad_type2;
    @FXML
    private RadioButton trad_type3;
    @FXML
    private DecimalField base_pricep2;
    @FXML
    private RadioButton land_scene1;
    @FXML
    private RadioButton land_scene2;
    @FXML
    private RadioButton land_scene3;
    @FXML
    private CheckBox attachs2;
    @FXML
    private CheckBox attachs3;
    @FXML
    private CheckBox attachs4;
    @FXML
    private CheckBox attachs5;
    @FXML
    private CheckBox attachs6;
    @FXML
    private CheckBox attachs1;
    @FXML
    private ImageView img;
    @FXML
    private AnchorPane report1;
    @FXML
    private Button btAddLiteral;
    @FXML
    private StringField landuse;
    @FXML
    private ListView<OptionPair> aa49s;
    @FXML
    private Button btDelCadas;
    @FXML
    private Button btAddCadas;
    @FXML
    private Button btPhotos;
    @FXML
    private Button btChangeVersion;
    @FXML
    private Label versionShow;
    @FXML
    private Button btCopy;
    @FXML
    private Button btRoad;
    @FXML
    private TextField floor_up;
    @FXML
    private TextField floor_bf;
    @FXML
    private TextField aa10;
    @FXML
    private TextField addr;
    EstimateController parentController = null;
    EstimateReport1Model model = new EstimateReport1Model();
    SaveCheckBo checkbo = new SaveCheckBo(EstimateModel.BASELANDBEAN);
    String street_rel_ext = "";
    BaseLandDialog manager;
    String splitChar = "&#32;&#32;";
    boolean isFirstRefresh = true;
    static Map<String, String> col_rp2 = new HashMap<String, String>();
    ChangeListener<Boolean> lostFocusListener = new ChangeListener<Boolean>(){

        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean bl, Boolean bl2) {
            if (!bl2.booleanValue()) {
                EstimateReport1Controller.this.reCal();
            }
        }
    };
    ChangeListener<Boolean> radioChangeListener = new ChangeListener<Boolean>(){

        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean bl, Boolean bl2) {
            if (EstimateReport1Controller.this.isFirstRefresh || !bl2.booleanValue()) {
                return;
            }
            EstimateReport1Controller.this.reCal();
        }
    };
    ChangeListener<Boolean> checkBoxListener = new ChangeListener<Boolean>(){

        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean bl, Boolean bl2) {
            if (EstimateReport1Controller.this.isFirstRefresh) {
                return;
            }
            EstimateReport1Controller.this.reCal();
        }
    };

    public void init(BaseLandDialog baseLandDialog, EstimateController estimateController) {
        this.manager = baseLandDialog;
        this.parentController = estimateController;
        this.initCombox();
        this.initButton();
        this.initTextField();
        this.img.setPreserveRatio(true);
        this.img.setFitHeight(302.0);
        this.img.setFitWidth(301.0);
    }

    private void initTextField() {
        String string = EstimateModel.BASELANDBEAN.voMain.getTableName().toUpperCase();
        for (Node node : this.report1.getChildren()) {
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
                ((TextField)node).setEditable(!node.getStyleClass().contains((Object)"forbidden"));
            } else if (node instanceof StringField) {
                string2 = node.getId().toUpperCase();
                stringArray = SaveCheckBo.columnSet.get(string + "_" + string2);
                if (stringArray != null && stringArray.length == 4) {
                    ((StringField)node).setName(stringArray[0]);
                    ((StringField)node).setMaxLength(StringProcess.parserInt(stringArray[2]));
                    ((StringField)node).setMinLength(StringProcess.parserInt(stringArray[3]));
                }
                node.focusedProperty().addListener(this.lostFocusListener);
                ((TextField)node).setEditable(!node.getStyleClass().contains((Object)"forbidden"));
            } else if (node instanceof StringArea) {
                string2 = node.getId().toUpperCase();
                stringArray = SaveCheckBo.columnSet.get(string + "_" + string2);
                if (stringArray != null && stringArray.length == 4) {
                    ((StringArea)node).setName(stringArray[0]);
                    ((StringArea)node).setMaxLength(StringProcess.parserInt(stringArray[2]));
                    ((StringArea)node).setMinLength(StringProcess.parserInt(stringArray[3]));
                }
                node.focusedProperty().addListener(this.lostFocusListener);
            } else if (node instanceof TextField) {
                node.focusedProperty().addListener(this.lostFocusListener);
                ((TextField)node).setEditable(!node.getStyleClass().contains((Object)"forbidden"));
            }
            if (node instanceof RadioButton) {
                ((RadioButton)node).selectedProperty().addListener(this.radioChangeListener);
            }
            if (!(node instanceof CheckBox)) continue;
            ((CheckBox)node).selectedProperty().addListener(this.checkBoxListener);
        }
        this.fill_date.focusedProperty().addListener((ChangeListener)new ChangeListener<Boolean>(){

            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean bl, Boolean bl2) {
                String string = EstimateReport1Controller.this.fill_date.getText();
                if (!(bl2.booleanValue() || StringProcess.isEmpty(string) || BaseLandVerifyUtil.checkDate(string, false))) {
                    JavaFXUtil.showToastMessageBox(EstimateReport1Controller.this.parentController.dialog.getStage(), "\u586b\u8868\u65e5\u671f\u683c\u5f0f\u932f\u8aa4", 500);
                    EstimateReport1Controller.this.fill_date.setText("");
                }
            }
        });
        this.floor_up.textProperty().addListener((ChangeListener)new ChangeListener<String>(){

            public void changed(ObservableValue<? extends String> observableValue, String string, String string2) {
                if (string2 != null && !string.equals(string2)) {
                    EstimateModel.BASELANDBEAN.voRentExt.setCre03(string2);
                }
            }
        });
        this.floor_bf.textProperty().addListener((ChangeListener)new ChangeListener<String>(){

            public void changed(ObservableValue<? extends String> observableValue, String string, String string2) {
                if (string2 != null && !string.equals(string2)) {
                    EstimateModel.BASELANDBEAN.voRentExt.setCre04(string2);
                }
            }
        });
        this.aa10.textProperty().addListener((ChangeListener)new ChangeListener<String>(){

            public void changed(ObservableValue<? extends String> observableValue, String string, String string2) {
                if (EstimateModel.BASELANDBEAN.voRentExt.getCre07ori() == 0.0 && !StringProcess.isEmpty(string) && !StringProcess.isEmpty(string2) && Double.parseDouble(string.replaceAll(",", "")) != Double.parseDouble(string2.replaceAll(",", ""))) {
                    EstimateModel.BASELANDBEAN.voRentExt.setCre07(Double.parseDouble(string2.replaceAll(",", "")));
                }
            }
        });
        this.addr.textProperty().addListener((ChangeListener)new ChangeListener<String>(){

            public void changed(ObservableValue<? extends String> observableValue, String string, String string2) {
                if (string2 != null && !string.equals(string2)) {
                    EstimateModel.BASELANDBEAN.voRentExt.setCre02(string2);
                }
            }
        });
    }

    private void initButton() {
        this.btAddCadas.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                String string = ((OptionPair)EstimateReport1Controller.this.aa48.getSelectionModel().getSelectedItem()).getValue();
                String string2 = ((OptionPair)EstimateReport1Controller.this.aa48.getSelectionModel().getSelectedItem()).getAlias();
                String string3 = StringProcess.parserShortLandNo2Long(EstimateReport1Controller.this.cadasNO.getValue());
                boolean bl = false;
                for (OptionPair optionPair : EstimateReport1Controller.this.aa49s.getItems()) {
                    if (!optionPair.getValue().equals(string + string3)) continue;
                    bl = true;
                    break;
                }
                if (!bl) {
                    EstimateReport1Controller.this.aa49s.getItems().add((Object)new OptionPair(string + string3, string2 + " \u5730\u865f\uff1a" + string3));
                    EstimateReport1Controller.this.reCal();
                    EstimateModel.BASELANDBEAN.queryBean.office = SQLiteDataProviderModel.getOfficeFromSect(EstimateModel.BASELANDBEAN.queryBean.AA45, string);
                } else {
                    JavaFXUtil.showToastMessageBox(new Stage(), "\u4e0d\u53ef\u91cd\u8907\u52a0\u5165", 1000);
                }
            }
        });
        this.btDelCadas.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                EstimateReport1Controller.this.aa49s.getItems().remove(EstimateReport1Controller.this.aa49s.getSelectionModel().getSelectedIndex());
                EstimateReport1Controller.this.reCal();
                if (EstimateReport1Controller.this.aa49s.getItems().size() == 0) {
                    EstimateModel.BASELANDBEAN.queryBean.office = "";
                }
            }
        });
        this.btAddLiteral.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                String string = EstimateReport1Controller.this.notes.getValue();
                if (!StringProcess.isEmpty(string)) {
                    GlossaryModel glossaryModel = new GlossaryModel();
                    glossaryModel.addLiteral(string, "MAIN", "MAIN_NOTE");
                    glossaryModel.query("MAIN", "MAIN_NOTE", 0, "");
                    EstimateReport1Controller.this.cbLiteral.setItems(FXCollections.observableArrayList(glossaryModel.getDataOptionpairs()));
                    EstimateReport1Controller.this.cbLiteral.getSelectionModel().selectLast();
                    JavaFXUtil.showToastMessageBox(EstimateReport1Controller.this.parentController.dialog.getStage(), "\u5df2\u65b0\u589e\u8fad\u5eab", 1500);
                }
            }
        });
        this.btUpload.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                ImageProcessBo imageProcessBo = new ImageProcessBo();
                StringBuilder stringBuilder = new StringBuilder();
                Node node = (Node)actionEvent.getSource();
                Stage stage = (Stage)node.getScene().getWindow();
                byte[] byArray = imageProcessBo.getImageByteArray(600, 600, stringBuilder, stage);
                if (byArray != null) {
                    EstimateReport1Controller.this.img.setImage(new Image((InputStream)new ByteArrayInputStream(byArray)));
                    EstimateModel.BASELANDBEAN.voImages.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
                    EstimateModel.BASELANDBEAN.voImages.setBaseno(EstimateModel.BASELANDBEAN.queryBean.baseno);
                    EstimateModel.BASELANDBEAN.voImages.setCity(EstimateModel.BASELANDBEAN.queryBean.AA45);
                    EstimateModel.BASELANDBEAN.voImages.setDist(EstimateModel.BASELANDBEAN.queryBean.AA46);
                    EstimateModel.BASELANDBEAN.voImages.setPhoto_type("SKT");
                    EstimateModel.BASELANDBEAN.voImages.setPhoto(byArray);
                } else if (stringBuilder.length() > 0) {
                    JavaFXUtil.showErrorMessageBox("\u4e0a\u50b3\u5716\u7247\u6709\u8aa4:" + stringBuilder.toString());
                }
            }
        });
        this.btPhotos.setOnAction(actionEvent -> {
            Node node = (Node)actionEvent.getSource();
            Window window = node.getScene().getWindow();
            ImageBrowserDialog imageBrowserDialog = new ImageBrowserDialog((Stage)window);
            imageBrowserDialog.load("\u5730\u50f9\u57fa\u6e96\u5730\u4f30\u50f9\u5831\u544a\u8868", "BASE");
            imageBrowserDialog.show();
        });
        this.btChangeVersion.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                if (EstimateReport1Controller.this.cbVersion.getSelectionModel().getSelectedItem() != null && !StringProcess.isEmpty(((OptionPair)EstimateReport1Controller.this.cbVersion.getSelectionModel().getSelectedItem()).getValue())) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "\u5207\u63db\u57fa\u6e96\u8868\u5c07\u6703\u95dc\u9589\u6574\u500b\u7a0b\u5f0f\uff0c\u662f\u5426\u8981\u5207\u63db?", new ButtonType[]{ButtonType.YES, ButtonType.NO});
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        EstimateModel.BASELANDBEAN.voMain.setVersion(((OptionPair)EstimateReport1Controller.this.cbVersion.getSelectionModel().getSelectedItem()).getValue());
                        if (EstimateReport1Controller.this.parentController.model.save()) {
                            Node node = (Node)actionEvent.getSource();
                            Stage stage = (Stage)node.getScene().getWindow();
                            stage.close();
                        } else {
                            JavaFXUtil.showErrorMessageBox("\u5207\u63db\u5931\u6557");
                        }
                    }
                } else {
                    JavaFXUtil.showToastMessageBox(new Stage(), "\u8acb\u78ba\u8a8d\u6b32\u5207\u63db\u662f\u7528\u57fa\u6e96\u8868", 1000);
                }
            }
        });
        this.btCopy.setOnAction(actionEvent -> {
            Node node = (Node)actionEvent.getSource();
            Window window = node.getScene().getWindow();
            EstimateVersionCopyDialog estimateVersionCopyDialog = new EstimateVersionCopyDialog((Stage)window);
            estimateVersionCopyDialog.load(EstimateModel.BASELANDBEAN.queryBean.year, EstimateModel.BASELANDBEAN.queryBean.baseno, EstimateReport1Model.version_conn);
            estimateVersionCopyDialog.show();
        });
        this.btRoad.setOnAction(actionEvent -> {
            try {
                Stage stage = new Stage();
                stage.initStyle(StageStyle.UNDECORATED);
                stage.initModality(Modality.APPLICATION_MODAL);
                FXMLLoader fXMLLoader = new FXMLLoader(this.getClass().getResource("/view/EstimateSetRoadData.fxml"));
                Scene scene = new Scene((Parent)fXMLLoader.load());
                stage.setScene(scene);
                EstimateSetRoadDataController estimateSetRoadDataController = (EstimateSetRoadDataController)fXMLLoader.getController();
                estimateSetRoadDataController.init(this.manager, this, EstimateModel.BASELANDBEAN.voMain.getStreet(), EstimateModel.BASELANDBEAN.voMain.getRoadwidth(), this.street_rel_ext);
                stage.showAndWait();
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }

    private void initCombox() {
        this.aa48.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getAA48List(EstimateModel.BASELANDBEAN.queryBean.AA45, "", EstimateModel.BASELANDBEAN.queryBean.AA46)));
        this.cbLanduse.setItems(FXCollections.observableArrayList(this.model.getLanduseList()));
        this.cbLanduse.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<String>(){

            public void changed(ObservableValue<? extends String> observableValue, String string, String string2) {
                if (EstimateReport1Controller.this.isFirstRefresh) {
                    return;
                }
                if (string2 != null) {
                    EstimateReport1Controller.this.landuse.setValue(string2);
                }
            }
        });
        GlossaryModel glossaryModel = new GlossaryModel();
        glossaryModel.query("MAIN", "MAIN_NOTE", 0, "");
        this.cbLiteral.setItems(FXCollections.observableArrayList(glossaryModel.getDataOptionpairs()));
        this.cbLiteral.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                if (optionPair2 != null && !StringProcess.isEmpty(optionPair2.getValue())) {
                    EstimateReport1Controller.this.notes.setText(optionPair2.getAlias());
                }
            }
        });
    }

    @Override
    public void refresh() {
        this.isFirstRefresh = true;
        this.pageRefresh();
        this.updateUIFromVo();
        if (EstimateModel.BASELANDBEAN.voImages != null && EstimateModel.BASELANDBEAN.voImages.getPhoto() != null) {
            this.img.setImage(new Image((InputStream)new ByteArrayInputStream(EstimateModel.BASELANDBEAN.voImages.getPhoto())));
        }
        this.refreshCombox();
        this.getVersionItem();
        this.isFirstRefresh = false;
    }

    private void pageRefresh() {
        this.aa48.getSelectionModel().selectFirst();
        this.cbLanduse.getSelectionModel().selectFirst();
        this.attachs1.setSelected(false);
        this.attachs2.setSelected(false);
        this.attachs3.setSelected(false);
        this.attachs4.setSelected(false);
        this.attachs5.setSelected(false);
        this.attachs6.setSelected(false);
        this.versionShow.setText("");
    }

    private void getVersionItem() {
        if (!StringProcess.isEmpty(this.model.voValue.get("urban").toString())) {
            ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
            arrayList.add(new OptionPair("", "\u8acb\u9078\u64c7"));
            LinkedHashMap<String, String> linkedHashMap = BaseLandFactorVersionHelper.getAvailableListByUrban(EstimateModel.BASELANDBEAN.queryBean.urban);
            if (linkedHashMap != null && linkedHashMap.size() > 0) {
                OptionPair optionPair = null;
                for (Map.Entry<String, String> entry : linkedHashMap.entrySet()) {
                    arrayList.add(new OptionPair(entry.getKey(), entry.getValue()));
                    if (!entry.getKey().equals(this.model.voValue.get("version").toString())) continue;
                    optionPair = new OptionPair(entry.getKey(), entry.getValue());
                }
                this.cbVersion.setItems(FXCollections.observableArrayList(arrayList));
                if (optionPair != null) {
                    this.cbVersion.getSelectionModel().select(optionPair);
                } else {
                    this.cbVersion.getSelectionModel().selectFirst();
                }
            }
        }
    }

    private void refreshCombox() {
        this.aa48.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getAA48List(EstimateModel.BASELANDBEAN.queryBean.AA45, "", EstimateModel.BASELANDBEAN.queryBean.AA46)));
        this.cbLanduse.setItems(FXCollections.observableArrayList(this.model.getLanduseList()));
    }

    void reCal() {
        this.updateVoFromUI();
        this.model.reCal();
        this.updateUIFromVo();
    }

    protected void updateVoFromUI() {
        Object object4;
        Object object2;
        Object object32;
        for (Object object32 : this.report1.getChildren()) {
            if (this.model.voValue.containsKey(object32.getId())) {
                if (object32 instanceof DecimalField) {
                    if (object32.getStyleClass().contains((Object)"rp2_ch") && ((BigDecimal)(object2 = new BigDecimal((String)this.model.voValue.get(object32.getId())))).compareTo((BigDecimal)(object4 = new BigDecimal(((DecimalField)((Object)object32)).getValue().toString()))) != 0) {
                        String string = col_rp2.get(object32.getId());
                        EstimateModel.col_update.put(string, true);
                    }
                    this.model.voValue.put(object32.getId(), ((DecimalField)((Object)object32)).getValue().toString());
                } else if (object32 instanceof StringField) {
                    this.model.voValue.put(object32.getId(), ((StringField)((Object)object32)).getValue().toString());
                }
                if (object32 instanceof StringArea) {
                    this.model.voValue.put(object32.getId(), ((StringArea)((Object)object32)).getValue().toString());
                }
            }
            if (!(object32 instanceof RadioButton) || !(object2 = (RadioButton)object32).isSelected()) continue;
            if ("land_scene1".equals(object32.getId())) {
                this.model.voValue.put("land_scene", "01");
                continue;
            }
            if ("land_scene2".equals(object32.getId())) {
                this.model.voValue.put("land_scene", "02");
                continue;
            }
            if (!"land_scene3".equals(object32.getId())) continue;
            this.model.voValue.put("land_scene", "03");
        }
        EstimateModel.BASELANDBEAN.voMain.setBeanByHashMap(this.model.voValue, false);
        StringBuilder stringBuilder = new StringBuilder();
        object32 = new StringBuilder();
        for (Object object4 : this.aa49s.getItems()) {
            stringBuilder.append(this.doSb1Str(((OptionPair)object4).getValue())).append("\u3001");
            ((StringBuilder)object32).append(((OptionPair)object4).getValue()).append(",");
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        if (((StringBuilder)object32).length() > 0) {
            ((StringBuilder)object32).deleteCharAt(((StringBuilder)object32).length() - 1);
        }
        if (this.aa49s.getItems().size() > 0) {
            EstimateModel.BASELANDBEAN.voMain.setAa48(((OptionPair)this.aa49s.getItems().get(0)).getValue().substring(0, 4));
        }
        EstimateModel.BASELANDBEAN.voMain.setAa49(((StringBuilder)object32).toString());
        object2 = "";
        object4 = "";
        if (!StringProcess.isEmpty(EstimateModel.BASELANDBEAN.queryBean.AA45)) {
            object2 = SQLiteDataProviderModel.getMapAA45().get(EstimateModel.BASELANDBEAN.queryBean.AA45);
        }
        if (!StringProcess.isEmpty(EstimateModel.BASELANDBEAN.queryBean.AA45) && !StringProcess.isEmpty(EstimateModel.BASELANDBEAN.queryBean.AA46)) {
            object4 = SQLiteDataProviderModel.getMapAA46().get(EstimateModel.BASELANDBEAN.queryBean.AA45).get(EstimateModel.BASELANDBEAN.queryBean.AA46);
        }
        EstimateModel.BASELANDBEAN.voMain.setLand_position((String)object2 + (String)object4 + stringBuilder.toString());
        stringBuilder.setLength(0);
        stringBuilder.append(this.attachs1.isSelected() ? "1" : "0").append(this.attachs2.isSelected() ? "1" : "0").append(this.attachs3.isSelected() ? "1" : "0");
        stringBuilder.append(this.attachs4.isSelected() ? "1" : "0").append(this.attachs5.isSelected() ? "1" : "0").append(this.attachs6.isSelected() ? "1" : "0");
        EstimateModel.BASELANDBEAN.voMain.setAttachs(stringBuilder.toString());
        if (this.trad_type1.isSelected()) {
            EstimateModel.BASELANDBEAN.voMain.setTrad_type("01");
        } else if (this.trad_type2.isSelected()) {
            EstimateModel.BASELANDBEAN.voMain.setTrad_type("02");
        } else if (this.trad_type3.isSelected()) {
            EstimateModel.BASELANDBEAN.voMain.setTrad_type("03");
        }
        EstimateModel.BASELANDBEAN.voMain.setNotes(this.street_rel_ext + this.splitChar + this.notes.getValue());
    }

    private String doSb1Str(String string) {
        String string2;
        String string3 = "";
        String string4 = "";
        ArrayList<NVO_SRKEYN_ALL> arrayList = this.model.queryAa48List(EstimateModel.BASELANDBEAN.queryBean.AA45, EstimateModel.BASELANDBEAN.queryBean.AA46);
        if (arrayList != null && arrayList.size() > 0) {
            string2 = string.substring(0, 4);
            for (NVO_SRKEYN_ALL nVO_SRKEYN_ALL : arrayList) {
                if (!nVO_SRKEYN_ALL.getKcde_4().equals(string2)) continue;
                string4 = nVO_SRKEYN_ALL.getKname();
            }
        }
        string2 = StringProcess.getLandShort(string.substring(4));
        string3 = string4 + string2 + "\u5730\u865f";
        return string3;
    }

    protected void updateUIFromVo() {
        this.model.updateHashMapValues();
        for (Node node : this.report1.getChildren()) {
            String string = node.getId();
            if (string == null || !this.model.voValue.containsKey(string)) continue;
            if (node instanceof AbsTextField) {
                ((AbsTextField)node).setValue(this.model.voValue.get(string).toString());
            } else if (node instanceof TextField) {
                ((TextField)node).setText(this.model.voValue.get(string).toString());
            }
            if (!(node instanceof StringArea)) continue;
            if (string.equals("notes")) {
                String string2 = this.model.voValue.get(string).toString();
                int n = string2.indexOf(this.splitChar);
                String string3 = "";
                if (n > -1) {
                    this.street_rel_ext = string2.substring(0, n);
                    this.street_rel_ext = this.street_rel_ext.replaceAll("&(?!amp;)", "");
                    string3 = string2.substring(n, string2.length());
                    string3 = string3.replaceAll(this.splitChar, "");
                    ((StringArea)node).setText(string3);
                    continue;
                }
                if (StringProcess.isEmpty(string2)) {
                    this.street_rel_ext = "";
                    ((StringArea)node).setText("");
                    continue;
                }
                JsonElement jsonElement = new JsonParser().parse(string2);
                if (jsonElement.isJsonArray()) {
                    this.street_rel_ext = string2;
                    this.street_rel_ext = this.street_rel_ext.replaceAll("&(?!amp;)", "");
                    ((StringArea)node).setText("");
                    continue;
                }
                this.street_rel_ext = "";
                ((StringArea)node).setText(string2);
                continue;
            }
            ((StringArea)node).setText(this.model.voValue.get(string).toString());
        }
        this.aa49s.setItems(FXCollections.observableArrayList(this.model.getAA49List()));
        this.trad_type1.setSelected(this.model.voValue.get("trad_type").equals("01"));
        this.trad_type2.setSelected(this.model.voValue.get("trad_type").equals("02"));
        this.trad_type3.setSelected(this.model.voValue.get("trad_type").equals("03"));
        this.land_scene1.setSelected(this.model.voValue.get("land_scene").equals("01"));
        this.land_scene2.setSelected(this.model.voValue.get("land_scene").equals("02"));
        this.land_scene3.setSelected(this.model.voValue.get("land_scene").equals("03"));
        String string = this.model.voValue.get("attachs").toString();
        if (string.length() > 5) {
            this.attachs1.setSelected(string.charAt(0) == '1');
            this.attachs2.setSelected(string.charAt(1) == '1');
            this.attachs3.setSelected(string.charAt(2) == '1');
            this.attachs4.setSelected(string.charAt(3) == '1');
            this.attachs5.setSelected(string.charAt(4) == '1');
            this.attachs6.setSelected(string.charAt(5) == '1');
        }
        this.base_pricep2.setValue(String.valueOf(EstimateModel.BASELANDBEAN.voMain.getBase_pricep()));
        if (EstimateReport1Model.version_conn.equals("BaseLand_A.db")) {
            this.versionShow.setText("\u7248\u672cA");
        } else if (EstimateReport1Model.version_conn.equals("BaseLand_B.db")) {
            this.versionShow.setText("\u7248\u672cB");
        } else if (EstimateReport1Model.version_conn.equals("BaseLand_C.db")) {
            this.versionShow.setText("\u7248\u672cC");
        } else {
            this.versionShow.setText("\u6b63\u5f0f\u7248");
        }
    }

    @Override
    public void init(IBaseLandDialog iBaseLandDialog) {
    }

    public boolean isRatio100percent() {
        double d = EstimateModel.BASELANDBEAN.voMain.getCprice_ratio();
        double d2 = EstimateModel.BASELANDBEAN.voMain.getPprice_ratio();
        double d3 = EstimateModel.BASELANDBEAN.voMain.getRprice_ratio();
        BigDecimal bigDecimal = new BigDecimal(d);
        BigDecimal bigDecimal2 = new BigDecimal(d2);
        BigDecimal bigDecimal3 = new BigDecimal(d3);
        return bigDecimal.add(bigDecimal2).add(bigDecimal3).doubleValue() == 100.0;
    }

    static {
        col_rp2.put("aa10", "as339_0");
        col_rp2.put("cov_ratio", "as365_ds_0");
        col_rp2.put("are_ratio", "as366_ds_0");
        col_rp2.put("width", "as340_ds_0");
        col_rp2.put("deep", "as341_ds_0");
    }
}

