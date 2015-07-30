package com.jyn.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class InputDemo {
	public static void main(String[] args) throws IOException {
		// typeOne();
		// typeTwo();
		typeThree();
	}

	public static void typeOne() throws IOException {
		System.out.print("Enter a Char:");
		char i = (char) System.in.read();
		System.out.println("your char is :" + i);
	}

	public static void typeTwo() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = null;
		System.out.println("Enter your value:");
		str = br.readLine();
		System.out.println("your value is :" + str);
	}

	public static void typeThree() {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入你的姓名：");
		String name = sc.nextLine();
		System.out.println("请输入你的年龄：");
		int age = sc.nextInt();
		System.out.println("请输入你的工资：");
		float salary = sc.nextFloat();
		System.out.println("你的信息如下：");
		System.out.println("姓名：" + name + "\n" + "年龄：" + age + "\n" + "工资："
				+ salary);
	}

}
