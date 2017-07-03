package com.ssm.controller;

import com.ssm.pojo.SysMoudle;
import com.ssm.service.SysMoudleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 墨殇 on 2017/7/3.
 */
@Controller
@RequestMapping("sysMoudle")
public class SysMoudleController {

    @Autowired
    private SysMoudleService sysMoudleService;
    //获取所有的一级菜单数据
    @RequestMapping(value="getModulebyParentId", method= RequestMethod.GET)
    public ResponseEntity<List<SysMoudle>>getModule(@RequestParam("parentId")Long parentId){
        try {
        List<SysMoudle> list=new ArrayList<>();
        list=this.sysMoudleService.findAllMoudle(parentId);


        return ResponseEntity.ok(list);

    } catch (Exception e) {
        e.printStackTrace();
    }

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

  }
