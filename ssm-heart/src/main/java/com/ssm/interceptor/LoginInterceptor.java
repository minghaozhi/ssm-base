package com.ssm.interceptor;

import com.ssm.common.UserLocal;
import com.ssm.pojo.SysUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 登录拦截器
 * @author join
 *
 */
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//获取当前登录的用户信息
		SysUser user = (SysUser) request.getSession().getAttribute("user");

		//判断用户是否登录
		if(user==null){
			//是ajax异步请求，返回一个消息给前台
			if(request.getHeader("x-requested-with")!=null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
				response.setHeader("sessionstatus", "timeOut");
				PrintWriter printWriter = response.getWriter();
				printWriter.print("{sessionState:timeout}");
				printWriter.flush();
				printWriter.close();
			}else{
				response.sendRedirect(request.getContextPath()+"/rest/sysuser/login");
			}
			return false;
		}else{
			//将用户信息存在ThreadLocal中
			UserLocal.setUser(user);
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
