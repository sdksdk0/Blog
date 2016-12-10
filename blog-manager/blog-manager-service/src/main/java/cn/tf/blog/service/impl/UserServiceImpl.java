package cn.tf.blog.service.impl;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.tf.blog.common.util.CookieUtils;
import cn.tf.blog.common.util.HttpClientUtil;
import cn.tf.blog.common.util.JsonUtils;
import cn.tf.blog.common.util.TaotaoResult;
import cn.tf.blog.mapper.UUserMapper;
import cn.tf.blog.po.UUser;
import cn.tf.blog.po.UUserExample;
import cn.tf.blog.po.UUserExample.Criteria;

import cn.tf.blog.service.UserService;
import cn.tf.blog.dao.JedisClient;



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
	
	@Autowired
	private JedisClient jedisClient;


	@Value("${REDIS_USER_SESSION_KEY}")
	private String REDIS_USER_SESSION_KEY;

	@Value("${SSO_SESSION_EXPIRE}")
	private Integer SSO_SESSION_EXPIRE;
	
	
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




	@Override
	public TaotaoResult userLogin(String username, String password,
			HttpServletRequest request, HttpServletResponse response) {
		UUserExample example = new UUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);

		List<UUser> list = userMapper.selectByExample(example);
		// 如果没有该用户名
		if (null == list || list.size() == 0) {
			return TaotaoResult.build(400, "用户名或密码错误");
		}
		UUser user = list.get(0);
		if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(
				user.getPassword())) {
			return TaotaoResult.build(400, "用户名或密码错误");
		}
		//未激活
		if(user.getState().equalsIgnoreCase("0")){
			//发送激活邮件
			return TaotaoResult.build(400, "账号未激活");
		}
		//被冻结了
		if(user.getState().equalsIgnoreCase("2")){
			//发送激活邮件
			return TaotaoResult.build(400, "账号被冻结");
		}
		
		// 生成token
				String token = UUID.randomUUID().toString();
				// 把用户信息写入redis
				user.setPassword(null);
				jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token,
						JsonUtils.objectToJson(user));
				// 设置过期时间
				jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token,
						SSO_SESSION_EXPIRE);

				// 添加写cookie的逻辑，cookie的有效期是关闭浏览器失效
				CookieUtils.setCookie(request, response, "TT_TOKEN_USER", token);
				
		return TaotaoResult.ok(token);
	}




}
