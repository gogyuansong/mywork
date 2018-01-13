package com.myproject.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myproject.dao.master.TestDao;
import com.myproject.service.TestService;
@Service
public class TestImpl implements TestService{
	
	@Autowired
	TestDao testDao;
	
	@Transactional
	@Override
	public List<Map<String,Object>> test(){
		return testDao.test(null);
	}
}
