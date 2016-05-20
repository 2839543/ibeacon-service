package com.test;

import com.test.MulTable.MulTableEnum;

public class MulTable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MulTable mul = new MulTable();
        mul.showMulTable(9, MulTable.MulTableEnum.DESC);
        mul.showMulTable(9, MulTable.MulTableEnum.ASC);
	}
	 public enum MulTableEnum{
	        ASC,
	        DESC
	    }
	    public void showMulTable(final int count, MulTableEnum e){
	        if(MulTableEnum.ASC == e){
	            ascMulTable(count);
	        }else{
	            descMulTable(count);
	        }
	    }
	    private void descMulTable(final int count){
	        if(count<1){
	            i=0;
	            return;
	        }
	        if(i!=count){
	            i++;
	            System.out.print(i+"*"+count+"="+i*count+"\t");
	            descMulTable(count);
	        }else{
	            i=0;
	            System.out.println();
	            descMulTable(count-1);
	        }
	    }
	    private void ascMulTable(final int count){
	        if(j>count){
	            j=1;
	            return;
	        }
	        if(i!=j){
	            i++;
	            System.out.print(i+"*"+j+"="+i*j+"\t");
	        }else{
	            i=0;
	            j++;
	            System.out.println();
	        }
	        ascMulTable(count);
	    }   
	    private int i=0;
	    private int j=1;
}
