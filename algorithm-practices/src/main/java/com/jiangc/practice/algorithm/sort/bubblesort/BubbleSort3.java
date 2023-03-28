package com.jiangc.practice.algorithm.sort.bubblesort;

import java.util.Arrays;

/**
 * 类名称：BubbleSort3<br>
 * 类描述：<br>
 * 创建时间：2018年09月03日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class BubbleSort3 {

    public static void main(String[] args) {
        //int[] arr = {2,4,6,3,12,7,23,15,88,64,57,11,86,74,34,33};
        int[] arr = {2,6,4,3};
        //System.out.println(Arrays.asList(arr));//貌似被误导了很久这样打印不出来东 //System.out.println(arr);//这样更打印不出来 //System.out.println(Arrays.asList(arr).toString());//仍然打印不出来 //System.out.println(arr.toString());//数组也有toString方法 果然数组也是类 但是仍然打印不出来
        /*for (int i = arr.length - 1; i >= 0; i--) {
            System.out.println(arr[i]);
        }*///打印太麻烦

        System.out.println("before sort:");
        Arrays.stream(arr).forEach(System.out::println);//lambada打印不费力

        bubbleSort02(arr);

        System.out.println("after sort:");
        Arrays.stream(arr).forEach(System.out::println);//lambada打印不费力
        /*
        int[] ints = new int[10000];
//向数组写入10000个数据 前1000倒序 ， 后9000顺序。
        for (int i = 0; i < 10000; i++) {
            if (i<=1000) ints[i] = 1000-i;
            else ints[i] = i;
        }
        System.out.println("排序前：");
        System.out.println(Arrays.toString(ints));
        System.out.println("排序后：");
//调用3种方式，分别记录运行时间
        long startTime = System.currentTimeMillis();//获取当前时间
        //System.out.println(Arrays.toString(bubbleSort02(ints))); // 方式  bubbleSort(ints) 没有经过优化的冒泡
//bubbleSort(ints);
        long endTime = System.currentTimeMillis();//获取当前时间
        System.out.println("程序运行时间："+(endTime-startTime)+"ms");//两个时间相减 = 程序运行时间
        */
    }

    /**
     * 最原始的冒泡排序算法，如果本来就是有序的数组会出现空跑的情况
     * @param arr
     */
    private static void bubbleSort01(int[] arr) {
        for (int i = arr.length - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j+1]){
                    swap(arr,j,j+1);
                }
            }
        }
    }

    /**
     * 原始冒泡排序算法的改进，避免空跑排序的情况，只要排序完成就不再进行排序
     * @param arr
     */
    public static void bubbleSort02(int[] arr){
        boolean flag = true;
        while (flag){
            flag = false;
            for (int i = arr.length - 1; i >= 1; i--) {
                for (int j = 0; j < i; j++) {
                    if (arr[j] > arr[j+1]){
                        swap(arr,j,j+1);
                        flag = true;
                    }
                }
            }
        }
    }

    /**
     * 设置排序边界，避免在已经排序的部分还要进行排序
     * @param arr
     */
    /*public static void bubbleSort03(int[] arr){
        boolean flag = true;
        while (flag){
            flag = false;
            int pos = 0;
            for (int i = arr.length - 1; i >= 1; i--) {
                for (int j = 0; j < i; j++) {
                    if (arr[j] > arr[j+1]){
                        swap(arr,j,j+1);
                        flag = true; //设置最新边界
                        pos = j;
                    }
                }
                i = pos;
            }
        }
    }*/

    /**
     * 一开始第一次写的时候直接a跟b进行交换swap(a,b){a = temp,b = a,b = temp} 真的错误的很离谱 因为调用这两个index做参数 根本改变不了数组中的每个元素的值，最后还是写成了如下所示的
     * @param arr
     * @param a
     * @param b
     */
    private static void swap(int[] arr,int a, int b) {
        System.out.printf("befor swap %d: %d, %d: %d%n",a,arr[a],b,arr[b]);
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
        System.out.printf("after swap %d: %d, %d: %d%n",a,arr[a],b,arr[b]);
    }
}
