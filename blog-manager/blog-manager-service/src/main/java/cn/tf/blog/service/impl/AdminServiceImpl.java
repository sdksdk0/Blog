package cn.tf.blog.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.tf.blog.common.util.HttpClientUtil;
import cn.tf.blog.common.util.TaotaoResult;
import cn.tf.blog.mapper.BloggerDao;
import cn.tf.blog.mapper.SAdminDao;
import cn.tf.blog.po.SAdmin;
import cn.tf.blog.po.UUser;
import cn.tf.blog.service.AdminService;
import cn.tf.blog.service.UserService;



@Service
public class AdminServiceImpl implements  AdminService{

	@Value("${SSO_BASE_URL}")
	public String SSO_BASE_URL;
	
	@Value("${SSO_ADMIN_TOKEN}")
	private String SSO_ADMIN_TOKEN;
	
	@Value("${SSO_PAGE_LOGIN}") 
	public String SSO_PAGE_LOGIN;
	
	@Value("${SSO_INTERCEPTOR}")
	public String SSO_INTERCEPTOR;
	
	@Value("${TAOTAO_MAIN}")
	public String TAOTAO_MAIN;
	

	@Resource 
	private SAdminDao sAdminDao;
	
	
	@Override
	public SAdmin getUserByToken(String token) {
		
		try {
			String json=HttpClientUtil.doGet(SSO_BASE_URL+SSO_ADMIN_TOKEN+token);
			
			//把json转换为taotaoresult
			TaotaoResult  result=TaotaoResult.formatToPojo(json, SAdmin.class);
			
			if(result.getStatus()==200){
				SAdmin  user=(SAdmin) result.getData();
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}


	@Override
	public SAdmin find(String username) {
		return sAdminDao.find(username);
	}


	@Override
	public SAdmin getByUserName(String userName) {
		return sAdminDao.getByUserName(userName);
	}


	@Override
	public Integer update(SAdmin admin) {
		return sAdminDao.update(admin);
	}


}
