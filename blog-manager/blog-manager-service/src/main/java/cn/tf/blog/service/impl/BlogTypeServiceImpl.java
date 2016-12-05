package cn.tf.blog.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tf.blog.mapper.BlogTypeDao;
import cn.tf.blog.po.UBlogtype;
import cn.tf.blog.service.BlogTypeService;


/**
 * 博客类型Service实现类
 * @author Administrator
 *
 */
@Service("blogTypeService")
public class BlogTypeServiceImpl implements BlogTypeService{

	@Resource
	private BlogTypeDao blogTypeDao;
	
	public List<UBlogtype> countList() {
		return blogTypeDao.countList();
	}

	public List<UBlogtype> list(Map<String, Object> map) {
		return blogTypeDao.list(map);
	}

	public Long getTotal(Map<String, Object> map) {
		return blogTypeDao.getTotal(map);
	}

	public Integer add(UBlogtype blogType) {
		//blogType.setBlogtypeId(UUID.randomUUID().toString());
		blogType.setBlogtypeId(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
		return blogTypeDao.add(blogType);
	}

	public Integer update(UBlogtype blogType) {
		return blogTypeDao.update(blogType);
	}

	public Integer delete(String id) {
		return blogTypeDao.delete(id);
	}

	@Override
	public List<UBlogtype> typelist(String username) {
		
		return blogTypeDao.typelist(username);
	}


}
