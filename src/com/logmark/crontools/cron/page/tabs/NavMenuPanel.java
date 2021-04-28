package com.logmark.crontools.cron.page.tabs;

import com.intellij.ui.JBColor;
import com.logmark.crontools.cron.enums.DateUnitEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @FileName: NavMenuPanel
 * @Desctiption: 导航栏
 * @Author: hlb
 * @Date: Created by 2021/4/27 10:54
 * @Modified Update By:
 */
public class NavMenuPanel extends JPanel {
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
    FlowLayout optionsMenuLayout = new FlowLayout(FlowLayout.LEFT, 1, 5);
    /**
     * 切换部分-秒
     */
    JButton secondButton;
    /**
     * 切换部分-分
     */
    JButton minuteButton;
    /**
     * 切换部分-时
     */
    JButton hourButton;
    /**
     * 切换部分-日
     */
    JButton dayButton;
    /**
     * 切换部分-月
     */
    JButton monthButton;
    /**
     * 切换部分-周
     */
    JButton weekButton;
    /**
     * 切换部分-年
     */
    JButton yearButton;

    public NavMenuPanel(CardLayout cardLayout, JPanel cardsTopForm, DateUnitEnum unitEnum) {
        this.cardLayout = cardLayout;
        this.cardsTopForm = cardsTopForm;
        secondButton = this.makeButton(DateUnitEnum.SECOND.getContent(), unitEnum);
        this.add(secondButton, optionsMenuLayout, 0);
        minuteButton = this.makeButton(DateUnitEnum.MINUTE.getContent(), unitEnum);
        this.add(minuteButton, optionsMenuLayout, 1);
        hourButton = this.makeButton(DateUnitEnum.HOUR.getContent(), unitEnum);
        this.add(hourButton, optionsMenuLayout, 2);
        dayButton = this.makeButton(DateUnitEnum.DAY.getContent(), unitEnum);
        this.add(dayButton, optionsMenuLayout, 3);
        monthButton = this.makeButton(DateUnitEnum.MONTH.getContent(), unitEnum);
        this.add(monthButton, optionsMenuLayout, 4);
        weekButton = this.makeButton(DateUnitEnum.WEEK.getContent(), unitEnum);
        this.add(weekButton, optionsMenuLayout, 5);
        yearButton = this.makeButton(DateUnitEnum.YEAR.getContent(), unitEnum);
        this.add(yearButton, optionsMenuLayout, 6);
    }

    private JButton makeButton(String name, DateUnitEnum unitEnum) {
        JButton jButton = new JButton(name);
        jButton.setContentAreaFilled(true);
        jButton.setBackground(unitEnum.getContent().equals(name) ? JBColor.LIGHT_GRAY : JBColor.WHITE);
        jButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // 对按钮添加鼠标活动
        final CardLayout layout = this.cardLayout;
        final JPanel topForm = this.cardsTopForm;
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
                layout.show(topForm, unitEnum.getValue());
            }
        });
        jButton.setBorderPainted(false);
        return jButton;
    }
}
