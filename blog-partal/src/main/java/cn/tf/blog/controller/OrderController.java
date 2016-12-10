package cn.tf.blog.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.tf.blog.common.util.CookieUtils;
import cn.tf.blog.po.SType;
import cn.tf.blog.pojo.CartItem;
import cn.tf.blog.pojo.Order;
import cn.tf.blog.service.CartService;
import cn.tf.blog.service.OrderService;
import cn.tf.blog.service.STypeService;



@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private STypeService typeService;
	
	@RequestMapping("/order-cart")
	public ModelAndView showOrderCart(HttpServletRequest request,String username,HttpServletResponse response,Model  model,HttpSession session){
		ModelAndView mav = new ModelAndView();
		
		
		if(session.getAttribute("username")!=null && session.getAttribute("username")==""){
			model.addAttribute("username",username);
		}else{
			session.setAttribute("username", username);
			model.addAttribute("username",username);
		}
		
		
		model.addAttribute("username",username);
		
		List<CartItem> list = cartService.getCartItemList(request, response);
		
		//传递给页面
		model.addAttribute("cartList",list);
		
		// 类别
		List<SType> typeList = typeService.typelist();
		model.addAttribute("typeList", typeList);
		
		mav.addObject("mainPage", "mall/order-cart.jsp");
		mav.setViewName("mall");

		return mav;
	}
	
	
	@RequestMapping("/create")
	public ModelAndView createOrder(Order  order,Model model,HttpSession session,HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		
		String username=(String) session.getAttribute("username");
		order.setUserId(username);
		
		// 类别
		List<SType> typeList = typeService.typelist();
		model.addAttribute("typeList", typeList);
		
		try {
			String orderId = orderService.createOrder(order);
			model.addAttribute("orderId",orderId);
			model.addAttribute("payment",order.getPayment());
			model.addAttribute("date",new DateTime().plusDays(3).toString("yyyy-MM-dd"));
			model.addAttribute("username");
			
			
			CookieUtils.setCookie(request, response, "TT_CART", null);
			
			
			mav.addObject("mainPage", "mall/success.jsp");
			mav.setViewName("mall");

			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message","订单创建异常!");
			
			mav.addObject("mainPage", "mall/exception.jsp");
			mav.setViewName("mall");

			return mav;
		}
	}
}
