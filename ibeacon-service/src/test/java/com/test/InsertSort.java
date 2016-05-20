package com.test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class InsertSort {
	/**
	 * 在要排序的一组数中，假设前面(n-1)[n>=2] 个数已经是排

好顺序的，现在要把第n个数插到前面的有序数中，使得这n个数

也是排好顺序的。如此反复循环，直到全部排好顺序。
	 */
	public  InsertSort() {
		int a[]={49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,56,17,18,23,34,15,35,25,53,51};  
		int temp=0;  
		for(int i=1;i<a.length;i++){  
		   int j=i-1;  
		   temp=a[i];  
		   for(;j>=0&&temp<a[j];j--){  
		       a[j+1]=a[j];  //将大于temp的值整体后移一个单位  
		   }  
		   a[j+1]=temp;  
		}  

//		for(int i=0;i<a.length;i++){  
//		   System.out.println(a[i]);  
//		}  
	}
	/**
	 * 算法先将要排序的一组数按某个增量d（n/2,n为要排序数的个数）分成若干组，
	 * 每组中记录的下标相差d.对每组中全部元素进行直接插入排序，
	 * 然后再用一个较小的增量（d/2）对它进行分组，在每组中再进行直接插入排序。
	 * 当增量减到1时，进行直接插入排序后，排序完成。
	 */
	public void shellSort(){
		int a[]={1,54,6,3,78,34,12,45,56,100};  
		double d1=a.length;  
		int temp=0;  
		while(true){  
		   d1= Math.ceil(d1/2);  
		   int d=(int) d1;  
		   for(int x=0;x<d;x++){  

		       for(int i=x+d;i<a.length;i+=d){  
		          int j=i-d;  
		          temp=a[i];  
		          System.out.println("i="+i+"j="+j);
		          for(;j>=0&&temp<a[j];j-=d){   
		               a[j+d]=a[j];  
		          }  
		          a[j+d]=temp;  
		       } 
		       System.out.println("-----------"); 
		   }  

		   if(d==1){  
		       break;  
		   }  
		}
		for(int i=0;i<a.length;i++){  
		   System.out.println(a[i]);  
		 
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InsertSort sor = new InsertSort();
		sor.shellSort();
		
	}
	public interface FunctionalDefaultMethods{
		void method();
		 void defaultMethod();
	}

}
