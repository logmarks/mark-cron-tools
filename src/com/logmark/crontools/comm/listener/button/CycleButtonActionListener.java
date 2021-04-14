package com.logmark.crontools.comm.listener.button;

import com.logmark.crontools.comm.utils.StringUtil;
import com.logmark.crontools.cron.bo.ExpressionBo;
import com.logmark.crontools.cron.bo.page.CronFrameBo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @FileName: CycleButtonActionListener
 * @Desctiption: 周期选择按钮监听器
 * @Author: hlb
 * @Date: Created by 2021/2/28 13:50
 * @Modified Update By:
 */
public class CycleButtonActionListener implements ActionListener {
    private ExpressionBo expressionBo;
    private CronFrameBo cronFrameBo;
    private JTextField textField;

    public CycleButtonActionListener(ExpressionBo expressionBo, CronFrameBo cronFrameBo, JTextField textField) {
        this.expressionBo = expressionBo;
        this.cronFrameBo = cronFrameBo;
        this.textField = textField;
    }

    // ------------------------------------------- 构造函数 get/set结束线--------------------------------

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!this.cronFrameBo.getCycleButton().isSelected()) {
            // 未选中指定按钮
            return;
        }
        String result = checkHorizontalLineExpression(expressionBo, cronFrameBo.getCycleMinValueText(), cronFrameBo.getCycleMaxValueText());
        if (!StringUtil.isBlank(result)) {
            this.expressionBo.setNinthRowTextArea(result);
            return;
        }
        this.textField.setText(this.cronFrameBo.getCycleMinValueText().getText()
                + ExpressionBo.HORIZONTAL_LINE + this.cronFrameBo.getCycleMaxValueText().getText());
        this.expressionBo.generateCronExpression().generateTenRunTime();
    }

    /**
     * 周期 - 规则判断
     *
     * @return
     */
    public String checkHorizontalLineExpression(ExpressionBo expressionBo, JTextField cycleMinValueText, JTextField cycleMaxValueText) {
        if (StringUtil.isBlank(cycleMinValueText.getText())) {
            return cronFrameBo.getPageUnit().getContent() + " 周期从 '' 起始值不能为空！";
        }
        if (StringUtil.isBlank(cycleMaxValueText.getText())) {
            return cronFrameBo.getPageUnit().getContent() + "秒 周期从 " + cycleMinValueText.getText() + " 到 '' 终止值不能为空！";
        }
        return null;
    }
}
