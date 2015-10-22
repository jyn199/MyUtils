package com.jyn.demo;

public class CompareStringDemo {
	public static void main(String[] args) {
		String a = "123";
		String b = "1" + new String("23");
		String c = "12" + "3";
		
		System.out.println(a == b);
		System.out.println(a == c);
	}
}
