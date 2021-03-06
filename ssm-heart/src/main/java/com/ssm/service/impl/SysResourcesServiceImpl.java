package com.ssm.service.impl;

import com.ssm.mapper.SysResourcesMapper;
import com.ssm.service.SysResourcesService;
import com.ssm.util.ResFormMap;
import com.ssm.util.ResUserFormMap;
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
    public List<ResFormMap> findByUserId(ResFormMap map) {
        return this.sysResourcesMapper.findByUserId(map);
    }

    @Override
    public List<ResFormMap> findByNames(ResFormMap resFormMap) {
        return sysResourcesMapper.findByNames(resFormMap);
    }
}
