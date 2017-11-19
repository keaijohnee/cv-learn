package com.huawei.l00379880.middle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/***********************************************************
 * @Description : 图像处理面板
 * @author      : 梁山广
 * @date        : 2017/11/19 14:59
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
public class ImagePanel extends JComponent implements ActionListener {
    private static final String ROOT_PATH = "D:\\l00379880\\GithubProjects\\images\\";
    private BufferedImage image;
    private JButton drawBtn;
    private JButton processBtn;
    private JButton saveBtn;

    public ImagePanel(BufferedImage image) {
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (image != null) {
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == drawBtn) {
            this.draw();
        }
        if (e.getSource() == saveBtn) {
            this.save();
        }
        if (e.getSource() == processBtn) {
            this.process();
        }
        this.repaint();
    }

    public JButton getDrawBtn() {
        drawBtn = new JButton("绘图");
        drawBtn.addActionListener(this);
        return drawBtn;
    }

    public JButton getProcessBtn() {
        processBtn = new JButton("处理");
        processBtn.addActionListener(this);
        return processBtn;
    }

    public JButton getSaveBtn() {
        saveBtn = new JButton("保存");
        saveBtn.addActionListener(this);
        return saveBtn;
    }

    /**
     * 绘制图像
     */
    public void draw() {
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(Color.GREEN);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawOval(100, 100, 200, 200);
        System.out.println("绘图成功!!");
    }

    /**
     * 像素处理
     */
    public void process() {

    }

    /**
     * 保存图片
     */
    public void save() {
        File f = new File(ROOT_PATH + "middle\\target_result.png");
        try {
            ImageIO.write(image, "png", f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("保存成功!!");
    }

    /**
     * 获取指定区域的像素数组(每个点的RGB为一个组合起来的大整数),屏蔽了整型图片和非整型图片的区别
     *
     * @param image  原始图片
     * @param x      取样处起点横坐标
     * @param y      样处起始点纵坐标
     * @param width  取样宽度
     * @param height 取样高度
     * @param pixels 取样的像素点数据存储的数组,用于返回像素
     * @return 将取好值的pixels数组返回
     */
    public int[] getRGB(BufferedImage image, int x, int y, int width, int height, int[] pixels) {
        int type = image.getType();
        if (type == BufferedImage.TYPE_INT_ARGB || type == BufferedImage.TYPE_INT_RGB) {
            return (int[]) image.getRaster().getDataElements(x, y, width, height, pixels);
        }
        return image.getRGB(x, y, width, height, pixels, 0, width);
    }

    /**
     * 设置取样区的像素
     *
     * @param image  原始图片
     * @param x      取样处起点横坐标
     * @param y      样处起始点纵坐标
     * @param width  取样宽度
     * @param height 取样高度
     * @param pixels 取样的像素点数据存储的数组,用于给该区域设置像素
     */
    public void setRGB(BufferedImage image, int x, int y, int width, int height, int[] pixels) {
        int type = image.getType();
        if (type == BufferedImage.TYPE_INT_ARGB || type == BufferedImage.TYPE_INT_RGB) {
            image.getRaster().setDataElements(x, y, width, height, pixels);
        } else {
            image.setRGB(x, y, width, height, pixels, 0, width);
        }
    }

    public static void main(String[] args) throws IOException {
        File file = new File(ROOT_PATH + "target.png");
        BufferedImage image = ImageIO.read(file);
        JFrame frame = new JFrame("图像的基本操作");
        ImagePanel panel = new ImagePanel(image);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(panel.getDrawBtn(), BorderLayout.SOUTH);
        JPanel jPanel = new JPanel();
        jPanel.add(panel.getDrawBtn());
        jPanel.add(panel.getProcessBtn());
        jPanel.add(panel.getSaveBtn());
        frame.add(jPanel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(image.getWidth() + 16, image.getHeight() + 38);
        frame.setVisible(true);
    }

}
