package cn.tf.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.tf.blog.common.pojo.PageBean;
import cn.tf.blog.common.util.PageUtil;
import cn.tf.blog.common.util.StringUtil;
import cn.tf.blog.po.UBlog;
import cn.tf.blog.po.UBlogtype;
import cn.tf.blog.po.ULink;
import cn.tf.blog.po.UUser;
import cn.tf.blog.service.BlogService;
import cn.tf.blog.service.BlogTypeService;
import cn.tf.blog.service.BloggerService;
import cn.tf.blog.service.LinkService;



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
		
		
		//友情链接
		Map<String,Object> linkmap=new HashMap<String,Object>();
		linkmap.put("username", username);
		List<ULink> linkList = linkService.list(linkmap);
		mav.addObject("linkList",linkList);
		
		
		
		
		mav.setViewName("mainTemp");
		return mav;
	}


}
