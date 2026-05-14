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
 *  javafx.scene.control.TableView
 *  javafx.scene.control.TextField
 *  javafx.scene.control.cell.PropertyValueFactory
 *  javafx.scene.control.cell.TextFieldTableCell
 *  javafx.stage.Stage
 *  javafx.util.Callback
 */
package com.wfusion.baseland.system;

import com.wfusion.baseland.SQLiteDataProviderModel;
import com.wfusion.baseland.basic.Controller;
import com.wfusion.baseland.basic.IBaseLandDialog;
import com.wfusion.baseland.system.GlossaryModel;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_GLOSSARY;
import moiland.baseland.glossary.em.EnumGlossaryType;

public class GlossaryController
extends Controller {
    @FXML
    private ComboBox<OptionPair> cbCode1;
    @FXML
    private ComboBox<OptionPair> cbCode0;
    @FXML
    private TextField tfLiteral;
    @FXML
    private Button btQuery;
    @FXML
    private TableView<NVO_BASELAND_GLOSSARY> tbList;
    @FXML
    private TableColumn<NVO_BASELAND_GLOSSARY, String> tcCode0;
    @FXML
    private TableColumn<NVO_BASELAND_GLOSSARY, String> tcCode1;
    @FXML
    private TableColumn<NVO_BASELAND_GLOSSARY, String> tcSNO;
    @FXML
    private TableColumn<NVO_BASELAND_GLOSSARY, String> tcLiteral;
    @FXML
    private TableColumn<NVO_BASELAND_GLOSSARY, String> tcFunc;
    @FXML
    private Button btExit;
    @FXML
    private Button btAdd;
    GlossaryModel model = new GlossaryModel();

    @Override
    public void init(IBaseLandDialog iBaseLandDialog) {
        super.init(iBaseLandDialog);
        this.setTitle("\u7cfb\u7d71\u7ba1\u7406 - \u8fad\u5eab\u7dad\u8b77");
        this.tbList.setEditable(true);
        this.cbCode0.setItems(FXCollections.observableArrayList(this.model.getTypeList()));
        this.cbCode0.getSelectionModel().selectFirst();
        this.cbCode0.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                String string = null;
                if (GlossaryController.this.cbCode0.getSelectionModel().getSelectedItem() != null) {
                    string = ((OptionPair)GlossaryController.this.cbCode0.getSelectionModel().getSelectedItem()).getValue();
                }
                GlossaryController.this.cbCode1.setItems(FXCollections.observableArrayList(GlossaryController.this.model.getFieldList(string)));
                GlossaryController.this.cbCode1.getSelectionModel().selectFirst();
            }
        });
        this.cbCode1.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getDefaultList()));
        this.tcCode0.setCellValueFactory((Callback)new PropertyValueFactory("nameOfCode_0"));
        this.tcCode1.setCellValueFactory((Callback)new PropertyValueFactory("nameOfCode_1"));
        this.tcSNO.setCellValueFactory((Callback)new PropertyValueFactory("sno"));
        this.tcLiteral.setCellValueFactory((Callback)new PropertyValueFactory("literal"));
        this.tcLiteral.setCellFactory(TextFieldTableCell.forTableColumn());
        this.tcLiteral.setOnEditCommit(cellEditEvent -> {
            NVO_BASELAND_GLOSSARY nVO_BASELAND_GLOSSARY = (NVO_BASELAND_GLOSSARY)cellEditEvent.getTableView().getItems().get(cellEditEvent.getTablePosition().getRow());
            String string = (String)cellEditEvent.getNewValue();
            nVO_BASELAND_GLOSSARY.setLiteral(string);
        });
        this.tcLiteral.setOnEditCommit(cellEditEvent -> {
            NVO_BASELAND_GLOSSARY nVO_BASELAND_GLOSSARY = (NVO_BASELAND_GLOSSARY)cellEditEvent.getTableView().getItems().get(cellEditEvent.getTablePosition().getRow());
            String string = (String)cellEditEvent.getNewValue();
            if (!StringProcess.isEmpty(string)) {
                StringBuilder stringBuilder = new StringBuilder();
                if (this.checkString(nVO_BASELAND_GLOSSARY.getCode_0(), string, stringBuilder)) {
                    nVO_BASELAND_GLOSSARY.setLiteral(string);
                    this.model.update(nVO_BASELAND_GLOSSARY);
                } else if (stringBuilder.length() > 0) {
                    JavaFXUtil.showErrorMessageBox(stringBuilder.toString());
                }
            }
        });
        Callback<TableColumn<NVO_BASELAND_GLOSSARY, String>, TableCell<NVO_BASELAND_GLOSSARY, String>> callback = new Callback<TableColumn<NVO_BASELAND_GLOSSARY, String>, TableCell<NVO_BASELAND_GLOSSARY, String>>(){

            public TableCell<NVO_BASELAND_GLOSSARY, String> call(TableColumn<NVO_BASELAND_GLOSSARY, String> tableColumn) {
                TableCell<NVO_BASELAND_GLOSSARY, String> tableCell = new TableCell<NVO_BASELAND_GLOSSARY, String>(){
                    final Button btn = new Button("\u522a\u9664");

                    public void updateItem(String string, boolean bl) {
                        super.updateItem((Object)string, bl);
                        if (bl) {
                            this.setGraphic(null);
                        } else {
                            this.btn.setOnAction(actionEvent -> {
                                NVO_BASELAND_GLOSSARY nVO_BASELAND_GLOSSARY = (NVO_BASELAND_GLOSSARY)this.getTableView().getItems().get(this.getIndex());
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "\u662f\u5426\u78ba\u5b9a\u522a\u9664 \u8868\u55ae[ " + nVO_BASELAND_GLOSSARY.getNameOfCode_0() + " ] \u6b04\u4f4d[" + nVO_BASELAND_GLOSSARY.getNameOfCode_1() + "] \u4e4b\u8fad\u5eab\u8cc7\u6599\uff1f", new ButtonType[]{ButtonType.YES, ButtonType.NO, ButtonType.CANCEL});
                                alert.showAndWait();
                                if (alert.getResult() == ButtonType.YES) {
                                    GlossaryController.this.model.delete(nVO_BASELAND_GLOSSARY);
                                    GlossaryController.this.refreshList();
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
        this.btQuery.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                if (GlossaryController.this.cbCode0.getSelectionModel().getSelectedItem() != null) {
                    GlossaryController.this.model.setCode0(StringProcess.NULL(((OptionPair)GlossaryController.this.cbCode0.getSelectionModel().getSelectedItem()).getValue()));
                }
                if (GlossaryController.this.cbCode1.getSelectionModel().getSelectedItem() != null) {
                    GlossaryController.this.model.setCode1(StringProcess.NULL(((OptionPair)GlossaryController.this.cbCode1.getSelectionModel().getSelectedItem()).getValue()));
                }
                GlossaryController.this.model.setLiteral(StringProcess.NULL(GlossaryController.this.tfLiteral.getText().toString()));
                GlossaryController.this.refreshList();
            }
        });
        this.btAdd.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                if (GlossaryController.this.cbCode0.getValue() != null && !StringProcess.isEmpty(((OptionPair)GlossaryController.this.cbCode0.getValue()).getValue()) && GlossaryController.this.cbCode1 != null && !StringProcess.isEmpty(((OptionPair)GlossaryController.this.cbCode1.getValue()).getValue())) {
                    StringBuilder stringBuilder = new StringBuilder();
                    if (GlossaryController.this.checkString(((OptionPair)GlossaryController.this.cbCode0.getValue()).getValue(), GlossaryController.this.tfLiteral.getText(), stringBuilder)) {
                        GlossaryController.this.model.glossaryAdd(((OptionPair)GlossaryController.this.cbCode0.getValue()).getValue(), ((OptionPair)GlossaryController.this.cbCode1.getValue()).getValue(), GlossaryController.this.tfLiteral.getText());
                        GlossaryController.this.refreshList();
                    } else if (stringBuilder.length() > 0) {
                        JavaFXUtil.showErrorMessageBox(stringBuilder.toString());
                    }
                } else {
                    JavaFXUtil.showErrorMessageBox("\u8acb\u5148\u9078\u64c7\u8868\u55ae\u8207\u6b04\u4f4d");
                }
            }
        });
        this.btExit.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                Node node = (Node)actionEvent.getSource();
                Stage stage = (Stage)node.getScene().getWindow();
                stage.close();
            }
        });
        this.refreshList();
    }

    protected boolean checkString(String string, String string2, StringBuilder stringBuilder) {
        boolean bl = true;
        if (EnumGlossaryType.MAIN.getCode().equals(string)) {
            if (string2.length() > 1000) {
                bl = false;
                stringBuilder.append(EnumGlossaryType.MAIN.getDescription() + "\u5099\u8a3b\u4e0d\u53ef\u8d85\u904e1000\u5b57");
            }
        } else if (EnumGlossaryType.APPR.getCode().equals(string)) {
            if (string2.length() > 200) {
                bl = false;
                stringBuilder.append(EnumGlossaryType.APPR.getDescription() + "\u5099\u8a3b\u4e0d\u53ef\u8d85\u904e200\u5b57");
            }
        } else if (EnumGlossaryType.SELL.getCode().equals(string)) {
            if (string2.length() > 200) {
                bl = false;
                stringBuilder.append(EnumGlossaryType.SELL.getDescription() + "\u5099\u8a3b\u4e0d\u53ef\u8d85\u904e200\u5b57");
            }
        } else if (EnumGlossaryType.RENT_EXT.getCode().equals(string)) {
            if (string2.length() > 200) {
                bl = false;
                stringBuilder.append(EnumGlossaryType.RENT_EXT.getDescription() + "\u5099\u8a3b\u4e0d\u53ef\u8d85\u904e200\u5b57");
            }
        } else if (EnumGlossaryType.DEVELOP.getCode().equals(string) && string2.length() > 35) {
            bl = false;
            stringBuilder.append(EnumGlossaryType.DEVELOP.getDescription() + "\u5099\u8a3b\u4e0d\u53ef\u8d85\u904e35\u5b57");
        }
        return bl;
    }

    private void refreshList() {
        this.tbList.getItems().clear();
        this.model.query();
        if (this.model.getListData() != null) {
            this.tbList.getItems().addAll(this.model.getListData());
        }
        this.tbList.refresh();
    }

    @Override
    public void refresh() {
    }
}

