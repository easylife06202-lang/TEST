/*
 * Decompiled with CFR 0.152.
 */
package com.wfusion.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateSubtraction {
    private static long MILLI_SECONDS = 1L;
    private static long SECOUNDS = 1000L * MILLI_SECONDS;
    private static long MINUTES = 60L * SECOUNDS;
    private static long HOURS = 60L * MINUTES;
    private static long DAYS = 24L * HOURS;
    private static Date date1 = null;
    private static Date date2 = null;

    private DateSubtraction(String string, String string2, String string3) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(string3);
        date1 = simpleDateFormat.parse(string);
        date2 = simpleDateFormat.parse(string2);
    }

    public static long getMilliseconds(String string, String string2, String string3) throws ParseException {
        new DateSubtraction(string, string2, string3);
        long l = date1.getTime();
        long l2 = date2.getTime();
        return l - l2;
    }

    public static long getSeconds(String string, String string2, String string3) throws ParseException {
        return DateSubtraction.getMilliseconds(string, string2, string3) / SECOUNDS;
    }

    public static long getMinutes(String string, String string2, String string3) throws ParseException {
        return DateSubtraction.getMilliseconds(string, string2, string3) / MINUTES;
    }

    public static long getHours(String string, String string2, String string3) throws ParseException {
        return DateSubtraction.getMilliseconds(string, string2, string3) / HOURS;
    }

    public static long getDays(String string, String string2, String string3) throws ParseException {
        return DateSubtraction.getMilliseconds(string, string2, string3) / DAYS;
    }

    public static int getMonth(String string, String string2, String string3) throws ParseException {
        new DateSubtraction(string, string2, string3);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        calendar.set(5, 1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        calendar2.set(5, 1);
        int n = 0;
        if (calendar.get(1) >= 1900 && calendar2.get(1) >= 1900) {
            n = calendar.get(2) - calendar2.get(2) + (calendar.get(1) - calendar2.get(1)) * 12;
        }
        return n;
    }
}

