package com.ykse.blogs.bean;

import java.sql.Timestamp;

public class Comment {
    
    /** 评论编号，主键 */
    private Integer         commentId;
    
    /** 评论内容   */
    private String          commentContent;
    
    /** 创建时间   */
    private Timestamp       createTime;
    
    /** 发评论者   */
    private User            user;
    
    /** 所属帖子   */
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
