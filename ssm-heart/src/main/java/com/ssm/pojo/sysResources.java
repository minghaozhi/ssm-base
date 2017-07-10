package com.ssm.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 菜单实体类
 * Created by 墨殇 on 2017/7/10.
 */
@Table(name = "sys_resources")
public class sysResources {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键自动生成策略
    private Long id;

    //名称
    private String name;

    private Long parentId;

    private String resKey;

    private String type;

    private String resUrl;

    private Long level;

    private String icon;

    private Integer ishide;

    private String description;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getResKey() {
        return resKey;
    }

    public void setResKey(String resKey) {
        this.resKey = resKey;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getIshide() {
        return ishide;
    }

    public void setIshide(Integer ishide) {
        this.ishide = ishide;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
