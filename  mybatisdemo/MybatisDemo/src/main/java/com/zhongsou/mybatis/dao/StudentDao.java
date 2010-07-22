package com.zhongsou.mybatis.dao;

import java.util.Map;

import com.zhongsou.mybatis.dao.bean.Student;

public interface StudentDao {
	Student findById(Long id);
	
	void insert(Student student);
	void update(Student student);
	void delete(Long id);
}
