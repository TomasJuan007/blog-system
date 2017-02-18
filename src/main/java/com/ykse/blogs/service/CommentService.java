package com.ykse.blogs.service;

import java.util.List;

import com.ykse.blogs.bean.Comment;

/**
 * 
 * <li>主要含：获取单个评论，获取评论列表，保存评论，删除评论，更新评论</li>
 * 
 * @author tao.huang
 * @version $Id: CommentService.java, v 0.1 2016年11月14日 下午6:32:17 tao.huang Exp $
 */
public interface CommentService {
    
    /**
     * 根据commentId获取评论
     * 
     * @param commentId
     * @return
     */
    public Comment getCommentById(Integer commentId);
    
   /**
    * 根据blogsId获取评论
    * 
    * @param blogs_id
    * @return
    */
    public List<Comment> getCommentsByBlogsId(Integer blogs_id, int startRow, int endRow);
    
    /**
     * 插入评论
     * 
     * @param comment
     * @return 操作结果，true为成功，false为失败
     */
    public boolean saveComment(Comment comment);
    
    /**
     * 根据commentId获取评论
     * 
     * @param comment_id
     * @return
     */
    public boolean deleteComment(Integer comment_id);
    
    /**
     * 根据comment更新当前评论
     * 
     * @param comment
     * @return
     */
    public boolean updateComment(Comment comment);
    
}
