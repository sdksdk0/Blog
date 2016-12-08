package cn.tf.blog.service;

import java.util.List;
import java.util.Map;

import cn.tf.blog.po.UComment;



/**
 * 评论Service接口
 * @author Administrator
 *
 */
public interface CommentService {

	/**
	 * 添加评论
	 * @param comment
	 * @return
	 */
	public int add(UComment comment);
	
	/**
	 * 修改评论
	 * @param comment
	 * @return
	 */
	public int update(UComment comment);
	
	/**
	 * 查找用户评论信息
	 * @param map
	 * @return
	 */
	public List<UComment> list(Map<String,Object> map);
	
	
	/**
	 * 获取总记录数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * 删除评论信息
	 * @param id
	 * @return
	 */
	public Integer delete(String id);
	

	public List<UComment> findCommentByTime(Map<String,Object> map);
	
}
