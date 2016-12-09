package cn.tf.blog.search.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tf.blog.common.util.ExceptionUtil;
import cn.tf.blog.common.util.TaotaoResult;
import cn.tf.blog.search.pojo.SearchResult;
import cn.tf.blog.search.service.SearchService;




//商品查询
@Controller
public class SearchController {
	
	@Autowired
	private SearchService  searchService;
	
	@RequestMapping(value="/query",method=RequestMethod.GET)
	@ResponseBody
	public TaotaoResult  search(@RequestParam("q") String queryString,
			@RequestParam(defaultValue="1")Integer page,
			@RequestParam(defaultValue="20")Integer rows){
		
		if(StringUtils.isBlank(queryString)){
			return TaotaoResult.build(400, "查询条件不能为空");
		}
		
		SearchResult searchResult=null;
		try {
			queryString=new String(queryString.getBytes("iso8859-1"),"utf-8");
			
			searchResult = searchService.search(queryString, page, rows);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return TaotaoResult.ok(searchResult);
	}

}
