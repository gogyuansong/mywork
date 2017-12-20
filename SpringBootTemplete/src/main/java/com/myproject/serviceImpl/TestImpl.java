package com.myproject.serviceImpl;

import org.springframework.transaction.annotation.Transactional;

import com.myproject.service.TestService;

public class TestImpl implements TestService{
	
	@Transactional
	@Override
	public void test() {
		// TODO Auto-generated method stub
	}
}
