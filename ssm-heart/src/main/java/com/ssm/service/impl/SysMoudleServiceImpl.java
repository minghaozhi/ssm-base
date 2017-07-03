package com.ssm.service.impl;

import com.github.abel533.entity.Example;
import com.ssm.mapper.SysMoudleMapper;
import com.ssm.pojo.SysMoudle;
import com.ssm.service.SysMoudleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 墨殇 on 2017/7/3.
 */
@Service("sysMoudleService")
public class SysMoudleServiceImpl implements SysMoudleService{
    @Autowired
    private SysMoudleMapper sysMoudleMapper;
    @Override
    public List<SysMoudle> findAllMoudle(Long parentId) {
        Example example=new Example(SysMoudle.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("isActivited",0);
        criteria.andEqualTo("parentId",parentId);
        List<SysMoudle> list=this.sysMoudleMapper.selectByExample(example);
        return list;
    }
}
