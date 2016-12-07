package cn.tf.blog.controller.admin;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.tf.blog.common.util.DateUtil;
import cn.tf.blog.common.util.ResponseUtil;
import cn.tf.blog.po.SAdmin;
import cn.tf.blog.po.UUser;
import cn.tf.blog.service.AdminService;
import cn.tf.blog.service.BloggerService;



import net.sf.json.JSONObject;

/**
 * 管理员博主Controller层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/admin/blogger")
public class AdminerController {

	@Resource
	private AdminService adminService;
	
	/** 
	 * 修改博主信息
	 * @param file1
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public String save(SAdmin blogger,HttpServletRequest request,HttpServletResponse response)throws Exception{
	
		int resultTotal=adminService.update(blogger);
		StringBuffer result=new StringBuffer();
		if(resultTotal>0){
			result.append("<script language='javascript'>alert('修改成功！');</script>");
		}else{
			result.append("<script language='javascript'>alert('修改失败！');</script>");
		}
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 查询博主信息
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/find")
	public String find(String username,HttpServletResponse response)throws Exception{
		SAdmin blogger=adminService.find(username);
		JSONObject jsonObject=JSONObject.fromObject(blogger);
		ResponseUtil.write(response, jsonObject);
		return null;
	}
	

	

}
