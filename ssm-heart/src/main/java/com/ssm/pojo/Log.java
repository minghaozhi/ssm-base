package com.ssm.pojo;


public class Log {

	//实体id
    private Long entityId;

    //实体类型
    private String entiyType;

    private String entityInfo;

    
    private String description;

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getEntiyType() {
        return entiyType;
    }

    public void setEntiyType(String entiyType) {
        this.entiyType = entiyType;
    }

    public String getEntityInfo() {
        return entityInfo;
    }

    public void setEntityInfo(String entityInfo) {
        this.entityInfo = entityInfo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
