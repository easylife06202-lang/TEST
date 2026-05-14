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
 *  javafx.scene.control.TableCell
 *  javafx.scene.control.TableColumn
 *  javafx.scene.control.TablePosition
 *  javafx.scene.control.TableView
 *  javafx.scene.control.cell.PropertyValueFactory
 *  javafx.scene.control.cell.TextFieldTableCell
 *  javafx.stage.Stage
 *  javafx.util.Callback
 *  javafx.util.StringConverter
 *  javafx.util.converter.IntegerStringConverter
 */
package com.wfusion.baseland.system;

import com.wfusion.baseland.SQLiteDataProviderModel;
import com.wfusion.baseland.basic.Controller;
import com.wfusion.baseland.basic.IBaseLandDialog;
import com.wfusion.baseland.system.InstruStdPriceModel;
import com.wfusion.fx.util.JavaFXUtil;
import com.wfusion.util.OptionPair;
import com.wfusion.util.StringProcess;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_INSTRU_STD_PRICE;

public class InstruStdPriceController
extends Controller {
    @FXML
    private ComboBox<OptionPair> instru_code;
    @FXML
    private ComboBox<OptionPair> cbAA45;
    @FXML
    private TableView<NVO_BASELAND_INSTRU_STD_PRICE> tbList;
    @FXML
    private TableColumn<NVO_BASELAND_INSTRU_STD_PRICE, Integer> floor_start;
    @FXML
    private TableColumn<NVO_BASELAND_INSTRU_STD_PRICE, Integer> floor_end;
    @FXML
    private TableColumn<NVO_BASELAND_INSTRU_STD_PRICE, Integer> price;
    @FXML
    private TableColumn<NVO_BASELAND_INSTRU_STD_PRICE, String> tcFunc;
    @FXML
    private Button btSave;
    @FXML
    private Button btExit;
    @FXML
    private Button btAdd;
    InstruStdPriceModel model = new InstruStdPriceModel();

    @Override
    public void init(IBaseLandDialog iBaseLandDialog) {
        super.init(iBaseLandDialog);
        this.tbList.setEditable(true);
        this.cbAA45.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getAA45List()));
        this.cbAA45.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                InstruStdPriceController.this.instru_code.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getInstruCodeList()));
            }
        });
        this.instru_code.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getDefaultList()));
        this.instru_code.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                if (InstruStdPriceController.this.cbAA45.getSelectionModel().getSelectedItem() != null) {
                    InstruStdPriceController.this.model.AA45 = ((OptionPair)InstruStdPriceController.this.cbAA45.getSelectionModel().getSelectedItem()).getValue();
                }
                if (InstruStdPriceController.this.instru_code.getSelectionModel().getSelectedItem() != null) {
                    InstruStdPriceController.this.model.instru_code = ((OptionPair)InstruStdPriceController.this.instru_code.getSelectionModel().getSelectedItem()).getValue();
                }
                if (!StringProcess.isEmpty(InstruStdPriceController.this.model.AA45) && !StringProcess.isEmpty(InstruStdPriceController.this.model.instru_code)) {
                    InstruStdPriceController.this.model.query();
                    InstruStdPriceController.this.refreshList();
                }
            }
        });
        this.floor_start.setCellValueFactory((Callback)new PropertyValueFactory("floor_start"));
        this.floor_start.setCellFactory(TextFieldTableCell.forTableColumn((StringConverter)new IntegerStringConverter()));
        this.floor_start.setOnEditCommit(cellEditEvent -> {
            TablePosition tablePosition = cellEditEvent.getTablePosition();
            int n = tablePosition.getRow();
            NVO_BASELAND_INSTRU_STD_PRICE nVO_BASELAND_INSTRU_STD_PRICE = (NVO_BASELAND_INSTRU_STD_PRICE)cellEditEvent.getTableView().getItems().get(n);
            nVO_BASELAND_INSTRU_STD_PRICE.setFloor_start((Integer)cellEditEvent.getNewValue());
        });
        this.floor_end.setCellValueFactory((Callback)new PropertyValueFactory("floor_end"));
        this.floor_end.setCellFactory(TextFieldTableCell.forTableColumn((StringConverter)new IntegerStringConverter()));
        this.floor_end.setOnEditCommit(cellEditEvent -> {
            TablePosition tablePosition = cellEditEvent.getTablePosition();
            int n = tablePosition.getRow();
            NVO_BASELAND_INSTRU_STD_PRICE nVO_BASELAND_INSTRU_STD_PRICE = (NVO_BASELAND_INSTRU_STD_PRICE)cellEditEvent.getTableView().getItems().get(n);
            nVO_BASELAND_INSTRU_STD_PRICE.setFloor_end((Integer)cellEditEvent.getNewValue());
        });
        this.price.setCellValueFactory((Callback)new PropertyValueFactory("uniprice"));
        this.price.setCellFactory(TextFieldTableCell.forTableColumn((StringConverter)new IntegerStringConverter()));
        this.price.setOnEditCommit(cellEditEvent -> {
            TablePosition tablePosition = cellEditEvent.getTablePosition();
            int n = tablePosition.getRow();
            NVO_BASELAND_INSTRU_STD_PRICE nVO_BASELAND_INSTRU_STD_PRICE = (NVO_BASELAND_INSTRU_STD_PRICE)cellEditEvent.getTableView().getItems().get(n);
            nVO_BASELAND_INSTRU_STD_PRICE.setUniprice((Integer)cellEditEvent.getNewValue());
        });
        this.btExit.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                Node node = (Node)actionEvent.getSource();
                Stage stage = (Stage)node.getScene().getWindow();
                stage.close();
            }
        });
        this.btAdd.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                NVO_BASELAND_INSTRU_STD_PRICE nVO_BASELAND_INSTRU_STD_PRICE = new NVO_BASELAND_INSTRU_STD_PRICE();
                nVO_BASELAND_INSTRU_STD_PRICE.setCity(InstruStdPriceController.this.model.AA45);
                nVO_BASELAND_INSTRU_STD_PRICE.setInstru_code(InstruStdPriceController.this.model.instru_code);
                nVO_BASELAND_INSTRU_STD_PRICE.setFloor_start(0);
                nVO_BASELAND_INSTRU_STD_PRICE.setFloor_end(0);
                nVO_BASELAND_INSTRU_STD_PRICE.setUniprice(0);
                InstruStdPriceController.this.model.getListData().add(nVO_BASELAND_INSTRU_STD_PRICE);
                InstruStdPriceController.this.refreshList();
            }
        });
        Callback<TableColumn<NVO_BASELAND_INSTRU_STD_PRICE, String>, TableCell<NVO_BASELAND_INSTRU_STD_PRICE, String>> callback = new Callback<TableColumn<NVO_BASELAND_INSTRU_STD_PRICE, String>, TableCell<NVO_BASELAND_INSTRU_STD_PRICE, String>>(){

            public TableCell<NVO_BASELAND_INSTRU_STD_PRICE, String> call(TableColumn<NVO_BASELAND_INSTRU_STD_PRICE, String> tableColumn) {
                TableCell<NVO_BASELAND_INSTRU_STD_PRICE, String> tableCell = new TableCell<NVO_BASELAND_INSTRU_STD_PRICE, String>(){
                    final Button btn = new Button("\u522a\u9664");

                    public void updateItem(String string, boolean bl) {
                        super.updateItem((Object)string, bl);
                        if (bl) {
                            this.setGraphic(null);
                        } else {
                            this.btn.setOnAction(actionEvent -> {
                                NVO_BASELAND_INSTRU_STD_PRICE nVO_BASELAND_INSTRU_STD_PRICE = (NVO_BASELAND_INSTRU_STD_PRICE)this.getTableView().getItems().get(this.getIndex());
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "\u662f\u5426\u78ba\u5b9a\u522a\u9664 [ " + nVO_BASELAND_INSTRU_STD_PRICE.getFloor_start() + " \u5230 " + nVO_BASELAND_INSTRU_STD_PRICE.getFloor_end() + "\u6a13 ] \u4e4b\u8cc7\u6599\uff1f", new ButtonType[]{ButtonType.YES, ButtonType.NO, ButtonType.CANCEL});
                                alert.showAndWait();
                                if (alert.getResult() == ButtonType.YES) {
                                    InstruStdPriceController.this.model.delete(nVO_BASELAND_INSTRU_STD_PRICE);
                                    InstruStdPriceController.this.refreshList();
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
        this.tcFunc.setCellFactory((Callback)callback);
        this.btSave.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                InstruStdPriceController.this.model.save();
                InstruStdPriceController.this.refreshList();
                JavaFXUtil.showToastMessageBox(new Stage(), "\u5132\u5b58\u6210\u529f", 1500);
            }
        });
    }

    private void refreshList() {
        this.tbList.getItems().clear();
        if (this.model.getListData() != null) {
            this.tbList.getItems().addAll(this.model.getListData());
        }
        this.tbList.refresh();
    }

    @Override
    public void refresh() {
    }
}

