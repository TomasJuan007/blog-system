package com.ykse.blogs.bean;

public class Standpoint {

	/** 立场编号、主键 */
	private Integer standpointId;
	
	/** 帖子编号 */
	private Blogs blogs;

    /** 用户编号 */
	private User user;

    /** 立场类型  */
	private Integer type;

	public Integer getStandpointId() {
		return standpointId;
	}

	public void setStandpointId(Integer standpointId) {
		this.standpointId = standpointId;
	}

	public Blogs getBlogs() {
		return blogs;
	}

	public void setBlogs(Blogs blogs) {
		this.blogs = blogs;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	
}
