package com.huawei.l00379880;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.util.Random;

/**
 * 高斯模糊
 */
public class Lesson13GaussBlur {
    public static void main(String[] args) {
        String rootPath = "D:\\l00379880\\GithubProjects\\images\\";
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat src = Imgcodecs.imread(rootPath + "lena.png");
        ImageUI ui = new ImageUI();
        ui.imshow("原始图片", src);
        int width = src.cols();
        int height = src.rows();
        int dims = src.channels();
        byte[] data = new byte[width * height * dims];
        src.get(0, 0, data);
        int b, g, r;
        // 对每个点加一下高斯噪声
        Random random = new Random();
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                // 产生0到50的随机数
                double bf = random.nextGaussian() * 30;
                double gf = random.nextGaussian() * 30;
                double rf = random.nextGaussian() * 30;
                // 转换为0到255的十进制数
                b = data[(row * width + col) * dims] & 0xff;
                g = data[(row * width + col) * dims + 1] & 0xff;
                r = data[(row * width + col) * dims + 2] & 0xff;
                // 加上高斯模糊的参数
                b += chomp(bf);
                g += chomp(gf);
                r += chomp(rf);
                // 把结果返回去
                data[(row * width + col) * dims] = (byte) b;
                data[(row * width + col) * dims + 1] = (byte) g;
                data[(row * width + col) * dims + 2] = (byte) r;
            }
        }
        // 把处理后的数据放回去
        src.put(0, 0, data);
        ImageUI gaussUI = new ImageUI();
        gaussUI.imshow("高斯模糊后的图像", src);
    }

    /**
     * 限制参数d 在0-255之间
     *
     * @param d 传入的double参数
     * @return 一个0到255之间的整数
     */
    public static int chomp(double d) {
        if (d > 255) {
            return 255;
        } else if (d < 0) {
            return 0;
        } else {
            return (int) d;
        }
    }
}
