package cn.tf.blog.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tf.blog.mapper.STypeMapper;
import cn.tf.blog.po.SType;
import cn.tf.blog.po.STypeExample;
import cn.tf.blog.po.STypeExample.Criteria;
import cn.tf.blog.service.STypeService;

@Service
public class STypeServiceImpl  implements STypeService{

	@Autowired
	private STypeMapper  typeMapper;
	
	
	@Override
	public List<SType> typelist() {
		
		STypeExample example=new STypeExample();
		Criteria criteria = example.createCriteria();
		criteria.andStateEqualTo(1+"");
		
		return typeMapper.selectByExample(example);
	}


	@Override
	public List<SType> list(Map<String, Object> map) {
		
		return typeMapper.list(map);
	}


	@Override
	public Long getTotal(Map<String, Object> map) {
		
		return typeMapper.getTotal(map);
	}


	@Override
	public int add(SType blogType) {
		blogType.setTypeid(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
		return typeMapper.insert(blogType);
	}


	@Override
	public int update(SType blogType) {
		
		return typeMapper.updateByPrimaryKeySelective(blogType);
	}


	@Override
	public int delete(String typeid) {
		SType blogType=new SType();
		blogType.setTypeid(typeid);
		blogType.setState(0+"");
		
		return typeMapper.updateByPrimaryKeySelective(blogType);
		
	}
	
	

}
