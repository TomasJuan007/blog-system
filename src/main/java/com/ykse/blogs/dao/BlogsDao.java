package com.ykse.blogs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ykse.blogs.bean.Blogs;

/**
 * Comment表数据库操作接口
 * 
 * <pre>主要含：获取单个博客，获取博客列表，获取博客数、按参数获取博客列表，按参数获取博客数、保存博客，删除博客，更新博客、增加评论数、减少评论数</pre>
 * 
 * @author tao.huang
 * @version $Id: BlogsDao.java, v 0.1 2016年11月14日 下午6:03:17 tao.huang Exp $
 */
public interface BlogsDao {
    
	/**
	 * 根据ID获取博客内容
	 * 
	 * @return
	 */
	public Blogs getBlogsById(int blogsId);
    
    /**
     * 获取博客
     * 
     * @param startRow
     * @param endRow
     * @return
     */
    public List<Blogs> getBlogsAll(@Param("startRow") int startRow, @Param("endRow") int endRow);
   
    /**
     * 获取博客总数，用于分页
     * 
     * @return
     */
    public Integer getBlogsCount();
    
    /**
     * 根据参数获取博客
     * 
     * @param blogs
     * @param startRow
     * @param endRow
     * @return
     */
    public List<Blogs> getBlogsByParam(@Param("blogs") Blogs blogs, @Param("startRow") int startRow, @Param("endRow") int endRow);
   
    /**
     * 根据参数获取博客总数，用于分页
     * 
     * @param blogs
     * @return
     */
    public Integer getBlogsCountByParam(@Param("blogs") Blogs blogs);
    
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
    
    /**
     * 根据ID更新支持反对数
     * 
     * @param blogsId
     * @return
     */
    public boolean updateSupportCountById(int blogsId);
    
    /**
     * 根据ID更新支持率
     * 
     * @param blogsId
     * @param rate
     * @return
     */
    public boolean updateSupportRateById(@Param("blogsId") int blogsId, @Param("rate") int rate);
}
