package com.jiangc.practice.algorithm.sort.insertionsort;

import java.util.Arrays;

/**
 * 类名称：InsertPromot<br>
 * 类描述：插入排序  https://blog.csdn.net/u012152619/article/details/47306209<br>
 * 创建时间：2018年08月20日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class InsertPromot {

    /**
     * 在要排序的一组数中，假设前面(n-1)[n>=2] 个数已经是排好顺序的，现在要把第n个数找到相应位置并插入，使得这n个数也是排好顺序的。如此反复循环，直到全部排好顺序。
     */
    public static void insertionSort(int[] array){
        int len = array.length;
        int counter = 1;

        for (int i = 1; i < len; i++) {

            int temp = array[i]; // 存储待排序的元素值
            int point = i-1; // 与待排序元素值作比较的元素的下标

            while (point >= 0 && array[point] > temp){ // 当前元素比待排序元素大

                array[point+1] = array[point];
                point--;

            }

            array[point+1] = temp; //找到了插入位置，插入待排序元素

            System.out.println("第" + counter + "轮排序结果:");

            //display();
            Arrays.stream(array).forEach(value -> System.out.print(value + " "));
            System.out.println();
            counter++;
        }
    }

    /**
     * 冒泡排序、选择排序、插入排序的时间复杂度为O（n2），都被称为简单排序或基本排序，三者都是稳定的排序算法
     * @param args
     */
    public static void main(String[] args) {
        int arr[] = {23,34,56,24,21,58,32};

        System.out.println(Arrays.asList(arr).toString());

        //insertionSort(arr);
        //binaryInsertionSort(arr);


        int arr1[] = {8,1,11,12,4,20,7,2,6,15};
        twoWayInsertionSort(arr1);
    }

    /**
     * 二分插入排序
     *
     * 结果是错误的
     *
     */
    public static void binaryInsertionSort(int[] array){

        int len = array.length;
        int counter = 1;

        for (int i = 1; i < len; i++) {

            int temp = array[i];//存储待排序的元素值

            if (array[i-1] > temp){ //比有序数组的最后一个元素要小

                int point = binarySearch(array,0,i-1,temp); //获取应插入位置的下标

                for (int j = i; j > point; j--) { //将有序数组中，插入点之后的元素后移一位
                    array[j] = array[j-1];

                }

                array[point] = temp;
            }

            System.out.println("第" + counter + "轮排序结果:");
            Arrays.stream(array).forEach(value->System.out.print(value+" "));
            System.out.println();
            counter++;
        }
    }

    /**
     * 二分插入排序
     */
    public static int binarySearch(int[] array,int lowerBound,int upperBound,int target){
        int curIndex ;

        while (lowerBound < upperBound){
            curIndex = (lowerBound + upperBound) / 2;
            if (array[curIndex] > target){
                upperBound = curIndex - 1;
            } else {
                lowerBound = curIndex + 1;
            }
        }

        return lowerBound;
    }

    /**
     * 2-路插入排序
     *
     * 目的是减少排序过程中移动记录的次数，但为此需要n个记录的辅助空间。
     *
     * 算法的思想为：另设一个和原始待排序列L相同的数组D，首先将L[1]复制给D[1]，并把D[1]看成是已排好序的序列中处于中间位置的元素（枢纽元素），之后将L中的从第二个元素开始依次插入到数组D中，大于D[1]的插入到D[1]之后的序列(此处我称为右半边序列，用的是数组左半部分空间)，小于D[1]的插入到D[1]之前的序列(左半边序列，用的是数组右半部分空间)。
     *
     * 该算法将数组当做首尾衔接的环形结构来使用。
     *
     * 排序完成之后，数组中的元素并不是按照下标升序排列的，而是靠first与final指针确定起始元素。
     *
     * 注意：当L[1]为最小值时，2-路插入排序失去它的优越性，等同于二分插入排序。
     */
    public static void twoWayInsertionSort(int[] array){

        int len = array.length;
        int[] newArr = new int[len];

        newArr[0] = array[0]; //将原数组的第一个元素作为枢纽元素
        int first = 0; //指向最小元素的指针
        int last = 0; //指向最大元素的指针

        Arrays.stream(array).forEach(value -> System.out.print(value + " ")); //打印初始化数组
        System.out.println();

        for (int i = 1; i < len; i++) {

            if (array[i] > newArr[last]){ //大于等于最大元素，直接插入到last后面，不用移动元素
                last++;
                newArr[last] = array[i];
            } else if (array[i] < newArr[first]){ //小于最小元素，直接插到first前面，不用移动元素
                first = (first-1+len) % len;
                newArr[first] = array[i];
            } else if (array[i] >= newArr[0]){ //在最大值与最小值之间，且大于等于枢纽元素，插入到last之前，需要移动元素

                int curIndex = last;
                last++;
                do {  //比array[i]大的元素后移一位
                    newArr[curIndex+1] = newArr[curIndex];
                    curIndex--;
                } while (newArr[curIndex]>array[i]);

                newArr[curIndex+1] = array[i]; //插入到正确的位置
            } else { //在最大值与最小值之间，且小于枢纽元素，插入到first之后，需要移动元素

                int curIndex = first;
                first = (first-1+len) % len;
                do { //比array[i]小的元素前移一位
                    newArr[curIndex-1] = newArr[curIndex];
                    curIndex = (curIndex+1+len) % len;
                } while (newArr[curIndex] <= array[i]);

                newArr[(curIndex-1+len) % len] = array[i]; //插入到正确的位置
            }

            Arrays.stream(newArr).forEach(value -> System.out.print(value + " "));//打印新数组中的元素
            System.out.println();

        }
    }
}
