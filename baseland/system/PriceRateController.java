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
 *  javafx.scene.control.Alert
 *  javafx.scene.control.Alert$AlertType
 *  javafx.scene.control.Button
 *  javafx.scene.control.ButtonType
 *  javafx.scene.control.ComboBox
 *  javafx.scene.control.TableCell
 *  javafx.scene.control.TableColumn
 *  javafx.scene.control.TableColumn$SortType
 *  javafx.scene.control.TableView
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
 *  javafx.util.converter.DoubleStringConverter
 */
package com.wfusion.baseland.system;

import com.wfusion.baseland.SQLiteDataProviderModel;
import com.wfusion.baseland.basic.Controller;
import com.wfusion.baseland.basic.IBaseLandDialog;
import com.wfusion.baseland.system.CreateSystemPriceRateController;
import com.wfusion.baseland.system.PriceRateModel;
import com.wfusion.fx.util.JavaFXUtil;
import com.wfusion.util.BigDecimalUtil;
import com.wfusion.util.OptionPair;
import com.wfusion.util.StringProcess;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_PRICERATE;
import moiland.baseland.verify.BaseLandVerifyUtil;

public class PriceRateController
extends Controller {
    @FXML
    private ComboBox<OptionPair> cbRateType;
    @FXML
    private ComboBox<OptionPair> cbYear;
    @FXML
    private ComboBox<OptionPair> cbAA45;
    @FXML
    private ComboBox<OptionPair> cbAA46;
    @FXML
    private Button btQuery;
    @FXML
    private Button btAdd;
    @FXML
    private Button btExit;
    @FXML
    private Button btSave;
    @FXML
    private Button btImport;
    @FXML
    private TableView<NVO_BASELAND_PRICERATE> tbList;
    @FXML
    private TableColumn<NVO_BASELAND_PRICERATE, String> tcYM;
    @FXML
    private TableColumn<NVO_BASELAND_PRICERATE, Double> tcIndexRate;
    @FXML
    private TableColumn<NVO_BASELAND_PRICERATE, String> tcFunc;
    PriceRateModel model = new PriceRateModel();
    DoubleStringConverter doubleConvert = new DoubleStringConverter(){

        public Double fromString(String string) {
            try {
                return super.fromString(string);
            }
            catch (NumberFormatException numberFormatException) {
                return null;
            }
        }
    };

    @Override
    public void init(IBaseLandDialog iBaseLandDialog) {
        super.init(iBaseLandDialog);
        this.setTitle("\u50f9\u683c\u65e5\u671f\u8abf\u6574\u6307\u6578");
        this.tbList.setEditable(true);
        this.tcYM.setCellValueFactory((Callback)new PropertyValueFactory("nameOfYm"));
        this.tcIndexRate.setCellValueFactory((Callback)new PropertyValueFactory("index_rate"));
        this.tcIndexRate.setCellFactory(TextFieldTableCell.forTableColumn((StringConverter)this.doubleConvert));
        this.tcIndexRate.setOnEditCommit(cellEditEvent -> {
            NVO_BASELAND_PRICERATE nVO_BASELAND_PRICERATE = (NVO_BASELAND_PRICERATE)cellEditEvent.getTableView().getItems().get(cellEditEvent.getTablePosition().getRow());
            if (!"Base".equals(nVO_BASELAND_PRICERATE.getDataType())) {
                Double d = (Double)cellEditEvent.getNewValue();
                if (d != null && Double.toString(d).matches("^[-]?[0-9]{1,3}+(.[0-9]{0,2})?$")) {
                    nVO_BASELAND_PRICERATE.setIndex_rate(BigDecimalUtil.round(d, 2));
                } else {
                    JavaFXUtil.showErrorMessageBox("\u683c\u5f0f\u932f\u8aa4\uff0c\u8acb\u4fee\u6b63\u8f38\u5165\u5167\u5bb9", "\u8a72\u6b04\u4f4d\u61c9\u70ba\u6578\u5b57\uff0c\u6700\u5927\u6574\u6578 5 \u4f4d\uff0c\u5c0f\u6578 2 \u4f4d");
                }
            } else {
                JavaFXUtil.showErrorMessageBox("\u6bd4\u6e96\u6708\u4efd\u6307\u6578\u4e0d\u53ef\u66f4\u6539");
            }
            this.refreshList();
        });
        Callback<TableColumn<NVO_BASELAND_PRICERATE, String>, TableCell<NVO_BASELAND_PRICERATE, String>> callback = new Callback<TableColumn<NVO_BASELAND_PRICERATE, String>, TableCell<NVO_BASELAND_PRICERATE, String>>(){

            public TableCell<NVO_BASELAND_PRICERATE, String> call(TableColumn<NVO_BASELAND_PRICERATE, String> tableColumn) {
                TableCell<NVO_BASELAND_PRICERATE, String> tableCell = new TableCell<NVO_BASELAND_PRICERATE, String>(){
                    final Button btn = new Button("\u522a\u9664");

                    public void updateItem(String string, boolean bl) {
                        super.updateItem((Object)string, bl);
                        if (bl) {
                            this.setGraphic(null);
                        } else {
                            NVO_BASELAND_PRICERATE nVO_BASELAND_PRICERATE = (NVO_BASELAND_PRICERATE)this.getTableView().getItems().get(this.getIndex());
                            if ("Additional".equals(nVO_BASELAND_PRICERATE.getDataType())) {
                                this.btn.setOnAction(actionEvent -> {
                                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "\u662f\u5426\u78ba\u5b9a\u522a\u9664\u6b64\u8cc7\u6599\uff1f", new ButtonType[]{ButtonType.YES, ButtonType.NO, ButtonType.CANCEL});
                                    alert.showAndWait();
                                    if (alert.getResult() == ButtonType.YES) {
                                        PriceRateController.this.model.delete(nVO_BASELAND_PRICERATE);
                                        PriceRateController.this.refreshList();
                                    }
                                });
                                this.setGraphic((Node)this.btn);
                            } else {
                                this.setGraphic(null);
                            }
                        }
                        this.setText(null);
                    }
                };
                return tableCell;
            }
        };
        this.tcFunc.setCellFactory((Callback)callback);
        this.initComboBox();
        this.btExit.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                Node node = (Node)actionEvent.getSource();
                Stage stage = (Stage)node.getScene().getWindow();
                stage.close();
            }
        });
        this.btQuery.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                PriceRateController.this.query();
            }
        });
        this.btAdd.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                PriceRateController.this.createPriceRate();
            }
        });
        this.btSave.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                PriceRateController.this.model.getListData().clear();
                PriceRateController.this.model.getListData().addAll((Collection<NVO_BASELAND_PRICERATE>)PriceRateController.this.tbList.getItems());
                if (PriceRateController.this.model.save()) {
                    JavaFXUtil.showToastMessageBox(PriceRateController.this.selfDialog.getStage(), "\u5132\u5b58\u6210\u529f", 500);
                }
            }
        });
        this.btImport.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                Node node = (Node)actionEvent.getSource();
                Stage stage = (Stage)node.getScene().getWindow();
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().addAll((Object[])new FileChooser.ExtensionFilter[]{new FileChooser.ExtensionFilter("Exchange Files", new String[]{"*.txt"})});
                File file = fileChooser.showOpenDialog((Window)stage);
                if (file != null) {
                    String string = "";
                    string = PriceRateController.this.model.importRate(file);
                    if (!StringProcess.isEmpty(string)) {
                        JavaFXUtil.showErrorMessageBox(string);
                    } else {
                        JavaFXUtil.showToastMessageBox(new Stage(), "\u532f\u5165\u6210\u529f", 1500);
                    }
                }
            }
        });
    }

    private void createPriceRate() {
        this.query();
        String string = "";
        String string2 = "";
        String string3 = "";
        String string4 = "";
        try {
            StringBuilder stringBuilder = new StringBuilder();
            if (this.cbAA45.getSelectionModel().getSelectedItem() != null) {
                string = ((OptionPair)this.cbAA45.getSelectionModel().getSelectedItem()).getValue();
            }
            if (this.cbAA46.getSelectionModel().getSelectedItem() != null) {
                string2 = ((OptionPair)this.cbAA46.getSelectionModel().getSelectedItem()).getValue();
            }
            if (this.cbRateType.getSelectionModel().getSelectedItem() != null) {
                string3 = ((OptionPair)this.cbRateType.getSelectionModel().getSelectedItem()).getValue();
            }
            if (this.cbYear.getSelectionModel().getSelectedItem() != null) {
                string4 = ((OptionPair)this.cbYear.getSelectionModel().getSelectedItem()).getValue();
            }
            this.checkInput(stringBuilder, string, string2, string3, string4);
            if (stringBuilder.length() > 0) {
                JavaFXUtil.showErrorMessageBox("\u5fc5\u8981\u6b04\u4f4d\u6709\u932f\u8aa4\u3002", stringBuilder.toString());
                return;
            }
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader fXMLLoader = new FXMLLoader(this.getClass().getResource("/view/CreateSystemPriceRate.fxml"));
            Scene scene = new Scene((Parent)fXMLLoader.load());
            stage.setScene(scene);
            CreateSystemPriceRateController createSystemPriceRateController = (CreateSystemPriceRateController)fXMLLoader.getController();
            createSystemPriceRateController.init(this.model, string, string2, string4, string3);
            stage.showAndWait();
            this.query();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    private void checkInput(StringBuilder stringBuilder, String string, String string2, String string3, String string4) {
        stringBuilder.setLength(0);
        if (!BaseLandVerifyUtil.checkCity(string, false)) {
            stringBuilder.append("\u8acb\u9078\u64c7 [\u7e23\u5e02] ").append("\n");
        }
        if (!BaseLandVerifyUtil.checkPriceRateType(string3, false)) {
            stringBuilder.append("\u8acb\u9078\u64c7 [\u6307\u6578\u985e\u578b] ").append("\n");
        }
        if (StringProcess.isEmpty(string2)) {
            stringBuilder.append("\u8acb\u9078\u64c7 [\u884c\u653f\u5340] ").append("\n");
        }
        if (!BaseLandVerifyUtil.checkYear(string4, false)) {
            stringBuilder.append("\u8acb\u9078\u64c7 [\u5e74\u5ea6] ").append("\n");
        }
    }

    protected void query() {
        if (this.cbAA45.getSelectionModel().getSelectedItem() != null) {
            this.model.setCity(((OptionPair)this.cbAA45.getSelectionModel().getSelectedItem()).getValue());
        }
        if (this.cbAA46.getSelectionModel().getSelectedItem() != null) {
            this.model.setDist(((OptionPair)this.cbAA46.getSelectionModel().getSelectedItem()).getValue());
        }
        if (this.cbRateType.getSelectionModel().getSelectedItem() != null) {
            this.model.setRateType(((OptionPair)this.cbRateType.getSelectionModel().getSelectedItem()).getValue());
        }
        if (this.cbYear.getSelectionModel().getSelectedItem() != null) {
            this.model.setYear(((OptionPair)this.cbYear.getSelectionModel().getSelectedItem()).getValue());
        }
        StringBuilder stringBuilder = new StringBuilder();
        this.model.checkInput(stringBuilder);
        if (stringBuilder.length() > 0) {
            JavaFXUtil.showErrorMessageBox("\u67e5\u8a62\u6b04\u4f4d\u6709\u932f\u8aa4\u3002", stringBuilder.toString());
            return;
        }
        this.model.query();
        this.refreshList();
    }

    private void initComboBox() {
        this.cbAA45.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getAA45List()));
        this.cbAA45.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                String string = null;
                String string2 = null;
                if (PriceRateController.this.cbAA45.getSelectionModel().getSelectedItem() != null) {
                    string = ((OptionPair)PriceRateController.this.cbAA45.getSelectionModel().getSelectedItem()).getValue();
                    PriceRateController.this.model.setCity(string);
                    PriceRateController.this.cbRateType.setDisable(false);
                }
                if (PriceRateController.this.cbRateType.getSelectionModel().getSelectedItem() != null) {
                    string2 = ((OptionPair)PriceRateController.this.cbRateType.getSelectionModel().getSelectedItem()).getValue();
                    PriceRateController.this.model.setRateType(string2);
                }
                if (!StringProcess.isEmpty(string) && !StringProcess.isEmpty(string2)) {
                    PriceRateController.this.cbAA46.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getPriceRateDistCode(string, string2)));
                    PriceRateController.this.cbAA46.setDisable(false);
                } else {
                    PriceRateController.this.cbAA46.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getDefaultList()));
                }
            }
        });
        this.cbRateType.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getPriceRateTypeCode()));
        this.cbRateType.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                String string = null;
                String string2 = null;
                if (PriceRateController.this.cbAA45.getSelectionModel().getSelectedItem() != null) {
                    string = ((OptionPair)PriceRateController.this.cbAA45.getSelectionModel().getSelectedItem()).getValue();
                    PriceRateController.this.model.setCity(string);
                }
                if (PriceRateController.this.cbRateType.getSelectionModel().getSelectedItem() != null) {
                    string2 = ((OptionPair)PriceRateController.this.cbRateType.getSelectionModel().getSelectedItem()).getValue();
                    PriceRateController.this.model.setRateType(string2);
                }
                if (!StringProcess.isEmpty(string) && !StringProcess.isEmpty(string2)) {
                    PriceRateController.this.cbAA46.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getPriceRateDistCode(string, string2)));
                    PriceRateController.this.cbAA46.setDisable(false);
                } else {
                    PriceRateController.this.cbAA46.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getDefaultList()));
                }
            }
        });
        this.cbAA46.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getDefaultList()));
        this.cbAA46.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                String string = null;
                String string2 = null;
                String string3 = null;
                if (PriceRateController.this.cbAA45.getSelectionModel().getSelectedItem() != null) {
                    string = ((OptionPair)PriceRateController.this.cbAA45.getSelectionModel().getSelectedItem()).getValue();
                    PriceRateController.this.model.setCity(string);
                }
                if (PriceRateController.this.cbRateType.getSelectionModel().getSelectedItem() != null) {
                    string3 = ((OptionPair)PriceRateController.this.cbRateType.getSelectionModel().getSelectedItem()).getValue();
                    PriceRateController.this.model.setRateType(string3);
                }
                if (PriceRateController.this.cbAA46.getSelectionModel().getSelectedItem() != null) {
                    string2 = ((OptionPair)PriceRateController.this.cbAA46.getSelectionModel().getSelectedItem()).getValue();
                    PriceRateController.this.model.setDist(string2);
                }
                if (!(StringProcess.isEmpty(string) || StringProcess.isEmpty(string3) || StringProcess.isEmpty(string2))) {
                    PriceRateController.this.cbYear.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getYearsFromPriceRateOption(string, string2, string3)));
                    PriceRateController.this.cbYear.getSelectionModel().selectFirst();
                    PriceRateController.this.cbYear.setDisable(false);
                }
            }
        });
        this.cbYear.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getDefaultList()));
        this.cbYear.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                String string = null;
                if (optionPair2 != null && !StringProcess.isEmpty(optionPair2.getValue())) {
                    string = optionPair2.getValue();
                    PriceRateController.this.model.setYear(string);
                    PriceRateController.this.btQuery.setDisable(false);
                    PriceRateController.this.btAdd.setDisable(false);
                }
            }
        });
    }

    private void refreshList() {
        TableColumn tableColumn = null;
        TableColumn.SortType sortType = null;
        if (this.tbList.getSortOrder().size() > 0) {
            tableColumn = (TableColumn)this.tbList.getSortOrder().get(0);
            sortType = tableColumn.getSortType();
        } else {
            tableColumn = (TableColumn)this.tbList.getColumns().get(0);
            tableColumn.setSortType(TableColumn.SortType.ASCENDING);
            sortType = tableColumn.getSortType();
        }
        this.tbList.getItems().clear();
        if (this.model.getListData() != null && this.model.getListData().size() > 0) {
            this.tbList.getItems().addAll(this.model.getListData());
            if (tableColumn != null) {
                this.tbList.getSortOrder().add((Object)tableColumn);
                tableColumn.setSortType(sortType);
                tableColumn.setSortable(true);
            }
        }
        this.tbList.refresh();
    }

    @Override
    public void refresh() {
    }
}

