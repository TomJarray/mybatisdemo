package com.zhongsou.mybatis.orm;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.zhongsou.mybatis.BaseTest;
import com.zhongsou.mybatis.dao.StudentDao;
import com.zhongsou.mybatis.dao.bean.POFactory;
import com.zhongsou.mybatis.dao.bean.Score;
import com.zhongsou.mybatis.dao.bean.Student;

public class StudentDaoTest extends BaseTest{
	private StudentDao studentDao;
	private DataSourceTransactionManager dataSourceTransactionManager;
	
	@Before
	public void init() throws Exception {
		super.init();
		studentDao = (StudentDao)ctx.getBean("studentDao");
		dataSourceTransactionManager = (DataSourceTransactionManager)ctx.getBean("transactionManagerMysql01");
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
			Student student = studentDao.findById(1L);
			student.setStudent_name("dddd");
			studentDao.update(student);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void partialupdate() {
		try
		{
			Student student = POFactory.getPoInstance(Student.class);
			
			student.setStudent_id(3L);
			student.setStudent_name("dddd");
			student.setStudent_age(null);
			//student.setStudent_gender(1);

			studentDao.partialupdate(student);
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
	
	@Test
	public void getStudentScores1() {
		try {
			List<Score> scores = studentDao.getStudentScores1();
			String string = "Hello World";
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getStudentScores2() {
		try {
			List<Student> students = studentDao.getStudentScores2();
			String string = "Hello World";
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void trans() {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = dataSourceTransactionManager.getTransaction(def);
		
		try {
			Student student = POFactory.getPoInstance(Student.class);
			student.setStudent_id(1L);
			student.setStudent_age(55);
			studentDao.update(student);
			
			student.setStudent_gender(0);
			studentDao.update(student);
			
			dataSourceTransactionManager.commit(status);
		} catch(Exception e) {
			dataSourceTransactionManager.rollback(status);
		}
	}

}
