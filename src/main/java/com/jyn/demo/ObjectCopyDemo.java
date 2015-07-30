package com.jyn.demo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable;

public class ObjectCopyDemo {
	
	public static void main(String[] args) throws OptionalDataException, IOException, ClassNotFoundException {
		SubObjectDemo sod = new SubObjectDemo("sub");
		ObjectDemo od = new ObjectDemo("demo", 1, sod);
		ObjectDemo od1 = (ObjectDemo)od.deepClone();
		od1.age = 2;
		od1.name = "demo1";
		od1.sod.sub = "sub1";
		System.out.println("name="+od.name+","+"age="+od.age+",sub="+od.sod.sub);
		System.out.println("name="+od1.name+","+"age="+od1.age+",sub="+od1.sod.sub);
	}

}

class ObjectDemo implements Serializable{
	String name;
	int age;
	SubObjectDemo sod;

	ObjectDemo(String name, int age, SubObjectDemo sod) {
		this.name = name;
		this.age = age;
		this.sod = sod;
	}

	public Object deepClone() throws IOException, OptionalDataException,
			ClassNotFoundException {
		// 将对象写到流里
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(this);
		// 从流里读出来
		ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
		ObjectInputStream oi = new ObjectInputStream(bi);
		return (oi.readObject());
	}
}

class SubObjectDemo implements Serializable{
	String sub;
	SubObjectDemo(String sub){
		this.sub = sub;
	}
}
