package com.jyn.demo;

class A {
	static{
		System.out.println("A");
	}
	{
		System.out.println("A---");
	}
	public A(){
		System.out.println("A+++");
	}
}

public class StaticDemo extends A{
	static{
		System.out.println("StaticDemo");
	}
	{
		System.out.println("StaticDemo---");
	}
	public StaticDemo(){
		System.out.println("StaticDemo+++");
	}
	public static void main(String[] args) {
		System.out.println("begin");
		new StaticDemo();
		new StaticDemo();
		System.out.println("end");
	}
}
