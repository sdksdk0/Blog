package cn.tf.blog.mapper;

import cn.tf.blog.po.SAdmin;
import cn.tf.blog.po.UUser;


 
public interface SAdminDao {

	/**
	 * 查询管理员信息
	 * @return
	 */
	public SAdmin find(String userName);
	
	/**
	 * 通过用户名查询用户
	 * @param userName
	 * @return
	 */
	public SAdmin getByUserName(String userName);
	
	/**
	 * 更新管理员信息
	 * @param blogger
	 * @return
	 */
	public Integer update(SAdmin blogger);
}
