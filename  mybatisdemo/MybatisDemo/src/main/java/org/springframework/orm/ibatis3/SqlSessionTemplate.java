package org.springframework.orm.ibatis3;

import org.springframework.jdbc.support.JdbcAccessor;

import javax.sql.DataSource;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import java.sql.SQLException;

import java.util.List;

import org.apache.ibatis.exceptions.IbatisException;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

import org.apache.ibatis.session.ExecutorType;

import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import org.springframework.util.Assert;
import org.springframework.dao.DataAccessException;
import org.springframework.util.ClassUtils;

/**
 * Helper class that simplifies data access via the iBatis
 * {@link org.apache.ibatis.session.SqlSession} API, converting checked SQLExceptions into unchecked
 * DataAccessExceptions, following the <code>org.springframework.dao</code> exception hierarchy.
 * Uses the same {@link org.springframework.jdbc.support.SQLExceptionTranslator} mechanism as
 * {@link org.springframework.jdbc.core.JdbcTemplate}.
 * 
 * <p>
 * The main method of this class executes a callback that implements a data access action.
 * Furthermore, this class provides numerous convenience methods that mirror
 * {@link org.apache.ibatis.session.SqlSession}'s execution methods.
 * 
 * <p>
 * It is generally recommended to use the convenience methods on this template for plain
 * query/insert/update/delete operations. However, for more complex operations like batch updates, a
 * custom SqlSessionCallback must be implemented, usually as anonymous inner class. For example:
 * 
 * <pre class="code">
 * getSqlSessionTemplate().execute(new SqlSessionCallback&lt;Object&gt;() {
 *     public Object doInSqlSession(SqlSession sqlSession) throws SQLException {
 *         sqlSession.getMapper(MyMapper.class).update(parameterObject);
 *         sqlSession.update(&quot;MyMapper.update&quot;, otherParameterObject);
 *         return null;
 *     }
 * }, ExecutorType.BATCH);
 * </pre>
 * 
 * The template needs a SqlSessionFactory to create SqlSessions, passed in via the
 * "sqlSessionFactory" property. A Spring context typically uses a {@link SqlSessionFactoryBean} to
 * build the SqlSessionFactory. The template can additionally be configured with a DataSource for
 * fetching Connections, although this is not necessary since a DataSource is specified for the
 * SqlSessionFactory itself (through configured Environment).
 * 
 * @author Putthibong Boonbong
 * @since 3.0
 * @see #execute
 * @see #setSqlSessionFactory(org.apache.ibatis.session.SqlSessionFactory)
 * @see SqlSessionFactoryBean#setDataSource
 * @see org.apache.ibatis.session.SqlSessionFactory#getConfiguration()
 * @see org.apache.ibatis.session.SqlSession
 * @see org.springframework.orm.ibatis3.SqlSessionOperations
 */
@SuppressWarnings("unchecked")
public class SqlSessionTemplate extends JdbcAccessor implements SqlSessionOperations {

    private SqlSessionFactory sqlSessionFactory;

    public SqlSessionTemplate() {}

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public SqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        setSqlSessionFactory(sqlSessionFactory);
    }

    /**
     * Sets the SqlSessionFactory this template will use when creating SqlSessions.
     */
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    /**
     * Returns either the DataSource explicitly set for this template of the one specified by the SqlSessionFactory's Environment.
     * 
     * @see org.apache.ibatis.mapping.Environment
     */
    public DataSource getDataSource() {
        DataSource ds = super.getDataSource();
        return (ds != null ? ds : this.sqlSessionFactory.getConfiguration().getEnvironment().getDataSource());
    }

    @Override
    public void afterPropertiesSet() {
        if (this.sqlSessionFactory == null) {
            throw new IllegalArgumentException("Property 'sqlSessionFactory' is required");
        }

        super.afterPropertiesSet();
    }

    public <T> T execute(SqlSessionCallback<T> action) throws DataAccessException {
        return execute(action, this.sqlSessionFactory.getConfiguration().getDefaultExecutorType());
    }

    /**
     * Execute the given data access action on a Executor.
     * 
     * @param action callback object that specifies the data access action
     * @param executorType SIMPLE, REUSE, BATCH
     * @return a result object returned by the action, or <code>null</code>
     * @throws DataAccessException in case of errors
     */
    public <T> T execute(SqlSessionCallback<T> action, ExecutorType executorType) throws DataAccessException {
        Assert.notNull(action, "Callback object must not be null");
        Assert.notNull(this.sqlSessionFactory, "No SqlSessionFactory specified");

        SqlSession sqlSession = SqlSessionUtils.getSqlSession(this.sqlSessionFactory, getDataSource(), executorType);

        try {
            return action.doInSqlSession(sqlSession);
        }
        catch (Throwable t) {
            throw wrapException(t);
        }
        finally {
            SqlSessionUtils.closeSqlSession(sqlSession, this.sqlSessionFactory);
        }
    }

    public Object selectOne(String statement) {
        return selectOne(statement, null);
    }

    public Object selectOne(final String statement, final Object parameter) {
        return execute(new SqlSessionCallback<Object>() {
            @Override
            public Object doInSqlSession(SqlSession sqlSession) {
                return sqlSession.selectOne(statement, parameter);
            }
        });
    }

    public List selectList(String statement) {
        return selectList(statement, null);
    }

    public List selectList(String statement, Object parameter) {
        return selectList(statement, parameter, RowBounds.DEFAULT);
    }

    public List selectList(final String statement, final Object parameter, final RowBounds rowBounds) {
        return execute(new SqlSessionCallback<List>() {
            @Override
            public List doInSqlSession(SqlSession sqlSession) {
                return sqlSession.selectList(statement, parameter, rowBounds);
            }
        });
    }

    public void select(String statement, Object parameter, ResultHandler handler) {
        select(statement, parameter, RowBounds.DEFAULT, handler);
    }

    public void select(final String statement, final Object parameter, final RowBounds rowBounds,
                       final ResultHandler handler) {
        execute(new SqlSessionCallback<Object>() {
            @Override
            public Object doInSqlSession(SqlSession sqlSession) {
                sqlSession.select(statement, parameter, rowBounds, handler);
                return null;
            }
        });
    }

    public int insert(String statement) {
        return insert(statement, null);
    }

    public int insert(final String statement, final Object parameter) {
        return execute(new SqlSessionCallback<Integer>() {
            @Override
            public Integer doInSqlSession(SqlSession sqlSession) {
                return sqlSession.insert(statement, parameter);
            }
        });
    }

    public int update(String statement) {
        return update(statement, null);
    }

    public int update(final String statement, final Object parameter) {
        return execute(new SqlSessionCallback<Integer>() {
            @Override
            public Integer doInSqlSession(SqlSession sqlSession) {
                return sqlSession.update(statement, parameter);
            }
        });
    }

    public int delete(String statement) {
        return delete(statement, null);
    }

    public int delete(final String statement, final Object parameter) {
        return execute(new SqlSessionCallback<Integer>() {
            @Override
            public Integer doInSqlSession(SqlSession sqlSession) {
                return sqlSession.delete(statement, parameter);
            }
        });
    }

    public <T> T getMapper(final Class<T> type) {
        return (T) java.lang.reflect.Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type},
                new InvocationHandler() {
                    @Override
                    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
                        return execute(new SqlSessionCallback<Object>() {
                            @Override
                            public Object doInSqlSession(SqlSession sqlSession) throws SQLException {
                                try {
                                    return method.invoke(sqlSession.getMapper(type), args);
                                }
                                catch (java.lang.reflect.InvocationTargetException e) {
                                    throw wrapException(e.getCause());
                                }
                                catch (Exception e) {
                                    throw new MybatisSystemException("SqlSession operation", e);
                                }
                            }
                        });
                    }
                });
    }

    public DataAccessException wrapException(Throwable t) {
        if (t instanceof IbatisException) {
            if (t.getCause() instanceof SQLException) {
                return getExceptionTranslator().translate("SqlSession operation", null, (SQLException) t.getCause());
            } else {
                return new MybatisSystemException("SqlSession operation", t);
            }
        } else if (t instanceof DataAccessException) {
            return (DataAccessException) t;
        } else {
            return new MybatisSystemException("SqlSession operation", t);
        }
    }

}
