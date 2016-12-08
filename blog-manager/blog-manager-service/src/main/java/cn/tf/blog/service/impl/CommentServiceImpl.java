package cn.tf.blog.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tf.blog.mapper.CommentMapper;
import cn.tf.blog.po.UComment;
import cn.tf.blog.service.CommentService;



/**
 * 评论Service实现类
 * @author Administrator
 *
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService{

	@Resource
	private CommentMapper commentDao;
	
	
	public int add(UComment comment) {
		return commentDao.add(comment);
	}

	public List<UComment> list(Map<String, Object> map) {
		return commentDao.list(map);
	}

	public Long getTotal(Map<String, Object> map) {
		return commentDao.getTotal(map);
	}

	public Integer delete(String id) {
		return commentDao.delete(id);
	}

	public int update(UComment comment) {
		return commentDao.update(comment);
	}

	@Override
	public List<UComment> findCommentByTime(Map<String,Object> map) {
		
		return commentDao.findCommentByTime(map);
	}

}
