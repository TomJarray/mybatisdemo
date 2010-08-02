package com.zhongsou.mybatis.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.SimpleExecutor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.orm.ibatis3.plugin.OffsetLimitInterceptor.BoundSqlSqlSource;
import org.springframework.orm.ibatis3.support.SqlSessionDaoSupport;

import com.zhongsou.mybatis.Page;
import com.zhongsou.mybatis.dao.bean.Student;


public class PageStudentDao extends SqlSessionDaoSupport{


	public List<Student> getStudents() {
//		List<Student> students = this.getSqlSessionTemplate().selectList("com.zhongsou.mybatis.dao.PageStudentDao.getStudents");
		
		// 获取sql语句，查询totalCount
//		this.getSqlSessionTemplate().
		// 产生Limit对象
		Configuration config = this.getSqlSessionFactory().getConfiguration();
		MappedStatement ms =config.getMappedStatement("com.zhongsou.mybatis.dao.PageStudentDao.getStudents");
		BoundSql boundSql = ms.getBoundSql(null);
		String sql = boundSql.getSql().trim();
		sql = "select count(*) as totalCount from (" + sql + ") as A";

		BoundSql newBoundSql = new BoundSql(config,sql, boundSql.getParameterMappings(), boundSql.getParameterObject());
		MappedStatement newMs = copyFromMappedStatement(ms, new BoundSqlSqlSource(newBoundSql));
		
//		ResultHandler resultHandler = new DefaultResultHandler();
		Executor executor;
		try {
			
			Page page = new Page();
			executor = new SimpleExecutor(config, new JdbcTransaction(this.getDataSource().getConnection(), false));
			List totalCounts = executor.query(newMs, null, RowBounds.DEFAULT, Executor.NO_RESULT_HANDLER);
			String s = "";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 执行queryList
		
		return null;
	}
	
//	private class My {
//		private Integer id;
//
//		public Integer getId() {
//			return id;
//		}
//
//		public void setId(Integer id) {
//			this.id = id;
//		}
//
//		
//		
//	}
	
	private MappedStatement copyFromMappedStatement(MappedStatement ms,SqlSource newSqlSource) {
		Builder builder = new MappedStatement.Builder(ms.getConfiguration(),ms.getId(),newSqlSource,ms.getSqlCommandType());
		
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		builder.keyProperty(ms.getKeyProperty());
		
		//setStatementTimeout()
		builder.timeout(ms.getTimeout());
		
		//setStatementResultMap()
		builder.parameterMap(ms.getParameterMap());
		
		//setStatementResultMap()
//		builder.resultMaps(ms.getResultMaps());
		builder.resultMaps(getPageResultMaps(ms.getConfiguration()));
		builder.resultSetType(ms.getResultSetType());
		
		//setStatementCache()
		builder.cache(ms.getCache());
		builder.flushCacheRequired(ms.isFlushCacheRequired());
		builder.useCache(ms.isUseCache());
		
		return builder.build();
	}
	
	private List<ResultMap> getPageResultMaps(final Configuration config) {
		
		final TypeHandlerRegistry registry = config.getTypeHandlerRegistry();
//		final List<ResultMapping> resultMappings = new ArrayList<ResultMapping>() { {
//			add(new ResultMapping.Builder(config, "totalCount", "totalCount", registry.getTypeHandler(int.class)).build());
//		} };
//		
//	    final ResultMap resultMap = new ResultMap.Builder(config, "default", Page.class, resultMappings).build();
	    
	    List<ResultMap> resultMaps = new ArrayList<ResultMap>() { 
	    	{
		    	add(new ResultMap.Builder(config, "defaultResultMap",  int.class, new ArrayList<ResultMapping>() {
		    		{
		    			add(new ResultMapping.Builder(config, "totalCount", "totalCount", registry.getTypeHandler(int.class)).build());
		    		}
		    	}).build());
	    	}
	    };
	    
	    return resultMaps;
	}
	
}
