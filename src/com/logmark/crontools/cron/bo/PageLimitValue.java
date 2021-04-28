package com.logmark.crontools.cron.bo;

/**
 * @FileName: PageLimitValue
 * @Desctiption: 页面最大最小值
 * @Author: hlb
 * @Date: Created by 2021/4/27 16:38
 * @Modified Update By:
 */
public class PageLimitValue {

    /**
     * 当前最小值
     */
    private Integer currentUnitMinValue = 0;

    /**
     * 当前最大值
     */
    private Integer currentUnitMaxValue = 59;

    public PageLimitValue() {
    }

    public PageLimitValue(Integer currentUnitMinValue, Integer currentUnitMaxValue) {
        this.currentUnitMinValue = currentUnitMinValue;
        this.currentUnitMaxValue = currentUnitMaxValue;
    }

    public Integer getCurrentUnitMinValue() {
        return currentUnitMinValue;
    }

    public PageLimitValue setCurrentUnitMinValue(Integer currentUnitMinValue) {
        this.currentUnitMinValue = currentUnitMinValue;
        return this;
    }

    public Integer getCurrentUnitMaxValue() {
        return currentUnitMaxValue;
    }

    public PageLimitValue setCurrentUnitMaxValue(Integer currentUnitMaxValue) {
        this.currentUnitMaxValue = currentUnitMaxValue;
        return this;
    }
}
