/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.factor.bean;

public class FactorScoreBean {
    private int level = 0;
    private double score = 0.0;
    private String text = "";

    public FactorScoreBean(int n, double d, String string) {
        this.level = n;
        this.score = d;
        this.text = string;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int n) {
        this.level = n;
    }

    public double getScore() {
        return this.score;
    }

    public void setScore(double d) {
        this.score = d;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String string) {
        this.text = string;
    }

    public String toString() {
        return this.getText();
    }
}

