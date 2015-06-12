package com.titanic.zconfiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.Mongo;

/**
 * 基于注解的spring配置
 * mongo数据源spring配置
 * @author titanic
 *
 */
@Configuration
public class MongoConfiguration
{

	@Value("${mongo.username}")
	private String username ;
	
	@Value("${mongo.password}")
	private String password  ;
	
	@Value("${mongo.address}")
	private String address  ;
	
	@Value("${mongo.database}")
	private String database;
	
	 @Bean 
	public MongoDbFactory mongoDbFactory() throws Exception
	{
		//mongo的用户名密码为空
		UserCredentials userCredentials = new UserCredentials(username, password);
																										//数据库地址，和数据库名称
		return new SimpleMongoDbFactory(new Mongo(address), database, userCredentials);
	}

	 @Bean 
	public  MongoTemplate mongoTemplate() throws Exception
	{
		return new MongoTemplate(mongoDbFactory());
	}

	
	 
}
