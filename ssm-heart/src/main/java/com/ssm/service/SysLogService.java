package com.ssm.service;



import com.ssm.pojo.SysLog;

import java.util.List;

public interface SysLogService {

	/**
	 * 添加系统日志
	 * @param sysLog
	 */
	public Integer add(SysLog sysLog);

	/**
	 * 删除系统日志
	 * @param id
	 */
	public Integer deleteById(Long id);

	/**
	 * 更新系统日志
	 * @param sysLog
	 */
	public Integer updateById(SysLog sysLog);



	/**
	 * 查询所有的系统日志
	 * @return
	 */
	public List<SysLog> queryAll();

	/**
	 * 逻辑删除系统日志
	 * @param id
	 * @return
	 */
	public Integer deleteByIdCon(Long id);

	/**
	 * 查询所有的用户操作类型
	 * @return
	 */
	public List<String>  findUserOperatorTypes();


}
