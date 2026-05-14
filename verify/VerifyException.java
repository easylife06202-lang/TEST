/*
 * Decompiled with CFR 0.152.
 */
package moiland.baseland.verify;

public class VerifyException
extends Exception {
    private static final long serialVersionUID = -2734505339654248911L;

    public VerifyException(String string) {
        super(string);
    }

    public VerifyException() {
        this("verify error.");
    }
}

