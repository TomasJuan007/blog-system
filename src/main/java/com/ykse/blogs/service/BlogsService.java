package com.ykse.blogs.service;

import com.ykse.blogs.bean.Blogs;

import java.util.List;

public interface BlogsService {
	Blogs getBlogsById(int blogsId);
	List<Blogs> getBlogsAll(String type, int startRow, int endRow);
	Integer getBlogsCount();
	List<Blogs> getBlogsByParam(Integer userId, String type, int startRow, int endRow);
	List<Blogs> getByParam(Blogs blogs, int startRow, int endRow);
	Integer getBlogsCountByParam(Integer userId);
	boolean saveBlogs(Blogs blogs);
    boolean updateBlogs(Blogs blogs);
    boolean updateViewCount(Blogs blogs);
    boolean deleteBlog(Integer blogId);
    boolean addCountById(int blogsId);
	boolean subtractCountById(int blogsId);
	boolean vote(int blogsId, int userId, int type);
	boolean cancelVote(int blogsId, int userId, int type);
}
