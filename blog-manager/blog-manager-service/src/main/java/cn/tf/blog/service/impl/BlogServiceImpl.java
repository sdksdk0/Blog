package cn.tf.blog.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.swing.JEditorPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tf.blog.mapper.BlogMapper;
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
	private BlogMapper blogMapper;

	
	public List<UBlog> countList(String username) {
		return blogMapper.countList(username);
	}

	public List<UBlog> list(Map<String, Object> map) {
		return blogMapper.list(map);
	}

	public Long getTotal(Map<String, Object> map) {
		return blogMapper.getTotal(map);
	}

	public UBlog findById(String id) {
		return blogMapper.findById(id);
	}

	public Integer update(UBlog blog) {
		return blogMapper.update(blog);
	}

	public UBlog getLastBlog(String id) {
		return blogMapper.getLastBlog(id);
	}

	public UBlog getNextBlog(String id) {
		return blogMapper.getNextBlog(id);
	}

	public Integer add(UBlog blog) {
		//blog.setBlogid(UUID.randomUUID().toString());
		blog.setBlogid(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
		
		
		return blogMapper.add(blog);
	}

	public Integer delete(String id) {
		return blogMapper.delete(id);
	}

	public Integer getBlogByTypeId(String typeId) {
		return blogMapper.getBlogByTypeId(typeId);
	}

	

}
