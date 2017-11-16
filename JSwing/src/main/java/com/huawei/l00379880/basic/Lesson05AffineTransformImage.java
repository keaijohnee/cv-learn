package com.huawei.l00379880.basic;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/***********************************************************
 * @Description : 仿射变换(平移-translation、旋转-rotation、
 *                放缩--differential scale、倾斜--skew)
 * @author      : 梁山广
 * @date        : 2017/11/15 20:57
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
public class Lesson05AffineTransformImage extends JComponent {
    private BufferedImage image;

    @Override
    protected void paintComponent(Graphics g) {
        // 向下转换
        Graphics2D image2d = (Graphics2D) g;
        // 反锯齿特效,Graphics2D有,但是Graphics没有.有了反锯齿效果即使图片被无线放大也不会失真.但是Graphics的会
        image2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        URL url = this.getClass().getResource("cat.jpg");
        try {
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 如果图片为空就不绘图了
        if (image == null) {
            return;
        }
        // 获取当前的transform对象
        AffineTransform saveXForm = image2d.getTransform();

        AffineTransform atf = new AffineTransform();
        atf.setToTranslation(this.getWidth() / 2, this.getHeight() / 2);
        // 旋转45度,去掉下面这一行就不旋转了.单独使用用setToRotate,会清除之前的效果
        atf.rotate(Math.PI / 4);
        // 缩小到原来的一半.注意不要用setToScale,setToXXX函数会清除前面的所有特效.类似的setToRotate也是同样的效果
        atf.scale(0.5, 0.5);
        image2d.drawImage(image, 100, 100, image.getWidth(), image.getHeight(), null);
        // 恢复当前的transform对象
        image2d.setTransform(saveXForm);
    }

    void paint() {

    }

    public static void main(String[] args) {
        // 新建组件的载体
        JFrame frame = new JFrame();
        frame.setTitle("图形变换");

        Lesson05AffineTransformImage mycanvas = new Lesson05AffineTransformImage();
        JButton okBtn = new JButton("绘图");
        okBtn.addActionListener(e -> {
            mycanvas.paint();
            // 记得要重新绘图
            mycanvas.repaint();
        });
        // 设置面板布局
        frame.getContentPane().setLayout(new BorderLayout());
        // 控件处于中间的位置
        frame.getContentPane().add(mycanvas, BorderLayout.CENTER);
        frame.add(okBtn, BorderLayout.SOUTH);
        // 设置退出动作
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 设置面板的大小
        frame.setSize(400, 400);
        // 设置可见性
        frame.setVisible(true);
    }
}
