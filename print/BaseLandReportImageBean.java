/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.print;

import java.awt.Image;

public class BaseLandReportImageBean {
    private String picname = "\u57fa\u6e96\u5730\uff1a";
    private String number = "";
    private String land_position = "";
    private String address = "";
    private Image pic1 = null;
    private Image pic2 = null;

    public String getPicname() {
        return this.picname;
    }

    public String getNumber() {
        return this.number;
    }

    public String getLand_position() {
        return this.land_position;
    }

    public String getAddress() {
        return this.address;
    }

    public Image getPic1() {
        return this.pic1;
    }

    public Image getPic2() {
        return this.pic2;
    }

    public void setPicname(String string) {
        this.picname = string;
    }

    public void setNumber(String string) {
        this.number = string;
    }

    public void setLand_position(String string) {
        this.land_position = string;
    }

    public void setAddress(String string) {
        this.address = string;
    }

    public void setPic1(Image image) {
        this.pic1 = image;
    }

    public void setPic2(Image image) {
        this.pic2 = image;
    }
}

