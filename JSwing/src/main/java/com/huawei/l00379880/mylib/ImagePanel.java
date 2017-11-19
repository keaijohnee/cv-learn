package com.huawei.l00379880.mylib;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JComponent implements ActionListener {
    // 处理按钮
    private JButton processBtn;
    private BufferedImage image;
    private BufferedImage resultImage;
    //直方图的数据
    private int[] histdata;

    public ImagePanel(BufferedImage image) {
        this.image = image;
    }

    public JButton getButton() {
        processBtn = new JButton("Process");
        processBtn.addActionListener(this);
        return processBtn;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (image != null) {
            g2d.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
        }
        if (resultImage != null) {
            g2d.drawImage(resultImage, image.getWidth() + 10, 0, resultImage.getWidth(), resultImage.getHeight(), null);
        }
        renderHistogram(g2d);
    }

    private void renderHistogram(Graphics2D g2d) {
        if (histdata != null) {
            int xstart = image.getWidth() + 50;
            int ystart = image.getHeight();

            g2d.setPaint(Color.BLACK);
            g2d.drawLine(xstart, 0, xstart, ystart);// Y Axis
            g2d.drawLine(xstart, ystart, xstart + 256, ystart); // X Axis

            // find max value
            int max = -1;
            int min = 0;
            for (int i = 0; i < histdata.length; i++) {
                max = Math.max(max, histdata[i]);
            }

            float delta = max - min;
            g2d.setPaint(Color.GREEN);
            for (int i = 0; i < histdata.length; i++) {
                float v1 = histdata[i] - min;
                int value = (int) ((v1 / delta) * 256);
                g2d.drawLine(xstart + i + 1, ystart, xstart + i + 1, ystart - value); // X
                // Axis
            }

        }
    }

    public void process() {
        SinCityFilter filter = new SinCityFilter();
        resultImage = filter.process(this.image);

        // histdata = filter.getHistData();
        /*for (int i = 0; i < histdata.length; i++) {
            System.out.println("gray value " + i + " :" + histdata[i]);
		}*/
    }

    public BufferedImage createCompatibleDestImage(BufferedImage src, ColorModel dstCM) {
        if (dstCM == null) {
            dstCM = src.getColorModel();
        }
        return new BufferedImage(dstCM, dstCM.createCompatibleWritableRaster(src.getWidth(), src.getHeight()),
                dstCM.isAlphaPremultiplied(), null);
    }

    /**
     * A convenience method for getting ARGB pixels from an image. This tries to
     * avoid the performance penalty of BufferedImage.getRGB unmanaging the
     * image.
     */
    public int[] getRGB(BufferedImage image, int x, int y, int width, int height, int[] pixels) {
        int type = image.getType();
        // 如果图片是int类型的话,必须通过getDataElements才能返回整型数组.
        // 如果不是int类型知己用自带的getRGB函数就能返回正确的像素数组了
        if (type == BufferedImage.TYPE_INT_ARGB || type == BufferedImage.TYPE_INT_RGB) {
            return (int[]) image.getRaster().getDataElements(x, y, width, height, pixels);
        }
        return image.getRGB(x, y, width, height, pixels, 0, width);
    }

    /**
     * A convenience method for setting ARGB pixels in an image. This tries to
     * avoid the performance penalty of BufferedImage.setRGB unmanaging the
     * image.
     */
    public void setRGB(BufferedImage image, int x, int y, int width, int height, int[] pixels) {
        // 如果图片是int类型的话,必须通过getDataElements才能返回整型数组.
        // 如果不是int类型知己用自带的getRGB函数就能返回正确的像素数组了
        int type = image.getType();
        if (type == BufferedImage.TYPE_INT_ARGB || type == BufferedImage.TYPE_INT_RGB) {
            image.getRaster().setDataElements(x, y, width, height, pixels);
        } else {
            image.setRGB(x, y, width, height, pixels, 0, width);
        }
    }

    public static void main(String[] args) throws IOException {
        String rootPath = "D:\\l00379880\\GithubProjects\\images\\";
        File f = new File(rootPath + "dilireba.png");
        BufferedImage image = ImageIO.read(f);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ImagePanel imp = new ImagePanel(image);
        frame.getContentPane().add(imp, BorderLayout.CENTER);
        JPanel flowPanel = new JPanel();
        flowPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        flowPanel.add(imp.getButton());
        frame.getContentPane().add(flowPanel, BorderLayout.SOUTH);
        frame.setSize(image.getWidth() * 2 + 50, image.getHeight() + 50);
        frame.setTitle("演示");
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == processBtn) {
            this.process();
            this.repaint();
        }
    }

}
