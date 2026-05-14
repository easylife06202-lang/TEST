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
 *  javafx.scene.control.Button
 *  javafx.scene.control.ComboBox
 *  javafx.scene.control.TextField
 *  javafx.stage.Stage
 */
package com.wfusion.baseland.system;

import com.wfusion.baseland.SQLiteDataProviderModel;
import com.wfusion.baseland.system.RegionalFactorModel;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_REGIONAL_FACTOR;

public class CreateReginalFactorController {
    @FXML
    private ComboBox<OptionPair> cbMainCode;
    @FXML
    private ComboBox<OptionPair> cbItemCode;
    @FXML
    private ComboBox<OptionPair> cbDegree;
    @FXML
    private TextField tfImpact;
    @FXML
    private TextField tfDnames;
    @FXML
    private Button btOK;
    @FXML
    private Button btExit;
    RegionalFactorModel model = null;
    NVO_BASELAND_REGIONAL_FACTOR vo = new NVO_BASELAND_REGIONAL_FACTOR();
    String city;
    String dist;
    String year;
    String version;
    String baseno;
    String mainCode;

    public void init(RegionalFactorModel regionalFactorModel, String string, String string2, String string3, final String string4, String string5, String string6) {
        this.model = regionalFactorModel;
        this.city = StringProcess.NULL(string);
        this.dist = StringProcess.NULL(string2);
        this.year = StringProcess.NULL(string3);
        this.version = StringProcess.NULL(string4);
        this.baseno = StringProcess.NULL(string5);
        this.mainCode = StringProcess.NULL(string6);
        this.cbMainCode.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getRegionalFactorMainCodeList(string4, this.mainCode)));
        this.cbMainCode.getSelectionModel().selectFirst();
        this.cbMainCode.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                String string = null;
                if (CreateReginalFactorController.this.cbMainCode.getSelectionModel().getSelectedItem() != null) {
                    string = ((OptionPair)CreateReginalFactorController.this.cbMainCode.getSelectionModel().getSelectedItem()).getValue();
                }
                CreateReginalFactorController.this.cbItemCode.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getRegionalFactorItemList(string4, string)));
            }
        });
        this.cbDegree.setItems(FXCollections.observableArrayList(this.model.getDegreeList()));
        this.cbDegree.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                String string = null;
                if (optionPair2 != null) {
                    string = optionPair2.getValue();
                    int n = StringProcess.parserInt(string);
                    if (n == 2) {
                        CreateReginalFactorController.this.tfDnames.setText("\u512a,\u52a3");
                    } else if (n == 3) {
                        CreateReginalFactorController.this.tfDnames.setText("\u512a,\u666e\u901a,\u52a3");
                    } else if (n == 5) {
                        CreateReginalFactorController.this.tfDnames.setText("\u512a,\u7a0d\u512a,\u666e\u901a,\u7a0d\u52a3,\u52a3");
                    } else if (n == 7) {
                        CreateReginalFactorController.this.tfDnames.setText("\u6975\u512a,\u512a,\u7a0d\u512a,\u666e\u901a,\u7a0d\u52a3,\u52a3,\u6975\u52a3");
                    } else if (n == 9) {
                        CreateReginalFactorController.this.tfDnames.setText("\u8d85\u6975\u512a,\u6975\u512a,\u512a,\u7a0d\u512a,\u666e\u901a,\u7a0d\u52a3,\u52a3,\u6975\u52a3,\u8d85\u6975\u52a3");
                    } else {
                        CreateReginalFactorController.this.tfDnames.setText("\u8acb\u8f38\u5165\u5404\u7b49\u7d1a\u540d\u7a31\uff0c\u4e26\u4ee5\u9017\u865f\u9694\u958b\u3002");
                    }
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
        this.btOK.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                if (CreateReginalFactorController.this.checkVo()) {
                    if (CreateReginalFactorController.this.model.isExist(CreateReginalFactorController.this.vo)) {
                        Node node = (Node)actionEvent.getSource();
                        Stage stage = (Stage)node.getScene().getWindow();
                        JavaFXUtil.showToastMessageBox(stage, "\u8cc7\u6599\u5df2\u5b58\u5728", 1500);
                        return;
                    }
                    String[] stringArray = CreateReginalFactorController.this.vo.getDnames().split(",");
                    if (stringArray.length != CreateReginalFactorController.this.vo.getDegree()) {
                        JavaFXUtil.showErrorMessageBox("\u7b49\u7d1a\u540d\u7a31\u6578\u91cf\u8207\u8a2d\u5b9a\u4e4b\u7b49\u7d1a\u4e0d\u7b26\uff0c\u8acb\u91cd\u65b0\u8f38\u5165", "\u7b49\u7d1a\u540d\u7a31\u8acb\u7528\u9017\u865f(,)\u9694\u958b\uff0c\u7b49\u7d1a\u540d\u7a31\u6578\u91cf\u9700\u8207\u8a2d\u5b9a\u7b49\u7d1a\u4e00\u81f4");
                        return;
                    }
                    CreateReginalFactorController.this.model.save(CreateReginalFactorController.this.vo, CreateReginalFactorController.this.city, CreateReginalFactorController.this.dist, CreateReginalFactorController.this.year, CreateReginalFactorController.this.version, CreateReginalFactorController.this.baseno, CreateReginalFactorController.this.mainCode);
                    Node node = (Node)actionEvent.getSource();
                    Stage stage = (Stage)node.getScene().getWindow();
                    stage.close();
                } else {
                    JavaFXUtil.showErrorMessageBox("\u8acb\u78ba\u5be6\u586b\u5beb\u4e0a\u8868\u5167\u5bb9");
                }
            }
        });
    }

    protected boolean checkVo() {
        this.vo.setCity(this.city);
        this.vo.setDist(this.dist);
        this.vo.setYear(this.year);
        this.vo.setVersion(this.version);
        this.vo.setBaseno(this.baseno);
        if (this.cbItemCode.getSelectionModel().getSelectedItem() == null || StringProcess.isEmpty(((OptionPair)this.cbItemCode.getSelectionModel().getSelectedItem()).getValue())) {
            return false;
        }
        this.vo.setItem(((OptionPair)this.cbItemCode.getSelectionModel().getSelectedItem()).getValue());
        if (StringProcess.isEmpty(this.tfImpact.getText().toString())) {
            return false;
        }
        this.vo.setImpact(StringProcess.parserInt(this.tfImpact.getText().toString()));
        if (this.cbDegree.getSelectionModel().getSelectedItem() == null) {
            return false;
        }
        this.vo.setDegree(StringProcess.parserInt(((OptionPair)this.cbDegree.getSelectionModel().getSelectedItem()).getValue()));
        if (StringProcess.isEmpty(this.tfDnames.getText().toString())) {
            return false;
        }
        this.vo.setDnames(this.tfDnames.getText().toString());
        return true;
    }
}

