/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.embed.swing.SwingFXUtils
 *  javafx.scene.control.Alert
 *  javafx.scene.control.Alert$AlertType
 *  javafx.scene.control.ButtonType
 *  javafx.scene.image.Image
 *  javafx.stage.FileChooser
 *  javafx.stage.FileChooser$ExtensionFilter
 *  javafx.stage.Stage
 *  javafx.stage.Window
 */
package com.wfusion.fx.util;

import com.wfusion.baseland.estimate.EstimateModel;
import com.wfusion.util.StringProcess;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.imageio.ImageIO;

public class ImageProcessBo {
    static int MAX_SIZE = 0x100000;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public byte[] getByteArrayFromImage(Image image) {
        if (image == null) {
            return null;
        }
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage((Image)image, null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] byArray = null;
        try {
            ImageIO.write((RenderedImage)bufferedImage, "jpg", byteArrayOutputStream);
            byArray = byteArrayOutputStream.toByteArray();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
        finally {
            try {
                byteArrayOutputStream.close();
            }
            catch (IOException iOException) {}
        }
        return byArray;
    }

    public byte[] getImageByteArray(int n, int n2, StringBuilder stringBuilder, Stage stage) {
        FileChooser fileChooser = new FileChooser();
        if (!StringProcess.isEmpty(EstimateModel.imagePath)) {
            fileChooser.setInitialDirectory(new File(EstimateModel.imagePath));
        }
        fileChooser.getExtensionFilters().addAll((Object[])new FileChooser.ExtensionFilter[]{new FileChooser.ExtensionFilter("jpg Files", new String[]{"*.jpg"})});
        File file = fileChooser.showOpenDialog((Window)stage);
        Image image = null;
        if (file != null && file.exists()) {
            EstimateModel.imagePath = file.getParent();
            if (file.length() > (long)MAX_SIZE) {
                stringBuilder.append("\u6a94\u6848\u4e0d\u53ef\u8d85\u904e1MB");
                return null;
            }
            image = new Image(file.toURI().toString());
            if (image.getHeight() > (double)n2 || image.getWidth() > (double)n) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "\u5f71\u50cf\u5c3a\u5bf8\u904e\u5927\uff0c\u5982\u8981\u7e7c\u7e8c\u4e0a\u50b3\uff0c\u7cfb\u7d71\u5c07\u6703\u7e2e\u5c0f\u8a72\u5f71\u50cf\uff0c\u8acb\u554f\u662f\u5426\u7e7c\u7e8c\uff1f", new ButtonType[]{ButtonType.YES, ButtonType.NO, ButtonType.CANCEL});
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    double d = image.getHeight() / (double)n2;
                    double d2 = image.getWidth() / (double)n;
                    double d3 = n2;
                    double d4 = n;
                    if (d > d2) {
                        d4 = image.getWidth() / d;
                    } else if (d < d2) {
                        d3 = image.getHeight() / d2;
                    }
                    image = new Image(file.toURI().toString(), d4, d3, true, true);
                } else {
                    return null;
                }
            }
            stringBuilder.append(file.getAbsolutePath());
        }
        return this.getByteArrayFromImage(image);
    }
}

