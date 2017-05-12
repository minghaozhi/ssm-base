package com.ssm.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.ssm.pojo.Sysuser;
import com.ssm.service.UserService;



public class ShiroDbRealm extends AuthorizingRealm {
	// 注入userService业务代码
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// 授权（当jsp页面读到shiro标签时，调用这个方法）
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		// 通过指定的realm值，找到之前放入的principal值，也就是user
//		SysUsers sysUsers = (SysUsers) principal.fromRealm(this.getName()).iterator().next();

		// 定义用于存放权限字符串的集合
		List<String> permissions = new ArrayList<String>();

		/*
		 * //加载数据,得到用户所有角色 Set<Role> roles = user.getRoles();
		 * 
		 * for(Role role : roles){ //得到角色所有模块 Set<Module> modules =
		 * role.getModules(); for(Module module : modules){ //将权限字符串加入权限集合中
		 * permissions.add(module.getCpermission()); }
		 * 
		 * }
		 */

		// 定义返回值类型
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(permissions);

		return info;
	}

	// 认证（登录）
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 1,向下转型
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;  

		// 2,调用业务代码进行查询
		 Sysuser user = userService.findUserByLoginName(upToken.getUsername());

//		 System.out.println(user);
		 
		// 3，调用校验密码的方法，校验用户名和密码
		 return new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
	}

}