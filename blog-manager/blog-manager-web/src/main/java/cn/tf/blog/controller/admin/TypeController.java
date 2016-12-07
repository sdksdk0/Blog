package cn.tf.blog.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.tf.blog.common.pojo.PageBean;
import cn.tf.blog.common.util.ResponseUtil;
import cn.tf.blog.po.SType;
import cn.tf.blog.po.UBlogtype;
import cn.tf.blog.service.BlogService;
import cn.tf.blog.service.BlogTypeService;
import cn.tf.blog.service.STypeService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 管理员博客类别Controller层
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/admin/blogType")
public class TypeController {

	@Resource
	private STypeService typeService;

	/**
	 * 分页查询博客类别信息
	 * 
	 * @param page
	 * @param rows
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows,
			HttpServletResponse response) throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(page),
				Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());

		List<SType> blogTypeList = typeService.list(map);
		Long total = typeService.getTotal(map);
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(blogTypeList);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}

	/**
	 * 添加或者修改博客类别信息
	 * 
	 * @param blogType
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public String save(SType blogType, HttpServletResponse response)
			throws Exception {
		int resultTotal = 0; // 操作的记录条数
		if (blogType.getTypeid() == null) {
			resultTotal = typeService.add(blogType);
		} else {
			resultTotal = typeService.update(blogType);
		}
		JSONObject result = new JSONObject();
		if (resultTotal > 0) {
			result.put("success", true);
		} else {
			result.put("success", false);
		}
		ResponseUtil.write(response, result);
		return null;
	}

	/**
	 * 删除博客类别信息
	 * 
	 * @param ids
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam(value = "ids") String ids,
			HttpServletResponse response) throws Exception {
		String[] idsStr = ids.split(",");
		JSONObject result = new JSONObject();
		for (int i = 0; i < idsStr.length; i++) {

			try {
				typeService.delete(idsStr[i]);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}

}
