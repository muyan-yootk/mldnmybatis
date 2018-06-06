package cn.mldn.mldnmybatis.base;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.mldn.util.MyBatisSessionFactory;
import cn.mldn.vo.News;

public class TestNews {
	@Test
	public void testSelectCount() throws Exception {
		String column = "title" ;
		String keyWord = "今天" ;
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("column", column) ;
		map.put("keyWord", "%" + keyWord + "%");
		Long count = MyBatisSessionFactory.getSession().selectOne(
				"cn.mldn.mapping.NewsNS.getAllCount",map); 
		System.out.println(count);   
		MyBatisSessionFactory.close();
	}   
	@Test
	public void testSelectSplit() throws Exception {
		String column = "title" ;
		String keyWord = "今天" ;
		long currentPage = 1 ;
		int lineSize = 5 ;
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("column", column) ;
		map.put("keyWord", "%" + keyWord + "%");
		map.put("start", (currentPage - 1) * lineSize) ;
		map.put("lineSize", lineSize) ;
		List<News> all = MyBatisSessionFactory.getSession().selectList(
				"cn.mldn.mapping.NewsNS.findSplit",map); 
		System.out.println(all);  
		MyBatisSessionFactory.close();
	}   
	@Test
	public void testSelectAll() throws Exception {
		List<News> all = MyBatisSessionFactory.getSession().selectList(
				"cn.mldn.mapping.NewsNS.findAll"); 
		System.out.println(all);  
		MyBatisSessionFactory.close();
	}  
	@Test
	public void testSelectOne() throws Exception {
		News news = MyBatisSessionFactory.getSession().selectOne(
				"cn.mldn.mapping.NewsNS.findById", 8L); 
		System.out.println(news); 
		MyBatisSessionFactory.close();
	} 
	
	@Test
	public void testEdit() throws Exception {
		News vo = new News() ;
		vo.setTitle("马上下课了，高兴吗？");
		vo.setPubdate(new Date());
		vo.setNid(6); 
		System.out.println(MyBatisSessionFactory.getSession().update(
				"cn.mldn.mapping.NewsNS.doEdit", vo)); 
		MyBatisSessionFactory.getSession().commit(); // 提交事务 
		MyBatisSessionFactory.close();
	}
	@Test
	public void testRemove() throws Exception {
		System.out.println(MyBatisSessionFactory.getSession().delete(
				"cn.mldn.mapping.NewsNS.doRemove", 9L)); 
		MyBatisSessionFactory.getSession().commit(); // 提交事务 
		MyBatisSessionFactory.close();
	}
	@Test
	public void testAdd() throws Exception {
		// 1、创建VO对象，并且为对象设置内容
		News vo = new News() ;
		vo.setTitle("今天是个好天气。");
		vo.setPubdate(new Date());
		// 2、通过SqlSession进行内容的设置；
		System.out.println(MyBatisSessionFactory.getSession().delete(
				"cn.mldn.mapping.NewsNS.doCreate", vo)); 
		// 3、在MyBatis里面默认都是开启事务的，所以如果没有进行事务的提交将无法正常进行处理
		MyBatisSessionFactory.getSession().commit(); // 提交事务
		System.out.println("当前的ID内容：" + vo.getNid());
		// 4、操作的最后必须关闭数据库连接
		MyBatisSessionFactory.close();
	}
}
