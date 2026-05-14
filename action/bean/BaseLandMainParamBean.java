/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.action.bean;

import com.wfusion.util.StringProcess;
import java.util.HashMap;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_MAIN;
import moiland.baseland.util.BaseLandFactorVersionHelper;
import moiland.baseland.verify.BaseLandVerifyUtil;

public class BaseLandMainParamBean {
    private NVO_BASELAND_MAIN landVo = new NVO_BASELAND_MAIN();
    private String mode = "";
    private StringBuffer message = new StringBuffer("");
    private String baseno1 = "";
    private String baseno2 = "";
    private String urban = "";
    private String baseseq1 = "";
    private String baseseq2 = "";
    private String version = "";
    private boolean success = false;

    public BaseLandMainParamBean(NVO_BASELAND_MAIN nVO_BASELAND_MAIN, String string) {
        this.landVo = nVO_BASELAND_MAIN;
        this.mode = string;
    }

    public BaseLandMainParamBean(HashMap<String, Object> hashMap) throws Exception {
        this.landVo.setBeanByRequest(hashMap, false);
        this.landVo.setCity(BaseLandVerifyUtil.checkCity(this.landVo.getCity()));
        this.landVo.setDist(BaseLandVerifyUtil.checkDist(this.landVo.getDist()));
        this.landVo.setYear(BaseLandVerifyUtil.checkYear(this.landVo.getYear()));
        this.mode = StringProcess.getField(hashMap, "mode");
        if (this.mode.equals("query")) {
            this.baseno1 = BaseLandVerifyUtil.checkBaselandNo(StringProcess.NULL(hashMap.get("baseno1")));
            this.baseseq1 = this.baseno1.substring(5, 9);
            this.baseno2 = BaseLandVerifyUtil.checkBaselandNo(StringProcess.NULL(hashMap.get("baseno2")));
            this.baseseq2 = this.baseno2.substring(5, 9);
            this.urban = BaseLandVerifyUtil.checkBaselandUrbanAllowEmpty(StringProcess.NULL(hashMap.get("urban")));
        } else if (this.mode.equals("add")) {
            this.urban = BaseLandVerifyUtil.checkBaselandUrban(this.landVo.getUrban());
            this.landVo.setUrban(this.urban);
        } else {
            this.landVo.setBaseno(BaseLandVerifyUtil.checkBaselandNo(this.landVo.getBaseno()));
        }
    }

    public HashMap<String, Object> getBaseLandMainHashMap() {
        HashMap<String, Object> hashMap = this.landVo.getFieldToHashMapExport();
        hashMap.remove("geom");
        hashMap.put("mode", this.mode);
        hashMap.put("message", this.message.toString());
        hashMap.put("success", this.success);
        hashMap.put("factorVersionMap", BaseLandFactorVersionHelper.getAvailableListByUrban(this.landVo.getUrban()));
        return hashMap;
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

    public NVO_BASELAND_MAIN getBandLandMainVo() {
        return this.landVo;
    }

    public void setBandLandMainVo(NVO_BASELAND_MAIN nVO_BASELAND_MAIN) {
        this.landVo = nVO_BASELAND_MAIN;
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

    public String getBaseno1() {
        return this.baseno1;
    }

    public String getBaseno2() {
        return this.baseno2;
    }

    public String getUrban() {
        return this.urban;
    }

    public String getBaseseq1() {
        return this.baseseq1;
    }

    public String getBaseseq2() {
        return this.baseseq2;
    }

    public String getVersion() {
        return this.version;
    }
}

