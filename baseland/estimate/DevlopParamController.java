/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.fxml.FXML
 *  javafx.scene.Node
 *  javafx.scene.control.Button
 *  javafx.scene.control.TextField
 *  javafx.stage.Stage
 */
package com.wfusion.baseland.estimate;

import com.wfusion.baseland.basic.Controller;
import com.wfusion.baseland.basic.IBaseLandDialog;
import com.wfusion.baseland.estimate.EstimateController;
import com.wfusion.baseland.estimate.EstimateModel;
import com.wfusion.baseland.estimate.EstimateReport8Controller;
import com.wfusion.baseland.estimate.EstimateReport8Model;
import com.wfusion.fx.util.NumberFormater;
import com.wfusion.util.StringProcess;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DevlopParamController
extends Controller {
    @FXML
    private TextField tfSale_electric;
    @FXML
    private TextField tfSale_balcony;
    @FXML
    private TextField tfSale_protrusionc;
    @FXML
    private TextField tfSale_parkarea;
    @FXML
    private TextField tfSale_publicratio;
    @FXML
    private TextField tfSale_protrusionm;
    @FXML
    private Button btSave;
    EstimateReport8Model model;

    public void init(IBaseLandDialog iBaseLandDialog, EstimateController estimateController, EstimateReport8Controller estimateReport8Controller, EstimateReport8Model estimateReport8Model) {
        super.init(iBaseLandDialog);
        this.model = estimateReport8Model;
        this.tfSale_electric.setText(NumberFormater.df3.format(EstimateModel.BASELANDBEAN.voDevelop.getSale_electric()));
        this.tfSale_balcony.setText(NumberFormater.df3.format(EstimateModel.BASELANDBEAN.voDevelop.getSale_balcony()));
        this.tfSale_protrusionc.setText(String.valueOf(EstimateModel.BASELANDBEAN.voDevelop.getSale_protrusionc()));
        this.tfSale_parkarea.setText(NumberFormater.df3.format(EstimateModel.BASELANDBEAN.voDevelop.getSale_parkarea()));
        this.tfSale_publicratio.setText(NumberFormater.df3.format(EstimateModel.BASELANDBEAN.voDevelop.getSale_publicratio()));
        this.tfSale_protrusionm.setText(String.valueOf(EstimateModel.BASELANDBEAN.voDevelop.getSale_protrusionm()));
        this.btSave.setOnAction(actionEvent -> {
            double d = StringProcess.parserDouble(this.tfSale_electric.getText().toString(), 1.15);
            double d2 = StringProcess.parserDouble(this.tfSale_balcony.getText().toString(), 1.15);
            int n = StringProcess.parserInt(this.tfSale_protrusionc.getText().toString(), 3);
            double d3 = StringProcess.parserDouble(this.tfSale_parkarea.getText().toString(), 8.0);
            double d4 = StringProcess.parserDouble(this.tfSale_publicratio.getText().toString(), 0.15);
            int n2 = StringProcess.parserInt(this.tfSale_protrusionm.getText().toString(), 12);
            estimateReport8Model.voValue.put("sale_electric", d);
            estimateReport8Model.voValue.put("sale_balcony", d2);
            estimateReport8Model.voValue.put("sale_protrusionc", n);
            estimateReport8Model.voValue.put("sale_protrusionm", n2);
            estimateReport8Model.voValue.put("sale_publicratio", d4);
            estimateReport8Model.voValue.put("sale_parkarea", d3);
            estimateReport8Controller.reCal();
            Node node = (Node)actionEvent.getSource();
            Stage stage = (Stage)node.getScene().getWindow();
            stage.close();
        });
    }

    @Override
    public void refresh() {
    }
}

