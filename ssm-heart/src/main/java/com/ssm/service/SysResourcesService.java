package com.ssm.service;

import com.ssm.util.ResFormMap;
import com.ssm.util.ResUserFormMap;

import java.util.List;

/**
 * Created by 墨殇 on 2017/7/10.
 */
public interface SysResourcesService {
    List<ResFormMap> findByUserId(ResFormMap map);

   public  List<ResFormMap> findByNames(ResFormMap resFormMap);
}
