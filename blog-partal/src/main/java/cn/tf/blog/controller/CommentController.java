package cn.tf.blog.controller;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.tf.blog.common.util.ResponseUtil;
import cn.tf.blog.po.UBlog;
import cn.tf.blog.po.UComment;
import cn.tf.blog.service.BlogService;
import cn.tf.blog.service.CommentService;
import cn.tf.blog.service.RedisService;



import net.sf.json.JSONObject;

/**
 * 评论Controller层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Resource
	private CommentService commentService;
	
	@Resource
	private BlogService blogService;
	@Autowired
	private RedisService redisService;
	
	/**
	 * 添加或者修改评论
	 * @param comment
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public String save(String blogid,String username,UComment comment,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
		
		JSONObject result=new JSONObject();
		int resultTotal=0; // 操作的记录条数
		
			comment.setBlogid(blogid);
			comment.setUsername(username);
			if(comment.getCommentid()==null){
				comment.setCommentid(UUID.randomUUID().toString());
				resultTotal=commentService.add(comment);
				// 该博客的回复次数加1
				UBlog blog=blogService.findById(blogid);
				blog.setReplyhit(blog.getReplyhit()+1);
				//blog.setContent(new String(blog.getContent().getBytes("iso-8859-1"),"utf-8"));
				redisService.addBlog(blog);
				
				//blogService.update(blog);
			}else{
				
			}
			if(resultTotal>0){
				result.put("success", true);
			}else{
				result.put("success", false);
			}

		ResponseUtil.write(response, result);
		return null;
	}

}
