package cn.tf.blog.sso.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.sun.xml.internal.xsom.impl.scd.Iterators.Map;

import cn.tf.blog.po.UUser;
import cn.tf.blog.po.UUserExample;
import cn.tf.blog.po.UUserExample.Criteria;
import cn.tf.blog.sso.dao.JedisClient;
import cn.tf.blog.sso.service.UserService;
import cn.tf.blog.common.util.CookieUtils;
import cn.tf.blog.common.util.JsonUtils;
import cn.tf.blog.common.util.TaotaoResult;
import cn.tf.blog.mapper.UUserMapper;




@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UUserMapper userMapper;

	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private JavaMailSender  mailSender;

	@Value("${REDIS_USER_SESSION_KEY}")
	private String REDIS_USER_SESSION_KEY;

	@Value("${SSO_SESSION_EXPIRE}")
	private Integer SSO_SESSION_EXPIRE;

	@Override
	public TaotaoResult checkData(String content, Integer type) {

		UUserExample example = new UUserExample();
		 Criteria criteria = example.createCriteria();

		// 对数据进行校验
		if (1 == type) {
			// 用户名校验
			criteria.andUsernameEqualTo(content);
		} else if (2 == type) {
			// 电话校验
			criteria.andPhoneEqualTo(content);
		} else {
			// 邮件校验
			criteria.andEmailEqualTo(content);
		}
		// 执行查询
		List<UUser> list = userMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			return TaotaoResult.ok(true);
		}
		return TaotaoResult.ok(false);
	}

	@Override
	public TaotaoResult createUser(HttpServletRequest request,UUser user,HttpSession session) {
		user.setCreatetime(new Date());
		user.setUserId(UUID.randomUUID().toString());
		user.setState(0+"");
		// md5加密
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword()
				.getBytes()));

		userMapper.insert(user);
		


		session.setAttribute("userId",user.getUserId());
		
		active(request, user, session);
		return TaotaoResult.ok();
	}
 

	// 用户登录
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

	//
	@Override
	public TaotaoResult getUserByToken(String token) {

		// 根据token从redis中查询用户信息
		String json = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);
		if (StringUtils.isBlank(json)) {
			return TaotaoResult.build(400, "会话过期，请重新登录");
		}
		// 更新过期时间
		jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token,
				SSO_SESSION_EXPIRE);
		// 返回用户信息
		return TaotaoResult.ok(JsonUtils.jsonToPojo(json, UUser.class));
	}

	// 退出登录
	@Override
	public TaotaoResult userLogout(String token) {
		// 根据token从redis删除用户信息
		jedisClient.del(REDIS_USER_SESSION_KEY + ":" + token);
		// 返回用户信息
		return TaotaoResult.ok();
	}
	//发送激活邮件
		public String active(HttpServletRequest request,UUser user,HttpSession session){
			String activeCode=UUID.randomUUID().toString();
			/*request.getSession().setAttribute("activeCode", activeCode);*/
			Object usId= session.getAttribute("userId");
			//把激活码存到redis中
			try {
				jedisClient.set("BLOG_ACTIVECODE_KEY:"+usId,activeCode);
				//设置过期时间，过期后无法激活
				jedisClient.expire("BLOG_ACTIVECODE_KEY:"+usId,1800);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			//session.setAttribute("activeCode", activeCode);
			String activePath=request.getRequestURL().toString().replace("user/register", "user/active/"+usId+"/"+activeCode);
			
			try {
				MimeMessage  message=mailSender.createMimeMessage();
				MimeMessageHelper  helper=new MimeMessageHelper(message,true);
				helper.setFrom("xingtian@tianfang1314.cn");
				helper.setTo(user.getEmail());
				helper.setSubject("博客云会员激活");
				helper.setText("<a href='"+activePath+"'>博客云会员激活</a><br>如果连接无效请把激活地址"+activePath+"复制到浏览器地址",true);
				mailSender.send(message);
				
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getMessage());

			}
			return null;
		}
		
	@Override
	public boolean activeUser(String usId) {
		return userMapper.updateStatus(usId);
	}


	@Override
	public int update(String newPassword, String id) {
		
		UUser user=new UUser();
		user.setUserId(id);
		user.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
		return userMapper.updateByPrimaryKeySelective(user);
	}
}
