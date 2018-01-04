package com.telecorp.dashqueue.utils;

import android.content.Context;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.format.Time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by softbaked on 8/17/16 AD.
 */

@SuppressWarnings("deprecation")
public class DateParseUtils {
    public static final String ISO8601_DATE_TIME_PATTERN_FROM_API = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ";
    public static final String ISO8601_DATE_TIME_PATTERN_TO_API = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String ISO8601_DATE_PATTERN_FROM_API = "yyyy-MM-dd";
    public static final String DATE_TIME_PATTERN_HH_mm = "HH:mm";
    public static final String DATE_TIME_PATTERN_dd_MM_YYYY = "dd/MM/yyyy HH:mm";


    public static String parseDateString(String dateString, String oldPattern, String newPattern) {
        if (!TextUtils.isEmpty(dateString)) {
            SimpleDateFormat sdf = new SimpleDateFormat(oldPattern, Locale.getDefault());
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            String result = dateString;
            try {
                Date date = sdf.parse(dateString);
                sdf = new SimpleDateFormat(newPattern, Locale.getDefault());
                result = sdf.format(date);
            } catch (ParseException e) {
                if (!TextUtils.equals(oldPattern, ISO8601_DATE_PATTERN_FROM_API)) {
                    return parseDateString(dateString, ISO8601_DATE_PATTERN_FROM_API, newPattern);
                }

                e.printStackTrace();
            }
            return result;
        } else {
            return dateString;
        }
    }


    public static String parseCalendarToDateString(Calendar calendar, String newPattern) {
        if (null != calendar) {
            Date date = new Date(calendar.getTimeInMillis());
            return new SimpleDateFormat(newPattern, Locale.getDefault()).format(date);
        }
        return "";
    }


    public static long getTimeFromDateString(String dateString, String oldPattern) {
        long time = 0;
        if (!TextUtils.isEmpty(dateString)) {
            SimpleDateFormat sdf = new SimpleDateFormat(oldPattern, Locale.getDefault());
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            try {
                Date date = sdf.parse(dateString);
                time = date.getTime();
            } catch (ParseException e) {
                if (!TextUtils.equals(oldPattern, ISO8601_DATE_PATTERN_FROM_API)) {
                    return getTimeFromDateString(dateString, ISO8601_DATE_PATTERN_FROM_API);
                }
                e.printStackTrace();
            }
            return time;
        } else {
            return time;
        }
    }

    public static long getTimeFromDateString(String dateString) {
        return getTimeFromDateString(dateString, ISO8601_DATE_PATTERN_FROM_API);
    }


    public static String parseDateString(String dateString, String desirePattern) {
        return parseDateString(dateString, ISO8601_DATE_TIME_PATTERN_FROM_API, desirePattern);
    }


    public static String parseIso8601DateStringToHourMinute(String dateString) {
        return parseDateString(dateString, ISO8601_DATE_TIME_PATTERN_FROM_API, DATE_TIME_PATTERN_HH_mm);
    }

    public static String parseDateToString(Date date, String desirePattern) {
        return parseDateToString(date, desirePattern, null);
    }

    public static String parseDateToString(Date date, String desirePattern, TimeZone timeZone) {
        SimpleDateFormat formateDate = new SimpleDateFormat(desirePattern, Locale.getDefault());
        if (null != timeZone) {
            formateDate.setTimeZone(timeZone);
        }
        return formateDate.format(date.getTime());
    }

    public static String parseDateToISO8601String(Date date) {
        return parseDateToString(date, ISO8601_DATE_TIME_PATTERN_FROM_API);
    }

    public static String parseDateToISO8601String(Calendar calendar) {
        return parseDateToString(new Date(calendar.getTimeInMillis()), ISO8601_DATE_TIME_PATTERN_FROM_API);
    }

    public static Calendar toCalendar(String dateString, String oldPattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(oldPattern, Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            Date date = sdf.parse(dateString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar;
        } catch (ParseException e) {
            if (!TextUtils.equals(oldPattern, ISO8601_DATE_PATTERN_FROM_API)) {
                return toCalendar(dateString, ISO8601_DATE_PATTERN_FROM_API);
            }
            e.printStackTrace();
        }
        return null;
    }

    public static Calendar toCalendar(String dateString) {
        return toCalendar(dateString, ISO8601_DATE_TIME_PATTERN_FROM_API);
    }

    public static String parseIso8601DateStringToDDMMYY(String dateString) {
        return parseDateString(dateString, ISO8601_DATE_TIME_PATTERN_FROM_API, DATE_TIME_PATTERN_dd_MM_YYYY);
    }

    public static Date parseIso8601DateStringToDate(String dateString) {
        if (!TextUtils.isEmpty(dateString)) {
            SimpleDateFormat sdf = new SimpleDateFormat(ISO8601_DATE_TIME_PATTERN_FROM_API, Locale.getDefault());
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            try {
                return sdf.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return new Date();
        } else {
            return new Date();
        }
    }


    public static String relativeParseDateTime(Context context, String dateString) {
        if (!TextUtils.isEmpty(dateString)) {
            SimpleDateFormat sdf = new SimpleDateFormat(ISO8601_DATE_TIME_PATTERN_FROM_API, Locale.getDefault());
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            String result = dateString;
            try {
                Date date = sdf.parse(dateString);
                result = String.valueOf(DateUtils.getRelativeDateTimeString(context, date.getTime(), DateUtils.DAY_IN_MILLIS, DateUtils.WEEK_IN_MILLIS, DateUtils.FORMAT_ABBREV_MONTH));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return result;
        } else {
            return dateString;
        }
    }


    public static LinkedHashMap<Date, String> get6DaysFromNowWithTimeOfNinjaVans() {
        TimeZone timeZone = TimeZone.getDefault();
        LinkedHashMap<Date, String> results = new LinkedHashMap<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(timeZone);
        Date now = new Date();
        for (int i = 0; i < 6; i++) {
            if (i == 0) {
                calendar.add(Calendar.DAY_OF_YEAR, 0);
            } else {
                calendar.add(Calendar.DAY_OF_YEAR, 1);
            }

            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                calendar.add(Calendar.DAY_OF_YEAR, 1);
            }

//            String dateString = (String) DateUtils.getRelativeDateTimeString(context, calendar.getTime().getTime(), DateUtils.DAY_IN_MILLIS, DateUtils.WEEK_IN_MILLIS, DateUtils.FORMAT_SHOW_DATE);
//            String timeString = "";
            for (int j = 0; j < 3; j++) {
                Date date = null;
                String dateTimeRangeString = "";
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                if (j == 0) {
                    calendar.set(Calendar.HOUR_OF_DAY, 9);
                    date = calendar.getTime();
                    dateTimeRangeString = parseDateToString(calendar.getTime(), "EEEE, d MMM HH:mm ", timeZone);
                    calendar.set(Calendar.HOUR_OF_DAY, 12);
                    dateTimeRangeString += " - " + parseDateToString(calendar.getTime(), "HH:mm", timeZone);
                } else if (j == 1) {
                    calendar.set(Calendar.HOUR_OF_DAY, 12);
                    date = calendar.getTime();
                    dateTimeRangeString = parseDateToString(calendar.getTime(), "EEEE, d MMM HH:mm ", timeZone);
                    calendar.set(Calendar.HOUR_OF_DAY, 15);
                    dateTimeRangeString += " - " + parseDateToString(calendar.getTime(), "HH:mm", timeZone);
                } else if (j == 2) {
                    calendar.set(Calendar.HOUR_OF_DAY, 15);
                    date = calendar.getTime();
                    dateTimeRangeString = parseDateToString(calendar.getTime(), "EEEE, d MMM HH:mm ", timeZone);
                    calendar.set(Calendar.HOUR_OF_DAY, 18);
                    dateTimeRangeString += " - " + parseDateToString(calendar.getTime(), "HH:mm", timeZone);
                }
                if (null != date && date.getTime() > now.getTime()) {
                    results.put(date, dateTimeRangeString);
                }
            }

        }
        return results;
    }


    public static String relativeDateTimeStringParse(Context context, long timeInMillis) {
        return String.valueOf(DateUtils.getRelativeDateTimeString(context, timeInMillis, DateUtils.DAY_IN_MILLIS, DateUtils.WEEK_IN_MILLIS, DateUtils.FORMAT_ABBREV_MONTH));
    }

    public static String relativeDateTimeStringParse(Context context, Calendar calendar) {
        return relativeDateTimeStringParse(context,calendar.getTimeInMillis());
    }

    public static String relativeDateTimeISO8601Parse(Context context,String dateTime){
        return relativeDateTimeStringParse(context,toCalendar(dateTime));
    }


    public static String relativeParse(String dateString) {
        if (!TextUtils.isEmpty(dateString)) {
            SimpleDateFormat sdf = new SimpleDateFormat(ISO8601_DATE_TIME_PATTERN_FROM_API, Locale.getDefault());
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            String result = dateString;
            try {
                Date date = sdf.parse(dateString);
//                Date currentDate = new Date();
                if (isToday(date.getTime())) {
                    sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
                }

//                else if (isYesterday(date.getTime())) {
//                    return String.valueOf(DateUtils.getRelativeDateTimeString(context, date.getTime(), DateUtils.DAY_IN_MILLIS, DateUtils.WEEK_IN_MILLIS, DateUtils.FORMAT_ABBREV_MONTH));
//                }
                else if (isThisYear(date.getTime())) {
                    sdf = new SimpleDateFormat("dd MMM", Locale.getDefault());
                } else {
                    sdf = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
                }
                result = sdf.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return result;
        } else {
            return dateString;
        }
    }

    private static boolean isThisYear(long when) {
        Time time = new Time();
        time.set(when);

        int thenYear = time.year;

        time.set(System.currentTimeMillis());
        return (thenYear == time.year);
    }

//    private static boolean isSameDaySameMonthSameYear(Date date1, Date date2) {
//        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
//        return fmt.format(date1).equals(fmt.format(date2));
//    }

    public static boolean isToday(long when) {
        Time time = new Time();
        time.set(when);

        int thenYear = time.year;
        int thenMonth = time.month;
        int thenMonthDay = time.monthDay;

        time.set(System.currentTimeMillis());
        return (thenYear == time.year)
                && (thenMonth == time.month)
                && (thenMonthDay == time.monthDay);
    }

    public static boolean isYesterday(long when) {
        Time time = new Time();
        time.set(when);

        int thenYear = time.year;
        int thenMonth = time.month;
        int thenMonthDay = time.monthDay;

        time.set(System.currentTimeMillis());
        return (thenYear == time.year)
                && (thenMonth == time.month)
                && (thenMonthDay + 1 == time.monthDay);
    }


    public static String getCurrentTimeString(Context context) {
        return String.valueOf(DateUtils.getRelativeDateTimeString(context, Calendar.getInstance().getTimeInMillis(), DateUtils.DAY_IN_MILLIS, DateUtils.WEEK_IN_MILLIS, DateUtils.FORMAT_ABBREV_MONTH));
    }


}
