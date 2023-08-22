package com.jiangcz.application.algorithm.sort.base;

import java.util.Arrays;
import java.util.Stack;

/**
 * 快速排序（非递归）
 *
 * ①. 从数列中挑出一个元素，称为"基准"（pivot）。
 * ②. 重新排序数列，所有比基准值小的元素摆放在基准前面，所有比基准值大的元素摆在基准后面（相同的数可以到任一边）。在这个分区结束之后，该基准就处于数列的中间位置。这个称为分区（partition）操作。
 * ③. 把分区之后两个区间的边界（low和high）压入栈保存，并循环①、②步骤
 * @param arr   待排序数组
 */
public class StackQuickSort {

    public static void main(String[] args) {
        int[] arr = {49, 38, 65, 97, 26, 13, 27, 49, 55, 4};
        System.out.println("排序前:" + Arrays.toString(arr));

        quickSortByStack(Arrays.copyOf(arr, arr.length));
    }


    private static void quickSortByStack(int[] arr) {

        if (arr.length <= 0) {
            return;
        }

        Stack<Integer> stack = new Stack<Integer>();

        stack.push(0);
        stack.push(arr.length - 1);

        while (!stack.empty()) {
            int high = stack.pop();
            int low = stack.pop();
            int pivot = partition(arr, low, high);

            if (pivot > low) {
                stack.push(low);
                stack.push(pivot - 1);
            }
            if (pivot < high && pivot >= 0) {
                stack.push(pivot + 1);
                stack.push(high);
            }
        }
        System.out.println("排序后:" + Arrays.toString(arr));
    }

    private static int partition(int[] arr, int low, int high) {
        if (arr.length <= 0 || low >= high) {
            return -1;
        }
        int leftIndex = low;
        int rightIndex = high;
        //挖坑1：保存基准的值
        int pivot = arr[leftIndex];
        while (leftIndex < rightIndex) {
            //坑2：从后向前找到比基准小的元素，插入到基准位置坑1中
            while (leftIndex < rightIndex && arr[rightIndex] >= pivot) {
                //不做交换
                rightIndex--;
            }
            //右侧数据小于基准，进行交换
            arr[leftIndex] = arr[rightIndex];


            //坑3：从前往后找到比基准大的元素，放到刚才挖的坑2中
            while (leftIndex < rightIndex && arr[leftIndex] <= pivot) {
                leftIndex++;
            }
            //左侧数据大于基准，进行交换
            arr[rightIndex] = arr[leftIndex];

        }
        //基准值填补到坑3中，准备分治递归快排
        arr[leftIndex] = pivot;
        return leftIndex;

    }

}