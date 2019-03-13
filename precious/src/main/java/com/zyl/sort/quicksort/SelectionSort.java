/*
 * chsi
 * Created on 2019-01-16
 */
package com.zyl.sort.quicksort;

/**
 * 选择排序
 *
 * @author zhuyl <a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class SelectionSort extends AbstractSort {
    @Override
    void sort(int[] dataArray) {
        for ( int i = 0; i < dataArray.length - 1; i++ ) {
            int min = i;
            for ( int j = i + 1; j < dataArray.length; j++ ) {
                if ( dataArray[i] > dataArray[j] ) {
                    min = j;
                }
            }
            if ( min != i ) {
                int tmp = dataArray[i];
                dataArray[i] = dataArray[min];
                dataArray[min] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        new SelectionSort().estimateSort();
    }
}