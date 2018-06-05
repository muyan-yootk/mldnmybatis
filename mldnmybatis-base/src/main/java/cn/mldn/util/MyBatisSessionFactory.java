package cn.mldn.util;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisSessionFactory {
	private static final String CONFIG_FILE = "mybatis/mybatis.cfg.xml" ;
	private static final ThreadLocal<SqlSession> SESSION_THREAD = new ThreadLocal<SqlSession>() ;
	private static SqlSessionFactory sessionFactory ;
	private static InputStream input ; // 进行资源信息读取
	private MyBatisSessionFactory() {} // 构造方法私有化
	static {	// 类加载的时候就直接进行SqlSessionFactory初始化
		rebuildSessionFactory();
	}
	public static SqlSession getSession() {
		SqlSession session = SESSION_THREAD.get() ;
		if (session == null) {	// 没有保存过
			if (sessionFactory == null) {
				rebuildSessionFactory(); 
			}
			session = sessionFactory.openSession() ;
			SESSION_THREAD.set(session); // 保存session对象
		}
		return session ;
	}
	public static void close() {
		SqlSession session = SESSION_THREAD.get() ;
		if (session != null) {
			session.close();
			SESSION_THREAD.remove(); 
		}
	}
	public static SqlSessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			rebuildSessionFactory();
		}
		return sessionFactory ;
	}
	private static void rebuildSessionFactory() {	// 创建SqlSessionFactory对象
		try {
			input = Resources.getResourceAsStream(CONFIG_FILE) ; 
			sessionFactory = new SqlSessionFactoryBuilder().build(input) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
