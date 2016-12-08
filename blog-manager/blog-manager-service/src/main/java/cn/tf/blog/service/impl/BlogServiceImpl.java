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
import cn.tf.blog.mapper.UBlogMapper;
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
	@Autowired
	private UBlogMapper  blMapper;

	
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

	public UBlog getLastBlog(String blogid) {
		return blogMapper.getLastBlog(blogid);
	}

	public UBlog getNextBlog(String blogid) {
		return blogMapper.getNextBlog(blogid);
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

	@Override
	public void update(String blogid,String state) {
		UBlog blog=new UBlog();
		blog.setBlogid(blogid);
		blog.setState(state);
		
		blMapper.updateByPrimaryKeySelective(blog);
	}

	

}
