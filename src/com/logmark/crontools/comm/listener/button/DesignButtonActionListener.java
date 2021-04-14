package com.logmark.crontools.comm.listener.button;

import com.logmark.crontools.cron.bo.ExpressionBo;
import com.logmark.crontools.cron.bo.page.CronFrameBo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @FileName: DesignButtonActionListener
 * @Desctiption: 指定按钮监听事件
 * @Author: hlb
 * @Date: Created by 2021/2/28 14:17
 * @Modified Update By:
 */
public class DesignButtonActionListener implements ActionListener {
    private ExpressionBo expressionBo;
    private CronFrameBo cronFrameBo;
    private JTextField textField;

    public DesignButtonActionListener(ExpressionBo expressionBo, CronFrameBo cronFrameBo, JTextField textField) {
        this.expressionBo = expressionBo;
        this.cronFrameBo = cronFrameBo;
        this.textField = textField;
    }

    // ------------------------------------------- 构造函数 get/set结束线--------------------------------

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!this.cronFrameBo.getDesignButton().isSelected()) {
            // 未选中指定按钮
            return;
        }
        if (this.cronFrameBo.getDesignTimeList() == null || this.cronFrameBo.getDesignTimeList().isEmpty()) {
            this.expressionBo.setNinthRowTextArea("'？'只能在日和星期（周）中指定使用，其作用为不指定");
            return;
        }
        StringBuilder text = new StringBuilder();
        for (Integer value : this.cronFrameBo.getDesignTimeList()) {
            text.append(value).append(ExpressionBo.COMMA);
        }
        this.textField.setText(text.substring(0, text.length() - 1));
        this.expressionBo.generateCronExpression().generateTenRunTime();
    }
}
