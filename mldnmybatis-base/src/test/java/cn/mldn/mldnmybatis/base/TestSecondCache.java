package cn.mldn.mldnmybatis.base;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import cn.mldn.util.MyBatisSessionFactory;
import cn.mldn.vo.News;

public class TestSecondCache {
	@Test
	public void testFirstCache() throws Exception {
		// 1、获取SqlSessionFactory对象，这个对象可以创建若干个SqlSession对象
		SqlSessionFactory factory = MyBatisSessionFactory.getSessionFactory() ;
		System.out.println("====== 1、发出第一次查询指令 ======");
		SqlSession sessionA = factory.openSession() ; // 创建第一个SqlSession
		News newsA = sessionA.selectOne("cn.mldn.mapping.NewsNS.findById", 8L);
		System.out.println(newsA);
		sessionA.close(); // 写入二级缓存
		System.err.println("====== 2、发出第二次查询指令 ======");
		SqlSession sessionB = factory.openSession() ; // 创建第二个SqlSession
		News newsB = sessionB.selectOne("cn.mldn.mapping.NewsNS.findById", 8L);
		System.err.println(newsB); 
		MyBatisSessionFactory.close();
	} 
 
}
