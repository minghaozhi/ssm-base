package com.ssm.controller;

import com.ssm.pojo.Sysuser;
import com.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 墨殇 on 2017/5/19.
 */
@Controller
@RequestMapping("sysUser")
public class SysUserController {
    @Autowired
    private UserService userService;
    /**
     * 注册用户
     */

    @RequestMapping(value="register",method= RequestMethod.POST)
    public String register(Sysuser sysuser, HttpServletRequest req){

        String name=req.getParameter("username");
        System.out.println("======================");
        System.out.println(name);
        String password=req.getParameter("password");
        boolean register = userService.register(name,password);
        if(register){
            return "index";
        }
        return "register";
    }

}
