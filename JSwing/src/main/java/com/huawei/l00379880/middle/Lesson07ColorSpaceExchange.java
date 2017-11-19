package com.huawei.l00379880.middle;

import java.awt.image.BufferedImage;

/***********************************************************
 * @Description : 色彩空间转换
 * @author      : 梁山广
 * @date        : 2017/11/19 21:11
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
public class Lesson07ColorSpaceExchange {
    public static String imgPath = CommonPanel.ROOT_PATH + "example.jpg";

    public static void process(BufferedImage image) {

        // 图像美白的参数,越接近1美白效果越好但是为1的话就会一片黑了
        double beta = 1.02;

        // 图像的的实际操作
        int width = image.getWidth();
        int height = image.getHeight();
        int[] pixels = new int[width * height];
        int index = 0;
        // 获取全部的像素
        CommonMethods.getRGB(image, 0, 0, width, height, pixels);
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {

                // 1、读像素
                // 得到当前点的下标.32位二进制数,每8bit依次代表A、R、G、B四个通道
                index = row * width + col;
                // RGB三通道混合在一起的
                int pixel = pixels[index];
                // 右移24位,相当于把32位中最高8位的值(Alpha通道)移到最低8bit,
                // &0xff是为了取出最低8bit,把结果转换为0~255之间的值,
                int channelA = (pixel >> 24) & 0xff;
                // R、G、B依次类推,分别为17~24,9~16,1~8位
                int channelR = (pixel >> 16) & 0xff;
                int channelG = (pixel >> 8) & 0xff;
                int channelB = pixel & 0xff;


                pixels[index] = (channelA << 24) | (channelR << 16) | (channelG << 8) | (channelB);
            }
        }
        CommonMethods.setRGB(image, 0, 0, width, height, pixels);
    }

    /**
     * RGB 到YCbCr空间的转换
     *
     * @param channelR R通道像素
     * @param channelG G通道像素
     * @param channelB B通道像素
     * @return 处理完的RGB通道像素值, 返回数组内的每个元素的意义也不再是RGB而是YCbCr了
     */
    public int[] rgb2CbCr(int channelR, int channelG, int channelB) {
        return null;
    }
}
