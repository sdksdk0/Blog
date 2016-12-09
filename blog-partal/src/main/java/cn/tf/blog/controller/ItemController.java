package cn.tf.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.tf.blog.po.SType;
import cn.tf.blog.po.UComment;
import cn.tf.blog.po.UUser;
import cn.tf.blog.pojo.ItemInfo;
import cn.tf.blog.service.CommentService;
import cn.tf.blog.service.ItemPortalService;
import cn.tf.blog.service.STypeService;
import cn.tf.blog.service.UserService;

@Controller
public class ItemController {

	@Autowired
	private ItemPortalService itemPortalService;
	@Autowired
	private STypeService typeService;
	@Autowired
	private UserService userService;
	@Autowired
	private CommentService commentService;
	

	@RequestMapping("/item/{itemId}")
	public ModelAndView showItem(@PathVariable Long itemId, Model model) {
		ModelAndView mav = new ModelAndView();
		ItemInfo item = itemPortalService.getItemById(itemId);

		// System.out.println("title:"+item.getTitle());

		mav.addObject("item", item);
		// 类别
		List<SType> typeList = typeService.typelist();
		model.addAttribute("typeList", typeList);
		// 查询最新注册的用户
		List<UUser> userList = userService.finduUserByTime();
		mav.addObject("userList", userList);

		// 最新评论
		Map<String, Object> map1 = new HashMap<String, Object>();
		List<UComment> commentList = commentService.findCommentByTime(map1);
		mav.addObject("commentList", commentList);

		mav.addObject("mainPage", "mall/item.jsp");
		mav.setViewName("index");

		return mav;
	}

	@RequestMapping(value = "/item/desc/{itemId}", produces = MediaType.TEXT_HTML_VALUE
			+ ";charset=utf-8")
	@ResponseBody
	public String getItemDesc(@PathVariable Long itemId) {
		String string = itemPortalService.getItemDescById(itemId);
		return string;
	}

	@RequestMapping(value = "/item/param/{itemId}", produces = MediaType.TEXT_HTML_VALUE
			+ ";charset=utf-8")
	@ResponseBody
	public String getItemParam(@PathVariable Long itemId) {
		String string = itemPortalService.getItemParam(itemId);
		return string;
	}

}
