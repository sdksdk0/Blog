package cn.tf.blog.sso.controller;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sun.xml.internal.xsom.impl.scd.Iterators.Map;

import net.sf.json.JSONObject;

import cn.tf.blog.po.UScore;
import cn.tf.blog.po.UUser;
import cn.tf.blog.common.util.ExceptionUtil;
import cn.tf.blog.common.util.ResponseUtil;
import cn.tf.blog.common.util.TaotaoResult;

import cn.tf.blog.sso.dao.JedisClient;
import cn.tf.blog.sso.service.ScoreService;
import cn.tf.blog.sso.service.UserService;



@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private ScoreService scoreService;
	
	@Autowired
	private JedisClient jedisClient;
	
	
	
	@RequestMapping("/check/{param}/{type}")
	@ResponseBody
	public Object checkData(@PathVariable String param, @PathVariable Integer type, String callback) {
		
		TaotaoResult result = null;
		
		//参数有效性校验
		if (StringUtils.isBlank(param)) {
			result = TaotaoResult.build(400, "校验内容不能为空");
		}
		if (type == null) {
			result = TaotaoResult.build(400, "校验内容类型不能为空");
		}
		if (type != 1 && type != 2 && type != 3 ) {
			result = TaotaoResult.build(400, "校验内容类型错误");
		}
		//校验出错
		if (null != result) {
			if (null != callback) {
				MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
				mappingJacksonValue.setJsonpFunction(callback);
				return mappingJacksonValue;
			} else {
				return result; 
			}
		}
		//调用服务
		try {
			result = userService.checkData(param, type);
			
		} catch (Exception e) {
			result = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		if (null != callback) {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		} else {
			return result; 
		}
	}
	

	
	
	
	//创建用户
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult   createUser(HttpServletRequest request, UUser user,HttpSession session){ 
		try {
			TaotaoResult result = userService.createUser(request,user,session);
			
			
			//创建用户后为用户激活创建积分系统
			
			UScore score=new UScore();

			score.setScoreid(UUID.randomUUID().toString());
			score.setUsername(user.getUsername());
			//创建用户奖励50个积分
			score.setScore(50);
			score.setMoney(Long.parseLong(0+""));
			
			scoreService.add(score);
			

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	
	//激活用户
	@RequestMapping("/active/{usId}/{activeCode}")
	public String active(HttpServletRequest request,@PathVariable("usId") String  usId,@PathVariable("activeCode") String activeCode,HttpSession session){
		
		//从redis中取出来
		String srcActiveCode=jedisClient.get("BLOG_ACTIVECODE_KEY:"+usId);
		
		if(((String) srcActiveCode).intern()==activeCode.intern()){
			if(userService.activeUser(usId)){

				return "/login";
			}
		}
		
		return "/regirest";
	}
	
	
	//接收表单，包含用户名和密码
	@RequestMapping(value="/login")
	@ResponseBody
	public  TaotaoResult userLogin(String username,String password,
			HttpServletRequest  request,HttpServletResponse response){
	
		try {
			TaotaoResult result = userService.userLogin(username, password,request,response);
			
			return result;
		} catch (Exception e) {	
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	
	//接收token调用service返回用户信息 
	
	@RequestMapping("/token/{token}")
	@ResponseBody
	public Object getUserByToken(@PathVariable  String token,String callback){
		
		TaotaoResult result=null;
		
		try {
			result = userService.getUserByToken(token);
			
		} catch (Exception e) {	
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		//判断是否为json调用
		if(StringUtils.isBlank(callback)){
			return result;
		}else{
			MappingJacksonValue  mappingJacksonValue=new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}
	}
	
	//退出登录
	@RequestMapping("/logout/{token}")
	@ResponseBody
	public Object userLogout(@PathVariable String token, String callback) {
		TaotaoResult result = null;
		try {
			result = userService.userLogout(token);
		} catch (Exception e) {
			e.printStackTrace();
			result = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		

		if (StringUtils.isBlank(callback)) {
			return result;
		} else {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(
					result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}
	}
	
	//修改密码
	@RequestMapping("/modifyPassword")
	public String modifyPassword(String newPassword,String id,HttpServletResponse response)throws Exception{

		int resultTotal=userService.update(newPassword,id);
		
		//跨域请求错误
		JSONObject result=new JSONObject();
		if(resultTotal>0){
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		ResponseUtil.write(response, result);
		return null;
	}
	
	
	
	
}

