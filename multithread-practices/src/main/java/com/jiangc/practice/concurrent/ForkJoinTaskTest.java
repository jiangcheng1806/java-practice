package com.jiangcz.application.concurrent;

import java.io.InputStream;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

public class ForkJoinTaskTest {

    public static void main(String[] args) throws InterruptedException {
        /*int[] arr = new int[1000];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
*/
        ForkJoinPool pool = new ForkJoinPool();
        //ForkJoinTask<Integer> result = pool.submit(new ForkJoinTaskTest().new SumTask(arr,0,arr.length));
        //System.out.println("最终计算结果: " + result.invoke());
        pool.submit(new SumTask(0, 10));
        pool.awaitTermination(100, TimeUnit.MILLISECONDS);
        System.out.println(SumTask.sum);
        pool.shutdown();

    }
    private static class SumTask extends RecursiveAction {
        private static final int THRESHOLD = 20;

        //private int arr[];
        private int start;
        private int end;

        private static int sum;

        /*public SumTask(int[] arr, int start, int end) {
            this.arr = arr;
            this.start = start;
            this.end = end;
        }*/
        public SumTask( int start, int end) {
            //this.arr = arr;
            this.start = start;
            this.end = end;
        }

        /**
         * 小计
         */
        /*private Integer subtotal(){
            Integer sum = 0;
            for (int i = 0; i < end; i++) {
                sum += arr[i];
            }
            System.out.println(Thread.currentThread().getName()+": ∑(" + start + "~" + end + ")=" + sum);
            return sum;
        }*/
        private Integer subtotal(){
            Integer sum = 0;
            for (int i = 0; i < end; i++) {
                sum += i;
            }
            System.out.println(Thread.currentThread().getName()+": ∑(" + start + "~" + end + ")=" + sum);
            return sum;
        }

        @Override
        /*protected Integer compute() {
            if ((end - start) < THRESHOLD){
                return subtotal();
            } else {
                int middle = (start + end) / 2;
                SumTask left = new SumTask(arr, start, middle);
                SumTask right = new SumTask(arr, middle, end);
                left.fork();  right.fork();
                return left.join() + right.join();
            }
        }*/

        protected void compute() {
            if ((start - end) >= THRESHOLD){
                sum += subtotal();
            } else {
                int middle = (start + end) / 2;
                SumTask left = new SumTask(start,middle);
                SumTask right = new SumTask(middle,end);
                left.fork();
                right.fork();
            }
        }
    }
}
