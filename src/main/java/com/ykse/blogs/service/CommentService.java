package com.ykse.blogs.service;

import com.ykse.blogs.bean.Comment;

import java.util.List;

public interface CommentService {
    Comment getCommentById(Integer commentId);
    List<Comment> getCommentsByBlogsId(Integer blogs_id, int startRow, int endRow);
    boolean saveComment(Comment comment);
    boolean deleteComment(Integer comment_id);
    boolean updateComment(Comment comment);
}
