package cn.mldn.mldnmybatis.base;

import java.util.Date;

import org.junit.Test;

import cn.mldn.util.MyBatisSessionFactory;
import cn.mldn.vo.News;

public class TestNews {
	@Test
	public void testAdd() throws Exception {
		// 1、创建VO对象，并且为对象设置内容
		News vo = new News() ;
		vo.setTitle("今天是个好天气。");
		vo.setPubdate(new Date());
		// 2、通过SqlSession进行内容的设置；
		System.out.println(MyBatisSessionFactory.getSession().insert("cn.mldn.mapping.NewsNS.doCreate", vo));
		// 3、在MyBatis里面默认都是开启事务的，所以如果没有进行事务的提交将无法正常进行处理
		MyBatisSessionFactory.getSession().commit(); // 提交事务
		System.out.println("当前的ID内容：" + vo.getNid());
		// 4、操作的最后必须关闭数据库连接
		MyBatisSessionFactory.close();
	}
}
