package com.ssm.service.impl;

import com.ssm.mapper.SysResourcesMapper;
import com.ssm.pojo.SysResources;
import com.ssm.service.SysResourcesService;
import com.ssm.util.ResFormMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 墨殇 on 2017/7/10.
 */
@Service("sysResourcesService")
public class SysResourcesServiceImpl implements SysResourcesService{

    @Autowired
    private SysResourcesMapper sysResourcesMapper;
    @Override
    public List<ResFormMap> findByUserId(Long id) {
        return this.sysResourcesMapper.findByUserId(id);
    }
}
