package org.springframework.orm.ibatis3.support;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.FactoryBean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.orm.ibatis3.SqlSessionTemplate;
import org.springframework.util.Assert;

import javax.sql.DataSource;

/**
 * BeanFactory that enables injection of iBatis Mapper Interfaces.
 * 
 * @author Eduardo Macarrón
 * @since 3.0
 * @see org.springframework.orm.ibatis3.SqlSessionTemplate
 */
public class MapperFactoryBean <T> implements FactoryBean<T>, InitializingBean {

    private DataSource dataSource;

    private Class<T> mapperInterface;

    private SqlSessionFactory sqlSessionFactory;

    private SqlSessionTemplate sqlSessionTemplate;

    public MapperFactoryBean() {
        super();
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setMapperInterface(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(mapperInterface, "Property 'MapperInterface' is required");

        if (sqlSessionFactory == null && sqlSessionTemplate == null) {
            throw new IllegalArgumentException("Property 'sqlSessionFactory' is required");

        } else if (sqlSessionTemplate == null) {
            sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        }

        if (dataSource != null) {
            sqlSessionTemplate.setDataSource(dataSource);
        }
    }

    @Override
    public T getObject() throws Exception {
        return sqlSessionTemplate.getMapper(mapperInterface);
    }

    @Override
    public Class<T> getObjectType() {
        return mapperInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
