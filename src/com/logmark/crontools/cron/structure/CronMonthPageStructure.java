package com.logmark.crontools.cron.structure;


import com.logmark.crontools.comm.listener.DesignCheckBoxActionListener;
import com.logmark.crontools.comm.utils.*;
import com.logmark.crontools.cron.bo.ExpressionBo;
import com.logmark.crontools.cron.bo.PageLimitValue;
import com.logmark.crontools.cron.bo.page.CronFrameBo;
import com.logmark.crontools.cron.enums.DateUnitEnum;
import com.logmark.crontools.cron.page.tabs.NavMenuPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @FileName: CronMonthPageStructure
 * @Desctiption: cron 月页面
 * @Author: hlb
 * @Date: Created by 2021/2/8 9:38
 * @Modified Update By:
 */
public class CronMonthPageStructure extends CronPageStructure {

    public CronMonthPageStructure() {
    }

    public CronMonthPageStructure(DateUnitEnum unit, ExpressionBo expressionBo
            , CardLayout cardLayout, JPanel cardsTopForm) {
        super(unit, expressionBo, cardLayout, cardsTopForm);
    }

    // ------------------------------------------- 构造函数 get/set结束线--------------------------------

    @Override
    public String checkExpression(String expressionChar) {
        return null;
    }

    @Override
    public JPanel getTopForm(DateUnitEnum page) {
        // 表达式之上 上部分
        JPanel expressionTopForm = new JPanel();
        expressionTopForm.setLayout(GridLayoutUtils.SIX_ROWS_ONE_COLUMNS);

        // 窗体选项栏
        expressionTopForm.add(new NavMenuPanel(super.getCardLayout(), super.getCardsTopForm(), page));

        // 窗体选择栏
        ButtonGroup buttonGroup = new ButtonGroup();
        JPanel radioFormFirstRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 5));
        this.getCronFrameBo().setWildcardButton(new JRadioButton("每月 允许的通配符[, - * /]", true));
        this.getCronFrameBo().getWildcardButton().addActionListener(e -> {
            if (!this.getCronFrameBo().getWildcardButton().isSelected()) {
                // 未选中指定按钮
                return;
            }
            getExpressionBo().setMonthTextField("*");
            getExpressionBo().generateCronExpression().generateTenRunTime();
        });
        buttonGroup.add(this.getCronFrameBo().getWildcardButton());
        radioFormFirstRow.add(this.getCronFrameBo().getWildcardButton());
        expressionTopForm.add(radioFormFirstRow);

        JPanel radioFormSecondRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 5));

        buttonGroup.add(this.getCronFrameBo().getCycleButton());
        radioFormSecondRow.add(this.getCronFrameBo().getCycleButton());
        radioFormSecondRow.add(this.getCronFrameBo().getCycleMinValueText());
        JLabel semicolonLabel = new JLabel(" -");
        radioFormSecondRow.add(semicolonLabel);
        radioFormSecondRow.add(this.getCronFrameBo().getCycleMaxValueText());
        JLabel radioFormSecondRowLastLabel = new JLabel("月");
        radioFormSecondRow.add(radioFormSecondRowLastLabel);
        expressionTopForm.add(radioFormSecondRow);

        JPanel radioFormThirdRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 5));
        buttonGroup.add(this.getCronFrameBo().getTimingPeriodButton());
        radioFormThirdRow.add(this.getCronFrameBo().getTimingPeriodButton());
        radioFormThirdRow.add(this.getCronFrameBo().getTimingPeriodMinValueText());
        JLabel radioFormThirdRowCenterLabel = new JLabel(" 月开始,每");
        radioFormThirdRow.add(radioFormThirdRowCenterLabel);
        radioFormThirdRow.add(this.getCronFrameBo().getTimingPeriodMaxValueText());
        JLabel radioFormThirdRowLastLabel = new JLabel("月执行一次");
        radioFormThirdRow.add(radioFormThirdRowLastLabel);
        expressionTopForm.add(radioFormThirdRow);

        JPanel radioFormFourthRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 5));
        buttonGroup.add(this.getCronFrameBo().getDesignButton());
        radioFormFourthRow.add(this.getCronFrameBo().getDesignButton());
        expressionTopForm.add(radioFormFourthRow);

        // 指定时间网格
        JPanel expressionTopFormGrid = new JPanel();
        expressionTopFormGrid.setLayout(GridLayoutUtils.ONE_ROWS_TWELVE_COLUMNS);

        for (int i = this.getExpressionBo().getCurrentUnitMinValue(); i <= this.getExpressionBo().getCurrentUnitMaxValue(); i++) {
            JCheckBox jCheckBox = new JCheckBox(String.valueOf(i));
            jCheckBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            DesignCheckBoxActionListener actionListener = new DesignCheckBoxActionListener()
                    .setCronFrameBo(this.getCronFrameBo())
                    .setExpressionBo(getExpressionBo())
                    .setTextField(getExpressionBo().getMonthTextField());
            jCheckBox.addActionListener(actionListener);
            expressionTopFormGrid.add(jCheckBox);
        }
        expressionTopForm.add(expressionTopFormGrid);

        return expressionTopForm;
    }

    /**
     * cron初始化，未添加监听事件
     *
     * @return
     */
    @Override
    public CronFrameBo getCronFrame() {
        // 初始化 秒上部分页面
        CronFrameBo cronFrameBo = new CronFrameBo();

        // 初始化 秒上部分页面
        cronFrameBo.setCycleButton(new JRadioButton("周期从"))
                .setCycleMinValueText(new MyTextField("1", ComponentIdUtils.CYCLE_MIN_VALUE_TEXT_ID))
                .setCycleMaxValueText(new MyTextField("2", ComponentIdUtils.CYCLE_MAX_VALUE_TEXT_ID))
                .setDesignButton(new JRadioButton("指定"))
                .setTimingPeriodButton(new JRadioButton("从"))
                .setTimingPeriodMinValueText(new MyTextField("1", ComponentIdUtils.TIMING_PERIOD_MIN_VALUE_TEXT_ID))
                .setTimingPeriodMaxValueText(new MyTextField("1", ComponentIdUtils.TIMING_PERIOD_MAX_VALUE_TEXT_ID))
                .setDesignTimeList(new ArrayList<>());

        cronFrameBo.getCycleMinValueText().addKeyListener(new ControlTheInputUtils());
        cronFrameBo.getCycleMaxValueText().addKeyListener(new ControlTheInputUtils());
        cronFrameBo.getTimingPeriodMinValueText().addKeyListener(new ControlTheInputUtils());
        cronFrameBo.getTimingPeriodMaxValueText().addKeyListener(new ControlTheInputUtils());

        return cronFrameBo;
    }

    @Override
    CronPageStructure setButtonAndTextFieldListener() {
        ExpressionBo expressionBo = super.getExpressionBo();
        expressionBo.setCurrentUnitMinValue(0).setCurrentUnitMaxValue(12);
        super.setExpressionBo(expressionBo);
        super.setPageLimitValue(new PageLimitValue(0, 12));
        super.setButtonAndTextFieldListener(expressionBo.getMonthTextField());
        return this;
    }
}
