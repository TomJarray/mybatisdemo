package com.zhongsou.mybatis.dao.bean;

import java.io.Serializable;

public class Score extends PO implements Serializable{

	private static final long serialVersionUID = -3090696155529216492L;
	
	private Long student_id;
	private Long course_id;
	private Float score;
	private String desc1;
	private String desc2;
	private String desc3;
	
	public Long getStudent_id() {
		return student_id;
	}
	public void setStudent_id(Long studentId) {
		student_id = studentId;
	}
	public Long getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Long courseId) {
		course_id = courseId;
	}
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	public String getDesc1() {
		return desc1;
	}
	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}
	public String getDesc2() {
		return desc2;
	}
	public void setDesc2(String desc2) {
		this.desc2 = desc2;
	}
	public String getDesc3() {
		return desc3;
	}
	public void setDesc3(String desc3) {
		this.desc3 = desc3;
	}
	
}
