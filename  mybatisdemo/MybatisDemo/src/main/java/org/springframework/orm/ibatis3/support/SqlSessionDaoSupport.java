package org.springframework.orm.ibatis3.support;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.orm.ibatis3.SqlSessionTemplate;
import org.springframework.orm.ibatis3.plugin.Page;
import org.springframework.orm.ibatis3.plugin.PageListHelper;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.util.Assert;


import javax.sql.DataSource;

/**
 * Convenient super class for iBATIS SqlSession data access objects. In the usual case, all that a
 * DAO needs is an SqlSessionFactory. This class also supports passing in an SqlSessionTemplate if a
 * custom DataSource or ExceptionTranslator is needed for a specific DAO.
 * <p>
 * 
 * By default, each DAO gets its own SqlSessionTemplate which holds the SqlSessionFactory.
 * SqlSessionTemplate is thread safe, so a single instance cannot be shared by all DAOs; there
 * should also be a small memory savings by doing this. To support a shared template, this class has
 * a constructor that accepts an SqlSessionTemplate. This pattern can be used in Spring
 * configuration files as follows:
 * 
 * <pre class="code">
 *   <bean id="baseDAO" abstract="true" lazy-init="true">
 *     <constructor-arg>
 *       <bean class="org.springframework.orm.ibatis3.SqlSessionTemplate">
 *         <constructor-arg ref="sqlSessionFactory" />
 *       </bean>
 *     </constructor-arg>
 *   </bean>
 * 
 *   <bean id="testDao" parent="baseDAO" class="org.springframework.orm.ibatis3.support.SqlSessionDaoSupport" />
 * </pre>
 * 
 * @author Putthibong Boonbong
 * @since 3.0
 * @see #setSqlSessionFactory
 * @see #setSqlSessionTemplate
 * @see org.springframework.orm.ibatis3.SqlSessionTemplate
 * @see org.springframework.orm.ibatis3.SqlSessionTemplate#setExceptionTranslator
 */
public abstract class SqlSessionDaoSupport extends DaoSupport {

    private SqlSessionTemplate sessionTemplate;

    private boolean externalTemplate;

    public SqlSessionDaoSupport() {
        sessionTemplate = new SqlSessionTemplate();
        externalTemplate = false;
    }

    public SqlSessionDaoSupport(SqlSessionTemplate sessionTemplate) {
        this.sessionTemplate = sessionTemplate;
        externalTemplate = true;
    }

    /**
     * Set the JDBC DataSource to be used by this DAO. Not required: The SqlSessionFactory defines a
     * shared DataSource.
     * <p>
     * This is a no-op if an external SqlSessionTemplate has been set.
     * 
     * @see #setSqlSessionFactory
     */
    public final void setDataSource(DataSource dataSource) {
        if (!this.externalTemplate) {
            this.sessionTemplate.setDataSource(dataSource);
        }
    }

    /**
     * Return the JDBC DataSource used by this DAO.
     */
    public final DataSource getDataSource() {
        return this.sessionTemplate.getDataSource();
    }

    /**
     * Set the SqlSessionFactory to work with.
     * <p>
     * This is a no-op if an external SqlSessionTemplate has been set.
     * 
     * @see #setSqlSessionTemplate
     */
    @Autowired
    public final void setSqlSessionFactory(SqlSessionFactory sessionFactory) {
        if (!this.externalTemplate) {
            this.sessionTemplate.setSqlSessionFactory(sessionFactory);
        }
    }

    /**
     * Return the SqlSessionFactory that this DAO uses.
     */
    public final SqlSessionFactory getSqlSessionFactory() {
        return this.sessionTemplate.getSqlSessionFactory();
    }

    /**
     * Set the SqlSessionTemplate for this DAO explicitly, as an alternative to specifying a
     * SqlSessionFactory.
     * 
     * @see #setSqlSessionFactory
     */
    public final void setSqlSessionTemplate(SqlSessionTemplate sessionTemplate) {
        this.sessionTemplate = sessionTemplate;
        this.externalTemplate = true;
    }

    /**
     * Return the SqlSessionTemplate for this DAO, pre-initialized with the SqlSessionFactory or set
     * explicitly.
     */
    public final SqlSessionTemplate getSqlSessionTemplate() {
        return this.sessionTemplate;
    }

    protected void checkDaoConfig() {
        Assert.notNull(sessionTemplate, "Property 'SqlSessionTemplate' is required");
        this.sessionTemplate.afterPropertiesSet();
    }
    
    protected String getFullName() {
    	StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2];
    	
    	return stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName();
    }
    
    public int getTotalCount(String statement, Object parameter) {
    	List s = sessionTemplate.selectList(statement, parameter);
    	return s.size();
    }
    
    public List pageList(String statement, Object parameter, Page page) {
		// get totalCount
    	if(page.getTotalCount() == -1)
    		page.setTotalCount(getTotalCount(statement, parameter));
    	// instance RowBounds
		RowBounds rowBounds = new RowBounds(page.getOffset(), page.getPageSize());
		// query
		return this.getSqlSessionTemplate().selectList(statement, parameter, rowBounds);
    }
}
