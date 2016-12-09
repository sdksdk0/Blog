package cn.tf.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tf.blog.common.util.TaotaoResult;
import cn.tf.blog.mapper.UScoreMapper;
import cn.tf.blog.po.UScore;
import cn.tf.blog.po.UScoreExample;
import cn.tf.blog.service.ScoreService;

@Service
public class ScoreServiceImpl implements ScoreService{

	@Autowired
	private UScoreMapper scoreMapper;
	
	@Override
	public int add(UScore score) {
		
		return scoreMapper.insert(score);
	}

	@Override
	public int update(UScore score) {
		return scoreMapper.update(score);
		
	}

	@Override
	public UScore findByUsername(String username) {
		return scoreMapper.findByUsername(username);
	}

	@Override
	public int updateScore(UScore score) {
		try {
			return scoreMapper.updateScore(score);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
	

}
