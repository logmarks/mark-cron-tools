package com.logmark.crontools.cron;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;


/**
 * @FileName: CronActionService
 * @Desctiption: cron表达式动作
 * @Author: hlb
 * @Date: Created by 2021/2/7 14:26
 * @Modified Update By:
 */
public class CronActionService extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        // insert action logic here
        CronDialog cronDialog = new CronDialog();
        cronDialog.show();
    }
}
