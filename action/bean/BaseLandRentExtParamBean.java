/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.action.bean;

import com.wfusion.util.StringProcess;
import java.util.HashMap;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT_EXT;
import moiland.baseland.verify.BaseLandVerifyUtil;

public class BaseLandRentExtParamBean {
    private NVO_BASELAND_RENT_EXT cRenextvo = new NVO_BASELAND_RENT_EXT();
    private String mode = "";
    private boolean autoCal = true;
    private StringBuffer message = new StringBuffer("");
    private boolean success = false;
    private String ofce = "";

    public BaseLandRentExtParamBean(HashMap<String, Object> hashMap) throws Exception {
        this.cRenextvo.setBeanByRequest(hashMap, false);
        this.cRenextvo.setCity(BaseLandVerifyUtil.checkCity(this.cRenextvo.getCity()));
        this.cRenextvo.setDist(BaseLandVerifyUtil.checkDist(this.cRenextvo.getDist()));
        this.cRenextvo.setYear(BaseLandVerifyUtil.checkYear(this.cRenextvo.getYear()));
        this.cRenextvo.setBaseno(BaseLandVerifyUtil.checkBaselandNo(this.cRenextvo.getBaseno()));
        this.mode = StringProcess.getField(hashMap, "mode");
        String string = StringProcess.getField(hashMap, "autocal");
        this.autoCal = StringProcess.parserBoolean(string);
        this.ofce = StringProcess.getField(hashMap, "ofce");
    }

    public HashMap<String, Object> getBaseLandRentExtHashMap() {
        HashMap<String, Object> hashMap = this.cRenextvo.getFieldToHashMapExport();
        hashMap.put("mode", this.mode);
        hashMap.put("message", this.message.toString());
        hashMap.put("success", this.success);
        return this.cRenextvo.getFieldToHashMapExport();
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

    public NVO_BASELAND_RENT_EXT getcRenextvo() {
        return this.cRenextvo;
    }

    public void setcRenextvo(NVO_BASELAND_RENT_EXT nVO_BASELAND_RENT_EXT) {
        this.cRenextvo = nVO_BASELAND_RENT_EXT;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean bl) {
        this.success = bl;
    }

    public String getOfce() {
        return this.ofce;
    }

    public void setOfce(String string) {
        this.ofce = string;
    }
}

