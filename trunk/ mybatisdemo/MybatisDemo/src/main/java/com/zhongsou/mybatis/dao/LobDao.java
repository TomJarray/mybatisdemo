package com.zhongsou.mybatis.dao;

import com.zhongsou.mybatis.dao.bean.Lob;

public interface LobDao {
	Lob findById(Long id);
	
	void insert(Lob lob);
	void update(Lob lob);
	void partialupdate(Lob lob);
	void delete(Long id);
}
