package cn.mldn.mldnmybatis.base;

import java.io.InputStream;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cn.mldn.vo.News;

public class TestNews {
	@Test
	public void testAdd() throws Exception {
		// 1、通过Resources加载配置文件
		InputStream input = Resources.getResourceAsStream("mybatis/mybatis.cfg.xml") ;
		// 2、通过读取到的资源创建资源的连接工厂类
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(input) ;
		// 3、具体的操作需要通过SqlSession展开，而SqlSession创建需要SqlSessionFactory处理
		SqlSession session = sessionFactory.openSession() ;
		// 4、创建VO对象，并且为对象设置内容
		News vo = new News() ;
		vo.setTitle("今天是个好天气。");
		vo.setPubdate(new Date());
		// 5、通过SqlSession进行内容的设置；
		System.out.println(session.insert("cn.mldn.mapping.NewsNS.doCreate", vo));
		// 6、在MyBatis里面默认都是开启事务的，所以如果没有进行事务的提交将无法正常进行处理
		session.commit(); // 提交事务
		System.out.println("当前的ID内容：" + vo.getNid());
		// 7、操作的最后必须关闭数据库连接
		session.close();
		input.close();
	}
}
