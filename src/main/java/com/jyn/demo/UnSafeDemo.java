package com.jyn.demo;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;

import sun.misc.Unsafe;

public class UnSafeDemo {

	public static class Target {
		int intParam = 3;
		long longParam;
		String strParam;
		String strParam2;
	}

	public static void main(String[] args) throws Exception {

		clearPassword("!@#123!@#");

		System.out.println(sizeOf("1"));
		byte[] i = new byte[1024 * 1024 * 1536];
		System.out.println(i.length);
		
		printOffset();
		updateField();
	}

	public static void clearPassword(String pw) throws Exception {
		String fake = new String(pw.replaceAll(".", "?"));
		System.out.println(pw);
		System.out.println(fake);

		Unsafe unsafe = getUnsafe();
		
	}
	
	public static void updateField() throws Exception{
		Unsafe unsafe = getUnsafe();
		
		System.out.println(unsafe.allocateMemory(1) + "===");
		
		Class clazz = Target.class;  
		Target target = new Target();
        Field intFiled  =  clazz.getDeclaredField("intParam")  ;  
        int a=(Integer)intFiled.get(target ) ;  
        System.out.println("原始值是:"+a);  
        //intParam的字段偏移是12 原始值是3 我们要改为10  
        System.out.println(unsafe.compareAndSwapInt(target, 12, 3, 10));  
        int b=(Integer)intFiled.get(target) ;  
        System.out.println("改变之后的值是:"+b);  
  
        //这个时候已经改为10了,所以会返回false  
        System.out.println(unsafe.compareAndSwapInt(target, 12, 3, 10));  
	}
	
	public static void printOffset() throws Exception{
		Unsafe unsafe = getUnsafe();
		Class clazz = Target.class;  
        Field[] fields = clazz.getDeclaredFields();  
        System.out.println("fieldName:fieldOffset");  
        for (Field f : fields) {  
            // 获取属性偏移量，可以通过这个偏移量给属性设置  
            System.out.println(f.getName() + ":" + unsafe.objectFieldOffset(f));  
        }
	}

	public static long sizeOf(Object o) throws Exception {
		Unsafe u = getUnsafe();
		HashSet<Field> fields = new HashSet<Field>();
		Class c = o.getClass();
		while (c != Object.class) {
			for (Field f : c.getDeclaredFields()) {
				if ((f.getModifiers() & Modifier.STATIC) == 0) {
					fields.add(f);
				}
			}
			c = c.getSuperclass();
		}
		// get offset
		long maxSize = 0;
		for (Field f : fields) {
			long offset = u.objectFieldOffset(f);
			if (offset > maxSize) {
				maxSize = offset;
			}
		}
		return ((maxSize / 8) + 1) * 8; // padding
	}

	public static Unsafe getUnsafe() throws Exception {
		Field f = Unsafe.class.getDeclaredField("theUnsafe"); // Internal
																// reference
		f.setAccessible(true);
		Unsafe unsafe = (Unsafe) f.get(null);
		return unsafe;
	}

}
