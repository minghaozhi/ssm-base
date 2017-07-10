package com.ssm.service;

import com.ssm.pojo.SysResources;
import com.ssm.util.ResFormMap;

import java.util.List;

/**
 * Created by 墨殇 on 2017/7/10.
 */
public interface SysResourcesService {
    List<ResFormMap> findByUserId(Long id);
}
