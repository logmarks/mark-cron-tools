package com.logmark.crontools.comm.utils;

import javax.swing.*;
import javax.swing.text.Document;

/**
 * @FileName: JMyTextField
 * @Desctiption: 我的文本框
 * @Author: hlb
 * @Date: Created by 2021/2/24 16:04
 * @Modified Update By:
 */
public class MyTextField extends JTextField {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public MyTextField setId(Integer id) {
        this.id = id;
        return this;
    }

    public MyTextField(Integer id) {
        this.id = id;
    }

    public MyTextField(String text, Integer id) {
        super(text);
        this.id = id;
    }

    public MyTextField(int columns, Integer id) {
        super(columns);
        this.id = id;
    }

    public MyTextField(String text, int columns, Integer id) {
        super(text, columns);
        this.id = id;
    }

    public MyTextField(Document doc, String text, int columns, Integer id) {
        super(doc, text, columns);
        this.id = id;
    }
}
