package org.springframework.orm.ibatis3.plugin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

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

public class PageListHelper {

	public static int getTotalCount(Configuration config, DataSource dataSource, String statement, Object parameter) {
		try {
			MappedStatement ms =config.getMappedStatement(statement);
			BoundSql boundSql = ms.getBoundSql(parameter);
			String sql = boundSql.getSql().trim();
			sql = "select count(*) as totalCount from (" + sql + ") as A";
	
			BoundSql newBoundSql = new BoundSql(config,sql, boundSql.getParameterMappings(), boundSql.getParameterObject());
			MappedStatement newMs = copyFromMappedStatement(ms, new BoundSqlSqlSource(newBoundSql));
			
			
			Executor executor = new SimpleExecutor(config, new JdbcTransaction(dataSource.getConnection(), false));
			List totalCountList = executor.query(newMs, parameter, RowBounds.DEFAULT, Executor.NO_RESULT_HANDLER);
			
			
			return Integer.parseInt(totalCountList.get(0).toString());
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	
	private static MappedStatement copyFromMappedStatement(MappedStatement ms,SqlSource newSqlSource) {
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
	
	private static List<ResultMap> getPageResultMaps(final Configuration config) {
		final TypeHandlerRegistry registry = config.getTypeHandlerRegistry();
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
