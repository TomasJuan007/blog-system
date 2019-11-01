package com.ykse.blogs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ykse.blogs.bean.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDao {
	Comment getCommentById(Integer commentId);
	List<Comment> getCommentsByBlogsId(@Param("blogsId")Integer blogsId, @Param("startRow")int startRow, @Param("endRow")int endRow);
	int saveComment(Comment comment);
	int deleteComment(Integer commentId);
	int updateComment(Comment comment);
}
