package cn.tf.blog.service;

import java.util.List;
import java.util.Map;

import cn.tf.blog.po.UBlog;

public interface RedisService {
	
	public int addBlog(UBlog  blog);

	public void deleteBlog(String blogid);
	
	public UBlog getUBlog(String blogid);

	public List<UBlog> getUBlogList(Map<String, Object> map);

}
