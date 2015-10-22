package com.jyn.demo;

public class ForNameDemo {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
//		Class.forName("com.jyn.demo.ForNameClass", false, ForNameDemo.class.getClassLoader());
		ForNameDemo.class.getClassLoader().loadClass("com.jyn.demo.ForNameClass");
		ForNameDemo.class.getClassLoader().loadClass("com.jyn.demo.ForNameClass").newInstance();
	}
}
