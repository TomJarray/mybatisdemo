package com.zhongsou.mybatis;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.orm.ibatis3.SqlSessionTemplate;

import com.zhongsou.mybatis.dao.StudentDao;
import com.zhongsou.mybatis.dao.TeacherDao;
import com.zhongsou.mybatis.dao.bean.Student;

public class PluginTest extends BaseTest{
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Before
	public void init() throws Exception {
		super.init();
		sqlSessionTemplate = (SqlSessionTemplate)ctx.getBean("sqlSessionTemplate");
		
	}
	
	@Test
	public void ss() {
			
			RowBounds rowbounds = new RowBounds(0, 5);
			List<Student> students = sqlSessionTemplate.selectList("com.zhongsou.mybatis.dao.StudentDao.getStudents", null, rowbounds);
			String s = "";
		
		
	}
	
	@Test
	public void DaoMapper() {
//		SqlSession session = sqlSessionFactory.openSession();
//		StudentDao studentDao = session.getMapper(StudentDao.class);
//		Student student = studentDao.findById(1L);
		TeacherDao teacherDao = sqlSessionTemplate.getMapper(TeacherDao.class);
		
		String string = "";
	}
}
