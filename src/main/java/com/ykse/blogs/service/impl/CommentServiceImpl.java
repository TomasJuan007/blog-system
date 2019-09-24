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

@Service  
public class CommentServiceImpl implements CommentService {

	@Autowired  
    private CommentDao commentDao;
	@Autowired
	private BlogsDao blogsDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
    public boolean saveComment(Comment comment) {
        if(commentDao.saveComment(comment) != 1)
            return false;
        int blogsId = comment.getBlogs().getBlogsId();
        return blogsDao.addCountById(blogsId);
    }

	@Override
    public List<Comment> getCommentsByBlogsId(Integer blogsId, int startRow, int endRow) {
        return commentDao.getCommentsByBlogsId(blogsId, startRow, endRow);
    }

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteComment(Integer commentId) {
	    Comment comment = commentDao.getCommentById(commentId);
	    int blogsId = comment.getBlogs().getBlogsId();
	    if(!blogsDao.subtractCountById(blogsId)) {
            return false;
        }
        return commentDao.deleteComment(commentId) == 1;
    }

    public boolean updateComment(Comment comment) {
        return commentDao.updateComment(comment) == 1;
    }

    public Comment getCommentById(Integer commentId) {
        return commentDao.getCommentById(commentId);
    }
}
