package com.ssm.service.impl;

import com.github.abel533.entity.Example;
import com.ssm.common.CryptographyUtil;
import com.ssm.common.UserLocal;
import com.ssm.pojo.QueryVo;
import com.ssm.pojo.SysUser;
import com.ssm.shiro.Encrypt;
import com.ssm.util.UserFormMap;
import com.ssm.util.UtilFuns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.mapper.UserMapper;
import com.ssm.service.UserService;

import java.util.Date;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public SysUser get(Long id) {
		SysUser sysUser =this.userMapper.selectByPrimaryKey(id);
		return sysUser;
	}

	@Override
	public SysUser findUserByLoginName(String username) {
	SysUser param=new SysUser();
	param.setLoginName(username);
	SysUser sysUser =this.userMapper.selectOne(param);
		return sysUser;
	}

	@Override
	public boolean register(SysUser sysUser) {
      String password= CryptographyUtil.md5(sysUser.getPassword(), sysUser.getLoginName());
      sysUser.setPassword(password);
		sysUser.setCreateTime(new Date());
		sysUser.setUpdateTime(new Date());
		sysUser.setIsActivited(0);
		sysUser.setCreateName(sysUser.getRealName());
		sysUser.setUpdateName(sysUser.getRealName());
		this.userMapper.insert(sysUser);
		return true;
	}

	@Override
	public Integer getTotal(UserFormMap userFormMap) {
		return userMapper.getTotal(userFormMap);
	}

	@Override
	public List<SysUser> findUser(QueryVo<SysUser> vo) {
		Example example=new Example(SysUser.class);
		Example.Criteria criteria=example.createCriteria();
        if (UtilFuns.isNotEmpty(vo.getEntity().getLoginName())){
        	criteria.andEqualTo("loginName",vo.getEntity().getLoginName());
		}
		if (UtilFuns.isNotEmpty(vo.getEntity().getRealName())){
			criteria.andEqualTo("realName",vo.getEntity().getRealName());
		}
          criteria.andEqualTo("isActivited",0);
		List<SysUser> sysUserList =this.userMapper.selectByExample(example);
		return sysUserList;
	}

	@Override
	public Integer add(SysUser sysUser,Integer flag) {
		SysUser user= UserLocal.getUser();
	    String	password = Encrypt.md5("123456",user.getRealName());
	    sysUser.setPassword(password);
	    sysUser.setCreateBy(user.getId());
	    sysUser.setCreateName(user.getRealName());
	    sysUser.setUpdateBy(user.getId());
	    sysUser.setUpdateName(user.getRealName());
	    sysUser.setCreateTime(new Date());
	    sysUser.setUpdateTime(new Date());
	    sysUser.setIsActivited(0);
       Integer count=this.userMapper.insert(sysUser);
 		return count;

	}

	@Override
	public SysUser findUserById(Long id) {
		SysUser sysUser=this.userMapper.selectByPrimaryKey(id);
		return sysUser;
	}

	@Override
	public List<UserFormMap> findUserPage(UserFormMap userFormMap) {
		return userMapper.findUserPage(userFormMap);
	}

}
