package com.logmark.crontools.comm.listener.button;

import com.logmark.crontools.comm.utils.StringUtil;
import com.logmark.crontools.cron.bo.ExpressionBo;
import com.logmark.crontools.cron.bo.page.CronFrameBo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @FileName: TimingPeriodButtonActionListener
 * @Desctiption: 指定循环 单选按钮监听事件
 * @Author: hlb
 * @Date: Created by 2021/2/28 14:25
 * @Modified Update By:
 */
public class TimingPeriodButtonActionListener implements ActionListener {
    private ExpressionBo expressionBo;
    private CronFrameBo cronFrameBo;
    private JTextField textField;

    public TimingPeriodButtonActionListener(ExpressionBo expressionBo, CronFrameBo cronFrameBo, JTextField textField) {
        this.expressionBo = expressionBo;
        this.cronFrameBo = cronFrameBo;
        this.textField = textField;
    }

    // ------------------------------------------- 构造函数 get/set结束线--------------------------------

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!this.cronFrameBo.getTimingPeriodButton().isSelected()) {
            // 未选中指定按钮
            return;
        }
        if (StringUtil.isBlank(this.cronFrameBo.getTimingPeriodMaxValueText().getText())) {
            this.expressionBo.setNinthRowTextArea("'/' 后必须跟整数！");
            return;
        }
        this.textField.setText(this.cronFrameBo.getTimingPeriodMinValueText().getText()
                + ExpressionBo.BACKSLASH + this.cronFrameBo.getTimingPeriodMaxValueText().getText());
        this.expressionBo.generateCronExpression().generateTenRunTime();
    }
}
