package com.ssm.service.impl;

import com.ssm.common.CryptographyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.mapper.UserMapper;
import com.ssm.pojo.Sysuser;
import com.ssm.service.UserService;

import java.util.Date;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public Sysuser get(Long id) {
		Sysuser sysuser=this.userMapper.selectByPrimaryKey(id);
		return sysuser;
	}

	@Override
	public Sysuser findUserByLoginName(String username) {
	Sysuser param=new Sysuser();
	param.setLoginName(username);
	Sysuser sysuser=this.userMapper.selectOne(param);
		return sysuser;
	}

	@Override
	public boolean register(Sysuser sysuser) {
      String password= CryptographyUtil.md5(sysuser.getPassword(),sysuser.getLoginName());
      sysuser.setPassword(password);
		sysuser.setCreateTime(new Date());
		sysuser.setUpdateTime(new Date());
		sysuser.setIsActivited(0);
		sysuser.setCreateName(sysuser.getRealName());
		sysuser.setUpdateName(sysuser.getRealName());
		this.userMapper.insert(sysuser);
		return true;
	}

}
