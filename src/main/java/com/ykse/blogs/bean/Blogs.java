package com.ykse.blogs.bean;

import java.sql.Timestamp;

public class Blogs {
    
	private Integer blogsId;

	private String blogTitle;

	private String blogContent;

	private Integer viewCount;
	
	private Integer commentCount;

	private Integer support;

	private Integer nonsupport;

	private Double rate;

	private Timestamp createTime;
    
    private User            user;
    
    public Integer getBlogsId() {
        return blogsId;
    }
    
    public void setBlogsId(Integer blogsId) {
        this.blogsId = blogsId;
    }
    
    public String getBlogTitle() {
        return blogTitle;
    }
    
    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }
    
    public String getBlogContent() {
        return blogContent;
    }
    
    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }
     
    public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getSupport() {
		return support;
	}

	public void setSupport(Integer support) {
		this.support = support;
	}

	public Integer getNonsupport() {
		return nonsupport;
	}

	public void setNonsupport(Integer nonsupport) {
		this.nonsupport = nonsupport;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
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
