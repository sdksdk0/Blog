package cn.tf.blog.rest.service;

import cn.tf.blog.common.util.TaotaoResult;



public interface ItemRestService {
	
	TaotaoResult  getItemBaseInfo(long itemId);
	TaotaoResult  getItemDesc(long itemId);
	
	TaotaoResult getItemParam(long itemId);
	
	
	
}
