package com.ssm.mapper;

import com.github.abel533.mapper.Mapper;
import com.ssm.pojo.SysUser;
import com.ssm.pojo.QueryVo;

public interface UserMapper extends Mapper<SysUser>{

    public Integer getTotal(QueryVo<SysUser> vo);
}
