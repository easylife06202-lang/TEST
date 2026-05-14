/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.util;

import com.wfusion.util.StringProcess;
import java.math.BigDecimal;

public class BigDecimalUtil {
    private static final int DEF_DIV_SCALE = 10;

    public static double add(double d, double d2) {
        BigDecimal bigDecimal = new BigDecimal(new Double(d).toString());
        BigDecimal bigDecimal2 = new BigDecimal(new Double(d2).toString());
        return bigDecimal.add(bigDecimal2).doubleValue();
    }

    public static double sub(double d, double d2) {
        BigDecimal bigDecimal = new BigDecimal(new Double(d).toString());
        BigDecimal bigDecimal2 = new BigDecimal(new Double(d2).toString());
        return bigDecimal.subtract(bigDecimal2).doubleValue();
    }

    public static double mul(double d, double d2) {
        BigDecimal bigDecimal = new BigDecimal(new Double(d).toString());
        BigDecimal bigDecimal2 = new BigDecimal(new Double(d2).toString());
        return bigDecimal.multiply(bigDecimal2).doubleValue();
    }

    public static double div(double d, double d2) {
        return BigDecimalUtil.div(d, d2, 10);
    }

    public static double div(double d, double d2, int n) {
        if (n < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal bigDecimal = new BigDecimal(new Double(d).toString());
        BigDecimal bigDecimal2 = new BigDecimal(new Double(d2).toString());
        return bigDecimal.divide(bigDecimal2, n, 4).doubleValue();
    }

    public static double divdown(double d, double d2, int n) {
        if (n < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal bigDecimal = new BigDecimal(new Double(d).toString());
        BigDecimal bigDecimal2 = new BigDecimal(new Double(d2).toString());
        return bigDecimal.divide(bigDecimal2, n, 1).doubleValue();
    }

    public static double round(double d, int n) {
        if (n < 0) {
            throw new IllegalArgumentException("The scale must be apositive integer or zero");
        }
        BigDecimal bigDecimal = new BigDecimal(1);
        BigDecimal bigDecimal2 = new BigDecimal(new Double(d).toString());
        return bigDecimal2.divide(bigDecimal, n, 4).doubleValue();
    }

    public static double roundDown(double d, int n) {
        if (n < 0) {
            throw new IllegalArgumentException("The scale must be apositive integer or zero");
        }
        BigDecimal bigDecimal = new BigDecimal(1);
        BigDecimal bigDecimal2 = new BigDecimal(new Double(d).toString());
        return bigDecimal2.divide(bigDecimal, n, 1).doubleValue();
    }

    public static double ceil(double d, int n) {
        return new BigDecimal(String.valueOf(d)).setScale(n, 2).doubleValue();
    }

    public static double ceil(String string, int n) {
        if (StringProcess.isNumber(string)) {
            return BigDecimalUtil.parserDecimal(string).setScale(n, 2).doubleValue();
        }
        System.out.println("\u50b3\u5165\u975e\u6578\u5b57!");
        return 0.0;
    }

    public static double floor(double d, int n) {
        return new BigDecimal(String.valueOf(d)).setScale(n, 3).doubleValue();
    }

    public static double floor(String string, int n) {
        if (StringProcess.isNumber(string)) {
            return BigDecimalUtil.parserDecimal(string).setScale(n, 3).doubleValue();
        }
        System.out.println("\u50b3\u5165\u975e\u6578\u5b57!");
        return 0.0;
    }

    public static double round(String string, int n) {
        return BigDecimalUtil.round(StringProcess.parserDouble(string), n);
    }

    public static BigDecimal parserDecimal(String string) {
        BigDecimal bigDecimal = new BigDecimal(0);
        try {
            bigDecimal = new BigDecimal(string);
        }
        catch (Exception exception) {
            System.out.println("StringProcess.parserDecimal:" + exception);
        }
        return bigDecimal;
    }
}

