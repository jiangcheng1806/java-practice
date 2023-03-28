package com.jiangc.practice.algorithm.sort.insertionsort;

/**
 * 插入排序外循环从第二个数开始一直到最后一个数也就是从out=1开始到nElems-1结束(out<nElems)
 * 内循环:然后往前查找比自己大的值,一边查找一边把遍历到的数往后挪,直到前面的数比自己小就停下来,把自己给插进去
 * 插入排序很精巧可以练习一下
 * @author jiangcheng-wb
 *
 */
public class ArrayIns {


	private long[] a; //ref of array a
	private int nElems; //number of data items
	
	//..................................................................
	
	public ArrayIns(int max) //constructor
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
	public void insertionSort()
	{
		int out,in;
		
		for (out = 1; out < nElems; out++) 
		{
			long temp = a[out];
			in = out;
			
			
			
			//这一段应该是挺难以理解的，但是我刚好现在理解了，所以应该写下来
			/**
			 * 就是使用temp记住out的值,然后将temp从in = out开始一直往前比较直到到1为止,如果temp<=a[in-1]就找到了插入的位置了
			 * 因为我们外循环是从左往右排的,所以被找到的可以插入这个temp的值的位置的左边一定是排好的
			 * 比如第一个可能交换的排定可以理解成这样的场景,out=1,temp就是a[1],然后a[0]跟temp比较,发现a[0]比temp小,
			 * 这样就可以将a[0]的值赋值给a[1],in--之后in变成了0,之后吧temp值赋值给a[0],这样就完成了替换了,
			 * 每一次插入都是从1到out-1的所有值中找到比out大的值,并将这个值一直到out之前的值往后移一位,把temp值放进去,
			 * out位置的值在往前查找的过程中已经第一个被in-1的值替换了就可以不管了,out的值通过temp放进了找到的位置上
			 */
			while(in>0 && a[in-1] >= temp)
			{
				a[in] = a[in-1];
				--in;
			}
			a[in] = temp;
		}
	}

}
