package cn.tf.blog.order.service;

import java.util.List;

import cn.tf.blog.common.util.TaotaoResult;
import cn.tf.blog.po.mall.TbOrder;
import cn.tf.blog.po.mall.TbOrderItem;
import cn.tf.blog.po.mall.TbOrderShipping;



public interface OrderService {
	
	TaotaoResult createOrder(TbOrder order,List<TbOrderItem>  itemList,TbOrderShipping orderShipping);

}
