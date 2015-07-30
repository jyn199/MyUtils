package com.jyn.demo;

import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMapDemo {
    static Map wMap = new WeakHashMap();
    public static void init(){
        wMap.put("1", "ding");
        wMap.put("2", "job");
        wMap.put(null, null);
    }
    public static void testWeakHashMap(){

        System.out.println("first get:"+wMap.get("1"));
        System.gc();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("next get:"+wMap.get("1"));
    }
    public static void main(String[] args) {
    	init();
        testWeakHashMap();
    }
}