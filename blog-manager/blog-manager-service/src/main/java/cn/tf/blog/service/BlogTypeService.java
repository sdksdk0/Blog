package cn.tf.blog.service;

import java.util.List;
import java.util.Map;

import cn.tf.blog.po.UBlogtype;



/**
 * 博客类型Service接口
 * @author Administrator
 *
 */
public interface BlogTypeService {

	/**
	 * 查询所有博客类型 以及对应的博客数量
	 * @return
	 */
	public List<UBlogtype> countList();
	
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
}
