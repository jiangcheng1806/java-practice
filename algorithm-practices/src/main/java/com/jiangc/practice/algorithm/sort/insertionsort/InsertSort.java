package com.jiangc.practice.algorithm.sort.insertionsort;

import java.util.Arrays;

/**
 * 类名称：InsertSort<br>
 * 类描述：<br>
 * 创建时间：2018年07月18日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class InsertSort {

    public static void main(String[] args) {
        int[] arr = {32,43,23,13,5};
        System.out.println("before sort , arr :" + Arrays.toString(arr));

        insertSort(arr);

        System.out.println("after sort , arr :" + Arrays.toString(arr));
    }

    public static void insertSort(int[] arr){
        int length = arr.length;

        for (int i = 1; i < length; i++) {//这里的i是被插入的值的位置，也就是从第二个开始，最后一个结尾 i -> [1，length)

            int v = arr[i];//使用v暂存arr[i],v代表被插入的值


            int j = i-1;

            //int b = arr[i-1];//使用 b 暂存i前面的数 //无意义

            /*if (v < b) { //如果v比b小则交换位置

                arr[i] = arr[i-1];

                arr[i-1] = v;

            }*/

            //arr[i] = b; //无意义

            //因为 v 每次跟 前面的数比较之后 位置 往前推一位 之后还要继续跟之前的数进行比较 不断往前推

            while (j >= 0 && v < arr[j]) { // j >= 0 有必要限制循环次数 也要注意这里 包括了 j = 0 的情况


                //v 是被比较的数 ， j是比大小的数 ， j  从 i-1 开始，符合条件就 往前推
                //-------------------------

                //arr[j] = arr[j-1];

                arr[j+1] = arr[j]; //替换后面的比替换前面的好 //第一次相当于 i 和 j调换了

                //arr[j-1] = v; //这里赋值没有意义 后面就会被 重新赋值了

                //-------------------------


                j--;
            }

            //当推进到 v 大于 arr[j] 的 时候 就可以 知道 v 应该 放在 arr[j] 的后面 因 j-- 已经操作 所以 当前的 arr[j] 还是原始值 而arr[j + 1]已经不是原始值了

            arr[j+1] = v;

        }
    }
}
