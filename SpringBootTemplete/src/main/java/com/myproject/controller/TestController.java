package com.myproject.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@RequestMapping("/test")
@EnableSwagger2
public class TestController {
	
	@RequestMapping("/test01")
	public String test01(){
		return "test";
	}
}
