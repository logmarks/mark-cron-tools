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
 * @FileName: CronWeekPageStructure
 * @Desctiption: cron 周页面
 * @Author: hlb
 * @Date: Created by 2021/2/8 9:38
 * @Modified Update By:
 */
public class CronWeekPageStructure extends CronPageStructure {

    public CronWeekPageStructure() {
    }

    public CronWeekPageStructure(DateUnitEnum unit, ExpressionBo expressionBo
            , CardLayout cardLayout, JPanel cardsTopForm) {
        super(unit, expressionBo, cardLayout, cardsTopForm);
    }

    // ---------------------------- get/set分割线 ------------------------------

    @Override
    public String checkExpression(String expressionChar) {
        return null;
    }

    public JPanel getExpressionTopForm(DateUnitEnum page) {
        // 表达式之上 上部分
        JPanel expressionTopForm = new JPanel();
        expressionTopForm.setLayout(GridLayoutUtils.EIGHT_ROWS_ONE_COLUMNS);
        // 窗体选项栏
        expressionTopForm.add(new NavMenuPanel(super.getCardLayout(), super.getCardsTopForm(), page));

        // 窗体选择栏
        ButtonGroup buttonGroup = new ButtonGroup();
        JPanel radioFormFirstRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 5));
        this.getCronFrameBo().setWildcardButton(new JRadioButton("每周 允许的通配符[, - * / L #]", true));
        this.getCronFrameBo().getWildcardButton().addActionListener(e -> {
            if (!this.getCronFrameBo().getWildcardButton().isSelected()) {
                // 未选中指定按钮
                return;
            }
            getExpressionBo().setWeekTextField("*");
            getExpressionBo().generateCronExpression().generateTenRunTime();
        });
        buttonGroup.add(this.getCronFrameBo().getWildcardButton());
        radioFormFirstRow.add(this.getCronFrameBo().getWildcardButton());
        expressionTopForm.add(radioFormFirstRow);

        JPanel radioFormSecondRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 5));
        this.getCronFrameBo().setNoDesignButton(new JRadioButton("不指定", true));
        this.getCronFrameBo().getNoDesignButton().addActionListener(e -> {
            if (!this.getCronFrameBo().getNoDesignButton().isSelected()) {
                // 未选中指定按钮
                return;
            }
            getExpressionBo().setWeekTextField("?");
            if (ExpressionBo.QUESTION_MARK.equals(getExpressionBo().getDayTextField().getText())) {
                getExpressionBo().setNinthRowTextArea("'？' 日或星期（周）中，只能有一个使用！");
                return;
            }
            getExpressionBo().generateCronExpression().generateTenRunTime();
        });
        buttonGroup.add(this.getCronFrameBo().getNoDesignButton());
        radioFormSecondRow.add(this.getCronFrameBo().getNoDesignButton());
        expressionTopForm.add(radioFormSecondRow);

        JPanel radioFormThirdRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 5));
        buttonGroup.add(this.getCronFrameBo().getCycleButton());
        radioFormThirdRow.add(this.getCronFrameBo().getCycleButton());
        radioFormThirdRow.add(this.getCronFrameBo().getCycleMinValueText());
        JLabel semicolonLabel = new JLabel(" -");
        radioFormThirdRow.add(semicolonLabel);
        radioFormThirdRow.add(this.getCronFrameBo().getCycleMaxValueText());
        expressionTopForm.add(radioFormThirdRow);

        JPanel radioFormFourthRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 5));
        buttonGroup.add(this.getCronFrameBo().getTimingPeriodButton());
        radioFormFourthRow.add(this.getCronFrameBo().getTimingPeriodButton());
        radioFormFourthRow.add(this.getCronFrameBo().getTimingPeriodMinValueText());
        JLabel radioFormFourthRowCenterLabel = new JLabel("周 的星期");
        radioFormFourthRow.add(radioFormFourthRowCenterLabel);
        radioFormFourthRow.add(this.getCronFrameBo().getTimingPeriodMaxValueText());
        expressionTopForm.add(radioFormFourthRow);

        JPanel radioFormFifthRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 5));
        buttonGroup.add(this.getCronFrameBo().getMonthLastWeekButton());
        radioFormFifthRow.add(this.getCronFrameBo().getMonthLastWeekButton());
        radioFormFifthRow.add(this.getCronFrameBo().getMonthLastWeekDayValue());
        expressionTopForm.add(radioFormFifthRow);

        JPanel radioFormSixthRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 5));
        buttonGroup.add(this.getCronFrameBo().getDesignButton());
        radioFormSixthRow.add(this.getCronFrameBo().getDesignButton());
        expressionTopForm.add(radioFormSixthRow);

        // 指定时间网格
        JPanel expressionTopFormGridTop = new JPanel();
        expressionTopFormGridTop.setLayout(GridLayoutUtils.ONE_ROWS_SEVEN_COLUMNS);
        for (int i = this.getPageLimitValue().getCurrentUnitMinValue(); i <= this.getPageLimitValue().getCurrentUnitMaxValue(); i++) {
            JCheckBox jCheckBox = new JCheckBox(String.valueOf(i));
            jCheckBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            DesignCheckBoxActionListener actionListener = new DesignCheckBoxActionListener()
                    .setCronFrameBo(this.getCronFrameBo())
                    .setExpressionBo(getExpressionBo())
                    .setTextField(getExpressionBo().getWeekTextField());
            jCheckBox.addActionListener(actionListener);
            expressionTopFormGridTop.add(jCheckBox);
        }
        expressionTopForm.add(expressionTopFormGridTop);

        return expressionTopForm;
    }

    /**
     * cron初始化，未添加监听事件
     *
     * @return
     */
    public CronFrameBo getCronFrame() {
        // 初始化 日上部分页面
        CronFrameBo cronFrameBo = new CronFrameBo();
        cronFrameBo.setCycleButton(new JRadioButton("周期 从星期"))
                .setCycleMinValueText(new MyTextField("1", ComponentIdUtils.CYCLE_MIN_VALUE_TEXT_ID))
                .setCycleMaxValueText(new MyTextField("2", ComponentIdUtils.CYCLE_MAX_VALUE_TEXT_ID))
                .setDesignButton(new JRadioButton("指定"))
                .setTimingPeriodButton(new JRadioButton("第"))
                .setTimingPeriodMinValueText(new MyTextField("1", ComponentIdUtils.TIMING_PERIOD_MIN_VALUE_TEXT_ID))
                .setTimingPeriodMaxValueText(new MyTextField("1", ComponentIdUtils.TIMING_PERIOD_MAX_VALUE_TEXT_ID))
                .setMonthLastWeekDayValue(new MyTextField("1", ComponentIdUtils.MONTH_LAST_WEEK_VALUE_ID))
                .setDesignTimeList(new ArrayList<>());
        cronFrameBo.getCycleMinValueText().addKeyListener(new ControlTheInputUtils());
        cronFrameBo.getCycleMaxValueText().addKeyListener(new ControlTheInputUtils());
        cronFrameBo.getTimingPeriodMinValueText().addKeyListener(new ControlTheInputUtils());
        cronFrameBo.getTimingPeriodMaxValueText().addKeyListener(new ControlTheInputUtils());
        cronFrameBo.setMonthLastWeekButton(new JRadioButton("本月最后一个星期"));
        cronFrameBo.getMonthLastWeekDayValue().addKeyListener(new ControlTheInputUtils());

        return cronFrameBo;
    }

    @Override
    CronPageStructure setLVAndCFBAndETFAndBTFL() {
        super.setCronFrameBo(this.getCronFrame());
        super.setPageLimitValue(new PageLimitValue(0, 7));
        super.setExpressionTopForm(this.getExpressionTopForm(super.getDateUnitEnum()));
        super.setButtonAndTextFieldListener(super.getExpressionBo().getWeekTextField());
        return this;
    }
}
