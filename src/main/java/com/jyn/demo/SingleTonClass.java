package com.jyn.demo;

public class SingleTonClass {
	private static SingleTonClass instance = new SingleTonClass();

	private SingleTonClass() {
	}

	public static SingleTonClass getInstance() {
		return instance;
	}
}
