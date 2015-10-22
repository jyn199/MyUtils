package com.jyn;


public class Test1 {
	
	private static final int N = 3;
	
	public static void main(String[] args) {
		int _t = N * 1 + 1;
		for(int i = 2; i <= N; i++){
			_t = (i) * _t  + 1;
		}
		System.out.println(_t);
	}
	
}