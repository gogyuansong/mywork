package com.myproject.dao.dynamic;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DynamicDao {
	
	public List<Map<String,Object>> test(@Param("map") Map<String,Object> map);
}
