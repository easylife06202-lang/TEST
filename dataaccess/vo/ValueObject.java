/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.dataaccess.vo;

import java.io.Serializable;

public abstract class ValueObject
implements Serializable {
    private boolean bnlInsertFlag = false;
    private boolean bnlUpdateFlag = false;
    private boolean bnlDeleteFlag = false;

    public boolean getInsertFlag() {
        return this.bnlInsertFlag;
    }

    public boolean getDeleteFlag() {
        return this.bnlUpdateFlag;
    }

    public boolean getUpdateFlag() {
        return this.bnlDeleteFlag;
    }

    public void setInsertFlag(boolean bl) {
        this.bnlInsertFlag = bl;
        this.bnlUpdateFlag = false;
        this.bnlDeleteFlag = false;
    }

    public void setUpdateFlag(boolean bl) {
        this.bnlInsertFlag = false;
        this.bnlUpdateFlag = bl;
        this.bnlDeleteFlag = false;
    }

    public void setDeleteFlag(boolean bl) {
        this.bnlInsertFlag = false;
        this.bnlUpdateFlag = false;
        this.bnlDeleteFlag = bl;
    }

    public void resetFlags() {
        this.bnlInsertFlag = false;
        this.bnlUpdateFlag = false;
        this.bnlDeleteFlag = false;
    }
}

