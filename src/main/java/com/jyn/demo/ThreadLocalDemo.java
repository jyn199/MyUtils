package com.jyn.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadLocalDemo {
	static Integer count = 0;
	//没用，具体计算还是在线程中计算的，然后更新主线程中的数据，所以还是有时间差的。
	static volatile Integer volatileCount = 0;
	
	static ExecutorService executor = Executors.newFixedThreadPool(3);
	
	public static void main(String[] args) {
		System.out.println(count);
		System.out.println(volatileCount);
		List<Future<String>> fs = new ArrayList<Future<String>>();
		for(int i = 0; i < 10000; i++){
			fs.add(executor.submit(new MyCallable(0)));
		}
//		List<Runnable> r = executor.shutdownNow();
//		System.out.println("停止数：" + r.size());
		
//		try {
//			System.out.println(fs.get(0).get());
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			e.printStackTrace();
//		}

		executor.shutdown();
		for(Future<String> f : fs){
			try {
				f.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		System.out.println(count);
		System.out.println(volatileCount);
	}
	
	
	static class MyCallable implements Callable<String>{
		static ThreadLocal<Integer> localCountMyCallable = new ThreadLocal<Integer>();
		
		public MyCallable(Integer count){
			// 由于线程可能重复利用，所以需要在新创建的时候初始化ThreadLocal变量
			localCountMyCallable.set(count);
		}
		
		public String call() throws Exception {
			count++;
			volatileCount++;
			// 防止异常
			if(localCountMyCallable.get() == null){
				localCountMyCallable.set(1);
			}
			localCountMyCallable.set(localCountMyCallable.get()+1);
//			System.out.println(Thread.currentThread().getId() + ":localCountMyCallable:" + localCountMyCallable.get());
//			Thread.sleep(10 * 1000);
			
			// Thread会重复利用，ThreadLocal对应的value还是存在强引用，所以不移除会出现内存溢出
			localCountMyCallable.remove();
			return null;
		}
	}
	
}


