package cn.tf.blog.mapper;

import cn.tf.blog.po.UUser;


/**
 * 博主Dao接口
 *
 */
public interface BloggerDao {

	/**
	 * 查询博主信息
	 * @return
	 */
	public UUser find(String userName);
	
	/**
	 * 通过用户名查询用户
	 * @param userName
	 * @return
	 */
	public UUser getByUserName(String userName);
	
	/**
	 * 更新博主信息
	 * @param blogger
	 * @return
	 */
	public Integer update(UUser blogger);
}
