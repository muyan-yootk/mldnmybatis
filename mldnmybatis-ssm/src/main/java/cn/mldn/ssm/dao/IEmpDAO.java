package cn.mldn.ssm.dao;

import java.util.Map;

import cn.mldn.ssm.vo.Emp;
import cn.mldn.util.dao.IBaseDAO;

public interface IEmpDAO extends IBaseDAO<Long, Emp> {
	public Emp findByIdAndName(Map<String,Object> map) ;
}	
