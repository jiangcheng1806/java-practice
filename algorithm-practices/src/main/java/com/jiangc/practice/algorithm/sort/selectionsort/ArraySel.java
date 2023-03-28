package com.jiangc.practice.algorithm.sort.selectionsort;

/**
 * 选择排序感觉还是不太够理解,不过基本上应该是这样的
 * 
 * 首先排定外循环,从最左到最右,也就是从out=0到nElems-1,out是下标,
 * 然后是内循环,从已排定的数的右边开始选最小排到已排定的数的右边,
 * 假定min=out,则从右边开始out+1,一直到最后一个数结尾,out为0的时候,要比较nElems-1次,即从1到nElems-1,即in=out+1到in<nElems
 * 比较到a[nElems-1]时就可以确定a[min],之后将a[out]与a[min]进行交换,如果恰好out一开始就是min也没关系,min是一个媒介,可以与out相等
 * 只不过min第一个赋值可以为out而已,也就是不断将min(初始是out的值)与out之后的值进行比较比较之后的较小的值放进min里
 * 
 * @author jiangcheng-wb
 *
 */
public class ArraySel {

	private long[] a; //ref of array a
	private int nElems; //number of data items
	
	//..................................................................
	
	public ArraySel(int max) //constructor
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
	public void selectionSort()
	{
		int out,in,min;
		
		for (out = 0; out < nElems-1; out++) 
		{
			min = out;
			for(in=out+1; in<nElems; in++){
				
				if(a[in] < a[min]){
					min = in;
				}
			}
			swap(out,min);
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
