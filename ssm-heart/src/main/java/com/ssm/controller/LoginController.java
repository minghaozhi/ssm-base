package com.ssm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssm.common.UserLocal;
import com.ssm.pojo.SysResources;
import com.ssm.service.SysResUserService;
import com.ssm.service.SysResourcesService;
import com.ssm.util.Common;
import com.ssm.util.ResFormMap;
import com.ssm.util.TreeObject;
import com.ssm.util.TreeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssm.pojo.SysUser;
import com.ssm.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("login")
public class LoginController {
@Autowired
private UserService userService;

@Autowired
private SysResUserService sysResUserService;

@Autowired
private SysResourcesService sysResourcesService;
	
	 /**
     *  登录用户(使用shiro框架的登录方法)
     * @return
     */
    @RequestMapping(value="login",method = RequestMethod.POST)
    public String login(@RequestParam("username")String username,
    		@RequestParam("password")String password,
    		HttpServletRequest request,HttpServletResponse response){

    	//判断用户是否已经禁用
    	SysUser user = this.userService.findUserByLoginName(username);
    	
    
    	
    
    	
    	try {
			//得到subject对象
			Subject subject = SecurityUtils.getSubject();
			
			//将用户数据封装为AuthenticationToken对象
			UsernamePasswordToken token = new UsernamePasswordToken(username,password);
			
			//实现登录
			subject.login(token);
			
			
			//登录成功后，就可以在shiro中取对象
			user = (SysUser) subject.getPrincipal();
			//将登录信息放入session中
			subject.getSession().setAttribute("user", user);
			UserLocal.setUser(user);
           //根据用户id查询用户菜单表
			ResFormMap resFormMap = new ResFormMap();
			resFormMap.put("userId", String.valueOf(user.getId()));
			List<ResFormMap> resFormMaps=this.sysResourcesService.findByUserId(resFormMap);
			List<TreeObject> list = new ArrayList<TreeObject>();
			for (ResFormMap map : resFormMaps) {
				TreeObject ts = new TreeObject();
				Common.flushObject(ts, map);
				list.add(ts);
			}
			TreeUtil treeUtil = new TreeUtil();
			List<TreeObject> ns = treeUtil.getChildTreeObjects(list, 0);
			subject.getSession().setAttribute("list", ns);
			return "index";
			
		} catch (Exception e) {
			e.printStackTrace();
			
			//当登录失败时抛出此异常
			request.setAttribute("errorInfo", "对不起用户名或密码错误！"); 
			
			return "/login";
		}
    	
    }
}
