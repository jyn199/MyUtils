package com.jyn.demo;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;

import sun.misc.Unsafe;

public class UnSafeDemo {
	public static void main(String[] args) throws Exception {

		clearPassword("!@#123!@#");

		System.out.println(sizeOf("1"));
		byte[] i = new byte[1024 * 1024 * 1536];
		System.out.println(i.length);
	}

	public static void clearPassword(String pw) throws Exception {
		String fake = new String(pw.replaceAll(".", "?"));
		System.out.println(pw);
		System.out.println(fake);

		Unsafe unsafe = getUnsafe();
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
