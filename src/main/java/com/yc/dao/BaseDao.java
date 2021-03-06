package com.yc.dao;

import java.util.List;
import java.util.Map;

public interface BaseDao<T> {
	public List<T> findAll(T t, String sqlId);

	public List<T> findAll(T t, Map map, String sqlId);

	public void add(T t, String sqlId);
   
	public void add(T t, Map map, String sqlId);
	
	public void addMany(T t,List list,String sqlId);

	public void delete(T t, String sqlId);

	public void delete(T t, Map map, String sqlId);
	
	public void deleteMany(T t,List list,String sqlId);

	public void update(T t, String sqlId);

	public void update(T t, Map map, String sqlId);
	
	public void updateMany(T t,List list,String sqlId);

	public double findFunc(T t, String sqlId);

	public double findFunc(T t, Map map, String sqlId);
}
