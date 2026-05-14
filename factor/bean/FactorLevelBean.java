/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.factor.bean;

import java.util.ArrayList;
import moiland.baseland.factor.bean.FactorItemBean;
import moiland.baseland.factor.bean.FactorScoreBean;

public class FactorLevelBean {
    private FactorItemBean itemBean = null;
    private ArrayList<FactorScoreBean> lvList = null;

    public FactorLevelBean(FactorItemBean factorItemBean) {
        this.itemBean = factorItemBean;
        this.lvList = new ArrayList();
    }

    public String getItemCode() {
        return this.itemBean.getItemCode();
    }

    public String getItemText() {
        return this.itemBean.getItemText();
    }

    public String getItemField() {
        return this.itemBean.getItemField();
    }

    public int getItemSn() {
        return this.itemBean.getItemSn();
    }

    public ArrayList<FactorScoreBean> getLvList() {
        return this.lvList;
    }

    public void setLvList(ArrayList<FactorScoreBean> arrayList) {
        this.lvList = arrayList;
    }

    public String getLvText(String string) {
        int n;
        String string2 = "";
        if (this.lvList.size() > 0 && string != null && !"".equals(string) && (n = Integer.parseInt(string, 10)) > 0) {
            string2 = this.lvList.get(n - 1).getText();
        }
        return string2;
    }

    public String toString() {
        String string = "\n";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("itemCode=").append(this.getItemCode()).append(string);
        stringBuilder.append("itemField=").append(this.getItemField()).append(string);
        stringBuilder.append("itemSn=").append(this.getItemSn()).append(string);
        stringBuilder.append("itemText=").append(this.getItemText()).append(string);
        stringBuilder.append("lvList=").append(this.lvList).append(string);
        return stringBuilder.toString();
    }
}

