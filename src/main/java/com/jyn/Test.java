package com.jyn;


public class Test {
	public static void main(String[] args) {
		int[] ds = {100,90,80,70};
		int count = 0;
		for(int d : ds){
			count += getTotalHeight(d, d);
		}
		System.out.println(count);
	}
	
	private static int getTotalHeight(int height, int total){
		int _h = height >> 1;
		if(_h <= 0){
			return total;
		}
		return getTotalHeight(_h, total+(_h<<1));
	}
	
}