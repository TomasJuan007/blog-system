package com.ykse.blogs.bean;

public class Standpoint {

	private Integer standpointId;
	
	private Blogs blogs;

	private User user;

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
