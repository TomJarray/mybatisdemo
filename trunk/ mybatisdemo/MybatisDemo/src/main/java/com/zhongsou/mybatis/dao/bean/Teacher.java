package com.zhongsou.mybatis.dao.bean;

import java.util.List;

public class Teacher extends PO {
	private Long teacher_id;
	private String teacher_name;
	private Integer teacher_type;
	
	private List<Schedule> schedules;
	
	public Long getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(Long teacherId) {
		teacher_id = teacherId;
	}
	public String getTeacher_name() {
		return teacher_name;
	}
	public void setTeacher_name(String teacherName) {
		teacher_name = teacherName;
	}
	public Integer getTeacher_type() {
		return teacher_type;
	}
	public void setTeacher_type(Integer teacherType) {
		teacher_type = teacherType;
	}
	
	public List<Schedule> getSchedules() {
		return schedules;
	}
	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}
	
}
