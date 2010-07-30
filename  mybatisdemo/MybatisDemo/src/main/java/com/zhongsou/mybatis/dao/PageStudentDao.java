package com.zhongsou.mybatis.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.zhongsou.mybatis.dao.bean.Student;

public class PageStudentDao {
	private SqlSessionFactory sqlSessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	public List<Student> getStudents() {
		SqlSession session = sqlSessionFactory.openSession();
		
		// 获取sql语句，查询totalCount
		
		// 产生Limit对象
		
		// 执行queryList
		
		return null;
	}
}
