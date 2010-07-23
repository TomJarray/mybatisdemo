package com.zhongsou.mybatis.orm;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.zhongsou.mybatis.BaseTest;
import com.zhongsou.mybatis.dao.StudentDao;
import com.zhongsou.mybatis.dao.bean.POFactory;
import com.zhongsou.mybatis.dao.bean.Student;

public class StudentDaoTest extends BaseTest{
	private StudentDao studentDao;
	
	@Before
	public void init() throws Exception {
		super.init();
		studentDao = (StudentDao)ctx.getBean("studentDao");
	}
	
	@Test
	public void findById() {
		Long id = 5L;
		try
		{
			Student student = studentDao.findById(id);
			Assert.assertEquals("zhangsan", student.getStudent_name());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void insert() {
		try
		{
			Student student = new Student();
			student.setStudent_name("wangwu1");
			student.setStudent_age(null);
			student.setStudent_gender(1);
			studentDao.insert(student);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void update() {
		try
		{
			//Student student = studentDao.findById(5L);
			Student student = POFactory.getPoInstance(Student.class);
			
			student.setStudent_id(5L);
			student.setStudent_name("dddd");
			student.setStudent_age(null);
			//student.setStudent_gender(1);

			studentDao.update(student);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void delete() {
		try
		{
			studentDao.delete(6L);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}