package cn.tf.blog.service.mall.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tf.blog.common.pojo.EUDResult;
import cn.tf.blog.common.util.ExceptionUtil;
import cn.tf.blog.common.util.IDUtils;
import cn.tf.blog.common.util.TaotaoResult;
import cn.tf.blog.mapper.mall.TbItemDescMapper;
import cn.tf.blog.mapper.mall.TbItemMapper;
import cn.tf.blog.mapper.mall.TbItemParamItemMapper;
import cn.tf.blog.po.mall.TbItem;
import cn.tf.blog.po.mall.TbItemDesc;
import cn.tf.blog.po.mall.TbItemDescExample;
import cn.tf.blog.po.mall.TbItemExample;
import cn.tf.blog.po.mall.TbItemExample.Criteria;
import cn.tf.blog.po.mall.TbItemParamItem;
import cn.tf.blog.service.mall.ItemService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.fabric.xmlrpc.base.Data;


@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private TbItemMapper  itemMapper;
	
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	@Autowired
	private TbItemParamItemMapper  itemParamItemMapper;
	
	
	@Override
	public TbItem getItemById(long itemId) {
		//主键查询
		//TbItem item =itemMapper.selectByPrimaryKey(itemId);
		
		//根据条件查询
		TbItemExample example=new TbItemExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andIdEqualTo(itemId);
		
		List<TbItem> list = itemMapper.selectByExample(example);
		if(list!=null && list.size()>0){
			TbItem item=list.get(0);
			return item;
		}
		return null;
	
	}


	
	//商品列表查询
	@Override
	public EUDResult getItemList(int page, int rows) {
			TbItemExample example=new TbItemExample();
			PageHelper.startPage(page, rows);
			example.setOrderByClause("updated");
			List<TbItem> list = itemMapper.selectByExample(example);
	
			EUDResult result=new EUDResult();
			result.setRows(list);
			PageInfo<TbItem>  pageInfo=new PageInfo<>(list);
			result.setTotal(pageInfo.getTotal());
			return result;
	}



	@Override
	public TaotaoResult createItem(TbItem item,String desc,String itemparam) throws Exception {
		//生成商品id
		Long itemId=IDUtils.genItemId();
		item.setId(itemId);
		item.setStatus((byte)1);  //商品的状态
		item.setCreated(new Date());
		item.setUpdated(new Date());
		itemMapper.insert(item);
		//添加商品描述
		TaotaoResult result=insertItemDesc(itemId, desc);
		if(result.getStatus()!=200){
			throw new Exception();
		}
		//添加规格参数
		result=insertItemParamItem(itemId, itemparam);
		if(result.getStatus()!=200){
			throw new Exception();
		}
		return TaotaoResult.ok();
	}

	private TaotaoResult insertItemDesc(Long itemId,String desc){
		
		TbItemDesc itemDesc=new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		
		itemDescMapper.insert(itemDesc);
		return TaotaoResult.ok();
				
	}
	
	//添加规格参数
	private TaotaoResult insertItemParamItem(Long itemId,String itemparam){
		TbItemParamItem  itemParamItem=new TbItemParamItem();
		itemParamItem.setCreated(new Date());
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemparam);
		itemParamItem.setUpdated(new Date());
		
		itemParamItemMapper.insert(itemParamItem);
		return TaotaoResult.ok();
		
	}



	//删除商品
	@Override
	public TaotaoResult deleteItem(String ids) {

		
		try {
			String[] idsArray = ids.split(",");
			List<Long> values = new ArrayList<Long>();
			for(String id : idsArray) {
				values.add(Long.parseLong(id));
			}
			TbItemExample e = new TbItemExample();
			TbItemExample.Criteria c = e.createCriteria();
			c.andIdIn(values);
			itemMapper.deleteByExample(e);
			
			
			TbItemDescExample de = new TbItemDescExample();
			cn.tf.blog.po.mall.TbItemDescExample.Criteria dc = de.createCriteria();
			dc.andItemIdIn(values);
			itemDescMapper.deleteByExample(de);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return TaotaoResult.ok(); 
	}



	//加载商品描述
	@Override
	public TbItemDesc listItemDesc(Long id) {
		return itemDescMapper.selectByPrimaryKey(id);
	}


	//更新
	@Override
	public TaotaoResult updateItem(TbItem item, TbItemDesc desc, TbItemParamItem itemParams) {
		try {
			//更新商品
			TbItemExample e = new TbItemExample();
			Criteria c = e.createCriteria();
			c.andIdEqualTo(item.getId());
			
			TbItem tbItem = itemMapper.selectByPrimaryKey(item.getId());
			item.setCreated(tbItem.getCreated());
			item.setUpdated(new Date());
			item.setStatus((byte)1);
			itemMapper.updateByExample(item, e);
			
			//更新商品描述
			TbItemDescExample de = new TbItemDescExample();
			cn.tf.blog.po.mall.TbItemDescExample.Criteria criteria = de.createCriteria();
			criteria.andItemIdEqualTo(item.getId());
			desc.setItemId(item.getId());
			
			desc.setCreated(tbItem.getCreated());
			desc.setUpdated(new Date());
			itemDescMapper.updateByExample(desc, de);
			
			//更新规格
			/*TaotaoResult result=updateItemParamItem(itemId, itemparam);
			if(result.getStatus()!=200){
				throw new Exception();
			}
			return TaotaoResult.ok();*/
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return TaotaoResult.ok();
		
	}


	//下架商品
	@Override
	public TaotaoResult instockItem(Long id) {
		
		
			TbItem item=new TbItem();
			item.setId(id);
			item.setStatus((byte)2);
			
			itemMapper.updateByPrimaryKeySelective(item);
			
			return TaotaoResult.ok();
		
		
	}



	@Override
	public TaotaoResult reshelfItem(long id) {
		TbItem item=new TbItem();
		item.setId(id);
		item.setStatus((byte)1);
		
		itemMapper.updateByPrimaryKeySelective(item);
		
		return TaotaoResult.ok();
	}
	

}
