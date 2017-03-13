package com.ykse.blogs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ykse.blogs.bean.Blogs;

public interface BlogsDao {
    
    /**
     * 获取博客
     * 
     * @param startRow
     * @param endRow
     * @return
     */
    public List<Blogs> getBlogsAll(@Param("startRow") int startRow, @Param("endRow") int endRow);
    
    /**
     * 根据ID获取博客内容
     * 
     * @return
     */
    public Blogs getBlogsById(int blogsId);
   
    /**
     * 
     * 保存博客
     * @param blogs
     * @return
     */
    public boolean saveBlogs(Blogs blogs);
   
    /**
     * 
     * 更新博客
     * @param blogs
     * @return
     */
    public boolean updateBlogs(Blogs blogs);
    
    /**
     * 根据ID删除博客
     * 
     * @param blogsId
     * @return
     */
    public int deleteBlogsById(Integer blogsId);
	
	/**
     * 获取博客总数，用于分页
     * 
     * @return
     */
    public Integer getBlogsCount();
    
    /**
     * 根据ID增加评论数
     * 
     * @param blogsId
     * @return
     */
    public boolean addCountById(int blogsId);
    
    /**
     * 根据ID减去评论数
     * 
     * @param blogsId
     * @return
     */
    public boolean subtractCountById(int blogsId);
}
