package com.huawei.l00379880;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/***********************************************************
 * @Description : 分水岭算法的实现,基本流程如下:
 *                输入图像-->灰度化-->二值化-->距离变换-->寻找种子
 *                -->生成Marker-->分水岭变换-->输出图像-->完
 * @author      : 梁山广
 * @date        : 2017/11/13 20:55
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
public class Lesson37WaterSheds {
    public static void main(String[] args) {

        // 1.输入图像并进行适当的预处理
        String rootPath = "D:\\l00379880\\GithubProjects\\images\\";
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        // 图像是彩色还是单色都是可以的
        Mat src = Imgcodecs.imread(rootPath + "cards.png");
        ImageUI ui = new ImageUI();
        ui.imshow("原始图像", src);
        // 白色背景换成黑色背景.因为白色会干扰比较大所以为了方便处理图片一般都把图像背景转为黑色
        int width = src.cols();
        int height = src.rows();
        int dims = src.channels();
        byte[] data = new byte[width * height * dims];
        src.get(0, 0, data);
        int index = 0;
        int b, g, r;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                index = (row * width + col) * dims;
                b = data[index] & 0xff;
                g = data[index + 1] & 0xff;
                r = data[index + 2] & 0xff;
                // 当一个是纯黑的时候就把这个点改为纯白
                if (b == 255 && g == 255 && r == 255) {
                    data[index] = (byte) 0;
                    data[index + 1] = (byte) 0;
                    data[index + 2] = (byte) 0;
                }
            }
        }
        src.put(0, 0, data);
        ImageUI srcReverse = new ImageUI();
        srcReverse.imshow("背景取反后的图像", src);

        // 2.灰度化
        Mat gray = new Mat();
        Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
        ImageUI grayUI = new ImageUI();
        grayUI.imshow("灰度图像", gray);

        // 3.二值化
        Mat bin = new Mat();
        // 注意灰度图像才可以进行二值化
        Imgproc.threshold(gray, bin, 0, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
        ImageUI binUI = new ImageUI();
        binUI.imshow("二值化图像", bin);

        // 4.距离变换
        Mat dist = new Mat();
        Imgproc.distanceTransform(bin, dist, Imgproc.DIST_L2, 3);
        // 转换到0~255之间的图像
        Core.normalize(dist, dist, 0, 255, Core.NORM_MINMAX);
        Mat dist_8u = new Mat();
        // 转换为彩色图像
        dist.convertTo(dist_8u, CvType.CV_8U);
        ImageUI distUI = new ImageUI();
        distUI.imshow("距离变换后的图像", dist_8u);

        // 5.寻找种子
        // 进行二值化和腐蚀,去除尖锐边角和噪声(0.4~1)*255范围进行二值化
        Imgproc.threshold(dist, dist, 102, 255, Imgproc.THRESH_BINARY);
        Mat erode = new Mat();
        // 获得腐蚀算子
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(13, 13));
        Imgproc.erode(dist, erode, kernel);
        Mat erode_8u = new Mat();
        erode.convertTo(erode_8u, CvType.CV_8U);
        ImageUI erodeUI = new ImageUI();
        erodeUI.imshow("腐蚀后的图像", erode_8u);

        // 6.生成marker

    }
}
