package cn.mldn.util.cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;

import cn.mldn.util.redis.RedisConnectionUtil;
import redis.clients.jedis.Jedis;

public class MyBatisRedisCache implements Cache {
	private Jedis jedis = new RedisConnectionUtil().getConnection() ; // 获取连接
	private ReadWriteLock lock = new ReentrantReadWriteLock() ;
	private String id ; // 保存的缓存数据的名称
	public MyBatisRedisCache(String id) {	// 不需要你关注，会由MyBatis自动调用
		this.id = id ;
	}
	@Override
	public void clear() {	// 删除掉所有缓存的内容
		this.jedis.flushDB() ;// 清除数据库
	}
	@Override
	public String getId() {
		return this.id ;
	}

	@Override
	public Object getObject(Object key) {
		byte [] keyData = SerializableUtil.serialize(key) ;
		byte [] valueData = this.jedis.get(keyData) ;
		if (valueData != null) {
			return SerializableUtil.deserialize(valueData) ; 
		} 
		return null;
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return this.lock ;
	}

	@Override
	public int getSize() {
		Long num = this.jedis.dbSize() ;
		return num.intValue() ;
	}

	@Override
	public void putObject(Object key, Object value) {
		byte [] keyData = SerializableUtil.serialize(key) ;
		byte [] valueData = SerializableUtil.serialize(value) ;
		this.jedis.set(keyData, valueData) ;
	}

	@Override
	public Object removeObject(Object key) {
		byte [] keyData = SerializableUtil.serialize(key) ;
		return this.jedis.del(keyData); 
	}
	
	private static class SerializableUtil {	// 定义一个内部类
		public static Object deserialize(byte [] data) {
			Object result = null ;
			ByteArrayInputStream bis = null ;
			ObjectInputStream ois = null ; // 对象反序列化处理
			try {
				bis = new ByteArrayInputStream(data) ; 
				ois = new ObjectInputStream(bis) ;
				result = ois.readObject() ; // 获取数据 
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					bis.close();
					ois.close(); 
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return result ;
		} 
		public static byte [] serialize(Object obj) {	// 进行序列化处理
			byte [] result = null ; // 保存对象二进制数据
			ObjectOutputStream oos = null ;
			ByteArrayOutputStream bos = null ; // 通过内存流进行序列化控制
			try {
				bos = new ByteArrayOutputStream() ;	// 实例化字节输出内存流
				oos = new ObjectOutputStream(bos) ; 
				oos.writeObject(obj); // 输出对象
				result = bos.toByteArray() ; // 获取全部二进制数据
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					bos.close();
					oos.close(); 
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return result ;
		}
	}

}
