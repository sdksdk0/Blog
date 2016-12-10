package cn.tf.blog.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.tf.blog.common.util.HttpClientUtil;
import cn.tf.blog.common.util.JsonUtils;
import cn.tf.blog.common.util.TaotaoResult;
import cn.tf.blog.pojo.Order;
import cn.tf.blog.service.OrderService;



@Service
public class OrderServiceImpl implements OrderService{

	@Value("${ORDER_BASE_URL}")
	private String ORDER_BASE_URL;
	@Value("${ORDER_CREATE_URL}")
	private String ORDER_CREATE_URL;
	
	@Override
	public String createOrder(Order order) {
		//调用order的服务
		String json = HttpClientUtil.doPostJson(ORDER_BASE_URL+ORDER_CREATE_URL, JsonUtils.objectToJson(order));
		
		TaotaoResult taotaoResult = TaotaoResult.format(json);
		
		if(taotaoResult.getStatus()==200){
			Object orderId = taotaoResult.getData();
			return orderId.toString();
		}
		return "";
	}


}
