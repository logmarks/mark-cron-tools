package com.logmark.crontools.cron.structure;


import com.logmark.crontools.comm.utils.*;
import com.logmark.crontools.cron.bo.ExpressionBo;
import com.logmark.crontools.cron.bo.PageLimitValue;
import com.logmark.crontools.cron.bo.page.CronFrameBo;
import com.logmark.crontools.cron.enums.DateUnitEnum;
import com.logmark.crontools.cron.page.tabs.NavMenuPanel;

import javax.swing.*;
import java.awt.*;

/**
 * @FileName: CronYearPageStructure
 * @Desctiption: cron 年页面
 * @Author: hlb
 * @Date: Created by 2021/2/8 9:38
 * @Modified Update By:
 */
public class CronYearPageStructure extends CronPageStructure {

    public CronYearPageStructure() {
    }

    public CronYearPageStructure(DateUnitEnum unit, ExpressionBo expressionBo
            , CardLayout cardLayout, JPanel cardsTopForm) {
        super(unit, expressionBo, cardLayout, cardsTopForm);
    }

    // ------------------------------------------- 构造函数 get/set结束线--------------------------------

    @Override
    public String checkExpression(String expressionChar) {
        return null;
    }

    public JPanel getExpressionTopForm(DateUnitEnum page) {
        // 表达式之上 上部分
        JPanel expressionTopForm = new JPanel();
        expressionTopForm.setLayout(GridLayoutUtils.FIVE_ROWS_ONE_COLUMNS);

        // 窗体选项栏
        ButtonGroup buttonGroup = new ButtonGroup();
        expressionTopForm.add(new NavMenuPanel(super.getCardLayout(), super.getCardsTopForm(), page));

        // 窗体选择栏
        JPanel radioFormFirstRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 5));
        this.getCronFrameBo().setNoWildcardButton(new JRadioButton("不指定 允许的通配符[, - * /] 非必填", true));
        radioFormFirstRow.add(this.getCronFrameBo().getNoWildcardButton());
        this.getCronFrameBo().getNoWildcardButton().addActionListener(e -> {
            if (!this.getCronFrameBo().getNoWildcardButton().isSelected()) {
                // 未选中指定按钮
                return;
            }
            getExpressionBo().setYearTextField("");
            getExpressionBo().generateCronExpression().generateTenRunTime();
        });
        buttonGroup.add(this.getCronFrameBo().getNoWildcardButton());
        expressionTopForm.add(radioFormFirstRow);

        JPanel radioFormSecondRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 5));
        this.getCronFrameBo().setWildcardButton(new JRadioButton("每年"));
        this.getCronFrameBo().getWildcardButton().addActionListener(e -> {
            if (!this.getCronFrameBo().getWildcardButton().isSelected()) {
                // 未选中指定按钮
                return;
            }
            getExpressionBo().setYearTextField("*");
            getExpressionBo().generateCronExpression().generateTenRunTime();
        });
        buttonGroup.add(this.getCronFrameBo().getWildcardButton());
        radioFormSecondRow.add(this.getCronFrameBo().getWildcardButton());
        expressionTopForm.add(radioFormSecondRow);

        JPanel radioFormThirdRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 5));
        buttonGroup.add(this.getCronFrameBo().getCycleButton());
        radioFormThirdRow.add(this.getCronFrameBo().getCycleButton());
        radioFormThirdRow.add(this.getCronFrameBo().getCycleMinValueText());
        JLabel radioFormThirdRowCenterLabel = new JLabel("-");
        radioFormThirdRow.add(radioFormThirdRowCenterLabel);
        radioFormThirdRow.add(this.getCronFrameBo().getCycleMaxValueText());
        expressionTopForm.add(radioFormThirdRow);

        JPanel radioFormFourthRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 5));
        buttonGroup.add(this.getCronFrameBo().getTimingPeriodButton());
        radioFormFourthRow.add(this.getCronFrameBo().getTimingPeriodButton());
        radioFormFourthRow.add(this.getCronFrameBo().getTimingPeriodMinValueText());
        JLabel radioFormFourthRowCenterLabel = new JLabel("年 开始，每");
        radioFormFourthRow.add(radioFormFourthRowCenterLabel);
        radioFormFourthRow.add(this.getCronFrameBo().getTimingPeriodMaxValueText());
        JLabel radioFormFourthRowRightLabel = new JLabel("年执行一次");
        radioFormFourthRow.add(radioFormFourthRowRightLabel);
        expressionTopForm.add(radioFormFourthRow);

        return expressionTopForm;
    }

    /**
     * cron初始化，未添加监听事件
     *
     * @return
     */
    public CronFrameBo getCronFrame() {
        // 初始化 年上部分页面
        CronFrameBo cronFrameBo = new CronFrameBo();
        cronFrameBo.setCycleButton(new JRadioButton("周期 从"))
                .setCycleMinValueText(new MyTextField("2020", ComponentIdUtils.CYCLE_MIN_VALUE_TEXT_ID))
                .setCycleMaxValueText(new MyTextField("3000", ComponentIdUtils.CYCLE_MAX_VALUE_TEXT_ID))
                .setTimingPeriodButton(new JRadioButton("从"))
                .setTimingPeriodMinValueText(new MyTextField("2020", ComponentIdUtils.TIMING_PERIOD_MIN_VALUE_TEXT_ID))
                .setTimingPeriodMaxValueText(new MyTextField("1", ComponentIdUtils.TIMING_PERIOD_MAX_VALUE_TEXT_ID));
        cronFrameBo.getTimingPeriodMinValueText().addKeyListener(new ControlTheInputUtils());
        cronFrameBo.getTimingPeriodMaxValueText().addKeyListener(new ControlTheInputUtils());

        return cronFrameBo;
    }

    @Override
    CronPageStructure setLVAndCFBAndETFAndBTFL() {
        super.setCronFrameBo(this.getCronFrame());
        super.setPageLimitValue(new PageLimitValue(2020, 3000));
        super.setExpressionTopForm(this.getExpressionTopForm(super.getDateUnitEnum()));
        super.setButtonAndTextFieldListener(super.getExpressionBo().getYearTextField());
        return this;
    }
}
