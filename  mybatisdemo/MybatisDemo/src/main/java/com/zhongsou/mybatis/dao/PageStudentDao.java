package com.zhongsou.mybatis.dao;

import java.util.List;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.orm.ibatis3.support.SqlSessionDaoSupport;

import com.zhongsou.mybatis.dao.bean.Student;

public class PageStudentDao extends SqlSessionDaoSupport{


	public List<Student> getStudents() {
//		List<Student> students = this.getSqlSessionTemplate().selectList("com.zhongsou.mybatis.dao.PageStudentDao.getStudents");
		
		// 获取sql语句，查询totalCount
//		this.getSqlSessionTemplate().
		// 产生Limit对象
		Configuration config = this.getSqlSessionFactory().getConfiguration();
		MappedStatement ms =config.getMappedStatement("com.zhongsou.mybatis.dao.PageStudentDao.getStudents");
		
		
//		MappedStatement ms = new MappedStatement.Builder(config, "com.zhongsou.mybatis.dao.PageStudentDao.getStudents", null, SqlCommandType.SELECT).build();
		
		
		
		BoundSql bs = ms.getBoundSql(null);
		String sql = bs.getSql();
		// 执行queryList
		
		return null;
	}
}
