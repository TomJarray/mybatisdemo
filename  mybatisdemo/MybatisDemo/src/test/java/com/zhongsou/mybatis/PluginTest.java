package com.zhongsou.mybatis;

import java.io.IOException;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.orm.ibatis3.SqlSessionFactoryBean;

public class PluginTest extends BaseTest{
	private SqlSessionFactory sqlSessionFactory;
	private SqlSessionFactoryBean sqlSessionFactoryBean;
	
	@Before
	public void init() throws Exception {
		super.init();
		sqlSessionFactoryBean = (SqlSessionFactoryBean)ctx.getBean("sqlSessionFactory");
		sqlSessionFactory = sqlSessionFactoryBean.buildSqlSessionFactory();
	}
	
	@Test
	public void ss() {
		
			SqlSession sqlSession = sqlSessionFactory.openSession();
			RowBounds rowbounds = new RowBounds(1, 2);
			sqlSession.selectList("com.zhongsou.mybatis.dao.StudentDao.getStudents", null, rowbounds);
			String s = "";
		
		
	}
}
