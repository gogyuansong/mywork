package com.myproject.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.Gson;
import com.myproject.service.TestService;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@RequestMapping("/test")
@EnableSwagger2
public class TestController {

	//swagger访问页面
	//http://localhost:8088/swagger-ui.html
	
	private static String propertiesValue01;
	
	@Autowired
	TestService testService;
	
	@ApiOperation(value="测试接口", notes="test01")
	@RequestMapping(value = "/test01", method = RequestMethod.POST)
	@ResponseBody
	public String test01(@RequestParam(required = true, name = "name", defaultValue = "test") String name,
			@RequestBody(required = true) String requestBoty) {
		//获取request
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		HttpServletRequest request = sra.getRequest();
		String s = request.getParameter("name");
		System.out.println(s);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("1", "莪的人分地方");
		map.put("2", "2");
		map.put("3", "2");
		map.put("4", "2");
		map.put("5", "2");
		map.put("6", "333");
		list.add(map);
		return new Gson().toJson(list);
	}
	
	@ApiOperation("dao测试")
	@RequestMapping(value = "/test02", method = RequestMethod.POST)
	public String test01(){
		List<Map<String,Object>> list = testService.test();
		return new Gson().toJson(list);
	}
	
/*	//通过value注解向静态成员变量中赋值，需要使用非静态的set方法
	@Value("${propertiesValue}")
	public void sePropertiesValue01(String propertiesValue01){
		this.propertiesValue01 = propertiesValue01;
	}*/
}
