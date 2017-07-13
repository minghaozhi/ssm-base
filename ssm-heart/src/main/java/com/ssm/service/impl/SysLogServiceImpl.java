package com.ssm.service.impl;

import com.github.abel533.entity.Example;
import com.github.abel533.entity.Example.Criteria;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.ssm.mapper.base.SysLogMapper;
import com.ssm.pojo.SysLog;
import com.ssm.service.SysLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {
@Autowired
private SysLogMapper sysLogMapper;

	@Override
	public Integer add(SysLog sysLog) {
     Integer count=this.sysLogMapper.insert(sysLog);
		return count;
	}

	@Override
	public Integer deleteById(Long id) {
		Integer count=this.sysLogMapper.deleteByPrimaryKey(id);
		return count;
	}

	@Override
	public Integer updateById(SysLog sysLog) {
		Integer count=this.sysLogMapper.updateByPrimaryKeySelective(sysLog);
		return count;
	}

	@Override
	public List<SysLog> queryAll() {
		return null;
	}

	@Override
	public Integer deleteByIdCon(Long id) {
		return null;
	}

	@Override
	public List<String> findUserOperatorTypes() {
		return null;
	}
}
