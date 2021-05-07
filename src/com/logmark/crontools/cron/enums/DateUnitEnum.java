package com.logmark.crontools.cron.enums;

import com.logmark.crontools.comm.enums.ContentEnum;

/**
 * @FileName: DateUnitEnum
 * @Desctiption: 时间单位枚举
 * @Author: hlb
 * @Date: Created by 2021/2/7 16:57
 * @Modified Update By:
 */
public enum DateUnitEnum implements ContentEnum {
    /**
     * 枚举 秒
     */
    SECOND("秒", "second"),

    /**
     * 枚举 分钟
     */
    MINUTE("分钟", "minute"),

    /**
     * 枚举 时
     */
    HOUR("小时", "hour"),

    /**
     * 枚举 日
     */
    DAY("日", "day"),

    /**
     * 枚举 月
     */
    MONTH("月", "month"),

    /**
     * 枚举 周
     */
    WEEK("周", "week"),

    /**
     * 枚举 年
     */
    YEAR("年", "year");

    private String content;
    private String value;

    DateUnitEnum() {
    }

    DateUnitEnum(String content, String value) {
        this.content = content;
        this.value = value;
    }

    public static DateUnitEnum getByValue(String value) {
        DateUnitEnum[] types = DateUnitEnum.values();
        for (DateUnitEnum type : types) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }

    public static DateUnitEnum getByContent(String content) {
        DateUnitEnum[] types = DateUnitEnum.values();
        for (DateUnitEnum type : types) {
            if (type.getContent().equals(content)) {
                return type;
            }
        }
        return null;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String getValue() {
        return value;
    }
}
