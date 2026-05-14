/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.event.ActionEvent
 *  javafx.event.EventHandler
 *  javafx.fxml.FXML
 *  javafx.scene.Node
 *  javafx.scene.control.Alert
 *  javafx.scene.control.Alert$AlertType
 *  javafx.scene.control.Button
 *  javafx.scene.control.ButtonType
 *  javafx.scene.control.Label
 *  javafx.scene.control.TableCell
 *  javafx.scene.control.TableColumn
 *  javafx.scene.control.TableView
 *  javafx.scene.control.cell.PropertyValueFactory
 *  javafx.scene.layout.AnchorPane
 *  javafx.stage.Stage
 *  javafx.util.Callback
 */
package com.wfusion.baseland.system;

import com.wfusion.baseland.SystemDelBakBean;
import com.wfusion.baseland.basic.Controller;
import com.wfusion.baseland.basic.IBaseLandDialog;
import com.wfusion.baseland.system.SystemDeleteBakModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class SystemDeleteBakController
extends Controller {
    @FXML
    private AnchorPane hbWinTitle;
    @FXML
    private Label TITLE;
    @FXML
    private Button EXIT;
    @FXML
    private TableView<SystemDelBakBean> tbList;
    @FXML
    private TableColumn<SystemDelBakBean, String> tcFileName;
    @FXML
    private TableColumn<SystemDelBakBean, String> tcFunc;
    @FXML
    private Button btExit;
    SystemDeleteBakModel model = new SystemDeleteBakModel();

    @Override
    public void init(IBaseLandDialog iBaseLandDialog) {
        super.init(iBaseLandDialog);
        this.setTitle("\u7cfb\u7d71\u7ba1\u7406 - \u522a\u9664\u5099\u4efd\u8cc7\u6599");
        this.tcFileName.setCellValueFactory((Callback)new PropertyValueFactory("fileShow"));
        this.tcFileName.setStyle("-fx-alignment: CENTER;");
        this.tcFunc.setCellValueFactory((Callback)new PropertyValueFactory(""));
        Callback<TableColumn<SystemDelBakBean, String>, TableCell<SystemDelBakBean, String>> callback = new Callback<TableColumn<SystemDelBakBean, String>, TableCell<SystemDelBakBean, String>>(){

            public TableCell<SystemDelBakBean, String> call(TableColumn<SystemDelBakBean, String> tableColumn) {
                TableCell<SystemDelBakBean, String> tableCell = new TableCell<SystemDelBakBean, String>(){
                    final Button btn = new Button("\u522a\u9664");

                    public void updateItem(String string, boolean bl) {
                        super.updateItem((Object)string, bl);
                        if (bl) {
                            this.setGraphic(null);
                        } else {
                            this.btn.setOnAction(actionEvent -> {
                                SystemDelBakBean systemDelBakBean = (SystemDelBakBean)this.getTableView().getItems().get(this.getIndex());
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "\u662f\u5426\u78ba\u5b9a\u522a\u9664 [ " + systemDelBakBean.getFileName() + " ] \u4e4b\u8cc7\u6599\uff1f", new ButtonType[]{ButtonType.YES, ButtonType.NO, ButtonType.CANCEL});
                                alert.showAndWait();
                                if (alert.getResult() == ButtonType.YES) {
                                    SystemDeleteBakController.this.model.deleteBak(systemDelBakBean);
                                    SystemDeleteBakController.this.refreshList();
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
        this.tcFunc.setStyle("-fx-alignment: CENTER;");
        this.btExit.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                Node node = (Node)actionEvent.getSource();
                Stage stage = (Stage)node.getScene().getWindow();
                stage.close();
            }
        });
        this.refreshList();
    }

    private void refreshList() {
        this.tbList.getItems().clear();
        if (this.model.queryBakList().size() > 0 && this.model.getBakList().size() > 0) {
            this.tbList.getItems().addAll(this.model.getBakList());
        }
        this.tbList.refresh();
    }

    @Override
    public void refresh() {
    }
}

