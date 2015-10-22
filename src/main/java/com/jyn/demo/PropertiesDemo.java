package com.jyn.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author yongnian.jiang
 * 
 */
public class PropertiesDemo {
	/*
	java.version Java 运行时版本 
	java.home Java 的安装目录 
	java.class.version Java 类格式的版本号 
	java.class.path Java 类的查找路径 
	java.io.tmpdir 默认的临时目录 
	java.compiler Java 所使用的及时编译器 
	java.ext.dirs Java 扩展包的目录 
	os.name 操作系统的名称 
	os.arch 操作系统的体系结构 
	os.version 操作系统的版本 
	file.separator 文件分隔符（Unix 下为'/'） 
	path.separator 路径分隔符（Unix 下为':'） 
	line.separator 换行符（Unix 下为'\n'） 
	user.name 用户帐号名 
	user.home 用户目录 
	user.dir 用户当前的工作目录 
	*/
	public static void main(String[] args) throws FileNotFoundException, IOException {
		System.out.println(System.getProperty("user.dir"));
		Properties p = System.getProperties();
		p.load(new FileInputStream(new File("test.properties")));
		System.out.println(p.get("app"));
	}
}
