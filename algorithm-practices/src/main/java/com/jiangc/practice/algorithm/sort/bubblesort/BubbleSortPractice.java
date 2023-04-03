package com.jiangc.practice.algorithm.sort.bubblesort;

import com.google.gson.Gson;

import java.util.Arrays;

public class BubbleSortPractice {
    public static void main(String[] args) {

        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            if (i < 50){
                arr[i] = 100-i;
            } else {
                arr[i] = i;
            }
        }

        System.out.println("before=>"+ new Gson().toJson(arr));
        bubbleSort(arr);
        System.out.println("after=>"+ new Gson().toJson(arr));
    }

    public static void bubbleSort(int[] arr){
        for (int i = arr.length - 1; i > 0 ; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
}
