package com.zhongsou.mybatis.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.orm.ibatis3.plugin.Page;
import org.springframework.orm.ibatis3.plugin.PageListHelper;
import org.springframework.orm.ibatis3.support.SqlSessionDaoSupport;

import com.zhongsou.mybatis.dao.bean.Student;

public class SampleStudentDao extends SqlSessionDaoSupport{

	public Student findById(Long id) {
		return (Student) this.getSqlSessionTemplate().selectOne(getFullName(), id);
	}
	
	public List<Student> getStudents() {
		return this.getSqlSessionTemplate().selectList(getFullName());
	}
	
	public int insert(Student student) {
		return this.getSqlSessionTemplate().insert(getFullName(), student);
	}
	
	public int update(Student student) {
		return this.getSqlSessionTemplate().update(getFullName(), student);
	}
	
	public int partialUpdate(Student student) {
		return this.getSqlSessionTemplate().update(getFullName(), student);
	}
	
	public int deleteById(Long id) {
		return this.getSqlSessionTemplate().delete(getFullName(), id);
	}
	
	public int delete(Student student) {
		return this.getSqlSessionTemplate().delete(getFullName(), student);
	}
	
	public List<Student> pageList(Object parameter, Page page) {
		return pageList(getFullName(), parameter, page);
	}
	
}
