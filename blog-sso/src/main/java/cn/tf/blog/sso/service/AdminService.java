package cn.tf.blog.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.tf.blog.common.util.TaotaoResult;
import cn.tf.blog.po.SAdmin;

 

public interface AdminService {
	
	TaotaoResult checkData(String content,Integer type);
	
	TaotaoResult userLogin(String username,String password,HttpServletRequest  request,HttpServletResponse response);

	TaotaoResult getUserByToken(String token);
	int update(String newPassword, String id);
	TaotaoResult userLogout(String token);
}
 