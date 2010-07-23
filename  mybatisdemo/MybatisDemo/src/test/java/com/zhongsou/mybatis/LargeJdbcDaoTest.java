package com.zhongsou.mybatis;

import org.junit.Test;

public class LargeJdbcDaoTest {

	@Test
	public void insertlob() {
		LargeJdbcDao.insertLob();
	}
	
	@Test
	public void selectlob() {
		LargeJdbcDao.selectLob();
	}
}
