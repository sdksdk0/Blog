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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.tf.blog.common.pojo.PageBean;
import cn.tf.blog.common.util.PageUtil;
import cn.tf.blog.common.util.StringUtil;
import cn.tf.blog.po.SType;
import cn.tf.blog.po.UBlog;
import cn.tf.blog.po.UBlogtype;
import cn.tf.blog.po.UComment;
import cn.tf.blog.po.UUser;
import cn.tf.blog.service.BlogService;
import cn.tf.blog.service.CommentService;
import cn.tf.blog.service.RedisService;
import cn.tf.blog.service.STypeService;
import cn.tf.blog.service.UserService;

/**
 * 主页Controller
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/")
public class IndexController {

	@Resource
	private BlogService blogService;

	@Autowired
	private STypeService typeService;
	@Autowired
	private UserService userService;
	@Autowired
	private CommentService commentService;

	// 打开首页
	@RequestMapping("/")
	public String showIndex() {
		return "redirect:/index";
	}

	/**
	 * 请求主页
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/index")
	public ModelAndView index(
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "blogtypeid", required = false) String blogtypeid,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "releaseDateStr", required = false) String releaseDateStr,
			HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		if (StringUtil.isEmpty(page)) {
			page = "1";
		}

		PageBean pageBean = new PageBean(Integer.parseInt(page), 10);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("blogtypeid", blogtypeid);
		map.put("state", 1);
		if (releaseDateStr != "" && releaseDateStr != null) {
			map.put("releaseDateStr",
					new String(releaseDateStr.getBytes("iso-8859-1"), "utf-8"));
		}
		map.put("username", username);

		List<UBlog> blogList = blogService.list(map);
		for (UBlog blog : blogList) {
			List<String> imagesList = blog.getImagesList();
			String blogInfo = blog.getContent();
			Document doc = Jsoup.parse(blogInfo);
			Elements jpgs = doc.select("img[src$=.jpg]"); // 　查找扩展名是jpg的图片
			for (int i = 0; i < jpgs.size(); i++) {
				Element jpg = jpgs.get(i);
				imagesList.add(jpg.toString());
				if (i == 2) {
					break;
				}
			}
		}
		mav.addObject("blogList", blogList);
		StringBuffer param = new StringBuffer(); // 查询参数
		if (StringUtil.isNotEmpty(blogtypeid)) {
			param.append("blogtypeid=" + blogtypeid + "&");
		}
		if (StringUtil.isNotEmpty(releaseDateStr)) {
			param.append("releaseDateStr="
					+ new String(releaseDateStr.getBytes("iso-8859-1"), "utf-8")
					+ "&");
		}
		if (StringUtil.isNotEmpty(username)) {
			param.append("username=" + username + "&");
		}

		mav.addObject("pageCode", PageUtil.genPagination(
				request.getContextPath() + "/index.html",
				blogService.getTotal(map), Integer.parseInt(page), 10,
				param.toString()));
		mav.addObject("mainPage", "blog/list.jsp");
		mav.addObject("pageTitle", "博客云");

		// 类别
		List<SType> typeList = typeService.typelist();
		mav.addObject("typeList", typeList);

		// 查询最新注册的用户
		List<UUser> userList = userService.finduUserByTime();
		mav.addObject("userList", userList);

		// 最新评论

		Map<String, Object> map1 = new HashMap<String, Object>();
		List<UComment> commentList = commentService.findCommentByTime(map1);
		mav.addObject("commentList", commentList);

		mav.setViewName("index");
		return mav;
	}

	@RequestMapping("/mall")
	public ModelAndView download() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("mainPage", "system/mall.jsp");
		mav.addObject("pageTitle", "博客云");

		// 类别
		List<SType> typeList = typeService.typelist();
		mav.addObject("typeList", typeList);

		// 查询最新注册的用户
		List<UUser> userList = userService.finduUserByTime();
		mav.addObject("userList", userList);

		// 最新评论

		Map<String, Object> map1 = new HashMap<String, Object>();
		List<UComment> commentList = commentService.findCommentByTime(map1);
		mav.addObject("commentList", commentList);

		mav.setViewName("index");
		return mav;
	}

	// 导航栏查询类别
	@RequestMapping("/typelist")
	public String typelist(Model model) {

		// 类别
		List<SType> typeList = typeService.typelist();
		model.addAttribute("typeList", typeList);
		return "common/menu";

	}

}
