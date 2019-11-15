package com.qf.domain;

import lombok.ToString;

import java.util.Date;
@ToString
public class LogInfo {
    private Integer logId;

    private String staffNumber;

    private String method;

    private String params;

    private Date executeTime;

    private String resultstatus;

    private String resultmsg;

    public LogInfo(Integer logId, String staffNumber, String method, String params, Date executeTime, String resultstatus, String resultmsg) {
        this.logId = logId;
        this.staffNumber = staffNumber;
        this.method = method;
        this.params = params;
        this.executeTime = executeTime;
        this.resultstatus = resultstatus;
        this.resultmsg = resultmsg;
    }

    public LogInfo() {
        super();
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(String staffNumber) {
        this.staffNumber = staffNumber == null ? null : staffNumber.trim();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }

    public String getResultstatus() {
        return resultstatus;
    }

    public void setResultstatus(String resultstatus) {
        this.resultstatus = resultstatus == null ? null : resultstatus.trim();
    }

    public String getResultmsg() {
        return resultmsg;
    }

    public void setResultmsg(String resultmsg) {
        this.resultmsg = resultmsg == null ? null : resultmsg.trim();
    }
}