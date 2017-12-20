package com.myproject.utils;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class JsonUtil {
   
   public static void main(String[] args) {
	   BeanTest  beanTest = new BeanTest();
	   beanTest.setTest("hha");
	   System.out.println(new Gson().toJson(beanTest));
}
   
   
	public static class BeanTest{
		
		//通过这个注解可以在接送转换的时候切换成员变量，这样的成员变量也可以用到request的请求对象中
		@SerializedName("TEST")
		private String test;

		public String getTest() {
			return test;
		}

		public void setTest(String test) {
			this.test = test;
		}
	}
}
