package com.ssm.controller;

import com.ssm.mapper.SysResourcesMapper;
import com.ssm.service.SysResourcesService;
import com.ssm.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 墨殇 on 2017/7/10.
 */
@Controller
@RequestMapping("sysResources")
public class SysResourcesController extends  BaseController{

    @Inject
    private SysResourcesMapper sysResourcesMapper;
    @ResponseBody
    @RequestMapping("treelists")
    public ResFormMap findByPage() {
        ResFormMap resFormMap = getFormMap(ResFormMap.class);
        String order = " order by level asc";
        resFormMap.put("$orderby", order);
        List<ResFormMap> mps = sysResourcesMapper.findByNames(resFormMap);
        List<TreeObject> list = new ArrayList<TreeObject>();
        for (ResFormMap map : mps) {
            TreeObject ts = new TreeObject();
            Common.flushObject(ts, map);
            list.add(ts);
        }
        TreeUtil treeUtil = new TreeUtil();
        List<TreeObject> ns = treeUtil.getChildTreeObjects(list, 0);
        resFormMap = new ResFormMap();
        resFormMap.put("treelists", ns);
        return resFormMap;
    }
}
