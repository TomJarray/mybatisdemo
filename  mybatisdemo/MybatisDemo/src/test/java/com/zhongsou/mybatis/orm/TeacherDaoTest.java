package com.zhongsou.mybatis.orm;

import org.junit.Before;
import org.junit.Test;

import com.zhongsou.mybatis.BaseTest;
import com.zhongsou.mybatis.dao.TeacherDao;
import com.zhongsou.mybatis.dao.bean.Teacher;

public class TeacherDaoTest extends BaseTest {
	private TeacherDao teacherDao;
	
	@Before
	public void init() throws Exception {
		super.init();
		teacherDao = (TeacherDao)ctx.getBean("teacherDao");
	}
	
	@Test
	public void findById() {
		Long id = 3L;
		try
		{
			Teacher teacher = teacherDao.findById(id);
			String string = "";
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
