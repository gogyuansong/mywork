package com.myproject.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import io.swagger.annotations.ApiOperation;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@RequestMapping("/test")
@EnableSwagger2
public class TestController {

	@ApiOperation("接口测试嘞01")
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
}
