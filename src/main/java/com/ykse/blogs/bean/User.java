package com.ykse.blogs.bean;

import java.sql.Timestamp;

public class User {
	
    /** 用户编号，主键  */
	private Integer        userId;
	
	/** 用户姓名   */
    private String         userName;
    
    /** 用户账号，以邮箱形式，唯一  */
    private String         userAccount;
    
    /** 用户登录密码   */
    private String         userPassword;
    
    /** 用户手机   */
    private String         userPhone;
    
    /** 用户性别，M or F */
    private String         userSex;
    
    /** 创建时间   */
    private Timestamp      createTime;
    
    /** 更新时间   */
    private Timestamp      updateTime;
    
    public Integer getUserId() {
        return userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getUserAccount() {
        return userAccount;
    }
    
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
    
    public String getUserPassword() {
        return userPassword;
    }
    
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    
    public String getUserPhone() {
        return userPhone;
    }
    
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
    
    public String getUserSex() {
        return userSex;
    }
    
    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }
    
    public Timestamp getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    
    public Timestamp getUpdateTime() {
        return updateTime;
    }
    
    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
     
}
