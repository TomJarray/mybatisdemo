package com.zhongsou.mybatis.dao.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Maurice Jin
 */
public class PO implements Serializable {

	private static final long serialVersionUID = 3194709419462743566L;
	
	private Map<String, Object> setterMap;

    public synchronized void initSetterMap() {
		if (setterMap == null) {
		    setterMap = new HashMap<String, Object>();
		    List<String> columns = new ArrayList<String>();
		    setterMap.put("columns", columns);
		}
    }

    public Map<String, Object> getSetterMap() {
    	return setterMap;
    }

}
