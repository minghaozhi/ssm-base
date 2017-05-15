package com.ssm.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by 墨殇 on 2017/5/15.
 */
@Table(name="sys_log")
public class SysLog extends BasePojo{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    //执行操作
    private String executeOperation;

    //操作人
    private String operator;

    //操作数据信息
    private String entityInfo;

    //操作时间
    private Date operationTime;

    //日志描述
    private String description;

    //请求的ip地址
    private String requestIp;

    //请求机器的MAC编码
    private String macCode;

    //计算机名称
    private String computerName;

    //关联实体Id
    private Long entityId;

    //关联实体类型
    private String entityType;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExecuteOperation() {
        return executeOperation;
    }

    public void setExecuteOperation(String executeOperation) {
        this.executeOperation = executeOperation;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getEntityInfo() {
        return entityInfo;
    }

    public void setEntityInfo(String entityInfo) {
        this.entityInfo = entityInfo;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public String getMacCode() {
        return macCode;
    }

    public void setMacCode(String macCode) {
        this.macCode = macCode;
    }

    public String getComputerName() {
        return computerName;
    }

    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }
}
