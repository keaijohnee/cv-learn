package com.huawei.l00379880.middle;

import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;

/***********************************************************************
 * @Description : BufferedImage的像素统计:最大值,最小值,平均值和标准差
 *                可以在Lesson03PixelOperationImage中的process函数中调用
 * @author      : 梁山广
 * @date        : 2017/11/19 18:16
 * @email       : liangshanguang2@gmail.com
 ***********************************************************************/
public class Lesson04PixelStatistic {

    @Getter
    @Setter
    private int min;
    @Getter
    @Setter
    private int max;
    @Getter
    @Setter
    private double means;
    @Getter
    @Setter
    private double standardDiff;


    public static void process(BufferedImage image) {

    }

}
