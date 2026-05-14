/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.print.bean;

public class BaseLandPrintRemarkBean {
    private String title = "";
    private String pageno = "";
    private String remark = "";
    private String baseno = "";

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String string) {
        this.title = string;
    }

    public String getBaseno() {
        return this.baseno;
    }

    public void setBaseno(String string) {
        this.baseno = string;
    }

    public String getPageno() {
        return this.pageno;
    }

    public void setPageno(String string) {
        this.pageno = string;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String string) {
        this.remark = string;
    }
}

