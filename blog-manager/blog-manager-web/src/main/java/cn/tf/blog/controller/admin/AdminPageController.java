package cn.tf.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminPageController {
	

	
	//去管理员界面
	@RequestMapping("/admin")
	public String showPage(@PathVariable String page){
		return "admin/main";
	}
	//展示其他页面
	@RequestMapping("/admin/{page}")
	public String showOtherPage(@PathVariable String page){
		return "admin/"+page;
	}

	
   
}
