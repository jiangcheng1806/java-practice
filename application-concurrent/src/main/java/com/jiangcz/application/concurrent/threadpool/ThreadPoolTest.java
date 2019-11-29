package com.jiangcz.theory.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 写个简单线程池应用例子
 * @author Chan Kiang
 *
 */
public class ThreadPoolTest {

	public static void main(String[] args) {
		
		//corePoolSize 5 maximumPoolSize 10 keepAliveTime 200 unit workQueue threadFactory handle
		//workQueue 多使用 linkedBlockingQueue和SychronizeQueue,也是用ArrayBlockingQueue和SynchronousQueue
		//handle 包括ThreadPoolExecutor.AbortPolicy、ThreadPoolExecutor.DiscardPolicy、ThreadPoolExecutor.DiscardOldestPolicy、ThreadPoolExecutor.CallerRunsPolicy
		//Executors.newCachedThreadPool();  //创建一个缓冲池，缓冲池容量大小为Integer.MAX_VALUE 
		//Executors.newSingleThreadExecutor();   
		//创建容量为1的缓冲池    Executors.newFixedThreadPool(int);    //创建固定容量大小的缓冲池
		ThreadPoolExecutor executor = new ThreadPoolExecutor(5,10,200,TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(5));
		
		for (int i = 0; i < 15; i++) {
			MyTask myTask = new MyTask(i);
			executor.execute(myTask);
			System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
		             executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());
		}
		
	}
}

/**
 * 自定义线程
 * @author Chan Kiang
 *
 */
class MyTask implements Runnable {

	private int taskNum;
	
	public MyTask(int num){
		this.taskNum = num;
	}
	
	@Override
	public void run() {
		System.out.println("正在执行task"+taskNum);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("task"+taskNum+"执行完毕");
	}
	
}