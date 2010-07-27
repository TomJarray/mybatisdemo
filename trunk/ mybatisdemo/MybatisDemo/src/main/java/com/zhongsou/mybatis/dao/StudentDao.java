package com.zhongsou.mybatis.dao;

import com.zhongsou.mybatis.dao.bean.Student;

public interface StudentDao {
	Student findById(Long id);
	
	void insert(Student student);
	void update(Student student);
	void partialupdate(Student student);
	void delete(Long student_id);
}
