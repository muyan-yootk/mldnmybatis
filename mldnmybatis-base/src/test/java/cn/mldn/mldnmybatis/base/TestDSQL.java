package cn.mldn.mldnmybatis.base;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import cn.mldn.util.MyBatisSessionFactory;
import cn.mldn.vo.News;

public class TestDSQL {
	@Test
	public void testEdit() throws Exception {
		News updateVo = new News() ;
		updateVo.setNid(10L);
		updateVo.setPubdate(new SimpleDateFormat("yyyy-MM-dd").parse("3056-12-12"));
		int len = MyBatisSessionFactory.getSession().update("cn.mldn.mapping.NewsNS.doEdit", updateVo);
		MyBatisSessionFactory.getSession().commit(); 
		MyBatisSessionFactory.close();
	}     
	
	@Test
	public void testDeleteIds() throws Exception {
		Set<Long> ids = new HashSet<Long>() ; // 传递的一般都是Set集合
		ids.add(6L) ;
		ids.add(7L) ;
		ids.add(8L) ;
		int len = MyBatisSessionFactory.getSession().delete(
				"cn.mldn.mapping.NewsNS.doRemoveByIds",ids.toArray());
		MyBatisSessionFactory.getSession().commit();
		System.out.println("删除的数据行数：" + len);
		MyBatisSessionFactory.close();
	}  
	
	@Test
	public void testSelectIds() throws Exception {
		Set<Long> ids = new HashSet<Long>() ; // 传递的一般都是Set集合
		ids.add(6L) ;
		ids.add(7L) ;
		ids.add(8L) ;
		List<News> all = MyBatisSessionFactory.getSession().selectList(
				"cn.mldn.mapping.NewsNS.findAllByIds",ids.toArray()); 
		all.forEach(System.out::println); 
		MyBatisSessionFactory.close();
	}  
	
	@Test
	public void testSelectSplit() throws Exception {
		long currentPage = 1 ;
		int lineSize = 5 ;
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("start", (currentPage - 1) * lineSize) ;
		map.put("lineSize", lineSize) ;
		List<News> all = MyBatisSessionFactory.getSession().selectList(
				"cn.mldn.mapping.NewsNS.findSplit",map); 
		System.out.println(all);  
		MyBatisSessionFactory.close();
	}    
	@Test
	public void testSelectAllMap() throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("nid", 8L) ;
		param.put("title", "今天是个好天气。") ;
		List<News> all = MyBatisSessionFactory.getSession().selectList(
				"cn.mldn.mapping.NewsNS.findAllMap",param); 
		all.forEach(System.out::println); 
		MyBatisSessionFactory.close();
	}  
	@Test 
	public void testSelectAll() throws Exception {
		News vo = new News() ;
		vo.setTitle("今天是个好天气。"); 
		List<News> all = MyBatisSessionFactory.getSession().selectList(
				"cn.mldn.mapping.NewsNS.findAll",vo); 
		all.forEach(System.out::println);
		MyBatisSessionFactory.close();
	}  

}
