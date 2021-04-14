package com.logmark.crontools.cron.bo;


import com.intellij.ui.JBColor;
import com.logmark.crontools.comm.utils.StringUtil;
import org.quartz.TriggerUtils;
import org.quartz.impl.triggers.CronTriggerImpl;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @FileName: ExpressionFieldBo
 * @Desctiption: 表达式对象
 * @Author: hlb
 * @Date: Created by 2021/2/8 10:03
 * @Modified Update By:
 */
public class ExpressionBo {
    public static final String HORIZONTAL_LINE = "-";
    public static final String BACKSLASH = "/";
    public static final String ASTERISK = "*";
    public static final String COMMA = ",";
    public static final String QUESTION_MARK = "?";
    public static final String W = "W";
    public static final Integer TEN = 10;
    /**
     * 表达式之下 值 组件
     */
    private JTextField secondTextField;
    private JTextField minuteTextField;
    private JTextField hourTextField;
    private JTextField dayTextField;
    private JTextField monthTextField;
    private JTextField weekTextField;
    private JTextField yearTextField;
    private JTextField cronExpressionInput;

    private JTextArea ninthRowTextArea;


    /**
     * 当前最小值
     */
    private Integer currentUnitMinValue = 0;

    /**
     * 当前最大值
     */
    private Integer currentUnitMaxValue = 59;

    public ExpressionBo() {
        this.secondTextField = ExpressionBo.getTextField("*", false);
        this.minuteTextField = ExpressionBo.getTextField("*", false);
        this.hourTextField = ExpressionBo.getTextField("*", false);
        this.dayTextField = ExpressionBo.getTextField("*", false);
        this.monthTextField = ExpressionBo.getTextField("*", false);
        this.weekTextField = ExpressionBo.getTextField("?", false);
        this.yearTextField = ExpressionBo.getTextField("", false);
        this.cronExpressionInput = ExpressionBo.getTextField("* * * * * ?", true);

        this.ninthRowTextArea = new JTextArea();
        this.ninthRowTextArea.setColumns(80);
        this.ninthRowTextArea.setRows(3);
        this.ninthRowTextArea.setBackground(JBColor.LIGHT_GRAY);
        this.ninthRowTextArea.setLineWrap(true);
        this.ninthRowTextArea.setFont(StringUtil.getDefaultFont());
    }

    private static JTextField getTextField(String name, boolean editAble) {
        JTextField textField = new JTextField();
        textField.setText(name);
        textField.setEditable(editAble);
        textField.setFont(StringUtil.getDefaultFont());
        return textField;
    }

    public void clearTextFieldText() {
        secondTextField.setText("");
        minuteTextField.setText("");
        hourTextField.setText("");
        dayTextField.setText("");
        monthTextField.setText("");
        weekTextField.setText("");
        yearTextField.setText("");
    }

    public JTextField getSecondTextField() {
        return secondTextField;
    }

    public ExpressionBo setSecondTextField(String value) {
        this.secondTextField.setText(value);
        return this;
    }

    public JTextField getMinuteTextField() {
        return minuteTextField;
    }

    public ExpressionBo setMinuteTextField(String value) {
        this.minuteTextField.setText(value);
        return this;
    }

    public JTextField getHourTextField() {
        return hourTextField;
    }

    public ExpressionBo setHourTextField(String value) {
        this.hourTextField.setText(value);
        return this;
    }

    public JTextField getDayTextField() {
        return dayTextField;
    }

    public ExpressionBo setDayTextField(String value) {
        this.dayTextField.setText(value);
        return this;
    }

    public JTextField getMonthTextField() {
        return monthTextField;
    }

    public ExpressionBo setMonthTextField(String value) {
        this.monthTextField.setText(value);
        return this;
    }

    public JTextField getWeekTextField() {
        return weekTextField;
    }

    public ExpressionBo setWeekTextField(String value) {
        this.weekTextField.setText(value);
        return this;
    }

    public JTextField getYearTextField() {
        return yearTextField;
    }

    public ExpressionBo setYearTextField(String value) {
        this.yearTextField.setText(value);
        return this;
    }

    public JTextField getCronExpressionInput() {
        return cronExpressionInput;
    }

    public ExpressionBo setCronExpressionInput(String value) {
        this.cronExpressionInput.setText(value);
        return this;
    }

    public JTextArea getNinthRowTextArea() {
        return ninthRowTextArea;
    }

    public ExpressionBo setNinthRowTextArea(String value) {
        this.ninthRowTextArea.setText(value);
        return this;
    }

    public Integer getCurrentUnitMinValue() {
        return currentUnitMinValue;
    }

    public ExpressionBo setCurrentUnitMinValue(Integer currentUnitMinValue) {
        this.currentUnitMinValue = currentUnitMinValue;
        return this;
    }

    public Integer getCurrentUnitMaxValue() {
        return currentUnitMaxValue;
    }

    public ExpressionBo setCurrentUnitMaxValue(Integer currentUnitMaxValue) {
        this.currentUnitMaxValue = currentUnitMaxValue;
        return this;
    }

    // ------------------------------------------- 构造函数 get/set结束线--------------------------------

    /**
     * 生成cron表达式并显示在Cron 表达式框中
     */
    public ExpressionBo generateCronExpression() {
        StringBuffer cronExpression = new StringBuffer();
        cronExpression.append(this.getSecondTextField().getText()).append(" ")
                .append(this.getMinuteTextField().getText()).append(" ")
                .append(this.getHourTextField().getText()).append(" ")
                .append(this.getDayTextField().getText()).append(" ")
                .append(this.getMonthTextField().getText()).append(" ")
                .append(this.getWeekTextField().getText());
        if (!StringUtil.isBlank(this.getYearTextField().getText())) {
            cronExpression.append(" ").append(this.getYearTextField().getText());
        }
        this.getCronExpressionInput().setText(cronExpression.toString());
        return this;
    }


    /**
     * 反解析cron表达式并生成表达式字段 显示在 表达式字段框中
     *
     * @param paringButton
     */
    public ExpressionBo antiAnalysisGenerationCron(JButton paringButton) {
        String cronText = this.getCronExpressionInput().getText();
        if (StringUtil.isBlank(cronText)) {
            JOptionPane.showMessageDialog(paringButton, "Cron 表达式不能为空！", "错误", 0);
            return this;
        }
        String[] cronArr = cronText.trim().split(" ");
        if (cronArr.length < 6 || cronArr.length > 7) {
            JOptionPane.showMessageDialog(paringButton, "Cron 表达式只能是6-7位！", "错误", 0);
            return this;
        }

        this.clearTextFieldText();
        this.getSecondTextField().setText(cronArr[0]);
        this.getMinuteTextField().setText(cronArr[1]);
        this.getHourTextField().setText(cronArr[2]);
        this.getDayTextField().setText(cronArr[3]);
        this.getMonthTextField().setText(cronArr[4]);
        this.getWeekTextField().setText(cronArr[5]);
        this.getYearTextField().setText(cronArr.length == 7 ? cronArr[6] : "");
        return this;
    }

    /**
     * 生成 最近10次运行时间并显示在text area框中
     */
    public ExpressionBo generateTenRunTime() {
        StringBuilder result = new StringBuilder();
        try {
            CronTriggerImpl cronTriggerImpl = new CronTriggerImpl();
            cronTriggerImpl.setCronExpression(cronExpressionInput.getText());
            // 调用quartz jar包生成最近10次运行时间
            List<Date> dates = TriggerUtils.computeFireTimes(cronTriggerImpl, null, 10);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (Date date : dates) {
                result.append(dateFormat.format(date)).append("\n");
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.ninthRowTextArea.setText(result.toString());
        return this;
    }

}
