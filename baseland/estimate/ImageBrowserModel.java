/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.baseland.estimate;

import com.wfusion.baseland.basic.Model;
import com.wfusion.baseland.estimate.EstimateModel;
import com.wfusion.util.SqlUtil;
import java.sql.Connection;
import java.sql.SQLException;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_IMAGES;
import moiland.baseland.dataaccess.ndao.NDAO_BASELAND_RENT_MONTH;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_IMAGES;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT_MONTH;

public class ImageBrowserModel
extends Model {
    public static final String BASELAND_IMAGES_TYPE_SKT = "SKT";
    public static final String BASELAND_IMAGES_PHOTO_TYPE_BASE = "BASE";
    public static final String BASELAND_IMAGES_PHOTO_TYPE_SELL1 = "SELL1";
    public static final String BASELAND_IMAGES_PHOTO_TYPE_SELL2 = "SELL2";
    public static final String BASELAND_IMAGES_PHOTO_TYPE_SELL3 = "SELL3";
    public static final String BASELAND_IMAGES_PHOTO_TYPE_RENT1 = "RENT1";
    public static final String BASELAND_IMAGES_PHOTO_TYPE_RENT2 = "RENT2";
    public static final String BASELAND_IMAGES_PHOTO_TYPE_RENT3 = "RENT3";

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean save(byte[] byArray, String string) throws Exception {
        Connection connection = null;
        try {
            connection = this.getVersionConnection();
            NDAO_BASELAND_IMAGES nDAO_BASELAND_IMAGES = new NDAO_BASELAND_IMAGES();
            NVO_BASELAND_IMAGES nVO_BASELAND_IMAGES = new NVO_BASELAND_IMAGES();
            nVO_BASELAND_IMAGES.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
            nVO_BASELAND_IMAGES.setBaseno(EstimateModel.BASELANDBEAN.queryBean.baseno);
            nVO_BASELAND_IMAGES.setCity(EstimateModel.BASELANDBEAN.queryBean.AA45);
            nVO_BASELAND_IMAGES.setDist(EstimateModel.BASELANDBEAN.queryBean.AA46);
            nVO_BASELAND_IMAGES.setOfce(EstimateModel.BASELANDBEAN.queryBean.office);
            nVO_BASELAND_IMAGES.setPhoto_type(string);
            nVO_BASELAND_IMAGES.setPhoto(byArray);
            if (nDAO_BASELAND_IMAGES.isExist(nVO_BASELAND_IMAGES, connection)) {
                nDAO_BASELAND_IMAGES.delete(nVO_BASELAND_IMAGES, connection);
            }
            nDAO_BASELAND_IMAGES.create(nVO_BASELAND_IMAGES, connection);
            boolean bl = true;
            return bl;
        }
        finally {
            SqlUtil.close(connection);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public byte[] getImg(String string) {
        Connection connection = null;
        try {
            connection = this.getVersionConnection();
            NVO_BASELAND_IMAGES nVO_BASELAND_IMAGES = new NVO_BASELAND_IMAGES();
            nVO_BASELAND_IMAGES.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
            nVO_BASELAND_IMAGES.setBaseno(EstimateModel.BASELANDBEAN.queryBean.baseno);
            nVO_BASELAND_IMAGES.setPhoto_type(string);
            nVO_BASELAND_IMAGES = (NVO_BASELAND_IMAGES)new NDAO_BASELAND_IMAGES().findByPk(nVO_BASELAND_IMAGES, connection);
            if (nVO_BASELAND_IMAGES != null) {
                byte[] byArray = nVO_BASELAND_IMAGES.getPhoto();
                return byArray;
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            SqlUtil.close(connection);
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean delete(String string) throws Exception {
        Connection connection = null;
        try {
            connection = this.getVersionConnection();
            NDAO_BASELAND_IMAGES nDAO_BASELAND_IMAGES = new NDAO_BASELAND_IMAGES();
            NVO_BASELAND_IMAGES nVO_BASELAND_IMAGES = new NVO_BASELAND_IMAGES();
            nVO_BASELAND_IMAGES.setYear(EstimateModel.BASELANDBEAN.queryBean.year);
            nVO_BASELAND_IMAGES.setBaseno(EstimateModel.BASELANDBEAN.queryBean.baseno);
            nVO_BASELAND_IMAGES.setPhoto_type(string);
            if (nDAO_BASELAND_IMAGES.isExist(nVO_BASELAND_IMAGES, connection)) {
                nDAO_BASELAND_IMAGES.delete(nVO_BASELAND_IMAGES, connection);
            }
            boolean bl = true;
            return bl;
        }
        finally {
            SqlUtil.close(connection);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean saveRent(NVO_BASELAND_RENT_MONTH nVO_BASELAND_RENT_MONTH) throws SQLException {
        Connection connection = null;
        boolean bl = false;
        try {
            connection = this.getVersionConnection();
            NDAO_BASELAND_RENT_MONTH nDAO_BASELAND_RENT_MONTH = new NDAO_BASELAND_RENT_MONTH();
            if (nDAO_BASELAND_RENT_MONTH.isExist(nVO_BASELAND_RENT_MONTH, connection)) {
                nDAO_BASELAND_RENT_MONTH.delete(nVO_BASELAND_RENT_MONTH, connection);
            }
            nDAO_BASELAND_RENT_MONTH.create(nVO_BASELAND_RENT_MONTH, connection);
            bl = true;
        }
        finally {
            SqlUtil.close(connection);
        }
        return bl;
    }
}

