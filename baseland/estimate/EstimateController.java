/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.beans.value.ChangeListener
 *  javafx.beans.value.ObservableValue
 *  javafx.fxml.FXML
 *  javafx.fxml.FXMLLoader
 *  javafx.scene.Node
 *  javafx.scene.control.ScrollPane
 *  javafx.scene.control.Tab
 *  javafx.scene.control.TabPane
 *  javafx.scene.layout.AnchorPane
 */
package com.wfusion.baseland.estimate;

import com.wfusion.baseland.basic.Controller;
import com.wfusion.baseland.basic.IBaseLandDialog;
import com.wfusion.baseland.estimate.EstimateDialog;
import com.wfusion.baseland.estimate.EstimateLeftController;
import com.wfusion.baseland.estimate.EstimateModel;
import com.wfusion.baseland.estimate.EstimateReport1Controller;
import com.wfusion.baseland.estimate.EstimateReport2Controller;
import com.wfusion.baseland.estimate.EstimateReport3Controller;
import com.wfusion.baseland.estimate.EstimateReport4Controller;
import com.wfusion.baseland.estimate.EstimateReport5Controller;
import com.wfusion.baseland.estimate.EstimateReport6Controller;
import com.wfusion.baseland.estimate.EstimateReport7Controller;
import com.wfusion.baseland.estimate.EstimateReport8Controller;
import java.io.IOException;
import java.lang.ref.WeakReference;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class EstimateController
extends Controller {
    @FXML
    protected EstimateLeftController estimateLeftController;
    @FXML
    private TabPane tabReport;
    protected EstimateReport1Controller estimateReport1Controller;
    protected EstimateReport2Controller estimateReport2Controller;
    protected EstimateReport3Controller estimateReport3Controller;
    protected EstimateReport4Controller estimateReport4Controller;
    protected EstimateReport5Controller estimateReport5Controller;
    protected EstimateReport6Controller estimateReport6Controller;
    protected EstimateReport7Controller estimateReport7Controller;
    protected EstimateReport8Controller estimateReport8Controller;
    private Tab tab2;
    private Tab tab3;
    private Tab tab4;
    private Tab tab5;
    private Tab tab6;
    private Tab tab7;
    private Tab tab8;
    protected EstimateModel model = new EstimateModel();
    EstimateDialog dialog = null;

    public void setDialog(EstimateDialog estimateDialog) {
        this.dialog = estimateDialog;
    }

    @Override
    public void init(IBaseLandDialog iBaseLandDialog) {
        super.init(iBaseLandDialog);
        this.setTitle("\u57fa\u6e96\u5730\u67e5\u4f30\u4f5c\u696d");
        this.estimateLeftController.init(this.dialog, this);
        this.initTab1();
        this.initTab2();
        this.initTab3();
        this.initTab4();
        this.initTab5();
        this.initTab6();
        this.initTab7();
        this.initTab8();
        this.tabReport.getSelectionModel().selectedItemProperty().addListener((ChangeListener)new ChangeListener<Tab>(){

            public void changed(ObservableValue<? extends Tab> observableValue, Tab tab, Tab tab2) {
                if (!EstimateModel.BASELANDBEAN.isInit) {
                    return;
                }
                if (tab.getText().contains("\u57fa\u6e96\u5730\u4f30\u50f9\u5831\u544a\u8868")) {
                    EstimateController.this.estimateReport1Controller.reCal();
                } else if (tab.getText().contains("\u6bd4\u8f03\u4f30\u50f9\u8868")) {
                    EstimateController.this.estimateReport2Controller.reCal();
                } else if (tab.getText().contains("\u6210\u672c\u6cd5(1)")) {
                    EstimateController.this.estimateReport3Controller.reCal();
                } else if (tab.getText().contains("\u6210\u672c\u6cd5(2)")) {
                    EstimateController.this.estimateReport4Controller.reCal();
                } else if (tab.getText().contains("\u6210\u672c\u6cd5(3)")) {
                    EstimateController.this.estimateReport5Controller.reCal();
                } else if (tab.getText().contains("\u6210\u672c\u6cd5(\u6536\u76ca)")) {
                    EstimateController.this.estimateReport6Controller.reCal();
                } else if (tab.getText().contains("\u6536\u76ca\u8abf\u67e5\u4f30\u50f9\u8868")) {
                    EstimateController.this.estimateReport7Controller.reCal();
                } else if (tab.getText().contains("\u571f\u958b\u6cd5\u8abf\u67e5\u4f30\u50f9\u8868")) {
                    EstimateController.this.estimateReport8Controller.reCal();
                }
                if (tab2.getText().contains("\u57fa\u6e96\u5730\u4f30\u50f9\u5831\u544a\u8868")) {
                    EstimateController.this.estimateReport1Controller.refresh();
                } else if (tab2.getText().contains("\u6bd4\u8f03\u4f30\u50f9\u8868")) {
                    EstimateController.this.estimateReport2Controller.refresh();
                } else if (tab2.getText().contains("\u6210\u672c\u6cd5(1)")) {
                    EstimateController.this.estimateReport3Controller.refresh();
                } else if (tab2.getText().contains("\u6210\u672c\u6cd5(2)")) {
                    EstimateController.this.estimateReport4Controller.refresh();
                } else if (tab2.getText().contains("\u6210\u672c\u6cd5(3)")) {
                    EstimateController.this.estimateReport5Controller.refresh();
                } else if (tab2.getText().contains("\u6210\u672c\u6cd5(\u6536\u76ca)")) {
                    EstimateController.this.estimateReport6Controller.refresh();
                } else if (tab2.getText().contains("\u6536\u76ca\u8abf\u67e5\u4f30\u50f9\u8868")) {
                    EstimateController.this.estimateReport7Controller.refresh();
                } else if (tab2.getText().contains("\u571f\u958b\u6cd5\u8abf\u67e5\u4f30\u50f9\u8868")) {
                    EstimateController.this.estimateReport8Controller.refresh();
                }
            }
        });
    }

    private void initTab1() {
        FXMLLoader fXMLLoader = new FXMLLoader(this.getClass().getResource("/view/EstimateReport1.fxml"));
        try {
            AnchorPane anchorPane = (AnchorPane)fXMLLoader.load();
            this.estimateReport1Controller = (EstimateReport1Controller)fXMLLoader.getController();
            this.estimateReport1Controller.init(this.dialog, this);
            Tab tab = new Tab("\u3000\u57fa\u6e96\u5730\u4f30\u50f9\u5831\u544a\u8868\u3000", (Node)new ScrollPane((Node)anchorPane));
            this.tabReport.getTabs().add((Object)tab);
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public WeakReference<AnchorPane> loadTab2(String string) {
        FXMLLoader fXMLLoader = new FXMLLoader(this.getClass().getResource(string));
        try {
            WeakReference<Object> weakReference = new WeakReference<Object>(fXMLLoader.load());
            this.estimateReport2Controller = (EstimateReport2Controller)fXMLLoader.getController();
            this.estimateReport2Controller.init(this.dialog, this);
            return weakReference;
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            return null;
        }
    }

    public void updateTab2(WeakReference<AnchorPane> weakReference) {
        ScrollPane scrollPane = (ScrollPane)this.tab2.getContent();
        if (scrollPane != null) {
            scrollPane.setContent(null);
            scrollPane = null;
            this.tab2.setContent(null);
            System.gc();
        }
        this.tab2.setText("\u3000\u6bd4\u8f03\u4f30\u50f9\u8868(A3)\u3000");
        this.tab2.setContent((Node)new ScrollPane((Node)weakReference.get()));
    }

    private void initTab2() {
        this.tab2 = new Tab("\u3000\u6bd4\u8f03\u4f30\u50f9\u8868\u3000");
        this.tabReport.getTabs().add((Object)this.tab2);
        System.out.println("\u521d\u59cb\u5b8c\u75622");
    }

    private void initTab3() {
        FXMLLoader fXMLLoader = new FXMLLoader(this.getClass().getResource("/view/EstimateReport3.fxml"));
        try {
            AnchorPane anchorPane = (AnchorPane)fXMLLoader.load();
            this.estimateReport3Controller = (EstimateReport3Controller)fXMLLoader.getController();
            this.estimateReport3Controller.init(this.dialog, this);
            this.tab3 = new Tab("\u3000\u6210\u672c\u6cd5(1)\u3000", (Node)new ScrollPane((Node)anchorPane));
            this.tabReport.getTabs().add((Object)this.tab3);
            System.out.println("\u521d\u59cb\u5b8c\u75623");
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    private void initTab4() {
        FXMLLoader fXMLLoader = new FXMLLoader(this.getClass().getResource("/view/EstimateReport4.fxml"));
        try {
            AnchorPane anchorPane = (AnchorPane)fXMLLoader.load();
            this.estimateReport4Controller = (EstimateReport4Controller)fXMLLoader.getController();
            this.estimateReport4Controller.init(this.dialog, this);
            this.tab4 = new Tab("\u3000\u6210\u672c\u6cd5(2)\u3000", (Node)new ScrollPane((Node)anchorPane));
            this.tabReport.getTabs().add((Object)this.tab4);
            System.out.println("\u521d\u59cb\u5b8c\u75624");
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    private void initTab5() {
        FXMLLoader fXMLLoader = new FXMLLoader(this.getClass().getResource("/view/EstimateReport5.fxml"));
        try {
            AnchorPane anchorPane = (AnchorPane)fXMLLoader.load();
            this.estimateReport5Controller = (EstimateReport5Controller)fXMLLoader.getController();
            this.estimateReport5Controller.init(this.dialog, this);
            this.tab5 = new Tab("\u3000\u6210\u672c\u6cd5(3)\u3000", (Node)new ScrollPane((Node)anchorPane));
            this.tabReport.getTabs().add((Object)this.tab5);
            System.out.println("\u521d\u59cb\u5b8c\u75625");
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    private void initTab6() {
        FXMLLoader fXMLLoader = new FXMLLoader(this.getClass().getResource("/view/EstimateReport6.fxml"));
        try {
            AnchorPane anchorPane = (AnchorPane)fXMLLoader.load();
            this.estimateReport6Controller = (EstimateReport6Controller)fXMLLoader.getController();
            this.estimateReport6Controller.init(this.dialog, this);
            this.tab6 = new Tab("\u3000\u6210\u672c\u6cd5(\u6536\u76ca)\u3000", (Node)new ScrollPane((Node)anchorPane));
            this.tabReport.getTabs().add((Object)this.tab6);
            System.out.println("\u521d\u59cb\u5b8c\u75626");
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    private void initTab7() {
        FXMLLoader fXMLLoader = new FXMLLoader(this.getClass().getResource("/view/EstimateReport7.fxml"));
        try {
            AnchorPane anchorPane = (AnchorPane)fXMLLoader.load();
            this.estimateReport7Controller = (EstimateReport7Controller)fXMLLoader.getController();
            this.estimateReport7Controller.init(this.dialog, this);
            this.tab7 = new Tab("\u3000\u6536\u76ca\u8abf\u67e5\u4f30\u50f9\u8868\u3000", (Node)new ScrollPane((Node)anchorPane));
            this.tabReport.getTabs().add((Object)this.tab7);
            System.out.println("\u521d\u59cb\u5b8c\u75627");
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    private void initTab8() {
        FXMLLoader fXMLLoader = new FXMLLoader(this.getClass().getResource("/view/EstimateReport8.fxml"));
        try {
            AnchorPane anchorPane = (AnchorPane)fXMLLoader.load();
            this.estimateReport8Controller = (EstimateReport8Controller)fXMLLoader.getController();
            this.estimateReport8Controller.init(this.dialog, this);
            this.tab8 = new Tab("\u3000\u571f\u958b\u6cd5\u8abf\u67e5\u4f30\u50f9\u8868\u3000", (Node)new ScrollPane((Node)anchorPane));
            this.tabReport.getTabs().add((Object)this.tab8);
            System.out.println("\u521d\u59cb\u5b8c\u75628");
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public void initTab() {
        this.estimateReport1Controller.init(this.dialog, this);
        this.estimateReport2Controller.init(this.dialog, this);
        this.estimateReport3Controller.init(this.dialog, this);
        this.estimateReport4Controller.init(this.dialog, this);
        this.estimateReport5Controller.init(this.dialog, this);
        this.estimateReport6Controller.init(this.dialog, this);
        this.estimateReport7Controller.init(this.dialog, this);
        this.estimateReport8Controller.init(this.dialog, this);
        this.estimateReport1Controller.refresh();
    }

    @Override
    public void refresh() {
    }

    public void selTab(int n) {
        this.tabReport.getSelectionModel().select(n);
    }
}

