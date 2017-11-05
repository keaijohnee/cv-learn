package com.huawei.l00379880;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

/**
 * 图像直方图的绘制
 */
public class Lesson15Histogram {
    public static void main(String[] args) {
        String rootPath = "D:\\l00379880\\GithubProjects\\images\\";
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat src = Imgcodecs.imread(rootPath + "lena.png");
        ImageUI ui = new ImageUI();
        ui.imshow("原始图片", src);


        // 转换为灰度图到gray中
        Mat gray = new Mat();
        Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);

        //图片集合
        List<Mat> matList = new ArrayList<>();
        matList.add(gray);
        Mat mask = Mat.ones(src.size(), CvType.CV_8UC1);
        Mat hist = new Mat();
        Imgproc.calcHist(matList, new MatOfInt(0), mask, hist, new MatOfInt(256), new MatOfFloat(0, 255));


    }
}
