package com.titanic.zconfiguration;

import java.beans.PropertyVetoException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 基于注解的spring配置
 * mysql数据库spring配置
 * @author titanic
 *
 */
@Configuration
public class MySqlConfiguration
{

	 @Value("${mysql.user}")
	private String user ;
	 
	 @Value("${mysql.password}")
	private String password ;
	 
	 @Value("${mysql.driver}")
	private String driver  ;
	 
	 @Value("${mysql.dbcUrl}")
	private String jdbcUrl  ;
	 
	 @Value("${mysql.initPoolSize}")
	private int initPoolSize ;
	 
	 @Value("${mysql.macPoolSize}")
	private int macPoolSize ;
	 
	 @Value("${mysql.minPoolSize}")
	 private int minPoolSize;
	 
	 @Value("${mysql.maxPoolSize}")
	 private int maxPoolSize;
	 
	 @Value("${mysql.initialPoolSize}")
	 private int initialPoolSize;

	 @Value("${mysql.idleConnectionTestPeriod}")
	 private int idleConnectionTestPeriod;
	 
	 
	/**
	 * mysql数据源spring配置
	 * 
	 * @author titanic
	 *
	 */
	@Bean
	public ComboPooledDataSource dataSource()
	{

		ComboPooledDataSource data = new ComboPooledDataSource();

		data.setUser(user);
		data.setPassword(password);
		data.setJdbcUrl(jdbcUrl);
		data.setInitialPoolSize(initPoolSize);
		data.setMaxPoolSize(macPoolSize);
		data.setMinPoolSize(minPoolSize);
		data.setMaxPoolSize(maxPoolSize);
		data.setInitialPoolSize(initialPoolSize);
		data.setIdleConnectionTestPeriod(idleConnectionTestPeriod);
		
		try
		{
			data.setDriverClass(driver);
		} catch (PropertyVetoException e)
		{
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * JdbcTemplate
	 * 
	 * @return
	 */
	@Bean
	public JdbcTemplate JdbcTemplate()
	{
		JdbcTemplate JdbcTemplate = new JdbcTemplate();
		JdbcTemplate.setDataSource(dataSource());
		return JdbcTemplate;
	}

	/**
	 * mysql事物管理器
	 * 
	 * @author titanic
	 *
	 */
	@Bean
	public DataSourceTransactionManager transactionManager()
	{
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(dataSource());
		return transactionManager;
	}
}
