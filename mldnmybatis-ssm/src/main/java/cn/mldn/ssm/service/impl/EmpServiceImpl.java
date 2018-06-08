package cn.mldn.ssm.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mldn.ssm.dao.IEmpDAO;
import cn.mldn.ssm.service.IEmpService;
import cn.mldn.ssm.vo.Emp;
import cn.mldn.util.service.abs.AbstractService;
@Service
public class EmpServiceImpl extends AbstractService implements IEmpService {
	@Autowired
	private IEmpDAO empDAO ;
	
	@Override
	public Emp getEmp(long eid, String ena) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("empno", eid) ;
		map.put("name", ena) ;
		return this.empDAO.findByIdAndName(map);
	} 
	
	@Override
	public boolean add(Emp vo) {
		return this.empDAO.doCreate(vo);
	}

	@Override
	public Emp edit2(Emp vo) { 
		if (this.empDAO.doEdit(vo)) {
			return vo ;
		}
		return null ;
	}
	
	@Override
	public boolean edit(Emp vo) {
		return this.empDAO.doEdit(vo);
	}

	@Override
	public Emp preEdit(long id) {
		return this.empDAO.findById(id);
	}

	@Override
	public boolean delete(Set<Long> ids) {
		if (ids == null || ids.size() == 0) {
			return false ;
		}
		return this.empDAO.doRemove(ids.toArray()); 
	}

	@Override
	public Map<String, Object> list(long currentPage, int lineSize, String column, String keyWord) {
		Map<String,Object> result = new HashMap<String,Object>() ;
		Map<String,Object> paramMap = super.paramConverterMap(currentPage, lineSize, column, keyWord) ;
		result.put("allEmps", this.empDAO.findSplit(paramMap)) ;
		result.put("allRecorders", this.empDAO.getAllCount(paramMap)) ;
		return result; 
	}

}
