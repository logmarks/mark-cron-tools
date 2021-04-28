package com.logmark.crontools.cron;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.components.JBScrollPane;
import com.logmark.crontools.comm.utils.GridLayoutUtils;
import com.logmark.crontools.cron.bo.ExpressionBo;
import com.logmark.crontools.cron.enums.DateUnitEnum;
import com.logmark.crontools.cron.structure.*;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @FileName: CronDialog
 * @Desctiption: cron表达式对话框
 * @Author: hlb
 * @Date: Created by 2021/2/22 14:06
 * @Modified Update By:
 */
public class CronDialog extends DialogWrapper {
    private final Map<DateUnitEnum, CronPageStructure> pageStructureMap = new HashMap<>();
    public static Map<DateUnitEnum, CronPageStructure> pageMap = new HashMap<>();

    /**
     * 卡片布局：实现页面局部切换
     */
    private CardLayout cardLayout;
    /**
     * 页面局部切换部分
     */
    private JPanel cardsTopForm;
    /**
     * 页面局部切换部分
     */
    private JPanel expressionBottomForm;
    /**
     * 表达式对象
     */
    private ExpressionBo expressionBo;

    protected CronDialog() {
        super(true);
        this.cardLayout = new CardLayout();
        this.cardsTopForm = new JPanel(this.cardLayout);
        this.expressionBo = new ExpressionBo();
        this.expressionBottomForm = getBottomForm();
        this.setMap();
        init();
        setTitle("Cron表达式生成器");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel contextPanel = new JPanel();
        contextPanel.setLayout(GridLayoutUtils.TWO_ROWS_ONE_COLUMNS);
        for (Map.Entry<DateUnitEnum, CronPageStructure> pageStructureEntry : this.pageStructureMap.entrySet()) {
            CronPageStructure pageStructure = pageStructureEntry.getValue();
            this.cardsTopForm.add(pageStructure.getExpressionTopForm(), pageStructureEntry.getKey().getValue());
        }
        this.cardLayout.show(this.cardsTopForm, DateUnitEnum.SECOND.getValue());
        contextPanel.add(this.cardsTopForm);
        contextPanel.add(this.expressionBottomForm);
        return contextPanel;
    }

    private CronDialog setMap() {
        CronPageStructure secondPage = new CronSecondPageStructure(DateUnitEnum.SECOND
                , this.expressionBo, this.cardLayout, this.cardsTopForm);
        this.pageStructureMap.put(DateUnitEnum.SECOND, secondPage);

        CronPageStructure minutePage = new CronMinutePageStructure(DateUnitEnum.MINUTE
                , this.expressionBo, this.cardLayout, this.cardsTopForm);
        this.pageStructureMap.put(DateUnitEnum.MINUTE, minutePage);

        CronPageStructure hourPage = new CronHourPageStructure(DateUnitEnum.HOUR
                , this.expressionBo, this.cardLayout, this.cardsTopForm);
        this.pageStructureMap.put(DateUnitEnum.HOUR, hourPage);

        CronPageStructure dayPage = new CronDayPageStructure(DateUnitEnum.DAY
                , this.expressionBo, this.cardLayout, this.cardsTopForm);
        this.pageStructureMap.put(DateUnitEnum.DAY, dayPage);

        CronPageStructure monthPage = new CronMonthPageStructure(DateUnitEnum.MONTH
                , this.expressionBo, this.cardLayout, this.cardsTopForm);
        this.pageStructureMap.put(DateUnitEnum.MONTH, monthPage);

        CronPageStructure weekPage = new CronWeekPageStructure(DateUnitEnum.WEEK
                , this.expressionBo, this.cardLayout, this.cardsTopForm);
        this.pageStructureMap.put(DateUnitEnum.WEEK, weekPage);

        CronPageStructure yearPage = new CronYearPageStructure(DateUnitEnum.YEAR
                , this.expressionBo, this.cardLayout, this.cardsTopForm);
        this.pageStructureMap.put(DateUnitEnum.YEAR, yearPage);

        CronDialog.pageMap = this.pageStructureMap;
        return this;
    }

    public JPanel getBottomForm() {

        // 表达式之下 下部分
        JPanel expressionBottomForm = new JPanel();
        expressionBottomForm.setLayout(GridLayoutUtils.TWO_ROWS_ONE_COLUMNS);

        // 最新10次运行时间之上 上部分
        JPanel expressionStrPartyForm = new JPanel();
        expressionStrPartyForm.setLayout(GridLayoutUtils.FOUR_ROWS_ONE_COLUMNS);

        JPanel strPartyFirstPanel = new JPanel();
        strPartyFirstPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel expressionLeftHrLabel = new JLabel("--------------------------------------------------------------------");
        strPartyFirstPanel.add(expressionLeftHrLabel);
        JLabel expressionHrLabel = new JLabel("表达式");
        strPartyFirstPanel.add(expressionHrLabel);
        JLabel expressionRightHrLabel = new JLabel("--------------------------------------------------------------------");
        strPartyFirstPanel.add(expressionRightHrLabel);
        expressionStrPartyForm.add(strPartyFirstPanel);

        JPanel strPartySecondPanel = new JPanel();
        strPartySecondPanel.setLayout(GridLayoutUtils.ONE_ROWS_EIGHT_COLUMNS);
        JLabel nullLabel = new JLabel("");
        strPartySecondPanel.add(nullLabel, BorderLayout.CENTER);
        JLabel secondLabel = new JLabel("秒");
        strPartySecondPanel.add(secondLabel, BorderLayout.CENTER);
        JLabel minuteLabel = new JLabel("分钟");
        strPartySecondPanel.add(minuteLabel, BorderLayout.CENTER);
        JLabel hourLabel = new JLabel("小时");
        strPartySecondPanel.add(hourLabel, BorderLayout.CENTER);
        JLabel dayLabel = new JLabel("日");
        strPartySecondPanel.add(dayLabel, BorderLayout.CENTER);
        JLabel monthLabel = new JLabel("月");
        strPartySecondPanel.add(monthLabel, BorderLayout.CENTER);
        JLabel weekLabel = new JLabel("星期");
        strPartySecondPanel.add(weekLabel, BorderLayout.CENTER);
        JLabel yearLabel = new JLabel("年");
        strPartySecondPanel.add(yearLabel, BorderLayout.CENTER);
        expressionStrPartyForm.add(strPartySecondPanel);

        JPanel strPartyThirdPanel = new JPanel();
        strPartyThirdPanel.setLayout(GridLayoutUtils.ONE_ROWS_EIGHT_COLUMNS);
        JLabel expressionLabel = new JLabel("表达式字段:");
        strPartyThirdPanel.add(expressionLabel, BorderLayout.CENTER);
        strPartyThirdPanel.add(expressionBo.getSecondTextField(), BorderLayout.CENTER);
        strPartyThirdPanel.add(expressionBo.getMinuteTextField(), BorderLayout.CENTER);
        strPartyThirdPanel.add(expressionBo.getHourTextField(), BorderLayout.CENTER);
        strPartyThirdPanel.add(expressionBo.getDayTextField(), BorderLayout.CENTER);
        strPartyThirdPanel.add(expressionBo.getMonthTextField(), BorderLayout.CENTER);
        strPartyThirdPanel.add(expressionBo.getWeekTextField(), BorderLayout.CENTER);
        strPartyThirdPanel.add(expressionBo.getYearTextField(), BorderLayout.CENTER);
        expressionStrPartyForm.add(strPartyThirdPanel);

        JPanel strPartyFourthPanel = new JPanel();
        BorderLayout cronBorderLayout = new BorderLayout();

        strPartyFourthPanel.setLayout(cronBorderLayout);

        JLabel cronExpressionLabel = new JLabel("Cron 表达式:");
        strPartyFourthPanel.add(cronExpressionLabel, BorderLayout.WEST);
        strPartyFourthPanel.add(expressionBo.getCronExpressionInput(), BorderLayout.CENTER);
        JButton paringButton = new JButton("反解析到UI");
        paringButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        paringButton.addActionListener(e -> {
            try {
                expressionBo.antiAnalysisGenerationCron(paringButton);
                expressionBo.generateTenRunTime();
            } catch (Exception exception) {
                System.out.println("反解析失败！");
                exception.printStackTrace();
            }
        });
        strPartyFourthPanel.add(paringButton, BorderLayout.EAST);

        expressionStrPartyForm.add(strPartyFourthPanel);
        expressionBottomForm.add(expressionStrPartyForm);

        // 最新10次运行时间之下 下部分
        JPanel runPartyForm = new JPanel();
        GridBagLayout bagLayout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        runPartyForm.setLayout(bagLayout);
        // 组件填充显示区域
        constraints.fill = GridBagConstraints.BOTH;
        // 恢复默认值
        constraints.weightx = 0.0;
        // 结束行
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        JLabel runningDateLabel = new JLabel("最近10次运行时间:");
        bagLayout.setConstraints(runningDateLabel, constraints);
        runPartyForm.add(runningDateLabel);
        // 指定组件的分配区域
        constraints.weightx = 0.5;
        constraints.weighty = 0.2;
        constraints.gridwidth = 1;
        JBScrollPane jsp = new JBScrollPane(expressionBo.getNinthRowTextArea());
        bagLayout.setConstraints(jsp, constraints);
        runPartyForm.add(jsp);
        expressionBottomForm.add(runPartyForm);
        return expressionBottomForm;
    }
}
