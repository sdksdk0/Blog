package cn.tf.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tf.blog.common.util.JsonUtils;
import cn.tf.blog.dao.JedisClient;

import cn.tf.blog.po.UBlog;
import cn.tf.blog.service.RedisService;


@Service
public class RedisServiceImpl implements RedisService{
	
	@Autowired
	private JedisClient jedisClient;


	@Override
	public int addBlog(UBlog blog) {
		
		try {
			jedisClient.set("BLOG_REDIS_ITEM_KEY :" + blog.getBlogid(),
					JsonUtils.objectToJson(blog));
			// 设置key的有效期,3天过期
			//jedisClient.expire("BLOG_REDIS_ITEM_KEY :" + blog.getBlogid(),259200);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}


	@Override
	public void deleteBlog(String blogid) {
		
		jedisClient.del("BLOG_REDIS_ITEM_KEY :" + blogid);
	}

}