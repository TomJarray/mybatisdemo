package com.zhongsou.mybatis;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;

import com.zhongsou.mybatis.dao.StudentDao;
import com.zhongsou.mybatis.dao.bean.Student;

public class PluginTest extends BaseTest{
	private SqlSessionFactory sqlSessionFactory;
	
	@Before
	public void init() throws Exception {
		super.init();
		sqlSessionFactory = (SqlSessionFactory)ctx.getBean("sqlSessionFactory");
		
	}
	
	@Test
	public void ss() {
		
			SqlSession sqlSession = sqlSessionFactory.openSession();
			RowBounds rowbounds = new RowBounds(0, 5);
			List<Student> students = sqlSession.selectList("com.zhongsou.mybatis.dao.StudentDao.getStudents", null, rowbounds);
			String s = "";
		
		
	}
	
	@Test
	public void DaoMapper() {
		SqlSession session = sqlSessionFactory.openSession();
		StudentDao studentDao = session.getMapper(StudentDao.class);
		Student student = studentDao.findById(1L);
		String string = "";
	}
}
