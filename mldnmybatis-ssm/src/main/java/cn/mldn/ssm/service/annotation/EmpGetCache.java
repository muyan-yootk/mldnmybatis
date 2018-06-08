package cn.mldn.ssm.service.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

@Caching(cacheable= {
		@Cacheable(key = "#eid", unless="#result == null") ,
		@Cacheable(key = "#ena", unless="#result == null") ,
})
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented 
public @interface EmpGetCache {

}
