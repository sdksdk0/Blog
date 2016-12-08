package cn.tf.blog.service;

import java.util.List;

import cn.tf.blog.po.UUser;



public interface UserService {
	
	UUser  getUserByToken(String token);

	List<UUser> finduUserByTime();

}
