package com.ykse.blogs.bean;

import java.sql.Timestamp;

public class Blogs {
    
    /** 帖子编号，主键   */
    private Integer         blogsId;
    
    /** 帖子题目  */
    private String          blogsTitle;
    
    /** 帖子内容   */
    private String          blogsContent;
    
    /** 帖子总评论数   */
    private Integer         commentCount;
    
    /** 创建时间   */
    private Timestamp       createTime;
    
    /** 发帖人   */
    private User            user;
    
    public Integer getBlogsId() {
        return blogsId;
    }
    
    public void setBlogsId(Integer blogsId) {
        this.blogsId = blogsId;
    }
    
    public String getBlogsTitle() {
        return blogsTitle;
    }
    
    public void setBlogsTitle(String blogsTitle) {
        this.blogsTitle = blogsTitle;
    }
    
    public String getBlogsContent() {
        return blogsContent;
    }
    
    public void setBlogsContent(String blogsContent) {
        this.blogsContent = blogsContent;
    }
     
    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
     
}
