package cn.tf.blog.mapper;

import java.util.List;
import java.util.Map;

import cn.tf.blog.po.UBlog;



/**
 * 博客Dao接口
 * @author Administrator
 *  
 */
public interface BlogMapper {
  
	/**
	 * 根据日期月份分组查询
	 * @return
	 */
	public List<UBlog> countList();
	
	/**
	 * 分页查询博客
	 * @return
	 */
	public List<UBlog> list(Map<String,Object> map);
	
	/**
	 * 获取总记录数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * 通过Id查找实体
	 * @param id
	 * @return
	 */
	public UBlog findById(String id);
	
	/**
	 * 更新博客信息
	 * @param blog
	 * @return
	 */
	public Integer update(UBlog blog); 
	
	/**
	 * 获取上一个博客
	 * @param id
	 * @return
	 */
	public UBlog getLastBlog(String id);
	
	/**
	 * 获取下一个博客
	 * @param id
	 * @return
	 */
	public UBlog getNextBlog(String id);
	
	/**
	 * 添加博客信息
	 * @param blog
	 * @return
	 */
	public Integer add(UBlog blog);
	
	/**
	 * 删除博客信息
	 * @param id
	 * @return
	 */
	public Integer delete(String id);
	
	/**
	 * 查询指定的博客类别下的博客数量
	 * @param typeId
	 * @return
	 */
	public Integer getBlogByTypeId(String typeId);


	
	
}
