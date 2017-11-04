package com.huawei.l00379880;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * 各种图片的模糊,均值模糊,中值模糊,自定义模糊
 *
 * @author l00379880
 * @date 2017-11-04 14:51
 */
public class Lesson12Blur {
    public static void main(String[] args) {
        String rootPath = "D:\\l00379880\\GithubProjects\\images\\";
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        // 读入含有噪声的lena图像 lesson12noiselena
        Mat src = Imgcodecs.imread(rootPath + "lenanoise.png");
        ImageUI ui = new ImageUI();
        ui.imshow("原始图像", src);

        // 1.均值模糊(均值卷积),效果是全局模糊
        Mat avgMat = new Mat();
        // 最后一个参数为卷积核,最好是奇数,数字越大模糊效果越强
        Imgproc.blur(src, avgMat, new Size(5, 5));
        ImageUI avgUI = new ImageUI();
        avgUI.imshow("均指模糊后的图像", avgMat);

        // 2.中值模糊,效果是局部模糊,中值模糊去局部噪声的效果非常好
        Mat meanMat = new Mat();
        Imgproc.medianBlur(src, meanMat, 5);
        ImageUI meanUI = new ImageUI();
        meanUI.imshow("中值模糊后的图像", meanMat);

        // 3.自定义模糊,用于对上面模糊的图片进行锐化处理,可用于还原模糊的图片.构造卷积矩阵非常关键
        Mat selfMat = new Mat();
        // 下面两行构造卷积矩阵
        Mat kMat = new Mat(3, 3, CvType.CV_32FC1);
        // 初始化一个3*3的矩阵
        float[] data = new float[]{0, -1, 0, -1, 5, -1, 0, -1, 0};
        // 用数组给上面的3*3的矩阵初始化
        kMat.put(0, 0, data);
        // 最重要:自定义过滤器.
        // 对第二部得到的图像进行第二次的锐化过滤
        Imgproc.filter2D(meanMat, selfMat, CvType.CV_8U, kMat);
        ImageUI selfUI = new ImageUI();
        selfUI.imshow("自定义过滤器处理后的图像", selfMat);

    }
}
