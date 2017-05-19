package com.ssm.service;

import com.ssm.pojo.Sysuser;

public interface UserService {

	//根据id获取用户
	public Sysuser get(Long id);

	public Sysuser findUserByLoginName(String username);

    public boolean register(String name, String password);
}
