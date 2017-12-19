package com.myproject.utils;

import org.springframework.beans.BeanUtils;

public class BeanUtil {

	
	//bean对象复制，两个对象可以是不同类的对象实例，但是两个对象中需要有同名的属性，然后这样就可以将同名的属性赋值个target的bean的同名属性
	public static void copyBean(Object beansource, Object beanTarget){
		BeanUtils.copyProperties(beansource, beanTarget);
	}
}
