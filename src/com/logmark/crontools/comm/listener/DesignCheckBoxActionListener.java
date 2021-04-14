package com.logmark.crontools.comm.listener;

import com.logmark.crontools.cron.bo.ExpressionBo;
import com.logmark.crontools.cron.bo.page.CronFrameBo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;

/**
 * @FileName: DesignCheckBoxActionListener
 * @Desctiption: 指定区域网格监听器
 * @Author: hlb
 * @Date: Created by 2021/2/25 14:25
 * @Modified Update By:
 */
public class DesignCheckBoxActionListener implements ActionListener {
    private ExpressionBo expressionBo;
    private CronFrameBo cronFrameBo;
    private JTextField textField;

    public DesignCheckBoxActionListener() {
    }

    public DesignCheckBoxActionListener(ExpressionBo expressionBo, CronFrameBo cronFrameBo, JTextField textField) {
        this.expressionBo = expressionBo;
        this.cronFrameBo = cronFrameBo;
        this.textField = textField;
    }

    public ExpressionBo getExpressionBo() {
        return expressionBo;
    }

    public DesignCheckBoxActionListener setExpressionBo(ExpressionBo expressionBo) {
        this.expressionBo = expressionBo;
        return this;
    }

    public CronFrameBo getCronFrameBo() {
        return cronFrameBo;
    }

    public DesignCheckBoxActionListener setCronFrameBo(CronFrameBo cronFrameBo) {
        this.cronFrameBo = cronFrameBo;
        return this;
    }

    public JTextField getTextField() {
        return textField;
    }

    public DesignCheckBoxActionListener setTextField(JTextField textField) {
        this.textField = textField;
        return this;
    }
    // ------------------------------------------- 构造函数 get/set结束线--------------------------------

    @Override
    public void actionPerformed(ActionEvent e) {
        JCheckBox temp = (JCheckBox) e.getSource();
        boolean b = temp.isSelected() ? this.cronFrameBo.getDesignTimeList().add(Integer.valueOf(e.getActionCommand()))
                : this.cronFrameBo.getDesignTimeList().remove(Integer.valueOf(e.getActionCommand()));
        this.cronFrameBo.getDesignTimeList().sort(Comparator.naturalOrder());
        if (!cronFrameBo.getDesignButton().isSelected()) {
            // 未选中指定按钮
            return;
        }
        if (this.cronFrameBo.getDesignTimeList() == null || this.cronFrameBo.getDesignTimeList().isEmpty()) {
            getExpressionBo().setNinthRowTextArea("'？'只能在日和星期（周）中指定使用，其作用为不指定");
            return;
        }
        StringBuilder secondText = new StringBuilder();
        for (Integer value : this.cronFrameBo.getDesignTimeList()) {
            secondText.append(value).append(",");
        }
        this.getTextField().setText(secondText.substring(0, secondText.length() - 1));
        getExpressionBo().generateCronExpression().generateTenRunTime();

    }

}
