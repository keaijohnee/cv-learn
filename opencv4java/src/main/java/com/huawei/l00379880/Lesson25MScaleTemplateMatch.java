package com.huawei.l00379880;

import org.opencv.core.*;
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
        Mat src = Imgcodecs.imread(rootPath + "test1.png");
        Mat template = Imgcodecs.imread(rootPath + "tpl.png");

        //下面注意:模板匹配的结果result的大大小必须是(原图片-模板大小+1),长款都必须是这个原则,否则无法运行
        int height = src.rows() - template.rows() + 1;
        int width = src.cols() - template.cols() + 1;
        Mat result = new Mat(height, width, CvType.CV_32FC1);

        // TM_XXX下面有5中选择,但是TM_CCOEFF_NORMED的效果最好
        int method = Imgproc.TM_CCOEFF_NORMED;

        // 开始匹配,并制定匹配方法
        Imgproc.matchTemplate(src, template, result, method);
        Core.MinMaxLocResult minMaxLocResult = Core.minMaxLoc(result);
        Point maxLoc = minMaxLocResult.maxLoc;
        Point minLoc = minMaxLocResult.minLoc;

        Point matchLoc = null;
        // Imgproc.TM_XX下五中方法的比较
        if (method == Imgproc.TM_SQDIFF || method == Imgproc.TM_SQDIFF_NORMED) {
            matchLoc = minLoc;
        } else {
            matchLoc = maxLoc;
        }

        Mat result8u = new Mat();
        Core.normalize(result, result, 0, 255, Core.NORM_MINMAX);
        result.convertTo(result8u, CvType.CV_8UC1);
        ImageUI result8uUI = new ImageUI();
        result8uUI.imshow("匹配结果的灰度图,右下角的点为开始匹配的点", result8u);

        // 为了不影响原图,所以先clone一份给自己
        Mat copy = src.clone();
        Imgproc.rectangle(copy, matchLoc, new Point(matchLoc.x + template.cols(), matchLoc.y + template.rows()), new Scalar(0, 0, 255), 2, 8, 0);
        ImageUI matchUI = new ImageUI();
        matchUI.imshow("把匹配的结果在原图上显示出来", copy);
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
