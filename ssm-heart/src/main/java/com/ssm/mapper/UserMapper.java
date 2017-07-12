package com.ssm.mapper;

import com.github.abel533.mapper.Mapper;
import com.ssm.pojo.SysUser;
import com.ssm.pojo.QueryVo;
import com.ssm.util.UserFormMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends Mapper<SysUser>{

    public Integer getTotal(UserFormMap userFormMap);

    public List<UserFormMap> findUserPage(UserFormMap userFormMap);
}
