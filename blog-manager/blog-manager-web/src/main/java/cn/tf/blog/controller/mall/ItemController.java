package cn.tf.blog.controller.mall;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tf.blog.common.pojo.EUDResult;
import cn.tf.blog.common.util.TaotaoResult;
import cn.tf.blog.po.mall.TbItem;
import cn.tf.blog.po.mall.TbItemDesc;
import cn.tf.blog.po.mall.TbItemParamItem;
import cn.tf.blog.service.mall.ItemService;




//商品管理

@Controller
public class ItemController {
	
	
	@Autowired
	private ItemService  itemService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable  Long itemId){
		TbItem tbItem=itemService.getItemById(itemId);
		return tbItem;
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EUDResult getItemList(Integer page,Integer rows){
		EUDResult result = itemService.getItemList(page, rows);
		return result;
	}
	
	
	//接收表单中的内容，使用一个pojo接收表单内容
	@RequestMapping(value="/item/save",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult createItem(TbItem item,String desc,String itemParams) throws Exception{
		TaotaoResult result=itemService.createItem(item,desc,itemParams);
		return result;
	}
	
	
	//删除商品
	@RequestMapping("/rest/item/delete")
	@ResponseBody
	public TaotaoResult  deleteItem(String ids){
		return itemService.deleteItem(ids);
		
	}
	
	//商品描述
	@RequestMapping("/rest/item/query/item/desc/{id}")
	@ResponseBody
	public TbItemDesc listItemDesc(@PathVariable Long id) {
		return itemService.listItemDesc(id);
	}
	
	//更新
	@RequestMapping(value="/rest/item/update",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult updateItem(TbItem item, TbItemDesc itemDesc, TbItemParamItem itemParams){
		TaotaoResult result=itemService.updateItem(item,itemDesc,itemParams);
		return result;
	}
	
	//下架
	@RequestMapping("/rest/item/instock")
	@ResponseBody
	public TaotaoResult  instockItem(String ids){
		
		String[] idsStr = ids.split(",");
		JSONObject result = new JSONObject();
		for (int i = 0; i < idsStr.length; i++) {
			itemService.instockItem((Long.parseLong(idsStr[i])));	
		}

		return TaotaoResult.ok();
	}
	
	//上架
	@RequestMapping("/rest/item/reshelf")
	@ResponseBody
	public TaotaoResult  reshelfItem(String ids){
		
		String[] idsStr = ids.split(",");
		JSONObject result = new JSONObject();
		for (int i = 0; i < idsStr.length; i++) {
			itemService.reshelfItem((Long.parseLong(idsStr[i])));	
		}

		return TaotaoResult.ok();
	}

}
