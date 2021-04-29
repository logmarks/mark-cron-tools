package com.logmark.crontools.comm.listener.document;

import com.logmark.crontools.comm.exception.ParamsException;
import com.logmark.crontools.comm.utils.ComponentIdUtils;
import com.logmark.crontools.comm.utils.MyTextField;
import com.logmark.crontools.comm.utils.StringUtil;
import com.logmark.crontools.cron.CronDialog;
import com.logmark.crontools.cron.bo.ExpressionBo;
import com.logmark.crontools.cron.bo.PageLimitValue;
import com.logmark.crontools.cron.enums.DateUnitEnum;
import com.logmark.crontools.cron.structure.CronPageStructure;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * @FileName: TextFieldDocumentListener
 * @Desctiption: TextField监听器
 * @Author: hlb
 * @Date: Created by 2021/2/24 11:12
 * @Modified Update By:
 */
public class TextFieldDocumentListener implements DocumentListener {
    private MyTextField currentTextField;
    private MyTextField otherTextField;
    private JTextField expressionTextField;
    private String expressionRule;
    private ExpressionBo expressionBo;
    private JRadioButton button;
    private PageLimitValue pageLimitValue;

    public TextFieldDocumentListener() {
    }

    public TextFieldDocumentListener(ExpressionBo expressionBo, JRadioButton button, String expressionRule
            , JTextField expressionTextField
            , MyTextField currentTextField, MyTextField otherTextField) {
        this.currentTextField = currentTextField;
        this.otherTextField = otherTextField;
        this.expressionTextField = expressionTextField;
        this.expressionRule = expressionRule;
        this.expressionBo = expressionBo;
        this.button = button;
    }

    public TextFieldDocumentListener(ExpressionBo expressionBo, JRadioButton button, String expressionRule
            , JTextField expressionTextField, MyTextField currentTextField
            , MyTextField otherTextField, PageLimitValue pageLimitValue) {
        this(expressionBo, button, expressionRule, expressionTextField, currentTextField, otherTextField);
        this.pageLimitValue = pageLimitValue;
    }

    public TextFieldDocumentListener setPageLimitValue(PageLimitValue pageLimitValue) {
        this.pageLimitValue = pageLimitValue;
        return this;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        try {
            textChange(e);
        } catch (ParamsException exception) {
            this.expressionBo.setNinthRowTextArea(exception.getMessage());
            exception.printStackTrace();
        }
    }


    @Override
    public void removeUpdate(DocumentEvent e) {
        try {
            textChange(e);
        } catch (ParamsException exception) {
            this.expressionBo.setNinthRowTextArea(exception.getMessage());
            exception.printStackTrace();
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        try {
            textChange(e);
        } catch (ParamsException exception) {
            this.expressionBo.setNinthRowTextArea(exception.getMessage());
            exception.printStackTrace();
        }
    }

    private void textChange(DocumentEvent e) throws ParamsException {
        if (!button.isSelected()) {
            // 未选中指定按钮
            return;
        }
        //失去焦点执行的代码
        Document document = e.getDocument();
        String text = null;
        try {
            text = document.getText(0, document.getLength());
        } catch (BadLocationException badLocationException) {
            System.out.println("输入框数据字段长度小于0！");
            badLocationException.printStackTrace();
            return;
        }
        CronPageStructure pageStructure = CronDialog.pageMap.get(DateUnitEnum.SECOND);
        String result = pageStructure.checkExpression(expressionRule);
        checkParams(!StringUtil.isBlank(result), result);

        // 输入框数据值处理  结果数据显示
        switch (currentTextField.getId()) {
            case ComponentIdUtils.CYCLE_MIN_VALUE_TEXT_ID:
                checkParams(StringUtil.isBlank(text), "周期开始值不能为空！");
                checkParams(Integer.valueOf(text).compareTo(Integer.parseInt(otherTextField.getText())) >= 0, "周期开始值必须小于结束值！");
                checkParams(Integer.valueOf(text).compareTo(pageLimitValue.getCurrentUnitMinValue()) <= 0
                        , "周期开始值必须大于" + pageLimitValue.getCurrentUnitMinValue() + "！");
                this.expressionTextField.setText(text + expressionRule + otherTextField.getText());
                break;
            case ComponentIdUtils.CYCLE_MAX_VALUE_TEXT_ID:
                checkParams(StringUtil.isBlank(text), "周期结束值不能为空！");
                checkParams(Integer.valueOf(text).compareTo(Integer.parseInt(otherTextField.getText())) <= 0, "周期结束值必须大于开始值！");
                checkParams(Integer.valueOf(text).compareTo(pageLimitValue.getCurrentUnitMaxValue()) >= 0
                        , "周期结束值必须小于" + pageLimitValue.getCurrentUnitMaxValue() + "！");
                this.expressionTextField.setText(otherTextField.getText() + expressionRule + text);
                break;
            case ComponentIdUtils.TIMING_PERIOD_MIN_VALUE_TEXT_ID:
                checkParams(StringUtil.isBlank(text), "指定时间循环的指定时间值不能为空！");
                checkParams(Integer.valueOf(text).compareTo(pageLimitValue.getCurrentTimingMaxValue()) >= 0
                        , "指定时间循环的指定时间值必须小于" + pageLimitValue.getCurrentTimingMaxValue() + "！");
                checkParams(Integer.valueOf(text).compareTo(pageLimitValue.getCurrentTimingMinValue()) < 0
                        , "指定时间循环的指定时间值必须不小于" + pageLimitValue.getCurrentTimingMinValue() + "！");
                this.expressionTextField.setText(text + expressionRule + otherTextField.getText());
                break;
            case ComponentIdUtils.TIMING_PERIOD_MAX_VALUE_TEXT_ID:
                checkParams(StringUtil.isBlank(text), "指定时间循环的循环值不能为空！");
//                checkParams(Integer.valueOf(text).compareTo(pageLimitValue.getCurrentUnitMaxValue()) >= 0
//                        , "指定时间循环的循环值必须小于" + pageLimitValue.getCurrentUnitMaxValue() + "！");
//                checkParams(Integer.valueOf(text).compareTo(pageLimitValue.getCurrentUnitMinValue()) < 0
//                        , "指定时间循环的循环值必须不小于" + pageLimitValue.getCurrentUnitMinValue() + "！");
                this.expressionTextField.setText(otherTextField.getText() + expressionRule + text);
                break;
            case ComponentIdUtils.MONTH_LAST_WEEK_DAY_VALUE_ID:
                //本月的最后一天
                checkParams(StringUtil.isBlank(text), "号数不能为空！");
                LocalDate localDate = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
                int maxDayVal = localDate.getDayOfMonth();
                checkParams(Integer.valueOf(text).compareTo(maxDayVal) > 0, "得到 每月 '' 号最近的那个工作日的Cron表达式，其号数必须不大于本月最后一天号数！");
                checkParams(Integer.valueOf(text).compareTo(0) <= 0, "得到 每月 '' 号最近的那个工作日的Cron表达式，其号数必须大于0！");
                this.expressionTextField.setText(text + expressionRule);
                break;
            case ComponentIdUtils.MONTH_LAST_WEEK_VALUE_ID:
                //本月的最后一个星期
                checkParams(StringUtil.isBlank(text), "得到 本月的最后一个星期几 的Cron表达式，其星期数不能为空！");
                checkParams(Integer.valueOf(text).compareTo(pageLimitValue.getCurrentUnitMaxValue()) > 0
                        , "得到 本月的最后一个星期几 的Cron表达式，其星期数必须不大于" + pageLimitValue.getCurrentUnitMaxValue() + "！");
                checkParams(Integer.valueOf(text).compareTo(pageLimitValue.getCurrentUnitMinValue()) < 0
                        , "得到 本月的最后一个星期几 的Cron表达式，其星期数必须大于等于！" + pageLimitValue.getCurrentUnitMinValue() + "！");
                this.expressionTextField.setText(text + expressionRule);
                break;
            default:
                break;
        }

        this.expressionBo.generateCronExpression().generateTenRunTime();
    }

    private void checkParams(boolean flag, String result) throws ParamsException {
        if (flag) {
            throw new ParamsException(result);
        }
    }

}
