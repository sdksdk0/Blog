package cn.tf.blog.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tf.blog.mapper.BlogDao;
import cn.tf.blog.po.UBlog;
import cn.tf.blog.service.BlogService;


/**
 * 博客Service实现类
 * @author Administrator
 *
 */
@Service("blogService")
public class BlogServiceImpl implements BlogService{

	@Resource
	private BlogDao blogDao;
	
	public List<UBlog> countList() {
		return blogDao.countList();
	}

	public List<UBlog> list(Map<String, Object> map) {
		return blogDao.list(map);
	}

	public Long getTotal(Map<String, Object> map) {
		return blogDao.getTotal(map);
	}

	public UBlog findById(String id) {
		return blogDao.findById(id);
	}

	public Integer update(UBlog blog) {
		return blogDao.update(blog);
	}

	public UBlog getLastBlog(String id) {
		return blogDao.getLastBlog(id);
	}

	public UBlog getNextBlog(String id) {
		return blogDao.getNextBlog(id);
	}

	public Integer add(UBlog blog) {
		return blogDao.add(blog);
	}

	public Integer delete(String id) {
		return blogDao.delete(id);
	}

	public Integer getBlogByTypeId(String typeId) {
		return blogDao.getBlogByTypeId(typeId);
	}

	

}
