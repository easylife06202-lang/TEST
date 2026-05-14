/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.instru.bean;

public class BaseLandInstruStdPriceBean {
    private String city = "";
    private String instruCode = "";
    private String instruName = "";
    private int floor = 0;
    private int price = 0;

    public BaseLandInstruStdPriceBean(String string, String string2, int n, int n2) {
        this.city = string;
        this.instruCode = string2;
        this.floor = n;
        this.price = n2;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String string) {
        this.city = string;
    }

    public String getInstruCode() {
        return this.instruCode;
    }

    public void setInstruCode(String string) {
        this.instruCode = string;
    }

    public String getInstruName() {
        return this.instruName;
    }

    public void setInstruName(String string) {
        this.instruName = string;
    }

    public int getFloor() {
        return this.floor;
    }

    public void setFloor(int n) {
        this.floor = n;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int n) {
        this.price = n;
    }
}

