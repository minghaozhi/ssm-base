package com.ssm.service;

import com.ssm.pojo.Log;
import com.ssm.pojo.SysLog;
import com.ssm.pojo.SysUser;
import com.ssm.pojo.QueryVo;
import com.ssm.util.UserFormMap;


import java.util.List;

public interface UserService {

	//根据id获取用户
	public SysUser get(Long id);

	public SysUser findUserByLoginName(String name);

    public boolean register(SysUser sysUser);

   public  Integer getTotal(UserFormMap userFormMap);

public 	List<SysUser> findUser(QueryVo<SysUser> vo);

    public Integer add(SysUser sysUser,Integer flag,Log log);

   public  SysUser findUserById(Long id);

   public  List<UserFormMap> findUserPage(UserFormMap userFormMap);

    public Integer update(SysUser sysUser, Integer flag, Log log);
}
