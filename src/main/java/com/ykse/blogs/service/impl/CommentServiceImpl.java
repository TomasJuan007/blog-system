package com.ykse.blogs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ykse.blogs.bean.Comment;
import com.ykse.blogs.dao.BlogsDao;
import com.ykse.blogs.dao.CommentDao;
import com.ykse.blogs.service.CommentService;

/**
 * 用户业务逻辑处理接口实现
 * 
 * @author huangtao
 * @version $Id: UserServiceImpl.java, v 0.1 2016年11月14日 下午4:49:21 huangtao Exp $
 */
@Service  
public class CommentServiceImpl implements CommentService {

	@Autowired  
    private CommentDao commentDao;
	@Autowired
	private BlogsDao blogsDao;

	/**
	 * 
	 * @see com.ykse.blogs.service.CommentService#saveComment(com.ykse.blogs.bean.Comment)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
    public boolean saveComment(Comment comment){
        if(commentDao.saveComment(comment) != 1)
            return false;
        int blogsId = comment.getBlogs().getBlogsId();
        if(blogsDao.addCountById(blogsId) != true)
            return false;
        return true;
    }
    
    /**
     * 
     * @see com.ykse.blogs.service.CommentService#getCommentByBlogsId(java.lang.Integer)
     */
	@Override
    public List<Comment> getCommentsByBlogsId(Integer blogsId, int startRow, int endRow) {
        List<Comment> comments = commentDao.getCommentsByBlogsId(blogsId, startRow, endRow);
        return comments;
    }
    
    /**
     * 
     * @see com.ykse.blogs.service.CommentService#deleteComment(java.lang.Integer)
     */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteComment(Integer commentId) {
	    Comment comment = commentDao.getCommentById(commentId);
	    int blogsId = comment.getBlogs().getBlogsId();
	    if(blogsDao.subtractCountById(blogsId) != true)
	        return false;
        if(commentDao.deleteComment(commentId) != 1)
            return false;
        return true;
    }
    
    /**
     * 
     * @see com.ykse.blogs.service.CommentService#updateComment(com.ykse.blogs.bean.Comment)
     */
    public boolean updateComment(Comment comment) {
        if(commentDao.updateComment(comment) != 1)
            return false;
        return true;
    }
    
    /**
     * 
     * @see com.ykse.blogs.service.CommentService#getCommentById(java.lang.Integer)
     */
    public Comment getCommentById(Integer commentId) {
        Comment comment = commentDao.getCommentById(commentId);
        if(comment != null) return comment;
        return null;
    }
    
}
