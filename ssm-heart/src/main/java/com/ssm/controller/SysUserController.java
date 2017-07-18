package com.ssm.controller;

import com.ssm.Log.SystemControllerLog;
import com.ssm.pojo.Log;
import com.ssm.pojo.SysLog;
import com.ssm.pojo.SysUser;
import com.ssm.pojo.QueryVo;
import com.ssm.service.SysResourcesService;
import com.ssm.service.UserService;
import com.ssm.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 墨殇 on 2017/5/19.
 */
@Controller
@RequestMapping("sysUser")
public class SysUserController extends BaseController{

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    @Autowired
    private UserService userService;

    @Autowired
    private SysResourcesService sysResourcesService;
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


    @RequestMapping(value = "addUser",method = RequestMethod.POST)
    @SystemControllerLog(description = "添加用户")
    public ResponseEntity<MessageResult> addUser(SysUser sysUser,Integer flag,Log log){
        MessageResult result=null;
        try {
            Integer count = this.userService.add(sysUser,flag,log);
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


    @RequestMapping(value = "findUserById",method = RequestMethod.POST)
    public SysUser findUserById(@RequestParam("id") Long id){
        SysUser sysUser=this.userService.findUserById(id);
        return  sysUser;
    }


    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String listUI(Model model) throws Exception {
        model.addAttribute("res", findByRes());
        return "/jsp/system/user/list";
    }
    @ResponseBody
    @RequestMapping(value = "findByPage")
    public PageView findByPage(String pageNow,
                               String pageSize, String column, String sort) throws Exception {
        UserFormMap userFormMap = getFormMap(UserFormMap.class);
        userFormMap=toFormMap(userFormMap, pageNow, pageSize,userFormMap.getStr("orderby"));
        userFormMap.put("column", column);
        userFormMap.put("sort", sort);
        int endNumber=0;
        int startNumber=0;
        if (pageNow==null){
            pageNow="0";
            endNumber=Integer.parseInt(pageSize);
            startNumber=0;
        }else {
            endNumber = Integer.parseInt(pageNow) * Integer.parseInt(pageSize);
            startNumber = endNumber - Integer.parseInt(pageSize);
        }
        userFormMap.put("endNumber", endNumber);
        userFormMap.put("startNumber", startNumber);

        pageView.setRecords(userService.findUserPage(userFormMap));//不调用默认分页,调用自已的mapper中findUserPage
        pageView.setRowCount(userService.getTotal(userFormMap));
        return pageView;
    }

    @RequestMapping("addUI")
    public String addUI(Model model) throws Exception {
        return  "/jsp/system/user/add";
    }

    /**
     * 验证账号是否存在
     * @param name
     * @return
     */
    @RequestMapping("isExist")
    @ResponseBody
    public boolean isExist(String name) {
       SysUser sysUser=this.userService.findUserByLoginName(name);
        if (sysUser == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 进入修改用户页面
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("editUI")
    public ModelAndView editUI(Long id) throws Exception {
        ModelAndView mv=new ModelAndView();
       SysUser sysUser=this.userService.findUserById(id);
       mv.addObject("sysUser",sysUser);
       mv.setViewName("/jsp/system/user/edit");
       return mv;

    }

    /**
     * 修改用户
     * @param sysUser
     * @param flag
     * @param log
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "update",method = RequestMethod.POST)
    @Transactional(readOnly=false)//需要事务操作必须加入此注解
    @SystemControllerLog(description = "修改用户")//凡需要处理业务逻辑的.都需要记录操作日志
    public ResponseEntity<MessageResult> update(SysUser sysUser, Integer flag, Log log){
        MessageResult result=null;
        try {
            Integer count = this.userService.update(sysUser,flag,log);
            if(count>0){
                result = new MessageResult(0, "修改成功！");
            }else{
                result = new MessageResult(1, "修改失败！");
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }catch (Exception e){
            result = new MessageResult(1, "修改失败！");
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * 删除用户
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("delete")
    @Transactional(readOnly=false)//需要事务操作必须加入此注解
    @SystemControllerLog(description = "删除用户")//凡需要处理业务逻辑的.都需要记录操作日志
    public ResponseEntity<MessageResult> deleteById(Long[] ids, Log log){

        MessageResult result = null;

        try {
            Integer count = this.userService.deleteByIds(ids,log);

            if(count>0){
                result = new MessageResult(0, "删除成功！");
            }else{
                result = new MessageResult(1, "删除失败！");
            }

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }

}