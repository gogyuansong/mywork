package com.myproject.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
	
	private static String propertiesValue01;
	
	
	//通过value注解向静态成员变量中赋值，需要使用非静态的set方法
	@Value("${propertiesValue}")
	public void sePropertiesValue01(String propertiesValue01){
		this.propertiesValue01 = propertiesValue01;
	}

}
