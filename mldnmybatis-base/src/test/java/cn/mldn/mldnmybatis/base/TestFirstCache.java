package cn.mldn.mldnmybatis.base;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import cn.mldn.util.MyBatisSessionFactory;
import cn.mldn.vo.News;

public class TestFirstCache {
	@Test
	public void testClearCache() throws Exception {
		SqlSession session = MyBatisSessionFactory.getSession();
		System.out.println("====== 1、发出第一次查询指令 ======");
		News newsA = session.selectOne("cn.mldn.mapping.NewsNS.findById", 8L);
		System.out.println(newsA);
		session.clearCache(); // 清除缓存
		System.err.println("====== 2、发出第二次查询指令 ======");
		News newsB = session.selectOne("cn.mldn.mapping.NewsNS.findById", 8L);
		System.err.println(newsB);
		MyBatisSessionFactory.close();
	} 
 
	
	@Test
	public void testCacheB() throws Exception {
		SqlSession session = MyBatisSessionFactory.getSession();
		System.out.println("====== 1、发出第一次查询指令 ======");
		News newsA = session.selectOne("cn.mldn.mapping.NewsNS.findById", 8L);
		newsA.setNid(66L); // 与数据库中的内容不同了
		newsA.setTitle("新的新闻标题"); // 与数据库中的内容不同了
		newsA.setPubdate(new Date()); // 与数据库中的内容不同了
		System.out.println(newsA);
		System.err.println("====== 2、发出第二次查询指令 ======");
		News newsB = session.selectOne("cn.mldn.mapping.NewsNS.findById", 8L);
		System.err.println(newsB);
		MyBatisSessionFactory.close();
	} 
	
	@Test
	public void testSelectOne() throws Exception {
		SqlSession session = MyBatisSessionFactory.getSession();
		System.out.println("====== 1、发出第一次查询指令 ======");
		News newsA = session.selectOne("cn.mldn.mapping.NewsNS.findById", 8L);
		System.out.println(newsA);
		System.err.println("====== 2、发出第二次查询指令 ======");
		News newsB = session.selectOne("cn.mldn.mapping.NewsNS.findById", 8L);
		System.err.println(newsB);
		MyBatisSessionFactory.close();
	} 
 
}
