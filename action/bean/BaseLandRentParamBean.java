/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.action.bean;

import com.wfusion.util.StringProcess;
import java.util.HashMap;
import java.util.TreeMap;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_RENT_MONTH;
import moiland.baseland.verify.BaseLandVerifyUtil;

public class BaseLandRentParamBean {
    private NVO_BASELAND_RENT cRentvo = new NVO_BASELAND_RENT();
    private TreeMap<String, NVO_BASELAND_RENT_MONTH> monthMap = new TreeMap();
    private String mode = "";
    private boolean autoCal = false;
    private StringBuffer message = new StringBuffer("");
    private boolean success = false;
    private String ofce = "";

    public BaseLandRentParamBean(HashMap<String, Object> hashMap) throws Exception {
        this.cRentvo.setBeanByRequest(hashMap, false);
        this.cRentvo.setCity(BaseLandVerifyUtil.checkCity(this.cRentvo.getCity()));
        this.cRentvo.setDist(BaseLandVerifyUtil.checkDist(this.cRentvo.getDist()));
        this.cRentvo.setYear(BaseLandVerifyUtil.checkYear(this.cRentvo.getYear()));
        this.cRentvo.setBaseno(BaseLandVerifyUtil.checkBaselandNo(this.cRentvo.getBaseno()));
        this.cRentvo.setCr33(StringProcess.unicode2Ascii(this.cRentvo.getCr33()));
        this.cRentvo.setCr39(StringProcess.unicode2Ascii(this.cRentvo.getCr39()));
        this.cRentvo.setCr44(StringProcess.unicode2Ascii(this.cRentvo.getCr44()));
        this.cRentvo.setCre07(StringProcess.parserDouble(StringProcess.getField(hashMap, "cre07")));
        this.cRentvo.setCre25(StringProcess.parserDouble(StringProcess.getField(hashMap, "cre25")));
        this.cRentvo.setCre27(StringProcess.parserDouble(StringProcess.getField(hashMap, "cre27")));
        this.cRentvo.setCre31(StringProcess.parserInt(StringProcess.getField(hashMap, "cre31")));
        this.cRentvo.setCre34(StringProcess.parserInt(StringProcess.getField(hashMap, "cre34")));
        this.cRentvo.setCre29(StringProcess.parserInt(StringProcess.getField(hashMap, "cre29")));
        this.cRentvo.setCre53(StringProcess.parserInt(StringProcess.getField(hashMap, "cre53")));
        this.cRentvo.setCre54(StringProcess.parserInt(StringProcess.getField(hashMap, "cre54")));
        this.mode = StringProcess.getField(hashMap, "mode");
        String string = StringProcess.getField(hashMap, "autocal");
        this.ofce = StringProcess.getField(hashMap, "ofce");
        this.autoCal = StringProcess.parserBoolean(string, false);
    }

    public HashMap<String, Object> getBaseLandRentHashMap() {
        HashMap<String, Object> hashMap = this.cRentvo.getFieldToHashMapExport();
        hashMap.put("mode", this.mode);
        hashMap.put("message", this.message.toString());
        hashMap.put("success", this.success);
        return this.cRentvo.getFieldToHashMapExport();
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

    public NVO_BASELAND_RENT getcRentvo() {
        return this.cRentvo;
    }

    public void setcRentvo(NVO_BASELAND_RENT nVO_BASELAND_RENT) {
        this.cRentvo = nVO_BASELAND_RENT;
    }

    public TreeMap<String, NVO_BASELAND_RENT_MONTH> getMonthMap() {
        return this.monthMap;
    }

    public void setMonthMap(TreeMap<String, NVO_BASELAND_RENT_MONTH> treeMap) {
        this.monthMap = treeMap;
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

