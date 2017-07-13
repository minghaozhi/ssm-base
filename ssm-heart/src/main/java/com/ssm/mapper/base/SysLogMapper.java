package com.ssm.mapper.base;

import com.github.abel533.mapper.Mapper;
import com.ssm.pojo.SysLog;

import java.util.List;

public interface SysLogMapper extends Mapper<SysLog>{
	/**
	 * 查询所有的用户操作类型
	 * @return
	 */
	public List<String> findUserOperatorTypes();
	
}
