package cn.tf.blog.pojo;

import java.util.List;

import cn.tf.blog.po.mall.TbOrder;
import cn.tf.blog.po.mall.TbOrderItem;
import cn.tf.blog.po.mall.TbOrderShipping;


public class Order extends TbOrder{
	private List<TbOrderItem>  orderItems;
	private TbOrderShipping orderShipping;
	public List<TbOrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<TbOrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public TbOrderShipping getOrderShipping() {
		return orderShipping;
	}
	public void setOrderShipping(TbOrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}
	
	

}
