package cn.tf.blog.service.mall;

import java.util.List;

import cn.tf.blog.common.pojo.EUTreeNode;



public interface ItemCatService {
	
	List<EUTreeNode>  getCatList(long parentId);

}
