package cn.tf.blog.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import cn.tf.blog.mapper.BloggerDao;
import cn.tf.blog.mapper.UBlogMapper;
import cn.tf.blog.mapper.UUserMapper;
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
	@Autowired
	private UUserMapper userMapper;
  
	public UUser find(String username) {
		return bloggerDao.find(username);
	}

	public UUser getByUserName(String userName) {
		return bloggerDao.getByUserName(userName);
	}

	public Integer update(UUser blogger) {
		return bloggerDao.update(blogger);
	}

	@Override
	public List<UUser> list(Map<String, Object> map) {
		return bloggerDao.list(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return bloggerDao.getTotal(map);
	}

	@Override
	public void delete(String userId, String state) {
		UUser user=new UUser();
		user.setUserId(userId);
		user.setState(state);
		
		userMapper.updateByPrimaryKeySelective(user);
		
	}
	
	
}
