package com.logmark.crontools.comm.enums;


import com.logmark.crontools.comm.utils.StringUtil;

/**
 * @FileName: ContentEnum
 * @Desctiption: 枚举通用接口
 * @Author: hlb
 * @Date: Created by 2021/2/7 17:21
 * @Modified Update By:
 */
public interface ContentEnum {

    String getContent();

    String getValue();

    default boolean equalsValue(String value) {
        return StringUtil.isNoneBlank(value) && value.equals(this.getValue());
    }
}
