package com.ssm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssm.common.CryptographyUtil;
import com.ssm.pojo.Sysuser;
import com.ssm.service.UserService;

@Controller
@RequestMapping("login")
public class LoginController {
@Autowired
private UserService userService;
	@RequestMapping(value="login",method=RequestMethod.GET)
    public String loginq(HttpServletRequest request){
    	//获取当前登录的用户
    	
    	Object obj = request.getSession().getAttribute("user");
    	
    	
    	if(obj!=null){
    		return "index";
    	}
    	
    	
		return "login";
    	
    }
	
	
	 /**
     *  登录用户(使用shiro框架的登录方法)
     * @return
     */
    @RequestMapping(value="login",method=RequestMethod.POST)
    public String login(@RequestParam("username")String username,
    		@RequestParam("password")String password,
    		HttpServletRequest request,HttpServletResponse response){

    	//判断用户是否已经禁用
    	Sysuser user = this.userService.findUserByLoginName(username);
    	
    
    	
    
    	
    	try {
			//得到subject对象
			Subject subject = SecurityUtils.getSubject();
			
			//将用户数据封装为AuthenticationToken对象
			UsernamePasswordToken token = new UsernamePasswordToken(username,password);
			
			//实现登录
			subject.login(token);
			
			
			//登录成功后，就可以在shiro中取对象
			user = (Sysuser) subject.getPrincipal();
			//将登录信息放入session中
			subject.getSession().setAttribute("user", user);
			
			subject.getSession().setTimeout(28800000);

			return "main";
			
		} catch (Exception e) {
			e.printStackTrace();
			
			//当登录失败时抛出此异常
			request.setAttribute("errorInfo", "对不起用户名或密码错误！"); 
			
			return "login";
		}
    	
    }
}
