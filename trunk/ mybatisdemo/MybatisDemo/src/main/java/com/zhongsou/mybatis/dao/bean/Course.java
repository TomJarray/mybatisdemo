package com.zhongsou.mybatis.dao.bean;

import java.io.Serializable;
import java.util.List;

public class Course extends PO {

	private Long course_id;
	private String course_name;
	private String course_desc;
	private Integer course_type;
	
	private List<Score> scores;
	private List<Schedule> schedules;
	
	public Long getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Long course_id) {
		this.course_id = course_id;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getCourse_desc() {
		return course_desc;
	}
	public void setCourse_desc(String course_desc) {
		this.course_desc = course_desc;
	}
	public Integer getCourse_type() {
		return course_type;
	}
	public void setCourse_type(Integer course_type) {
		this.course_type = course_type;
	}
	
	public List<Score> getScores() {
		return scores;
	}
	public void setScores(List<Score> scores) {
		this.scores = scores;
	}
	public List<Schedule> getSchedules() {
		return schedules;
	}
	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}
	
}
