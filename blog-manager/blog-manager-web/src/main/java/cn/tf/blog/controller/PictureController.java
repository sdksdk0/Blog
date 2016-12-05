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
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String  pictureUpload(MultipartFile  uploadFile){
		
		Map result=pictureService.uploadPicture(uploadFile);
		System.out.println(result);
		//把result转换为json格式的字符串
		
		String json=JsonUtils.objectToJson(result);
		 
		return json;
	}
	
	@RequestMapping("/user/blog/pic/upload")
	@ResponseBody
	public String  pictureUpload1(MultipartFile  uploadFile){
		
		Map result=pictureService.uploadPicture(uploadFile);
		System.out.println(result);
		//把result转换为json格式的字符串
		
		String json=JsonUtils.objectToJson(result);
		 
		return json;
	}


}
