package com.zhongsou.mybatis.dao.bean;

import net.sf.cglib.proxy.Enhancer;

/**
 * 
 * @author Maurice Jin
 */
public class POFactory {

    private static final POInterceptor poInterceptor = new POInterceptor();

    @SuppressWarnings("unchecked")
    public static <T extends PO> T getPoInstance(Class<T> clazz) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(poInterceptor);
		return (T) enhancer.create();
    }
}
