package com.jyn.demo;

import java.util.concurrent.atomic.AtomicInteger;

public class WaitNotifyDemo {
	public static void main(String argv[]) {
		
		AtomicInteger synObj = new AtomicInteger(0);
		
		TestPrint a = new TestPrint(synObj, "A", 0);
		TestPrint b = new TestPrint(synObj, "B", 1);
		TestPrint c = new TestPrint(synObj, "C", 2);
		
		b.start();
		a.start();
		c.start();
	}
}

class TestPrint extends Thread {
	
	private AtomicInteger synObj;
	private String name;
	private int flag;
	
	private int count = 0;
	
	public TestPrint(AtomicInteger synObj, String name, int flag) {
		this.synObj = synObj;
		this.name = name;
		this.flag = flag;
	}
	
	@Override
	public void run() {
		while (true) {
			synchronized (synObj) {
				if(synObj.get() % 3 == flag){
					synObj.set(synObj.get() + 1);
					System.out.print(name);
					count++;
					synObj.notify();
					if(count == 10)
						break;
				}else{
					try {
						synObj.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}