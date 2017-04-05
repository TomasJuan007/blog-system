package com.ykse.blogs.bean;

import java.sql.Blob;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2017/4/5.
 */
public class Files {

    /** 文件编号，主键 */
    private Integer filesId;

    /** 发帖人   */
    private User user;

    /**文件名称 */
    private String fileName;

    /**文件 */
    private Object blobContent;

    /** 创建时间 */
    private Timestamp createTime;

    public Integer getFilesId() {
        return filesId;
    }

    public void setFilesId(Integer filesId) {
        this.filesId = filesId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFileName() { return fileName; }

    public void setFileName(String fileName) { this.fileName = fileName; }

    public Object getBlobContent() {
        return blobContent;
    }

    public void setBlobContent(Object blobContent) { this.blobContent = blobContent; }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
