package cn.van.model;

import java.util.Date;

public class UserMobileMapDO {
    private Long userId;

    private String mobile;

    private Integer zoneNum;

    private String projectName;

    private Date createTime;

    private Date gmtModify;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Integer getZoneNum() {
        return zoneNum;
    }

    public void setZoneNum(Integer zoneNum) {
        this.zoneNum = zoneNum;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }
}