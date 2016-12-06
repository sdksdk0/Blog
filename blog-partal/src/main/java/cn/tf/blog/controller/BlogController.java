package cn.tf.blog.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.tf.blog.common.util.StringUtil;
import cn.tf.blog.po.UBlog;
import cn.tf.blog.po.UBlogtype;
import cn.tf.blog.po.ULink;
import cn.tf.blog.po.UUser;
import cn.tf.blog.service.BlogIndex;
import cn.tf.blog.service.BlogService;
import cn.tf.blog.service.BlogTypeService;
import cn.tf.blog.service.BloggerService;
import cn.tf.blog.service.CommentService;
import cn.tf.blog.service.LinkService;


/**
 * 博客Controller层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/blog")
public class BlogController {

	@Resource
	private BlogService blogService;
	
	@Resource
	private CommentService commentService;
	
	@Autowired
	private BloggerService  bloggerService;
	@Autowired
	private BlogTypeService blogTypeService;
	@Autowired
	private LinkService linkService;

	
	
	// 博客索引
	private BlogIndex blogIndex=new BlogIndex();
	
	
	
	/**
	 * 请求博客详细信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/articles/{username}/{blogid}")
	public ModelAndView details(@PathVariable("username") String username,@PathVariable("blogid") String blogid,HttpServletRequest request)throws Exception{
		ModelAndView mav=new ModelAndView();
		UBlog blog=blogService.findById(blogid);
		String keyWords=blog.getKeyword();
		if(StringUtil.isNotEmpty(keyWords)){
			String arr[]=keyWords.split(" ");
			mav.addObject("keyWords",StringUtil.filterWhite(Arrays.asList(arr)));			
		}else{
			mav.addObject("keyWords",null);			
		}
		
		blog.setContent(new String(blog.getContent().getBytes("iso-8859-1"),"utf-8"));
		
		mav.addObject("blog", blog);
		blog.setClickhit(blog.getClickhit()+1); // 博客点击次数加1
		blogService.update(blog);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("blogid", blogid);
		map.put("state", 1); // 查询审核通过的评论
		mav.addObject("commentList", commentService.list(map)); 
		mav.addObject("pageCode", this.genUpAndDownPageCode(blogService.getLastBlog(blogid),blogService.getNextBlog(blogid),request.getSession().getServletContext().getContextPath()));
		mav.addObject("mainPage", "blog/view.jsp");
		mav.addObject("pageTitle",blog.getTitle()+"_博客云");
		
		
		//根据博主的名字查询博主信息
		UUser user = bloggerService.find(username);
		mav.addObject("user", user);
		
		//按日期分类
		List<UBlog> countList = blogService.countList(username);
		mav.addObject("countList", countList);
		
		//按日志类别分
		List<UBlogtype> blogTypeCountList = blogTypeService.countList(username);
		mav.addObject("blogTypeCountList",blogTypeCountList);
		
		
		//友情链接
		Map<String,Object> linkmap=new HashMap<String,Object>();
		linkmap.put("username", username);
		List<ULink> linkList = linkService.list(linkmap);
		mav.addObject("linkList",linkList);
		
		mav.setViewName("mainTemp");
		return mav;
	}
	
	
	//不带用户名的详情
	@RequestMapping("/articles/{blogid}")
	public ModelAndView details(@PathVariable("blogid") String blogid,HttpServletRequest request)throws Exception{
		ModelAndView mav=new ModelAndView();
		UBlog blog=blogService.findById(blogid);
		String keyWords=blog.getKeyword();
		if(StringUtil.isNotEmpty(keyWords)){
			String arr[]=keyWords.split(" ");
			mav.addObject("keyWords",StringUtil.filterWhite(Arrays.asList(arr)));			
		}else{
			mav.addObject("keyWords",null);			
		}
		
		blog.setContent(new String(blog.getContent().getBytes("iso-8859-1"),"utf-8"));
		
		
		mav.addObject("blog", blog);
		blog.setClickhit(blog.getClickhit()+1); // 博客点击次数加1
		blogService.update(blog);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("blogid", blogid);
		map.put("state", 1); // 查询审核通过的评论
		mav.addObject("commentList", commentService.list(map)); 
		mav.addObject("pageCode", this.genUpAndDownPageCode(blogService.getLastBlog(blogid),blogService.getNextBlog(blogid),request.getSession().getServletContext().getContextPath()));
		mav.addObject("mainPage", "blog/view.jsp");
		mav.addObject("pageTitle",blog.getTitle()+"_博客云");
		
		//根据博客id查询博主信息
		
		String username=blog.getUsername();
		//根据博主的名字查询博主信息
		UUser user = bloggerService.find(username);
		mav.addObject("user", user);
		
		//按日期分类
		List<UBlog> countList = blogService.countList(username);
		mav.addObject("countList", countList);
		
		//按日志类别分
		List<UBlogtype> blogTypeCountList = blogTypeService.countList(username);
		mav.addObject("blogTypeCountList",blogTypeCountList);
		
		//友情链接
		Map<String,Object> linkmap=new HashMap<String,Object>();
		linkmap.put("username", username);
		List<ULink> linkList = linkService.list(linkmap);
		mav.addObject("linkList",linkList);
		

		mav.setViewName("mainTemp");
		return mav;
	}
	
	
	
	
	
	/**
	 * 根据关键字查询相关博客信息
	 * @param q
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/q")
	public ModelAndView search(@RequestParam(value="q",required=false)String q,@RequestParam(value="page",required=false)String page,HttpServletRequest request)throws Exception{
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "blog/result.jsp");
		List<UBlog> blogList=blogIndex.searchBlog(q.trim());
		Integer toIndex=blogList.size()>=Integer.parseInt(page)*10?Integer.parseInt(page)*10:blogList.size();
		mav.addObject("blogList",blogList.subList((Integer.parseInt(page)-1)*10, toIndex));
		mav.addObject("pageCode",this.genUpAndDownPageCode(Integer.parseInt(page), blogList.size(), q,10,request.getSession().getServletContext().getContextPath()));
		mav.addObject("q",q);
		mav.addObject("resultTotal",blogList.size());
		mav.addObject("pageTitle","搜索关键字'"+q+"'结果页面_博客云");
		mav.setViewName("index");
		return mav;
	}
	
	/**
	 * 获取下一篇博客和下一篇博客代码
	 * @param lastBlog
	 * @param nextBlog
	 * @return
	 */
	private String genUpAndDownPageCode(UBlog lastBlog,UBlog nextBlog,String projectContext){
		StringBuffer pageCode=new StringBuffer();
		if(lastBlog==null || lastBlog.getBlogid()==null){
			pageCode.append("<p>上一篇：没有了</p>");
		}else{
			pageCode.append("<p>上一篇：<a href='"+projectContext+"/blog/articles/"+lastBlog.getUsername()+"/"+lastBlog.getBlogid()+".html'>"+lastBlog.getTitle()+"</a></p>");
		}
		if(nextBlog==null || nextBlog.getBlogid()==null){
			pageCode.append("<p>下一篇：没有了</p>");
		}else{
			pageCode.append("<p>下一篇：<a href='"+projectContext+"/blog/articles/"+nextBlog.getUsername()+"/"+nextBlog.getBlogid()+".html'>"+nextBlog.getTitle()+"</a></p>");
		}
		return pageCode.toString();
	}
	
	/**
	 * 获取上一页，下一页代码 查询博客用到
	 * @param page 当前页
	 * @param totalNum 总记录数
	 * @param q 查询关键字
	 * @param pageSize 每页大小
	 * @param projectContext
	 * @return
	 */
	private String genUpAndDownPageCode(Integer page,Integer totalNum,String q,Integer pageSize,String projectContext){
		long totalPage=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
		StringBuffer pageCode=new StringBuffer();
		if(totalPage==0){
			return "";
		}else{
			pageCode.append("<nav>");
			pageCode.append("<ul class='pager' >");
			if(page>1){
				pageCode.append("<li><a href='"+projectContext+"/blog/q.html?page="+(page-1)+"&q="+q+"'>上一页</a></li>");
			}else{
				pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
			}
			if(page<totalPage){
				pageCode.append("<li><a href='"+projectContext+"/blog/q.html?page="+(page+1)+"&q="+q+"'>下一页</a></li>");				
			}else{
				pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");				
			}
			pageCode.append("</ul>");
			pageCode.append("</nav>");
		}
		return pageCode.toString();
	}
}
