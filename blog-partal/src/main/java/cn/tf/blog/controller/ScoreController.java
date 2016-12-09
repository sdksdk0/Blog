package cn.tf.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.tf.blog.common.util.TaotaoResult;
import cn.tf.blog.po.SType;
import cn.tf.blog.po.UComment;
import cn.tf.blog.po.UScore;
import cn.tf.blog.po.UUser;
import cn.tf.blog.pojo.ItemInfo;
import cn.tf.blog.service.CommentService;
import cn.tf.blog.service.ItemPortalService;
import cn.tf.blog.service.STypeService;
import cn.tf.blog.service.ScoreService;
import cn.tf.blog.service.UserService;

//兑换积分
@Controller
@RequestMapping("/score")
public class ScoreController {
	
	@Autowired
	private ScoreService scoreService;
	@Autowired
	private ItemPortalService itemPortalService;
	@Autowired
	private STypeService typeService;
	@Autowired
	private UserService userService;
	@Autowired
	private CommentService commentService;
	
	
	//兑换K币
	@RequestMapping("/redure")
	@ResponseBody
	public int changScore(Long id,Long price,String username){
		
		//判断积分是否足够

		UScore score=new UScore();
		score.setMoney(price);
		score.setUsername(username);
		
		//scoreService.selectScore();
		
		int result=	scoreService.updateScore(score);
		System.out.println(result);
		
		return result;
	}
	
	
	//去购买积分页面
	@RequestMapping("/buy")
	public ModelAndView showScore(Model model){
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

		mav.addObject("mainPage", "mall/score.jsp");
		mav.setViewName("index");

		return mav;
		
	}
	
	
	

}
