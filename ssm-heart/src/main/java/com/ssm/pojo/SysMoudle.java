package com.ssm.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 菜单实体类
 * Created by 墨殇 on 2017/7/3.
 */
@Table(name = "sys_moudle")
public class SysMoudle extends BasePojo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键自动生成策略
    private Long id;
    //编码
    private String code;
    //名称
    private String name;
    //父id
    private Long parentId;
    //层级
    private Long levels;
    //是否是叶子节点0表示是，1表示不是
    private Long hasChild;
    //图标
    private String img;
    //排序
    private Long priority;
    //描述
    private String description;
    //0表示有效，1表示无效
    private Long valid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Long getLevels() {
        return levels;
    }

    public void setLevels(Long levels) {
        this.levels = levels;
    }

    public Long getHasChild() {
        return hasChild;
    }

    public void setHasChild(Long hasChild) {
        this.hasChild = hasChild;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getValid() {
        return valid;
    }

    public void setValid(Long valid) {
        this.valid = valid;
    }
}
