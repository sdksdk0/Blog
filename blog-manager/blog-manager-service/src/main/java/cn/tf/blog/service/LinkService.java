package cn.tf.blog.service;

import java.util.List;
import java.util.Map;

import cn.tf.blog.po.ULink;



/**
 * 友情链接Service接口
 * @author Administrator
 *
 */
public interface LinkService {

	/**
	 * 添加友情链接
	 * @param link
	 * @return
	 */
	public int add(ULink link);
	
	/**
	 * 修改友情链接
	 * @param link
	 * @return
	 */
	public int update(ULink link);
	
	/**
	 * 查找友情链接信息
	 * @param map
	 * @return
	 */
	public List<ULink> list(Map<String,Object> map);	
	
	/**
	 * 获取总记录数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * 删除友情链接
	 * @param id
	 * @return
	 */
	public Integer delete(String id);
}
