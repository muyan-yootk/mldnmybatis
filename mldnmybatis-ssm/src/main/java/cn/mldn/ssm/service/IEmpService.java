package cn.mldn.ssm.service;

import java.util.Map;
import java.util.Set;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import cn.mldn.ssm.service.annotation.EmpGetCache;
import cn.mldn.ssm.vo.Emp;

@CacheConfig(cacheNames = "emp")
public interface IEmpService {
	public boolean add(Emp vo) ;

	@CacheEvict(key="#ids[0]")
	public boolean delete(Set<Long> ids) ; 
	
	@Caching(put = { 
			@CachePut(key = "#vo.empno", unless = "#result == null"),
			@CachePut(key = "#vo.name", unless = "#result == null"), })
	public Emp edit2(Emp vo);
	
	public boolean edit(Emp vo);
	
	@EmpGetCache
	public Emp getEmp(long eid, String ena);
	
	@Cacheable(cacheNames = "emp")
	public Emp preEdit(long id) ;
	
	
	public Map<String,Object> list(long currentPage,int lineSize,String column,String keyWord) ;
}
