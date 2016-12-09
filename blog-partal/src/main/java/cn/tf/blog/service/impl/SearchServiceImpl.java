package cn.tf.blog.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.tf.blog.common.util.HttpClientUtil;
import cn.tf.blog.common.util.TaotaoResult;
import cn.tf.blog.pojo.SearchResult;
import cn.tf.blog.service.SearchService;



@Service
public class SearchServiceImpl implements  SearchService{

	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;
	
	
	//调用服务
	@Override
	public SearchResult search(String queryString, int page) {
		
		Map<String,String>  param=new HashMap<>();
		param.put("q", queryString);
		param.put("page", page+"");
		
		//执行调用服务
		try {
			String json=HttpClientUtil.doGet(SEARCH_BASE_URL,param);
			
			//把字符串转换
			TaotaoResult  result=TaotaoResult.formatToPojo(json, SearchResult.class);
		
			if(result.getStatus()==200){
				SearchResult searchResult=(SearchResult) result.getData();
				return searchResult;
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
