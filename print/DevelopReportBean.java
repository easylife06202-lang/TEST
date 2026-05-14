/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.print;

import moiland.baseland.dataaccess.nvo.NVO_BASELAND_DEVELOP;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_DEVELOP_EXT;
import moiland.baseland.dataaccess.nvo.NVO_BASELAND_MAIN;

public class DevelopReportBean {
    private NVO_BASELAND_MAIN voMain = null;
    private NVO_BASELAND_DEVELOP voDevp = null;
    private NVO_BASELAND_DEVELOP_EXT voExt = null;
    private String notes = "";

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String string) {
        this.voDevp.setNotes(string);
        this.notes = string;
    }

    public DevelopReportBean(NVO_BASELAND_MAIN nVO_BASELAND_MAIN, NVO_BASELAND_DEVELOP nVO_BASELAND_DEVELOP, NVO_BASELAND_DEVELOP_EXT nVO_BASELAND_DEVELOP_EXT) {
        this.voMain = nVO_BASELAND_MAIN;
        this.voDevp = nVO_BASELAND_DEVELOP;
        this.voExt = nVO_BASELAND_DEVELOP_EXT;
        this.notes = this.voDevp.getNotes();
    }

    public DevelopReportBean(NVO_BASELAND_MAIN nVO_BASELAND_MAIN, NVO_BASELAND_DEVELOP_EXT nVO_BASELAND_DEVELOP_EXT) {
        this.voMain = nVO_BASELAND_MAIN;
        this.voExt = nVO_BASELAND_DEVELOP_EXT;
    }

    public NVO_BASELAND_MAIN getVoMain() {
        return this.voMain;
    }

    public NVO_BASELAND_DEVELOP getVoDevp() {
        return this.voDevp;
    }

    public NVO_BASELAND_DEVELOP_EXT getVoExt() {
        return this.voExt;
    }
}

