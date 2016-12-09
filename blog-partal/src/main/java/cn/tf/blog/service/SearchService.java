package cn.tf.blog.service;

import cn.tf.blog.pojo.SearchResult;



public interface SearchService {
	
	SearchResult  search(String queryString,int page);

}
