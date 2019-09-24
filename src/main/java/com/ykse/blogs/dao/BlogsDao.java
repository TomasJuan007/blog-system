package com.ykse.blogs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ykse.blogs.bean.Blogs;

public interface BlogsDao {
    Blogs getBlogsById(int blogsId);
    List<Blogs> getBlogsAll(@Param("startRow") int startRow, @Param("endRow") int endRow);
    Integer getBlogsCount();
    List<Blogs> getBlogsByParam(@Param("blogs") Blogs blogs, @Param("startRow") int startRow, @Param("endRow") int endRow);
    Integer getBlogsCountByParam(@Param("blogs") Blogs blogs);
    List<Blogs> getHeatedBlogsAll(@Param("startRow") int startRow, @Param("endRow") int endRow);
    List<Blogs>  getHeatedBlogsByParam(@Param("blogs") Blogs blogs, @Param("startRow") int startRow, @Param("endRow") int endRow);
    boolean saveBlogs(Blogs blogs);
    boolean updateBlogs(Blogs blogs);
    boolean updateViewCount(Blogs blogs);
    int deleteBlogsById(Integer blogsId);
    boolean addCountById(int blogsId);
    boolean subtractCountById(int blogsId);
    boolean updateSupportCountById(int blogsId);
    boolean updateSupportRateById(@Param("blogsId") int blogsId, @Param("rate") Double rate);
}
