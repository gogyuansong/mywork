package com.myproject.dao.master;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestDao {
	//test
	public List<Map<String,Object>> test(Map<String,Object> map);
}
