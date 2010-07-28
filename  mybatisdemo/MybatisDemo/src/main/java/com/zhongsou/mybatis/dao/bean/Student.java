package com.zhongsou.mybatis.dao.bean;

import java.util.List;

public class Student extends PO {
	
	private Long student_id;
	private String student_name;
	private Integer student_age;
	private Integer student_gender;
	
	private List<Score> scores;
	
	public Long getStudent_id() {
		return student_id;
	}
	public void setStudent_id(Long studentId) {
		student_id = studentId;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String studentName) {
		student_name = studentName;
	}
	public Integer getStudent_age() {
		return student_age;
	}
	public void setStudent_age(Integer studentAge) {
		student_age = studentAge;
	}
	public Integer getStudent_gender() {
		return student_gender;
	}
	public void setStudent_gender(Integer studentGender) {
		student_gender = studentGender;
	}
	
	
	public List<Score> getScores() {
		return scores;
	}
	public void setScores(List<Score> scores) {
		this.scores = scores;
	}
	
}
