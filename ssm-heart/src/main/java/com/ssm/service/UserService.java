package com.ssm.service;

import com.ssm.pojo.Sysuser;
import com.ssm.pojo.QueryVo;


import java.util.List;

public interface UserService {

	//根据id获取用户
	public Sysuser get(Long id);

	public Sysuser findUserByLoginName(String username);

    public boolean register(Sysuser sysuser);

   public  Integer getTotal(QueryVo<Sysuser> vo);

public 	List<Sysuser> findUser(QueryVo<Sysuser> vo);
}
