package com.ssm.mapper;

import com.github.abel533.mapper.Mapper;
import com.ssm.pojo.Sysuser;
import com.ssm.pojo.vo.QueryVo;

public interface UserMapper extends Mapper<Sysuser>{

    public Integer getTotal(QueryVo<Sysuser> vo);
}
