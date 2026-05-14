/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.flooreffect.bean;

public class BaseLandFloorEffectFormBean {
    private String baselandNo = "";
    private String year = "";
    private String caseNo = "";
    private String targetFloor = "";
    private long buildingPrice = 0L;
    private long totalSellPrice = 0L;
    private int totalBasement = 0;
    private int totalFloor = 0;

    public String getKey() {
        return this.baselandNo + this.year + this.caseNo;
    }

    public String getBaselandNo() {
        return this.baselandNo;
    }

    public void setBaselandNo(String string) {
        this.baselandNo = string;
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(String string) {
        this.year = string;
    }

    public String getCaseNo() {
        return this.caseNo;
    }

    public void setCaseNo(String string) {
        this.caseNo = string;
    }

    public String getTargetFloor() {
        return this.targetFloor;
    }

    public void setTargetFloor(String string) {
        this.targetFloor = string;
    }

    public long getBuildingPrice() {
        return this.buildingPrice;
    }

    public void setBuildingPrice(long l) {
        this.buildingPrice = l;
    }

    public long getTotalSellPrice() {
        return this.totalSellPrice;
    }

    public void setTotalSellPrice(long l) {
        this.totalSellPrice = l;
    }

    public int getTotalBasement() {
        return this.totalBasement;
    }

    public void setTotalBasement(int n) {
        this.totalBasement = n;
    }

    public int getTotalFloor() {
        return this.totalFloor;
    }

    public void setTotalFloor(int n) {
        this.totalFloor = n;
    }
}

