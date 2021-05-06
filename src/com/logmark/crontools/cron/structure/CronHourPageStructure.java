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
 * @FileName: CronHourPageStructure
 * @Desctiption: cron 时页面
 * @Author: hlb
 * @Date: Created by 2021/2/8 9:38
 * @Modified Update By:
 */
public class CronHourPageStructure extends CronPageStructure {


    public CronHourPageStructure() {

    }

    public CronHourPageStructure(DateUnitEnum unit, ExpressionBo expressionBo
            , CardLayout cardLayout, JPanel cardsTopForm) {
        super(unit, expressionBo, cardLayout, cardsTopForm);
    }

    // ---------------------------- get/set分割线 ------------------------------

    @Override
    public String checkExpression(String expressionChar) {
        return null;
    }

    public JPanel getExpressionTopForm(DateUnitEnum page) {

        JPanel expressionTopForm = new JPanel();
        expressionTopForm.setLayout(GridLayoutUtils.SEVEN_ROWS_ONE_COLUMNS);
        // 窗体选项栏
        expressionTopForm.add(new NavMenuPanel(super.getCardLayout(), super.getCardsTopForm(), page));

        // 窗体选择栏
        ButtonGroup buttonGroup = new ButtonGroup();
        JPanel radioFormFirstRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 5));
        JRadioButton wildcardButton = new JRadioButton("每小时 允许的通配符[, - * /]", true);
        wildcardButton.addActionListener(e -> {
            getExpressionBo().setHourTextField("*");
            getExpressionBo().generateCronExpression().generateTenRunTime();
        });
        buttonGroup.add(wildcardButton);
        radioFormFirstRow.add(wildcardButton);
        expressionTopForm.add(radioFormFirstRow);

        JPanel radioFormSecondRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 5));

        buttonGroup.add(this.getCronFrameBo().getCycleButton());
        radioFormSecondRow.add(this.getCronFrameBo().getCycleButton());
        radioFormSecondRow.add(this.getCronFrameBo().getCycleMinValueText());
        JLabel semicolonLabel = new JLabel(" -");
        radioFormSecondRow.add(semicolonLabel);
        radioFormSecondRow.add(this.getCronFrameBo().getCycleMaxValueText());
        JLabel radioFormSecondRowLastLabel = new JLabel("小时");
        radioFormSecondRow.add(radioFormSecondRowLastLabel);
        expressionTopForm.add(radioFormSecondRow);

        JPanel radioFormThirdRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 5));
        buttonGroup.add(this.getCronFrameBo().getTimingPeriodButton());
        radioFormThirdRow.add(this.getCronFrameBo().getTimingPeriodButton());
        radioFormThirdRow.add(this.getCronFrameBo().getTimingPeriodMinValueText());
        JLabel radioFormThirdRowCenterLabel = new JLabel(" 小时开始,每");
        radioFormThirdRow.add(radioFormThirdRowCenterLabel);
        radioFormThirdRow.add(this.getCronFrameBo().getTimingPeriodMaxValueText());
        JLabel radioFormThirdRowLastLabel = new JLabel("小时执行一次");
        radioFormThirdRow.add(radioFormThirdRowLastLabel);
        expressionTopForm.add(radioFormThirdRow);

        JPanel radioFormFourthRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 5));
        buttonGroup.add(this.getCronFrameBo().getDesignButton());
        radioFormFourthRow.add(this.getCronFrameBo().getDesignButton());
        expressionTopForm.add(radioFormFourthRow);

        // 指定时间网格
        JPanel expressionTopFormGrid = new JPanel();
        expressionTopFormGrid.setLayout(GridLayoutUtils.ONE_ROWS_THIRTEEN_COLUMNS);
        JLabel amLabel = new JLabel("AM: ");
        expressionTopFormGrid.add(amLabel);
        for (int i = this.getPageLimitValue().getCurrentUnitMinValue(); i <= this.getPageLimitValue().getCurrentUnitMaxValue() / 2; i++) {
            JCheckBox jCheckBox = new JCheckBox(String.valueOf(i));
            jCheckBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            DesignCheckBoxActionListener actionListener = new DesignCheckBoxActionListener()
                    .setCronFrameBo(this.getCronFrameBo())
                    .setExpressionBo(getExpressionBo())
                    .setTextField(getExpressionBo().getHourTextField());
            jCheckBox.addActionListener(actionListener);
            expressionTopFormGrid.add(jCheckBox);
        }
        expressionTopForm.add(expressionTopFormGrid);

        JPanel expressionTopFormGridPm = new JPanel();
        expressionTopFormGridPm.setLayout(GridLayoutUtils.ONE_ROWS_THIRTEEN_COLUMNS);
        JLabel pmLabel = new JLabel("PM: ");
        expressionTopFormGridPm.add(pmLabel);
        for (int i = this.getPageLimitValue().getCurrentUnitMaxValue() / 2 + 1; i <= this.getPageLimitValue().getCurrentUnitMaxValue(); i++) {
            JCheckBox jCheckBox = new JCheckBox(String.valueOf(i));
            jCheckBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            DesignCheckBoxActionListener actionListener = new DesignCheckBoxActionListener()
                    .setCronFrameBo(this.getCronFrameBo())
                    .setExpressionBo(getExpressionBo())
                    .setTextField(getExpressionBo().getHourTextField());
            jCheckBox.addActionListener(actionListener);
            expressionTopFormGridPm.add(jCheckBox);
        }
        expressionTopForm.add(expressionTopFormGridPm);

        return expressionTopForm;
    }

    /**
     * cron初始化，未添加监听事件
     *
     * @return
     */
    public CronFrameBo getCronFrame() {
        // 初始化 时上部分页面
        CronFrameBo cronFrameBo = new CronFrameBo();
        cronFrameBo.setCycleButton(new JRadioButton("周期从"))
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
        super.setPageLimitValue(new PageLimitValue(0, 23));
        super.setExpressionTopForm(this.getExpressionTopForm(super.getDateUnitEnum()));
        super.setButtonAndTextFieldListener(super.getExpressionBo().getHourTextField());
        return this;
    }
}
