package com.ykse.blogs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ykse.blogs.bean.Comment;

/**
 * Comment表数据库操作接口
 * 
 * <pre>主要含：获取单个评论，获取评论列表，保存评论，删除评论，更新评论</pre>
 * 
 * @author tao.huang
 * @version $Id: CommentDao.java, v 0.1 2016年11月14日 下午6:35:09 tao.huang Exp $
 */
public interface CommentDao {
    
    /**
     * 根据id获取评论
     * 
     * @param commentId
     * @return
     */
    public Comment getCommentById(Integer commentId);
	
    /**
     * 根据博客id获取评论列表
     * 
     * @param blogsId
     * @param startRow
     * @param endRow
     * @return
     */
    public List<Comment> getCommentsByBlogsId(@Param("blogsId")Integer blogsId, @Param("startRow")int startRow, @Param("endRow")int endRow);
   
    /**
     * 保存评论
     * 
     * @param comment
     * @return
     */
	public int saveComment(Comment comment);
	
	/**
	 * 删除评论
	 * 
	 * @param commentId
	 * @return
	 */
	public int deleteComment(Integer commentId);
	
	/**
	 * 更新评论
	 * 
	 * @param comment
	 * @return
	 */
	public int updateComment(Comment comment);
	
}
