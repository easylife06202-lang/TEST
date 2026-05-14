/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.util;

import com.wfusion.util.StringProcess;
import java.util.ArrayList;

public class ArraysUtils {
    public static final boolean[] extend(boolean[] blArray) {
        int n = blArray.length;
        boolean[] blArray2 = new boolean[n + 1];
        System.arraycopy(blArray, 0, blArray2, 0, n);
        return blArray2;
    }

    public static final byte[] extend(byte[] byArray) {
        int n = byArray.length;
        byte[] byArray2 = new byte[n + 1];
        System.arraycopy(byArray, 0, byArray2, 0, n);
        return byArray2;
    }

    public static final char[] extend(char[] cArray) {
        int n = cArray.length;
        char[] cArray2 = new char[n + 1];
        System.arraycopy(cArray, 0, cArray2, 0, n);
        return cArray2;
    }

    public static final short[] extend(short[] sArray) {
        int n = sArray.length;
        short[] sArray2 = new short[n + 1];
        System.arraycopy(sArray, 0, sArray2, 0, n);
        return sArray2;
    }

    public static final int[] extend(int[] nArray) {
        int n = nArray.length;
        int[] nArray2 = new int[n + 1];
        System.arraycopy(nArray, 0, nArray2, 0, n);
        return nArray2;
    }

    public static final long[] extend(long[] lArray) {
        int n = lArray.length;
        long[] lArray2 = new long[n + 1];
        System.arraycopy(lArray, 0, lArray2, 0, n);
        return lArray2;
    }

    public static final float[] extend(float[] fArray) {
        int n = fArray.length;
        float[] fArray2 = new float[n + 1];
        System.arraycopy(fArray, 0, fArray2, 0, n);
        return fArray2;
    }

    public static final double[] extend(double[] dArray) {
        int n = dArray.length;
        double[] dArray2 = new double[n + 1];
        System.arraycopy(dArray, 0, dArray2, 0, n);
        return dArray2;
    }

    public static final String[] extend(String[] stringArray) {
        int n = stringArray.length;
        String[] stringArray2 = new String[n + 1];
        System.arraycopy(stringArray, 0, stringArray2, 0, n);
        return stringArray2;
    }

    public static final Object[] extend(Object[] objectArray) {
        int n = objectArray.length;
        Object[] objectArray2 = new Object[n + 1];
        System.arraycopy(objectArray, 0, objectArray2, 0, n);
        return objectArray2;
    }

    public static final boolean[] extend(boolean[] blArray, int n) {
        boolean[] blArray2 = new boolean[n];
        System.arraycopy(blArray, 0, blArray2, 0, blArray.length);
        return blArray2;
    }

    public static final char[] extend(char[] cArray, int n) {
        char[] cArray2 = new char[n];
        System.arraycopy(cArray, 0, cArray2, 0, cArray.length);
        return cArray2;
    }

    public static final short[] extend(short[] sArray, int n) {
        short[] sArray2 = new short[n];
        System.arraycopy(sArray, 0, sArray2, 0, sArray.length);
        return sArray2;
    }

    public static final int[] extend(int[] nArray, int n) {
        int[] nArray2 = new int[n];
        System.arraycopy(nArray, 0, nArray2, 0, nArray.length);
        return nArray2;
    }

    public static final long[] extend(long[] lArray, int n) {
        long[] lArray2 = new long[n];
        System.arraycopy(lArray, 0, lArray2, 0, lArray.length);
        return lArray2;
    }

    public static final float[] extend(float[] fArray, int n) {
        float[] fArray2 = new float[n];
        System.arraycopy(fArray, 0, fArray2, 0, fArray.length);
        return fArray2;
    }

    public static final double[] extend(double[] dArray, int n) {
        double[] dArray2 = new double[n];
        System.arraycopy(dArray, 0, dArray2, 0, dArray.length);
        return dArray2;
    }

    public static final String[] extend(String[] stringArray, int n) {
        String[] stringArray2 = new String[n];
        System.arraycopy(stringArray, 0, stringArray2, 0, stringArray.length);
        return stringArray2;
    }

    public static final Object[] extend(Object[] objectArray, int n) {
        Object[] objectArray2 = new Object[n];
        System.arraycopy(objectArray, 0, objectArray2, 0, objectArray.length);
        return objectArray2;
    }

    public static final boolean[] append(boolean[] blArray, boolean bl) {
        int n = blArray.length;
        boolean[] blArray2 = new boolean[n + 1];
        System.arraycopy(blArray, 0, blArray2, 0, n);
        blArray2[n] = bl;
        return blArray2;
    }

    public static final char[] append(char[] cArray, char c) {
        int n = cArray.length;
        char[] cArray2 = new char[n + 1];
        System.arraycopy(cArray, 0, cArray2, 0, n);
        cArray2[n] = c;
        return cArray2;
    }

    public static final short[] append(short[] sArray, short s) {
        int n = sArray.length;
        short[] sArray2 = new short[n + 1];
        System.arraycopy(sArray, 0, sArray2, 0, n);
        sArray2[n] = s;
        return sArray2;
    }

    public static final int[] append(int[] nArray, int n) {
        int n2 = nArray.length;
        int[] nArray2 = new int[n2 + 1];
        System.arraycopy(nArray, 0, nArray2, 0, n2);
        nArray2[n2] = n;
        return nArray2;
    }

    public static final long[] append(long[] lArray, long l) {
        int n = lArray.length;
        long[] lArray2 = new long[n + 1];
        System.arraycopy(lArray, 0, lArray2, 0, n);
        lArray2[n] = l;
        return lArray2;
    }

    public static final float[] append(float[] fArray, float f) {
        int n = fArray.length;
        float[] fArray2 = new float[n + 1];
        System.arraycopy(fArray, 0, fArray2, 0, n);
        fArray2[n] = f;
        return fArray2;
    }

    public static final double[] append(double[] dArray, double d) {
        int n = dArray.length;
        double[] dArray2 = new double[n + 1];
        System.arraycopy(dArray, 0, dArray2, 0, n);
        dArray2[n] = d;
        return dArray2;
    }

    public static final String[] append(String[] stringArray, String string) {
        int n = stringArray.length;
        String[] stringArray2 = new String[n + 1];
        System.arraycopy(stringArray, 0, stringArray2, 0, n);
        stringArray2[n] = string;
        return stringArray2;
    }

    public static final String[] append(String[] stringArray, String[] stringArray2) {
        int n = stringArray.length;
        String[] stringArray3 = new String[n + stringArray2.length];
        System.arraycopy(stringArray, 0, stringArray3, 0, n);
        System.arraycopy(stringArray2, 0, stringArray3, n, stringArray2.length);
        return stringArray3;
    }

    public static final Object[] append(Object[] objectArray, Object object) {
        int n = objectArray.length;
        Object[] objectArray2 = new Object[n + 1];
        System.arraycopy(objectArray, 0, objectArray2, 0, n);
        objectArray2[n] = object;
        return objectArray2;
    }

    public static final byte[] append(byte[] byArray, byte[] byArray2, int n) {
        byte[] byArray3 = new byte[byArray.length + n];
        System.arraycopy(byArray, 0, byArray3, 0, byArray.length);
        System.arraycopy(byArray2, 0, byArray3, byArray.length, n);
        return byArray3;
    }

    public static final String[] extend(String[] stringArray, int n, String string) {
        String[] stringArray2 = new String[n];
        for (int i = 0; i < n; ++i) {
            stringArray2[i] = string;
        }
        return ArraysUtils.append(stringArray, stringArray2);
    }

    public static final String[] init(int n, String string) {
        return ArraysUtils.extend(new String[0], n, string);
    }

    public static final ArrayList<Object> array2List(Object[] objectArray) {
        if (objectArray == null) {
            objectArray = new Object[]{};
        }
        ArrayList<Object> arrayList = new ArrayList<Object>();
        for (int i = 0; i < objectArray.length; ++i) {
            arrayList.add(objectArray[i]);
        }
        return arrayList;
    }

    public static String[] list2Array(ArrayList<? extends String> arrayList) {
        if (arrayList == null) {
            return new String[0];
        }
        return arrayList.toArray(new String[0]);
    }

    public static String[] reverse(String[] stringArray) {
        String[] stringArray2 = new String[stringArray.length];
        for (int i = 0; i < stringArray.length; ++i) {
            stringArray2[i] = stringArray[stringArray.length - i - 1];
        }
        return stringArray2;
    }

    public static ArrayList<Object> subList(ArrayList<?> arrayList, int n) {
        ArrayList<Object> arrayList2 = new ArrayList<Object>();
        if (arrayList != null) {
            for (int i = 0; i < arrayList.size(); i += n) {
                arrayList2.add(new ArrayList(arrayList.subList(i, i + n > arrayList.size() ? arrayList.size() : i + n)));
            }
        }
        return arrayList2;
    }

    public static String[] StringArrayJoin(String[] stringArray, String[] stringArray2) {
        if (stringArray == null && stringArray2 == null) {
            return null;
        }
        if ((stringArray == null || stringArray.length == 0) && stringArray2 != null) {
            return stringArray2;
        }
        if ((stringArray2 == null || stringArray2.length == 0) && stringArray != null) {
            return stringArray;
        }
        String[] stringArray3 = new String[stringArray.length + stringArray2.length];
        System.arraycopy(stringArray, 0, stringArray3, 0, stringArray.length);
        System.arraycopy(stringArray2, 0, stringArray3, stringArray.length, stringArray2.length);
        return stringArray3;
    }

    public static String[][] split2(String string, String string2, String string3) {
        string2 = StringProcess.EMPTY(string2, ";");
        string3 = StringProcess.EMPTY(string3, ":");
        String[][] stringArray = null;
        String[] stringArray2 = StringProcess.split(string, string2);
        stringArray = new String[stringArray2.length][0];
        for (int i = 0; i < stringArray2.length; ++i) {
            stringArray[i] = StringProcess.split(stringArray2[i], string3);
        }
        return stringArray;
    }

    public static ArrayList<ArrayList<Object>> split(ArrayList<Object> arrayList, int n, boolean bl) {
        ArrayList<Integer> arrayList2 = new ArrayList<Integer>();
        ArrayList<ArrayList<Object>> arrayList3 = new ArrayList<ArrayList<Object>>();
        int n2 = 0;
        while ((double)n2 < (double)arrayList.size() * 1.0 / (double)n) {
            if ((double)(n2 + 1) > (double)arrayList.size() * 1.0 / (double)n) {
                arrayList2.add(arrayList.size() % n);
            } else {
                arrayList2.add(n);
            }
            ++n2;
        }
        for (n2 = arrayList2.size() - 1; n2 >= 0; --n2) {
            int n3 = (Integer)arrayList2.get(n2);
            ArrayList<Object> arrayList4 = new ArrayList<Object>();
            int n4 = n2 * n;
            int n5 = n4 + (Integer)arrayList2.get(n2);
            arrayList4.addAll(arrayList.subList(n4, n5));
            arrayList3.add(0, arrayList4);
            if (!bl) continue;
            for (int i = 0; i < n3; ++i) {
                arrayList.remove(arrayList.size() - 1);
            }
        }
        return arrayList3;
    }
}

