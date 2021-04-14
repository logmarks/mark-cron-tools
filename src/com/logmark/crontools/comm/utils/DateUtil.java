package com.logmark.crontools.comm.utils;

import org.apache.commons.lang.time.DateUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @FileName: DateUtil
 * @Desctiption: 时间工具类
 * @Author: hlb
 * @Date: Created by 2021/2/25 9:33
 * @Modified Update By:
 */
public class DateUtil extends DateUtils {
    public static final String YMDHMS = "yyyy-MM-dd HH:mm:ss";
    public static final String S = "ss";
    public static final String M = "mm";
    public static final String H = "HH";
    public static final String D = "dd";
    public static final String MM = "MM";
    public static final String Y = "yyyy";

    public static String getStringDate(Date date, String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(date);
    }

    public static String getStringDate(LocalDateTime date, String dateFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        return formatter.format(date);
    }
}
