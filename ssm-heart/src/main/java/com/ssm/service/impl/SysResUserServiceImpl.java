package com.ssm.service.impl;

import com.github.abel533.entity.Example;
import com.ssm.mapper.SysResUserMapper;
import com.ssm.pojo.SysResUser;
import com.ssm.service.SysResUserService;
import com.ssm.util.UtilFuns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 墨殇 on 2017/7/10.
 */
@Service("sysResUserService")
public class SysResUserServiceImpl implements SysResUserService{

    @Autowired
    private SysResUserMapper sysResUserMapper;
     @Override
    public List<SysResUser> findByUserId(Long id) {
        Example example=new Example(SysResUser.class);
        Example.Criteria criteria=example.createCriteria();
        if(UtilFuns.isNotNull(id)){
            criteria.andEqualTo("iserId",id);
        }
        List<SysResUser> list=this.sysResUserMapper.selectByExample(example);
        return list;
    }
}
