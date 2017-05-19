package com.ssm.service.impl;

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
	param.setName(username);
	Sysuser sysuser=this.userMapper.selectOne(param);
		return sysuser;
	}

	@Override
	public boolean register(String name, String password) {
		Sysuser sysuser=new Sysuser();
		sysuser.setName(name);
		sysuser.setPassword(password);
		sysuser.setCreateTime(new Date());
		sysuser.setUpdateTime(new Date());
		this.userMapper.insert(sysuser);
		return true;
	}

}
