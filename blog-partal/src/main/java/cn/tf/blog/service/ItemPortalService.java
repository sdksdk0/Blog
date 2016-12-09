package cn.tf.blog.service;


import cn.tf.blog.pojo.ItemInfo;




public interface ItemPortalService {
	ItemInfo  getItemById(long itemId);
	
	String getItemDescById(long itemId);
	
	String getItemParam(long itemId);

}
