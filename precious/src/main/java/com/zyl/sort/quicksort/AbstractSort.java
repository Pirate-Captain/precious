/*
 * chsi
 * Created on 2019-01-16
 */
package com.zyl.sort.quicksort;

import java.util.Random;

/**
 * @author zhuyl <a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public abstract class AbstractSort {
    /**
     * 对数组进行排序
     *
     * @param dataArray 待排序数组
     */
    abstract void sort(int[] dataArray);

    public void estimateSort() {
        for ( int dataLength = 10; dataLength <= 1000000; dataLength *= 10 ) {
            int[] dataArray = new int[dataLength];
            long startTime = System.currentTimeMillis();
            for ( int index = 0; index < dataLength; index++ ) {
                dataArray[index] = new Random().nextInt(dataLength);
            }
            long endTime = System.currentTimeMillis();

            sort(dataArray);

            System.out.println("准备：" + dataLength + " 长度的数据耗时：" + (endTime - startTime) + "，排序耗时：" + (System.currentTimeMillis() - endTime));
        }
    }
}