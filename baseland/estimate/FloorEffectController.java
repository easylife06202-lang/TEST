/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
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

import com.wfusion.baseland.basic.Controller;
import com.wfusion.baseland.basic.IBaseLandDialog;
import com.wfusion.baseland.estimate.EstimateModel;
import com.wfusion.baseland.estimate.FloorEffectModel;
import com.wfusion.fx.util.JavaFXUtil;
import com.wfusion.util.StringProcess;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.TreeMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_FLOOR_EFFECT;
import moiland.baseland.flooreffect.bean.LandPriceAllocationCalculateBean;
import moiland.baseland.util.FloorEffectCalculateTool;

public class FloorEffectController
extends Controller {
    @FXML
    private AnchorPane dataPane;
    @FXML
    private Label inp_avg;
    @FXML
    private Label lb_avg;
    @FXML
    private Button btReturn;
    @FXML
    private Button btCal;
    @FXML
    private Button EXIT;
    private String cs04f = "";
    private long cs48 = 0L;
    private long cs47 = 0L;
    TreeMap<String, LandPriceAllocationCalculateBean> datas = null;
    FloorEffectModel model = new FloorEffectModel();
    NVO_BASELAND_FLOOR_EFFECT floorVo = null;
    DecimalFormat df = new DecimalFormat("##0.00");
    StringBuilder ret = null;

    public void init(IBaseLandDialog iBaseLandDialog, String string, String string2, String string3, int n, int n2, String string4, long l, long l2, final StringBuilder stringBuilder) {
        LandPriceAllocationCalculateBean landPriceAllocationCalculateBean;
        String string5;
        int n3;
        super.init(iBaseLandDialog);
        this.ret = stringBuilder;
        this.cs04f = string4 + "F";
        this.cs48 = l;
        this.cs47 = l2;
        this.floorVo = this.getCasevo(string3);
        if (this.EXIT != null) {
            this.EXIT.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

                public void handle(ActionEvent actionEvent) {
                    stringBuilder.delete(0, stringBuilder.length());
                    Node node = (Node)actionEvent.getSource();
                    Stage stage = (Stage)node.getScene().getWindow();
                    stage.close();
                }
            });
        }
        this.btReturn.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                if (FloorEffectController.this.datas != null && FloorEffectController.this.datas.size() > 0) {
                    FloorEffectController.this.model.saveFloorEffect(FloorEffectController.this.floorVo, FloorEffectController.this.datas, FloorEffectController.this.inp_avg.getText(), FloorEffectController.this.lb_avg.getText());
                }
                Node node = (Node)actionEvent.getSource();
                Stage stage = (Stage)node.getScene().getWindow();
                stage.close();
            }
        });
        this.btCal.setOnAction((EventHandler)new EventHandler<ActionEvent>(){

            public void handle(ActionEvent actionEvent) {
                FloorEffectController.this.calFloorEffect();
            }
        });
        this.datas = this.model.getFloorEffect(this.floorVo);
        if (this.datas == null) {
            this.datas = new TreeMap();
        } else if (n + n2 != this.datas.size()) {
            JavaFXUtil.showNormalMessageBox("\u8acb\u91cd\u65b0\u8a08\u7b97\u6a13\u5c64\u6548\u7528\u6bd4", "\u8207\u4e0a\u6b21\u8a08\u7b97\u5c64\u6578\u4e0d\u540c\uff0c\u8acb\u5f9e\u65b0\u8a08\u7b97");
        } else {
            this.inp_avg.setText(this.df.format(this.floorVo.getAvg_effect()));
            this.lb_avg.setText(this.df.format(this.floorVo.getAvg_ratio()));
        }
        TreeMap<String, LandPriceAllocationCalculateBean> treeMap = new TreeMap<String, LandPriceAllocationCalculateBean>();
        for (n3 = 1; n3 <= n; ++n3) {
            string5 = "B" + n3 + "F";
            landPriceAllocationCalculateBean = new LandPriceAllocationCalculateBean();
            landPriceAllocationCalculateBean.setFloor(string5);
            landPriceAllocationCalculateBean.setFloorEffectRatio(100.0);
            if (this.datas.containsKey(string5)) {
                landPriceAllocationCalculateBean.setFloorEffectRatio(this.datas.get(string5).getFloorEffectRatio());
                landPriceAllocationCalculateBean.setBuildingEffectRatio(this.datas.get(string5).getBuildingEffectRatio());
                landPriceAllocationCalculateBean.setLandPriceAllocationRatio(this.datas.get(string5).getLandPriceAllocationRatio());
            }
            treeMap.put(string5, landPriceAllocationCalculateBean);
        }
        for (n3 = 1; n3 <= n2; ++n3) {
            string5 = n3 + "F";
            landPriceAllocationCalculateBean = new LandPriceAllocationCalculateBean();
            landPriceAllocationCalculateBean.setFloor(string5);
            landPriceAllocationCalculateBean.setFloorEffectRatio(100.0);
            if (this.datas.containsKey(string5)) {
                landPriceAllocationCalculateBean.setFloorEffectRatio(this.datas.get(string5).getFloorEffectRatio());
                landPriceAllocationCalculateBean.setBuildingEffectRatio(this.datas.get(string5).getBuildingEffectRatio());
                landPriceAllocationCalculateBean.setLandPriceAllocationRatio(this.datas.get(string5).getLandPriceAllocationRatio());
            }
            treeMap.put(string5, landPriceAllocationCalculateBean);
        }
        this.datas.clear();
        this.datas.putAll(treeMap);
        this.createView(this.cs04f);
    }

    private NVO_BASELAND_FLOOR_EFFECT getCasevo(String string) {
        if ("1".equals(string)) {
            return EstimateModel.BASELANDBEAN.floor_1;
        }
        if ("2".equals(string)) {
            return EstimateModel.BASELANDBEAN.floor_2;
        }
        return EstimateModel.BASELANDBEAN.floor_3;
    }

    private void calFloorEffect() {
        for (Node node : this.dataPane.getChildren()) {
            String string;
            if (!(node instanceof AnchorPane) || StringProcess.isEmpty(string = node.getId()) || !this.datas.containsKey(string = string.replaceAll("U", ""))) continue;
            for (Node node2 : ((AnchorPane)node).getChildren()) {
                if (StringProcess.isEmpty(node2.getId()) || !node2.getId().startsWith("inp")) continue;
                this.datas.get(string).setFloorEffectRatio(StringProcess.parserDouble(((TextField)node2).getText()));
            }
        }
        Map<String, Object> map = FloorEffectCalculateTool.calcaultLandPriceAllocationRatio(this.datas, this.cs04f, this.cs48, this.cs47);
        this.datas.putAll((Map)map.get("data"));
        this.inp_avg.setText(this.df.format((Double)map.get("averageFloorEffectRatio")));
        this.lb_avg.setText(this.df.format((Double)map.get("averageLandPriceAllocationRatio")));
        this.ret.setLength(0);
        this.ret.append(this.df.format((Double)map.get("averageLandPriceAllocationRatio"))).append(",").append(this.df.format(this.datas.get(this.cs04f).getLandPriceAllocationRatio()));
        this.createView(this.cs04f);
    }

    private void createView(String string) {
        for (Node node : this.dataPane.getChildren()) {
            String string2;
            if (!(node instanceof AnchorPane) || StringProcess.isEmpty(string2 = node.getId()) || !this.datas.containsKey(string2 = string2.replaceAll("U", ""))) continue;
            node.setDisable(!this.datas.containsKey(string2));
            for (Node node2 : ((AnchorPane)node).getChildren()) {
                if (!StringProcess.isEmpty(node2.getId()) && node2.getId().startsWith("inp")) {
                    ((TextField)node2).setText(this.df.format(this.datas.get(string2).getFloorEffectRatio()));
                }
                if (StringProcess.isEmpty(node2.getId()) || !node2.getId().startsWith("lb_")) continue;
                ((Label)node2).setText(this.df.format(this.datas.get(string2).getLandPriceAllocationRatio()));
                if (!string.equals(string2) || ((Label)node2).getStyleClass().contains((Object)"target")) continue;
                ((Label)node2).getStyleClass().add((Object)"target");
            }
        }
    }

    @Override
    public void refresh() {
    }
}

