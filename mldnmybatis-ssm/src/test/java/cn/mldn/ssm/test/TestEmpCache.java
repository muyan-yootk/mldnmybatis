package cn.mldn.ssm.test;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.ssm.service.IEmpService;
import cn.mldn.ssm.vo.Emp;

@ContextConfiguration(locations= {"classpath:spring-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestEmpCache {
	@Autowired
	private IEmpService empService ;
	
	@Test
	public void testEmpDelete() {
		long eid = 7389 ;
		Emp voA = this.empService.preEdit(eid) ;	// 数据查询得到缓存
		System.out.println("*** 1、【数据查询】" + voA);
		Set<Long> all = new HashSet<Long>() ;
		all.add(eid) ;
		System.out.println("*** 2、【数据更新】" + this.empService.delete(all));
		Emp voB = this.empService.preEdit(eid) ;
		System.out.println("*** 3、【数据查询】" + voB);
	}   
	
	@Test
	public void testEmpUpdate() {
		Emp voA = this.empService.preEdit(7369L) ;	// 数据查询得到缓存
		System.out.println("*** 1、【数据查询】" + voA);
		Emp newEmp = new Emp() ;
		newEmp.setEmpno(7369L);
		newEmp.setName("中强");
		newEmp.setJob("大强之悲哀");
		newEmp.setSalary(9000.00);
		System.out.println("*** 2、【数据更新】" + this.empService.edit2(newEmp));
		Emp voB = this.empService.preEdit(7369L) ;
		System.out.println("*** 3、【数据查询】" + voB);
	}  
	
	@Test
	public void testPreEdit() {
		Emp voA = this.empService.preEdit(7369L) ;
		System.out.println(voA);
		System.out.println("********************************");
		Emp voB = this.empService.preEdit(7369L) ;
		System.out.println(voB);
	}
	
	@Test
	public void testGet() {
		Emp voA = this.empService.preEdit(7397L) ;	// id作为缓存
		System.out.println(voA);
		System.out.println("********************************");
		Emp voB = this.empService.preEdit(7397L) ;	// 根据id查询
		System.out.println(voB);
		System.out.println("********************************");
		Emp voC = this.empService.preEdit(7397L) ;	// 根据id查询
		System.out.println(voC);
	}
}
