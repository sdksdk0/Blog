package cn.tf.blog.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tf.blog.mapper.BloggerDao;
import cn.tf.blog.po.UUser;
import cn.tf.blog.service.BloggerService;



/**
 * 博主Service实现类
 * @author Administrator
 *
 */
@Service("bloggerService")
public class BloggerServiceImpl implements BloggerService{

	@Resource
	private BloggerDao bloggerDao;

	public UUser find(String username) {
		return bloggerDao.find(username);
	}

	public UUser getByUserName(String userName) {
		return bloggerDao.getByUserName(userName);
	}

	public Integer update(UUser blogger) {
		return bloggerDao.update(blogger);
	}
	
	
}
