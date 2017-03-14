package com.ykse.blogs.service;


import java.util.List;

import com.ykse.blogs.bean.Blogs;

public interface BlogsService {
	
	/**
	 * 根据Id获取博客
	 * 
	 * @param blogsId
	 * @return
	 */
	public Blogs getBlogsById(int blogsId);
	
	/**
     * 获取博客
     * @param endRow 
     * @param startRow 
     * 
     * @param userId
     * @return User
     */
	public List<Blogs> getBlogsAll(int startRow, int endRow); 
	
	/**
	 * 获取博客数
	 * 
	 * @return
	 */
	public Integer getBlogsCount();
	
	/**
	 * 根据参数获取博客
	 * 
	 * @param userId
	 * @param startRow
	 * @param endRow
	 * @return
	 */
	public List<Blogs> getBlogsByParam(Integer userId, int startRow, int endRow);
	
	/**
	 * 根据参数获取博客数
	 * 
	 * @param userId
	 * @return
	 */
	public Integer getBlogsCountByParam(Integer userId);
	
	/**
     * 发布博客
     * 
     * @param user 提供要修改的博客内容与标题
     * @return 操作结果，true为成功，false为失败
     */
    public boolean saveBlogs(Blogs blogs);
    
    /**
     * 修改博客
     * 
     * @param user 提供要修改的内容与标题
     * @return 操作结果，true为成功，false为失败
     */
    public boolean updateBlogs(Blogs blogs);
    
    /**
     * 根据Id删除博客
     * 
     * @param blogId
     * @return
     */
    public boolean deleteBlog(Integer blogId);
    
    /**
     * 根据Id增加评论数
     * 
     * @param blogsId
     * @return
     */
    public boolean addCountById(int blogsId);
    
    /**
     * 根据Id减评论数
     * 
     * @param blogsId
     * @return
     */
    public boolean subtractCountById(int blogsId);
}
