package com.ssm.mapper;

import com.github.abel533.mapper.Mapper;
import com.ssm.pojo.SysResources;
import com.ssm.util.ResFormMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 墨殇 on 2017/7/10.
 */
public interface SysResourcesMapper extends Mapper<SysResources>{

   public  List<ResFormMap> findByUserId(ResFormMap map);
}
