package com.zhongsou.mybatis.orm;

import org.junit.Before;
import org.junit.Test;

import com.zhongsou.mybatis.BaseTest;
import com.zhongsou.mybatis.dao.PageStudentDao;

public class PageStudentDaoTest extends BaseTest {
	private PageStudentDao pageStudentDao;

	@Before
	public void init() throws Exception {
		super.init();
		pageStudentDao = (PageStudentDao)ctx.getBean("pageStudentDao");
	}

	@Test
	public void getStudents() {
		pageStudentDao.getStudents();
	}
}
