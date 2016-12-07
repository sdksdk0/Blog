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

import cn.tf.blog.po.SAdmin;
import cn.tf.blog.po.SAdminExample;
import cn.tf.blog.po.SAdmin;
import cn.tf.blog.po.SAdminExample;
import cn.tf.blog.po.SAdminExample.Criteria;
import cn.tf.blog.sso.dao.JedisClient;
import cn.tf.blog.sso.service.AdminService;
import cn.tf.blog.sso.service.UserService;
import cn.tf.blog.common.util.CookieUtils;
import cn.tf.blog.common.util.JsonUtils;
import cn.tf.blog.common.util.TaotaoResult;
import cn.tf.blog.mapper.SAdminMapper;





@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private SAdminMapper sAdminMapper;

	@Autowired
	private JedisClient jedisClient;
	

	@Value("${REDIS_USER_SESSION_KEY}")
	private String REDIS_ADMIN_SESSION_KEY;

	@Value("${SSO_SESSION_EXPIRE}")
	private Integer SSO_SESSION_EXPIRE;

	@Override
	public TaotaoResult checkData(String content, Integer type) {

		SAdminExample example = new SAdminExample();
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
		List<SAdmin> list = sAdminMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			return TaotaoResult.ok(true);
		}
		return TaotaoResult.ok(false);
	}

	

	// 用户登录
	@Override
	public TaotaoResult userLogin(String username, String password,
			HttpServletRequest request, HttpServletResponse response) {
		SAdminExample example = new SAdminExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);

		List<SAdmin> list = sAdminMapper.selectByExample(example);
		// 如果没有该用户名
		if (null == list || list.size() == 0) {
			return TaotaoResult.build(400, "用户名或密码错误");
		}
		SAdmin user = list.get(0);
		if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(
				user.getPassword())) {
			return TaotaoResult.build(400, "用户名或密码错误");
		}
		
		// 生成token
		String token = UUID.randomUUID().toString();
		// 把用户信息写入redis
		user.setPassword(null);
		jedisClient.set(REDIS_ADMIN_SESSION_KEY + ":" + token,
				JsonUtils.objectToJson(user));
		// 设置过期时间
		jedisClient.expire(REDIS_ADMIN_SESSION_KEY + ":" + token,
				SSO_SESSION_EXPIRE);

		// 添加写cookie的逻辑，cookie的有效期是关闭浏览器失效
		CookieUtils.setCookie(request, response, "TT_TOKEN_ADMIN", token);

		return TaotaoResult.ok(token);
	}

	//
	@Override
	public TaotaoResult getUserByToken(String token) {

		// 根据token从redis中查询用户信息
		String json = jedisClient.get(REDIS_ADMIN_SESSION_KEY + ":" + token);
		if (StringUtils.isBlank(json)) {
			return TaotaoResult.build(400, "会话过期，请重新登录");
		}
		// 更新过期时间
		jedisClient.expire(REDIS_ADMIN_SESSION_KEY + ":" + token,
				SSO_SESSION_EXPIRE);
		// 返回用户信息
		return TaotaoResult.ok(JsonUtils.jsonToPojo(json, SAdmin.class));
	}

	// 退出登录
	@Override
	public TaotaoResult userLogout(String token) {
		// 根据token从redis删除用户信息
		jedisClient.del(REDIS_ADMIN_SESSION_KEY + ":" + token);
		// 返回用户信息
		return TaotaoResult.ok();
	}
	



	@Override
	public int update(String newPassword, String id) {
		
		SAdmin user=new SAdmin();
		user.setAdminid(id);
		user.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
		return sAdminMapper.updateByPrimaryKeySelective(user);
	}
}
