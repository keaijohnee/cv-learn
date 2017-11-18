package com.huawei.l00379880.basic;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/***********************************************************
 * @Description : 图像裁剪
 * @author      : 梁山广
 * @date        : 2017/11/18 20:32
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
public class Lesson11Clip extends JComponent implements ActionListener {
    private static final String ROOT_PATH = "D:\\l00379880\\GithubProjects\\images\\";
    private JButton processBtn;
    private JButton processBtn2;
    private BufferedImage image;

    public Lesson11Clip(BufferedImage image) {
        this.image = image;
    }

    public JButton getButton() {
        processBtn = new JButton("Process");
        processBtn.addActionListener(this);
        return processBtn;
    }

    public JButton getButton2() {
        processBtn2 = new JButton("Process2");
        processBtn2.addActionListener(this);
        return processBtn2;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (image != null) {
            g2d.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == processBtn) {
            this.process();
        }
        if (e.getSource() == processBtn2) {
            this.process2();
        }
        this.repaint();
    }

    public void process() {
        Graphics2D g2d = image.createGraphics();
        Font font = new Font("微软雅黑", Font.BOLD + Font.ITALIC, 34);
        // 设置透明度,第一个参数有16种模式可选,SRC_OVER最常用,此外SRC_ATOP、CLEAR(把交集部分从第一张中抠除)、
        // SRC_IN(如果两张图片相交把交集给第一张图片)、SRC_OUT(将交集扣除)、
        // 可以利用透明度实现淡入淡出动画
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
        g2d.setComposite(ac);
        g2d.setColor(Color.RED);
        g2d.setFont(font);
        g2d.drawString("欧冠决赛", 100, 100);
        BasicStroke bs = new BasicStroke(5);
        g2d.setStroke(bs);
        g2d.setColor(Color.GREEN);
        g2d.drawRoundRect(80, 62, 180, 50, 20, 20);
        System.out.println("操作1完成");
    }

    public void process2() {
        Graphics2D g2d = image.createGraphics();
        // 设置透明度,第一个参数有16种模式可选
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
        g2d.setComposite(ac);
        Font font = new Font("微软雅黑", Font.BOLD + Font.ITALIC, 44);
        g2d.setFont(font);
        g2d.drawString("哈特防守伊瓜因", 100, 200);
        try {
            saveImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("操作2完成!");
    }

    public void saveImage() throws IOException {
        File outFile = new File("D:\\dilireba.png");
        ImageIO.write(image, "png", outFile);
    }

    public static void main(String[] args) throws IOException {
        File f = new File(ROOT_PATH + "target.png");
        BufferedImage image = ImageIO.read(f);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Lesson11Clip imp = new Lesson11Clip(image);
        frame.getContentPane().add(imp, BorderLayout.CENTER);
        JPanel flowPanel = new JPanel();
        flowPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        flowPanel.add(imp.getButton());
        flowPanel.add(imp.getButton2());
        frame.getContentPane().add(flowPanel, BorderLayout.SOUTH);
        frame.setSize(image.getWidth() + 16, image.getHeight() + 50);
        frame.setTitle("演示");
        frame.setVisible(true);
    }
}