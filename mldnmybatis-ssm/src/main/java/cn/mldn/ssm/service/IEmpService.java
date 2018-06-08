package cn.mldn.ssm.service;

import java.util.Map;
import java.util.Set;

import org.springframework.cache.annotation.Cacheable;

import cn.mldn.ssm.vo.Emp;

public interface IEmpService {
	public boolean add(Emp vo) ;
	public boolean edit(Emp vo) ;
	@Cacheable(cacheNames="emp")
	public Emp preEdit(long id) ;
	public boolean delete(Set<Long> ids) ;
	public Map<String,Object> list(long currentPage,int lineSize,String column,String keyWord) ;
}
