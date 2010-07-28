package com.zhongsou.mybatis.dao.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Maurice Jin
 */
public class PO {
	
	private Set<String> updatedSet;

    public synchronized void initUpdatedSetp() {
		if (updatedSet == null) {
		    updatedSet = new HashSet<String>();
		}
    }

	public Set<String> getUpdatedSet() {
		return updatedSet;
	}

	public void setUpdatedSet(Set<String> updatedSet) {
		this.updatedSet = updatedSet;
	}

}
