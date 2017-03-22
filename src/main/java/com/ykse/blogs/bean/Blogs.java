package com.ykse.blogs.bean;

import java.sql.Timestamp;

public class Blogs {
    
	/** 帖子编号，主键 */
	private Integer blogsId;

	/** 帖子题目 */
	private String blogsTitle;

	/** 帖子内容 */
	private String blogsContent;

	/** 帖子阅读数 */
	private Integer viewCount;
	
	/** 帖子总评论数 */
	private Integer commentCount;

	/** 帖子赞成数 */
	private Integer support;

	/** 帖子不赞成数 */
	private Integer nonsupport;

	/** 帖子得分 */
	private Double rate;

	/** 创建时间 */
	private Timestamp createTime;
    
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
