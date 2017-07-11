package com.ssm.service;

import com.ssm.pojo.SysUser;
import com.ssm.pojo.QueryVo;
import com.ssm.util.UserFormMap;


import java.util.List;

public interface UserService {

	//根据id获取用户
	public SysUser get(Long id);

	public SysUser findUserByLoginName(String username);

    public boolean register(SysUser sysUser);

   public  Integer getTotal(QueryVo<SysUser> vo);

public 	List<SysUser> findUser(QueryVo<SysUser> vo);

    public Integer add(SysUser sysUser);

   public  SysUser findUserById(Long id);

   public  List<UserFormMap> findUserPage(UserFormMap userFormMap);
}
