/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.action.bean;

import com.wfusion.util.StringProcess;
import java.util.HashMap;
import java.util.TreeMap;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_APPRAISAL;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_APPRAISALA3_SCORE;
import moiland.baseland.verify.BaseLandVerifyUtil;

public class BaseLandAppraisalParamBean {
    private NVO_BASELAND_APPRAISALA3_SCORE tarAppVo = new NVO_BASELAND_APPRAISALA3_SCORE();
    private TreeMap<String, NVO_BASELAND_APPRAISALA3_SCORE> scoreAppMap = new TreeMap();
    private NVO_BASELAND_APPRAISAL resultVo = new NVO_BASELAND_APPRAISAL();
    private String mode = "";
    private boolean autoCal = true;
    private StringBuffer message = new StringBuffer("");
    private boolean success = false;
    private String ofce = "";
    private String urban = "";
    private String baseno = "";
    private String version = "";

    public BaseLandAppraisalParamBean(HashMap<String, Object> hashMap) throws Exception {
        this.resultVo.setBeanByHashMap(hashMap, false);
        this.resultVo.setCity(BaseLandVerifyUtil.checkCity(this.resultVo.getCity()));
        this.resultVo.setDist(BaseLandVerifyUtil.checkDist(this.resultVo.getDist()));
        this.resultVo.setYear(BaseLandVerifyUtil.checkYear(this.resultVo.getYear()));
        this.resultVo.setBaseno(BaseLandVerifyUtil.checkBaselandNo(this.resultVo.getBaseno()));
        this.mode = StringProcess.getField(hashMap, "mode");
        String string = StringProcess.getField(hashMap, "autocal");
        this.ofce = StringProcess.getField(hashMap, "ofce");
        this.autoCal = StringProcess.parserBoolean(string);
        this.urban = this.resultVo.getBaseno().substring(3, 5);
        this.baseno = this.resultVo.getBaseno();
        this.version = StringProcess.getField(hashMap, "version");
    }

    public NVO_BASELAND_APPRAISAL getResultVo() {
        return this.resultVo;
    }

    public void setResultVo(NVO_BASELAND_APPRAISAL nVO_BASELAND_APPRAISAL) {
        this.resultVo = nVO_BASELAND_APPRAISAL;
    }

    public NVO_BASELAND_APPRAISALA3_SCORE getTarAppVo() {
        return this.tarAppVo;
    }

    public void setTarAppVo(NVO_BASELAND_APPRAISALA3_SCORE nVO_BASELAND_APPRAISALA3_SCORE) {
        this.tarAppVo = nVO_BASELAND_APPRAISALA3_SCORE;
    }

    public String getMode() {
        return this.mode;
    }

    public void setMode(String string) {
        this.mode = string;
    }

    public boolean isAutoCal() {
        return this.autoCal;
    }

    public void setAutoCal(boolean bl) {
        this.autoCal = bl;
    }

    public TreeMap<String, NVO_BASELAND_APPRAISALA3_SCORE> getScoreAppMap() {
        return this.scoreAppMap;
    }

    public void setScoreAppMap(TreeMap<String, NVO_BASELAND_APPRAISALA3_SCORE> treeMap) {
        this.scoreAppMap = treeMap;
    }

    public void clearMessage() {
        this.message = new StringBuffer("");
    }

    public StringBuffer getMessage() {
        return this.message;
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

    public String getUrban() {
        return this.urban;
    }

    public String getBaseno() {
        return this.baseno;
    }

    public String getVersion() {
        return this.version;
    }
}

