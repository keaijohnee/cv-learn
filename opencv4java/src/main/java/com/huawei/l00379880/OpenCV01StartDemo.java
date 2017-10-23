package com.huawei.l00379880;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;

public class OpenCV01StartDemo {
    public static void main(String[] args) {
        //引入OpenCV的dll
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat imgSrc = new Mat();
        //设置图像大小
        imgSrc.create(300, 300, CvType.CV_8UC3);
        //设置为红色
        imgSrc.setTo(new Scalar(0, 0, 255));
        //把正方形写入图片,中文路径还不行
        Imgcodecs.imwrite("D:\\l00379880\\GithubProjects\\images\\test.jpg", imgSrc);
        imgSrc.release();//记得释放内存
    }
}
