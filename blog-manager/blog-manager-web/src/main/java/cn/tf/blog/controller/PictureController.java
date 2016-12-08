package cn.tf.blog.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.tf.blog.common.util.JsonUtils;
import cn.tf.blog.service.PictureService;



@Controller
public class PictureController {
	
	@Autowired
	private PictureService  pictureService;
	
	@RequestMapping("/user/pic/upload")
	@ResponseBody
	public String  pictureUpload(MultipartFile  uploadFile){
		
		Map result=pictureService.uploadPicture(uploadFile);
		
		//把result转换为json格式的字符串
		String json=JsonUtils.objectToJson(result);
		 
		return json;
	}
	
	@RequestMapping("/user/blog/pic/upload")
	@ResponseBody
	public String  pictureUpload1(MultipartFile  uploadFile){
		
		Map result=pictureService.uploadPicture(uploadFile);
		
		//把result转换为json格式的字符串
		String json=JsonUtils.objectToJson(result);
		 
		return json;
	}
	
	
	@RequestMapping("/user/blogger/pic/upload")
	@ResponseBody
	public String  pictureUpload2(MultipartFile  uploadFile){
		
		Map result=pictureService.uploadPicture(uploadFile);
		
		//把result转换为json格式的字符串
		String json=JsonUtils.objectToJson(result);
		 
		return json;
	}
	
	@RequestMapping("/admin/pic/upload")
	@ResponseBody
	public String  pictureadmin(MultipartFile  uploadFile){
		
		Map result=pictureService.uploadPicture(uploadFile);
		
		//把result转换为json格式的字符串
		String json=JsonUtils.objectToJson(result);
		 
		return json;
	}

	
	@RequestMapping("/admin/mall/pic/upload")
	@ResponseBody
	public String  pictureadmin2(MultipartFile  uploadFile){
		
		Map result=pictureService.uploadPicture(uploadFile);
		
		//把result转换为json格式的字符串
		String json=JsonUtils.objectToJson(result);
		 
		return json;
	}
}
