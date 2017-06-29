package com.ssm.common;

import com.ssm.pojo.Sysuser;

/**
 * Created by 墨殇 on 2017/6/29.
 */
public class UserLocal {

        //声明一个存放用户的ThreadLocal
        private static ThreadLocal<Sysuser> threadLocal = new ThreadLocal<Sysuser>();

        public static void setUser(Sysuser user){
            threadLocal.set(user);
        }

        public static Sysuser getUser(){
            Sysuser user = threadLocal.get();
            return user;
        }


    }
