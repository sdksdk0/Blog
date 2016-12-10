package cn.tf.blog.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tf.blog.common.util.TaotaoResult;
import cn.tf.blog.po.UUser;



public interface UserService {
	
	UUser  getUserByToken(String token);

	List<UUser> finduUserByTime();


	TaotaoResult userLogin(String username, String password,
			HttpServletRequest request, HttpServletResponse response);

}
