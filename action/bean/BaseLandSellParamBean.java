/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.action.bean;

import com.wfusion.util.StringProcess;
import java.util.HashMap;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_SELL;
import moiland.baseland.verify.BaseLandVerifyUtil;

public class BaseLandSellParamBean {
    private NVO_BASELAND_SELL sellVo = new NVO_BASELAND_SELL();
    private String mode = "";
    private boolean autoCal = true;
    private boolean defaultRatio = false;
    private StringBuffer message = new StringBuffer("");
    private String ofce = "";
    private boolean success = false;

    public BaseLandSellParamBean(NVO_BASELAND_SELL nVO_BASELAND_SELL, String string) throws Exception {
        this.sellVo = nVO_BASELAND_SELL;
        this.sellVo.setCs52(StringProcess.unicode2Ascii(this.sellVo.getCs52()));
        this.sellVo.setCs58(StringProcess.unicode2Ascii(this.sellVo.getCs58()));
        this.sellVo.setCs56(StringProcess.unicode2Ascii(this.sellVo.getCs56()));
        this.sellVo.setCs01(StringProcess.unicode2Ascii(this.sellVo.getCs01()));
        this.sellVo.setLand_position(StringProcess.unicode2Ascii(this.sellVo.getLand_position()));
        this.mode = string;
    }

    public BaseLandSellParamBean(HashMap<String, Object> hashMap) throws Exception {
        this.sellVo.setBeanByRequest(hashMap, false);
        this.sellVo.setCity(BaseLandVerifyUtil.checkCity(this.sellVo.getCity()));
        this.sellVo.setDist(BaseLandVerifyUtil.checkDist(this.sellVo.getDist()));
        this.sellVo.setYear(BaseLandVerifyUtil.checkYear(this.sellVo.getYear()));
        this.sellVo.setCaseno(BaseLandVerifyUtil.checkCasenoAllowEmpty(this.sellVo.getCaseno()));
        this.sellVo.setBaseno(BaseLandVerifyUtil.checkBaselandNo(this.sellVo.getBaseno()));
        this.sellVo.setCs52(StringProcess.unicode2Ascii(this.sellVo.getCs52()));
        this.sellVo.setCs58(StringProcess.unicode2Ascii(this.sellVo.getCs58()));
        this.sellVo.setCs56(StringProcess.unicode2Ascii(this.sellVo.getCs56()));
        this.ofce = StringProcess.getField(hashMap, "ofce");
        this.mode = StringProcess.getField(hashMap, "mode");
        String string = StringProcess.getField(hashMap, "autocal");
        this.autoCal = StringProcess.parserBoolean(string);
        this.defaultRatio = StringProcess.parserBoolean(StringProcess.getField(hashMap, "defaultRatio"));
    }

    public HashMap<String, Object> getBaseLandSellHashMap() {
        HashMap<String, Object> hashMap = this.sellVo.getFieldToHashMapExport();
        hashMap.put("mode", this.mode);
        hashMap.put("message", this.message.toString());
        hashMap.put("success", this.success);
        return this.sellVo.getFieldToHashMapExport();
    }

    public void clearMessage() {
        this.message = new StringBuffer("");
    }

    public String getMode() {
        return this.mode;
    }

    public void setMode(String string) {
        this.mode = string;
    }

    public NVO_BASELAND_SELL getSellVo() {
        return this.sellVo;
    }

    public void setSellVo(NVO_BASELAND_SELL nVO_BASELAND_SELL) {
        this.sellVo = nVO_BASELAND_SELL;
    }

    public StringBuffer getMessage() {
        return this.message;
    }

    public void setMessage(StringBuffer stringBuffer) {
        this.message = stringBuffer;
    }

    public boolean isAutoCal() {
        return this.autoCal;
    }

    public void setAutoCal(boolean bl) {
        this.autoCal = bl;
    }

    public boolean isDefaultRatio() {
        return this.defaultRatio;
    }

    public void setDefaultRatio(boolean bl) {
        this.defaultRatio = bl;
    }

    public String getOfce() {
        return this.ofce;
    }

    public void setOfce(String string) {
        this.ofce = string;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean bl) {
        this.success = bl;
    }
}

