package cn.tf.blog.service;



import java.util.List;

import cn.tf.blog.po.UScore;

public interface ScoreService {
	
	public int add(UScore score);

	public int  update(UScore score);

	public UScore findByUsername(String username);

	//兑换积分
	public int updateScore(UScore score);

	public List<UScore> selectScore(String username);

}
