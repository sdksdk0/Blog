package cn.tf.blog.service;

import cn.tf.blog.po.UScore;

public interface ScoreService {
	
	public int add(UScore score);

	public int  update(UScore score);

	public UScore findByUsername(String username);

}
