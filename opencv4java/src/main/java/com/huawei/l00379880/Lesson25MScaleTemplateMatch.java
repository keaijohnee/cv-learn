package com.huawei.l00379880;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

/***********************************************************
 * @Description : 多尺度模板匹配
 * @author      : 梁山广
 * @date        : 2017/11/7 10:42
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
public class Lesson25MScaleTemplateMatch {
    public static void main(String[] args) {
        String rootPath = "D:\\l00379880\\GithubProjects\\images\\";
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat src = Imgcodecs.imread(rootPath + "flower.png");
        ImageUI ui = new ImageUI();
        ui.imshow("原始图片", src);
    }

    /**
     * 获取高斯算子,图片越来越小
     *
     * @param image
     * @return
     */
    public static List<Mat> buildGaussianPyramid(Mat image, int level) {
        List<Mat> pyramid = new ArrayList<>();
        Mat copy = image.clone();
        pyramid.add(image.clone());
        Mat dst = new Mat();
        for (int i = 0; i < level; i++) {
            Imgproc.pyrDown(copy, dst, new Size(copy.cols() / 2, copy.rows() / 2));
            dst.copyTo(copy);
            pyramid.add(dst.clone());
        }
        return pyramid;
    }

}
