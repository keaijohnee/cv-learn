package com.huawei.l00379880;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

/***********************************************************
 * @Description : Canny边缘检测和提取算法,五个步骤如下
 *                1.高斯模糊---GaussianBlur
 *                2.灰度转换---cvtColor
 *                3.计算梯度---Sobel/Scharr
 *                4.非最大信号抑制
 *                5.高低阈值输出二值图像
 * @author      : 梁山广
 * @date        : 2017/11/8 21:16
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
public class Lesson28CannyEdgeDetect {
    public static void main(String[] args) {
        String rootPath = "D:\\l00379880\\GithubProjects\\images\\";
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        // 读入含有噪声的lena图像 lesson12noiselena
        Mat src = Imgcodecs.imread(rootPath + "dilireba.png");
        ImageUI ui = new ImageUI();
        ui.imshow("原始图像", src);
    }
}
