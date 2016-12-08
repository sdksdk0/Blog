package cn.tf.blog.service.mall;

import cn.tf.blog.common.pojo.EUDResult;
import cn.tf.blog.common.util.TaotaoResult;
import cn.tf.blog.po.mall.TbItem;
import cn.tf.blog.po.mall.TbItemDesc;
import cn.tf.blog.po.mall.TbItemParamItem;



public interface  ItemService {
	TbItem getItemById(long itemId);

	EUDResult getItemList(int page,int rows);
	
	TaotaoResult createItem(TbItem item,String desc,String itemparam) throws Exception;

	TaotaoResult deleteItem(String ids);

	TbItemDesc listItemDesc(Long id);

	/**
	 * 
	 * 更新商品
	 * @param item
	 * @param desc
	 * @param itemParamss
	 * @return
	 */
	
	TaotaoResult updateItem(TbItem item, TbItemDesc desc, TbItemParamItem itemParamss);

	/**
	 * 
	 * 下架商品
	 * @param ids
	 * @return
	 */
	TaotaoResult instockItem(Long id);

	//上架
	TaotaoResult reshelfItem(long id);
}
