/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.fxml.FXML
 *  javafx.scene.Node
 *  javafx.scene.control.Button
 *  javafx.scene.control.TextField
 *  javafx.scene.image.Image
 *  javafx.scene.image.ImageView
 *  javafx.stage.Stage
 */
package com.wfusion.baseland.estimate;

import com.wfusion.baseland.basic.Controller;
import com.wfusion.baseland.basic.IBaseLandDialog;
import com.wfusion.baseland.estimate.EstimateModel;
import com.wfusion.baseland.estimate.ImageBrowserModel;
import com.wfusion.fx.util.ExceptionDialog;
import com.wfusion.fx.util.ImageProcessBo;
import com.wfusion.fx.util.JavaFXUtil;
import com.wfusion.util.StringProcess;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT_MONTH;

public class ImageBrowserRentController
extends Controller {
    @FXML
    private TextField tfImg1;
    @FXML
    private Button btUpload1;
    @FXML
    private ImageView img1;
    @FXML
    private TextField tfImg2;
    @FXML
    private Button btUpload2;
    @FXML
    private ImageView img2;
    @FXML
    private Button btBrowse1;
    @FXML
    private Button btBrowse2;
    @FXML
    private Button btDelete1;
    @FXML
    private Button btDelete2;
    @FXML
    private Button btSave;
    @FXML
    private TextField land_position;
    @FXML
    private TextField addr;
    ImageProcessBo bo = new ImageProcessBo();
    private String prefix;
    private ImageBrowserModel model = new ImageBrowserModel();
    IBaseLandDialog _selfDialog = null;
    NVO_BASELAND_RENT_MONTH rentvo = null;

    public void init(IBaseLandDialog iBaseLandDialog, String string, String string2, NVO_BASELAND_RENT_MONTH nVO_BASELAND_RENT_MONTH) {
        super.init(iBaseLandDialog);
        this.setTitle(string);
        this.prefix = string2;
        this.initButton();
        this._selfDialog = iBaseLandDialog;
        this.rentvo = nVO_BASELAND_RENT_MONTH;
        this.land_position.setText(nVO_BASELAND_RENT_MONTH.getLand_position());
        this.addr.setText(nVO_BASELAND_RENT_MONTH.getAddr());
    }

    private void initButton() {
        this.initImg();
        this.btBrowse1.setOnAction(actionEvent -> {
            Node node = (Node)actionEvent.getSource();
            Stage stage = (Stage)node.getScene().getWindow();
            if (this.chooseImg(stage, this.img1, this.tfImg1)) {
                this.btUpload1.setDisable(false);
            }
        });
        this.btBrowse2.setOnAction(actionEvent -> {
            Node node = (Node)actionEvent.getSource();
            Stage stage = (Stage)node.getScene().getWindow();
            if (this.chooseImg(stage, this.img2, this.tfImg2)) {
                this.btUpload2.setDisable(false);
            }
        });
        this.btUpload1.setOnAction(actionEvent -> {
            String string = this.tfImg1.getText().toString();
            File file = new File(string);
            if (!StringProcess.isEmpty(EstimateModel.BASELANDBEAN.queryBean.office)) {
                if (!StringProcess.isEmpty(string) && file.exists()) {
                    byte[] byArray = this.getImgByteArray(this.img1);
                    try {
                        if (this.model.save(byArray, this.prefix + "1")) {
                            JavaFXUtil.showToastMessageBox(this._selfDialog.getStage(), "\u4e0a\u50b3\u6210\u529f", 500);
                            this.btDelete1.setDisable(false);
                        }
                    }
                    catch (Exception exception) {
                        ExceptionDialog.show(exception);
                    }
                } else {
                    JavaFXUtil.showErrorMessageBox("\u8acb\u5148\u9078\u64c7\u5716\u7247");
                }
            } else {
                JavaFXUtil.showErrorMessageBox("\u8acb\u5148\u9078\u64c7\u57fa\u6e96\u7b2c\u57fa\u5730\u5ea7\u843d");
            }
        });
        this.btUpload2.setOnAction(actionEvent -> {
            String string = this.tfImg2.getText().toString();
            File file = new File(string);
            if (!StringProcess.isEmpty(EstimateModel.BASELANDBEAN.queryBean.office)) {
                if (!StringProcess.isEmpty(string) && file.exists()) {
                    byte[] byArray = this.getImgByteArray(this.img2);
                    try {
                        if (this.model.save(byArray, this.prefix + "2")) {
                            JavaFXUtil.showToastMessageBox(this._selfDialog.getStage(), "\u4e0a\u50b3\u6210\u529f", 500);
                            this.btDelete2.setDisable(false);
                        }
                    }
                    catch (Exception exception) {
                        ExceptionDialog.show(exception);
                    }
                } else {
                    JavaFXUtil.showErrorMessageBox("\u8acb\u5148\u9078\u64c7\u5716\u7247");
                }
            } else {
                JavaFXUtil.showErrorMessageBox("\u8acb\u5148\u9078\u64c7\u57fa\u6e96\u7b2c\u57fa\u5730\u5ea7\u843d");
            }
        });
        this.btDelete1.setOnAction(actionEvent -> {
            try {
                if (this.model.delete(this.prefix + "1")) {
                    JavaFXUtil.showToastMessageBox(this._selfDialog.getStage(), "\u522a\u9664\u6210\u529f", 500);
                    this.img1.setImage(null);
                } else {
                    JavaFXUtil.showToastMessageBox(this._selfDialog.getStage(), "\u522a\u9664\u5931\u6557\uff0c\u8acb\u5617\u8a66\u91cd\u65b0\u8f09\u5165", 500);
                }
            }
            catch (Exception exception) {
                ExceptionDialog.show(exception);
            }
        });
        this.btDelete2.setOnAction(actionEvent -> {
            try {
                if (this.model.delete(this.prefix + "2")) {
                    JavaFXUtil.showToastMessageBox(this._selfDialog.getStage(), "\u522a\u9664\u6210\u529f", 500);
                    this.img2.setImage(null);
                } else {
                    JavaFXUtil.showToastMessageBox(this._selfDialog.getStage(), "\u522a\u9664\u5931\u6557\uff0c\u8acb\u5617\u8a66\u91cd\u65b0\u8f09\u5165", 500);
                }
            }
            catch (Exception exception) {
                ExceptionDialog.show(exception);
            }
        });
        this.btSave.setOnAction(actionEvent -> {
            try {
                if (this.land_position.getText().length() > 300) {
                    JavaFXUtil.showToastMessageBox(this._selfDialog.getStage(), "\u571f\u5730\u6a19\u793a\u4e0d\u53ef\u8d85\u904e300\u5b57\uff0c\u8acb\u4fee\u6b63", 500);
                    return;
                }
                if (this.addr.getText().length() > 100) {
                    JavaFXUtil.showToastMessageBox(this._selfDialog.getStage(), "\u5efa\u7269\u9580\u724c\u4e0d\u53ef\u8d85\u904e100\u5b57\uff0c\u8acb\u4fee\u6b63", 500);
                    return;
                }
                this.rentvo.setLand_position(this.land_position.getText());
                this.rentvo.setAddr(this.addr.getText());
                if (this.model.saveRent(this.rentvo)) {
                    JavaFXUtil.showToastMessageBox(this._selfDialog.getStage(), "\u5132\u5b58\u6210\u529f", 500);
                } else {
                    JavaFXUtil.showToastMessageBox(this._selfDialog.getStage(), "\u5132\u5b58\u5931\u6557\uff0c\u8acb\u5617\u8a66\u91cd\u65b0\u8f09\u5165", 500);
                }
            }
            catch (Exception exception) {
                ExceptionDialog.show(exception);
            }
        });
    }

    private void initImg() {
        byte[] byArray = null;
        byArray = this.model.getImg(this.prefix + "1");
        if (byArray != null) {
            this.img1.setImage(new Image((InputStream)new ByteArrayInputStream(byArray)));
            this.btDelete1.setDisable(false);
        }
        if ((byArray = this.model.getImg(this.prefix + "2")) != null) {
            this.img2.setImage(new Image((InputStream)new ByteArrayInputStream(byArray)));
            this.btDelete2.setDisable(false);
        }
    }

    private byte[] getImgByteArray(ImageView imageView) {
        if (imageView == null) {
            return null;
        }
        byte[] byArray = this.bo.getByteArrayFromImage(imageView.getImage());
        return byArray;
    }

    private boolean chooseImg(Stage stage, ImageView imageView, TextField textField) {
        StringBuilder stringBuilder = new StringBuilder();
        byte[] byArray = this.bo.getImageByteArray((int)imageView.getFitWidth() * 2, (int)imageView.getFitHeight() * 2, stringBuilder, stage);
        boolean bl = false;
        if (byArray != null) {
            imageView.setImage(new Image((InputStream)new ByteArrayInputStream(byArray)));
            textField.setText(stringBuilder.toString());
            bl = true;
        } else if (stringBuilder.length() > 0) {
            JavaFXUtil.showErrorMessageBox(stringBuilder.toString());
        }
        return bl;
    }

    @Override
    public void refresh() {
    }
}

