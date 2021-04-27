package com.logmark.crontools.comm.utils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @FileName: ControlTheInputUtils
 * @Desctiption: 输入控制
 * @Author: hlb
 * @Date: Created by 2021/2/21 16:27
 * @Modified Update By:
 */
public class ControlTheInputUtils extends KeyAdapter {
    private Integer minValue;
    private Integer maxValue;

    public ControlTheInputUtils() {
        this.minValue = 0;
        this.maxValue = 60;
    }

    public ControlTheInputUtils(Integer minValue, Integer maxValue) {
        this.minValue = minValue == null ? 0 : minValue;
        this.maxValue = maxValue == null ? 60 : maxValue;
    }

    public Integer getMinValue() {
        return minValue;
    }

    public ControlTheInputUtils setMinValue(Integer minValue) {
        this.minValue = minValue;
        return this;
    }

    public Integer getMaxValue() {
        return maxValue;
    }

    public ControlTheInputUtils setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
        return this;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        String key = "0123456789" + (char) 8;
        if (key.indexOf(e.getKeyChar()) < 0) {
            //如果不是数字则取消
            e.consume();
            return;
        }
        Integer keyChar = Integer.parseInt(String.valueOf(e.getKeyChar()));
        if (minValue.compareTo(keyChar) > 0 || this.maxValue.compareTo(keyChar) < 0) {
            //如果不是数字不在 [最小值，最大值]之间，则取消
            e.consume();
        }
    }
}
