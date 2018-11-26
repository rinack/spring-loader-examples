package org.springtest.examples.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
	
	private static ApplicationContext factory;

	public static void main(String[] args) {
		
		System.out.println("现在开始初始化容器");
		factory = new ClassPathXmlApplicationContext("classpath:beans.xml");
		System.out.println("容器初始化成功");
		// 得到Preson，并使用
		Person person = factory.getBean("person", Person.class);
		System.out.println(person);

		System.out.println("现在开始关闭容器！");
		((ClassPathXmlApplicationContext) factory).registerShutdownHook();
		
	}
}
