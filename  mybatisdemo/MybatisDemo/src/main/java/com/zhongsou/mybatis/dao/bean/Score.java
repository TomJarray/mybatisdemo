package com.zhongsou.mybatis.dao.bean;

import java.io.Serializable;

public class Score extends PO implements Serializable{

	private static final long serialVersionUID = -3090696155529216492L;
	
	private Long score_id;
	private Long student_id;
	private Long course_id;
	private Float score;
	
	private Student student;
	private Course course;
	
	public Long getScore_id() {
		return score_id;
	}
	public void setScore_id(Long score_id) {
		this.score_id = score_id;
	}
	public Long getStudent_id() {
		return student_id;
	}
	public void setStudent_id(Long student_id) {
		this.student_id = student_id;
	}
	public Long getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Long course_id) {
		this.course_id = course_id;
	}
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
}
