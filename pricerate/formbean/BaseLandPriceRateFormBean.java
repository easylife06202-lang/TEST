/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.pricerate.formbean;

public class BaseLandPriceRateFormBean {
    private String city = "";
    private String rateType = "";
    private String dist = "";
    private String year = "";
    private String nameOfCity = "";
    private String nameOfDist = "";
    private String nameOfRateType = "";

    public String getCity() {
        return this.city;
    }

    public void setCity(String string) {
        this.city = string;
    }

    public String getRateType() {
        return this.rateType;
    }

    public void setRateType(String string) {
        this.rateType = string;
    }

    public String getDist() {
        return this.dist;
    }

    public void setDist(String string) {
        this.dist = string;
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(String string) {
        this.year = string;
    }

    public String getNameOfCity() {
        return this.nameOfCity;
    }

    public void setNameOfCity(String string) {
        this.nameOfCity = string;
    }

    public String getNameOfDist() {
        return this.nameOfDist;
    }

    public void setNameOfDist(String string) {
        this.nameOfDist = string;
    }

    public String getNameOfRateType() {
        return this.nameOfRateType;
    }

    public void setNameOfRateType(String string) {
        this.nameOfRateType = string;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("BaseLandPriceRateFormBean [city=");
        stringBuilder.append(this.city);
        stringBuilder.append(", rateType=");
        stringBuilder.append(this.rateType);
        stringBuilder.append(", dist=");
        stringBuilder.append(this.dist);
        stringBuilder.append(", year=");
        stringBuilder.append(this.year);
        stringBuilder.append(", nameOfCity=");
        stringBuilder.append(this.nameOfCity);
        stringBuilder.append(", nameOfDist=");
        stringBuilder.append(this.nameOfDist);
        stringBuilder.append(", nameOfRateType=");
        stringBuilder.append(this.nameOfRateType);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}

