package com.jyn.demo;

public class ParentStaticDemo {
	public static void main(String[] args) {
		Child c = new Child();
	}
}

class Parent{
	static{
		System.out.println("Parent static");
	}
	{
		System.out.println("Parent");
	}
	public Parent(){
		System.out.println("Parent1");
	}
}

class Child extends Parent{
	static{
		System.out.println("Child static");
	}
	{
		System.out.println("Child");
	}
	public Child(){
		System.out.println("Child1");
	}
}