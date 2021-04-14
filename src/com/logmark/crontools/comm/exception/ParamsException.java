package com.logmark.crontools.comm.exception;

/**
 * @FileName: ParamsException
 * @Desctiption: 自定义参数异常
 * @Author: hlb
 * @Date: Created by 2021/2/25 9:15
 * @Modified Update By:
 */
public class ParamsException extends RuntimeException {
    public ParamsException() {

    }

    public ParamsException(String result) {
        super(result);
    }
}
