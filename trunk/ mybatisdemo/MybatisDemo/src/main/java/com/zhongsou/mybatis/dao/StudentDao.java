package com.zhongsou.mybatis.dao;

import java.util.List;

import com.zhongsou.mybatis.bean.StudentInfo;
import com.zhongsou.mybatis.dao.bean.Score;
import com.zhongsou.mybatis.dao.bean.Student;

public interface StudentDao {
	Student findById(Long id);
	List<Student> getStudents();
	
	void insert(Student student);
	void update(Student student);
	void partialupdate(Student student);
	void delete(Long student_id);
	
	List<Score> getStudentScores1();
	List<Student> getStudentScores2();
	List<StudentInfo> getStudentScores3();
}
