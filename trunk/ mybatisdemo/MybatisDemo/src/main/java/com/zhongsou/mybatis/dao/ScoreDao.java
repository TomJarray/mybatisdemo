package com.zhongsou.mybatis.dao;

import com.zhongsou.mybatis.dao.bean.Score;

public interface ScoreDao {
	
	void insert(Score score);
	void update(Score score);
	void delete(Score score);
}
