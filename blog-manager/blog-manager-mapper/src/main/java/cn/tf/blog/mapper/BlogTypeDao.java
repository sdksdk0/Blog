package cn.tf.blog.mapper;

import java.util.List;
import java.util.Map;

import cn.tf.blog.po.UBlogtype;



/**
 * 博客类型Dao接口
 * @author Administrator
 *
 */
public interface BlogTypeDao {

	/**
	 * 查询所有博客类型 以及对应的博客数量
	 * @param username 
	 * @return
	 */
	public List<UBlogtype> countList(String username);
	
	/**
	 * 通过id查询博客类型
	 * @param id
	 * @return
	 */
	public UBlogtype findById(String id);
	
	/**
	 * 分页查询博客类别信息
	 * @param map
	 * @return
	 */
	public List<UBlogtype> list(Map<String,Object> map);
	
	/**
	 * 获取总记录数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * 添加博客类别信息
	 * @param blogType
	 * @return
	 */
	public Integer add(UBlogtype blogType);
	
	/**
	 * 修改博客类别信息
	 * @param blogType
	 * @return
	 */
	public Integer update(UBlogtype blogType);
	
	/**
	 * 删除博客类别信息
	 * @param id
	 * @return
	 */
	public Integer delete(String id);

	public List<UBlogtype> typelist(String username);
}
