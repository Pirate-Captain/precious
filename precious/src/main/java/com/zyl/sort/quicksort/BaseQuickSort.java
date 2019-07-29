/*
 * chsi
 * Created on 2019-01-14
 */
package com.zyl.sort.quicksort;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class BaseQuickSort extends AbstractSort {

    @Override
    void sort(int[] dataArray) {
        sort(dataArray, 0, dataArray.length - 1);
    }

    private void sort(int[] dataArray, int left, int right) {
        int baseData = dataArray[left];
        int l = left;
        int r = right;

        while ( l < r ) {
            while ( (l < r) && dataArray[r] >= baseData ) {
                r--;
            }
            if ( l < r ) {
                int tmp = dataArray[r];
                dataArray[r] = dataArray[l];
                dataArray[l] = tmp;
            }
            while ( (l < r) && dataArray[l] <= baseData ) {
                l++;
            }
            if ( l < r ) {
                int tmp = dataArray[l];
                dataArray[l] = dataArray[r];
                dataArray[r] = tmp;
            }
        }
        if ( l > left ) {
            sort(dataArray, left, l - 1);
        }
        if ( r < right ) {
            sort(dataArray, l + 1, right);
        }
    }

    public static void main(String[] args) {
        BaseQuickSort quickSort = new BaseQuickSort();
        quickSort.estimateSort();
    }
}