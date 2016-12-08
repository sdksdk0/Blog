package cn.tf.blog.service.mall.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tf.blog.common.pojo.EUTreeNode;
import cn.tf.blog.mapper.mall.TbItemCatMapper;
import cn.tf.blog.po.mall.TbItemCat;
import cn.tf.blog.po.mall.TbItemCatExample;
import cn.tf.blog.po.mall.TbItemCatExample.Criteria;
import cn.tf.blog.service.mall.ItemCatService;




@Service
public class ItemCatServiceImpl implements ItemCatService{

	@Autowired
	private TbItemCatMapper  itemCatMapper;
	
	
	@Override
	public List<EUTreeNode> getCatList(long parentId) {
		TbItemCatExample  example=new TbItemCatExample();
		 Criteria createCriteria = example.createCriteria();
		createCriteria.andParentIdEqualTo(parentId);
		//根据条件查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		List<EUTreeNode>  resultList=new ArrayList<>();
		//把列表转换为treenodelist
		for (TbItemCat tbItemCat : list) {
			EUTreeNode node=new EUTreeNode();
			node.setId(tbItemCat.getId());
			node.setText(tbItemCat.getName());
			node.setState(tbItemCat.getIsParent()?"closed":"open");
			resultList.add(node);
		}
		return resultList;
		
	}


}
