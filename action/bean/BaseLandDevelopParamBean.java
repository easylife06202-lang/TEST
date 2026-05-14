/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.action.bean;

import java.util.HashMap;
import java.util.Map;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_DEVELOP;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_DEVELOP_EXT;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_MAIN;

public class BaseLandDevelopParamBean {
    private NVO_BASELAND_MAIN voMain = new NVO_BASELAND_MAIN();
    private NVO_BASELAND_DEVELOP voDevelop = new NVO_BASELAND_DEVELOP();
    private NVO_BASELAND_DEVELOP_EXT voDevelopExt = new NVO_BASELAND_DEVELOP_EXT();
    private StringBuffer message = new StringBuffer("");
    private boolean success = false;

    public BaseLandDevelopParamBean(NVO_BASELAND_MAIN nVO_BASELAND_MAIN, NVO_BASELAND_DEVELOP nVO_BASELAND_DEVELOP, NVO_BASELAND_DEVELOP_EXT nVO_BASELAND_DEVELOP_EXT) {
        this.voMain = (NVO_BASELAND_MAIN)nVO_BASELAND_MAIN.clone();
        this.voDevelop = nVO_BASELAND_DEVELOP;
        this.voDevelopExt = nVO_BASELAND_DEVELOP_EXT;
    }

    public BaseLandDevelopParamBean(NVO_BASELAND_MAIN nVO_BASELAND_MAIN, HashMap<String, Object> hashMap, boolean bl) {
        this.voMain = (NVO_BASELAND_MAIN)nVO_BASELAND_MAIN.clone();
        this.voDevelop.setBeanByHashMap(hashMap, bl);
        this.voDevelopExt.setBeanByHashMap(hashMap, bl);
        this.verifyInputData();
    }

    private void verifyInputData() {
    }

    public Map<String, Object> getBaseLandDevelopDataMap() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("voMain", this.voMain.getFieldToHashMapExport());
        hashMap.put("voDevelop", this.voDevelop.getFieldToHashMapExport());
        hashMap.put("voDevelopExt", this.voDevelopExt.getFieldToHashMapExport());
        hashMap.put("message", this.message.toString());
        hashMap.put("success", this.success);
        return hashMap;
    }

    public void clearMessage() {
        this.message = new StringBuffer("");
    }

    public StringBuffer getMessage() {
        return this.message;
    }

    public void setMessage(StringBuffer stringBuffer) {
        this.message = stringBuffer;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean bl) {
        this.success = bl;
    }

    public NVO_BASELAND_MAIN getVoMain() {
        return this.voMain;
    }

    public void setVoMain(NVO_BASELAND_MAIN nVO_BASELAND_MAIN) {
        this.voMain = nVO_BASELAND_MAIN;
    }

    public NVO_BASELAND_DEVELOP getVoDevelop() {
        return this.voDevelop;
    }

    public void setVoDevelop(NVO_BASELAND_DEVELOP nVO_BASELAND_DEVELOP) {
        this.voDevelop = nVO_BASELAND_DEVELOP;
    }

    public NVO_BASELAND_DEVELOP_EXT getVoDevelopExt() {
        return this.voDevelopExt;
    }

    public void setVoDevelopExt(NVO_BASELAND_DEVELOP_EXT nVO_BASELAND_DEVELOP_EXT) {
        this.voDevelopExt = nVO_BASELAND_DEVELOP_EXT;
    }
}

