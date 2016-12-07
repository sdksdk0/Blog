package cn.tf.blog.service;

import java.util.List;
import java.util.Map;

import cn.tf.blog.po.SType;


public interface STypeService {
	
	//查询所有的分类
		public List<SType> typelist();

		public List<SType> list(Map<String, Object> map);

		public Long getTotal(Map<String, Object> map);

		public int add(SType blogType);

		public int update(SType blogType);

		public int delete(String string);

}
