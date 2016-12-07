package cn.tf.blog.service;

import cn.tf.blog.po.SAdmin;
import cn.tf.blog.po.UUser;




public interface AdminService {
	
	SAdmin  getUserByToken(String token);
	
	/**
	 * 查询博主信息
	 * @return
	 */
	public SAdmin find(String username);
	
	/**
	 * 通过用户名查询用户
	 * @param userName
	 * @return
	 */
	public SAdmin getByUserName(String userName);
	
	/**
	 * 更新博主信息
	 * @param blogger
	 * @return
	 */
	public Integer update(SAdmin blogger);

}
