package com.jyn.demo;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
	static AtomicInteger ai = new AtomicInteger();
	public static void main(String[] args) {
		ai.decrementAndGet();
	}
}
