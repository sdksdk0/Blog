package cn.tf.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
	public int changScore(Long id,Long price,String username,HttpSession session){
		
		session.setAttribute("username", username);
		//判断积分是否足够

		UScore score=new UScore();
		score.setMoney(price);
		score.setUsername(username);
		
		//通过用户名查询其账户下的积分数
		List<UScore>  scorelist=scoreService.selectScore(username);
		UScore uScore2 = scorelist.get(0);
		if(uScore2.getMoney()<price){
			return 0;
		}else{
			int result=	scoreService.updateScore(score);
			return result;
		}
	
	}
	
	
	//去购买积分页面
	@RequestMapping("/buy/{itemId}")
	public ModelAndView showItem(@PathVariable Long itemId, Model model) {
		ModelAndView mav = new ModelAndView();
		ItemInfo item = itemPortalService.getItemById(itemId);

		mav.addObject("item", item);
		// 类别
		List<SType> typeList = typeService.typelist();
		model.addAttribute("typeList", typeList);
		
		mav.addObject("mainPage", "mall/score.jsp");
		mav.setViewName("mall");

		return mav;
	}
	
	
	

}
