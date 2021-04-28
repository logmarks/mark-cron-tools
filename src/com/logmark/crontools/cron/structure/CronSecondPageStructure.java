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
 * @FileName: CronSecondPageStructure
 * @Desctiption: cron 秒页面
 * @Author: hlb
 * @Date: Created by 2021/2/8 9:38
 * @Modified Update By:
 */
public class CronSecondPageStructure extends CronPageStructure {

    public CronSecondPageStructure() {
    }

    public CronSecondPageStructure(DateUnitEnum unit, ExpressionBo expressionBo
            , CardLayout cardLayout, JPanel cardsTopForm) {
        super(unit, expressionBo, cardLayout, cardsTopForm);
    }

// ------------------------------------------- 构造函数 get/set结束线--------------------------------

    @Override
    public String checkExpression(String expressionChar) {
        return ExpressionBo.HORIZONTAL_LINE.equals(expressionChar) ? checkHorizontalLineExpression() :
                ExpressionBo.BACKSLASH.equals(expressionChar) ? checkBackslashExpression() : null;
    }

    public JPanel getExpressionTopForm(DateUnitEnum page) {
        // 表达式之上 上部分
        JPanel expressionTopForm = new JPanel();
        expressionTopForm.setLayout(GridLayoutUtils.TWO_ROWS_ONE_COLUMNS);

        JPanel expressionTopFormOptions = new JPanel();
        expressionTopFormOptions.setLayout(GridLayoutUtils.FIVE_ROWS_ONE_COLUMNS);
        // 窗体选项栏
        expressionTopFormOptions.add(new NavMenuPanel(super.getCardLayout(), super.getCardsTopForm(), page));

        // 窗体选择栏
        ButtonGroup buttonGroup = new ButtonGroup();
        JPanel radioFormFirstRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 5));
        JRadioButton wildcardButton = new JRadioButton("每秒 允许的通配符[, - * /]", true);
        wildcardButton.addActionListener(e -> {
            getExpressionBo().setSecondTextField("*");
            getExpressionBo().generateCronExpression().generateTenRunTime();
        });
        buttonGroup.add(wildcardButton);
        radioFormFirstRow.add(wildcardButton);
        expressionTopFormOptions.add(radioFormFirstRow);

        JPanel radioFormSecondRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 5));

        buttonGroup.add(this.getCronFrameBo().getCycleButton());
        radioFormSecondRow.add(this.getCronFrameBo().getCycleButton());
        radioFormSecondRow.add(this.getCronFrameBo().getCycleMinValueText());
        JLabel semicolonLabel = new JLabel(" -");
        radioFormSecondRow.add(semicolonLabel);
        radioFormSecondRow.add(this.getCronFrameBo().getCycleMaxValueText());
        JLabel radioFormSecondRowLastLabel = new JLabel("秒");
        radioFormSecondRow.add(radioFormSecondRowLastLabel);
        expressionTopFormOptions.add(radioFormSecondRow);

        JPanel radioFormThirdRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 5));
        buttonGroup.add(this.getCronFrameBo().getTimingPeriodButton());
        radioFormThirdRow.add(this.getCronFrameBo().getTimingPeriodButton());
        radioFormThirdRow.add(this.getCronFrameBo().getTimingPeriodMinValueText());
        JLabel radioFormThirdRowCenterLabel = new JLabel(" 秒开始,每");
        radioFormThirdRow.add(radioFormThirdRowCenterLabel);
        radioFormThirdRow.add(this.getCronFrameBo().getTimingPeriodMaxValueText());
        JLabel radioFormThirdRowLastLabel = new JLabel("秒执行一次");
        radioFormThirdRow.add(radioFormThirdRowLastLabel);
        expressionTopFormOptions.add(radioFormThirdRow);

        JPanel radioFormFourthRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 5));
        buttonGroup.add(this.getCronFrameBo().getDesignButton());
        radioFormFourthRow.add(this.getCronFrameBo().getDesignButton());
        expressionTopFormOptions.add(radioFormFourthRow);
        expressionTopForm.add(expressionTopFormOptions);

        // 指定时间网格
        JPanel expressionTopFormGrid = new JPanel();
        expressionTopFormGrid.setLayout(GridLayoutUtils.SIX_ROWS_TEN_COLUMNS);

        for (int i = this.getPageLimitValue().getCurrentUnitMinValue(); i <= this.getPageLimitValue().getCurrentUnitMaxValue(); i++) {
            JCheckBox jCheckBox = new JCheckBox(String.valueOf(i));
            jCheckBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            DesignCheckBoxActionListener actionListener = new DesignCheckBoxActionListener()
                    .setCronFrameBo(this.getCronFrameBo())
                    .setExpressionBo(getExpressionBo())
                    .setTextField(getExpressionBo().getSecondTextField());
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
    public CronFrameBo getCronFrame() {
        // 初始化 秒上部分页面
        CronFrameBo cronFrameBo = new CronFrameBo();
        cronFrameBo.setCycleButton(new JRadioButton("周期从"))
                .setPageUnit(DateUnitEnum.SECOND)
                .setCycleMinValueText(new MyTextField("1", ComponentIdUtils.CYCLE_MIN_VALUE_TEXT_ID))
                .setCycleMaxValueText(new MyTextField("2", ComponentIdUtils.CYCLE_MAX_VALUE_TEXT_ID))
                .setDesignButton(new JRadioButton("指定"))
                .setTimingPeriodButton(new JRadioButton("从"))
                .setTimingPeriodMinValueText(new MyTextField("0", ComponentIdUtils.TIMING_PERIOD_MIN_VALUE_TEXT_ID))
                .setTimingPeriodMaxValueText(new MyTextField("1", ComponentIdUtils.TIMING_PERIOD_MAX_VALUE_TEXT_ID))
                .setDesignTimeList(new ArrayList<>());
        cronFrameBo.getCycleMinValueText().addKeyListener(new ControlTheInputUtils());
        cronFrameBo.getCycleMaxValueText().addKeyListener(new ControlTheInputUtils());
        cronFrameBo.getTimingPeriodMinValueText().addKeyListener(new ControlTheInputUtils());
        cronFrameBo.getTimingPeriodMaxValueText().addKeyListener(new ControlTheInputUtils());

        return cronFrameBo;
    }

    @Override
    CronPageStructure setLVAndCFBAndETFAndBTFL() {
        super.setCronFrameBo(this.getCronFrame());
        super.setPageLimitValue(new PageLimitValue(0, 60));
        super.setExpressionTopForm(this.getExpressionTopForm(super.getDateUnitEnum()));
        super.setButtonAndTextFieldListener(super.getExpressionBo().getSecondTextField());
        return this;
    }
}
