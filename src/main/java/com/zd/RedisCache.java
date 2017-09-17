package com.zd;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;

public class RedisCache implements Cache {
	
	/*
	 * 日志对象
	 */
	private static Logger logger=LogManager.getLogger(RedisCache.class);
	//对象编号
	private String id;
	
	private Jedis redisClient=createRedis();
	
	//用于同步锁
	private ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
	
	private RedisCache(String id){
		if(id==null){
			throw new IllegalArgumentException("Cache instance requires an Id");
		}
		logger.debug("create an cache instance with id:"+id);
		this.id=id;
	}
	
	/*
	 * jedis从连接池中获取
	 */
	public static Jedis createRedis(){
		//获取Jedis实例--》这个地址需要改变
		//Jedis jedis=new Jedis("192.168.137.128");
		Jedis jedis=RedisPool.getPool().getResource();
		return jedis;
	}
	
	//将缓存中的数据删除 实际上就是清空redis中的数据,加入当前有很多个用户用同一个redis缓存，则需要改进
	@Override
	public void clear() {
		logger.info("redis  begin  to clear"); 
		this.redisClient.flushDB();
	}
	//获取当前这个缓存的id
	@Override
	public String getId() {
		return this.id;
	}

	@Override//通过key 到缓存redis中取值
	public Object getObject(Object key) {
		//缓存穿透
		byte[] values=this.redisClient.get(SerializableUtil.serialize(key));
		if(values==null){
			return null;
		}
		Object obj=SerializableUtil.unSerialize(values);
		logger.info("redis get an object with key"+key+":value id :"+obj);
		return obj;
	}

	@Override//获取锁
	public ReadWriteLock getReadWriteLock() {
		return getReadWriteLock();
	}

	@Override//获取数量
	public int getSize() {
		Long size=this.redisClient.dbSize();
		int s=Integer.valueOf(size+"");
		return s;
	}

	@Override//存
	public void putObject(Object key, Object value) {
		byte[] keybyte=SerializableUtil.serialize(key);
		byte[] valuebyte=SerializableUtil.serialize(value);
		this.redisClient.set(keybyte, valuebyte);
	}

	@Override//移除
	public Object removeObject(Object key) {
		byte[] keybyte=SerializableUtil.serialize(key);
		
		return this.redisClient.expire(keybyte, 0);
	}
}
