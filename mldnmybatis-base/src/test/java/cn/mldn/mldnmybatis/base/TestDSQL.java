package cn.mldn.mldnmybatis.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.mldn.util.MyBatisSessionFactory;
import cn.mldn.vo.News;

public class TestDSQL {
	@Test
	public void testSelectAll() throws Exception {
		News vo = new News() ;
		vo.setTitle("今天是个好天气。"); 
		List<News> all = MyBatisSessionFactory.getSession().selectList(
				"cn.mldn.mapping.NewsNS.findAll",vo); 
		all.forEach(System.out::println);
		MyBatisSessionFactory.close();
	}  
	@Test
	public void testSelectAllMap() throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("title", "今天是个好天气。") ;
		List<News> all = MyBatisSessionFactory.getSession().selectList(
				"cn.mldn.mapping.NewsNS.findAll",param); 
		all.forEach(System.out::println); 
		MyBatisSessionFactory.close();
	} 
}
