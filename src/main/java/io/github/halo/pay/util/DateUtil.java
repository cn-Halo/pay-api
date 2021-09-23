package io.github.halo.pay.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static final String YMD_FORMAT = "yyyy-MM-dd";

    public static final String YM_FORMAT = "yyyy-MM";

    public static final String FULL_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String YYYYMMDD_FORMAT = "yyyyMMdd";

    public static final String YYYYMMDDHHMMSS_FORMAT = "yyyyMMddHHmmss";

    public static final String HHMMSS_FORMAT = "HHmmss";

    public static final String HMS_FORMAT = "HH:mm:ss";


    public static String string2String(String date, String originFormat, String nowFormat) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(originFormat);
        TemporalAccessor temporalAccessor = dateTimeFormatter.parse(date);
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern(nowFormat);
        return dateTimeFormatter1.format(temporalAccessor);
    }

    /**
     * 得到当前自定义格式的时间
     *
     * @param format 日期格式
     */
    public static String getCurrentDate(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

    /**
     * 在当前时间的基础上增加分钟数
     */
    public static String addMinute(long minutes, String pattern) {
        LocalDateTime s = LocalDateTime.now().plusMinutes(minutes);
        return s.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 得到当前时间的YYYY-MM-DD格式
     *
     * @return YYYY-MM-dd字符串格式
     */
    public static String getCurrentDate_YMD() {
        SimpleDateFormat sdf = new SimpleDateFormat(YMD_FORMAT);
        return sdf.format(new Date());
    }

    /**
     * 得到当前时间的YYYY-MM格式
     *
     * @return YYYY-MM字符串格式
     */
    public static String getCurrentDate_YM() {
        SimpleDateFormat sdf = new SimpleDateFormat(YM_FORMAT);
        return sdf.format(new Date());
    }

    /**
     * 得到当前时间的YYYYMMdd格式
     *
     * @return YYYYMMdd字符串格式
     */
    public static String getCurrentDate_YYYYMMDD() {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD_FORMAT);
        return sdf.format(new Date());
    }

    /**
     * 得到当天开始时间 全格式
     */
    public static Date getBeginDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(YMD_FORMAT);
        SimpleDateFormat sdf2 = new SimpleDateFormat(FULL_FORMAT);
        Date start = new Date();
        try {
            start = sdf2.parse(sdf.format(start) + " 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return start;
    }

    /**
     * 得到当天的结束时间 全格式
     */
    public static Date getEndDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(YMD_FORMAT);
        SimpleDateFormat sdf2 = new SimpleDateFormat(FULL_FORMAT);
        Date end = new Date();
        try {
            end = sdf2.parse(sdf.format(end) + " 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return end;
    }

    /**
     * 得到昨天的开始时间 全格式
     */
    public static Date getYesterdayBeginDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(YMD_FORMAT);
        SimpleDateFormat sdf2 = new SimpleDateFormat(FULL_FORMAT);
        Date start = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        calendar.add(Calendar.DATE, -1);
        start = calendar.getTime();
        try {
            start = sdf2.parse(sdf.format(start) + " 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return start;
    }

    /**
     * 得到昨天的结束时间 全格式
     */
    public static Date getYesterdayEndDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(YMD_FORMAT);
        SimpleDateFormat sdf2 = new SimpleDateFormat(FULL_FORMAT);
        Date end = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(end);
        calendar.add(Calendar.DATE, -1);
        end = calendar.getTime();
        try {
            end = sdf2.parse(sdf.format(end) + " 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return end;
    }

    /**
     * 将Date转换为字符串
     *
     * @param format 格式
     */
    public static String dateToString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 将字符串转换为Date
     */
    public static Date stringToDate(String date, String format) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(date);
    }

    /**
     * 转换时间格式
     */
    public static String dateFormatTrans(String date) throws Exception {
        Date d = stringToDate(date, "yyyyMMddHHmmss");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(d);
    }

    /**
     * 转换时间格式
     *
     * @param date yyyy-MM-dd
     * @return yyyyMMdd
     */
    public static String dateFormatToShort(String date) throws ParseException {
        DateFormat format = new SimpleDateFormat(YMD_FORMAT);
        DateFormat format2 = new SimpleDateFormat(YYYYMMDD_FORMAT);
        String billDate = "";
        Date d = format.parse(date);
        billDate = format2.format(d);
        return billDate;
    }

    /**
     * 转换时间格式
     *
     * @param date yyyyMMdd
     * @return yyyy-MM-dd
     */
    public static String dateFormatToLong(String date) throws ParseException {
        DateFormat format = new SimpleDateFormat(YYYYMMDD_FORMAT);
        DateFormat format2 = new SimpleDateFormat(YMD_FORMAT);
        String billDate = "";
        Date d = format.parse(date);
        billDate = format2.format(d);
        return billDate;
    }

    /**
     * 计算 day 天后的时间
     */
    public static Date addDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }


}

