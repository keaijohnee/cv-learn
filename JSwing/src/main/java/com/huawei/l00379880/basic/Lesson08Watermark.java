package com.huawei.l00379880.basic;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/***********************************************************
 * @Description : 文字与水印合成
 * @author      : 梁山广
 * @date        : 2017/11/17 20:31
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
public class Lesson08Watermark extends JComponent {
    private BufferedImage image;

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        try {
            URL url = this.getClass().getResource("bigcat.jpg");
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (image == null) {
            return;
        }
        g2d.setPaint(Color.BLUE);
        // 设置字体的大小与样式.第二个参数为字体的样式
        Font font = new Font("微软雅黑", Font.BOLD, 24);
        g2d.setFont(font);
        g2d.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
        g2d.drawString("一个可爱的橘猫", 230, 50);
    }

    public void drawFonts() {
        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        System.out.println("开始添加水印!!");
        g2d.setPaint(Color.RED);
        // 设置字体的大小与样式.第二个参数为字体的样式
        Font font = new Font("微软雅黑", Font.BOLD, 24);
        g2d.setFont(font);
        g2d.drawString("a lovely cat", 260, 680);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("显示不同的字体");
        Lesson08Watermark mycanvas = new Lesson08Watermark();
        JButton drawBtn = new JButton("绘图");
        drawBtn.addActionListener(e -> {
            mycanvas.drawFonts();
            mycanvas.repaint();
        });
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(mycanvas, BorderLayout.CENTER);
        frame.getContentPane().add(drawBtn, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600, 800);
        frame.setVisible(true);
    }
}
