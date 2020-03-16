package com.zxy.cms.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 * 
 * @ClassName: UserInterceptor 
 * @Description: 个人中心的拦截器
 * @author: admin
 * @date: 2020年3月13日 下午3:04:55
 */
public class MyInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		
		Object obj = session.getAttribute("user");
		
		if (null!=obj) 
			return true;
			
		request.setAttribute("msg", "请登录后重试");
		request.getRequestDispatcher("/WEB-INF/view/passport/login.jsp").forward(request, response);
		
		return false;
	}
	
}
