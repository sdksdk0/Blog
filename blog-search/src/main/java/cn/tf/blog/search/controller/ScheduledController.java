package cn.tf.blog.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tf.blog.common.util.TaotaoResult;
import cn.tf.blog.search.service.ItemService;

@Component
@Controller
public class ScheduledController {

	@Autowired
	private ItemService itemService;
	
	/**
	 * 导入商品数据库到索引库
	 */
	//@Scheduled(cron = "0 15 19 * * ?")   //每天凌晨两点执行
	@Scheduled(cron = "0 0 2 * * ?")   //每天凌晨两点执行
	@RequestMapping("/import")
	public void importAllItems() {
		System.out.println("开始执行");
		itemService.importAllItems();
		System.out.println("执行结束");
	}
}


