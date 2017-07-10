package com.ssm.pojo;

import javax.persistence.Table;

/**
 * Created by 墨殇 on 2017/7/10.
 */
@Table(name = "sys_res_user")
public class SysResUser {

    private Long resId;

    private Long userId;

    public Long getResId() {
        return resId;
    }

    public void setResId(Long resId) {
        this.resId = resId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
