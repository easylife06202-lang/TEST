/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.concurrent.Task
 *  javafx.concurrent.WorkerStateEvent
 *  javafx.event.ActionEvent
 *  javafx.event.EventHandler
 *  javafx.fxml.FXML
 *  javafx.scene.Node
 *  javafx.scene.control.Button
 *  javafx.stage.Stage
 *  javafx.stage.Window
 */
package com.wfusion.baseland.system;

import com.wfusion.baseland.basic.Controller;
import com.wfusion.baseland.basic.IBaseLandDialog;
import com.wfusion.baseland.system.BaseLandBuildPriceAdjustDialog;
import com.wfusion.baseland.system.CarryOverModel;
import com.wfusion.baseland.system.GlossaryDialog;
import com.wfusion.baseland.system.GoAHPDialog;
import com.wfusion.baseland.system.IndividualFactorDialog;
import com.wfusion.baseland.system.InstruDialog;
import com.wfusion.baseland.system.InstruStdPriceDialog;
import com.wfusion.baseland.system.PriceRateDialog;
import com.wfusion.baseland.system.ReginalFactorDialog;
import com.wfusion.baseland.system.ReportParamDialog;
import com.wfusion.baseland.system.SystemCarryOverDialog;
import com.wfusion.baseland.system.SystemCopyDialog;
import com.wfusion.baseland.system.SystemDeleteBakDialog;
import com.wfusion.fx.util.ExceptionDialog;
import com.wfusion.fx.util.JavaFXUtil;
import com.wfusion.fx.util.UIProgressIndicator;
import com.wfusion.util.StringProcess;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;

public class SystemController
extends Controller {
    @FXML
    private Button btCarryOver;
    @FXML
    private Button btReginalFactor;
    @FXML
    private Button btWeightParam;
    @FXML
    private Button btPriceDateIndex;
    @FXML
    private Button btNoteDict;
    @FXML
    private Button btInvidualFactor;
    @FXML
    private Button btInstru;
    @FXML
    private Button btInstruStdPrice;
    @FXML
    private Button btReportParam;
    @FXML
    private Button btExit;
    @FXML
    private Button btUpdateSect;
    @FXML
    private Button btBaseLandBuildPriceAdjust;
    @FXML
    private Button btSystemCopy;
    @FXML
    private Button btSystemDelBak;

    @Override
    public void init(final IBaseLandDialog iBaseLandDialog) {
        super.init(iBaseLandDialog);
        this.setTitle("\u7cfb\u7d71\u7ba1\u7406");
        this.btUpdateSect.setOnAction(actionEvent -> {
            UIProgressIndicator uIProgressIndicator = new UIProgressIndicator(this.selfDialog.getStage(), "\u66f4\u65b0\u6bb5\u4ee3\u78bc\u4e2d");
            Task<Integer> task = new Task<Integer>(){

                protected Integer call() throws Exception {
                    int n = 0;
                    try {
                        n = new CarryOverModel().updateSect();
                    }
                    catch (Error error) {
                        ExceptionDialog.show(error);
                    }
                    return n;
                }
            };
            uIProgressIndicator.show(task);
            task.setOnSucceeded(arg_0 -> this.lambda$null$78((Task)task, uIProgressIndicator, arg_0));
            new Thread((Runnable)task).start();
        });
        this.btExit.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                iBaseLandDialog.getStage().close();
            }
        });
        this.btCarryOver.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                Node node = (Node)actionEvent.getSource();
                Window window = node.getScene().getWindow();
                SystemCarryOverDialog systemCarryOverDialog = new SystemCarryOverDialog((Stage)window);
                SystemController.this.showDialog((Stage)window, systemCarryOverDialog);
            }
        });
        this.btReginalFactor.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                Node node = (Node)actionEvent.getSource();
                Window window = node.getScene().getWindow();
                ReginalFactorDialog reginalFactorDialog = new ReginalFactorDialog((Stage)window);
                SystemController.this.showDialog((Stage)window, reginalFactorDialog);
            }
        });
        this.btInvidualFactor.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                Node node = (Node)actionEvent.getSource();
                Window window = node.getScene().getWindow();
                IndividualFactorDialog individualFactorDialog = new IndividualFactorDialog((Stage)window);
                SystemController.this.showDialog((Stage)window, individualFactorDialog);
            }
        });
        this.btNoteDict.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                Node node = (Node)actionEvent.getSource();
                Window window = node.getScene().getWindow();
                GlossaryDialog glossaryDialog = new GlossaryDialog((Stage)window);
                SystemController.this.showDialog((Stage)window, glossaryDialog);
            }
        });
        this.btInstru.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                Node node = (Node)actionEvent.getSource();
                Window window = node.getScene().getWindow();
                InstruDialog instruDialog = new InstruDialog((Stage)window);
                SystemController.this.showDialog((Stage)window, instruDialog);
            }
        });
        this.btInstruStdPrice.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                Node node = (Node)actionEvent.getSource();
                Window window = node.getScene().getWindow();
                InstruStdPriceDialog instruStdPriceDialog = new InstruStdPriceDialog((Stage)window);
                SystemController.this.showDialog((Stage)window, instruStdPriceDialog);
            }
        });
        this.btPriceDateIndex.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                Node node = (Node)actionEvent.getSource();
                Window window = node.getScene().getWindow();
                PriceRateDialog priceRateDialog = new PriceRateDialog((Stage)window);
                SystemController.this.showDialog((Stage)window, priceRateDialog);
            }
        });
        this.btReportParam.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                Node node = (Node)actionEvent.getSource();
                Window window = node.getScene().getWindow();
                ReportParamDialog reportParamDialog = new ReportParamDialog((Stage)window);
                SystemController.this.showDialog((Stage)window, reportParamDialog);
            }
        });
        this.btWeightParam.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                Node node = (Node)actionEvent.getSource();
                Window window = node.getScene().getWindow();
                GoAHPDialog goAHPDialog = new GoAHPDialog((Stage)window);
                SystemController.this.showDialog((Stage)window, goAHPDialog);
            }
        });
        this.btBaseLandBuildPriceAdjust.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                Node node = (Node)actionEvent.getSource();
                Window window = node.getScene().getWindow();
                BaseLandBuildPriceAdjustDialog baseLandBuildPriceAdjustDialog = new BaseLandBuildPriceAdjustDialog((Stage)window);
                SystemController.this.showDialog((Stage)window, baseLandBuildPriceAdjustDialog);
            }
        });
        this.btSystemCopy.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                Node node = (Node)actionEvent.getSource();
                Window window = node.getScene().getWindow();
                SystemCopyDialog systemCopyDialog = new SystemCopyDialog((Stage)window);
                SystemController.this.showDialog((Stage)window, systemCopyDialog);
            }
        });
        this.btSystemDelBak.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                Node node = (Node)actionEvent.getSource();
                Window window = node.getScene().getWindow();
                SystemDeleteBakDialog systemDeleteBakDialog = new SystemDeleteBakDialog((Stage)window);
                SystemController.this.showDialog((Stage)window, systemDeleteBakDialog);
            }
        });
    }

    private void showDialog(Stage stage, final IBaseLandDialog iBaseLandDialog) {
        UIProgressIndicator uIProgressIndicator = new UIProgressIndicator(stage);
        Task<String> task = new Task<String>(){

            protected String call() throws Exception {
                try {
                    return iBaseLandDialog.load();
                }
                catch (Error error) {
                    ExceptionDialog.show(error);
                    return "";
                }
            }
        };
        uIProgressIndicator.show(task);
        task.setOnSucceeded(arg_0 -> SystemController.lambda$showDialog$80((Task)task, iBaseLandDialog, uIProgressIndicator, arg_0));
        new Thread((Runnable)task).start();
    }

    @Override
    public void refresh() {
    }

    private static /* synthetic */ void lambda$showDialog$80(Task task, IBaseLandDialog iBaseLandDialog, UIProgressIndicator uIProgressIndicator, WorkerStateEvent workerStateEvent) {
        String string = (String)task.getValue();
        if (string != null && StringProcess.isEmpty(string)) {
            iBaseLandDialog.show();
        } else {
            JavaFXUtil.showErrorMessageBox("\u767c\u751f\u932f\u8aa4", string);
        }
        uIProgressIndicator.close();
    }

    private /* synthetic */ void lambda$null$78(Task task, UIProgressIndicator uIProgressIndicator, WorkerStateEvent workerStateEvent) {
        JavaFXUtil.showToastMessageBox(this.selfDialog.getStage(), "\u66f4\u65b0" + task.getValue() + "\u7b46", 800);
        uIProgressIndicator.close();
    }
}

