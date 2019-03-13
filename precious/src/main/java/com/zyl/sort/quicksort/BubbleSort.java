/*
 * chsi
 * Created on 2019-01-16
 */
package com.zyl.sort.quicksort;

/**
 * @author zhuyl <a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class BubbleSort extends AbstractSort {

    @Override
    public void sort(int[] dataArray) {
        for ( int i = 0; i < dataArray.length; i++ ) {
            for ( int j = 0; j < dataArray.length - 1; j++ ) {
                if ( dataArray[j] > dataArray[j + 1] ) {
                    int tmp = dataArray[j];
                    dataArray[j] = dataArray[j + 1];
                    dataArray[j+1] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        new BubbleSort().estimateSort();
    }
}