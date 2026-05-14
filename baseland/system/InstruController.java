/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.event.ActionEvent
 *  javafx.event.EventHandler
 *  javafx.fxml.FXML
 *  javafx.scene.Node
 *  javafx.scene.control.Button
 *  javafx.scene.control.TableColumn
 *  javafx.scene.control.TableColumn$SortType
 *  javafx.scene.control.TableView
 *  javafx.scene.control.cell.PropertyValueFactory
 *  javafx.scene.control.cell.TextFieldTableCell
 *  javafx.stage.Stage
 *  javafx.util.Callback
 *  javafx.util.StringConverter
 *  javafx.util.converter.DoubleStringConverter
 *  javafx.util.converter.IntegerStringConverter
 */
package com.wfusion.baseland.system;

import com.wfusion.baseland.basic.Controller;
import com.wfusion.baseland.basic.IBaseLandDialog;
import com.wfusion.baseland.system.InstruModel;
import com.wfusion.fx.util.JavaFXUtil;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_INSTRU;
import moiland.baseland.verify.BaseLandVerifyUtil;

public class InstruController
extends Controller {
    @FXML
    private TableView<NVO_BASELAND_INSTRU> tbList;
    @FXML
    private TableColumn<NVO_BASELAND_INSTRU, String> tcInstCode;
    @FXML
    private TableColumn<NVO_BASELAND_INSTRU, String> tcInstName;
    @FXML
    private TableColumn<NVO_BASELAND_INSTRU, Integer> tcInstYearLimit;
    @FXML
    private TableColumn<NVO_BASELAND_INSTRU, Double> tcInstResidualRate;
    @FXML
    private Button btExit;
    InstruModel model = new InstruModel();
    IntegerStringConverter intConvert = new IntegerStringConverter(){

        public Integer fromString(String string) {
            try {
                return super.fromString(string);
            }
            catch (NumberFormatException numberFormatException) {
                return null;
            }
        }
    };
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
        this.setTitle("\u5efa\u7269\u69cb\u9020\u7269\u7a2e\u985e");
        this.tbList.setEditable(true);
        this.btExit.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                Node node = (Node)actionEvent.getSource();
                Stage stage = (Stage)node.getScene().getWindow();
                stage.close();
            }
        });
        this.tcInstCode.setCellValueFactory((Callback)new PropertyValueFactory("instru_code"));
        this.tcInstName.setCellValueFactory((Callback)new PropertyValueFactory("instru_name"));
        this.tcInstYearLimit.setCellValueFactory((Callback)new PropertyValueFactory("year_limits"));
        this.tcInstYearLimit.setCellFactory(TextFieldTableCell.forTableColumn((StringConverter)this.intConvert));
        this.tcInstYearLimit.setOnEditCommit(cellEditEvent -> {
            NVO_BASELAND_INSTRU nVO_BASELAND_INSTRU = (NVO_BASELAND_INSTRU)cellEditEvent.getTableView().getItems().get(cellEditEvent.getTablePosition().getRow());
            Integer n = (Integer)cellEditEvent.getNewValue();
            if (n != null && BaseLandVerifyUtil.checkInstruYearLimitsNumber(n)) {
                nVO_BASELAND_INSTRU.setYear_limits(n);
                this.model.update(nVO_BASELAND_INSTRU);
            } else {
                JavaFXUtil.showErrorMessageBox("\u683c\u5f0f\u932f\u8aa4\uff0c\u8acb\u4fee\u6b63\u8f38\u5165\u5167\u5bb9", "\u8a72\u6b04\u4f4d\u61c9\u70ba\u6578\u5b57\uff0c\u6700\u5927\u6574\u6578 3 \u4f4d");
            }
            this.refreshList();
        });
        this.tcInstResidualRate.setCellValueFactory((Callback)new PropertyValueFactory("residual_rate"));
        this.tcInstResidualRate.setCellFactory(TextFieldTableCell.forTableColumn((StringConverter)this.doubleConvert));
        this.tcInstResidualRate.setOnEditCommit(cellEditEvent -> {
            NVO_BASELAND_INSTRU nVO_BASELAND_INSTRU = (NVO_BASELAND_INSTRU)cellEditEvent.getTableView().getItems().get(cellEditEvent.getTablePosition().getRow());
            Double d = (Double)cellEditEvent.getNewValue();
            if (d != null && BaseLandVerifyUtil.checkInstruResidualRateNumber(d)) {
                nVO_BASELAND_INSTRU.setResidual_rate((double)((int)(d * 100.0)) / 100.0);
                this.model.update(nVO_BASELAND_INSTRU);
            } else {
                JavaFXUtil.showErrorMessageBox("\u683c\u5f0f\u932f\u8aa4\uff0c\u8acb\u4fee\u6b63\u8f38\u5165\u5167\u5bb9", "\u8a72\u6b04\u4f4d\u61c9\u70ba\u6578\u5b57\uff0c\u6700\u5927\u6574\u6578 3 \u4f4d\uff0c\u5c0f\u6578 2 \u4f4d");
            }
            this.refreshList();
        });
        this.refreshList();
    }

    private void refreshList() {
        TableColumn tableColumn = null;
        TableColumn.SortType sortType = null;
        if (this.tbList.getSortOrder().size() > 0) {
            tableColumn = (TableColumn)this.tbList.getSortOrder().get(0);
            sortType = tableColumn.getSortType();
        }
        this.tbList.getItems().clear();
        this.model.query();
        if (this.model.getListData() != null) {
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

