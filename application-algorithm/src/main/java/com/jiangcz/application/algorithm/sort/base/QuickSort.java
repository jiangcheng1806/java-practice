package com.jiangcz.application.algorithm.sort.base;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {49, 38, 65, 97, 26, 13, 27, 49, 55, 4};
        System.out.println("排序前:" + Arrays.toString(arr));
        int[] a = Arrays.copyOf(arr, arr.length);

        quicksort1(a, 0, a.length - 1);
    }
    private static void quicksort1(int[] arr, int low, int high) {
        if (arr.length <= 0 || low >= high) {
            return;
        }
        int leftIndex = low;
        int rightIndex = high;
        //挖坑1：保存基准的值
        int temp = arr[leftIndex];
        while (leftIndex < rightIndex) {
            //坑2：从后向前找到比基准小的元素，插入到基准位置坑1中
            while (leftIndex < rightIndex && arr[rightIndex] >= temp) {
                //不做交换
                rightIndex--;
            }
            //右侧数据小于基准，进行交换
            arr[leftIndex] = arr[rightIndex];



            //坑3：从前往后找到比基准大的元素，放到刚才挖的坑2中
            while (leftIndex < rightIndex && arr[leftIndex] <= temp) {
                leftIndex++;
            }
            //左侧数据大于基准，进行交换
            arr[rightIndex] = arr[leftIndex];

        }
        //基准值填补到坑3中，准备分治递归快排
        arr[leftIndex] = temp;

        quicksort1(arr, low, leftIndex - 1);
        quicksort1(arr, leftIndex + 1, high);
    }
}