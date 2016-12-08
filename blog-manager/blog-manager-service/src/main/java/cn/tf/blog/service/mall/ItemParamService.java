package cn.tf.blog.service.mall;

import cn.tf.blog.common.pojo.EUDResult;
import cn.tf.blog.common.util.TaotaoResult;
import cn.tf.blog.po.mall.TbItemParam;
import cn.tf.blog.po.mall.TbItemParamItem;



public interface ItemParamService {
	
	TaotaoResult getItemParamByCid(long cid);
	TaotaoResult insertItemParam(TbItemParam itemParam);
	
	EUDResult getItemList(int page,int rows);
	
	TbItemParamItem listParamDesc(Long id);
	/*
	 * 删除
	 */
	TaotaoResult deleteParam(String ids);
	
}
