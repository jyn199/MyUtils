package com.jyn.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ToHtml {
	public static String ToHTML(String text) {
		String tmp = text.replaceAll("\r\n", "<br/>\r\n");
		return tmp;
	}
	
	public static void main(String[] args) throws IOException {
//		byte[] buffer = new byte[1024];
//		int len = System.in.read(buffer);
//		System.out.println(len);
		
		Scanner s = new Scanner(System.in);
		s.useDelimiter(" ");
		while(s.hasNext()){
			System.out.println(ToHTML(s.next()));
		}
		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		ToHTML("test");
	}
}
