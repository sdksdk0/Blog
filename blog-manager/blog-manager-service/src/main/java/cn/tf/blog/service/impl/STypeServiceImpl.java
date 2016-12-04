package cn.tf.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tf.blog.mapper.STypeMapper;
import cn.tf.blog.po.SType;
import cn.tf.blog.service.STypeService;

@Service
public class STypeServiceImpl  implements STypeService{

	@Autowired
	private STypeMapper  typeMapper;
	
	
	@Override
	public List<SType> typelist() {
		
		return typeMapper.selectByExample(null);
	}
	
	

}
