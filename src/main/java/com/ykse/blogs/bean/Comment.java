package com.ykse.blogs.bean;

import java.sql.Timestamp;

public class Comment {
    
    private Integer         commentId;
    
    private String          commentContent;
    
    private Timestamp       createTime;
    
    private User            user;
    
    private Blogs           blogs;
    
    public Integer getCommentId() {
        return commentId;
    }
    
    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }
    
    public String getCommentContent() {
        return commentContent;
    }
    
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
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
    
    public Blogs getBlogs() {
        return blogs;
    }
    
    public void setBlogs(Blogs blogs) {
        this.blogs = blogs;
    }

}
