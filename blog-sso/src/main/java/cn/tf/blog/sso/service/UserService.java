package cn.tf.blog.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.tf.blog.po.UUser;
import cn.tf.blog.common.util.TaotaoResult;



public interface UserService {
	
	TaotaoResult checkData(String content,Integer type);
	TaotaoResult createUser(HttpServletRequest request, UUser user,HttpSession session);
	TaotaoResult userLogin(String username,String password,HttpServletRequest  request,HttpServletResponse response);

	TaotaoResult getUserByToken(String token);
	
	/*
	 * 
	 * 退出登录
	 */
	TaotaoResult userLogout(String token);
	//激活
	boolean activeUser(String usId);

}
