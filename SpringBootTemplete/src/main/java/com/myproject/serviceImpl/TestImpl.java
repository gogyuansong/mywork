package com.myproject.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myproject.dao.dynamic.DynamicDao;
import com.myproject.dao.master.TestDao;
import com.myproject.datasouce.dynamicDataSource.DynamicDataSourceContextHolder;
import com.myproject.datasouce.dynamicDataSource.DynamicDataSourceType;
import com.myproject.service.TestService;
@Service
public class TestImpl implements TestService{
	
	@Autowired
	TestDao testDao;
	
	@Autowired
	DynamicDao dtestDao;
	
	@Transactional
	@Override
	public List<Map<String,Object>> test(){
		return testDao.test(null);
	}
	
	@Override
	public List<Map<String,Object>> dynamicTest(){
		DynamicDataSourceContextHolder.setDataSourceType(DynamicDataSourceType.NODE_01);
		return dtestDao.test(null);
	}
}
