package com.jiangc.practice.algorithm.sort.bubblesort;

/**
 * 
 * 
 * 我的理解
 * 冒泡排序可以说排定是倒着来的,最后一个数最先被排定,第二个数最后一次被排定,第一个数在最后一次排定之后也是被排定了,
 * 所以按循序看,排定顺序作为外循环,应该是从out=eLems-1开始的到1结束,out是数组的下标
 * 内循环是从最左开始到被排定的数的前一个为止进行循环,也就是说是从in=0开始到out-1结束即<out，因为swap是对in和in+1进行比较和替换
 * in最小是0,最大是out-1,所以in+1最大便是被排定的数,当内循环到达被排定的前一个数时,这次比较替换就可以排定当前被排定的数了
 * 以上就是冒泡排序
 * 
 * 
 * demonstrate bubble sort
 * @author jiangcheng-wb
 *
 */
public class ArrayBub {
	
	private long[] a; //ref of array a
	private int nElems; //number of data items
	
	//..................................................................
	
	public ArrayBub(int max) //constructor
	{
		a = new long[max]; //create the array
		nElems = 0; //no items yet
	}
	
	//..................................................................
	public void insert(long value) //put element into array
	{
		a[nElems] = value; //insert it
		nElems++; //increment size
	}
	
	//..................................................................
	public void display() //display array contents
	{
		for(int j=0; j<nElems; j++){ //for each element
			System.out.println(a[j] + " "); //display its
		}
	}
	
	//..................................................................
	public void bubbleSort()
	{
		int out,in;
		for(out=nElems-1;out>1;out--){
			for(in=0; in<out; in++){
				if( a[in] > a[in+1]){
					swap(in,in+1);
				}
			}
		}
	}
	
	//..................................................................
	public void swap(int one,int two)
	{
		long temp = a[one];
		a[one] = a[two];
		a[two] = temp;
	}
}

class BubbleSortApp1{
	public static void main(String[] args) {
		int maxSize = 100;
		ArrayBub arr;
		arr = new ArrayBub(maxSize);
		arr.insert(77);
		arr.insert(99);
		arr.insert(44);
		arr.insert(55);
		arr.insert(22);
		arr.insert(88);
		arr.insert(11);
		arr.insert(00);
		arr.insert(66);
		arr.insert(33);
		
		arr.display();
		
		arr.bubbleSort();
		arr.display();
		
	}
}