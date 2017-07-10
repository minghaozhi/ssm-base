package com.ssm.service;

import com.ssm.pojo.SysResUser;

import java.util.List;

/**
 * Created by 墨殇 on 2017/7/10.
 */
public interface SysResUserService {
   public  List<SysResUser> findByUserId(Long id);
}
