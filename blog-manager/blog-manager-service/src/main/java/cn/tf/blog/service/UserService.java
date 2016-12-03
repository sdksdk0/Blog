package cn.tf.blog.service;

import cn.tf.blog.po.UUser;



public interface UserService {
	
	UUser  getUserByToken(String token);

}
