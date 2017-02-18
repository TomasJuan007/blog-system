package com.ykse.blogs.service;


import java.util.List;

import com.ykse.blogs.bean.Blogs;

public interface BlogsService {
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
    
    public Blogs getBlogsById(int blogsId);

    public boolean addCountById(int blogsId);
    
    public boolean subtractCountById(int blogsId);
    
    public Integer getBlogsCount();
    
}
