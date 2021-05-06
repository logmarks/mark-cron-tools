package com.logmark.crontools.cron.structure;


import com.logmark.crontools.comm.listener.button.CycleButtonActionListener;
import com.logmark.crontools.comm.listener.button.DesignButtonActionListener;
import com.logmark.crontools.comm.listener.button.TimingPeriodButtonActionListener;
import com.logmark.crontools.comm.listener.document.TextFieldDocumentListener;
import com.logmark.crontools.comm.utils.StringUtil;
import com.logmark.crontools.cron.bo.ExpressionBo;
import com.logmark.crontools.cron.bo.PageLimitValue;
import com.logmark.crontools.cron.bo.page.CronFrameBo;
import com.logmark.crontools.cron.enums.DateUnitEnum;

import javax.swing.*;
import java.awt.*;

/**
 * @FileName: CronPageStructure
 * @Desctiption: cron页面接口
 * @Author: hlb
 * @Date: Created by 2021/2/8 9:36
 * @Modified Update By:
 */
public abstract class CronPageStructure {
    private DateUnitEnum dateUnitEnum;
    /**
     * 构建表达式之上页面
     */
    private JPanel expressionTopForm;
    /**
     * 表达式之上 按钮/值 组件
     */
    private CronFrameBo cronFrameBo;

    /**
     * 表达式之下 值 组件
     */
    private ExpressionBo expressionBo;
    /**
     * 卡片布局：实现页面局部切换
     */
    private CardLayout cardLayout;
    /**
     * 页面局部切换部分
     */
    private JPanel cardsTopForm;

    /**
     * 页面最大/最小值
     */
    private PageLimitValue pageLimitValue;

    public CronPageStructure() {
    }

    public CronPageStructure(DateUnitEnum unit, ExpressionBo expressionBo
            , CardLayout cardLayout, JPanel cardsTopForm) {
        this();
        this.dateUnitEnum = unit;
        this.cardLayout = cardLayout;
        this.cardsTopForm = cardsTopForm;
        this.expressionBo = expressionBo;
        this.setLVAndCFBAndETFAndBTFL();
    }

    /**
     * 设置 pageLimitValue，cronFrameBo，expressionTopFrom的值，
     * 且调用setButtonAndTextFieldListener，给各个按钮绑定监听器
     *
     * @return
     */
    abstract CronPageStructure setLVAndCFBAndETFAndBTFL();

    /**
     * 监听事件设置方法
     */
    public CronPageStructure setButtonAndTextFieldListener(JTextField textField) {
        this.getCronFrameBo().getCycleButton()
                .addActionListener(
                        new CycleButtonActionListener(this.getExpressionBo(), this.getCronFrameBo(), textField));

        this.getCronFrameBo().getCycleMinValueText().getDocument()
                .addDocumentListener(new TextFieldDocumentListener(this.getExpressionBo(), this.getCronFrameBo().getCycleButton()
                        , ExpressionBo.HORIZONTAL_LINE, textField
                        , this.getCronFrameBo().getCycleMinValueText(), this.getCronFrameBo().getCycleMaxValueText())
                        .setPageLimitValue(this.getPageLimitValue()));

        this.getCronFrameBo().getCycleMaxValueText().getDocument()
                .addDocumentListener(new TextFieldDocumentListener(this.getExpressionBo(), this.getCronFrameBo().getCycleButton()
                        , ExpressionBo.HORIZONTAL_LINE, textField
                        , this.getCronFrameBo().getCycleMaxValueText(), this.getCronFrameBo().getCycleMinValueText())
                        .setPageLimitValue(this.getPageLimitValue()));

        if (this.getCronFrameBo().getDesignButton() != null) {
            this.getCronFrameBo().getDesignButton()
                    .addActionListener(new DesignButtonActionListener(this.getExpressionBo(), this.getCronFrameBo(), textField));
        }

        this.getCronFrameBo().getTimingPeriodButton()
                .addActionListener(new TimingPeriodButtonActionListener(this.getExpressionBo(), this.getCronFrameBo(), textField));

        this.getCronFrameBo().getTimingPeriodMinValueText().getDocument()
                .addDocumentListener(new TextFieldDocumentListener(getExpressionBo(), this.getCronFrameBo().getTimingPeriodButton()
                        , ExpressionBo.BACKSLASH, textField
                        , this.getCronFrameBo().getTimingPeriodMinValueText(), this.getCronFrameBo().getTimingPeriodMaxValueText())
                        .setPageLimitValue(this.getPageLimitValue()));
        this.getCronFrameBo().getTimingPeriodMaxValueText().getDocument()
                .addDocumentListener(new TextFieldDocumentListener(getExpressionBo(), this.getCronFrameBo().getTimingPeriodButton()
                        , ExpressionBo.BACKSLASH, textField
                        , this.getCronFrameBo().getTimingPeriodMaxValueText(), this.getCronFrameBo().getTimingPeriodMinValueText())
                        .setPageLimitValue(this.getPageLimitValue()));

        // day页面
        if (this.getCronFrameBo().getMonthLastWeekDayButton() != null) {
            this.getCronFrameBo().getMonthLastWeekDayButton().addActionListener(e -> {
                if (!this.getCronFrameBo().getMonthLastWeekDayButton().isSelected()) {
                    // 未选中指定按钮
                    return;
                }
                if (StringUtil.isBlank(this.getCronFrameBo().getMonthLastWeekDayValue().getText())) {
                    getExpressionBo().setNinthRowTextArea("cron表达式格式非法--在'日'的格式错误！");
                    return;
                }
                getExpressionBo().setDayTextField(this.getCronFrameBo().getMonthLastWeekDayValue().getText() + "W");
                getExpressionBo().generateCronExpression().generateTenRunTime();
            });
        }

        if (this.getCronFrameBo().getMonthLastWeekDayValue() != null) {
            this.getCronFrameBo().getMonthLastWeekDayValue().getDocument().addDocumentListener(
                    new TextFieldDocumentListener(getExpressionBo(), this.getCronFrameBo().getMonthLastWeekDayButton()
                            , "W", getExpressionBo().getDayTextField()
                            , this.getCronFrameBo().getMonthLastWeekDayValue(), null)
                            .setPageLimitValue(this.getPageLimitValue()));
        }

        // week页面
        if (this.getCronFrameBo().getMonthLastWeekButton() != null) {
            this.getCronFrameBo().getMonthLastWeekButton().addActionListener(e -> {
                if (!this.getCronFrameBo().getMonthLastWeekButton().isSelected()) {
                    // 未选中指定按钮
                    return;
                }
                if (StringUtil.isBlank(this.getCronFrameBo().getMonthLastWeekDayValue().getText())) {
                    getExpressionBo().setNinthRowTextArea("cron表达式格式非法--在'月'的格式错误！");
                    return;
                }
                getExpressionBo().setWeekTextField(this.getCronFrameBo().getMonthLastWeekDayValue().getText() + "L");
                getExpressionBo().generateCronExpression().generateTenRunTime();
            });
        }

        if (this.getCronFrameBo().getMonthLastWeekDayValue() != null) {
            this.getCronFrameBo().getMonthLastWeekDayValue().getDocument()
                    .addDocumentListener(new TextFieldDocumentListener(getExpressionBo(), this.getCronFrameBo().getMonthLastWeekButton()
                            , "L", getExpressionBo().getWeekTextField()
                            , this.getCronFrameBo().getMonthLastWeekDayValue(), null)
                            .setPageLimitValue(this.getPageLimitValue()));
        }
        return this;
    }

    /**
     * 规则判断
     *
     * @return
     */
    public String checkExpression(String expressionChar) {
        if (ExpressionBo.HORIZONTAL_LINE.equals(expressionChar)) {
            return checkHorizontalLineExpression();
        }
        if (ExpressionBo.BACKSLASH.equals(expressionChar)) {
            return checkBackslashExpression();
        }
        if (expressionChar.equals(ExpressionBo.W)) {
            return checkMonthLastWeekExpression();
        }
        return null;
    }

    public String checkMonthLastWeekExpression() {
        if (StringUtil.isBlank(this.getCronFrameBo().getMonthLastWeekDayValue().getText())) {
            return " 'W' 前必须跟整数！";
        }
        return null;
    }

    public String checkHorizontalLineExpression() {
        if (StringUtil.isBlank(this.getCronFrameBo().getCycleMinValueText().getText())) {
            return dateUnitEnum.getContent() + " 周期从 '' 起始值不能为空！";
        }
        if (StringUtil.isBlank(this.getCronFrameBo().getCycleMaxValueText().getText())) {
            return dateUnitEnum.getContent() + " 周期从  到 '' 终止值不能为空！";
        }
        return null;
    }

    /**
     * 从什么时候开始多久执行一次 / 规则判断
     *
     * @return
     */
    public String checkBackslashExpression() {
        if (StringUtil.isBlank(this.getCronFrameBo().getTimingPeriodMaxValueText().getText())) {
            return "'/' 后必须跟整数！";
        }
        return null;
    }

    // ---------------------------- get/set分割线 ------------------------------

    public DateUnitEnum getDateUnitEnum() {
        return dateUnitEnum;
    }

    public CronPageStructure setDateUnitEnum(DateUnitEnum dateUnitEnum) {
        this.dateUnitEnum = dateUnitEnum;
        return this;
    }

    public PageLimitValue getPageLimitValue() {
        return pageLimitValue;
    }

    public CronPageStructure setPageLimitValue(PageLimitValue pageLimitValue) {
        this.pageLimitValue = pageLimitValue;
        return this;
    }

    public JPanel getExpressionTopForm() {
        return expressionTopForm;
    }

    public CronPageStructure setExpressionTopForm(JPanel expressionTopForm) {
        this.expressionTopForm = expressionTopForm;
        return this;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public CronPageStructure setCardLayout(CardLayout cardLayout) {
        this.cardLayout = cardLayout;
        return this;
    }

    public JPanel getCardsTopForm() {
        return cardsTopForm;
    }

    public CronPageStructure setCardsTopForm(JPanel cardsTopForm) {
        this.cardsTopForm = cardsTopForm;
        return this;
    }

    public ExpressionBo getExpressionBo() {
        return expressionBo;
    }

    public CronPageStructure setExpressionBo(ExpressionBo expressionBo) {
        this.expressionBo = expressionBo;
        return this;
    }

    public CronFrameBo getCronFrameBo() {
        return cronFrameBo;
    }

    public CronPageStructure setCronFrameBo(CronFrameBo cronFrameBo) {
        this.cronFrameBo = cronFrameBo;
        return this;
    }

}
