package com.ssm.mapper;

import com.github.abel533.mapper.Mapper;
import com.ssm.mapper.base.BaseMapper;
import com.ssm.pojo.SysResources;
import com.ssm.util.ResFormMap;
import com.ssm.util.ResUserFormMap;

import java.util.List;

/**
 * Created by 墨殇 on 2017/7/10.
 */
public interface SysResourcesMapper extends BaseMapper{

   public  List<ResFormMap> findByUserId(ResFormMap map);

   public  List<ResUserFormMap> findRes(ResUserFormMap resQueryForm);


}
