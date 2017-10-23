package com.huawei.l00379880;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class OpenCV02Lena {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        // 读取lena图像,读入的一次是BGR,Imgcodecs.CV_LOAD_IMAGE_COLOR表示地是以彩色度图像加载进来的
        // 以灰度图的形式加载, Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
        Mat src = Imgcodecs.imread("D:\\l00379880\\GithubProjects\\images\\lena.jpg", Imgcodecs.CV_LOAD_IMAGE_COLOR);
        Rect rect = new Rect(10, 10, 200, 200);
        //tl:topleft,br:bottomright,左上角和右下角两个点确定了就好办了,以灰度图加载的时候Scalar中的3个参数要改成一个了,因为是单通道了new Scalar(255)
        Imgproc.rectangle(src, rect.tl(), rect.br(), new Scalar(0, 0, 255), 2, Imgproc.LINE_8, 0);
        Imgcodecs.imwrite("D:\\l00379880\\GithubProjects\\images\\lena_result.jpg", src);
        src.release();
    }
}
