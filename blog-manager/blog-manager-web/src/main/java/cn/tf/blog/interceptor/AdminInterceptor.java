package cn.tf.blog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.tf.blog.common.util.CookieUtils;
import cn.tf.blog.po.SAdmin;
import cn.tf.blog.po.UUser;
import cn.tf.blog.service.AdminService;
import cn.tf.blog.service.UserService;
import cn.tf.blog.service.impl.UserServiceImpl;





public class AdminInterceptor implements  HandlerInterceptor{

	@Autowired 
	private  AdminService adminService;
	
	//在handler执行之前处理    
		@Override
		public boolean preHandle(HttpServletRequest request,
				HttpServletResponse response, Object handler) throws Exception {
			//返回值决定handler是否执行
			//判断用户是否登陆，从cookie中取token，根据token获取用户信息，调用sso系统的接口。
			String token=CookieUtils.getCookieValue(request, "TT_TOKEN_ADMIN");
			SAdmin admin = adminService.getUserByToken(token);
			if(null==admin){
				//调到登录页面 
				response.sendRedirect("http://localhost:8087/sso/page/admin/login");
				return false;
			}
			//把用户信息放入Request
			request.setAttribute("admin", admin);
			return true;
		}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	
	//返回modelAndView之后，响应用户操作
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
