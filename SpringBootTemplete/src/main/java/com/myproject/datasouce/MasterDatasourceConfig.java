package com.myproject.datasouce;



import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
@Configuration
@MapperScan(basePackages="com.myproject.dao.master", sqlSessionFactoryRef="masterDatasourceFactory")
@PropertySources(@PropertySource("classpath:datasource/master/master.properties"))
public class MasterDatasourceConfig{

	@Value("${master.datasource.url}")
	private String url;
	
	@Value("${master.datasource.username}")	
	private String userName;
	
	@Value("${master.datasource.password}")	
	private String password;
	
	@Value("${master.datasource.driver}")	
	private String  driver;
	
	@Bean(name = "masterDataSource")
    @Primary
    public DataSource getDataSource() {  
        DruidDataSource datasource = new DruidDataSource();  
        System.out.println("master");
        System.out.println(url);
        System.out.println(driver);
        System.out.println(userName);
        System.out.println(password);
        datasource.setUrl(url);  
        datasource.setDriverClassName(driver);  
        datasource.setUsername(userName);  
        datasource.setPassword(password);  
        datasource.setInitialSize(1);  
        datasource.setMinIdle(1);  
        datasource.setMaxWait(60000);  
        datasource.setMaxActive(20);  
        datasource.setMinEvictableIdleTimeMillis(300000);  
        datasource.setValidationQuery("SELECT 'x' FROM DUAL");
        try {  
            datasource.setFilters("stat,wall");  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return datasource;  
    }
   
   @Bean(name="masterTransactionManager")
   @Primary
   public DataSourceTransactionManager masterTransactionManager(){
	   return new DataSourceTransactionManager(getDataSource());
   }
   
   @Bean(name="masterDatasourceFactory")
   @Primary
   public SqlSessionFactory masterDatasourceFactory(DataSource masterDataSource) throws Exception{
	   SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
	   bean.setDataSource(masterDataSource);
	   bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/master/*Mapper.xml"));
	   return bean.getObject();
   }
   
/*    @Bean  
    public ServletRegistrationBean druidServlet() {  
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();  
        servletRegistrationBean.setServlet(new StatViewServlet());  
        servletRegistrationBean.addUrlMappings("/druid/*");  
        Map<String, String> initParameters = new HashMap<String, String>();  
        // initParameters.put("loginUsername", "druid");// 用户名  
        // initParameters.put("loginPassword", "druid");// 密码  
        initParameters.put("resetEnable", "false");// 禁用HTML页面上的“Reset All”功能  
        initParameters.put("allow", "127.0.0.1"); // IP白名单 (没有配置或者为空，则允许所有访问)  
        // initParameters.put("deny", "192.168.20.38");// IP黑名单  
        // (存在共同时，deny优先于allow)  
        servletRegistrationBean.setInitParameters(initParameters);  
        return servletRegistrationBean;  
    }  
  */
/*    @Bean  
    public FilterRegistrationBean filterRegistrationBean() {  
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();  
        filterRegistrationBean.setFilter(new WebStatFilter());  
        filterRegistrationBean.addUrlPatterns("/*");  
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");  
        return filterRegistrationBean;  
    }  
  
    // 按照BeanId来拦截配置 用来bean的监控  
    @Bean(value = "druid-stat-interceptor")  
    public DruidStatInterceptor DruidStatInterceptor() {  
        DruidStatInterceptor druidStatInterceptor = new DruidStatInterceptor();  
        return druidStatInterceptor;  
    }  
  
    @Bean  
    public BeanNameAutoProxyCreator beanNameAutoProxyCreator() {  
        BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();  
        beanNameAutoProxyCreator.setProxyTargetClass(true);  
        // 设置要监控的bean的id  
        //beanNameAutoProxyCreator.setBeanNames("sysRoleMapper","loginController");  
        beanNameAutoProxyCreator.setInterceptorNames("druid-stat-interceptor");  
        return beanNameAutoProxyCreator;  
    }  */
}
