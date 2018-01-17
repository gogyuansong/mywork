package com.myproject.datasouce.dynamicDataSource;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.ResourceUtils;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.gson.Gson;
import com.myproject.utils.FileUtil;

@Configuration
@MapperScan(basePackages = "com.myproject.dao.dynamic", sqlSessionFactoryRef = "dynamicDatasourceFactory")
public class DynamicDatasourceConfig {

	private static Map<Object, Object> dynamicDataSourceMap = new HashMap<Object, Object>();

	@Bean(name = "dynamicDataSource")
	public DynamicDataSource getDataSource() {
		DynamicDataSource datasource = new DynamicDataSource();
		datasource.setDefaultTargetDataSource(getDataSourceList());
		datasource.setTargetDataSources(dynamicDataSourceMap);
		return datasource;
	}

	@Bean(name = "dynamicTransactionManager")
	public DataSourceTransactionManager dynamicTransactionManager(DynamicDataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "dynamicDatasourceFactory")
	public SqlSessionFactory dynamicDatasourceFactory(DynamicDataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("classpath:mapper/dynamic/*Mapper.xml"));
		return bean.getObject();
	}
	
	/**
	 * 动态增加数据源
	 * @param key
	 * @param value
	 * @param acc
	 */
	public void addDatasource(Object key, Object value, AnnotationConfigEmbeddedWebApplicationContext acc){
		dynamicDataSourceMap.put(key, value);
		AbstractRoutingDataSource sc = (AbstractRoutingDataSource) acc.getBean("dynamicDataSource");
		sc.setTargetDataSources(dynamicDataSourceMap);
		//获取第一个作为默认数据源
		sc.setDefaultTargetDataSource(dynamicDataSourceMap.values().toArray()[0]);
		sc.afterPropertiesSet();
		new DataSourceTransactionManager(sc);
	}
	
	/**
	 * 
	 * @param key
	 * @param acc
	 */
	public void deleteDatasource(Object key, AnnotationConfigEmbeddedWebApplicationContext acc){
		dynamicDataSourceMap.remove(key);
		AbstractRoutingDataSource sc = (AbstractRoutingDataSource) acc.getBean("dynamicDataSource");
		sc.setTargetDataSources(dynamicDataSourceMap);
		//获取第一个作为默认数据源
		sc.setDefaultTargetDataSource(dynamicDataSourceMap.values().toArray()[0]);
		sc.afterPropertiesSet();
		new DataSourceTransactionManager(sc);
	}

	@SuppressWarnings("unchecked")
	private DruidDataSource getDataSourceList() {
		try {
			File file = ResourceUtils.getFile("classpath:\\datasource\\dynamicDataSource\\nodes.json");
			DruidDataSource defaultDataSource = null;
			String jsonStr = FileUtil.readFile(file);
			Map<String, Object> dataSourceMap = new Gson().fromJson(jsonStr, Map.class);
			int i = 0;
			for (Entry<String, Object> entry : dataSourceMap.entrySet()) {
				Map<String, Object> node = (Map<String, Object>) entry.getValue();
				DruidDataSource datasource = new DruidDataSource();
				datasource.setUrl((String) node.get("url"));
				datasource.setDriverClassName((String) node.get("driver"));
				datasource.setUsername((String) node.get("userName"));
				datasource.setPassword((String) node.get("passWord"));
				datasource.setInitialSize(1);
				datasource.setMinIdle(1);
				datasource.setMaxWait(60000);
				datasource.setMaxActive(20);
				datasource.setMinEvictableIdleTimeMillis(300000);
				datasource.setValidationQuery("SELECT 'x' FROM DUAL");
				dynamicDataSourceMap.put(entry.getKey(), datasource);
				if (i == 0) {
					// 取第一个datasource当默认数据源
					defaultDataSource = datasource;
					i++;
				}
			}
			return defaultDataSource;
		} catch (Exception e) {
			return null;
		}
	}
}
