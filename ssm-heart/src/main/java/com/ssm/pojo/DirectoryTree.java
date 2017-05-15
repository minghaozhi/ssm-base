package com.ssm.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by 墨殇 on 2017/5/15.
 */
@Table(name = "sys_directory_tree")
public class DirectoryTree {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    //编码
    private String code;

    //名称
    private String name;

    //父模块ID
    private Long parentId;

    //是否叶子节点
    private Integer isLeaf;
    //显示名称
    private String displayName;

    //访问链接
    private String url;

    //菜单或按钮图标
    private String img;


    //描述
    private String description;

    //有效
    private int valid;
    //排序
    private  int priority;
}
