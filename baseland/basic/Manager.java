/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.stage.Stage
 */
package com.wfusion.baseland.basic;

import com.wfusion.baseland.basic.IManager;
import javafx.stage.Stage;

public class Manager
implements IManager {
    protected Stage parentStage;
    protected Stage stage;

    public Stage getParentStage() {
        return this.parentStage;
    }

    public Stage getStage() {
        return this.stage;
    }
}

