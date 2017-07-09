package com.ssm.common;

import com.ssm.pojo.SysUser;

/**
 * Created by 墨殇 on 2017/6/29.
 */
public class UserLocal {

        //声明一个存放用户的ThreadLocal
        private static ThreadLocal<SysUser> threadLocal = new ThreadLocal<SysUser>();

        public static void setUser(SysUser user){
            threadLocal.set(user);
        }

        public static SysUser getUser(){
            SysUser user = threadLocal.get();
            return user;
        }


    }
