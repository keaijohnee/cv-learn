package com.huawei.l00379880;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * 超大图像的二值化
 *
 * @author 梁山广
 * @eamil liangshanguang2@gmail.com
 * @date 2017-11-05 21:14
 */
public class Lesson22BigImgBinnary {
    public static void main(String[] args) {
        String rootPath = "D:\\l00379880\\GithubProjects\\images\\";
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat src = Imgcodecs.imread(rootPath + "case9.jpg");

        //图片太大没法完全显示,所以需要先进性图片缩小进行效果查看,但是处理的图片是实际的图片
        Mat smallSrc = new Mat();
        Imgproc.resize(src, smallSrc, new Size(src.cols() / 5, src.rows() / 5));
        ImageUI smallUI = new ImageUI();
        smallUI.imshow("缩小16倍的图片", smallSrc);

        Mat gray = new Mat();
        Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);

        Mat dst = new Mat();
        Imgproc.adaptiveThreshold(gray, dst, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 55, 10);
        Mat smallDst = new Mat();
        Imgproc.resize(dst, smallDst, new Size(src.cols() / 5, src.rows() / 5));
        ImageUI smallDstUI = new ImageUI();
        // 结果还凑合但是还是有不少噪点
        smallDstUI.imshow("二值化之后的图像", smallDst);
        // 图片写入文件
        Imgcodecs.imwrite(rootPath + "case9_binary.jpg", dst);

        // 下面用自定义的方法进行二值化处理
        int width = gray.cols();
        int height = gray.cols();


    }
}
