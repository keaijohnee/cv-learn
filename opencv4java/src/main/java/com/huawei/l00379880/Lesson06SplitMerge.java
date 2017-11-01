package com.huawei.l00379880;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.util.ArrayList;
import java.util.List;

/**
 * 通道分离与合并
 *
 * @author l0037980
 * @datte 2017-11-01 19:09
 */
public class Lesson06SplitMerge {
    public static void main(String[] args) {
        String rootPath = "D:\\l00379880\\GithubProjects\\images\\";
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        // 默认是彩色读取
        Mat src = Imgcodecs.imread(rootPath + "lena.png");
        ImageUI ui = new ImageUI();
        ui.imshow("lena显示", src);

        // 拆分通道显示
        List<Mat> matList = new ArrayList<>();
        Core.split(src, matList);
        for (int i = 0; i < matList.size(); i++) {
            ImageUI singleChannelUI = new ImageUI();
            singleChannelUI.imshow("channel--" + i, matList.get(i));
        }
    }
}
