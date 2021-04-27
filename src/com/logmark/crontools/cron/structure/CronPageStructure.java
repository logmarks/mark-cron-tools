package com.logmark.crontools.cron.structure;


import com.intellij.ui.JBColor;
import com.logmark.crontools.comm.listener.button.CycleButtonActionListener;
import com.logmark.crontools.comm.listener.button.DesignButtonActionListener;
import com.logmark.crontools.comm.listener.button.TimingPeriodButtonActionListener;
import com.logmark.crontools.comm.listener.document.TextFieldDocumentListener;
import com.logmark.crontools.comm.utils.StringUtil;
import com.logmark.crontools.cron.bo.ExpressionBo;
import com.logmark.crontools.cron.bo.page.CronFrameBo;
import com.logmark.crontools.cron.enums.DateUnitEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @FileName: CronPageStructure
 * @Desctiption: cron页面接口
 * @Author: hlb
 * @Date: Created by 2021/2/8 9:36
 * @Modified Update By:
 */
public abstract class CronPageStructure {
    private DateUnitEnum dateUnitEnum;
    /**
     * 构建选项菜单页面
     */
    private JPanel optionsMenu;

    /**
     * 构建表达式之上页面
     */
    private JPanel expressionTopForm;
    /**
     * 表达式之上 按钮/值 组件
     */
    private CronFrameBo cronFrameBo;

    /**
     * 表达式之下 值 组件
     */
    private ExpressionBo expressionBo;
    /**
     * 卡片布局：实现页面局部切换
     */
    private CardLayout cardLayout;
    /**
     * 页面局部切换部分
     */
    private JPanel cardsTopForm;

    public CronPageStructure() {
        this.cardLayout = new CardLayout();
        this.cardsTopForm = new JPanel(this.cardLayout);

    }

    public CronPageStructure(DateUnitEnum dateUnitEnum) {
        this();
        this.dateUnitEnum = dateUnitEnum;
    }

    public CronPageStructure(DateUnitEnum unit, ExpressionBo expressionBo) {
        this(unit);
        this.expressionBo = expressionBo;
    }

    public DateUnitEnum getDateUnitEnum() {
        return dateUnitEnum;
    }

    public CronPageStructure setDateUnitEnum(DateUnitEnum dateUnitEnum) {
        this.dateUnitEnum = dateUnitEnum;
        return this;
    }

    public JPanel getOptionsMenu() {
        return optionsMenu;
    }

    public CronPageStructure setOptionsMenu(JPanel optionsMenu) {
        this.optionsMenu = optionsMenu;
        return this;
    }

    public JPanel getExpressionTopForm() {
        return expressionTopForm;
    }

    public CronPageStructure setExpressionTopForm(JPanel expressionTopForm) {
        this.expressionTopForm = expressionTopForm;
        return this;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public CronPageStructure setCardLayout(CardLayout cardLayout) {
        this.cardLayout = cardLayout;
        return this;
    }

    public JPanel getCardsTopForm() {
        return cardsTopForm;
    }

    public CronPageStructure setCardsTopForm(JPanel cardsTopForm) {
        this.cardsTopForm = cardsTopForm;
        return this;
    }

    public ExpressionBo getExpressionBo() {
        return expressionBo;
    }

    public CronPageStructure setExpressionBo(ExpressionBo expressionBo) {
        this.expressionBo = expressionBo;
        return this;
    }

    public CronFrameBo getCronFrameBo() {
        return cronFrameBo;
    }

    public CronPageStructure setCronFrameBo() {
        this.setCronFrameBo(getCronFrame());
        return this;
    }

    protected abstract CronFrameBo getCronFrame();


    public CronPageStructure setCronFrameBo(CronFrameBo cronFrameBo) {
        this.cronFrameBo = cronFrameBo;
        return this;
    }

    // ------------------------------------------- 构造函数 get/set结束线--------------------------------

    public JPanel getOptions(DateUnitEnum unitEnum) {
        // 窗体选项栏
        JPanel optionsMenu = new JPanel();
        FlowLayout optionsMenuLayout = new FlowLayout(FlowLayout.LEFT, 1, 5);
        JButton secondButton = this.makeButton(DateUnitEnum.SECOND.getContent(), unitEnum);
        optionsMenu.add(secondButton, optionsMenuLayout, 0);
        JButton minuteButton = this.makeButton(DateUnitEnum.MINUTE.getContent(), unitEnum);
        optionsMenu.add(minuteButton, optionsMenuLayout, 1);
        JButton hourButton = this.makeButton(DateUnitEnum.HOUR.getContent(), unitEnum);
        optionsMenu.add(hourButton, optionsMenuLayout, 2);
        JButton dayButton = this.makeButton(DateUnitEnum.DAY.getContent(), unitEnum);
        optionsMenu.add(dayButton, optionsMenuLayout, 3);
        JButton monthButton = this.makeButton(DateUnitEnum.MONTH.getContent(), unitEnum);
        optionsMenu.add(monthButton, optionsMenuLayout, 4);
        JButton weekButton = this.makeButton(DateUnitEnum.WEEK.getContent(), unitEnum);
        optionsMenu.add(weekButton, optionsMenuLayout, 5);
        JButton yearButton = this.makeButton(DateUnitEnum.YEAR.getContent(), unitEnum);
        optionsMenu.add(yearButton, optionsMenuLayout, 6);
        return optionsMenu;
    }

    private JButton makeButton(String name, DateUnitEnum unitEnum) {
        JButton jButton = new JButton(name);
        jButton.setContentAreaFilled(true);
        jButton.setBackground(unitEnum.getContent().equals(name) ? JBColor.LIGHT_GRAY : JBColor.WHITE);
        jButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // 对按钮添加鼠标活动
        jButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jButton.setBackground(JBColor.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jButton.setBackground(JBColor.WHITE);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                jButton.setBackground(JBColor.GRAY);
                DateUnitEnum unitEnum = DateUnitEnum.getByContent(jButton.getText());
                cardLayout.show(cardsTopForm, unitEnum.getValue());
            }
        });
        jButton.setBorderPainted(false);
        return jButton;
    }

    /**
     * 获取表达式之上的页面
     *
     * @return
     */
    abstract JPanel getTopForm();

    protected CronPageStructure setTopForm() {
        JPanel topForm = this.getTopForm();
        this.setExpressionTopForm(topForm);
        return this;
    }

    /**
     * 获取当前页面实例
     *
     * @return
     */
    public static CronPageStructure getInstance(DateUnitEnum page, ExpressionBo expressionBo, CardLayout cardLayout, JPanel cardsTopForm) {
        CronPageStructure pageStructure = null;
        switch (page) {
            case MINUTE:
                pageStructure = new CronMinutePageStructure();
                pageStructure.setExpressionBo(expressionBo).setCronFrameBo()
                        .setDateUnitEnum(page).setCardLayout(cardLayout).setCardsTopForm(cardsTopForm)
                        .setTopForm().setButtonAndTextFieldListener(expressionBo.getMinuteTextField());
                break;
            case HOUR:
                pageStructure = new CronHourPageStructure();
                expressionBo.setCurrentUnitMinValue(0).setCurrentUnitMaxValue(23);
                pageStructure.setExpressionBo(expressionBo).setCronFrameBo()
                        .setDateUnitEnum(page).setCardLayout(cardLayout).setCardsTopForm(cardsTopForm)
                        .setTopForm().setButtonAndTextFieldListener(expressionBo.getHourTextField());
                break;
            case DAY:
                pageStructure = new CronDayPageStructure();
                expressionBo.setCurrentUnitMinValue(1).setCurrentUnitMaxValue(31);
                pageStructure.setExpressionBo(expressionBo).setCronFrameBo()
                        .setDateUnitEnum(page).setCardLayout(cardLayout).setCardsTopForm(cardsTopForm)
                        .setTopForm().setButtonAndTextFieldListener(expressionBo.getDayTextField());
                break;
            case MONTH:
                pageStructure = new CronMonthPageStructure();
                expressionBo.setCurrentUnitMinValue(1).setCurrentUnitMaxValue(12);
                pageStructure.setExpressionBo(expressionBo).setCronFrameBo()
                        .setDateUnitEnum(page).setCardLayout(cardLayout).setCardsTopForm(cardsTopForm)
                        .setTopForm().setButtonAndTextFieldListener(expressionBo.getMonthTextField());
                break;
            case WEEK:
                pageStructure = new CronWeekPageStructure();
                expressionBo.setCurrentUnitMinValue(1).setCurrentUnitMaxValue(7);
                pageStructure.setExpressionBo(expressionBo).setCronFrameBo()
                        .setDateUnitEnum(page).setCardLayout(cardLayout).setCardsTopForm(cardsTopForm)
                        .setTopForm().setButtonAndTextFieldListener(expressionBo.getWeekTextField());
                break;
            case YEAR:
                pageStructure = new CronYearPageStructure();
                expressionBo.setCurrentUnitMinValue(2020).setCurrentUnitMaxValue(3000);
                pageStructure.setExpressionBo(expressionBo).setCronFrameBo()
                        .setDateUnitEnum(page).setCardLayout(cardLayout).setCardsTopForm(cardsTopForm)
                        .setTopForm().setButtonAndTextFieldListener(expressionBo.getYearTextField());
                break;
            default:
                pageStructure = new CronSecondPageStructure();
                pageStructure.setExpressionBo(expressionBo).setCronFrameBo()
                        .setDateUnitEnum(page).setCardLayout(cardLayout).setCardsTopForm(cardsTopForm)
                        .setTopForm().setButtonAndTextFieldListener(expressionBo.getSecondTextField());
                break;
        }
        return pageStructure;
    }

    /**
     * 监听事件设置方法
     */
    protected void setButtonAndTextFieldListener(JTextField textField) {
        this.getCronFrameBo().getCycleButton()
                .addActionListener(
                        new CycleButtonActionListener(this.getExpressionBo(), this.getCronFrameBo(), textField));

        this.getCronFrameBo().getCycleMinValueText().getDocument()
                .addDocumentListener(new TextFieldDocumentListener(this.getExpressionBo(), this.getCronFrameBo().getCycleButton()
                        , ExpressionBo.HORIZONTAL_LINE, textField
                        , this.getCronFrameBo().getCycleMinValueText(), this.getCronFrameBo().getCycleMaxValueText()));

        this.getCronFrameBo().getCycleMaxValueText().getDocument()
                .addDocumentListener(new TextFieldDocumentListener(this.getExpressionBo(), this.getCronFrameBo().getCycleButton()
                        , ExpressionBo.HORIZONTAL_LINE, textField
                        , this.getCronFrameBo().getCycleMaxValueText(), this.getCronFrameBo().getCycleMinValueText()));

        if (this.getCronFrameBo().getDesignButton() != null) {
            this.getCronFrameBo().getDesignButton()
                    .addActionListener(new DesignButtonActionListener(this.getExpressionBo(), this.getCronFrameBo(), textField));
        }

        this.getCronFrameBo().getTimingPeriodButton()
                .addActionListener(new TimingPeriodButtonActionListener(this.getExpressionBo(), this.getCronFrameBo(), textField));

        this.getCronFrameBo().getTimingPeriodMinValueText().getDocument()
                .addDocumentListener(new TextFieldDocumentListener(getExpressionBo(), this.getCronFrameBo().getTimingPeriodButton()
                        , ExpressionBo.BACKSLASH, getExpressionBo().getSecondTextField()
                        , this.getCronFrameBo().getTimingPeriodMinValueText(), this.getCronFrameBo().getTimingPeriodMaxValueText()));
        this.getCronFrameBo().getTimingPeriodMaxValueText().getDocument()
                .addDocumentListener(new TextFieldDocumentListener(getExpressionBo(), this.getCronFrameBo().getTimingPeriodButton()
                        , ExpressionBo.BACKSLASH, getExpressionBo().getSecondTextField()
                        , this.getCronFrameBo().getTimingPeriodMaxValueText(), this.getCronFrameBo().getTimingPeriodMinValueText()));

        // day页面
        if (this.getCronFrameBo().getMonthLastWeekDayButton() != null) {
            this.getCronFrameBo().getMonthLastWeekDayButton().addActionListener(e -> {
                if (!this.getCronFrameBo().getMonthLastWeekDayButton().isSelected()) {
                    // 未选中指定按钮
                    return;
                }
                if (StringUtil.isBlank(this.getCronFrameBo().getMonthLastWeekDayValue().getText())) {
                    getExpressionBo().setNinthRowTextArea("cron表达式格式非法--在'日'的格式错误！");
                    return;
                }
                getExpressionBo().setDayTextField(this.getCronFrameBo().getMonthLastWeekDayValue().getText() + "W");
                getExpressionBo().generateCronExpression().generateTenRunTime();
            });
        }

        if (this.getCronFrameBo().getMonthLastWeekDayValue() != null) {
            this.getCronFrameBo().getMonthLastWeekDayValue().getDocument().addDocumentListener(
                    new TextFieldDocumentListener(getExpressionBo(), this.getCronFrameBo().getMonthLastWeekDayButton()
                            , "W", getExpressionBo().getDayTextField()
                            , this.getCronFrameBo().getMonthLastWeekDayValue(), null));
        }

        // week页面
        if (this.getCronFrameBo().getMonthLastWeekButton() != null) {
            this.getCronFrameBo().getMonthLastWeekButton().addActionListener(e -> {
                if (!this.getCronFrameBo().getMonthLastWeekButton().isSelected()) {
                    // 未选中指定按钮
                    return;
                }
                if (StringUtil.isBlank(this.getCronFrameBo().getMonthLastWeekDayValue().getText())) {
                    getExpressionBo().setNinthRowTextArea("cron表达式格式非法--在'月'的格式错误！");
                    return;
                }
                getExpressionBo().setWeekTextField(this.getCronFrameBo().getMonthLastWeekDayValue().getText() + "L");
                getExpressionBo().generateCronExpression().generateTenRunTime();
            });
        }

        if (this.getCronFrameBo().getMonthLastWeekDayValue() != null) {
            this.getCronFrameBo().getMonthLastWeekDayValue().getDocument()
                    .addDocumentListener(new TextFieldDocumentListener(getExpressionBo(), this.getCronFrameBo().getMonthLastWeekButton()
                            , "L", getExpressionBo().getWeekTextField()
                            , this.getCronFrameBo().getMonthLastWeekDayValue(), null));
        }

    }

    /**
     * 规则判断
     *
     * @return
     */
    public String checkExpression(String expressionChar) {
        if (ExpressionBo.HORIZONTAL_LINE.equals(expressionChar)) {
            return checkHorizontalLineExpression();
        }
        if (ExpressionBo.BACKSLASH.equals(expressionChar)) {
            return checkBackslashExpression();
        }
        if (expressionChar.equals(ExpressionBo.W)) {
            return checkMonthLastWeekExpression();
        }
        return null;
    }

    public String checkMonthLastWeekExpression() {
        if (StringUtil.isBlank(this.getCronFrameBo().getMonthLastWeekDayValue().getText())) {
            return " 'W' 前必须跟整数！";
        }
        return null;
    }

    public String checkHorizontalLineExpression() {
        if (StringUtil.isBlank(this.getCronFrameBo().getCycleMinValueText().getText())) {
            return dateUnitEnum.getContent() + " 周期从 '' 起始值不能为空！";
        }
        if (StringUtil.isBlank(this.getCronFrameBo().getCycleMaxValueText().getText())) {
            return dateUnitEnum.getContent() + " 周期从  到 '' 终止值不能为空！";
        }
        return null;
    }

    /**
     * 从什么时候开始多久执行一次 / 规则判断
     *
     * @return
     */
    public String checkBackslashExpression() {
        if (StringUtil.isBlank(this.getCronFrameBo().getTimingPeriodMaxValueText().getText())) {
            return "'/' 后必须跟整数！";
        }
        return null;
    }

}
