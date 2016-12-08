package cn.tf.blog.controller.mall;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tf.blog.common.pojo.EUTreeNode;
import cn.tf.blog.service.mall.ItemCatService;


//商品分类管理
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	
	@Autowired
	private ItemCatService  itemCatService;
	
	@RequestMapping("/list")
	@ResponseBody
	private List<EUTreeNode>  getCatList(@RequestParam(value="id",defaultValue="0")Long parentId){
		List<EUTreeNode> list= itemCatService.getCatList(parentId);
		return list;
		
	}
	

}