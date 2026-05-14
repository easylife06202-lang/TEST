/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.beans.value.ChangeListener
 *  javafx.beans.value.ObservableValue
 *  javafx.collections.FXCollections
 *  javafx.concurrent.Task
 *  javafx.concurrent.WorkerStateEvent
 *  javafx.event.ActionEvent
 *  javafx.event.EventHandler
 *  javafx.fxml.FXML
 *  javafx.scene.Node
 *  javafx.scene.control.Button
 *  javafx.scene.control.CheckBox
 *  javafx.scene.control.ComboBox
 *  javafx.scene.control.ProgressIndicator
 *  javafx.stage.Stage
 */
package com.wfusion.baseland.system;

import com.wfusion.baseland.SQLiteDataProviderModel;
import com.wfusion.baseland.basic.Controller;
import com.wfusion.baseland.basic.IBaseLandDialog;
import com.wfusion.baseland.system.CarryOverModel;
import com.wfusion.fx.util.JavaFXUtil;
import com.wfusion.util.OptionPair;
import com.wfusion.util.StringProcess;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;

public class SystemCarryOverController
extends Controller {
    @FXML
    private ComboBox<String> exportYear;
    @FXML
    private ComboBox<OptionPair> cbAA45;
    @FXML
    private Button btSave;
    @FXML
    private Button btExit;
    @FXML
    private ComboBox<String> importYear;
    @FXML
    private CheckBox cbOverwrite;
    @FXML
    private CheckBox cbOverwrite1;
    CarryOverModel model = new CarryOverModel();
    String AA45 = "";
    String msg = "";
    @FXML
    private ProgressIndicator progress;

    @Override
    public void init(IBaseLandDialog iBaseLandDialog) {
        super.init(iBaseLandDialog);
        this.cbAA45.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getAA45List()));
        this.cbAA45.getSelectionModel().selectFirst();
        this.cbAA45.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                if (SystemCarryOverController.this.cbAA45.getSelectionModel().getSelectedItem() != null) {
                    SystemCarryOverController.this.AA45 = ((OptionPair)SystemCarryOverController.this.cbAA45.getSelectionModel().getSelectedItem()).getValue();
                }
                if (!StringProcess.isEmpty(SystemCarryOverController.this.AA45)) {
                    SystemCarryOverController.this.exportYear.setItems(FXCollections.observableArrayList(SystemCarryOverController.this.model.getExistYears()));
                    SystemCarryOverController.this.exportYear.setDisable(false);
                }
            }
        });
        this.exportYear.setItems(FXCollections.observableArrayList(new ArrayList()));
        this.exportYear.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<String>(){

            public void changed(ObservableValue<? extends String> observableValue, String string, String string2) {
                if (!StringProcess.isEmpty(string2)) {
                    int n = StringProcess.parserInt(string2);
                    ArrayList<String> arrayList = new ArrayList<String>();
                    arrayList.add(String.valueOf(n + 1));
                    arrayList.add(String.valueOf(n + 2));
                    arrayList.add(String.valueOf(n + 3));
                    arrayList.add(String.valueOf(n + 4));
                    SystemCarryOverController.this.importYear.setItems(FXCollections.observableArrayList(arrayList));
                    SystemCarryOverController.this.importYear.setDisable(false);
                }
            }
        });
        this.importYear.setItems(FXCollections.observableArrayList(new ArrayList()));
        this.btExit.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                Node node = (Node)actionEvent.getSource();
                Stage stage = (Stage)node.getScene().getWindow();
                stage.close();
            }
        });
        this.btSave.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                Task<Void> task = new Task<Void>(){

                    public Void call() throws InterruptedException {
                        String string = null;
                        String string2 = null;
                        if (SystemCarryOverController.this.exportYear.getSelectionModel().getSelectedItem() != null) {
                            string = (String)SystemCarryOverController.this.exportYear.getSelectionModel().getSelectedItem();
                        }
                        if (SystemCarryOverController.this.importYear.getSelectionModel().getSelectedItem() != null) {
                            string2 = (String)SystemCarryOverController.this.importYear.getSelectionModel().getSelectedItem();
                        }
                        if (!(StringProcess.isEmpty(SystemCarryOverController.this.AA45) || StringProcess.isEmpty(string) || StringProcess.isEmpty(string2))) {
                            boolean bl = SystemCarryOverController.this.cbOverwrite.isSelected();
                            boolean bl2 = SystemCarryOverController.this.cbOverwrite1.isSelected();
                            SystemCarryOverController.this.msg = SystemCarryOverController.this.model.copy(SystemCarryOverController.this.AA45, string, string2, bl, bl2);
                        }
                        return null;
                    }
                };
                task.setOnSucceeded((EventHandler)new EventHandler<WorkerStateEvent>(){

                    public void handle(WorkerStateEvent workerStateEvent) {
                        SystemCarryOverController.this.getEXIT().setDisable(false);
                        SystemCarryOverController.this.btSave.setDisable(false);
                        SystemCarryOverController.this.btExit.setDisable(false);
                        SystemCarryOverController.this.progress.setVisible(false);
                        if (!StringProcess.isEmpty(SystemCarryOverController.this.msg)) {
                            if (SystemCarryOverController.this.msg.contains("\u767c\u751f\u932f\u8aa4")) {
                                JavaFXUtil.showErrorMessageBox("\u767c\u751f\u932f\u8aa4\uff0c\u8acb\u9ede\u9078 \u986f\u793a\u8a73\u7d30\u8cc7\u8a0a \u67e5\u770b\u539f\u56e0", SystemCarryOverController.this.msg);
                            } else {
                                JavaFXUtil.showNormalMessageBox("\u8f49\u5165\u6210\u529f", SystemCarryOverController.this.msg);
                            }
                        }
                    }
                });
                SystemCarryOverController.this.getEXIT().setDisable(true);
                SystemCarryOverController.this.btSave.setDisable(true);
                SystemCarryOverController.this.btExit.setDisable(true);
                SystemCarryOverController.this.progress.setVisible(true);
                Thread thread = new Thread((Runnable)task);
                thread.start();
            }
        });
        this.cbOverwrite.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                SystemCarryOverController.this.cbOverwrite1.setSelected(false);
            }
        });
        this.cbOverwrite1.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                SystemCarryOverController.this.cbOverwrite.setSelected(false);
            }
        });
    }

    @Override
    public void refresh() {
    }
}

