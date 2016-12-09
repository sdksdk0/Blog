package cn.tf.blog.service;

import cn.tf.blog.common.util.TaotaoResult;
import cn.tf.blog.pojo.ItemInfo;




public interface ItemService {
	ItemInfo  getItemById(long itemId);
	
	String getItemDescById(long itemId);
	
	String getItemParam(long itemId);

}
