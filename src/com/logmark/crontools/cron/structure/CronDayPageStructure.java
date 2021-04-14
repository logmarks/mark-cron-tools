package com.logmark.crontools.cron.structure;


import com.logmark.crontools.comm.listener.DesignCheckBoxActionListener;
import com.logmark.crontools.comm.utils.*;
import com.logmark.crontools.cron.bo.ExpressionBo;
import com.logmark.crontools.cron.bo.page.CronFrameBo;
import com.logmark.crontools.cron.enums.DateUnitEnum;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @FileName: CronDayPageStructure
 * @Desctiption: cron 日页面
 * @Author: hlb
 * @Date: Created by 2021/2/8 9:38
 * @Modified Update By:
 */
public class CronDayPageStructure extends CronPageStructure {

    public CronDayPageStructure() {
    }

    // ------------------------------------------- 构造函数 get/set结束线--------------------------------

    @Override
    public String checkExpression(String expressionChar) {
        return null;
    }

    @Override
    public void setUpTextField(JTextField textField) {

    }

    @Override
    public JPanel getTopForm() {
        // 表达式之上 上部分
        JPanel expressionTopForm = new JPanel();
        expressionTopForm.setLayout(GridLayoutUtils.TEN_ROWS_ONE_COLUMNS);
        // 窗体选项栏
        expressionTopForm.add(this.getOptions(DateUnitEnum.DAY));

        // 窗体选择栏
        ButtonGroup buttonGroup = new ButtonGroup();
        JPanel radioFormFirstRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 5));
        this.getCronFrameBo().setWildcardButton(new JRadioButton("日 允许的通配符[, - * / L W]", true));
        this.getCronFrameBo().getWildcardButton().addActionListener(e -> {
            if (!this.getCronFrameBo().getWildcardButton().isSelected()) {
                // 未选中指定按钮
                return;
            }
            getExpressionBo().setDayTextField("*");
            getExpressionBo().generateCronExpression().generateTenRunTime();
        });
        buttonGroup.add(this.getCronFrameBo().getWildcardButton());
        radioFormFirstRow.add(this.getCronFrameBo().getWildcardButton());
        expressionTopForm.add(radioFormFirstRow);

        JPanel radioFormSecondRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 5));
        this.getCronFrameBo().setNoDesignButton(new JRadioButton("不指定"));
        this.getCronFrameBo().getNoDesignButton().addActionListener(e -> {
            if (!this.getCronFrameBo().getNoDesignButton().isSelected()) {
                // 未选中指定按钮
                return;
            }
            getExpressionBo().setDayTextField("?");
            if (ExpressionBo.QUESTION_MARK.equals(getExpressionBo().getWeekTextField().getText())) {
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
        JLabel radioFormThirdRowLastLabel = new JLabel("日");
        radioFormThirdRow.add(radioFormThirdRowLastLabel);
        expressionTopForm.add(radioFormThirdRow);

        JPanel radioFormFourthRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 5));
        buttonGroup.add(this.getCronFrameBo().getTimingPeriodButton());
        radioFormFourthRow.add(this.getCronFrameBo().getTimingPeriodButton());
        radioFormFourthRow.add(this.getCronFrameBo().getTimingPeriodMinValueText());
        JLabel radioFormFourthRowCenterLabel = new JLabel(" 日开始,每");
        radioFormFourthRow.add(radioFormFourthRowCenterLabel);
        radioFormFourthRow.add(this.getCronFrameBo().getTimingPeriodMaxValueText());
        JLabel radioFormFourthRowLastLabel = new JLabel("日执行一次");
        radioFormFourthRow.add(radioFormFourthRowLastLabel);
        expressionTopForm.add(radioFormFourthRow);

        JPanel radioFormFifthRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 5));

        buttonGroup.add(this.getCronFrameBo().getMonthLastWeekDayButton());
        radioFormFifthRow.add(this.getCronFrameBo().getMonthLastWeekDayButton());
        radioFormFifthRow.add(this.getCronFrameBo().getMonthLastWeekDayValue());
        JLabel monthLastWeekDayLabel = new JLabel(" 号最近的那个工作日");
        radioFormFifthRow.add(monthLastWeekDayLabel);
        expressionTopForm.add(radioFormFifthRow);

        JPanel radioFormSixthRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 5));
        this.getCronFrameBo().setLastDayButton(new JRadioButton("本月最后一天"));
        this.getCronFrameBo().getLastDayButton().addActionListener(e -> {
            if (!getCronFrameBo().getLastDayButton().isSelected()) {
                // 未选中指定按钮
                return;
            }
            getExpressionBo().setDayTextField("L");
            getExpressionBo().generateCronExpression().generateTenRunTime();
        });
        buttonGroup.add(this.getCronFrameBo().getLastDayButton());
        radioFormSixthRow.add(this.getCronFrameBo().getLastDayButton());
        expressionTopForm.add(radioFormSixthRow);

        JPanel radioFormSeventhRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 5));
        buttonGroup.add(this.getCronFrameBo().getDesignButton());
        radioFormSeventhRow.add(this.getCronFrameBo().getDesignButton());
        expressionTopForm.add(radioFormSeventhRow);

        // 指定时间网格
        JPanel expressionTopFormGridTop = new JPanel();
        expressionTopFormGridTop.setLayout(GridLayoutUtils.ONE_ROWS_SIXTEEN_COLUMNS);
        for (int i = this.getExpressionBo().getCurrentUnitMinValue(); i <= this.getExpressionBo().getCurrentUnitMaxValue() / 2 + 1; i++) {
            JCheckBox jCheckBox = new JCheckBox(String.valueOf(i));
            jCheckBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            DesignCheckBoxActionListener actionListener = new DesignCheckBoxActionListener()
                    .setCronFrameBo(this.getCronFrameBo())
                    .setExpressionBo(getExpressionBo())
                    .setTextField(getExpressionBo().getDayTextField());
            jCheckBox.addActionListener(actionListener);
            expressionTopFormGridTop.add(jCheckBox);
        }
        expressionTopForm.add(expressionTopFormGridTop);

        JPanel expressionTopFormGridBottom = new JPanel();
        expressionTopFormGridBottom.setLayout(GridLayoutUtils.ONE_ROWS_SIXTEEN_COLUMNS);
        for (int i = this.getExpressionBo().getCurrentUnitMaxValue() / 2 + 2; i <= this.getExpressionBo().getCurrentUnitMaxValue(); i++) {
            JCheckBox jCheckBox = new JCheckBox(String.valueOf(i));
            jCheckBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            DesignCheckBoxActionListener actionListener = new DesignCheckBoxActionListener()
                    .setCronFrameBo(this.getCronFrameBo())
                    .setExpressionBo(getExpressionBo())
                    .setTextField(getExpressionBo().getDayTextField());
            jCheckBox.addActionListener(actionListener);
            expressionTopFormGridBottom.add(jCheckBox);
        }
        expressionTopForm.add(expressionTopFormGridBottom);

        return expressionTopForm;
    }

    /**
     * cron初始化，未添加监听事件
     *
     * @return
     */
    @Override
    public CronFrameBo getCronFrame() {
        // 初始化 日上部分页面
        CronFrameBo cronFrameBo = new CronFrameBo();
        cronFrameBo.setCycleButton(new JRadioButton("周期从"))
                .setCycleMinValueText(new MyTextField("1", ComponentIdUtils.CYCLE_MIN_VALUE_TEXT_ID))
                .setCycleMaxValueText(new MyTextField("2", ComponentIdUtils.CYCLE_MAX_VALUE_TEXT_ID))
                .setDesignButton(new JRadioButton("指定"))
                .setTimingPeriodButton(new JRadioButton("从"))
                .setTimingPeriodMinValueText(new MyTextField("1", ComponentIdUtils.TIMING_PERIOD_MIN_VALUE_TEXT_ID))
                .setTimingPeriodMaxValueText(new MyTextField("1", ComponentIdUtils.TIMING_PERIOD_MAX_VALUE_TEXT_ID))
                .setMonthLastWeekDayValue(new MyTextField("1", ComponentIdUtils.MONTH_LAST_WEEK_DAY_VALUE_ID))
                .setDesignTimeList(new ArrayList<>());
        cronFrameBo.getCycleMinValueText().addKeyListener(new ControlTheInputUtils());
        cronFrameBo.getCycleMaxValueText().addKeyListener(new ControlTheInputUtils());
        cronFrameBo.getTimingPeriodMinValueText().addKeyListener(new ControlTheInputUtils());
        cronFrameBo.getTimingPeriodMaxValueText().addKeyListener(new ControlTheInputUtils());
        cronFrameBo.setMonthLastWeekDayButton(new JRadioButton("每月"));
        cronFrameBo.getMonthLastWeekDayValue().addKeyListener(new ControlTheInputUtils());

        return cronFrameBo;
    }
}
