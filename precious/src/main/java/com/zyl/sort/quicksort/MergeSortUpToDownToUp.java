/*
 * chsi
 * Created on 2019-01-16
 */
package com.zyl.sort.quicksort;

/**
 * 归并排序（从上到下 再从下到上）
 * 先把整个数组打散，然后再归并
 *
 * @author zhuyl <a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class MergeSortUpToDownToUp extends AbstractSort {

    private void mergeSort(int[] dataArray, int start, int end) {
        if ( null == dataArray || start >= end ) {
            return;
        }
        int mid = (start + end) / 2;
        mergeSort(dataArray, start, mid);
        mergeSort(dataArray, mid + 1, end);
        mergeData(dataArray, start, mid, end);
    }

    private void mergeData(int[] dataArray, int start, int mid, int end) {
        int i = start;
        int j = mid + 1;
        int k = 0;
        int[] tmp = new int[end - start + 1];
        while ( i <= mid && j <= end ) {
            if ( dataArray[i] <= dataArray[j] ) {
                tmp[k++] = dataArray[i++];
            } else {
                tmp[k++] = dataArray[j++];
            }
        }
        while ( i <= mid ) {
            tmp[k++] = dataArray[i++];
        }
        while ( j <= end ) {
            tmp[k++] = dataArray[j++];
        }
        if ( tmp.length >= 0 ) {
            System.arraycopy(tmp, 0, dataArray, start, tmp.length);
        }
    }

    @Override
    void sort(int[] dataArray) {
        mergeSort(dataArray, 0, dataArray.length - 1);
    }

    public static void main(String[] args) {
        new MergeSortUpToDownToUp().estimateSort();
    }
}