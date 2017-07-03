package com.ssm.controller;

import com.ssm.pojo.SysMoudle;
import com.ssm.service.SysMoudleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    //获取菜单数据，用json封装
    @RequestMapping(value="getModule", method= RequestMethod.GET)
    public ResponseEntity<List<SysMoudle>>getModule(){
        try {
        List<SysMoudle> list=new ArrayList<>();
        list=this.sysMoudleService.findAllMoudle();


        return ResponseEntity.ok(list);

    } catch (Exception e) {
        e.printStackTrace();
    }

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

  }
