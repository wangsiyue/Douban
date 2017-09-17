package com.zd;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
 * 提供序列化  SerializableUtil工具类   以实现序列化操作
 */
public class SerializableUtil {
	/*
	 *将对象 序列化
	 */
	public static  byte[] serialize(Object obj){
		ObjectOutputStream oos=null;
		ByteArrayOutputStream baos=null;
		byte[] bs=null;
		
		try {
			baos=new ByteArrayOutputStream(); //内容缓存输出流
			oos=new ObjectOutputStream(baos);
			oos.writeObject(obj);
			bs=baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(baos!=null){
				try {
					baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return bs;
	}
	
	public static Object unSerialize(byte[] bs){
		ByteArrayInputStream bais=null;
		Object obj=null;
		
		try {
			bais=new ByteArrayInputStream(bs);
			ObjectInputStream ois=new ObjectInputStream(bais);
			obj=ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(bais!=null){
				try {
					bais.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} 
		return obj;
	}
}
