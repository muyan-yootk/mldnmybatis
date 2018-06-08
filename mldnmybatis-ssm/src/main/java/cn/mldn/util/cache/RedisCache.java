package cn.mldn.util.cache;

import java.util.concurrent.Callable; 

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisCache implements Cache {
	private RedisTemplate<String,Object> redisTemplate ; 
	private String name ; // 缓存区的名字
	@Override
	public String getName() {
		return this.name;
	} 

	@Override
	public Object getNativeCache() {
		return this.redisTemplate;
	}

	@Override
	public ValueWrapper get(Object key) {
		Object object = this.redisTemplate.opsForValue().get(String.valueOf(key)) ;
		if (object != null) {	// 数据查找到了
			return new SimpleValueWrapper(object) ;
		}
		return null;
	}

	@Override
	public <T> T get(Object key, Class<T> type) {
		T object = (T) this.redisTemplate.opsForValue().get(String.valueOf(key)) ;
		return object;
	}

	@Override
	public <T> T get(Object key, Callable<T> valueLoader) {
		return null;
	}

	@Override
	public void put(Object key, Object value) {
		this.redisTemplate.opsForValue().set(String.valueOf(key), value);
	}

	@Override
	public ValueWrapper putIfAbsent(Object key, Object value) {
		return null;
	}

	@Override
	public void evict(Object key) {
		this.redisTemplate.delete(String.valueOf(key)) ;
	}

	@Override
	public void clear() {
		this.redisTemplate.getConnectionFactory().getConnection().flushDb(); 
	}
	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	public void setName(String name) {
		this.name = name;
	}
}
