package com.ssm.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssm.common.CipherUtil;
import com.ssm.pojo.Sysuser;
import com.ssm.service.UserService;


public class ShiroDbRealm extends AuthorizingRealm {
	private static Logger logger = LoggerFactory.getLogger(ShiroDbRealm.class);
	private static final String ALGORITHM = "MD5";
	
	@Autowired
	private UserService userService;

	public ShiroDbRealm() {
		super();
	}
	
	/**
	 * ��֤��½
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		System.out.println(token.getUsername());
		Sysuser user = userService.findUserByLoginName(token.getUsername());
		System.out.println(user);
		CipherUtil cipher = new CipherUtil();//MD5����
		if (user != null) {
			return new SimpleAuthenticationInfo(user.getName(), cipher.generatePassword(user.getPassword()), getName());
		}else{
			throw new AuthenticationException();
		}
	}

	/**
	 * ��½�ɹ�֮�󣬽��н�ɫ��Ȩ����֤
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		/*����Ӧ�ø��userNameʹ��role��permission ��serive�������жϣ�������Ӧ ��Ȩ�޼ӽ��������������һ��*/
		Set<String> roleNames = new HashSet<String>();
	    Set<String> permissions = new HashSet<String>();
	    roleNames.add("admin");//��ӽ�ɫ����Ӧ��index.jsp
	    roleNames.add("administrator");
	    permissions.add("create");//���Ȩ��,��Ӧ��index.jsp
	    permissions.add("login.do?main");
	    permissions.add("login.do?logout");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
	    info.setStringPermissions(permissions);
		return info;
	}


	/**
	 * ��������û���Ȩ��Ϣ����.
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}


	/**
	 * ��������û���Ȩ��Ϣ����.
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}

//	@PostConstruct
//	public void initCredentialsMatcher() {//MD5加密
//		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(ALGORITHM);
//		setCredentialsMatcher(matcher);
//	}
}
