package com.ykse.blogs.bean;

import java.sql.Timestamp;

public class Files {

    private Integer filesId;

    private User user;

    private String fileName;

    private byte[] blobContent;

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

    public byte[] getBlobContent() {
        return blobContent;
    }

    public void setBlobContent(byte[] blobContent) { this.blobContent = blobContent; }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
