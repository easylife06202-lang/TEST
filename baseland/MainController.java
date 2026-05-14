/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.concurrent.Task
 *  javafx.event.ActionEvent
 *  javafx.event.EventHandler
 *  javafx.fxml.FXML
 *  javafx.scene.Node
 *  javafx.scene.control.Button
 *  javafx.scene.control.Label
 *  javafx.scene.layout.BorderPane
 *  javafx.stage.Stage
 *  javafx.stage.Window
 */
package com.wfusion.baseland;

import com.wfusion.baseland.basic.Controller;
import com.wfusion.baseland.basic.IBaseLandDialog;
import com.wfusion.baseland.estimate.EstimateDialog;
import com.wfusion.baseland.estimate.EstimateModel;
import com.wfusion.baseland.system.SystemDialog;
import com.wfusion.fx.util.ExceptionDialog;
import com.wfusion.fx.util.JavaFXUtil;
import com.wfusion.fx.util.UIProgressIndicator;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MainController
extends Controller {
    @FXML
    private Button btEstimate;
    @FXML
    private Button btReport;
    @FXML
    private Button btSystem;
    @FXML
    private Label VERSION;
    @FXML
    private BorderPane pane;

    @Override
    public void init(IBaseLandDialog iBaseLandDialog) {
        this.updateBeginData();
        super.init(iBaseLandDialog);
        this.setTitle("\u57fa\u6e96\u5730\u9078\u5b9a\u53ca\u67e5\u4f30\u4f5c\u696d V2.17");
        this.VERSION.setText("V2.17");
        this.VERSION.setOnMouseClicked(mouseEvent -> this.systemInfo());
        this.btEstimate.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                Node node = (Node)actionEvent.getSource();
                Window window = node.getScene().getWindow();
                EstimateDialog estimateDialog = new EstimateDialog((Stage)window);
                EstimateModel.BASELANDBEAN.init();
                MainController.this.showDialog((Stage)window, estimateDialog);
            }
        });
        this.btSystem.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                Node node = (Node)actionEvent.getSource();
                Window window = node.getScene().getWindow();
                SystemDialog systemDialog = new SystemDialog((Stage)window);
                MainController.this.showDialog((Stage)window, systemDialog);
            }
        });
    }

    private void updateBeginData() {
        EstimateModel.updateBeginData();
    }

    private void systemInfo() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        List<String> list = runtimeMXBean.getInputArguments();
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : list) {
            stringBuilder.append(string).append("\n");
        }
        JavaFXUtil.showNormalMessageBox("\u7cfb\u7d71\u8cc7\u8a0a", stringBuilder.toString());
    }

    private void showDialog(Stage stage, final IBaseLandDialog iBaseLandDialog) {
        UIProgressIndicator uIProgressIndicator = new UIProgressIndicator(stage);
        Task<Node> task = new Task<Node>(){

            protected Node call() throws Exception {
                try {
                    iBaseLandDialog.load();
                }
                catch (Error error) {
                    ExceptionDialog.show(error);
                }
                return null;
            }
        };
        uIProgressIndicator.show(task);
        task.setOnSucceeded(workerStateEvent -> {
            iBaseLandDialog.show();
            uIProgressIndicator.close();
        });
        new Thread((Runnable)task).start();
    }

    @Override
    public void refresh() {
    }
}

