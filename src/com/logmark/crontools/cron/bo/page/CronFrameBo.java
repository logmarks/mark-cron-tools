package com.logmark.crontools.cron.bo.page;

import com.logmark.crontools.comm.utils.MyTextField;
import com.logmark.crontools.cron.enums.DateUnitEnum;

import javax.swing.*;
import java.util.List;

/**
 * @FileName: CronFrameBo
 * @Desctiption: 页面上部分组件
 * @Author: hlb
 * @Date: Created by 2021/2/25 13:59
 * @Modified Update By:
 */
public class CronFrameBo {

    /**
     * 周期单选按钮
     */
    private DateUnitEnum pageUnit;
    /**
     * 周期单选按钮
     */
    private JRadioButton cycleButton;
    /**
     * 周期开始值 输入框
     */
    private MyTextField cycleMinValueText;
    /**
     * 周期结束值 输入框
     */
    private MyTextField cycleMaxValueText;
    /**
     * 指定循环 单选按钮
     */
    private JRadioButton timingPeriodButton;
    /**
     * 指定 多久开始循环输入框
     */
    private MyTextField timingPeriodMinValueText;
    /**
     * 指定 多久循环输入框
     */
    private MyTextField timingPeriodMaxValueText;
    /**
     * 指定按钮
     */
    private JRadioButton designButton;
    /**
     * 指定 最后一周中最近的一次工作日值 按钮值
     */
    private MyTextField monthLastWeekDayValue;
    /**
     * 指定 最后一周中最近的一次工作日值 按钮
     */
    private JRadioButton monthLastWeekDayButton;
    /**
     * 通配符按钮
     */
    private JRadioButton wildcardButton;
    /**
     * 不指定 允许的通配符按钮
     */
    private JRadioButton noWildcardButton;
    /**
     * 未指定按钮
     */
    private JRadioButton noDesignButton;
    /**
     * 本月最后一天 按钮
     */
    private JRadioButton lastDayButton;
    /**
     * 本月最后一个星期
     */
    private JRadioButton monthLastWeekButton;

    /**
     * 指定区域  多选结果
     */
    private List<Integer> designTimeList;

    public CronFrameBo() {
    }

    public DateUnitEnum getPageUnit() {
        return pageUnit;
    }

    public CronFrameBo setPageUnit(DateUnitEnum pageUnit) {
        this.pageUnit = pageUnit;
        return this;
    }

    public MyTextField getCycleMinValueText() {
        return cycleMinValueText;
    }

    public CronFrameBo setCycleMinValueText(MyTextField cycleMinValueText) {
        this.cycleMinValueText = cycleMinValueText;
        return this;
    }

    public MyTextField getCycleMaxValueText() {
        return cycleMaxValueText;
    }

    public CronFrameBo setCycleMaxValueText(MyTextField cycleMaxValueText) {
        this.cycleMaxValueText = cycleMaxValueText;
        return this;
    }

    public MyTextField getTimingPeriodMinValueText() {
        return timingPeriodMinValueText;
    }

    public CronFrameBo setTimingPeriodMinValueText(MyTextField timingPeriodMinValueText) {
        this.timingPeriodMinValueText = timingPeriodMinValueText;
        return this;
    }

    public MyTextField getTimingPeriodMaxValueText() {
        return timingPeriodMaxValueText;
    }

    public CronFrameBo setTimingPeriodMaxValueText(MyTextField timingPeriodMaxValueText) {
        this.timingPeriodMaxValueText = timingPeriodMaxValueText;
        return this;
    }

    public List<Integer> getDesignTimeList() {
        return designTimeList;
    }

    public CronFrameBo setDesignTimeList(List<Integer> designTimeList) {
        this.designTimeList = designTimeList;
        return this;
    }

    public JRadioButton getCycleButton() {
        return cycleButton;
    }

    public CronFrameBo setCycleButton(JRadioButton cycleButton) {
        this.cycleButton = cycleButton;
        return this;
    }

    public JRadioButton getTimingPeriodButton() {
        return timingPeriodButton;
    }

    public CronFrameBo setTimingPeriodButton(JRadioButton timingPeriodButton) {
        this.timingPeriodButton = timingPeriodButton;
        return this;
    }

    public JRadioButton getDesignButton() {
        return designButton;
    }

    public CronFrameBo setDesignButton(JRadioButton designButton) {
        this.designButton = designButton;
        return this;
    }

    public MyTextField getMonthLastWeekDayValue() {
        return monthLastWeekDayValue;
    }

    public CronFrameBo setMonthLastWeekDayValue(MyTextField monthLastWeekDayValue) {
        this.monthLastWeekDayValue = monthLastWeekDayValue;
        return this;
    }

    public JRadioButton getMonthLastWeekDayButton() {
        return monthLastWeekDayButton;
    }

    public CronFrameBo setMonthLastWeekDayButton(JRadioButton monthLastWeekDayButton) {
        this.monthLastWeekDayButton = monthLastWeekDayButton;
        return this;
    }

    public JRadioButton getNoWildcardButton() {
        return noWildcardButton;
    }

    public CronFrameBo setNoWildcardButton(JRadioButton noWildcardButton) {
        this.noWildcardButton = noWildcardButton;
        return this;
    }

    public JRadioButton getWildcardButton() {
        return wildcardButton;
    }

    public CronFrameBo setWildcardButton(JRadioButton wildcardButton) {
        this.wildcardButton = wildcardButton;
        return this;
    }

    public JRadioButton getNoDesignButton() {
        return noDesignButton;
    }

    public CronFrameBo setNoDesignButton(JRadioButton noDesignButton) {
        this.noDesignButton = noDesignButton;
        return this;
    }

    public JRadioButton getLastDayButton() {
        return lastDayButton;
    }

    public CronFrameBo setLastDayButton(JRadioButton lastDayButton) {
        this.lastDayButton = lastDayButton;
        return this;
    }

    public JRadioButton getMonthLastWeekButton() {
        return monthLastWeekButton;
    }

    public CronFrameBo setMonthLastWeekButton(JRadioButton monthLastWeekButton) {
        this.monthLastWeekButton = monthLastWeekButton;
        return this;
    }
// ------------------------------------------- 构造函数 get/set结束线--------------------------------

}
