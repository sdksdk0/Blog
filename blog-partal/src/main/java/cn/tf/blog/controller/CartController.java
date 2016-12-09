package cn.tf.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.tf.blog.common.util.TaotaoResult;
import cn.tf.blog.po.SType;
import cn.tf.blog.po.UComment;
import cn.tf.blog.po.UUser;
import cn.tf.blog.pojo.CartItem;
import cn.tf.blog.pojo.ItemInfo;
import cn.tf.blog.service.CartService;
import cn.tf.blog.service.CommentService;
import cn.tf.blog.service.STypeService;
import cn.tf.blog.service.UserService;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	@Autowired
	private STypeService typeService;
	@Autowired
	private UserService userService;
	@Autowired
	private CommentService commentService;

	@RequestMapping("/add/{itemId}")
	public ModelAndView addCartItem(@PathVariable Long itemId,
			@RequestParam(defaultValue = "1") Integer num,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		TaotaoResult result = cartService.addCartItem(itemId, num, request,
				response);
		ModelAndView mav = new ModelAndView();

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

		mav.addObject("mainPage", "mall/cartSuccess.jsp");
		mav.setViewName("index");

		return mav;
	}

	@RequestMapping("/success")
	public String showSuccess() {
		return "cartSuccess";
	}

	@RequestMapping("/cart")
	public ModelAndView showCart(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<CartItem> list = cartService.getCartItemList(request, response);

		ModelAndView mav = new ModelAndView();
		model.addAttribute("cartList", list);
		// 类别
		List<SType> typeList = typeService.typelist();
		model.addAttribute("typeList", typeList);
		

		mav.addObject("mainPage", "mall/cart.jsp");
		mav.setViewName("mall");

		return mav;
	}

	@RequestMapping("/delete/{itemId}")
	public String deleteCartItem(@PathVariable Long itemId,
			HttpServletRequest request, HttpServletResponse response) {
		cartService.deleteCartItem(itemId, request, response);
		return "redirect:/cart/cart.html";
	}

}
