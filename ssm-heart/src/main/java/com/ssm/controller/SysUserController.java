package com.ssm.controller;

import com.ssm.pojo.SysUser;
import com.ssm.pojo.QueryVo;
import com.ssm.service.UserService;
import com.ssm.util.MessageResult;
import com.ssm.util.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 墨殇 on 2017/5/19.
 */
@Controller
@RequestMapping("sysUser")
public class SysUserController {

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    @Autowired
    private UserService userService;
    /**
     * 注册用户
     */

    @RequestMapping(value="register",method= RequestMethod.POST)
    public String register(SysUser sysUser){

        String name= sysUser.getLoginName();
        System.out.println("======================");
        System.out.println(name);

        boolean register = userService.register(sysUser);
        if(register){
            return "index";
        }
        return "register";
    }

    @RequestMapping(value="userList",method= RequestMethod.GET)
    public String userList(){
        return "user.jsp";
    }
    @RequestMapping(value = "findUser")
    @ResponseBody
    public Paging<SysUser> list(QueryVo<SysUser> vo, SysUser sysUser, Integer limit, Integer offset) throws Exception{

     vo.setEntity(sysUser);
     if(sysUser.getLoginName()!=null&& sysUser.getRealName()!=null) {
         sysUser.setLoginName(new String(sysUser.getLoginName().getBytes("iso-8859-1"), "utf-8"));
         sysUser.setRealName(new String(sysUser.getRealName().getBytes("iso-8859-1"), "utf-8"));

     }  Integer total = userService.getTotal(vo);
        Paging<SysUser> paging = new Paging<SysUser>();
        paging.setTotal(total);
        vo.setStartSize(offset);
        vo.setPageSize(limit);

        List<SysUser> list = userService.findUser(vo);
        paging.setRows(list);
        return paging;
    }



    @RequestMapping(value = "addUser",method = RequestMethod.POST)
    public ResponseEntity<MessageResult> addUser(SysUser sysUser){
        MessageResult result=null;
        try {
            Integer count = this.userService.add(sysUser);
            if(count>0){
                result = new MessageResult(0, "添加成功！");
            }else{
                result = new MessageResult(1, "添加失败！");
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }catch (Exception e){
            result = new MessageResult(1, "添加失败！");
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

}
