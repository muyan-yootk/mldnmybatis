package cn.mldn.ssm.test;

import java.util.Map;
import java.util.Random;

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
	public void testPreEdit() {
		Emp voA = this.empService.preEdit(7369L) ;
		System.out.println(voA);
		System.out.println("********************************");
		Emp voB = this.empService.preEdit(7369L) ;
		System.out.println(voB);
	}
	
	@Test
	public void testGet() {
		Emp voA = this.empService.getEmp(7369L,"小强") ;	// id作为缓存
		System.out.println(voA);
		System.out.println("********************************");
		Emp voB = this.empService.getEmp(7369L,"小强") ;	// 根据id查询
		System.out.println(voB);
		System.out.println("********************************");
		Emp voC = this.empService.preEdit(7369L) ;	// 根据id查询
		System.out.println(voC);
	}
}
