package cn.mldn.ssm.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.mldn.ssm.dao.IEmpDAO;
import cn.mldn.ssm.vo.Emp;
@Repository
public class EmpDAOImpl implements IEmpDAO {
	@Autowired
	private SqlSessionFactory sessionFactory ;
	@Override
	public boolean doCreate(Emp vo) {
		return this.sessionFactory.openSession().insert("cn.mldn.mapping.EmpNS.doCreate", vo) > 0;
	}

	@Override
	public boolean doEdit(Emp vo) {
		return this.sessionFactory.openSession().update("cn.mldn.mapping.EmpNS.doEdit", vo) > 0;
	}

	@Override
	public boolean doRemove(Set<Long> ids) {
		return this.sessionFactory.openSession().delete("cn.mldn.mapping.EmpNS.doRemove", ids.toArray()) > 0;
	}

	@Override
	public Emp findById(Long id) {
		return this.sessionFactory.openSession().selectOne("cn.mldn.mapping.EmpNS.findById",id);
	}

	@Override
	public List<Emp> findAll() {
		return this.sessionFactory.openSession().selectList("cn.mldn.mapping.EmpNS.findAll");
	}

	@Override
	public List<Emp> findSplit(Map<String, Object> params) {
		return this.sessionFactory.openSession().selectList("cn.mldn.mapping.EmpNS.findSplit", params);
	}

	@Override
	public Long getAllCount(Map<String, Object> params) {
		return this.sessionFactory.openSession().selectOne("cn.mldn.mapping.EmpNS.getAllCount", params);
	} 

}
