package com.test;

import java.util.Arrays;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		m(3);
		
	}
	public static void m(int i) {
		 if (i <= 1) { 
		      System.out.println("1*1=1 "); 
		    } else {
		      m(i - 1); 
		      for (int j = 1; j <= i; j++) { 
		        System.out.print(j + "*" + i + "=" + j * i + " "); 
		      } 
		      System.out.println(); 
		    } 
		  }  
}
