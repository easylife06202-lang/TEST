/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.util;

import com.wfusion.util.BigDecimalUtil;
import com.wfusion.util.StringProcess;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.TreeSet;

public class DateTime {
    private static SimpleDateFormat FORMAT_PATTERN = new SimpleDateFormat();
    private static SimpleDateFormat formatter = new SimpleDateFormat(" yyyy.MM.dd 'at' HH:mm:ss:SSS");
    private static SimpleDateFormat formatter14 = new SimpleDateFormat("yyyyMMddHHmmss");
    private static SimpleDateFormat formatterShort = new SimpleDateFormat("HH:mm:ss");

    public static String getTaiwanToday() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String string = simpleDateFormat.format(new Date());
        String string2 = String.valueOf(Integer.parseInt(string.substring(0, 4)) - 1911);
        return StringProcess.fillZero(string2, 3) + string.substring(4);
    }

    public static String getDateText(String string) {
        StringBuffer stringBuffer = new StringBuffer();
        if (string.length() == 7) {
            stringBuffer.append(string.substring(0, 3) + "\u5e74");
            stringBuffer.append((string.substring(3, 4).equals("0") ? string.substring(4, 5) : string.substring(3, 5)) + "\u6708");
            stringBuffer.append((string.substring(5, 6).equals("0") ? string.substring(6) : string.substring(5)) + "\u65e5");
        } else {
            stringBuffer.append(string);
        }
        return stringBuffer.toString();
    }

    public static String getVaule(String string) {
        return DateTime.getVaule(string, new Date());
    }

    public static String getVaule(String string, Date date) {
        FORMAT_PATTERN.applyPattern(string);
        return DateTime.format(date);
    }

    private static String format(Date date) {
        return FORMAT_PATTERN.format(date).toString();
    }

    public static String getYear() {
        return DateTime.getVaule("yyyy");
    }

    public static String getMonth() {
        return DateTime.getVaule("MM");
    }

    public static String getDay() {
        return DateTime.getVaule("dd");
    }

    public static String getHour() {
        return DateTime.getVaule("kk");
    }

    public static String getMin() {
        return DateTime.getVaule("mm");
    }

    public static String getSec() {
        return DateTime.getVaule("ss");
    }

    public static String getTWYear() {
        int n = Integer.parseInt(DateTime.getVaule("yyyy")) - 1911;
        return (n < 100 ? "0" : "") + String.valueOf(n);
    }

    public static String getChYear4Price() {
        int n = StringProcess.parserInt(DateTime.getTWYear()) + 1;
        int n2 = StringProcess.parserInt(DateTime.getMonth());
        if (n2 >= 9) {
            ++n;
        }
        return StringProcess.fillZero(n, 3);
    }

    public ArrayList<String> getYearList(int n, int n2, boolean bl) {
        ArrayList<String> arrayList = new ArrayList<String>();
        int n3 = Math.abs(n - n2);
        boolean bl2 = n <= n2;
        for (int i = 0; i <= n3; ++i) {
            int n4 = bl2 ? n + i : n - i;
            String string = StringProcess.fillZero(n4, 3);
            if (bl) {
                string = string + "," + string;
            }
            arrayList.add(string);
        }
        return arrayList;
    }

    public ArrayList<String> getNumberList(int n, int n2, int n3, boolean bl) {
        ArrayList<String> arrayList = new ArrayList<String>();
        int n4 = Math.abs(n - n2);
        boolean bl2 = n <= n2;
        for (int i = 0; i <= n4; ++i) {
            int n5 = bl2 ? n + i : n - i;
            String string = StringProcess.fillZero(n5, n3);
            if (bl) {
                string = string + "," + string;
            }
            arrayList.add(string);
        }
        return arrayList;
    }

    public ArrayList<String> getYearList(int n, int n2, boolean bl, boolean bl2) {
        ArrayList<String> arrayList = new ArrayList();
        int n3 = StringProcess.parserInt(DateTime.getTWYear());
        arrayList = bl ? this.getYearList(n3 - n, n3 + n2, bl2) : this.getYearList(n3 + n2, n3 - n, bl2);
        return arrayList;
    }

    public ArrayList<String> getYearList(String string, int n, int n2, boolean bl, boolean bl2) {
        ArrayList<String> arrayList = new ArrayList();
        int n3 = StringProcess.parserInt(string);
        arrayList = bl ? this.getYearList(n3 - n, n3 + n2, bl2) : this.getYearList(n3 + n2, n3 - n, bl2);
        return arrayList;
    }

    public ArrayList<String> getYearList(ArrayList<String> arrayList, int n, int n2, boolean bl, boolean bl2) {
        ArrayList<String> arrayList2 = new ArrayList();
        if (arrayList.size() > 0) {
            int n3 = StringProcess.parserInt(arrayList.get(0).split(",")[0]);
            int n4 = StringProcess.parserInt(arrayList.get(arrayList.size() - 1).split(",")[0]);
            arrayList2 = bl ? (n3 > n4 ? this.getYearList(n4 - n, n3 + n2, bl2) : this.getYearList(n3 - n, n4 + n2, bl2)) : (n3 > n4 ? this.getYearList(n3 + n2, n4 - n, bl2) : this.getYearList(n4 + n2, n3 - n, bl2));
        } else {
            arrayList2 = this.getYearList(n, n2, bl, bl2);
        }
        return arrayList2;
    }

    public String getFullDate() {
        return DateTime.getYear() + DateTime.getMonth() + DateTime.getDay();
    }

    public String getFullChDate() {
        return DateTime.getTWYear() + DateTime.getMonth() + DateTime.getDay();
    }

    public String getFullTime() {
        return DateTime.getHour() + DateTime.getMin() + DateTime.getSec();
    }

    public static String getCurrentTime() {
        return formatter.format(Calendar.getInstance().getTime());
    }

    public static String getCurrentTime14() {
        return formatter14.format(Calendar.getInstance().getTime());
    }

    public static String getCurrentShortTime() {
        return formatterShort.format(Calendar.getInstance().getTime());
    }

    public static ArrayList<String> getPeriodList(ArrayList<String> arrayList) {
        TreeSet<Integer> treeSet = new TreeSet<Integer>();
        for (String arrayList22 : arrayList) {
            treeSet.add(Integer.valueOf(arrayList22));
        }
        TreeSet treeSet2 = new TreeSet();
        Iterator iterator = treeSet.iterator();
        while (iterator.hasNext()) {
            int n = DateTime.calculatePeriod((Integer)iterator.next());
            treeSet2.add(n);
        }
        ArrayList<String> arrayList2 = new ArrayList<String>();
        Iterator iterator2 = treeSet2.iterator();
        while (iterator2.hasNext()) {
            Integer n = (Integer)iterator2.next();
            arrayList2.add(n.toString());
        }
        return arrayList2;
    }

    public static int calculatePeriod(String string) {
        return DateTime.calculatePeriod(Integer.valueOf(string));
    }

    private static int calculatePeriod(int n) {
        int n2 = 3;
        return (int)BigDecimalUtil.ceil(BigDecimalUtil.div(n, n2), 0);
    }

    public static String[] getMonth(String string, int n) {
        int n2 = StringProcess.parserInt(string);
        int n3 = 4;
        int n4 = 3;
        String[] stringArray = new String[n4];
        if (n2 > 0 && n2 <= n3) {
            int n5 = Integer.valueOf(string) * n4;
            for (int i = n5 - (n4 - 1); i <= n5; ++i) {
                stringArray[(i - 1) % n4] = StringProcess.fillZero(i, n);
            }
        }
        return stringArray;
    }

    public static boolean checkTwDateByValue(String string) {
        String string2 = "([01][0-9]{2})([0][1-9]|[1][012])([0][1-9]|[12][0-9]|[3][01])";
        boolean bl = false;
        if (string.matches(string2)) {
            StringBuffer stringBuffer = new StringBuffer("");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            stringBuffer.append(StringProcess.parserInt(string.substring(0, 3)) + 1911);
            stringBuffer.append(string.substring(3));
            try {
                Date date = simpleDateFormat.parse(stringBuffer.toString());
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.setTime(date);
                String string3 = simpleDateFormat.format(gregorianCalendar.getTime());
                string3 = StringProcess.fillZero(StringProcess.parserInt(string3.substring(0, 4)) - 1911, 3) + string3.substring(4);
                if (string3.equals(string)) {
                    bl = true;
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return bl;
    }

    public static ArrayList<String[]> queryMonthFirstEnd(int n) throws ParseException {
        ArrayList<String[]> arrayList = new ArrayList<String[]>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        int n2 = calendar.get(1);
        int n3 = calendar.get(2) + 1;
        int n4 = calendar.get(5);
        System.out.println("year=" + n2 + ",month=" + n3);
        block0: for (int i = n; i <= n2; ++i) {
            for (int j = 1; j <= 12; ++j) {
                String string = i - 1911 + StringProcess.fillZero(j, 2);
                String string2 = string + "01";
                Date date = simpleDateFormat.parse(i + StringProcess.fillZero(j, 2) + "01");
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTime(date);
                String string3 = "";
                if (i == n2 && j == n3) {
                    string3 = string + StringProcess.fillZero(n4, 2);
                } else {
                    if (i == n2 && j > n3) continue block0;
                    string3 = string + calendar2.getActualMaximum(5);
                }
                String[] stringArray = new String[]{string2, string3};
                arrayList.add(stringArray);
            }
        }
        return arrayList;
    }

    public static String m2y(String string, String string2) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        return numberFormat.format(StringProcess.parserDouble(string) + StringProcess.parserDouble(string2) / 12.0);
    }

    public static String[] y2m(String string) {
        double d = StringProcess.parserDouble(string);
        int n = (int)Math.floor(d);
        int n2 = (int)Math.round((d - (double)n) * 12.0);
        return new String[]{String.valueOf(n), String.valueOf(n2)};
    }
}

