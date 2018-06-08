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
public class TestEmpService {
	private Random rand = new Random() ;
	@Autowired
	private IEmpService empService ;
	private long currentPage = 1 ;
	private int lineSize = 2 ;
	private String column = null ;
	private String keyWord = "强" ;

	@Test
	public void testList() { 
		Map<String, Object> map = this.empService.list(currentPage, lineSize, column, keyWord);
		System.out.println(map);
	}
	@Test
	public void testAdd() {
		for (int x = 0 ; x < 10 ; x ++) {
		Emp vo = new Emp() ;
		vo.setEmpno(7388L + x );
		vo.setName("强子");
		vo.setJob("某商场团购代表");
		vo.setSalary(8000.0);
		vo.setPhoto("nophoto.jpg");
		System.out.println(this.empService.add(vo));
		}
	}
	@Test
	public void testEdit() {
		Emp vo = new Emp() ;
		vo.setEmpno(7369L);
		vo.setJob("某天茂的间谍");
		System.out.println(this.empService.edit(vo));
	}
	@Test
	public void testPreEdit() {
		Emp vo = this.empService.preEdit(7369L) ;
		System.out.println(vo);
	}
}
