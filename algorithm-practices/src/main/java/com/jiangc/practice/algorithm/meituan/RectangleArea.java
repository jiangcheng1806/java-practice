package com.jiangc.practice.algorithm.meituan;

import java.util.Scanner;

/**
 * 类名称：RectangleArea<br>
 * 类描述：<br>
 * 创建时间：2018年08月17日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class RectangleArea {

    /**
     *
     * 给定一组非负整数组成的数组h，代表一组柱状图的高度，其中每个柱子的宽度都为1。 在这组柱状图中找到能组成的最大矩形的面积（如图所示）。 入参h为一个整型数组，代表每个柱子的高度，返回面积的值
     *
     * -输入
     * 输入包括两行,第一行包含一个整数n(1 ≤ n ≤ 10000)
     * 第二行包括n个整数,表示h数组中的每个值,h_i(1 ≤ h_i ≤ 1,000,000)
     * -输出
     * 输出一个整数,表示最大的矩阵面积。
     *
     * tip :其实只要掌握一点，这道题目就比较好解了。那就是分析清楚，最大的矩形面积是由什么构成的。其实最直接的一个想法，就是找出所有能够构成的矩形，然后找到其中最大的一个。
     *      那要怎么样能够遍历所有的矩形呢？其实每个构成的矩形的高，都是某一个柱状图的高，也就是那个矩形，最低的柱状图的高。所以以每一个柱状图为中心，向两边展开，找到大于等于自己的柱状图，就归位面积的一部分，一直扫面到小于自身高度的停止。然后和现有最大面积比较。最后得到最大面积。
     *      这个思路可以解决问题，但是，其效率可想而知，如果这是一个递增的数组，基本上每个柱状图就都要遍历一遍数组，效率就显得十分的低下了。目前没有其他的解决办法，留个坑，以后填。
     *
     *
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(solution(arr,n));
    }

    public static int solution(int[] arr,int len){
        int max = 0;
        for (int i = 0; i < len; i++) {
            int count = 1;
            int j = i;
            if (i == 0){
                while (++j < len && arr[j] >= arr[i]){
                    count++;
                }
            } else if (i == len-1){
                while (--j > 0 && arr[j] >= arr[i]){
                    count++;
                }
            } else {
                while (++j < len && arr[j] > arr[i]){
                    count++;
                }
                j = i;
                while (--j > 0 && arr[j] >= arr[i]){
                    count++;
                }
            }
            if (arr[i] * count >= max){
                max = arr[i] * count;
            }
        }
        return max;
    }

}
