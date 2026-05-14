/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  javafx.event.ActionEvent
 *  javafx.event.EventHandler
 *  javafx.fxml.FXML
 *  javafx.scene.Node
 *  javafx.scene.control.Button
 *  javafx.scene.control.Label
 *  javafx.scene.control.TextField
 *  javafx.scene.layout.AnchorPane
 *  javafx.stage.Stage
 */
package com.wfusion.baseland.estimate;

import com.google.gson.Gson;
import com.wfusion.baseland.basic.Controller;
import com.wfusion.baseland.basic.IBaseLandDialog;
import com.wfusion.baseland.estimate.EstimateReport1Controller;
import com.wfusion.util.StringProcess;
import java.util.HashMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EstimateSetRoadDataController
extends Controller {
    @FXML
    private AnchorPane panel;
    @FXML
    private Label masterRoad;
    @FXML
    private Button btSave;
    @FXML
    private Button btExit;
    @FXML
    private Label masterWidth;
    @FXML
    private TextField road1;
    @FXML
    private TextField road2;
    @FXML
    private TextField road3;
    @FXML
    private TextField road4;
    @FXML
    private TextField width1;
    @FXML
    private TextField width2;
    @FXML
    private TextField width3;
    @FXML
    private TextField width4;
    @FXML
    private AnchorPane hbWinTitle;
    @FXML
    private Label TITLE;

    public void init(IBaseLandDialog iBaseLandDialog, final EstimateReport1Controller estimateReport1Controller, String string, String string2, String string3) {
        super.init(iBaseLandDialog);
        this.masterRoad.setText(string);
        this.masterWidth.setText(string2);
        if (!StringProcess.isEmpty(string3)) {
            this.getRoadData(string3);
        }
        if (this.btExit != null) {
            this.btExit.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

                public void handle(ActionEvent actionEvent) {
                    Node node = (Node)actionEvent.getSource();
                    Stage stage = (Stage)node.getScene().getWindow();
                    stage.close();
                }
            });
        }
        this.btSave.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                String string;
                estimateReport1Controller.street_rel_ext = string = EstimateSetRoadDataController.this.doStreatData();
                estimateReport1Controller.reCal();
                Node node = (Node)actionEvent.getSource();
                Stage stage = (Stage)node.getScene().getWindow();
                stage.close();
            }
        });
    }

    private String doStreatData() {
        String string = "";
        String string2 = "\"";
        for (int i = 1; i <= 4; ++i) {
            String string3 = "";
            String string4 = "";
            for (Node node : this.panel.getChildren()) {
                String string5 = node.getId();
                if (StringProcess.isEmpty(string5)) continue;
                if (!StringProcess.isEmpty(string5) && node instanceof TextField && string5.equals("road" + i)) {
                    string3 = ((TextField)node).getText();
                    continue;
                }
                if (StringProcess.isEmpty(string5) || !(node instanceof TextField) || !string5.equals("width" + i)) continue;
                string4 = ((TextField)node).getText();
            }
            if (!StringProcess.isEmpty(string)) {
                string = string + ",";
            }
            string = string + "{" + string2 + "ROAD" + string2 + ":";
            string = string + string2 + string3 + string2 + ",";
            string = string + string2 + "WIDTH" + string2 + ":";
            string = string + string2 + string4 + string2 + "}";
        }
        return "[" + string + "]";
    }

    private void getRoadData(String string) {
        Gson gson = new Gson();
        HashMap[] hashMapArray = null;
        if (!StringProcess.isEmpty(string)) {
            hashMapArray = (HashMap[])gson.fromJson(string, HashMap[].class);
        }
        if (hashMapArray != null) {
            int n = 1;
            for (HashMap hashMap : hashMapArray) {
                for (Node node : this.panel.getChildren()) {
                    String string2 = node.getId();
                    if (StringProcess.isEmpty(string2)) continue;
                    if (!StringProcess.isEmpty(string2) && node instanceof TextField && string2.equals("road" + n)) {
                        ((TextField)node).setText((String)hashMap.get("ROAD"));
                        continue;
                    }
                    if (StringProcess.isEmpty(string2) || !(node instanceof TextField) || !string2.equals("width" + n)) continue;
                    ((TextField)node).setText((String)hashMap.get("WIDTH"));
                }
                ++n;
            }
        } else {
            for (Node node : this.panel.getChildren()) {
                String string3 = node.getId();
                if (StringProcess.isEmpty(string3)) continue;
                if (!StringProcess.isEmpty(string3) && node instanceof TextField) {
                    ((TextField)node).setText("");
                    continue;
                }
                if (StringProcess.isEmpty(string3) || !(node instanceof TextField)) continue;
                ((TextField)node).setText("");
            }
        }
    }

    @Override
    public void refresh() {
    }
}

