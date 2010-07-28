package com.zhongsou.mybatis.dao.bean;

public class Schedule {
	
	private Long schedule_id;
	private Long course_id;
	private Long teacher_id;
	private String address;
	
	private Course course;
	private Teacher teacher;
	
	public Long getSchedule_id() {
		return schedule_id;
	}
	public void setSchedule_id(Long scheduleId) {
		schedule_id = scheduleId;
	}
	public Long getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Long courseId) {
		course_id = courseId;
	}
	public Long getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(Long teacherId) {
		teacher_id = teacherId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
}
