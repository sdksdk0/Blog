package cn.tf.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.tf.blog.common.util.HttpClientUtil;
import cn.tf.blog.common.util.TaotaoResult;
import cn.tf.blog.mapper.UUserMapper;
import cn.tf.blog.po.UUser;
import cn.tf.blog.service.UserService;



@Service
public class UserServiceImpl implements  UserService{
	
	
	@Autowired
	private UUserMapper userMapper;

	@Value("${SSO_BASE_URL}")
	public String SSO_BASE_URL;
	
	@Value("${SSO_USER_TOKEN}")
	private String SSO_USER_TOKEN;
	
	@Value("${SSO_PAGE_LOGIN}") 
	public String SSO_PAGE_LOGIN;
	
	@Value("${SSO_INTERCEPTOR}")
	public String SSO_INTERCEPTOR;
	
	@Value("${TAOTAO_MAIN}")
	public String TAOTAO_MAIN;
	
	
	@Override
	public UUser getUserByToken(String token) {
		
		try {
			String json=HttpClientUtil.doGet(SSO_BASE_URL+SSO_USER_TOKEN+token);
			
			//把json转换为taotaoresult
			TaotaoResult  result=TaotaoResult.formatToPojo(json, UUser.class);
			
			if(result.getStatus()==200){
				UUser  user=(UUser) result.getData();
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}


	@Override
	public List<UUser> finduUserByTime() {
		return userMapper.finduUserByTime();
	}


}
