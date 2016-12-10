package cn.tf.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.tf.blog.common.pojo.PageBean;
import cn.tf.blog.common.util.CookieUtils;
import cn.tf.blog.common.util.ExceptionUtil;
import cn.tf.blog.common.util.PageUtil;
import cn.tf.blog.common.util.StringUtil;
import cn.tf.blog.common.util.TaotaoResult;
import cn.tf.blog.po.SType;
import cn.tf.blog.po.UBlog;
import cn.tf.blog.po.UBlogtype;
import cn.tf.blog.po.UComment;
import cn.tf.blog.po.ULink;
import cn.tf.blog.po.UUser;
import cn.tf.blog.service.BlogService;
import cn.tf.blog.service.BlogTypeService;
import cn.tf.blog.service.BloggerService;
import cn.tf.blog.service.CommentService;
import cn.tf.blog.service.LinkService;
import cn.tf.blog.service.STypeService;
import cn.tf.blog.service.UserService;



/**
 * 主页Controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/")
public class UserController {

	@Resource
	private BlogService blogService;
	@Autowired
	private BloggerService  bloggerService;
	@Autowired
	private BlogTypeService blogTypeService;
	@Autowired
	private LinkService linkService;
	@Autowired
	private STypeService typeService;
	@Autowired
	private UserService userService;
	@Autowired
	private CommentService commentService;
	
	
	/**
	 * 请求主页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userlist")
	public ModelAndView index(@RequestParam(value="page",required=false)String page,
			@RequestParam(value="blogtypeid",required=false)String blogtypeid,
			String username,
			@RequestParam(value="releaseDateStr",required=false)String releaseDateStr,HttpServletRequest request)throws Exception{
		ModelAndView mav=new ModelAndView();
		if(StringUtil.isEmpty(page)){
			page="1";
		}

		
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("blogtypeid", blogtypeid);
		
		if(releaseDateStr!="" && releaseDateStr!=null){
			map.put("releaseDateStr", new String(releaseDateStr.getBytes("iso-8859-1"),"utf-8"));
		}
		map.put("username", username);
		List<UBlog> blogList=blogService.list(map);
		for(UBlog blog:blogList){
			List<String> imagesList=blog.getImagesList();
			String blogInfo=blog.getContent();
			Document doc=Jsoup.parse(blogInfo);
			Elements jpgs=doc.select("img[src$=.jpg]"); //　查找扩展名是jpg的图片
			for(int i=0;i<jpgs.size();i++){
				Element jpg=jpgs.get(i);
				imagesList.add(jpg.toString());
				if(i==2){
					break;
				}
			}
		}
		mav.addObject("blogList", blogList);
		StringBuffer param=new StringBuffer(); // 查询参数
		if(StringUtil.isNotEmpty(blogtypeid)){
			param.append("blogtypeid="+blogtypeid+"&");
		}
		if(StringUtil.isNotEmpty(releaseDateStr)){
			param.append("releaseDateStr="+new String(releaseDateStr.getBytes("iso-8859-1"),"utf-8")+"&");
		}
		if(StringUtil.isNotEmpty(username)){
			param.append("username="+username+"&");
		}
		
		mav.addObject("pageCode",PageUtil.genPagination(request.getContextPath()+"/userlist", blogService.getTotal(map), Integer.parseInt(page), 10, param.toString()));
		mav.addObject("mainPage", "blog/list.jsp");
		mav.addObject("pageTitle","博客云");
		
		
		//根据博主的名字查询博主信息
		UUser user = bloggerService.find(username);
		mav.addObject("user", user);
		
		//按日期分类
		List<UBlog> countList = blogService.countList(username);
		mav.addObject("countList", countList);
		
		//按日志类别分
		List<UBlogtype> blogTypeCountList = blogTypeService.countList(username);
		mav.addObject("blogTypeCountList",blogTypeCountList);
		
		
		//最新评论
		Map<String,Object> map1=new HashMap<String,Object>();
		map1.put("username", username);
		List<UComment> commentList = commentService.findCommentByTime(map1);
		mav.addObject("commentList",commentList);
		
		//类别
		List<SType> typeList = typeService.typelist();
		mav.addObject("typeList",typeList);
		
		//友情链接
		Map<String,Object> linkmap=new HashMap<String,Object>();
		linkmap.put("username", username);
		List<ULink> linkList = linkService.list(linkmap);
		mav.addObject("linkList",linkList);
		
		mav.setViewName("mainTemp");
		return mav;
	}

	
	/**
	 * 管理员
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/adminlist")
	public ModelAndView index1(@RequestParam(value="page",required=false)String page,
			@RequestParam(value="typeid",required=false)String typeid,
			HttpServletRequest request)throws Exception{
		ModelAndView mav=new ModelAndView();
		if(StringUtil.isEmpty(page)){
			page="1";
		}


		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("typeid", typeid);
		
		
		
		List<UBlog> blogList=blogService.list(map);
		for(UBlog blog:blogList){
			List<String> imagesList=blog.getImagesList();
			String blogInfo=blog.getContent();
			Document doc=Jsoup.parse(blogInfo);
			Elements jpgs=doc.select("img[src$=.jpg]"); //　查找扩展名是jpg的图片
			for(int i=0;i<jpgs.size();i++){
				Element jpg=jpgs.get(i);
				imagesList.add(jpg.toString());
				if(i==2){
					break;
				}
			}
		}
		mav.addObject("blogList", blogList);
		StringBuffer param=new StringBuffer(); // 查询参数
		if(StringUtil.isNotEmpty(typeid)){
			param.append("typeid="+typeid);
		}

		
		mav.addObject("pageCode",PageUtil.genPagination(request.getContextPath()+"/adminlist", blogService.getTotal(map), Integer.parseInt(page), 10, param.toString()));
		mav.addObject("mainPage", "blog/list.jsp");
		mav.addObject("pageTitle","博客云");
		
		//类别
		List<SType> typeList = typeService.typelist();
		mav.addObject("typeList",typeList);
				
		//查询最新注册的用户
		List<UUser> userList=userService.finduUserByTime();
		mav.addObject("userList",userList);
		
		//最新评论
		Map<String,Object> map1=new HashMap<String,Object>();
		List<UComment> commentList = commentService.findCommentByTime(map1);
		mav.addObject("commentList",commentList);
		
		mav.setViewName("index");
		return mav;
	}
	

	//用户登录控制
	//接收表单，包含用户名和密码
		@RequestMapping(value="/login",method=RequestMethod.POST)
		@ResponseBody
		public  TaotaoResult userLogin(String username,String password,
				HttpServletRequest  request,HttpServletResponse response){
		
			try {
				TaotaoResult result = userService.userLogin(username, password,request,response);
				
				if(result.getStatus()==200){
					request.getSession().setAttribute("username", username);
					CookieUtils.setCookie(request, response, "cusername", username);
				}
				
				
				
				return result;
			} catch (Exception e) {	
				e.printStackTrace();
				return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
			}
		}
	
	
	
	

	

}
