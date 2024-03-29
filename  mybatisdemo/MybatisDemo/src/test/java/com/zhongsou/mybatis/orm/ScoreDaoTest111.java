package com.zhongsou.mybatis.orm;

import org.junit.Before;
import org.junit.Test;

import com.zhongsou.mybatis.BaseTest;
import com.zhongsou.mybatis.dao.ScoreDao;
import com.zhongsou.mybatis.dao.bean.POFactory;
import com.zhongsou.mybatis.dao.bean.Score;

public class ScoreDaoTest111 extends BaseTest {
private ScoreDao scoreDao;
	
	@Before
	public void init() throws Exception {
		super.init();
		scoreDao = (ScoreDao)ctx.getBean("scoreDao");
	}
	
	@Test
	public void insert() {
		try
		{
			Score score = new Score();
			score.setStudent_id(3L);
			score.setCourse_id(1L);
			score.setScore(100f);
			scoreDao.insert(score);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void update() {
		try
		{
			String s = 
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到到" +
"到到到到到到到到到道道";
			Score score = POFactory.getPoInstance(Score.class);
			
			score.setStudent_id(1L);
			score.setCourse_id(1L);
			score.setScore(0f);
//			score.setDesc1(s + s);
//			score.setDesc2(s + s);
//			score.setDesc3(s + s);

			scoreDao.update(score);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void delete() {
		try
		{
			Score score = new Score();
			score.setStudent_id(1L);
			score.setCourse_id(1L);
			scoreDao.delete(score);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
