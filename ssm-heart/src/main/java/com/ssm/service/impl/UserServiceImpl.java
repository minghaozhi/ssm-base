package com.ssm.service.impl;

import com.github.abel533.entity.Example;
import com.ssm.common.CryptographyUtil;
import com.ssm.common.UserLocal;
import com.ssm.pojo.Log;
import com.ssm.pojo.QueryVo;
import com.ssm.pojo.SysLog;
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
	public SysUser findUserByLoginName(String name) {
	SysUser param=new SysUser();
	param.setLoginName(name);
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
	public Integer add(SysUser sysUser,Integer flag,Log log) {
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
		log.setEntiyType("用户管理业务");
		log.setEntityInfo("添加用户，用户的名字："+sysUser.getRealName()+"用户的昵称："+sysUser.getLoginName());
		log.setDescription("用户添加操作");
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

	@Override
	public Integer update(SysUser sysUser, Integer flag, Log log) {
		SysUser user=UserLocal.getUser();
		sysUser.setUpdateBy(user.getId());
		sysUser.setUpdateName(user.getRealName());
		sysUser.setUpdateTime(new Date());
		Integer count=this.userMapper.updateByPrimaryKeySelective(sysUser);
		log.setEntiyType("用户管理业务");
		log.setEntityInfo("修改用户，用户的名字："+sysUser.getRealName()+"用户的昵称："+sysUser.getLoginName());
		log.setDescription("用户修改操作");
		return count;

	}

	@Override
	public Integer deleteByIds(Long[] ids, Log log) {
		Integer count = 0;
		// 日志内容
		String logContent = "";
		try {
			for (Long id : ids) {
				count += this.deleteById(id,log);
				logContent += id + ",";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		log.setEntityInfo(logContent);
		logContent = "用户id:" + logContent;
		log.setDescription(logContent);
		return count;
	}

	public Integer deleteById(Long id, Log log) {

		SysUser sysUser=this.userMapper.selectByPrimaryKey(id);
		Integer count = userMapper.delete(sysUser);
		//业务日志
		log.setEntityId(1l);
		log.setEntiyType("用户管理业务");
		log.setEntityInfo("删除用户，用户的名称为："+sysUser.getRealName()+"用户账号："+sysUser.getLoginName());
		log.setDescription("用户删除操作");
		return count;
	}

}
