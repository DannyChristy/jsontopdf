package com.ey;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GuiWindow {
    public JFrame jFrame;

    public GuiWindow(){
        jFrame=null;
    }

    public JFrame createGUI() {
        jFrame = new JFrame("JSON to PDF convertor");
        jFrame.setSize(500, 500);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.getContentPane().setLayout(new FlowLayout());
        return this.jFrame;
    }
}
