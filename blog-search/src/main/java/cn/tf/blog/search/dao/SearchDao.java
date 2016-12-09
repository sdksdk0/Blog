package cn.tf.blog.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import cn.tf.blog.search.pojo.SearchResult;



public interface SearchDao {
	
	SearchResult searchItem(SolrQuery solrQuery) throws Exception;


}
