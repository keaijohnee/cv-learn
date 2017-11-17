package com.huawei.l00379880.basic;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/***********************************************************
 * @Description : 我自己的鼠标绘制类
 * @author      : 梁山广
 * @date        : 2017/11/17 17:07
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
public class Lesson07MyPaint extends JComponent {
    private BufferedImage image;

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (image == null) {
            return;
        }
        g2d.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
    }

    public static void main(String[] args) {
        // 新建组件的载体
        JFrame frame = new JFrame();
        frame.setTitle("鼠标响应事件");
        Lesson07MyPaint mycanvas = new Lesson07MyPaint();
        Lesson07MouseListener controller = new Lesson07MouseListener(mycanvas);
        mycanvas.addMouseListener(controller);
        mycanvas.addMouseMotionListener(controller);
        // 设置面板布局
        frame.getContentPane().setLayout(new BorderLayout());
        // 控件处于中间的位置
        frame.getContentPane().add(mycanvas, BorderLayout.CENTER);
        // 设置退出动作
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 设置面板的大小
        frame.setSize(400, 400);
        // 设置可见性
        frame.setVisible(true);
    }
}
