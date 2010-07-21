package com.zhongsou.mybatis;

import junit.framework.Assert;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseTest {
	
	protected static ApplicationContext ctx;
	
	@Before
	public void init() throws Exception{
		try{
			String[] locations = {"config/applicationContext*.xml"};
			ctx = new ClassPathXmlApplicationContext(locations);
		}catch(Exception e){
			e.printStackTrace();
			Assert.fail();
		}
	}
}
