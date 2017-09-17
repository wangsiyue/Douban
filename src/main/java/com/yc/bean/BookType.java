package com.yc.bean;

import java.io.Serializable;

public class BookType implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer typeid;
	private String typename;
	public Integer getTypeid() {
		return typeid;
	}
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	@Override
	public String toString() {
		return "BookType [typeid=" + typeid + ", typename=" + typename + "]";
	}
	
	
}
