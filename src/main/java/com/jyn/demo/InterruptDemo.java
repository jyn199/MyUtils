package com.jyn.demo;

/**
 * 进程
 * @author yongnian.jiang
 *
 */
class ATask implements Runnable {

	/*
	 * 中断方式两种：
	 * 1、当线程在sleep的时候：调用interrupt会产生InterruptedException
	 * 2、程序中增加Thread.interrupted()判断来确定退出循环
	 */
	private double d = 0.0;

	public void run() {
		try {
			while (!Thread.interrupted()) {
				System.out.println("I am running!");
				Thread.sleep(1000);
				for (int i = 0; i < 10; i++) {
					d = d + (Math.PI + Math.E) / d;
				}
				// 给线程调度器可以切换到其它进程的信号
				Thread.yield();
			}
		}catch (InterruptedException e) {  
            System.out.println("Exiting by Exception");  
        }
	}
}

public class InterruptDemo {

	public static void main(String[] args) throws Exception {
		// 将任务交给一个线程执行
		Thread t = new Thread(new ATask());
		t.start();

		// 运行一断时间中断线程
		Thread.sleep(10);
		System.out.println("****************************");
		System.out.println("Interrupted Thread!");
		System.out.println("****************************");
		t.interrupt();
	}
}
