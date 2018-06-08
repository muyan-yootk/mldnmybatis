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
}
