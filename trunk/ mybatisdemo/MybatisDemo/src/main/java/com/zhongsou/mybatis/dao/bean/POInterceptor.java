package com.zhongsou.mybatis.dao.bean;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.apache.commons.lang.StringUtils;

/**
 * <pre>
 * set方法拦截器
 * 拦截PO中所有的set方法,把set的属性放入到map中
 * </pre>
 * 
 * @author Maurice Jin
 */
public class POInterceptor implements MethodInterceptor {

    private static final String SET_METHOD = "set";

    @SuppressWarnings("unchecked")
	@Override
    public Object intercept(Object obj, Method method, Object[] args,
	    MethodProxy proxy) throws Throwable {
		// 拦截DataObject中所有的set方法,把set的属性放入到map中
		if (method.getName().startsWith(SET_METHOD)) {
		    if (obj instanceof PO) {
				PO po = (PO) obj;
				po.initUpdatedSetp();
				String attribute = StringUtils.substring(method.getName(),
					SET_METHOD.length());
				attribute = StringUtils.uncapitalize(attribute);
				if (args != null && args.length == 1) {
					po.getUpdatedSet().add(attribute);
				}
		    }
		}
		return proxy.invokeSuper(obj, args);
    }
    
}
