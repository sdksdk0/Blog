package cn.tf.blog.service;

import cn.tf.blog.po.UBlog;

public interface RedisService {
	
	public int addBlog(UBlog  blog);

	public void deleteBlog(String blogid);

}
