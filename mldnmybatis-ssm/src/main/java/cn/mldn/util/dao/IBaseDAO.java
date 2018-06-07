package cn.mldn.util.dao;

import java.util.List;
import java.util.Map; 

/**
 * 数据层操作的标准方法定义：增加、修改、删除、查询全部、根据ID查询、分页查询、模糊查询、数据统计
 * @author mldn
 * @param <K> 主键的类型
 * @param <V> VO的类型
 */
public interface IBaseDAO<K, V> {
	/**
	 * 实现数据的增加操作
	 * @param vo 保存要增加数据的VO类对象，该对象不可能为null
	 * @return 增加成功返回true，否则返回false
	 */
	public boolean doCreate(V vo) ;
	/**
	 * 实现数据的修改（编辑）操作，该操作主要实现的是根据ID进行全部数据的修改
	 * @param vo  要修改的新数据，一定要包含有主键
	 * @return 修改成功返回true，否则返回false
	 */
	public boolean doEdit(V vo) ;
	/**
	 * 执行数据的删除操作
	 * @param ids 要删除的数据编号
	 * @return 删除成功返回true，否则返回false
	 */
	public boolean doRemove(Object[] ids) ;
	/**
	 * 根据主键实现指定数据的查询，查询的数据以VO对象的形式保存
	 * @param id 要查询的主键数据
	 * @return 如果该数据存在则将数据转为VO对象存储，如果不存在返回null
	 */
	public V findById(K id)  ;
	/**
	 * 查询全部的数据
	 * @return 返回数据的集合，如果没有任何数据存在则返回空集合（不是null，而是size()==0）
	 */
	public List<V> findAll() ;
	/**
	 * 实现数据的分页显示
	 * @param params 查询的分页参数，设置的Map内容为：
	 * 	· 当前所在页：long currentPage；
	 * 	· 每页显示行：int lineSize
	 * 	· 模糊查询列：column；
	 * 	· 模糊查询关键字：keyWord
	 * @return 返回数据的集合，如果没有任何数据存在则返回空集合（不是null，而是size()==0）
	 */
	public List<V> findSplit(Map<String,Object> params) ;
	/**
	 * 实现数据表的数据行统计
	 * @param params 查询的分页参数，设置的Map内容为：
	 * 	· 模糊查询列：column；
	 * 	· 模糊查询关键字：keyWord
	 * @return COUNT()统计函数执行结果
	 */
	public Long getAllCount(Map<String,Object> params) ;
}
