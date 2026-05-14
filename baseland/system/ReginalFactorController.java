/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.beans.property.SimpleObjectProperty
 *  javafx.beans.value.ChangeListener
 *  javafx.beans.value.ObservableValue
 *  javafx.collections.FXCollections
 *  javafx.collections.ObservableList
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
 *  javafx.scene.control.ComboBox
 *  javafx.scene.control.TableCell
 *  javafx.scene.control.TableColumn
 *  javafx.scene.control.TableColumn$CellDataFeatures
 *  javafx.scene.control.TablePosition
 *  javafx.scene.control.TableView
 *  javafx.scene.control.cell.ComboBoxTableCell
 *  javafx.scene.control.cell.PropertyValueFactory
 *  javafx.scene.control.cell.TextFieldTableCell
 *  javafx.stage.FileChooser
 *  javafx.stage.FileChooser$ExtensionFilter
 *  javafx.stage.Modality
 *  javafx.stage.Stage
 *  javafx.stage.StageStyle
 *  javafx.stage.Window
 *  javafx.util.Callback
 *  javafx.util.StringConverter
 *  javafx.util.converter.IntegerStringConverter
 */
package com.wfusion.baseland.system;

import com.wfusion.baseland.SQLiteDataProviderModel;
import com.wfusion.baseland.basic.Controller;
import com.wfusion.baseland.basic.IBaseLandDialog;
import com.wfusion.baseland.system.CreateReginalFactorController;
import com.wfusion.baseland.system.RegionalFactorModel;
import com.wfusion.baseland.system.SystemReginalFactor_editController;
import com.wfusion.fx.util.JavaFXUtil;
import com.wfusion.util.OptionPair;
import com.wfusion.util.StringProcess;
import java.io.File;
import java.io.IOException;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_REGIONAL_FACTOR;
import moiland.baseland.util.CodeList;
import moiland.baseland.verify.BaseLandVerifyUtil;

public class ReginalFactorController
extends Controller {
    @FXML
    private TableColumn<NVO_BASELAND_REGIONAL_FACTOR, OptionPair> tcMainCode;
    @FXML
    private TableColumn<NVO_BASELAND_REGIONAL_FACTOR, OptionPair> tcItemCode;
    @FXML
    private TableColumn<NVO_BASELAND_REGIONAL_FACTOR, Integer> tcImpact;
    @FXML
    private TableColumn<NVO_BASELAND_REGIONAL_FACTOR, OptionPair> tcDegree;
    @FXML
    private TableColumn<NVO_BASELAND_REGIONAL_FACTOR, String> tcDnames;
    @FXML
    private TableColumn<NVO_BASELAND_REGIONAL_FACTOR, String> tcFunc;
    @FXML
    private TableColumn<NVO_BASELAND_REGIONAL_FACTOR, String> tcSetting;
    @FXML
    private ComboBox<OptionPair> cbAA45;
    @FXML
    private ComboBox<OptionPair> cbAA46;
    @FXML
    private ComboBox<OptionPair> cbYear;
    @FXML
    private ComboBox<OptionPair> cbVersion;
    @FXML
    private ComboBox<OptionPair> cbBaseLandNO;
    @FXML
    private ComboBox<OptionPair> cbMainCode;
    @FXML
    private Button btExit;
    @FXML
    private Button btQuery;
    @FXML
    private Button btImport;
    @FXML
    private TableView<NVO_BASELAND_REGIONAL_FACTOR> tbList;
    @FXML
    private Button btAdd;
    RegionalFactorModel model = new RegionalFactorModel();
    String AA45 = "";
    String AA46 = "";
    String year = "";
    String version = "";
    String baseno = "";
    String mainCode = "";

    @Override
    public void init(IBaseLandDialog iBaseLandDialog) {
        super.init(iBaseLandDialog);
        this.setTitle("\u7cfb\u7d71\u7ba1\u7406 - \u5340\u57df\u56e0\u7d20\u7dad\u8b77");
        this.initComboBox();
        this.tbList.setEditable(true);
        this.tcMainCode.setCellValueFactory((Callback)new PropertyValueFactory("nameOfMainCode"));
        this.tcItemCode.setCellValueFactory((Callback)new PropertyValueFactory("nameOfItem"));
        this.tcFunc.setCellValueFactory((Callback)new PropertyValueFactory(""));
        this.tcSetting.setCellValueFactory((Callback)new PropertyValueFactory(""));
        this.tcImpact.setCellValueFactory((Callback)new PropertyValueFactory("impact"));
        this.tcImpact.setCellFactory(TextFieldTableCell.forTableColumn((StringConverter)new IntegerStringConverter()));
        this.tcImpact.setOnEditCommit(cellEditEvent -> {
            TablePosition tablePosition = cellEditEvent.getTablePosition();
            int n = tablePosition.getRow();
            NVO_BASELAND_REGIONAL_FACTOR nVO_BASELAND_REGIONAL_FACTOR = (NVO_BASELAND_REGIONAL_FACTOR)cellEditEvent.getTableView().getItems().get(n);
            nVO_BASELAND_REGIONAL_FACTOR.setImpact((Integer)cellEditEvent.getNewValue());
            this.model.save(nVO_BASELAND_REGIONAL_FACTOR, this.AA45, this.AA46, this.year, this.version, this.baseno, this.mainCode);
        });
        this.tcDegree.setCellValueFactory((Callback)new Callback<TableColumn.CellDataFeatures<NVO_BASELAND_REGIONAL_FACTOR, OptionPair>, ObservableValue<OptionPair>>(){

            public ObservableValue<OptionPair> call(TableColumn.CellDataFeatures<NVO_BASELAND_REGIONAL_FACTOR, OptionPair> cellDataFeatures) {
                NVO_BASELAND_REGIONAL_FACTOR nVO_BASELAND_REGIONAL_FACTOR = (NVO_BASELAND_REGIONAL_FACTOR)cellDataFeatures.getValue();
                OptionPair optionPair = new OptionPair(String.valueOf(nVO_BASELAND_REGIONAL_FACTOR.getDegree()), String.valueOf(nVO_BASELAND_REGIONAL_FACTOR.getDegree()));
                return new SimpleObjectProperty((Object)optionPair);
            }
        });
        this.tcDegree.setOnEditCommit(cellEditEvent -> {
            TablePosition tablePosition = cellEditEvent.getTablePosition();
            OptionPair optionPair = (OptionPair)cellEditEvent.getNewValue();
            int n = tablePosition.getRow();
            NVO_BASELAND_REGIONAL_FACTOR nVO_BASELAND_REGIONAL_FACTOR = (NVO_BASELAND_REGIONAL_FACTOR)cellEditEvent.getTableView().getItems().get(n);
            nVO_BASELAND_REGIONAL_FACTOR.setDegree(StringProcess.parserInt(optionPair.getValue(), 0));
            this.model.save(nVO_BASELAND_REGIONAL_FACTOR, this.AA45, this.AA46, this.year, this.version, this.baseno, this.mainCode);
        });
        this.tcDnames.setCellValueFactory((Callback)new PropertyValueFactory("dnames"));
        this.tcDnames.setCellFactory(TextFieldTableCell.forTableColumn());
        this.tcDnames.setOnEditCommit(cellEditEvent -> {
            NVO_BASELAND_REGIONAL_FACTOR nVO_BASELAND_REGIONAL_FACTOR = (NVO_BASELAND_REGIONAL_FACTOR)cellEditEvent.getTableView().getItems().get(cellEditEvent.getTablePosition().getRow());
            String string = (String)cellEditEvent.getNewValue();
            String[] stringArray = StringProcess.split(string, ",");
            if (stringArray.length != nVO_BASELAND_REGIONAL_FACTOR.getDegree()) {
                JavaFXUtil.showErrorMessageBox("\u7b49\u7d1a\u540d\u7a31\u6578\u91cf\u8207\u8a2d\u5b9a\u4e4b\u7b49\u7d1a\u4e0d\u7b26\uff0c\u8acb\u91cd\u65b0\u8f38\u5165", "\u7b49\u7d1a\u540d\u7a31\u8acb\u7528\u9017\u865f(,)\u9694\u958b\uff0c\u7b49\u7d1a\u540d\u7a31\u6578\u91cf\u9700\u8207\u8a2d\u5b9a\u7b49\u7d1a\u4e00\u81f4");
                this.refreshList();
            } else {
                nVO_BASELAND_REGIONAL_FACTOR.setDnames((String)cellEditEvent.getNewValue());
                this.model.save(nVO_BASELAND_REGIONAL_FACTOR, this.AA45, this.AA46, this.year, this.version, this.baseno, this.mainCode);
            }
        });
        Callback<TableColumn<NVO_BASELAND_REGIONAL_FACTOR, String>, TableCell<NVO_BASELAND_REGIONAL_FACTOR, String>> callback = new Callback<TableColumn<NVO_BASELAND_REGIONAL_FACTOR, String>, TableCell<NVO_BASELAND_REGIONAL_FACTOR, String>>(){

            public TableCell<NVO_BASELAND_REGIONAL_FACTOR, String> call(TableColumn<NVO_BASELAND_REGIONAL_FACTOR, String> tableColumn) {
                TableCell<NVO_BASELAND_REGIONAL_FACTOR, String> tableCell = new TableCell<NVO_BASELAND_REGIONAL_FACTOR, String>(){
                    final Button btn = new Button("\u522a\u9664");

                    public void updateItem(String string, boolean bl) {
                        super.updateItem((Object)string, bl);
                        if (bl) {
                            this.setGraphic(null);
                        } else {
                            this.btn.setOnAction(actionEvent -> {
                                NVO_BASELAND_REGIONAL_FACTOR nVO_BASELAND_REGIONAL_FACTOR = (NVO_BASELAND_REGIONAL_FACTOR)this.getTableView().getItems().get(this.getIndex());
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "\u662f\u5426\u78ba\u5b9a\u522a\u9664 [ " + nVO_BASELAND_REGIONAL_FACTOR.getNameOfItem() + " ] \u4e4b\u8cc7\u6599\uff1f", new ButtonType[]{ButtonType.YES, ButtonType.NO, ButtonType.CANCEL});
                                alert.showAndWait();
                                if (alert.getResult() == ButtonType.YES) {
                                    ReginalFactorController.this.model.delete(nVO_BASELAND_REGIONAL_FACTOR);
                                    ReginalFactorController.this.refreshList();
                                }
                            });
                            this.setGraphic((Node)this.btn);
                        }
                        this.setText(null);
                    }
                };
                return tableCell;
            }
        };
        Callback<TableColumn<NVO_BASELAND_REGIONAL_FACTOR, String>, TableCell<NVO_BASELAND_REGIONAL_FACTOR, String>> callback2 = new Callback<TableColumn<NVO_BASELAND_REGIONAL_FACTOR, String>, TableCell<NVO_BASELAND_REGIONAL_FACTOR, String>>(){

            public TableCell<NVO_BASELAND_REGIONAL_FACTOR, String> call(TableColumn<NVO_BASELAND_REGIONAL_FACTOR, String> tableColumn) {
                TableCell<NVO_BASELAND_REGIONAL_FACTOR, String> tableCell = new TableCell<NVO_BASELAND_REGIONAL_FACTOR, String>(){
                    final Button btn = new Button("\u8a2d\u5b9a\u6a19\u6e96");

                    public void updateItem(String string, boolean bl) {
                        super.updateItem((Object)string, bl);
                        if (bl) {
                            this.setGraphic(null);
                        } else {
                            NVO_BASELAND_REGIONAL_FACTOR nVO_BASELAND_REGIONAL_FACTOR = (NVO_BASELAND_REGIONAL_FACTOR)this.getTableView().getItems().get(this.getIndex());
                            if (nVO_BASELAND_REGIONAL_FACTOR.getItem() != null && ReginalFactorController.this.model.factorItem != null) {
                                if (ReginalFactorController.this.model.factorItem.get(nVO_BASELAND_REGIONAL_FACTOR.getItem()).isAutoSwitchLevel()) {
                                    this.btn.setOnAction(actionEvent -> {
                                        try {
                                            String[] stringArray = StringProcess.split(nVO_BASELAND_REGIONAL_FACTOR.getDnames(), ",");
                                            if (stringArray.length != nVO_BASELAND_REGIONAL_FACTOR.getDegree()) {
                                                JavaFXUtil.showErrorMessageBox("\u7b49\u7d1a\u540d\u7a31\u6578\u91cf\u8207\u8a2d\u5b9a\u4e4b\u7b49\u7d1a\u4e0d\u7b26\uff0c\u8acb\u91cd\u65b0\u8f38\u5165", "\u7b49\u7d1a\u540d\u7a31\u8acb\u7528\u9017\u865f(,)\u9694\u958b\uff0c\u7b49\u7d1a\u540d\u7a31\u6578\u91cf\u9700\u8207\u8a2d\u5b9a\u7b49\u7d1a\u4e00\u81f4");
                                                ReginalFactorController.this.refreshList();
                                            } else {
                                                ReginalFactorController.this.setStandard(nVO_BASELAND_REGIONAL_FACTOR);
                                            }
                                        }
                                        catch (Exception exception) {
                                            exception.printStackTrace();
                                        }
                                    });
                                    this.setGraphic((Node)this.btn);
                                } else {
                                    this.setGraphic(null);
                                }
                            }
                        }
                        this.setText(null);
                    }
                };
                return tableCell;
            }
        };
        this.tcFunc.setCellFactory((Callback)callback);
        this.tcFunc.setStyle("-fx-alignment: CENTER;");
        this.tcSetting.setCellFactory((Callback)callback2);
        this.tcSetting.setStyle("-fx-alignment: CENTER;");
        this.btExit.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                Node node = (Node)actionEvent.getSource();
                Stage stage = (Stage)node.getScene().getWindow();
                stage.close();
            }
        });
        this.btQuery.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                ReginalFactorController.this.query();
            }
        });
        this.btAdd.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                ReginalFactorController.this.createRec();
            }
        });
        this.btImport.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                Node node = (Node)actionEvent.getSource();
                Stage stage = (Stage)node.getScene().getWindow();
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().addAll((Object[])new FileChooser.ExtensionFilter[]{new FileChooser.ExtensionFilter("Exchange Files", new String[]{"*.rgf"})});
                File file = fileChooser.showOpenDialog((Window)stage);
                if (file != null) {
                    String string = ReginalFactorController.this.getSetName(file.getName());
                    String string2 = ReginalFactorController.this.model.importAndCheckData(file.getParent(), file.getName(), false);
                    if ("hasData".equals(string2)) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "\u532f\u5165\u7684\u5340\u57df\u56e0\u7d20\u5df2\u5b58\u5728\u662f\u5426\u8986\u84cb", new ButtonType[]{ButtonType.YES, ButtonType.NO, ButtonType.CANCEL});
                        alert.showAndWait();
                        if (alert.getResult() == ButtonType.YES) {
                            string2 = ReginalFactorController.this.model.importAndCheckData(file.getParent(), file.getName(), true);
                            if ("sucess".equals(string2)) {
                                JavaFXUtil.showToastMessageBox(new Stage(), string + "\u5340\u57df\u56e0\u7d20\u532f\u5165\u6210\u529f", 2000);
                            } else {
                                JavaFXUtil.showErrorMessageBox(string2);
                            }
                        }
                    } else if ("sucess".equals(string2)) {
                        JavaFXUtil.showToastMessageBox(new Stage(), string + "\u5340\u57df\u56e0\u7d20\u532f\u5165\u6210\u529f", 2000);
                    } else {
                        JavaFXUtil.showErrorMessageBox(string2);
                    }
                }
            }
        });
    }

    private void initComboBox() {
        this.cbAA45.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getAA45List()));
        this.cbAA45.getSelectionModel().selectFirst();
        this.cbAA45.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                String string = null;
                if (ReginalFactorController.this.cbAA45.getSelectionModel().getSelectedItem() != null) {
                    string = ((OptionPair)ReginalFactorController.this.cbAA45.getSelectionModel().getSelectedItem()).getValue();
                }
                ReginalFactorController.this.cbAA46.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getAA46List(string)));
                ReginalFactorController.this.cbAA46.getSelectionModel().selectFirst();
                ReginalFactorController.this.cbAA46.setDisable(false);
            }
        });
        this.cbAA46.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getDefaultList()));
        this.cbAA46.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                ReginalFactorController.this.getAA46();
            }
        });
        this.cbYear.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getDefaultList()));
        this.cbYear.getSelectionModel().selectFirst();
        this.cbYear.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                if (ReginalFactorController.this.cbAA45.getSelectionModel().getSelectedItem() != null) {
                    ReginalFactorController.this.AA45 = ((OptionPair)ReginalFactorController.this.cbAA45.getSelectionModel().getSelectedItem()).getValue();
                }
                if (ReginalFactorController.this.cbAA46.getSelectionModel().getSelectedItem() != null) {
                    ReginalFactorController.this.AA46 = ((OptionPair)ReginalFactorController.this.cbAA46.getSelectionModel().getSelectedItem()).getValue();
                }
                if (ReginalFactorController.this.cbYear.getSelectionModel().getSelectedItem() != null) {
                    ReginalFactorController.this.year = ((OptionPair)ReginalFactorController.this.cbYear.getSelectionModel().getSelectedItem()).getValue();
                }
                if (ReginalFactorController.this.cbVersion.getSelectionModel().getSelectedItem() != null) {
                    ReginalFactorController.this.version = ((OptionPair)ReginalFactorController.this.cbVersion.getSelectionModel().getSelectedItem()).getValue();
                }
                if (!(StringProcess.isEmpty(ReginalFactorController.this.AA45) || StringProcess.isEmpty(ReginalFactorController.this.AA46) || StringProcess.isEmpty(ReginalFactorController.this.year))) {
                    ReginalFactorController.this.cbBaseLandNO.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getBaselandNoList_withVersion(ReginalFactorController.this.AA45, ReginalFactorController.this.AA46, ReginalFactorController.this.year, ReginalFactorController.this.version)));
                    ReginalFactorController.this.cbVersion.setDisable(false);
                }
            }
        });
        this.cbVersion.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getFactorVersion()));
        this.cbVersion.getSelectionModel().selectFirst();
        this.cbVersion.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                String string = null;
                if (ReginalFactorController.this.cbVersion.getSelectionModel().getSelectedItem() != null) {
                    string = ((OptionPair)ReginalFactorController.this.cbVersion.getSelectionModel().getSelectedItem()).getValue();
                    ReginalFactorController.this.btQuery.setDisable(false);
                    ReginalFactorController.this.btAdd.setDisable(false);
                }
                if (!StringProcess.isEmpty(ReginalFactorController.this.year)) {
                    ReginalFactorController.this.cbMainCode.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getRegionalFactorMainCodeList(string, "")));
                    ReginalFactorController.this.cbMainCode.setDisable(false);
                    ReginalFactorController.this.cbBaseLandNO.setDisable(false);
                }
            }
        });
        this.cbVersion.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                if ("".equals(((OptionPair)ReginalFactorController.this.cbVersion.getSelectionModel().getSelectedItem()).getValue())) {
                    ReginalFactorController.this.cbMainCode.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getBaselandNoList_withVersion("", "", "", "")));
                    ReginalFactorController.this.cbBaseLandNO.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getBaselandNoList_withVersion("", "", "", "")));
                    ReginalFactorController.this.cbMainCode.getSelectionModel().selectFirst();
                    ReginalFactorController.this.cbBaseLandNO.getSelectionModel().selectFirst();
                } else {
                    String string = ((OptionPair)ReginalFactorController.this.cbAA45.getSelectionModel().getSelectedItem()).getValue();
                    String string2 = ((OptionPair)ReginalFactorController.this.cbAA46.getSelectionModel().getSelectedItem()).getValue();
                    String string3 = ((OptionPair)ReginalFactorController.this.cbYear.getSelectionModel().getSelectedItem()).getValue();
                    String string4 = ((OptionPair)ReginalFactorController.this.cbVersion.getSelectionModel().getSelectedItem()).getValue();
                    ReginalFactorController.this.cbBaseLandNO.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getBaselandNoList_withVersion(string, string2, string3, string4)));
                    ReginalFactorController.this.cbBaseLandNO.getSelectionModel().selectFirst();
                }
            }
        });
        this.cbBaseLandNO.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getDefaultList()));
        this.cbBaseLandNO.getSelectionModel().selectFirst();
        this.cbMainCode.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getDefaultList()));
        this.cbMainCode.getSelectionModel().selectFirst();
    }

    protected void getAA46() {
        String string = null;
        String string2 = null;
        if (this.cbAA45.getSelectionModel().getSelectedItem() != null) {
            string = ((OptionPair)this.cbAA45.getSelectionModel().getSelectedItem()).getValue();
        }
        if (this.cbAA46.getSelectionModel().getSelectedItem() != null) {
            string2 = ((OptionPair)this.cbAA46.getSelectionModel().getSelectedItem()).getValue();
        }
        if (!StringProcess.isEmpty(string) && !StringProcess.isEmpty(string2)) {
            this.cbYear.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getYearsListFromRegionalFactor(string, string2)));
            this.cbYear.setDisable(false);
        }
    }

    protected void createRec() {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            if (this.cbAA45.getSelectionModel().getSelectedItem() != null) {
                this.AA45 = ((OptionPair)this.cbAA45.getSelectionModel().getSelectedItem()).getValue();
            }
            if (this.cbAA46.getSelectionModel().getSelectedItem() != null) {
                this.AA46 = ((OptionPair)this.cbAA46.getSelectionModel().getSelectedItem()).getValue();
            }
            if (this.cbYear.getSelectionModel().getSelectedItem() != null) {
                this.year = ((OptionPair)this.cbYear.getSelectionModel().getSelectedItem()).getValue();
            }
            if (this.cbVersion.getSelectionModel().getSelectedItem() != null) {
                this.version = ((OptionPair)this.cbVersion.getSelectionModel().getSelectedItem()).getValue();
            }
            this.baseno = ((OptionPair)this.cbBaseLandNO.getSelectionModel().getSelectedItem()).getValue();
            this.mainCode = ((OptionPair)this.cbMainCode.getSelectionModel().getSelectedItem()).getValue();
            this.checkInput(stringBuilder);
            if (stringBuilder.length() > 0) {
                JavaFXUtil.showErrorMessageBox("\u5fc5\u8981\u6b04\u4f4d\u6709\u932f\u8aa4\u3002", stringBuilder.toString());
                return;
            }
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader fXMLLoader = new FXMLLoader(this.getClass().getResource("/view/CreateReginalFactor.fxml"));
            Scene scene = new Scene((Parent)fXMLLoader.load());
            stage.setScene(scene);
            CreateReginalFactorController createReginalFactorController = (CreateReginalFactorController)fXMLLoader.getController();
            createReginalFactorController.init(this.model, this.AA45, this.AA46, this.year, this.version, this.baseno, this.mainCode);
            stage.showAndWait();
            this.refreshList();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    private void query() {
        this.AA45 = ((OptionPair)this.cbAA45.getSelectionModel().getSelectedItem()).getValue();
        this.AA46 = ((OptionPair)this.cbAA46.getSelectionModel().getSelectedItem()).getValue();
        this.year = ((OptionPair)this.cbYear.getSelectionModel().getSelectedItem()).getValue();
        this.version = ((OptionPair)this.cbVersion.getSelectionModel().getSelectedItem()).getValue();
        this.baseno = ((OptionPair)this.cbBaseLandNO.getSelectionModel().getSelectedItem()).getValue();
        this.mainCode = ((OptionPair)this.cbMainCode.getSelectionModel().getSelectedItem()).getValue();
        StringBuilder stringBuilder = new StringBuilder();
        this.checkInput(stringBuilder);
        if (stringBuilder.length() > 0) {
            JavaFXUtil.showErrorMessageBox("\u67e5\u8a62\u6b04\u4f4d\u6709\u932f\u8aa4\u3002", stringBuilder.toString());
            return;
        }
        int n = this.model.query(this.AA45, this.AA46, this.year, this.version, this.baseno, this.mainCode).size();
        if (n <= 0) {
            JavaFXUtil.showNormalMessageBox("\u67e5\u7121\u8cc7\u6599", "");
        }
        this.tcDegree.setCellFactory(ComboBoxTableCell.forTableColumn((ObservableList)FXCollections.observableArrayList(this.model.getDegreeList())));
        this.refreshList();
    }

    private void refreshList() {
        this.tbList.getItems().clear();
        if (this.model.getListData() != null) {
            this.tbList.getItems().addAll(this.model.getListData());
        }
        this.tbList.refresh();
    }

    private void checkInput(StringBuilder stringBuilder) {
        stringBuilder.setLength(0);
        if (!BaseLandVerifyUtil.checkCity(this.AA45, false)) {
            stringBuilder.append("\u8acb\u9078\u64c7 [\u7e23\u5e02] ").append("\n");
        }
        if (!BaseLandVerifyUtil.checkDist(this.AA46, false)) {
            stringBuilder.append("\u8acb\u9078\u64c7 [\u884c\u653f\u5340] ").append("\n");
        }
        if (!BaseLandVerifyUtil.checkYear(this.year, false)) {
            stringBuilder.append("\u8acb\u9078\u64c7 [\u5e74\u671f] ").append("\n");
        }
        if (!BaseLandVerifyUtil.checkFactorVersion(this.version, false)) {
            stringBuilder.append("\u8acb\u9078\u64c7 [\u7248\u672c\u985e\u578b] ").append("\n");
        }
        if (!BaseLandVerifyUtil.checkFactorVersion(this.version, true)) {
            stringBuilder.append("\u57fa\u6e96\u5730\u7de8\u865f\u8f38\u5165\u932f\u8aa4").append("\n");
        }
        if (!BaseLandVerifyUtil.checkFactorVersion(this.version, true)) {
            stringBuilder.append("\u4e3b\u8981\u9805\u76ee\u8f38\u5165\u932f\u8aa4").append("\n");
        }
    }

    private String getSetName(String string) {
        String string2 = "";
        if (!StringProcess.isEmpty(string)) {
            string2 = string.substring(3, 6) + "\u5e74" + CodeList.decodeCity(string.substring(0, 1)) + CodeList.decodeDist(string.substring(0, 1), string.substring(1, 3));
            if (!"0000".equals(string.subSequence(6, 10))) {
                string2 = string2 + "\u7248\u672c:" + string.subSequence(6, 10);
            }
        }
        return string2;
    }

    private void setStandard(NVO_BASELAND_REGIONAL_FACTOR nVO_BASELAND_REGIONAL_FACTOR) throws Exception {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fXMLLoader = new FXMLLoader(this.getClass().getResource("/view/SystemReginalFactor_edit.fxml"));
        Scene scene = new Scene((Parent)fXMLLoader.load());
        stage.setScene(scene);
        SystemReginalFactor_editController systemReginalFactor_editController = (SystemReginalFactor_editController)fXMLLoader.getController();
        systemReginalFactor_editController.init(nVO_BASELAND_REGIONAL_FACTOR, this.model, this.selfDialog);
        stage.showAndWait();
    }

    @Override
    public void refresh() {
    }
}

