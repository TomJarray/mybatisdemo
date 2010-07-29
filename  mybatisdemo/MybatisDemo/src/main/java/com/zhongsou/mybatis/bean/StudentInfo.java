package com.zhongsou.mybatis.bean;

import com.zhongsou.mybatis.dao.bean.Student;

public class StudentInfo extends Student{
	private Float totalScore;

	public Float getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Float totalScore) {
		this.totalScore = totalScore;
	}
	
}
