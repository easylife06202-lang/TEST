/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.beans.value.ChangeListener
 *  javafx.beans.value.ObservableValue
 *  javafx.collections.FXCollections
 *  javafx.collections.ObservableList
 *  javafx.concurrent.Task
 *  javafx.event.ActionEvent
 *  javafx.event.EventHandler
 *  javafx.fxml.FXML
 *  javafx.scene.Node
 *  javafx.scene.control.Alert
 *  javafx.scene.control.Alert$AlertType
 *  javafx.scene.control.Button
 *  javafx.scene.control.ButtonType
 *  javafx.scene.control.ComboBox
 *  javafx.scene.control.Label
 *  javafx.scene.control.ListView
 *  javafx.scene.control.SelectionMode
 *  javafx.scene.control.TextField
 *  javafx.scene.input.MouseEvent
 *  javafx.scene.layout.AnchorPane
 *  javafx.stage.FileChooser
 *  javafx.stage.FileChooser$ExtensionFilter
 *  javafx.stage.Stage
 *  javafx.stage.Window
 *  org.apache.commons.io.FileUtils
 */
package com.wfusion.baseland.estimate;

import com.wfusion.baseland.MainApp;
import com.wfusion.baseland.QueryBean;
import com.wfusion.baseland.SQLiteDataProviderModel;
import com.wfusion.baseland.basic.BaseLandDialog;
import com.wfusion.baseland.basic.Model;
import com.wfusion.baseland.estimate.EstimateController;
import com.wfusion.baseland.estimate.EstimateModel;
import com.wfusion.baseland.estimate.EstimatePrintOptionDialog;
import com.wfusion.baseland.estimate.EstimateReport1Model;
import com.wfusion.baseland.estimate.EstimateVersionDialog;
import com.wfusion.fx.util.ExceptionDialog;
import com.wfusion.fx.util.JavaFXUtil;
import com.wfusion.fx.util.UIProgressIndicator;
import com.wfusion.util.OptionPair;
import com.wfusion.util.StringProcess;
import com.wfusion.util.ZipUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import moiland.baseland.appraiser.AppraiserExport;
import moiland.baseland.appraiser.AppraiserImport;
import moiland.baseland.bo.SaveCheckBo;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_AHP;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_REPORT_PARAM;
import moiland.baseland.factor.em.EnumFactorVersion;
import moiland.baseland.util.BaseLandFactorVersionHelper;
import org.apache.commons.io.FileUtils;

public class EstimateLeftController {
    private static final String PACKAGE_DB = Model.SQLITE_PATH + "package.db";
    @FXML
    private ComboBox<OptionPair> cbAA46;
    @FXML
    private ComboBox<String> cbYear;
    @FXML
    private Button btExport;
    @FXML
    private ComboBox<OptionPair> cbAA45;
    @FXML
    private Button btQueryLBaseLand;
    @FXML
    private Button btNewBaseLand;
    @FXML
    private ListView<QueryBean> lvList;
    @FXML
    private TextField tfSerialNO;
    @FXML
    private ComboBox<OptionPair> cbArea;
    @FXML
    private ComboBox<OptionPair> cbVersion;
    @FXML
    private Button btExit;
    @FXML
    private Button btDelete;
    @FXML
    private Button btLoad;
    @FXML
    private Button btPrint;
    @FXML
    private Label lbBaseNo;
    @FXML
    private Button btSave;
    @FXML
    private Button btImport;
    EstimateController parentController = null;
    EstimateLeftController self_controller = null;
    BaseLandDialog parrentDialog = null;
    WeakReference<AnchorPane> panel2 = null;
    private boolean comboxFlag = true;
    private boolean hasShowExportMessage = true;

    public void init(final BaseLandDialog baseLandDialog, EstimateController estimateController) {
        this.parrentDialog = baseLandDialog;
        this.parentController = estimateController;
        this.self_controller = this;
        this.initCombox();
        this.initButton();
        this.lvList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.lvList.setOnMouseClicked((EventHandler)new EventHandler<MouseEvent>(){

            public void handle(MouseEvent mouseEvent) {
                EstimateLeftController.this.showExportMessage();
                if (mouseEvent.getClickCount() == 2 && EstimateLeftController.this.lvList.getSelectionModel().getSelectedItem() != null) {
                    EstimateVersionDialog estimateVersionDialog = new EstimateVersionDialog(baseLandDialog.getStage());
                    estimateVersionDialog.load((QueryBean)EstimateLeftController.this.lvList.getSelectionModel().getSelectedItem(), EstimateLeftController.this.self_controller);
                    estimateVersionDialog.show();
                }
            }
        });
    }

    private void initButton() {
        this.btSave.setOnAction(actionEvent -> {
            if (!this.parentController.estimateReport1Controller.isRatio100percent()) {
                JavaFXUtil.showToastMessageBox(this.parrentDialog.getStage(), "\u6b0a\u91cd\u52a0\u7e3d\u4e0d\u7b49\u65bc100\uff0c\u8acb\u6aa2\u67e5\u88681 \u5404\u7a2e\u4f30\u503c\u53ca\u6b0a\u91cd\u5340\u584a", 5000);
                return;
            }
            if (!this.parentController.estimateReport2Controller.isRatio100percent()) {
                JavaFXUtil.showToastMessageBox(this.parrentDialog.getStage(), "\u6b0a\u91cd\u52a0\u7e3d\u4e0d\u7b49\u65bc100\uff0c\u8acb\u6aa2\u67e5\u88682 (77) \u6a19\u7684\u6c7a\u5b9a\u6b0a\u6578", 5000);
                return;
            }
            this.parentController.estimateReport1Controller.refresh();
            this.parentController.estimateReport2Controller.refresh();
            this.parentController.estimateReport3Controller.refresh();
            this.parentController.estimateReport4Controller.refresh();
            this.parentController.estimateReport5Controller.refresh();
            this.parentController.estimateReport6Controller.refresh();
            this.parentController.estimateReport7Controller.refresh();
            this.parentController.estimateReport8Controller.refresh();
            if (this.parentController.estimateReport2Controller.savecheck()) {
                if (this.parentController.model.save()) {
                    JavaFXUtil.showToastMessageBox(this.parrentDialog.getStage(), "\u5132\u5b58\u6210\u529f", 5000);
                }
            } else {
                JavaFXUtil.showToastMessageBox(this.parrentDialog.getStage(), "\u6bd4\u8f03\u4f30\u50f9\u8868\u6bd4\u8f03\u5dee\u7570\u7387\u904e\u5927\uff0c\u8acb\u586b\u5beb\u5099\u8a3b\u8aaa\u660e", 5000);
            }
        });
        this.btQueryLBaseLand.setOnAction(actionEvent -> this.query(this.parrentDialog.getStage()));
        this.btNewBaseLand.setOnAction(actionEvent -> {
            this.newBaseLand(this.parrentDialog.getStage());
            this.query(this.parrentDialog.getStage());
        });
        this.btLoad.setOnAction(actionEvent -> {
            if (this.lvList.getSelectionModel().getSelectedItem() != null) {
                EstimateVersionDialog estimateVersionDialog = new EstimateVersionDialog(this.parrentDialog.getStage());
                estimateVersionDialog.load((QueryBean)this.lvList.getSelectionModel().getSelectedItem(), this);
                estimateVersionDialog.show();
            } else {
                JavaFXUtil.showToastMessageBox(new Stage(), "\u8acb\u9078\u64c7\u6b32\u8f09\u5165\u6848\u4ef6", 1000);
            }
        });
        this.btDelete.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "\u662f\u5426\u78ba\u5b9a\u522a\u9664\u6b64\u57fa\u6e96\u5730\u6848\u4ef6", new ButtonType[]{ButtonType.YES, ButtonType.NO, ButtonType.CANCEL});
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES && this.delete()) {
                JavaFXUtil.showToastMessageBox(this.parrentDialog.getStage(), "\u522a\u9664\u5b8c\u6210", 5000);
                this.query(this.parrentDialog.getStage());
            }
        });
        this.btPrint.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                QueryBean queryBean = (QueryBean)EstimateLeftController.this.lvList.getSelectionModel().getSelectedItem();
                if (queryBean != null) {
                    EstimatePrintOptionDialog estimatePrintOptionDialog = new EstimatePrintOptionDialog(EstimateLeftController.this.parrentDialog.getStage());
                    estimatePrintOptionDialog.load(actionEvent, (QueryBean)EstimateLeftController.this.lvList.getSelectionModel().getSelectedItem(), EstimateLeftController.this.self_controller);
                    estimatePrintOptionDialog.show();
                } else {
                    JavaFXUtil.showErrorMessageBox("\u8acb\u9078\u64c7\u6848\u4ef6", "");
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
        this.btExport.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                if (!EstimateLeftController.this.parentController.estimateReport1Controller.isRatio100percent()) {
                    JavaFXUtil.showToastMessageBox(EstimateLeftController.this.parrentDialog.getStage(), "\u6b0a\u91cd\u52a0\u7e3d\u4e0d\u7b49\u65bc100\uff0c\u8acb\u6aa2\u67e5\u5404\u7a2e\u4f30\u503c\u53ca\u6b0a\u91cd\u5340\u584a", 5000);
                    return;
                }
                if (!EstimateLeftController.this.parentController.estimateReport2Controller.isRatio100percent()) {
                    JavaFXUtil.showToastMessageBox(EstimateLeftController.this.parrentDialog.getStage(), "\u6b0a\u91cd\u52a0\u7e3d\u4e0d\u7b49\u65bc100\uff0c\u8acb\u6aa2\u67e5\u88682 (77) \u6a19\u7684\u6c7a\u5b9a\u6b0a\u6578", 5000);
                    return;
                }
                boolean bl = EstimateLeftController.this.lvList.getSelectionModel().getSelectedItems().size() > 1;
                QueryBean queryBean = (QueryBean)EstimateLeftController.this.lvList.getSelectionModel().getSelectedItem();
                if (queryBean != null) {
                    boolean bl2 = true;
                    StringBuilder stringBuilder = new StringBuilder();
                    bl2 = EstimateLeftController.this.checkImageSize(queryBean.year, queryBean.baseno, stringBuilder);
                    if (bl2) {
                        Node node = (Node)actionEvent.getSource();
                        Stage stage = (Stage)node.getScene().getWindow();
                        FileChooser fileChooser = new FileChooser();
                        String string = bl ? queryBean.year + queryBean.AA45 + queryBean.AA46 : queryBean.year + queryBean.baseno;
                        fileChooser.setInitialFileName(string);
                        fileChooser.getExtensionFilters().addAll((Object[])new FileChooser.ExtensionFilter[]{new FileChooser.ExtensionFilter("Exchange Files", new String[]{"*.ZIP"})});
                        File file = fileChooser.showSaveDialog((Window)stage);
                        if (file != null) {
                            String string2 = "";
                            try {
                                String string3;
                                Object object;
                                string2 = EstimateLeftController.this.parentController.model.exportCheck(PACKAGE_DB, file.getAbsolutePath(), Model.SQLITE_PATH + "BaseLand.db", queryBean.year, queryBean.baseno, stringBuilder);
                                if ("SUCC".equals(string2)) {
                                    this.exportAllDB(queryBean, file);
                                    JavaFXUtil.showToastMessageBox(EstimateLeftController.this.parrentDialog.getStage(), "\u532f\u51fa\u5b8c\u6210", 2000);
                                } else if ("NOSUCC".equals(string2)) {
                                    object = new Alert(Alert.AlertType.CONFIRMATION);
                                    object.setTitle("\u9078\u64c7\u8f38\u51fa\u6a21\u5f0f");
                                    object.setHeaderText("\u6848\u4ef6\u4e2d\u6709\u90e8\u5206\u8868\u55ae\u672a\u5b8c\u6210\uff0c\u8acb\u554f\u662f\u5426\u8f38\u51fa? \n\u5168\u90e8\u8f38\u51fa:\u7121\u8996\u6aa2\u6838\u5b8c\u5168\u8f38\u51fa (\u532f\u5165\u55ae\u6a5f\u7248\u7528) \n\u5b8c\u6210\u8f38\u51fa:\u50c5\u532f\u51fa\u5b8c\u6210\u8868\u55ae\uff0c\u5efa\u8b70\u7e73\u4ea4\u6848\u4ef6\u4f7f\u7528 (\u63d0\u4f9b\u5730\u6240\u7528)");
                                    object.setContentText(stringBuilder.toString());
                                    string3 = new ButtonType("\u5168\u90e8\u8f38\u51fa (\u532f\u5165\u55ae\u6a5f\u7248\u7528)");
                                    ButtonType buttonType = new ButtonType("\u5b8c\u6210\u8f38\u51fa (\u63d0\u4f9b\u5730\u6240\u7528)");
                                    object.getButtonTypes().setAll((Object[])new ButtonType[]{buttonType, string3, ButtonType.CANCEL});
                                    Optional optional = object.showAndWait();
                                    if (optional.get() == string3) {
                                        this.exportAllDB(queryBean, file);
                                        JavaFXUtil.showToastMessageBox(EstimateLeftController.this.parrentDialog.getStage(), "\u532f\u51fa\u5b8c\u6210", 2000);
                                    } else if (optional.get() == buttonType) {
                                        string2 = "";
                                        stringBuilder = new StringBuilder();
                                        string2 = this.exportSuccDB(queryBean, stringBuilder, file, string);
                                        if ("FALSE".equals(string2)) {
                                            JavaFXUtil.showErrorMessageBox(stringBuilder.toString());
                                        } else {
                                            JavaFXUtil.showToastMessageBox(EstimateLeftController.this.parrentDialog.getStage(), "\u532f\u51fa\u5b8c\u6210", 2000);
                                        }
                                    } else {
                                        file.delete();
                                        System.gc();
                                    }
                                } else if (!StringProcess.isEmpty(stringBuilder.toString())) {
                                    JavaFXUtil.showErrorMessageBox(stringBuilder.toString());
                                }
                                object = new SaveCheckBo(EstimateModel.BASELANDBEAN);
                                string3 = ((SaveCheckBo)object).unicodeCheckColumn();
                                if (!StringProcess.isEmpty(string3)) {
                                    JavaFXUtil.showNormalMessageBox("\u63d0\u9192:\u4ee5\u4e0b\u6b04\u4f4d\u5167\u5bb9\u6709unicode\u5b57\u9ad4\uff0c\u8f49\u4ea4\u5730\u6240\u7121\u6cd5\u6b63\u78ba\u986f\u793a", string3);
                                }
                            }
                            catch (Exception exception) {
                                exception.printStackTrace();
                            }
                        }
                    } else if (stringBuilder.length() > 0) {
                        JavaFXUtil.showErrorMessageBox(stringBuilder.toString());
                    }
                } else {
                    JavaFXUtil.showErrorMessageBox("\u8acb\u9078\u64c7\u6848\u4ef6", "");
                }
            }

            private String exportSuccDB(QueryBean queryBean, StringBuilder stringBuilder, File file, String string) throws Exception {
                String string2 = "TRUE";
                ObservableList observableList = EstimateLeftController.this.lvList.getSelectionModel().getSelectedItems();
                String string3 = file.getAbsolutePath();
                File file2 = new File(Model.SQLITE_PATH + File.separator + "output");
                if (!file2.exists()) {
                    file2.mkdir();
                }
                for (QueryBean queryBean2 : observableList) {
                    String string4 = file2.getAbsolutePath() + File.separator + queryBean2.year + queryBean2.baseno + ".CHG";
                    String string5 = EstimateLeftController.this.parentController.model.exportSuccData(PACKAGE_DB, string4, Model.SQLITE_PATH + "BaseLand.db", queryBean2.year, queryBean2.baseno, stringBuilder);
                    if (!string5.equals("FALSE")) continue;
                    string2 = "FALSE";
                }
                this.createZIP(string3, file2.getAbsolutePath());
                return string2;
            }

            private void exportAllDB(QueryBean queryBean, File file) throws Exception {
                AppraiserExport appraiserExport = new AppraiserExport(true);
                ObservableList observableList = EstimateLeftController.this.lvList.getSelectionModel().getSelectedItems();
                String string = file.getAbsolutePath();
                File file2 = new File(Model.SQLITE_PATH + File.separator + "output");
                if (!file2.exists()) {
                    file2.mkdir();
                }
                for (QueryBean queryBean2 : observableList) {
                    String string2 = file2.getAbsolutePath() + File.separator + queryBean2.year + queryBean2.baseno + ".CHG";
                    appraiserExport.export(PACKAGE_DB, string2, Model.SQLITE_PATH + "BaseLand.db", queryBean2.year, queryBean2.baseno);
                }
                this.createZIP(string, file2.getAbsolutePath());
            }

            private void createZIP(String string, String string2) throws Exception {
                ZipUtil zipUtil = new ZipUtil();
                File file = new File(string2);
                File file2 = new File(string);
                zipUtil.makeZip(file, file2);
                FileUtils.deleteDirectory((File)file);
            }
        });
        this.btImport.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                Node node = (Node)actionEvent.getSource();
                Stage stage = (Stage)node.getScene().getWindow();
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().addAll((Object[])new FileChooser.ExtensionFilter[]{new FileChooser.ExtensionFilter("Exchange Files", new String[]{"*.CHG", "*.ZIP"})});
                File file = fileChooser.showOpenDialog((Window)stage);
                if (file != null) {
                    String string = file.getName();
                    if (string.substring(string.lastIndexOf(".") + 1).equalsIgnoreCase("ZIP")) {
                        File file2;
                        boolean bl = false;
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "\u82e5\u532f\u5165\u6848\u4ef6\u5df2\u5b58\u5728\uff0c\u662f\u5426\u4e00\u5f8b\u8986\u84cb\u539f\u8cc7\u6599\uff1f", new ButtonType[]{ButtonType.YES, ButtonType.NO, ButtonType.CANCEL});
                        alert.showAndWait();
                        if (alert.getResult() == ButtonType.YES) {
                            bl = true;
                        }
                        if (!bl) {
                            file2 = new Alert(Alert.AlertType.CONFIRMATION, "\u82e5\u4e0d\u9032\u884c\u8986\u84cb\uff0c\u5247\u532f\u5165\u7684\u6bcf\u7b46\u8cc7\u6599\u90fd\u8981\u78ba\u8a8d\u662f\u5426\u8986\u84cb\uff0c\u78ba\u8a8d\u7e7c\u7e8c\u57f7\u884c\uff1f", new ButtonType[]{ButtonType.YES, ButtonType.CANCEL});
                            file2.showAndWait();
                            if (file2.getResult() == ButtonType.CANCEL) {
                                return;
                            }
                        }
                        if (!(file2 = new File(Model.SQLITE_PATH + File.separator + "output")).exists()) {
                            file2.mkdir();
                        }
                        ZipUtil zipUtil = new ZipUtil();
                        try {
                            File[] fileArray;
                            zipUtil.unZip(file, file2.getAbsolutePath());
                            for (File file3 : fileArray = file2.listFiles()) {
                                this.importSingleDB(file3, bl);
                            }
                            FileUtils.deleteDirectory((File)file2);
                        }
                        catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    } else {
                        this.importSingleDB(file, false);
                    }
                    JavaFXUtil.showNormalMessageBox("\u532f\u5165\u4f5c\u696d\u7d50\u675f", "");
                }
            }

            private void importSingleDB(File file, boolean bl) {
                AppraiserImport appraiserImport = new AppraiserImport(file.getAbsolutePath(), Model.SQLITE_PATH + "BaseLand.db");
                StringBuffer stringBuffer = new StringBuffer();
                String string = file.getName().substring(0, file.getName().lastIndexOf("."));
                try {
                    if (!appraiserImport.checkBaseno(stringBuffer)) {
                        if (stringBuffer.length() > 0) {
                            JavaFXUtil.showErrorMessageBox(stringBuffer.toString());
                        } else if (bl) {
                            if (!appraiserImport.importData(stringBuffer)) {
                                JavaFXUtil.showErrorMessageBox(stringBuffer.toString());
                            }
                        } else {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "\u57fa\u6e96\u5730\u6848\u4ef6(" + string + ")\u5df2\u5b58\u5728\u662f\u5426\u8986\u84cb", new ButtonType[]{ButtonType.YES, ButtonType.NO});
                            alert.showAndWait();
                            if (alert.getResult() == ButtonType.YES && !appraiserImport.importData(stringBuffer)) {
                                JavaFXUtil.showErrorMessageBox(stringBuffer.toString());
                            }
                        }
                    } else if (!appraiserImport.importData(stringBuffer)) {
                        JavaFXUtil.showErrorMessageBox(stringBuffer.toString());
                    }
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    private boolean delete() {
        QueryBean queryBean = (QueryBean)this.lvList.getSelectionModel().getSelectedItem();
        boolean bl = false;
        if (queryBean != null) {
            String string = queryBean.year;
            String string2 = queryBean.baseno;
            bl = this.parentController.model.delete(string, string2);
        } else {
            JavaFXUtil.showErrorMessageBox("\u8acb\u9078\u64c7\u6848\u4ef6", "");
        }
        return bl;
    }

    private void query(Stage stage) {
        if (this.checkInput(true)) {
            ArrayList<QueryBean> arrayList = this.parentController.model.getBandMainNum(EstimateModel.BASELANDBEAN.queryBean);
            if (arrayList.size() > 0) {
                this.lvList.setItems(FXCollections.observableArrayList(arrayList));
            } else {
                this.lvList.setItems(FXCollections.observableArrayList());
                JavaFXUtil.showToastMessageBox(stage, "\u67e5\u7121\u6848\u4ef6", 500);
            }
        }
    }

    protected void newBaseLand(Stage stage) {
        if (!this.checkInput(false)) {
            return;
        }
        EstimateModel.BASELANDBEAN.init();
        EstimateModel.BASELANDBEAN.queryBean.mode = "add";
        EstimateModel.BASELANDBEAN.queryBean.office = this.parentController.model.getOfficeByTown(EstimateModel.BASELANDBEAN.queryBean.AA45, EstimateModel.BASELANDBEAN.queryBean.AA46);
        if (this.cbAA45.getSelectionModel().getSelectedItem() != null) {
            EstimateModel.BASELANDBEAN.queryBean.AA45 = ((OptionPair)this.cbAA45.getSelectionModel().getSelectedItem()).getValue();
        }
        if (this.cbAA46.getSelectionModel().getSelectedItem() != null) {
            EstimateModel.BASELANDBEAN.queryBean.AA46 = ((OptionPair)this.cbAA46.getSelectionModel().getSelectedItem()).getValue();
        }
        if (this.cbYear.getSelectionModel().getSelectedItem() != null) {
            EstimateModel.BASELANDBEAN.queryBean.year = (String)this.cbYear.getSelectionModel().getSelectedItem();
        }
        if (this.cbArea.getSelectionModel().getSelectedItem() != null) {
            EstimateModel.BASELANDBEAN.queryBean.urban = ((OptionPair)this.cbArea.getSelectionModel().getSelectedItem()).getValue();
        }
        if (this.cbVersion.getSelectionModel().getSelectedItem() != null) {
            EstimateModel.BASELANDBEAN.queryBean.version = ((OptionPair)this.cbVersion.getSelectionModel().getSelectedItem()).getValue();
        }
        int n = StringProcess.parserInt(this.tfSerialNO.getText().toString(), 0);
        this.parentController.model.getNewBaseNo(n);
        String string = this.parentController.model.createCheck();
        if (!StringProcess.isEmpty(string)) {
            JavaFXUtil.showErrorMessageBox(string);
            return;
        }
        this.parentController.model.updateCompareFactorList();
        EstimateModel.BASELANDBEAN.initBeans();
        this.querySystemParam();
        if (!this.haveAHP()) {
            JavaFXUtil.showErrorMessageBox("\u67e5\u7121\u6b0a\u91cd\u53c3\u6578", "\u67e5\u7121\u6b0a\u91cd\u53c3\u6578\uff0c\u8acb\u5148\u81f3\u7cfb\u7d71\u7ba1\u7406-\u6b0a\u91cd\u53c3\u6578\u9032\u884c\u8a2d\u5b9a");
            return;
        }
        SaveCheckBo saveCheckBo = new SaveCheckBo(EstimateModel.BASELANDBEAN);
        String string2 = saveCheckBo.saveCheckColumn();
        if (StringProcess.isEmpty(string2)) {
            this.parentController.model.save();
            EstimateModel.BASELANDBEAN.queryBean.mode = "edit";
            UIProgressIndicator uIProgressIndicator = new UIProgressIndicator(stage);
            Task<Void> task = new Task<Void>(){

                protected Void call() throws Exception {
                    try {
                        Object var1_1 = null;
                        EstimateLeftController.this.panel2 = "A3BD".equals(EstimateModel.BASELANDBEAN.queryBean.version) ? EstimateLeftController.this.parentController.loadTab2("/view/EstimateReport2BD.fxml") : ("A3BF".equals(EstimateModel.BASELANDBEAN.queryBean.version) ? EstimateLeftController.this.parentController.loadTab2("/view/EstimateReport2BF.fxml") : EstimateLeftController.this.parentController.loadTab2("/view/EstimateReport2.fxml"));
                    }
                    catch (Error error) {
                        ExceptionDialog.show(error);
                    }
                    return null;
                }
            };
            uIProgressIndicator.show(task);
            task.setOnSucceeded(workerStateEvent -> {
                this.parentController.updateTab2(this.panel2);
                this.parentController.estimateReport2Controller.refreshCombox();
                this.updateBaseNoLabel();
                this.parentController.estimateReport1Controller.refresh();
                EstimateModel.BASELANDBEAN.isInit = true;
                uIProgressIndicator.close();
            });
            new Thread((Runnable)task).start();
        } else {
            JavaFXUtil.showErrorMessageBox(string2);
        }
    }

    private void querySystemParam() {
        NVO_BASELAND_REPORT_PARAM nVO_BASELAND_REPORT_PARAM = this.parentController.model.getSystemParam(EstimateModel.BASELANDBEAN.queryBean);
        if (nVO_BASELAND_REPORT_PARAM != null) {
            EstimateModel.BASELANDBEAN.voSell_1.setCs09(nVO_BASELAND_REPORT_PARAM.getOwner_rate());
            EstimateModel.BASELANDBEAN.voSell_1.setCs10(nVO_BASELAND_REPORT_PARAM.getOwner_ratio());
            EstimateModel.BASELANDBEAN.voSell_1.setCs12(nVO_BASELAND_REPORT_PARAM.getDebt_rate());
            EstimateModel.BASELANDBEAN.voSell_1.setCs13(nVO_BASELAND_REPORT_PARAM.getDebt_ratio());
            EstimateModel.BASELANDBEAN.voSell_1.setCs15(nVO_BASELAND_REPORT_PARAM.getPresale_rate());
            EstimateModel.BASELANDBEAN.voSell_1.setCs16(nVO_BASELAND_REPORT_PARAM.getPresale_ratio());
            EstimateModel.BASELANDBEAN.voSell_1.setCs32(nVO_BASELAND_REPORT_PARAM.getDesign_ratio());
            EstimateModel.BASELANDBEAN.voSell_1.setCs34(nVO_BASELAND_REPORT_PARAM.getAd_ratio());
            EstimateModel.BASELANDBEAN.voSell_1.setCs36(nVO_BASELAND_REPORT_PARAM.getManage_ratio());
            EstimateModel.BASELANDBEAN.voSell_1.setCs38(nVO_BASELAND_REPORT_PARAM.getTax_ratio());
            EstimateModel.BASELANDBEAN.voSell_1.setCs42(nVO_BASELAND_REPORT_PARAM.getDevp_rate());
            EstimateModel.BASELANDBEAN.voSell_2.setCs09(nVO_BASELAND_REPORT_PARAM.getOwner_rate());
            EstimateModel.BASELANDBEAN.voSell_2.setCs10(nVO_BASELAND_REPORT_PARAM.getOwner_ratio());
            EstimateModel.BASELANDBEAN.voSell_2.setCs12(nVO_BASELAND_REPORT_PARAM.getDebt_rate());
            EstimateModel.BASELANDBEAN.voSell_2.setCs13(nVO_BASELAND_REPORT_PARAM.getDebt_ratio());
            EstimateModel.BASELANDBEAN.voSell_2.setCs15(nVO_BASELAND_REPORT_PARAM.getPresale_rate());
            EstimateModel.BASELANDBEAN.voSell_2.setCs16(nVO_BASELAND_REPORT_PARAM.getPresale_ratio());
            EstimateModel.BASELANDBEAN.voSell_2.setCs32(nVO_BASELAND_REPORT_PARAM.getDesign_ratio());
            EstimateModel.BASELANDBEAN.voSell_2.setCs34(nVO_BASELAND_REPORT_PARAM.getAd_ratio());
            EstimateModel.BASELANDBEAN.voSell_2.setCs36(nVO_BASELAND_REPORT_PARAM.getManage_ratio());
            EstimateModel.BASELANDBEAN.voSell_2.setCs38(nVO_BASELAND_REPORT_PARAM.getTax_ratio());
            EstimateModel.BASELANDBEAN.voSell_2.setCs42(nVO_BASELAND_REPORT_PARAM.getDevp_rate());
            EstimateModel.BASELANDBEAN.voSell_3.setCs09(nVO_BASELAND_REPORT_PARAM.getOwner_rate());
            EstimateModel.BASELANDBEAN.voSell_3.setCs10(nVO_BASELAND_REPORT_PARAM.getOwner_ratio());
            EstimateModel.BASELANDBEAN.voSell_3.setCs12(nVO_BASELAND_REPORT_PARAM.getDebt_rate());
            EstimateModel.BASELANDBEAN.voSell_3.setCs13(nVO_BASELAND_REPORT_PARAM.getDebt_ratio());
            EstimateModel.BASELANDBEAN.voSell_3.setCs15(nVO_BASELAND_REPORT_PARAM.getPresale_rate());
            EstimateModel.BASELANDBEAN.voSell_3.setCs16(nVO_BASELAND_REPORT_PARAM.getPresale_ratio());
            EstimateModel.BASELANDBEAN.voSell_3.setCs32(nVO_BASELAND_REPORT_PARAM.getDesign_ratio());
            EstimateModel.BASELANDBEAN.voSell_3.setCs34(nVO_BASELAND_REPORT_PARAM.getAd_ratio());
            EstimateModel.BASELANDBEAN.voSell_3.setCs36(nVO_BASELAND_REPORT_PARAM.getManage_ratio());
            EstimateModel.BASELANDBEAN.voSell_3.setCs38(nVO_BASELAND_REPORT_PARAM.getTax_ratio());
            EstimateModel.BASELANDBEAN.voSell_3.setCs42(nVO_BASELAND_REPORT_PARAM.getDevp_rate());
            EstimateModel.BASELANDBEAN.voRentExt.setCre10(nVO_BASELAND_REPORT_PARAM.getOwner_rate());
            EstimateModel.BASELANDBEAN.voRentExt.setCre11(nVO_BASELAND_REPORT_PARAM.getOwner_ratio());
            EstimateModel.BASELANDBEAN.voRentExt.setCre13(nVO_BASELAND_REPORT_PARAM.getDebt_rate());
            EstimateModel.BASELANDBEAN.voRentExt.setCre14(nVO_BASELAND_REPORT_PARAM.getDebt_ratio());
            EstimateModel.BASELANDBEAN.voRentExt.setCre16(nVO_BASELAND_REPORT_PARAM.getPresale_rate());
            EstimateModel.BASELANDBEAN.voRentExt.setCre17(nVO_BASELAND_REPORT_PARAM.getPresale_ratio());
            EstimateModel.BASELANDBEAN.voRentExt.setCre35(nVO_BASELAND_REPORT_PARAM.getDesign_ratio());
            EstimateModel.BASELANDBEAN.voRentExt.setCre38(nVO_BASELAND_REPORT_PARAM.getAd_ratio());
            EstimateModel.BASELANDBEAN.voRentExt.setCre40(nVO_BASELAND_REPORT_PARAM.getManage_ratio());
            EstimateModel.BASELANDBEAN.voRentExt.setCre42(nVO_BASELAND_REPORT_PARAM.getTax_ratio());
            EstimateModel.BASELANDBEAN.voRentExt.setCre50(nVO_BASELAND_REPORT_PARAM.getDevp_rate());
            EstimateModel.BASELANDBEAN.voRent.setCr30(nVO_BASELAND_REPORT_PARAM.getBuild_benefit_rate());
            EstimateModel.BASELANDBEAN.voRent.setCr35(nVO_BASELAND_REPORT_PARAM.getLand_benefit_rate());
            EstimateModel.BASELANDBEAN.voDevelop.setOwner_rate(nVO_BASELAND_REPORT_PARAM.getOwner_rate());
            EstimateModel.BASELANDBEAN.voDevelop.setOwner_ratio(nVO_BASELAND_REPORT_PARAM.getOwner_ratio());
            EstimateModel.BASELANDBEAN.voDevelop.setDebt_rate(nVO_BASELAND_REPORT_PARAM.getDebt_rate());
            EstimateModel.BASELANDBEAN.voDevelop.setDebt_ratio(nVO_BASELAND_REPORT_PARAM.getDebt_ratio());
            EstimateModel.BASELANDBEAN.voDevelop.setPresale_rate(nVO_BASELAND_REPORT_PARAM.getPresale_rate());
            EstimateModel.BASELANDBEAN.voDevelop.setPresale_ratio(nVO_BASELAND_REPORT_PARAM.getPresale_ratio());
            EstimateModel.BASELANDBEAN.voDevelop.setDesign_ratio(nVO_BASELAND_REPORT_PARAM.getDesign_ratio());
            EstimateModel.BASELANDBEAN.voDevelop.setAd_ratio(nVO_BASELAND_REPORT_PARAM.getAd_ratio());
            EstimateModel.BASELANDBEAN.voDevelop.setManage_ratio(nVO_BASELAND_REPORT_PARAM.getManage_ratio());
            EstimateModel.BASELANDBEAN.voDevelop.setTax_ratio(nVO_BASELAND_REPORT_PARAM.getTax_ratio());
        }
    }

    private boolean haveAHP() {
        NVO_BASELAND_AHP nVO_BASELAND_AHP = this.parentController.model.getAHP(EstimateModel.BASELANDBEAN.queryBean);
        if (nVO_BASELAND_AHP != null) {
            return nVO_BASELAND_AHP.isHaveData();
        }
        return false;
    }

    public void load(Stage stage) {
        if (this.lvList.getSelectionModel().getSelectedItem() == null) {
            JavaFXUtil.showToastMessageBox(stage, "\u8acb\u5148\u9078\u64c7\u6848\u4ef6", 500);
            return;
        }
        this.parentController.selTab(0);
        EstimateModel.BASELANDBEAN.init();
        EstimateModel.BASELANDBEAN.initBeans();
        EstimateModel.BASELANDBEAN.isInit = false;
        if (this.panel2 != null) {
            this.panel2.clear();
            this.panel2 = null;
        }
        System.gc();
        UIProgressIndicator uIProgressIndicator = new UIProgressIndicator(stage);
        Task<Void> task = new Task<Void>(){

            protected Void call() throws Exception {
                EstimateModel.BASELANDBEAN.queryBean = (QueryBean)EstimateLeftController.this.lvList.getSelectionModel().getSelectedItem();
                if (EstimateModel.BASELANDBEAN.queryBean != null) {
                    EstimateLeftController.this.parentController.model.updateMainVoFromDB();
                    EstimateLeftController.this.parentController.model.updateCompareFactorList();
                    EstimateLeftController.this.parentController.model.updateAppRaVoFromDB();
                    EstimateLeftController.this.parentController.model.updateAppRaA3VoFromDB();
                    EstimateLeftController.this.parentController.model.updateSellVoFromDB();
                    EstimateLeftController.this.parentController.model.updateFloorEffectVoFromDB();
                    EstimateLeftController.this.parentController.model.updateRentExtVoFromDB();
                    EstimateLeftController.this.parentController.model.updateRentVoFromDB();
                    EstimateLeftController.this.parentController.model.updateRentMonthVoFromDB();
                    EstimateLeftController.this.parentController.model.updateDevelopVoFromDB();
                    EstimateLeftController.this.parentController.model.updateBaseLandImages();
                    EstimateModel.BASELANDBEAN.queryBean.mode = "edit";
                    try {
                        EstimateLeftController.this.panel2 = "A3BD".equals(EstimateModel.BASELANDBEAN.queryBean.version) ? EstimateLeftController.this.parentController.loadTab2("/view/EstimateReport2BD.fxml") : ("A3BF".equals(EstimateModel.BASELANDBEAN.queryBean.version) ? EstimateLeftController.this.parentController.loadTab2("/view/EstimateReport2BF.fxml") : EstimateLeftController.this.parentController.loadTab2("/view/EstimateReport2.fxml"));
                    }
                    catch (Error error) {
                        ExceptionDialog.show(error);
                    }
                    finally {
                        new EstimateReport1Model().reCal();
                    }
                }
                return null;
            }
        };
        uIProgressIndicator.show(task);
        task.setOnSucceeded(workerStateEvent -> {
            this.parentController.updateTab2(this.panel2);
            this.parentController.estimateReport2Controller.refreshCombox();
            EstimateModel.BASELANDBEAN.isInit = true;
            this.parentController.estimateReport1Controller.refresh();
            this.parentController.estimateReport8Controller.pageRefresh();
            this.comboxFlag = false;
            this.cbArea.getSelectionModel().select((Object)new OptionPair(EstimateModel.BASELANDBEAN.queryBean.urban, ""));
            this.comboxFlag = true;
            this.cbVersion.getSelectionModel().select((Object)new OptionPair(EstimateModel.BASELANDBEAN.queryBean.version, EnumFactorVersion.findSelfByString(EstimateModel.BASELANDBEAN.queryBean.version).getDescription()));
            uIProgressIndicator.close();
        });
        new Thread((Runnable)task).start();
    }

    private void initCombox() {
        this.cbAA45.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getAA45List()));
        this.cbAA45.getSelectionModel().selectFirst();
        this.cbAA45.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                if (EstimateLeftController.this.cbAA45.getSelectionModel().getSelectedItem() != null && !StringProcess.isEmpty(((OptionPair)EstimateLeftController.this.cbAA45.getSelectionModel().getSelectedItem()).getValue())) {
                    EstimateModel.BASELANDBEAN.queryBean.AA45 = ((OptionPair)EstimateLeftController.this.cbAA45.getSelectionModel().getSelectedItem()).getValue();
                    EstimateLeftController.this.cbAA46.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getAA46List(EstimateModel.BASELANDBEAN.queryBean.AA45)));
                    EstimateLeftController.this.cbAA46.getSelectionModel().selectFirst();
                    EstimateLeftController.this.updateBaseNoLabel();
                }
            }
        });
        this.cbAA46.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getDefaultList()));
        this.cbAA46.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                if (EstimateLeftController.this.cbAA46.getSelectionModel().getSelectedItem() != null) {
                    EstimateModel.BASELANDBEAN.queryBean.AA46 = ((OptionPair)EstimateLeftController.this.cbAA46.getSelectionModel().getSelectedItem()).getValue();
                    EstimateLeftController.this.updateBaseNoLabel();
                    EstimateLeftController.this.cbYear.setItems(FXCollections.observableArrayList(EstimateLeftController.this.parentController.model.getExistYearList()));
                }
            }
        });
        this.cbYear.setItems(FXCollections.observableArrayList(this.parentController.model.getExistYearList()));
        this.cbYear.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<String>(){

            public void changed(ObservableValue<? extends String> observableValue, String string, String string2) {
                if (EstimateLeftController.this.cbYear.getSelectionModel().getSelectedItem() != null) {
                    EstimateModel.BASELANDBEAN.queryBean.year = (String)EstimateLeftController.this.cbYear.getSelectionModel().getSelectedItem();
                }
            }
        });
        this.cbArea.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getAreaList()));
        this.cbArea.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                if (EstimateLeftController.this.comboxFlag && EstimateLeftController.this.cbArea.getSelectionModel().getSelectedItem() != null) {
                    EstimateModel.BASELANDBEAN.queryBean.urban = ((OptionPair)EstimateLeftController.this.cbArea.getSelectionModel().getSelectedItem()).getValue();
                    EstimateLeftController.this.updateBaseNoLabel();
                    EstimateLeftController.this.changeVersion();
                }
            }
        });
        this.cbVersion.setItems(FXCollections.observableArrayList(SQLiteDataProviderModel.getDefaultList()));
        this.cbVersion.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<OptionPair>(){

            public void changed(ObservableValue<? extends OptionPair> observableValue, OptionPair optionPair, OptionPair optionPair2) {
                if (EstimateLeftController.this.comboxFlag && EstimateLeftController.this.cbVersion.getSelectionModel().getSelectedItem() != null) {
                    EstimateModel.BASELANDBEAN.queryBean.version = ((OptionPair)EstimateLeftController.this.cbVersion.getSelectionModel().getSelectedItem()).getValue();
                }
            }
        });
    }

    private void changeVersion() {
        if (this.cbArea.getSelectionModel().getSelectedItem() != null) {
            ArrayList<OptionPair> arrayList = new ArrayList<OptionPair>();
            arrayList.add(new OptionPair("", "\u8acb\u9078\u64c7"));
            LinkedHashMap<String, String> linkedHashMap = BaseLandFactorVersionHelper.getAvailableListByUrban(((OptionPair)this.cbArea.getValue()).getValue());
            if (linkedHashMap != null && linkedHashMap.size() > 0) {
                for (Map.Entry<String, String> entry : linkedHashMap.entrySet()) {
                    arrayList.add(new OptionPair(entry.getKey(), entry.getValue()));
                }
                this.cbVersion.setItems(FXCollections.observableArrayList(arrayList));
                this.cbVersion.getSelectionModel().selectFirst();
            }
        }
    }

    private void updateBaseNoLabel() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.cbAA45.getSelectionModel().getSelectedItem() != null) {
            stringBuilder.append(((OptionPair)this.cbAA45.getSelectionModel().getSelectedItem()).getValue());
            if (this.cbAA46.getSelectionModel().getSelectedItem() != null) {
                stringBuilder.append(((OptionPair)this.cbAA46.getSelectionModel().getSelectedItem()).getValue());
                if (this.cbArea.getSelectionModel().getSelectedItem() != null) {
                    stringBuilder.append(((OptionPair)this.cbArea.getSelectionModel().getSelectedItem()).getValue());
                }
            }
        }
        this.lbBaseNo.setText(stringBuilder.toString());
        String string = this.parentController.model.getNewBaseseq();
        this.tfSerialNO.setPromptText(string);
    }

    private boolean checkInput(boolean bl) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean bl2 = true;
        if (MainApp.IS_TEST && StringProcess.isEmpty(EstimateModel.BASELANDBEAN.queryBean.AA45)) {
            EstimateModel.BASELANDBEAN.queryBean.AA45 = "E";
            EstimateModel.BASELANDBEAN.queryBean.AA46 = "16";
            EstimateModel.BASELANDBEAN.queryBean.year = "107";
            EstimateModel.BASELANDBEAN.queryBean.urban = "EA";
        }
        if (StringProcess.isEmpty(EstimateModel.BASELANDBEAN.queryBean.AA45)) {
            stringBuilder.append("\u8acb\u9078\u64c7 [\u7e23\u5e02] \u6b04\u4f4d").append("\n");
            bl2 = false;
        }
        if (StringProcess.isEmpty(EstimateModel.BASELANDBEAN.queryBean.AA46)) {
            stringBuilder.append("\u8acb\u9078\u64c7 [\u884c\u653f\u5340] \u6b04\u4f4d").append("\n");
            bl2 = false;
        }
        if (StringProcess.isEmpty(EstimateModel.BASELANDBEAN.queryBean.year)) {
            stringBuilder.append("\u8acb\u9078\u64c7 [\u5e74\u5ea6] \u6b04\u4f4d").append("\n");
            bl2 = false;
        }
        if (!bl) {
            String string;
            if (StringProcess.isEmpty(this.tfSerialNO.getText().toString())) {
                this.tfSerialNO.setText(this.tfSerialNO.getPromptText());
            }
            if ((string = StringProcess.fillZero(StringProcess.parserInt(this.tfSerialNO.getText().toString(), 0), 4)).length() > 4 || "0000".equals(string)) {
                stringBuilder.append("\u81ea\u8a02\u6d41\u6c34\u865f [").append(this.tfSerialNO.getText()).append("] \u683c\u5f0f\u4e0d\u7b26 (\u6700\u591a\u56db\u4f4d\u6574\u6578)").append("\n");
                bl2 = false;
            }
            if (!this.parentController.model.checkExistBaseNum(string)) {
                stringBuilder.append("\u7de8\u865f [").append(string).append("] \u5df2\u5b58\u5728\uff0c\u8acb\u91cd\u53d6\u7de8\u865f").append("\n");
                bl2 = false;
            }
            if (StringProcess.isEmpty(EstimateModel.BASELANDBEAN.queryBean.urban)) {
                stringBuilder.append("\u8acb\u9078\u64c7 [\u5206\u5340] \u6b04\u4f4d\uff0c\u4e14\u4e0d\u53ef\u4ee5\u9078\u64c7\"\u4e0d\u5206\u5340\"").append("\n");
                bl2 = false;
            }
            if (StringProcess.isEmpty(EstimateModel.BASELANDBEAN.queryBean.version)) {
                stringBuilder.append("\u8acb\u9078\u64c7 [\u9069\u7528\u57fa\u6e96\u8868] \u6b04\u4f4d").append("\n");
                bl2 = false;
            }
        }
        if (!bl2) {
            JavaFXUtil.showErrorMessageBox("\u8f38\u5165\u689d\u4ef6\u932f\u8aa4", stringBuilder.toString());
        }
        return bl2;
    }

    private boolean checkImageSize(String string, String string2, StringBuilder stringBuilder) {
        boolean bl = this.parentController.model.checkImageSize(string, string2, stringBuilder);
        return bl;
    }

    public void printMethod(ActionEvent actionEvent, QueryBean queryBean, boolean bl, boolean bl2) {
        byte[] byArray = this.parentController.model.print(queryBean, bl, bl2);
        Node node = (Node)actionEvent.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        String string = this.getVersion();
        fileChooser.setInitialFileName(queryBean.baseno + string);
        fileChooser.getExtensionFilters().addAll((Object[])new FileChooser.ExtensionFilter[]{new FileChooser.ExtensionFilter("PDF Files", new String[]{"*.pdf"})});
        File file = fileChooser.showSaveDialog((Window)stage);
        if (file != null) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(byArray);
                fileOutputStream.close();
            }
            catch (IOException iOException) {
                JavaFXUtil.showErrorMessageBox(iOException.toString());
            }
        }
    }

    private String getVersion() {
        String string = Model.getVersion();
        if (!StringProcess.isEmpty(string)) {
            string = "(" + string + ")";
        }
        return string;
    }

    private void showExportMessage() {
        if (this.hasShowExportMessage) {
            this.hasShowExportMessage = false;
            JavaFXUtil.showToastMessageBox(this.parrentDialog.getStage(), "\u82e5\u8981\u532f\u51fa\u6a94\u6848\uff0c\u6309\u4f4f\u300cCtrl\u300d\u53ef\u9032\u884c\u8907\u9078", 3000);
            new Thread(() -> {
                try {
                    Thread.sleep(10000L);
                    this.hasShowExportMessage = true;
                }
                catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }).start();
        }
    }
}

