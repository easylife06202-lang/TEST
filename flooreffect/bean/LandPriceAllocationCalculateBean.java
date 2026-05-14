/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.flooreffect.bean;

public class LandPriceAllocationCalculateBean {
    private String floor = "";
    private double floorEffectRatio = 0.0;
    private double buildingEffectRatio = 0.0;
    private double landPriceAllocationRatio = 0.0;

    public String getFloor() {
        return this.floor;
    }

    public void setFloor(String string) {
        this.floor = string;
    }

    public double getFloorEffectRatio() {
        return this.floorEffectRatio;
    }

    public void setFloorEffectRatio(double d) {
        this.floorEffectRatio = d;
    }

    public double getBuildingEffectRatio() {
        return this.buildingEffectRatio;
    }

    public void setBuildingEffectRatio(double d) {
        this.buildingEffectRatio = d;
    }

    public double getLandPriceAllocationRatio() {
        return this.landPriceAllocationRatio;
    }

    public void setLandPriceAllocationRatio(double d) {
        this.landPriceAllocationRatio = d;
    }
}

