package com.zhongsou.mybatis.orm;


import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.orm.ibatis3.plugin.Page;

import com.zhongsou.mybatis.BaseTest;
import com.zhongsou.mybatis.dao.SampleStudentDao;
import com.zhongsou.mybatis.dao.bean.POFactory;
import com.zhongsou.mybatis.dao.bean.Student;

public class SampleStudentDaoTest extends BaseTest{
	private SampleStudentDao sampleStudentDao;
	
	@Before
	public void init() throws Exception {
		super.init();
		sampleStudentDao = (SampleStudentDao)ctx.getBean("sampleStudentDao");
	}
	
	@Test
	public void findById() {
		Student student = sampleStudentDao.findById(10L);
		String s = "";
	}
	
	@Test
	public void getStudents() {
		List<Student> students = sampleStudentDao.getStudents();
		String string = "";
	}
	
	@Test
	public void insert() {
		Student student = new Student();
		student.setStudent_name("ddgdg");
		student.setStudent_age(1);
		student.setStudent_gender(0);
		
		sampleStudentDao.insert(student);
	}
	
	@Test
	public void update() {
		Student student = sampleStudentDao.findById(1L);
		student.setStudent_name("11");
		sampleStudentDao.update(student);
	}
	
	@Test
	public void particalUpdate() {
		Student student = POFactory.getPoInstance(Student.class);
		student.setStudent_id(1L);
		student.setStudent_age(11);
		sampleStudentDao.partialUpdate(student);
	}
	
	@Test
	public void deleteById() {
		sampleStudentDao.deleteById(30L);
	}
	
	@Test
	public void delete() {
		Student student = sampleStudentDao.findById(29L);
		sampleStudentDao.delete(student);
	}
	
	@Test
	public void pageList() {
		Page page = new Page();
		page.setPageNo(2);
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("student_age", 0);
		List<Student> students = sampleStudentDao.pageList(parameter, page);
		String string = "";
	}
	
}
